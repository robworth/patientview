/**
    Add a date column in unit table.
 */
 ALTER TABLE unit ADD COLUMN `lastReceived` datetime DEFAULT NULL;
