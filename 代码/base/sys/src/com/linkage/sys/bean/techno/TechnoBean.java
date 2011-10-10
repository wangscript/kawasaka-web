package com.linkage.sys.bean.techno;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.group.GroupDao;
import com.linkage.sys.dao.product.ProductDao;
import com.linkage.sys.dao.staff.StaffDao;
import com.linkage.sys.dao.techno.TechnoDao;


public class TechnoBean extends CashierBaseBean {
	
/**
 * ��ѯ���Ź�˾��Ϣ
 */
public IDataset queryTechnoLists(PageData pd,IData data, Pagination pagination) throws Exception {
	TechnoDao dao = new TechnoDao(pd);
		return dao.queryTechnoLists(pd, data, pagination);
	}
	
/**
 * ��ѯ�����Ĳ����Ƿ��Ѿ�����
 * existsProduct
 */

public boolean existsTechno(PageData pd,IData data, Pagination pagination) throws Exception {
	GroupDao dao = new GroupDao(pd);
	Parameter parameter = new Parameter();
	parameter.add(data.getString("TECHNO_CLASS", ""));
	String sql = "SELECT COUNT(*) FROM TD_M_TECHNO WHERE  TECHNO_CLASS=? ";
	return dao.getCount(sql, parameter)>=1?true:false;
}
public boolean existsSolu(PageData pd,IData data, Pagination pagination) throws Exception {
	GroupDao dao = new GroupDao(pd);
	Parameter parameter = new Parameter();
	parameter.add(data.getString("SOLU_NAME", ""));
	String sql = "SELECT COUNT(*) FROM TD_M_SOLUTION WHERE  SOLU_NAME=? ";
	return dao.getCount(sql, parameter)>=1?true:false;
}


public IDataset querySoluLists(PageData pd,IData data, Pagination pagination) throws Exception {
	TechnoDao dao = new TechnoDao(pd);
		return dao.querySoluLists(pd, data, pagination);
	}
}
