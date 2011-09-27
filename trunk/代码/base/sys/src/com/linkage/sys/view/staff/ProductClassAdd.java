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
public abstract class ProductClassAdd extends CashierBasePage{
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
		this.setConditions(conditions);
	}
	/**
	 * ��ѯ�û���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void addProductClass(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "������Ʒ����ɹ���";
		IData params = pd.getData("cond", true);
		String productclass = params.getString("PRODUCT_CLASS");
		IData param = new DataMap();
		param.put("PRODUCT_CLASS", productclass);
		Boolean exist = this.productBean.existsProductClassList(pd, param, null);
		if(exist){
			common.error("��Ʒ���ࡾ"+productclass+"���Ѵ��ڣ����������룡");
			return;
		}		   
		
		params.put("CLASSORDER", "".equals(params.getString("CLASSORDER", ""))?"0":params.getString("CLASSORDER"));
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ITEM_FLAG", "1"); 
		IDataset dataset = new DatasetList();
		dataset.add(params);
		this.productBean.addProductClassList(pd, dataset);
		//������Ŀ������ѯ����
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
