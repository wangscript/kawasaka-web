package com.linkage.sys.dao.common;

import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
/**
 * 公用的Dao类，系统所有的数据库连接最好使用该DAO类实现，
 * 目前未实现任何功能，只是为了以后方便扩展通用功能用
 * @author chenzg
 *
 */
public class CashierAppEntity extends AppEntity {

	public CashierAppEntity(PageData pd) throws Exception
	{
		super(pd);

		//log.debug("CSAppEntity 路由连接到... getRouteEparchy================[" + pd.getRouteEparchy() + "]================");
	}

	public CashierAppEntity(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
		//log.debug("CSAppEntity 指定连接到... connName================[" + connName + "]================");
	}
}