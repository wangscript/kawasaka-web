package t5demo.model;

import java.text.Format;
import java.util.Date;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.text.SimpleDateFormat;

/**
 * Demo model object
 */
public class User {
	private long id;
	private String userName;
	private String email;
	private Date birthday = new Date();
	private String password;
	private Role role = Role.GUEST;

	@Inject
	public User() {
		super();
	}
	
	public User(String name, String email, Date birthday, String password) {
		this();
		
		this.userName = name;
		this.email = email;
		this.birthday = birthday==null?(new Date()):birthday;
		this.password = password;
	}

	@NonVisual
	public long getId() {
		return id;
	}

	@Validate("required")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	@Validate("required,regexp")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() { 
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Validate("required")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
