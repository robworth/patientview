/*
This is only need if this table doesnt exist already
*/
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL default '',
  `password` varchar(100) NOT NULL default '',
  `name` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `emailverified` tinyint(1) default '0',
  `firstlogon` tinyint(1) default '0',
  `dummypatient` tinyint(1) NOT NULL default '0',
  `lastlogon` datetime default NULL,
  `failedlogons` int(10) default '0',
  `accountlocked` tinyint(1) default '0',
  `screenname` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=INNODB;

CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `radarUserId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`),
  constraint `FK_RDR_USER_MAPPING_USERID` foreign key (`userId`) references `user`(`id`) on delete Cascade
) ENGINE=InnoDB;

/*
ONLY DO THIS AFTER YOU HAVE RUN THE EXPORT FILE TO MAP THESE FIELDS INTO RPV
*/
ALTER TABLE `tbl_adminusers`
  DROP COLUMN `uEmail`,
  DROP COLUMN `uPass`,
  DROP COLUMN `uUserName`;

ALTER TABLE `tbl_patient_users`
  DROP COLUMN `pUserName`,
  DROP COLUMN `pPassWord`;

ALTER TABLE `tbl_users`
  DROP COLUMN `uEmail`,
  DROP COLUMN `uPass`,
  DROP COLUMN `uUserName`;


