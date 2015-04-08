package xyz.onesway.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.onesway.bean.User;

/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:24:30
 */
@Repository
@Transactional
public class userDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * add new user
     * @param user
     * @return true or false
     */
    public boolean save(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * login check password
     * @param name
     * @return User Entity
     */
    public User findUserByName(String name) {
        try {
            String hql = " from User u where u.username = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, name);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * delete user by use id
     * @param userId
     * @return true or false
     */
    public boolean delete(int userId) {
        try {
            String hql = " update User u set u.deleted = 1 where u.userId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, userId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update user info
     * @param user
     * @return true or false
     */
    public boolean updateUserById(User user) {
        try {
            String hsql = "update User u set u.username = ?, u.password = ?, u.homeid = ? where u.userid =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setString(0, user.getUsername());
            query.setString(1, user.getPassword());
            query.setInteger(2, user.getHomeId());
            query.setInteger(3, user.getUserId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update user's password
     * @param userId
     * @param username
     * @return
     */
    public boolean updateUserPwd(int userId, String password) {
        try {
            String hql = "update User u set u.password = ? where u.userid = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, password);
            query.setInteger(1, userId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
