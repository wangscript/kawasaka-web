package com.linkage.sys.bean.group;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.group.GroupDao;
import com.linkage.sys.dao.product.ProductDao;
import com.linkage.sys.dao.staff.StaffDao;


public class GroupBean extends CashierBaseBean {
	
/**
 * ��ѯ���Ź�˾��Ϣ
 */
public IDataset queryGroupLists(PageData pd,IData data, Pagination pagination) throws Exception {
	GroupDao dao = new GroupDao(pd);
		return dao.queryGroupLists(pd, data, pagination);
	}
	
/**
 * ��ѯ�����Ĳ����Ƿ��Ѿ�����
 * existsProduct
 */

public boolean existsGroup(PageData pd,IData data, Pagination pagination) throws Exception {
	GroupDao dao = new GroupDao(pd);
	Parameter parameter = new Parameter();
	parameter.add(data.getString("GROUP_NAME", ""));
	String sql = "SELECT COUNT(*) FROM TD_M_GROUP WHERE  GROUP_NAME=? ";
	return dao.getCount(sql, parameter)>=1?true:false;
}

}
