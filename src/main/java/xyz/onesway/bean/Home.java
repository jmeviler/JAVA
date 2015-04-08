package xyz.onesway.bean;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Home {
    private int homeId;
    private int deviceid;
    private int sensorid;
    private int actuatorid;
    private int states;
    private String location;
    private String home_entity;
    private int taskid;
    
    public Home() {
    }

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    public int getSensorid() {
        return sensorid;
    }

    public void setSensorid(int sensorid) {
        this.sensorid = sensorid;
    }

    public int getActuatorid() {
        return actuatorid;
    }

    public void setActuatorid(int actuatorid) {
        this.actuatorid = actuatorid;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHome_entity() {
        return home_entity;
    }

    public void setHome_entity(String home_entity) {
        this.home_entity = home_entity;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

}
