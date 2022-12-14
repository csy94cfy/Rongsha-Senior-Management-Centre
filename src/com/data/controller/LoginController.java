package com.data.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.common.SessionUtil;
import com.data.entity.MenuEntity;
import com.data.service.LoginService;
import com.framework.core.BaseManageController;
import com.framework.core.SessionManageEntity;
import com.framework.util.ManageConstants;

@Scope("prototype")
@Controller
public class LoginController extends BaseManageController {

	@Resource
	private LoginService loginService;
	
	@RequestMapping(value = { "/login" })
	public String index() {
		return "login/index";
	}

	@ResponseBody	
	@RequestMapping("/checkUser")
	public String checkUser() throws IOException {
		// 取得参数
		String strUserId = request.getParameter("USERID");
		String strPasswd = request.getParameter("PASSWORD");
		SessionManageEntity userBean = loginService.getUserByUserId(strUserId);
		if (userBean == null) {// 未找到用户
			return "NONE";
		} else if (!userBean.getUserPassword().equals(strPasswd)) {// 密码不正确
			return "PASSERR";
		} 
		// 设定工程路径
		SessionUtil.setBasePath(request);
		// 用户对象与菜单列表存入Session
		request.getSession().setAttribute(ManageConstants.LOGIN_USER, userBean);
		// 设定Redis权限对象
		SessionUtil.setSession(request,userBean);
		return "OK";
	}

	@RequestMapping(value = "/queryUserMenuList")
	public @ResponseBody List<MenuEntity> queryUserMenuList() throws IOException {
		List<MenuEntity> userMenuList = new ArrayList<MenuEntity>();
		SessionManageEntity user = SessionUtil.getSession(request);
		try {
			userMenuList = loginService.listMenuByUserIdAndUserRoleId(user.getUserRoleId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMenuList;
	}
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "home/homeWelcome";
	}

	@RequestMapping("/")
	public String home() throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		if (user == null) {
			return "login/index";
		} else {
			return "home/homeMain";
		}
	}

	@ResponseBody
	@RequestMapping("/logout")
	public String logout(Model model) throws IOException {
		request.getSession().removeAttribute(ManageConstants.LOGIN_USER);
		return "";
	}
	
	@RequestMapping(value = { "/openPassReset" })
	public String openPassReset() throws IOException{
		SessionManageEntity user = SessionUtil.getSession(request);
		request.setAttribute("userId", user.getUserId());
		request.setAttribute("userName", user.getUserName());
		return "home/passReset";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/savePassword" })
	public int savePassword(String password) throws IOException {
		SessionManageEntity user = SessionUtil.getSession(request);
		user.setUserPassword(password);
		return loginService.updatePasswordById(user);
	}

}
