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

public abstract class TechnoList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * �����Ʒ��������ҵ�����Bean
	 */
	private TechnoBean technoBean = new TechnoBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("cond", true);
	//	conditions.put("ITEM_FLAG", "1");
		IData data = new DataMap();
		//data.put("ITEM_FLAG", "1");
		IDataset technoclass = technoBean.queryTechnoLists(pd, data, null);
		conditions.put("TECHNOCLASS", technoclass);
		if(null!=pd.getParameter("TECHNO_CLASS")&&!"".equals(pd.getParameter("TECHNO_CLASS","")))
			conditions.put("TECHNO_CLASS", pd.getParameter("TECHNO_CLASS",""));
		this.setConditions(conditions);
	}
	

	
	/**
	 * ��ѯ���м�������
	 * @param cycle
	 * @throws Exception
	 */
	public void querytechnoList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		//params.put("ITEM_FLAG", "1");

		IDataset GroupList = technoBean.queryTechnoLists(pd, params, pd.getPagination());
		this.setInfos(GroupList); 
		//this.init(cycle); 
	}
	
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
		redirectToMsg(msg);
	}
	
/**
 * ��ѯ���������б�
 */

	public void querySoluList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		//params.put("ITEM_FLAG", "1");

		IDataset SoluList = technoBean.querySoluLists(pd, params, pd.getPagination());
		this.setInfos(SoluList); 
		//this.init(cycle); 
	}


	/**
	 * ���Ӵ�����Ϣ
	 */
	
	public void addSolution(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		IData params = pd.getData("add", true);
		String TECHNO_CLASS = params.getString("SOLU_NAME");
		IData param = new DataMap();
		param.put("SOLU_NAME", TECHNO_CLASS);
		Boolean exist = this.technoBean.existsSolu(pd, param, null);
		if(exist){
			common.error("���������Ѿ�����,�����������룡");
			return;
		}		
		dao.insert("td_m_solution", params);
	      StringBuilder  strScript = new StringBuilder("");
	      strScript.append("parent.document.getElementById('bquery').click();");
	      redirectToMsgByScript("�����ɹ���", strScript.toString());
	}
	
	/**
	 * querySoluBypk
	 */
	
	public void querySoluBypk(IRequestCycle cycle) throws Exception {
		this.init(cycle);
		PageData pd = getPageData();
       
		UtilDAO dao = new UtilDAO(pd);
		IData data = new DataMap();
		data.put("SOLU_ID", pd.getParameter("SOLU_ID"));

		IData info = dao.queryByPK("TD_M_SOLUTION", data); // ͨ����ȡ����ֵ����ѯ�����ݷ�������Դ(info)��

		this.setInfo(info);
		pd.setTransferData(pd.getData());

		if (info == null){
			common.error("��Ҫ�鿴�������Ѳ�����");}
	}
	/**
	 * updateSohu
	 */
	
	public void updateSohu(IRequestCycle cycle) throws Exception {
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
    	dao.update("TD_M_SOLUTION", edits);


      StringBuilder  strScript = new StringBuilder("");
      strScript.append("parent.document.getElementById('bquery').click();");
      redirectToMsgByScript("�޸ĳɹ���", strScript.toString());
	}
	
	public void delSohu(IRequestCycle cycle) throws Exception {
		
		PageData pd = getPageData();
		UtilDAO dao = new UtilDAO(pd);
		IData edits = pd.getData("edit", true);
		IData data = new DataMap();
		data.put("SOLU_ID", pd.getParameter("SOLU_ID"));
    	dao.delete("TD_M_SOLUTION", data);
 

	      StringBuilder  strScript = new StringBuilder("");
	      strScript.append("parent.document.getElementById('bquery').click();");
	      redirectToMsgByScript("ɾ���ɹ���", strScript.toString());
	}
}
