package xyz.onesway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.User;
import xyz.onesway.dao.userDao;

/**
 * @author Ben Li
 * @version Date：2015年3月21日 下午10:23:58
 */
@Service
public class UserService {

    @Autowired
    private userDao userDao;

    // password不需要传递到dao层 直接在业务逻辑层处理
    public User login(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
        User user = userDao.findUserByName(userName);
        
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean register(User user) {
        if (user == null) {
            return false;
        } else {
            if (user.getUsername() == null || user.getUsername() == null) {
                return false;
            } else {
                return userDao.save(user);
            }

        }
    }

    public User updateUserInfo(User user) {
        if (user == null) {
            return user;
        }
        if (user.getUsername() == null || user.getPassword() == null) {
            return null;
        }
        if (userDao.updateUserById(user)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean updateUserPwd(int userId, String password) {
        try {
            return userDao.updateUserPwd(userId, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int userId) {
        try {
            return userDao.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
