package com.linkage.home.view.news;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.NewsHomeBean;
import com.linkage.sys.bean.news.NewsBean;


public abstract class News extends AppSafePage {
	
	public abstract void setHots(IDataset hots);
	public abstract void setKavasakas(IDataset kavasakas);
	public abstract void setHangyes(IDataset hangyes);
	public abstract void setNotes(IDataset notes);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			NewsHomeBean newsBean = new NewsHomeBean();
			IData params = new DataMap();
			//热点新闻
			params.clear();
			params.put("LIMIT", "5");
			params.put("NEW_CID", "11");
			IDataset hots = newsBean.queryNews(pd, params, pd.getPagination());
			setHots(hots);
			//川荣新闻
			params.clear();
			params.put("LIMIT", "5");
			params.put("NEW_CID", "12");
			IDataset kavasakas = newsBean.queryNews(pd, params, pd.getPagination());
			setKavasakas(kavasakas);
			//行业动态
			params.clear();
			params.put("LIMIT", "5");
			params.put("NEW_CID", "13");
			IDataset hangyes = newsBean.queryNews(pd, params, pd.getPagination());
			setHangyes(hangyes);			
			//公告
			params.clear();
			params.put("LIMIT", "5");
			params.put("NEW_CID", "14");
			IDataset notes = newsBean.queryNews(pd, params, pd.getPagination());
			setNotes(notes);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}