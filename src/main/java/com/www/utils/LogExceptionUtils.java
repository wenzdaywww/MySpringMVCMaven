package com.www.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
/**
 * 异常捕获记录
  * @author www
 */
public class LogExceptionUtils {
	/**
	 * 捕获并记录异常信息
	 * @param e 异常对象
	 * @param cls 异常发生所在的类
	 */
	@SuppressWarnings("rawtypes")
	public static void catchException(Throwable e, Class cls){
		Logger logger = Logger.getLogger(cls);
		e.printStackTrace();
		if (e instanceof Exception) {
			logger.info("业务提示："+getStackTraceAsString(e));
		}else {
			logger.info("系统错误："+getStackTraceAsString(e));
		}
	}
	/**
	 * 获取异常信息
	 * @param ex
	 * @return
	 */
	public static String getStackTraceAsString(Throwable ex) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}
