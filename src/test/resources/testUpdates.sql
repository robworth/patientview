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
  `created` datetime NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `title` varchar(50) default NULL,
  `forename` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `telephone` varchar(100) default NULL,
  `screenName` varchar(100) default NULL,
  `emailVerified` tinyint(1) default '0',
  `firstLogin` datetime default NULL,
  `lastLogin` datetime default NULL,
  `failedLogons` int(10) default NULL,
  `accountLocked` tinyint(1) default '0',
  PRIMARY KEY  (`id`)
);

CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL
);

CREATE TABLE `rdr_professional_user` (
  `userId` bigint(20) NOT NULL,
  `gmc` varchar(50) default NULL,
  `centreId` bigint(20) default NULL,
  `centreRole` varchar(20) default NULL
);
