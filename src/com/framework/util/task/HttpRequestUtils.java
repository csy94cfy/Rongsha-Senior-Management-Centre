package com.framework.util.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestUtils {

	public static final String SYSTEM_TYPE_MANAGER = "2607";
	public static final Integer LOGIN_TIMEOUT = 1;

	public static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

	public static Map<String, String> HTTP_SESSION = Maps.newHashMap();

	static {
		try {

			//获取配置文件的其他服务器访问路径地址
			//
			//			HTTPHOST_OrderAppServer = PropertiesUtils.getValue("RemoteIface_OrderAppServer");
			//
			//			HTTPHOST_PartTimeAppServer = PropertiesUtils.getValue("RemoteIface_ParttimeHttp");
			//
			//			HTTPHOST_QueryOrderServer = PropertiesUtils.getValue("RemoteIface_QueryOrderServer");
			//
			//			HTTPHOST_PushMsgServer = PropertiesUtils.getValue("RemoteIface_PushMsgServer");
			//
			//			HTTPHOST_LbsServer = PropertiesUtils.getValue("RemoteIface_LbsServer");
			//
			//			HTTPHOST_Pt365Manage = PropertiesUtils.getValue("RemoteIface_Pt365Manage");

		} catch (Exception e) {
			logger.error("properties file read error;");
		}
	}

	/**
	 * 向指定URL发送post方法的请求(无需登录验证)
	 *
	 * @param host-项目主机
	 * @param url-发送请求的URL
	 * @param param-请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPostRequestNoLogin(String host, String url, String param) {
		String serverResult = "";

		try {
			if (StringUtils.isNotEmpty(param)) {
				param += "&sType=" + SYSTEM_TYPE_MANAGER + "&time=" + DateUtil.getSystemTime(DateUtil.DF_YMD_HMS);
			} else {
				param = "sType=" + SYSTEM_TYPE_MANAGER + "&time=" + DateUtil.getSystemTime(DateUtil.DF_YMD_HMS);
			}
			String sign = Md5Utils.signOfMD5(param);
			param += "&sign=" + sign;
			serverResult = sendPost(host + url, param, false, null);

			//retVal = retVal.replace("/n", "");
		} catch (Exception e) {
			serverResult = e.getMessage();
		}

		return serverResult;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url-发送请求的URL
	 * @param param-请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPostRequestByLogin(String host, String url, String param, String userName, String userPwd) {

		String serverResult = "";

		try {
			if (StringUtils.isNotEmpty(param)) {
				param += "&sType=" + SYSTEM_TYPE_MANAGER + "&time=" + DateUtil.getSystemTime(DateUtil.DF_YMD_HMS);
			} else {
				param = "sType=" + SYSTEM_TYPE_MANAGER + "&time=" + DateUtil.getSystemTime(DateUtil.DF_YMD_HMS);
			}
			String sign = Md5Utils.signOfMD5(param);
			param += "&sign=" + sign;

			serverResult = sendPost(host + url, param, true, userName);

			@SuppressWarnings("rawtypes")
			Map map = null;

			try {
				map = new ObjectMapper().readValue(serverResult, Map.class);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (map == null) {
				return "";
			}
			String errorcode = (String) map.get("errorcode");

			if (LOGIN_TIMEOUT.toString().equals(errorcode)) {

				getSessionByLogin(host, userName, userPwd);

				serverResult = sendPost(host + url, param, true, userName);
				//retVal = retVal.replace("/n", "");
			}
		} catch (Exception e) {
			serverResult = e.getMessage();
		}

		return serverResult;
	}

	/**
	 * 向验证URL发送POST方法的请求,取回Session
	 *
	 * @param username-用户名
	 * @param password-密码
	 */
	@SuppressWarnings("all")
	//TODO 未实现
	private static void getSessionByLogin(String host, String username, String password) {

		String param = "";
		String serverResult = "";
		String sessionCode = "";

		if (!"".equals(sessionCode)) {
			HTTP_SESSION.put(username, sessionCode);
		}

	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {

			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				logger.debug("{}:{}", key, map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求 发生异常", e);
			e.printStackTrace();
		}
		// 关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param) {
		return sendPost(url, param, false, null);
	}

	/**
	 * 向指定URL发送POST方法的请求
	 *
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param, boolean useSessionId, String userId) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			if (useSessionId && HTTP_SESSION.containsKey(userId)) {
				//第二次运行的时候，把上次读取的session的值设置上
				conn.setRequestProperty("Cookie", "JSESSIONID=" + HTTP_SESSION.get(userId));
			}
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			// 发送请求参数
			if (param != null && !param.equals("")) {
				out.print(param);
			}
			// flush输出流的缓冲
			out.flush();
			InputStream is = conn.getInputStream();

			String charset = "UTF-8";
			Pattern pattern = Pattern.compile("charset=\\S*");
			Matcher matcher = pattern.matcher(conn.getContentType());
			if (matcher.find()) {
				charset = matcher.group().replace("charset=", "");
			}

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(is, charset));

			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求 发生异常", e);
			throw new RuntimeException(e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将请求参数 按"&"和"="
	 * 分割之后存放到Map中
	 */
	public static Map<String, Object> convertParamsToMap(String params) {
		Map<String, Object> paramMap = Maps.newHashMap();

		if (StringUtils.isNotEmpty(params)) {

			if (params.startsWith("?")) {
				params = params.substring(1);
			}
			String[] arrayParam = params.split("&");
			for (String param : arrayParam) {
				if (StringUtils.isNotEmpty(param)) {
					String[] pv = param.split("=");
					paramMap.put(pv[0], pv.length > 1 ? pv[1] : "");
				}
			}
		}

		return paramMap;
	}

	public static void main(String[] args) {
		Map<String, Object> map = convertParamsToMap("className=com.shopmanager.service.ReflectService&methodName=mulitiParam&paramType=java.lang.String~com.shop.entity.MenuEntity&paramValue=helloworld~{\"menuId\":\"asdaidididid\",\"menuName\":\"ceiasdasd\",\"menuUrl\":\"123\",\"menuLevel\":\"3\",\"menuPid\":\"1\",\"menuSort\":\"aaas\",\"menuIcon\":\"1245asda\"}");
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key);
			System.out.println(map.get(key));
		}
	}
}
