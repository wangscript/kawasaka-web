/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.home.bean;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppBean;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.dao.NewsHomeDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class NewsHomeBean extends AppBean{
	

	/**
	 * 查询新闻信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryNews(PageData pd,IData data, Pagination pagination) throws Exception {
		NewsHomeDao dao = new NewsHomeDao(pd);
		return dao.queryNews(pd, data, pagination);
	}
	
	public IDataset queryNewsList(PageData pd,IData data, Pagination pagination) throws Exception {
		NewsHomeDao dao = new NewsHomeDao(pd);
		return dao.queryNewsList(pd, data, pagination);
	}
	
	public IDataset queryLastNews(PageData pd,IData data, Pagination pagination) throws Exception {
		NewsHomeDao dao = new NewsHomeDao(pd);
		return dao.queryLastNews(pd, data, pagination);
	}
	
	public IDataset queryThumbNews(PageData pd,IData data, Pagination pagination) throws Exception {
		NewsHomeDao dao = new NewsHomeDao(pd);
		return dao.queryThumbNews(pd, data, pagination);
	}	
}
