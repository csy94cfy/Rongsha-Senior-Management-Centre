package com.data.dao;

import org.apache.ibatis.annotations.Param;

import com.data.entity.UserInfoEntity;
import com.framework.core.IBaseManageDao;

public interface IggxxDao extends IBaseManageDao<UserInfoEntity>{
	
	long delete(@Param("ids") String[] ids);
}
	