package com.framework.util.task;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: 孙海波
 * Date: 2016-1-20
 */
public class Signature {
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
	"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	/**
	 * 签名算法
	 * @param o 要参与签名的数据对象
	 * @return 签名
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	public static String getSign(Object o) throws IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(o) != null && f.get(o) != "") {
				list.add(f.getName() + "=" + f.get(o) + "&");
			}
		}
		int size = list.size();
		String [] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
//		result += "key=" + SystemParameter.getValue("wx_key");
		//        result = MD5.MD5Encode(result).toUpperCase();
		result = MD5Encode(result, "UTF-8").toUpperCase();
		return result;
	}

	private static String byteArrayToHexString(byte b[]) {
	StringBuffer resultSb = new StringBuffer();
	for (int i = 0; i < b.length; i++)
		resultSb.append(byteToHexString(b[i]));

	return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}
	
	public static void main(String[] args) {
		String arg0 = "pt365123";
		String arg1 = "";
		String md5 = MD5Encode(arg0, arg1);
		System.out.println(md5);
	}
}
