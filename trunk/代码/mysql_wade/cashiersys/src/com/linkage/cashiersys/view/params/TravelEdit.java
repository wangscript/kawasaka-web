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
public abstract class TravelEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ������Ŀ������������ҵ�����Bean
	 */
	private ParamsBean paramsBean = new ParamsBean();
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
			this.queryTravels(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * ��ѯҵ��������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void queryTravels(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset areas = paramsBean.queryTravels(pd, params, null);
		if(areas!=null && areas.size() == 1){
			this.setInfo(areas.getData(0));
		}else{
			common.error("��ȡ�����������Ϣʧ�ܣ�");
		}
	}
	/**
	 * �������޸�ҵ��������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void saveTravels(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//�޸�
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.paramsBean.updateTravels(pd, params);
			msg = "�޸�����������ɹ���";
		}
		//����
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			params.put("TRAVEL_CODE", "BTR_" + DualMgr.getSeqId(pd, "seq_travel_id"));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.paramsBean.addTravels(pd, dataset);
			msg = "��������������ɹ���";
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
