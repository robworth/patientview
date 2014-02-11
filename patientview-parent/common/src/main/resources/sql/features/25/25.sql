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

/**
    alter the columns in the tables to DATETIME
     */
    /**
        pv_patientjoin_request table.
     */
    ALTER TABLE pv_patientjoin_request MODIFY COLUMN dateofbirth DATETIME NOT NULL;

     /**
        rdr_alport_deafness table.
     */
    ALTER TABLE rdr_alport_deafness ADD COLUMN dateProblemFirstNoticed DATETIME NULL;
    ALTER TABLE rdr_alport_deafness ADD COLUMN dateStartedUsingHearingAid DATETIME NULL;

    UPDATE rdr_alport_deafness
    SET dateProblemFirstNoticed = DATE_SUB(NOW(), INTERVAL ageProblemFirstNoticed YEAR),
    dateStartedUsingHearingAid = DATE_SUB(NOW(), INTERVAL ageStartedUsingHearingAid YEAR);

    ALTER TABLE rdr_alport_deafness DROP COLUMN ageProblemFirstNoticed;
    ALTER TABLE rdr_alport_deafness DROP COLUMN ageStartedUsingHearingAid;

     /**
        rdr_hnf1b_misc table.
     */
    ALTER TABLE rdr_hnf1b_misc ADD COLUMN dateAtDiabetesDiagnosis DATETIME NULL;
    ALTER TABLE rdr_hnf1b_misc ADD COLUMN dateAtGoutDiagnosis DATETIME NULL;

    UPDATE rdr_hnf1b_misc
    SET dateAtDiabetesDiagnosis = DATE_SUB(NOW(), INTERVAL ageAtDiabetesDiagnosis YEAR),
    dateAtGoutDiagnosis = DATE_SUB(NOW(), INTERVAL ageAtGoutDiagnosis YEAR);

    ALTER TABLE rdr_hnf1b_misc DROP COLUMN ageAtDiabetesDiagnosis;
    ALTER TABLE rdr_hnf1b_misc DROP COLUMN ageAtGoutDiagnosis;

     /**
        patient table.
     */
    ALTER TABLE patient  ADD COLUMN temp DATETIME NULL;
    UPDATE patient
    SET temp = CASE
         /** dd.MM.y */
         WHEN dateofbirth REGEXP '[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}' THEN STR_TO_DATE(dateofbirth, '%d.%m.%y')
         /** yyyy-MM-dd */
         WHEN dateofbirth REGEXP '[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}' THEN STR_TO_DATE(dateofbirth, '%Y-%m-%d')
         /** dd-MM-y */
         WHEN dateofbirth REGEXP '[0-9]{2}\\-[0-9]{2}\\-[0-9]{2}' THEN STR_TO_DATE(dateofbirth, '%d-%m-%y')
         /** dd/MM/y */
         WHEN dateofbirth REGEXP '[0-9]{2}\\/[0-9]{2}\\/[0-9]{2}' THEN STR_TO_DATE(dateofbirth, '%d/%m/%y')
         ELSE NULL END;

    ALTER TABLE patient DROP COLUMN dateofbirth;
    ALTER TABLE patient CHANGE temp dateofbirth DATETIME NULL ;
     ALTER TABLE patient ADD COLUMN `radarConsentConfirmedByUserId` int(11) DEFAULT NULL;
