Radar: https://www.renalradar.org/
=================================

RaDaR is the site where healthcare professionals can register patients and enter data.
Patients who are registered with RaDaR can look up their test results and info at PatientView (https://github.com/robworth/patientview).

There is also admin functionality on the site for managing users.


Getting Starting
================

- Get running locally with the test database.  The Radar database is shared with PatientView, see instructions here https://github.com/robworth/patientview.
- Login as a patient, professional, and an admin and review the application screens.

Note:
=====

Now that this module is part of a multi-module project, all commands in the documentation are from the context of the module pom.xml directory,
i.e. patientview-parent/radar.


Running locally using Intellij Idea or similar IDE
==================================================

- Setup your properties file.  It should be located at /src/main/filters/radar-dev.properties.
Copy this manually and complete for your local environment.  There is an example file in /src/main/resources/developer/radar-dev.properties
(Note: you may need to remove the submodule for filters that points to git@git.solidstategroup.com:radar_view_filters.git in the .gitmodules file)

- Setup a maven run configuration that will build the following command:

`clean jetty:run -Pdev`

This will clear down any temporary files (as specified by the maven clean plugin and .gitignore file).
Build the app and deploy to an embedded Jetty server.


Test Users
==========

- username: patient1    pass:  pppppp
- username: patient2    pass:  pppppp
- username: superadmin  pass:  pppppp


Running Tests
=============

Tests run against a separate db and rebuild the schema and data from scratch for each test run using DBUnit.

- Setup your properties file.  It should be located at /src/main/filters/radar-test.properties.  There is an example file in /src/main/resources/developer/radar-test.properties.
  Copy this manually and complete for your local environment.
- Set your jdbc.databasename and jdbc.url properties to point to a new empty database
- Run the tests

`mvn test -Ptest'


Outsourced Development Processes
================================

- fork the outsourcing branch - it is called 'shinetech'
- clone your new repo
- implement new feature/bug fix
- commit/push fix into your forked repo
- submit pull request from your forked repo into https://github.com/robworth/radar/tree/shinetech

