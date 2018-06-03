package dao;

import model.User;

public interface IUserDao {
    User userLogin(String id);
    User selectUser(long id);
    int insertUser(User user);

}
