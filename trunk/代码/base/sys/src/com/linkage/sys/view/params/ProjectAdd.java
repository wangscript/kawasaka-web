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
import com.linkage.dbframework.jdbc.SQLParser;
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
		IDataset ProjectList = ProjectBean.queryProjctList(pd, params, pd
				.getPagination());
		IData ProjectL=null;
		if(ProjectList!=null && ProjectList.size()>0){
		 ProjectL = ProjectList.getData(0);}
		if (ProjectL != null && ProjectL.size() > 0) {
			ProjectL.put("PROJECT_NAME", params.getString("projectName"));
			this.setInfo(ProjectL);
		} else {
			this.setInfo(params);

		}

	}
	
	/**
	 * 新增参数
	 * addinparams
	 */
	public void addinparams(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();	
		UtilDAO dao = new UtilDAO(pd);
		String msg = "新增成功！";
		String Project_id =null;
		IData params = pd.getData("cond", true);

		String project_name = params.getString("PROJECT_NAME");
		String JXS = params.getString("JXS");
		String JPK = params.getString("JPK");
			if(project_name==null || project_name.equals("")){
				common.error("项目为空，不能直接新增参数，请先新增项目！");
				return;
			}
			Project_id = ProjectBean.queryProjctLists(pd, params, pd.getPagination()).getData(0).getString("PROJECT_ID");
			params.put("PROJECT_ID", Project_id);
			Boolean exist = this.ProjectBean.existsInparams(pd, params, null);
			if(exist){
				//common.error("确定需要修改参数！");
				 msg = "修改成功！";
				    this.jixu(JXS, JPK, project_name, Project_id, cycle);	
					dao.save("wt_d_inparam", params);
					redirectToMsg(msg);
					return;
			}
			this.jixu(JXS, JPK, project_name, Project_id, cycle);
			dao.insert("wt_d_inparam", params);
			redirectToMsg(msg);
		
	}

	
	/**
	 * 
	 * 级序机算
	 */
	
	 public void jixu (String jxs, String jpk,String projectname , String projectid ,IRequestCycle cycle) throws Exception {
		   PageData pd = this.getPageData();	
			UtilDAO dao = new UtilDAO(pd);
			IDataset params = new DatasetList();
			IData param = new DataMap();
			IData param2 = new DataMap();
			param2.put("PROJECT_ID", projectid);		
			SQLParser parser = new SQLParser(param2);
			parser.addSQL("delete from wt_b_jx  where  PROJECT_ID=:PROJECT_ID  ");
            dao.executeUpdates(parser);
			String [] jpkk = jpk.split(",");

		 for (int i=0; i<=Integer.parseInt(jxs)-1; i++){
			 
			 param.put("JXS", i+1);
			 param.put("JX", i+1);
			 param.put("JPK", jpkk[i]);
			 param.put("PROJECT_ID", projectid);
			 param.put("PROJECT_NAME", projectname);

			 dao.insert("wt_b_jx", param);
		 }		
	 }
	
	 /**
	  * 
	  * upinparams  
	  * 
	  */
	public void upinparams(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();	
		UtilDAO dao = new UtilDAO(pd);
		String msg = "修改成功！";
		
		IData params = pd.getData("cond", true);
		String project_name = params.getString("PROJECT_NAME");
//		if(project_name==null || project_name.equals("")){
//			common.error("项目为空，不能直接新增参数，请先新增项目！");
//			return;
//		}
		String Project_id = ProjectBean.queryProjctLists(pd, params, pd.getPagination()).getData(0).getString("PROJECT_ID");
		params.put("PROJECT_ID", Project_id);
		Boolean exist = this.ProjectBean.existsInparams(pd, params, null);
		if(!exist){
			common.error("该项目已参数已经被删除无法修改！");
			return;
		}		
		dao.save("wt_d_inparam", params);
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
