/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.view.categorys;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.appframework.data.TreeItem;
import com.linkage.component.PageData;
import com.linkage.sys.bean.categorys.CategorysBean;
import com.linkage.sys.bean.files.FilesBean;
import com.linkage.sys.view.common.CashierBasePage;

/**
 * @author wuwl
 *
 */
public abstract class CategorysList extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setTreeData(TreeItem treeData);
	/**
	 * �����������ҵ�����Bean
	 */
	private CategorysBean bean = new CategorysBean();
	/**
	 * ҳ���ʼ������
	 * @param cycle
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		TreeItem root = new TreeItem("root", null, null, null);	
		bean.getNodes(pd,root,"1");
		setTreeData(root);
	}
}
