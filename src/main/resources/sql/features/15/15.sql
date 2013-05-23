DROP TABLE IF EXISTS `pv_admin_notification`;
CREATE TABLE `pv_admin_notification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `notification_id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

insert into pv_admin_notification (id, notification_id, email) values (1, 0, 'rob.worth@worthsolutions.com');
insert into pv_admin_notification (id, notification_id, email) values (2, 1, 'rob.worth@worthsolutions.com');
insert into pv_admin_notification (id, notification_id, email) values (3, 1, 'rob.worth@worthsolutions.com');
insert into pv_admin_notification (id, notification_id, email) values (4, 2, 'rob.worth@worthsolutions.com');