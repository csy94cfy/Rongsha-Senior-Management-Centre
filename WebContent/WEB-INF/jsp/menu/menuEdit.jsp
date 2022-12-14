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
$(document).ready(function() {
	// 默认初始化
	$("#menuLevel").comboBox();
	var menuLevel = "${menu.menuLevel}";
	
	// 修改页面 填充状态指定项   
	if(menuLevel){
		$("#menuLevel").comboBox('select',menuLevel);
	}
})
//保存
function save(){
	$('#menuId').valid({notNull : true,len:32});
	$('#menuName').valid({notNull : true,len:40});
	$('#menuServer').valid({notNull : true,len:40});
	$('#menuUrl').valid({notNull : true,len:80});
	$('#menuLevel').valid({comNotNull:true,len:40});
	$('#menuSort').valid({notNull : true,len:40});
	$('#menuPid').valid({notNull : true,len:40});
	if($.valid()){
		var urlAddress = "menuController/updateMenu";
		if($('#hidId').val()==""){//新增
			urlAddress = "menuController/insertMenu";
		}
		//提交保存
		$("#editForm").ajaxSubmit({
			url:urlAddress,
			dataType:"json",
			success: function(data){
				if(data=='1'){
					parent.loadData();
					parent.modal.close()
					parent.alertify.success('操作成功!');
				}else{
					parent.alertify.error('操作失败!');
				}
				
			}
		})
	}
}
</script>
</head>
<body>
	<div class="modal-body">
		<form id="editForm" class="form-horizontal no-margin">
			<input type="hidden" id="hidId" name="id" value="${menu.id}">
			<div class="form-group">
				<label for="menuId" class="col-sm-3 col-xs-3 control-label">菜单代码</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuId" value="${menu.menuId}" name="menuId" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="form-group">
				<label for="menuName" class="col-sm-3 col-xs-3 control-label">菜单名称</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuName" value="${menu.menuName}" name="menuName" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="form-group">
				<label for="menuServer" class="col-sm-3 col-xs-3 control-label">服务器地址</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuServer" value="${menu.menuServer}" name="menuServer" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="form-group">
				<label for="menuUrl" class="col-sm-3 col-xs-3 control-label">菜单URL</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuUrl" value="${menu.menuUrl}" name="menuUrl" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="form-group">
				<label for="menuLevel" class="col-sm-3 col-xs-3 control-label">菜单级别</label>
				<div class="col-sm-8 col-xs-8">
					<select class="form-control" id="menuLevel" name="menuLevel">
						<option selected="selected" value="000">请选择</option>
						<option value="0">一级菜单</option>
						<option value="1">二级菜单</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="menuSort" class="col-sm-3 col-xs-3 control-label">菜单序号</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuSort" value="${menu.menuSort}" name="menuSort" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
			<div class="form-group">
				<label for="menuPid" class="col-sm-3 col-xs-3 control-label">所属菜单</label>
				<div class="col-sm-8 col-xs-8">
					<input id="menuPid" value="${menu.menuPid}" name="menuPid" class="form-control" type="text" maxlength="40"/>
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn btn-success" type="button" onclick="save()"><i class="fa fa-check-circle"></i> 保 存</button>
		<button class="btn btn-danger" type="button" onclick="parent.modal.close()"><i class="fa fa-times-circle"></i> 取 消</button>
	</div>
</body>
</html>