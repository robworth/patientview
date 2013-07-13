/**
      Merge tbl_demographics into patient table
 */
ALTER TABLE patient ADD COLUMN  `radarNo` int(11) NULL;
ALTER TABLE patient ADD COLUMN  `rrNo` varchar(10) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `dateReg` datetime DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `nhsNoType` int(11) NOT NULL;
ALTER TABLE patient ADD COLUMN   `uktNo` bigint(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `surnameAlias` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `AGE` int(11) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `ethnicGp` varchar(6) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `postcodeOld` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `consent` bit(1) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `dateBapnReg` datetime DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `consNeph` varchar(6) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `status` int(11) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `emailAddress` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `rrtModality` int(10) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `otherClinicianAndContactInfo` varchar(500) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `comments` varchar(500) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `republicOfIrelandId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `isleOfManId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `channelIslandsId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `indiaId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `generic` tinyint(1) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `genericDiagnosis` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `dateOfGenericDiagnosis` datetime DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `unitcode` varchar(20) NOT NULL DEFAULT '';

UPDATE patient p
SET p.unitcode = p.centreCode;

ALTER TABLE patient DROP INDEX nhsno;
ALTER TABLE patient DROP COLUMN `centreCode`;

ALTER TABLE patient ADD UNIQUE `nhsno` (`nhsno`,`unitcode`);
ALTER TABLE patient ADD CONSTRAINT fk_unitcode Foreign Key (unitcode) References unit (unitcode);
ALTER TABLE patient add CONSTRAINT fk_genericDiagnosis Foreign Key (genericDiagnosis) References rdr_prd_code (ERA_EDTA_PRD_code);
