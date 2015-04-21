public class taskDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(Task task) {
            try {
                sessionFactory.getCurrentSession().save(task);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
    }

    public boolean delete(int taskId) {
        try {
            String hql = " update Task t set t.deleted = 1 where t.taskId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setInteger(0, taskId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateGoal(int taskId, String goal) {
        try {
            String hsql = "update Task t set t.goal = ? where t.taskId =?";
            Query query = sessionFactory.getCurrentSession().createQuery(hsql);
            query.setString(0, goal);
            query.setInteger(1, taskId);
            return (query.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Task> findTaskByHomeId(int homeId) {
        try {
            String hql = "from Task t left join Home h on t.taskId = h.taskId where h.homeId = ?";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setString(0, homeId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
