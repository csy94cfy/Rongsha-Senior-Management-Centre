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
<title>角色管理</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$.widget.init();
		// 初始化功能按钮
		var toolbar =[
			{text : '新增',validCount : '0+',handler : function(rows){
				modal.open({title:'新增角色',url:'roleController/openEditPage'})
			}},
			{text : '修改',validCount : '1',handler : function(rows){
				modal.open({title:'编辑角色',url:'roleController/openEditPage?id='+rows[0].id})
			}},
			{text : '删除',validCount : '1+',handler : function(rows){
				alertify.confirm("确认删除吗?", function(e){if(e) {
					//取得删除用户列表
					var delRoleList=rows[0].id;
					for(var i=1;i<rows.length;i++){
						delRoleList = delRoleList + "," + rows[i].id;
					}
					
					var roleIds=rows[0].roleId;
					for(var i=1;i<rows.length;i++){
						roleIds = roleIds + "," + rows[i].roleId;
					}
					
					$.ajax({
						url: 'roleController/deleteRole',
						type: 'post',//提交的方式
						dataType:'json',
						data: {id:delRoleList,roleIds:roleIds},
						success: function(result) {
							if (result==-1) {
								alertify.error("角色含有用户不可删除)");
							}
							if(result>=1){
								loadData();
								alertify.success("删除成功!");
							}
						}
					});
				}});
			}},
			{text : '角色权限',validCount : '1',handler : function(rows){
				modal.open({title:'编辑权限',width:400,height:430,url:'roleController/openPowerPage?roleId='+rows[0].roleId})
			}},
		]		
		
		
		$('#table').datagrid({
			url : 'roleController/selectRoleList',//查询数据的地址
			form : 'selectform',//用于筛选的form id
			//字段
			columns : [
				{field : 'id',title : '流水号',width : 80,hidden:true},
				{field : 'roleId',title : '角色代码',width : 100,sortable : true},
				{field : 'roleName',title : '角色名称',width : 100},
				{field : 'userNumber',title : '用户数量',width : 100},
				{field : 'remark',title : '备注',width : 150},
				{field : 'updateBy',title : '变更者',width : 120,sortable : true},
				{field : 'updateDate',title : '变更时间',width : 170,sortable : true}
			],
			//按钮功能栏
			toolbar : toolbar,
			singleSelect : false //是否单行选中-默认false
			//,pageSize : 10//分页，每页查询数量-默认10
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
			<span class="title">角色管理</span>
		</div>
		<div class="widget-body">
			<div class="widget-control">
				<form id="selectform" class="widget-form" method="post">
					<div class="widget-screen">
						<label for="roleId" class="control-label">角色编码</label>
						<div class="widget-item">
							<input class="form-control" id="roleId" name="roleId" type="text" placeholder="可按角色代码模糊查询">
						</div>
					</div>
					<div class="widget-screen">
						<label for="roleName" class="control-label">角色名称</label>
						<div class="widget-item">
							<input class="form-control" id="roleName" name="roleName" type="text" placeholder="可按角色名称模糊查询">
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