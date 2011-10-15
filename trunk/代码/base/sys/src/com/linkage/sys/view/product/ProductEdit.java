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
public abstract class ProductEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
//	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setTypes(IData types);
	public abstract void setProductClasses(IDataset productClasses);
	
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
	public void queryProduct(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		params.put("PRODUCT_ID",params.getString("productId",""));
		params.put("ITEM_FLAG", "1");
		IDataset productTypeLists = productBean.queryProductLists(pd, params, pd.getPagination());
		if(productTypeLists!=null && productTypeLists.size() != 1){
			common.error("��ȡ��Ʒ��Ϣ����");
		}
//		IData param = new DataMap();
		IData info =productTypeLists.getData(0);
		IData data = new DataMap();
		IData conditions = new DataMap();
		conditions.putAll(productTypeLists.getData(0));
		data.put("ITEM_FLAG", "1");
		//���ô���
		IDataset productclass = productBean.queryProductClassLists(pd, data, null);
		conditions.put("PRODUCTCLASS", productclass);
		
		

		//���ô���
		String group_id = productTypeLists.getData(0).getString("GROUP_ID","");
		if (group_id != null && !"".equals(group_id)) {
			IData types = new DataMap();
			IData param = new DataMap();
			param.put("GROUP_CLASS", "����");
			param.put("ITEM_FLAG", "1");
			param.put("GROUP_ID", group_id);
			setProductClasses(productBean.queryProductClass(pd, param,null));
		}
		
		//����С��
		String product_class=productTypeLists.getData(0).getString("PRODUCT_CLASS","");
		if (product_class != null && !"".equals(product_class)) {
			IData tmpparams = new DataMap();
			tmpparams.put("ITEM_FLAG", "1");
			tmpparams.put("PRODUCT_CLASS", product_class);
			IData types = new DataMap();
			
			types.put("PRODUCTTYPE", productBean.queryProductTypes(pd, tmpparams,null));
			setTypes(types);
		}
		this.setConditions(conditions);
//		pd.setTransfer("PRODUCT_ID",params.getString("productId",""));
	}
	/**
	 * ���²�Ʒ��Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void updateProduct(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "���²�Ʒ�ɹ���";
		IData params = pd.getData("cond", true);
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		params.put("ITEM_FLAG", "1");
		String product_name = params.getString("PRODUCT_NAME","");
		Boolean exist = this.productBean.existsProductById(pd, params, null);
		if(exist){
			common.error("��Ʒ���ơ�"+product_name+"���Ѵ��ڣ����������룡");
			return;
		}
		
		params.put("PRIVILEGE_PRICE", "".equals(params.getString("PRIVILEGE_PRICE",""))?null:params.getString("PRIVILEGE_PRICE"));
		params.put("RETAIL_PRICE", "".equals(params.getString("RETAIL_PRICE",""))?null:params.getString("RETAIL_PRICE"));
		params.put("VIP_PRICE", "".equals(params.getString("VIP_PRICE",""))?null:params.getString("VIP_PRICE"));

		if("".equals(params.getString("PRODUCT_TYPE","")))
			params.put("PRODUCT_TYPE","0");
		
		if(!this.productBean.updateProduct(pd, params))
		{
			common.error("��Ʒ����ʧ�ܣ�");
			return;
		}
		redirectToMsg(msg);
	}
	
	/**
	 * ɾ����Ʒ��Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void deleteProduct(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		String msg = "ɾ����Ʒ�ɹ���";
		IData params = new DataMap();
		params.put("ITEM_FLAG","0");//0��ʾʧЧ
		params.put("PRODUCT_ID",pd.getData("cond", true).getString("PRODUCT_ID",""));
		if(!this.productBean.updateProduct(pd, params)){
			common.error("ɾ����Ʒʧ�ܣ�");
			return;
		}
		redirectToMsg(msg);
	}
	
	/**
	 * ���ݲ��Ų�ѯ����
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
			params.put("GROUP_CLASS", "����");
			params.put("ITEM_FLAG", "1");
			params.put("GROUP_ID", group_id);
			setProductClasses(productBean.queryProductClass(pd, params,null));
		}
	}
	
	/**
	 * ���ݴ����ѯС��
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
