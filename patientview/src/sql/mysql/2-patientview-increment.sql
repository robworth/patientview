/**
 *    Used to upgrade the current live patientview database to match the codebase.
 *
 */


-- FEATURE-JPA

ALTER TABLE aboutme MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE centre ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE comment MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE diagnosis MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE edtacode DROP PRIMARY KEY;
ALTER TABLE edtacode ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE edtacode ADD UNIQUE (edtaCode);

ALTER TABLE emailverification MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE feedback MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE letter MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE log MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE medicine MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE news MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE patient DROP PRIMARY KEY;
ALTER TABLE patient ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE patient ADD UNIQUE INDEX (nhsno, centreCode);

ALTER TABLE result_heading DROP PRIMARY KEY;
ALTER TABLE result_heading ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE result_heading ADD UNIQUE (headingcode);

ALTER TABLE splashpage MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE splashpageuserseen MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE testresult ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE treatment DROP PRIMARY KEY;
ALTER TABLE treatment ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE treatment ADD UNIQUE INDEX (nhsNo, treatmentCode);

ALTER TABLE uktcode MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE uktstatus ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE unit DROP PRIMARY KEY;
ALTER TABLE unit ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE unit ADD UNIQUE (unitcode);

ALTER TABLE unitstat MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE USER DROP PRIMARY KEY;
ALTER TABLE USER ADD id BIGINT(20)
NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE USER ADD UNIQUE (username);

ALTER TABLE userlog MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE usermapping MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;


-- FEATURE-MULTITENANT


CREATE TABLE `tenancy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `context` (`context`),
  UNIQUE KEY `name` (`name`)
);

INSERT INTO tenancy VALUES (1, 'rpv', 'Renal Patient View', 'Renal Patient View');
INSERT INTO tenancy VALUES (2, 'ibd', 'Inflammatory bowel disease', 'Inflammatory bowel disease');

CREATE TABLE `tenancyuserrole` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(255) NOT NULL,
  `tenancy_id` BIGINT(20) DEFAULT NULL,
  `user_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tenancy_id` (`tenancy_id`,`user_id`,`role`),
  KEY `FK7A1749E1AEFDD122` (`tenancy_id`),
  CONSTRAINT `FK7A1749E1AEFDD122` FOREIGN KEY (`tenancy_id`) REFERENCES `tenancy` (`id`)
);

INSERT INTO tenancyuserrole (role, tenancy_id, user_id) SELECT role, 1, id FROM USER;

-- per tenancy system data
ALTER TABLE edtacode ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE edtacode set tenancy_id = 1;

ALTER TABLE result_heading ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE result_heading set tenancy_id = 1;

ALTER TABLE unit ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE unit set tenancy_id = 1;

ALTER TABLE news ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE news SET tenancy_id = 1;

ALTER TABLE splashpage ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE splashpage SET tenancy_id = 1;

ALTER TABLE usermapping ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE usermapping SET tenancy_id = 1;

ALTER TABLE log ADD tenancy_id BIGINT(20);

UPDATE log SET tenancy_id = 1;

-- indexes to speed up queries

CREATE INDEX nhs_log_index1 ON LOG (nhsno);
CREATE INDEX action_log_index1 ON LOG (action);


-- FEATURE-IBD

CREATE TABLE `ibd_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `barriers` text,
  `confidence_id` bigint(20) NOT NULL,
  `eatingAHealthyDietScore` int(11) default NULL,
  `fertilityPregnancyScore` int(11) default NULL,
  `goalToAchieve` text,
  `goals` text,
  `howToAchieveGoal` text,
  `importance_id` bigint(20) NOT NULL,
  `learningAboutMyConditionScore` int(11) default NULL,
  `managingFlareUpsScore` int(11) default NULL,
  `managingMySocialLifeHobbiesScore` int(11) default NULL,
  `managingPainScore` int(11) default NULL,
  `managingWorkStudiesScore` int(11) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherAreasToDiscuss` text,
  `overallMyConditionScore` int(11) default NULL,
  `reviewDate` datetime default NULL,
  `sexualRelationshipsScore` int(11) default NULL,
  `sleepingScore` int(11) default NULL,
  `stoppingSmokingScore` int(11) default NULL,
  `stressAndWorryScore` int(11) default NULL,
  `supportFromFamilyAndFriendsScore` int(11) default NULL,
  `takingMyMedicinesRegularlyScore` int(11) default NULL,
  `tirednessFatigueScore` int(11) default NULL,
  `travellingScore` int(11) default NULL,
  `whatCanBeDone` text,
  PRIMARY KEY  (`id`)
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
  `mg` double default NULL,
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
  `nhsno` varchar(255) NOT NULL,
  `otherMedication` text,
  `reasonForStopping` text,
  `medication_id` bigint(20) default NULL,
  `medication_dose_id` bigint(20) default NULL,
  `medication_type_id` bigint(20) NOT NULL,
  `otherMedicationDose` text,
  PRIMARY KEY  (`id`),
  KEY `FK434B606EF8204181` (`medication_dose_id`),
  KEY `FK434B606E99BAAE42` (`medication_id`),
  KEY `FK434B606E57719061` (`medication_type_id`),
  CONSTRAINT `FK434B606E57719061` FOREIGN KEY (`medication_type_id`) REFERENCES `ibd_medication_type` (`id`),
  CONSTRAINT `FK434B606E99BAAE42` FOREIGN KEY (`medication_id`) REFERENCES `ibd_medication` (`id`),
  CONSTRAINT `FK434B606EF8204181` FOREIGN KEY (`medication_dose_id`) REFERENCES `ibd_medication_dose` (`id`)
);

CREATE TABLE `ibd_myibd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body_part_affected_id` bigint(20) DEFAULT NULL,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) DEFAULT NULL,
  `namedConsultant` text,
  `nhsno` varchar(10) NOT NULL,
  `nurses` text,
  `yearForSurveillanceColonoscopy` datetime DEFAULT NULL,
  `yearOfDiagnosis` datetime DEFAULT NULL,
  `family_history_id` bigint(20) DEFAULT NULL,
  `smoking_id` bigint(20) DEFAULT NULL,
  `surgery_id` bigint(20) DEFAULT NULL,
  `vaccination_record_id` bigint(20) DEFAULT NULL,
  `weight` double DEFAULT NULL
  PRIMARY KEY (`id`)
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
  `nhsno` varchar(255) NOT NULL,
  `nutritionDate` datetime NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_colitis_symptoms` (
  `id` bigint(20) NOT NULL auto_increment,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `complication_id` int(11) NOT NULL,
  `number_of_stools_daytime_id` int(11) NOT NULL,
  `number_of_stools_nighttime_id` int(11) NOT NULL,
  `present_blood_id` int(11) NOT NULL,
  `toilet_timing_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `ibd_crohns_symptoms` (
  `id` bigint(20) NOT NULL auto_increment,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `abdominal_pain_id` int(11) NOT NULL,
  `complication_id` int(11) NOT NULL,
  `mass_in_tummy_id` int(11) NOT NULL,
  `openBowels` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
);

ALTER TABLE patient ADD otherConditions TEXT;

CREATE TABLE `diagnostic` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `datestamp` DATETIME NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `diagnostic_type_id` INT(11) NOT NULL,
  `nhsno` VARCHAR(255) NOT NULL,
  `unitcode` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `ibd_myibd_severity_level` (
  `id` bigint(20) NOT NULL auto_increment,
  `level` int(11) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `severity_id` bigint(20) NOT NULL,
  `treatment` text,
  PRIMARY KEY  (`id`)
);


-- IBD Medication Data

insert into `ibd_medication`(`id`,`name`) values (1,'Asacol MR 400 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (1,NULL,400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,1);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (2,NULL,800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,2);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (3,NULL,1200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,3);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (4,NULL,1600.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,4);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (5,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,5);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (6,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,6);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (7,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (1,7);
insert into `ibd_medication`(`id`,`name`) values (2,'Asacol MR 800 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (8,NULL,800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (2,8);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (9,NULL,1600.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (2,9);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (10,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (2,10);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (11,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (2,11);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (12,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (2,12);
insert into `ibd_medication`(`id`,`name`) values (14,'Balsazide (Colazide) 750 mg capsule');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (59,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (14,59);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (60,NULL,2250.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (14,60);
insert into `ibd_medication`(`id`,`name`) values (11,'Ipocol MR 400 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (40,NULL,400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,40);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (41,NULL,800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,41);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (42,NULL,1200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,42);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (43,NULL,1600.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,43);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (44,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,44);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (45,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,45);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (46,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (11,46);
insert into `ibd_medication`(`id`,`name`) values (12,'Mesren MR 400 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (47,NULL,400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,47);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (48,NULL,800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,48);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (49,NULL,1200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,49);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (50,NULL,1600.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,50);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (51,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,51);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (52,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,52);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (53,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (12,53);
insert into `ibd_medication`(`id`,`name`) values (10,'Mezavant XL 1.2 G tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (36,NULL,1200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (10,36);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (37,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (10,37);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (38,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (10,38);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (39,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (10,39);
insert into `ibd_medication`(`id`,`name`) values (13,'Octasa  MR 800 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (54,NULL,800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (13,54);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (55,NULL,1600.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (13,55);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (56,NULL,2400.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (13,56);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (57,NULL,3200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (13,57);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (58,NULL,4800.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (13,58);
insert into `ibd_medication`(`id`,`name`) values (15,'Olsalazine (Dipentum) 250 mg capsule');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (61,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,61);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (62,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,62);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (63,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,63);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (64,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,64);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (65,NULL,2500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,65);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (66,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (15,66);
insert into `ibd_medication`(`id`,`name`) values (16,'Olsalazine (Dipentum) 500 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (67,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,67);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (68,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,68);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (69,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,69);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (70,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,70);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (71,NULL,2500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,71);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (72,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (16,72);
insert into `ibd_medication`(`id`,`name`) values (3,'Pentasa 1 G granules');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (13,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (3,13);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (14,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (3,14);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (15,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (3,15);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (16,NULL,4000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (3,16);
insert into `ibd_medication`(`id`,`name`) values (4,'Pentasa 2 G granules');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (17,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (4,17);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (18,NULL,4000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (4,18);
insert into `ibd_medication`(`id`,`name`) values (8,'Salofalk 1.5 G granules');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (33,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (8,33);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (34,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (8,34);
insert into `ibd_medication`(`id`,`name`) values (5,'Salofalk 250 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (19,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (5,19);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (20,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (5,20);
insert into `ibd_medication`(`id`,`name`) values (9,'Salofalk 3 G granules');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (35,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (9,35);
insert into `ibd_medication`(`id`,`name`) values (7,'Salofalk 500 mg granules');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (27,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,27);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (28,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,28);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (29,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,29);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (30,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,30);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (31,NULL,2500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,31);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (32,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (7,32);
insert into `ibd_medication`(`id`,`name`) values (6,'Salofalk 500 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (21,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,21);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (22,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,22);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (23,NULL,1500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,23);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (24,NULL,2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,24);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (25,NULL,2500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,25);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (26,NULL,3000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (6,26);
insert into `ibd_medication`(`id`,`name`) values (18,'Sulfasalazine (Salazopyrin) 250 mg/5ml suspension');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (75,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (18,75);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (76,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (18,76);
insert into `ibd_medication`(`id`,`name`) values (17,'Sulfasalazine (Salazopyrin) 500 mg tablet');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (73,NULL,500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (17,73);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (74,NULL,1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (17,74);
insert into `ibd_medication_type`(`id`,`name`) values (1,'Aminosalicylates (Mesalazine / 5 ASAs)');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,1);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,2);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,14);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,11);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,12);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,10);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,13);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,15);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,16);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,3);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,4);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,8);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,5);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,9);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,7);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,6);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,18);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (1,17);
insert into `ibd_medication`(`id`,`name`) values (20,'Aminosalicylate / 5ASA Foam Enemas (Asacol / Salofalk)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (80,'Asacol foam enema',1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (20,80);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (81,'Salofalk foam enema',1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (20,81);
insert into `ibd_medication`(`id`,`name`) values (21,'Aminosalicylate / 5ASA Liquid Enemas (Pentasa / Salofalk)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (82,'Pentasa liquid enema',1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (21,82);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (83,'Salofalk liquid enema',2000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (21,83);
insert into `ibd_medication`(`id`,`name`) values (19,'Aminosalicylate / 5ASA Suppositories (Asacol / Pentasa / Salofalk)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (77,'Asacol rectal suppositories',250.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (19,77);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (78,'Asacol rectal suppositories',500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (19,78);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (79,'Pentasa rectal suppositories',1000.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (19,79);
insert into `ibd_medication`(`id`,`name`) values (23,'Steroid Foam Enemas (Colifoam/ Prednisolone /Budenofalk)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (87,'Budenofalk foam enema',2.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (23,87);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (86,'Prednisolone foam enema',20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (23,86);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (85,'Colifoam foam enema',125.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (23,85);
insert into `ibd_medication`(`id`,`name`) values (24,'Steroid Liquid Enemas (Predenema/ Predsol)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (88,'Predenema liquid enema',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (24,88);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (89,'Predsol liquid enema',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (24,89);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (90,'Entocort liquid enema',2.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (24,90);
insert into `ibd_medication`(`id`,`name`) values (22,'Steroid suppositories (Predsol)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (84,'Predsol rectal suppositories',5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (22,84);
insert into `ibd_medication_type`(`id`,`name`) values (2,'Rectal / Topical Treatments (Suppositories / Enemas)');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,20);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,21);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,19);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,23);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,24);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (2,22);
insert into `ibd_medication`(`id`,`name`) values (26,'Budenofalk (Budesonide)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (100,NULL,3.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (26,100);
insert into `ibd_medication`(`id`,`name`) values (28,'Clipper (beclamethasone)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (102,NULL,5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (28,102);
insert into `ibd_medication`(`id`,`name`) values (27,'Entocort (Budesonide)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (101,NULL,3.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (27,101);
insert into `ibd_medication`(`id`,`name`) values (25,'Prednisolone');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (95,'maintenance dose',5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,95);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (96,'maintenance dose',10.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,96);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (97,'maintenance dose',15.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,97);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (98,'maintenance dose',20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,98);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (93,'reducing by 5mg per week',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,93);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (94,'reducing by 5mg per fortnight',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,94);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (99,'maintenance dose',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,99);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (91,'reducing by 5mg per week',40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,91);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (92,'reducing by 5mg per fortnight',40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (25,92);
insert into `ibd_medication_type`(`id`,`name`) values (3,'Oral Steroids (Prednisolone / Budesonide)');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (3,26);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (3,28);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (3,27);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (3,25);
insert into `ibd_medication`(`id`,`name`) values (29,'Azathioprine');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (103,NULL,25.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,103);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (104,NULL,50.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,104);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (105,NULL,75.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,105);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (106,NULL,100.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,106);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (107,NULL,125.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,107);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (108,NULL,150.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,108);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (109,NULL,175.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,109);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (110,NULL,200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,110);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (111,NULL,225.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,111);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (112,NULL,250.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (29,112);
insert into `ibd_medication`(`id`,`name`) values (30,'Mercaptopurine');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (113,NULL,12.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,113);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (114,NULL,25.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,114);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (115,NULL,50.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,115);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (116,NULL,62.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,116);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (117,NULL,75.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,117);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (118,NULL,100.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,118);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (119,NULL,125.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,119);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (120,NULL,150.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (30,120);
insert into `ibd_medication`(`id`,`name`) values (31,'Methotrexate');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (121,NULL,2.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,121);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (122,NULL,5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,122);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (123,NULL,7.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,123);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (124,NULL,10.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,124);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (125,NULL,12.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,125);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (126,NULL,15.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,126);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (127,NULL,17.5);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,127);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (128,NULL,20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,128);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (129,NULL,25.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (31,129);
insert into `ibd_medication_type`(`id`,`name`) values (4,'Immunomodulators (Azathioprine / Mercaptopurine / Methotrexate)');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (4,29);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (4,30);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (4,31);
insert into `ibd_medication`(`id`,`name`) values (33,'Adalimumab (Humira)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (132,NULL,40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (33,132);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (133,NULL,80.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (33,133);
insert into `ibd_medication`(`id`,`name`) values (32,'Infliximab (Remicade)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (130,'/kg infusion',5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (32,130);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (131,'/kg infusion',10.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (32,131);
insert into `ibd_medication_type`(`id`,`name`) values (5,'Biologics (Adalimumab-Humira /  Infliximab-Remicade)');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (5,33);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (5,32);
insert into `ibd_medication`(`id`,`name`) values (38,'Anit-diarrhoeals');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (167,'Loperamide',2.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,167);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (168,'Loperamide',4.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,168);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (169,'Loperamide',6.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,169);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (170,'Loperamide',8.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,170);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (171,'Loperamide',10.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,171);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (172,'Loperamide',12.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (38,172);
insert into `ibd_medication`(`id`,`name`) values (36,'Anti-acid');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (152,'Omeprazole',10.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,152);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (155,'Lansoprazole',15.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,155);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (153,'Omeprazole',20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,153);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (157,'Pantoprazole',20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,157);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (159,'Eso-ompreazole (Nexium)',20.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,159);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (156,'Lansoprazole',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,156);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (154,'Omeprazole',40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,154);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (158,'Pantoprazole',40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,158);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (160,'Eso-ompreazole (Nexium)',40.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,160);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (150,'Ranitidine',150.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,150);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (151,'Ranitine',300.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (36,151);
insert into `ibd_medication`(`id`,`name`) values (34,'Iron');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (140,'/5ml Ferrous fumarate (Galfer) syrup',140.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,140);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (135,'Ferrous sulphate MR (Feospan) capsules',150.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,135);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (134,'Ferrous sulphate tablets',200.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,134);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (138,'Ferrous fumarate (Fersamal) tablets',210.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,138);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (141,'Ferrous gluconate tablets',300.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,141);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (139,'Ferrous fumarate (Galfer) capsules',305.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,139);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (137,'Ferrous fumarate (Fersaday) tablets',322.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,137);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (136,'Ferrous sulphate MR (Ferrograd) tablets',325.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (34,136);
insert into `ibd_medication`(`id`,`name`) values (35,'Painkillers (Paracetamol, Codeine, Co-codamol)');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (146,'Co-codamol (8mg codeine/500 mg paracetamol) tablets',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,146);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (147,'Co-codamol (30 mg codeine/ 500 mg paracetamol) tablets',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,147);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (144,'Codeine tablets',15.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,144);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (145,'Codeine tablets',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,145);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (148,'Dihydrocodeine',30.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,148);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (149,'Dihydrocodeine',60.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,149);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (142,'Paracetamol tablets',500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,142);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (143,'Paracetamol capsules',500.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (35,143);
insert into `ibd_medication`(`id`,`name`) values (37,'Vitamins & minerals');
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (161,'Calcium (One tablet)',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,161);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (162,'Caclium (Two tablets)',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,162);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (163,'Calcium with vitamin D (one tablet)',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,163);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (164,'Calcium with Vitamin D (two tablets)',0.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,164);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (165,'B12 injections (Hydroxocobalamin)',1.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,165);
insert into `ibd_medication_dose`(`id`,`extraInformation`,`mg`) values (166,'Folic acid',5.0);
insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values (37,166);
insert into `ibd_medication_type`(`id`,`name`) values (6,'Other');
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (6,38);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (6,36);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (6,34);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (6,35);
insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values (6,37);

-- my ibd links data


insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Primary Diagnosis','myIbdLinks','Primary Diagnosis','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Disease Extent','myIbdLinks','Disease Extent','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Year of Diagnosis','myIbdLinks','Year of Diagnosis','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Complications','myIbdLinks','Complications','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Other parts of the body affected','myIbdLinks','Other parts of the body affected','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Year for Surveillance Colonoscopy','myIbdLinks','Year for Surveillance Colonoscopy','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Named Consultant','myIbdLinks','Named Consultant','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Nurses','myIbdLinks','Nurses','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Weight','myIbdLinks','Weight','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('IBD Related Family History','myIbdLinks','IBD Related Family History','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Smoking History','myIbdLinks','Smoking History','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Surgery History','myIbdLinks','Surgery History','','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `medicalLink02`, `tenancy_id`) values('Vaccination History','myIbdLinks','Vaccination History','','','2');

insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Overall my condition','careplanLinks','Overall my condition','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Tiredness /Fatigue','careplanLinks','Tiredness /Fatigue','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Managing Pain','careplanLinks','Managing Pain','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Stress and worry','careplanLinks','Stress and worry','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Support from family and friends','careplanLinks','Support from family and friends','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Managing my social life / hobbies','careplanLinks','Managing my social life / hobbies','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Managing work / studies','careplanLinks','Managing work / studies','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Taking my medicines regularly','careplanLinks','Taking my medicines regularly','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Managing flare ups','careplanLinks','Managing flare ups','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Stopping smoking','careplanLinks','Stopping smoking','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Sleeping','careplanLinks','Sleeping','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Sexual relationships','careplanLinks','Sexual relationships','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Fertility / Pregnancy','careplanLinks','Fertility / Pregnancy','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Learning about my condition','careplanLinks','Learning about my condition','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Eating a healthy diet','careplanLinks','Eating a healthy diet','','2');
insert into `edtacode` (`edtaCode`, `linkType`, `description`, `medicalLink01`, `tenancy_id`) values('Travelling','careplanLinks','Travelling','','2');

/**
IBD Import changes
 */
ALTER TABLE `patient`
  CHANGE `centreCode` `centreCode` VARCHAR(20) DEFAULT '' NOT NULL,
  ADD COLUMN `address4` VARCHAR(255) NULL AFTER `otherConditions`,
  ADD COLUMN `bloodgroup` VARCHAR(255) NULL AFTER `address4`,
  ADD COLUMN `bmdexam` DATETIME NULL AFTER `bloodgroup`,
  ADD COLUMN `gpemail` VARCHAR(255) NULL AFTER `bmdexam`,
  ADD COLUMN `diagnosisDate` DATETIME NULL AFTER `gpemail`;

ALTER TABLE `ibd_myibd`
  CHANGE `nhsno` `nhsno` VARCHAR(10) NOT NULL,
  ADD COLUMN `eiManifestations` VARCHAR(255) NULL AFTER `weight`,
  ADD COLUMN `unitcode` VARCHAR(20) NULL AFTER `eiManifestations`;

CREATE TABLE `pv_allergy` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `confidenceLevel` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `infoSource` varchar(255) default NULL,
  `reaction` varchar(255) default NULL,
  `recordedDate` datetime default NULL,
  `status` varchar(255) default NULL,
  `substance` varchar(255) default NULL,
  `typeCode` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE `pv_procedure` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `proceduretext` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
);

