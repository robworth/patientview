Steps to create a new database
==============================

- Create the schema with patientview-create.sql
- (Optional) Add some test data with patientview-test-data.sql


Steps to upgrade a database
===========================

- patientview-increment.sql will upgrade the live database to this branch of the code
- See archive for previous patches per feature. Add the schema patches relating to each feature (numbered) in order
(The sum of the patches should equal patientview-create.sql.).
