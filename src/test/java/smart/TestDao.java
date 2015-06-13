package smart;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xyz.onesway.bean.Temperature;

/**
 * @author Ben Li
 * @version Date：2015年5月30日 下午4:40:06
 */
public class TestDao extends HibernateDaoSupport{
    
    public void save(int tem){
        Temperature temperature = new Temperature();
        temperature.setTemperature(tem);
        this.getHibernateTemplate().save(temperature);
    }
    
}
