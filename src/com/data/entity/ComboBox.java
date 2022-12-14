package com.data.entity;

/**
 * 下拉列表填充
 */
public class ComboBox {

	/**
	 * 下拉列表的值
	 */
	private String CODE;

	/**
	 * 下拉列表显示内容
	 */
	private String NAME;

	public String getCODE() {
		return this.CODE;
	}

	public String getNAME() {
		return this.NAME;
	}

	public void setCODE(String CODE) {
		this.CODE = CODE == null ? null : CODE.trim();
	}

	public void setNAME(String NAME) {
		this.NAME = NAME == null ? null : NAME.trim();
	}
}
