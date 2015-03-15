package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Home {
    private int homeId;
    private String userName;
    private String device;
    
    public Home() {
    }    
    
    public int getHomeId() {
        return homeId;
    }
    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }

}
