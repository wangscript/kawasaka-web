package com.linkage.home.view.about;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.home.bean.NewsHomeBean;


public abstract class About extends AppSafePage {
	
	public abstract void setInfo(IData info);
	public abstract void setCidName(String cidName);
	
	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String cidName = new String("");
			IData info = new DataMap();
			System.out.println(getPageName());
			if(getPageName().equals("About")){
				NewsHomeBean newsBean = new NewsHomeBean();
				IData params = new DataMap();
				params.put("NEW_CID", "21");
				params.put("LIMIT", "1");
				IDataset news = newsBean.queryNews(pd, params, pd.getPagination());
				info = news.size()>0?news.getData(0):null;
				if(info != null){
					cidName = Utility.getStaticValue(pd, "CATEGORY_LIST", info.getString("NEW_CID"));
				}
			}else{
				String new_id = pd.getData().getString("NEW_ID","");
				if("".equals(new_id)){
					common.error("����NEW_IDû�д��룡");
				}
				NewsHomeBean newsBean = new NewsHomeBean();
				IData params = new DataMap();
				params.put("NEW_ID", new_id);
				IDataset news = newsBean.queryNews(pd, params, pd.getPagination());
				info = news.size()>0?news.getData(0):null;
				if(info == null){
					common.error("�����Ų����ڣ�");
				}
				cidName = Utility.getStaticValue(pd, "CATEGORY_LIST", info.getString("NEW_CID"));
				if("".equals(cidName)){
					common.error("����C_ID����");
				}
			}
			setCidName(cidName);			
			setInfo(info);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}