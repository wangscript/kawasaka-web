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
public abstract class HotelsItemsList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private ParamsBean paramsBean = new ParamsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = pd.getData("cond", true);
		
		//页面参数返回
		IData data = new DataMap();
		data.put("ITEM_FLAG", "1");
		IDataset hotels = this.paramsBean.queryHotels(pd, data, null);
		IDataset items = this.paramsBean.queryItems(pd, data, null);
		conditions.put("HOTELS", hotels);
		conditions.put("ITEMS", items);
		this.setConditions(conditions);
	}
	/**
	 * 查询酒店项目参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void queryHotelsItems(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset items = paramsBean.queryHotelsItems(pd, params, pd.getPagination());
		
		//页面参数返回
		this.setInfos(items);
		this.init(cycle);
	}
}
