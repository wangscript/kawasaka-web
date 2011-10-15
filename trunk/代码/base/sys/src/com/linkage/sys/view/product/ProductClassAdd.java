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
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProductClassAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
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
		conditions.put("ITEM_FLAG", "1");
//		common.error(getPageData().getRequestContext().getServlet().getServletContext().getRealPath(""));
		this.setConditions(conditions);
	}
	/**
	 * 查询用户信息
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void addProductClass(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "新增产品大类成功！";
		IData params = pd.getData("cond", true);
		String productclass = params.getString("PRODUCT_CLASS");
		String group_id = params.getString("GROUP_ID");
		IData param = new DataMap();
		param.put("PRODUCT_CLASS", productclass);
		param.put("GROUP_ID", group_id);
		Boolean exist = this.productBean.existsProductClassList(pd, param, null);
		if(exist){
			common.error("部门【"+Utility.getStaticValue(pd,"TD_M_GROUP",new java.lang.String[]{"GROUP_CLASS","GROUP_ID"},"GROUP_NAME", new java.lang.String[]{"部门",group_id})+"】中产品大类【"+productclass+"】已存在，请重新输入！");
			return;
		}		   
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ITEM_FLAG", "1"); 
		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.productBean.addProductClassList(pd, dataset);
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
