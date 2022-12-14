package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.ILoginDao;
import com.data.entity.MenuEntity;
import com.framework.core.SessionManageEntity;

/**
 * 用户登录Service
 */
@Service
public class LoginService {
	@Resource
	private ILoginDao loginDao;
	
	/**
	 * 通过用户账号获取用户
	 */
	public SessionManageEntity getUserByUserId(String userId) {
		return loginDao.getUserByUserId(userId);
	}

	/* * 根据用户Id和用户角色Id，取得权限菜单
	 */
	public List<MenuEntity> listMenuByUserIdAndUserRoleId(String userRoleId) {
		return loginDao.listMenuByUserIdAndUserRoleId(userRoleId);
	}
	
	/**
	 * 修改当前登录用户的用户密码
	 */
	public int updatePasswordById(SessionManageEntity user) {
		return this.loginDao.updatePasswordById(user);
	}
}
