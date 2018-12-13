package com.www.utils;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * 配置文件读取
 * @author www
 *
 */
public class ConfigUtils {
	
	public static void main(String[] args) {
		Properties p=ConfigUtils.getProperties("src/config.properties");
		System.out.println(ConfigUtils.getPropertyValue(p, "jdbc.driverclass"));
		System.out.println(ConfigUtils.getPropertyValue(p, "jdbc.url"));
		System.out.println(ConfigUtils.getPropertyValue(p, "jdbc.username"));
		System.out.println(ConfigUtils.getPropertyValue(p, "jdbc.password"));
		ConfigUtils.setPropertyValue(p, "www", "123");
		System.out.println(ConfigUtils.getPropertyValue(p, "www"));
	}

	/**
	 * 读取配置文件
	 * @param filePath 配置文件路径
	 * @return Properties
	 */
	public static Properties getProperties(String filePath){
		Properties p=null;
		try {
			FileInputStream fis=new FileInputStream(filePath);
			p=new Properties();
			p.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	/**
	 * 获取配置文件内容
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(Properties properties,String key){
		try {
			return (String)properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 设置配置文件内容
	 * @param properties
	 * @param key
	 * @param value
	 */
	public static void setPropertyValue(Properties properties, String key, String value){
    	try{
    		properties.setProperty(key, value);
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    }
}
