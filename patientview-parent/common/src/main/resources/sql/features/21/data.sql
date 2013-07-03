/**
     Inserts data in tbl_demograpics to patient
 */
INSERT INTO patient (RADAR_NO, RR_NO, DATE_REG, NHS_NO_TYPE, UKT_NO, SNAME_ALIAS, AGE, ETHNIC_GP, POSTCODE_OLD, CONSENT, DATE_BAPN_REG, CONS_NEPH, STATUS, RDG, emailAddress, RRT_modality, otherClinicianAndContactInfo, comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, RENAL_UNIT_2, NHSNO, HOSPITALNUMBER, SURNAME, FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE)
SELECT                RADAR_NO, RR_NO, DATE_REG, NHS_NO_TYPE, UKT_NO, SNAME_ALIAS, AGE, ETHNIC_GP, POSTCODE_OLD, CONSENT, DATE_BAPN_REG, CONS_NEPH, STATUS, RDG, emailAddress, RRT_modality, otherClinicianAndContactInfo, comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, RENAL_UNIT_2, NHS_NO, HOSP_NO, SNAME, FNAME, DOB, ADD1, ADD2, ADD3, ADD4, POSTCODE, PHONE1, PHONE2, MOBILE
FROM tbl_demographics;


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
  )

/**
    Update the centreCode
 */

UPDATE patient p
SET p.centreCode = u.unitcode
FROM tbl_demographics d, unit u
WHERE d.nhsno = p.nhsno
  AND d.renal_unit = u.id;



