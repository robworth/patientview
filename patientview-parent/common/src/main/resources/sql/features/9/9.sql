/**
      Patch: PV and Radar single user table
 */

 -- this should have been done ages ago
alter table user drop column role;

CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `radarUserId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`),
  constraint `FK_RDR_USER_MAPPING_USERID` foreign key (`userId`) references `user`(`id`) on delete Cascade
);

