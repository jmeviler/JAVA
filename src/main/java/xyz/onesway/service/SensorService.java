package xyz.onesway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Sensor;
import xyz.onesway.dao.sensorDao;

/**
 * @author Ben Li
 * @version Date：2015年4月21日 上午10:41:58
 */
@Service
public class SensorService {

    @Autowired
    private sensorDao sensorDao;

    //create a Sensor
    public boolean createSensor (Sensor sensor) {
        if(sensor.getName() == null) {
            return false;
        } else {
            return SensorDao.save(sensor);
        }
    }

    //delete Sensor by id
    public boolean delete (int sensorId) {
        try {
            return sensorDao.delete(sensorId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //update Sensor's info
    public boolean updateSensor (Sensor sensor) {
        try {
            return sensorDao.updateSensor(sensor);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find uers's task list
    public List<Sensor> findUserSensor (int homeId) {
        try {
            return sensorDao.findUserSensor(homeId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

