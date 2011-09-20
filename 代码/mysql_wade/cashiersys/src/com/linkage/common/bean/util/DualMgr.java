/* $Id: DualMgr.java,v 1.1 2009/10/27 13:32:55 wangmo Exp $ */

/* =============== CODE DEFINE BETWEEN 736000 TO 737000 ================= */
package com.linkage.common.bean.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppEntity;
import com.linkage.component.PageData;
import com.linkage.dbframework.jdbc.SQLParser;

public class DualMgr {
	/**
	 * ������ˮ��
	 * 
	 * @author luoy
	 * @throws Exception
	 */
	public static String getSeqId(PageData pd, String sequenceName) throws Exception {
		IData data = new DataMap();
		
		String eparchyCode = "0898";

		data.put("VEPARCHY_CODE", eparchyCode);
		data.put("VSEQUENCENAME", sequenceName);

		SQLParser parser = new SQLParser(data);
		parser.addSQL(" SELECT f_sys_getseqid(:VEPARCHY_CODE,:VSEQUENCENAME) OUTSTR FROM dual ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		String seqId = ((IData) out.get(0)).getString("OUTSTR", "");
		return seqId;
	}

	/**
	 * ������ˮ��(�����ã�
	 * 
	 * @author luoy
	 * @throws Exception
	 */
	public static String getGrpSeqId(PageData pd, String sequenceName) throws Exception {
		IData data = new DataMap();
		String eparchyCode = pd.getContext().getLoginEpachyId();

		data.put("VEPARCHY_CODE", eparchyCode);
		data.put("VSEQUENCENAME", sequenceName);

		SQLParser parser = new SQLParser(data);
		parser.addSQL(" SELECT f_sys_getseqid_grp(:VEPARCHY_CODE,:VSEQUENCENAME) OUTSTR FROM dual ");

		AppEntity dao = new AppEntity(pd, "cg");
		IDataset out = dao.queryList(parser);

		String seqId = ((IData) out.get(0)).getString("OUTSTR", "");
		return seqId;
	}

	/**
	 * ��õ�ǰʱ��(YYYY-MM-DD HH24:MI:SS) ykx
	 * 
	 * @param pd
	 * @return ʱ��YYYY-MM-DD HH24:MI:SS�ַ�����ʽ
	 * @throws Exception
	 */
	public static String getSysDate(PageData pd) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * ��õ�ǰʱ��ǰ�����������ߺ󣨸����������ʱ�䣬 ����ʱ���ʽ YYYYMMDDHH24MISS ���
	 * 
	 * @author Caorl
	 * @param pd
	 * @param seconds
	 *            ��ǰʱ��ĺ����ǰ���룬������������
	 * @return
	 * @throws Exception
	 */
	public static String getBeforeOrAfterSecondsOfSysDate(PageData pd, int seconds) throws Exception {

		SQLParser parser = new SQLParser(new DataMap());

		parser.addSQL(" SELECT TO_CHAR(SYSDATE+" + seconds + "/24/3600,'YYYYMMDDHH24MISS') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��õ�ǰʱ��ǰ�����������ߺ󣨸����������ʱ�䣬 ����ʱ���ʽ YYYY-MM-DD HH24:MI:SS ���
	 * 
	 * @author Caorl
	 * @param pd
	 * @param seconds
	 *            ��ǰʱ��ĺ����ǰ���룬������������
	 * @return
	 * @throws Exception
	 */
	public static String getOtherSecondsOfSysDate(PageData pd, int seconds) throws Exception {

		SQLParser parser = new SQLParser(new DataMap());

		parser.addSQL(" SELECT TO_CHAR(SYSDATE+" + seconds + "/24/3600,'YYYY-MM-DD HH24:MI:SS') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}
	
	/**
	 * ��õ�ǰʱ��(YYYY-MM-DD HH24:MI:SS)ǰһ�� by dengr
	 * 
	 * @param pd
	 * @return ʱ��YYYY-MM-DD HH24:MI:SS�ַ�����ʽ
	 * @throws Exception
	 */
	public static String getSysDateSubSec(PageData pd) throws Exception {

		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(" SELECT TO_CHAR(SYSDATE-1/24/3600,'YYYY-MM-DD HH24:MI:SS') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ø���ʱ��(YYYY-MM-DD HH24:MI:SS)ǰһ�� by dengr
	 * 
	 * @param pd
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String getDateSubSec(PageData pd, String dateString) throws Exception {

		String sql_ref = "select to_char(to_date('" + dateString
				+ "','yyyy-mm-dd hh24:mi:ss')-1/24/3600,'yyyy-mm-dd hh24:mi:ss') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	//��ø���ʱ��(YYYY-MM-DD HH24:MI:SS)��һ�� by chengxf2
	public static String getDateAddSec(PageData pd, String dateString) throws Exception {

		String sql_ref = "select to_char(to_date('" + dateString
				+ "','yyyy-mm-dd hh24:mi:ss')+1/24/3600,'yyyy-mm-dd hh24:mi:ss') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ����: �޸�ʱ��: 2009-5-16 ����07:50:14 �޸��ߣ�tangxy
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getSysDatePrue(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(" SELECT TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��õ�ǰ��������ʱ��
	 * 
	 * @author songzy
	 * @param pd
	 * @return ��ǰ��������ʱ��
	 * @throws Exception
	 */
	public static String getLateDate(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(" SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "") + " 00:00:00";
	}

	/**
	 * ��ȡ��������(yyyy-mm-dd)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getNowDate(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ���������(yyyy-MM-dd)
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getTomorrowDate(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate+1, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ���������(yyyy-MM-dd)
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getYesterdayDate(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate-1, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ�������һ��(yyyy-mm-dd)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getLastDateThisMonth(PageData pd) throws Exception {
		String sql_ref = "select to_char(last_day(sysdate), 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ�������ʱ��(yyyy-mm-dd hh24:mi:ss)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getLastTimeThisMonth(PageData pd) throws Exception {
		String sql_ref = "select to_char(last_day(sysdate), 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + " 23:59:59";
	}
	
	/**
	 * ��ȡ���µ�һ��(yyyy-mm-dd);
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getNextMonthFirstDate(PageData pd) throws Exception {
		String sql_ref = "select to_char(last_day(sysdate)+1, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ���µ�һʱ��(yyyy-mm-dd hh24:mi:ss)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getNextMonthFirstTime(PageData pd) throws Exception {
		String sql_ref = "select to_char(last_day(sysdate)+1, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + " 00:00:00";
	}

	/**
	 * ��ȡ���µ�һ��(yyyy-mm-dd)
	 * 
	 * @author Liuy4
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getFirstDateThisMonth(PageData pd) throws Exception {
		String sql_ref = "select to_char(add_months(last_day(sysdate)+1, -1), 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ2050-12-31 23:59:59
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getTheLastTime() throws Exception {
		return "2050-12-31 23:59:59";
	}

	//��õ�ǰʱ�����ڱ����е���
	public static String getDayOfSysdate(PageData pd) throws Exception {
		String sql_ref = "SELECT to_char(SYSDATE,'dd') OUTSTR FROM dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	//��������ʱ�������е���
	public static String getDayOfInputdate(PageData pd,String inputDate) throws Exception {
		String sql_ref = "SELECT to_char(to_date('"+inputDate+"','yyyy-mm-dd hh24:mi:ss'),'dd') OUTSTR FROM dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	/**
	 * ��ȡ��������
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getNowCycle(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("select to_char(sysdate,'yyyymm') OUTSTR from dual");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getLastCycle(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("select to_char(add_months(sysdate,-1),'yyyymm') OUTSTR from dual");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getNextCycle(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("select to_char(add_months(sysdate,+1),'yyyymm') OUTSTR from dual");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ȡ���½��������,��ʽΪyyyy-MM-dd
	 * 
	 * @author chenhao 2009-2-23
	 * @param pd
	 * @return String ���½��������
	 * @throws Exception
	 */
	public static String getTodayLastMonth(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("select to_char(add_months(sysdate,-1),'yyyy-mm-dd') OUTSTR from dual");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ȡ�������������,��ʽΪyyyy-MM-dd
	 * 
	 * @param pd
	 * @return String �������������
	 * @throws Exception
	 */
	public static String getTomorrowLastMonth(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("select to_char(add_months(sysdate,-1)+1,'yyyy-mm-dd') OUTSTR from dual");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * ��ȡ�������������Ϣ
	 * 
	 * @author songzy
	 * @param pd
	 * @param inparam
	 * @return IDataset
	 * @throws Exception
	 */
	public static IDataset queryAcycPara(PageData pd, IData inparam) throws Exception {
		AppEntity dao = new AppEntity(pd);
		return dao.queryListByCodeCode("TD_A_ACYCPARA", "SEL_ACYCPARA", inparam);
	}

	/**
	 * ��ȡ�������������Ϣ
	 * 
	 * @author songzy
	 * @param pd
	 * @param inparam
	 * @return IDataset
	 * @throws Exception
	 */
	public static IDataset queryCycle(PageData pd, IData inparam) throws Exception {
		AppEntity dao = new AppEntity(pd);
		return dao.queryListByCodeCode("TD_B_CYCLE", "SEL_CYCLE", inparam);
	}

	/**
	 * ��ȡϵͳʱ�䣫iMonths��ʱ��
	 * 
	 * @param pd
	 * @param iMonths
	 * @return
	 * @throws Exception
	 * @author ykx
	 */
	public static String getSysdateAddMonths(PageData pd, int iMonths) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL("SELECT TO_CHAR(ADD_MONTHS(SYSDATE," + iMonths + "),'YYYY-MM-DD HH24:MI:SS') OUTSTR FROM DUAL");
		AppEntity dao = new AppEntity(pd);
		return ((IData) (dao.queryList(parser).get(0))).getString("OUTSTR");
	}

	/**
	 * ����������� ƫ���� �������ڱ�����yyyy-MM-dd��ʽ
	 * 
	 * @author zhujm 2009-04-21
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String dayExcursion(String dateString, int nbr) throws Exception {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = bartDateFormat.parse(dateString);
		cal.setTime(date);
		cal.add(Calendar.DATE, nbr);

		return bartDateFormat.format(cal.getTime());
	}

	/**
	 * ����������� ƫ���·� �������ڱ�����yyyy-MM-dd��ʽ
	 * 
	 * @author zhujm 2009-03-03
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String monthExcursion(String dateString, int nbr, String flag) throws Exception {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = bartDateFormat.parse(dateString);
		cal.setTime(date);
		cal.add(Calendar.MONTH, nbr);

		if ("MIN_DAY".equals(flag))
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if ("MAX_DAY".equals(flag))
		{
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.DATE, -1);
		}

		return bartDateFormat.format(cal.getTime());
	}

	/**
	 * ����������� ƫ���·� �������ڱ�����yyyy-MM-dd��ʽ ��ȡ��Ȼ��
	 * 
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String monthExcursion(String dateString, int nbr) throws Exception {
		return monthExcursion(dateString, nbr, null);
	}

	/**
	 * ����������� ƫ���� �������ڱ�����yyyy-MM-dd��ʽ
	 * 
	 * @author zhujm 2009-04-21
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String yearExcursion(String dateString, int nbr, boolean ifNature) throws Exception {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = bartDateFormat.parse(dateString);
		cal.setTime(date);
		cal.add(Calendar.YEAR, nbr);

		if (ifNature)
		{
			cal.set(Calendar.DAY_OF_YEAR, 1);
		}

		return bartDateFormat.format(cal.getTime());
	}

	/**
	 * ����������� ƫ���� �������ڱ�����yyyy-MM-dd��ʽ ��ȡ��Ȼ��
	 * 
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String yearExcursion(String dateString, int nbr) throws Exception {
		return yearExcursion(dateString, nbr, false);
	}

	/**
	 * ��ȡ��ǰ���ڣ�YYYYMM
	 * @return
	 * @throws Exception
	 * @author xj
	 */
	public static String getNowCyc(PageData pd) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(" SELECT TO_CHAR(SYSDATE,'YYYYMM') OUTSTR FROM DUAL ");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "");
	}

	/**
	 * Ĭ�ϵĽ�������
	 * @return
	 * @throws Exception
	 * @author xj
	 */
	public static String getLastCyc() throws Exception {
		return "203301";
	}

	/**
	 * ��ȡ�����·ݵ����һ�� �������ڱ�����yyyy-MM-dd��ʽ
	 * 
	 * @author luojh 2009-03-25
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String getMonthLast(PageData pd, String dateString) throws Exception {
		String sql_ref = "select to_char(last_day(to_date('" + dateString + "', 'yyyy-mm-dd'))) OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ��ȡ�����·ݵ����ʱ��(yyyy-mm-dd hh24:mi:ss)
	 * 
	 * @author luojh 2009-03-25
	 * @param dateString
	 * @param nbr
	 * @return
	 * @throws Exception
	 */
	public static String getMonthLastTime(PageData pd, String dateString) throws Exception {
		String sql_ref = "select to_char(last_day(to_date('" + dateString
				+ "', 'yyyy-mm-dd')),'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + " 23:59:59";
	}

	/**
	 * ��ȡ����
	 * 
	 * @author tangxy
	 * @return
	 * @throws Exception
	 */
	public static String getNowYear(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'yyyy') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ����: ��ý�������һ�� �޸�ʱ��: 2009-5-13 ����08:00:12 �޸��ߣ�tangxy
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getLastDayOfThisYear(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'yyyy') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + "-12-31 23:59:59";
	}

	/**
	 * ��ȡ�����
	 * 
	 * @author anwx
	 * @return
	 * @throws Exception
	 */
	public static String getNowMonth(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'yyyy-mm') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	public static String getNowMonthNoFormat(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'yyyymm') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	public static String getMonthNoFormat(PageData pd,String month) throws Exception {
		String sql_ref = "select to_char(add_months(sysdate,to_number("+month+")),'yyyymm') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	/**
	 * ��ȡ�����
	 * 
	 * @author anwx
	 * @return
	 * @throws Exception
	 */
	public static String getCurMonth(PageData pd) throws Exception {
		String sql_ref = "select to_number(to_char(sysdate, 'mm')) OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ����У��
	 * 
	 * @author zhuxu
	 * @return
	 * @throws Exception
	 */
	public static boolean cheekDate(String date) throws Exception {
		boolean result = true;
		try
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			result = df.format(df.parse(date)).equals(date);
		}
		catch (ParseException e)
		{
			result = false;
		}
		return result;
	}

	/**
	 * ��ȡ�ַ����ֽڳ���
	 * 
	 * @author zhuxu
	 * @return int
	 * @throws Exception
	 */
	public static int cheekString(String str) throws Exception {

		return str.getBytes().length;
	}

	/**
	 * ���ڸ�ʽ��
	 * 
	 * @author chenhao 2009-2-23
	 * @param data
	 *            ���������
	 * @param formatter
	 *            Ҫת���ɵĸ�ʽ(��yyyy-MM-dd HH:mm:ss)
	 * @return String ת���������
	 * @throws ParseException
	 */
	public static String dataToData(String data, String formatter) throws ParseException {
		DateFormat format = new SimpleDateFormat(formatter);
		return format.format(format.parse(data));
	}

	/**
	 * ���2��ʱ����²�ֵ(YYYY-MM-DD HH24:MI:SS)
	 * 
	 * @param pd
	 * @return �²�ֵ
	 * @throws Exception
	 */
	public static int monthInterval(PageData pd, String strOlddate, String strCurdate) throws Exception {
		AppEntity dao = new AppEntity(pd);
		IData param = new DataMap();
		param.put("CUR_DATE", strCurdate);
		param.put("OLD_DATE", strOlddate);

		IDataset dataset = dao.queryListByCodeCode("NormalPara", "MonthInterval", param, null);
		return Integer.parseInt(dataset.get(0, "OUTSTR").toString());
	}

	/**
	 * ��ȡ���ݿ⵱ǰʱ��[YYYYMMDDHH24MISS]
	 * 
	 * @author Liuy4
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static String getSysDate4Id(PageData pd) throws Exception {
		String sql_ref = "select to_char(sysdate, 'YYYYMMDDHH24MISS') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}

	/**
	 * ���ݴ����ʱ�䣬�����ʱ��ǰһ���ʱ��
	 * 
	 * @param pd
	 * @return ʱ��YYYY-MM-DD HH24:MI:SS�ַ�����ʽ
	 * @throws Exception
	 */
	public static String getLastSecond(PageData pd, String sysdate) throws Exception {
		String sql_ref = " SELECT TO_CHAR(TO_DATE('" + sysdate
				+ "','YYYY-MM-DD HH24:MI:SS')-1/86400,'YYYY-MM-DD HH24:MI:SS') OUTSTR FROM DUAL ";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();

	}

	/**
	 * ���ݴ����ʱ�䣬������µ�һʱ��(yyyy-mm-dd 00:00:00)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getNextMonthFirstTime(PageData pd, String sysdate) throws Exception {
		String sql_ref = "SELECT TO_CHAR(LAST_DAY(TO_DATE('" + sysdate
				+ "', 'YYYY-MM-DD HH24:MI:SS'))+1, 'YYYY-MM-DD') OUTSTR FROM DUAL";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + " 00:00:00";
	}

	/**
	 * ���ݴ����ʱ�䣬����������ʱ��(yyyy-mm-dd 23:59:59)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getLastTimeThisMonth(PageData pd, String sysdate) throws Exception {
		String sql_ref = "SELECT TO_CHAR(LAST_DAY(TO_DATE('" + sysdate
				+ "', 'YYYY-MM-DD HH24:MI:SS')), 'YYYY-MM-DD') OUTSTR FROM DUAL";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString() + " 23:59:59";
	}

	/**
	 * ���ݴ����ʱ�䣬����������ʱ��(yyyy-mm-dd 23:59:59)
	 * 
	 * @author Liuy4
	 * @return
	 * @throws Exception
	 */
	public static String getTimeString(PageData pd, String sysdate) throws Exception {
		String sql_ref = "select to_char(to_date('" + sysdate
				+ "', 'yyyy-mm-dd hh24:mi:ss'), 'yyyymmddhh24miss') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	/**
	 * ���2��ʱ����²�ֵ(YYYY-MM-DD HH24:MI:SS)
	 * 
	 * @param pd
	 * @return �²�ֵ
	 * @throws Exception
	 */
	public static int dayInterval(PageData pd, String strOlddate, String strCurdate) throws Exception {
		AppEntity dao = new AppEntity(pd);
		IData param = new DataMap();
		param.put("CUR_DATE", strCurdate);
		param.put("OLD_DATE", strOlddate);

		IDataset dataset = dao.queryListByCodeCode("NormalPara", "DayInterval", param, null);
		return Integer.parseInt(dataset.get(0, "OUTSTR").toString());
	}

	//�������ʱ�䲻����ʱ���룬�������ʱ����
	//iΪ0,�����00:00:00
	//iΪ1,�����23:59:59
	public static String suffixDate(String date, int i) {
		StringBuffer suffix=new StringBuffer();
		if(i==0){
			suffix.append(" 00:00:00");
		}else{
			suffix.append(" 23:59:59");
		}
		
		if(date.length()<12){
			date=date+suffix.toString();
		}
		return date;
	}
	
	/**
	 * ���2��ʱ������ֵ(YYYY-MM-DD)
	 * 
	 * @param pd
	 * @return �²�ֵ
	 * @throws Exception
	 */
	public static int yearInterval(PageData pd, String strOlddate, String strCurdate) throws Exception {
		AppEntity dao = new AppEntity(pd);
		IData param = new DataMap();
		param.put("CUR_DATE", strCurdate);
		param.put("OLD_DATE", strOlddate);

		IDataset dataset = dao.queryListByCodeCode("NormalPara", "YearInterval", param, null);
		return Integer.parseInt(dataset.get(0, "OUTSTR").toString());
	}
	
	/**
	 * ��ȡָ��ʱ��������µ����һ��
	 * @author fanwg
	 * @date 2009-9-12
	 * @param pd
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static String getDateLastMonthSec(PageData pd, String dateString) throws Exception {

		String sql_ref = "select to_char(last_day(to_date('"+dateString+"','yyyy-mm-dd')), 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	/**
	 * ��ȡָ��ʱ����¸��µĵ�һ��
	 * @author fanwg
	 * @date 2009-9-12
	 * @param pd
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static String getDateNextMonthFirstDay(PageData pd, String dateString) throws Exception {

		String sql_ref = "select to_char(last_day(to_date('"+dateString+"','yyyy-mm-dd'))+1, 'yyyy-mm-dd') OUTSTR from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}
	
	/**
	 * ��ȡָ��ʱ����ض�����
	 * @author fanwg
	 * @date 2009-9-12
	 * @param pd
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static String getDateDay(PageData pd, String dateString) throws Exception {

		String sql_ref = "select to_char(to_date('"+dateString+"','yyyy-mm-dd'),'dd') OUTSTR  from dual";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();
	}	
	
	/**
	 * ���ݴ����ʱ���õ�ǰ��������ʱ��
	 * 
	 * @author songzy
	 * @param pd
	 * @return ��ǰ��������ʱ��
	 * @throws Exception
	 */
	public static String getLateDateByDate(PageData pd, String dateString) throws Exception {
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(" SELECT TO_CHAR(to_date('" + dateString + "', 'yyyy-mm-dd hh24:mi:ss'),'YYYY-MM-DD') OUTSTR FROM DUAL");

		AppEntity dao = new AppEntity(pd);
		IDataset out = dao.queryList(parser);

		return ((IData) out.get(0)).getString("OUTSTR", "") + " 00:00:00";
	}
	
	/**
	 * ���ݴ����ʱ�䣬�����ʱ���һ���ʱ��
	 * 
	 * @param pd
	 * @return ʱ��YYYY-MM-DD HH24:MI:SS�ַ�����ʽ
	 * @throws Exception
	 */
	public static String getNextSecond(PageData pd, String sysdate) throws Exception {
		String sql_ref = " SELECT TO_CHAR(TO_DATE('" + sysdate
				+ "','YYYY-MM-DD HH24:MI:SS')+1,'YYYY-MM-DD HH24:MI:SS') OUTSTR FROM DUAL ";
		SQLParser parser = new SQLParser(new DataMap());
		parser.addSQL(sql_ref);
		AppEntity app = new AppEntity(pd);
		IDataset dataset = app.queryList(parser);
		return dataset.get(0, "OUTSTR").toString();

	}
}
