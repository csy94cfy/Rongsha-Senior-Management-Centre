package com.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 类描述：共通方法（无业务功能相关方法）
 */
public class ManageUtil extends org.apache.commons.lang3.StringUtils {

	/**
	 * 汉字转换位汉语拼音，英文字符不变（暂未用）
	 */
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/**
	 * 获取32为系统UUID（暂未用）
	 */
	public static String getSysUUid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取18位UUID：yMMddHHmmssSSS + 四位轮询的随机数
	 */
	private static AtomicLong seq = new AtomicLong(999);

	public static String getUUid() {

		StringBuffer bf = new StringBuffer();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		bf.append(sdFormat.format(new Date()).substring(3));
		if (seq.getAndIncrement() >= 10000) {
			seq.set(999);
		}
		return bf.append(seq.getAndIncrement()).toString();

	}

	/**
	 * 获取一定长度的随机字符串（暂未用）
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获取指定位数的随机整数（暂未用）
	 */
	public static String getRandomNum(int digit) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < digit; i++) {
			sb.append(r.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * ISO-8859-1转换为utf-8编码（暂未用）
	 */
	public static String iso8859ToUtf8(String strContent) throws Exception {
		return new String(strContent.getBytes("ISO-8859-1"), "utf-8");
	}

	/**
	 * 获得本机IP
	 */
	public static String getLocalIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			return "0.0.0.0";
		}
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (StringUtil.isNull(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (StringUtil.isNull(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (StringUtil.isNull(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 获得指定文件的属性值
	 */
	private static Properties prop = null;

	public static Properties ReadProperties(String filePath) throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream in = ManageUtil.class.getResourceAsStream(filePath);
			prop.load(in);
			in.close();
			return prop;
		} else {
			return prop;
		}

	}
	
	//获取日期间所有日期的日期格式
	public static List<Date> getDate (String startTime,String endTime) {
		List<Date> lDate = new ArrayList<Date>();
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = sdf.parse(startTime);
		Date dEnd = sdf.parse(endTime);
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
		// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lDate;
	}
	//获取日期间所有日期字符串格式
	public static List<String> getDateString (String startTime,String endTime) {
		List<String> lDate = new ArrayList<String>();
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dBegin = sdf.parse(startTime);
		Date dEnd = sdf.parse(endTime);
		lDate.add(sdf.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
		// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(sdf.format(calBegin.getTime()));
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lDate;
	}
}
