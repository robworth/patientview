/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_mydiabetes`;
CREATE TABLE `dia_mydiabetes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diabetesType` varchar(255) DEFAULT NULL,,
  `yearDiagnosed` int(4) DEFAULT NULL,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) DEFAULT NULL
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient` (`nhsno`,`unitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;