package com.linkage.home.view.techno;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.bean.TechnoHomeBean;


public abstract class Techno extends AppSafePage {
	
	public abstract void setInfosCalculation(IDataset infosCalculation);
//	public abstract void setInfosProcess(IDataset infosProcess);
//	public abstract void setInfosPatent(IDataset infosPatent);


	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
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
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}