package com.linkage.sys.view.techno;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.UtilDAO;
import com.linkage.component.PageData;
import com.linkage.sys.bean.group.GroupBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.bean.techno.TechnoBean;
import com.linkage.sys.view.common.CashierBasePage;

public abstract class TechnoAdd extends CashierBasePage{
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
		IData conditions = pd.getData("cond", true);

	}
	
	/**
	 * �����Ʒ��������ҵ�����Bean
	 */
	private TechnoBean technoBean = new TechnoBean();
	

	
	/**
	 * ���Ӵ�����Ϣ
	 */
	
	public void addTechno(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		String msg = "�����ɹ���";
		IData params = pd.getData("add", true);
		String TECHNO_CLASS = params.getString("TECHNO_CLASS");
		IData param = new DataMap();
		param.put("TECHNO_CLASS", TECHNO_CLASS);
		Boolean exist = this.technoBean.existsTechno(pd, param, null);
		if(exist){
			common.error("��Ʒ�����Ѿ�����,�����������룡");
			return;
		}		
		dao.insert("TD_M_TECHNO", params);
	      StringBuilder  strScript = new StringBuilder("");
	      strScript.append("parent.document.getElementById('bquery').click();");
	      redirectToMsgByScript("�����ɹ���", strScript.toString());
	}
	
	/**
	 * 
	 */
	
	public void updateTechno(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		UtilDAO dao = new UtilDAO(pd);
		IData edits = pd.getData("edit", true);
		//IData data = new DataMap();
		//data.put("GROUP_ID", pd.getData());
//		Boolean exist = this.technoBean.existsTechno(pd, edits, null);
//		if(exist){
//			common.error("��Ʒ���ࡾ"+edits.getString("TECHNO_CLASS")+"���Ѿ��������������룡");
//			return;
//		}
    	dao.update("TD_M_TECHNO", edits);


      StringBuilder  strScript = new StringBuilder("");
      strScript.append("parent.document.getElementById('bquery').click();");
      redirectToMsgByScript("�޸ĳɹ���", strScript.toString());
	}
	
	/**
	 * 
	 */
	public void querytechnoBypk(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();

		UtilDAO dao = new UtilDAO(pd);
		IData data = new DataMap();
		data.put("TECHNO_ID", pd.getParameter("TECHNO_ID"));
		IData info = dao.queryByPK("TD_M_TECHNO", data); // ͨ����ȡ����ֵ����ѯ�����ݷ�������Դ(info)��

		this.setInfo(info);
		pd.setTransferData(pd.getData());

		if (info == null){
			common.error("��Ҫ�鿴�������Ѳ�����");}
	}
	
/**
 * delTechno
 */
	
	public void delTechno(IRequestCycle cycle) throws Exception {
		
		PageData pd = getPageData();
		UtilDAO dao = new UtilDAO(pd);
		IData edits = pd.getData("edit", true);
		IData data = new DataMap();
		data.put("TECHNO_ID", pd.getParameter("TECHNO_ID"));
    	dao.delete("TD_M_TECHNO", data);
 

	      StringBuilder  strScript = new StringBuilder("");
	      strScript.append("parent.document.getElementById('bquery').click();");
	      redirectToMsgByScript("ɾ���ɹ���", strScript.toString());
	}
}
