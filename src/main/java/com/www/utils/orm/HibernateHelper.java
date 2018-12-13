/**
 * 
 */
package com.www.utils.orm;

import java.sql.Connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.www.utils.LogExceptionUtils;

/**
 * hibernate的相关对象类
 * 1、创建Configuration对象
 * 2、创建sessionFactory对象
 * 3、创建session对象
 * 4、创建connection对象
 * @author www
 */
public class HibernateHelper {


	/**Hibernate的核心配置文件对象*/
	private Configuration configuration;
	/** SessionFactory的对象*/
	private SessionFactory sessionFactory;
	/**HibernateHelper对象*/
	private static HibernateHelper hibernateHelper;
	/**当前线程*/
	@SuppressWarnings("rawtypes")
	private final ThreadLocal threadLocal = new ThreadLocal();
	
	private  HibernateHelper() {
		try {
			/**使用XML配置实体类时使用Configuration创建对象**/
//			configuration=new Configuration().configure();
//			sessionFactory=configuration.buildSessionFactory();
			/**使用注解配置实体类时使用AnnotationConfiguration创建对象,
			 * hibernate4之后取消AnnotationConfiguration，直接用Configuration**/
			configuration=new Configuration();
			sessionFactory=configuration.configure().buildSessionFactory();
		} catch (HibernateException e) {
			LogExceptionUtils.catchException(e, HibernateHelper.class);//异常日志记录
		}
	}
	/**
	 * 单例化
	 * @return
	 */
	private static synchronized HibernateHelper getInstance() {
		if (hibernateHelper == null)
			hibernateHelper = new HibernateHelper();
		return hibernateHelper;
	}
	/**
	 * 获取当前线程hibernate的session对象
	 * @return session对象
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static Session currentSession() throws HibernateException {
		Session session = (Session) getInstance().threadLocal.get();
		if (session == null || !session.isOpen() || !session.isConnected()) {
			if (session != null)
				session.close();
			session = getInstance().sessionFactory.openSession();
			getInstance().threadLocal.set(session);
		}
		return session;
	}
	/**
	 * 获取当前的数据库connection对象
	 * @return
	 */
//	public static Connection currentConnection() {
//		Session session = currentSession();
//		return session.connection();
//	}
	/**
	 * 清除session对象
	 */
	public static void clearSession() {
		Session session = (Session) getInstance().threadLocal.get();
		if (session != null)
			session.clear();
	}
	/**
	 * 关闭session对象
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public static void closeSession() throws HibernateException {
		Session session = (Session) getInstance().threadLocal
				.get();
		if (session != null) {
			session.close();
			getInstance().threadLocal.set(null);
		}
	}
	/**
	 * 清除缓存和session对象
	 * @throws HibernateException
	 */
	public static void flushAndClearSession() throws HibernateException {
		Session session = (Session) getInstance().threadLocal
				.get();
		if (session != null) {
			session.flush();
			session.clear();
		}
	}
	
	public static Session createClassicSession() throws HibernateException {
		return (Session) createSession();
	}

	public static org.hibernate.Session createSession()
			throws HibernateException {
		return getInstance().sessionFactory.openSession();
	}
	/**
	 * 获取Hibernate的核心配置文件对象
	 * @return configuration Hibernate的核心配置文件对象
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	/**
	 * 设置Hibernate的核心配置文件对象
	 * @param configuration Hibernate的核心配置文件对象
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	/**
	 * 获取SessionFactory的对象
	 * @return sessionFactory SessionFactory的对象
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * 设置SessionFactory的对象
	 * @param sessionFactory SessionFactory的对象
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
