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
import com.linkage.home.dao.TechnoHomeDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class TechnoHomeBean extends AppBean{
	

	/**
	 * 查询技术信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryTechnos(PageData pd,IData data, Pagination pagination) throws Exception {
		TechnoHomeDao dao = new TechnoHomeDao(pd);
		return dao.queryTechnos(pd, data, pagination);
	}
	
	public IDataset querySolutions(PageData pd,IData data, Pagination pagination) throws Exception {
		TechnoHomeDao dao = new TechnoHomeDao(pd);
		return dao.querySolutions(pd, data, pagination);
	}
	
	
	public IDataset queryNewsList(PageData pd,IData data, Pagination pagination) throws Exception {
		TechnoHomeDao dao = new TechnoHomeDao(pd);
		return dao.queryNewsList(pd, data, pagination);
	}
	
	public IDataset queryLastNews(PageData pd,IData data, Pagination pagination) throws Exception {
		TechnoHomeDao dao = new TechnoHomeDao(pd);
		return dao.queryLastNews(pd, data, pagination);
	}
	
	public IDataset queryThumbNews(PageData pd,IData data, Pagination pagination) throws Exception {
		TechnoHomeDao dao = new TechnoHomeDao(pd);
		return dao.queryThumbNews(pd, data, pagination);
	}	
}
