package com.data.entity;

import com.framework.core.BaseManageEntity;

/**
 * m_menu_power-菜单权限
 */
public class MenuPowerEntity extends BaseManageEntity {

	// 权限类别
	private String powerType;

	// 权限ID
	private String powerId;

	// 菜单ID
	private String menuId;

	// 菜单Name
	private String menuName;

	// 备注
	private String remark;

	// 创建人
	private String createBy;

	// 创建时间
	private String createDate;

	// 修改人
	private String updateBy;

	// 修改时间
	private String updateDate;

	// 删除状态
	private String delFlag;

	// 一级菜单
	private String menuPid;

	// 菜单关键字
	private String menuKeyword;

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuKeyword() {
		return menuKeyword;
	}

	public void setMenuKeyword(String menuKeyword) {
		this.menuKeyword = menuKeyword;
	}

}
