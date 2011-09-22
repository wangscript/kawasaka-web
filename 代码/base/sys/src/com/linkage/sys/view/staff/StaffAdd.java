/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.view.staff;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setHotels(IDataset hotels);
	
	/**
	 * 负责员工管理这块的业务操作Bean
	 */
	private StaffBean staffBean = new StaffBean();
	private ParamsBean paramsBean = new ParamsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("cond", true);
		
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset departs = paramsBean.queryDeparts(pd, data, null);
		IDataset areas = paramsBean.queryAreas(pd, data, null);
		//IDataset hotels = paramsBean.queryHotels(pd, data, null);
		
		conditions.put("DEPARTS", departs);
		conditions.put("AREAS", areas);
		//conditions.put("HOTELS", hotels);
		
		this.setConditions(conditions);
	}
	/**
	 * 查询用户信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void addStaff(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "新增员工信息成功，初始化密码默认为:123456";
		IData params = pd.getData("cond", true);
		String staffId = params.getString("STAFF_ID");
		IData param = new DataMap();
		param.put("STAFF_ID", staffId);
		IDataset staffDs = this.staffBean.queryStaff(pd, param, null);
		if(staffDs!=null && staffDs.size()>0){
			common.error("该员工号【"+staffId+"】已存在，请重新输入！");
		}		
		
		params.put("STAFF_PWD", "123456");
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.staffBean.addStaffs(pd, dataset);
		
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
