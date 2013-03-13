/**
      Patch: Combine unit and tbl_centre tables

      Radar will use the unit table and we will remove the tbl_centre

      The radar data model joins on id, so we need to maintain the radar centre ids as unit ids.

      The patient view units are id agnostic (accessed by unit code).
 */


-- shift the patient view units to 1000+
update unit set id = id + 1000;

-- update ids of any radar units that already exist in the patientview dataset
UPDATE tbl_centres c, unit u SET u.id = c.cid WHERE c.unitcode = u.unitcode;

-- the radar units do not have a tenancy so we need to allow nulls in the tenancy id column
ALTER TABLE unit MODIFY tenancy_id bigint(20) NULL;

-- add the radar units into the patientview table excluding the already existing units
insert into unit (unitcode, id, name, shortname, country, sourcetype)
select unitcode, cID, cName, cAbbrev, cCountry, 'radargroup'
from tbl_centres where unitcode not in (SELECT c.unitcode FROM tbl_centres c, unit u WHERE c.unitcode = u.unitcode);

-- drop the now unused table
drop table tbl_centres;
