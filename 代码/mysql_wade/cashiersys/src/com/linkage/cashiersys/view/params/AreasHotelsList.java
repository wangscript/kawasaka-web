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
public abstract class AreasHotelsList extends CashierBasePage{
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
		IDataset hotels = this.paramsBean.queryHotels(pd, data, null);
		IDataset areas = this.paramsBean.queryAreas(pd, data, null);
		conditions.put("HOTELS", hotels);
		conditions.put("AREAS", areas);
		this.setConditions(conditions);
	}
	/**
	 * ��ѯ�Ƶ���Ŀ����
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void queryAreasHotels(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset areasHotels = paramsBean.queryAreasHotels(pd, params, pd.getPagination());
		
		//ҳ���������
		this.setInfos(areasHotels);
		this.init(cycle);
	}
}
