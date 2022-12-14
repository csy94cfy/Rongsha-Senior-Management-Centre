package com.data.entity;

import com.framework.core.BaseManageEntity;
/**
 * 工作人员信息管理
 * @author hzy
 *
 */
public class UserInfoEntity extends BaseManageEntity{
	
	private String realName;	//真实姓名
	private String userPhone;	//电话号码
	private String openid;	//工作类型
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
