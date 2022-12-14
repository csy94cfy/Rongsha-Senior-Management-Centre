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
<title>老年人信息管理</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		//页面加载
		$.widget.init();
// 		$("#searchFlag").comboBox();
		// 初始化功能按钮
		var toolbar = [
			{ text : '新增', validCount : '0+', 
			handler : function(rows) {
				modal.open({width:'850px',height:'450px', title : '添加老年人信息', url : 'chuangweiController/openEditPage' })
			} 
		},
			{text : '修改',validCount : '1',handler : function(rows){
				modal.open({width:'850px',height:'450px',title:'编辑老年人信息',url:'chuangweiController/openEditPage?id='+rows[0].id})
			}},
			{text : '删除',validCount : '1+',handler : function(rows){
				alertify.confirm("确认删除吗?", function(e){
					if(e) {
						//取得删除用户列表
						var delJoinInfoIds=rows[0].id;
						for(var i=1;i<rows.length;i++){
							delJoinInfoIds = delJoinInfoIds + "," + rows[i].id;
					}
					$.ajax({
						url: 'chuangweiController/deleteUserInfo',
						type: 'post',//提交的方式
						dataType:'json',
						data: {ids:delJoinInfoIds},
						success: function(result) {
							if(result>=1){
								loadData();
								alertify.success("删除成功!");
							}
						}
					});
				}});
			}}		
		]
		//列表方法
		$('#table').datagrid({
			url : 'chuangweiController/selectUserInfoList',//查询数据的地址
			form : 'selectform',//用于筛选的form id
			//字段
			columns : [
				{field : 'realName',title : '老人姓名',width : 150},
				{field : 'userPhone',title : '老人年龄',width : 130},
				{field : 'openid',title : '居住类型',width : 170}
			],
			//按钮功能栏
			toolbar : toolbar,
			singleSelect : false//是否单行选中-默认false
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
			<span class="title">老年人信息管理</span>
		</div>
		<div class="widget-body">
			<div class="widget-control">
				<form id="selectform" class="widget-form" method="post">
				<input id="queryCondition"type="hidden" name="queryCondition" >
					<div class="widget-screen">
						<label for="realName" class="control-label">老人姓名</label>
						<div class="widget-item">
							<input class="form-control" name=realName type="text"  >
						</div>
					</div>
					<div class="widget-screen">
						<label for="userPhone" class="control-label">年龄</label>
						<div class="widget-item">
							<input class="form-control" name="userPhone" type="text" >
						</div>
					</div>
					<div class="widget-screen">
						<label for="openid" class="control-label">居住类型</label>
						<div class="widget-item">
							<select class="form-control" id="openid" name="openid">
								<option value="000">所有</option>
								<option value="居家">居家</option>
								<option value="社区养老中心">社区养老中心</option>
								<option value="养老机构">养老机构</option>
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