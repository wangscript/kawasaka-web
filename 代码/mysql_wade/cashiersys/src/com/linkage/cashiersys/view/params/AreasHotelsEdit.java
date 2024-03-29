/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.params;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.params.ParamsBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class AreasHotelsEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private ParamsBean paramsBean = new ParamsBean();
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
			this.queryAreasHotels(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset hotels = this.paramsBean.queryHotels(pd, data, null);
		IDataset areas = this.paramsBean.queryAreas(pd, data, null);
		conditions.put("HOTELS", hotels);
		conditions.put("AREAS", areas);
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * 查询业务区酒店参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public void queryAreasHotels(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset hotelsItems = paramsBean.queryAreasHotels(pd, params, null);
		if(hotelsItems!=null && hotelsItems.size() == 1){
			this.setInfo(hotelsItems.getData(0));
		}else{
			common.error("获取业务区酒店参数信息失败！");
		}
	}
	/**
	 * 新增、修改业务区酒店参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void saveAreasHotels(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//修改
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.paramsBean.updateAreasHotels(pd, params);
			msg = "修改业务区酒店参数成功！";
		}
		//新增
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.paramsBean.addAreasHotels(pd, dataset);
			msg = "新增业务区酒店参数成功！";
		}
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
