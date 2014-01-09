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



UPDATE   tbl_diagnosis dig
SET      radar_no = (SELECT radarno FROM patient ptt WHERE  ptt.id = dig.radar_no)
WHERE    dig.radar_no > 900;

UPDATE  rdc_genetic_test  dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE  rdr_hnf1b_misc dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE  tbl_clinicalData dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE  tbl_diagnosis dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE    tbl_pathology dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE tbl_relapse  dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;

UPDATE tbl_hospitalisation dig
SET radar_no = (SELECT radarno FROM patient ptt WHERE ptt.id = dig.radar_no)
WHERE dig.radar_no > 900;




