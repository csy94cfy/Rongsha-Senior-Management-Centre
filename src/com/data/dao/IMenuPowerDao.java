package com.data.dao;

import org.apache.ibatis.annotations.Param;

import com.data.entity.MenuPowerEntity;
import com.framework.core.IBaseManageDao;

public interface IMenuPowerDao extends IBaseManageDao<MenuPowerEntity> {
	/**
	 * 通过角色Id删除用户菜单权限
	 */
	public int deleteByRoleId(@Param("roleId") String roleId, @Param("updateBy") String updateBy);
	
	/**
	 * 通过角色Id增加用户菜单权限
	 */
	public int insertByRoleId(@Param("roleId") String roleId,@Param("menuIds")String[] menuIds,@Param("createBy") String createBy);


}
