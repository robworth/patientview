/**
      Patch: Combine unit and tbl_centre tables

      Radar will use the unit table and we will remove the tbl_centre

      The radar data model joins on id, so we need to maintain the radar centre ids as unit ids.

      The patient view units are id agnostic (accessed by unit code).
 */

-- add the country column to the unit table so we can remove the tbl_centres later
alter table unit add column country varchar(100);

 -- add in a unitcode on the centre table so we can join
alter table tbl_centres add column unitcode varchar(100);

-- supply patient view unit codes for the centres so we can join
update tbl_centres set unitcode = "11023" where cid = 1;
update tbl_centres set unitcode = "RQ3" where cid = 2;
update tbl_centres set unitcode = "RA723" where cid = 3;
update tbl_centres set unitcode = "RWM51" where cid = 4;
update tbl_centres set unitcode = "SGC02" where cid = 5;
update tbl_centres set unitcode = "99RQR13" where cid = 6;
update tbl_centres set unitcode = "RBS25" where cid = 7;
update tbl_centres set unitcode = "RJ122" where cid = 8;
update tbl_centres set unitcode = "RP4" where cid = 9;
update tbl_centres set unitcode = "RW3RM" where cid = 10;
update tbl_centres set unitcode = "99RTD01" where cid = 11;
update tbl_centres set unitcode = "99RCSLB" where cid = 12;
update tbl_centres set unitcode = "99RHM01" where cid = 13;
update tbl_centres set unitcode = "RENALREG" where cid = 14;
update tbl_centres set unitcode = "DEMO" where cid = 15;
update tbl_centres set unitcode = "UNKNOWN" where cid = 16;
update tbl_centres set unitcode = "VELLORE" where cid = 55;
update tbl_centres set unitcode = "BANGALORE" where cid = 56;
update tbl_centres set unitcode = "NEWDEHLI" where cid = 57;
update tbl_centres set unitcode = "TEHRAN" where cid = 58;
update tbl_centres set unitcode = "DEMOENTRY" where cid = 99;

update tbl_centres set CCOUNTRY = 1 where cid = 16;
update tbl_centres set CABBREV = "UNKNOWN" where cid = 16;
update tbl_centres set CCOUNTRY = 2 where cid = 99;

-- shift the patient view units to 1000+
update unit set id = id + 1000;

-- update ids of any radar units that already exist in the patientview dataset
UPDATE tbl_centres c, unit u SET u.id = c.cid WHERE c.unitcode = u.unitcode;

-- add the radar units into the patientview table excluding the already existing units
insert into unit (unitcode, id, name, shortname, country, sourcetype, tenancy_id)
select unitcode, cID, cName, cAbbrev, cCountry, 'renalunit', 1
from tbl_centres where unitcode not in (SELECT c.unitcode FROM tbl_centres c, unit u WHERE c.unitcode = u.unitcode);

-- set the country to be 1 (the UK) for units from RPV
UPDATE unit SET country = 1 WHERE sourceType = 'renalunit' AND (country IS NULL OR country = '');

-- drop the now unused table
drop table tbl_centres;
