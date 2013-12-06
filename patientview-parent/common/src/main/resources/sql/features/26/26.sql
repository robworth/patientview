/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_careplan`;
CREATE TABLE `dia_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `barriers` text,
  `confidence_id` bigint(20) NOT NULL,
  `heartAttacksStrokesScore` int(11) default NULL,
  `kidneysScore` int(11) default NULL,
  `goalToAchieve` text,
  `goals` text,
  `howToAchieveGoal` text,
  `importance_id` bigint(20) NOT NULL,
  `feetScore` int(11) default NULL,
  `eatingExerciseSportScore` int(11) default NULL,
  `bloodPressureScore` int(11) default NULL,
  `diabetesControlScore` int(11) default NULL,
  `cholesterolScore` int(11) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherAreasToDiscuss` text,
  `diabetesOverallScore` int(11) default NULL,
  `reviewDate` datetime default NULL,
  `eyesScore` int(11) default NULL,
  `pregnancySexRelationshipsScore` int(11) default NULL,
  `drivingWorkStudyScore` int(11) default NULL,
  `hypoglycaemiaScore` int(11) default NULL,
  `weightScore` int(11) default NULL,
  `smokingAlcoholScore` int(11) default NULL,
  `moodScore` int(11) default NULL,
  `takingMedicationRegularlyScore` int(11) default NULL,
  `whatCanBeDone` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
    Add a date column in unit table.
 */
 ALTER TABLE unit ADD COLUMN `lastImportDate` datetime DEFAULT NULL;

 ALTER TABLE pv_patientjoin_request CHANGE email email VARCHAR(255) NULL;

 ALTER TABLE pv_patientjoin_request MODIFY COLUMN dateofbirth DATETIME NOT NULL;  -- check dateofbirth has valid dates

 -- fix up bad data for existing join requests
 UPDATE pv_patientjoin_request SET dateofbirth = '1900-01-01 00:00:00' WHERE dateofbirth = '0000-00-00 00:00:00';

 /**
    Required for the generic genetic panel - should already by on live site
  */
 ALTER TABLE rdc_genetic_test ADD COLUMN dateSent DATETIME NULL;

 ALTER TABLE patient ADD COLUMN (patientLinkId BIGINT(11));
