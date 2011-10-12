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
				common.error("����C_IDû�д��룡");
			}
			String cidName = Utility.getStaticValue(pd, "CATEGORY_LIST", cid);
			if("".equals(cidName)){
				common.error("����C_ID����");
			}
			setCid(cid);
			setCidName(cidName);
			NewsHomeBean newsBean = new NewsHomeBean();
			IData params = new DataMap();
			params.put("NEW_CID", cid);
			Pagination pagination = pd.getPagination();
			//����ÿҳ��ʾ����
			int pagesize = 10; 
			pagination.setBatch(false, pagesize);
			int page = 1;
			if(pd.getData().getInt("page",1)!=1){
				page = pd.getData().getInt("page",1);
				pagination.setRange(((pd.getData().getInt("page",1)>1?pd.getData().getInt("page",1):1)-1)*pagesize, pagesize);
			}
			IDataset infos = newsBean.queryNewsList(pd, params, pagination);
			//���õ�ǰҳ��
			setP(page);
			//������ҳ��
			setPnum(infos.count()%pagesize>0?infos.count()/pagesize+1:infos.count()/pagesize);			
			setInfos(infos);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}