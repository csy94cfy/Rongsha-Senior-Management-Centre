package com.framework.core;

import com.data.common.CommonUtil;

public class BaseManageEntity {

	private String id; // ID
	private String remark; // 备注
	private String createBy; // 创建人
	private String createDate; // 创建时间
	private String updateBy; // 修改人
	private String updateDate; // 修改时间
	private String delFlag; // 删除状态

	private int page = 1; // 当前页数
	private int rows = 20; // 查询条数
	private String sort = "";// 排序条件
	private String order = ""; // 升序或降序

	private String cityIds = ""; // 登录用户的城市权限

	private String queryCondition; // 页面提交的查询条件
	
	private int start = 0; // 开始位置

	/**
	 * 执行插入操作前，手动调用该方法
	 */
	public void beforeInsert(String id, String createBy) {
		this.id = id;
		this.createBy = createBy;
		this.updateBy = createBy;
	}

	/**
	 * 执行插入操作前，手动调用该方法
	 */
	public void beforeInsert(String createBy) {
		this.id = CommonUtil.getUuid();
		this.createBy = createBy;
		this.updateBy = createBy;
	}

	/**
	 * 执行插入操作前，手动调用该方法
	 */
	public void beforeUpdate(String id, String updateBy) {
		this.id = id;
		this.updateBy = updateBy;
	}

	public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getStart() {
		return (this.getPage() - 1) * this.getRows();
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setStart(int start) {
		this.start = start;
	}

}
