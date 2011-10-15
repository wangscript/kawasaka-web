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
import com.linkage.component.util.Utility;
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProductTypeEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
//	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
//	public abstract void setHotels(IDataset hotels);
	
	/**
	 * 负责产品管理这块的业务操作Bean
	 */
	private ProductBean productBean = new ProductBean();
	/**
	 * 查询产品小类信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		params.put("ID",params.getString("classTypeId"));
		params.put("ITEM_FLAG", "1");
		IDataset productTypeLists = productBean.queryProductTypeLists(pd, params, pd.getPagination());
		if(productTypeLists!=null && productTypeLists.size() != 1){
			common.error("获取产品小类信息出错！");
		}
//		IData param = new DataMap();
		IData info =productTypeLists.getData(0);
		IData data = new DataMap();
		IData conditions = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset productclass = productBean.queryProductClassLists(pd, data, null);
		conditions.put("PRODUCTCLASS", productclass);
		this.setInfo(productTypeLists.getData(0));
		this.setConditions(conditions);
		pd.setTransfer("ID",params.getString("classTypeId"));
	}
	/**
	 * 更新产品大类信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void updateProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "更新产品小类成功！";
		IData params = pd.getData("cond", true);
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG", "1");
		String productclass = params.getString("PRODUCT_CLASS","");
		String producttype = params.getString("PRODUCT_TYPE","");
		Boolean exist = this.productBean.existsProductTypeListById(pd, params, null);
		if(exist){
			common.error("产品大类【"+Utility.getStaticValue(pd,"TD_M_PRODUCT_CLASS",new java.lang.String[]{"ITEM_FLAG","ID"},"PRODUCT_CLASS", new java.lang.String[]{"1",productclass})+"】中已经存在产品小类【"+producttype+"】,请重新输入！");
			return;
		}
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("产品小类更新失败！");
		}
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));

		if(!this.productBean.updateProductType(pd, params))
		{
			common.error("产品小类更新失败！");
			return;
		}
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
	/**
	 * 删除产品小类信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void deleteProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "删除产品小类成功！";
		IData params = new DataMap();
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG","0");//0表示失效
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("删除产品小类失败！");
		}
		
		if(!this.productBean.updateProductType(pd, params)){
			common.error("删除产品小类失败！");
			return;
		}
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
}
