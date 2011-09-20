/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.bean.system;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.common.CashierBaseBean;
import com.linkage.cashiersys.dao.system.MenuDao;
import com.linkage.cashiersys.dao.system.RightsDao;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;

/**
 * @author chenzg
 * 
 */
public class RightsBean extends CashierBaseBean{
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
	public IDataset queryRights(PageData pd,IData data, Pagination pagination) throws Exception {
		RightsDao dao = new RightsDao(pd);
		return dao.queryRights(pd, data, pagination);
	}
	/**
	 * 新增权限信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addRights(PageData pd,IDataset dataset) throws Exception {
		RightsDao dao = new RightsDao(pd);
		dao.addRights(pd, dataset);
	}
	/**
	 * 修改权限信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void updateRights(PageData pd,IData data) throws Exception {
		RightsDao dao = new RightsDao(pd);
		dao.updateRights(pd, data, new String[]{"RIGHT_CODE"});
	}
	
	public IDataset queryMenusRights(PageData pd,IData data) throws Exception {
		RightsDao dao = new RightsDao(pd);
		return dao.queryMenusRights(pd, data);
	}
	public IDataset queryAllChildMenus(PageData pd) throws Exception {
		RightsDao dao = new RightsDao(pd);
		IData data = pd.getData();
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("Select menu_code,menu_name From td_s_menu m Where m.parent_menu_code Is Not Null and flag='1'");
		return dao.queryList(parser);
	}
	public void updateMenusRights(PageData pd, IData data, String[] selectedItems) throws Exception {
		RightsDao dao = new RightsDao(pd);
		dao.delete("TD_S_MENU_RIGHT_CONFIG", data, new String[]{"RIGHT_CODE"});
		IDataset dataset = new DatasetList();
		for(String s:selectedItems){
			if(!"".equals(s)){
				IData idata = new DataMap();
				idata.put("MENU_ID", s);
				idata.put("RIGHT_CODE", data.getString("RIGHT_CODE"));
				idata.put("UPDATE_STAFF_ID", data.getString("UPDATE_STAFF_ID"));
				idata.put("UPDATE_TIME", data.getString("UPDATE_TIME"));
				dataset.add(idata);
			}
		}
		dao.insertMenusRights(pd, dataset);	
	}	
}
