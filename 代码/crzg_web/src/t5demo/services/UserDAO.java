package t5demo.services;

import java.util.List;

import t5demo.model.User;

public interface UserDAO {
	
	List<User> findAllUsers();
	
	User find(long id);
	
	void save(User user);
	
	void update(User user);

	void delete(User user);

	User findUserByName(String name);

}
