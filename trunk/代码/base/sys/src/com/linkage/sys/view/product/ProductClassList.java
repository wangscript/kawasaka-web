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
public abstract class ProductClassList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * �����Ʒ��������ҵ�����Bean
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
		
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		this.setConditions(conditions);
	}
	/**
	 * ��ѯ��Ʒ������Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void queryProductClassList(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		params.put("ITEM_FLAG", "1");
		IDataset productClassList = productBean.queryProductClassLists(pd, params, pd.getPagination());
		for(int i=0;i<productClassList.size();i++){
			((IData)productClassList.getData(i)).put("TEST", "<h1>test</h1>");
		}
		this.setInfos(productClassList);
		this.init(cycle); 
	}

}
