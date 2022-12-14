package com.framework.util.task;
import java.math.BigDecimal;

import com.framework.util.StringUtil;

/**
 * 数学运算
 * @author 赵涛 2018-06-28
 */
public class MathUtil {

	/**
	 * 加法运算(保留两位小数，并省略小数点后的0)
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(StringUtil.nullTo0(v1));
		BigDecimal b2 = new BigDecimal(StringUtil.nullTo0(v2));
		return numberFormat(b1.add(b2).toString());
	}
	
	/**
	 * 加法运算(多个数字一起加)(保留两位小数，并省略小数点后的0)
	 * @param strs 可变参数
	 * @return
	 */
	public static String addEx(String... strs) {
		if (strs == null || strs.length <= 0) {
			return "0";
		}
		try {
			BigDecimal b1 = new BigDecimal(StringUtil.nullTo0(strs[0]));
			for (int i = 1; i < strs.length; i++) {
				BigDecimal b2 = new BigDecimal(StringUtil.nullTo0(strs[i]));
				b1 = b1.add(b2);
			}
			return numberFormat(b1.toString());
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 减法运算(保留两位小数，并省略小数点后的0)
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String sub(String v1, String v2) {
		try {
			BigDecimal b1 = new BigDecimal(StringUtil.nullTo0(v1));
			BigDecimal b2 = new BigDecimal(StringUtil.nullTo0(v2));
			return numberFormat(b1.subtract(b2).toString());
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	* 乘法运算(保留两位小数，并省略小数点后的0)
	* @param v1
	* @param v2
	* @return
	*/
	public static String mul(String v1, String v2) {
		try {
			BigDecimal b1 = new BigDecimal(StringUtil.nullTo0(v1));
			BigDecimal b2 = new BigDecimal(StringUtil.nullTo0(v2));
			return numberFormat(b1.multiply(b2).toString());
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	* 除法运算(保留两位小数，并省略小数点后的0)
	* @param v1
	* @param v2
	* @return
	*/
	public static String div(String v1, String v2) {
		if (doubleValue(v1) == 0 || doubleValue(v2) == 0)
			return "0";
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return numberFormat(b1.divide(b2, 2, 4).toString());
	}

	/**
	 * 百分比运算(保留两位小数，并省略小数点后的0)
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String percentage(String v1, String v2) {
		try {
			BigDecimal b1 = new BigDecimal(StringUtil.nullTo0(v1));
			BigDecimal b2 = new BigDecimal(StringUtil.nullTo0(v2));
			BigDecimal b3 = b1.multiply(b2);
			return numberFormat(b3.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP).toString());
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 转换成double型，失败返回0
	 * @param data
	 * @return
	 */
	public static double doubleValue(String data) {
		try {
			return Double.valueOf(StringUtil.nullTo0(data)).doubleValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 转换成int型，失败返回0
	 * @param data
	 * @return
	 */
	public static int intValue(String data) {
		try {
			return Double.valueOf(StringUtil.nullTo0(data)).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 转换成long型，失败返回0
	 * @param data
	 * @return
	 */
	public static long longValue(String data) {
		try {
			return Long.valueOf(StringUtil.nullTo0(data));
		} catch (Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 数字转换成int型数字
	 * @param data
	 * @return
	 */
	public static String toInt(String data) {
		return String.valueOf(intValue(data));
	}
	
	/**
	* 数字格式化，(保留两位小数，并省略小数点后的0)
	* @param 内容
	* @param 保留小数点后几位
	* @return
	*/
	public static String numberFormat(String num) {
		if (StringUtil.isNull(num)) {
			return "0";
		}
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(2 ,BigDecimal.ROUND_HALF_UP);
		String rstNum = String.valueOf(bd);
		if(rstNum.indexOf(".") > 0){  
			rstNum = rstNum.replaceAll("0+?$", "");//去掉多余的0  
			rstNum = rstNum.replaceAll("[.]$", "");//如最后一位是.则去掉  
		}
		return rstNum;
	}
	
	/**
	* 数字格式化，(保留两位小数)
	* @param 内容
	* @param 保留小数点后几位
	* @return
	*/
	public static String decimalFormat(String num) {
		if (StringUtil.isNull(num)) {
			return "0.00";
		}
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(2 ,BigDecimal.ROUND_HALF_UP);
		return String.valueOf(bd);
	}
	/**
	* 数字格式化，(保留一位小数)
	* @param 内容
	* @param 保留小数点后几位
	* @return
	*/
	public static String decimalFormatNum(String num) {
		if (StringUtil.isNull(num)) {
			return "0.0";
		}
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(1 ,BigDecimal.ROUND_HALF_UP);
		return String.valueOf(bd);
	}
	/**
	 * 负数变正数
	 * @param num
	 * @return
	 */
	public static String abs(String num){
		BigDecimal bd = new BigDecimal(StringUtil.nullTo0(num));
		bd = bd.abs();
		bd = bd.setScale(1 ,BigDecimal.ROUND_HALF_UP);
		if(bd.doubleValue() % 1.0 == 0) {
			return String.valueOf(bd.intValue());
		}
		return String.valueOf(bd);
	}
	
	/**
	 * 数字比较
	 * @param v1
	 * @param v2
	 * @return v1小于v2 负数;v1等于v2 0;v1大于v2 正数
	 */
	public static double numberCompare(String v1, String v2) {
		String rst = sub(v1, v2);
		return doubleValue(rst);
	}
	
	/**
	 * 验证价格：
	 * 1、是否是数字
	 * 2、小数位不超过2位数
	 */
	public static String checkPrice(String price) {
		// 是否是数字
		if (StringUtil.isNotNumber(price)) {
			return "请输入有效的金额";
		}
		
		int index = price.indexOf(".");
		// 有小数位并且小数位不是最后一位，验证小数位数。
		if (index != -1 && index != (price.length() - 1)) {
			for (int i = price.length() - 1; i > index; i--) {
				if (price.charAt(i) != '0' && (i - index) > 2) {
					return "金额只能输入到分";
				}
			}
		}
		
		return null;
	}
}
