package com.www.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:角色表
 * @author www 
 */
@Entity
@Table(name="sysrole")
public class Sysrole {
	/**ID*/
	@Id
	@Column(name="id")
	private  java.math.BigDecimal id; //ID
	/**角色ID*/
	@Column(name="roleid")
	private  java.lang.String roleid; 
	/**角色名称*/
	@Column(name="rolename")
	private  java.lang.String rolename; 

	/**
	 * ID
	 * @hibernate.property column="id"
	 * @return id
	 */
	public  java.math.BigDecimal getId() {
		return this.id; 
	}

	/**
	 * ID
	 * @hibernate.property column="id"
	 * @return id
	 */
	public void setId(java.math.BigDecimal id) {
		this.id=id; 
	}
	/**
	 * 角色ID
	 * @hibernate.property column="roleid"
	 * @return roleid
	 */
	public  java.lang.String getRoleid() {
		return this.roleid; 
	}

	/**
	 * 角色ID
	 * @hibernate.property column="roleid"
	 * @return roleid
	 */
	public void setRoleid(java.lang.String roleid) {
		this.roleid=roleid; 
	}
	/**
	 * 角色名称
	 * @hibernate.property column="rolename"
	 * @return rolename
	 */
	public  java.lang.String getRolename() {
		return this.rolename; 
	}

	/**
	 * 角色名称
	 * @hibernate.property column="rolename"
	 * @return rolename
	 */
	public void setRolename(java.lang.String rolename) {
		this.rolename=rolename; 
	}

	@Override
	public String toString() {
		return "Sysrole [id=" + id + ", roleid=" + roleid + ", rolename="
				+ rolename + "]";
	}

}
