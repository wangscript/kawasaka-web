package com.linkage.sys.dao.group;

import com.linkage.component.PageData;
import com.linkage.sys.dao.common.CashierAppEntity;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.dao.common.CashierAppEntity;



public class GroupDao extends CashierAppEntity{
	public GroupDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public GroupDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	public IDataset queryGroupLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_group t  where (1 = 1) ");
		parser.addSQL(" and T.GROUP_ID=:GROUP_ID ");
		parser.addSQL(" and T.GROUP_CLASS = :GROUP_CLASS ");
		parser.addSQL(" and T.GROUP_NAME=:GROUP_NAME ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}

}
