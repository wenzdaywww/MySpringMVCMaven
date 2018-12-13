package com.www.app.scheduler;

import java.util.Date;

import com.www.utils.DatetimeUtils;

/**
 * 定时任务
 * @author www
 *
 */
public class DayScheduler {
	/**
	 * 定时执行的任务
	 */
	public void dayTask(){
		System.out.println("这是定时任务"+DatetimeUtils.datetimeToString(new Date()));
	}
}
