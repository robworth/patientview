CREATE TABLE rdr_prd_code (
ERA_EDTA_PRD_code VARCHAR(20) NOT NULL,
ERA_EDTA_primaryRenalDiagnosisTerm VARCHAR(200),
histology BOOLEAN,
clinicalHistory BOOLEAN,
familyHistory BOOLEAN,
clinicalExam BOOLEAN,
biochemistry BOOLEAN,
immunology BOOLEAN,
urineAnalysis BOOLEAN,
imaging BOOLEAN,
geneTest BOOLEAN,
otherCriteriaAndNotes VARCHAR(1000),
SNOMED_CT_conceptIdentifierForFocusConcept VARCHAR(50),
SNOMED_CT_fullySpecifiedName VARCHAR(200),
SNOMED_CT_expressionConstraint VARCHAR(200),
majorHeading VARCHAR(200),
mappingToOldPRDCode INT(10),
mappingToOldPRDTerm VARCHAR(200),
ERA_EDTA_defaultSortOrder INT(10),
geneticsHomeReferenceLink VARCHAR(200),
nationalCenterForBiotechnologyLink VARCHAR(200),
ICD_10_code VARCHAR(200), 
ICD10_rubricTerm VARCHAR(200),
alternativesearchTerms VARCHAR(200),
PRIMARY KEY  (ERA_EDTA_PRD_code)
) ENGINE=InnoDB;

CREATE TABLE rdr_diagnosis_mapping (
workingGroup VARCHAR(100) NOT NULL,
PRDCode VARCHAR(20) NOT NULL,
ordering INT(10),
PRIMARY KEY (workingGroup, PRDCode),
--FOREIGN KEY (workingGroup) REFERENCES unit(unitcode) ON DELETE CASCADE, this gives error for some reason
FOREIGN KEY (PRDCode) REFERENCES rdr_prd_code(ERA_EDTA_PRD_code) ON DELETE CASCADE
) ENGINE=InnoDB;

ALTER TABLE tbl_demographics ADD RDG VARCHAR(100);
ALTER TABLE tbl_demographics ADD emailAddress VARCHAR(50);
ALTER TABLE tbl_demographics ADD phone1 VARCHAR(20);
ALTER TABLE tbl_demographics ADD phone2 VARCHAR(20);
ALTER TABLE tbl_demographics ADD mobile VARCHAR(20);
ALTER TABLE tbl_demographics ADD RRT_modality INT(10);
ALTER TABLE tbl_demographics ADD genericDiagnosis VARCHAR(20);
ALTER TABLE tbl_demographics ADD dateOfGenericDiagnosis DATETIME;
ALTER TABLE tbl_demographics ADD otherClinicianAndContactInfo VARCHAR(500);
ALTER TABLE tbl_demographics ADD comments VARCHAR(500);
ALTER TABLE tbl_demographics ADD republicOfIrelandId VARCHAR(20); 
ALTER TABLE tbl_demographics ADD isleOfManId VARCHAR(20); 
ALTER TABLE tbl_demographics ADD channelIslandsId VARCHAR(20); 
ALTER TABLE tbl_demographics ADD indiaId VARCHAR(20); 
ALTER TABLE tbl_demographics ADD generic BOOLEAN;
ALTER TABLE tbl_demographics ADD CONSTRAINT fk_RDG FOREIGN KEY (RDG) REFERENCES unit (unitcode);
ALTER TABLE tbl_demographics ADD CONSTRAINT fk_genericDiagnosis FOREIGN KEY (genericDiagnosis) REFERENCES rdr_prd_code (ERA_EDTA_PRD_code);

ALTER TABLE unit ADD sourceType VARCHAR(50);
ALTER TABLE unit ADD shortName VARCHAR(50);

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

CREATE TABLE `rdr_alport_deafness` (
  `id` bigint(20) NOT NULL auto_increment,
  `radar_no` bigint(20) NOT NULL,
  `evidenceOfDeafness` int(11) NOT NULL,
  `ageProblemFirstNoticed` int(11) default NULL,
  `ageStartedUsingHearingAid` int(11) default NULL,
  PRIMARY KEY  (`id`)
);

/**
The create table is not actually needed as the table will already exist when radar and rpv dbs are merged
so only need the update statement below
 */
CREATE TABLE `medicine` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(20) default NULL,
  `chino` varchar(20) default NULL,
  `unitcode` varchar(20) NOT NULL default '',
  `startdate` datetime default '0000-00-00 00:00:00',
  `enddate` datetime default '0000-00-00 00:00:00',
  `name` varchar(100) default '',
  `dose` varchar(100) default '',
  PRIMARY KEY  (`id`)
);

-- ALTER TABLE medicine ALTER COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT;
-- ALTER TABLE medicine ADD enddate DATETIME DEFAULT '0000-00-00 00:00:00' NULL;
-- ALTER TABLE medicine ALTER COLUMN nhsno VARCHAR(20) DEFAULT NULL;
-- ALTER TABLE medicine ADD COLUMN chino VARCHAR(20) DEFAULT NULL AFTER nhsno;









