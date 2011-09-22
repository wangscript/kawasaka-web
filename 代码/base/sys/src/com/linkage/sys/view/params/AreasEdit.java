/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.view.params;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class AreasEdit extends CashierBasePage{
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
			this.queryAreas(cycle);
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
	public void queryAreas(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset areas = paramsBean.queryAreas(pd, params, null);
		if(areas!=null && areas.size() == 1){
			this.setInfo(areas.getData(0));
		}else{
			common.error("��ȡҵ����������Ϣʧ�ܣ�");
		}
	}
	/**
	 * �������޸�ҵ��������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void saveAreas(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//�޸�
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.paramsBean.updateAreas(pd, params);
			msg = "�޸�ҵ���������ɹ���";
		}
		//����
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			params.put("AREA_CODE", "BAR_" + DualMgr.getSeqId(pd, "seq_area_id"));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.paramsBean.addAreas(pd, dataset);
			msg = "����ҵ���������ɹ���";
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
