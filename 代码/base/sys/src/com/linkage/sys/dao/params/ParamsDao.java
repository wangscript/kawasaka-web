/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.dao.params;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author chezg
 *
 */
public class ParamsDao extends CashierAppEntity{
	public ParamsDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public ParamsDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}

	/**
	 * 查询部门信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-15
	 */
	public IDataset queryDeparts(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_depart_config t  where (1 = 1) ");
		parser.addSQL(" and DEPART_CODE=:DEPART_CODE ");
		parser.addSQL(" and DEPART_NAME like '%' ||:DEPART_NAME||'%' ");
		parser.addSQL(" and DEPART_DESC like '%' ||:DEPART_DESC||'%' ");
		parser.addSQL(" and ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY DEPART_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增部门参数信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-15
	 */
	public void addDeparts(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_DEPART_CONFIG", dataset);
	}
	/**
	 * 修改项目信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-15
	 */
	public boolean updateDeparts(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_DEPART_CONFIG", data, keys);
	}
	/**
	 * 查询部门项目参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public IDataset queryDepartsItems(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select a.*, b.DEPART_NAME, c.BUSI_ITEM_NAME from TD_M_DEPART_ITEM_CONFIG a, TD_M_DEPART_CONFIG b, TD_M_ITEM_CONFIG c  where (1 = 1) ");
		parser.addSQL(" and a.DEPART_CODE=b.DEPART_CODE ");
		parser.addSQL(" and a.BUSI_ITEM_CODE=c.BUSI_ITEM_CODE ");
		parser.addSQL(" and a.DEPART_CODE=:DEPART_CODE ");
		parser.addSQL(" and a.BUSI_ITEM_CODE=:BUSI_ITEM_CODE ");
		parser.addSQL(" and a.DEPART_ITEM_DESC like '%' ||:DEPART_ITEM_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.DEPART_CODE, a.BUSI_ITEM_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增部门项目参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void addDepartsItems(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_DEPART_ITEM_CONFIG", dataset);
	}
	/**
	 * 修改部门项目参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public boolean updateDepartsItems(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_DEPART_ITEM_CONFIG", data, keys);
	}

	/**
	 * 查询业务区参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public IDataset queryAreas(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_AREA_CONFIG t  where (1 = 1) ");
		parser.addSQL(" and AREA_CODE=:AREA_CODE ");
		parser.addSQL(" and AREA_NAME like '%' ||:AREA_NAME||'%' ");
		parser.addSQL(" and AREA_DESC like '%' ||:AREA_DESC||'%' ");
		parser.addSQL(" and ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY AREA_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增业务区参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addAreas(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_AREA_CONFIG", dataset);
	}
	/**
	 * 修改业务区参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public boolean updateAreas(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_AREA_CONFIG", data, keys);
	}

}
