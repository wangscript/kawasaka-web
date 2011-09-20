package t5demo.pages;

import org.apache.tapestry5.annotations.Persist;

public class InPlaceEditorExample {
	@Persist
	private String edit="Please click here";

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}
}
