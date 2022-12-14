package com.framework.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 字符串共通工具类
 * 
 * @author wangxch
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空（包括null,"","  ","null"都算为空）
	 * 
	 * @param str
	 * @return true:为空;false:不为空;
	 */
	public static boolean isNull(String str) {
		return ((str == null) || (str.trim().equals("")) || (str
				.equalsIgnoreCase("null")));
	}

	/**
	 * 判断字符串是否为空（包括null,"","  ","null"都算为空）
	 * 
	 * @param str
	 *            数组，可传多个
	 * @return true:至少有一个为空;false:全不为空;
	 */
	public static boolean isNullEx(String... str) {
		if (str == null || str.length == 0)
			return true;
		for (String ts : str) {
			if (isNull(ts))
				return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否不为空（包括null,"","  ","null"都算为空）
	 * 
	 * @param str
	 * @return true:不为空;false:为空;
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * source是否在data中
	 * 
	 * @param source
	 * @param data
	 *            可变长度参数
	 * @return true:source在data中;false:source不在data中
	 */
	public static boolean isIn(String source, String... data) {
		if (isNull(source) || data == null || data.length <= 0) {
			return false;
		}
		for (String ts : data) {
			if (ts.equals(source)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * source是否不在data中
	 * 
	 * @param source
	 * @param data
	 * @return true:source不在data中;false:source在data中
	 */
	public static boolean isNotIn(String source, String... data) {
		return !isIn(source, data);
	}

	/**
	 * 如果字符串是空，则返回0，否则返回本身
	 * 
	 * @param num
	 * @return
	 */
	public static String nullTo0(String num) {
		if (isNull(num)) {
			return "0";
		}
		return num;
	}

	/**
	 * 如果字符串是空，则返回空串
	 * 
	 * @param data
	 * @return
	 */
	public static String nullToBlank(String data) {
		if (isNull(data)) {
			return "";
		}
		return data;
	}

	/**
	 * 查询一个字符串是否包含另一个字符串
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int indexOf(String arg0, String arg1) {
		if (isNull(arg0) || isNull(arg1)) {
			return -1;
		}
		return arg0.indexOf(arg1);
	}

	/**
	 * 是否不是数字(空或不能转换为double的数据)
	 * 
	 * @param str
	 * @return true:不是数字;false:是数字;
	 */
	public static boolean isNotNumber(String str) {
		if (isNull(str))
			return true;
		try {
			Double.valueOf(str).doubleValue();
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	/**
	 * 是否不是正整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotInt(String str) {
		if (str == null || "".equals(str))
			return true;
		String eL = "^\\d*[1-9]\\d*$";// 正整数
		if (str.substring(0,1).equals("0")) {
			return true;
		}
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		return !b;
	}

	/**
	 * 检查字符串长度，不能超过max值
	 * 
	 * @param data
	 * @param max
	 * @return true:data长度超过max;false:data长度未超过max;
	 */
	public static boolean checkLength(String data, int max) {
		if (isNull(data))
			return false;
		if (data.length() > max)
			return true;
		return false;
	}

	/**
	 * 截取字符串
	 * 
	 * @param data
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String subString(String data, int beginIndex, int endIndex) {
		String rstInfo = "";
		if (isNull(data))
			return rstInfo;
		if (beginIndex >= endIndex)
			return rstInfo;
		if (data.length() <= beginIndex)
			return rstInfo;
		if (endIndex > data.length())
			endIndex = data.length();
		return data.substring(beginIndex, endIndex);
	}

	/**
	 * 截取字符串
	 * 
	 * @param data
	 * @param beginIndex
	 * @return
	 */
	public static String subString(String data, int beginIndex) {
		String rstInfo = "";
		if (isNull(data))
			return rstInfo;
		if (data.length() <= beginIndex)
			return rstInfo;
		return data.substring(beginIndex);
	}

	/**
	 * json格式字符串转Map
	 * 
	 * @param data
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String data) {
		Gson gson = new Gson();
		// 使用谷歌的gson将json转换为map类型 TypeToken<Map<String, String>>()
		// 此格式可以以自己的需求进行调整
		Map<String, Object> mapData = gson.fromJson(data,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		return mapData;
	}

	/**
	 * Map格式数据转json字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String mapToJson(Map<String, Object> data) {
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	/**
	 * json格式字符串转成对象
	 * 
	 * @param data
	 * @param type
	 * @return
	 */
	public static <T> T jsonToBean(String data, Class<T> type) {
		if (isNull(data)) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(data, type);
	}

	/**
	 * bean格式数据转json字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String beanToJson(Object data) {
		if (data == null)
			return null;
		Gson gson = new Gson();
		return gson.toJson(data);
	}

	/**
	 * 将Map转换为XML格式的字符串
	 * 
	 * @param data
	 *            Map类型数据
	 * @return XML格式的字符串
	 * @throws Exception
	 */
	public static String mapToXml(Map<String, String> data) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		org.w3c.dom.Document document = documentBuilder.newDocument();
		org.w3c.dom.Element root = document.createElement("xml");
		document.appendChild(root);
		for (String key : data.keySet()) {
			String value = data.get(key);
			if (value == null) {
				value = "";
			}
			value = value.trim();
			org.w3c.dom.Element filed = document.createElement(key);
			filed.appendChild(document.createTextNode(value));
			root.appendChild(filed);
		}
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String output = writer.getBuffer().toString(); // .replaceAll("\n|\r",
														// "");
		try {
			writer.close();
		} catch (Exception ex) {
		}
		return output;
	}

	/**
	 * 将xml格式的字符串，转换为MAP格式数据
	 * 
	 * @param strXML
	 * @return
	 */
	public static Map<String, String> xmlToMap(String strXML) {
		try {
			Map<String, String> data = Maps.newHashMap();
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			String FEATURE = null;
			FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
			documentBuilderFactory.setFeature(FEATURE, true);
			FEATURE = "http://xml.org/sax/features/external-general-entities";
			documentBuilderFactory.setFeature(FEATURE, false);
			FEATURE = "http://xml.org/sax/features/external-parameter-entities";
			documentBuilderFactory.setFeature(FEATURE, false);
			FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
			documentBuilderFactory.setFeature(FEATURE, false);
			documentBuilderFactory.setXIncludeAware(false);
			documentBuilderFactory.setExpandEntityReferences(false);
			
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(
					strXML.getBytes("UTF-8"));
			org.w3c.dom.Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					data.put(element.getNodeName(), element.getTextContent());
				}
			}
			try {
				stream.close();
			} catch (Exception ex) {
			}
			return data;
		} catch (Exception ex) {
		}
		return null;
	}

}