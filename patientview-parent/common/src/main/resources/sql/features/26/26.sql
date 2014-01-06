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

 ALTER TABLE patient ADD UNIQUE KEY `nhsno-unitcode-sourceType` (`nhsno`,`unitcode`, `sourceType`);

 CREATE TABLE rdr_radar_number (id BIGINT NOT NULL AUTO_INCREMENT, creationDate TIMESTAMP, PRIMARY KEY (id) );

 INSERT INTO rdr_radar_number (id)
 SELECT radarNo
 FROM   patient
 WHERE  radarNo IS NOT NULL;

 ALTER TABLE patient
 ADD CONSTRAINT fk_patient_radar_number
 FOREIGN KEY (radarNo) REFERENCES rdr_radar_number(id)
 ON UPDATE CASCADE ON DELETE CASCADE;

 ALTER TABLE patient
 ADD CONSTRAINT fk_patient_link_id
 FOREIGN KEY (patientLinkId) REFERENCES patient(id)
 ON UPDATE CASCADE ON DELETE CASCADE;