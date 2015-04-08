/**
* add new filed deleted
* Author Ben Li
*/

alter table info_user add COLUMN deleted int DEFAULT 0;

alter table info_home add COLUMN deleted int DEFAULT 0;

alter table info_device add COLUMN deleted int DEFAULT 0;

alter table info_sensor add COLUMN deleted int DEFAULT 0;

alter table info_actuator add COLUMN deleted int DEFAULT 0;

alter table info_task add COLUMN deleted int DEFAULT 0;

