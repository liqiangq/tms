package com.thtf.ezone.ezmap.util;

import com.thtf.ezone.ezframework.adapter.struts.ActionContext;
import com.thtf.ezone.eztms.acl.model.TmsSyqxsdRenyuan;
import com.thtf.ezone.eztms.login.util.LoginConstant;

public class ComanyUtil {

	public static String getCurrentUserCompany(ActionContext ctx){
		 TmsSyqxsdRenyuan renyuan = (TmsSyqxsdRenyuan) ctx.session()
			.getAttribute(LoginConstant.TMS_LOGIN_USER);
			if (renyuan!=null && renyuan.getOpcompany()!=null) {
				return renyuan.getOpcompany();				
			}
		return null;
	}
}
