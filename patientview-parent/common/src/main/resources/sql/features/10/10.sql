-- feature join page

CREATE TABLE `pv_patientjoin_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL DEFAULT '',
  `lastname` varchar(100) NOT NULL DEFAULT '',
  `dateofbirth` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `nhsNo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);