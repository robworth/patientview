-- Create usermapping table
DROP TABLE IF EXISTS `usermapping`;
CREATE TABLE `usermapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- Remove unused user records
DELETE FROM user WHERE firstlogon = 1;

-- Add records to the usermapping table for patient
INSERT INTO usermapping (username, unitcode, nhsno) SELECT username, unitcode, nhsno FROM user;

-- Add records to the usermapping table for unit PATIENT
INSERT INTO usermapping (username, unitcode, nhsno) SELECT username, "PATIENT", nhsno FROM user WHERE user.role = 'patient' AND user.username NOT LIKE '%-GP';

-- Remove the unitcode and nhsno columns from the user table
ALTER TABLE user DROP COLUMN nhsno;
ALTER TABLE user DROP COLUMN unitcode;

--- Correct spelling of anonymous
ALTER TABLE feedback CHANGE annonymous anonymous TINYINT(1) NOT NULL;