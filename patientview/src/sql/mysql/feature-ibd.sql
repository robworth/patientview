CREATE TABLE `ibd_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `barriers` text,
  `confidenceScale` int(11) default NULL,
  `furtherTopics` text,
  `goalScale` int(11) default NULL,
  `goalToAchieve` text,
  `goals` text,
  `howToAchieveGoal` text,
  `nhsno` varchar(255) NOT NULL,
  `reviewDate` datetime default NULL,
  `whatCanBeDone` text,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_careplan_areas_to_discuss` (
  `careplan_id` bigint(20) NOT NULL,
  `area_to_discuss_id` bigint(20) default NULL,
  KEY `FK149F8986A3FEFAD1` (`careplan_id`),
  CONSTRAINT `FK149F8986A3FEFAD1` FOREIGN KEY (`careplan_id`) REFERENCES `ibd_careplan` (`id`)
);

CREATE TABLE `ibd_medication` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` text NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_medication_allowed_dosages` (
  `medication_id` bigint(20) NOT NULL,
  `dose_id` bigint(20) NOT NULL,
  KEY `FKBA6261E967F0EF1` (`dose_id`),
  KEY `FKBA6261E999BAAE42` (`medication_id`),
  CONSTRAINT `FKBA6261E999BAAE42` FOREIGN KEY (`medication_id`) REFERENCES `ibd_medication` (`id`),
  CONSTRAINT `FKBA6261E967F0EF1` FOREIGN KEY (`dose_id`) REFERENCES `ibd_medication_dose` (`id`)
);

CREATE TABLE `ibd_medication_dose` (
  `id` bigint(20) NOT NULL auto_increment,
  `extraInformation` varchar(255) default NULL,
  `mg` double NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_medication_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` text NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_medication_type_medications` (
  `medication_type_id` bigint(20) NOT NULL,
  `medication_id` bigint(20) NOT NULL,
  KEY `FK4D48F99B99BAAE42` (`medication_id`),
  KEY `FK4D48F99B57719061` (`medication_type_id`),
  CONSTRAINT `FK4D48F99B57719061` FOREIGN KEY (`medication_type_id`) REFERENCES `ibd_medication_type` (`id`),
  CONSTRAINT `FK4D48F99B99BAAE42` FOREIGN KEY (`medication_id`) REFERENCES `ibd_medication` (`id`)
);

CREATE TABLE `ibd_my_medication` (
  `id` bigint(20) NOT NULL auto_increment,
  `dateStarted` datetime NOT NULL,
  `dateStopped` datetime default NULL,
  `medication_frequency_id` bigint(20) NOT NULL,
  `medication_no_of_id` bigint(20) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherMedication` text,
  `reasonForStopping` text,
  `medication_id` bigint(20) default NULL,
  `medication_dose_id` bigint(20) default NULL,
  `medication_type_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
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

CREATE TABLE `ibd_nutrition` (
  `id` bigint(20) NOT NULL auto_increment,
  `comment` varchar(255) default NULL,
  `foodsThatDisagree` varchar(255) NOT NULL,
  `weight` double NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `nutritionDate` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `ibd_crohns` (
  `id` bigint(20) NOT NULL auto_increment,
  `abdominalPain` int(11) NOT NULL,
  `chornsDate` datetime NOT NULL,
  `complications` int(11) NOT NULL,
  `feeling` int(11) NOT NULL,
  `massInTummy` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `openBowels` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `ibd_colitis` (
  `id` bigint(20) NOT NULL auto_increment,
  `colitisDate` datetime NOT NULL,
  `feeling` int(11) NOT NULL,
  `furtherComplications` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `presentBlood` int(11) NOT NULL,
  `stoolsDay` int(11) NOT NULL,
  `stoolsNight` int(11) NOT NULL,
  `toiletTiming` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/** MEDICATION TABLE DATE */
insert  into `ibd_medication`(`id`,`name`) values (1,'Asacol'),(2,'Pentasa'),(3,'Salofalk'),(4,'Azathioprine');
insert  into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,1),(1,2),(2,3),(3,4),(4,5);
insert  into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (1,NULL,400),(2,NULL,800),(3,NULL,500),(4,NULL,750),(5,'/KG',10);
insert  into `ibd_medication_type`(`id`,`name`) values (1,'Aminosalicylate'),(2,'Immunomodulators');
insert  into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,1),(1,2),(1,3),(2,4);
