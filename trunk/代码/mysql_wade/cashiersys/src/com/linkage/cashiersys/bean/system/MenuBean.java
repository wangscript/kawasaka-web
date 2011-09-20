/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.bean.system;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.common.CashierBaseBean;
import com.linkage.cashiersys.dao.staff.StaffDao;
import com.linkage.cashiersys.dao.system.MenuDao;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;

/**
 * @author chenzg
 * 
 */
public class MenuBean extends CashierBaseBean{
	/**
	 * 查询用户信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public IDataset queryMenus(PageData pd,IData data, Pagination pagination) throws Exception {
		MenuDao dao = new MenuDao(pd);
		return dao.queryMenus(pd, data, pagination);
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
	public IDataset queryParentMenus(PageData pd, Pagination pagination) throws Exception {
		MenuDao dao = new MenuDao(pd);
		IData data = new DataMap();
		data.put("MENU_TYPE", "1");
		data.put("FLAG", "1");
		return dao.queryParentMenus(pd, data, pagination);
	}
	/**
	 * 新增菜单信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addMenus(PageData pd,IDataset dataset) throws Exception {
		MenuDao dao = new MenuDao(pd);
		dao.addMenus(pd, dataset);
	}
	/**
	 * 修改菜单信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void updateMenus(PageData pd,IData data) throws Exception {
		MenuDao dao = new MenuDao(pd);
		dao.updateMenus(pd, data, new String[]{"MENU_CODE"});
	}
}
