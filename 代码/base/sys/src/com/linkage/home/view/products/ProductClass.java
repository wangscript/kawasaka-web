package com.linkage.home.view.products;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.GroupHomeBean;
import com.linkage.home.bean.ProductHomeBean;


public abstract class ProductClass extends AppSafePage {
	
//	public abstract void setInfosProductClass(IDataset infosProductClass);
	public abstract void setInfosProducts(IDataset infosProducts);
//	public abstract void setInfosPatent(IDataset infosPatent);
	public abstract void setProductClass(String productClass);
	public abstract void setInfosGroups(IDataset infosGroups);

//	public abstract void setProductType(String productType);
	public abstract void setGroupId(String groupId);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String product_class = pd.getParameter("PRODUCT_CLASS", "");
			String group_id = pd.getParameter("GROUP_ID", "");
			if ("".equals(product_class)&&"".equals(group_id)) {
				common.error("参数product_class与group_id都没有传入！");
			}
//			Pagination pa = pd.getPagination();
//			pa.setCount(5);
			ProductHomeBean productBean = new ProductHomeBean();
			GroupHomeBean groupHomeBean = new GroupHomeBean();
			IData params = new DataMap();
			params.put("ITEM_FLAG", "1");
//			IDataset infosProductClass = productBean.queryProductClassLists(pd, params, null);
//			for(int i=0;i<infosProductClass.size();i++){
//				if(!"".equals(infosProductClass.getData(i).getString("PRODUCT_CLASS", "")))
//					params.put("PRODUCT_CLASS", infosProductClass.getData(i).getString("ID", ""));
//				infosProductClass.getData(i).put("infos", productBean.queryProductTypeLists(pd, params, null));
//			}
//			
//			setInfosProductClass(infosProductClass);
			
			params.put("GROUP_CLASS", "部门");
			IDataset infosGroups = groupHomeBean.queryNormalGroups(pd, params, null);
			for(int i=0;i<infosGroups.size();i++){
				if(!"".equals(infosGroups.getData(i).getString("GROUP_ID", "")))
					params.put("GROUP_ID", infosGroups.getData(i).getString("GROUP_ID", ""));
				infosGroups.getData(i).put("infos", productBean.queryProductClassLists(pd, params, null));
			}
			
			setInfosGroups(infosGroups);
			params.clear();
			params.put("ITEM_FLAG", "1");
			params.put("PRODUCT_CLASS", product_class);
			params.put("GROUP_ID", group_id);
			IDataset infosShowProductTypes = productBean.queryProductTypeLists(pd, params, null);
			
			IDataset infosProducts = new DatasetList();
			for(int i=0;i<infosShowProductTypes.size();i++)
			{
				IData typeData = infosShowProductTypes.getData(i);
				typeData.put("PRODUCT_TYPE_ID", typeData.getString("ID", ""));
				params.put("PRODUCT_TYPE", typeData.getString("ID", ""));
				IDataset infosHomeShowProduct = productBean.queryProducts(pd, params, null);
				IDataset tmpProducts = new DatasetList();
				int k=3;
				int size = infosHomeShowProduct.size()/k<=0?1:infosHomeShowProduct.size();
				for(int j=0;j<size;j++)
				{
					IDataset tmpDataset = new DatasetList();
					for(int l=j*k;l<k*(j+1)&&l<infosHomeShowProduct.size();l++)
					{
						tmpDataset.add(infosHomeShowProduct.getData(l));
					}
					IData tmpData = new DataMap();
					tmpData.put("infos", tmpDataset);
					tmpProducts.add(tmpData);
				}
				
				typeData.put("typeProductInfos", tmpProducts);
				infosProducts.add(typeData);
				
			}
//			int k=3;
//			int size = infosHomeShowProduct.size()/k<=0?1:infosHomeShowProduct.size();
//			for(int i=0;i<size;i++)
//			{
//				IDataset tmpDataset = new DatasetList();
//				for(int j=i*k;j<k*(i+1)&&j<infosHomeShowProduct.size();j++)
//				{
//					tmpDataset.add(infosHomeShowProduct.getData(j));
//				}
//				IData tmpData = new DataMap();
//				tmpData.put("infos", tmpDataset);
//				infosProducts.add(tmpData);
//			}
			setInfosProducts(infosProducts);
			setProductClass(product_class);
			setGroupId(group_id);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}