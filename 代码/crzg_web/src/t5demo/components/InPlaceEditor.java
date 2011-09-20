package t5demo.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

@IncludeJavaScriptLibrary("${tapestry.scriptaculous}/controls.js")
public class InPlaceEditor {
	private static final String PARAM_NAME = "t:InPlaceEditor";
    
    @Parameter(required = true)
    private String value;
    
    @Environmental
    private RenderSupport pageRenderSupport;
    
    @Inject
    private ComponentResources resources;
    
    @Inject
    private Request request;
    
    void afterRender(MarkupWriter writer){
    	String elementName=resources.getElementName();
    	if(elementName==null)elementName="span";
    	
    	String clientId = pageRenderSupport.allocateClientId(resources.getId());
        writer.element(elementName, "id", clientId);
        resources.renderInformalParameters(writer);
        if (value != null)
            writer.write(value);
        writer.end();
        
        JSONObject config = new JSONObject();
        config.put("paramName", PARAM_NAME);
    	Link link = resources.createActionLink("edit", false);
    	pageRenderSupport.addScript("new Ajax.InPlaceEditor('%s', '%s', %s);", clientId, link.toAbsoluteURI(), config);
    }
    
    Object onEdit(){
    	value = request.getParameter(PARAM_NAME);
    	return new TextStreamResponse("text", value);
    }
}
