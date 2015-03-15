package xyz.onesway.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.onesway.bean.Home;
import xyz.onesway.service.HomeService;

public class Test001 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        HomeService service = (HomeService) ctx.getBean("homeService");
        Home home = new Home();
        home.setHomeId(2);
        home.setUserName("admin");
        home.setDevice("Fix");
        System.out.println(service.save(home));
    }
}
