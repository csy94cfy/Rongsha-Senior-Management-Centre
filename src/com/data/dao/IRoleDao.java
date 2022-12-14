package com.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.data.entity.RoleEntity;
import com.data.entity.TreeNode;
import com.framework.core.IBaseManageDao;


public interface IRoleDao extends IBaseManageDao<RoleEntity>{

		//通过角色id查询单个角色
		public RoleEntity getByRoleId(@Param("roleId") String roleId);

		//根据角色加载菜单和功能
		public List<TreeNode> listMenuPowerByRoleId(@Param("roleId") String roleId);
		
		//取得当前角色下用户数量
		public long check(@Param("roleId") String roleId);
		
		/**
		 * 通过角色Id删除用户菜单权限
		 */
		public int deleteByRoleId(@Param("roleId") String roleId, @Param("updateBy") String updateBy);
		
		/**
		 * 通过角色Id增加用户菜单权限
		 */
		public int insertByRoleId(@Param("roleId") String roleId,@Param("menuIds")String[] menuIds,@Param("createBy") String createBy);

}