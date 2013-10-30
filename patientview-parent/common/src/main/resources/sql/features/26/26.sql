/**
Add taskTitle column in pv_patientjoin_request table.
*/
ALTER TABLE pv_patientjoin_request ADD COLUMN `taskTitle` varchar(100) DEFAULT NULL;

UPDATE pv_patientjoin_request
 SET taskTitle = 'Join request';