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

import com.data.common.CommonUtil;
import com.data.entity.UserInfoEntity;
import com.data.service.ggxxService;
import com.framework.core.BaseManageController;
import com.framework.core.SessionManageEntity;
import com.framework.core.SessionUtil;



@Scope("prototype")
@Controller
@RequestMapping("/ggxxController")
public class ggxxController extends BaseManageController{
	
	@Resource
	private ggxxService ggxxService;
	/**
	 * 入口
	 */
	@RequestMapping(value = { "/subMain" })
	public String subMain() {
		super.setCommonAttribute(request);
		return "ggxx/userInfoList";
	}
	
	/**
	 * 查询用户信息管理
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = { "/selectUserInfoList" })
	public Map<String, Object> selectUserInfoList(UserInfoEntity userInfoEntity) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", ggxxService.countUserInfo(userInfoEntity));
		map.put("rows", ggxxService.listUserInfo(userInfoEntity));
		return map;
	}
	
	/**
	 * 打开用户信息管理编辑页面
	 */
	@RequestMapping(value = { "/openEditPage" })
	public String openEditPage(ModelMap modelMap,String id) {
		modelMap.put("userInfo", ggxxService.getUserInfoById(id));
		return "ggxx/userInfoEdit";
	}
	
	
	/**
	 * 新增用户信息管理
	 */
	@ResponseBody
	@RequestMapping(value = { "/insertUserInfo" })
	public int insertUserInfo(UserInfoEntity userInfoEntity) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		userInfoEntity.setCreateBy(user.getUserId());
		userInfoEntity.setUpdateBy(user.getUserId());
		userInfoEntity.setId(CommonUtil.getUuid());
		return ggxxService.insertUserInfo(userInfoEntity);
	}

	/**
	 * 修改用户信息管理
	 */
	@ResponseBody
	@RequestMapping(value = { "/updateUserInfo" })
	public long updateUserInfo(UserInfoEntity userInfoEntity) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		userInfoEntity.setUpdateBy(user.getUserId());
		return ggxxService.updateUserInfo(userInfoEntity);
	}

	/**
	 * 按id集合字符串删除用户信息管理
	 */
	@ResponseBody
	@RequestMapping(value = { "/deleteUserInfo" })
	public long deleteUserInfo(String ids) throws IOException {
		return ggxxService.deleteUserInfo(ids);
	}

}
