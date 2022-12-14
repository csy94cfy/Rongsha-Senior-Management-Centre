package com.data.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.common.SessionUtil;
import com.data.entity.UserEntity;
import com.data.service.UserService;
import com.framework.core.BaseManageController;
import com.framework.core.SessionManageEntity;
import com.framework.util.StringUtil;

@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController extends BaseManageController {

	@Resource
	private UserService userService;

	/**
	 * 打开首页
	 * @throws IOException 
	 */
	@RequestMapping(value = { "/subMain" })
	public String subMain() throws IOException {
		// 初始化帮助信息和功能权限信息
		super.setCommonAttribute(request);
		return "user/userList";
	}

	/**
	 * 打开编辑页
	 * @throws IOException 
	 */
	@RequestMapping(value = { "/openEditPage" })
	public String openEditPage(String id) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		String currentUserRoleId = user.getUserRoleId();
		UserEntity editUser = null;
		if (StringUtil.isNotNull(id)) {
			editUser = userService.getUserById(id);
		}
		request.setAttribute("editUser", editUser);
		request.setAttribute("currentUserRoleId", currentUserRoleId);
		return "user/userEdit";
	}

	/**
	 * 查询用户列表
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = { "/selectUserList" })
	public Map<String, Object> selectUserList(UserEntity paraUser) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", userService.countUser(paraUser));
		map.put("rows", userService.listUser(paraUser));
		return map;
	}

	/**
	 * 新增用户
	 */
	@ResponseBody
	@RequestMapping(value = { "/insertUser" })
	public int insertUser(UserEntity paraUser) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		paraUser.setCreateBy(user.getUserId());
		paraUser.setUpdateBy(user.getUserId());
		return userService.insertUser(paraUser);
	}

	/**
	 * 修改用户
	 */
	@ResponseBody
	@RequestMapping(value = { "/updateUser" })
	public long updateUser(UserEntity paraUser) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		paraUser.setUpdateBy(user.getUserId());
		return userService.updateUser(paraUser);
	}
	
	
	/**
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping(value = { "/deleteUser" })
	public int deleteUser(Model model,String userIds,String editUserIds) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		String currentUserId = user.getUserId();
		if (StringUtil.isNotNull(userIds)) {
			return userService.deleteUserByIds(userIds,editUserIds, currentUserId);
		}
		return 0;
	}
}
