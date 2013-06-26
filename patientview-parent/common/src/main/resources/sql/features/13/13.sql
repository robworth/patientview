/*
Part of patch 9 originally.

ONLY DO THIS AFTER YOU HAVE RUN THE EXPORT FILE TO MAP THESE FIELDS INTO RPV
*/
ALTER TABLE `tbl_adminusers`
  DROP COLUMN `uName`,
  DROP COLUMN `uEmail`,
  DROP COLUMN `uPass`,
  DROP COLUMN `uUserName`;

ALTER TABLE `tbl_patient_users`
  DROP COLUMN `pUserName`,
  DROP COLUMN `pPassWord`;

ALTER TABLE `tbl_users`
  DROP COLUMN `uEmail`,
  DROP COLUMN `uPass`,
  DROP COLUMN `uUserName`;