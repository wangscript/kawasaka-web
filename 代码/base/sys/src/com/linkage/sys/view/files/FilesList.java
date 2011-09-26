/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.view.files;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.component.bean.adm.UtilBean;
import com.linkage.sys.bean.files.FilesBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wuwl
 *
 */
public abstract class FilesList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责管理这块的业务操作Bean
	 */
	private FilesBean filesBean = new FilesBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		
	}
	/**
	 * 查询新闻信息
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void queryFiles(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		IDataset items = filesBean.queryFiles(pd, params, pd.getPagination());
		this.setInfos(items);
		this.setConditions(params);
	}
	
	public void deleteFiles(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		UtilBean bean = new UtilBean();
        String file_id = pd.getParameter("file_id");
        bean.deleteFile(pd, file_id);
        queryFiles(cycle);
	}
}
