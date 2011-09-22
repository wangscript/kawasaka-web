/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.dao.staff;

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
public class StaffDao extends CashierAppEntity{
	public StaffDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public StaffDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	/**
	 * 查询用户信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public IDataset queryStaff(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_S_STAFF t  where (1 = 1) ");
		parser.addSQL(" and t.staff_id=:STAFF_ID ");
		parser.addSQL(" and t.staff_name like '%' ||:STAFF_NAME||'%' ");
		parser.addSQL(" and t.update_time=:UPDATE_TIME ");
		parser.addSQL(" and t.DEPART_CODE=:DEPART_CODE ");
		parser.addSQL(" and t.AREA_CODE=:AREA_CODE ");
		parser.addSQL(" and t.HOTEL_CODE=:HOTEL_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增员工信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-19
	 */
	public void addStaffs(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_STAFF", dataset);		
	}
	/**
	 * 更新员工信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-19
	 */
	public boolean updateStaffs(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_STAFF", data, keys);
	}
	/**
	 * 修改用户密码
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public int updateStaffPasswd(PageData pd, IData data) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		String sql = "UPDATE td_s_staff t SET t.staff_pwd=:NEW_STAFF_PWD1 "
					+ " WHERE t.staff_id=:STAFF_ID "
					+ " AND t.staff_pwd=:OLD_STAFF_PWD ";
		return dao.executeUpdate(sql, data);
	}
	/**
	 * 重置密码
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public int resetStaffPasswd(PageData pd, IData data) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		String sql = "UPDATE td_s_staff t SET t.staff_pwd='123456' "
					+ " WHERE t.staff_id=:STAFF_ID ";
		return dao.executeUpdate(sql, data);
	}
	/**
	 * 查询员工菜单信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public IDataset queryStaffMenu(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select a.*, b.STAFF_NAME, c.MENU_NAME from TD_S_STAFF_MENU_CONFIG a, TD_S_STAFF b, TD_S_MENU c  where (1 = 1) ");
		parser.addSQL(" and a.STAFF_ID=b.STAFF_ID ");
		parser.addSQL(" and a.MENU_CODE=c.MENU_CODE ");
		parser.addSQL(" and a.STAFF_ID=:STAFF_ID ");
		parser.addSQL(" and a.MENU_CODE=:MENU_CODE ");
		parser.addSQL(" and a.MENU_DESC like '%' ||:MENU_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.STAFF_ID, a.MENU_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增员工菜单信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addStaffMenu(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_STAFF_MENU_CONFIG", dataset);
	}
	/**
	 * 修改员工菜单信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public boolean updateStaffMenu(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_STAFF_MENU_CONFIG", data, keys);
	}
	/**
	 * 查询员工权限信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public IDataset queryStaffRight(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select a.*, b.STAFF_NAME, c.RIGHT_NAME from TD_S_STAFF_RIGHT_CONFIG a, TD_S_STAFF b, TD_S_RIGHT c  where (1 = 1) ");
		parser.addSQL(" and a.STAFF_ID=b.STAFF_ID ");
		parser.addSQL(" and a.RIGHT_CODE=c.RIGHT_CODE ");
		parser.addSQL(" and a.STAFF_ID=:STAFF_ID ");
		parser.addSQL(" and a.RIGHT_CODE=:RIGHT_CODE ");
		parser.addSQL(" and a.STAFF_RIGHT_DESC like '%' ||:STAFF_RIGHT_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.STAFF_ID, a.RIGHT_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增员工权限信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addStaffRight(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_S_STAFF_RIGHT_CONFIG", dataset);
	}
	/**
	 * 修改员工权限信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public boolean updateStaffRight(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_STAFF_RIGHT_CONFIG", data, keys);
	}
}
