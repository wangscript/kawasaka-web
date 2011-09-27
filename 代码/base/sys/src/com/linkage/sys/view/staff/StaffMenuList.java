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
import com.linkage.sys.bean.system.MenuBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffMenuList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ������Ŀ������������ҵ�����Bean
	 */
	private StaffBean staffBean = new StaffBean();
	private MenuBean menuBean = new MenuBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = pd.getData("cond", true);
		
		//ҳ���������
		IData data = new DataMap();
		data.put("MENU_TYPE", "0");
		data.put("ITEM_FLAG", "1");
		IDataset staffs = this.staffBean.queryStaff(pd, data, null);
		IDataset menus = this.menuBean.queryMenus(pd, data, null);
		conditions.put("STAFFS", staffs);
		conditions.put("MENUS", menus);
		this.setConditions(conditions);
	}
	/**
	 * ��ѯԱ���˵���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void queryStaffMenu(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset staffMenus = staffBean.queryStaffMenu(pd, params, pd.getPagination());
		
		//ҳ���������
		this.setInfos(staffMenus);
		this.init(cycle);
	}
}
