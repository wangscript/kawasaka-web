/**
 * 
 * @author:wull
 */
package com.linkage.sys.view.inparams;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.common.bean.util.UtilDAO;
import com.linkage.component.PageData;
import com.linkage.sys.bean.project.ProjectBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProjectAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setTypes(IData types);
	public abstract void setProductClasses(IDataset productClasses);
	
	/**
	 * 负责产品大类的业务操作Bean
	 */
	private ProjectBean ProjectBean = new ProjectBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("cond", true);
//		conditions.put("ITEM_FLAG", "1");
//		IData data = new DataMap();
//		data.put("ITEM_FLAG", "1");
//		IDataset productclass = productBean.queryProductClassLists(pd, data, null);
//		conditions.put("PRODUCTCLASS", productclass);
//		if(null!=pd.getParameter("productClass")&&!"".equals(pd.getParameter("productClass","")))
//			conditions.put("PRODUCT_CLASS", pd.getParameter("productClass",""));
		this.setConditions(conditions);
	}
	/**
	 * 查询用户信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void addProgect(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "新增项目成功！";
		IData params = pd.getData("cond", true);
		String ptoject_name = params.getString("PROJECT_NAME");
		IData param = new DataMap();
		param.put("PROJECT_NAME", ptoject_name);
		Boolean exist = this.ProjectBean.existsProject(pd, param, null);
		if(exist){
			common.error("项目名称已经存在,请请重新输入！");
			return;
		}		
		
		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.ProjectBean.addProject(pd, dataset);
		redirectToMsg(msg);
	}
	
	
	/**
	 * wt查询列表
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		//params.put("ITEM_FLAG", "1");

		IDataset ProjectList = ProjectBean.queryProjctLists(pd, params, pd.getPagination());
		this.setInfos(ProjectList); //	setProductClasses(productBean.queryProductClass(pd, params,null));
		}
	
	/**
	 * 
	 * 传递参数到下页
	 * transferName
	 */
	
	public void transferName(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		params.put("PROJECT_NAME", params.getString("projectName"));
		
		log.debug("-----------------------------");
		log.debug(params);
//log.debug(params.get(arg0)("projectName"));
		//IDataset ProjectList = ProjectBean.queryProjctLists(pd, params, pd.getPagination());
		this.setInfo(params); //	setProductClasses(productBean.queryProductClass(pd, params,null));
		}
	
	/**
	 * 新增参数
	 * addinparams
	 */
	public void addinparams(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		String msg = "新增成功！";
		
		IData params = pd.getData("cond", true);
		String project_name = params.getString("PROGECT_NAME");
		String Project_id = ProjectBean.queryProjctLists(pd, params, pd.getPagination()).getData(0).getString("PROJECT_ID");
		params.put("PROJECT_ID", Project_id);
		log.debug("kkkkkkkkkkkkkkkkkkkkk-------------------3");
		log.debug(ProjectBean.queryProjctLists(pd, params, pd.getPagination()));
		log.debug(Project_id);
		IData param = new DataMap();
		param.put("PROJECT_NAME", project_name);
		param.put("PROJECT_ID", Project_id);
		
		Boolean exist = this.ProjectBean.existsInparams(pd, param, null);
		if(exist){
			common.error("该项目已存在输入参数，如需重新计算请直接修改后，再进行计算！");
			return;
		}		
		log.debug("kkkkkkkkkkkkkkkkkkkkk-------------------2");
		log.debug(params);
		dao.insert("wt_d_inparam", params);
		redirectToMsg(msg);
	}

	
	/**
	 * 根据大类查询小类
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductTypes(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		String product_class = pd.getParameter("PRODUCT_CLASS","");
	
		if (product_class != null && !"".equals(product_class)) {
			IData types = new DataMap();
			IData params = new DataMap();
			params.clear();
			params.put("ITEM_FLAG", "1");
			params.put("PRODUCT_CLASS", product_class);
			//types.put("PRODUCTTYPE", productBean.queryProductTypes(pd, params,null));
			setTypes(types);
		}
		else setTypes(null);
		
	}
	
	
}
