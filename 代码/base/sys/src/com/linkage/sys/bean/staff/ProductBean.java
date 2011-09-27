/**
 * 
 * @author:wull
 */
package com.linkage.sys.bean.product;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.product.ProductDao;
import com.linkage.sys.dao.staff.StaffDao;

/**
 * @author wull
 * 
 */
public class ProductBean extends CashierBaseBean{
	/**
	 * 查询产品大类
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public IDataset queryProductClassLists(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.queryProductClassLists(pd, data, pagination);
	}
	
	
	
	/**
	 * 查询产品小类
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public IDataset queryProductTypeLists(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.queryProductTypeLists(pd, data, pagination);
	}
	
	/**
	 * 只查询产品小类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public IDataset queryProductTypes(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.queryProductTypes(pd, data, pagination);
	}
	
	
	/**
	 * 查询产品信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public IDataset queryProductLists(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.queryProductLists(pd, data, pagination);
	}
	
	
	
	/**
	 * 查询已经存在的产品大类
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProductClassList(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_CLASS", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT_CLASS WHERE ITEM_FLAG='1' AND PRODUCT_CLASS=?";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	/**
	 * 查询已经存在的产品小类
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProductType(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_CLASS", ""));
		parameter.add(data.getString("PRODUCT_TYPE", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT_TYPE WHERE ITEM_FLAG='1' AND PRODUCT_CLASS=? AND PRODUCT_TYPE=?";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	/**
	 * 查询已经存在的产品
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProduct(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_NAME", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT WHERE ITEM_FLAG='1' AND PRODUCT_NAME=? ";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	
	
	/**
	 * 根据产品大类ID查询与其他产品大类相同的产品名称
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProductClassListById(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_CLASS", ""));
		parameter.add(data.getString("ID", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT_CLASS WHERE ITEM_FLAG='1' AND PRODUCT_CLASS=? and id <> ?";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	/**
	 * 根据产品ID查询与其他产品相同的产品名称
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProductById(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_NAME", ""));
		parameter.add(data.getString("PRODUCT_ID", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT WHERE ITEM_FLAG='1' AND PRODUCT_NAME=? and PRODUCT_ID <> ?";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	/**
	 * 根据产品小类ID查询同一个产品大类中与其他产品小类相同的产品名称
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	
	 */
	public boolean existsProductTypeListById(PageData pd,IData data, Pagination pagination) throws Exception {
		ProductDao dao = new ProductDao(pd);
		Parameter parameter = new Parameter();
		parameter.add(data.getString("PRODUCT_CLASS", ""));
		parameter.add(data.getString("PRODUCT_TYPE", ""));
		parameter.add(data.getString("ID", ""));
		String sql = "SELECT COUNT(*) FROM TD_M_PRODUCT_TYPE WHERE ITEM_FLAG='1' AND PRODUCT_CLASS=? AND PRODUCT_TYPE=? and id <> ?";
		return dao.getCount(sql, parameter)>=1?true:false;
	}
	
	
	/**
	 * 新增产品大类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public void addProductClassList(PageData pd,IDataset dataset) throws Exception {
		ProductDao dao = new ProductDao(pd);
		dao.addProductClassList(pd, dataset);
	}
	
	
	/**
	 * 新增产品小类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public void addProductType(PageData pd,IDataset dataset) throws Exception {
		ProductDao dao = new ProductDao(pd);
		dao.addProductType(pd, dataset);
	}
	
	/**
	 * 新增产品信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public void addProduct(PageData pd,IDataset dataset) throws Exception {
		ProductDao dao = new ProductDao(pd);
		dao.addProduct(pd, dataset);
	}
	
	
	/**
	 * 更新产品大类信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductClassList(PageData pd,IData data) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.updateProductClassList(pd, data, new String[]{"ID"});
	}
	
	/**
	 * 更新产品小类信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductType(PageData pd,IData data) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.updateProductType(pd, data, new String[]{"ID"});
	}
	
	/**
	 * 更新产品信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProduct(PageData pd,IData data) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.updateProduct(pd, data, new String[]{"PRODUCT_ID"});
	}
	
	
	/**
	 * 删除产品大类信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public boolean deleteProductClassList(PageData pd,IData data) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.delete("TD_M_PRODUCT_CLASS",data,new String[] {"ID"});
	}
	
	
	/**
	 * 删除产品小类信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public boolean deleteProductType(PageData pd,IData data) throws Exception {
		ProductDao dao = new ProductDao(pd);
		return dao.delete("TD_M_PRODUCT_TYPE",data,new String[] {"ID"});
	}
	
	
	
	
	
	
	
	/**
	 * 查询员工菜单信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryStaffMenu(PageData pd,IData data, Pagination pagination) throws Exception {
		StaffDao dao = new StaffDao(pd);
		return dao.queryStaffMenu(pd, data, pagination);
	}
	/**
	 * 修改员工菜单信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public void updateStaffMenu(PageData pd,IData data) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.updateStaffMenu(pd, data, new String[]{"STAFF_ID", "MENU_CODE"});
	}
	/**
	 * 新增员工菜单信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 */
	public void addStaffMenu(PageData pd,IDataset dataset) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.addStaffMenu(pd, dataset);
	}
	/**
	 * 查询员工权限信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryStaffRight(PageData pd,IData data, Pagination pagination) throws Exception {
		StaffDao dao = new StaffDao(pd);
		return dao.queryStaffRight(pd, data, pagination);
	}
	/**
	 * 修改员工权限信息
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:wull
	 */
	public void updateStaffRight(PageData pd,IData data) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.updateStaffRight(pd, data, new String[]{"STAFF_ID", "RIGHT_CODE"});
	}
	/**
	 * 新增员工权限信息
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 */
	public void addStaffRight(PageData pd,IDataset dataset) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.addStaffRight(pd, dataset);
	}
}
