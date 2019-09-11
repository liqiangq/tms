package com.thtf.ezone.ezmap.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.thtf.ezone.ezmap.Constant;

public class DateUtil {

	public static SimpleDateFormat DEFAULT_DATE_FORMATER = new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT);

	public static SimpleDateFormat DEFAULT_TIME_FORMATER = new SimpleDateFormat(Constant.DEFAULT_TIME_FORMAT);

	public static SimpleDateFormat DEFAULT_DATETIME_FORMATER = new SimpleDateFormat(Constant.DEFAULT_DATETIME_FORMAT);

	public static String NORMAL_DATETIME = Constant.DEFAULT_DATETIME_FORMAT;

	public static String timePattern = "HH:mm";

	public static final String getDate(Date date) {
		if (date != null) {
			return DEFAULT_DATE_FORMATER.format(date);
		} else {
			return "";
		}
	}

	public static final Date convertStringToDate(String dataFormat, String strDate) throws ParseException {
		if (!StringUtils.isEmpty(strDate)) {
			SimpleDateFormat format = null;
			if (StringUtils.isEmpty(dataFormat)) {
				format = DEFAULT_DATE_FORMATER;
			} else {
				format = new SimpleDateFormat(dataFormat);
			}
			try {
				return format.parse(strDate);
			} catch (ParseException e) {
				try {
					return format.parse(strDate);
				} catch (ParseException e1) {
					try {
						return format.parse(strDate);
					} catch (ParseException e2) {
						throw e2;
					}
				}
			}
		} else {
			throw new ParseException("Empty strDate!", 0);
		}
	}

	public static final String convertDateToString(Date date) {
		if (date != null) {
			return DEFAULT_DATE_FORMATER.format(date);
		} else {
			return "";
		}
	}

	public static final String convertDateToString(String dateFormat, Date date) {
		if (date != null) {
			if (StringUtils.isEmpty(dateFormat)) {
				return DEFAULT_DATE_FORMATER.format(date);
			} else {
				return new SimpleDateFormat(dateFormat).format(date);
			}
		} else {
			return "";
		}
	}

	public static Date convertStringToDate(String strDate) throws ParseException {
		if (!StringUtils.isEmpty(strDate)) {
			try {
				return DEFAULT_DATE_FORMATER.parse(strDate);
			} catch (ParseException e) {
				try {
					return DEFAULT_DATETIME_FORMATER.parse(strDate);
				} catch (ParseException e1) {
					try {
						return DEFAULT_TIME_FORMATER.parse(strDate);
					} catch (ParseException e2) {
						throw e2;
					}
				}
			}
		} else {
			throw new ParseException("Empty strDate!", 0);
		}
	}

	public static String getTimeNow(Date date) {
		if (date != null) {
			return DEFAULT_TIME_FORMATER.format(date);
		} else {
			return "";
		}
	}

	public static Calendar getToday() throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static final String getDateTime(String dateFormat, Date date) {
		if (date != null) {
			if (StringUtils.isEmpty(dateFormat)) {
				return DEFAULT_DATETIME_FORMATER.format(date);
			} else {
				return new SimpleDateFormat(dateFormat).format(date);
			}
		} else {
			return "";
		}
	}

	public static String getNowDateString() {
		return DEFAULT_DATE_FORMATER.format(new Date(System.currentTimeMillis()));
	}

	public static void main(String[] args) throws Exception {
		System.out.println("getYearFirstDayAsString=" + getYearFirstDayAsString());
		System.out.println("getMonthFirstDayAsString=" + getMonthFirstDayAsString());
		System.out.println("getNowDateAsString=" + getNowDateAsString());
		System.out.println("getNowDateTimeEndString=" + getNowDateTimeEndString());
		System.out.println("getNowDateTimeBeginAsString=" + getNowDateTimeBeginAsString());
		System.out.println("getDate7BeforeNowAsString=" + getDate7BeforeNowAsString());
		SimpleDateFormat df = new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT);
		Date beginDate = df.parse("2008-06-25");
		Date endDate = new Date(System.currentTimeMillis());
		System.out.println("getLocalTimeAsString=" + getLocalTimeAsString(beginDate, endDate));

	}

	/**
	 * 
	 * 功能说明:获取当年第一天 如2008-02-01
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-13
	 */
	public static Date getYearFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * 功能说明:获取当年第一天 如2008-02-01
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-13
	 */
	public static String getYearFirstDayAsString() {
		return DEFAULT_DATE_FORMATER.format(getYearFirstDay());
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * 功能说明: 获取当月第一天 如2008-02-01
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-12
	 */
	public static String getMonthFirstDayAsString() {
		return DEFAULT_DATE_FORMATER.format(getMonthFirstDay());
	}
	
	public static String getMonthFirstDayAsString(Date date) {
		return DEFAULT_DATE_FORMATER.format(getMonthFirstDay(date));
	}

	/**
	 * 
	 * 功能说明: 获取前一周日期
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-23
	 */
	public static Date getDate7BeforeNow() {
		Calendar calendar = Calendar.getInstance();
		Date nowDate = new Date(System.currentTimeMillis());
		calendar.setTime(nowDate);
		calendar.get(Calendar.WEEK_OF_MONTH);
		calendar.add(Calendar.DATE, -7);
		return calendar.getTime();
	}

	/**
	 * 
	 * 功能说明: 获取前一周日期字符串
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-24
	 */
	public static String getDate7BeforeNowAsString() {
		return DEFAULT_DATE_FORMATER.format(getDate7BeforeNow());
	}

	/**
	 * 
	 * 功能说明: 获取当天 如2008-06-09
	 * 
	 * @return 2008-6-13
	 */
	public static String getNowDateAsString() {
		Date nowDate = new Date(System.currentTimeMillis());
		return DEFAULT_DATE_FORMATER.format(nowDate);
	}
	
	public static String getNowDateAsString1() {
		Date nowDate = new Date(System.currentTimeMillis());
		return DEFAULT_DATETIME_FORMATER.format(nowDate);
	}

	/**
	 * 
	 * 功能说明: 获取当前时间
	 * 
	 * @return 2008-6-23
	 */
	public static String getNowDateTimeAsString() {
		Date nowDate = new Date(System.currentTimeMillis());
		return DEFAULT_DATETIME_FORMATER.format(nowDate);
	}

	/**
	 * 获取当前日期前几天
	 * 
	 * @param dayBefore
	 * @return
	 */
	public static String getBeforeDateAsString(int dayBefore) {
		return getBeforeDateAsString(null, dayBefore);
	}

	/**
	 * 获取当前日期前几天 如2008-06-09
	 * 
	 * @param dateFormat
	 * @param dayBefore
	 * @return
	 */
	public static String getBeforeDateAsString(String dateFormat, int dayBefore) {
		Calendar calendar = Calendar.getInstance();
		Date nowDate = new Date(System.currentTimeMillis());
		calendar.setTime(nowDate);
		calendar.add(Calendar.DATE, -dayBefore);
		if (StringUtils.isEmpty(dateFormat)) {
			return DEFAULT_DATE_FORMATER.format(calendar.getTime());
		} else {
			return new SimpleDateFormat(dateFormat).format(calendar.getTime());
		}
	}

	/**
	 * 获取当前日期后几天 如2008-06-09
	 * 
	 * @param dayBefore
	 * @return
	 */
	public static String getAfterDateAsString(int dayBefore) {
		return getAfterDateAsString(null, dayBefore);
	}

	/**
	 * 获取当前日期后几天
	 * 
	 * @param dateFormat
	 * @param dayBefore
	 * @return
	 */
	public static String getAfterDateAsString(String dateFormat, int dayBefore) {
		Calendar calendar = Calendar.getInstance();
		Date nowDate = new Date(System.currentTimeMillis());
		calendar.setTime(nowDate);
		calendar.add(Calendar.DATE, dayBefore);
		if (StringUtils.isEmpty(dateFormat)) {
			return DEFAULT_DATE_FORMATER.format(calendar.getTime());
		} else {
			return new SimpleDateFormat(dateFormat).format(calendar.getTime());
		}
	}

	/**
	 * 
	 * 功能说明:获取当天0点 如2008-06-01 00:00:00
	 * 
	 * @return
	 * @throws ParseException
	 *             2008-6-13
	 */
	public static String getNowDateTimeBeginAsString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return DEFAULT_DATETIME_FORMATER.format(cal.getTime());
	}

	/**
	 * 
	 * 功能说明: 获取当天24点 如2008-06-01 23:59:59
	 * 
	 * @return 2008-6-13
	 */
	public static String getNowDateTimeEndString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return DEFAULT_DATETIME_FORMATER.format(cal.getTime());
	}

	public static String getLocalTimeAsString(Date beginDate, Date endDate) {
		return getLocalTimeAsString(endDate.getTime() - beginDate.getTime());
	}

	public static String getLocalTimeAsString(long beginDate, long endDate) {
		return getLocalTimeAsString(endDate - beginDate);
	}

	public static String getLocalTimeAsString(long time) {
		StringBuffer sb = new StringBuffer("");
		if (time > (1000 * 60 * 60 * 24)) {
			long days = time / (1000 * 60 * 60 * 24);
			sb.append(days).append("天");
			time = time - days * 1000 * 60 * 60 * 24;
		}
		if (time > (1000 * 60 * 60)) {
			long hours = time / (1000 * 60 * 60);
			sb.append(hours).append("小时");
			time = time - hours * 1000 * 60 * 60;
		}
		if (time > (1000 * 60)) {
			long mins = time / (1000 * 60);
			sb.append(mins).append("分钟");
			time = time - mins * 1000 * 60;
		}
		if (time > 1000) {
			long second = time / 1000;
			sb.append(second).append("秒");
		}
		return sb.toString();
	}
}
