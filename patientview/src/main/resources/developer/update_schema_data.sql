SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;


--
-- Source for table job
--

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
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
-- Dumping data for table job
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Source for table emailqueue
--

DROP TABLE IF EXISTS `emailqueue`;
CREATE TABLE `emailqueue` (
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
-- Dumping data for table emailqueue
--

LOCK TABLES `emailqueue` WRITE;
/*!40000 ALTER TABLE `emailqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
--  Foreign keys for table emailqueue
--

ALTER TABLE `emailqueue`
ADD CONSTRAINT `FKC4A69AF58F35B002` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);


--
-- Source for table groupmessage
--

DROP TABLE IF EXISTS `groupmessage`;
CREATE TABLE `groupmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table groupmessage
--

LOCK TABLES `groupmessage` WRITE;
/*!40000 ALTER TABLE `groupmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupmessage` ENABLE KEYS */;
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
ADD COLUMN groupEnum varchar(255) DEFAULT NULL;



SET AUTOCOMMIT=1;
SET FOREIGN_KEY_CHECKS=1;
