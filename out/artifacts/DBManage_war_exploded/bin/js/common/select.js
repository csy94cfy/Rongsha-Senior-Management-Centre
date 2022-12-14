function loadComboBox($obj,type,code){
	
	// 加载是否成功标识
	var loadFlag = false;
	
	// 加载url地址
	var urlAddr ="";
	
	if(type == "getSourceDataBaseList"){
		urlAddr = "commonController/getSourceDataBaseList";
	}else
	// 列出用户角色编辑列表
	if(type == "getTargetDataBaseList"){
		urlAddr = "commonController/getTargetDataBaseList";
	}else
	// 根据当前用户权限，列出相应的区域查询列表
	if(type == "getDataNameList"){
		urlAddr = "commonController/getDataNameList?para="+code;
	}	
	
	$obj.comboBox({
		editable:false,
		url : urlAddr,
		onLoadSuccess:function(data){
			if(code){
				$obj.comboBox('select',code);
			}else{
				$obj.comboBox('select','000');
			}
			loadFlag = true;		
		}
		
	})
	return loadFlag;
}





function loadRoleList($obj,type,code,hidden){
	
	// 加载是否成功标识
	var loadFlag = false;
	
	// 加载url地址
	var urlAddr ="";
	// 列出用户角色查询列表
	if(type == "SEARCH_ROLE"){
		urlAddr = "commonController/getSearchRoleByPowerType";
	}else
	// 列出用户角色编辑列表
	if(type == "EDIT_ROLE"){
		urlAddr = "commonController/getEditRoleByPowerType";
	}	
	$obj.comboBox({
		editable:false,
		url : urlAddr,
		onLoadSuccess:function(data){
			if(data.length == 2 && (type == "SEARCH_CITY_BY_USER" || type == "EDIT_CITY_BY_USER")){
				// 默认 选择
				$obj.comboBox('select',data[1].code);
				
				if (hidden!=false) {
					// console.log("默认不显示！");
					$obj.parent().parent().hide();	
				}
			}else if(type == "SEARCH_ALL_PROVINCE"){
				if(code){
					$obj.comboBox('select',code);
				}else{
					$obj.comboBox('select','100000');
				}
			}else{
				if(code){
					$obj.comboBox('select',code);
				}else{
					$obj.comboBox('select','000');
				}
			}
			loadFlag = true;		
		}
		
	})
	return loadFlag;
}