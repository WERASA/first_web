package service.Imp;

import dao.IUserDao;
import model.User;
import org.springframework.stereotype.Service;
import service.IUserService;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

    public User userLogin(String username) {
        return this.userDao.userLogin(username);
    }
    public int insertUser(User user) { return this.userDao.insertUser(user); }

}
