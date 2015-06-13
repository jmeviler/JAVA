package xyz.onesway.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ben Li
 * @version Date：2015年5月28日 下午9:50:59
 */
@XmlRootElement
public class Temperature {
    
    private int  tId; 
    private int  year;    
    private int month;    
    private int  day;    
    private int  hour;    
    private int  minute;
    private int temperature;
    private String name;

    public Temperature() {
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }



    public int getYear() {
        return year;
    }


    public void setYear(int year) {
        this.year = year;
    }


    public int getMonth() {
        return month;
    }


    public void setMonth(int month) {
        this.month = month;
    }


    public int getDay() {
        return day;
    }


    public void setDay(int day) {
        this.day = day;
    }


    public int getHour() {
        return hour;
    }


    public void setHour(int hour) {
        this.hour = hour;
    }


    public int getMinute() {
        return minute;
    }


    public void setMinute(int minute) {
        this.minute = minute;
    }


    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


}
