package xyz.onesway.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import smart.SpacebrewServiceServer;
import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;

/**
 * @author Ben Li
 * @version Date：2015年5月28日 下午10:30:20
 */
@Path("/tem")
public class TemResource {
    private SpacebrewServiceServer serviceServer ;
    public TemResource() {
        serviceServer =  new SpacebrewServiceServer();
    }
    
    @Path("/today/{name}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
        MediaType.TEXT_PLAIN })
    public String today(@PathParam("name") String name){
        String str = "";
        TemDao temDao = new TemDao();
        List<Temperature> list = temDao.today(name);
        int tem = 0;
        int count = 1;
        System.out.println(list.size());
        for(int j = 0; j < 24; j++) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getHour() == j){
                    count+=1;
                    tem+=list.get(i).getTemperature();
                }
            }
            str+=tem/count+",";
            tem =0;
            count =1;
        }
        return str;
    }
    
    @Path("/new/{name}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
        MediaType.TEXT_PLAIN })
    public int getTem(@PathParam("name") String name){
        TemDao dao = new TemDao();
        return dao.getNewTem(name);
    }
    
    
    @Path("/test/{name}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
        MediaType.TEXT_PLAIN })
    public int test(@PathParam("name") String name){
        serviceServer.setSwtich(false);
        return 0;
    }
}
