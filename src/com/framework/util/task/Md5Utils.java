package com.framework.util.task;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Md5Utils {
	/**
	 * 对参数进行字典排序之后用MD5加密
	 */
	public static String signOfMD5(String param) {
		if (StringUtils.isEmpty(param)) {
			return "";
		}

		String sign = "";
		String[] params = param.split("&");
		if (params != null && params.length > 0) {
			Map<String, String> values = new TreeMap<>(Comparator.naturalOrder());
			String[] keyValue;
			for (String p : params) {
				keyValue = p.split("=");
				if (keyValue.length > 1) {
					values.put(keyValue[0], keyValue[1]);
				}
			}

			if (values != null && values.size() > 0) {
				// TODO 获取加密规则
				//String source = SystemParameter.getValue("sys_type_key");
				String source = "sssss";
				String key = "365shop";
				String[] keys = source.split(";");
				if (keys.length >= 6) {
					key = keys[4];
				}

				StringBuffer valueSB = new StringBuffer(key);
				for (String k : values.keySet()) {
					valueSB.append(values.get(k));
				}

				sign = Signature.MD5Encode(valueSB.toString(), "UTF-8");
			}
		}

		return sign;
	}
}
