# Removing the bad patient mappings

DELETE   usm
FROM     specialtyuserrole spu
,        USER usr
,        usermapping usm
WHERE    spu.role = 'unitadmin'
AND      spu.id = usr.id
AND      usr.username = usm.username
AND      unitcode = 'PATIENT'
AND      nhsno IS NULL;

# Lets remove the bad patientLinkIds

UPDATE patient SET patientLinkId = NULL WHERE patientlinkId = 0;

# Change the table so it used constraints
ALTER TABLE patient ENGINE=InnoDB;



