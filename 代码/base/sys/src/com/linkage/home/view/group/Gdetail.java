package com.linkage.home.view.group;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.home.bean.GroupHomeBean;
import com.linkage.home.bean.NewsHomeBean;


public abstract class Gdetail extends AppSafePage {
	
	public abstract void setInfos(IDataset infos);
	public abstract void setDeps(IDataset deps);
	public abstract void setDept(IData dept);
	
	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String group_id = pd.getData().getString("GROUP_ID","");
			if("".equals(group_id)){
				common.error("����GROUP_IDû�д��룡");
			}
			GroupHomeBean groupBean = new GroupHomeBean();
			IData params = new DataMap();
			params.put("GROUP_ID", group_id);
			IDataset groups = groupBean.queryGroupDetail(pd, params, pd.getPagination());
			IData dept = groups.size()>0?groups.getData(0):null;
			if(dept == null){
				common.error("�ò��Ų����ڣ�");
			}		log.debug(pd.getData().getString("GROUP_ID",""));
			setDept(dept);
			
			IData params2 = new DataMap();
			params.clear();
			IDataset groups2 = groupBean.queryGroups(pd, params2, pd.getPagination());
			IDataset deps = groupBean.queryGroup2(pd, params, pd.getPagination());
			setInfos(groups2);
			setDeps(deps);
			
			
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}