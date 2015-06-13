package xyz.onesway.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import xyz.onesway.bean.Temperature;
import xyz.onesway.tool.DB_manager;
import xyz.onesway.tool.GetDate;

/**
 * @author Ben Li
 * @version Date：2015年5月29日 下午2:58:06
 */
public class TemDao extends SuperDao{

    //添加
    public boolean insert(Temperature t){
        t.setName("home1");
        String sql=" insert into info_temperature(year , month , day , hour , minute , name, temperature) values(?,?,?,?,?,?,?)";
        return exe_Update(sql, t.getYear(),t.getMonth(),t.getDay(),t.getHour(),t.getMinute(),t.getName(),t.getTemperature());
    }
    
    //当天的数据
    public List<Temperature> today(String name) {
        Connection con=DB_manager.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement("select hour, minute, temperature from info_temperature where name = ? and year = ? and month = ? and day = ?");
            ps.setString(1, name);
            ps.setInt(2, GetDate.getYear());
            ps.setInt(3, GetDate.getMonth());
            ps.setInt(4, GetDate.getDay());
            rs=ps.executeQuery();
            List<Temperature> al=new ArrayList<Temperature>();
            while (rs.next()) {
                Temperature t=new Temperature();
                t.setYear(GetDate.getYear());
                t.setMonth(GetDate.getMonth());
                t.setDay(GetDate.getDay());
                t.setHour(rs.getInt(1));
                t.setMinute(rs.getInt(2));
                t.setTemperature(rs.getInt(3));;
                al.add(t);
            }
            return al;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //获取某一天的数据
    public List<Temperature> findByDay(String name, int year, int month, int day) {
        Connection con=DB_manager.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=con.prepareStatement("select hour, minute, temperature from info_temperature where name = ? and year = ? and month = ? and day = ?");
            ps.setString(1, name);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ps.setInt(4, day);
            rs=ps.executeQuery();
            List<Temperature> al=new ArrayList<Temperature>();
            while (rs.next()) {
                Temperature t=new Temperature();
                t.setYear(GetDate.getYear());
                t.setMonth(GetDate.getMonth());
                t.setDay(GetDate.getDay());
                t.setHour(rs.getInt(1));
                t.setMinute(rs.getInt(2));
                t.setTemperature(rs.getInt(3));;
                al.add(t);
            }
            return al;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public int getNewTem(String name){
        Connection con=DB_manager.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql = "select temperature from info_temperature where name = ? order by tid desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs=ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1000000000; 
        }
        return 1;
    }

}
