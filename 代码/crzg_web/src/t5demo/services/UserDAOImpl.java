package t5demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.InjectService;

import t5demo.model.User;

public class UserDAOImpl implements UserDAO {
	
	private static long nextId = 1;
	
	private List<User> database = new ArrayList<User>();
	
	public UserDAOImpl(){
		createDemoData();
	}
	
	@InjectService("SqlSessionFactory")
    private SqlSessionFactory sqlMapper;
	public List<User> findAllUsers() {
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
            return null;
        }
        SqlSession session = sqlMapper.openSession();
        database = session.selectList("t5demo.model.UserMapper.selectUsers");
        //System.out.println(ls);
        return database;
		//return database;
	}

	public void save(User user) {
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
        }
        SqlSession session = sqlMapper.openSession();
        int id = session.delete("t5demo.model.UserMapper.insertUser", user);
        session.commit();
	}
	
	public void update(User user) { 
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
        }
        SqlSession session = sqlMapper.openSession();
        int id = session.delete("t5demo.model.UserMapper.updateUser", user);
        session.commit();
	}

	public void createDemoData() {
	}


	public User find(long id){
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
            return null;
        }
        SqlSession session = sqlMapper.openSession();
        User data = (User)session.selectOne("t5demo.model.UserMapper.selectUserById", id+"");
        return data;
	}

	public void delete(User user) {
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
        }
        SqlSession session = sqlMapper.openSession();
        int id = session.delete("t5demo.model.UserMapper.deleteUserById", user.getId()+"");
        session.commit();
        System.out.println(id+","+user.getId());
	}

	public User findUserByName(String name) {
		if ( sqlMapper == null ) {
            System.out.println("null-mapper");
            return null;
        }
        SqlSession session = sqlMapper.openSession();
        User data = (User)session.selectOne("t5demo.model.UserMapper.selectUserByName", name+"");
        return data;
	}
}
