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
            String hql = " update Actuator a set a.deleted = 1 where a.actuatorId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, actuatorId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserById(Actuator actuator) {
        try {
            String hsql = "update Actuator a set a.name = ?, a.status = ? where a.actuatorId =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setString(0, Actuator.getName());
            query.setString(1, Actuator.getStatus());
            query.setInteger(2, Actuator.getActuatorId());
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Task findUserById(int actuatorId) {
        try {
            String hql = " from Actuator a where a.actuatorId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, actuatorId);
            return (Task) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
