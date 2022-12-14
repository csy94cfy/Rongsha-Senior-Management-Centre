<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.framework.util.ManageConstants"%>
<%
String basePath = (String)request.getServletContext().getAttribute(ManageConstants.BASE_PATH);
String staticPath = (String)request.getServletContext().getAttribute(ManageConstants.STATIC_PATH);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
<jsp:include page="/WEB-INF/jsp/login/include.jsp"></jsp:include>
<script type="text/javascript">

$(function(){
	// 密码失去焦点时验证
	$('#password').blur(function(){
		$('#password').valid({notNull : true,len: 14,});
		$.valid();
	});
    // 确认密码失去焦点时验证
    $('#password2').blur(function(){
		$('#password2').valid({
			define:function(val,error){
				if(val!=$('#password').val()){
					 error('确认密码需与密码相同!');
					 return false;
				}
				return true;
			}
		});
	$.valid();
	}); 
})
//保存
function save(){
	// 防止不输入直接点击保存
	$('#password').valid({notNull : true,len:14});
	// 验证成功 修改密码
	if($.valid()){
		$("#editForm").ajaxSubmit({
			url:"savePassword",
			dataType:"json",
			type : "POST",
			success: function(data){
				// console.log(data)
				if(data>=1){
					parent.modal.close()
					top.alertify.success('修改成功!');
				}else{
					top.alertify.error('修改失败！');
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
 			<div class="form-group">
 				<label for="userId" class="col-sm-3 col-xs-3 control-label">用户编号</label>
				<div class="col-sm-8 col-xs-8">
					<input class="form-control" type="text" id="userId" value="${userId }" disabled="disabled" name="userId"/>
				</div>
	 		</div>
	 		<div class="form-group">
 				<label for="userName" class="col-sm-3 col-xs-3 control-label">用户账号</label>
				<div class="col-sm-8 col-xs-8">
					<input class="form-control" type="text" id="userName" value="${userName }" disabled="disabled" name="userName"/>
				</div>
 			</div>
	 		<div class="form-group">
 				<label for="password" class="col-sm-3 col-xs-3 control-label">新密码</label>
				<div class="col-sm-8 col-xs-8">
					<input id="password" name="password"  type="password" class="form-control" />
				</div>
 			</div>
 			<div class="form-group">
 				<label for="password2" class="col-sm-3 col-xs-3 control-label">重复密码</label>
				<div class="col-sm-8 col-xs-8">
					<input id="password2" type="password" class="form-control" />
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