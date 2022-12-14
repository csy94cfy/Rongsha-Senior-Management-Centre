package com.data.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.common.SessionUtil;
import com.data.entity.MenuEntity;
import com.data.service.MenuService;
import com.framework.core.BaseManageController;
import com.framework.core.SessionManageEntity;
import com.framework.util.StringUtil;


@Scope("prototype")
@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseManageController {

	@Resource
	private MenuService menuService;

	@RequestMapping(value = { "/subMain" })
	public String menuMain() {
		// 初始化帮助信息 和 功能权限
		super.setCommonAttribute(request);
		return "menu/menuList";
	}

	/**
	 * 查询菜单列表
	 */
	@ResponseBody
	@RequestMapping(value = { "/selectMenuList" })
	public Map<String, Object> selectMenuList(MenuEntity paraMenu){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", menuService.countMenu(paraMenu));
		map.put("rows", menuService.listMenu(paraMenu));
		return map;
	}

	/**
	 * 新增菜单
	 */
	@ResponseBody
	@RequestMapping(value = { "/insertMenu" })
	public int insertMenu(MenuEntity paraMenu) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		paraMenu.setCreateBy(user.getUserId());
		paraMenu.setUpdateBy(user.getUserId());
		return menuService.insertMenu(paraMenu);
	}

	/**
	 * 修改菜单
	 */
	@ResponseBody
	@RequestMapping(value = { "/updateMenu" })
	public long updateMenu(MenuEntity paraMenu) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		paraMenu.setUpdateBy(user.getUserId());
		return menuService.updateMenu(paraMenu);
	}

	/**
	 * 删除菜单
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = { "/deleteMenu" })
	public int deleteMenu(String menuIds) throws IOException{
		SessionManageEntity user = SessionUtil.getSession(request);
		if (StringUtil.isNotNull(menuIds)) {
			return menuService.deleteMenuById(menuIds, user.getUserId());
		}
		return 0;
	}

	/**
	 * 打开编辑页
	 */
	@RequestMapping(value = { "/openEditPage" })
	public String openEditPage(ModelMap map,String id) throws IOException {
		if (StringUtil.isNotNull(id)) {
			map.put("menu", menuService.getMenuById(id));
		}
		return "menu/menuEdit";
	}

	/**
	 * 打开设置菜单基本功能信息设置页面
	 */
	@RequestMapping(value = { "/openBaseFuncPage" })
	public String openBaseFuncPage(ModelMap map, String strMenuId) throws IOException {
		map.put("strMenuId", strMenuId);
		return "menu/baseFuncMenu";
	}
	

}
