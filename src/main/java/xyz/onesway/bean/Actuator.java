package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:15:44
 */
@XmlRootElement
public class Actuator {
    private int id;
    private String name;
    private int states;
    private int deleted;
    private String username;
    private String location;

    public Actuator() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public int getDeleted() {
        return deleted;
    }
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
