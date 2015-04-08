package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:19:09
 */
@XmlRootElement
public class Task {
    private int taskId;
    private String goal;
    private String activity;
    
    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
