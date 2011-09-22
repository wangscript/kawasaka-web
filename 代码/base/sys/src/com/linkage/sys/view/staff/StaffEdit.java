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
public abstract class StaffEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setHotels(IDataset hotels);
	
	/**
	 * ����Ա����������ҵ�����Bean
	 */
	private StaffBean staffBean = new StaffBean();
	private ParamsBean paramsBean = new ParamsBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		
		//׼��ҳ�����
		IData conditions = pd.getData("cond", true);
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset departs = paramsBean.queryDeparts(pd, data, null);
		IDataset areas = paramsBean.queryAreas(pd, data, null);
		//IDataset hotels = paramsBean.queryHotels(pd, data, null);
		
		conditions.put("DEPARTS", departs);
		conditions.put("AREAS", areas);
		//conditions.put("HOTELS", hotels);
		conditions.put("DISABLED", "true");
		this.setConditions(conditions);
		//��ѯ�û���Ϣ
		this.queryStaffs(cycle);
	}
	/**
	 * ��ѯ�û���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-19
	 */
	public void queryStaffs(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		String staffId = params.getString("STAFF_ID");
		IDataset staffs = staffBean.queryStaff(pd, params, pd.getPagination());
		if(staffs!=null && staffs.size() != 1){
			common.error("��ȡԱ����"+staffId+"����Ϣ����");
		}
		
		IData param = new DataMap();
		param.put("AREA_CODE", staffs.getData(0).getString("AREA_CODE", ""));
		param.put("ITEM_FLAG", "1");		
		
		this.setInfo(staffs.getData(0));
	}
	/**
	 * �����û���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void updateStaff(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		
		IData params = pd.getData("cond", true);
		String staffId = params.getString("STAFF_ID");
		String msg = "�޸�Ա����"+staffId+"����Ϣ�ɹ���";
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		
		this.staffBean.updateStaffs(pd, params);
		
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	/**
	 * ��ѯҵ�����ľƵ���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public void queryHotelsByArea(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		
		IData params = new DataMap();
		params.put("AREA_CODE", pd.getParameter("AREA_CODE", ""));
		params.put("ITEM_FLAG", "1");
		
	}
}
