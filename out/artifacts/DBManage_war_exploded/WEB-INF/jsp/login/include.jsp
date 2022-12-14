<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ page import="com.framework.util.ManageConstants"%>
<%
	pageContext.getRequest().setAttribute("version","20180901163009");
	String basePath = (String)request.getServletContext().getAttribute(ManageConstants.BASE_PATH);
	String staticPath = (String)request.getServletContext().getAttribute(ManageConstants.STATIC_PATH);
%>

<!-- 页面必备引入插件 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/css/shop.css?_=${version}">
<script type="text/javascript" src="<%=staticPath%>bin/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=staticPath%>bin/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=staticPath%>bin/js/shop.js?_=${version}"></script>
<script type="text/javascript" src="<%=staticPath%>bin/js/common/common.js?_=${version}"></script>

<!-- 部分页面必备引入插件 -->
<script type="text/javascript" src="<%=staticPath%>bin/js/common/select.js?_=${version}"></script>
<script type="text/javascript" src="<%=staticPath%>bin/js/modal.js?_=${version}"></script>
<script type="text/javascript" src="<%=staticPath%>bin/plugin/alertify/alertify.js?_=${version}"></script>
<script type="text/javascript" src="<%=staticPath%>bin/js/jquery.form.min.js"></script>

<!-- 时间插件 -->
<%-- <link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/plugin/jedate/jedate.css">
<script type="text/javascript" src="<%=staticPath%>bin/plugin/jedate/jeDate.js"></script> --%>
<link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/css/bootstrap-datetimepicker.css">
<script type="text/javascript" src="<%=staticPath%>bin/js/bootstrap-datetimepicker.min.js"></script>

<!-- alertify提示框 CSS -->
<link href="<%=staticPath%>bin/plugin/alertify/alertify.core.css" rel="stylesheet" id="toggleCSS">
<link href="<%=staticPath%>bin/plugin/alertify/alertify.default.css" rel="stylesheet" id="toggleCSS">

<!--[if lt IE 9]>
<script src="<%=staticPath%>bin/js/html5/html5shiv.min.js"></script>
<script src="<%=staticPath%>bin/js/html5/respond.js"></script>
<![endif]-->
