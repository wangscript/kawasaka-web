package com.linkage.common.bean.util;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IDataset;
import com.linkage.appframework.data.IData;
import com.linkage.component.AppBean;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;

public class UtilBean extends AppBean {
	
   /**
     * create schedule
     * @param pd
     * @param data
     * @throws Exception
     */
    public void createSchedule(PageData pd, IData data) throws Exception {	    
    	UtilDAO dao = new UtilDAO(pd);
    	
    	data.put("SCHE_ID", dao.getSequence("SEQ_SCHE_ID"));
    	data.put("SCHE_STATUS", UtilFactory.SCHE_STATUS_NONOTIFY);
    	data.put("CREA_STAFF", pd.getContext().getStaffId());
    	data.put("CREA_TIME", common.getSysTime());
    	dao.insert("TF_DM_SCHEDULE", data);
    }
    
    /**
     * create schedules
     * @param pd
     * @param dataset
     * @throws Exception
     */
    public void createSchedules(PageData pd, IDataset dataset) throws Exception {	    
    	UtilDAO dao = new UtilDAO(pd);
    	
    	for (int i=0; i<dataset.size(); i++) {
    		IData data = (IData) dataset.get(i);
    		data.put("SCHE_ID", dao.getSequence("SEQ_SCHE_ID"));
    		data.put("SCHE_STATUS", UtilFactory.SCHE_STATUS_NONOTIFY);
    		data.put("CREA_STAFF", pd.getContext().getStaffId());
    		data.put("CREA_TIME", common.getSysTime());
    	}
    	dao.insert("TF_DM_SCHEDULE", dataset);
    }
    
    /**
     * import schedules
     * @param pd
     * @param dataset
     * @return IDataset
     * @throws Exception
     */
    public IDataset importSchedules(PageData pd, IDataset dataset) throws Exception {	    
    	UtilDAO dao = new UtilDAO(pd);
    	
    	IDataset succds = new DatasetList();
    	IDataset failds = new DatasetList();
    	for (int i=0; i<dataset.size(); i++) {
    		IData data = (IData) dataset.get(i);
    		
    		boolean import_result = data.getBoolean("IMPORT_RESULT");
    		/* 判断导入数据输入校验是否通过 */
    		if (!import_result) {
    			failds.add(data);
    			continue;
    		}
    		
    		/* 判断导入数据业务逻辑校验是否通过 */
    		String sche_type = (String) data.get("SCHE_TYPE");
    		
    		StringBuffer import_error = new StringBuffer();
    		/* 判断日程类型是否是系统支持的，仅作为举例说明下业务逻辑的判断，这种校验在xml中配置datasrc即可 */
    		if (sche_type != null && !(UtilFactory.SCHE_TYPE_DAILY.equals(sche_type) || UtilFactory.SCHE_TYPE_PLAN.equals(sche_type))) {
    			import_error.append("日程类型系统不支持;");
    		}
    		/* 存放失败数据 */
    		if (import_error.length() > 0) {
    			data.put("IMPORT_RESULT", "false");
    			data.put("IMPORT_ERROR", import_error.toString());
    			failds.add(data);
    			continue;
    		}
    		
    		/* 正确数据处理 */
    		data.put("SCHE_ID", dao.getSequence("SEQ_SCHE_ID"));
    		data.put("SCHE_STATUS", UtilFactory.SCHE_STATUS_NONOTIFY);
    		data.put("CREA_STAFF", pd.getContext().getStaffId());
    		data.put("CREA_TIME", common.getSysTime());
    		
    		succds.add(data);
    	}
    	dao.insert("TF_DM_SCHEDULE", succds);
    	
    	return failds;
    }

	/**
     * update schedule
     * @param pd
     * @param data
     * @param boolean
     * @throws Exception
     */
    public boolean updateSchedule(PageData pd, IData data) throws Exception {
    	return updateSchedule(pd, data, null);
    }

	/**
     * update schedule
     * @param pd
     * @param data
     * @param values
     * @return boolean
     * @throws Exception
     */
    public boolean updateSchedule(PageData pd, IData data, String[] values) throws Exception {	    
    	String[] keys = values == null ? null : new String[] {"SCHE_ID", "SCHE_STATUS"};
    	UtilDAO dao = new UtilDAO(pd);
    	return dao.save("TF_DM_SCHEDULE", data, keys, values);
    }
    
	/**
     * delete schedule
     * @param pd
     * @param sche_id
     * @param boolean
     * @throws Exception
     */
    public boolean deleteSchedule(PageData pd, String sche_id) throws Exception {	    
    	return deleteSchedule(pd, sche_id, null);
    }
    
	/**
     * delete schedule
     * @param pd
     * @param sche_id
     * @param sche_status
     * @throws Exception
     */
    public boolean deleteSchedule(PageData pd, String sche_id, String sche_status) throws Exception {	    
    	UtilDAO dao = new UtilDAO(pd);
    	
    	IData data = new DataMap();
    	data.put("SCHE_ID", sche_id);
    	if (sche_status != null) data.put("SCHE_STATUS", sche_status);
    	return dao.delete("TF_DM_SCHEDULE", data, new String[] {"SCHE_ID","SCHE_STATUS"});
    }
    
	/**
     * delete schedules
     * @param pd
     * @param sches
     * @throws Exception
     */
    public void deleteSchedules(PageData pd, String[] sches) throws Exception {	    
    	UtilDAO dao = new UtilDAO(pd);
    	
    	IDataset dataset = new DatasetList();
    	for (int i=0; i<sches.length; i++) {
    		IData data = new DataMap();
    		data.put("SCHE_ID", sches[i]);
    		dataset.add(data);
    	}
    	
    	dao.delete("TF_DM_SCHEDULE", dataset);
    }
    
    /**
     * query schedule
     * @param pd
     * @param sche_id
     * @return IData
     * @throws Exception
     */
    public IData querySchedule(PageData pd, String sche_id) throws Exception {
    	UtilDAO dao = new UtilDAO(pd);
    	
    	IData data = new DataMap();
    	data.put("SCHE_ID", sche_id);
    	return dao.queryByPK("TF_DM_SCHEDULE", data);
    }
	
    
}
