/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.view.staff;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffList extends CashierBasePage{
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
	public void queryStaffs(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset staffs = staffBean.queryStaff(pd, params, pd.getPagination());
		this.setInfos(staffs);
		this.init(cycle);
	}

}
