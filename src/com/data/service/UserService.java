package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.IUserDao;
import com.data.entity.UserEntity;


/**
 * 用户信息 service
 */
@Service
public class UserService {

	@Resource
	private IUserDao userDao;

	/**
	 * 新增用户
	 */
	public int insertUser(UserEntity user) {
		int result = 0;

		if (this.userDao.checkByUserId(user.getUserId()) <= 0) {
			result = this.userDao.insert(user);
			// 添加用户城市
		} else {
			result = -1;
		}

		return result;
	}

	/**
	 * 通过id查询用户
	 */

	public UserEntity getUserById(String id) {
		return this.userDao.getById(id);
	}

	/**
	 * 通过条件查询多个用户
	 */

	public List<UserEntity> listUser(UserEntity user) {
		return this.userDao.list(user);
	}

	/**
	 * 通过条件统计用户
	 */

	public long countUser(UserEntity user) {
		return this.userDao.count(user);
	}

	/**
	 * 更新用户
	 */

	public long updateUser(UserEntity user) {
		return this.userDao.update(user);
	}

	/**
	 * 批量删除用户
	 */

	public int deleteUserByIds(String userIds,String editUserIds,String updateBy) {
		int result = 0;

		String[] arrayUserIds = userIds.split(",");
		if (arrayUserIds.length > 0) {
			for (int i = 0; i < arrayUserIds.length; i++) {
				int tmpResult = this.userDao.deleteById(arrayUserIds[i], updateBy);
				result = result + tmpResult;
			}
		}
		return result;
	}

	/**
	 * 通过账号获取用户
	 */

	public UserEntity getUserByUserId(String userId) {
		return this.userDao.getByUserId(userId);
	}
	
}