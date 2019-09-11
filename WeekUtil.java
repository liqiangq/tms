package com.thtf.ezone.ezmap.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

public class WeekUtil {

	public static Map getWeeksMap() {
		int weeks = WeekUtil.getMaxWeekNumOfYear(2008);
		int currenweeks = WeekUtil.getWeekOfYear(new Date());
		Map<String,String> weeksMap = new TreeMap<String,String> ();
		weeksMap.put("", "--请选择--");
		for (int i = 1; i <= currenweeks; i++) {
			weeksMap.put(String.valueOf(i), getBeginWeek(i) + "--" + getEndWeek(i));
		}
		return weeksMap;
	}

	public static void getRecentWeek(int i) {
		getBeginWeek(i);
		getEndWeek(i);
	}

	public static String getBeginWeek(int i) {
		Calendar calBegind = Calendar.getInstance();
		int j = calBegind.get(Calendar.DAY_OF_WEEK) - 1;
		calBegind.add(Calendar.DATE, -i * 7 + 1 - j);
		return DateUtil.getDate(calBegind.getTime());
	}

	public static String getEndWeek(int i) {
		Calendar calEnd = Calendar.getInstance();
		int j = calEnd.get(Calendar.DAY_OF_WEEK) - 1;
		calEnd.add(Calendar.DATE, -(i - 1) * 7 - j);
		return DateUtil.getDate(calEnd.getTime());
	}

	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}
}
