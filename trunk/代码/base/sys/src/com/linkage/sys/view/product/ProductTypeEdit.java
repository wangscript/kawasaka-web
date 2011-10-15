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
	 * �����Ʒ��������ҵ�����Bean
	 */
	private ProductBean productBean = new ProductBean();
	/**
	 * ��ѯ��ƷС����Ϣ
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
			common.error("��ȡ��ƷС����Ϣ����");
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
	 * ���²�Ʒ������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void updateProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "���²�ƷС��ɹ���";
		IData params = pd.getData("cond", true);
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG", "1");
		String productclass = params.getString("PRODUCT_CLASS","");
		String producttype = params.getString("PRODUCT_TYPE","");
		Boolean exist = this.productBean.existsProductTypeListById(pd, params, null);
		if(exist){
			common.error("��Ʒ���ࡾ"+Utility.getStaticValue(pd,"TD_M_PRODUCT_CLASS",new java.lang.String[]{"ITEM_FLAG","ID"},"PRODUCT_CLASS", new java.lang.String[]{"1",productclass})+"�����Ѿ����ڲ�ƷС�ࡾ"+producttype+"��,���������룡");
			return;
		}
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("��ƷС�����ʧ�ܣ�");
		}
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));

		if(!this.productBean.updateProductType(pd, params))
		{
			common.error("��ƷС�����ʧ�ܣ�");
			return;
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
	/**
	 * ɾ����ƷС����Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void deleteProductType(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "ɾ����ƷС��ɹ���";
		IData params = new DataMap();
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG","0");//0��ʾʧЧ
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("ɾ����ƷС��ʧ�ܣ�");
		}
		
		if(!this.productBean.updateProductType(pd, params)){
			common.error("ɾ����ƷС��ʧ�ܣ�");
			return;
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
}
