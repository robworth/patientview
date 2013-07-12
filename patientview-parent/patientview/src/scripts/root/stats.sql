USE patientview;

SELECT log.*  INTO OUTFILE '/usr/local/etc/unitstatstemp/UNIT-YR-MTH.csv' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"' LINES TERMINATED BY '\n' FROM log WHERE MONTH(date) = 'MTH' AND YEAR(date) = 'YR' AND unitcode='UNIT';
