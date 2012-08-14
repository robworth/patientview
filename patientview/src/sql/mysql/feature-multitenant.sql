
CREATE TABLE `tenancy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `context` (`context`),
  UNIQUE KEY `name` (`name`)
);

INSERT INTO tenancy VALUES (1, 'rpv', 'Renal Patient View', 'RPV');
INSERT INTO tenancy VALUES (2, 'ibd', 'IBD', 'IBD');

CREATE TABLE `tenancyuserrole` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(255) NOT NULL,
  `tenancy_id` BIGINT(20) DEFAULT NULL,
  `user_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tenancy_id` (`tenancy_id`,`user_id`,`role`),
  KEY `FK7A1749E1AEFDD122` (`tenancy_id`),
  CONSTRAINT `FK7A1749E1AEFDD122` FOREIGN KEY (`tenancy_id`) REFERENCES `tenancy` (`id`)
);

INSERT INTO tenancyuserrole (role, tenancy_id, user_id) SELECT role, 1, id FROM USER;

ALTER TABLE USER DROP COLUMN role;

-- per tenancy system data
ALTER TABLE edtacode ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE edtacode set tenancy_id = 1;

ALTER TABLE result_heading ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE result_heading set tenancy_id = 1;

ALTER TABLE unit ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE unit set tenancy_id = 1;

ALTER TABLE news ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE news SET tenancy_id = 1;

ALTER TABLE splashpage ADD tenancy_id BIGINT(20)
NOT NULL;

UPDATE splashpage SET tenancy_id = 1;