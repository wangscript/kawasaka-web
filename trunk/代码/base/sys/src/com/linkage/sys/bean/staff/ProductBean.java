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
	 * ��ѯ��Ʒ����
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
	 * ��ѯ��ƷС��
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
	 * ֻ��ѯ��ƷС����Ϣ
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
	 * ��ѯ��Ʒ��Ϣ
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
	 * ��ѯ�Ѿ����ڵĲ�Ʒ����
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
	 * ��ѯ�Ѿ����ڵĲ�ƷС��
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
	 * ��ѯ�Ѿ����ڵĲ�Ʒ
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
	 * ���ݲ�Ʒ����ID��ѯ��������Ʒ������ͬ�Ĳ�Ʒ����
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
	 * ���ݲ�ƷID��ѯ��������Ʒ��ͬ�Ĳ�Ʒ����
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
	 * ���ݲ�ƷС��ID��ѯͬһ����Ʒ��������������ƷС����ͬ�Ĳ�Ʒ����
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
	 * ������Ʒ������Ϣ
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
	 * ������ƷС����Ϣ
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
	 * ������Ʒ��Ϣ
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
	 * ���²�Ʒ������Ϣ
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
	 * ���²�ƷС����Ϣ
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
	 * ���²�Ʒ��Ϣ
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
	 * ɾ����Ʒ������Ϣ
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
	 * ɾ����ƷС����Ϣ
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
	 * ��ѯԱ���˵���Ϣ
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
	 * �޸�Ա���˵���Ϣ
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
	 * ����Ա���˵���Ϣ
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
	 * ��ѯԱ��Ȩ����Ϣ
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
	 * �޸�Ա��Ȩ����Ϣ
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
	 * ����Ա��Ȩ����Ϣ
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
