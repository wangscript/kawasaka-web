/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.home.bean;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppBean;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.dao.GroupHomeDao;
import com.linkage.home.dao.NewsHomeDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class GroupHomeBean extends AppBean{
	

	/**
	 * 查询部门信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryGroups(PageData pd,IData data, Pagination pagination) throws Exception {
		GroupHomeDao dao = new GroupHomeDao(pd);
		return dao.queryGroup(pd, data, pagination);
	}
	public IDataset queryGroup2(PageData pd,IData data, Pagination pagination) throws Exception {
		GroupHomeDao dao = new GroupHomeDao(pd);
		return dao.queryGroup2(pd, data, pagination);
	}
	public IDataset queryGroupDetail(PageData pd,IData data, Pagination pagination) throws Exception {
		GroupHomeDao dao = new GroupHomeDao(pd);
		return dao.queryGroupDetail(pd, data, pagination);
	}
	public IDataset queryGroupsIT(PageData pd,IData data, Pagination pagination) throws Exception {
		GroupHomeDao dao = new GroupHomeDao(pd);
		return dao.queryGroupIT(pd, data, pagination);
	}
}
