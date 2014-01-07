# Lets remove the back patientLinkId

UPDATE patient SET patientLinkId = NULL WHERE patientlinkId = 0;

# Change the table so it used constraints
ALTER TABLE patient ENGINE=InnoDB;


