package t5demo.pages;

import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.ioc.Messages;

import t5demo.model.User;
import t5demo.services.UserDAO;

/**
 * Start page of application app.
 */
public class Start{
    @Inject
    private BeanModelSource beanModelSource;
    
    @Inject
    private ComponentResources resources;
    
    @Inject
    private Messages msg;
    
    @Inject
    private UserDAO userDAO;
	
	@Inject
	private Block userDetails;
	
	private User user;
	
	private User detailUser;

	public List<User> getUsers(){
		return userDAO.findAllUsers();
	}
	
	public BeanModel getModel() {
		BeanModel model = beanModelSource.create(User.class, false, msg);
		model.add("delete", null);
		model.add("view", null);
		return model;
	}
	
	void onActionFromDelete(long id){
		user = userDAO.find(id);
		if(user!=null)
			userDAO.delete(user);
	}
	
	Object onActionFromView(long id){
		detailUser = userDAO.find(id);
		return userDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getDetailUser() {
		return detailUser;
	}
}