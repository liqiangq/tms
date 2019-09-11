package com.thtf.ezone.ezmap.util;

import java.awt.Color;

import com.thtf.ezone.ezmap.model.Cardevice;
import com.thtf.ezone.eztms.common.TmsUtil;

public class TestMap {
	public static void main(String[] args) {
		Cardevice cardevice = new Cardevice();
		cardevice.setSerial("13811112222");
		cardevice.setCarid("皖AH1234");
		cardevice.setAlarmstates("皖AH1234");
		TmsUtil.setWebBasePath("c:/");
		String url = EzmapUtil.createImage2("108", cardevice, Color.RED);

	}
	private static void printLog(String message){
    	System.out.println(message);
    }
}
