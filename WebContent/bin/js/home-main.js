function getThisPath(){
    var scriptSrc = document.getElementsByTagName('script')[document.getElementsByTagName('script').length -1].src;
    var jsName = scriptSrc.split('/')[scriptSrc.split('/').length-1];
    return scriptSrc.replace(jsName,'');
}
var thisPagePath = getThisPath();
/**
 * 框架入口对象
 * @version : 1.0.1
 * @date : 2018-11-15
 */
window.homeMain = (function(){
	//重置页面元素宽高
	var _pageResize = function(){
		$('.shop-con').width($(window).width()-$('.shop-menu').width());
		$('.menu-tab-items>div').width(99999);
		$('.shop-menu').height($(window).height()-$('.shop-header').height());
		$('.shop-con').height($(window).height()-$('.shop-header').height());
		$('.menu-items').height($('.shop-menu').height()-$('.more-btn').height());
		$('.shop-iframes>div').height($(window).height()-$('.shop-header').height()-$(".menu-tab").height());
		
		_tab.checkTabWidth();
	}
	
	var _showType = (function(){
		var _menuShowType = 1;//菜单显示类型 1全部显示 2只显示图标
		//更改菜单显示类型
		var _changeShowType = function(){
			//隐藏二级菜单
			$('.menu-two').animate({height:0},100,function(){
 				$(this).hide();
 			});
			
			var width = 50;
			if(_menuShowType == 2){
				width = 180;
				$('.shop-con').width($('.shop-con').width()-130);
			}
 			$('.shop-menu').animate({width:width},50,function(){
 				_menuShowType = _menuShowType == 1 ? 2 : 1;
 				_pageResize();
 			});
 			
		}
		
		return {
			change : _changeShowType,
			showAll : function(){
				if(_menuShowType == 2)
					_changeShowType();
			},
			showIcon : function(){
				if(_menuShowType == 1)
					_changeShowType();
			}
		}
	})();
	
	//tab页
	var _tab = (function(){
		var _tabs = new Array();//tab页集合
		var _moveRange = 150;//默认移动距离 单位px
		
		//验证tab页宽度(用于判断是否超出最大长度，如果超出需要显示左滑右滑按钮)
		var _checkTabWidth = function(){
			var _width = 0;
			var isShow = 0;
			for(var i=0;i<_tabs.length;i++){
				var tab = _tabs[i].tabDom;
				_width += tab.outerWidth();
				if(_width > $('.menu-tab').width()){
					//标签超出最大限制
					isShow = 1;
					break;
				}
			}
			if(isShow == 1){
				$('.menu-btn').show();
				$('.menu-tab-items').width($(window).width()-$('.shop-menu').outerWidth()-$('.menu-btn').outerWidth()*2);
			}else{
				$('.menu-btn').hide();
				$('.menu-tab-items').width($(window).width()-$('.shop-menu').outerWidth());
				$('.menu-tab-items>div').css('left',0);
			}
			
		}
		
		//删除tab页(按url查找)
		var _delTab = function(url){
			
			for(var i=0;i<_tabs.length;i++){
				var tab = _tabs[i];
				if(url == tab.url){
					//判断当前删除tab是否为激活的tab
					if(tab.tabDom.hasClass('active')){
						if(i!=0){
							_activeTab(_tabs[i-1]);
						}else if(_tabs.length > 1){
							_activeTab(_tabs[i+1]);
						}
					}
					_tabs.splice(i,1);//从数组中删除tab对象
					tab.tabDom.remove();//从页面tabs中删除tab对象的dom元素
					tab.iframeDiv.remove();//从页面tabs中删除tab对象的dom元素
					_checkTabWidth();//删除后从新计算tab宽度
					return true;
				} 
			}
			return false;
		}
		
		//激活tab页
		var _activeTab = function(tab){
			if(!tab)return null;
			$('.menu-tab-item').removeClass('active');
			$(tab.tabDom).addClass('active');
			
			//获取tab栏相关宽度
			var prevWidth=0,nextWidth=0,tabWidth=0,isTab=0;
			for(var i=0;i<_tabs.length;i++){
				var width = _tabs[i].tabDom.outerWidth();
				if(tab.url == _tabs[i].url){
					tabWidth = width;
					isTab = 1;
					continue;
				}
				if(isTab == 0){
					prevWidth += width;
				}else if(isTab == 1){
					nextWidth += width;
				}
			}
			var itemsWidth = $('.menu-tab-items').width();
			//判断是否超过tab栏总宽度
			if((prevWidth + nextWidth + tabWidth) > itemsWidth){
				var tabLeft = parseFloat($('.menu-tab-items>div').css('left').replace('px',''));//当前left
				//判断激活tab是否被左面挡住
				if((tabLeft * -1) > prevWidth)
					$('.menu-tab-items>div').animate({left : prevWidth * -1},250,function(){});
				//判断激活tab是否被右面挡住
				if( (prevWidth+tabWidth) > (tabLeft * -1 + itemsWidth))
					$('.menu-tab-items>div').animate({left : (prevWidth + tabWidth - itemsWidth) * -1 },250,function(){});
			}
			
			//隐藏其他iframe 显示激活的iframe
			$('.shop-iframes>div').hide();
			$(tab.iframeDiv).show();
			
		}
		
		//添加tab页
		var _addTab = function(tabName,url,config){
			//判断页面是否已经添加
			for(var i=0;i<_tabs.length;i++){
				if(url == _tabs[i].url){
					_activeTab(_tabs[i]);
					return;
				} 
			}
			
			//tab页 头部分代码开始
			
			var menuTab = $('<div class="menu-tab-item">'+tabName+'</div>');
			
			if(!config || config.isCanClose != false){
				//关闭函数
				var delFunction = function(){_delTab(url)}
				//关闭按钮
				var closeBtn = $('<span>×</span>');
				closeBtn.on('click',delFunction);
				
				//头部双击关闭事件
				menuTab.on('dblclick',delFunction);
				menuTab.append(closeBtn);
			}
			
			//tab页 头部分代码结束
			
			//tab页 内容部分代码开始
			var iframeDiv = $('<div style="width: 100%;"></div>');
			iframeDiv.height($(window).height()-$('.shop-header').height()-$(".menu-tab").height());
			
			var iframeDom = $('<iframe scrolling="auto" frameborder="0" style="width: 100%;height: 100%"></iframe>');
			iframeDom.attr('src',url);
			iframeDiv.append(iframeDom);
			
			//显示遮罩层
			var loadingDom = $('<div></div>');
			var loadingBG = $('<div style="background-color:black;position: absolute;top: 0;left: 0;width: 100%;height: 100%;opacity: 0.2;z-index: 11"></div>');
			var loadingIMG = $('<img style="position: absolute;z-index: 12;left: 50%;top: 40%;margin-left: -29px;" src="'+thisPagePath+'../image/loading.gif">');
			loadingDom.append(loadingBG);
			loadingDom.append(loadingIMG);
			iframeDiv.append(loadingDom);
			
			//iframe 加载完成 隐藏遮罩层
			iframeDom.on('load',function(){loadingDom.remove()});
			
			$('.shop-iframes').append(iframeDiv);
			//tab页 内容部分代码结束
			
			var tab = {
				tabDom : menuTab,
				iframeDiv : iframeDiv,
				name : tabName,
				url : url
			}
			_tabs.push(tab);
			$('.menu-tab-items>div').append(menuTab);
			menuTab.on('click',function(){
				_activeTab(tab);
			});
			_checkTabWidth();
			menuTab.click();
			
		}
		
		//向左滑动
		var _leftMove = function(){
			var _width = 0;
			for(var i=0;i<_tabs.length;i++){
				var tab = _tabs[i].tabDom;
				_width += tab.outerWidth();
			}
			if(_width > $('.menu-tab').width()){
				//标签超出最大限制
				var nowLeft = parseFloat($('.menu-tab-items>div').css('left').replace('px',''));//当前left
				var newLeft = nowLeft + _moveRange;//获取移动后距离
				if(newLeft > 0)//最小值调整
					newLeft = 0;
				$('.menu-tab-items>div').animate({left:newLeft},250,function(){});
				
			}
		}
		//向右滑动
		var _rightMove = function(){
			var _width = 0;
			for(var i=0;i<_tabs.length;i++){
				var tab = _tabs[i].tabDom;
				_width += tab.outerWidth();
			}
			if(_width > $('.menu-tab').width()){
				//标签超出最大限制
				var nowLeft = parseFloat($('.menu-tab-items>div').css('left').replace('px',''));//当前left
				var minLeft = $('.menu-tab-items').width() - _width;//左滑动最小值
				var newLeft = nowLeft - _moveRange;//获取移动后距离
				if(newLeft < minLeft)//最小值调整
					newLeft = minLeft;
				$('.menu-tab-items>div').animate({left:newLeft},250,function(){});
				
			}
		}
		
		return {
			addTab : _addTab,
			delTab : _delTab,
			checkTabWidth : _checkTabWidth,
			leftMove : _leftMove,
			rightMove : _rightMove
		}
	})();
	
	object = [{
		menuName : '菜单名称',
		menuIcon : '菜单图标',
		mlist : [{
				menuName : '菜单名称',
				menuUrl : '菜单链接'
		}]
	}]
	
	//初始化
	var _init = function(menuData,sessionId){
		//加载菜单数据
/*		for(var i=0;i<menuData.length;i++){
			var menuItem = $('<div class="menu-item"></div>');
			var menuOne = $('<div title="'+menuData[i].menuName+'" class="menu-one"><div class="fa '+ ( menuData[i].menuIcon || 'fa-asterisk' ) +'"></div><div>'+menuData[i].menuName+'</div></div>');
			menuItem.append(menuOne);
			var menuTwo = $('<div class="menu-two"></div>');
			menuItem.append(menuTwo);
			for(var j=0;j<menuData[i].mlist.length;j++){
				var subMenu = menuData[i].mlist[j];
				var menuTwoItem = $('<div>'+subMenu.menuName+'</div>');
				menuTwo.append(menuTwoItem);
				
				//二级菜单点击事件
				(function(subMenu){
					menuTwoItem.on('click',function(){
						_tab.addTab(subMenu.menuName,subMenu.menuUrl);
					});
				})(subMenu);
			}
			$('.menu-items').append(menuItem);
		}*/

		for(var i=0;i<menuData.length;i++){
			if(menuData[i].menuLevel == "0"){
				var menuItem = $('<div class="menu-item"></div>');
				var menuOne = $('<div id="'+menuData[i].menuId+'" title="'+menuData[i].menuName+'" class="menu-one"><div class="fa '+ ( menuData[i].menuIcon || 'fa-asterisk' ) +'"></div><div>'+menuData[i].menuName+'</div></div>');
				menuItem.append(menuOne);
				var menuTwo = $('<div class="menu-two"></div>');
				menuItem.append(menuTwo);
				for(var j=0;j<menuData.length;j++){
					if(menuData[j].menuPid == menuData[i].menuId){
						var subMenu = menuData[j];
						var menuTwoItem = $('<div>'+subMenu.menuName+'</div>');
						menuTwo.append(menuTwoItem);
						//二级菜单点击事件
						(function(subMenu){
							menuTwoItem.on('click',function(){
								//alert("subMenu.menuServer:subMenu.menuUrl="+subMenu.menuServer+":"+subMenu.menuUrl);
								if(!!(subMenu.menuServer)){
									_tab.addTab(subMenu.menuName,subMenu.menuServer+subMenu.menuUrl+"?ManageSessionId="+sessionId);	
								}else{
									_tab.addTab(subMenu.menuName,subMenu.menuUrl);	
								}
							});
						})(subMenu);	
					}
				}
				$('.menu-items').append(menuItem);	
			}
			
		}
		
		//绑定隐藏菜单按钮的点击事件
		$('.more-btn').on('click',_showType.change);
		
		//绑定一级菜单点击事件
		$('.menu-one').on('click',function(){
			//隐藏打开的二级菜单
			$('.menu-two').animate({height:0},100,function(){
				$(this).hide();
			});
			
			//打开所属一级菜单的二级菜单
			var menuTwoObj = $(this).next('.menu-two');
			if(menuTwoObj.height() == 0){
				//当前菜单未打开
				_showType.showAll();
				
				menuTwoObj.stop(true);
				menuTwoObj.show();
				var menuTwoItems = menuTwoObj.children();
				var height = 0;
				if(menuTwoItems.length > 0){
					height = menuTwoItems.length * $(menuTwoItems[0]).height();
				}
				menuTwoObj.animate({height: height},100);
			}
		});
		
		//绑定左滑动事件
		$('#tab-btn-left').on('click',_tab.leftMove);
		//绑定右滑动事件
		$('#tab-btn-right').on('click',_tab.rightMove);
		
		//绑定页面大小改变事件
		$(window).on('resize',function(){
			_pageResize();
		})
	}
	
	return {
		showType : _showType,
		init : _init,
		tab : _tab,
		pageResize : _pageResize
	};
})();