PatientView & RaDaR Combined Repository
=======================================

This repository contains a multi-module Maven build comprising all the artifacts used for the PatientView and Radar projects.

Repository Structure
--------------------

- **PatientView Parent**: a parent module encompassing all modules in the project
- **PatientView Common**: a module for common code and resources, including a database schema definition
- **PatientView**: a module for the PatientView web application
- **PatientView Monitoring**: a module for a standalone monitoring executable JAR
- **Radar**: a module for the Renal Radar web application

Getting Started
---------------

Each module has a README.md in it's root directory, please refer to them for more information.

To get the code and build the project, running the tests you need Java, Maven and GIT installed then:

```sh
git clone https://github.com/robworth/patientview.git
```

```sh
cd patientview
```

You may need to change your branch at this point, e.g.

```sh
git checkout -b develop origin/develop
```

Then configure your properties file for each module (see module READMEs for details), then:

```sh
mvn clean install -Ptest
```

Creating an Intellij Idea Project
---------------------------------

- Import project from existing model
- Select Maven
- Create a new project with the root diectory set to ~\patientview\patientview-parent, search for projects recursively, import Maven project automatically and create module groups for multi-module Maven projects
- Select the localhost profile

Creating an Intellij Idea Maven install/build all 
-------------------------------------------------

- Add a new Maven run configuration
- Use working directory ~/patientview/patientview-parent, and profile 'localhost'
```sh
clean install -DskipTests
```

How to prepare and deploy a new release 
---------------------------------------

Assuming the new release number is 1.2:

- Create a new branch from develop for the release called "release-1-2" 
```sh
git clone https://github.com/robworth/patientview.git
cd patientview
git checkout -b develop origin/develop
git checkout -b release-1-2
mvn versions:set -DnewVersion=1.2-RELEASE
git commit -a -m 'set release version to 1.2'
git push
```


mvn versions:set -DnewVersion=1.0-RELEASE


