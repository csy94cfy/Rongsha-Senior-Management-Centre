package com.data.common;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.framework.core.SessionManageEntity;
import com.framework.util.ManageConstants;
import com.framework.util.ManageUtil;

public class SessionUtil {

	public static void setBasePath(HttpServletRequest request) throws IOException {
		// 取得工程路径
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/";

		Properties properties = ManageUtil.ReadProperties("/config.properties");
		String staticPath = properties.getProperty("resource.path");
		staticPath = basePath; // TODO:当静态资源改为配置文件设定，则注释掉此句

		// 基本路径及动态路径存入Session
		request.getSession().setAttribute(ManageConstants.BASE_PATH, basePath);
		request.getSession().setAttribute(ManageConstants.STATIC_PATH, staticPath);

	}
	
	//通过权限Session取得权限对象(用于Center服务器)
	public static SessionManageEntity getSession(HttpServletRequest request) throws IOException {
		SessionManageEntity result = null;
		
		//取得具有身份验证权力的sessionId
//		String sessionId = (String) request.getSession().getAttribute(ManageConstants.CURR_REDIS_SESSION);
		
		//如果没有ManageSessionId
//		if (StringUtil.isNotNull(sessionId)) {
//			//从Redis中取得权限用户对象
//			result = (SessionManageEntity) RedisUtil.get(sessionId);
//		}
		result = (SessionManageEntity) request.getSession().getAttribute(ManageConstants.LOGIN_USER);

		return result;
	}
	
	//通过权限Session取得权限对象（用于子工程）
//	public static SessionManageEntity getSession(HttpServletRequest request) throws IOException {
//		SessionManageEntity result = null;
//		
//		//取得具有身份验证权力的sessionId
//		String sessionId = request.getParameter(ManageConstants.CURR_REDIS_SESSION);
//		
//		//取得具有身份验证权力的sessionId(子工程分开时，去掉此代码)
//		if (StringUtil.isNull(sessionId)) {
//			sessionId = (String) request.getSession().getAttribute(ManageConstants.CURR_REDIS_SESSION);
//		}
//				
//		//如果没有ManageSessionId
//		if (StringUtil.isNotNull(sessionId)) {
//			//从Redis中取得权限用户对象
//			result = (SessionManageEntity) RedisUtil.get(sessionId);
//		}
//
//		return result;
//	}
	
	
	
	//设定权限SessionId
	public static void setSession(HttpServletRequest request,SessionManageEntity userBean) throws IOException {
		//取得当前登录用户的SessionId
		String sessionId = request.getRequestedSessionId();
		
		//把用户对象存入Session
		request.getSession().setAttribute(ManageConstants.LOGIN_USER, userBean);
		
		//把权限SessionId存入Session属性中
		request.getSession().setAttribute(ManageConstants.CURR_REDIS_SESSION, sessionId);
		
		//存入登录用户名，用于首页显示
		request.getSession().setAttribute("LoginName", userBean.getUserName());
		
		//存入登录用户Id，用于导出列表
		request.getSession().setAttribute("LoginId", userBean.getUserId());
		
	}
	
	//设定权限SessionId
	public static void clearSession(HttpServletRequest request) throws IOException {
		//取得当前登录用户的SessionId
//		String sessionId = request.getRequestedSessionId();
		
		//把权限SessionId从Session属性中清除
		request.getSession().removeAttribute(ManageConstants.CURR_REDIS_SESSION);
		
		//存入登录用户名，用于首页显示
		request.getSession().removeAttribute("LoginName");
		
	}
}
