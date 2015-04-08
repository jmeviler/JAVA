package xyz.onesway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Home;
import  xyz.onesway.dao.homeDao;

@Service
public class HomeService {
    @Autowired
    private homeDao homeDao;

    public boolean save(Home home) {
        return homeDao.save(home);
    }

    public boolean update(int homeId,Home home) {
        home.setHomeId(homeId);
        return homeDao.update(home);
    }
    
    public List<Home> findAll(){
        return homeDao.findAll();
    }
    
    public Home findById(int homeId) {
        return homeDao.findById(homeId);
    }
    
    public boolean deleteById(int homeId) {
        return homeDao.deleteById(homeId);
    }
}
