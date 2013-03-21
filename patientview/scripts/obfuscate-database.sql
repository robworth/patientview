/**
  *   This script should take a live db and make is OK to use on staging/dev
  *   Obviously not complete at the moment!
 */

UPDATE unit SET renaladminemail = 'patientview-testing@solidstategroup.com';
UPDATE unit SET unitenquiriesemail = 'patientview-testing@solidstategroup.com';
UPDATE unit SET appointmentemail = 'patientview-testing@solidstategroup.com';
UPDATE unit SET peritonealdialysisemail = 'patientview-testing@solidstategroup.com';