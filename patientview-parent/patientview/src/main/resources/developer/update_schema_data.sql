SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;


--
-- Source for table pv_job
--

DROP TABLE IF EXISTS `pv_job`;
CREATE TABLE `pv_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `errorCount` bigint(20) NOT NULL,
  `finished` datetime DEFAULT NULL,
  `groupEnum` varchar(255) NOT NULL,
  `started` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `message_id` bigint(20) NOT NULL,
  `specialty_id` bigint(20) NOT NULL,
  `report` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table pv_job
--

LOCK TABLES `pv_job` WRITE;
/*!40000 ALTER TABLE `pv_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Source for table pv_emailqueue
--

DROP TABLE IF EXISTS `pv_emailqueue`;
CREATE TABLE `pv_emailqueue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `message_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `finished` datetime DEFAULT NULL,
  `report` text,
  PRIMARY KEY (`id`),
  KEY `FKC4A69AF58F35B002` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Dumping data for table pv_emailqueue
--

LOCK TABLES `pv_emailqueue` WRITE;
/*!40000 ALTER TABLE `pv_emailqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_emailqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
--  Foreign keys for table pv_emailqueue
--

ALTER TABLE `pv_emailqueue`
ADD CONSTRAINT `FKC4A69AF58F35B002` FOREIGN KEY (`job_id`) REFERENCES `pv_job` (`id`);


--
-- Source for table pv_groupmessage
--

DROP TABLE IF EXISTS `pv_groupmessage`;
CREATE TABLE `pv_groupmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table pv_groupmessage
--

LOCK TABLES `pv_groupmessage` WRITE;
/*!40000 ALTER TABLE `pv_groupmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_groupmessage` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Source for table conversation
--
Alter TABLE conversation
ADD COLUMN type varchar(255) DEFAULT NULL,
ADD COLUMN groupEnum varchar(255) DEFAULT NULL;


--
-- Source for table message
--
Alter TABLE message
ADD COLUMN type varchar(255) DEFAULT NULL,
ADD COLUMN groupEnum varchar(255) DEFAULT NULL,
ADD COLUMN unit_id BIGINT(20) DEFAULT NULL;

ALTER TABLE `message`
ADD CONSTRAINT `fk_message_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`);

ALTER TABLE `message`
MODIFY COLUMN `recipient_id` bigint(20);

--
-- Update user table
--
ALTER TABLE USER ADD COLUMN isrecipient TINYINT(1) NOT NULL DEFAULT 0;
ALTER TABLE USER ADD COLUMN isclinician TINYINT(1) NOT NULL DEFAULT 0;


--
-- add pv_user_log table
--
DROP TABLE IF EXISTS `pv_user_log`;
CREATE TABLE `pv_user_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) DEFAULT NULL,
  `lastdatadate` datetime DEFAULT NULL,
  `unitcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Insert data into pv_user_log table with exist patient user,
-- so that we won't miss data.
--
INSERT INTO pv_user_log(nhsno, unitcode, lastdatadate)
SELECT DISTINCT um.nhsno, l.unitcode, l.date
FROM usermapping um LEFT OUTER JOIN log l
  ON um.nhsno = l.nhsno AND l.action = 'patient data load'
WHERE um.unitcode = 'PATIENT'
  AND um.nhsno != ''


SET AUTOCOMMIT=1;
SET FOREIGN_KEY_CHECKS=1;
