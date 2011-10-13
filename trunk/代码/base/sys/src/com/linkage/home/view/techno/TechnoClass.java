package com.linkage.home.view.techno;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.bean.TechnoHomeBean;


public abstract class TechnoClass extends AppSafePage {
	
	public abstract void setInfosCalculation(IDataset infosCalculation);
	public abstract void setInfo(IData info);
	public abstract void setTechnoClass(String soluName);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String techno_id = pd.getParameter("TECHNO_ID", "");
			String techno_class = pd.getParameter("TECHNO_CLASS", "");
			if("".equals(techno_id)&&"".equals(techno_class)){
				common.error("参数techno_id与techno_class都没有传入！");
			}
//			Pagination pa = pd.getPagination();
//			pa.setCount(5);
			TechnoHomeBean technoBean = new TechnoHomeBean();
			IData params = new DataMap();
			params.clear();
			IDataset infosCalculation = technoBean.queryTechnos(pd, params, null);
			for(int i=0;i<infosCalculation.size();i++){
				if(!"".equals(infosCalculation.getData(i).getString("TECHNO_CLASS", "")))
					params.put("TECHNO_CLASS", infosCalculation.getData(i).getString("TECHNO_CLASS", ""));
					infosCalculation.getData(i).put("infos", technoBean.querySolutions(pd, params, null));
			}
			
			setInfosCalculation(infosCalculation);
			
			//单个方案
			params.clear();
			params.put("TECHNO_ID", techno_id);
			params.put("TECHNO_CLASS", techno_class);
			IDataset infos = technoBean.queryTechnos(pd, params, null);
			IData info = infos.size()>0?infos.getData(0):null;
			if(info == null){
				common.error("技术名称不存在！");
			}
			String technoClass = info.getString("TECHNO_CLASS",""); 
			if("".equals(technoClass)){
				common.error("技术类别不存在或是已经失效！");
			}
			setTechnoClass(technoClass);			
			setInfo(info);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}