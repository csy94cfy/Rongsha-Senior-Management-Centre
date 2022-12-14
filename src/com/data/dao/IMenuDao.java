package com.data.dao;

import org.apache.ibatis.annotations.Param;

import com.data.entity.MenuEntity;
import com.framework.core.IBaseManageDao;


public interface IMenuDao extends IBaseManageDao<MenuEntity>{
	
	/**
	 * 通过菜单的url 获取菜单信息
	 */
	public MenuEntity getByUrl(@Param("menuUrl")String url);
	
	/**
	 * 通过菜单id删除菜单下的所有基本功能信息
	 */
	public int deleteMenuFuncByMuneId(String strMenuId, String updateBy);
}