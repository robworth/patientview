--  Changes for 65 - PV - Admin can message all patients in unit


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
);

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
);


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
);


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


SET AUTOCOMMIT=1;
SET FOREIGN_KEY_CHECKS=1;
