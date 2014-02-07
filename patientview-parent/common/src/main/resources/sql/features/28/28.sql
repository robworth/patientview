/**
    Add 2 columns in user table.
    remove a column in user table.
 */
ALTER TABLE user ADD COLUMN `firstName` VARCHAR(100)  NULL;
ALTER TABLE user ADD COLUMN `lastName` VARCHAR(100)  NULL;


CREATE TABLE user_table_datafix AS
SELECT  usr.username
,       usr.name
,       SUBSTRING_INDEX(  NAME,' ', 1) firstname
,       SUBSTRING_INDEX(  REPLACE(NAME, '-GP','') ,' ', -1) surname
FROM  USER usr
LEFT JOIN   usermapping usm ON usm.username = usr.username
LEFT JOIN   patient ptt ON usm.nhsno = ptt.nhsno
WHERE ptt.surname IS NULL
AND (  NAME NOT LIKE '%,%'
AND   NAME NOT LIKE '%(%');

INSERT INTO user_table_datafix
SELECT  usr.username
,       usr.name
,       TRIM(SUBSTRING_INDEX(  NAME ,',',-1)) firstname
,       REPLACE(TRIM(SUBSTRING_INDEX(  NAME ,',', 1)), '-GP','') surname
FROM  USER usr
LEFT JOIN   usermapping usm ON usm.username = usr.username
LEFT JOIN   patient ptt ON usm.nhsno = ptt.nhsno
WHERE ptt.surname IS NULL
AND   NAME LIKE '%,%'
AND   NAME NOT LIKE '%(%';

INSERT INTO user_table_datafix
SELECT  usr.username
,       usr.name
,       TRIM(SUBSTR(SUBSTRING_INDEX(REPLACE(NAME, 'Dr', '') , '(' ,1) ,1, INSTR(REPLACE(NAME, 'Dr', ''), ' ') )) firstname
,       TRIM(SUBSTR(SUBSTRING_INDEX(REPLACE(NAME, 'Dr', ''), '(' ,1) ,INSTR(REPLACE(NAME, 'Dr', ''), ' '), INSTR(REPLACE(NAME, 'Dr', ''), '(') - 1 )) surname
FROM  USER usr
LEFT JOIN   usermapping usm ON usm.username = usr.username
LEFT JOIN   patient ptt ON usm.nhsno = ptt.nhsno
WHERE ptt.surname IS NULL
AND   NAME LIKE '%(%'
AND   NAME LIKE '%)';

INSERT INTO user_table_datafix
SELECT  DISTINCT usr.username
,       usr.name
,       ptt.forename
,	    ptt.surname
FROM    USER usr
INNER JOIN usermapping usm ON usr.username = usm.username
INNER JOIN patient ptt ON ptt.nhsno = usm.nhsno;

SELECT *
FROM  user_table_datafix;

UPDATE USER usr INNER JOIN user_table_datafix ddf ON usr.username = ddf.username SET usr.firstname = ddf.firstname, usr.lastname = ddf.surname;

ALTER TABLE USER DROP COLUMN NAME;

ALTER TABLE unit ADD COLUMN visible TINYINT DEFAULT 1;

#ALTER TABLE testresult ADD COLUMN units VARCHAR(20); not doable

UPDATE patient SET sex = 'F' WHERE sex = 'Female';

UPDATE patient SET sex = 'M' WHERE sex = 'Male';