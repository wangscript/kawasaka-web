/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.bean.news;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class NewsBean extends CashierBaseBean{
	
	public IDataset queryDiscountType(PageData pd) throws Exception {
		NewsDao dao = new NewsDao(pd);
		return dao.queryList("Select * From td_s_static t Where t.type_id = 'DISCOUNT_TYPES' And t.valid_flag='1'");
	}

	
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
		NewsDao dao = new NewsDao(pd);
		return dao.queryNews(pd, data, pagination);
	}
	/**
	 * 修改新闻信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-13
	 */
	public void updateNews(PageData pd,IData data) throws Exception {
		NewsDao dao = new NewsDao(pd);
		dao.updateNews(pd, data, new String[]{"NEW_ID"});
	}
	/**
	 * 新增新闻参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public void addNews(PageData pd,IDataset dataset) throws Exception {
		NewsDao dao = new NewsDao(pd);
		dao.addNews(pd, dataset);
	}




}
