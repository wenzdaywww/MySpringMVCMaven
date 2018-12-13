/**
 * 
 */
package com.www.app.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




/**
 * 用户类
 * @author www
 */
@Entity
@Table(name="sysuser")
public class Sysuser {
	/**用户ID*/
	@Id
	@Column(name="userid")
	private String userId;
	/**用户名*/
	@Column(name="username")
	private String userName;
	/**用户密码*/
	@Column(name="passwd")
	private String passwd;
	/**QQ*/
	@Column(name="userqq")
	private BigDecimal userQQ;
	/**创建时间*/
	@Column(name="redate")
	private Timestamp reDate;
	
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

	/**
	 * 获取创建时间
	 * @return reDate 创建时间
	 */
	public Timestamp getReDate() {
		return reDate;
	}
	/**
	 * 设置创建时间
	 * @param reDate 创建时间
	 */
	public void setReDate(Timestamp reDate) {
		this.reDate = reDate;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sysuser [userId=" + userId+ ", userName=" + userName + ", passwd="+ passwd +"]\n";
	}

	
}
