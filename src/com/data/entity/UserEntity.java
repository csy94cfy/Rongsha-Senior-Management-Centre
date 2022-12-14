package com.data.entity;

import java.io.Serializable;

import com.framework.core.BaseManageEntity;

public class UserEntity extends BaseManageEntity implements Serializable{
	private static final long serialVersionUID = -7330089771471386754L;
	/* m_user */
	private String id; //ID
	private String userId; //用户ID
	private String userName; //用户名称
	private String userPassword; //用户密码
	private String userRoleId; //角色ID
	private String userLoginDate; //登录时间
	private String remark; //备注
	private String createBy; //创建人
	private String createDate; //创建时间
	private String updateBy; //修改人  
	private String updateDate; //修改时间
	private String delFlag; //删除状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserLoginDate() {
		return userLoginDate;
	}
	public void setUserLoginDate(String userLoginDate) {
		this.userLoginDate = userLoginDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userRoleId=" + userRoleId + ", userLoginDate=" + userLoginDate + ", remark="
				+ remark + ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", delFlag=" + delFlag + "]";
	}



}