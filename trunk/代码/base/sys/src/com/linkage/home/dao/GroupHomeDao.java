/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.home.dao;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wuwl
 *
 */
public class GroupHomeDao extends AppEntity{
	public GroupHomeDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public GroupHomeDao(PageData pd, String connName) throws Exception
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
	public IDataset queryGroup(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_group t  where (1 = 1) and group_class='分公司' ");
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;
	}
	
	public IDataset queryGroup2(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_group t  where (1 = 1) and group_class='部门' ");
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;
	}
	
	public IDataset queryGroupDetail(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_group t  where (1 = 1) ");
		parser.addSQL(" and GROUP_ID=:GROUP_ID ");
		parser.addSQL(" ORDER BY GROUP_ID ");

		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;
	}
	
	public IDataset queryGroupIT(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_group t  where (1 = 1) and group_class='分公司' and group_name like '%软件%' or group_name like '%计算机%'");
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
}
