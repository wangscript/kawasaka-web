/**
 * 
 * @author:wull
 * @date:2011-9-22
 */
package com.linkage.sys.dao.project;

import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;
import com.linkage.dbframework.util.Pagination;
import com.linkage.dbframework.util.Parameter;
import com.linkage.sys.dao.common.CashierAppEntity;

/**
 * @author wull
 * 
 */
public class ProjectDao extends CashierAppEntity {
	public ProjectDao(PageData pd) throws Exception {
		super(pd);
	}

	public ProjectDao(PageData pd, String connName) throws Exception {
		super(pd, connName);
	}

	
	public IDataset queryProjectLists(PageData pd, IData data, Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
    	SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from wt_b_project t  where (1 = 1) ");
		parser.addSQL(" and T.PROJECT_NAME=:PROJECT_NAME ");
		parser.addSQL(" and T.MODEL = :MODEL ");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null? new DatasetList() : dataset;
	}
	
	
	/**
	 * 查询产品大类信息
	 * 
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductClassLists(PageData pd, IData data,Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		SQLParser parser = new SQLParser(data);
		parser.addSQL("select * from TD_M_PRODUCT_CLASS t ");
		parser.addSQL(" inner join TD_M_GROUP g on (t.group_id = g.group_id and g.group_class = '部门' )");
		parser.addSQL(" where (1 = 1) ");
		parser.addSQL(" and T.ID=:ID ");
		parser.addSQL(" and T.GROUP_ID=:GROUP_ID ");
		parser.addSQL(" and T.PRODUCT_CLASS like concat('%',:PRODUCT_CLASS,'%') ");
		parser.addSQL(" and T.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and T.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" order by t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null ? new DatasetList() : dataset;
	}

	/**
	 * 只查询产品大类类型信息
	 * 
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductClass(PageData pd, IData data,Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.ID,t.PRODUCT_CLASS from TD_M_PRODUCT_CLASS t ");
		parser.addSQL(" inner join TD_M_GROUP g on (t.group_id = g.group_id and g.group_class = '部门' ) ");
		parser.addSQL(" where (1 = 1) ");
		parser.addSQL(" and T.ID=:ID ");
		parser.addSQL(" and T.GROUP_ID=:GROUP_ID ");
		parser.addSQL(" and T.PRODUCT_CLASS like concat('%',:PRODUCT_CLASS,'%') ");
		parser.addSQL(" and T.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and T.ITEM_FLAG=:ITEM_FLAG ");
		parser.addSQL(" order by t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null ? new DatasetList() : dataset;
	}

	// /**
	// * 查询产品小类信息
	// * @param pd
	// * @param data
	// * @return
	// * @throws Exception
	// * @author:wull
	// */
	// public IDataset queryProductTypeLists(PageData pd, IData data, Pagination
	// pagination) throws Exception {
	// CashierAppEntity dao = new CashierAppEntity(pd);
	// SQLParser parser = new SQLParser(data);
	// parser.addSQL("select t.* from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
	// parser.addSQL("and u.PRODUCT_CLASS = t.PRODUCT_CLASS ");
	// parser.addSQL(" and t.ID=:ID ");
	// parser.addSQL(" and u.PRODUCT_CLASS = :PRODUCT_CLASS");
	// parser.addSQL(" and t.PRODUCT_TYPE like concat('%',:PRODUCT_TYPE,'%') ");
	// parser.addSQL(" and t.CLASSORDER=:CLASSORDER ");
	// parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
	// parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
	// parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
	// IDataset dataset = dao.queryList(parser, pagination);
	// return dataset == null? new DatasetList() : dataset;
	// }

	/**
	 * 查询产品小类信息
	 * 
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductTypeLists(PageData pd, IData data,Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.* from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
		parser.addSQL("and u.ID = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.ID=:ID ");
		parser.addSQL(" and u.ID = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE like concat('%',:PRODUCT_TYPE,'%') ");
		parser.addSQL(" and t.CLASSORDER=:CLASSORDER ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null ? new DatasetList() : dataset;
	}

	/**
	 *只 查询产品小类类型信息
	 * 
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductTypes(PageData pd, IData data,Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.ID,t.PRODUCT_TYPE from TD_M_PRODUCT_CLASS u,TD_M_PRODUCT_TYPE t  where (1 = 1) ");
		parser.addSQL("and u.ID = t.PRODUCT_CLASS ");
		// parser.addSQL(" and u.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" and u.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,t.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null ? new DatasetList() : dataset;
	}

	/**
	 * 查询产品信息
	 * 
	 * @param pd
	 * @param data
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public IDataset queryProductLists(PageData pd, IData data,Pagination pagination) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		SQLParser parser = new SQLParser(data);
		parser.addSQL("select t.* from TD_M_PRODUCT t   INNER JOIN TD_M_PRODUCT_CLASS u ON (t.PRODUCT_CLASS=u.ID AND u.ITEM_FLAG='1') ");
		parser.addSQL(" LEFT JOIN TD_M_PRODUCT_TYPE v ON  (t.PRODUCT_TYPE=v.ID AND v.ITEM_FLAG='1') ");
		parser.addSQL(" INNER JOIN TD_M_GROUP g ON  (t.GROUP_ID=g.GROUP_ID AND g.GROUP_CLASS='部门') ");
		parser.addSQL(" where (1 = 1) ");
		// parser.addSQL(" and u.ID = t.PRODUCT_CLASS ");
		parser.addSQL(" and t.PRODUCT_ID=:PRODUCT_ID ");
		parser.addSQL(" and t.GROUP_ID=:GROUP_ID ");
		parser.addSQL(" and t.PRODUCT_CLASS = :PRODUCT_CLASS");
		parser.addSQL(" and t.PRODUCT_TYPE = :PRODUCT_TYPE");
		parser.addSQL(" and t.PRODUCT_NAME like concat('%',:PRODUCT_NAME,'%') ");
		parser.addSQL(" and t.PRODUCT_MODEL like concat('%',:PRODUCT_MODEL,'%') ");
		parser.addSQL(" and t.PRODUCT_FACTORY like concat('%',:PRODUCT_FACTORY,'%') ");
		parser.addSQL(" and t.PRODUCT_GOOD=:PRODUCT_GOOD ");
		parser.addSQL(" and t.HOME_SHOW=:HOME_SHOW ");
		parser.addSQL(" and t.ITEM_FLAG= :ITEM_FLAG ");
		parser.addSQL(" order by u.CLASSORDER desc,v.CLASSORDER desc");
		IDataset dataset = dao.queryList(parser, pagination);
		return dataset == null ? new DatasetList() : dataset;
	}

	/**
	 * 新增产品大类信息
	 * 
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProductClassList(PageData pd, IDataset dataset) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		// String sql =
		// "insert into TD_M_PRODUCT_CLASS( PRODUCT_CLASS, CLASSORDER, ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?, ?, ?, ?,?)";
		// Parameter[] params = new Parameter[dataset.size()];
		// for (int i=0; i<params.length; i++) {
		// params[i] = new Parameter();
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
		// params[i].add(((IData)dataset.get(i)).getString("CLASSORDER",null));
		// params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
		// }
		// executeBatch(sql, params);
		for (int i = 0; i < dataset.size(); i++) { dataset.getData(i).remove("ID");
		}
		dao.insert("TD_M_PRODUCT_CLASS", dataset);
	}

	/**
	 * 新增产品小类信息
	 * 
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProductType(PageData pd, IDataset dataset) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		// String sql =
		// "insert into TD_M_PRODUCT_TYPE( PRODUCT_CLASS,PRODUCT_TYPE, CLASSORDER, ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?,?, ?, ?, ?,?)";
		// Parameter[] params = new Parameter[dataset.size()];
		// for (int i=0; i<params.length; i++) {
		// params[i] = new Parameter();
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_TYPE",null));
		// params[i].add(((IData)dataset.get(i)).getString("CLASSORDER","0"));
		// params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
		// }
		// executeBatch(sql, params);
		//    	

		for (int i = 0; i < dataset.size(); i++) { dataset.getData(i).remove("ID");
		}
		dao.insert("TD_M_PRODUCT_TYPE", dataset);

	}

	/**
	 * 新增产品信息
	 * 
	 * @param pd
	 * @param dataset
	 * @throws Exception
	 * @author:wull
	 * @date:2010-5-19
	 */
	public void addProject(PageData pd, IDataset dataset) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		// String sql =
		// "insert into TD_M_PRODUCT( PRODUCT_CLASS,PRODUCT_TYPE, PRODUCT_NAME,PRODUCT_MODEL,PRODUCT_FACTORY,PRODUCT_PICTURE,PRODUCT_INTRODUCE,PRODUCT_GOOD,HOME_SHOW,PRIVILEGE_PRICE,RETAIL_PRICE,VIP_PRICE,REMARK,ITEM_FLAG,UPDATE_STAFF_ID,UPDATE_TIME) values(?,?, ?, ?, ?,?,?, ?, ?, ?,?,?, ?, ?, ?,?)";
		// Parameter[] params = new Parameter[dataset.size()];
		// for (int i=0; i<params.length; i++) {
		// params[i] = new Parameter();
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_CLASS",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_TYPE",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_NAME",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_MODEL",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_FACTORY",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_PICTURE",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_INTRODUCE",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRODUCT_GOOD",null));
		// params[i].add(((IData)dataset.get(i)).getString("HOME_SHOW",null));
		// params[i].add(((IData)dataset.get(i)).getString("PRIVILEGE_PRICE",null));
		// params[i].add(((IData)dataset.get(i)).getString("RETAIL_PRICE",null));
		// params[i].add(((IData)dataset.get(i)).getString("VIP_PRICE",null));
		// params[i].add(((IData)dataset.get(i)).getString("REMARK",null));
		// params[i].add(((IData)dataset.get(i)).getString("ITEM_FLAG",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_STAFF_ID",null));
		// params[i].add(((IData)dataset.get(i)).getString("UPDATE_TIME",null));
		// }
		// executeBatch(sql, params);
		//    	
		//    	
//		for (int i = 0; i < dataset.size(); i++) { dataset.getData(i).remove("PRODUCT_ID");
//		}
		dao.insert("WT_B_PROJECT", dataset);

	}

	/**
	 * 更新产品大类信息
	 * 
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductClassList(PageData pd, IData data, String[] keys) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT_CLASS", data, keys);
	}

	/**
	 * 更新产品小类信息
	 * 
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProductType(PageData pd, IData data, String[] keys) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT_TYPE", data, keys);
	}

	/**
	 * 更新产品信息
	 * 
	 * @param pd
	 * @param data
	 * @param keys
	 * @return
	 * @throws Exception
	 * @author:wull
	 */
	public boolean updateProduct(PageData pd, IData data, String[] keys) throws Exception {
		CashierAppEntity dao = new CashierAppEntity(pd);
		return dao.save("TD_M_PRODUCT", data, keys);
	}

}
