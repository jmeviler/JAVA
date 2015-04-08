package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Li
 * @version Date：2015年3月19日 下午10:08:18
 */

@XmlRootElement
public class Device {
    
    private int deviceId;
    private String name;
    private int states;
    
    public Device() {
    }
    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
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
