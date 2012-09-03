CREATE TABLE `testresult` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `unitcode` varchar(100) default '',
  `testcode` varchar(100) default '',
  `datestamp` datetime default '0000-00-00 00:00:00',
  `prepost` varchar(100) default '',
  `value` varchar(100) default '',
  `RADAR_NO` int(11) unsigned NOT NULL,
  PRIMARY KEY  (`id`)
);


CREATE TABLE unit (
unitcode varchar(100) NOT NULL DEFAULT '',
name varchar(50),
);

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
) ENGINE=InnoDB;

CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `radarUserId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`),
  constraint `FK_RDR_USER_MAPPING_USERID` foreign key (`userId`) references `user`(`id`) on delete Cascade
) ENGINE=InnoDB;
