package com.linkage.home.view;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.GroupHomeBean;
import com.linkage.home.bean.NewsHomeBean;
import com.linkage.home.bean.ProductHomeBean;


public abstract class Index extends AppSafePage {
	
	public abstract void setNotes(IDataset notes);
	public abstract void setThumbs(IDataset thumbs);
	public abstract void setLastnewss(IDataset lastnewss);

	public abstract void setProducts(IDataset products);

	public abstract void setDeps(IDataset deps);


	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			NewsHomeBean newsBean = new NewsHomeBean();
			IData params = new DataMap();
			//����
			params.clear();
			params.put("LIMIT", "3");
			params.put("NEW_CID", "14");
			IDataset notes = newsBean.queryNews(pd, params, pd.getPagination());
			setNotes(notes);
			//��������
			params.clear();
			params.put("LIMIT", "4");
			IDataset thumbs = newsBean.queryThumbNews(pd, params, pd.getPagination());
			setThumbs(thumbs);
			//�������Ŷ�̬
			params.clear();
			params.put("LIMIT", "10");
			IDataset lastnewss = newsBean.queryLastNews(pd, params, pd.getPagination());
			setLastnewss(lastnewss);
			//��Ʒ�б�
			ProductHomeBean productBean = new ProductHomeBean();
			params.clear();
			params.put("ITEM_FLAG", "1");
			IDataset infosProductClass = productBean.queryProducts(pd, params, null);
			IDataset products = new DatasetList();
		    IData data = new DataMap();
		    data.put("PRODUCT_ID", "00");
		    data.put("PRODUCT_NAME", "��ѡ���Ʒ");
		    products.add(data);
		    products.addAll(infosProductClass);
		    log.debug("-products-"+products);
		    setProducts(products);
	
			GroupHomeBean groupBean = new GroupHomeBean();
			IDataset groups = groupBean.queryGroups(pd, params, pd.getPagination());
			IDataset deps = groupBean.queryGroup2(pd, params, pd.getPagination());
			//setInfos(groups);
			setDeps(deps);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}