package com.www.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期操作
 * add by www
 */
public class DatetimeUtils {
	public static String DATE_PATTEN = "yyyy-MM-dd";
	public static String DATETIME_PATTEN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将日期转换成指定patten的样式
	 * @param date
	 * @param patten
	 * @return
	 */
	public static String dateToString(Date date, String patten){
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(date);
	}
	/**
	 * 将日期转换成yyyy-MM-dd样式
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return dateToString(date,DATE_PATTEN);
	}
	/**
	 * 将日期转换成yyyy-MM-dd HH:mm:ss样式
	 * @param date
	 * @return
	 */
	public static String datetimeToString(Date date){
		return dateToString(date,DATETIME_PATTEN);
	}
	
	/**
	 * 将字符转换成指定样式的日期形式
	 * @param sDate
	 * @param patten
	 * @return java.util.Date 如果字符串格式不对，转换不成功，则返回空
	 */
	public static Date stringToDate(String sDate, String patten){
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try {
			return sdf.parse(sDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 将字符转换成DATE_PATTEN样式的日期形式
	 * @param sDate
	 * @return java.util.Date 如果字符串格式不对，转换不成功，则返回空
	 */
	public static Date stringToDate(String sDate){
		return stringToDate(sDate,DATE_PATTEN);
	}
	
	/**
	 * 将字符转换成DATETIME_PATTEN样式的日期形式
	 * @param sDate
	 * @return java.util.Date 如果字符串格式不对，转换不成功，则返回空
	 */
	public static Date stringToDatetime(String sDate){
		return stringToDate(sDate,DATETIME_PATTEN);
	}
	
	/**
	 * 比较两个日期的大小
	 * @param date1
	 * @param date2
	 * @return 1:date1<date2; 0:date1=date2; -1:date1>date2
	 */
	public static int compareTwoDate(Date date1, Date date2){
		if(date1.before(date2)){//date1<date2
			return 1;
		}else if(date1.after(date2)){//date1>date2
			return -1;
		}else{//date1=date2
			return 0;
		}
	}
	/**
	 * 取当前日期
	 * @return
	 */
	public static Date getCurrentDate(){
		return new Date();
	}
	
	public static void main(String[] args) throws Exception{
		
		String str = "12~121~21";
		String[] arr = str.split("~");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
