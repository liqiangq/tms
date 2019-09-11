package com.thtf.ezone.ezmap.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.thtf.ezone.ezmap.model.Locationmsg;
import com.thtf.ezone.ezmap.model.OilInspect;
import com.thtf.ezone.ezmap.service.IOilInspectService;
import com.thtf.ezone.security.util.BeanUtil;

public class Oil {

	public List getDriveStopList(List list) {// 通过传入的一天内车辆数据信息，将连续行驶和连续停车的数据分离
		List lastList = new LinkedList();

		List stopList = new LinkedList();// 记录一段时间内的连续停车信息
		List driveList = new LinkedList();// 记录一段时间内的连续行驶信息
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Locationmsg msg = (Locationmsg) list.get(i);
				if (msg.getCaroffflag().equals("1")) {
					if (stopList != null && stopList.size() > 0) {
						lastList.add(stopList);
						stopList = new LinkedList();
					}

					driveList.add(msg);

				} else {
					if (driveList != null && driveList.size() > 0) {
						lastList.add(driveList);
						driveList = new LinkedList();
					}
					msg.setOilcost(null);
					stopList.add(msg);
				}

				if (i == list.size() - 1) {
					if (stopList != null && stopList.size() > 0) {
						lastList.add(stopList);
						stopList = new LinkedList();
					}
					if (driveList != null && driveList.size() > 0) {
						lastList.add(driveList);
						driveList = new LinkedList();
					}
				}
			}

		}
		return lastList;
	}

	// --返回更新后的数据列表
	public List computeOilPara(List list) {
		List returnList = new LinkedList();

		if (list != null && list.size() > 0) {

			for (int k = 0; k < list.size(); k++) {
				List listTmp = (List) list.get(k);
				if (listTmp != null && listTmp.size() > 0) {
					// ----调用处理数据的函数
					Locationmsg msg1 = (Locationmsg) listTmp.get(0);
					if (msg1.getCaroffflag().equals("1")) {
						if (listTmp.size() < 5) {

							double ylxs = 0;
							Date date = new Date();
							for (int j = 0; j < listTmp.size(); j++) {

								Locationmsg msg = (Locationmsg) listTmp.get(j);
								ylxs = Double.valueOf(msg.getOilcost());
								if (j == listTmp.size() - 1) {
									date = msg.getSendtime();
								}

							}// --计算平均油量系数完毕

							for (int j = 0; j < listTmp.size(); j++) {
								Locationmsg msg = (Locationmsg) listTmp.get(j);
								msg.setOilcostnew(String.valueOf(ylxs / 4));
								msg.setOilnew(getOil(msg));
								returnList.add(msg);
							}// --更新数据表中对应的数据的值,并且将更新完毕的数据存放入list中

						} else if (listTmp.size() == 5) {
							double ylxs1 = 0;
							Date date = new Date();
							for (int j = 0; j < listTmp.size() - 1; j++) {
								Locationmsg msg = (Locationmsg) listTmp.get(j);
								ylxs1 += Double.valueOf(msg.getOilcost());
							}
							for (int j = 0; j < listTmp.size() - 1; j++) {
								Locationmsg msg = (Locationmsg) listTmp.get(j);
								msg.setOilcostnew(String.valueOf(ylxs1 / 4));
								msg.setOilnew(getOil(msg));
								returnList.add(msg);
							}
							// --前四条数据的油量系数数值为前四条油量系数的平均值

							ylxs1 = 0;
							for (int j = 1; j < listTmp.size(); j++) {
								Locationmsg msg = (Locationmsg) listTmp.get(j);
								date = msg.getSendtime();
								ylxs1 += Double.valueOf(msg.getOilcost());
							}
							Locationmsg msg = (Locationmsg) listTmp.get(listTmp.size() - 1);
							msg.setOilcostnew(String.valueOf(ylxs1 / 4));
							msg.setOilnew(getOil(msg));
							returnList.add(msg);
							// --第五条数据数值为后四条数据的平均值

						} else if (listTmp.size() > 5) {
							double tmp = 0;

							for (int j = 0; j <= listTmp.size() - 4; j++) {
								double ylxs = 0;
								Date date = new Date();
								Locationmsg msg = new Locationmsg();

								// --每次计算四条数据
								for (int i = j; i < j + 4; i++) {
									if (j == 0) {
										msg = (Locationmsg) listTmp.get(i + 1);// 启动后第一条数据抛弃，采用第二条数据作为第一条数据
									} else {
										msg = (Locationmsg) listTmp.get(i);
									}
									date = msg.getSendtime();
									ylxs += Double.valueOf(msg.getOilcost());
								}

								if (j == 0) {
									tmp = ylxs / 4;
									for (int i = 0; i < j + 4; i++) {
										msg = (Locationmsg) listTmp.get(i);
										msg.setOilcostnew(String.valueOf(tmp));
										msg.setOilnew(getOil(msg));
										returnList.add(msg);
									}
								} else {// 油量系数计算结果 大于上一条计算结果时，默认上一计算结果。削上行峰。
									if (ylxs / 4 < tmp) {
										tmp = ylxs / 4;
									}

									msg = (Locationmsg) listTmp.get(j + 3);
									msg.setOilcostnew(String.valueOf(tmp));
									msg.setOilnew(getOil(msg));
									returnList.add(msg);
								}

							}
						}
					} else {
						// ---
						for (int j = 0; j < listTmp.size(); j++) {
							Locationmsg msg = (Locationmsg) listTmp.get(j);
							Date date = new Date();
							date = msg.getSendtime();
							msg.setOilcostnew(null);
							msg.setOilnew(null);
							returnList.add(msg);
						}

					}

				}
			}
		}

		return returnList;
	}

	public String getOil(Locationmsg msg) {
		String returnOil = null;
		IOilInspectService service = (IOilInspectService) BeanUtil.getBean("oilInspectService");

		String hql = "from OilInspect where serial='" + msg.getSerial() + "'";
		List listInspect = new LinkedList();
		listInspect = service.LoadRowsetOilInspect(hql);
		double oilLast = 0;
		double msgOilPara = 0;
		if (listInspect != null && listInspect.size() > 0) {
			msgOilPara = Double.valueOf(msg.getOilcostnew());
			for (int i = 0; i < listInspect.size(); i++) {
				OilInspect oilInspect = (OilInspect) listInspect.get(i);
				double oilParaTemp = Double.valueOf(oilInspect.getOilPara());
				if (i == 0) {
					if (msgOilPara < oilParaTemp) {
						double youliang = Double.valueOf(oilInspect.getOil());
						oilLast = youliang * (msgOilPara / oilParaTemp);
						break;
					}
				} else {
					if (msgOilPara < oilParaTemp) {
						OilInspect oilInspect1 = (OilInspect) listInspect.get(i - 1);
						oilLast = ((Double.valueOf(oilInspect.getOil()) - Double.valueOf(oilInspect1.getOil())) / (Double
								.valueOf(oilInspect.getOilPara()) - Double.valueOf(oilInspect1.getOilPara())))
								* Double.valueOf(msg.getOilcostnew());
						break;
					}
				}
			}
		}
		returnOil = String.valueOf(oilLast);

		return returnOil;
	}
}
