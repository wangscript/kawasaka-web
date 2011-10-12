package com.linkage.home.components;

import org.apache.tapestry.IMarkupWriter;
import org.apache.tapestry.IRequestCycle;
import com.linkage.appframework.data.IData;
import com.linkage.component.jwcs.AppTempletComponent;


public abstract class aboutSide extends AppTempletComponent {

	public abstract IData getAbout();
	public abstract boolean isDisabled();

	protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle) {
		super.renderComponent(writer, cycle);
	}

}
