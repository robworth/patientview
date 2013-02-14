/**
      Patch 1: Multi-tenant and IBD
 */


-- FEATURE-JPA

ALTER TABLE aboutme MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE centre ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE comment MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE diagnosis MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE edtacode DROP PRIMARY KEY;
ALTER TABLE edtacode ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE edtacode ADD UNIQUE (edtaCode);

ALTER TABLE emailverification MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE feedback MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE letter DROP PRIMARY KEY;
ALTER TABLE letter MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE log MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE medicine MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE news MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE patient DROP PRIMARY KEY;
ALTER TABLE patient ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE patient ADD UNIQUE INDEX (nhsno, centreCode);

ALTER TABLE result_heading DROP PRIMARY KEY;
ALTER TABLE result_heading ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE result_heading ADD UNIQUE (headingcode);

ALTER TABLE splashpage MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE splashpageuserseen MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE treatment DROP PRIMARY KEY;
ALTER TABLE treatment ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE treatment ADD UNIQUE INDEX (nhsNo, treatmentCode);

ALTER TABLE uktcode MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE uktstatus DROP PRIMARY KEY;
ALTER TABLE uktstatus ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE unit DROP PRIMARY KEY;
ALTER TABLE unit ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
ALTER TABLE unit ADD UNIQUE (unitcode);

ALTER TABLE unitstat MODIFY COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE USER DROP PRIMARY KEY;
ALTER TABLE USER ADD id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;
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

CREATE TABLE `ibd_medication_dose` (
  `id` bigint(20) NOT NULL auto_increment,
  `extraInformation` varchar(255) default NULL,
  `mg` double default NULL,
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
  `bodyPartAffected` text,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) NOT NULL,
  `namedConsultant` text,
  `nhsno` varchar(10) NOT NULL,
  `nurses` text,
  `yearForSurveillanceColonoscopy` datetime DEFAULT NULL,
  `yearOfDiagnosis` datetime NOT NULL,
  `familyHistory` text,
  `smoking` text,
  `surgery` text,
  `vaccinationRecord` text,
  `eiManifestations` varchar(255) DEFAULT NULL,
  `unitcode` varchar(20) DEFAULT NULL,
  `complications` TEXT NULL,
  PRIMARY KEY (`id`)
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


/**
IBD Import changes
 */
ALTER TABLE `patient` MODIFY `centreCode` VARCHAR(20) DEFAULT '' NOT NULL;
ALTER TABLE `patient` ADD COLUMN `address4` VARCHAR(255) NULL /*!AFTER `otherConditions`*/;
ALTER TABLE `patient` ADD COLUMN `bloodgroup` VARCHAR(255) NULL /*!AFTER `address4`*/;
ALTER TABLE `patient` ADD COLUMN `bmdexam` DATETIME NULL /*!AFTER `bloodgroup`*/;
ALTER TABLE `patient` ADD COLUMN `gpemail` VARCHAR(255) NULL /*!AFTER `bmdexam`*/;
ALTER TABLE `patient` ADD COLUMN `diagnosisDate` DATETIME NULL /*!AFTER `gpemail`*/;

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


