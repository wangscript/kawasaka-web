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
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.bean.system.RightsBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffRightList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private StaffBean staffBean = new StaffBean();
	private RightsBean rightBean = new RightsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = pd.getData("cond", true);
		
		//页面参数返回
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset staffs = this.staffBean.queryStaff(pd, data, null);
		IDataset rights = this.rightBean.queryRights(pd, data, null);
		conditions.put("STAFFS", staffs);
		conditions.put("RIGHTS", rights);
		this.setConditions(conditions);
	}
	/**
	 * 查询员工权限信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void queryStaffRight(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset staffRights = staffBean.queryStaffRight(pd, params, pd.getPagination());
		
		//页面参数返回
		this.setInfos(staffRights);
		this.init(cycle);
	}
}
