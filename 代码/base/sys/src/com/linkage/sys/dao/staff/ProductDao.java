/**
 * 
 * @author:wull
 * @date:2011-9-22
 */
package com.linkage.sys.dao.product;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wull
 *
 */
public class ProductDao extends CashierAppEntity{
	public ProductDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public ProductDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}
	
	
	/**
	 * 查询产品大类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductClassLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_PRODUCT_CLASS t  where (1 = 1) ");
		parser.addSQL(" and T.ID=:ID ");
		parser.addSQL(" and T.PRODUCT_CLASS like concat('%',:PRODUCT_CLASS,'%') ");
		parser.addSQL(" and T.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and T.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" order by t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	/**
	 * 查询产品小类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductTypeLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.* from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
		parser.addSQL("and u.PRODUCT_CLASS = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.ID=:ID ");
		parser.addSQL(" and u.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE like concat('%',:PRODUCT_TYPE,'%') ");
		parser.addSQL(" and t.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	/**
	 *只 查询产品小类类型信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductTypes(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.PRODUCT_TYPE from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
		parser.addSQL("and u.PRODUCT_CLASS = t.PRODUCT_CLASS ");
		parser.addSQL(" and u.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	
	/**
	 * 查询产品信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.* from TD_M_PRODUCT_CLASS u, TD_M_PRODUCT_TYPE v,TD_M_PRODUCT t where (1 = 1) ");
		parser.addSQL(" and u.PRODUCT_CLASS = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.PRODUCT_ID=:PRODUCT_ID ");
		parser.addSQL(" and t.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE = :PRODUCT_TYPE");
		parser.addSQL(" and t.PRODUCT_NAME like concat('%',:PRODUCT_NAME,'%') ");
		parser.addSQL(" and t.PRODUCT_MODEL like concat('%',:PRODUCT_MODEL,'%') ");
		parser.addSQL(" and t.PRODUCT_FACTORY like concat('%',:PRODUCT_FACTORY,'%') ");
		parser.addSQL(" and t.PRODUCT_GOOD=:PRODUCT_GOOD ");
		parser.addSQL(" and t.HOME_SHOW=:HOME_SHOW ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and v.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and t.PRODUCT_CLASS=u.PRODUCT_CLASS ");
		parser.addSQL(" and t.PRODUCT_TYPE=v.PRODUCT_TYPE ");
		parser.addSQL(" order by u.CLASSORDER desc,v.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	
	
	/**
	 * 新增产品大类信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProductClassList(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
//		String sql = "insert into TD_M_PRODUCT_CLASS( PRODUCT_CLASS, CLASSORDER, ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?, ?, ?, ?,?)";
//		Parameter[] params = new Parameter[dataset.size()];
//    	for (int i=0; i<params.length; i++) {
//    		params[i] = new Parameter();
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
//    		params[i].add(((IData)dataset.get(i)).getString("CLASSORDER",null));
//    		params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
//    	}
//    	executeBatch(sql, params);
		for(int i=0;i<dataset.size();i++){
			dataset.getData(i).remove("ID");
		}
		dao.insert("TD_M_PRODUCT_CLASS", dataset);
	}
	
	
	/**
	 * 新增产品小类信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProductType(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
//		String sql = "insert into TD_M_PRODUCT_TYPE( PRODUCT_CLASS,PRODUCT_TYPE, CLASSORDER, ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?,?, ?, ?, ?,?)";
//		Parameter[] params = new Parameter[dataset.size()];
//    	for (int i=0; i<params.length; i++) {
//    		params[i] = new Parameter();
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_TYPE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("CLASSORDER","0"));
//    		params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
//    	}
//    	executeBatch(sql, params);
//    	
    	
    	for(int i=0;i<dataset.size();i++){
			dataset.getData(i).remove("ID");
		}
		dao.insert("TD_M_PRODUCT_TYPE", dataset);
    	
	}
	
	
	
	/**
	 * 新增产品信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProduct(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
//		String sql = "insert into TD_M_PRODUCT( PRODUCT_CLASS,PRODUCT_TYPE, PRODUCT_NAME,PRODUCT_MODEL,PRODUCT_FACTORY,PRODUCT_PICTURE,PRODUCT_INTRODUCE,PRODUCT_GOOD,HOME_SHOW,PRIVILEGE_PRICE,RETAIL_PRICE,VIP_PRICE,REMARK,ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?,?, ?, ?, ?,?,?, ?, ?, ?,?,?, ?, ?, ?,?)";
//		Parameter[] params = new Parameter[dataset.size()];
//    	for (int i=0; i<params.length; i++) {
//    		params[i] = new Parameter();
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_TYPE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_NAME",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_MODEL",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_FACTORY",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_PICTURE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_INTRODUCE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRODUCT_GOOD",null));
//    		params[i].add(((IData)dataset.get(i)).getString("HOME_SHOW",null));
//    		params[i].add(((IData)dataset.get(i)).getString("PRIVILEGE_PRICE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("RETAIL_PRICE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("VIP_PRICE",null));
//    		params[i].add(((IData)dataset.get(i)).getString("REMARK",null));
//    		params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
//    		params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
//    	}
//    	executeBatch(sql, params);
//    	
//    	
    	for(int i=0;i<dataset.size();i++){
			dataset.getData(i).remove("PRODUCT_ID");
		}
		dao.insert("TD_M_PRODUCT", dataset);
    	
	}
	
	
	
	/**
	 * 更新产品大类信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductClassList(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT_CLASS", data, keys);
	}
	
	/**
	 * 更新产品小类信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductType(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT_TYPE", data, keys);
	}
	
	
	/**
	 * 更新产品信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProduct(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT", data, keys);
	}
	
	/**
	 * 更新员工信息
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
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
	 * @author:wull
	 * @date:2011-9-22
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
	 * @author:wull
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
	 * @author:wull
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
	 * @author:wull
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
	 * @author:wull
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
	 * @author:wull
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
	 * @author:wull
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
	 * @author:wull
	 * @date:2010-5-20
	 */
	public boolean updateStaffRight(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_S_STAFF_RIGHT_CONFIG", data, keys);
	}
}
