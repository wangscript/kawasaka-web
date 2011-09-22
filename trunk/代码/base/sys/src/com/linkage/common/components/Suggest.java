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

public abstract class Suggest extends BaseFormComponent 
{
	  public abstract String getValue();
	  public abstract Object getModel();
	  public abstract String getStyle();
	  public abstract String getEnterAction();

	//public abstract void setUploads(IDataset uploads);
	//public abstract void setOtherConnectInfos(IDataset otherConnectInfos);
	
	protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle) {
		IForm form = getForm(cycle);
		String name = form.getElementId(this);
		String value = getValue();
		Object model = getModel();
		String style = getStyle();
	    String enterAction = getEnterAction();
	    IDataset dataModel = null;
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
		if (!cycle.isRewinding()) {
			Body body = Body.get(cycle);
			if (body == null) {
				throw new ApplicationRuntimeException(Tapestry.format(
					"must-be-contained-by-body", "Suggest"));
			}

			//String value = getValue();
			//body.includeExternalScript(writer, "./common/scripts/suggest.js");
			
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
		    if (enterAction != null) writer.attribute("onkeypress", "if (window.event.keyCode == 13) { Wade.event.stopEvent(); return " + enterAction + "; }");
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
		    writer.printRaw(html.toString());
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
	  private String pinjieStr(String src){
		  return makeStringToPinyin(src) + ":" + src;
	  }
	  private String makeStringToPinyin(String src){
				StringBuilder outStr = new StringBuilder();   
				if(src!=null && !src.trim().equalsIgnoreCase("")){
					char[] srcChar ;   
					srcChar=src.toCharArray();   
					//汉语拼音格式输出类   
					HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();   
					  
					//输出设置，大小写，音标方式等   
					hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);    
					hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
					hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
					String[] temp = new String[src.length()];   
					for(int i=0;i<srcChar.length;i++){   
					    char c = srcChar[i];   
					    //是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)   
					    if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")){   
					    	try{   
						      String tmp[] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
						       temp[i] = tmp[0].substring(0, 1); 
						    }catch(BadHanyuPinyinOutputFormatCombination e) {   
						      e.printStackTrace();   
						    }   
					    }else if(((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=122)){   
					     temp[i] = String.valueOf(srcChar[i]);   
					    }else{   
					     temp[i] = String.valueOf(srcChar[i]);   
					    }   
					}
					int i=0;   
					for(String s : temp){   
					   if(i == temp.length - 1){   
						   outStr.append(s);   
					   }else{   
						   outStr.append(s + "");   
					   }   
					   i++;   
					}
				}
				return outStr.toString();
			 }	  
}
