package xyz.onesway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Device;
import xyz.onesway.dao.deviceDao;

/**
 * @author Ben Li
 * @version Date：2015年4月21日 上午10:41:58
 */
@Service
public class DeviceService {

    @Autowired
    private deviceDao deviceDao;

    //create a Device
    public boolean createDevice (Device device) {
        if(device.getName() == null) {
            return false;
        } else {
            return deviceDao.save(device);
        }
    }

    //delete Device by id
    public boolean delete (int deviceId) {
        System.out.println(deviceId);
        try {
            return deviceDao.delete(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Device findDeviceById(int deviceId) {
        try {
            return deviceDao.findDeviceById(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //update Device's info
    public boolean updateDeviceById (Device device) {
        try {
            return deviceDao.updateDeviceById(device);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find uers's task list
    public List<Device> findUserDevice (String username, String name) {
        try {
            return deviceDao.findUserDevice(username,name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

