/**
 * 
 */
package com.www.init;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.www.app.entity.Sysrole;
import com.www.utils.CacheUtils;
import com.www.utils.orm.IHibernateDB;



/**
 * 缓存初始化,实现InitializingBean接口可在应用启动后自动初始化缓存
 * @author www
 */
@Component
public class CacheInit implements InitializingBean {
	@Resource
	private IHibernateDB op;
	/**日志*/
	private Logger logger=Logger.getLogger(CacheInit.class);
	/**
	 * 应用启动后自动初始化缓存
	 */
	public void afterPropertiesSet() throws Exception {
		cacheInit();
	}
	/**
	 * 缓存初始化
	 */
	public void cacheInit(){
		logger.info("正在加载角色菜单。。。。。。。。。。。。。。。。");
		List<Sysrole> roleList=op.queryBySqlClassWithString("select * from sysrole", Sysrole.class);
		CacheUtils.putCaches("role", roleList);
		logger.info("加载角色菜单完成。。。。。。。。。。。。。。。。");
	}
	
	
}
