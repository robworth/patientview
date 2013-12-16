/**
    Add a user id column in patient table.
 */
 ALTER TABLE patient ADD COLUMN `radarConsentConfirmedByUserId` int(11) DEFAULT NULL;

