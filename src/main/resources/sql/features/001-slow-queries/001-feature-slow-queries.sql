-- DESCRIBE SELECT this.id AS id0_, this.date AS date0_, this.nhsno AS nhsno0_, this.user AS user0_, this.action AS action0_, this.actor AS actor0_, this.unitcode AS unitcode0_, this.extrainfo AS extrainfo0_ FROM LOG this WHERE this.nhsno='1234567890' AND this.action LIKE 'logon' ORDER BY this.date DESC LIMIT 1;
CREATE INDEX log_index_nhsno ON LOG (nhsno);
CREATE INDEX log_index_action ON LOG (action);
-- note could have a compound index for nhs and action
CREATE INDEX log_index_date ON LOG (DATE);

-- select letter0_.id as id0_, letter0_.nhsno as nhsno0_, letter0_.unitcode as unitcode0_, letter0_.date as date0_, letter0_.type as type0_, letter0_.content as content0_ from letter letter0_ where letter0_.id=32811531;
ALTER TABLE letter MODIFY COLUMN id INT(11) NOT NULL PRIMARY KEY;

-- SELECT testresult.*, unit.shortname FROM testresult, user, usermapping, result_heading, unit WHERE user.username = 'jswl' AND user.username = usermapping.username AND usermapping.nhsno = testresult.nhsno AND testresult.testcode = result_heading.headingcode AND testresult.unitcode = unit.unitcode AND result_heading.panel = 1;
CREATE INDEX usermapping_index_nhsno ON USERMAPPING (nhsno);
CREATE INDEX usermapping_index_username ON USERMAPPING (username);