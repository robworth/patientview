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


 DELETE
  pv_groupmessage AS p
FROM
  pv_groupmessage AS p,
  (SELECT
    id
  FROM
    pv_groupmessage
  WHERE id NOT IN
    (SELECT
      a.id
    FROM
      pv_groupmessage a
    GROUP BY a.conversation_id,
      a.recipient_id)) AS b
WHERE p.id = b.id ;
ALTER TABLE pv_groupmessage ADD UNIQUE uniquegroupmessage (conversation_id, recipient_id);
