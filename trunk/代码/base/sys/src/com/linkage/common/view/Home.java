package com.linkage.common.view;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.login.LoginBean;
import com.linkage.component.AppContext;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;


public abstract class Home extends AppSafePage {

	/**
	 * staff login
	 * @param cycle
	 * @throws Exception
	 */
	public void login(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		LoginBean bean = new LoginBean();
		IData data = bean.queryStaff(pd, pd.getData());
		if (data == null || data.size() == 0) {
			common.error("用户名或密码输入错误,登陆失败！");
		}else{
			//session设置，设置工号和姓名的session.
			AppContext ctx = (AppContext)pd.getContext();
			ctx.setAttribute("STAFF_ID", data.getString("STAFF_ID"));
			ctx.setAttribute("STAFF_NAME", data.getString("STAFF_NAME"));			
			ctx.setAttribute("ROLE_CODE", data.getString("ROLE_CODE"));
			ctx.setAttribute("AREA_CODE", data.getString("AREA_CODE"));
			ctx.setAttribute("DEPART_CODE", data.getString("DEPART_CODE"));
			ctx.setStaffId(data.getString("STAFF_ID", ""));
			ctx.setStaffName(data.getString("STAFF_NAME", ""));
			ctx.setPrivMap(new DataMap());
			
			//设置用户菜单
			IData menus = bean.prepareStaffMenuInfo(pd, data);
			ctx.getPrivMap().put("MENUS_SET", menus);
			//设置用户权
			IData rights = bean.prepareStaffRightInfo(pd, data);
			//ctx.getPrivMap().put("RIGHTS_SET", rights);
			
			pd.getSession().setAttribute("STAFF_ID", data.get("STAFF_ID"));
			pd.getSession().setAttribute("STAFF_NAME", data.get("STAFF_NAME"));
			pd.getSession().setAttribute("ROLE_CODE", rights.keySet().toString());
			//System.out.println("------------------------->"+pd.getSession().getAttribute("ROLE_CODE"));
			
			log.debug("ctx==="+ctx);
			ctx.setEpachyName(ctx.getPrivMap().toString());
	    	ctx.setValidate(true);
	    	cycle.activate("Main");
		} 
	}

}