package com.thtf.ezone.ezmap.util;

import java.text.DecimalFormat;

import com.thtf.ezone.ezmap.Constant;

public class DoubleUtil {

	private static DecimalFormat format = new DecimalFormat(Constant.DEFAULT_DOUBLE_FORMAT);
	
	public static String formatDouble(double value){
		return format.format(value);
	}
}
