/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.dao.news;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wuwl
 *
 */
public class NewsDao extends CashierAppEntity{
	public NewsDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public NewsDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
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
	public IDataset queryNews(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from tf_f_news t  where (1 = 1) ");
		parser.addSQL(" and NEW_ID=:NEW_ID ");
		parser.addSQL(" and NEW_FLAG=:NEW_FLAG ");
		parser.addSQL(" and NEW_TITLE like '%' ||:NEW_TITLE||'%' ");
		parser.addSQL(" and NEW_CID=:NEW_CID ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY NEW_ID ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增新闻参数信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public void addNews(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("tf_f_news", dataset);
	}
	/**
	 * 修改项目信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public boolean updateNews(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("tf_f_news", data, keys);
	}
}
