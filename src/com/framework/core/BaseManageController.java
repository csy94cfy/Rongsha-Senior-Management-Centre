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
	
	//HTTP�������(�������������еĲ�����)
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	/**
	 * ��ʼ��������ӿڵ���ִ��ǰ����ִ�б�������
	 */
	@ModelAttribute
	public void init(HttpServletRequest request,
			HttpServletResponse response)  throws IOException {
		this.request = request;
		this.response = response;
	}
	
	protected void setCommonAttribute(HttpServletRequest request) {
		// ��ȡҳ���ַ
		String url = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
		if (StringUtil.isNull(url)) {
			return;
		}
		
		url = url.substring(1);
		// ��ӡ��־
		log.info("����subMain --> \"{}\"", url);

	}


	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String expSQL(HttpServletRequest request, Exception ex) {
		log.error(ex.toString());

		return "home/except";
	}

	/** ����@ExceptionHandler�쳣���� */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {

		request.setAttribute("ex", ex);

		log.error(getExceptionMessage(ex));

		return "home/except";
	}

	/**
	 * ���쳣��Ϣ�ж�λ������
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
	 * ͨ���쳣����ȡ�ô�����Ϣ
	 */
	public static String getExceptionMessage(Exception e) {
		String message = e.toString();
		if (message.lastIndexOf(":") != -1)
			message = message.substring(0, message.lastIndexOf(":"));
		return getTraceInfo(e).append(message).toString();
	}

	/**
	 * ���ؽ����Ϣ�趨
	 * @param data �����
	 * @param errorCode ������
	 * @param message �������
	 */
	public Map<String,Object> setResultInfo(Object data, String errorCode, String message) {

		//�ӿڽ���趨
		Map<String, Object> result = Maps.newHashMap();
		
		if (data == null || (data instanceof String && StringUtil.isNull((String)data))) {
			//data ��Ŀ������
		} else {
			result.put("data", data);
		}
		
		result.put("errorcode", errorCode);
		result.put("message", message);
		
		return result;
	}
}
