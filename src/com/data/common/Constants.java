package com.data.common;

/**
 * 系统不可变参数设定类
 */
public class Constants {
	
	public static final String LOGINED = "user_login_id";
	
	/************************** 状态码**************************/
	public static final String FAIL = "0"; //失败
	public static final String SUCCESS = "100"; //成功
	public static final String LOGIN_TIMEOUT = "1"; //登录Session超时
	
	
	/************************** 运行环境标识 **************************/
	public static final String ENVIRONMENT_TEST1 = "1"; // 运行环境标识（测试1）
	public static final String ENVIRONMENT_TEST2 = "2"; // 运行环境标识（测试2）
	public static final String ENVIRONMENT_FORMAL = "9"; // 运行环境标识（正式）
	
	/************************** UUID标识 **************************/
	public static final String UUID_FLAG_OTHER_ID = "99"; // UUID标识
	
	/************************** 数据备份 **************************/
	public static final String READY_SUCCESS = "1000";// 准备正常
	public static final String OPERATION_SUCCESS = "1100";// 操作成功
	public static final String OPERATION_FAILURE_STRUCTURE = "1101";// 失败-表结构不同
	public static final String OPERATION_FAILURE_EXCEPTION = "1102";// 失败-数据异常
	public static final String OPERATION_FAILURE_CREATE = "1103";// 失败-建表失败
}
