/**
 * 
 */
package com.www.utils.orm;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

/**
 * hibernate接口类
 * @author www
 *
 */
public interface IHibernateDB {
	/**
	 * 获取session对象
	 * @return
	 */
	public abstract Session getSession();
	/**
	 * 批量新增，返回主键集合
	 * @param entityList 实体对象集合
	 * @return 主键集合
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Serializable> saveBtach(List entityList) ;
	/**
	 * 插入数据，返回主键值
	 * @param entity 实体对象
	 * @return 主键值
	 */
	public abstract Serializable save(Object entity);
	
	/**
	 * 删除数据，
	 * @param entity 实体对象
	 */
	public abstract void delete(Object entity);
	/**
	 * 批量删除数据，
	 * @param entityList 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public abstract void deleteBatch(List entityList);
	/**
	 * 更新数据，
	 * @param entity 实体对象
	 */
	public abstract void update(Object entity);
	/**
	 * 批量更新数据，
	 * @param entity 实体对象
	 */
	@SuppressWarnings("rawtypes")
	public abstract void updateBatch(List entityList);
	/**
	 * 获取序列值
	 * @param seqName 序列名
	 * @return
	 */
	public abstract String getSequence(String seqName);
	/**
	 * 获取当前数据库时间
	 * @return Date类型时间
	 */
	public abstract Date getDBDate();
	/**
	 * 获取当前数据库时间
	 * @return Timestamp类型时间
	 */
	public abstract Timestamp getDBTimestamp();
	/**
	 * 查询单条数据,返回object对象
	 * @param sql 完整的查询语句
	 */
	public abstract Object queryUniqueBySql(String sql);
	/**
	 * 查询单条数据,查询返回bean对象
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return bean对象
	 */
	public abstract <T> T queryUniqueBySqlClassWithString(String sql,Class<T> toClass);
	/**
	 * 查询全部数据，查询返回bean对象几个
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return List<T> 多条数据集合
	 */
	public abstract <T> List<T> queryBySqlClassWithString(String sql,Class<T> toClass);
	/**
	 * 按分页查询多条数据,分页查询返回为bean对象集合
	 * @param <T> 类名
	 * @param sql 完整的查询语句
	 * @param toClass 类
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return List<T> 多条数据集合
	 */
	public abstract <T> List<T> queryBySqlClassWithString(String sql,Class<T> toClass,Integer page,Integer pageSize);
	/**
	 * 查询条数,通过变量赋值查询计数
	 * @param sql 待赋值的查询语句
	 * @param obj 存值对象
	 * @return count计数
	 */
	public abstract int getCountBySqlWithObject(String sql,Object obj);
	/**
	 * 查询条数,查询计数
	 * @param sql 完整的查询语句
	 * @return count计数
	 */
	public abstract int getCountBySqlWithString(String sql);
	/**
	 * 查询单条数据,通过对变量赋值查询返回bean对象
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return bean对象
	 */
	public abstract <T> T queryUniqueBySqlClassWithObject(String sql,Object obj,Class<T> toClass);
	/**
	 * 查询全部数据,通过对变量赋值查询得到bean对象集合
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @return List<T> 多条数据集合
	 */
	public abstract <T> List<T> queryBySqlClassWithObject(String sql,Object obj,Class<T> toClass);
	/**
	 * 按分页查询多条数据,通过对变量赋值进行分页查询瞪大bean对象集合
	 * @param <T> 类名
	 * @param sql 待赋值的查询语句
	 * @param obj 存值对象
	 * @param toClass 类
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return List<T> 多条数据集合
	 */
	public abstract <T> List<T> queryBySqlClassWithObject(String sql,Object obj,Class<T> toClass,Integer page,Integer pageSize);
	/**
	 * 按分页查询多条数据,返回为集合
	 * @param sql 完整的查询语句
	 * @param page 查询的页数
	 * @param pageSize 查询的条数
	 * @return list集合
	 */
	@SuppressWarnings("rawtypes")
	public abstract List queryBySql(String sql,Integer page,Integer pageSize);
}
