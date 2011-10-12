package com.linkage.home.view.about;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.bean.NewsHomeBean;


public abstract class AboutClass extends AppSafePage {
	
	public abstract void setInfos(IDataset infos);
	public abstract void setCidName(String cidName);
	public abstract void setCid(String cid);
	public abstract void setP(int p);
	public abstract void setPnum(int pnum);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String cid = pd.getData().getString("C_ID","");
			if(getPageName().equals("Culture")){
				cid = "22";
			}
			if(getPageName().equals("Yearbook")){
				cid = "23";
			}
			if(getPageName().equals("Honor")){
				cid = "24";
			}
			if("".equals(cid)){
				common.error("参数C_ID没有传入！");
			}
			String cidName = Utility.getStaticValue(pd, "CATEGORY_LIST", cid);
			if("".equals(cidName)){
				common.error("参数C_ID错误！");
			}
			setCid(cid);
			setCidName(cidName);
			NewsHomeBean newsBean = new NewsHomeBean();
			IData params = new DataMap();
			params.put("NEW_CID", cid);
			Pagination pagination = pd.getPagination();
			//设置每页显示行数
			int pagesize = 10; 
			pagination.setBatch(false, pagesize);
			int page = 1;
			if(pd.getData().getInt("page",1)!=1){
				page = pd.getData().getInt("page",1);
				pagination.setRange(((pd.getData().getInt("page",1)>1?pd.getData().getInt("page",1):1)-1)*pagesize, pagesize);
			}
			IDataset infos = newsBean.queryNewsList(pd, params, pagination);
			//设置当前页数
			setP(page);
			//设置总页数
			setPnum(infos.count()%pagesize>0?infos.count()/pagesize+1:infos.count()/pagesize);			
			setInfos(infos);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}