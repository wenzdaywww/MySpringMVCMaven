package com.www.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.www.utils.LogExceptionUtils;
import com.www.utils.orm.HibernateHelper;


/**
 * 事务AOP切面
 * @author www
 */
@Aspect
@Component
public class TransAspect {
	/**日志对象*/
	Logger logger = Logger.getLogger(TransAspect.class);
	/**
	 * 环绕通知
	 * @param pro
	 * @return
	 */
	@Around("execution(* com.www.app..*.*TA(..))")//切入点
	public Object aspectTrans(ProceedingJoinPoint pro){
		Transaction transaction=null;
		try {
			Session session=HibernateHelper.currentSession();
			transaction=session.getTransaction();
			transaction.begin();
			logger.info("环绕通知：===========执行事务控制前==============");
			Object obj = pro.proceed();
			transaction.commit();
			logger.info("环绕通知：===========执行事务控制后==============");
			return obj;
		} catch (Throwable e) {
			transaction.rollback();
			HibernateHelper.closeSession();
			LogExceptionUtils.catchException(e, TransAspect.class);
		}
		return null;
	}
}
