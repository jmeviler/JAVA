package xyz.onesway.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import xyz.onesway.bean.Home;

@Repository
public class homeDao extends HibernateDaoSupport{
  //保存
    public boolean save(Home home){
        try {
            System.out.println(this.getHibernateTemplate());
            this.getHibernateTemplate().save(home);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //更新
    public boolean update(Home home) {
        try {
            this.getHibernateTemplate().update(home);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public Home findById(int homeId){
        try {
            return this.getHibernateTemplate().get(Home.class, homeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    public List<Home> findAll() {
        return (List<Home>) this.getHibernateTemplate().find("from home");
    }
    
    public boolean deleteById(int homeId) {
        try {
            this.getHibernateTemplate().delete(
                    this.getHibernateTemplate().get(Home.class, homeId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
