package com.data.common;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 预加载数据类
 */
public class DataUtil {
	
	
	private static Map<String, String> D_SystemParameter = Maps.newHashMap();
	
	
	private static Map<String, String> D_WorkBook = Maps.newHashMap();
	
	
	
	public static String getSystemParameterValue(String key) {
		return D_SystemParameter.get(key);
	}
	
}