/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_checkups`;
CREATE TABLE `dia_checkups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `footCheckDate` datetime DEFAULT NULL,
  `footCheckPlace` varchar(255) DEFAULT NULL,
  `lastRetinalDate` datetime DEFAULT NULL,
  `lastRetinalPlace` varchar(255) DEFAULT NULL,
  `leftDpPulse` varchar(255) DEFAULT NULL,
  `leftMGrade` varchar(255) DEFAULT NULL,
  `leftMonofilament` varchar(255) DEFAULT NULL,
  `leftPtPulse` varchar(255) DEFAULT NULL,
  `leftRGrade` varchar(255) DEFAULT NULL,
  `leftRiskScore` varchar(255) DEFAULT NULL,
  `leftVA` varchar(255) DEFAULT NULL,
  `nhsno` varchar(255) NOT NULL,
  `rightDpPulse` varchar(255) DEFAULT NULL,
  `rightMGrade` varchar(255) DEFAULT NULL,
  `rightMonofilament` varchar(255) DEFAULT NULL,
  `rightPtPulse` varchar(255) DEFAULT NULL,
  `rightRGrade` varchar(255) DEFAULT NULL,
  `rightRiskScore` varchar(255) DEFAULT NULL,
  `rightVA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nhsno` (`nhsno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
    Add a column in emailverification table.
 */
ALTER TABLE emailverification ADD COLUMN   `lastverificationdate` datetime DEFAULT NULL;

