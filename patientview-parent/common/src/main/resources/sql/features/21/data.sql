/**
     Inserts data from tbl_demograpics to patient
     -- there are 64 patients that exist in PV and Radar which the same nhsno and unit, we will not merge these
     -- otherwise we risk removing radar uploaded data with the PV importer

     544 row(s) affected
 */
INSERT INTO patient
(radarNO, rrNo, dateReg, nhsNoType, uktNo, surnameAlias, AGE, ethnicGp, postcodeOld, CONSENT, dateBapnReg,
consNeph, STATUS, emailAddress, rrtModality, genericDiagnosis, dateOfGenericDiagnosis, otherClinicianAndContactInfo,
comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, NHSNO, HOSPITALNUMBER, SURNAME,
FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE, unitcode,
sourceType)
SELECT
d.RADAR_NO, d.RR_NO, d.DATE_REG, d.NHS_NO_TYPE, d.UKT_NO, d.SNAME_ALIAS, d.AGE, d.ETHNIC_GP, d.POSTCODE_OLD,
d.CONSENT, d.DATE_BAPN_REG, d.CONS_NEPH, d.STATUS, d.emailAddress, d.RRT_modality, d.genericDiagnosis,
d.dateOfGenericDiagnosis, d.otherClinicianAndContactInfo, d.comments, d.republicOfIrelandId, d.isleOfManId,
d.channelIslandsId, d.indiaId, d.generic, d.NHS_NO, d.HOSP_NO, d.SNAME, d.FNAME, d.DOB, d.ADD1, d.ADD2, d.ADD3,
d.ADD4, d.POSTCODE, d.PHONE1, d.PHONE2, d.MOBILE, d.RDG, 'Radar'
FROM tbl_demographics d;


/**
    Update the sex
 */

UPDATE patient p INNER JOIN
    (
      SELECT s.sType,d.*
        FROM tbl_sex s, tbl_demographics d
        WHERE s.sID = d.sex
    ) dData ON p.nhsno = dData.nhs_no AND p.unitcode = dData.RDG
SET p.sex = dData.sType;

/**
    NOTE - RUN THE UserUpgradeManager.addUserForAllRadarPatients before this part of script
    (Created 279 new users, and 372 new mappings for 544 users)

    Create a user mapping for the disease group
    Insert into usermapping with tbl_demographics nhs_no and RDG fields

    582 row(s) affected
 */

INSERT INTO usermapping (username, unitcode, nhsno, specialty_id)
SELECT
u.username AS username,
unitcode,
p.nhsno,
1 AS specialty_id
FROM patient p, tbl_patient_users pu, rdr_user_mapping m, USER u
WHERE
p.radarNo = pu.RADAR_NO
AND
pu.pID = m.radarUserId
AND
m.userId = u.id
AND
p.sourceType = 'Radar'
AND
m.role = 'ROLE_PATIENT';
/**
    Create a user mapping for the source data entering user's unit
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit

    348 row(s) affected
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
  AND role = 'ROLE_PATIENT'
  AND NOT EXISTS (
SELECT d2.*
FROM tbl_demographics d2, unit u, usermapping ump
WHERE
d2.renal_unit = u.id
AND
d2.nhs_no = ump.nhsno
AND
u.unitcode = ump.unitcode
AND
ump.username NOT LIKE '%-GP%'
AND
d.RADAR_NO = d2.RADAR_NO);


/**
    Create a user mapping for the source data entering user's unit(Allow visibility for another Renal Unit)
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit_2

    4 row(s) affected
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
  and role = 'ROLE_PATIENT'
  AND NOT EXISTS (
SELECT d2.*
FROM tbl_demographics d2, unit u, usermapping ump
WHERE
d2.renal_unit_2 = u.id
AND
d2.nhs_no = ump.nhsno
AND
u.unitcode = ump.unitcode
AND
ump.username NOT LIKE '%-GP%'
AND
d.RADAR_NO = d2.RADAR_NO);


/**
12/03 20:44:54 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6008727193, taking first
12/03 20:44:54 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6446314329, taking first
12/03 20:44:54 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 311, taking first
12/03 20:44:54 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6439467786, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 381, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 394, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 398, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 401, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 747, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 746, taking first
12/03 20:44:55 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 745, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 744, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 739, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 738, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 737, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 735, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 733, taking first
12/03 20:44:56 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 491, taking first
12/03 20:44:57 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 518, taking first
12/03 20:44:57 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 767, taking first
12/03 20:44:57 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 4240248582, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 2709025965, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 691, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 7094410859, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 710, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 750, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 752, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 762, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 763, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 764, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 771, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 773, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 776, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 777, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 778, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 779, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 783, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 784, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 787, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 793, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 794, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 795, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 798, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 799, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 801, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 807, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 809, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 810, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 815, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 816, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 818, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 821, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 822, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 827, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 829, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 830, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 837, taking first
12/03 20:44:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 838, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 842, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 843, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 844, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 845, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 848, taking first
12/03 20:44:59 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 855, taking first
 */

