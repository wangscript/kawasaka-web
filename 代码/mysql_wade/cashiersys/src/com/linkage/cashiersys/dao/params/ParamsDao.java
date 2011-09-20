/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.dao.params;

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
	 * 查询项目信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public IDataset queryItems(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_item_config t  where (1 = 1) ");
		parser.addSQL(" and t.BUSI_ITEM_CODE=:BUSI_ITEM_CODE ");
		parser.addSQL(" and t.BUSI_ITEM_NAME like '%' ||:BUSI_ITEM_NAME||'%' ");
		parser.addSQL(" and t.BUSI_ITEM_UNIT=:BUSI_ITEM_UNIT ");
		parser.addSQL(" and t.BUSI_ITEM_FEE=:BUSI_ITEM_FEE ");
		parser.addSQL(" and t.ITEM_TYPE_CODE=:ITEM_TYPE_CODE ");
		parser.addSQL(" and t.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" ORDER BY t.BUSI_ITEM_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	/**
	 * 新增项目
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public void addItems(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_ITEM_CONFIG", dataset);
	}
	/**
	 * 修改项目信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public boolean updateItem(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_ITEM_CONFIG", data, keys);
	}
	/**
	 * 删除项目
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public boolean deleteItems(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.delete("TD_M_ITEM_CONFIG", data, keys);
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
	 * 查询酒店参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public IDataset queryHotels(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_HOTEL_CONFIG a  where (1 = 1) ");
		parser.addSQL(" and a.HOTEL_CODE=:HOTEL_CODE ");
		parser.addSQL(" and a.HOTEL_NAME like '%' ||:HOTEL_NAME||'%' ");
		parser.addSQL(" and a.HOTEL_DESC like '%' ||:HOTEL_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.HOTEL_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增酒店参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void addHotels(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_HOTEL_CONFIG", dataset);
	}
	/**
	 * 修改酒店参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public boolean updateHotels(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_HOTEL_CONFIG", data, keys);
	}
	/**
	 * 查询酒店项目参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public IDataset queryHotelsItems(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select a.*, b.HOTEL_NAME, c.BUSI_ITEM_NAME from TD_M_HOTEL_ITEM_CONFIG a, TD_M_HOTEL_CONFIG b, TD_M_ITEM_CONFIG c  where (1 = 1) ");
		parser.addSQL(" and a.HOTEL_CODE=b.HOTEL_CODE ");
		parser.addSQL(" and a.BUSI_ITEM_CODE=c.BUSI_ITEM_CODE ");
		parser.addSQL(" and a.HOTEL_CODE=:HOTEL_CODE ");
		parser.addSQL(" and a.BUSI_ITEM_CODE=:BUSI_ITEM_CODE ");
		parser.addSQL(" and a.HOTEL_ITEM_DESC like '%' ||:HOTEL_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.HOTEL_CODE, a.BUSI_ITEM_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增酒店项目参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addHotelsItems(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_HOTEL_ITEM_CONFIG", dataset);
	}
	/**
	 * 修改酒店项目参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public boolean updateHotelsItems(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_HOTEL_ITEM_CONFIG", data, keys);
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
	/**
	 * 查询业务区酒店参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public IDataset queryAreasHotels(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select a.*, b.HOTEL_NAME, c.AREA_NAME from TD_M_AREA_HOTEL_CONFIG a, TD_M_HOTEL_CONFIG b, TD_M_AREA_CONFIG c  where (1 = 1) ");
		parser.addSQL(" and a.HOTEL_CODE=b.HOTEL_CODE ");
		parser.addSQL(" and a.AREA_CODE=c.AREA_CODE ");
		parser.addSQL(" and a.HOTEL_CODE=:HOTEL_CODE ");
		parser.addSQL(" and a.AREA_CODE=:AREA_CODE ");
		parser.addSQL(" and a.AREA_HOTEL_DESC like '%' ||:AREA_HOTEL_DESC||'%' ");
		parser.addSQL(" and a.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and a.UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and a.UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY a.AREA_CODE, a.HOTEL_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增业务区酒店参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public void addAreasHotels(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_AREA_HOTEL_CONFIG", dataset);
	}
	/**
	 * 修改业务区酒店参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public boolean updateAreasHotels(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_AREA_HOTEL_CONFIG", data, keys);
	}


	
	/**
	 * 查询旅行社参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public IDataset queryTravels(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_TRAVEL_CONFIG t  where (1 = 1) ");
		parser.addSQL(" and TRAVEL_CODE=:TRAVEL_CODE ");
		parser.addSQL(" and TRAVEL_NAME like '%' ||:TRAVEL_NAME||'%' ");
		parser.addSQL(" and TRAVEL_DESC like '%' ||:TRAVEL_DESC||'%' ");
		parser.addSQL(" and ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY TRAVEL_CODE ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	/**
	 * 新增旅行社参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addTravels(PageData pd, IDataset dataset) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		dao.insert("TD_M_TRAVEL_CONFIG", dataset);
	}
	/**
	 * 修改旅行社参数
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public boolean updateTravels(PageData pd, IData data, String[] keys) throws Exception{
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_TRAVEL_CONFIG", data, keys);
	}
}
