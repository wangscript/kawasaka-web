/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.view.system;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.bean.system.RightsBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class StaffRightEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ������Ŀ������������ҵ�����Bean
	 */
	private StaffBean staffBean = new StaffBean();
	private RightsBean rightsBean = new RightsBean();
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
			this.queryStaffRight(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset staffs = this.staffBean.queryStaff(pd, data, null);
		IDataset rights = this.rightsBean.queryRights(pd, data, null);
		conditions.put("STAFFS", staffs);
		conditions.put("RIGHTS", rights);
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * ��ѯԱ��Ȩ����Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void queryStaffRight(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset staffRigths = staffBean.queryStaffRight(pd, params, null);
		if(staffRigths!=null && staffRigths.size() == 1){
			this.setInfo(staffRigths.getData(0));
		}else{
			common.error("��ȡԱ��Ȩ����Ϣʧ�ܣ�");
		}
	}
	/**
	 * �������޸�Ա��Ȩ����Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void saveStaffRight(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//�޸�
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.staffBean.updateStaffRight(pd, params);
			msg = "�޸�Ա��Ȩ����Ϣ�ɹ���";
		}
		//����
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.staffBean.addStaffRight(pd, dataset);
			msg = "����Ա��Ȩ����Ϣ�ɹ���";
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
