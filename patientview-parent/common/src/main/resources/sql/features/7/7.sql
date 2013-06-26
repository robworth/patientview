/**
 *  Patch: remove the CHI number from the model
 */

-- add a new enum column for nhs no type after nhs_no
ALTER TABLE tbl_demographics ADD COLUMN NHS_NO_TYPE INT(11) NOT NULL AFTER NHS_NO;

-- set the type to be 2 for all rows with a chi_no set, also set the nhs no to be the chi no (there are no users with both an NHS and CHI no)
UPDATE tbl_demographics SET NHS_NO_TYPE = 2, NHS_NO = CHI_NO WHERE CHI_NO IS NOT NULL;

-- set the remaining rows to be type = 1
UPDATE tbl_demographics SET NHS_NO_TYPE = 1 WHERE NHS_NO_TYPE = 0;

-- remove the column chi_no
ALTER TABLE tbl_demographics DROP COLUMN CHI_NO;

-- remove the chi_no from medicine (Note: there are no rows with the chi no set)
ALTER TABLE medicine DROP COLUMN chino;





