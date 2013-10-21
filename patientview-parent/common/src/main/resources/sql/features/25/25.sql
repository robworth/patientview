/**
    Add columns in user table.
 */
ALTER TABLE user ADD COLUMN `created` datetime DEFAULT NULL;
ALTER TABLE user ADD COLUMN `updated` datetime DEFAULT NULL;

/**
    Update the user' created from log table.
 */
UPDATE user u INNER JOIN log
ON (
        u.username = log.user
    AND
        (
            log.action = 'patient add'
         OR
            log.action = 'admin add'
         )
    )
SET u.created = log.date;
