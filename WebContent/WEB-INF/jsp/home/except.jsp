<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.framework.util.ManageConstants"%>
<%
String basePath = (String)request.getServletContext().getAttribute(ManageConstants.BASE_PATH);
String staticPath = (String)request.getServletContext().getAttribute(ManageConstants.STATIC_PATH);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>�쳣ҳ</title>
	<link rel="stylesheet" href="<%=staticPath%>bin/css/edit.css" type="text/css">
	<script language="javascript">
		//�������ô�С
		function resize(){
		parent.resizeDetail(700,250);
		}
	</script>
</head>
<body onLoad="resize()">
	���������쳣 <br>
	<ul>
		<li>����ϵ����Ա</li>
	</ul>
	<div><a href="javascript:history.back();">����</a></div>
</body>
</html>
