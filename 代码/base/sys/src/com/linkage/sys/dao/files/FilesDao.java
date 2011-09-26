/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.dao.files;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wuwl
 *
 */
public class FilesDao extends CashierAppEntity{
	public FilesDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public FilesDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}

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
	public IDataset queryFiles(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from td_m_file t  where (1 = 1) ");
		parser.addSQL(" and FILE_ID=:FILE_ID ");
		parser.addSQL(" and FILE_NAME like '%' ||:FILE_NAME||'%' ");
		parser.addSQL(" and FILE_TYPE=:FILE_TYPE ");
		parser.addSQL(" and UPDATE_STAFF_ID=:UPDATE_STAFF_ID ");
		parser.addSQL(" and UPDATE_TIME=:UPDATE_TIME ");
		parser.addSQL(" ORDER BY FILE_ID ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}

}
