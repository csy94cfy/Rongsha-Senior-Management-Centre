package com.framework.core;

import java.io.Serializable;


/**
 * m_user-管控用户信息(用于Session存储)
 */
public class SessionManageEntity implements Serializable{
	private static final long serialVersionUID = -7330089771471386754L;
	/* m_user */
	private String id;
	private String userId; //用户ID
	private String userName; //用户名称
	private String userPassword; //用户密码
	private String userRoleId; //角色ID
	private String roleName;//角色名称
	private String updateBy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SessionManageEntity [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userRoleId=" + userRoleId + ", roleName=" + roleName + ", updateBy=" + updateBy
				+ "]";
	}
	
}
