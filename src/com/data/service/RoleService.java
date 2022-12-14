package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.IRoleDao;
import com.data.entity.RoleEntity;
import com.data.entity.TreeNode;


/**
 * 用户角色管理Service
 */
@Service
public class RoleService {

	@Resource
	private IRoleDao roleDao;
//
//	@Resource
//	private IMenuPowerDao menuPowerDao;
//
//	@Resource
//	private IFuncPowerDao funcPowerDao;

	/**
	 * 通过条件获取角色信息
	 */
	public List<RoleEntity> listRole(RoleEntity roleEntity) {
		return this.roleDao.list(roleEntity);
	}

	/**
	 * 通过条件统计角色信息条数
	 */
	public long countRole(RoleEntity roleEntity) {
		return this.roleDao.count(roleEntity);
	}

	/**
	 * 插入角色信息
	 */
	public int insertRole(RoleEntity roleEntity) {
		return this.roleDao.insert(roleEntity);
	}

	/**
	 * 更新角色信息
	 */
	public long updateRole(RoleEntity roleEntity) {
		return this.roleDao.update(roleEntity);
	}

	/**
	 * 通过id获取角色信息
	 */
	public RoleEntity getRoleById(String id) {
		return this.roleDao.getById(id);
	}

	/**
	 * 通过角色id获取角色信息
	 */
	public RoleEntity getRoleByRoleId(String id) {
		return this.roleDao.getByRoleId(id);
	}

	/**
	 * 通过id批量删除角色信息
	 */
	public long deleteRoleByIds(String ids, String updateBy) {
		String[] idArr = ids.split(",");
		for (String string : idArr) {
			roleDao.deleteByRoleId(string, "系统删除");
		}
		
		return this.roleDao.deleteByIds(idArr, updateBy);
	}
	
	//取得当前角色下用户数量
	public long check(String roleId) {
		return this.roleDao.check(roleId);
	}

	/**
	 * 根据角色id取得所有菜单和功能
	 */
	public List<TreeNode> getPowerMenuByRole(String roleId) {
		return this.roleDao.listMenuPowerByRoleId(roleId);
	}

	/**
	 * 更新角色权限
	 */
	public int updateRolePower(String menuIds,String roleId, String currentUserId) {
		int count = 0;
		// 更新菜单权限 先删除之前的用户菜单权限
		count += this.roleDao.deleteByRoleId(roleId, currentUserId);
		// 插入新的用户菜单权限
		if (!("".equals(menuIds.trim())) && menuIds.endsWith(",")) {
			menuIds = menuIds.substring(0, menuIds.lastIndexOf(","));
			String[] menuIdArr = menuIds.split(",");
			count += this.roleDao.insertByRoleId(roleId, menuIdArr, currentUserId);
		}
		return count;
	}
}