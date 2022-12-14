function getThisPath(){
    var scriptSrc = document.getElementsByTagName('script')[document.getElementsByTagName('script').length -1].src;
    var jsName = scriptSrc.split('/')[scriptSrc.split('/').length-1];
    return scriptSrc.replace(jsName,'');
}
var thisPagePath = getThisPath();

(function($){
	/**
	 * comboBox
	 * @version 1.1.1
	 */
	$.fn.comboBox = (function(){
		var _COMBO_BOX_OBJS = {};
		var _index = 0;
		return function(){
			var comboBoxName = $(this).attr('combobox-name');
			var comboBoxObj = null;
			if(comboBoxName)
				comboBoxObj = _COMBO_BOX_OBJS[comboBoxName]
			var paramKey = arguments[0] || {};
			if(typeof paramKey === 'object'){
				if(!comboBoxName){
					//下拉框初始化
					comboBoxName = 'combobox'+_index;
					$(this).attr('combobox-name',comboBoxName);
					paramKey['$originalDom'] = $(this);
					comboBoxObj = new ComboBox(paramKey);
					_COMBO_BOX_OBJS[comboBoxName] = comboBoxObj;
					comboBoxObj.init({firstInit:true});
					_index++;
				}else{
					comboBoxObj.init(paramKey);
				}
			}else if(typeof paramKey === 'string'){
				return comboBoxObj[paramKey].apply(comboBoxObj,Array.prototype.slice.apply(arguments).slice(1));
			}
			
		}
	})();
	function ComboBox(paramObj){
		var that = this;
		//请求取值的url
		this.url = paramObj.url || "";
		//取值的字段名
		this.valueField = paramObj.valueField || "code";
		//取文本的字段名
		this.textField = paramObj.valueField || "name";
		//选项卡数据数组
		this.optionsArray = paramObj.data || [];//{code:1,text:'显示文字'}
		//默认值
		this.defaultValue = paramObj.defaultValue || null;
		
		//下拉框加载事件
		this.onLoadSuccess = paramObj.onLoadSuccess || function(){};
		//值更改事件
		this.onChange = paramObj.onChange || function(){};
		this.onWidget = paramObj.$originalDom.data('onWidget') || function(){};
		
		this.onSelect = paramObj.onSelect || function(){};
		this.readonly = paramObj.readonly == false ? false: true;
		var optionsHeight = paramObj.height || '200px';
		
		
		//原控件dom对象
		this.$originalDom = paramObj.$originalDom;
		//替代原dom元素的控件
		this.$valDom = $('<input type="hidden" class="form-control" combobox-name="'+this.$originalDom.attr('combobox-name')+'" id="'+this.$originalDom.attr('id')+'" name="'+this.$originalDom.attr('name')+'" />')
		//去掉原控件的name 并修改Id
		this.$originalDom.attr({id:this.$originalDom.attr('id')+'_original',name:''});
		this.$originalDom.after(this.$valDom);
		//下拉框最外层dom元素
		this.$comboDiv = $('<div combobox-name="'+this.$originalDom.attr('combobox-name')+'" class="combobox-div"></div>');
		this.$originalDom.after(this.$comboDiv);
		this.$originalDom.removeClass('form-control');
		this.$originalDom.hide();
		//输入框dom元素
		this.$controlInput = $('<input class="form-control" style="background-color:transparent;cursor:context-menu" combobox-flag="show" type="text"/>');
		this.$controlInput.attr('readonly',this.readonly);
		
		this.$comboDiv.append(this.$controlInput);
		//图标dom元素
		this.$icon = $('<div class="fa fa-angle-down combobox-icon" style="cursor:context-menu"></div>');
		this.$comboDiv.append(this.$icon);
		//选项卡dom元素
		this.$optionDiv = $('<div class="combobox-options"></div>');
		this.$optionDiv.css('max-height',optionsHeight);
		this.$comboDiv.append(this.$optionDiv);
		//输入框失去焦点-隐藏选项
		this.$controlInput.on('blur',function(){
			that.$optionDiv.hide()
		});
		//输入框获取焦点-显示选项
		this.$controlInput.on('focus',function(){
			that.$optionDiv.show();
			//调整滚动条位置
			var $selected = that.$optionDiv.find('.selected');
			if($selected.length > 0){
				var nowScrollTop = that.$optionDiv.scrollTop();
				var $selectedTop = $selected.offset().top - that.$optionDiv.offset().top 
				that.$optionDiv.scrollTop($selectedTop + nowScrollTop);
			}
		});
		this.$icon.on('click',function(){
			that.$controlInput.focus();
		});
	}
	ComboBox.prototype.init = function(paramObj){
		paramObj = paramObj || {};
		
		var _oldUrl = this.url;
		var _oldOptionsArray = this.optionsArray;
		
		//请求取值的url
		this.url = paramObj.url || this.url;
		//取值的字段名
		this.valueField = paramObj.valueField || this.valueField;
		//取文本的字段名
		this.textField = paramObj.valueField || this.textField;
		//选项卡数据数组
		this.optionsArray = paramObj.data || this.optionsArray;//{code:1,text:'显示文字'}
		//默认值
		this.defaultValue = paramObj.defaultValue || this.defaultValue;
		this.readonly = paramObj.readonly == false ? false: true;
		this.$controlInput.attr('readonly',this.readonly);
		//下拉框加载事件
		this.onLoadSuccess = paramObj.onLoadSuccess || this.onLoadSuccess;
		//值更改事件
		this.onChange = paramObj.onChange || this.onChange;
		this.onWidget = paramObj.onWidget || this.onWidget;
		this.onSelect = paramObj.onSelect || this.onSelect;
		//设置选项数据
		if(paramObj.firstInit || (this.optionsArray.length <= 0 && _oldOptionsArray != this.optionsArray) || (!!this.url && _oldUrl != this.url)){
			_setOptions.call(this,function(){
				//初始化选项
				_initOptions.call(this);				
			});
		}
	}
	ComboBox.prototype.getComboBox = function(){
		return this;
	}
	/**
	 * 设置值 通过显示的文字 选择选项
	 */
	ComboBox.prototype.setValue = function(val,changeFlag){
		var $obj = this.$optionDiv.find('div:contains('+val+')');
		if($obj.length == 0){
			_selectedOption.call(this,$obj,changeFlag);
			return true;
		}else if($obj.length > 0){
			_selectedOption.call(this,$($obj[0]),changeFlag);
			return true;
		}
		return false;
	}
	/**
	 * 获取显示的文字
	 */
	ComboBox.prototype.getText = function(){
		return this.$controlInput.val();
	}
	/**
	 * 获取显示的文字
	 */
	ComboBox.prototype.getValue = function(){
		return this.$valDom.val();
	}
	/**
	 * 选择 通过选项的value 选择选项
	 */
	ComboBox.prototype.select = function(val,changeFlag){
		var $obj = this.$optionDiv.find('div[value='+val+']');
		if($obj.length > 0){
			_selectedOption.call(this,$obj,changeFlag);
			return true;
		}else if($obj.length > 0){
			_selectedOption.call(this,$($obj[0]),changeFlag);
			return true;
		}
		return false;
	}
	/**
	 * 选中选项
	 */
	var _selectedOption = function($option,changeFlag){
		var _oldValue = this.$valDom.val();
		var _newValue = $option.attr('value');
		this.$optionDiv.find('div').removeClass('selected');
		$option.addClass('selected');
		this.$controlInput.val($option.text());
		this.$valDom.val(_newValue);
		this.$optionDiv.hide();
		changeFlag != false && typeof this.onChange === 'function' && this.onChange(_newValue,_oldValue);
		changeFlag != false && typeof this.onWidget === 'function' && this.onWidget(_newValue,_oldValue);
	}
	/**
	 * 初始化选项
	 */
	_initOptions = function(){
		var that = this;
		this.$optionDiv.empty();
		for(var i=0;i<this.optionsArray.length;i++){
			this.$optionDiv.append('<div class="combobox-option" value="'+this.optionsArray[i].code+'" title="'+this.optionsArray[i].text+'">'+this.optionsArray[i].text+'</div>');
		}
		//选项的鼠标移入事件
		this.$optionDiv.find('div').on('mouseover',function(){
			if(!$(this).hasClass('selected'))
				$(this).css('background-color','#eee');
			that.$controlInput.unbind('blur');
		});
		//选项的鼠标移出事件
		this.$optionDiv.find('div').on('mouseout',function(){
			if(!$(this).hasClass('selected'))
				$(this).css('background-color','#fff');
			that.$controlInput.on('blur',function(){
				that.$optionDiv.hide()
			});
		});
		//选项的点击事件
		this.$optionDiv.find('div').on('click',function(){
			typeof that.onSelect === 'function' && that.onSelect($(this).attr('value'),$(this).html());
			_selectedOption.call(that,$(this));
		});
		
		if(this.defaultValue != null && this.defaultValue != ""){
			this.select(this.defaultValue,false);
		}else{
			if(this.$optionDiv.length>0)
				this.select(this.$optionDiv[0].code,false);
		}
		
	}
	/**
	 * 设置选项数据
	 */
	_setOptions = function(callback){
		var that = this;
		var tagName = this.$originalDom.prop("tagName");
		if(this.url != null && this.url != ""){
			$.ajax({
				async : true,
				url: this.url,
				type: 'post',//提交的方式
				dataType:'json',
				data: {},
				success: function(result) {
					var options = [];
					for(var i=0;i<result.length;i++){
						if(result[i] != null)
							options.push({code:result[i][that.valueField],text:result[i][that.textField]});
					}
					that.optionsArray = options;
					typeof callback === 'function' && callback.call(that,result);
					typeof that.onLoadSuccess === 'function' && that.onLoadSuccess.call(that,result);
				}
			});
		}else if("SELECT" == tagName){
			//设置默认值
			this.defaultValue = this.defaultValue || this.$originalDom.val();
			var optionDoms = this.$originalDom.find('option');
			var options = [];
			for(var i=0;i<optionDoms.length;i++){
				options.push({code:$(optionDoms[i]).val(),text:$(optionDoms[i]).html()});
			}
			this.optionsArray = options;
			typeof callback === 'function' && callback.call(that);
		}
	}
	
	/**
	 * 表单验证
	 * @version 1.0
	 */
	$.valid = (function(){
		/**
		 * 待验证集合
		 */
		var _CONTROLS = [];
		/**
		 * 弹窗对象
		 */
		var _ALERTIFY = null;
		/**
		 * 表单验证的规则
		 */
		var _RULES = {
			notNull : {//非空验证
				validator : function(val,param){
					return val != null && val != '';
				},
				error : function(obj,objName,param){
					_ALERTIFY.error("请输入" + objName + '!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			comNotNull : {//下拉框非空验证
				validator : function(val,param){
					return val != null && val != '' && val != '000';
				},
				error : function(obj,objName,param){
					_ALERTIFY.error('请选择' + objName + '!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			len : {//长度验证
				validator : function(val,param){
					return val.length <= param;
				},
				error : function(obj,objName,param){
					_ALERTIFY.error(objName + '最多可输入' + param + '位!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			num : {//数字验证
				validator : function(val,param){
					if('int' == param)//整数
						return val % 1 == 0
					if('float' == param)//浮点数
						return val % 1 != 0
					return !isNaN(val);//数字
				},
				error : function(obj,objName,param){
					if('int' == param)//整数
						_ALERTIFY.error(objName + '只能输入整数!');
					else if('float' == param)//浮点数
						_ALERTIFY.error(objName + '只能输入浮点数!');
					else 
						_ALERTIFY.error(objName + '只能输入数字!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			min : {//最小值验证
				validator : function(val,param){
					return val < param;
				},
				error : function(obj,objName,param){
					_ALERTIFY.error(objName + '的最小值为:' + param + '!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			max : {//最大值验证
				validator : function(val,param){
					return val > param;
				},
				error : function(obj,objName,param){
					_ALERTIFY.error(objName + '的最大值为:' + param + '!');
					obj.closest('.form-group').addClass('has-error');
				}
			},
			define : {
				validator : function(val,param,obj){
					return param(val,function(errorInfo){
						_ALERTIFY.error(errorInfo);
						obj.closest('.form-group').addClass('has-error');
					});
				},
				error : function(obj,objName,param){
				}
			}
		};
		
		/**
		 * 添加 要验证的控件 到 待验证集合
		 */
		$.fn.valid = function(validObj,errInfo){
			var controlObj = { 
				$domObj : $(this),
				validObj : validObj
			} 
			_CONTROLS.push(controlObj);
			return true;
		};
		
		return function(param){
			if(param == 'clear'){
				_CONTROLS = [];
				return true;
			}
			
			if(param == true){
				_ALERTIFY = alertify;	
			}else{
				_ALERTIFY = parent.alertify;
			}
			
			var controls = _CONTROLS;
			//遍历所有控件
			for(var i = 0 ; i < controls.length ; i++){
				var $obj = controls[i].$domObj;//控件
				var validObj = controls[i].validObj;//规则对象
				var objName = validObj.name || $obj.parent().siblings(".control-label").html();//获取控件对应名称
				//遍历控件所定义的所有规则
				for(var key in validObj){
					var rule = _RULES[key];//从规则对象中取得规则
					if(typeof rule === 'object' && typeof rule.validator === 'function' && rule.validator($obj.val(),validObj[key],$obj) != true ){
						rule.error($obj,objName,validObj[key]);
						_CONTROLS = [];
						return false;
					}
				}
				$obj.closest('.form-group').removeClass('has-error');
			}
			//验证过一次之后重置 控件集合
			_CONTROLS = [];
			return true;
		};
	})();
	
	
	/**
	 * 日期框
	 * @version 1.0
	 */
	$.fn.dateBox=function(paramObj,param2){
		if(!paramObj)
			paramObj={};
		if(typeof paramObj === "object"){
			var dateBoxConfig = {
				format : 'yyyy-mm-dd',//时间格式
				weekStart : 0,//一周从哪一天开始。0（星期日）到6（星期六）
				startDate : '',//控件开始时间
				endDate : '',//控件结束时间
				autoclose : true,//当选择一个日期之后是否立即关闭此日期时间选择器。
				startView : 2,//日期时间选择器打开之后首先显示的视图。 可接受的值：0时/1天/2月/3年/4十年
				minView : 2,//日期时间选择器所能够提供的最精确的时间选择视图
				maxView : undefined,//日期时间选择器所能够提供的最精确的时间选择视图
				todayBtn : true,//今天按钮。可接受的值：false不显示/true显示/linked显示并选中
				todayHighlight : false,//是否高亮当前日期
				keyboardNavigation : true,//是否支持键盘改变日期
				language : 'zh-CN',//语言
				forceParse : true,//关闭时 是否强制解析输入框的值
				minuteStep : 10,//分钟预设数量
				showMeridian : true,
				initialDate : new Date() //初始化时的时间
			};
			$.extend(dateBoxConfig,paramObj);
//			$(this).attr('readonly',true);
			return $(this).datetimepicker(dateBoxConfig);
		}else{
			$(this).datetimepicker(paramObj,param2);
		}
	}
	
	
	/**
	 * 列表筛选
	 */
	$.widget = (function(){
		
		var _loadWidgetInfo = function($widgetForm){
			var $formControls = $widgetForm.find('.form-control:not([combobox-flag=show])');//所有控件
			var $widgetScreenInfo = $widgetForm.children('.widget-screen-info');
			$widgetScreenInfo.html('已选择：');
			for(var i=0;i<$formControls.length;i++){
				var val = $($formControls[i]).val();
				var text = $($formControls[i]).val();
				if($($formControls[i]).attr('combobox-name'))
					text = $($formControls[i]).comboBox('getText');
				if(val != null && val != '' && val != '000'){
					$widgetScreenInfo.append('【'+text+'】');
				}
			}
		}
		
		var _init = function(){
			var $widgetForms = $('.widget-form');
			for(var i=0;i<$widgetForms.length;i++){
				var $widgetForm = $($widgetForms[i]);
				var $widgetScreenInfo = $('<div class="widget-screen-info"></div>');
				$widgetForm.append($widgetScreenInfo);
				
				var $widgetScreenMore = $widgetForm.find('.widget-screen-more');//更多按钮
				var $widgetScreenHide = $widgetForm.find('.widget-screen-hide');//隐藏的筛选控件
				if($widgetScreenHide.length == 1 && $widgetScreenMore.length > 0){
					var $modalDom = $('<div class="modal" role="dialog" aria-hidden="true"></div>');
					var $modalDialog = $('<div class="modal-dialog"></div>');
					$modalDom.append($modalDialog);
					var $modalContent = $('<div class="modal-content" style="border-radius:0px"></div>');
					$modalDialog.append($modalContent);
					var $modalHeader = $('<div class="modal-header">更多筛选条件<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button></div>');
					var $modalBody = $('<div class="modal-body"></div>');	
					$modalContent.append($modalHeader);
					$modalContent.append($modalBody);
					$modalBody.append($widgetScreenHide);
					$modalBody.append('<div class="clear"></div>');
					$widgetForm.append($modalDom);
					(function($modalDom,$widgetScreenHide){
						$widgetScreenMore.on('click',function(){
							$modalDom.modal({backdrop: true,keyboard: true});
							$widgetScreenHide.show();
						})
					})($modalDom,$widgetScreenHide);
				}
				
				
				(function($widgetForm){
					var _loadWidgetInfoFn = function(){
						_loadWidgetInfo($widgetForm);
					}
					//为所有文本框和日期框添加事件
					$widgetForm.find('.form-control').on('change',_loadWidgetInfoFn)
					$widgetForm.find('.form-control').data('onWidget',_loadWidgetInfoFn);
					//为所有下拉框添加事件
					var $comboboxDivs = $widgetForm.find('.combobox-div');
					for(var j=0;j<$comboboxDivs.length;j++){
						$($comboboxDivs[j]).comboBox({onWidget : _loadWidgetInfoFn});
					}
				})($widgetForm);
				
				_loadWidgetInfo($widgetForm);
			}
		}
		return {
			init : _init
		}
	})();
	
	/**
	 * jQuery 重写 ajax
	 */
    $.ajax= (function(){
    	var _ajax=$.ajax;
    	
    	var loadingDoms = [];
    	
    	var showLoading = function(){
    		var _$loadingDom = $('<div></div>');
    		loadingDoms.push(_$loadingDom);
        	var _$loadingBG = $('<div style="background-color:#999;position: fixed;top: 0;left: 0;width: 100%;height: 100%;opacity: 0.2;"></div>');
        	var _$loadingIMG = $('<img style="position: fixed;z-index: 12;left: 50%;top: 40%;margin-left: -29px;" src="'+thisPagePath+'../image/loading.gif">');
        	_$loadingDom.append(_$loadingBG);
        	_$loadingDom.append(_$loadingIMG);
        	$(document.body).append(_$loadingDom);
    	}
    	var hideLoading = function(){
    		for(var i=0;i<loadingDoms.length;i++){
    			loadingDoms[i].remove();
    		}
    		loadingDoms = [];
    	}
    	
    	return function(opt){
            //备份opt中error和success方法
            var fn = {
                error:function(XMLHttpRequest, textStatus, errorThrown){},
                success:function(data, textStatus){},
                beforeSend:function(){},
                complete:function(){}
            }
            if(opt.error){
                fn.error=opt.error;
            }  
            if(opt.success){
                fn.success=opt.success;
            }
            if(opt.beforeSend){
            	fn.beforeSend = opt.beforeSend;
            }
            if(opt.complete){
            	fn.complete = opt.complete;
            }
            //扩展增强处理
            var _opt = $.extend(opt,{
                error:function(XMLHttpRequest, textStatus, errorThrown){
            	    var date = new Date();
            	    var seperator1 = "-";
            	    var seperator2 = ":";
            	    var month = date.getMonth() + 1;
            	    var strDate = date.getDate();
            	    if (month >= 1 && month <= 9) {
            	        month = "0" + month;
            	    }
            	    if (strDate >= 0 && strDate <= 9) {
            	        strDate = "0" + strDate;
            	    }
            	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            	            + " " + date.getHours() + seperator2 + date.getMinutes()
            	            + seperator2 + date.getSeconds();
            	    alertify.set({
            			labels : {
            				ok   : "确认",
            				cancel : "取消"
            			},
            			delay : 5000,
            			buttonReverse : false,
            	    	buttonFocus  : "ok"
            		});
            	    if(XMLHttpRequest.getResponseHeader('sessionstatus')=="timeout"){
            	    	alertify.alert("会话超时，请重新登录");
            	    }else{
            	    	alertify.alert("程序异常,错误码:"+XMLHttpRequest.readyState+",错误信息:"+textStatus+",出错时间:"+currentdate+",请联系管理员!");	
            	    }
            	    
                	//错误方法增强处理
//                    fn.error.apply(this,arguments);
                   
                },
                success:function(data, textStatus){
            	    //成功回调方法增强处理
//                	if(data=='sessionTimeout'){
//                		alertify.confirm("登录信息超时,请从新登录,是否现在前往登录页?", function(e){if(e) {
//                			location.reload();
//    					}});
//                	}else{
                		  fn.success.apply(this,arguments);
//                	}
                },
                beforeSend:function(XHR){
                    //提交前回调方法
                	showLoading();
                	fn.beforeSend.apply(this,arguments);
                },
                complete:function(XHR, TS){
                    //请求完成后回调函数 (请求成功或失败之后均调用)。
                	hideLoading();
                	fn.complete.apply(this,arguments);
                }
            });
            return _ajax(_opt);
        };
    })();
   
    

	function Datagrid(paramObj){
		
		var $originalDom = paramObj.$originalDom || null;//JQ Dom对象
		var _singleSelect = paramObj.singleSelect || false;//是否单行选中
//		var _showRowCheck = paramObj.showRowCheck != false ? true : false;//是否显示每行的复选框
		
		//数据部分
		var _url = paramObj.url || null;//请求地址
		var _formId = paramObj.form || null;//请求formId
		var _rowsField = paramObj.rowsField || 'rows';//列表数据 key
		var _totalField = paramObj.totalField || 'total';//列表总数 key
		var _rows = [];//表格所有行数据集合
		var _noDataPrompt = paramObj.noDataPrompt || '没有相关数据!';//没有数据时的提示
		
		//工具栏部分
		var _toolbar = paramObj.toolbar || [];//工具栏{text : '添加',validCount : '0+',callback : function(selectedRows){alert(123);}}
		var _toolbtnDoms = [];//工具栏Dom元素集合
		
		//表格部分
		var _columns = paramObj.columns || [];//字段集合{field : 'id',title : '名称',width : 80,formatter:function(v,r){},hidden:true},
		var _columnslWidth = 0;//字段总宽度
		var _tableHeight = paramObj.tableHeight;//表格高度
		var _$widgetTable;//表格DIV
		var _$widgetTableHead;//内容表格DIV
		var _$widgetTableBody;//内容表格DIV
		var _$widgetHeadTableDom;//头表格的table
		var _$widgetTableHeadDom;//表格head部分
		var _$scrollTh=$('<th class="scroll-th"></th>');//标题头部滚动条占位TH
		var _$widgetTableBodyDom;//表格body部分
		var _$noDataPromptDiv;//无数据 提示Div
		var _multiLineShow = paramObj.multiLineShow || false;
//		var _options = paramObj.options || [];//功能栏
		
		// 事件
//		var _rowSelect = paramObj.rowSelect || null;//行选择事件
		var _resizeBefore = paramObj.resizeBefore || null;//大小调整之前调用
		var _rowClick = paramObj.rowClick || null;//行点击事件
		var _rowDblclick = paramObj.rowDblclick || null;//行双击事件
		
		// 分页部分
		var _pagingTagShow = paramObj.pagingTagShow != false ? true : false;//是否显示分页标签
		var _pagingNumShow = paramObj.pagingNumShow != false ? true : false;//是否显示分页数量
		var _pagingSizeShow = paramObj.pagingSizeShow != false ? true : false;//是否显示分页每页数量元素
		var _pageSize = paramObj.pageSize || null;//每页显示数量
		var _pageMaxShowSize = 0;//每页可显示最大行数
		var _$pagingSizeInput;//分页数量元素
		var _$pagingNum;//分页数量元素
		var _$pagingTag;//分页标签元素
		var _curPage = paramObj.curPage || 0;//当前页
    	var _totalRow = 0;//总数量
    	var _totalPage = 0;//总页数

    	/**
    	 * 绑定行单击事件
    	 */
    	this.onRowClick = function(callback){
    		if(!!callback && typeof callback === 'function')
    			_rowClick = callback;
    	}

    	/**
    	 * 绑定行双击事件
    	 */
    	this.onRowDbclick = function(callback){
    		if(!!callback && typeof callback === 'function')
    			_rowDblclick = callback;
    	}
    	
    	/**
    	 * 绑定重置大小事件
    	 */
    	this.onResizeBefore = function(callback){
    		if(!!callback && typeof callback === 'function')
    			_resizeBefore = callback;
    	}
    	
    	/**
    	 * 深度复制 - 去除对象索引(含子对象)
    	 */
    	var _deepCopy = function(obj){
		    var newobj;
		    if(obj instanceof Array){
		    	newobj = [];
		    	for (var i=0;i<obj.length;i++) {
					newobj[i] = _deepCopy(obj[i]);
			    }
		    }else if(typeof obj === "object"){
		    	newobj = {};
				for (var attr in obj) {
					newobj[attr] = _deepCopy(obj[attr]);
			    }
		    }else{
		    	return obj;
		    }
		    return newobj;
		}
    	
    	/**
		 * 在表格末尾添加多行
		 */
		this.addRows = function(rows){
			var newRows = _deepCopy(rows);
			_setTotalRow(_totalRow+newRows.length);
			var rowsMaxIndex = 0;
			if(_rows.length > 0)
				rowsMaxIndex = parseInt(_rows[_rows.length-1].row_index) + 1;
			for(var i=0;i<newRows.length;i++){
				var row = newRows[i];
				row.row_index = rowsMaxIndex + i;
				_addColumn(row);
				_rows.push(row);
			}
		}
    	
    	/**
		 * 在表格末尾添加单行
		 */
		this.addRow = function(row){
			var newRows = [];
			newRows.push(row);
			this.addRows(newRows);
		}
		
		/**
		 * 删除多行
		 */
		this.delRows = function(rows){
			for(var i=0;i<rows.length;i++){
				this.delRow(rows[i]);
			}
		}
		
		/**
		 * 删除行
		 */
		this.delRow = function(row){
			for(var i=0;i<_rows.length;i++){
				if(_rows[i].row_index == row.row_index){
					_$widgetTableBodyDom.find('tr[row-index='+row.row_index+']').remove();
					_rows.splice(i,1);
					break;
				}
			}
		}
    	
		/**
		 * 修改某一行的某一列
		 */
		this.update = function(rowIndex,field,val){
			_rows[rowIndex][field] = val;
			_$widgetTableBodyDom.find('tr[row-index='+rowIndex+']>td[field='+field+']').html(val);
		}
		
    	/**
		 * 获取所有行
		 */
		this.getRows = function(){
			return _deepCopy(_rows);
		}
    	
		/**
		 * 获取选中行
		 */
		this.getSelects = function(){
			return _getSelects();
		}
		var _getSelects = function(){
			var $selectTrs = _$widgetTableBodyDom.find('.selected');
			var selectTrs = [];
			for(var i=0;i<$selectTrs.length;i++){
				var rowIndex = $($selectTrs[i]).attr('row-index');
				for(var j=0;j<_rows.length;j++){
					if(_rows[j].row_index != rowIndex)
						continue;
					selectTrs.push(_rows[j]);
					break;
				}
			}
			return selectTrs;
		}
		
    	
    	/**
    	 * 初始化
    	 */
    	this.init = function(){
    		_setToolbarDom();
    		_setTableDom();
    		_setPagingDom();
    		_resize(_tableHeight);
    		_verifyToolbar();
    		
    		
    		//控制筛选form一个input时回车提交表单问题
    		$("#"+_formId).submit(function(){return false;});
    	}
    	
    	/**
    	 * 调整表格大小
    	 */
    	this.resize = function(height){
    		_resize(height);
    	};
    	
    	/**
    	 * 从url加载数据
    	 */
    	this.load = function(isReload){
    		if(isReload != false)
    			_curPage = 0;
    		_load();
    	}
    	
    	/**
    	 * 从url加载数据
    	 */
    	var _load = function(){
    		var jsonDataObj = {};
    		if(_formId != null){
    			jsonDataObj = $('#'+_formId).serialize();//获取参数
    			if(jsonDataObj!="")
    				jsonDataObj+="&";
    			jsonDataObj+="page="+(_curPage+1);
    			jsonDataObj+="&rows="+_pageSize;
    		}
			
			$.ajax({
				url: _url,
				async : false,
				type: 'post',//提交的方式
				dataType:'json',
				data: jsonDataObj,
				success: function(result) {
					_rows = result[_rowsField];
					_formatRows();
					_setBodyColumns();//设置字段内容
					_setTotalRow(result[_totalField]);//总数量
					_verifyToolbar();//校验工具栏
				}
			});
			_$widgetTableBody&&_$widgetTableBody.scrollTop(0);
    	}
    	
    	/**
    	 * 格式化行数据 - 为每行数据添加index
    	 */
    	var _formatRows = function(){
    		for(var i=0;i<_rows.length;i++){
    			_rows[i].row_index = i;
    		}
    	}
    	
    	/**
    	 * 设置列表的 body部分
    	 */
    	var _setBodyColumns = function(){
    		_$widgetTableBodyDom.empty();
    		_$noDataPromptDiv.empty();
    		if(_rows.length > 0){
    			for(var i=0;i<_rows.length;i++){
    				_addColumn(_rows[i]);
    			}
    			if(_pageSize>_pageMaxShowSize){
    				_$scrollTh.css({'width':'17px','min-width':'17px'});
    			}
    		}else{
    			//没有数据时显示 提示
    			_$noDataPromptDiv.append(_noDataPrompt);
    		}
    	}
    	
    	/**
    	 * 添加一行
    	 */
    	var _addColumn = function(row){
    		$tbody = _$widgetTableBodyDom;
    		var $tr = $('<tr></tr>');
    		$tbody.append($tr);
			$tr.attr('row-index',row.row_index);
			(function(row){
				$tr.on('click',function(){
					//单行选中 - 清空选中状态
    				if(_singleSelect == true)
    					_$widgetTableBodyDom.find('.selected').removeClass('selected');
    				//反选
    				if($(this).hasClass('selected'))
    					$(this).removeClass('selected');
    				else
    					$(this).addClass('selected');
    				//检验功能区
    				_verifyToolbar();
    				//触发回调
    				if(typeof _rowClick === 'function')
    					_rowClick(row);
    			});
				$tr.on('dblclick',function(){
					if(typeof _rowDblclick === 'function')
						_rowDblclick(row);
				});
			})(row);
			
			for(var j=0;j<_columns.length;j++){
    			if(_columns[j].hidden == true)
    				continue;
				var column = _columns[j];
				//  console.info("row[column.field]"+row[column.field])
				var value = row[column.field]==null?'':row[column.field];
				if(typeof column.formatter === 'function')
					value = column.formatter(value,row);
				var $tempDiv = $('<div>'+value+'</div>');
				var $td = $('<td field="'+column.field+'"></td>');
				$td.append($tempDiv);
				//控制多行显示
				if(_multiLineShow == false){
					$td.css('height','31px');
					$tempDiv.attr('title',value);
				}
				if(column.width && column.width > 0){
					$td.css({'min-width':column.width,'max-width' : column.width});
				}
					
				$tr.append($td);
    		}
    	}
    	
    	
    	
		/**
		 * 设置工具栏(按钮) dom元素 
		 */
    	var _setToolbarDom = function(){
    		var $widgetBtns = $('<div class="widget-btns"></div>');
    		$originalDom.append($widgetBtns);
    		for(var i=0;i<_toolbar.length;i++){
    			var $btn = $('<button class="btn btn-default btn-sm" type="button">'+_toolbar[i].text+'</button>');
    			(function(toolbar){
    				$btn.on('click', function(){
    					toolbar.handler(_getSelects());
        			});
    			})(_toolbar[i])
    			$widgetBtns.append($btn);
    			_toolbtnDoms.push($btn);
    		}
    	}
    	
    	/**
    	 * 校验工具栏(按钮) 
    	 */
    	var _verifyToolbar = function(){
    		var selectedCount = _$widgetTableBodyDom.find('.selected').length;
    		for(var i=0;i<_toolbar.length;i++){
				var tool = _toolbar[i];
				var validCount = tool.validCount || '0+';
				var bindFlag = false;
				if(validCount.indexOf("+")>=0){
					var tempCount = parseInt(validCount.replace("+",''));
					if(validCount.indexOf("+")==0){
						bindFlag = selectedCount > tempCount;
					}else{
						bindFlag = selectedCount >= tempCount;
					}
				}else if(validCount.indexOf("-")>=0){
					var tempCount = parseInt(validCount.replace("-",''));
					if(validCount.indexOf("-")==0){
						bindFlag = selectedCount < tempCount;
					}else{
						bindFlag = selectedCount <= tempCount;
					}
				}else{
					bindFlag = selectedCount == parseInt(validCount);
				}
				if(bindFlag && typeof tool.verify === "function"){
					bindFlag = tool.verify(_getSelects()) != false ? true : false;
				}
				if(bindFlag){
					$(_toolbtnDoms[i]).prop('disabled',false);
				}else{
					$(_toolbtnDoms[i]).prop('disabled',true);
				}
			}
    		
    	}
    	
    	/**
    	 * 设置数据区(列表) dom元素
    	 */
    	var _setTableDom = function(){
    		_$widgetTable = $('<div class="widget-table"></div>');//table - div
    		$originalDom.append(_$widgetTable);
    		_$widgetTableHead = $('<div class="widget-table-head"></div>');//table - div
    		_$widgetTable.append(_$widgetTableHead);
    		_$widgetHeadTableDom = $("<table style='table-layout:fixed'></table>");
    		_$widgetTableHead.append(_$widgetHeadTableDom);
    		var $thead = $('<thead></thead>');
    		_$widgetHeadTableDom.append($thead);
    		_$widgetTableHeadDom = $thead; 
    		var $tr = $('<tr></tr>');
			$thead.append($tr);
			
			//计算总宽度
			for(var i=0;i<_columns.length;i++){
    			if(_columns[i].hidden != true){
    				_columnslWidth += parseInt(_columns[i].width);
    			}
    		}
			for(var i=0;i<_columns.length;i++){
    			if(_columns[i].hidden != true){
    				var $tempThDiv = $('<div>'+_columns[i].title+'</div>');
    				var $th = $('<th title="'+_columns[i].title+'"></th>');
    				$th.append($tempThDiv);
    				if(_columns[i].width && _columns[i].width > 0){
    					$th.css({'min-width':_columns[i].width,'max-width' : _columns[i].width});
    				}
    				$tr.append($th);
    			}
    		}
			$tr.append(_$scrollTh);
    		
			_$widgetTableBody = $('<div class="widget-table-body"></div>');//table - div
			_$widgetTableBody.scroll(function(e){
				_$widgetTableHead.scrollLeft($(this).scrollLeft());
			})
    		_$widgetTable.append(_$widgetTableBody);
			
			var $widgetTableBodyPlaceholder = $('<div class="widget-table-body-placeholder"></div>');//table - div
			$widgetTableBodyPlaceholder.css('min-width',_columnslWidth);
			_$widgetTableBody.append($widgetTableBodyPlaceholder);
    		var $tableBody = $("<table style='table-layout:fixed'></table>");
    		$widgetTableBodyPlaceholder.append($tableBody);
    		var $tbody = $('<tbody></tbody>');
    		$tableBody.append($tbody);
    		_$widgetTableBodyDom = $tbody;
    		
    		_$noDataPromptDiv = $('<div class="widget-table-nodata"></div>');//no data div
    		_$widgetTableBody.append(_$noDataPromptDiv);
    	}
    	
    	/**
    	 * 表格 自适应
    	 */
    	var _resize = function(height){
    		if(typeof _resizeBefore === 'function'){
    			_resizeBefore();
    		}
    		var surplusHeight = height || window.TABLE_HEIGHT || $(window).height() - $(document.body).height() + _$widgetTable.height();
    		if(!window.TABLE_HEIGHT)
    			window.TABLE_HEIGHT = surplusHeight;
    		_$widgetTable.height(surplusHeight);
    		_$widgetTableBody.height(surplusHeight-_$widgetTableHeadDom.outerHeight()-2);
    		
    		//计算高度 得到页面可显示最大高度
    		var itemHeight = 31;
			var tableHeight = _$widgetTable.height();
			var tableWidth = _$widgetTable.width();
			var tableFooterHeight = 1;
			if(_columnslWidth>tableWidth){
				tableFooterHeight = 18;
			}
			_pageMaxShowSize = parseInt((tableHeight - itemHeight - tableFooterHeight) / itemHeight);
    		//自动判断每页的数量
    		if(_pageSize == null){
    			_pageSize = _pageMaxShowSize;
    			_$pagingSizeInput.val(_pageSize);
    		}
    	}
    	
    	/**
    	 * 全选
    	 */
    	var _allSelect = function(){
    		_$widgetTableBodyDom.find('tr').addClass('selected');
    		_verifyToolbar();
    	}
    	
    	/**
    	 * 反选
    	 */
    	var _reverseSelect = function(){
    		var $trs = _$widgetTableBodyDom.find('tr');
    		for(var i=0;i<$trs.length;i++){
    			var tr = $trs[i];
    			if($(tr).hasClass('selected')){
    				$(tr).removeClass('selected');
    			}else{
    				$(tr).addClass('selected');
    			}
    		}
    		_verifyToolbar();
    	}
    	
    	/**
    	 * 设置分页区dom元素
    	 */
    	var _setPagingDom = function(){
    		var $widgetPaging = $('<div class="widget-paging"></div>');
    		$originalDom.append($widgetPaging);
    		if(!_singleSelect){
    			//分页按钮
    			var $widgetPagingBtns = $('<div class="widget-paging-btns"></div>');
    			$widgetPaging.append($widgetPagingBtns);
    			var $allSelectBtn = $('<button class="btn btn-default btn-sm" type="button">全 选</button>');
    			$widgetPagingBtns.append($allSelectBtn);
    			$allSelectBtn.on('click',_allSelect);
    			var $reverseSelectBtn = $('<button class="btn btn-default btn-sm" type="button">反 选</button>');
    			$widgetPagingBtns.append($reverseSelectBtn);
    			$reverseSelectBtn.on('click',_reverseSelect);
    		}
    		//分页信息
    		var $widgetPagingInfo = $('<div class="widget-paging-info"></div>');
    		$widgetPaging.append($widgetPagingInfo);
    		var $widgetPagingSize = $('<div></div>');//分页每页数量信息
    		$widgetPagingInfo.append($widgetPagingSize);
    		var $widgetPagingNum = $('<div></div>');//分页数量信息
    		$widgetPagingInfo.append($widgetPagingNum);
    		var $widgetPagingTag = $('<ul class="pagination no-margin pagination-sm"></ul>');//分页标签
    		$widgetPagingInfo.append($widgetPagingTag);
    		
    		if(_pagingSizeShow!=false){
    			_$pagingSizeInput = $('<input type="text"/>'); 
    			_$pagingSizeInput.val(_pageSize);
    			_$pagingSizeInput.on('blur',function(){
    				if(isNaN($(this).val())||$(this).val()<=0){
    					$(this).val(_pageSize);
    					return;
    				}
    				if($(this).val()>100){
    					$(this).val(100);
    				}
    				if($(this).val()!='')
    					_pageSize = parseInt($(this).val());
    			});
    			$widgetPagingSize.append('每页显示');
    			$widgetPagingSize.append(_$pagingSizeInput);
    			$widgetPagingSize.append('行');
    		}
    		
    		_$pagingNum = $widgetPagingNum;
    		_$pagingTag = $widgetPagingTag;
    		_setTotalRow(0);
    	}

		/**
		 * 设置分页参数
		 */
		var _setTotalRow = function(totalRow){
    		_totalRow = totalRow;
    		var temp = parseInt(totalRow / _pageSize);
    		_totalPage = totalRow % _pageSize == 0 ? temp : temp + 1;
    		if(isNaN(_totalPage))
    			_totalPage = 0;
    		_setPagingNum();
			_setPagingTag();
    	}
    	
    	/**
		 * 设置分页数量部分
		 */
		var _setPagingNum = function(){
			if(_pagingNumShow == false)
				return false;
			_$pagingNum.empty();//清空分页标签内容
			var showStartRow = _curPage*_pageSize;
    		var showEndSize = showStartRow+_pageSize > _totalRow ? _totalRow : showStartRow+_pageSize;
    		_$pagingNum.html('显示'+showStartRow+'到'+showEndSize+'行，共'+_totalRow+'行');
		}
		
		/**
		 * 设置分页标签
		 */
		var _setPagingTag = function(){
			if(_pagingTagShow == false)
				return false;
			_$pagingTag.empty();//清空分页标签内容
			
    		var prev_btn = $("<li><a>«</a></li>");//上一页
    		var one_btn = $('<li><a>1</a></li>');//第一页
    		if(_curPage == 0){
    			$(prev_btn).addClass("disabled");
    			$(one_btn).addClass("active");
    		}else{
    			$(prev_btn).on('click',function(){
    				_pagingGo(_curPage-1);
    			});
    			$(one_btn).on("click",function(){
    				_pagingGo(0);
    			});
    		}
    		_$pagingTag.append(prev_btn);
    		_$pagingTag.append(one_btn);
    		
    		//左面省略号
    		if(_curPage>=4){
    			_$pagingTag.append('<li class="none"><a>…</a></li>');
    		}
    		
    		//数字部分
    		for(var i=-2;i<=2;i++){
    			var num = i+_curPage+1;
    			if(num<=1)continue;
    			if(num>=_totalPage)break;
    			var num_btn = $('<li><a>'+num+'</a></li>');
    			if(i == 0){
    				$(num_btn).addClass('active');
    			}else{
    				$(num_btn).on('click',(function(){
    					var page = num-1;
    					return function(){
    						_pagingGo(page);
    					}
    				})())
    			}
    			_$pagingTag.append(num_btn);
    		}
    		
    		//右面省略号
    		if((_curPage+4)<_totalPage){
    			_$pagingTag.append('<li class="none"><a>…</a></li>');
    		}
    		//最后一页
    		if(_totalPage>1){
    			var endPage = $('<li><a>'+_totalPage+'</a></li>');
    			var nextPage = $('<li><a>»</a></li>');
    			if((_totalPage-1)==_curPage){
    				$(endPage).addClass("active");
    				$(nextPage).addClass("disabled");
    			}else{
    				$(endPage).on('click',function(){
    					_pagingGo(_totalPage-1);
    				});
    				$(nextPage).on('click',function(){
    					_pagingGo(_curPage+1);
    				});
    			}
    			_$pagingTag.append(endPage);
    			_$pagingTag.append(nextPage);
    		}else{
    			_$pagingTag.append('<li class="disabled" ><a>»</a></li>')
    		}
		}
		
		/**
		 * 分页跳转
		 */
		var _pagingGo = function(num){
			_curPage = num;
			_load();
		}
    	
	}
	
	/**
	 * table
	 * @version 1.0
	 */
	$.fn.datagrid = (function(){
		var _TABLE_OBJS = {};
		var _index = 0;
		return function(){
			var tableName = $(this).attr('table-name');
			var tableObj = null;
			if(tableName)
				tableObj = _TABLE_OBJS[tableName]
			var paramKey = arguments[0];
			if(typeof paramKey === 'object'){
				//列表初始化
				tableName = 'table'+_index;
				paramKey['$originalDom'] = $(this);
				tableObj = new Datagrid(paramKey);
				_TABLE_OBJS[tableName] = tableObj;
				$(this).attr('table-name',tableName);
				_index++;
				tableObj.init();
			}else if(typeof paramKey === 'string'){
				return tableObj[paramKey].apply(tableObj,Array.prototype.slice.apply(arguments).slice(1));
			}
			
		}
	})();
	
    
    
})(jQuery);



