-- Refactor 'RPV' to 'renal' and 'tenancy' to 'specialty'

ALTER TABLE `unit`
  CHANGE `rpvadminname` `renaladminname` VARCHAR(100),
  CHANGE `rpvadminphone` `renaladminphone` VARCHAR(100),
  CHANGE `rpvadminemail` `renaladminemail` VARCHAR(100);

RENAME TABLE `tenancy` TO `specialty`;

ALTER TABLE `tenancyuserrole` DROP KEY `FK7A1749E1AEFDD122`;
ALTER TABLE `tenancyuserrole` DROP FOREIGN KEY `FK7A1749E1AEFDD122`;

ALTER TABLE `tenancyuserrole`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NULL;

ALTER TABLE `tenancyuserrole` ADD CONSTRAINT `FK7A1749E1AEFDD122` FOREIGN KEY (specialty_id) References specialty (id);

RENAME TABLE `tenancyuserrole` TO `specialtyuserrole`;

ALTER TABLE `edtacode`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

ALTER TABLE `log`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NULL;

ALTER TABLE `news`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

ALTER TABLE `result_heading`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

ALTER TABLE `splashpage`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

ALTER TABLE `unit`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

ALTER TABLE `usermapping`
  CHANGE `tenancy_id` `specialty_id` BIGINT(20) NOT NULL;

UPDATE specialty SET context = 'renal' WHERE id = 1;