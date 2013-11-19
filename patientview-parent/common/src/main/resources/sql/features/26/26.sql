/**
    Add a date column in unit table.
 */
 ALTER TABLE unit ADD COLUMN `lastImportDate` datetime DEFAULT NULL;

 /**
    Create the link table for patient->patient
 */
 CREATE TABLE rdr_patient_linkage (id BIGINT PRIMARY KEY AUTO_INCREMENT, source_nhsno VARCHAR(20), source_unitcode VARCHAR(20), dest_nhsno VARCHAR(20), dest_unitcode VARCHAR(20));