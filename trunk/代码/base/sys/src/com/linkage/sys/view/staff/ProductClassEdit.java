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
public abstract class ProductClassEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
//	public abstract void setInfos(IDataset infos);
//	public abstract void setConditions(IData conditions);
//	public abstract void setHotels(IDataset hotels);
	
	/**
	 * �����Ʒ��������ҵ�����Bean
	 */
	private ProductBean productBean = new ProductBean();
	/**
	 * ��ѯ��Ʒ������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductClassList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		params.put("ID",params.getString("classId"));
		params.put("ITEM_FLAG", "1");
		IDataset productClassLists = productBean.queryProductClassLists(pd, params, pd.getPagination());
		if(productClassLists!=null && productClassLists.size() != 1){
			common.error("��ȡ��Ʒ������Ϣ����");
		}
		IData param = new DataMap();
		this.setInfo(productClassLists.getData(0));
		
		pd.setTransfer("ID",params.getString("classId"));
	}
	/**
	 * ���²�Ʒ������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void updateProductClassList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "���²�Ʒ����ɹ���";
		IData params = pd.getData("cond", true);
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG", "1");
		String productclass = params.getString("PRODUCT_CLASS","");
		Boolean exist = this.productBean.existsProductClassListById(pd, params, null);
		if(exist){
			common.error("��Ʒ���ࡾ"+productclass+"���Ѵ��ڣ����������룡");
			return;
		}
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("��Ʒ���ࡾ"+productclass+"������ʧ�ܣ�");
		}
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));
		if(!this.productBean.updateProductClassList(pd, params))
		{
			common.error("��Ʒ���ࡾ"+productclass+"������ʧ�ܣ�");
			return;
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
	/**
	 * ɾ����Ʒ������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void deleteProductClass(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "ɾ����Ʒ����ɹ���";
		IData params = new DataMap();
		params.put("ID",pd.getParameter("ID",""));
		params.put("ITEM_FLAG","0");
		if(null==pd.getParameter("ID","")||"".equals(pd.getParameter("ID","")))
		{
			common.error("ɾ����Ʒ����ʧ�ܣ�");
		}
		
		if(!this.productBean.updateProductClassList(pd, params)){
			common.error("ɾ����Ʒ����ʧ�ܣ�");
			return;
		}
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
	
}
