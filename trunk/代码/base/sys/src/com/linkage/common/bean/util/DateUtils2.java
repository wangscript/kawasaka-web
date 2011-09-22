package com.linkage.common.bean.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils2 {
	public static Date toDate(String strDate) throws Exception {
		Calendar c = Calendar.getInstance();
		if (strDate.length() == 19) {	// YYYY-MM-DD HH:MI:SS
			int year  = Integer.parseInt(strDate.substring(0, 4));
			int month = Integer.parseInt(strDate.substring(5, 7));
			int date  = Integer.parseInt(strDate.substring(8, 10));
			int hour  = Integer.parseInt(strDate.substring(11, 13));
			int min   = Integer.parseInt(strDate.substring(14, 16));
			int sec   = Integer.parseInt(strDate.substring(17, 19));
			c.set(year, month, date, hour, min, sec);
			return c.getTime(); 
		} else if (strDate.length() == 10) { // "YYYY-MM-DD"
			int year  = Integer.parseInt(strDate.substring(0, 4));
			int month = Integer.parseInt(strDate.substring(5, 7));
			int date  = Integer.parseInt(strDate.substring(8, 10));
			c.set(year, month, date);
			return c.getTime();
		} else {
			throw new Exception("DateUtils.toDate(String strDate) error!");
		}
	}
	
	public static String toString(Date date, String format) throws Exception {
		if ("YYYY-MM-DD HH:MI:SS".equals(format)) {
			Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return f.format(date);
		} else if ("YYYY-MM-DD".equals(format)) {
			Format f = new SimpleDateFormat("yyyy-MM-dd");
			return f.format(date);
		} else if ("YYYY-MM".equals(format)) {
			Format f = new SimpleDateFormat("yyyy-MM");
			return f.format(date);
		} else if ("YYYY".equals(format)) {
			Format f = new SimpleDateFormat("yyyy-MM");
			return f.format(date);
		} else {
			throw new Exception("DateUtils.toString(date, format) error! invalid argument(format).");
		}
	}
	
	public static String firstDayOfMonth() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String firstDayOfMonth(String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.set(Calendar.DAY_OF_MONTH, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}

	public static String lastDayOfMonth() throws Exception {
		String firstDayOfNextMonth = firstDayOfMonth(addMonths(1));
		return addDays(-1, firstDayOfNextMonth);
	}
	
	public static String lastDayOfMonth(String strBaseDate) throws Exception {
		String firstDayOfNextMonth = firstDayOfMonth(addMonths(1, strBaseDate));
		return addDays(-1, firstDayOfNextMonth);
	}
	
	public static String firstDayOfYear() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String firstDayOfYear(String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String lastDayOfYear() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String lastDayOfYear(String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, 31);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");	
	}
		
	public static String today() throws Exception {
		return toString(new Date(), "YYYY-MM-DD");
	}
	
	public static String today(String format) throws Exception {
		return toString(new Date(), format);
	}
		

	public static String addDays(int dayOffset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, dayOffset);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addDays(int dayOffset, String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.add(Calendar.DAY_OF_MONTH, dayOffset);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addMonths(int monthOffset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, monthOffset);	
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addMonths(int monthOffset, String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.add(Calendar.MONTH, monthOffset);	
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addMonthsForStart(int monthOffset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, monthOffset);	
		return firstDayOfMonth(toString(c.getTime(), "YYYY-MM-DD HH:MI:SS"));
	}
	
	public static String addMonthForStart(int monthOffset, String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.add(Calendar.MONTH, monthOffset);	
		return firstDayOfMonth(toString(c.getTime(), "YYYY-MM-DD HH:MI:SS"));
	}
	
	public static String addMonthsForEnd(int monthOffset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, monthOffset);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return addDays(-1, toString(c.getTime(), "YYYY-MM-DD HH:MI:SS"));
	}
	
	public static String addMonthsForEnd(int monthOffset, String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.add(Calendar.MONTH, monthOffset);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return addDays(-1, toString(c.getTime(), "YYYY-MM-DD HH:MI:SS"));
	}
	
	public static String addYears(int yearOffset) throws Exception {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, yearOffset);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addYears(int yearOffset, String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.add(Calendar.YEAR, yearOffset);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String addYearsForStart(int yearOffset) throws Exception {
		return firstDayOfYear(addYears(yearOffset));
	}
	
	public static String addYearsForStart(int yearOffset, String strBaseDate) throws Exception {
		return firstDayOfYear(addYears(yearOffset, strBaseDate));
	}
	
	public static String addYearsForEnd(int yearOffset) throws Exception {
		return lastDayOfYear(addYears(yearOffset));
	}
	
	public static String addYearsForEnd(int yearOffset, String strBaseDate) throws Exception {
		return lastDayOfYear(addYears(yearOffset, strBaseDate));
	}
	
	public static String sunOfWeek() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static String sunOfWeek(String strBaseDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(toDate(strBaseDate));
		c.set(Calendar.DAY_OF_WEEK, 1);
		return toString(c.getTime(), "YYYY-MM-DD HH:MI:SS");
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(sunOfWeek());
		System.out.println(sunOfWeek("2009-07-01"));
		System.out.println(toDate("2009-07-01"));
	}
	
	/**
	 * 返回2个时间的月差值（不要查数据库）
	 * @param end_date
	 * @param start_date
	 * @return
	 * @throws Exception
	 * @author awx
	 * @date 2009-9-30
	 */
	public static int monthInterval(String oldDate, String currDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = df.parse(oldDate);
		Date endDate = df.parse(currDate);
	
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);	
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endDate);
		
		int month;
		month = (c2.get(Calendar.YEAR) -  c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) -  c1.get(Calendar.MONTH));
		return month;
	}
}
