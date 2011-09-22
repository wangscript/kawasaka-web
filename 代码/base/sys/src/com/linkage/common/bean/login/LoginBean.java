package com.linkage.common.bean.login;

import java.util.Set;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppBean;
import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.sys.bean.system.MenuBean;

public  class LoginBean extends AppBean {
	private MenuBean menuBean = new MenuBean();
	/**
	 * 查询用户资料
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-9
	 */
    public IData queryStaff(PageData pd,IData data) throws Exception {
    	AppEntity dao = new AppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_S_STAFF t  where (1 = 1");
		parser.addSQL(" and t.STAFF_PWD=:PASSWORD ");
		parser.addSQL(" and t.STAFF_NAME=:STAFF_ID and t.flag='1') or");
		parser.addSQL(" (1 = 1 and t.STAFF_PWD=:PASSWORD ");
		parser.addSQL(" and t.STAFF_ID=:STAFF_ID and t.flag='1')");
		IDataset dataset = dao.queryList(parser);
		return dataset.size() <= 0 ? null : (IData)dataset.get(0);
    }
    /**
     * 获取用户菜单
     * @param pd
     * @param data
     * @return
     * @throws Exception
     * @author:chenzg
     * @date:2010-5-9
     */
    public IDataset queryStaffMenu(PageData pd, IData data) throws Exception{
    	AppEntity dao = new AppEntity(pd);
    	SQLParser parser = new SQLParser(data);
    	parser.addSQL("SELECT a.menu_code, b.menu_name, b.parent_menu_code FROM td_s_staff_menu_config a, td_s_menu b WHERE (1=1)");
		parser.addSQL(" AND a.menu_code=b.menu_code ");
		parser.addSQL(" AND a.staff_id=:STAFF_ID ");
		parser.addSQL(" AND a.item_flag='1' ");
		parser.addSQL(" union Select a.menu_code, a.menu_name, a.parent_menu_code From td_s_menu a,td_s_menu_right_config b Where a.menu_code = b.menu_id And b.right_code In (Select c.RIGHT_CODE From td_s_staff_right_config c Where  c.item_flag='1' And c.staff_id = :STAFF_ID )");	
		IDataset dataset = dao.queryList(parser);		
		return dataset == null ? new DatasetList() : dataset;
    }
    /**
     * 获取用户权限
     * @param pd
     * @param data
     * @return
     * @throws Exception
     * @author:chenzg
     * @date:2010-5-9
     */
    public IDataset queryStaffRight(PageData pd, IData data) throws Exception{
    	AppEntity dao = new AppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("SELECT t.* FROM td_s_staff_right_config t WHERE (1=1)");
		parser.addSQL(" AND t.staff_id=:STAFF_ID ");
		parser.addSQL(" AND t.item_flag='1' ");
		IDataset dataset = dao.queryList(parser);
		return dataset == null ? new DatasetList() : dataset;
    }
    /**
     * 准备员工的菜单信息
     * @param pd
     * @param data
     * @return
     * @throws Exception
     * @author:chenzg
     * @date:2010-5-22
     */
    public IData prepareStaffMenuInfo(PageData pd, IData data) throws Exception{
    	IData retMap = new DataMap();
    	//查出所有父菜单信息
    	IDataset allParentMenus = menuBean.queryParentMenus(pd, null);
    	//构造父菜单对应的子菜单集合结构
    	for(int i=0;i<allParentMenus.size();i++){
    		retMap.put(allParentMenus.getData(i).getString("MENU_CODE"), new DataMap());
    	}
    	//查出员工菜单信息
    	data.put("STAFF_ID", pd.getContext().getStaffId());
    	IDataset staffMenus = this.queryStaffMenu(pd, data);
    	//将子菜单放进子菜单集合
    	for(int i = 0; i<staffMenus.size(); i++){
    		IData menu = staffMenus.getData(i);
    		String menuCode = menu.getString("MENU_CODE");
    		String menuName = menu.getString("MENU_NAME");
    		String parentMenuCode = menu.getString("PARENT_MENU_CODE");
    		retMap.getData(parentMenuCode).put(menuCode, menuName);
    	}
    	
    	//去掉没有子菜单的父菜单
    	String[] names = retMap.getNames();
    	for(int i=0;i<names.length;i++){
    		String key = names[i];
    		if(retMap.getData(key).isEmpty()){
    			retMap.remove(key);
    		}
    	}
    	
    	return retMap;
    }
    /**
     * 准备员工权限
     * @param pd
     * @param data
     * @return
     * @throws Exception
     * @author:chenzg
     * @date:2010-5-22
     */
    public IData prepareStaffRightInfo(PageData pd, IData data) throws Exception{
    	IData retMap = new DataMap();
    	IDataset staffRights = this.queryStaffRight(pd, data);
    	for(int i=0;i<staffRights.size();i++){
    		IData right = staffRights.getData(i);
    		String rightCode = right.getString("RIGHT_CODE");
    		String rightName = right.getString("RIGHT_NAME");
    		retMap.put(rightCode, rightCode);
    	}    	
    	return retMap;
    }
}