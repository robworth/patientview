/**
      Patch: PV and Radar single user table

      NOT FULLY TESTED ON DEV YET
 */

CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `radarUserId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`),
  constraint `FK_RDR_USER_MAPPING_USERID` foreign key (`userId`) references `user`(`id`) on delete Cascade
);

/*
ONLY DO THIS AFTER YOU HAVE RUN THE EXPORT FILE TO MAP THESE FIELDS INTO RPV
*/
ALTER TABLE `tbl_adminusers`
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
