-- add pv_user_log table
DROP TABLE IF EXISTS `pv_user_log`;
CREATE TABLE `pv_user_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) DEFAULT NULL,
  `lastdatadate` datetime DEFAULT NULL,
  `unitcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

-- todo need to run this on ssg server overnight
--  Insert data into pv_user_log table with exist patient user,
-- so that we won't miss data.
INSERT INTO pv_user_log(nhsno, unitcode, lastdatadate)
SELECT DISTINCT um.nhsno, l.unitcode, l.date
FROM usermapping um LEFT OUTER JOIN log l
  ON um.nhsno = l.nhsno AND l.action = 'patient data load'
WHERE um.unitcode = 'PATIENT'
  AND um.nhsno != '';
