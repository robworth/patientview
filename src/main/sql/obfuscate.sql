/* remove all user date */
DELETE FROM tbl_consultants;
DELETE FROM tbl_adminusers;
DELETE FROM tbl_users;
DELETE FROM letter;
DELETE FROM LOG;
DELETE FROM medicine;
DELETE FROM patient;
DELETE FROM diagnosis;
DELETE FROM tbl_6month;
DELETE FROM tbl_clinicaldata;
DELETE FROM tbl_demographics;
DELETE FROM tbl_diagnosis;
DELETE FROM tbl_hdial;
DELETE FROM tbl_hospitalisation;
DELETE FROM tbl_immunsup_treatment;
DELETE FROM tbl_labdata;
DELETE FROM tbl_pathology;
DELETE FROM tbl_patient_users;
DELETE FROM tbl_pdial;
DELETE FROM tbl_relapse;
DELETE FROM tbl_rrt_hd;
DELETE FROM tbl_rrt_pd;
DELETE FROM tbl_rrt_plasma;
DELETE FROM tbl_rrt_treatment;
DELETE FROM tbl_therapy;
DELETE FROM tbl_transplant;
DELETE FROM testresult;
DELETE FROM treatment;
DELETE FROM uktstatus;
DELETE FROM USER;


/* insert mock users */

insert  into `tbl_adminusers`(`uID`,`uName`,`uEmail`,`uPass`,`uUserName`) values (1,'Rob Stark','admin@radar.com','Ï|o}äŒ“ê¡÷	)Q°o','±›◊¯{èÊŸ ◊G\'*äE');

insert  into `tbl_consultants`(`cID`,`cSNAME`,`cFNAME`,`cCentre`) values (9,'Thorne','Alliser',5),(11,'Stark','Arya',9),(12,'BOOTH','Dr Caroline',8),(13,'Greyjoy','Balon',10),(15,'Selmy','Barristan',9),(21,'Stark','Benjen',12),(25,'Stark','Catelyn',11),(26,'Stark','Bran',3),(29,'Lannister','Cersei',3),(31,'Targaryen','Daenerys',12),(32,'Stark','Eddard',6),(33,'Clegane','Gregor',6),(34,'Lannister','Jaime',6),(37,'Mormont','Jeor',13),(41,'H‚Äôghar','Jaqen',13),(43,'Baratheon','Joffrey',4),(44,'Snow','Jon',7),(45,'Mormont','Jorah',9),(48,'Drogo','Khal',5),(49,'Baelish','Petyr',2),(50,'Baratheon','Renly',12),(51,'Stark','Robb',3),(56,'Baratheon','Robert',11),(57,'Clegane','Sandor',7),(59,'Stark','Sansa',7),(61,'Baratheon','Stannis',2),(62,'Greyjoy','Theon',9),(63,'Lannister','Tyrion',8),(65,'Lannister','Tywin',4),(66,'Targaryen','Viserys',11),(67,'Frey','Walder',9);

insert  into `tbl_demographics`(`RADAR_NO`,`RR_NO`,`DATE_REG`,`NHS_NO`,`HOSP_NO`,`UKT_NO`,`CHI_NO`,`SNAME`,`SNAME_ALIAS`,`FNAME`,`DOB`,`AGE`,`SEX`,`ETHNIC_GP`,`ADD1`,`ADD2`,`ADD3`,`ADD4`,`POSTCODE`,`POSTCODE_OLD`,`CONSENT`,`DATE_BAPN_REG`,`CONS_NEPH`,`RENAL_UNIT`,`RENAL_UNIT_2`,`STATUS`,`RDG`,`emailAddress`,`phone1`,`phone2`,`mobile`,`RRT_modality`,`genericDiagnosis`,`dateOfGenericDiagnosis`,`otherClinicianAndContactInfo`,`comments`,`republicOfIrelandId`,`isleOfManId`,`channelIslandsId`,`indiaId`,`generic`) values (595,NULL,'2012-07-19 00:00:00','ìﬂ®1G]€*t6üoaåÚ®',NULL,NULL,NULL,'Àïà∂íT‘J',NULL,'Û˙\nÎñóSË','PÕ;ﬁôè	tÚß…Lö¡—é',29,1,'9S1..',NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,NULL,NULL,'Alport0',NULL,NULL,NULL,NULL,NULL,'2756','2012-07-12 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,1);

insert  into `tbl_patient_users`(`pID`,`RADAR_NO`,`pUserName`,`pPassWord`,`pDOB`,`pDateReg`) values (45,595,'patient@radar.com','Ï|o}äŒ“ê¡÷	)Q°o','1982-11-02 00:00:00','2012-07-19 00:00:00');

insert  into `tbl_users`(`uID`,`uSurname`,`uForename`,`uTitle`,`uGMC`,`uRole`,`uEmail`,`uPhone`,`uCentre`,`uDateJoin`,`uPass`,`uUserName`) values (28,'Stark','Eddard','Lord','88888888','DBA','superuser@radar.com','123123123',14,'2012-07-19 00:00:00','Ï|o}äŒ“ê¡÷	)Q°o','˚Æ~Èßâä”Ïı≥Ï7®º]\nüE⁄Ë'),(1,'Lannister','Tyrion','Lord','889988','Trainer','professional@radar.com','1231231',15,'2012-07-19 16:41:58','Ï|o}äŒ“ê¡÷	)Q°o','qWT≈—`˚pLÕK41öUÆ ¶\"ú|');