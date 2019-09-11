package com.thtf.ezone.ezmap.util;

import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

/**
 * 时间戳转换工具
 */
public class TimestampConverter extends DateConverter {

	public TimestampConverter(String format) {
		super(format);
	}

	protected Object convertToDate(Class type, Object value) {
		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}

				return df.parse((String) value);
			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Timestamp");
			}
		}

		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}

	protected Object convertToString(Class type, Object value) {
		if (value instanceof Date) {
			try {
				return df.format(value);
			} catch (Exception e) {
				throw new ConversionException("Error converting Timestamp to String");
			}
		}

		return value.toString();
	}
}