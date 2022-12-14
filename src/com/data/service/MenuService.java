package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.IMenuDao;
import com.data.entity.MenuEntity;

/**
 * 菜单Service实现
 */
@Service
public class MenuService {
	
	@Resource
	private IMenuDao menuDao;

	/**
	 * 通过条件查询菜单
	 */
	
	public List<MenuEntity> listMenu(MenuEntity menuEntity) {
		return menuDao.list(menuEntity);
	}

	/**
	 * 根据条件统计菜单条数
	 */
	
	public long countMenu(MenuEntity menuEntity) {
		return this.menuDao.count(menuEntity);
	}

	/**
	 * 新增菜单
	 */
	
	public int insertMenu(MenuEntity menuEntity) {
		return this.menuDao.insert(menuEntity);
	}

	/**
	 * 更新菜单
	 */
	
	public long updateMenu(MenuEntity menuEntity) {
		return this.menuDao.update(menuEntity);
	}

	/**
	 * 通过id删除菜单
	 */
	
	public int deleteMenuById(String menuIds, String updateBy) {
		int ret = 0;

		String[] arrMenuIds = menuIds.split(",");
		if (arrMenuIds.length > 0) {
			for (int i = 0; i < arrMenuIds.length; i++) {
				int tmpRet = this.menuDao.deleteById(arrMenuIds[i], updateBy);
				ret = ret + tmpRet;
			}
		}
		return ret;
	}

	/**
	 * 通过id获取菜单信息
	 */
	
	public MenuEntity getMenuById(String menuId) {
		return this.menuDao.getById(menuId);
	}


}
