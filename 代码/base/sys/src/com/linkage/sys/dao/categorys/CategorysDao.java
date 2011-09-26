/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.dao.categorys;

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
public class CategorysDao extends CashierAppEntity{
	public CategorysDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public CategorysDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}

	/**
	 * 查询节点信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryRoots(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.DATA_ID, t.DATA_NAME, t.PDATA_ID, t.VALID_FLAG from td_s_static t  where (1 = 1) ");
		parser.addSQL(" and `TYPE_ID` = 'CATEGORY_LIST' ");
		parser.addSQL(" and `PDATA_ID` IS NULL");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}

	public IDataset queryNodes(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.DATA_ID, t.DATA_NAME, t.PDATA_ID, t.VALID_FLAG from td_s_static t  where (1 = 1) ");
		parser.addSQL(" and `TYPE_ID` = 'CATEGORY_LIST' ");
		parser.addSQL(" and `PDATA_ID` = :PID");
		parser.addSQL(" ORDER BY `t`.`DATA_ID` ASC ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
}
