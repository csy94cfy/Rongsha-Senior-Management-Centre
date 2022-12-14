function getThisPath(){
    var scriptSrc = document.getElementsByTagName('script')[document.getElementsByTagName('script').length -1].src;
    var jsName = scriptSrc.split('/')[scriptSrc.split('/').length-1];
    return scriptSrc.replace(jsName,'');
}
var thisPagePath = getThisPath();
/**
 * 模态窗口对象
 * modal.open(paramObj)函数
 * paramObj参数格式
	{
 		url 		: 页面链接,//必填
 		width 		: 窗口宽度,//可选，默认600px
 		height 		: 窗口高度,//可选，默认auto
 		top 		: 窗口距离顶部距离,//可选，默认5%
 		backdrop 	: 是否显示透明黑色背景、并在点击背景时关闭窗口,//可选，默认false
 		keyboard 	: 是否在按下"Esc"键后关闭窗口,//可选，默认true
	}
 * 依赖：bootstrap.js
 */
window.modal = (function($){
	var _modals = new Array();//窗口对象数组{index:索引,obj:dom对象}
	var _modal_loadings = new Array();//加载中窗口对象数组{index:索引,obj:dom对象}
	var _index = 0;//当前窗口数量
	//从数组中删除对象
	var _delModal = function(index){
		for(var i=0;i<_modals.length;i++){
			if(_modals[i].index == index){
				_modals.splice(i, 1);
				break;
			}
		}
		if(_modals.length==0){
			_index = 0;
		}
	}

	//打开新的模态窗口
	var _open = function(paramObj){
		//可选参数展示
		var _width 		= paramObj.width||'600px';//窗口宽度
		var _height 	= paramObj.height||'auto';//窗口高度
		var _top	 	= paramObj.top||'5%';//距离顶部距离
		var _backdrop 	= paramObj.backdrop||false;//点击背景 关闭窗口
		var _keyboard 	= paramObj.keyboard||true;//按下ESC 关闭窗口
		var _url 		= paramObj.url;//窗口URL
		var _title 		= paramObj.title;//窗口标题
		
		var $loadingDom = $('<div></div>');
		var $loadingBG = $('<div style="background-color:black;position: fixed;top: 0;left: 0;width: 100%;height: 100%;opacity: 0.2;"></div>');
		var $loadingIMG = $('<img style="position: fixed;z-index: 12;left: 50%;top: 40%;margin-left: -29px;" src="'+thisPagePath+'../image/loading.gif">');
		$loadingDom.append($loadingBG);
		$loadingDom.append($loadingIMG);
		$(document.body).append($loadingDom);
			
		var $modalDom = $('<div index="'+_index+'" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"></div>');
		$modalDom.modal({backdrop: _backdrop,keyboard: _keyboard});
		
		var modal_dialog = $('<div class="modal-dialog"></div>');
		modal_dialog.css({'width':_width ,'margin-top':_top});
		
		var modal_content = $('<div class="modal-content" style="border-radius:0px"></div>');
		var modal_header = $('<div class="modal-header">'+_title+'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>');
		modal_content.append(modal_header);
		
		var modal_iframe = $('<iframe style="width: 100%;border: 0px;"></iframe>');
		if(_height == 'auto'){
			$modalDom.css('height','0px');
			modal_iframe.on('load',function(){
				$loadingDom.remove();
				$modalDom.show();
				$modalDom.css('height','auto');
				$(modal_iframe).height($(modal_iframe).contents().find("html").outerHeight());
				$modalDom.focus();
				
			});
		}else{
			modal_iframe.css('height',_height);
			$loadingDom.remove();
			$modalDom.focus();
		}
		modal_content.append(modal_iframe);
		modal_iframe.attr('src',_url);
		
		
		modal_dialog.append(modal_content);
		$modalDom.append(modal_dialog);
		$(document.body).append($modalDom);
		
		_modals.push({obj:$modalDom,index: _index,iframe:modal_iframe});
		
		$modalDom.on('hidden.bs.modal', function(){
			_delModal($(this).attr('index'));
			$(this).remove();
			if(_modals.length>0)
				$(document.body).addClass('modal-open');
		});
		
		_index++;
		
		return $modalDom;
	}
	
	var _resize = function(){
		var index = _modals.length - 1;
		if(index >= 0){
			var modal_iframe = _modals[index].iframe;
			$(modal_iframe).height($(modal_iframe).contents().find("html").outerHeight());
		}
	} 
	
	var _close = function(param){
		var index = _modals.length - 1;
		if(index >= 0){
			var modalObj = _modals[index].obj;
			if(typeof param == "function"){
				$(modalObj).on('hidden.bs.modal', function(){
					param(this);
				});
			}
			$(modalObj).modal('hide');
		}else if(typeof param == "function"){
			param();
		}
	}
	
	var _go = function(paramObj){
		var that = this;
		this.close(function(){that.open(paramObj)});
	}
	return {
		open : _open,
		resize : _resize,
		close : _close,
		go : _go
	}
})(jQuery);
