-- More space for dose content
ALTER TABLE medicine CHANGE dose dose VARCHAR(200) DEFAULT '' NULL;