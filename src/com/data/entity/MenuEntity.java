package com.data.entity;

import com.framework.core.BaseManageEntity;

/**
 * m_menu-菜单表
 */
public class MenuEntity extends BaseManageEntity {

	private String menuId; // 菜单ID
	private String menuName; // 菜单名称
	private String menuServer; // 服务器地址
	private String menuUrl; // 菜单URL
	private String menuLevel; // 菜单层级
	private String menuPid; // 父节点ID
	private String menuSort; // 菜单顺序号
	private String menuIcon; // 菜单图标
	private String menuParentName; // 父节点菜单名称
	
	//set、get与重写tostring方法
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuServer() {
		return menuServer;
	}
	public void setMenuServer(String menuServer) {
		this.menuServer = menuServer;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	public String getMenuSort() {
		return menuSort;
	}
	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuParentName() {
		return menuParentName;
	}
	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}
	@Override
	public String toString() {
		return "MenuEntity [menuId=" + menuId + ", menuName=" + menuName + ", menuServer=" + menuServer + ", menuUrl="
				+ menuUrl + ", menuLevel=" + menuLevel + ", menuPid=" + menuPid + ", menuSort=" + menuSort
				+ ", menuIcon=" + menuIcon + ", menuParentName=" + menuParentName + "]";
	}
}
