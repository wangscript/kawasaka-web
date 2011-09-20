package t5demo.model;

import java.util.List;

public interface UserMapper {
    public User selectUserById(int id);
    public User selectUserByName(String name);
    public List<User> selectUsers();
    public int deleteUserById();
    public int insertUser();
    public int updateUser();
}