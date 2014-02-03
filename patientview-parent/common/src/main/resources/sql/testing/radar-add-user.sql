SELECT   *
FROM     USER;

DELETE
FROM     USER
WHERE    id <> 57987;


SELECT   *
FROM     usermapping
WHERE    username <> 'superadmin';

DELETE
FROM     usermapping
WHERE    username <> 'superadmin';

SELECT  *
FROM    specialtyuserrole
WHERE   role <> 'superadmin';

DELETE
FROM    specialtyuserrole
WHERE   role <> 'superadmin';

SELECT  *
FROM    patient;

DELETE
FROM    patient;

INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (2234567890, 'TEST','RENALA','RENALA');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (6548973216, 'TEST','RENALB','RENALB');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (6548973275, 'TEST','RENALC','RENALC');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (1235677893, 'TEST','RENALA-SRNS','RENALA');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (1235277895, 'TEST','RENALA-ARPKD','RENALA');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (8235277891, 'TEST','RENALC-AHUS','RENALC');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (7231277896, 'TEST','RENALC-HNF1B','RENALC');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (6231277895, 'TEST','SRNS','SRNS');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (5231427810, 'TEST','ALLPORT','ALLPORT');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (5436427219, 'TEST','HNF1B','HNF1B');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (4136827210, 'TEST','ARPKD','ARPKD');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (3136427416, 'TEST','HYPERRDG','HYPERRDG');
INSERT INTO patient (nhsno, forename, surname, unitcode) VALUES (9326427412, 'TEST','AHUS','AHUS');