DROP TABLE IF EXISTS `pv_admin_notification`;
CREATE TABLE `pv_admin_notification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `notification_id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);
