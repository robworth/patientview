/**
    Add 2 columns in user table.
    remove a column in user table.
 */
ALTER TABLE user ADD COLUMN `firstName` VARCHAR(100)  NULL;
ALTER TABLE user ADD COLUMN `lastName` VARCHAR(100)  NULL;

UPDATE user
SET firstName = SUBSTRING_INDEX(  NAME ,' ', 1),
lastName = SUBSTRING_INDEX(  NAME ,' ', -1);

ALTER TABLE `user` DROP COLUMN `name`;