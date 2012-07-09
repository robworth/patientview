CREATE TABLE `testresult` (
  `nhsno` varchar(100) NOT NULL default '',
  `unitcode` varchar(100) NOT NULL default '',
  `testcode` varchar(100) NOT NULL default '',
  `datestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `prepost` varchar(100) default '',
  `value` varchar(100) NOT NULL default ''
);


CREATE TABLE unit (
unitcode varchar(100) NOT NULL DEFAULT '',
name varchar(50),
);
