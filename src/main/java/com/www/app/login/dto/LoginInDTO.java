/**
 * 
 */
package com.www.app.login.dto;

/**
 * 登录DTO
 * @author www
 */
public class LoginInDTO {
	/**用户ID*/
	private String userId;
	/**用户密码*/
	private String passwd;
	/**
	 * 获取用户ID
	 * @return userId 用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户密码
	 * @return passwd 用户密码
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 设置用户密码
	 * @param passwd 用户密码
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
