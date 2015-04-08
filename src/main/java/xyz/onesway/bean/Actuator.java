package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:15:44
 */
@XmlRootElement
public class Actuator {
    private int actuatorId;
    private String name;
    private int states;
        
    public Actuator() {
    }

    public int getActuatorId() {
        return actuatorId;
    }

    public void setActuatorId(int actuatorId) {
        this.actuatorId = actuatorId;
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
    
    
}
