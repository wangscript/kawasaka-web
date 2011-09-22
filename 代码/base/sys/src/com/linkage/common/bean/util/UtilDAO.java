package com.linkage.common.bean.util;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class UtilDAO extends AppEntity {
    
	/**
     * construct function
     * @param pd
     * @throws Exception
     */
    public UtilDAO(PageData pd) throws Exception {
    	super(pd);
    }
    
	/**
     * construct function
     * @param pd
     * @param connName
     * @throws Exception
     */
    public UtilDAO(PageData pd, String connName) throws Exception {
    	super(pd, connName);
    }
    
    public String getSEQ() throws Exception{
    	IDataset dateset = this.queryList(" select to_char(SYSDATE,'yyyymmdd')||lpad(SEQ_NGEOS_IBSYSID.nextval, 6, '0') IBSYSID from dual");
		if (dateset.size()>0){
	    	return dateset.getData(0).getString("IBSYSID");
		}
		else
		{
	    	return common.getTimestampFormat("yyyyMMddHHmmss");
		}
    }
    
    public String getCLOSE_SEQ() throws Exception{
    	IDataset dateset = this.queryList(" select to_char(SYSDATE,'yyyymmdd')||lpad(SEQ_CLOSE_IBSYSID.nextval, 6, '0') IBSYSID from dual");
		if (dateset.size()>0){
	    	return dateset.getData(0).getString("IBSYSID");
		}
		else
		{
	    	return common.getTimestampFormat("yyyyMMddHHmmss");
		}
    }    
}