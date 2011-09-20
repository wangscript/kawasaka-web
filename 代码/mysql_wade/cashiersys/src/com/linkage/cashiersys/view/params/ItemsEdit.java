/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.params;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.params.ParamsBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.common.bean.util.DualMgr;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class ItemsEdit extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	public abstract void setAvailableDiscounts(IDataset availableDiscounts);
	public abstract void setSelectedDiscounts(IDataset selectedDiscounts);
	
	/**
	 * 负责项目参数管理这块的业务操作Bean
	 */
	private ParamsBean paramsBean = new ParamsBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String operType = pd.getParameter("operType", "");
		if("edit".equals(operType)){
			this.queryItems(cycle);
			conditions.put("DISABLED", "true");
		}else if("add".equals(operType)){
			conditions.put("DISABLED", "false");
		}
		this.setConditions(conditions);
		pd.setTransferData(pd.getData());
	}
	/**
	 * 查询项目信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void queryItems(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData params = pd.getData();
		IDataset items = paramsBean.queryItems(pd, params, null);
		if(items!=null && items.size() == 1){
			this.setInfo(items.getData(0));
			IDataset availableDiscounts = paramsBean.queryDiscountType(pd);
			if(items.getData(0).getString("BUSI_DISCOUNT") != null){
				IDataset selectedDiscounts = new DatasetList();
				String[] discounts = items.getData(0).getString("BUSI_DISCOUNT").split(",");
				for(String discount:discounts){
					for(int i = 0; i<availableDiscounts.size(); i++){
						IData tmpdiscount = availableDiscounts.getData(i);
						if(tmpdiscount.getString("DATA_ID").equals(discount)){
							selectedDiscounts.add(tmpdiscount);
							break;
						}
					}
				}
				setSelectedDiscounts(selectedDiscounts);
				for(int i = 0; i<selectedDiscounts.size(); i++){
					availableDiscounts.remove(selectedDiscounts.getData(i));
				}
			}
			setAvailableDiscounts(availableDiscounts);
		}else{
			common.error("获取项目参数信息失败！");
		}
	}
	/**
	 * 修改项目信息
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-13
	 */
	public void saveItems(IRequestCycle cycle) throws Exception {
		PageData pd = this.getPageData();
		IData conditions = new DataMap();
		String msg = "";
		
		String operType = pd.getParameter("operType", "");
		//修改
		if("edit".equals(operType)){
			IData params = pd.getData("edit", true);
			params.put("BUSI_ITEM_FEE", params.getDouble("BUSI_ITEM_FEE", 0)*100);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			String[] selectedItems  = pd.getParameters("SELECTED_ITEM");
			StringBuffer sb = new StringBuffer();
			for(String s:selectedItems){
				if(!"".equals(s)){
					sb.append(s + ',');
				}
			}
			if(sb.length() != 0){
				sb.delete(sb.length()-1, sb.length());
			}
			params.put("BUSI_DISCOUNT", sb.toString());
			this.paramsBean.updateItems(pd, params);
			msg = "项目信息修改成功！";
		}
		//新增
		else if("add".equals(operType)){
			IData params = pd.getData("add", true);
			params.put("BUSI_ITEM_FEE", params.getDouble("BUSI_ITEM_FEE", 0)*100);
			params.put("UPDATE_STAFF_ID", pd.getContext().getStaffId());
			params.put("UPDATE_TIME", DualMgr.getSysDate(pd));
			params.put("BUSI_ITEM_CODE", "BIT_" + DualMgr.getSeqId(pd, "seq_item_id"));
			IDataset dataset = new DatasetList();
			dataset.add(params);
			this.paramsBean.addItems(pd, dataset);
			msg = "新增项目信息成功！";
		}
		//返回项目参数查询界面
		StringBuilder  strScript = new StringBuilder("");
		strScript.append("parent.document.getElementById('bquery').click();");
		redirectToMsgByScript(msg, strScript.toString());
	}
}
