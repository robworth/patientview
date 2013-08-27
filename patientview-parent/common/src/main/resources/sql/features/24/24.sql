/**
    Add a column in emailverification table.
 */
ALTER TABLE emailverification ADD COLUMN   `lastverificationdate` datetime DEFAULT NULL;