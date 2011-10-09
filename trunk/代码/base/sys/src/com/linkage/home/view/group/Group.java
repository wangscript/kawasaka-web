package com.linkage.home.view.group;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.GroupHomeBean;
import com.linkage.home.bean.NewsHomeBean;
import com.linkage.sys.bean.news.NewsBean;


public abstract class Group extends AppSafePage {
	
	public abstract void setInfos(IDataset infos);
	public abstract void setDeps(IDataset deps);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			GroupHomeBean groupBean = new GroupHomeBean();
			IData params = new DataMap();
			params.clear();
			IDataset groups = groupBean.queryGroups(pd, params, pd.getPagination());
			IDataset deps = groupBean.queryGroup2(pd, params, pd.getPagination());
			setInfos(groups);
			setDeps(deps);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}