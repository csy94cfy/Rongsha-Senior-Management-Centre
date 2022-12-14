/*根据当前页面是否有帮助信息，显示帮助按锯*/
function setHelpInfo(obj,helpInfoId){
	//初始化帮助信息
	if("none"==helpInfoId){
		// 如果帮助信息不存在  则默认不显示 帮助信息按钮
		obj.css({display:"none"})
	}else{
		//如果帮助信息存在   则点击按钮时 打开帮助信息查看页面
		obj.click(function(){
			modal.open({title:'帮助信息查看',height:450,url:'commonController/showHelpInfoPage?id='+helpInfoId})
		}); 
	}
}

/*格式化日期*/
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

$.ajaxSetup({   
	contentType:"application/x-www-form-urlencoded;charset=utf-8",   
	complete:function(XMLHttpRequest,textStatus){ 
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，  
		if(sessionstatus=="timeout"){ 
			alert("登录超时,请重新登录！");
			//如果超时就处理 ，指定要跳转的页面  
			location.href="timeOutController";
		}   
	}   
});

//显示提醒信息
var ShowMsg={ 
	title:'提示', 
	content:'模拟qq弹出框消息提醒', 
	width:'300px', 
	height:'100px', 
	setTitle:function(value){ 
		this.title=value; 
	}, 
	setContent:function(value){ 
		this.content=value; 
	}, 
	getTitle:function(){ 
		return this.title; 
	}, 
	getContent:function(){ 
		return this.content; 
	}, 
	show:function(){ 
		//清除
		if(!!document.getElementById("_winPopDiv")){
			document.body.removeChild(document.getElementById("_winPopDiv")); 
		}
		//弹窗div 
		var _winPopDiv = document.createElement('div');  
		_winPopDiv.id="_winPopDiv";  
		_winPopDiv.style.cssText = 'background: white;width:300px; position:absolute; right:0; bottom:0; border:1px solid #666; margin:0; padding:1px; overflow:hidden; display:block;'; 
		//消息标题div 
		var _titleDiv= document.createElement('div');  
		_titleDiv.id="_titleDiv";   
		_titleDiv.innerHTML=this.getTitle();  
		_titleDiv.style.cssText = 'width:100%; height:22px; line-height:20px; background:#2FA7E8; font-weight:bold; text-align:left; font-size:14px;';  
		_winPopDiv.appendChild(_titleDiv); 
		//关闭消息按钮span 
		var _closeSpan= document.createElement('span');  
		_closeSpan.id="_closeSpan";  
		_closeSpan.innerHTML="关闭"; 
		_closeSpan.style.cssText = 'position:absolute; right:4px; top:-1px; color:#FFF; cursor:pointer;font-size:14px;';  
		_titleDiv.appendChild(_closeSpan);  
		//内容div 
		var _conDiv= document.createElement('div');  
		_conDiv.id="_conDiv";  
		//_conDiv.style.cssText = 'margin:10px;border:solid 1px #ccc;background:#DBDBDB;width:280px; min-height:100px; font-weight:bold; font-size:12px; color:red; text-decoration:underline; text-align:left;';  
		_conDiv.style.cssText = 'margin:10px;border:solid 1px #ccc;background:#DBDBDB;width:280px; min-height:100px;';
		_conDiv.innerHTML=this.getContent(); 
		_winPopDiv.appendChild(_conDiv);   
		document.body.appendChild(_winPopDiv);  
		//关闭span绑定事件 
		var closeDiv = document.getElementById("_closeSpan");
		if(!!closeDiv.addEventListener){ 
			closeDiv.addEventListener("click",function(e){  
				if (window.event != undefined) {  
					window.event.cancelBubble = true;  
				} else if (e.stopPropagation) {  
					e.stopPropagation();  
				}  
			document.getElementById('_winPopDiv').style.cssText="display:none;"; 
			},false); 
		}else if(!!closeDiv.attachEvent){ 
			closeDiv.attachEvent("onclick",function(e){
			if (window.event != undefined) {  
				window.event.cancelBubble = true;  
			} else if (e.stopPropagation) {  
				e.stopPropagation();  
			}  
			document.getElementById('_winPopDiv').style.cssText="display:none;"; 
			}); 
		} 
	} 
}; 
		



var CDOT = "CDOT";				//There is a decimal point.
var CNUMONLY = "CNUMONLY";		//There is not a decimal point.


/** 
 * @Function_Name: filterKeyForNumber 
 * @Description: 限制Input控件输入数字，onkeypress内调用
 * @param obj--限制输入对象
 * @param strFlg 
 *			CDOT:带小数点数字
 *			CNUMONLY:纯数字
 * @author misty
 * @date 2014年4月8日 下午10:09:11 
 */
function filterKeyForNumber(obj, strFlg) {
	switch (strFlg) {
		case CDOT:
			if ((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 46
					|| /[^\d\.]/.test(obj.value)) {
				event.returnValue = false;
			}
			break;
		case CNUMONLY:
			if (event.keyCode < 48 || event.keyCode > 57) {
				event.returnValue = false;
			}
			break;
	}
}

/**	
 * @FunctionName: placeHolder	
 * @Description: 跨浏览器placeHolder,对于不支持原生placeHolder的浏览器，通过value或插入span元素两种方案模拟	
 * @param obj{Object} 要应用placeHolder的表单元素对象
 * @param span{Boolean}是否采用悬浮的span元素方式来模拟placeHolder，默认值false,默认使用value方式模拟
 * @return void	
 * @author ljg	
 * @date 2015年2月2日 下午2:42:25	
 */	
function placeHolder(obj, span) {
	if (!obj.getAttribute('placeholder')) return;
	var imitateMode = span === true ? true: false;
	var supportPlaceholder = 'placeholder' in document.createElement('input');
	if (!supportPlaceholder) {
		var defaultValue = obj.getAttribute('placeholder');
		if (!imitateMode) {
			obj.onfocus = function() { (obj.value == defaultValue) && (obj.value = '');
				obj.style.color = '';
			}
			obj.onblur = function() {
				if (obj.value == defaultValue) {
					obj.style.color = '';
				} else if (obj.value == '') {
					obj.value = defaultValue;
					obj.style.color = '#ACA899';
				}
			}
			obj.onblur();
		} else {
			var placeHolderCont = document.createTextNode(defaultValue);
			var oWrapper = document.createElement('span');
			oWrapper.style.cssText = 'position:absolute; color:#ACA899; display:inline-block; overflow:hidden;';
			oWrapper.className = 'wrap-placeholder';
			oWrapper.style.fontFamily = getStyle(obj, 'fontFamily');
			oWrapper.style.fontSize = getStyle(obj, 'fontSize');
			oWrapper.style.marginLeft = parseInt(getStyle(obj, 'marginLeft')) ? parseInt(getStyle(obj, 'marginLeft')) + 3 + 'px': 3 + 'px';
			oWrapper.style.marginTop = parseInt(getStyle(obj, 'marginTop')) ? getStyle(obj, 'marginTop') : 1 + 'px';
			oWrapper.style.paddingLeft = getStyle(obj, 'paddingLeft');
			oWrapper.style.width = obj.offsetWidth - parseInt(getStyle(obj, 'marginLeft')) + 'px';
			oWrapper.style.height = obj.offsetHeight + 'px';
			oWrapper.style.lineHeight = obj.nodeName.toLowerCase() == 'textarea' ? '': obj.offsetHeight + 'px';
			oWrapper.appendChild(placeHolderCont);
			obj.parentNode.insertBefore(oWrapper, obj);
			oWrapper.onclick = function() {
				obj.focus();
			}
			
			//删除字符时
			obj.onkeyup = function(){
				oWrapper.style.display = this.value != '' ? 'none': 'inline-block';
			}
			
			obj.onblur = changeHandler;
			
			//绑定input或onpropertychange事件
			if (typeof(obj.oninput) == 'object') {
				obj.addEventListener("input", changeHandler, false);
			} else {
				obj.onpropertychange = changeHandler;
			}
			function changeHandler() {
				oWrapper.style.display = obj.value != '' ? 'none': 'inline-block';
			}
			/**
				 * @name getStyle
				 * @class 获取样式
				 * @param {Object} obj 要获取样式的对象
				 * @param {String} styleName 要获取的样式名
				 */
			function getStyle(obj, styleName) {
				var oStyle = null;
				if (obj.currentStyle) oStyle = obj.currentStyle[styleName];
				else if (window.getComputedStyle) oStyle = window.getComputedStyle(obj, null)[styleName];
				return oStyle;
			}
		}
	}
}


/** 
 * @Function_Name: filterKeybordAZaz09 
 * @Description: 限制输入A~Z，a~z,0~9 
 * @param obj 返回值说明
 * @author misty
 * @date 2014年4月8日 下午10:15:50 
 */
function filterKeybordAZaz09(obj){
	if((event.keyCode<48 || event.keyCode>57)
		&& (event.keyCode<65 || event.keyCode>90)
		&& (event.keyCode<97 || event.keyCode>122)){
		event.returnValue=false;
	}
}

/** 
 * @Function_Name: filterKeybordAZaz09 
 * @Description: 限制输入大小写字母 
 * @param obj 返回值说明
 * @author misty
 * @date 2014年4月8日 下午10:17:07 
 */
function filterKeybordAZaz(obj){
	if((event.keyCode<65 || event.keyCode>90)
		&& (event.keyCode<97 || event.keyCode>122)){
		event.returnValue=false;
	}
}

/* 左补0 */  
function pad(num, n) {  
	var len = num.toString().length;  
	while(len < n) {  
		num = "0" + num;  
		len++;  
	}
	return num;  
}

function nullSafe(obj){
	if (obj == undefined || obj == null || obj == "null") {
		return "";
	}
	return obj;
}

/*根据名称取得当前页面的参数值*/
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return decodeURI(r[2]); return null;
}

/*
 * 计算两个日期的间隔天数
 * BeginDate:起始日期的文本框，格式為：2012-01-01
 * EndDate:結束日期的文本框，格式為：2012-01-02
 * 返回兩個日期所差的天數
 * 調用方法：
 * alert("相差"+Computation("date1","date2")+"天");
 */
function GetDateRegion(BeginDate,EndDate){
	var aDate, oDate1, oDate2, iDays;
	aDate = BeginDate.split("-");
	oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);   //转换为12/13/2008格式
	aDate = EndDate.split("-");
	oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
	//iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24)+1;   //把相差的毫秒数转换为天数
	var i=(oDate2 - oDate1) / 1000 / 60 / 60 /24;
	if(i<0){
		i-=1;
	}else{
		i+=1;
	}
	iDays = i;   //把相差的毫秒数转换为天数
	//alert(iDays);
	return iDays;
 }

function getCurrDate(){
	var result,year,mon,date,today = new Date();;
	year= today.getFullYear();
	mon = today.getMonth() + 1;
	mon = (mon<10) ? "0"+mon : mon;
	date= today.getDate();
	date = (date<10) ? "0"+date : date;
	result = year +"-"+mon+"-"+date;
	return result;
 }
 
 /**	
 * @FunctionName: getTax	
 * @Description: 根据工资计算出所得税
 * @param salary
 * @returns {Number}
 * @return Number-所得税
 * @author ljg	
 * @date 2015年3月30日 下午5:06:27	
 */	
function getTax(salary){
		var tax = 0;
		var salaryPre = salary - 3500;
		if( salaryPre < 0 ){
			tax = 0;
		}else if(salaryPre<=1500 && salaryPre > 0){
			tax = salaryPre*0.03;
		}else if(salaryPre<=4500){
			tax = salaryPre*0.1 - 105;
		}else if(salaryPre<=9000){
			tax = salaryPre*0.2 - 555;
		}else if(salaryPre<=35000){
			tax = salaryPre*0.25 - 1005;
		}else if(salaryPre<=55000){
			tax = salaryPre*0.30 - 2755;
		}else if(salaryPre<=80000){
			tax = salaryPre*0.35 - 5505;
		}else if(salaryPre > 80000){
			tax = salaryPre*0.45 - 13505;
		}
		return tax;
	}


$.queryCondition = (function(){
	var queryConditionDom = $('<div>已选择：</div>');
	queryConditionDom.css({'float':'left','width':'100%','margin-left':' -15px','color':'red'});
	
	//获取easyui对象的显示信息
	function _getText(easyuiObj){
// 		var label = $(easyuiObj).siblings('div').html();
		var val = $(easyuiObj).textbox('getValue');
		var text = $(easyuiObj).textbox('getText');
		if(text == '' || val == '' || val == '000')
			return '';
// 		return "【"+label+":"+text+"】";
		return "【"+text+"】";
	}
	
	//设置查询信息
	function setQueryInfo(){
		var queryEasyuiDom = $('.easyui-textbox,.easyui-combobox,.easyui-datebox');
		queryConditionDom.html('已选择：');
		for(var i=0;i<queryEasyuiDom.length;i++){
			queryConditionDom.append(_getText(queryEasyuiDom[i]));
		}
	}
	
	var controllBtn = $('<a type="0" class="easyui-linkbutton" style="float:left;cursor: pointer;margin-left:5px;margin-top: 15px;">更多...</a>');
	controllBtn.on('click',function(){
		if($(this).attr('type') == '0'){
			_showParam();
		}else{
			_hideParam();
		}
	});
	
	function _hideParam(){
		$(controllBtn).html('更多...');
		$(controllBtn).attr('type', 0);
		$(".hide-query-param").animate({height:0},300,function(){
			$(this).css('height','auto');
			$(this).hide();
		});
	}
	
	function _showParam(){
		$(controllBtn).html('关闭');
		$(controllBtn).attr('type', 1);
		$('.hide-query-param').show();
		var tempHeight = $('.hide-query-param').height();
		$(".hide-query-param").height(0);
		$(".hide-query-param").animate({height:tempHeight},300,function(){
			$(this).css('height','auto');
		});
	}
	
	function _init(){
		$('.easyui-panel').append(queryConditionDom);
		
		$('.hide-query-param').css({
			'float':'left',
			'width':'100%',
			'overflow':'hidden',
			'display':'none'
		});
		$('.hide-query-param>div').css({
			'width':'auto',
			'padding':'0px'
		});
		
		$('.hide-query-param').before(controllBtn);
		
		
		//为所有combobox添加onChange事件
		var easyuiComboboxObjs = $('.easyui-combobox');
		for(var i=0;i<easyuiComboboxObjs.length;i++){
			var obj = easyuiComboboxObjs[i];
			//原先的OnChange方法
			var _oldOnChange = $(obj).combobox("options").onChange;
			(function(obj,oldOnChange){
				$(obj).combobox({
					onChange : function(newVal,oldVal){
						setQueryInfo();
						oldOnChange(newVal,oldVal);
					}
				});
			})(obj,_oldOnChange);
		}
		
		//为所有datebox添加onChange事件
		var easyuiDateboxObjs = $('.easyui-datebox');
		for(var i=0;i<easyuiDateboxObjs.length;i++){
			var obj = easyuiDateboxObjs[i];
			//原先的OnChange方法
			var _oldOnChange = $(obj).datebox("options").onChange;
			(function(obj,oldOnChange){
				$(obj).datebox({
					onChange : function(newVal,oldVal){
						setQueryInfo();
						oldOnChange(newVal,oldVal);
					}
				});
			})(obj,_oldOnChange);
		}
		
		//为所有textbox添加onChange事件
		var easyuiTextboxObjs = $('.easyui-textbox');
		for(var i=0;i<easyuiTextboxObjs.length;i++){
			var obj = easyuiTextboxObjs[i];
			//原先的OnChange方法
			var _oldOnChange = $(obj).textbox("options").onChange;
			(function(obj,oldOnChange){
				$(obj).textbox({
					onChange : function(newVal,oldVal){
						setQueryInfo();
						oldOnChange(newVal,oldVal);
					}
				});
			})(obj,_oldOnChange);
		}
		
	}
	
	return {
		init : function(){
			_init();
		},
		show : function(){
			_showParam();
		},
		hide : function(){
			_hideParam();
		}
	}
})();


/**
* 按钮设置3秒内不可点击
*/
var last_click_time; 
function setButttonDisable3Secound() {
	if (last_click_time) {
		var current_time = new Date().getTime();
		if (current_time - last_click_time > 3000) {
			last_click_time = current_time;
		} else {
			alertify.error("点击过快,请稍后重试！");
			return "false";
		}
	} else {
		last_click_time = new Date().getTime();
	}
}


/**
 * 获取页面查询条件
 */
function getQueryCondition(){
	var  query_condition ='';	   
	$(".form-control:not([combobox-flag=show])").each(	
		function(){
			if($(this).val()){
				if(undefined != $(this).parent().prev().html()){
						var condition = $(this).val();
						if($(this).attr('combobox-name')){
							condition = $(this).comboBox('getText');
						}
						query_condition += '[' + $(this).parent().prev().html() + ':' + condition + ']' + ' ';
				} else {
					query_condition += '[' + $(this).val() + ']' + ' ';   
				}
			}		 
		} 
	);
	return query_condition;
}

/**
 * 根据用户权限确定是否显示toolBar按钮
 * toolbar 拥有全部功能的字符toolbar
 * funcPowers 功能权限字符串 用，分割
 * */
function initToolBarByFuncPower(toolbar,funcPowers){
	// console.log(funcPowers)
	for(var i =0;i<toolbar.length;i++){
		// 如果用户没有权限  则移除数组中的按钮
		if((funcPowers).indexOf(toolbar[i].text)==-1){
			toolbar.splice(i,1);
			// 移除按钮后 调整数组下标
			i--;
		}
	}
}

/*表单按钮权限*/
function btnPower(btnName,condPowers){
	if(!(condPowers.search(btnName.text().replace(" ","")) != -1)){
		btnName.hide();
	}else {
		btnName.show();
	}
}
