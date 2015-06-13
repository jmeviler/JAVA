package xyz.onesway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.onesway.bean.Device;
import xyz.onesway.bean.Sensor;

@Repository
@Transactional
public class sensorDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Sensor sensor) {
            try {
                sessionFactory.getCurrentSession().save(sensor);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public boolean delete(int sensorId) {
        try {
            String hql = " update Sensor s set s.deleted = 1 where s.id = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, sensorId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Sensor findSensorById(int sensorId) {
        System.out.println(sensorId);
        try {
            String hql = "from Sensor s where s.id = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, sensorId);
            return (Sensor) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateSensorById(Sensor sensor) {
        try {
            String hsql = "update Sensor s set s.states = ?, s.location = ? where s.id =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setInteger(0, sensor.getStates());
            query.setString(1, sensor.getLocation());
            query.setInteger(2, sensor.getId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Sensor> findUsersDevice(String username, String name) {
        try {
            name = "%" + name +"%";
            String hql = " from Sensor s where s.username = ? and s.name like ? and s.deleted !=1";
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
