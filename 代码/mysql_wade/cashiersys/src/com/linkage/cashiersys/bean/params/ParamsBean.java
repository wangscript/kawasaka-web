/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.bean.params;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.common.CashierBaseBean;
import com.linkage.cashiersys.dao.params.ParamsDao;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;

/**
 * @author chenzg
 * 
 */
public class ParamsBean extends CashierBaseBean{
	
	public IDataset queryDiscountType(PageData pd) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryList("Select * From td_s_static t Where t.type_id = 'DISCOUNT_TYPES' And t.valid_flag='1'");
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
	public IDataset queryItems(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryItems(pd, data, pagination);
	}
	/**
	 * 修改项目信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public void updateItems(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateItem(pd, data, new String[]{"BUSI_ITEM_CODE"});
	}
	/**
	 * 新增项目
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-14
	 */
	public void addItems(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addItems(pd, dataset);
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
	public IDataset queryDeparts(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryDeparts(pd, data, pagination);
	}
	/**
	 * 修改项目信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public void updateDeparts(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateDeparts(pd, data, new String[]{"DEPART_CODE"});
	}
	/**
	 * 新增部门参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-15
	 */
	public void addDeparts(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addDeparts(pd, dataset);
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
	public IDataset queryDepartsItems(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryDepartsItems(pd, data, pagination);
	}
	/**
	 * 修改部门项目参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void updateDepartsItems(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateDepartsItems(pd, data, new String[]{"DEPART_CODE", "BUSI_ITEM_CODE"});
	}
	/**
	 * 新增部门项目参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void addDepartsItems(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addDepartsItems(pd, dataset);
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
	public IDataset queryHotels(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryHotels(pd, data, pagination);
	}
	/**
	 * 修改酒店参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void updateHotels(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateHotels(pd, data, new String[]{"HOTEL_CODE"});
	}
	/**
	 * 增加酒店参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-16
	 */
	public void addHotels(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addHotels(pd, dataset);
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
	public IDataset queryHotelsItems(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryHotelsItems(pd, data, pagination);
	}
	/**
	 * 修改酒店项目参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void updateHotelsItems(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateHotelsItems(pd, data, new String[]{"HOTEL_CODE", "BUSI_ITEM_CODE"});
	}
	/**
	 * 新增酒店项目参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addHotelsItems(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addHotelsItems(pd, dataset);
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
	public IDataset queryAreas(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryAreas(pd, data, pagination);
	}
	/**
	 * 修改业务区参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void updateAreas(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateAreas(pd, data, new String[]{"AREA_CODE"});
	}
	/**
	 * 新增业务区参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addAreas(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addAreas(pd, dataset);
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
	public IDataset queryAreasHotels(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryAreasHotels(pd, data, pagination);
	}
	/**
	 * 修改业务区酒店参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public void updateAreasHotels(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateAreasHotels(pd, data, new String[]{"AREA_CODE", "HOTEL_CODE"});
	}
	/**
	 * 新增业务区酒店参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-18
	 */
	public void addAreasHotels(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addAreasHotels(pd, dataset);
	}

	/**
	 * 查询旅行社参数
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public IDataset queryTravels(PageData pd,IData data, Pagination pagination) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		return dao.queryTravels(pd, data, pagination);
	}
	
	/**
	 * 修改旅行社参数
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void updateTravels(PageData pd,IData data) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.updateTravels(pd, data, new String[]{"TRAVEL_CODE"});
	}
	/**
	 * 新增旅行社参数
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-17
	 */
	public void addTravels(PageData pd,IDataset dataset) throws Exception {
		ParamsDao dao = new ParamsDao(pd);
		dao.addTravels(pd, dataset);
	}	

}
