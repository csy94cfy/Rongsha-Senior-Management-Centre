package com.data.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.entity.ComboBox;
import com.data.service.CommonService;
import com.framework.core.BaseManageController;


@Scope("prototype")
@Controller
@RequestMapping("/commonController")
public class CommonController extends BaseManageController{
	
	@Resource
	private CommonService commonService;
	
	/**
	 * 获取角色查询下拉列表
	 */
	@ResponseBody
	@RequestMapping("/getSearchRoleByPowerType")
	public List<ComboBox> getSearchRoleByPowerType(){
		return commonService.getSearchRoleList();

	}

	/**
	 * 获取角色编辑下拉列表
	 */
	@ResponseBody
	@RequestMapping("/getEditRoleByPowerType")
	public List<ComboBox> getEditRoleByPowerType(){
		return commonService.getEditRoleList();
	}
	
	
}
