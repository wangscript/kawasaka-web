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
import com.linkage.home.dao.ProductHomeDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class ProductHomeBean extends AppBean{
	

	/**
	 * 查询产品大类信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductClassLists(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductHomeDao dao = new ProductHomeDao(pd);
		return dao.queryProductClassLists(pd, data, pagination);
	}
	
	public IDataset queryProductTypeLists(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductHomeDao dao = new ProductHomeDao(pd);
		return dao.queryProductTypeLists(pd, data, pagination);
	}
	
	
	public IDataset queryProducts(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductHomeDao dao = new ProductHomeDao(pd);
		return dao.queryProducts(pd, data, pagination);
	}
	
	public IDataset queryLastNews(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductHomeDao dao = new ProductHomeDao(pd);
		return dao.queryLastNews(pd, data, pagination);
	}
	
	public IDataset queryThumbNews(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductHomeDao dao = new ProductHomeDao(pd);
		return dao.queryThumbNews(pd, data, pagination);
	}	
}
