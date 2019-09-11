package com.thtf.ezone.ezmap.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CommonUtil {

	
	public static String getBeanValue(Object currentBean, String method) {

		Object returnValue = "";

		Class currentClass = currentBean.getClass();

		Method willCall = null;

		try {

			willCall = currentClass.getMethod(method);

			returnValue = willCall.invoke(currentBean, null);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return String.valueOf(returnValue);

	}

	public static String getUninField(Object beanObject, String methods) {

		String[] methodsName = methods.split(",");
		String value = "";

		for (int i = 0; i < methodsName.length; i++) {
			String currentValue = getBeanValue(beanObject, methodsName[i]);

			if (!"null".equals(currentValue)) {
				value += " " + currentValue;
			}
		}

		return value;

	}

}
