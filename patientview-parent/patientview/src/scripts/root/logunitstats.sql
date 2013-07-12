USE patientview;

INSERT INTO unitstat (unitcode, yearmonth, action, count)
SELECT unitcode, DATE_FORMAT(date,'%Y-%m') AS yearmonth, action, COUNT(*)
FROM log
WHERE unitcode <> ''
AND YEAR(date) = 'YR'
AND MONTH(date) =  'MTH'
GROUP BY unitcode, yearmonth, action;

INSERT INTO unitstat (unitcode, yearmonth, action, count)
SELECT unitcode, DATE_FORMAT(date,'%Y-%m') AS yearmonth, 'unique data load' AS action, COUNT(DISTINCT nhsno) as count
FROM log
WHERE unitcode <> ''
AND action = 'patient data load'
AND YEAR(date) = 'YR'
AND MONTH(date) =  'MTH'
GROUP BY unitcode, yearmonth;

INSERT INTO unitstat (unitcode, yearmonth, action, count)
SELECT unitcode, DATE_FORMAT(date,'%Y-%m') AS yearmonth, 'unique logon' AS action, COUNT(DISTINCT user)
FROM log
WHERE unitcode <> ''
AND action = 'logon'
AND YEAR(date) = 'YR'
AND MONTH(date) =  'MTH'
GROUP BY unitcode, yearmonth;
