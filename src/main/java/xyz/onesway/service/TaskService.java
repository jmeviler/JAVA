package xyz.onesway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Task;
import xyz.onesway.dao.taskDao;

/**
 * @author Ben Li
 * @version Date：2015年4月21日 上午10:41:58
 */
@Service
public class TaskService {

    @Autowired
    private taskDao taskDao;

    //create a task
    public boolean createTask (Task task) {
        if (task == null) {
            return false;
        } else {
            if(task.getGoal() == null || task.getActivity() == null) {
                return false;
            } else {
                return taskDao.save(task);
            }
        }
    }

    //delete task by id
    public boolean delete (int taskId) {
        try {
            return taskDao.delete(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //update task's goal
    public boolean updateGoal (int taskId, String goal) {
        try {
            return taskDao.updateGoal(taskId, goal);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Task> findUserTask (int homeId) {
            return taskDao.findTaskByHomeId(homeId);
    }
}
