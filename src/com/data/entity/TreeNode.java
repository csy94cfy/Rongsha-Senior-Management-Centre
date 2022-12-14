package com.data.entity;

/**
 * 树形菜单实体
 */
public class TreeNode {


	/**
	 * 判断是否角色全权限
	 */
	private String roleFlag;

	/**
	 * 当前节点id
	 */
	private String id;

	/**
	 * 父节点id
	 */
	private String pId;

	/**
	 * 节点显示名称
	 */
	private String name;

	/**
	 * 节点默认是否为选中状态    
	 * "true"/"false"
	 */
	private String checked;
	
	/**
	 * 节点默认是否为打开状态
	 * "true"/"false"
	 */
	private String open;
	
	/**
	 * 菜单级别 1 2 3 
	 */
	private String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

}
