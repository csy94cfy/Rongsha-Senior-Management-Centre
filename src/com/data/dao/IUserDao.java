package com.data.dao;

import com.data.entity.UserEntity;
import com.framework.core.IBaseManageDao;


public interface IUserDao extends IBaseManageDao<UserEntity> {

	 //验证账号是否存在
	public int checkByUserId(String userId);

	//根据ID获取用户信息
	public UserEntity getByUserId(String userId);

	
}