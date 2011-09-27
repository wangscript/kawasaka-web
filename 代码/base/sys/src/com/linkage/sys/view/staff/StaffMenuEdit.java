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
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.bean.system.MenuBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffMenuEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setMenus(IDataset menus);
	
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
		IData conditions = new DataMap();
		String operType = pd.getParameter("operType", "");
		if("edit".equals(operType)){
			this.queryStaffMenu(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		IData data = new DataMap();
		data.put("MENU_TYPE", "0");
		data.put("ITEM_FLAG", "1");
		
		IDataset parentMenus = this.menuBean.queryParentMenus(pd, null);
		IDataset staffs = this.staffBean.queryStaff(pd, data, null);
		IDataset menus = this.menuBean.queryMenus(pd, data, null);
		conditions.put("PARENT_MENUS", parentMenus);
		conditions.put("STAFFS", staffs);
		conditions.put("MENUS", menus);
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
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
		IData params = pd.getData();
		IDataset staffMenus = staffBean.queryStaffMenu(pd, params, null);
		if(staffMenus!=null && staffMenus.size() == 1){
			this.setInfo(staffMenus.getData(0));
		}else{
			common.error("��ȡԱ���˵���Ϣʧ�ܣ�");
		}
	}
	/**
	 * �������޸�Ա���˵���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void saveStaffMenu(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//�޸�
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.staffBean.updateStaffMenu(pd, params);
			msg = "�޸�Ա���˵���Ϣ�ɹ���";
		}
		//����
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.staffBean.addStaffMenu(pd, dataset);
			msg = "����Ա���˵���Ϣ�ɹ���";
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
	/**
	 * ��ѯ���˵����Ӳ˵�
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-22
	 */
	public void queryMenuByParent(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		
		IData params = new DataMap();
		params.put("PARENT_MENU_CODE", pd.getParameter("PARENT_MENU_CODE", ""));
		params.put("FLAG", "1");
		
		IDataset menus = menuBean.queryMenus(pd, params, null);
		this.setMenus(menus);
	}
}
