/**
    Create dia_diabetes table.
 */
DROP TABLE IF EXISTS `dia_mydiabetes`;
CREATE TABLE `dia_mydiabetes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diabetesType` varchar(255) DEFAULT NULL,
  `yearDiagnosed` int(4) DEFAULT NULL,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient` (`nhsno`,`unitcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

