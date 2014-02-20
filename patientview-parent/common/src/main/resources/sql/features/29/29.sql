
/**
    Create dia_footcheckup table.
 */
DROP TABLE IF EXISTS `dia_footcheckup`;
CREATE TABLE `dia_footcheckup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) NOT NULL DEFAULT '',
  `unitcode` varchar(255) NOT NULL DEFAULT '',
  `footCheckDate` datetime DEFAULT NULL,
  `footCheckPlace` varchar(255) DEFAULT NULL,
  `leftDpPulse` varchar(255) DEFAULT NULL,
  `leftPtPulse` varchar(255) DEFAULT NULL,
  `leftMonofilament` varchar(255) DEFAULT NULL,
  `leftRiskScore` varchar(255) DEFAULT NULL,
  `rightDpPulse` varchar(255) DEFAULT NULL,
  `rightPtPulse` varchar(255) DEFAULT NULL,
  `rightMonofilament` varchar(255) DEFAULT NULL,
  `rightRiskScore` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

ALTER TABLE dia_footcheckup ADD CONSTRAINT u_foot_date_nhsno_unitcode UNIQUE (footCheckDate,nhsno,unitcode);


/**
    Create dia_eyecheckup table.
 */
DROP TABLE IF EXISTS `dia_eyecheckup`;
CREATE TABLE `dia_eyecheckup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) NOT NULL DEFAULT '',
  `unitcode` varchar(255) NOT NULL DEFAULT '',
  `lastRetinalDate` datetime DEFAULT NULL,
  `lastRetinalPlace` varchar(255) DEFAULT NULL,
  `leftMGrade` varchar(255) DEFAULT NULL,
  `leftRGrade` varchar(255) DEFAULT NULL,
  `leftVA` varchar(255) DEFAULT NULL,
  `rightMGrade` varchar(255) DEFAULT NULL,
  `rightRGrade` varchar(255) DEFAULT NULL,
  `rightVA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

ALTER TABLE dia_eyecheckup ADD CONSTRAINT u_eye_date_nhsno_unitcode UNIQUE (lastRetinalDate,nhsno,unitcode);

/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_mydiabetes`;
CREATE TABLE `dia_mydiabetes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) DEFAULT NULL,
  `diabetesType` varchar(255) DEFAULT NULL,
  `yearDiagnosed` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient` (`nhsno`,`unitcode`)
) ENGINE=InnoDB;

/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_careplan`;
CREATE TABLE `dia_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(255) NOT NULL,
  `diabetesOverallScore` int(11) default NULL,
  `moodScore` int(11) default NULL,
  `diabetesControlScore` int(11) default NULL,
  `hypoglycaemiaScore` int(11) default NULL,
  `weightScore` int(11) default NULL,
  `bloodPressureScore` int(11) default NULL,
  `cholesterolScore` int(11) default NULL,
  `smokingAlcoholScore` int(11) default NULL,
  `eatingExerciseSportScore` int(11) default NULL,
  `drivingWorkStudyScore` int(11) default NULL,
  `pregnancySexRelationshipsScore` int(11) default NULL,
  `eyesScore` int(11) default NULL,
  `kidneysScore` int(11) default NULL,
  `feetScore` int(11) default NULL,
  `heartAttacksStrokesScore` int(11) default NULL,
  `takingMedicationRegularlyScore` int(11) default NULL,
  `otherAreasToDiscuss` text,
  `goals` text,
  `goalToAchieve` text,
  `importance_id` bigint(20) NOT NULL,
  `howToAchieveGoal` text,
  `barriers` text,
  `whatCanBeDone` text,
  `confidence_id` bigint(20) NOT NULL,
  `reviewDate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
    Extend the unique key on results_heading table to be both headingcode and specialty_id
 */
ALTER TABLE result_heading DROP KEY headingcode;

ALTER TABLE result_heading ADD UNIQUE KEY headingcode_specialtyid  (headingcode, specialty_id);

# Diabetes data setup
INSERT INTO specialty(context, description, NAME) VALUES ('diabetes', 'Diabetes', 'Diabetes');


INSERT INTO USER (username, PASSWORD, firstname, lastname) VALUES ('diabetes-sa', '','Super Admin','Diabetes');


INSERT INTO specialtyuserrole (role, specialty_id, user_id) VALUES ('superadmin', '3', '73579');

DROP INDEX edtacode ON edtacode;

CREATE INDEX edtaCode_idx ON edtacode(edtacode, specialty_id);

INSERT INTO edtacode(edtacode, linkTYpe, description, specialty_id) VALUES( 'static','static', 'Further Information', '3');

