package com.linkage.home.view.news;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.sys.bean.news.NewsBean;


public abstract class News extends AppSafePage {
	
	public abstract void setNotes(IDataset notes);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			NewsBean newsBean = new NewsBean();
			IData params = new DataMap();
			IDataset notes = newsBean.queryNews(pd, params, pd.getPagination());
			setNotes(notes);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}