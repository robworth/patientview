/**
    Add a date column in unit table.
 */
 ALTER TABLE unit ADD COLUMN `lastImportDate` datetime DEFAULT NULL;

 ALTER TABLE pv_patientjoin_request CHANGE email email VARCHAR(255) NULL;

 ALTER TABLE pv_patientjoin_request MODIFY COLUMN dateofbirth DATETIME NOT NULL;  -- check dateofbirth has valid dates

 -- fix up bad data for existing join requests
 UPDATE pv_patientjoin_request SET dateofbirth = '1900-01-01 00:00:00' WHERE dateofbirth = '0000-00-00 00:00:00';

 /**
    Required for the generic genetic panel - should already by on live site
  */
 ALTER TABLE rdc_genetic_test ADD COLUMN dateSent DATETIME NULL;

 ALTER TABLE patient ADD COLUMN (patientLinkId BIGINT(11));