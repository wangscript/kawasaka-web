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
public abstract class ProductTypeList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
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
		if(null!=pd.getParameter("PRODUCT_CLASS")&&!"".equals(pd.getParameter("PRODUCT_CLASS","").trim()))
				conditions.put("PRODUCT_CLASS", pd.getParameter("PRODUCT_CLASS",""));
		data.put("ITEM_FLAG", "1");
		IDataset productclass = productBean.queryProductClassLists(pd, data, null);
//		for(int i=0;i<productclass.size();i++)
//			productclass.getData(i).put("PRODUCT_CLASS_ID", productclass.getData(i).getString("PRODUCT_CLASS","PRODUCT_CLASS"));
		conditions.put("PRODUCTCLASS", productclass);
		this.setConditions(conditions); 
		if(null!=pd.getParameter("PRODUCT_CLASS")&&!"".equals(pd.getParameter("PRODUCT_CLASS","").trim()))
		{
			data.put("PRODUCT_CLASS", pd.getParameter("PRODUCT_CLASS",""));
			IDataset productTypeList = productBean.queryProductTypeLists(pd, data, pd.getPagination());
			this.setInfos(productTypeList);
		}
	}
	/**
	 * 查询产品大类信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductTypeList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		params.put("ITEM_FLAG", "1");
		IDataset productTypeList = productBean.queryProductTypeLists(pd, params, pd.getPagination());
		this.setInfos(productTypeList); 
		this.init(cycle); 
	}

}
