/**
 * 
 */
package com.www.utils.orm;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.www.utils.BeanUtils;
import com.www.utils.CodeUtils;
import com.www.utils.LogExceptionUtils;
import com.www.utils.StringUtils;


/**
 * hibernate实现类
 * @author www
 */
@Component
public class HibernateDB implements IHibernateDB {
	
	/**日志对象*/
	private Logger logger=Logger.getLogger(HibernateDB.class);
	
	public HibernateDB(){
	}
	/**
	 * 获取session对象
	 */
	public Session getSession() {
		return HibernateHelper.currentSession();
	}
	/**
	 * 批量新增，返回主键集合
	 * @param entityList 实体对象集合
	 * @return 主键集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Serializable> saveBtach(List entityList) {
		if (BeanUtils.isEmpty(entityList)) {
			return null;
		}
		List<Serializable> result=new ArrayList<Serializable>();
		for (Object entity:entityList) {
			Serializable key = getSession().save(entity);
			getSession().flush();
			if (key!=null) {
				result.add(key);
			}
		}
		return result;
	}
	/**
	 * 插入数据，返回主键值
	 * @param entity 实体对象
	 * @return 主键值
	 */
	public Serializable save(Object entity) {
		if (BeanUtils.isEmpty(entity)) {
			return null;
		}
		Serializable key = getSession().save(entity);
		return key;
	}
	/**
	 * 删除数据，
	 * @param entity 实体对象
	 */
	public void delete(Object entity) {
		if (BeanUtils.isEmpty(entity)) {
			return;
		}
		getSession().delete(entity);
	}
	/**
	 * 批量删除数据，
	 * @param entityList 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public void deleteBatch(List entityList) {
		if (BeanUtils.isEmpty(entityList)) {
			return;
		}
		for (Object entity:entityList) {
			getSession().delete(entity);
		}
	}
	/**
	 * 更新数据，
	 * @param entity 实体对象
	 */
	public void update(Object entity) {
		if (BeanUtils.isEmpty(entity)) {
			return;
		}
		getSession().update(entity);
	}
	/**
	 * 批量更新数据，
	 * @param entity 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public void updateBatch(List entityList) {
		if (BeanUtils.isEmpty(entityList)) {
			return;
		}
		for (Object entity:entityList) {
			getSession().update(entity);
		}
	}
	/**
	 * 获取序列值
	 * @param seqName 序列名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getSequence(String seqName) {
		List ls =queryBySql("select "+seqName+".nextval from dual", null, null);
		if (ls != null && ls.size() == 1)
			return ((Number) ls.get(0)).toString();
		else
			return "";
	}
	/**
	 * 获取当前数据库时间
	 * @return Date类型时间
	 */
	public Date getDBDate() {
		try {
			String dbdate = (String) queryUniqueBySql("select to_char(sysdate,'yyyy-mm-dd HH24:mi:ss') dbdate from dual");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = format.parse(dbdate);
			return date==null?null:new Date(date.getTime());
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
			return null;
		}
	}

	/**
	 * 获取当前数据库时间
	 * @return Timestamp类型时间
	 */
	public Timestamp getDBTimestamp() {
		Date date = getDBDate();
		return date==null?null:new Timestamp(date.getTime());
	}
	/**
	 * 查询单条数据,返回object对象
	 * @param sql 完整的查询语句
	 */
	@SuppressWarnings("rawtypes")
	public Object queryUniqueBySql(String sql) {
		List ls = queryBySql(sql, Integer.valueOf(1), Integer.valueOf(1));
		if (ls != null && ls.size() > 0)
			return ls.get(0);
		else
			return null;
	}
	/**
	 * 查询单条数据,查询返回bean对象
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return bean对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T queryUniqueBySqlClassWithString(String sql,Class<T> toClass) {
		List ls=queryBySqlClassWithString(sql, toClass,Integer.valueOf(1),Integer.valueOf(1));
		if (ls==null||ls.size()==0) {
			return null;
		}
		return (T) ls.get(0);
	}
	/**
	 * 查询全部数据，查询返回bean对象几个
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return List<T> 多条数据集合
	 */
	public <T> List<T> queryBySqlClassWithString(String sql,Class<T> toClass){
		return queryBySqlClassWithString(sql,toClass,null,null);
	}
	/**
	 * 按分页查询多条数据,分页查询返回为bean对象集合
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param toClass 类
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return List<T> 多条数据集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> queryBySqlClassWithString(String sql,Class<T> toClass,Integer page,Integer pageSize){
		try {
			if (toClass.newInstance() instanceof List) {//泛型为list类不进行查询
				return null;
			}
			sql=modifySql(sql);
			SQLQuery re = getSession().createSQLQuery(sql);
			re.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if ((page!=null&&page>0)&&(pageSize!=null&&pageSize>0)) {//设置查询页数和查询数目
				re.setFirstResult((page.intValue()-1)*pageSize.intValue()).setMaxResults(pageSize);
			}
			List ls = re.list();
			if (ls==null||ls.size()==0) {
				return null;
			}
			List result=new ArrayList();
			for (int i = 0; i < ls.size(); i++) {
				Map  map=(Map)ls.get(i);
				T bean = toClass.newInstance(); 
				bean=(T) BeanUtils.mapToObject(map, bean);
				result.add(bean);
			}
			return result;
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
		} 
		return null;
	} 
	/**
	 * 查询条数,通过变量赋值查询计数
	 * @param sql 待赋值的查询语句，格式如：userId=:userId
	 * @param obj 存值对象
	 * @return count计数
	 */
	@SuppressWarnings("rawtypes")
	public int getCountBySqlWithObject(String sql,Object obj){
		List ls=queryBySqlWithObject(sql, obj,Integer.valueOf(1),Integer.valueOf(1));
		if (ls!=null&&ls.size()==1) {
			return ((Number)ls.get(0)).intValue();
		}
		return 0;
	}
	/**
	 * 查询条数,查询计数
	 * @param sql 完整的查询语句
	 * @return count计数
	 */
	@SuppressWarnings("rawtypes")
	public int getCountBySqlWithString(String sql){
		List ls=queryBySql(sql, null,null);
		if (ls!=null&&ls.size()==1) {
			return ((Number)ls.get(0)).intValue();
		}
		return 0;
	}
	/**
	 * 查询单条数据,通过对变量赋值查询返回bean对象
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句，格式如：userId=:userId
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return bean对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T queryUniqueBySqlClassWithObject(String sql,Object obj,Class<T> toClass) {
		List ls=queryBySqlClassWithObject(sql, obj,toClass,Integer.valueOf(1),Integer.valueOf(1));
		if (ls==null||ls.size()==0) {
			return null;
		}
		return (T) ls.get(0);
	}
	/**
	 * 查询全部数据,通过对变量赋值查询得到bean对象集合
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句，格式如：userId=:userId
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return List<T> 多条数据集合
	 */
	public <T> List<T> queryBySqlClassWithObject(String sql,Object obj,Class<T> toClass){
		return queryBySqlClassWithObject(sql,obj, toClass,null,null);
	}
	/**
	 * 按分页查询多条数据,通过对变量赋值进行分页查询瞪大bean对象集合
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句，格式如：userId=:userId
	 * @param obj 存值对象
	 * @param toClass 类
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return List<T> 多条数据集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> queryBySqlClassWithObject(String sql,Object obj,Class<T> toClass,Integer page,Integer pageSize){
		try {
			if (toClass.newInstance() instanceof List) {//泛型为list类不进行查询
				return null;
			}
			sql=setSqlFieldValue(sql, obj);
			SQLQuery re = getSession().createSQLQuery(sql);
			re.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			if ((page!=null&&page>0)&&(pageSize!=null&&pageSize>0)) {//设置查询页数和查询数目
				re.setFirstResult((page.intValue()-1)*pageSize.intValue()).setMaxResults(pageSize);
			}
			List ls = re.list();
			if (ls==null||ls.size()==0) {
				return null;
			}
			List result=new ArrayList();
			for (int i = 0; i < ls.size(); i++) {
				Map  map=(Map)ls.get(i);
				T bean = toClass.newInstance(); 
				bean=(T) BeanUtils.mapToObject(map, bean);
				result.add(bean);
			}
			return result;
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
		} 
		return null;
	}
	/**
	 * 按分页查询多条数据,分页查询返回为集合
	 * @param sql 待赋值的查询语句，格式如：userId=:userId
	 * @param obj 存值对象
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return list集合
	 */
	@SuppressWarnings("rawtypes")
	private List queryBySqlWithObject(String sql,Object obj,Integer page,Integer pageSize){
		try {
			sql=setSqlFieldValue(sql, obj);
			SQLQuery re = getSession().createSQLQuery(sql);
			if ((page!=null&&page>0)&&(pageSize!=null&&pageSize>0)) {//设置查询页数和查询数目
				re.setFirstResult((page.intValue()-1)*pageSize.intValue()).setMaxResults(pageSize);
			}
			List ls = re.list();
			if (ls==null||ls.size()==0) {
				return null;
			}
			return ls;
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
		} 
		return null;
	}
	/**
	 * 按分页查询多条数据,返回为集合
	 * @param sql 完整的查询语句
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return list集合
	 */
	@SuppressWarnings("rawtypes")
	public List queryBySql(String sql,Integer page,Integer pageSize){
		try {
			sql=modifySql(sql);
			SQLQuery re = getSession().createSQLQuery(sql);
			if ((page!=null&&page>0)&&(pageSize!=null&&pageSize>0)) {//设置查询页数和查询数目
				re.setFirstResult((page.intValue()-1)*pageSize.intValue()).setMaxResults(pageSize);
			}
			List ls = re.list();
			if (ls==null||ls.size()==0) {
				return null;
			}
			return ls;
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
		} 
		return null;
	}
	/**
	 * 对sql语句里面的变量进行赋值或者删除空的条件
	 * @param sql 查询语句
	 * @param obj 存值对象
	 * @return 赋值后的sql语句
	 */
	private String setSqlFieldValue(String sql,Object obj){
		sql=modifySql(sql);
		int mhindex = 0;
		while ((mhindex = sql.indexOf(":")) > -1) {//存在待赋值的变量
			String befStr = sql.substring(0, mhindex);//冒号前的sql语句
			mhindex++;
			String minStr = sql.substring(mhindex);//冒号后的sql语句
			String fieldName = getFiledName(minStr);//获取字段名称
			String aftStr = sql.substring(mhindex + fieldName.length());//变量后面的sql语句
			Object fieldValue = getFieldValue(obj, fieldName);//字段赋值
			int kgNum=StringUtils.getStrCount(befStr, " ");
			//变量前的关系符。可能的值：like = > < >= <= != <> between in( and or ,
			String temp=befStr.substring(StringUtils.getStrIndex(befStr, " ", kgNum-1), StringUtils.getStrIndex(befStr, " ", kgNum)-1);
			//获取befStr倒数第3个空格和倒数第4个空格之间的字符串
			String ds34=befStr.substring(StringUtils.getStrIndex(befStr, " ", kgNum-3), StringUtils.getStrIndex(befStr, " ", kgNum-2)-1);
			if (BeanUtils.isEmpty(fieldValue)) {//字段值为空则直接删除这个条件
				if ("(".equals(ds34)) {//要删除的条件在括号内，即and ( userid =
					if (StringUtils.isInStrs(temp, "=","<",">",">=","<=","<>","!=","like")) {//and ( userid = :userid
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-2));
					}else if ("between".equals(temp)){//and ( userid between :userid1 and :userid2
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-2));
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 3), aftStr.length());
					}else if (StringUtils.isInStrs(temp, "and","or")) {//and (  and :userid between userid and username
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-1));
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 5), aftStr.length());
					}else if ("in(".equals(temp)) {//and ( userid in( :userid
						if (aftStr.startsWith(" , ")) {
							aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
						}else if (aftStr.startsWith(" ) ")){
							befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-2));
							aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
						}
					}
				}else if (StringUtils.isInStrs(ds34, "and","or")){//要删除的条件在括号外, and userid =/between :userid
					if (StringUtils.isInStrs(temp, "=","<",">",">=","<=","<>","!=","like")) {//and userid = :userid
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-3));
					}else if ("between".equals(temp)) {//and userid between :userid
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-3));
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 3), aftStr.length());
					}else if ("in(".equals(temp)) {//and userid in( :userid
						if (aftStr.startsWith(" , ")) {
							aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
						}else if (aftStr.startsWith(" ) ")){
							befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-3));
							aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
						}
					}
				}else if ("between".equals(ds34)){//and/( userid between 'www' and :userid
					String ds56=befStr.substring(StringUtils.getStrIndex(befStr, " ", kgNum-5), StringUtils.getStrIndex(befStr, " ", kgNum-4)-1);
					if ("(".equals(ds56)) {//and ( userid between 'www' and :userid
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-4));
					}else {//and userid between 'www' and :userid
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-5));
					}
				}else if ("in(".equals(ds34)) {//and userid in( 'www' ,
					if (aftStr.startsWith(" , ")) {
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
					}
				}else {
					if ("(".equals(temp)&&aftStr.startsWith(" between")) {//and ( :userid between userid and username
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 5), aftStr.length());
					}else if ("(".equals(temp)&&aftStr.startsWith(" in(")) {//这种情况and ( :userid in( userid , username ) 
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, ")", 1), aftStr.length());
					}else if (StringUtils.isInStrs(temp, "and","or")&&aftStr.startsWith(" in(")) {//and :userid in( userid , username )
						befStr=befStr.substring(0, StringUtils.getStrIndex(befStr, " ", kgNum-1));
						aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, ")", 1), aftStr.length());
					}else if (StringUtils.isInStrs(temp, ",","in(")) {//and /( userid in( 'www' , 'www' ,  或者and /( userid in( 'www' ,
						if (aftStr.startsWith(" , ")) {
							aftStr=aftStr.substring(StringUtils.getStrIndex(aftStr, " ", 2), aftStr.length());
						}
					}
				}
				sql=befStr+aftStr;
			}else {//字段值不为空则填进去
				String value=fieldValue.toString();
				value=value.replace(" ", CodeUtils.produce32MD5(" "));//变量的值中有空格,将空格转换成md5，避免对后面的影响
				if ("like".equals(temp)) {//符号是like
					sql=befStr+"'%"+value+"%'"+aftStr;
				}else{//符号非like
					sql=befStr+"'"+value+"'"+aftStr;
				}
			}
		}
		sql = sql.replaceAll("\\s+"," ");
		sql=sql.replace("and ( )", "");
		sql=sql.replace(", )", ")");
		sql=sql.replace("( or", "(");
		sql=sql.replace("( and", "(");
		sql=sql.replace(CodeUtils.produce32MD5(" "), " ");//变量的值中有空格,将空格转换成md5，恢复空格
		logger.info("赋值后的sql=> "+sql);
		return sql;
	}
	/**
	 * 对sql语句进行格式修改
	 * @param sql
	 * @return
	 */
	private String modifySql(String sql){
		logger.info("格式化前的sql=> "+sql);
		sql=sql.toLowerCase()+" ";//换成小写
		sql = sql.replaceAll("\\s*<\\s*", " < ");
		sql = sql.replaceAll("\\s*,\\s*", " , ");
		sql = sql.replaceAll("\\s*>\\s*", " > ");
		sql = sql.replaceAll("\\s*=\\s*", " = ");
		sql = sql.replaceAll("\\s*\\(\\s*", " ( ");
		sql = sql.replaceAll("\\s*\\)\\s*", " ) ");
		sql = sql.replaceAll("\\s*>\\s*=\\s*", " >= ");
		sql = sql.replaceAll("\\s*<\\s*=\\s*", " <= ");
		sql = sql.replaceAll("\\s*!\\s*=\\s*", " != ");
		sql = sql.replaceAll("\\s*<\\s*>\\s*", " <> ");
		sql = sql.replaceAll("\\s*in\\s*\\(\\s*", " in( ");
		sql = sql.replaceAll("\\s+"," ");//匹配任何空白字符，包括空格、制表符、换页符等,全部换成一个空格
		sql = appendAfterWhenNotExist(sql, "where", " 1 = 1"," 1 = 1 and");
		logger.info("格式化后的sql=> "+sql);
		return sql;
	}
	/**
	 * 对字段进行赋值
	 * @param value 存储值的对象
	 * @param fieldName 字段名
	 * @return 该字段的值
	 */
	@SuppressWarnings("rawtypes")
	private  Object getFieldValue(Object value, String fieldName) {
		try {
			if (BeanUtils.isEmpty(value))
				return null;
			if (BeanUtils.isEmpty(fieldName))
				return null;
			if (value instanceof Map)
				return ((Map) value).get(fieldName);
			else{
				Field[] fields = value.getClass().getDeclaredFields(); 
				Object obj=null;
				for (Field field : fields) {    
					field.setAccessible(true); 
					if (fieldName.toUpperCase().equals(field.getName().toUpperCase())) {
						obj=field.get(value);//获取此属性的值
						break;
					}
				}
				return obj;
			}
		} catch (Exception e) {
			LogExceptionUtils.catchException(e, HibernateDB.class);
		} 
		return null;
	}
	/**
	 * 获取字段名称
	 * @param sql
	 * @return
	 */
	private  String getFiledName(String sql) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sql.length(); i++) {
			char c = sql.charAt(i);
			if (StringUtils.isInStrs(String.valueOf(c), " ", ",",")", "\t", "\n", "\r" ))
				return sb.toString();
			sb.append(c);
		}
		return sb.toString();
	}
	/**
	 * 判断sql语句where后面是否有条件
	 * @param str
	 * @param afterStr
	 * @param existKey
	 * @param notExistAppend
	 * @return
	 */
	private String appendAfterWhenNotExist(String str, String afterStr,String existKey, String notExistAppend) {
		int begin = 0;
		int minwhere = -1;
		StringBuilder sb = new StringBuilder();
		String af = "";
		if (str.indexOf(afterStr) == -1)//不存在where
			return str;
		while ((minwhere = str.indexOf(afterStr, begin)) > -1) {
			minwhere += afterStr.length();
			String bef = str.substring(begin, minwhere);//where前字符串
			af = str.substring(minwhere);//where后字符串
			begin = minwhere;
			if (!startsWithIgnoreCase(af, existKey)) {
				sb.append(bef);
				if ((af!=null && af.length()==0)||" ".equals(af)) {//where后面没条件，只有空格或者什么都没有
					sb.append(existKey);
				}else {
					sb.append(notExistAppend);
				}
			} else {
				sb.append(bef);
			}
		}
		sb.append(af);
		return sb.toString();
	}
	/**
	 * 判断字符串str是否已prefix字符串开头
	 * @param str
	 * @param prefix
	 * @return true是，false否
	 */
	private  boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null )
			return false;
		if (str.startsWith(prefix))
			return true;
		if (str.length() < prefix.length())
			return false;
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}
}
