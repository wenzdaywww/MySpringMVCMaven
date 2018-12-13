package com.www.app.login.dto;

import java.math.BigDecimal;

/**
 * 注册DTO
  * @author www
 */
public class RegistInDTO {
	
	private String userId;
	/**用户名*/
	private String userName;
	/**密码*/
	private String passwd;
	/**确认密码*/
	private String surePwd;
	/**QQ*/
	private BigDecimal userQQ;
	/**信息*/
	private String info;
	
	/**
	 * 获取信息
	 * @return info 信息
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * 设置信息
	 * @param info 信息
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * 获取userId
	 * @return userId userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置userId
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户名
	 * @return userName 用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取密码
	 * @return passwd 密码
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 设置密码
	 * @param passwd 密码
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * 获取确认密码
	 * @return surePwd 确认密码
	 */
	public String getSurePwd() {
		return surePwd;
	}
	/**
	 * 设置确认密码
	 * @param surePwd 确认密码
	 */
	public void setSurePwd(String surePwd) {
		this.surePwd = surePwd;
	}
	/**
	 * 获取QQ
	 * @return userQQ QQ
	 */
	public BigDecimal getUserQQ() {
		return userQQ;
	}
	/**
	 * 设置QQ
	 * @param userQQ QQ
	 */
	public void setUserQQ(BigDecimal userQQ) {
		this.userQQ = userQQ;
	}

}
