<%@page import="com.data.common.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.ManageConstants"%>
<%@ page import="com.framework.core.SessionManageEntity" %>
<%
String basePath = (String)request.getServletContext().getAttribute(ManageConstants.BASE_PATH);
String staticPath = (String)request.getServletContext().getAttribute(ManageConstants.STATIC_PATH);
SessionManageEntity user = (SessionManageEntity)SessionUtil.getSession(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<base href="<%=basePath%>">
<title>欢迎使用榕厦健康中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=staticPath%>bin/image/favicon.ico" />
<style type="text/css">
</style>
</head>
<body>    
	<div style="text-align: center;padding-top: 200px;"> 
		<span style="font-size: 60px;font-family: 楷体;color: #1d68c9;">欢迎进入榕厦健康中心</span>
	</div>
</body>
</html>