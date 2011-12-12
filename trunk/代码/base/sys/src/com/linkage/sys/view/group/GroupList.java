package com.linkage.sys.view.group;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.sys.bean.group.GroupBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

public abstract class GroupList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("cond", true);

	}
	
	/**
	 * 负责产品管理这块的业务操作Bean
	 */
	private GroupBean groupBean = new GroupBean();
	
	/**
	 * 查询所有公司/部门
	 * @param cycle
	 * @throws Exception
	 */
	public void queryGroupList(IRequestCycle cycle) throws Exception {
	
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);

		IDataset GroupList = groupBean.queryGroupLists(pd, params, pd.getPagination());
		this.setInfos(GroupList); 
		//this.init(cycle); 
	}
}
