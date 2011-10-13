/**
 * 
 * @author:wuwl
 * @date:2010-5-12
 */
package com.linkage.home.dao;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wuwl
 *
 */
public class ProductHomeDao extends AppEntity{
	public ProductHomeDao(PageData pd) throws Exception
	{
		super(pd);
	}

	public ProductHomeDao(PageData pd, String connName) throws Exception
	{
		super(pd, connName);
	}

	/**
	 * 查询方案解决信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset querySolutions(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_SOLUTION t  where (1 = 1) ");
		parser.addSQL(" and SOLU_ID=:SOLU_ID ");
		parser.addSQL(" and TECHNO_CLASS=:TECHNO_CLASS ");
		parser.addSQL(" ORDER BY SOLU_NAME ");
		IDataset dataset = dao.queryList(parser,pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	/**
	 * 查询技术信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryTechnos(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_TECHNO t  where (1 = 1) ");
		parser.addSQL(" and TECHNO_ID=:TECHNO_ID ");
		parser.addSQL(" and TECHNO_CLASS=:TECHNO_CLASS ");
		parser.addSQL(" ORDER BY TECHNO_CLASS ");
		IDataset dataset = dao.queryList(parser,pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	/**
	 * 查询产品大类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductClassLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_PRODUCT_CLASS t  where (1 = 1) ");
		parser.addSQL(" and T.ID=:ID ");
		parser.addSQL(" and T.PRODUCT_CLASS =:PRODUCT_CLASS ");
		parser.addSQL(" and T.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and T.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" order by t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	/**
	 * 查询产品小类信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductTypeLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.* from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
		parser.addSQL("and u.ID = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.ID=:ID ");
		parser.addSQL(" and t.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE =:PRODUCT_TYPE ");
		parser.addSQL(" and t.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	/**
	 * 查询产品首页信息
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProducts(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
    	parser.addSQL("select t.* from TD_M_PRODUCT t   INNER JOIN TD_M_PRODUCT_CLASS u ON (t.PRODUCT_CLASS=u.ID AND u.ITEM_FLAG='1') ");
		parser.addSQL(" LEFT JOIN TD_M_PRODUCT_TYPE v ON  (t.PRODUCT_TYPE=v.ID AND v.ITEM_FLAG='1') ");
		parser.addSQL(" where (1 = 1) ");
//		parser.addSQL(" and u.PRODUCT_CLASS = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.PRODUCT_ID=:PRODUCT_ID ");
		parser.addSQL(" and t.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE = :PRODUCT_TYPE");
		parser.addSQL(" and t.PRODUCT_NAME like concat('%',:PRODUCT_NAME,'%') ");
		parser.addSQL(" and t.PRODUCT_MODEL like concat('%',:PRODUCT_MODEL,'%') ");
		parser.addSQL(" and t.PRODUCT_FACTORY like concat('%',:PRODUCT_FACTORY,'%') ");
		parser.addSQL(" and t.PRODUCT_GOOD=:PRODUCT_GOOD ");
		parser.addSQL(" and t.HOME_SHOW=:HOME_SHOW ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,v.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	
	
	/**
	 * 查询新闻信息
	 * @param pd
	 * @param data
	 * @param pagination
	 * @return
	 * @throws Exception
	 * @author:wuwl
	 * @date:2010-5-15
	 */
	public IDataset queryNews(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from tf_f_news t  where (1 = 1) ");
		parser.addSQL(" and NEW_ID=:NEW_ID ");
		parser.addSQL(" and NEW_CID=:NEW_CID ");
		parser.addSQL(" ORDER BY NEW_ID ");
//		parser.addSQL(" LIMIT :LIMIT");
		IDataset dataset = dao.queryList(parser,pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	public IDataset queryNewsList(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from tf_f_news t  where (1 = 1) ");
		parser.addSQL(" and NEW_ID=:NEW_ID ");
		parser.addSQL(" and NEW_CID=:NEW_CID ");
		parser.addSQL(" ORDER BY NEW_ID ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	public IDataset queryLastNews(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from tf_f_news t  where (1 = 1) ");
		parser.addSQL(" and NEW_TYPE=0 ");		
		parser.addSQL(" and (NEW_CID=11 or NEW_CID=12 or NEW_CID=13) ");
		parser.addSQL(" ORDER BY NEW_ID ");
//		parser.addSQL(" LIMIT :LIMIT");
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;		
		
	}
	
	public IDataset queryThumbNews(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		//dao.executeQuery("set @rownum=0");
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select NEW_ID, NEW_TITLE, NEW_WRITER, NEW_DESCRIPTION, NEW_THUMB, UPDATE_TIME from tf_f_news t  where (1 = 1) ");
		parser.addSQL(" and NEW_TYPE=1 ");		
		parser.addSQL(" and (NEW_CID=11 or NEW_CID=12 or NEW_CID=13) ");
		parser.addSQL(" ORDER BY NEW_ID ");
//		parser.addSQL(" LIMIT :LIMIT");

		/*
		StringBuffer sql = new StringBuffer();
		sql.append("set @rownum=0;select NEW_ID, NEW_TITLE, NEW_WRITER, NEW_DESCRIPTION, NEW_THUMB, UPDATE_TIME,@rownum:=@rownum+1 as rownum from tf_f_news t  where (1 = 1) ");
		sql.append(" and NEW_TYPE=1 ");
		sql.append(" and (NEW_CID=11 or NEW_CID=12 or NEW_CID=13) ");
		sql.append(" ORDER BY NEW_ID ");
		if(!"".equals(data.getString("LIMIT",""))){
			sql.append(" LIMIT "+data.getString("LIMIT")+" ;");
		}else{
			sql.append(";");
		}
		
		IDataset dataset = dao.queryList(sql.toString());*/
		IDataset dataset = dao.queryList(parser);
		return dataset == null? new DatasetList() : dataset;		
		
	}	
}
