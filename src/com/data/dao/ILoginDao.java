package com.data.dao;

import java.util.List;

import com.data.entity.MenuEntity;
import com.framework.core.SessionManageEntity;


public interface ILoginDao {
	/**
	 * 通过用户账号获取用户信息
	 */
	public SessionManageEntity getUserByUserId(String userId);

	/**
	 * 通过用户账号和用户角色获取菜单权限
	 */
	public List<MenuEntity> listMenuByUserIdAndUserRoleId(String userRoleId);
	
	/**
	 * 修改当前登录用户的用户密码
	 */
	public int updatePasswordById(SessionManageEntity user);
}