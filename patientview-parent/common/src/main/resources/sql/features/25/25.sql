/**
    drop nhsNoType column in patient table.
 */
 ALTER TABLE patient DROP COLUMN `nhsNoType`;

/**
    Add a user id column in patient table.
 */
 ALTER TABLE patient ADD COLUMN `radarConsentConfirmedByUserId` int(11) DEFAULT NULL;


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

