package com.linkage.common.bean.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/** 获取某月的第一天 */
	public static String firstDayOfMonth (int month_offset) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month_offset);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(c.getTime());
	}
	
	/** 在指定日期的基础上算某月的第一天 */
	public static String firstDayOfMonth(String date, int month_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.MONTH, month_offset);
		c.set(Calendar.DAY_OF_MONTH, 1);	
		return sdf.format(c.getTime());
	}
	
	/** 获取某月的最后一天 */
	public static String lastDayOfMonth(int month_offset) throws Exception {
		String firstDay = firstDayOfMonth(month_offset + 1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = df.parse(firstDay);
		Date preDate = new Date(d.getTime() - 1);
		return df.format(preDate);
	}
	
	/** 在当前的基础上增减天数 */
	public static String addDays(int day_offset) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day_offset);
		return sdf.format(c.getTime());
	}
	
	/** 在指定日期的基础上增减天数 */
	public static String addDays(String date, int day_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, day_offset);	
		return sdf.format(c.getTime());
	}
	
	/** 按月偏移 */
	public static String addMonths(String date, int month_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.MONTH, month_offset);	
		return sdf.format(c.getTime());
	}
	
	/** 按自然月偏移 */
	public static String addMonthsNature(String date, int month_offset) throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return firstDayOfMonth(date, month_offset);	
	}
	
	/** 按年偏移 */
	public static String addYears(String date, int year_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.YEAR, year_offset);	
		return sdf.format(c.getTime());
	}
	
	/** 按自然年偏移 */
	public static String addYearsNature(String date, int year_offset) throws Exception {
		String d = addYears(date, year_offset - 1);
		return d.substring(0, 4) + "-12-31";
	}
	
	public static String today() {
		return addDays(0);
	}
	
	public static String yesterday() {
		return addDays(-1);
	}
	
	public static String tomorrow() {
		return addDays(1);
	}
	
	/** 根据配置计算开始时间 */
	public static String startDate(String enable_tag, String start_absolute_date, String start_offset, String start_unit) throws Exception {
		String start_date;
		if ("0".equals(enable_tag)) { 		 // 0:立即生效
			start_date = startDateOffset(today(), start_offset, start_unit);
		} else if ("1".equals(enable_tag)) { // 1:下帐期生效
			start_date = startDateOffset(firstDayOfMonth(1), start_offset, start_unit);
		} else if ("2".equals(enable_tag)) { // 2:次日生效
			start_date = startDateOffset(tomorrow(), start_offset, start_unit);
		} else if ("3".equals(enable_tag)) { // 3:可选立即或下帐期生效
			start_date = startDateOffset(firstDayOfMonth(1), start_offset, start_unit);
		} else if ("4".equals(enable_tag)) { // 4:绝对时间
			start_date = start_absolute_date;
		} else {
			start_date = today();
		}
		return start_date;
	}
	
	/** 指定预约时间,算开始时间 */
	public static String startDateBook(String fixDate, String enable_tag, String start_absolute_date, String start_offset, String start_unit) throws Exception {
		String start_date;
		if ("0".equals(enable_tag)) { 		 // 0:立即生效
			start_date = startDateOffset(fixDate, start_offset, start_unit);
		} else if ("1".equals(enable_tag)) { // 1:下帐期生效
			start_date = startDateOffset(firstDayOfMonth(fixDate, 1), start_offset, start_unit);
		} else if ("2".equals(enable_tag)) { // 2:次日生效
			start_date = startDateOffset(addDays(fixDate, 1), start_offset, start_unit);
		} else if ("3".equals(enable_tag)) { // 3:可选立即或下帐期生效
			start_date = startDateOffset(firstDayOfMonth(fixDate, 1), start_offset, start_unit);
		} else if ("4".equals(enable_tag)) { // 4:绝对时间
			int iresult = start_absolute_date.compareTo(fixDate);//比较2个日期的大小
			if(iresult >= 0) {
				start_date = start_absolute_date;
			} else{
				start_date = fixDate;
			}
		} else {
			start_date = today();
		}
		return start_date;
	}

	/** 根据配置计算结束时间 */
	public static String endDate(String start_date, String end_enable_tag, String end_absolute_date, String end_offset, String end_unit) throws Exception {
		if ("0".equals(end_enable_tag)) { 			// 0:绝对时间
			return end_absolute_date;
		} else if ("1".equals(end_enable_tag)) { 	// 1:相对时间
			return endDateOffset(start_date, end_offset, end_unit);
		} else if ("2".equals(end_enable_tag)) { 	// 2:手工录入
			return "2050-12-31";
		} 
		
		return "2050-12-31";
	}
	
	/** 开始时间偏移 */
	public static String startDateOffset(String date, String offset, String unit) throws Exception {
		String startDate = date;
		if (offset == null || offset.equals("") || unit == null || unit.equals("")) {
			return date;
		}
		
		if ("0".equals(unit)) {			// 按天偏移
			startDate = addDays(date, Integer.parseInt(offset));
		} else if ("1".equals(unit)) {	// 按自然天偏移 
			startDate = addDays(date, Integer.parseInt(offset));
		} else if ("2".equals(unit)) {  // 按月偏移 (目前统一按自然月处理) 只要开始时间有偏移都是月初(1号)生效
			startDate = addMonthsNature(date, Integer.parseInt(offset));
		} else if ("3".equals(unit)) {  // 按自然月偏移 (只要开始时间有偏移都是月初(1号)生效)
			startDate = addMonthsNature(date, Integer.parseInt(offset));
		} else if ("4".equals(unit)) {  // 按年偏移
			startDate = addYears(date, Integer.parseInt(offset));
		} else if ("5".equals(unit)) {  // 按自然年偏移(取年底)
			startDate = addYearsNature(date, Integer.parseInt(offset));
		}
		
		return startDate;
	}
	
	/** 结束时间偏移 */
	public static String endDateOffset(String date, String offset, String unit) throws Exception {
		String endDate = date;
		if (offset == null || offset.equals("") || unit == null || unit.equals("")) {
			return "2050-12-31";
		}
		
		if ("0".equals(unit)) {			// 按天偏移
			endDate = addDays(date, Integer.parseInt(offset));
		} else if ("1".equals(unit)) {	// 按自然天偏移 
			endDate = addDays(date, Integer.parseInt(offset));
		} else if ("2".equals(unit)) {  // 按月偏移 (目前统一按自然月处理) 取上月月底
			endDate = addMonthsNature(date, Integer.parseInt(offset));
			endDate = addDays(endDate, -1); 
		} else if ("3".equals(unit)) {  // 按自然月偏移(取上月月底) 
			endDate = addMonthsNature(date, Integer.parseInt(offset));
			endDate = addDays(endDate, -1);
		} else if ("4".equals(unit)) {  // 按年偏移
			endDate = addYears(date, Integer.parseInt(offset));
		} else if ("5".equals(unit)) {  // 按自然年偏移(取年底)
			endDate = addYearsNature(date, Integer.parseInt(offset));
		}
		
		return endDate;
	}
	
	/**
	 * 按指定格式获取时间格式
	 *
	 * @param date
	 * @return
	 * @throws Exception
	 * @author chenlei
	 */
	public static String getDateForYYYYMMDD(String date)throws Exception{
		if(date!=null&&date.length()>=10){
			date = date.replaceAll("-", "");
			date = date.substring(0,8);
		}
		return date;
	}
		
	public static void main(String[] args) throws Exception {
		
	}
}
