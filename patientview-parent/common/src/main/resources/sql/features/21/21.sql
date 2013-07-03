/**
      Merge tbl_demographics into patient table
 */

ALTER TABLE patient ADD COLUMN  `RADAR_NO` int(11) NULL;
ALTER TABLE patient ADD COLUMN  `RR_NO` varchar(10) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `DATE_REG` datetime DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `NHS_NO_TYPE` int(11) NOT NULL;
ALTER TABLE patient ADD COLUMN   `UKT_NO` bigint(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `SNAME_ALIAS` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `AGE` int(11) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `ETHNIC_GP` varchar(6) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `POSTCODE_OLD` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `CONSENT` bit(1) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `DATE_BAPN_REG` datetime DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `CONS_NEPH` varchar(6) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `STATUS` int(11) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `RDG` varchar(100) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `emailAddress` varchar(50) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `RRT_modality` int(10) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `otherClinicianAndContactInfo` varchar(500) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `comments` varchar(500) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `republicOfIrelandId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `isleOfManId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `channelIslandsId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `indiaId` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `generic` tinyint(1) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `RENAL_UNIT_2` int(11) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `genericDiagnosis` varchar(20) DEFAULT NULL;
ALTER TABLE patient ADD COLUMN   `dateOfGenericDiagnosis` datetime DEFAULT NULL;


ALTER TABLE patient add constraint fk_RDG Foreign Key (RDG) References unit (unitcode);
ALTER TABLE patient add constraint fk_genericDiagnosis Foreign Key (genericDiagnosis) References rdr_prd_code (ERA_EDTA_PRD_code);







