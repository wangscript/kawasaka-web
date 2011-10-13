package com.linkage.home.view.techno;

import org.apache.tapestry.event.PageEvent;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.component.AppSafePage;
import com.linkage.component.PageData;
import com.linkage.component.util.Utility;
import com.linkage.dbframework.util.Pagination;
import com.linkage.home.bean.TechnoHomeBean;


public abstract class TechnoDetail extends AppSafePage {
	
	public abstract void setInfosCalculation(IDataset infosCalculation);
	public abstract void setInfo(IData info);
	public abstract void setSoluName(String soluName);

	public void pageBeginRender(PageEvent event)
	{
		PageData pd;
		try {
			pd = getPageData();
			String solu_id = pd.getParameter("SOLU_ID", "");
			if("".equals(solu_id)){
				common.error("����solu_idû�д��룡");
			}
//			Pagination pa = pd.getPagination();
//			pa.setCount(5);
			TechnoHomeBean technoBean = new TechnoHomeBean();
			IData params = new DataMap();
			params.clear();
			IDataset infosCalculation = technoBean.queryTechnos(pd, params, null);
			for(int i=0;i<infosCalculation.size();i++){
				if(!"".equals(infosCalculation.getData(i).getString("TECHNO_CLASS", "")))
					params.put("TECHNO_CLASS", infosCalculation.getData(i).getString("TECHNO_CLASS", ""));
					infosCalculation.getData(i).put("infos", technoBean.querySolutions(pd, params, null));
			}
			
			setInfosCalculation(infosCalculation);
			
			//��������
			params.clear();
			params.put("SOLU_ID", solu_id);
			IDataset infos = technoBean.querySolutions(pd, params, null);
			IData info = infos.size()>0?infos.getData(0):null;
			if(info == null){
				common.error("���������ڣ�");
			}
			String soluName = info.getString("SOLU_NAME",""); 
			if("".equals(soluName)){
				common.error("�������Ʋ����ڻ����Ѿ�ʧЧ��");
			}
			setSoluName(soluName);			
			setInfo(info);
		} catch (Exception e) {
			log.error("��ʼ��ҳ��ִ��ʧ�ܣ��������:" + e);
		}finally{
			super.pageBeginRender(event);
		}
	}
}