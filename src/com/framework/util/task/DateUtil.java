package com.framework.util.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.framework.util.StringUtil;

/**
 * 日期共通工具类
 * @author wangxch
 */
public class DateUtil {
	
	/**
	 * 格式(yyyy-MM-dd HH:mm:ss)
	 */
	public static final String DF_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 格式(yyyyMMddHHmmss)
	 */
	public static final String DF_YMDHMS = "yyyyMMddHHmmss";
	
	/**
	 * 格式(yyyy-MM-dd)
	 */
	public static final String DF_Y_M_D = "yyyy-MM-dd";
	
	/**
	 * 格式(yyyy/MM/dd)
	 */
	public static final String DF_YMDLine = "yyyy/MM/dd";
	
	/**
	 * 格式(yyyyMMdd)
	 */
	public static final String DF_YMD = "yyyyMMdd";
	
	/**
	 * 格式(yyyy/MM/dd HH:mm)
	 */
	public static final String DF_YMD_HM = "yyyy/MM/dd HH:mm";
	
	/**
	 * 格式(yyyy-MM-dd HH:mm)
	 */
	public static final String DF_YMD_HMLine = "yyyy-MM-dd HH:mm";
	
	
	/**
	 * 格式(yyyy/MM/dd HH:mm:ss)
	 */
	public static final String DF_YMD_HM_S = "yyyy/MM/dd HH:mm:ss";
	
	
	/**
	 * 格式(MM.dd)
	 */
	public static final String DF_MD = "MM.dd";
	
	/**
	 * 格式(HH:mm)
	 */
	public static final String DF_HM = "HH:mm";
	
	
	/********** 获取系统日期 **********/
	
	/**
	 * 取得系统时间
	 * @param format 格式(使用"DF_YMD_HMS"等类型)
	 * @param date 指定时间，null为当前时间
	 * @return
	 */
	public static String getSystemTime(String format, Date date) {
		if (StringUtil.isNull(format)) {
			return null;
		}
		Date tempDate = date;
		if (tempDate == null)
			tempDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String systemDate = simpleDateFormat.format(tempDate);
		return systemDate;
	}
	
	/**
	 * 取得系统时间
	 * @param format 格式(使用"DF_YMD_HMS"等类型)
	 * @param time long型时间戳
	 * @return
	 */
	public static String getSystemTime(String format, String time) {
		if (StringUtil.isNull(format)) {
			return null;
		}
		Date tempDate = null;
		if (MathUtil.longValue(time) > 0L)
			tempDate = new Date(MathUtil.longValue(time));
		return getSystemTime(format, tempDate);
	}
	
	/**
	 * 取得系统时间
	 * @param format 格式(使用"DF_YMD_HMS"等类型)
	 * @return
	 */
	public static String getSystemTime(String format) {
		if (StringUtil.isNull(format)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String systemDate = simpleDateFormat.format(new Date());
		return systemDate;
	}
	
	/**
	 * 在指定时间基础上，增加一定秒数，获取新时间
	 * @param format format 格式(使用"DF_YMD_HMS"等类型)
	 * @param date 指定时间，null为当前时间
	 * @param second 在当前时间基础上，增加的秒数
	 * @return
	 */
	public static String getAddSystemTime(String format, Date date, long second) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		if (date == null) {
			date = new Date();
		}
		long nowDate = date.getTime();
		nowDate = second * 1000 + nowDate;
		String systemDate = simpleDateFormat.format(new Date(nowDate));
		return systemDate;
	}
	
//	/**
//	 * 获取日期（例：20180620）
//	 */
//	public static String getYyyyMMdd() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//		String systemDate = simpleDateFormat.format(new Date());
//		return systemDate;
//	}
//	
//	/**
//	 * 获取日期（例：2018-06-20）
//	 */
//	public static String getYyyy_MM_dd() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String systemDate = simpleDateFormat.format(new Date());
//		return systemDate;
//	}
//	
//	/********** 获取系统日期时间 **********/
//	/**
//	 * 获取时间（例：20180620194204）
//	 */
//	public static String getYyyyMMddHHmmss() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String systemDate = simpleDateFormat.format(new Date());
//		return systemDate;
//	}
//	
//	/**
//	 * 获取时间（例：2018-06-20 19:42:04）
//	 */
//	public static String getYyyy_MM_dd_HH_mm_ss() {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String systemDate = simpleDateFormat.format(new Date());
//		return systemDate;
//	}
	
	/**
	 * 获取时间（例：20180620194204）
	 * @param second 在当前时间基础上，增加的秒数
	 * @return
	 */
	public static String getAddYyyyMMddHHmmss(long second) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		long nowDate = new Date().getTime();
		nowDate = second * 1000 + nowDate;
		String systemDate = simpleDateFormat.format(new Date(nowDate));
		return systemDate;
	}
	
	/**
	 * 获取时间（例：20180620194204）
	 * @param second 在当前时间基础上，增加的秒数
	 * @return
	 */
	public static String getAddYyyy_MM_dd_HH_mm_ss(long second) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowDate = new Date().getTime();
		nowDate = second * 1000 + nowDate;
		String systemDate = simpleDateFormat.format(new Date(nowDate));
		return systemDate;
	}
	
	/**
	 * 比较两个时间的差值
	 * @param time1 时间1，空为当前时间
	 * @param time2 时间2，空为当前时间
	 * @param format 格式(例:yyyy-MM-dd HH:mm:ss或yyyyMMddHHmmss等)
	 * @return 单位秒
	 */
	public static String getTimeDifference(String time1, String time2, String format) {
		try {
			if (StringUtil.isNull(format)) {
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date tDate1 = null;
			if (StringUtil.isNull(time1)) {
				tDate1 = new Date();
			} else {
				tDate1 = df.parse(time1);
			}
			Date tDate2 = null;
			if (StringUtil.isNull(time2)) {
				tDate2 = new Date();
			} else {
				tDate2 = df.parse(time2);
			}
			long l=tDate1.getTime()-tDate2.getTime();
			long s = l / 1000;
			return String.valueOf(s);
		} catch (ParseException e) {
			return "0";
		}
	}
	
	/**
	 * 根据当前日期往前/往后推移{day}天的日期;
	 * @param day 天数;正整数 - 向前推移{day}天的日期; 负整数 - 向后推移{day}天的日期
	 * 例：当天日期为：2018-08-03 -> getFormatDate(0)
	 * 	     明天日期为：2018-08-04 -> getFormatDate(1)
	 *	     昨天日期为：2018-08-02 -> getFormatDate(-1)
	 * @return 返回日期格式：yyyy-MM-dd
	 */
	public static String getFormatDate(int day) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(new Date());
		date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
		return dft.format(date.getTime());
	}
	
}