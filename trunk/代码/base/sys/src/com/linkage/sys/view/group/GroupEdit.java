package com.linkage.sys.view.group;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.UtilBean;
import com.linkage.common.bean.util.UtilDAO;
import com.linkage.component.PageData;
import com.linkage.sys.bean.group.GroupBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

public abstract class GroupEdit extends CashierBasePage {
	public abstract void setInfo(IData info);

	public abstract void setInfos(IDataset infos);

	public abstract void setConditions(IData conditions);

	/**
	 * ҳ���ʼ������
	 * 
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {

	}

	/**
	 * �����Ʒ��������ҵ�����Bean
	 */
	private GroupBean groupBean = new GroupBean();

	/**
	 * ��ѯ���й�˾/����
	 * 
	 * @param cycle
	 * @throws Exception
	 */
	public void queryGroupListByPK(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();

		UtilDAO dao = new UtilDAO(pd);

		IData data = new DataMap();
		data.put("GROUP_ID", pd.getParameter("GROUP_ID"));
		IData info = dao.queryByPK("TD_M_GROUP", data); // ͨ����ȡ����ֵ����ѯ�����ݷ�������Դ(info)��

		this.setInfo(info);
		pd.setTransferData(pd.getData());

		if (info == null){
			common.error("��Ҫ�鿴�������Ѳ�����");}
	}
	
	/**
	 * 
	 */
	public void upGroup(IRequestCycle cycle) throws Exception {
		
		PageData pd = getPageData();
		UtilDAO dao = new UtilDAO(pd);
		IData edits = pd.getData("edit", true);
		//IData data = new DataMap();
		//data.put("GROUP_ID", pd.getData());
    	dao.update("TD_M_GROUP", edits);
    	pd.setTransferData("cond");

		redirectToMsg("�޸ĳɹ�", "sys.group.GroupList", "queryGroupList");

	}
	/**
	 * 
	 */
	
	public void DelGroup(IRequestCycle cycle) throws Exception {
		
		PageData pd = getPageData();
		UtilDAO dao = new UtilDAO(pd);

		IData data = new DataMap();
		data.put("GROUP_ID", pd.getParameter("edit_GROUP_ID"));
    	dao.delete("TD_M_GROUP", data);
    	pd.setTransferData("cond");

		redirectToMsg("ɾ���ɹ�", "sys.group.GroupList", "queryGroupList");
	}
}
