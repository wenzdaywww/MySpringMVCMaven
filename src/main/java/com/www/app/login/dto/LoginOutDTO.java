/**
 * 
 */
package com.www.app.login.dto;

/**
 * 登录返回DTO
 * @author www
 */
public class LoginOutDTO {
	/**用户ID*/
	private String userId;
	/**信息*/
	private String info;
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

}
