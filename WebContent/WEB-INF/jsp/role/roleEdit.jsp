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
<script type="text/javascript">
$(document).ready(function() {
	if($('#hidId').val()!=""){//修改
		$("#roleId").attr("readOnly","true");
	}
})
//保存
function save(){
	$('#roleId').valid({notNull : true,len : 10});
	$('#roleName').valid({notNull : true,len : 20});
	$('#remark').valid({len : 80});
	if($.valid()){
		var urlAddress = "roleController/updateRole";
		if($('#hidId').val()==""){//新增
			urlAddress = "roleController/insertRole";
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
			<input type="hidden" id="hidId" name="id" value="${role.id}">
			<div class="form-group">
				<label for="roleId" class="col-sm-3 col-xs-3 control-label">角色代码</label>
				<div class="col-sm-8 col-xs-8">
					<input id="roleId" value="${role.roleId}" name="roleId" class="form-control" type="text" placeholder="请输入建议长度为4的代码"/>
				</div>
			</div>
			<div class="form-group">
				<label for="roleName" class="col-sm-3 col-xs-3 control-label">角色名称</label>
				<div class="col-sm-8 col-xs-8">
					<input id="roleName" value="${role.roleName}" name="roleName" class="form-control" type="text" placeholder="请输入角色名称"/>
				</div>
			</div>
			<div class="form-group">
				<label for="remark" class="col-sm-3 col-xs-3 control-label">备注</label>
				<div class="col-sm-8 col-xs-8">
					<input id="remark" value="${role.remark}" name="remark" class="form-control" type="text" placeholder="请输入备注信息"/>
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