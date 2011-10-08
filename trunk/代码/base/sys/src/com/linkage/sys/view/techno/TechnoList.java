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
	 * 负责产品管理这块的业务操作Bean
	 */
	private TechnoBean technoBean = new TechnoBean();
	/**
	 * 页面初始化参数
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
	 * 查询所有技术大类
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
	 * 增加大类信息
	 */
	
	public void addTechno(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		String msg = "新增成功！";
		IData params = pd.getData("add", true);
		String TECHNO_CLASS = params.getString("TECHNO_CLASS");
		IData param = new DataMap();
		param.put("TECHNO_CLASS", TECHNO_CLASS);
		Boolean exist = this.technoBean.existsTechno(pd, param, null);
		if(exist){
			common.error("产品名称已经存在,请请重新输入！");
			return;
		}		
		dao.insert("TD_M_TECHNO", params);
		redirectToMsg(msg);
	}
	
/**
 * 查询技术方案列表
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
	 * 增加大类信息
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
			common.error("方案名称已经存在,请请重新输入！");
			return;
		}		
		dao.insert("td_m_solution", params);
	      StringBuilder  strScript = new StringBuilder("");
	      strScript.append("parent.document.getElementById('bquery').click();");
	      redirectToMsgByScript("新增成功！", strScript.toString());
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

		IData info = dao.queryByPK("TD_M_SOLUTION", data); // 通过获取主键值将查询的数据放入数据源(info)中

		this.setInfo(info);
		pd.setTransferData(pd.getData());

		if (info == null){
			common.error("您要查看的数据已不存在");}
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
//			common.error("产品大类【"+edits.getString("TECHNO_CLASS")+"】已经存在请重新输入！");
//			return;
//		}
    	dao.update("TD_M_SOLUTION", edits);


      StringBuilder  strScript = new StringBuilder("");
      strScript.append("parent.document.getElementById('bquery').click();");
      redirectToMsgByScript("修改成功！", strScript.toString());
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
	      redirectToMsgByScript("删除成功！", strScript.toString());
	}
}
