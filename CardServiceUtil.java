package com.thtf.ezone.ezmap.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.thtf.ezone.ezframework.adapter.struts.ActionContext;
import com.thtf.ezone.ezmap.Constant;
import com.thtf.ezone.ezmap.model.Cardevice;
import com.thtf.ezone.ezmap.query.CollectionCondition;
import com.thtf.ezone.ezmap.query.LogicType;
import com.thtf.ezone.ezmap.query.OperatorType;
import com.thtf.ezone.ezmap.query.Order;
import com.thtf.ezone.ezmap.query.Parameter;
import com.thtf.ezone.ezmap.query.QueryInfo;
import com.thtf.ezone.ezmap.query.SingleCondition;
import com.thtf.ezone.ezmap.service.ICardeviceService;
import com.thtf.ezone.ezmap.type.SelectType;
import com.thtf.ezone.eztms.acl.model.TmsSyqxsdRenyuan;
import com.thtf.ezone.eztms.acl.util.PrivilegeJspUtil;
import com.thtf.ezone.eztms.login.util.LoginConstant;
import com.thtf.ezone.security.util.BeanUtil;

/**
 * 车辆DAO工具类
 * 
 * @author lixiang.gao
 * 
 */
public class CardServiceUtil {

	private static Log log = LogFactory.getLog(CardServiceUtil.class);

	private static Map<String, String> cachedSerialMap = new HashMap<String, String>();

	private static Map<String, String> cachedCaridMap = new HashMap<String, String>();

	private static ICardeviceService cardeviceService;

	/**
	 * 
	 * 功能说明: 根据车机序列号查询车牌号
	 * 
	 * @param serial
	 * @return 2008-6-24
	 */
	public static String getCarid(String serial) {
		Assert.hasText(serial, "serial must not be empty.");
		String carid = cachedSerialMap.get(serial);
		if (carid == null) {
			try {
				Cardevice cardDevice = getCardeviceService()
						.loadCardeviceBySerial(serial);
				if (cardDevice != null) {
					carid = cardDevice.getCarid();
					cachedSerialMap.put(serial, carid);
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		return carid;
	}

	/**
	 * 
	 * 功能说明: 根据车牌号查询车机序列号
	 * 
	 * @param carid
	 * @return 2008-6-24
	 */
	public static String getSerial(String carid) {
		Assert.hasText(carid, "carid must not be empty.");
		String serial = cachedCaridMap.get(carid);
		if (serial == null) {
			try {
				Cardevice cardDevice = getCardeviceService()
						.loadCardeviceByCarid(carid);
				if (cardDevice != null) {
					serial = cardDevice.getSerial();
					cachedCaridMap.put(carid, serial);
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		return serial;
	}

	public static void flushCachedCardevice(Cardevice cardevice) {
		String serial = cardevice.getSerial();
		String carid = cardevice.getCarid();
		if (!StringUtils.isEmpty(serial)) {
			cachedSerialMap.remove(serial);
		}
		if (!StringUtils.isEmpty(carid)) {
			cachedCaridMap.remove(carid);
		}
	}

	/**
	 * 
	 * 功能说明: 查询当前用户的被授权车机序号
	 * 
	 * @param ctx
	 * @return 2008-6-24
	 */
	public static Map<String, String> getAllCarDevices(ActionContext ctx,
			SelectType type) {
		Map<String, String> caridmap = new LinkedHashMap<String, String>();
		if (SelectType.SINGLE.equals(type)) {
			caridmap.put(Constant.PLEASE_SELECT_VALUE,
					Constant.PLEASE_SELECT_LABLE);
		} else if (SelectType.ALL.equals(type)) {
			caridmap.put(Constant.SELECT_ALL_VALUE, Constant.SELECT_ALL_LABLE);
		}
		List<Cardevice> cardevices = getAllCardevices(ctx);
		for (Cardevice cardevice : cardevices) {
			if (!StringUtils.isEmpty(cardevice.getSerial())
					&& !StringUtils.isEmpty(cardevice.getCarid())) {
				caridmap.put(cardevice.getSerial(), cardevice.getCarid());
			}
		}
		return caridmap;
	}

	/**
	 * 
	 * 功能说明: 查询当前用户所有的被授权车牌号
	 * 
	 * @param ctx
	 * @return 2008-6-24
	 */
	public static List<String> getAllCarDeviceCarids(ActionContext ctx) {
		List<String> caridmap = new ArrayList<String>();
		List<Cardevice> cardevices = getAllCardevices(ctx);
		for (Cardevice cardevice : cardevices) {
			if (!StringUtils.isEmpty(cardevice.getCarid())) {
				caridmap.add(cardevice.getCarid());
			}
		}
		return caridmap;
	}

	// /**
	// * 功能说明: 查询当前用户所有的被授权车牌号
	// *
	// * @param ctx
	// * @param caridmap
	// */
	// public static void getAllCarDeviceCaridMaps(ActionContext ctx,
	// Map<String, String> caridmap) {
	// List<Cardevice> cardevices = getAllCardevices(ctx);
	// for (Cardevice cardevice : cardevices) {
	// if (!StringUtils.isEmpty(cardevice.getCarid())) {
	// caridmap.put(cardevice.getCarid(), cardevice.getCarid());
	// }
	// }
	// }

	/**
	 * 
	 * 功能说明: 查询当前用户所有的车机系列号
	 * 
	 * @param ctx
	 * @return 2008-6-24
	 */
	public static List<String> getAllCarDeviceSerials(ActionContext ctx) {
		List<String> serials = new ArrayList<String>();
		List<Cardevice> cardevices = getAllCardevices(ctx);
		for (Cardevice cardevice : cardevices) {
			serials.add(cardevice.getSerial());
		}
		return serials;
	}

	public static ICardeviceService getCardeviceService() {
		if (cardeviceService == null) {
			cardeviceService = (ICardeviceService) BeanUtil
					.getBean("cardeviceService");
		}
		return cardeviceService;
	}

	/**
	 * 获取当前用户所有授权的车辆信息
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static List<Cardevice> getAllCardevices(ActionContext ctx) {
		List<Cardevice> cardevices = new ArrayList<Cardevice>();
		TmsSyqxsdRenyuan renyuan = (TmsSyqxsdRenyuan) ctx.session()
				.getAttribute(LoginConstant.TMS_LOGIN_USER);
		if (renyuan != null) {
			// 未登录
			cardevices = (List<Cardevice>) PrivilegeJspUtil.getDeviceService()
					.getObjectByUser(renyuan);
		}
		Collections.sort(cardevices, new CaridComparator());
		return cardevices;
	}

	public static class CaridComparator implements Comparator<Cardevice> {

		private Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);

		public int compare(Cardevice cardevice1, Cardevice cardevice2) {
			// String carid1= cardevice1.getCarid();
			// String carid2= cardevice2.getCarid();
			// int comp= carid1.compareTo(carid2);

			// int comp=
			return cmp.compare(cardevice1.getCarid(), cardevice2.getCarid());
			// System.out.println("Carid1="+carid1+",Carid2="+carid2+",comp="+comp
			// );
			// return comp;
		}

	}

	/**
	 * 获取指定车辆
	 */
	public static List<Cardevice> getSelectedCardevices(ActionContext ctx,
			List<String> serials) throws Exception {
		Assert.notNull(ctx, "ctx must not be null.");
		List<Cardevice> selectCardevices = new ArrayList<Cardevice>();
		if ("true".equalsIgnoreCase(ctx.request().getParameter("isRight"))) {
			List<Cardevice> cardevices = getAllCardevices(ctx);
			String serial = null;
			for (Cardevice cardevice : cardevices) {
				serial = cardevice.getSerial();
				if (!StringUtils.isEmpty(serial) && serials.contains(serial)) {
					selectCardevices.add(cardevice);
				}
			}
		} else {
			selectCardevices.addAll(searchBySerials(ctx, serials));
		}
		return selectCardevices;
	}

	public static List<String> getSerials(ActionContext ctx) throws Exception {
		String open = ctx.request().getParameter("open");
		List<String> selectedSerials = new ArrayList<String>();
		if ("true".equalsIgnoreCase(ctx.request().getParameter("isRight"))) {
			List<Cardevice> cardevices = getAllCardevices(ctx);
			if (cardevices.size() == 0) {
				ctx.session().setAttribute("selectedSerials", selectedSerials);
				return selectedSerials;
			}
			String serials = ctx.request().getParameter("serials");
			String carids = ctx.request().getParameter("carids");
			
			
			
			if (!StringUtils.isEmpty(serials) && !"-1".equals(serials)
					&& !"{0}".equals(serials)) {
				String[] serialsArray = serials.split(",");
				List<String> serialsList = Arrays.asList(serialsArray);
				String serial;
				for (Cardevice cardevice : cardevices) {
					serial = cardevice.getSerial();
					if (!StringUtils.isEmpty(serial)
							&& serialsList.contains(serial)) {
						selectedSerials.add(serial);
					}
				}
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			} else if (!StringUtils.isEmpty(carids) && !"-1".equals(carids)
					&& !"{0}".equals(carids)) {
				String[] caridsArray = null;
				if ("get".equalsIgnoreCase(ctx.request().getMethod())) {
					String newCards = new String(carids.getBytes("ISO8859-1"),
							"GB2312");
					caridsArray = newCards.split(",");
				} else {
					caridsArray = carids.split(",");
				}
				List<String> caridsList = Arrays.asList(caridsArray);
				String carid;
				for (Cardevice cardevice : cardevices) {
					carid = cardevice.getCarid();
					if (!StringUtils.isEmpty(carid)
							&& caridsList.contains(carid)) {
						selectedSerials.add(cardevice.getSerial());
					}
				}
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			} else {
				selectedSerials = (List<String>) ctx.session().getAttribute(
						"selectedSerials");
				if (selectedSerials == null) {
					selectedSerials = new ArrayList<String>();
				}
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			}
			cardevices.clear();
		} else {

			String serials = ctx.request().getParameter("serials");
			String carids = ctx.request().getParameter("carids");
		
			if (!StringUtils.isEmpty(serials) && !"-1".equals(serials)
					&& !"{0}".equals(serials)) {
				String[] serialsArray = serials.split(",");
				selectedSerials = Arrays.asList(serialsArray);
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			} else if (!StringUtils.isEmpty(carids) && !"-1".equals(carids)
					&& !"{0}".equals(carids)) {
				String[] caridsArray = null;
				
				if ("get".equalsIgnoreCase(ctx.request().getMethod())) {
					String newCards = "";
					byte[] caridsBTGB = carids.getBytes("ISO8859-1");
					
					if(!StringUtils.isEmpty(open)){
						newCards = new String(new String(caridsBTGB,"UTF-8"));//reflesh
					}else{
						newCards = new String(carids.getBytes("ISO8859-1"),
						"GB2312");//reflesh
					}
					
					caridsArray = newCards.split(",");
					
				} else {			
					caridsArray = carids.split(",");				
					
				}
				
				List<String> caridsList = Arrays.asList(caridsArray);
				selectedSerials = searchByCarids(ctx, caridsList);
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			} else {
				selectedSerials = (List<String>) ctx.session().getAttribute(
						"selectedSerials");
				if (selectedSerials == null) {
					selectedSerials = new ArrayList<String>();
				}
				ctx.session().setAttribute("selectedSerials", selectedSerials);
			}
		}
		return selectedSerials;
	}

	private static List<String> searchByCarids(ActionContext ctx,
			List<String> caridList) throws Exception {
		List<String> serials = new ArrayList<String>();
		if (caridList == null || caridList.size() == 0) {
			return serials;
		}
		CollectionCondition searchCondition = new CollectionCondition();

		Parameter serialParam = new Parameter("a.carid", caridList,
				OperatorType.IN);
		SingleCondition condition = new SingleCondition(LogicType.AND,
				serialParam);
		searchCondition.addCondition(condition);

		Map<String, String> tableAliases = new LinkedHashMap<String, String>();
		tableAliases.put("Cardevice", "a");

		List<String> fields = new ArrayList<String>();
		fields.add("a");

		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order("a.carid"));
		QueryInfo queryInfo = new QueryInfo(tableAliases, searchCondition,
				fields, orders);
		ICardeviceService cardeviceservice = (ICardeviceService) BeanUtil
				.getBean("cardeviceService");
		List list = cardeviceservice.loadCardevices(queryInfo, 0, -1);
		if (list != null && list.size() == 2 && list.get(1) != null) {
			List<Cardevice> result = (List<Cardevice>) list.get(1);
			for (Cardevice cardevice : result) {
				System.out.println("cardevice : "+cardevice.toString());
				serials.add(cardevice.getSerial());
			}
			result.clear();
			list.clear();
		}
		System.out.println("serials size : "+serials.size());
		return serials;
	}

	private static List<Cardevice> searchBySerials(ActionContext ctx,
			List<String> serials) throws Exception {
		List<Cardevice> cardevices = new ArrayList<Cardevice>();
		if (serials == null || serials.size() == 0) {
			return cardevices;
		}
		CollectionCondition searchCondition = new CollectionCondition();

		Parameter serialParam = new Parameter("a.serial", serials,
				OperatorType.IN);
		SingleCondition condition = new SingleCondition(LogicType.AND,
				serialParam);
		searchCondition.addCondition(condition);

		Map<String, String> tableAliases = new LinkedHashMap<String, String>();
		tableAliases.put("Cardevice", "a");

		List<String> fields = new ArrayList<String>();
		fields.add("a");

		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order("a.carid"));
		QueryInfo queryInfo = new QueryInfo(tableAliases, searchCondition,
				fields, orders);
		ICardeviceService cardeviceservice = (ICardeviceService) BeanUtil
				.getBean("cardeviceService");
		List list = cardeviceservice.loadCardevices(queryInfo, 0, -1);
		if (list != null && list.size() == 2 && list.get(1) != null) {
			List<Cardevice> result = (List<Cardevice>) list.get(1);
			for (Cardevice cardevice : result) {
				cardevices.add(cardevice);
			}
			result.clear();
			list.clear();
		}
		return cardevices;
	}
}
