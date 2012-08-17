Steps to create a new database
==============================

// TODO update the patientview-create.sql script for IBD
- Create the schema with patientview-create.sql
- (Optional) Add some test data with patientview-increment.sql


Steps to upgrade a database
===========================

- Work out with features you are missing.
- Add the schema patches relating to each feature (numbered) in order (The sum of the patches should equal patientview-create.sql.).


// TODO Bootstrap data for IBD single tenancy