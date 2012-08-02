CREATE TABLE `ibd_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `barriers` varchar(255) default NULL,
  `confidenceScale` int(11) default NULL,
  `furtherTopics` varchar(255) default NULL,
  `goalScale` int(11) default NULL,
  `goalToAchieve` varchar(255) default NULL,
  `goals` varchar(255) default NULL,
  `howToAchieveGoal` varchar(255) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `reviewDate` datetime default NULL,
  `whatCanBeDone` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_careplan_areas_to_discuss` (
  `careplan_id` bigint(20) NOT NULL,
  `area_to_discuss_id` bigint(20) default NULL,
  KEY `FK149F8986A3FEFAD1` (`careplan_id`),
  CONSTRAINT `FK149F8986A3FEFAD1` FOREIGN KEY (`careplan_id`) REFERENCES `ibd_careplan` (`id`)
);

CREATE TABLE `ibd_myibd` (
  `id` bigint(20) NOT NULL auto_increment,
  `body_part_affected_id` bigint(20) NOT NULL,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) NOT NULL,
  `family_history_id` bigint(20) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `smoking_id` bigint(20) NOT NULL,
  `surgery_id` bigint(20) NOT NULL,
  `vaccination_record_id` bigint(20) NOT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_myibd_complications` (
  `myibd_id` bigint(20) NOT NULL,
  `complication_id` bigint(20) default NULL,
  KEY `FKC4EEAD21FDB07963` (`myibd_id`),
  CONSTRAINT `FKC4EEAD21FDB07963` FOREIGN KEY (`myibd_id`) REFERENCES `ibd_myibd` (`id`)
);