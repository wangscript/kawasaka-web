/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.sys.bean.categorys;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.appframework.data.TreeItem;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.bean.common.CashierBaseBean;
import com.linkage.sys.dao.categorys.CategorysDao;
import com.linkage.sys.dao.files.FilesDao;
import com.linkage.sys.dao.news.NewsDao;

/**
 * @author wuwl
 * 
 */
public class CategorysBean extends CashierBaseBean{
	

	
	/**
	 * 查询节点信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryRoots(PageData pd,IData data, Pagination pagination) throws Exception {
		CategorysDao dao = new CategorysDao(pd);
		return dao.queryRoots(pd, data, pagination);
	}

	public void getNodes(PageData pd, TreeItem root, String type) throws Exception {
		/*
		TreeItem node1_1 = new TreeItem("node", root1, "投诉类型", null);		
		TreeItem node1_1_1 = new TreeItem("node_desktop", node1_1, "投诉申告", "alert('train.class.ClassHome', 'init')");
		
		TreeItem node1_1_1_1 = new TreeItem("node_desktop", node1_1_1, "其它渠道", "redirectTo('train.class.ClassHome', 'init')");
		
		new TreeItem("nod_desktop_1", node1_1_1_1, "省公司网站", "redirectTo('train.class.ClassHome', 'init')");
		new TreeItem("nod_desktop_2", node1_1_1_1, "集团网站", "redirectTo('train.class.ClassHome', 'init')");
		new TreeItem("nod_desktop_3", node1_1_1_1, "其它渠道", "redirectTo('train.class.ClassHome', 'init')");
		
		TreeItem node2_1 = new TreeItem("node_basic", node1_1, "用户建议", "redirectTo('train.class.ClassBasicMsg', 'queryClass')");
		new TreeItem("node_basic", node2_1, "网络类建议", "redirectTo('train.class.ClassBasicMsg', 'queryClass')");
		*/
		CategorysDao dao = new CategorysDao(pd);
		IData data = new DataMap();
		Pagination pagination = new Pagination();
		IDataset iroot = dao.queryRoots(pd, data, pagination);
		//放入主节点
		TreeItem node = new TreeItem(iroot.getData(0).getString("DATA_ID"), root, iroot.getData(0).getString("DATA_NAME"), null);
		getCNodes(pd, node, type, iroot.getData(0).getString("DATA_ID"));
	}
	
	public void getCNodes(PageData pd, TreeItem root, String type, String NodeId) throws Exception{
		CategorysDao dao = new CategorysDao(pd);
		IData data = new DataMap();
		data.put("PID", NodeId);
		Pagination pagination = new Pagination();
		IDataset rs = dao.queryNodes(pd, data, pagination);
		for(int i = 0; i<rs.size(); i++){
			TreeItem node = null;
			if(type.equals(rs.getData(i).getString("VALID_FLAG"))){
				node = new TreeItem(rs.getData(i).getString("DATA_ID"), root, rs.getData(i).getString("DATA_NAME"), "setReturnValue(this.id,this.title)");
			}
			else{
				node = new TreeItem(rs.getData(i).getString("DATA_ID"), root, rs.getData(i).getString("DATA_NAME"), null);
			}
			
			getCNodes(pd, node, type, rs.getData(i).getString("DATA_ID"));
		}
	}

}
