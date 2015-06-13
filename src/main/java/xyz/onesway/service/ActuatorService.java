package xyz.onesway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Actuator;
import xyz.onesway.dao.actuatorDao;

/**
 * @author Ben Li
 * @version Date：2015年4月21日 上午10:41:58
 */
@Service
public class ActuatorService {

    @Autowired
    private actuatorDao actuatorDao;

    //create a Actuator
    public boolean createActuator (Actuator actuator) {
        if(actuator.getName() == null) {
            return false;
        } else {
            return actuatorDao.save(actuator);
        }
    }

    //delete Actuator by id
    public boolean delete (int actuatorId) {
        try {
            return actuatorDao.delete(actuatorId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Actuator findActuatorById(int actuatorId) {
        try {
            return actuatorDao.findActuatorById(actuatorId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //update Actuator's info
    public boolean updateActuatorById (Actuator actuator) {
        try {
            return actuatorDao.updateActuatorById(actuator);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find uers's task list
    public List<Actuator> findUserActuator (String username, String name) {
        try {
            return actuatorDao.findUserActuator(username,name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

