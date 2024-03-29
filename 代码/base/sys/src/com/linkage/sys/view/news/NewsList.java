/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.view.news;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.sys.bean.news.NewsBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wuwl
 *
 */
public abstract class NewsList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private NewsBean newsBean = new NewsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		
	}
	/**
	 * 查询新闻信息
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void queryNews(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset items = newsBean.queryNews(pd, params, pd.getPagination());
		this.setInfos(items);
		this.setConditions(params);
	}
}
