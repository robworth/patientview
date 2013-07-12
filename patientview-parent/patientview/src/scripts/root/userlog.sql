USE patientview;

INSERT INTO userlog (datestamp, unitcode, role, count)
SELECT now() AS datestamp, usermapping.unitcode, specialtyuserrole.role, count(*) AS count
FROM user, usermapping, specialtyuserrole
WHERE user.username NOT LIKE '%-GP'
AND user.username = usermapping.username
AND user.id = specialtyuserrole.user_id
GROUP BY usermapping.unitcode, specialtyuserrole.role;
