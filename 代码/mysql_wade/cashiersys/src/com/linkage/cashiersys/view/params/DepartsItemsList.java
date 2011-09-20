/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.params;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.params.ParamsBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class DepartsItemsList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * ������Ŀ������������ҵ�����Bean
	 */
	private ParamsBean paramsBean = new ParamsBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = pd.getData("cond", true);
		
		//ҳ���������
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset deaprts = this.paramsBean.queryDeparts(pd, data, null);
		IDataset items = this.paramsBean.queryItems(pd, data, null);
		conditions.put("DEPARTS", deaprts);
		conditions.put("ITEMS", items);
		this.setConditions(conditions);
	}
	/**
	 * ��ѯ�û���Ϣ
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void queryDepartsItems(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset items = paramsBean.queryDepartsItems(pd, params, pd.getPagination());
		
		//ҳ���������
		this.setInfos(items);
		this.init(cycle);
	}
}
