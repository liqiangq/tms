package com.thtf.ezone.ezmap.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.thtf.ezone.ezframework.adapter.struts.ActionContext;
import com.thtf.ezone.ezmap.Constant;
import com.thtf.ezone.ezmap.model.Cardevice;
import com.thtf.ezone.ezmap.model.StationLine;
import com.thtf.ezone.ezmap.service.IStationLineService;
import com.thtf.ezone.ezmap.type.SelectType;
import com.thtf.ezone.eztms.acl.model.TmsSyqxsdRenyuan;
import com.thtf.ezone.eztms.login.util.LoginConstant;
import com.thtf.ezone.security.util.BeanUtil;

public class StationLineUtil{

	public static Map getStationLines(ActionContext ctx,SelectType type){
		String companyName = ComanyUtil.getCurrentUserCompany(ctx);
		IStationLineService stationLineService = (IStationLineService) BeanUtil
		.getBean("stationLineService");
		List<StationLine> list=(ArrayList) stationLineService.findAllStationLine(companyName);
		
		Map<Long, String> stationlinemap = new LinkedHashMap<Long, String>();
		if (SelectType.SINGLE.equals(type)) {
			stationlinemap.put(Long.valueOf(Constant.PLEASE_SELECT_VALUE),
					Constant.PLEASE_SELECT_LABLE);
		} else if (SelectType.ALL.equals(type)) {
			stationlinemap.put(Long.valueOf(Constant.SELECT_ALL_VALUE), Constant.SELECT_ALL_LABLE);
		}
		
		for (StationLine stationLine : list) {
			if (!StringUtils.isEmpty(stationLine.getLinename())) {
				stationlinemap.put(stationLine.getLineid() , stationLine.getLinename());
			}
		}
		return stationlinemap;
	}
}
