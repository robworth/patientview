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