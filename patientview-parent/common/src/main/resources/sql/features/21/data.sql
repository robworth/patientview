
/**
     Inserts data from tbl_demograpics to patient
     -- there are 64 patients that exist in PV and Radar which the same nhsno and unit, we will not merge these
     -- otherwise we risk removing radar uploaded data with the PV importer

     564 row(s) affected
 */
INSERT INTO patient
(radarNO, rrNo, dateReg, nhsNoType, uktNo, surnameAlias, AGE, ethnicGp, postcodeOld, CONSENT, dateBapnReg,
consNeph, STATUS, emailAddress, rrtModality, genericDiagnosis, dateOfGenericDiagnosis, otherClinicianAndContactInfo,
comments, republicOfIrelandId, isleOfManId, channelIslandsId, indiaId, generic, NHSNO, HOSPITALNUMBER, SURNAME,
FORENAME, DATEOFBIRTH, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, POSTCODE, TELEPHONE1, TELEPHONE2, MOBILE, unitcode,
sourceType, sex)
SELECT
d.RADAR_NO, d.RR_NO, d.DATE_REG, d.NHS_NO_TYPE, d.UKT_NO, d.SNAME_ALIAS, d.AGE, d.ETHNIC_GP, d.POSTCODE_OLD,
d.CONSENT, d.DATE_BAPN_REG, d.CONS_NEPH, d.STATUS, d.emailAddress, d.RRT_modality, d.genericDiagnosis,
d.dateOfGenericDiagnosis, d.otherClinicianAndContactInfo, d.comments, d.republicOfIrelandId, d.isleOfManId,
d.channelIslandsId, d.indiaId, d.generic, d.NHS_NO, d.HOSP_NO, d.SNAME, d.FNAME, d.DOB, d.ADD1, d.ADD2, d.ADD3,
d.ADD4, d.POSTCODE, d.PHONE1, d.PHONE2, d.MOBILE, u.unitcode, 'Radar', s.sType
FROM tbl_demographics d, unit u, tbl_sex s
WHERE
d.renal_unit = u.id
AND
d.sex = s.sId;

/**
    todo NOTE - RUN THE UserUpgradeManager.addUserForAllRadarPatients before this part of script
    (Created 279 new users, and 372 new mappings for 544 users)

    Create a user mapping for the disease group
    Insert into usermapping with tbl_demographics nhs_no and RDG fields

    632 row(s) affected
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
  AND role = 'ROLE_PATIENT';

/**
    Create a user mapping for the source data entering user's unit
    Only do this if it does not already exist
    Insert into usermapping with use renal_unit

    378 row(s) affected
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
12/16 20:59:38 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6008727193, taking first
12/16 20:59:39 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6446314329, taking first
12/16 21:00:00 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6008727193, taking first
12/16 21:00:00 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6446314329, taking first
12/16 21:00:18 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 298, taking first
12/16 21:00:18 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 299, taking first
12/16 21:00:22 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 311, taking first
12/16 21:00:25 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 6439467786, taking first
12/16 21:00:52 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 381, taking first
12/16 21:00:57 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 394, taking first
12/16 21:00:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 398, taking first
12/16 21:01:00 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 401, taking first
12/16 21:01:03 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 747, taking first
12/16 21:01:06 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 746, taking first
12/16 21:01:07 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 745, taking first
12/16 21:01:10 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 744, taking first
12/16 21:01:23 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 739, taking first
12/16 21:01:26 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 738, taking first
12/16 21:01:28 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 737, taking first
12/16 21:01:31 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 735, taking first
12/16 21:01:37 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 733, taking first
12/16 21:01:44 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 491, taking first
12/16 21:01:57 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 518, taking first
12/16 21:02:39 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 767, taking first
12/16 21:02:42 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 4240248582, taking first
12/16 21:02:51 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 2709025965, taking first
12/16 21:02:52 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 691, taking first
12/16 21:02:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient user found for nhsno 7094410859, taking first
12/16 21:02:58 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 710, taking first
12/16 21:03:00 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 750, taking first
12/16 21:03:01 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 752, taking first
12/16 21:03:03 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 762, taking first
12/16 21:03:04 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 763, taking first
12/16 21:03:04 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 764, taking first
12/16 21:03:05 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 771, taking first
12/16 21:03:05 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 773, taking first
12/16 21:03:06 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 776, taking first
12/16 21:03:06 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 777, taking first
12/16 21:03:06 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 778, taking first
12/16 21:03:07 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 779, taking first
12/16 21:03:08 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 783, taking first
12/16 21:03:08 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 784, taking first
12/16 21:03:09 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 787, taking first
12/16 21:03:10 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 793, taking first
12/16 21:03:10 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 794, taking first
12/16 21:03:10 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 795, taking first
12/16 21:03:11 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 798, taking first
12/16 21:03:11 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 799, taking first
12/16 21:03:12 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 801, taking first
12/16 21:03:13 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 807, taking first
12/16 21:03:14 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 809, taking first
12/16 21:03:14 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 810, taking first
12/16 21:03:15 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 815, taking first
12/16 21:03:16 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 816, taking first
12/16 21:03:16 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 818, taking first
12/16 21:03:17 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 821, taking first
12/16 21:03:17 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 822, taking first
12/16 21:03:19 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 827, taking first
12/16 21:03:19 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 829, taking first
12/16 21:03:19 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 830, taking first
12/16 21:03:21 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 837, taking first
12/16 21:03:21 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 838, taking first
12/16 21:03:22 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 842, taking first
12/16 21:03:23 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 843, taking first
12/16 21:03:23 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 844, taking first
12/16 21:03:23 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 845, taking first
12/16 21:03:24 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 848, taking first
12/16 21:03:26 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 855, taking first
12/16 21:03:29 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 867, taking first
12/16 21:03:29 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 868, taking first
12/16 21:03:31 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 874, taking first
12/16 21:03:33 ERROR iew.radar.dao.impl.UserDaoImpl  - Duplicate patient users found for radarno 883, taking first
12/16 21:03:33 INFO  .RadarRpvSingleUserTableExport  - Completed. Created 308 new users, and 402 new mappings for 614 users

*/