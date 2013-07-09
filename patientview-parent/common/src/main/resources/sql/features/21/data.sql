/**
     Inserts data in tbl_demograpics to patient
 */
INSERT INTO patient (RADAR_NO, RR_NO, DATE_REG, NHS_NO_TYPE, UKT_NO, SNAME_ALIAS, AGE, ETHNIC_GP, POSTCODE_OLD, CONSENT, DATE_BAPN_REG, CONS_NEPH, STATUS, emailAddress, RRT_modality, otherClinicianAndContactInfo, comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, NHSNO, HOSPITALNUMBER, SURNAME, FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE)
SELECT                d.RADAR_NO, d.RR_NO, d.DATE_REG, d.NHS_NO_TYPE, d.UKT_NO, d.SNAME_ALIAS, d.AGE, d.ETHNIC_GP, d.POSTCODE_OLD, d.CONSENT, d.DATE_BAPN_REG, d.CONS_NEPH, d.STATUS, d.emailAddress, d.RRT_modality, d.otherClinicianAndContactInfo, d.comments, d.republicOfIrelandId, d.isleOfManId, d.channelIslandsId, d.indiaId, d.generic, d.NHS_NO, d.HOSP_NO, d.SNAME, d.FNAME, d.DOB, d.ADD1, d.ADD2, d.ADD3, d.ADD4, d.POSTCODE, d.PHONE1, d.PHONE2, d.MOBILE
FROM tbl_demographics d
WHERE d.nhs_no in (select distinct d.nhs_no from tbl_demographics d, patient p where p.nhsno != d.nhs_no);

/**
    Update the sex
 */

UPDATE patient p
SET p.sex =
  (
    SELECT s.sType
    FROM tbl_sex s, tbl_demographics d
    WHERE d.nhs_no = p.nhsno
    AND s.sID = d.sex
  );

/**
    Update the centreCode
 */

UPDATE patient p
SET p.unitcode =
  (
    SELECT u.unitcode
    FROM unit u, tbl_demographics d
    WHERE d.nhs_no = p.nhsno
    AND u.id = d.renal_unit
  )
WHERE unitcode = "" OR unitcode IS NULL;


/**
    Insert data into usermapping with non null value in RENAL_UNIT_2 field of tbl_demograpics
 */

INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT CONCAT(d.fname, d.sname) AS username,
  ( SELECT u.unitcode
      FROM unit u
     WHERE u.id = d.renal_unit_2 ) AS unitcode,
  d.nhs_no AS nhsno,
  ( SELECT DISTINCT um.specialty_id
      FROM usermapping um
     WHERE um.nhsno = d.nhs_no
       AND um.username NOT LIKE '%-GP%') AS specialty_id
FROM tbl_demographics d
WHERE d.renal_unit_2 IS NOT NULL;


