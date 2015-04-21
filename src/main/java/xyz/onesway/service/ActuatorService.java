package xyz.onesway.service;

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

    //update Actuator's info
    public boolean updateActuator (Actuator actuator) {
        try {
            return actuatorDao.updateActuator(actuator);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //find uers's task list
    public List<Actuator> findUserActuator (int homeId) {
        try {
            return actuatorDao.findUserActuator(homeId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

