/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.bean.files;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.files.FilesDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class FilesBean extends CashierBaseBean{
	

	
	/**
	 * 查询文件信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryFiles(PageData pd,IData data, Pagination pagination) throws Exception {
		FilesDao dao = new FilesDao(pd);
		return dao.queryFiles(pd, data, pagination);
	}




}
