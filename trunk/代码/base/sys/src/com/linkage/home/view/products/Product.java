package com.linkage.home.view.products;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.ProductHomeBean;


public abstract class Product extends AppSafePage {
	
	public abstract void setInfosProductClass(IDataset infosProductClass);
	public abstract void setInfosProducts(IDataset infosProducts);
//	public abstract void setInfosPatent(IDataset infosPatent);


	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
//			Pagination pa = pd.getPagination();
//			pa.setCount(5);
			ProductHomeBean productBean = new ProductHomeBean();
			IData params = new DataMap();
			params.clear();
			params.put("ITEM_FLAG", "1");
			IDataset infosProductClass = productBean.queryProductClassLists(pd, params, null);
			for(int i=0;i<infosProductClass.size();i++){
				if(!"".equals(infosProductClass.getData(i).getString("PRODUCT_CLASS", "")))
					params.put("PRODUCT_CLASS", infosProductClass.getData(i).getString("ID", ""));
				infosProductClass.getData(i).put("infos", productBean.queryProductTypeLists(pd, params, null));
			}
			
			setInfosProductClass(infosProductClass);
			params.clear();
			params.put("ITEM_FLAG", "1");
			params.put("HOME_SHOW", "1");
			IDataset infosHomeShowProduct = productBean.queryProducts(pd, params, null);
			IDataset infosProducts = new DatasetList();
			int k=3;
			int size = infosHomeShowProduct.size()/k<=0?1:infosHomeShowProduct.size();
			for(int i=0;i<size;i++)
			{
				IDataset tmpDataset = new DatasetList();
				for(int j=i*k;j<k*(i+1)&&j<infosHomeShowProduct.size();j++)
				{
//					String url = common.getProperty("sysconfig.xml", "hostUrlConfig/url/productUrl", "");
//					String urlStr = infosHomeShowProduct.getData(j).getString("PRODUCT_PICTURE", "").trim();
//					if(!"".equals(urlStr))
//					{
//					infosHomeShowProduct.getData(j).put("PRODUCT_IMG", url+"/image?file_id="+urlStr.split(":")[0]);
//					}
					tmpDataset.add(infosHomeShowProduct.getData(j));
				}
				IData tmpData = new DataMap();
				tmpData.put("infos", tmpDataset);
				infosProducts.add(tmpData);
			}
			setInfosProducts(infosProducts);
			log.debug("-infosProducts--"+infosProducts);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}