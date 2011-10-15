/**
 * 
 * @author:wull
 */
package com.linkage.sys.view.product;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProductAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setTypes(IData types);
	public abstract void setProductClasses(IDataset productClasses);
	
	/**
	 * 负责产品大类的业务操作Bean
	 */
	private ProductBean productBean = new ProductBean();
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
	public void addProduct(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "新增产品成功！";
		IData params = pd.getData("cond", true);
		String product_name = params.getString("PRODUCT_NAME");
		IData param = new DataMap();
		param.put("PRODUCT_NAME", product_name);
		Boolean exist = this.productBean.existsProduct(pd, param, null);
		if(exist){
			common.error("产品名称已经存在,请请重新输入！");
			return;
		}		
		params.put("ITEM_FLAG", "1");
		
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		if("".equals(param.getString("PRODUCT_TYPE","")))
		params.put("PRODUCT_TYPE","0");
		
		params.put("PRIVILEGE_PRICE", "".equals(params.getString("PRIVILEGE_PRICE",""))?null:params.getString("PRIVILEGE_PRICE"));
		params.put("RETAIL_PRICE", "".equals(params.getString("RETAIL_PRICE",""))?null:params.getString("RETAIL_PRICE"));
		params.put("VIP_PRICE", "".equals(params.getString("VIP_PRICE",""))?null:params.getString("VIP_PRICE"));

		
		
		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.productBean.addProduct(pd, dataset);
		redirectToMsg(msg);
	}
	
	
	/**
	 * 根据部门查询大类
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductClass(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		String group_id = pd.getParameter("GROUP_ID","");
		if (group_id != null && !"".equals(group_id)) {
			IData types = new DataMap();
			IData params = new DataMap();
			params.put("GROUP_CLASS", "部门");
			params.put("ITEM_FLAG", "1");
			params.put("GROUP_ID", group_id);
			setProductClasses(productBean.queryProductClass(pd, params,null));
		}
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
			types.put("PRODUCTTYPE", productBean.queryProductTypes(pd, params,null));
			setTypes(types);
		}
		else setTypes(null);
		
	}
	
	
}
