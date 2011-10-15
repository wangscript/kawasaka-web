/**
 * 
 * @author:wull
 */
package com.linkage.sys.view.product;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProductList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setTypes(IData types);
	public abstract void setProductClasses(IDataset productClasses);
	
	/**
	 * 负责产品管理这块的业务操作Bean
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
		IData data = new DataMap();
//		if(null!=pd.getParameter("PRODUCT_CLASS")&&!"".equals(pd.getParameter("PRODUCT_CLASS","").trim()))
//		{
//			conditions.put("PRODUCT_CLASS", pd.getParameter("PRODUCT_CLASS",""));
//			data.put("ITEM_FLAG", "1");
//			IDataset productclass = productBean.queryProductClassLists(pd, data, null);
//			conditions.put("PRODUCTCLASS", productclass);
//		}
		
		this.setConditions(conditions);
		
		//设置大类
		String group_id = conditions.getString("GROUP_ID","");
		if (group_id != null && !"".equals(group_id)) {
			IData types = new DataMap();
			IData params = new DataMap();
			params.put("GROUP_CLASS", "部门");
			params.put("ITEM_FLAG", "1");
			params.put("GROUP_ID", group_id);
			setProductClasses(productBean.queryProductClass(pd, params,null));
		}
		
//		//设置小类
		String product_class=conditions.getString("PRODUCT_CLASS","");
		if (product_class != null && !"".equals(product_class)) {
			IData tmpparams = new DataMap();
			tmpparams.put("ITEM_FLAG", "1");
			tmpparams.put("PRODUCT_CLASS", product_class);
			IData types = new DataMap();
			
			types.put("PRODUCTTYPE", productBean.queryProductTypes(pd, tmpparams,null));
			setTypes(types);
		}
	}
	/**
	 * 查询产品信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		params.put("ITEM_FLAG", "1");
		IDataset productTypeList = productBean.queryProductLists(pd, params, pd.getPagination());
		this.setInfos(productTypeList); 
		this.init(cycle); 
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
			IData params = new DataMap();
			params.put("ITEM_FLAG", "1");
			params.put("PRODUCT_CLASS", product_class);
			IData types = new DataMap();
			types.put("PRODUCTTYPE", productBean.queryProductTypes(pd, params,null));
			setTypes(types);
		}
		else setTypes(null);
	}

}
