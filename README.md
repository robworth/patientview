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

Processing pull requests 
------------------------------------------------------------
If you plan to implement an improvement; fork the repository, create a feature/XXX branch off develop branch on your local environment, and after your work is done create a pull request to develop branch.

Your feature branch will be tested on a seperate server, code will be reviewed, and if everything looks good, will be merged to develop branch for some further testing.

Develop branch is meant to be bug-free, and be ready for a live build at a short notice.



How to prepare and deploy a new release candidate to staging 
------------------------------------------------------------

Assuming the new release number is 1.2. 

- Create a new branch from develop for the release called "release-1-2" 
- Update the Maven version numbers

```sh
git clone https://github.com/robworth/patientview.git
cd patientview
git checkout -b develop origin/develop
git checkout -b release-1-2
mvn versions:set -DnewVersion=1.2-RELEASE
git commit -a -m 'set release version to 1.2'
git push
```

- Update the Jenkins Staging build configuration branch specifier to 'origin/release-1-0'
- Build and test on staging URLs

How to prepare and deploy a release candidate to production 
-----------------------------------------------------------

- The Jenkins Production build configuration branch specifier will always be 'master'
- Merge release branch to master

```sh
git checkout master
git merge --no-ff release-1-2
git tag -a 1.2
git commit -a -m 'merge release version to 1.2 to master'
git push
```

- Build the Jenkins Production build and manually deploy WAR assets to live server
- Merge any release bug fixes back to develop 

```sh
git checkout develop
git merge --no-ff release-1-2
mvn versions:set -DnewVersion=1.3-SNAPSHOT
git commit -a -m 'merge release version to 1.2 to develop, and bump to next snapshot version'
git push
```

- Delete the feature branch

```sh
git branch -d release-1-2
```
