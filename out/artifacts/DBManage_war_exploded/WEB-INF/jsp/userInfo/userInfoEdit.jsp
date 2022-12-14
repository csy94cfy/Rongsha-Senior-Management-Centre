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
<title>工作人员编辑</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#openid").comboBox();
	// 默认初始化
	$("#openid").comboBox();
	var openid = "${userInfo.openid}";
	
	// 修改页面 填充状态指定项   
	if(openid){
		$("#openid").comboBox('select',openid);
	}
});

//保存
function save(){
	var realName = $('#realName').val();
	if(realName == "" || realName.length == 0){
		parent.alertify.error('请输入真实姓名!');
		return;
	}
	if(realName.length > 20){
		parent.alertify.error('真实姓名长度小于20位!');
		return;
	}
	var userPhone = $('#userPhone').val();
	if(userPhone == "" || userPhone.length == 0){
		parent.alertify.error('请输入电话号码!');
		return;
	}
	if(userPhone.length > 16){
		parent.alertify.error('电话号码长度小于16位!');
		return;
	}	
	if($.valid()){
		var urlAddress = "userInfoController/updateUserInfo";
		if($('#hidId').val()==""){//新增
			urlAddress = "userInfoController/insertUserInfo";
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
	<div class="modal-body" style="height: 300px;">
		<form id="editForm" class="form-horizontal no-margin">
			<input type="hidden" id="hidId" name="id" value="${userInfo.id}">
			<div class="form-group" >
				<label for="realName" class="col-sm-3 col-xs-3 control-label" style="width: 27%;">真实姓名</label>
				<div class="col-sm-8 col-xs-8">
					<input id="realName" value="${userInfo.realName}" name="realName" class="form-control" type="text" placeholder="请输入真实姓名"/>
				</div>
			</div>
			<div class="form-group" >
				<label for="userPhone" class="col-sm-3 col-xs-3 control-label" style="width: 27%;">电话号码</label>
				<div class="col-sm-8 col-xs-8">
					<input id="userPhone" value="${userInfo.userPhone}" name="userPhone" class="form-control" type="text" placeholder="请输入电话号码"/>
				</div>
			</div>
			<div class="form-group" >
				<label for="userPhone" class="col-sm-3 col-xs-3 control-label" style="width: 27%;">电话号码</label>
				<div class="col-sm-8 col-xs-8">
					<select class="form-control" id="openid" name="openid">
								<option value="000">请选择</option>
								<option value="门卫">门卫</option>
								<option value="保洁">保洁</option>
								<option value="护理">护理</option>
								<option value="财务">财务</option>
							</select>
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