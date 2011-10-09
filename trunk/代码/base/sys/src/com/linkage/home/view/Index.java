package com.linkage.home.view;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.NewsHomeBean;


public abstract class Index extends AppSafePage {
	
	public abstract void setNotes(IDataset notes);
	public abstract void setThumbs(IDataset thumbs);
	public abstract void setLastnewss(IDataset lastnewss);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			NewsHomeBean newsBean = new NewsHomeBean();
			IData params = new DataMap();
			//公告
			params.clear();
			params.put("LIMIT", "3");
			params.put("NEW_CID", "14");
			IDataset notes = newsBean.queryNews(pd, params, pd.getPagination());
			setNotes(notes);
			//焦点新闻
			params.clear();
			params.put("LIMIT", "4");
			IDataset thumbs = newsBean.queryThumbNews(pd, params, pd.getPagination());
			setThumbs(thumbs);
			//最新新闻动态
			params.clear();
			params.put("LIMIT", "10");
			IDataset lastnewss = newsBean.queryLastNews(pd, params, pd.getPagination());
			setLastnewss(lastnewss);			
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}