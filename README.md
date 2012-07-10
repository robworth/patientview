patientview
===========

Complete instructions to build to follow:

But basically you need to:

- remove the submodule for filters that points to git@git.solidstategroup.com:patient_view_filters.git in the .gitmodules file
- create a <filter>src/main/filters/${env}-filters.properties</filter> for your profile
- install the db using patientview/src/sql/mysql/patientview.sql
- run the maven build command:

mvn clean package -P<profile-name>

The war file will be built to the maven target directory.

Instructions to run locally to follow...