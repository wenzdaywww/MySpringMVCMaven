package com.www.utils;

/**
 * 字符串判断
 * @author www
 * 2017-1-18
 */
public class StringUtils {
	/**
	 * 计算某子字符串在父字符串出现的次数
	 * @param parentStr 父字符串
	 * @param childStr 子字符串
	 * @return 出现的次数
	 */
	public static int getStrCount(String parentStr,String childStr){
		int index=0;
		int count=0;
		int temp=0;
		while ((temp=parentStr.substring(index, parentStr.length()).indexOf(childStr))>-1) {
			index=index+temp+childStr.length();
			count++;
		}
		return count;
	}
	/**
	 * 获取某子字符串在父字符串中第count次出现的位置
	 * @param parentStr 父字符串
	 * @param childStr 子字符串
	 * @param count 第count次出现
	 * @return 某子字符串在父字符串中第count次出现的位置
	 */
	public static int getStrIndex(String parentStr,String childStr,int count){
		if (count>0&&childStr!=null&&childStr.length()>0) {
			int index=0;
			for (int i = 0; i < count; i++) {
				int temp=parentStr.substring(index, parentStr.length()).indexOf(childStr);
				if (temp==-1) {
					break;
				}else {
					index=index+temp+childStr.length();
				}
			}
			return index;
		}
		return -1;
	}
	/**
	 * 判断字符串targetStr是否等于多个字符串其中一个
	 * @param targetStr 待判断字符串
	 * @param strs 不定个数字符串
	 * @return true为str等于其中一值，false为不等于任何值
	 */
	public static boolean isInStrs(String targetStr,String... strs){
		boolean isIn=false;
		for (String s : strs) {
			if (s.equals(targetStr)) {
				isIn=true;
				break;
			}
		}
		return isIn;
	}
	/**
	 * 判断字符串str是否不等于多个字符串其中一个
	 * @param targetStr 待判断字符串
	 * @param strs 不定个数字符串
	 * @return true为str不等任何值，false为等于其中一值
	 */
	public static boolean isNotInStrs(String targetStr,String... strs){
		return !isInStrs(targetStr, strs);
	}
	/**
	 * 多个字符串添加 单引号后拼接用于SQL语句
	 * @param strs 多个字符串或字符串数组
	 * @return 适用于sql语句的字符串
	 */
	public static String strsToSqlStrs(String... strs){
		String str="";
		for (String s:strs) {
			str+="'"+s+"',";
		}
		str=str.substring(0, str.length()-1);
		return str;
	}
}
