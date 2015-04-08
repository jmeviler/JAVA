/**
    Author : Ben Li
    Version : 1.0
    Date: 2015-03-18
*/
create database smart;
-- home table
create table info_home (
        homeid int primary key not null,
        deviceid int not null default 0,
        sensorid int not null default 0,
        actuatorid int not null default 0,
        states int , location varchar(100) ,
        home_entity varchar(100),
        taskid int not null default 0 
        );

create table info_user (
        userid int primary key,
        username varchar(100) not null,
        homeid int not null default 0,
        password varchar(41) not null
    );

create table info_task (
        taskid int primary key not null,
        goal varchar(100) not null default 0,
        activity varchar(100) not null default 0
    );
create table info_device (
        deviceid int  primary key not null,
        name varchar(100) not null,
        states int not null default 0
    );
create table info_sensor (
        sensorid int  primary key not null,
        name varchar(100) not null,
        states int not null default 0
    );

create table info_actuator (
        actuatorid int  primary key not null,
        name varchar(100) not null,
        states int not null default 0
    );



