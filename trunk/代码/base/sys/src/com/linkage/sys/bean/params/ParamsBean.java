/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.sys.bean.params;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.params.ParamsDao;

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
	 * ��ѯ������Ϣ
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
	 * �޸���Ŀ��Ϣ
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
	 * �������Ų���
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
	 * ��ѯ������Ŀ����
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
	 * �޸Ĳ�����Ŀ����
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
	 * ����������Ŀ����
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
	 * ��ѯҵ��������
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
	 * �޸�ҵ��������
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
	 * ����ҵ��������
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



}
