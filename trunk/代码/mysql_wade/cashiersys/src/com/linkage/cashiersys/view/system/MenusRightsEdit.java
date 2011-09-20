/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.system;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.system.RightsBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;

/**
 * @author chenzg
 *
 */
public abstract class MenusRightsEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setAvailableItems(IDataset availableItems);
	public abstract void setSelectedItems(IDataset selectedItems);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private RightsBean rightsBean = new RightsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		Pagination pagination = pd.getPagination();
		IData data = new DataMap();
		data.put("RIGHT_CODE", pd.getData().getString("RIGHT_CODE"));
		String operType = pd.getParameter("operType", "");

		IDataset availableItems = this.rightsBean.queryAllChildMenus(pd);
		IDataset selectedItems =  this.rightsBean.queryMenusRights(pd, data);
		IDataset removeItems = new DatasetList();
		for(int k=0; k < selectedItems.size(); k++){
			IData sMenuItem = selectedItems.getData(k);
			for(int i = 0; i<=availableItems.size(); i++){
				IData  menuItem = availableItems.getData(i);
				if(menuItem.getString("MENU_CODE").equals(sMenuItem.getString("MENU_CODE"))){
					removeItems.add(menuItem);
					break;
				}
			}
		}
		availableItems.removeAll(removeItems);
		setAvailableItems(availableItems);
		setSelectedItems(selectedItems);		
		pd.setTransferData(pd.getData());
	}

	public void saveMenusRights(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		String Sy = pd.getParameter("operType", "");
		String[] selectedItems  = pd.getParameters("SELECTED_ITEM");
		
		IData params = new DataMap();
		params.put("RIGHT_CODE", pd.getData().getString("RIGHT_CODE"));
		params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
		params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
		this.rightsBean.updateMenusRights(pd, params ,selectedItems);
		msg = "修改权限菜单成功！";
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
