/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.bean.staff;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.params.ParamsDao;
import com.linkage.sys.dao.staff.StaffDao;

/**
 * @author chenzg
 * 
 */
public class StaffBean extends CashierBaseBean{
	/**
	 * ��ѯ�û���Ϣ
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public IDataset queryStaff(PageData pd,IData data, Pagination pagination) throws Exception {
		StaffDao dao = new StaffDao(pd);
		return dao.queryStaff(pd, data, pagination);
	}
	/**
	 * ����Ա����Ϣ
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-19
	 */
	public void addStaffs(PageData pd,IDataset dataset) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.addStaffs(pd, dataset);
	}
	/**
	 * ����Ա����Ϣ
	 * @param pd
	 * @param data
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-19
	 */
	public void updateStaffs(PageData pd,IData data) throws Exception {
		StaffDao dao = new StaffDao(pd);
		String flag = data.getString("RESET_PWD");
		data.remove("RESET_PWD");
		if ("1".equals(flag)){
			data.put("STAFF_PWD", "123456");
		}
		dao.updateStaffs(pd, data, new String[]{"STAFF_ID"});
	}
	/**
	 * �޸��û�����
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public int updateStaffPasswd(PageData pd,IData data) throws Exception {
		StaffDao dao = new StaffDao(pd);
		return dao.updateStaffPasswd(pd, data);
	}
	/**
	 * �����û�����
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public int resetStaffPasswd(PageData pd,IData data) throws Exception {
		StaffDao dao = new StaffDao(pd);
		return dao.resetStaffPasswd(pd, data);
	}
	/**
	 * ��ѯԱ���˵���Ϣ
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-20
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
	 * @author:chenzg
	 * @date:2010-5-20
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
	 * @author:chenzg
	 * @date:2010-5-20
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
	 * @author:chenzg
	 * @date:2010-5-20
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
	 * @author:chenzg
	 * @date:2010-5-20
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
	 * @author:chenzg
	 * @date:2010-5-20
	 */
	public void addStaffRight(PageData pd,IDataset dataset) throws Exception {
		StaffDao dao = new StaffDao(pd);
		dao.addStaffRight(pd, dataset);
	}
}
