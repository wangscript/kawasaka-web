package com.linkage.sys.dao.common;

import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
/**
 * ���õ�Dao�࣬ϵͳ���е����ݿ��������ʹ�ø�DAO��ʵ�֣�
 * Ŀǰδʵ���κι��ܣ�ֻ��Ϊ���Ժ󷽱���չͨ�ù�����
 * @author chenzg
 *
 */
public class CashierAppEntity extends AppEntity {

	public CashierAppEntity(PageData pd) throws Exception
	{
		super(pd);

		//log.debug("CSAppEntity ·�����ӵ�... getRouteEparchy================[" + pd.getRouteEparchy() + "]================");
	}

	public CashierAppEntity(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
		//log.debug("CSAppEntity ָ�����ӵ�... connName================[" + connName + "]================");
	}
}