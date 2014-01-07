# Lets remove the back patientLinkId

UPDATE patient SET patientLinkId = NULL WHERE patientlinkId = 0;

# Change the table so it used constraints
ALTER TABLE patient ENGINE=InnoDB;

ALTER TABLE PATIENT ADD (lastdatadate DATE);

UPDATE patient p
SET lastdatadate = (SELECT MAX(lastdatadate) FROM pv_user_log l WHERE p.nhsno = l.nhsno AND p.unitcode = l.unitcode);


DROP TABLE pv_user_log;

