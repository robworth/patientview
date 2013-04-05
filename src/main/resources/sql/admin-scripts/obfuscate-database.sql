/**
  *   This script should take a live db and make is OK to use on staging/dev

   *  You can log into patientview using real usernames and the password 'pppppp'
   *  See bottom of script for radar users, passwords unchanged.
 */

UPDATE aboutme SET aboutme = 'Something about me', talkabout = 'I want to talk about...';
UPDATE centre SET centreEmail = 'patientview-testing@solidstategroup.com';
UPDATE COMMENT SET body = CONCAT('a comment from ', nhsno);
UPDATE diagnosis SET diagnosis = CONCAT('a diagnosis for ', nhsno);
DELETE FROM emailverification;
UPDATE feedback SET NAME = CONCAT('Name for nhsno ', nhsno), COMMENT = CONCAT('Comment for nhsno ', nhsno), COMMENTedited = CONCAT('Comment edited for nhsno ', nhsno);
DELETE FROM jforum_bookmarks;
UPDATE jforum_posts_text SET post_text = CONCAT('Post text for id ', post_id), post_subject = CONCAT('Post subject for id ', post_id);
DELETE FROM jforum_privmsgs;
DELETE FROM jforum_privmsgs_text;
UPDATE jforum_users SET user_email = 'patientview-testing@solidstategroup.com';
UPDATE letter SET TYPE = CONCAT('some type suitable for ', unitcode), content = 'Some letter content';
DELETE FROM LOG;
UPDATE medicine SET NAME = CONCAT('Medicine name for nhsno ', nhsno), dose = CONCAT('Medicine dose for nhsno ', nhsno);
-- todo all the message stuff
UPDATE patient SET surname = CONCAT('Surname for nhsno ', nhsno), forename = CONCAT('Forename for nhsno ', nhsno), dateofbirth = '1975-05-17', sex = 'F', address1 = 'addresss1', address2 = 'address2', address3 = 'address3', postcode = 'N8 999', telephone1 = '8888888888', telephone2 = NULL, mobile = NULL,
gpname = 'Dr Doctor', gpaddress1 = 'Some St', gpaddress2 = 'London', gpaddress3 = '', gppostcode = 'N8 555', gptelephone = NULL, otherconditions = NULL, address4 = NULL, bloodgroup = NULL, bmdexam = NULL, gpemail = NULL;
UPDATE rdc_genetic_test SET testDoneOn = NULL, whatResultsShowed = 'Some results info', keyEvidence = 'Some facts';
DELETE FROM rdr_alport_deafness;
DELETE FROM rdr_hnf1b_misc;
UPDATE tbl_adminusers SET uEmail = 'patientview-testing@solidstategroup.com';
DELETE FROM tbl_clinicaldata;
UPDATE tbl_consultants SET cSNAME = CONCAT('Surname for cID ', cID), cFNAME = CONCAT('First name for cID ', cID);
UPDATE tbl_demographics SET SNAME = CONCAT('Surname for radar no ', radar_no), sname_alias = '-', FNAME = CONCAT('Firstname for radar no ', radar_no), DOB = '1975-05-17', age = 37, SEX = 2,
ETHNIC_GP = '9S1..', ADD1 = 'address1', ADD2 = 'address2', ADD3 = 'address3', ADD4 = '-', postcode = 'N8 888', postcode_old = '-', emailaddress = 'patientview-testing@solidstategroup.com', phone1 = '8888888888',
phone2 = NULL, mobile = NULL, genericDiagnosis = NULL, otherClinicianAndContactInfo = NULL, comments = NULL;
UPDATE tbl_diagnosis SET DIAG_TXT = CONCAT('Diagnosis for radar no ', radar_no), age_at_diag = 4, height_first_visit = 150, sig_diag1 = 'Some other diagnosis', sig_diag2 = NULL;
DELETE FROM tbl_hospitalisation;
DELETE FROM tbl_immunsup_treatment;
DELETE FROM tbl_issuetracker;
DELETE FROM tbl_labdata;
DELETE FROM tbl_pathology;
UPDATE tbl_patient_users SET pUserName = CONCAT(CONCAT(pId, 'patient'), '-testing@solidstategroup.com'), pDOB = '1975-05-17 00:00:00';
DELETE FROM tbl_relapse;
DELETE FROM tbl_rrt_plasma;
DELETE FROM tbl_rrt_treatment;
DELETE FROM tbl_therapy;
DELETE FROM tbl_transplant;
DELETE FROM tbl_transplant_reject;
UPDATE tbl_users SET uSurname =  CONCAT('Surname for uID', uID), uForename = CONCAT('Forename for uID', uID), uTitle = 'Ms', uRole = 'Some job role',
uEmail = CONCAT(CONCAT(uId, 'professional'), '-testing@solidstategroup.com');
UPDATE unit SET renaladminemail = 'patientview-testing@solidstategroup.com', unitenquiriesemail = 'patientview-testing@solidstategroup.com', appointmentemail = 'patientview-testing@solidstategroup.com', peritonealdialysisemail = 'patientview-testing@solidstategroup.com';
-- set all the user passwords to 'pppppp'
UPDATE USER SET PASSWORD = '891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb', NAME = CONCAT('Name for ', username), email = 'patientview-testing@solidstategroup.com', screenname = CONCAT('Screen name for ', username);

-- setup the radar test users
UPDATE tbl_users SET uEmail = 'radarsuperuser@solidstategroup.com' WHERE uID = 28;
UPDATE tbl_users SET uEmail = 'demo@nhs.net' WHERE uID = 37;
UPDATE tbl_adminusers SET uEmail = 'renalreg@renalreg.com' WHERE uID = 2;



