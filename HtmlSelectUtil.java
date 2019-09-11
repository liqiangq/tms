package com.thtf.ezone.ezmap.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.thtf.ezone.ezmap.type.SamplingInterval;

public class HtmlSelectUtil {

	private static Map<String,String> speedMap;
	private static Map<String,String> samplingIntervalMap;
	
	public static Map<String,String> getSpeedMap(){
		if (speedMap==null){
			speedMap = new LinkedHashMap<String,String>();
			speedMap.put("10", "快速");
			speedMap.put("1000", "中速");
			speedMap.put("3000", "慢速");
		}
		return speedMap;
	}
	
	public static Map<String,String> getSamplingIntervalMap(){		
		if (samplingIntervalMap==null){
			samplingIntervalMap = new LinkedHashMap<String,String>();
			for (SamplingInterval samplingInterval: SamplingInterval.values()){
				samplingIntervalMap.put(samplingInterval.getCode(), samplingInterval.getDesc());
			}
		}
		return samplingIntervalMap;
	}
}
