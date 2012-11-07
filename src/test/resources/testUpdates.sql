CREATE TABLE `testresult` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `unitcode` varchar(100) default '',
  `testcode` varchar(100) default '',
  `datestamp` datetime default '0000-00-00 00:00:00',
  `prepost` varchar(100) default '',
  `value` varchar(100) default '',
  `RADAR_NO` int(11) unsigned NOT NULL,
  `nhsNo` varbinary(50) NOT NULL default '',
  PRIMARY KEY  (`id`)
);


CREATE TABLE `unit` (
  `unitcode` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL default '',
  `shortname` varchar(15) NOT NULL default '',
  `sourceType` varchar(50) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `country` varchar(100) default NULL
);
