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
import com.linkage.sys.bean.params.ParamsBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.bean.staff.StaffBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wull
 *
 */
public abstract class ProductTypeAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * �����Ʒ�����ҵ�����Bean
	 */
	private ProductBean productBean = new ProductBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("cond", true);
		conditions.put("ITEM_FLAG", "1");
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset productclass = productBean.queryProductClassLists(pd, data, null);
		conditions.put("PRODUCTCLASS", productclass);
		if(null!=pd.getParameter("productClass")&&!"".equals(pd.getParameter("productClass","")))
			conditions.put("PRODUCT_CLASS", pd.getParameter("productClass",""));
		this.setConditions(conditions);
	}
	/**
	 * ��ѯ�û���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void addProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "������ƷС��ɹ���";
		IData params = pd.getData("cond", true);
		String productclass = params.getString("PRODUCT_CLASS");
		String producttype = params.getString("PRODUCT_TYPE");
		IData param = new DataMap();
		param.put("PRODUCT_CLASS", productclass);
		param.put("PRODUCT_TYPE", producttype);
		Boolean exist = this.productBean.existsProductType(pd, param, null);
		if(exist){
			common.error("��Ʒ���ࡾ"+productclass+"�����Ѿ����ڲ�ƷС�ࡾ"+producttype+"��,���������룡");
			return;
		}		
		params.put("ITEM_FLAG", "1");
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));

		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.productBean.addProductType(pd, dataset);
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString()); 
	}
	
	
}
