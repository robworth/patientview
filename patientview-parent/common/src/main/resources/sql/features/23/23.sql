/**
    Add two columns in pv_patientjoin_request table.
 */
ALTER TABLE pv_patientjoin_request ADD COLUMN   `isComplete` tinyint(1) DEFAULT '0';
ALTER TABLE pv_patientjoin_request ADD COLUMN   `notes` varchar(255) DEFAULT NULL;
