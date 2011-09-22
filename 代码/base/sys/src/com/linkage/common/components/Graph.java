package com.linkage.common.components;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.tapestry.ApplicationRuntimeException;
import org.apache.tapestry.IForm;
import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.Tapestry;
import org.apache.tapestry.form.IPropertySelectionModel;
import org.apache.tapestry.html.Body;

import com.linkage.appframework.data.DataMap;
import com.linkage.appframework.data.DatasetList;
import com.linkage.appframework.data.IData;
import com.linkage.appframework.data.IDataset;
import com.linkage.webframework.tapestry.components.BaseFormComponent;

public abstract class Graph extends BaseFormComponent 
{
	  public abstract String getValue();
	  //public abstract Object getModel();
	  public abstract String getStyle();
	  //public abstract String getEnterAction();

	//public abstract void setUploads(IDataset uploads);
	//public abstract void setOtherConnectInfos(IDataset otherConnectInfos);
	
	protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle) {
		IForm form = getForm(cycle);
		String name = form.getElementId(this);
		String value = getValue();
		//Object model = getModel();
		String style = getStyle();
	    //String enterAction = getEnterAction();
	    IDataset dataModel = null;
	    /*
	    if (model == null) {
	        model = new DatasetList();
	    }
	    if ((model == null) || (model instanceof IDataset)) {
	    	dataModel = (IDataset)model;
	    } else if (model instanceof IPropertySelectionModel) {
	    	dataModel = convertToDataset((IPropertySelectionModel)model);
	    } else {
	        throw new ApplicationRuntimeException("Suggest component " + name + ": type of parameter model is no other than com.linkage.appframework.data.IDataset or org.apache.tapestry.form.IPropertySelectionModel.");
	    }
	    */
		if (!cycle.isRewinding()) {
			Body body = Body.get(cycle);
			if (body == null) {
				throw new ApplicationRuntimeException(Tapestry.format(
					"must-be-contained-by-body", "Graph"));
			}

			//String value = getValue();
			//body.includeExternalScript(writer, "./common/scripts/suggest.js");
			/*
			writer.beginEmpty("input");
			writer.attribute("type", "hidden");
			writer.attribute("name", name);
			writer.attribute("id", name);
			writer.attribute("value", value);
			renderInformalParameters(writer, cycle);
			writer.beginEmpty("input");
			writer.attribute("type", "text");
			writer.attribute("name", "input_" + name);
			writer.attribute("id", "input_" + name);
			writer.attribute("value", "");
			writer.attribute("autocomplete", "off");
			writer.attribute("style", style);
		    //if (enterAction != null) writer.attribute("onkeypress", "if (window.event.keyCode == 13) { Wade.event.stopEvent(); return " + enterAction + "; }");
			renderInformalParameters(writer, cycle);
		    writer.printRaw("\r\n");
		    writer.printRaw("<div id=\"suggest_" + name + "\" style=\"z-index:100;display:none;\"></div>\r\n");
		    writer.printRaw("<script type=\"text/javascript\" src=\"./common/scripts/suggest.js\"></script>\r\n");
		    StringBuffer html = new StringBuffer();
		    html.append("<script>");
		    html.append("document.getElementById(\"" + "input_" + name + "\").style.display = \"block\";");
		    html.append("var suggestCode"+ name +"List = [];");
		    html.append("var suggestText"+ name +"List = [];");
	        for (int i = 0; i < dataModel.size(); ++i) {
	            IData data = (IData)dataModel.get(i);
	        	html.append("suggestCode"+ name +"List.push(\""+ data.getString("DATA_ID") +"\");");
	        	html.append("suggestText"+ name +"List.push(\""+ pinjieStr(data.getString("DATA_NAME")) +"\");");
	        }
		    html.append("var suggest = new Suggest.Local(\""+ name +"\",\""+ "input_" + name +"\", \"" + "suggest_" + name +"\", suggestCode"+ name +"List, suggestText"+ name +"List, {dispAllKey: true});");
		    html.append("</script>");
		    html.append("\r\n");
		    */
			IDataset idata = new DatasetList();
			IData data0 = new DataMap();
			data0.put("NAME", "开始");
			data0.put("DATA", "10");
			idata.add(data0);
			idata.add(data0);
			idata.add(data0);
			String vars = new String();
			vars = convertToString(idata);
			StringBuffer html = new StringBuffer();
			html.append("<object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0\" width=\"800\" height=\"375\" id=\"" + name + "\" align=\"middle\">");
		    /*
			html.append("\r\n");
		    html.append("	<param name=\"allowScriptAccess\" value=\"sameDomain\" />");
		    */
		    html.append("\r\n");
		    html.append("	<param name=\"movie\" value=\"./common/swf/graph.swf\" />");
		    html.append("\r\n");
		    /*
		    html.append("	<param name=\"quality\" value=\"high\" />");
		    html.append("\r\n");
		    html.append("	<param name=\"bgcolor\" value=\"#ffffff\" />");
		    html.append("\r\n");
		    html.append("	<param name=\"wmode\" value=\"transparent\" />");
		    html.append("\r\n");
		    */
		    html.append("	<param name=\"FlashVars\" value=\"" + vars + "\" />");
		    html.append("\r\n");
		    html.append("	<embed src=\"./common/swf/graph.swf\" quality=\"high\" bgcolor=\"#ffffff\" width=\"800\" height=\"375\" name=\"" + name + "\" align=\"middle\" wmode=\"transparent\" allowScriptAccess=\"sameDomain\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />");
		    html.append("\r\n");
		    html.append("</object>");
			writer.printRaw(html.toString());
		    writer.printRaw("\r\n");
		}

	}	
	  private IDataset convertToDataset(IPropertySelectionModel model) {
		    IDataset dataset = new DatasetList();
		    int count = model.getOptionCount();
		    for (int i = 0; i < count; ++i) {
		      IData data = new DataMap();
		      String value = model.getValue(i);
		      if (value != null) { 
		    	if (!"".equals(value)){
			        data.put("DATA_ID", model.getValue(i));
			        data.put("DATA_NAME", model.getLabel(i));
			        dataset.add(data); 
		    	}
		       }
		    }
		    return dataset;
		  }

	  private String convertToString(IDataset idata){
		StringBuffer vars = new StringBuffer();
		//主名称
		vars.append("rtitle=销售记录");
		//Y轴名称
		vars.append("&vtitle=纵向记录");
		//X轴名称
		vars.append("&ntitle=时间段");
		//底部按钮
		vars.append("&toggle=全部显示|全部隐藏");
		//未知
		vars.append("&cnames=未知");
		//图形类型
		//vars.append("&gtypes=line|bar|hbar|pie|mline|fun");
		vars.append("&gtypes=line|bar|hbar");
		//用于饼状图的类型
		vars.append("&datatype=12");
		//用于饼状图的类型
		//vars.append("&fsize=23");
		//用于饼状图的类型
		//vars.append("&uloc=cn|$|0|2");
		//用于饼状图的类型--未知
		//vars.append("&total=370");
		//输入数据
		vars.append("&xdata=" + convertToIDataset(idata));
		return vars.toString();
	  }
	private String convertToIDataset(IDataset idata) {
		StringBuffer dataStr = new StringBuffer();
		try {
			for(int i = 0; i < idata.size(); i++){
				IData data = idata.getData(i);
				dataStr.append(data.getString("NAME") + "\\t" + data.getString("DATA") + "\\n");
			}
		} catch (Exception e) {
			new ApplicationRuntimeException(Tapestry.format("data must-be-not-nothing", "Graph"));
		}
		return dataStr.toString();
	}
}
