package com.linkage.sys.view.group;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.common.bean.util.UtilDAO;
import com.linkage.component.PageData;
import com.linkage.sys.bean.group.GroupBean;
import com.linkage.sys.bean.product.ProductBean;
import com.linkage.sys.view.common.CashierBasePage;

public abstract class GroupAdd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:wull
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = getPageData();
		IData conditions = pd.getData("add", true);

	}
	
	/**
	 * 负责产品管理这块的业务操作Bean
	 */
	private GroupBean groupBean = new GroupBean();
	
	/**
	 * 查询所有公司/部门
	 * @param cycle
	 * @throws Exception
	 */
	public void saveGroup(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		
		UtilDAO dao = new UtilDAO(pd);
		String msg = "新增成功！";
		IData params = pd.getData("add", true);
		String group_name = params.getString("GROUP_NAME");
		IData param = new DataMap();
		param.put("GROUP_NAME", group_name);
		Boolean exist = this.groupBean.existsGroup(pd, param, null);
		if(exist){
			common.error("部门名称已经存在,请请重新输入！");
			return;
		}		
		dao.insert("TD_M_GROUP", params);
		redirectToMsg(msg);
	}
}
