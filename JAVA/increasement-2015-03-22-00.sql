/**
* author :Ben Li
* add auto_increment
*/

alter table info_user modify userid int auto_increment;
alter table info_home modify homeid int auto_increment;
alter table info_actuator modify actuatorid int auto_increment;
alter table info_device modify deviceid int auto_increment;
alter table info_sensor modify sensorid int auto_increment;
alter table info_task modify taskid int auto_increment;
