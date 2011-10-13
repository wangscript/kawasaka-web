package com.linkage.home.view.products;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.home.bean.ProductHomeBean;

public abstract class ProductDetail extends AppSafePage {

	public abstract void setInfosProductClass(IDataset infosProductClass);

	public abstract void setInfo(IData info);

	public void pageBeginRender(PageEvent event) {
		PageData pd;
		try {
			pd = getPageData();
			String product_id = pd.getParameter("PRODUCT_ID", "");
			if ("".equals(product_id)) {
				common.error("参数product_id没有传入！");
			}

			// Pagination pa = pd.getPagination();
			// pa.setCount(5);
			ProductHomeBean productBean = new ProductHomeBean();
			IData params = new DataMap();
			params.clear();
			params.put("ITEM_FLAG", "1");
			IDataset infosProductClass = productBean.queryProductClassLists(pd,
					params, null);
			for (int i = 0; i < infosProductClass.size(); i++) {
				if (!"".equals(infosProductClass.getData(i).getString(
						"PRODUCT_CLASS", "")))
					params.put("PRODUCT_CLASS", infosProductClass.getData(i)
							.getString("ID", ""));
				infosProductClass.getData(i).put("infos",
						productBean.queryProductTypeLists(pd, params, null));
			}

			setInfosProductClass(infosProductClass);
			params.clear();
			params.put("ITEM_FLAG", "1");
			params.put("PRODUCT_ID", product_id);
			IDataset infos = productBean.queryProducts(pd, params, null);
			IData info = infos.size() > 0 ? infos.getData(0) : null;
			if (info == null) {
				common.error("产品不存在！");
			}
//			String url = common.getProperty("sysconfig.xml",
//					"hostUrlConfig/url/productUrl", "");
//			String urlStr = info.getString("PRODUCT_PICTURE", "").trim();
//			if (!"".equals(urlStr)) {
//				info.put("PRODUCT_IMG", url + "/image?file_id="
//						+ urlStr.split(":")[0]);
//			}
			info.put("RETAIL_PRICE", "".equals(info.getString("RETAIL_PRICE", "").trim())?"0":info.getString("RETAIL_PRICE", ""));
			info.put("PRIVILEGE_PRICE", "".equals(info.getString("PRIVILEGE_PRICE", "").trim())?"0":info.getString("PRIVILEGE_PRICE", ""));
			info.put("VIP_PRICE", "".equals(info.getString("VIP_PRICE", "").trim())?"0":info.getString("VIP_PRICE", ""));
			setInfo(info);
		} catch (Exception e) {
			log.error("初始化页面执行失败！错误情况:" + e);
		} finally {
			super.pageBeginRender(event);
		}
	}
}