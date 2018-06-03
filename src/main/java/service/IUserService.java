package service;

import model.User;

public interface IUserService {

    public User selectUser(long userId);
    public int insertUser(User user);
    public User userLogin(String username);

}
