/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.view.news;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.sys.bean.news.NewsBean;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author chenzg
 *
 */
public abstract class NewsEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ������Ŀ������������ҵ�����Bean
	 */
	private NewsBean newsBean = new NewsBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String operType = pd.getParameter("operType", "");
		if("edit".equals(operType)){
			this.queryNews(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * ��ѯ��Ŀ��Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void queryNews(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset departs = newsBean.queryNews(pd, params, null);
		if(departs!=null && departs.size() == 1){
			departs.getData(0).put("NEW_CID_NAME", Utility.getStaticValue(pd, "CATEGORY_LIST", departs.getData(0).getString("NEW_CID")));
			this.setInfo(departs.getData(0));
		}else{
			common.error("��ȡ���Ų�����Ϣʧ�ܣ�");
		}
	}
	/**
	 * �޸�������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-13
	 */
	public void saveNews(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//�޸�
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			this.newsBean.updateNews(pd, params);
			msg = "���Ų����޸ĳɹ���";
		}
		//����
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.newsBean.addNews(pd, dataset);
			msg = "�������ųɹ���";
		}
		//�������Ų�����ѯ����
		//StringBuilder  strScript = new StringBuilder("");
		//strScript.append("parent.document.getElementById('bquery').click();");
		//redirectToMsgByScript(msg, strScript.toString());
		redirectToMsg(msg);
	}
}
