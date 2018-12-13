package com.www.app.webservice.axis2service;

import org.springframework.stereotype.Service;

import com.www.app.entity.Sysuser;


/**
 * 2、axis2发布webservice
 * @author www
 *
 */
@Service("axis2Service")
public class Axis2Service {

	public Axis2Service(){
		System.out.println("创建了。。。。。。。。。。。。。。。。。。。。。");
	}
	
	public String getValue(String name){
		System.out.println("Axis2Service.getValue2我被调用啦............");
		return "返回的name="+name;
	}
	
	public Sysuser getUser(String name){
		Sysuser user=new Sysuser();
		user.setUserId("123");
		user.setUserName(name);
		user.setPasswd("www123");
		System.out.println("Axis2Service.getUser我被调用啦............");
		return user;
	}
	/**
	 * 登入
	 * @param xmlStr
	 * @return
	 */
	public String login(String xmlStr){
		System.out.println("Axis2Service.login我被调用啦............");
		return xmlStr;
	}
	/**
	 * 退出
	 * @param xmlStr
	 * @return
	 */
	public String logout(String xmlStr){
		System.out.println("Axis2Service.logout我被调用啦............");
		return xmlStr;
	}
	/**
	 * 获取
	 * @param xmlStr
	 * @return
	 */
	public String get(String xmlStr){
		System.out.println("Axis2Service.get我被调用啦............");
		return xmlStr;
	}
	/**
	 * 设置
	 * @param xmlStr
	 * @return
	 */
	public String set(String xmlStr){
		System.out.println("Axis2Service.set我被调用啦............");
		return xmlStr;
	}
	/**
	 * 通知
	 * @param xmlStr
	 * @return
	 */
	public String notify(String xmlStr){
		System.out.println("Axis2Service.notify我被调用啦............");
		return xmlStr;
	}
}
