/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.dao.system;

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
public class MenuDao extends CashierAppEntity{
	public MenuDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public MenuDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	/**
	 * 查询菜单信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public IDataset queryMenus(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_S_MENU t  where (1 = 1) ");
		parser.addSQL(" and MENU_CODE=:MENU_CODE ");
		parser.addSQL(" and MENU_NAME like concat('%', :MENU_NAME,'%') ");
		parser.addSQL(" and PARENT_MENU_CODE=:PARENT_MENU_CODE ");
		parser.addSQL(" and MENU_URL like concat('%' ,:MENU_URL,'%') ");
		parser.addSQL(" and MENU_DESC like concat('%', :MENU_DESC,'%') ");
		parser.addSQL(" and MENU_TYPE=:MENU_TYPE ");
		parser.addSQL(" and FLAG=:FLAG ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY MENU_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 查询所有的父菜单
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-22
	 */
	public IDataset queryParentMenus(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_S_MENU t  where (1 = 1) ");
		parser.addSQL(" and MENU_TYPE=:MENU_TYPE ");
		parser.addSQL(" and FLAG=:FLAG ");
		parser.addSQL(" ORDER BY MENU_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增菜单信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addMenus(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_MENU", dataset);		
	}
	/**
	 * 修改菜单信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public boolean updateMenus(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_MENU", data, keys);
	}
}
