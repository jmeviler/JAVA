public class sensorIdDao {

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
            String hql = " update Sensor s set s.deleted = 1 where s.sensorId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, sensorId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSensorById(Sensor sensor) {
        try {
            String hsql = "update Sensor s set s.name = ?, s.status = ? where s.sensorId =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setString(0, Sensor.getName());
            query.setString(1, Sensor.getStatus());
            query.setInteger(2, Sensor.getSensorId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Task findUsersDevice(int homeId) {
        try {
            String hql = " from Sensor s where s.sensorId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, sensorId);
            return (Task) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
