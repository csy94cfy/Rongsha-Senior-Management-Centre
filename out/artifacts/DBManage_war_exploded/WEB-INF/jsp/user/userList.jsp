<%@ page import="java.util.Date"%>
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
<title>系统用户管理</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$.widget.init();// 初始化筛选列表
		loadRoleList($('#userRoleId'), "SEARCH_ROLE");   
		// 初始化功能按钮
		var toolbar =[ 
			{ text : '新增', validCount : '0+', 
				handler : function(rows) {
					modal.open({width:'600px',height:'500px', title : '添加用户', url : 'userController/openEditPage' })
				} 
			}, { text : '修改', validCount : '1', 
				handler : function(rows) {
					modal.open({width:'600px',height:'500px', title : '编辑用户', url : 'userController/openEditPage?id=' + rows[0].id })
					}
			}, { text : '删除', validCount : '1+',
			handler : function(rows) { 
					var userIds = rows[0].id;
					var editUserIds= rows[0].userId;
				for (var i = 1; i < rows.length; i++) {
					userIds = userIds + "," + rows[i].id;
					}
				alertify.confirm("确认删除吗?", 
					function(e) {
						if (e) {
							$.ajax({
								url : 'userController/deleteUser',
								type : 'post',//提交的方式
								dataType : 'json',
								data : {
								userIds : userIds,
								editUserIds:editUserIds
							},
							success : function(result) {
								if (result >= 1) {
								alertify.success("删除成功!");
									loadData();
									}
								}
							});
							}
					});
				}}
		]
		
// 		initToolBarByFuncPower(toolbar,"${funcPowers}");
		// datagrid 表格配置
		$('#table').datagrid({
		url : 'userController/selectUserList',//查询数据的地址
		form : 'selectFrom',//用于筛选的form id
		//字段
		columns : [ 
			{ field : 'id', title : 'ID', width : 100 , hidden:true}, 
			{ field : 'userId', title : '用户账号', width : 100 }, 
			{ field : 'userName', title : '用户名称', width : 100 }, 
			{ field : 'userRoleId', title : '用户角色', width : 100 }, 
			{ field : 'remark', title : '备注', width : 150 }, 
			{field : 'updateBy',title : '变更者',width : 120},
			{field : 'updateDate',title : '变更时间',width : 170}
		],
		//按钮功能栏
		toolbar : toolbar,
		singleSelect : false,//是否单行选中-默认false
		// pageSize : 10 //分页，每页查询数量-默认10
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
			<span class="title">系统用户管理</span>
		</div>
		<div class="widget-body">
			<div class="widget-control">
				<form id="selectFrom" class="widget-form" method="post">
					<div class="widget-screen">
						<label for="userId" class="control-label">用户账号</label>
						<div class="widget-item">
							<input class="form-control" id="userId" name="userId"
								type="text" placeholder="可按用户账号模糊查询">
						</div>
					</div>
					<div class="widget-screen">
						<label for="userName" class="control-label">用户名称</label>
						<div class="widget-item">
							<input class="form-control" id="userName"
								name="userName" type="text" placeholder="可按用户名称模糊查询">
						</div>
					</div>
					<div class="widget-screen">
						<label for="userRoleId" class="control-label">用户角色</label>
						<div class="widget-item">
							<select class="form-control" id="userRoleId"
								name="userRoleId">
								<option value="000">所有</option>
							</select>
						</div>
					</div>
					<button class="btn btn-primary btn-sm" onclick="searchData()" type="button">查 询</button>
				</form>
			</div>
			<!-- 数据表格 -->
			<div id="table"></div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>