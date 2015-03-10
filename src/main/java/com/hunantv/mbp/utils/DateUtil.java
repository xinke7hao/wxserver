/************************************************************************************
 * @File name   :      DateUtils.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月24日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年11月24日 上午9:35:31
 ************************************************************************************/
package com.hunantv.mbp.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期辅助类
 * 优先使用common-lang3中的DateUtils
 * @author XuYanbo
 *
 */
public class DateUtil {

	public static final String DATE_COMMON_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_COMMON_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Date转换成字符串
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 日期转换成字符串
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(LocalDate date, String format){
		return date.format(DateTimeFormatter.ofPattern(format));
	}
	
	/**
	 * 日期时间转换成字符串
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param date
	 * @param format
	 * @return
	 */
	public static String datetimeToStr(LocalDateTime date, String format){
		return date.format(DateTimeFormatter.ofPattern(format));
	}
	
	/**
	 * 字符串转换成日期
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param datestr
	 * @return
	 */
	public static LocalDate strToDate(String datestr, String format){
		return LocalDate.parse(datestr, DateTimeFormatter.ofPattern(format));
	}
	
	/**
	 * 日期所在月第一天
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @return
	 */
	public static LocalDate firstDayOfMonth(LocalDate date){
		return date.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 日期所在月第N天
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @param n
	 * @return
	 */
	public static LocalDate dayOfMonth(LocalDate date, int n){
		return date.withDayOfMonth(n);
	}
	
	/**
	 * 获取指定年第N天
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @param n
	 * @return
	 */
	public static LocalDate dayOfYear(int year, int n){
		return LocalDate.ofYearDay(year, n);
	}
	
	/**
	 * 日期所在月最后一天
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @return
	 */
	public static LocalDate lastDayOfMonth(LocalDate date){
		return date.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取当前日期后N天
	 * @Author：XuYanbo
	 * @Date：2015年2月9日
	 * @param date
	 * @param n
	 * @return
	 */
	public static LocalDate addDay(LocalDate date, int n){
		return date.plusDays(n);
	}

	public static void main(String[] args) {
		
	}
}
