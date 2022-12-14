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
<title>角色编辑</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=staticPath%>bin/plugin/ztree/bootstrapStyle.css" />
<script type="text/javascript" src="<%=staticPath%>bin/plugin/ztree/jquery.ztree.core.js" ></script>
<script type="text/javascript" src="<%=staticPath%>bin/plugin/ztree/jquery.ztree.excheck.js" ></script>
<script type="text/javascript" src="<%=staticPath%>bin/plugin/ztree/jquery.ztree.exedit.js" ></script>

<style>
	div.content_wrap {width: 600px;height:180px;}
	div.content_wrap div.left{float: left;width: 250px;}
	div.content_wrap div.right{float: right;width: 340px;}
	div.zTreeDemoBackground {width:200px;height:100%;text-align:left;}
	
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:370px;height:300px;overflow-y:scroll;overflow-x:auto;}
	ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
	ul.log.small {height:45px;}
	ul.log li {color: #666666;list-style: none;padding-left: 10px;}
	ul.log li.dark {background-color: #E3E3E3;}
</style>

<script type="text/javascript">
var strRoleId = "${role.roleId}";
$('#hidRoleId').val(strRoleId);
var setting = {
	view: {
		selectedMulti: false
	},
	check: {
		enable: true
	},
	async: {
		enable: true,
		url:'roleController/getPowerMenuByRole?roleId='+strRoleId
		// autoParam:[],
		// contentType: "application/json",
		// otherParam:{}
		// dataFilter: filter //异步获取的数据filter 里面可以进行处理  filter 在下面
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

$(document).ready(function(){
	$.fn.zTree.init($("#rolePowerTree"), setting);
});


// 点击保存按钮 执行保存
function save(){
	// 获取选中的城市
	var treeObj = $.fn.zTree.getZTreeObj("rolePowerTree");
	var nodes = treeObj.getCheckedNodes(true);
	
	//console.log(nodes);
	
	var menuIds ='';
	
	// 拼接选中的一级菜单 二级菜单 和  功能
	for(var i = 0;i < nodes.length;i++){
		if(nodes[i].level=='0'||nodes[i].level=='1'){
			menuIds +=nodes[i].id+',';
			//console.log(nodes[i].id+":0 and 1");
		}else if(nodes[i].level=='2'){
			funcIds+=nodes[i].id+'-'+nodes[i].pId+',';
			//console.log(nodes[i].name+":2");
		}
	}
	
	// console.log("menuIds"+menuIds);
	// console.log("funcIds"+funcIds);
	
	// 修改用户角色权限
	$.ajax({
		type : "POST",
		url : "roleController/updateRolePower",
		data : {
			menuIds:menuIds,
			roleId:strRoleId
		},
		datatype : "json",
		success : function(data) {
			if(data>0){
				parent.modal.close();
				parent.alertify.success('修改角色权限成功!');
			}else{
				parent.alertify.error('修改角色权限失败!');
			}
		} 
	}); 
}



</script>
</head>
<body>
	<div class="modal-body">
		<form id="editForm" class="form-horizontal no-margin">
			<input type="hidden" id="hidRoleId">
			<ul id="rolePowerTree" class="ztree"></ul>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn btn-success" type="button" onclick="save()"><i class="fa fa-check-circle"></i> 保 存</button>
		<button class="btn btn-danger" type="button" onclick="parent.modal.close()"><i class="fa fa-times-circle"></i> 取 消</button>
	</div>
</body>
</html>