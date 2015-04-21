package xyz.onesway.service;

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
        try {
            return deviceDao.delete(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //update Device's info
    public boolean updateDevice (Device device) {
        try {
            return deviceDao.updateDevice(device);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find uers's task list
    public List<Device> findUserDevice (int homeId) {
        try {
            return deviceDao.findUserDevice(homeId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

