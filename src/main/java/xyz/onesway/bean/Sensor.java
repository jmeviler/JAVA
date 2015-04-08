package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:12:20
 */
@XmlRootElement
public class Sensor {
    private int sensorId;
    private String name;
    private int states;
    
    public Sensor() {
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
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
