package com.linkage.sys.view.group;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.common.bean.util.UtilDAO;
import com.linkage.component.PageData;
import com.linkage.sys.bean.group.GroupBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

public abstract class GroupAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("add", true);

	}
	
	/**
	 * �����Ʒ��������ҵ�����Bean
	 */
	private GroupBean groupBean = new GroupBean();
	
	/**
	 * ��ѯ���й�˾/����
	 * @param cycle
	 * @throws Exception
	 */
	public void saveGroup(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		String msg = "�����ɹ���";
		IData params = pd.getData("add", true);
		String group_name = params.getString("GROUP_NAME");
		IData param = new DataMap();
		param.put("GROUP_NAME", group_name);
		Boolean exist = this.groupBean.existsGroup(pd, param, null);
		if(exist){
			common.error("���������Ѿ�����,�����������룡");
			return;
		}		
		dao.insert("TD_M_GROUP", params);
		redirectToMsg(msg);
	}
}
