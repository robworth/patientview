/**
  *   Radar: back fill any empty nhsnos in the testresult table
 */

UPDATE testresult t, tbl_demographics d SET t.nhsno = d.nhs_no WHERE t.radar_no = d.radar_no AND t.nhsno = '' AND t.radar_no IS NOT NULL;