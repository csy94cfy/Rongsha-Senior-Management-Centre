package com.framework.core;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.data.service.CommonService;
import com.framework.util.ManageConstants;

public class SessionUtil {
	
	@Resource
	private CommonService commonService;
	
	//通过权限Session取得权限对象
	public static SessionManageEntity getSession(HttpServletRequest request) throws IOException {
		
		//判断Session是否已有登录用户对象
		SessionManageEntity result = (SessionManageEntity) request.getSession().getAttribute(ManageConstants.LOGIN_USER);
		
		return result;
	}
	
	
	//设定权限SessionId
	public static void setSession(HttpServletRequest request,SessionManageEntity userBean) throws IOException {
		
		//把登录对象存入Session属性中
		request.getSession().setAttribute(ManageConstants.LOGIN_USER, userBean);
		
		//存入登录用户名，用于首页显示
		request.getSession().setAttribute("LoginName", userBean.getUserName());
		
		//存入登录用户Id，用于导出列表
		request.getSession().setAttribute("LoginId", userBean.getUserId());
		
	}
	
	//设定权限SessionId
	public static void clearSession(HttpServletRequest request) throws IOException {
	
		//把权限SessionId从Session属性中清除
		request.getSession().removeAttribute(ManageConstants.LOGIN_USER);
		
		//存入登录用户名，用于首页显示
		request.getSession().removeAttribute("LoginName");

	}
}
