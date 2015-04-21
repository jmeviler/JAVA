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
            String hql = " update Device d set d.deleted = 1 where d.deviceId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, deviceId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserById(Device device) {
        try {
            String hsql = "update Device d set d.name = ?, d.status = ? where d.deviceid =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setString(0, device.getName());
            query.setString(1, device.getStatus());
            query.setInteger(2, device.getDeviceId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Device findUsersTask(int deviceId) {
        try {
            String hql = " from Device d where d.deviceid = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, deviceId);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
