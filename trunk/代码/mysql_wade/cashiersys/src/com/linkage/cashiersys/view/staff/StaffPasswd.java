/**
 * 
 * @author:chenzg
 * @date:2010-5-12
 */
package com.linkage.cashiersys.view.staff;

import org.apache.tapestry.IRequestCycle;

import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.cashiersys.bean.staff.StaffBean;
import com.linkage.cashiersys.view.common.CashierBasePage;
import com.linkage.component.PageData;

/**
 * @author chenzg
 *
 */
public abstract class StaffPasswd extends CashierBasePage{
	public abstract void setInfo(IData info);
	public abstract void setInfos(IDataset infos);
	public abstract void setConditions(IData conditions);
	
	/**
	 * 负责员工管理这块的业务操作Bean
	 */
	private StaffBean staffBean = new StaffBean();
	/**
	 * 页面初始化参数
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void init(IRequestCycle cycle) throws Exception {
		String alertInfo = "密码长度至少1位，最长12位！";
		this.setAlertInfo(alertInfo);
	}
	/**
	 * 修改用户密码
	 * @param cycle
	 * @throws Exception
	 * @author:chenzg
	 * @date:2010-5-12
	 */
	public void updateStaffPasswd(IRequestCycle cycle) throws Exception {
		String msg = "";
		PageData pd = this.getPageData();
		IData params = pd.getData("cond", true);
		String operType = pd.getParameter("cond_OPER_TYPE", "");
		params.put("STAFF_ID", pd.getContext().getStaffId());
		
		//密码修改
		if("A".equals(operType)){
			int counts = staffBean.updateStaffPasswd(pd, params);
			//修改失败
			if(counts < 1){
				common.error("密码修改失败！");
			}
			msg = "密码修改成功！";
		}
		//密码重置
		else if("B".equals(operType)){
			int counts = staffBean.resetStaffPasswd(pd, params);
			//重置失败
			if(counts < 1){
				common.error("密码重置失败！");
			}
			msg = "密码重置成功，您的密码重置为:12346";
		}else{
			common.error("密码操作失败！");
		}
		
		redirectToMsg(msg);
	}
}
