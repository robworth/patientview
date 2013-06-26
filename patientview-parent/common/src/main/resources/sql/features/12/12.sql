-- limiting access to messaging for some units

DROP TABLE IF EXISTS `pv_feature_access`;
CREATE TABLE `pv_feature_access` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(50) NOT NULL,
`unit_id` bigint(20) NOT NULL,
PRIMARY KEY (`id`)
);

insert into pv_feature_access (name, unit_id) values ('messaging', 1007);