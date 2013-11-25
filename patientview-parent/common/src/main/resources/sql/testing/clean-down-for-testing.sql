delete from rdr_patient_linkage;
delete from usermapping;
delete from rdr_user_mapping;
delete from userlog;
delete from log;
delete from pv_user_log;
delete from testresult;
delete from pv_procedure;
delete from pv_patientjoin_request;
delete from pv_allergy;
delete from patient;
delete from medicine;
delete from letter;
delete from emailverification;
delete from diagnostic;
delete from comment;
delete from aboutme;
delete from news;

delete from conversation;
delete from message;
delete from pv_emailqueue;
DELETE FROM pv_job;
delete from pv_groupmessage;

delete from rdr_hnf1b_misc;
delete from rdr_alport_deafness;
delete from rdc_genetic_test;

delete from diagnosis;
delete from tbl_diagnosis;
delete from tbl_labdata;
delete from tbl_patient_users;
delete from tbl_users;
delete from tbl_adminusers;

delete from specialtyuserrole where user_id not in (57987); -- superadmin
delete from user where username not in ('superadmin');
delete from unit where unitcode not in ('RENALA','RENALB','RENALC','SRNS','MPGN','ALPORT','HNF1B');

