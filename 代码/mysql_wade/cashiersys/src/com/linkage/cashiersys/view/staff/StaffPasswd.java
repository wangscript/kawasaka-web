/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.staff;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.staff.StaffBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class StaffPasswd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ����Ա����������ҵ�����Bean
	 */
	private StaffBean staffBean = new StaffBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		String alertInfo = "���볤������1λ���12λ��";
		this.setAlertInfo(alertInfo);
	}
	/**
	 * �޸��û�����
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void updateStaffPasswd(IRequestCycle cycle) throws Exception {
		String msg = "";
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		String operType = pd.getParameter("cond_OPER_TYPE", "");
		params.put("STAFF_ID", pd.getContext().getStaffId());
		
		//�����޸�
		if("A".equals(operType)){
			int counts = staffBean.updateStaffPasswd(pd, params);
			//�޸�ʧ��
			if(counts < 1){
				common.error("�����޸�ʧ�ܣ�");
			}
			msg = "�����޸ĳɹ���";
		}
		//��������
		else if("B".equals(operType)){
			int counts = staffBean.resetStaffPasswd(pd, params);
			//����ʧ��
			if(counts < 1){
				common.error("��������ʧ�ܣ�");
			}
			msg = "�������óɹ���������������Ϊ:12346";
		}else{
			common.error("�������ʧ�ܣ�");
		}
		
		redirectToMsg(msg);
	}
}
