package com.linkage.home.view.products;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.ProductHomeBean;


public abstract class ProductClass extends AppSafePage {
	
	public abstract void setInfosProductClass(IDataset infosProductClass);
	public abstract void setInfosProducts(IDataset infosProducts);
//	public abstract void setInfosPatent(IDataset infosPatent);
	public abstract void setProductClass(String productClass);
	public abstract void setProductType(String productType);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String product_class = pd.getParameter("PRODUCT_CLASS", "");
			String product_type = pd.getParameter("PRODUCT_TYPE", "");
			if ("".equals(product_class)&&"".equals(product_type)) {
				common.error("参数product_class与product_type都没有传入！");
			}
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
			params.put("PRODUCT_CLASS", product_class);
			params.put("PRODUCT_TYPE", product_type);
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
			setProductClass(product_class);
			setProductType(product_type);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}