/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.system;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.system.MenuBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class MenusEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private MenuBean menuBean = new MenuBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String operType = pd.getParameter("operType", "");
		if("edit".equals(operType)){
			this.queryAreas(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		//查询父菜单
		IDataset parentMenus = this.menuBean.queryParentMenus(pd, null);
		conditions.put("PARENT_MENUS", parentMenus);
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * 查询菜单信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void queryAreas(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset menus = menuBean.queryMenus(pd, params, null);
		if(menus!=null && menus.size() == 1){
			this.setInfo(menus.getData(0));
		}else{
			common.error("获取菜单信息失败！");
		}
	}
	/**
	 * 新增修改菜单信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void saveMenus(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//修改
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.menuBean.updateMenus(pd, params);
			msg = "修改菜单信息成功！";
		}
		//新增
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.menuBean.addMenus(pd, dataset);
			msg = "新增菜单信息成功！";
		}
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
