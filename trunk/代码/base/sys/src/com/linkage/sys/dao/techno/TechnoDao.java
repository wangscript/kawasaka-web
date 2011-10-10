package com.linkage.sys.dao.techno;

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



public class TechnoDao extends CashierAppEntity{
	public TechnoDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public TechnoDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	public IDataset queryTechnoLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select r.techno_class TECHNO_CLASS ,r.techno_id TECHNO_ID,r.techno_desc TECHNO_DESC ,e.num NUM from td_m_techno r LEFT JOIN (select techno_class techno_class ,ifnull(count(techno_class),0) NUM from td_m_solution t  group by t.techno_class ) e  on e.techno_class=r.techno_class");


		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	/**
	 * querySoluLists
	 */
	public IDataset querySoluLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_solution t ");

		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
}
