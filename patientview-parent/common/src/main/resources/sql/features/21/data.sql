/**
    Update the patient with tbl_demograpics data, todo this only makes sense if you match on nhsno and unit code, [paul: should be zero records that require updating on the live server - check this]
 */
UPDATE patient p INNER JOIN tbl_demographics d on p.nhsno = d.nhs_no
SET p.radarNO = d.RADAR_NO,
    p.rrNo = d.RR_NO,
    p.dateReg = d.DATE_REG,
    p.nhsNoType = d.NHS_NO_TYPE,
    p.uktNo = d.UKT_NO,
    p.surnameAlias = d.SNAME_ALIAS,
    p.AGE = d.AGE,
    p.ethnicGp = d.ETHNIC_GP,
    p.postcodeOld = d.POSTCODE_OLD,
    p.CONSENT = d.CONSENT,
    p.dateBapnReg = d.DATE_BAPN_REG,
    p.consNeph = d.CONS_NEPH,
    p.STATUS = d.STATUS,
    p.emailAddress = d.emailAddress,
    p.rrtModality = d.RRT_modality,
    p.genericDiagnosis = d.genericDiagnosis,
    p.dateOfGenericDiagnosis = d.dateOfGenericDiagnosis,
    p.otherClinicianAndContactInfo = d.otherClinicianAndContactInfo,
    p.comments = d.comments,
    p.republicOfIrelandId = d.republicOfIrelandId,
    p.isleOfManId = d.isleOfManId,
    p.channelIslandsId = d.channelIslandsId,
    p.indiaId = d.indiaId,
    p.generic = d.generic,
    p.unitcode = d.RDG;

/**
     Inserts data in tbl_demograpics to patient
     -- new records
 */
INSERT INTO patient (radarNO, rrNo, dateReg, nhsNoType, uktNo, surnameAlias, AGE, ethnicGp, postcodeOld, CONSENT, dateBapnReg, consNeph, STATUS, emailAddress, rrtModality, genericDiagnosis, dateOfGenericDiagnosis, otherClinicianAndContactInfo, comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, NHSNO, HOSPITALNUMBER, SURNAME, FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE, unitcode)
SELECT                d.RADAR_NO, d.RR_NO, d.DATE_REG, d.NHS_NO_TYPE, d.UKT_NO, d.SNAME_ALIAS, d.AGE, d.ETHNIC_GP, d.POSTCODE_OLD, d.CONSENT, d.DATE_BAPN_REG, d.CONS_NEPH, d.STATUS, d.emailAddress, d.RRT_modality, d.genericDiagnosis, d.dateOfGenericDiagnosis, d.otherClinicianAndContactInfo, d.comments, d.republicOfIrelandId, d.isleOfManId, d.channelIslandsId, d.indiaId, d.generic, d.NHS_NO, d.HOSP_NO, d.SNAME, d.FNAME, d.DOB, d.ADD1, d.ADD2, d.ADD3, d.ADD4, d.POSTCODE, d.PHONE1, d.PHONE2, d.MOBILE, d.RDG
FROM tbl_demographics d
/*WHERE d.nhs_no in (SELECT DISTINCT d.nhs_no FROM tbl_demographics d, patient p WHERE p.nhsno != d.nhs_no);*/

WHERE d.nhs_no not in (SELECT DISTINCT d.nhs_no FROM tbl_demographics d, patient p WHERE p.nhsno = d.nhs_no AND BLAH.unitcode = BLAH.unitcode); -- todo complete reworking the where clause to use 'not in' and match on unitcode as well as nhsno


/**
    Update the sex todo can drop the tbl_sex
 */

UPDATE patient p
SET p.sex =
  (
    SELECT s.sType
    FROM tbl_sex s, tbl_demographics d
    WHERE d.nhs_no = p.nhsno -- todo again join on unitcode
    AND s.sID = d.sex
  );

/**
    Create a user mapping for the disease group
    Insert into usermapping with tbl_demographics nhs_no and RDG fields
 */
INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT CONCAT(d.fname, d.sname) AS username,   -- todo join to the user table via the existing user mapping to get the username , exclude GPs
  d.RDG AS unitcode,
  d.nhs_no AS nhsno,
  1 AS specialty_id
FROM tbl_demographics d, unit u, patient p
WHERE d.RDG = u.unitcode
  AND P.unitcode = u.unitcode
  AND p.nhsno = d.nhs_no
  AND u.sourceType = 'radargroup';

/**
    Create a user mapping for the source data entering user's unit
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit
 */
INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT CONCAT(d.fname, d.sname) AS username, -- todo join to the user table via the existing user mapping to get the username, exclude GPs
  ( SELECT u.unitcode
      FROM unit u
     WHERE u.id = d.renal_unit ) AS unitcode,
  d.nhs_no AS nhsno,
  1 AS specialty_id
FROM tbl_demographics d
WHERE NOT EXISTS (
-- todo maybe test for GP users, test this works
                      SELECT d.*
                        FROM tbl_demographics d LEFT OUTER JOIN unit u ON d.renal_unit = u.id, usermapping ump
                        WHERE d.nhs_no = ump.nhsno
                          AND u.unitcode = ump.unitcode
                    );

--  todo add a user mapping for the renal_unit_2

-- todo clean up unused stuff