package com.framework.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.framework.util.StringUtil;
import com.google.common.collect.Maps;

public class BaseManageController {
	public static Logger log = LoggerFactory.getLogger(BaseManageController.class);
	protected static final String ERROR_MSG_KEY = "errorMsg";
	
	//HTTP请求对象(用来解析请求中的参数用)
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	/**
	 * 初始化（子类接口调用执行前会先执行本方法）
	 */
	@ModelAttribute
	public void init(HttpServletRequest request,
			HttpServletResponse response)  throws IOException {
		this.request = request;
		this.response = response;
	}
	
	protected void setCommonAttribute(HttpServletRequest request) {
		// 获取页面地址
		String url = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
		if (StringUtil.isNull(url)) {
			return;
		}
		
		url = url.substring(1);
		// 打印日志
		log.info("进入subMain --> \"{}\"", url);

	}


	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String expSQL(HttpServletRequest request, Exception ex) {
		log.error(ex.toString());

		return "home/except";
	}

	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {

		request.setAttribute("ex", ex);

		log.error(getExceptionMessage(ex));

		return "home/except";
	}

	/**
	 * 从异常信息中定位错误行
	 */
	public static StringBuffer getTraceInfo(Exception e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = e.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getClassName().contains("Controller") || stacks[i].getClassName().contains("ServiceImpl")) {
				sb.append("class: ").append(stacks[i].getClassName());
				sb.append("; method: ").append(stacks[i].getMethodName());
				sb.append("; line: ").append(stacks[i].getLineNumber());
				sb.append("; Exception: ");
				break;
			}
		}
		return sb;
	}

	/**
	 * 通过异常对象取得错误信息
	 */
	public static String getExceptionMessage(Exception e) {
		String message = e.toString();
		if (message.lastIndexOf(":") != -1)
			message = message.substring(0, message.lastIndexOf(":"));
		return getTraceInfo(e).append(message).toString();
	}

	/**
	 * 返回结果信息设定
	 * @param data 结果集
	 * @param errorCode 错误码
	 * @param message 结果描述
	 */
	public Map<String,Object> setResultInfo(Object data, String errorCode, String message) {

		//接口结果设定
		Map<String, Object> result = Maps.newHashMap();
		
		if (data == null || (data instanceof String && StringUtil.isNull((String)data))) {
			//data 项目不返回
		} else {
			result.put("data", data);
		}
		
		result.put("errorcode", errorCode);
		result.put("message", message);
		
		return result;
	}
}
