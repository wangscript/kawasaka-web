package com.linkage.home.view.news;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.sys.bean.news.NewsBean;


public abstract class NewsDetail extends AppSafePage {
	
	public abstract void setInfo(IData info);
	
	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String new_id = pd.getData().getString("NEW_ID","");
			if("".equals(new_id)){
				common.error("����NEW_IDû�д��룡");
			}
			NewsBean newsBean = new NewsBean();
			IData params = new DataMap();
			params.put("NEW_ID", new_id);
			IDataset news = newsBean.queryNews(pd, params, pd.getPagination());
			IData info = news.size()>0?news.getData(0):null;
			if(info == null){
				common.error("�����Ų����ڣ�");
			}
			setInfo(info);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}