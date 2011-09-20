/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.dao.system;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.dao.common.CashierAppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;

/**
 * @author chezg
 *
 */
public class RightsDao extends CashierAppEntity{
	public RightsDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public RightsDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	/**
	 * 查询权限信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public IDataset queryRights(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_S_RIGHT t  where (1 = 1) ");
		parser.addSQL(" and RIGHT_CODE=:RIGHT_CODE ");
		parser.addSQL(" and RIGHT_NAME like '%' ||:RIGHT_NAME||'%' ");
		parser.addSQL(" and RIGHT_DESC like '%' ||:RIGHT_DESC||'%' ");
		parser.addSQL(" and FLAG=:FLAG ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY RIGHT_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增权限信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addRights(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_RIGHT", dataset);		
	}
	/**
	 * 修改权限信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public boolean updateRights(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_RIGHT", data, keys);
	}

	public IDataset queryMenusRights(PageData pd, IData data) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("Select a.menu_code,a.menu_name From td_s_menu a,td_s_menu_right_config b where (1 = 1) and a.menu_code = b.menu_id");
		parser.addSQL(" and RIGHT_CODE=:RIGHT_CODE ");
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;
	}


	public void insertMenusRights(PageData pd, IDataset dataset) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_MENU_RIGHT_CONFIG", dataset);
	}
}
