package xyz.onesway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.onesway.bean.Device;

@Repository
@Transactional
public class deviceDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Device device) {
            try {
                sessionFactory.getCurrentSession().save(device);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public boolean delete(int deviceId) {
        try {
            String hql = " update Device d set d.deleted = 1 where d.id = ?";
            System.out.println(hql);
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, deviceId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Device findDeviceById(int deviceId) {
        try {
            String hql = "from Device d where d.id = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, deviceId);
            return (Device) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateDeviceById (Device device) {
        
        try {
            String hsql = "update Device d set d.states = ? , d.location = ? where d.id =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setInteger(0, device.getStates());
            query.setString(1, device.getLocation());
            query.setInteger(2, device.getId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Device> findUserDevice(String username, String name) {
        try {
            name = "%" + name +"%";
            String hql = " from Device d where d.username = ? and d.name like ? and d.deleted !=1";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, username);
            query.setString(1, name);
            return  query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
