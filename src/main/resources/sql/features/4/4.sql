/**
      Patch: RPV - Group: HNF1 Beta - Bespoke Qs on Radar
 */

CREATE TABLE rdr_hnf1b_misc (
  id int(11) unsigned NOT NULL auto_increment,
  radar_no bigint(20) NOT NULL,
  renalCysts int(11) default '0',
  singleKidney int(11) default '0',
  otherRenalMalformations int(11) default '0',
  otherRenalMalformationsDetails varchar(1000) default NULL,
  diabetes int(11) default '0',
  ageAtDiabetesDiagnosis int(11) default '0',
  gout int(11) default '0',
  ageAtGoutDiagnosis int(11) default '0',
  genitalMalformation int(11) default '0',
  genitalMalformationDetails varchar(1000) default NULL,
  PRIMARY KEY  (id)
);