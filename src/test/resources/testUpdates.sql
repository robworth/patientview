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

CREATE TABLE `rdc_genetic_test` (
  `id` bigint(20) NOT NULL auto_increment,
  `radar_no` bigint(20) NOT NULL,
  `testsDone` int(11) NOT NULL,
  `labWhereTestWasDone` text,
  `testDoneOn` text,
  `referenceNumber` varchar(255) default NULL,
  `whatResultsShowed` text,
  `keyEvidence` text,
  PRIMARY KEY  (`id`)
);
