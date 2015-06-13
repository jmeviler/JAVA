package xyz.onesway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.onesway.bean.Actuator;

@Repository
@Transactional
public class actuatorDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Actuator actuator) {
            try {
                sessionFactory.getCurrentSession().save(actuator);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public boolean delete(int actuatorId) {
        try {
            String hql = " update Actuator a set a.deleted = 1 where a.id = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, actuatorId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Actuator findActuatorById(int actuatorId) {
        try {
            String hql = "from Actuator a where a.id = ? and a.deleted != 1";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, actuatorId);
            return (Actuator) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateActuatorById(Actuator actuator) {
        try {
            String hsql = "update Actuator a set a.states = ?, a.location = ? where a.id =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setInteger(0, actuator.getStates());
            query.setString(1, actuator.getLocation());
            query.setInteger(2, actuator.getId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Actuator>findUserActuator(String username, String name) {
        try {
            name = "%" + name +"%";
            String hql = "from Actuator a where a.username = ? and a.name like ? and a.deleted !=1";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, username);
            query.setString(1, name);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
