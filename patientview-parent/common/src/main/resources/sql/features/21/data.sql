/**
     Inserts data in tbl_demograpics to patient
     -- new records
 */
INSERT INTO patient (radarNO, rrNo, dateReg, nhsNoType, uktNo, surnameAlias, AGE, ethnicGp, postcodeOld, CONSENT, dateBapnReg, consNeph, STATUS, emailAddress, rrtModality, genericDiagnosis, dateOfGenericDiagnosis, otherClinicianAndContactInfo, comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, NHSNO, HOSPITALNUMBER, SURNAME, FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE, unitcode)
SELECT                d.RADAR_NO, d.RR_NO, d.DATE_REG, d.NHS_NO_TYPE, d.UKT_NO, d.SNAME_ALIAS, d.AGE, d.ETHNIC_GP, d.POSTCODE_OLD, d.CONSENT, d.DATE_BAPN_REG, d.CONS_NEPH, d.STATUS, d.emailAddress, d.RRT_modality, d.genericDiagnosis, d.dateOfGenericDiagnosis, d.otherClinicianAndContactInfo, d.comments, d.republicOfIrelandId, d.isleOfManId, d.channelIslandsId, d.indiaId, d.generic, d.NHS_NO, d.HOSP_NO, d.SNAME, d.FNAME, d.DOB, d.ADD1, d.ADD2, d.ADD3, d.ADD4, d.POSTCODE, d.PHONE1, d.PHONE2, d.MOBILE, d.RDG
FROM tbl_demographics d
WHERE d.nhs_no NOT IN (SELECT DISTINCT d.nhs_no FROM tbl_demographics d, patient p WHERE p.nhsno = d.nhs_no AND d.RDG = p.unitcode);


/**
    Update the sex todo can drop the tbl_sex[Richard: I think it's better to drop tbl_sex table after the patient table merge is done(some panels related with it)]
 */

UPDATE patient p INNER JOIN
    (
      SELECT s.sType,d.*
        FROM tbl_sex s, tbl_demographics d
        WHERE s.sID = d.sex
    ) dData ON p.nhsno = dData.nhs_no AND p.unitcode = dData.RDG
SET p.sex = dData.sType

/**
    Create a user mapping for the disease group
    Insert into usermapping with tbl_demographics nhs_no and RDG fields
 */
INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT user.username AS username,
  d.RDG AS unitcode,
  d.nhs_no AS nhsno,
  1 AS specialty_id
FROM tbl_demographics d, tbl_patient_users, rdr_user_mapping, user
WHERE d.RADAR_NO = tbl_patient_users.RADAR_NO
  AND tbl_patient_users.pID = rdr_user_mapping.radarUserId
  AND rdr_user_mapping.userId = user.id
  AND NOT EXISTS (
                      SELECT d.*
                        FROM tbl_demographics d LEFT OUTER JOIN unit u ON d.RDG = u.id, usermapping ump
                        WHERE d.nhs_no = ump.nhsno
                          AND u.unitcode = ump.unitcode
                          AND ump.username NOT LIKE '%-GP%'
                    );

/**
    Create a user mapping for the source data entering user's unit
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit
 */
INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT user.username AS username,
  ( SELECT u.unitcode
      FROM unit u
     WHERE u.id = d.renal_unit ) AS unitcode,
  d.nhs_no AS nhsno,
  1 AS specialty_id
FROM tbl_demographics d, tbl_patient_users, rdr_user_mapping, user
WHERE d.RADAR_NO = tbl_patient_users.RADAR_NO
  AND tbl_patient_users.pID = rdr_user_mapping.radarUserId
  AND rdr_user_mapping.userId = user.id
  AND NOT EXISTS (
                      SELECT d.*
                        FROM tbl_demographics d LEFT OUTER JOIN unit u ON d.renal_unit = u.id, usermapping ump
                        WHERE d.nhs_no = ump.nhsno
                          AND u.unitcode = ump.unitcode
                          AND ump.username NOT LIKE '%-GP%'
                    );


/**
    Create a user mapping for the source data entering user's unit(Allow visibility for another Renal Unit)
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit_2
 */
INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT user.username AS username,
  ( SELECT u.unitcode
      FROM unit u
     WHERE u.id = d.renal_unit_2 ) AS unitcode,
  d.nhs_no AS nhsno,
  1 AS specialty_id
FROM tbl_demographics d, tbl_patient_users, rdr_user_mapping, user
WHERE d.RADAR_NO = tbl_patient_users.RADAR_NO
  AND tbl_patient_users.pID = rdr_user_mapping.radarUserId
  AND rdr_user_mapping.userId = user.id
  AND d.renal_unit_2 IS NOT NULL
  AND NOT EXISTS (
                      SELECT d.*
                        FROM tbl_demographics d LEFT OUTER JOIN unit u ON d.renal_unit_2 = u.id, usermapping ump
                        WHERE d.nhs_no = ump.nhsno
                          AND u.unitcode = ump.unitcode
                          AND ump.username NOT LIKE '%-GP%'
                    );

