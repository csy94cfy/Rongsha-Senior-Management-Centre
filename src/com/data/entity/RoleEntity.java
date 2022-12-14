package com.data.entity;

import com.framework.core.BaseManageEntity;

/**
 * m_role-用户角色
 */
public class RoleEntity extends BaseManageEntity {

	private String roleId; // 角色ID
	private String roleName; // 角色名称
	private String userNumber; // 用户数量
	
	/**
	 * 设定【角色ID】
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 取得【角色ID】
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设定【角色名称】
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 取得【角色名称】
	 */
	public String getRoleName() {
		return roleName;
	}
	
	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
}
