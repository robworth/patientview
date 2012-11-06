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
  `id` bigint(20) NOT NULL auto_increment,
  `body_part_affected_id` bigint(20) NOT NULL,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) NOT NULL,
  `namedConsultant` text,
  `nhsno` varchar(255) NOT NULL,
  `nurses` text,
  `yearForSurveillanceColonoscopy` datetime default NULL,
  `yearOfDiagnosis` datetime NOT NULL,
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