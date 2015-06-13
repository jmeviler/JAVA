package xyz.onesway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.regexp.internal.recompile;

import xyz.onesway.bean.Home;

@Repository
@Transactional
public class homeDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
  //保存
    public boolean save(Home home){
        try {
            sessionFactory.getCurrentSession().save(home);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //更新
    public boolean update(Home home) {
//        try {
//            this.getHibernateTemplate().update(home);
//        } catch (Exception e) {
//            return false;
//        }
        return true;
    }
    
    public Home findHomeByName(String name){
        try {
            String hql = " from Home h where h.name = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, name);
            return (Home) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    public boolean findHomeByname(String name) {
        try {
            String hql = " from Home h where h.name = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, name);
            if((Home) query.uniqueResult()!=null){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateHomePwd(int homeId, String pwd) {
        try {
            String hql = "update Home h set h.password = ? where h.homeId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, pwd);
            query.setInteger(1, homeId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
    }
    
    public Home findById(int homeId){
        try {
//            return this.getHibernateTemplate().get(Home.class, homeId);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public List<Home> findAll() {
//        return (List<Home>) this.getHibernateTemplate().find("from home");
        return null;
    }
    
    public boolean deleteById(int homeId) {
        try {
            String hql = " update Home h set h.deleted = 1 where h.homeId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, homeId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
