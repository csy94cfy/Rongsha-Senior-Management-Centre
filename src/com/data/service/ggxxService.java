package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.IggxxDao;
import com.data.entity.UserInfoEntity;

/**
 * 用户信息管理
 * @author hzy
 *
 */
@Service
public class ggxxService {
	
	@Resource
	private IggxxDao userInfoDao;

	/**
	 * 通过条件查询用户信息管理
	 */
	public List<UserInfoEntity> listUserInfo(UserInfoEntity userInfoEntity){
		return userInfoDao.list(userInfoEntity);
	}
	
	/**
	 * 通过条件查询用户信息管理数量
	 */
	public long countUserInfo(UserInfoEntity userInfoEntityy) {
		return userInfoDao.count(userInfoEntityy);
	}
	
	/**
	 * 新增用户信息管理
	 */
	public int insertUserInfo(UserInfoEntity userInfoEntity) {
		return this.userInfoDao.insert(userInfoEntity);
	}
	
	/**
	 * 查询用户信息管理
	 */
	public UserInfoEntity getUserInfoById(String id) {
		return this.userInfoDao.getById(id);
	}

	/**
	 * 修改用户信息管理
	 */
	public long updateUserInfo(UserInfoEntity userInfoEntity) {
		return this.userInfoDao.update(userInfoEntity);
	}

	/**
	 * 批量修改用户信息管理
	 */
	public long deleteUserInfo(String ids) {
		String[] idArr = ids.split(",");
		return this.userInfoDao.delete(idArr);
	}
	
}
