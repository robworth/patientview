delete from PV_ADMIN_NOTIFICATION where id = 3;
ALTER TABLE `PV_ADMIN_NOTIFICATION` ADD UNIQUE KEY (`notification_id`, `email`);