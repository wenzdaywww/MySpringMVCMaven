package com.www.app.login.dto;
/**
 * 注册返回DTO
  * @author www
 */
public class RegistOutDTO {
	
	private String userId;
	/**用户名*/
	private String userName;
	/**QQ*/
	private String userQQ;
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
	 * 获取QQ
	 * @return userQQ QQ
	 */
	public String getUserQQ() {
		return userQQ;
	}
	/**
	 * 设置QQ
	 * @param userQQ QQ
	 */
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	
}
