PatientView
===========

PatientView allows patients to login and see their details, medicines and test results.

Data is supplied by the patients renal unit.  It is sent to the PatientView server from the hospital units via encrypted XML over sFTP,
 then imported into the database.

Health care professionals can login to PatientView to administrate patients and unit users.


Getting Started
===============

- Get running locally with the test database (see below)
- Login as a patient and a superadmin (see below)

Note:
=====

Now that this module is part of a multi-module project, all commands in the documentation are from the context of the module pom.xml directory,
i.e. patientview-parent/patientview.


Running locally using Intellij IDEA or similar IDE
==================================================

- Import the mysql dump (/src/main/resources/developer/schema_data_patientview_develop.sql) into a local database called 'patientview'

- Setup your properties file.  It should be located at /src/main/filters/localhost-filters.properties.
Copy this manually and complete for your local environment.  There is an example file in /src/main/resources/developer/localhost-filters.properties
(Note: you may need to remove the submodule for filters that points to git@git.solidstategroup.com:patient_view_filters.git in the .gitmodules file)

- Setup a maven run configuration that will build the following command:

`clean compile war:inplace tomcat7:run -Plocalhost`

- Supply the following VM parameters to the run configuration runner tab to allocate enough memory and to allow the JSPs to compile:

`-Xmx512m -XX:MaxPermSize=128m -Dorg.apache.jasper.compiler.Parser.STRICT_QUOTE_ESCAPING=false`

This will clear down any temporary files (as specified by the maven clean plugin and .gitignore file).
Build the exploded war over the main/src/webapp directory.
Starts an embedded Tomcat 7 server and runs the webapp.

NOTE: The build is still dependent on Tomcat for some of the configuration so you cannot use Jetty.


Test Users
==========

- username: patient1    pass:  pppppp
- username: patient2    pass:  pppppp
- username: superadmin  pass:  pppppp


Running Tests Against Mysql
===========================

Tests run against a separate db and rebuild the schema and data from scratch for each test run.

- Setup your properties file.  It should be located at /src/main/filters/test-filters.properties.
  Copy this manually and complete for your local environment.
- Set your jdbc.databasename and jdbc.url properties to point to a new empty database
- Run the tests

`mvn test -Ptest'


Running Tests In Memory
=======================

Tests run against a H2 in memory db and rebuild the schema using the JPA definitions from scratch for each test run.

- Setup your properties file.  It should be located at /src/main/filters/localhost-test-filters.properties.
  Copy this manually and complete for your local environment.
- Run the tests

`mvn test -Plocalhost-test'


Outsourced Development Process
==============================

- fork the outsourcing branch - it is called 'shinetech'
- clone your new repo
- implement new feature/bug fix
- commit/push fix into your forked repo
- submit pull request from your forked repo into https://github.com/robworth/patientview/tree/shinetech


Debugging JSPs using Intellij IDEA
==================================

- Not possible using the embedded tomcat maven plugin
- Build the webapp using maven:

`clean compile war:inplace`

- Create a local Tomcat 7 run configuration and run the webapp directory as an exploded war artifact.
- Make sure you turn off any "before launch" options so not to interfere with the maven output.


Building a deployable WAR file & setting up a database
======================================================

- remove the submodule for filters that points to git@git.solidstategroup.com:patient_view_filters.git in the .gitmodules file
- create a <filter>src/main/filters/${env}-filters.properties</filter> for your profile
- install the db using patientview/src/sql/mysql/patientview.sql
- run the maven build command:

`mvn clean package -P<profile-name>`

The war file will be built to the maven target directory.


JPA annotations in Intellij IDEA
================================

It is best to let Maven handle this.  If you have issues with your IDE, you can try:

- From the settings menu, go to Compiler - Annotation Processors
- Enable annotation processing
- Add an annotation processor: org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor
- Turn on processing for the patientview module: generated sources directory name: target/generated-sources
- Make sure you have set the generated-sources directory to be on the classpath for the project


Notes on using Git submodules
=============================

http://chrisjean.com/2009/04/20/git-submodules-adding-using-removing-and-updating/

`git submodule add git@mygithost:billboard lib/billboard`

`git submodule init`

`git submodule update`

I've found it necessary to perform a git pull when in the directory of the submodule to update the submodule files locally.

`git submodule rm lib/billboard`


Notes on using Git submodules in Jenkins
========================================

To update submodules when building setup this execute shell pre step:

`git submodule foreach git checkout master`
`git submodule foreach git pull`


Notes on Multi-specialty
=====================

Logging in
==========

- Users land at www.patientview.org
- Specialty generic landing page invites uses to login
- We will extract user login details out of the user table into a specialty schema
- The spring security success handler will check the user's specialties.  Single specialty users will be forwarded directly to the specialty landing page,  e.g. www.patientview.org/rpv
- Multi-specialty users will hit www.patientview.org/launchpad which will allow them to select a specialty to be redirected to.
- Should the user have no specialty the user will not even be able to log in

Using a specialty
=================

- Once the user arrives at a specialty their session will be noted as associated with that specialty.  We will have new methods to get the current user role for the specialty.
- User can change specialty via the specialty switcher or by logging out.
- Should the user try to manually change their URL to view data from a separate specialty they will either get a not authorised error HTTP 403 (if the user doesn't have a role for that specialty) or they will get a HTTP 404 response.
- All secure links/URLs in the application must be prefixed by the specialty context, e.g. www.patientview.org/renal/patient/patient_details.do
- Each specialty will have a spring security configuration to secure URLs with roles, e.g. /renal/* requires ROLE_RPV_PATIENT or RPV_ROLE_UNITADMIN
- The links in the templates will be directed to the specialty context automatically using PatientViewLinkTag that overrides via `<html:link>`

The specialty servlet filter
============================

- We will implement a custom specialty servlet filter that appears after the spring security filter in the stack
- This will create a virtual specialty context without need for rewriting anything in struts or the JSPs
- The filter will strip off specialty context and forward down the filter chain
- It will NOT check that the specialty requested matches the specialty selected in the user's session to control specialty switching
- This filter has no responsibility for security - that is ALL handled by spring security


Spring security configuration
=============================

- see context-security.xml

Securing features per specialty
===============================

- There will be no security to stop users accessing "hidden" specialty specific features
- Templates will have specialty specific content by extending the PatientViewPresentTag to allow conditional templates e.g. `<logic:present specialty="renal">`

Writing JSP templates
=====================

- CSS, there will be a common base CSS for all specialties, if necessary we can have custom .css files for each specialty.
- The application will not supply different HTML markup per specialty.

Administration Area
====================
- Superadmin users can now be setup per specialty.  A per specialty superadmin implements the "specialityadmin" role described in the spec.