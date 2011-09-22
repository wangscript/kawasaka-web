package com.linkage.common.bean.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/** ��ȡĳ�µĵ�һ�� */
	public static String firstDayOfMonth (int month_offset) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month_offset);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(c.getTime());
	}
	
	/** ��ָ�����ڵĻ�������ĳ�µĵ�һ�� */
	public static String firstDayOfMonth(String date, int month_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.MONTH, month_offset);
		c.set(Calendar.DAY_OF_MONTH, 1);	
		return sdf.format(c.getTime());
	}
	
	/** ��ȡĳ�µ����һ�� */
	public static String lastDayOfMonth(int month_offset) throws Exception {
		String firstDay = firstDayOfMonth(month_offset + 1);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = df.parse(firstDay);
		Date preDate = new Date(d.getTime() - 1);
		return df.format(preDate);
	}
	
	/** �ڵ�ǰ�Ļ������������� */
	public static String addDays(int day_offset) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day_offset);
		return sdf.format(c.getTime());
	}
	
	/** ��ָ�����ڵĻ������������� */
	public static String addDays(String date, int day_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, day_offset);	
		return sdf.format(c.getTime());
	}
	
	/** ����ƫ�� */
	public static String addMonths(String date, int month_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.MONTH, month_offset);	
		return sdf.format(c.getTime());
	}
	
	/** ����Ȼ��ƫ�� */
	public static String addMonthsNature(String date, int month_offset) throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return firstDayOfMonth(date, month_offset);	
	}
	
	/** ����ƫ�� */
	public static String addYears(String date, int year_offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date d = sdf.parse(date);
		c.setTime(d);
		c.add(Calendar.YEAR, year_offset);	
		return sdf.format(c.getTime());
	}
	
	/** ����Ȼ��ƫ�� */
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
	
	/** �������ü��㿪ʼʱ�� */
	public static String startDate(String enable_tag, String start_absolute_date, String start_offset, String start_unit) throws Exception {
		String start_date;
		if ("0".equals(enable_tag)) { 		 // 0:������Ч
			start_date = startDateOffset(today(), start_offset, start_unit);
		} else if ("1".equals(enable_tag)) { // 1:��������Ч
			start_date = startDateOffset(firstDayOfMonth(1), start_offset, start_unit);
		} else if ("2".equals(enable_tag)) { // 2:������Ч
			start_date = startDateOffset(tomorrow(), start_offset, start_unit);
		} else if ("3".equals(enable_tag)) { // 3:��ѡ��������������Ч
			start_date = startDateOffset(firstDayOfMonth(1), start_offset, start_unit);
		} else if ("4".equals(enable_tag)) { // 4:����ʱ��
			start_date = start_absolute_date;
		} else {
			start_date = today();
		}
		return start_date;
	}
	
	/** ָ��ԤԼʱ��,�㿪ʼʱ�� */
	public static String startDateBook(String fixDate, String enable_tag, String start_absolute_date, String start_offset, String start_unit) throws Exception {
		String start_date;
		if ("0".equals(enable_tag)) { 		 // 0:������Ч
			start_date = startDateOffset(fixDate, start_offset, start_unit);
		} else if ("1".equals(enable_tag)) { // 1:��������Ч
			start_date = startDateOffset(firstDayOfMonth(fixDate, 1), start_offset, start_unit);
		} else if ("2".equals(enable_tag)) { // 2:������Ч
			start_date = startDateOffset(addDays(fixDate, 1), start_offset, start_unit);
		} else if ("3".equals(enable_tag)) { // 3:��ѡ��������������Ч
			start_date = startDateOffset(firstDayOfMonth(fixDate, 1), start_offset, start_unit);
		} else if ("4".equals(enable_tag)) { // 4:����ʱ��
			int iresult = start_absolute_date.compareTo(fixDate);//�Ƚ�2�����ڵĴ�С
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

	/** �������ü������ʱ�� */
	public static String endDate(String start_date, String end_enable_tag, String end_absolute_date, String end_offset, String end_unit) throws Exception {
		if ("0".equals(end_enable_tag)) { 			// 0:����ʱ��
			return end_absolute_date;
		} else if ("1".equals(end_enable_tag)) { 	// 1:���ʱ��
			return endDateOffset(start_date, end_offset, end_unit);
		} else if ("2".equals(end_enable_tag)) { 	// 2:�ֹ�¼��
			return "2050-12-31";
		} 
		
		return "2050-12-31";
	}
	
	/** ��ʼʱ��ƫ�� */
	public static String startDateOffset(String date, String offset, String unit) throws Exception {
		String startDate = date;
		if (offset == null || offset.equals("") || unit == null || unit.equals("")) {
			return date;
		}
		
		if ("0".equals(unit)) {			// ����ƫ��
			startDate = addDays(date, Integer.parseInt(offset));
		} else if ("1".equals(unit)) {	// ����Ȼ��ƫ�� 
			startDate = addDays(date, Integer.parseInt(offset));
		} else if ("2".equals(unit)) {  // ����ƫ�� (Ŀǰͳһ����Ȼ�´���) ֻҪ��ʼʱ����ƫ�ƶ����³�(1��)��Ч
			startDate = addMonthsNature(date, Integer.parseInt(offset));
		} else if ("3".equals(unit)) {  // ����Ȼ��ƫ�� (ֻҪ��ʼʱ����ƫ�ƶ����³�(1��)��Ч)
			startDate = addMonthsNature(date, Integer.parseInt(offset));
		} else if ("4".equals(unit)) {  // ����ƫ��
			startDate = addYears(date, Integer.parseInt(offset));
		} else if ("5".equals(unit)) {  // ����Ȼ��ƫ��(ȡ���)
			startDate = addYearsNature(date, Integer.parseInt(offset));
		}
		
		return startDate;
	}
	
	/** ����ʱ��ƫ�� */
	public static String endDateOffset(String date, String offset, String unit) throws Exception {
		String endDate = date;
		if (offset == null || offset.equals("") || unit == null || unit.equals("")) {
			return "2050-12-31";
		}
		
		if ("0".equals(unit)) {			// ����ƫ��
			endDate = addDays(date, Integer.parseInt(offset));
		} else if ("1".equals(unit)) {	// ����Ȼ��ƫ�� 
			endDate = addDays(date, Integer.parseInt(offset));
		} else if ("2".equals(unit)) {  // ����ƫ�� (Ŀǰͳһ����Ȼ�´���) ȡ�����µ�
			endDate = addMonthsNature(date, Integer.parseInt(offset));
			endDate = addDays(endDate, -1); 
		} else if ("3".equals(unit)) {  // ����Ȼ��ƫ��(ȡ�����µ�) 
			endDate = addMonthsNature(date, Integer.parseInt(offset));
			endDate = addDays(endDate, -1);
		} else if ("4".equals(unit)) {  // ����ƫ��
			endDate = addYears(date, Integer.parseInt(offset));
		} else if ("5".equals(unit)) {  // ����Ȼ��ƫ��(ȡ���)
			endDate = addYearsNature(date, Integer.parseInt(offset));
		}
		
		return endDate;
	}
	
	/**
	 * ��ָ����ʽ��ȡʱ���ʽ
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
