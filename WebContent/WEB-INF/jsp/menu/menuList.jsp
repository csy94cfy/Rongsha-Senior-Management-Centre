<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.ManageConstants"%>
<%
String basePath = (String)request.getServletContext().getAttribute(ManageConstants.BASE_PATH);
String staticPath = (String)request.getServletContext().getAttribute(ManageConstants.STATIC_PATH);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>菜单管理</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$.widget.init();
		$('#menuLevel').comboBox()
		$('#menuPid').comboBox()
		// 初始化功能按钮
		var toolbar = [
			{text : '新增',validCount : '0+',handler : function(rows){
				modal.open({title:'新增菜单',url:'menuController/openEditPage'})
			}},
			{text : '修改',validCount : '1',handler : function(rows){
				modal.open({title:'修改菜单',url:'menuController/openEditPage?id='+rows[0].id})
			}},
			{text : '删除',validCount : '1+',handler : function(rows){
				alertify.confirm("确认删除吗?", function(e){if(e) {
					//取得删除菜单列表
					var deleteMenuList=rows[0].id;
					for(var i=1;i<rows.length;i++){
						deleteMenuList = deleteMenuList + "," + rows[i].id;
					}
					
					$.ajax({
						url: 'menuController/deleteMenu',
						type: 'post',//提交的方式
						dataType:'json',
						data: {menuIds:deleteMenuList},
						success: function(result) {
							if(result>=1){
								alertify.success("删除成功!");
								loadData();
							}
						}
					});
				}});
			}}
		]
		
		$('#table').datagrid({
			url : 'menuController/selectMenuList',//查询数据的地址
			form : 'selectform',//用于筛选的form id
			//字段
			columns : [
				{field : 'menuId',title : '菜单代码',width : 50,sortable : true,}, 
				{field : 'menuName',title : '菜单名称',width : 90,sortable : true,},
				{field : 'menuServer',title : '服务器地址',width : 120,sortable : true,},
				{field : 'menuUrl',title : '菜单URL',width : 170,sortable : true,},
				{field : 'menuLevel',title : '菜单级别',width : 50,sortable : true,},
				{field : 'menuSort',title : '菜单序号',width : 50,sortable : true,},
				{field : 'menuPid',title : '所属菜单编号',width : 70,sortable : true,},
				{field : 'menuParentName',title : '所属菜单名称',width : 70,sortable : true,}
			],
			//按钮功能栏
			toolbar : toolbar,
			singleSelect : false,//是否单行选中-默认false
			//pageSize : 10//分页，每页查询数量-默认10
		});
	});
	
	// 加载 datagrid 填充数据 
	function loadData(){
		$('#table').datagrid('load');
	}
	
	function searchData(){
		//点击查询会有倒计时三秒之内不让再查询
 		var status = setButttonDisable3Secound();
		if(status == "false"){
			return;
		}
		loadData();
	}
</script>
</head>
<body>
 	<div class="widget">
		<div class="widget-header">
			<span class="title">菜单管理</span>
		</div>
		<div class="widget-body">
			<div class="widget-control">
				<form id="selectform" class="widget-form" method="post">
				<input id="queryCondition"type="hidden" name="queryCondition" >
					<div class="widget-screen">
						<label for="menuId" class="control-label">菜单代码</label>
						<div class="widget-item">
							<input class="form-control" id="textbox" name="menuId" type="text" placeholder="可按菜单代码模糊查询">
						</div>
					</div>
					<div class="widget-screen">
						<label for="menuName" class="control-label">菜单名称</label>
						<div class="widget-item">
							<input class="form-control" id="textbox" name="menuName" type="text" placeholder="可按菜单名称模糊查询">
						</div>
					</div>
					<div class="widget-screen">
						<label for="menuUrl" class="control-label">菜单地址</label>
						<div class="widget-item">
							<input class="form-control" id="menuUrl" name="menuUrl" type="text"placeholder="可按菜单地址模糊查询">
						</div>
					</div>					
					<div class="widget-screen">
						<label for="menuLevel" class="control-label">菜单级别</label>
						<div class="widget-item">
							<select class="form-control" id="menuLevel" name="menuLevel" >
								<option selected="selected" value="000">所有</option>
								<option value="0">一级菜单</option>
								<option value="1">二级菜单</option>
							</select>
						</div>
					</div>
					<div class="widget-screen">
						<label for="menuPid" class="control-label">一级菜单</label>
						<div class="widget-item">
							<select class="form-control" id=menuPid name="menuPid" >
								<option selected="selected" value="000">所有</option>
								<option value="9010000">人员管理</option>
								<option value="9020000">数据库管理</option>
							</select>
						</div>
					</div>	
					<button id="btnSearch" class="btn btn-primary btn-sm" onclick="searchData()" type="button">查 询</button>	
				</form>
			</div>
			<!-- 数据表格 -->
			<div id="table"></div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>