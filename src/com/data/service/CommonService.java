package com.data.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.dao.ICommonDao;
import com.data.entity.ComboBox;

@Service
public class CommonService {
	
	@Resource
	private ICommonDao commonDao;
	public List<ComboBox> getSearchRoleList() {
		return this.commonDao.getSearchRoleList();
	}
	
	public List<ComboBox> getEditRoleList() {
		return this.commonDao.getEditRoleList();
	}

}
