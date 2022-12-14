package com.data.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.framework.util.StringUtil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 共通工具类
 */
public class CommonUtil {
	
	//获取Uuid使用
	private static AtomicLong seq = new AtomicLong(1000);
	
	/**
	 * 获取18位UUID：yMMddHHmmssSSS + 四位递增数(原子性的)
	 * @return
	 */
	public static synchronized String getUuid(){
//		StringBuffer bf = new StringBuffer();
//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		bf.append(sdFormat.format(new Date()).substring(3));
//		long andIncrement = seq.getAndIncrement();
//		if(andIncrement >= 9999){
//			seq.set(1000);
//		}
//		return bf.append(andIncrement).toString();
		return getUuid(Constants.UUID_FLAG_OTHER_ID);
	}
	
	/**
	 * 获取UUID
	 * @param type Constants.UUID_FLAG
	 * @return
	 */
	public static synchronized String getUuid(String type) {
		// 环境类型
		StringBuffer bf = new StringBuffer();
		String environmentType = DataUtil.getSystemParameterValue(KeySysParam.ENVIRONMENT_TYPE);
		if (StringUtil.isNotIn(environmentType, Constants.ENVIRONMENT_TEST1, Constants.ENVIRONMENT_TEST2)) {
			environmentType = Constants.ENVIRONMENT_FORMAL;
		}
		if (StringUtil.isNull(type)) {
			type = Constants.UUID_FLAG_OTHER_ID;
		}
		bf.append(environmentType).append(type);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		bf.append(sdFormat.format(new Date()).substring(3));
		long andIncrement = seq.getAndIncrement();
		if(andIncrement >= 9999){
			seq.set(1000);
		}
		return bf.append(andIncrement).toString();
	}
	
	/**
	 * 
	* @Title: converterToSpell
	* @Description: 汉字转换位汉语拼音，英文字符不变
	* @param @param chines
	* @param @return
	* @return String
	* @throws
	 */
	 public static String converterToSpell(String chines){          
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
            }else{  
                pinyinName += nameChar[i];  
            }  
        }  
        return pinyinName;  
    }
	
}
