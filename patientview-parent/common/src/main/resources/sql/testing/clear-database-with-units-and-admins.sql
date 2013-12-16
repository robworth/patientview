-- MySQL dump 10.13  Distrib 5.1.69, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: patientview_dev
-- ------------------------------------------------------
-- Server version	5.1.69

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aboutme`
--

DROP TABLE IF EXISTS `aboutme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aboutme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(10) NOT NULL,
  `aboutme` text,
  `talkabout` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=536 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aboutme`
--

LOCK TABLES `aboutme` WRITE;
/*!40000 ALTER TABLE `aboutme` DISABLE KEYS */;
/*!40000 ALTER TABLE `aboutme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centre`
--

DROP TABLE IF EXISTS `centre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `centreCode` varchar(10) NOT NULL DEFAULT '',
  `centreName` varchar(100) DEFAULT '',
  `centreAddress1` varchar(100) DEFAULT '',
  `centreAddress2` varchar(100) DEFAULT '',
  `centreAddress3` varchar(100) DEFAULT '',
  `centreAddress4` varchar(100) DEFAULT '',
  `centrePostCode` varchar(100) DEFAULT '',
  `centreTelephone` varchar(100) DEFAULT '',
  `centreEmail` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `centreCode` (`centreCode`)
) ENGINE=MyISAM AUTO_INCREMENT=576776 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centre`
--

LOCK TABLES `centre` WRITE;
/*!40000 ALTER TABLE `centre` DISABLE KEYS */;
INSERT INTO `centre` VALUES (576657,'2020','Belfast City Hospital','Lisburn Road','Belfast','Co. Antrim',NULL,'BT9 7AB','028 90329241','Hospital@bch.n-i.nhs.uk'),(576658,'RAE05','St. James\'s University Hospital','Renal Unit','Beckett Street','Leeds','North Yorks','LS9 7TF','0113 206 4600','renal@leedsth.nhs.uk'),(576659,'45020','Altnagelvin Area Hospital','Glenshane Road','Londonderry','Co. Londonderry',NULL,'BT47 6SB','028 71345171','Altnagelvin@westerntrust.hscni.net'),(576660,'RAZ','St Helier Hospital','Renal Unit','Wrythe Lane','Carshalton',NULL,'SM5 1AA','020 8296 2000','enquiries@epsom-sthelier.nhs.uk'),(576664,'RCJAT','James Cook University Hospital','Renal Unit','Marton Road','Middlesbrough','Cleveland','TS4 3BW','01642 854617','beverley.smith@stees.nhs.uk'),(576662,'RAQ01','Lister Hospital','Department of Renal Medicine','Coreys Mill Lane','Stevenage','Hertfordshire','SG1 4AB','01438 781230',NULL),(576665,'REF12','Royal Cornwall Hospital','Truro','Cornwall',NULL,NULL,'TR1 3LJ','01872 250000','donna.steward@cornwall.nhs.uk'),(576671,'RGU01','Brighton',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576701,'RHU02','Wessex Renal and Transplant Service Portsmouth','Wessex Renal and Transplant Service','Queen Alexandra Hospital','Portsmouth','Hants','PO6 3LY','02392 286346','renalpatientview@porthosp.nhs.uk'),(576702,'RJ100','Renal Patient View','Renal Offices','6th Floor, Borough Wing','Guy\'s Hospital','London','SE1 9RT','020 7188 7635','rpvadministrator@gstt.nhs.uk'),(576703,'RJZ','King\'s College Hospital','Renal Admin','King\'s College Hospital','London',NULL,'SE5 9RS','020 3299 6233','kch-tr.renal@nhs.net'),(576707,'RK7CC','Sheffield Kidney Institute','Northern General Hospital','Sorby OPD','Herries Road','Sheffield','S5 7AU','0114 2269201',NULL),(576708,'RKB01','UHCW renal unit','University Hospital','Clifford Bridge Road','Walsgrave','Coventry','CV2 2DX','02476 964000',NULL),(576710,'RLNGH','City Hospitals Sunderland',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576713,'RM102','Norfolk and Norwich University Hospital NHS Trust','Colney Lane','Norwich',NULL,NULL,'NR4 7UY','01603 286286','hospital@nnuh.nhs.uk'),(576726,'RM574','Prestwich','BURY NEW ROAD',NULL,'PRESTWICH',NULL,NULL,NULL,NULL),(576731,'RMF01','Royal Preston Hospital','Renal Unit','Sharoe Green Road','Fulwood','Preston','PR2 9HT','01772-716565',NULL),(576732,'RQR00','St. James\'s University Hospital','Renal Unit','Beckett Street','Leeds','North Yorks','LS9 7TF','0113 206 4600','renal@leedsth.nhs.uk'),(576741,'RRK02','UHB NHS Foundation Trust','Dept of Nephrology and Transplantation','Queen Elizabeth Hospital','Edgbaston','Birmingham','B15  2TH','0121 472 1311, ext 3170','bindu.gohil@uhb.nhs.uk'),(576743,'RSC02','Royal Infirmary Edinburgh','Renal Unit','51 Little France Crescent','Old Dalkeith Road','Edinburgh','EH16 4SA','0131 536 1000',NULL),(576745,'RTD01','FRH','Freeman Road',NULL,'Newcastle upon Tyne',NULL,'NE7 7DN','0191 2336161',NULL),(576754,'RX1CC','Nottingham University Hospitals NHS Trust','Renal Unit','Hucknall Road','Nottingham','Notts','NG5 1PB','0115 969 1169',NULL),(576758,'SAC02','Cross House Hospital','Renal Unit','14 Lister Street','Kilmarnock','Ayrshire','KA2 0BE','01563 521133',NULL),(576759,'SFC01','Queen Margaret Hospital','Renal Unit','Whitefield Road','Dunfermline',NULL,'KY12 0SU','01383 623623',NULL),(576761,'SGC04','Glasgow Western Infirmary','Renal Unit','Dumbarton Road','Glasgow','Scotland','G11 6NT','0141 211 2000','renal@northglasgow.scot.nhs.uk'),(576764,'SGC05','Royal Infirmary Glasgow','Renal Unit','3rd Floor Walton Building','Glasgow','Scotland','G4 0SF','0141 211 4000','renal@northglasgow.scot.nhs.uk'),(576768,'SYC01','Dumfries and Galloway NHS Trust','Renal Unit','Bankend Road','Dumfries',NULL,'DG1 4AP','01387 241657',NULL),(576767,'SNC01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576771,'RM301','Salford Royal NHS Trust','Hope Hospital','Stott Lane','Salford','Salford','M6 6HD','0161 206 4023',NULL),(576770,'A','Centre Name','Centre Address 1','Centre Address 2','Centre Address 3','Centre Address 4','Centre Postcode','020 22222 2222','centre@email.com'),(576772,'DUMMY','Dummy County Hospital','Hospital Road','dummton','Co. Tyrone',NULL,'BT79 0NS','345348735','dummy@example.com'),(576775,'RENALB','Example Unit A','Hospital A','A Road','Ailston','Ambry','AA4 1AA','01234 567 789','arenalunit@example.com');
/*!40000 ALTER TABLE `centre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nhsno` varchar(10) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=814 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `started` datetime NOT NULL,
  `participant1_id` bigint(20) NOT NULL,
  `participant2_id` bigint(20) NOT NULL,
  `subject` text NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `groupEnum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `diagnosis` varchar(200) DEFAULT '',
  `displayorder` int(3) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=22365840 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnostic`
--

DROP TABLE IF EXISTS `diagnostic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `diagnostic_type_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnostic`
--

LOCK TABLES `diagnostic` WRITE;
/*!40000 ALTER TABLE `diagnostic` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnostic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edtacode`
--

DROP TABLE IF EXISTS `edtacode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edtacode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `edtaCode` varchar(100) NOT NULL DEFAULT '',
  `linkType` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT '',
  `medicalLink01` varchar(100) DEFAULT '',
  `medicalLink02` varchar(100) DEFAULT '',
  `medicalLink03` varchar(100) DEFAULT '',
  `medicalLink04` varchar(100) DEFAULT '',
  `medicalLink05` varchar(100) DEFAULT '',
  `medicalLink06` varchar(100) DEFAULT '',
  `medicalLinkText01` varchar(100) DEFAULT '',
  `medicalLinkText02` varchar(100) DEFAULT '',
  `medicalLinkText03` varchar(100) DEFAULT '',
  `medicalLinkText04` varchar(100) DEFAULT '',
  `medicalLinkText05` varchar(100) DEFAULT '',
  `medicalLinkText06` varchar(100) DEFAULT '',
  `patientLink01` varchar(100) DEFAULT '',
  `patientLink02` varchar(100) DEFAULT '',
  `patientLink03` varchar(100) DEFAULT '',
  `patientLink04` varchar(100) DEFAULT '',
  `patientLink05` varchar(100) DEFAULT '',
  `patientLink06` varchar(100) DEFAULT '',
  `patientLinkText01` varchar(100) DEFAULT '',
  `patientLinkText02` varchar(100) DEFAULT '',
  `patientLinkText03` varchar(100) DEFAULT '',
  `patientLinkText04` varchar(100) DEFAULT '',
  `patientLinkText05` varchar(100) DEFAULT '',
  `patientLinkText06` varchar(100) DEFAULT '',
  `specialty_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `edtaCode` (`edtaCode`)
) ENGINE=MyISAM AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edtacode`
--

LOCK TABLES `edtacode` WRITE;
/*!40000 ALTER TABLE `edtacode` DISABLE KEYS */;
INSERT INTO `edtacode` VALUES (1,'41','edtaCode','Adult polycystic kidney disease (PKD)','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/polycystic-kidneys-pkd.php','http://www.pkdcharity.org.uk/adpkd.html','http://www.kidney.org.uk/Medical-Info/kidney-disease/pckd.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','Adult PKD (long version) (EdREN)','Adult PKD (PKD Charity)','Adult PKD (NKF)','Chronic renal failure (EdREN)','','',1),(2,'80','edtaCode','Diabetic Nephropathy','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','','','','','Chronic renal failure (EdREN textbook)','Blood pressure and renal disease (EdREN Handbook)','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/diabetes.html','http://www.edren.org/pages/edreninfo/diabetic-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','Diabetes and the kidney (NKF)','Diabetic kidney disease (EdREN)','Chronic renal failure (EdREN)','','','',1),(3,'00','edtaCode','Chronic renal failure, cause uncertain','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','','','','','Chronic renal failure (EdREN Textbook)','Blood pressure and renal disease (EdREN Handbook)','','','','','http://www.renal.org/rixg/nodiagnosis.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','No diagnosis stated (RIXG)','Chronic Renal Failure (EdREN)','','','','',1),(4,'82','edtaCode','Myeloma kidney (or light chain disease)','','','','','','','','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/myel.html','','','','','','Kidney disease in myeloma','','','','','',1),(5,'12','edtaCode','IgA nephropathy','http://www.igan-world.org/infoprof.htm','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','IgA nephropathy for professionals (IgAN Network)','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN Textbook)','','','','http://www.igan-world.org/infopatients.htm','http://www.kidney.org.uk/Medical-Info/kidney-disease/Iga.html','http://www.edren.org/pages/edreninfo/iga-nephropathy.php','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','IgA nephropathy for patients (IgAN Network)','IgA nephropathy (NKF)','IgA nephropathy (EdREN)','Blood pressure and kidney disease (EdREN)','Chronic renal failure (EdREN)','',1),(6,'HD','treatment','Haemodialysis','http://www.edrep.org/pages/textbook/haemodialysis.php','http://www.edren.org/pages/handbooks/unit-handbook/haemodialysis.php','','','','','Haemodialysis for the non-specialist','Haemodialysis for the renal unit SHO','','','','','http://www.kidney.org.uk/Medical-Info/haemodialysis.html','http://www.kidneypatientguide.org.uk/site/HD.html','http://www.edren.org/pages/edreninfo/haemodialysis-hemodialysis.php','http://www.edren.org/pages/edreninfo/diet-in-renal-disease.php','','','Info on haemodialysis from the NKF','From the Kidney Patient Guide, with animations','Info on haemodialysis from EdREN','Diet for people with kidney disease','','',1),(7,'PD','treatment','Peritoneal dialysis','http://www.edrep.org/pages/textbook/peritoneal-dialysis.php','http://www.edren.org/pages/handbooks/unit-handbook/peritoneal-dialysis.php','','','','','PD for the non-specialist','Peritoneal dialysis for the renal unit SHO','','','','','http://www.kidney.org.uk/Medical-Info/pd.html','http://www.kidneypatientguide.org.uk/site/pd.html','http://www.edren.org/pages/edreninfo/peritoneal-dialysis.php','http://www.edren.org/pages/edreninfo/diet-in-renal-disease.php','','','Info on peritoneal dialysis from the NKF','From the Kidney Patient Guide, with animations','From EdREN','Diet for people with kidney disease','','',1),(8,'TP','treatment','Kidney transplant','http://www.edrep.org/pages/textbook/transplantation.php','http://www.edren.org/pages/handbooks/transplant-handbook.php','','','','','Renal transplantation for the non-specialist','Edinburgh Transplant Unit Handbook','','','','','http://www.kidney.org.uk/Medical-Info/transplant.html','http://www.kidneypatientguide.org.uk/site/transplants.html','http://www.edren.org/pages/unit/transplant-unit/about-transplantation.php','http://www.edren.org/pages/edreninfo/diet-in-renal-disease.php','','','Info on kidney transplants','From the Kidney Patient Guide, with animations','From EdREN','Diet for people with kidney disease','','',1),(9,'GEN','treatment','General nephrology (not dialysis, not transplant)','http://www.renal.org/pages/pages/other-info/ckd/hypertension.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/ckd-1-3.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','Blood pressure in renal disease (UK CKD eGuide)','Blood pressure in renal disease (Edren handbook)','CKD stages 1-3 ','CRF (CKD stage 4-5)','','','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://www.edren.org/pages/edreninfo/diet-in-renal-disease.php','http://sdm.rightcare.nhs.uk/pda/','','','High blood pressure and kidney disease','Chronic kidney disease: preventing it from getting worse','Diet for people with kidney disease','Decision aids to help with planning for end stage (established) kidney failure','','',1),(10,'static','static','Further information','http://renux.dmed.ed.ac.uk/EdREN/EdRenHDBKhome.html','http://renux.dmed.ed.ac.uk/EdREN/EdRenHDBKhome.html#anchor340890','','','','','Edinburgh Renal Unit handbook','Edinburgh Transplant Unit handbook','','','','','http://www.kidney.org.uk/Medical-Info/index.html','http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/longlist.html','http://kidney.niddk.nih.gov/kudiseases/a-z.asp','https://www.renalpatientview.org/infoLinks.do','','','Kidney info from the NKF','Kidney words explained (EdREN)','Kidney and urologic diseases from the NIDDK','See all our info links (on all diseases and treatments)','','',1),(11,'14','edtaCode','Membranous nephropathy','http://www.edren.org/pages/handbooks/unit-handbook/nephrotic-syndrome.php','http://www.edren.org/pages/handbooks/unit-handbook/proteinuria-in-renal-disease.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','Nephrotic syndrome (EdREN Handbook)','Proteinuria (EdREN Handbook)','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN Textbook)','','','http://www.edren.org/pages/edreninfo/membranous-nephropathy.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Membranous nephropathy (EdREN)','Chronic renal failure (EdREN)','','','','',1),(12,'10','edtaCode','Glomerulonephritis','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN Textbook)','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/glom.html','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://kidney.niddk.nih.gov/kudiseases/pubs/glomerular/index.htm','','','Glomerulonephritis (NKF)','Blood pressure and kidney disease (EdREN)','Chronic renal failure (EdREN)','Glomerular diseases (NIDDK)','','',1),(13,'11','edtaCode','FSGS with nephrotic syndrome','http://www.edren.org/pages/handbooks/unit-handbook/nephrotic-syndrome.php','http://www.edren.org/pages/handbooks/unit-handbook/proteinuria-in-renal-disease.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','Nephrotic syndrome (EdREN Handbook)','Proteinuria (EdREN Handbook)','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN Textbook)','','','http://www.edren.org/pages/edreninfo/fsgs.php','http://www.kidney.org.uk/Medical-Info/kidney-disease/fsgs.html','http://www.edren.org/pages/edreninfo/nephrotic-syndrome.php','http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_adult.html','http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_child.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','FSGS (EdREN)','FSGS (NKF)','Nephrotic syndrome (EdREN)','Nephrotic syndrome in adults (NKF)','Nephrotic syndrome in children (NKF)','Chronic renal failure (EdREN)',1),(14,'13','edtaCode','Dense deposit disease','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN Textbook)','','','','','','','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','','','','    (required)','Chronic renal failure (EdREN)','Blood pressure and kidney disease (EdREN)','','','',1),(15,'15','edtaCode','MPGN type 1','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN Textbook)','','','','','','','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','','','','required','Chronic renal failure (EdREN)','Blood pressure and kidney disease (EdREN)','','','',1),(16,'16','edtaCode','Crescentic nephritis','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/crescentic-nephritis-rpgn.php','','','','','','Crescentic nephritis (EdREN)','','','','','',1),(17,'17','edtaCode','FSGS with nephrotic syndrome','http://www.edren.org/pages/handbooks/unit-handbook/nephrotic-syndrome.php','http://www.edren.org/pages/handbooks/unit-handbook/proteinuria-in-renal-disease.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','ephrotic syndrome (EdREN Handbook)                         NEPHROTIC SYNDROME (EdREN Handbook)','Proteinuria (EdREN Handbook)','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN Textbook)','','','http://www.edren.org/pages/edreninfo/fsgs.php','http://www.kidney.org.uk/Medical-Info/kidney-disease/fsgs.html','http://www.edren.org/pages/edreninfo/nephrotic-syndrome.php','http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_adult.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','FSGS (EdREN)                                                         [In adults - see 11 for childre','FSGS (NKF)','Nephrotic syndrome (EdREN)','hrotic syndrome in adults, and in children (NKF)                                 Nephrotic syndrome','Chronic renal failure (EdREN)','',1),(18,'19','edtaCode','Glomerulonephritis','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','Blood pressure in renal disease','Chronic renal failure (EdREN textbook)','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/glom.html','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://kidney.niddk.nih.gov/kudiseases/pubs/glomerular/index.htm','','','Glomerulonephritis (NKF)','Blood pressure and kidney disease (EdREN)','Chronic renal failure (EdREN)','Glomerular diseases (NIDDK)','','',1),(19,'20','edtaCode','Reflux nephropathy','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/obstruction-and-obstructive-nephropathy.php','','','','','','Reflux nephropathy (Edren)','','','','','',1),(20,'21','edtaCode','Obstruction - neurogenic bladder','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/obstruction-and-obstructive-nephropathy.php','','','','','','Obstruction and obstructive nephropathy (EdREN)','','','','','',1),(21,'22','edtaCode','Obstructive nephropathy - congenital','','','','','','','0','','','','','','http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Obstruction.html','http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm','','','','','Obstructive nephropathy (EdREN)\r\n                       Obstructive nephropathy (EdREN)','Congenital kidney diseases (from NephKids)','','','','',1),(22,'23','edtaCode','Obstructive nephropathy - acquired','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN text book)','','','','','','http://www.edren.org/pages/edreninfo/obstruction-and-obstructive-nephropathy.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Obstruction and obstructive nephropathy (EdREN)','Chronic renal failure (EdREN)','','','','',1),(23,'24','edtaCode','Reflux nephropathy','http://www.edrep.org/pages/resources/urinary-tract-infections.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','Reflux nephropathy (EdREN - Education)','Chronic renal failure (EdREN textbook)','','','','','http://www.edren.org/pages/edreninfo/reflux-nephropathy.php','http://www.kidney.org.uk/Medical-Info/reflux/index.html','http://www.edren.org/pages/edreninfo/reflux-nephropathy.php','','','','Reflux nephropathy (EdREN)','Reflux nephropathy (NKF)','Chronic renal failure (EdREN)','','','',1),(24,'29','edtaCode','Reflux nephropathy (pyelonephritis)','http://www.edrep.org/pages/resources/urinary-tract-infections.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','Reflux nephropathy teaching information (EdREN)','Chronic renal failure (EdREN textbook)','','','','','http://www.edren.org/pages/edreninfo/reflux-nephropathy.php','http://www.kidney.org.uk/Medical-Info/reflux/index.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','Reflux nephropathy (EdREN)','Reflux nephropathy (NKF)','Chronic renal failure (EdREN)','','','',1),(25,'30','edtaCode','Interstitial nephritis','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','','','','','','Interstitial nephritis (EdREN)','','','','','',1),(26,'31','edtaCode','Analgesic nephropathy','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Interstitial nephritis (EdREN)','Chronic renal failure (EdREN)','','','','',1),(27,'32','edtaCode','Cisplatin nephropathy','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis/more-info-on-interstitial-nephritis.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Interstitial nephritis (EdREN)','Chronic renal failure (EdREN)','','','','',1),(28,'33','edtaCode','Cyclosporin nephropathy','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Interstitial nephritis (EdREN)','Chronic renal failure (EdREN)','','','','',1),(29,'34','edtaCode','Lead nephropathy','','','','','','','','','','','','','http://www.edrep.org/pages/textbook/interstitial-nephritis.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Interstitial nephritis (EdREN textbook)','Chronic renal failure (EdREN)','','','','',1),(30,'39','edtaCode','Drug-induced interstitial nephropathy','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Interstitial nephritis (EdREN)','Chronic renal failure (EdREN)','','','','',1),(31,'40','edtaCode','Cystic kidney disease (unspecified type)','http://renux.dmed.ed.ac.uk/EdREN/Teachingbits/nonPCKD.html','','','','','','Diseases that may be confused with PKD (from EdREN)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/cysts.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Kidney cysts (NKF)','Chronic renal failure (EdREN)','','','','',1),(32,'42','edtaCode','Polycystic kidney disease (infantile type)','','','','','','','','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/arpkd.html','http://www.pkdcharity.org.uk/arpkd.html','','','','','Infantile PKD (NKF)','ARPKD (PKD Charity)','','','','',1),(33,'43','edtaCode','Medullary cystic disease/nephronophthisis','','','','','','','info required','','','','','','','','','','','','info required','','','','','',1),(34,'49','edtaCode','Cystic kidney disease (other specified type)','','','','','','','info required','','','','','','','','','','','','info required','','','','','',1),(35,'50','edtaCode','Inherited nephropathy (unspecified type)','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Congenital kidney diseases (from NephKids)','Chronic renal failure (EdREN)','','','','',1),(36,'51','edtaCode','Alport syndrome','','','','','','','See patient links','','','','','','http://www.edren.org/pages/edreninfo/alport-syndrome.php','http://www.kidney.org.uk/Medical-Info/alports/index.html','','','','','Alport syndrome (EdREN)','Alport\'s syndrome (NKF)','','','','',1),(37,'52','edtaCode','Cystinosis','http://www.ncbi.nlm.nih.gov/omim/219800','','','','','','Cystinosis (OMIM) -  very long, not very up to date','','','','','','http://www.rarerenal.org/diseases/cystinosis/patient-information/','http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm','http://www.rarerenal.org/diseases/cystinosis','','','','Cystinosis from RareRenal.org','Congenital kidney diseases (from NephKids)','Cystinosis UK Rare Disease Group','','','',1),(38,'53','edtaCode','Primary oxalosis','','','','','','','','','','','','','http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm','http://ghr.nlm.nih.gov/condition=primaryhyperoxaluria','http://www.ohf.org/about_disease.html','','','','Congenital kidney diseases (from NephKids)','Primary Hyperoxaluria (NIH)','Primary Hyperoxaluria (Oxalosis Foundation)','','','',1),(39,'54','edtaCode','Fabry disease','','','','','','','See patients links','','','','','','http://www.kidney.org.uk/Medical-Info/fabry-disease/index.html','','','','','','Fabry disease (NKF)','','','','','',1),(40,'59','edtaCode','Hereditary nephropathy (other specified type)','','','','','','','','','','','','','','','','','','','','','','','','',1),(41,'61','edtaCode','Oligomeganephronic dysplasia','','','','','','','','','','','','','http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm','','','','','','Congenital kidney diseases (from NephKids)','','','','','',1),(42,'63','edtaCode','Congenital renal dysplasia','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html','http://www.edren.org/pages/edreninfo/reflux-nephropathy.php','http://www.kidney.org.uk/Medical-Info/reflux/index.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','Single or small kidney (NKF)','Reflux nephropathy (EdREN)','Reflux nephropathy (NKF)','Chronic renal failure (EdREN)','','',1),(43,'66','edtaCode','Prune belly syndrome','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/obstruction-and-obstructive-nephropathy.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','Obstruction and obstructive nephropathy (EdREN)','Chronic renal failure (EdREN)','','','','',1),(44,'70','edtaCode','Renal artery stenosis (or renovascular disease - type unspecified\')','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/Ras.html','http://www.edren.org/pages/edreninfo/angiography-and-angioplasty.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','Renal artery stenosis (NKF; requires review)','Renal arteriography and angioplasty (EdREN)','Chronic renal failure (EdREN)','','','',1),(45,'71','edtaCode','Nephropathy caused by malignant hypertension','','','','','','','0','','','','','','','','','','','','(needed)','','','','','',1),(46,'72','edtaCode','Hypertensive nephropathy','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','Blood pressure and renal disease (EdREN Handbook)','Chronic renal failure (EdREN textbook)','','','','','','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','0','Blood pressure and kidney disease (EdREN)                         Blood pressure and kidney disease','Chronic renal failure (EdREN)','','','',1),(47,'73','edtaCode','Polyarteritis (vasculitis)','http://www.edren.org/pages/handbooks/unit-handbook/systemic-vasculitis.php','','','','','','Systemic vasculitis (EdREN Handbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html','http://www.edren.org/pages/edreninfo/vasculitis.php','','','','','Vasculitis (NKF)','Vasculitis (EdREN)','','','','',1),(48,'74','edtaCode','Wegener\'s granulomatosis','http://www.edren.org/pages/handbooks/unit-handbook/systemic-vasculitis.php','','','','','','Systemic vasculitis (EdREN Handbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html','http://www.edren.org/pages/edreninfo/vasculitis.php','','','','','Vasculitis (NKF)','Vasculitis (EdREN)','','','','',1),(49,'75','edtaCode','Cholesterol emboli/ischaemic nephropathy','','','','','','','[links as for 89]','','','','','','','','','','','','[links as for 89]','','','','','',1),(50,'76','edtaCode','Glomerulonephritis related to liver cirrhosis','','','','','','','[links as for 89]','','','','','','','','','','','','','','','','','',1),(51,'78','edtaCode','Cryoglobulinaemia','','','','','','','[links as for 89]','','','','','','','','','','','','','','','','','',1),(52,'79','edtaCode','Renal vascular disease (other cause)','','','','','','','[links as for 89]','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/Ras.html','http://www.edren.org/pages/edreninfo/angiography-and-angioplasty.php','','','','','Renal artery stenosis (NKF)','Renal arteriography and angioplasty (EdREN)','','','','',1),(53,'PRE','treatment','General nephrology (not dialysis, not transplant)','http://www.renal.org/pages/pages/other-info/ckd/hypertension.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','http://www.edrep.org/pages/textbook/ckd-1-3.php','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','Blood pressure in renal disease (UK CKD eGuide)','Blood pressure in renal disease (Edren handbook)','CKD stages 1-3 ','CRF (CKD stage 4-5)','','','http://www.edren.org/pages/edreninfo/blood-pressure-and-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','http://www.edren.org/pages/edreninfo/diet-in-renal-disease.php','','','','High blood pressure and kidney disease','Chronic kidney disease: preventing it from getting worse','Diet for people with kidney disease','','','',1),(54,'81','edtaCode','Diabetic nephropathy (II)','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','','','','','','Chronic renal failure (EdREN textbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/diabetes.html','http://www.edren.org/pages/edreninfo/diabetic-kidney-disease.php','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','Diabetes and the kidney (NKF)','Diabetic kidney disease (EdREN)','Chronic renal failure (EdREN)','','','',1),(55,'83','edtaCode','Amyloidosis','','','','','','','See patient links','','','','','','http://www.intelihealth.com/IH/ihtIH/WSIHW000/9339/9444.html','http://kidney.niddk.nih.gov/kudiseases/pubs/amyloidosis/','http://amyloidosis.org/whatisit.asp','http://www.information-on-amyloidosis.com/','','','Amyloidosis from InteliHealth (US; complicated)','NIDDK; Simpler but mentions Primary and Dialysis-related amyloid only','Amyloidosis support network (very comprehensive, but complicated)','From NCERx - again, very detailed, complicated','','',1),(56,'84','edtaCode','SLE (Lupus nephritis)','http://www.edren.org/pages/handbooks/unit-handbook/sle.php','','','','','','Treatment of SLE (EdREN Handbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/lupus.html','http://www.edren.org/pages/edreninfo/sle-lupus.php','','','','','Lupus and lupus kidney disease (NKF)','Lupus nephritis (EdREN)','','','','',1),(57,'85','edtaCode','Henoch-Schonlein purpura','','','','','','','Information source required','','','','','','','','','','','','Info link required','','','','','',1),(58,'86','edtaCode','Goodpasture\'s disease','','','','','','','See patient info','','','','','','http://bit.ly/d3cy1K','http://www.edren.org/pages/edreninfo/goodpastures-anti-gbm-disease.php','','','','','Goodpasture\'s disease (long version) from EdREN','Goodpasture\'s disease (short version) from EdREN','','','','',1),(59,'87','edtaCode','Scleroderma kidney','','','','','','','(Links required)','','','','','','','','','','','','(Links required)','','','','','',1),(60,'88','edtaCode','HUS and TTP','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/hus-and-ttp.php','http://kidney.niddk.nih.gov/kudiseases/pubs/hemolyticuremic/index.htm','','','','','HUS and TTP (EdREN)','HUS in childhood (NIDDK)','','','','',1),(61,'89','edtaCode','Small vessel vasculitis (or other multisystem disease)','http://www.edren.org/pages/handbooks/unit-handbook/systemic-vasculitis.php','','','','','','Systemic vasculitis (EdREN Handbook)','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html','http://www.edren.org/pages/edreninfo/vasculitis.php','','','','','Vasculitis (NKF)','Vasculitis (EdREN)','','','','',1),(62,'90','edtaCode','Cortical necrosis or irreversible acute tubular necrosis','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis/more-info-on-intersitial-nephritis.php','','','','','','Acute tubular necrosis (EdREN)','','','','','',1),(63,'91','edtaCode','Tuberculosis','','','','','','','','','','','','','','','','','','','(Information required)','','','','','',1),(64,'92','edtaCode','Urate nephropathy','','','','','','','','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','','','','','','Interstitial nephritis (EdREN)','','','','','',1),(65,'93','edtaCode','Nephrocalcinosis','','','','','','','(Links required)','','','','','','','','','','','','(Links required)','','','','','',1),(66,'94','edtaCode','Balkan nephropathy','','','','','','','(Links required)','','','','','','http://www.edren.org/pages/edreninfo/interstitial-nephritis.php','','','','','','Interstitial nephritis (EdREN)','','','','','',1),(67,'95','edtaCode','Kidney tumour','','','','','','','','','','','','','http://www.patient.co.uk/showdoc/27000676/','http://www.merckmedicus.com/pp/us/hcp/hcp_patient_resource_allhandouts_content_search.jsp?pg=/ppdocs','http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html','','','','Kidney cancer (Patient UK)','Kidney cancer (Merck)','Single or small kidney (NKF)','','','',1),(68,'96','edtaCode','Loss of kidney through operation or trauma','','','','','','','','','','','','','http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html','http://kidney.niddk.nih.gov/kudiseases/pubs/solitarykidney/index.htm','','','','','Single or small kidney (NKF)','Solitary kidney (NIDDK)','','','','',1),(69,'99','edtaCode','Other kidney disease','http://www.renal.org/rixg/nodiagnosis.html','','','','','','No diagnosis stated (RIXG)','','','','','','http://www.renal.org/rixg/nodiagnosis.html','','','','','','No diagnosis stated (RIXG)','','','','','',1),(70,'DEF','edtaCode','No diagnosis stated','http://www.renal.org/rixg/nodiagnosis.html','','','','','','Why is this message showing?','','','','','','http://www.renal.org/rixg/nodiagnosis.html','','','','','','Why is this message showing?','','','','','',1),(71,'100','edtaCode','Chronic renal failure, cause uncertain','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','','','','','Chronic renal failure (EdREN Textbook)','Blood pressure and renal disease (EdREN Handbook)','','','','','http://www.renal.org/rixg/nodiagnosis.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','No diagnosis stated (RIXG)','Chronic Renal Failure (EdREN)','','','','',1),(72,'0','edtaCode','Chronic renal failure, cause uncertain','http://www.edrep.org/pages/textbook/crf-ckd-4-5.php','http://www.edren.org/pages/handbooks/unit-handbook/blood-pressure-in-renal-disease.php','','','','','Chronic renal failure (EdREN Textbook)','Blood pressure and renal disease (EdREN Handbook)','','','','','http://www.renal.org/rixg/nodiagnosis.html','http://www.edren.org/pages/edreninfo/ckd-chronic-renal-failure-and-its-progression.php','','','','','No diagnosis stated (RIXG)','Chronic Renal Failure (EdREN)','','','','',1),(73,'Primary Diagnosis','myIbdLinks','Primary Diagnosis','','','','','','','','','','','','','','','','','','','','','','','','',2),(74,'Disease Extent','myIbdLinks','Disease Extent','','','','','','','','','','','','','','','','','','','','','','','','',2),(75,'Year of Diagnosis','myIbdLinks','Year of Diagnosis','','','','','','','','','','','','','','','','','','','','','','','','',2),(76,'Complications','myIbdLinks','Complications','','','','','','','','','','','','','','','','','','','','','','','','',2),(77,'Other parts of the body affected','myIbdLinks','Other parts of the body affected','','','','','','','','','','','','','','','','','','','','','','','','',2),(78,'Year for Surveillance Colonoscopy','myIbdLinks','Year for Surveillance Colonoscopy','','','','','','','','','','','','','','','','','','','','','','','','',2),(79,'Named Consultant','myIbdLinks','Named Consultant','','','','','','','','','','','','','','','','','','','','','','','','',2),(80,'Nurses','myIbdLinks','Nurses','','','','','','','','','','','','','','','','','','','','','','','','',2),(81,'Weight','myIbdLinks','Weight','','','','','','','','','','','','','','','','','','','','','','','','',2),(82,'IBD Related Family History','myIbdLinks','IBD Related Family History','','','','','','','','','','','','','','','','','','','','','','','','',2),(83,'Smoking History','myIbdLinks','Smoking History','','','','','','','','','','','','','','','','','','','','','','','','',2),(84,'Surgery History','myIbdLinks','Surgery History','','','','','','','','','','','','','','','','','','','','','','','','',2),(85,'Vaccination History','myIbdLinks','Vaccination History','','','','','','','','','','','','','','','','','','','','','','','','',2),(86,'Overall my condition','careplanLinks','Overall my condition','','','','','','','','','','','','','','','','','','','','','','','','',2),(87,'Tiredness /Fatigue','careplanLinks','Tiredness /Fatigue','','','','','','','','','','','','','','','','','','','','','','','','',2),(88,'Managing Pain','careplanLinks','Managing Pain','','','','','','','','','','','','','','','','','','','','','','','','',2),(89,'Stress and worry','careplanLinks','Stress and worry','','','','','','','','','','','','','','','','','','','','','','','','',2),(90,'Support from family and friends','careplanLinks','Support from family and friends','','','','','','','','','','','','','','','','','','','','','','','','',2),(91,'Managing my social life / hobbies','careplanLinks','Managing my social life / hobbies','','','','','','','','','','','','','','','','','','','','','','','','',2),(92,'Managing work / studies','careplanLinks','Managing work / studies','','','','','','','','','','','','','','','','','','','','','','','','',2),(93,'Taking my medicines regularly','careplanLinks','Taking my medicines regularly','','','','','','','','','','','','','','','','','','','','','','','','',2),(94,'Managing flare ups','careplanLinks','Managing flare ups','','','','','','','','','','','','','','','','','','','','','','','','',2),(95,'Stopping smoking','careplanLinks','Stopping smoking','','','','','','','','','','','','','','','','','','','','','','','','',2),(96,'Sleeping','careplanLinks','Sleeping','','','','','','','','','','','','','','','','','','','','','','','','',2),(97,'Sexual relationships','careplanLinks','Sexual relationships','','','','','','','','','','','','','','','','','','','','','','','','',2),(98,'Fertility / Pregnancy','careplanLinks','Fertility / Pregnancy','','','','','','','','','','','','','','','','','','','','','','','','',2),(99,'Learning about my condition','careplanLinks','Learning about my condition','','','','','','','','','','','','','','','','','','','','','','','','',2),(100,'Eating a healthy diet','careplanLinks','Eating a healthy diet','','','','','','','','','','','','','','','','','','','','','','','','',2),(101,'Travelling','careplanLinks','Travelling','','','','','','','','','','','','','','','','','','','','','','','','',2);
/*!40000 ALTER TABLE `edtacode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailverification`
--

DROP TABLE IF EXISTS `emailverification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailverification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `verificationcode` varchar(50) NOT NULL,
  `expirydatestamp` datetime NOT NULL,
  `lastverificationdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5557 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailverification`
--

LOCK TABLES `emailverification` WRITE;
/*!40000 ALTER TABLE `emailverification` DISABLE KEYS */;
INSERT INTO `emailverification` VALUES (5545,'renala-unitadmin1','renala-unitadmin1@solidstategroup.com','04iYM5bhIcTxTnuufIbeJJ4kur9HELsEBdXk0O3K9wtUJiw4u3','2013-12-05 12:10:31','2013-11-21 12:10:31'),(5546,'renalb-unitadmin1','renalb-unitadmin1@solidstategroup.com','NRMgFYmPUztCoHwGVGAXdsHmp70yI86u70zEquvKjsXlfgkBgc','2013-12-05 12:11:30','2013-11-21 12:11:30'),(5547,'renalc-unitadmin1','renalc-unitadmin1@solidstategroup.com','oIXbNZ61tuzcXU3vx9wr05VEe9jmqcMEG2hcGaF08teCr43GSh','2013-12-05 12:12:04','2013-11-21 12:12:04'),(5548,'renala-renalb-unitadmin','renala-renalb-unitadmin@solidstategroup.com','qBfRhlcGTQLw7veAinjI4HdXyeKYuQd1cAYYrQcrB6maMhtyJi','2013-12-05 12:16:43','2013-11-21 12:16:43'),(5549,'srns-groupadmin1','srns-groupadmin1@solidstategroup.com','e5JCMVXGLE5QI51b2N3TeZm3UW4mRopIBIpb5kNV8znqPe1QXu','2013-12-05 12:26:51','2013-11-21 12:26:51'),(5550,'mpgn-groupadmin1','mpgn-groupadmin1@solidstategroup.com','kx78Dq11k9JKjVanpwgV3WkI5YYo2BSdlNkD0tcDRWoxUBiOmu','2013-12-05 12:27:31','2013-11-21 12:27:31'),(5551,'alport-groupadmin1','alport-groupadmin1@solidstategroup.com','zVYrD7oAeDfB36GrNwaEEBw88Q1cj6obAmXypAo7yKJUaZ0bhj','2013-12-05 12:27:55','2013-11-21 12:27:55'),(5552,'hnf1b-groupadmin1','hnf1b-groupadmin1@solidstategroup.com','pKabjSTZEDgk3dxkqs90kY6jgCN9CcfOKkcSZj42HNa3cEF0Yc','2013-12-05 12:28:21','2013-11-21 12:28:21'),(5553,'gen1-groupadmin1','gen1-groupadmin1@solidstategroup.com','uVIrqKBtG7JpiUFi1JgQR1uMBevWBv3kiHk8GqriWuSxvaVsau','2013-12-05 12:28:55','2013-11-21 12:28:55'),(5554,'gen2-groupadmin1','gen2-groupadmin1@solidstategroup.com','KGSDIZE8S6KGlArnPrhO7XCrm7ykF817Txc9MXBcNngNmVbMsn','2013-12-05 12:29:51','2013-11-21 12:29:51'),(5555,'srns-gen1-groupadmin','srns-gen1-groupadmin@solidstategroup.com','Etr5eSnxzwQ7P7I7QgxbObNWLVhSaiA3KX7UKN1tCzTjRuD7IV','2013-12-05 12:32:32','2013-11-21 12:32:32'),(5556,'renala-alport-unitadmin','renala-alport-unitadmin@solidstategroup.com','QLaJT2BKrWW1deAoIrNuQyO6V4gpFtKEiOOa8HDd2a05fTLA1k','2013-12-05 12:37:03','2013-11-21 12:37:03');
/*!40000 ALTER TABLE `emailverification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `datestamp` datetime NOT NULL,
  `comment` text NOT NULL,
  `commentedited` text,
  `anonymous` tinyint(1) NOT NULL DEFAULT '1',
  `makepublic` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_careplan`
--

DROP TABLE IF EXISTS `ibd_careplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_careplan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barriers` text,
  `confidence_id` bigint(20) NOT NULL,
  `eatingAHealthyDietScore` int(11) DEFAULT NULL,
  `fertilityPregnancyScore` int(11) DEFAULT NULL,
  `goalToAchieve` text,
  `goals` text,
  `howToAchieveGoal` text,
  `importance_id` bigint(20) NOT NULL,
  `learningAboutMyConditionScore` int(11) DEFAULT NULL,
  `managingFlareUpsScore` int(11) DEFAULT NULL,
  `managingMySocialLifeHobbiesScore` int(11) DEFAULT NULL,
  `managingPainScore` int(11) DEFAULT NULL,
  `managingWorkStudiesScore` int(11) DEFAULT NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherAreasToDiscuss` text,
  `overallMyConditionScore` int(11) DEFAULT NULL,
  `reviewDate` datetime DEFAULT NULL,
  `sexualRelationshipsScore` int(11) DEFAULT NULL,
  `sleepingScore` int(11) DEFAULT NULL,
  `stoppingSmokingScore` int(11) DEFAULT NULL,
  `stressAndWorryScore` int(11) DEFAULT NULL,
  `supportFromFamilyAndFriendsScore` int(11) DEFAULT NULL,
  `takingMyMedicinesRegularlyScore` int(11) DEFAULT NULL,
  `tirednessFatigueScore` int(11) DEFAULT NULL,
  `travellingScore` int(11) DEFAULT NULL,
  `whatCanBeDone` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_careplan`
--

LOCK TABLES `ibd_careplan` WRITE;
/*!40000 ALTER TABLE `ibd_careplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_careplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_colitis_symptoms`
--

DROP TABLE IF EXISTS `ibd_colitis_symptoms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_colitis_symptoms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `complication_id` int(11) NOT NULL,
  `number_of_stools_daytime_id` int(11) NOT NULL,
  `number_of_stools_nighttime_id` int(11) NOT NULL,
  `present_blood_id` int(11) NOT NULL,
  `toilet_timing_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_colitis_symptoms`
--

LOCK TABLES `ibd_colitis_symptoms` WRITE;
/*!40000 ALTER TABLE `ibd_colitis_symptoms` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_colitis_symptoms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_crohns_symptoms`
--

DROP TABLE IF EXISTS `ibd_crohns_symptoms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_crohns_symptoms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `abdominal_pain_id` int(11) NOT NULL,
  `complication_id` int(11) NOT NULL,
  `mass_in_tummy_id` int(11) NOT NULL,
  `openBowels` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_crohns_symptoms`
--

LOCK TABLES `ibd_crohns_symptoms` WRITE;
/*!40000 ALTER TABLE `ibd_crohns_symptoms` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_crohns_symptoms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_medication`
--

DROP TABLE IF EXISTS `ibd_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_medication`
--

LOCK TABLES `ibd_medication` WRITE;
/*!40000 ALTER TABLE `ibd_medication` DISABLE KEYS */;
INSERT INTO `ibd_medication` VALUES (1,'Asacol MR 400 mg tablet'),(2,'Asacol MR 800 mg tablet'),(14,'Balsazide (Colazide) 750 mg capsule'),(11,'Ipocol MR 400 mg tablet'),(12,'Mesren MR 400 mg tablet'),(10,'Mezavant XL 1.2 G tablet'),(13,'Octasa  MR 800 mg tablet'),(15,'Olsalazine (Dipentum) 250 mg capsule'),(16,'Olsalazine (Dipentum) 500 mg tablet'),(3,'Pentasa 1 G granules'),(4,'Pentasa 2 G granules'),(8,'Salofalk 1.5 G granules'),(5,'Salofalk 250 mg tablet'),(9,'Salofalk 3 G granules'),(7,'Salofalk 500 mg granules'),(6,'Salofalk 500 mg tablet'),(18,'Sulfasalazine (Salazopyrin) 250 mg/5ml suspension'),(17,'Sulfasalazine (Salazopyrin) 500 mg tablet'),(20,'Aminosalicylate / 5ASA Foam Enemas (Asacol / Salofalk)'),(21,'Aminosalicylate / 5ASA Liquid Enemas (Pentasa / Salofalk)'),(19,'Aminosalicylate / 5ASA Suppositories (Asacol / Pentasa / Salofalk)'),(23,'Steroid Foam Enemas (Colifoam/ Prednisolone /Budenofalk)'),(24,'Steroid Liquid Enemas (Predenema/ Predsol)'),(22,'Steroid suppositories (Predsol)'),(26,'Budenofalk (Budesonide)'),(28,'Clipper (beclamethasone)'),(27,'Entocort (Budesonide)'),(25,'Prednisolone'),(29,'Azathioprine'),(30,'Mercaptopurine'),(31,'Methotrexate'),(33,'Adalimumab (Humira)'),(32,'Infliximab (Remicade)'),(38,'Anit-diarrhoeals'),(36,'Anti-acid'),(34,'Iron'),(35,'Painkillers (Paracetamol, Codeine, Co-codamol)'),(37,'Vitamins & minerals');
/*!40000 ALTER TABLE `ibd_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_medication_allowed_dosages`
--

DROP TABLE IF EXISTS `ibd_medication_allowed_dosages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_allowed_dosages` (
  `medication_id` bigint(20) NOT NULL,
  `dose_id` bigint(20) NOT NULL,
  KEY `FKBA6261E967F0EF1` (`dose_id`),
  KEY `FKBA6261E999BAAE42` (`medication_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_medication_allowed_dosages`
--

LOCK TABLES `ibd_medication_allowed_dosages` WRITE;
/*!40000 ALTER TABLE `ibd_medication_allowed_dosages` DISABLE KEYS */;
INSERT INTO `ibd_medication_allowed_dosages` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,8),(2,9),(2,10),(2,11),(2,12),(14,59),(14,60),(11,40),(11,41),(11,42),(11,43),(11,44),(11,45),(11,46),(12,47),(12,48),(12,49),(12,50),(12,51),(12,52),(12,53),(10,36),(10,37),(10,38),(10,39),(13,54),(13,55),(13,56),(13,57),(13,58),(15,61),(15,62),(15,63),(15,64),(15,65),(15,66),(16,67),(16,68),(16,69),(16,70),(16,71),(16,72),(3,13),(3,14),(3,15),(3,16),(4,17),(4,18),(8,33),(8,34),(5,19),(5,20),(9,35),(7,27),(7,28),(7,29),(7,30),(7,31),(7,32),(6,21),(6,22),(6,23),(6,24),(6,25),(6,26),(18,75),(18,76),(17,73),(17,74),(20,80),(20,81),(21,82),(21,83),(19,77),(19,78),(19,79),(23,87),(23,86),(23,85),(24,88),(24,89),(24,90),(22,84),(26,100),(28,102),(27,101),(25,95),(25,96),(25,97),(25,98),(25,93),(25,94),(25,99),(25,91),(25,92),(29,103),(29,104),(29,105),(29,106),(29,107),(29,108),(29,109),(29,110),(29,111),(29,112),(30,113),(30,114),(30,115),(30,116),(30,117),(30,118),(30,119),(30,120),(31,121),(31,122),(31,123),(31,124),(31,125),(31,126),(31,127),(31,128),(31,129),(33,132),(33,133),(32,130),(32,131),(38,167),(38,168),(38,169),(38,170),(38,171),(38,172),(36,152),(36,155),(36,153),(36,157),(36,159),(36,156),(36,154),(36,158),(36,160),(36,150),(36,151),(34,140),(34,135),(34,134),(34,138),(34,141),(34,139),(34,137),(34,136),(35,146),(35,147),(35,144),(35,145),(35,148),(35,149),(35,142),(35,143),(37,161),(37,162),(37,163),(37,164),(37,165),(37,166);
/*!40000 ALTER TABLE `ibd_medication_allowed_dosages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_medication_dose`
--

DROP TABLE IF EXISTS `ibd_medication_dose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_dose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `extraInformation` varchar(255) DEFAULT NULL,
  `mg` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=173 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_medication_dose`
--

LOCK TABLES `ibd_medication_dose` WRITE;
/*!40000 ALTER TABLE `ibd_medication_dose` DISABLE KEYS */;
INSERT INTO `ibd_medication_dose` VALUES (1,NULL,400),(2,NULL,800),(3,NULL,1200),(4,NULL,1600),(5,NULL,2400),(6,NULL,3200),(7,NULL,4800),(8,NULL,800),(9,NULL,1600),(10,NULL,2400),(11,NULL,3200),(12,NULL,4800),(59,NULL,1500),(60,NULL,2250),(40,NULL,400),(41,NULL,800),(42,NULL,1200),(43,NULL,1600),(44,NULL,2400),(45,NULL,3200),(46,NULL,4800),(47,NULL,400),(48,NULL,800),(49,NULL,1200),(50,NULL,1600),(51,NULL,2400),(52,NULL,3200),(53,NULL,4800),(36,NULL,1200),(37,NULL,2400),(38,NULL,3200),(39,NULL,4800),(54,NULL,800),(55,NULL,1600),(56,NULL,2400),(57,NULL,3200),(58,NULL,4800),(61,NULL,500),(62,NULL,1000),(63,NULL,1500),(64,NULL,2000),(65,NULL,2500),(66,NULL,3000),(67,NULL,500),(68,NULL,1000),(69,NULL,1500),(70,NULL,2000),(71,NULL,2500),(72,NULL,3000),(13,NULL,1000),(14,NULL,2000),(15,NULL,3000),(16,NULL,4000),(17,NULL,2000),(18,NULL,4000),(33,NULL,1500),(34,NULL,3000),(19,NULL,500),(20,NULL,1000),(35,NULL,3000),(27,NULL,500),(28,NULL,1000),(29,NULL,1500),(30,NULL,2000),(31,NULL,2500),(32,NULL,3000),(21,NULL,500),(22,NULL,1000),(23,NULL,1500),(24,NULL,2000),(25,NULL,2500),(26,NULL,3000),(75,NULL,500),(76,NULL,1000),(73,NULL,500),(74,NULL,1000),(80,'Asacol foam enema',1000),(81,'Salofalk foam enema',1000),(82,'Pentasa liquid enema',1000),(83,'Salofalk liquid enema',2000),(77,'Asacol rectal suppositories',250),(78,'Asacol rectal suppositories',500),(79,'Pentasa rectal suppositories',1000),(87,'Budenofalk foam enema',2),(86,'Prednisolone foam enema',20),(85,'Colifoam foam enema',125),(88,'Predenema liquid enema',0),(89,'Predsol liquid enema',0),(90,'Entocort liquid enema',2),(84,'Predsol rectal suppositories',5),(100,NULL,3),(102,NULL,5),(101,NULL,3),(95,'maintenance dose',5),(96,'maintenance dose',10),(97,'maintenance dose',15),(98,'maintenance dose',20),(93,'reducing by 5mg per week',30),(94,'reducing by 5mg per fortnight',30),(99,'maintenance dose',30),(91,'reducing by 5mg per week',40),(92,'reducing by 5mg per fortnight',40),(103,NULL,25),(104,NULL,50),(105,NULL,75),(106,NULL,100),(107,NULL,125),(108,NULL,150),(109,NULL,175),(110,NULL,200),(111,NULL,225),(112,NULL,250),(113,NULL,12.5),(114,NULL,25),(115,NULL,50),(116,NULL,62.5),(117,NULL,75),(118,NULL,100),(119,NULL,125),(120,NULL,150),(121,NULL,2.5),(122,NULL,5),(123,NULL,7.5),(124,NULL,10),(125,NULL,12.5),(126,NULL,15),(127,NULL,17.5),(128,NULL,20),(129,NULL,25),(132,NULL,40),(133,NULL,80),(130,'/kg infusion',5),(131,'/kg infusion',10),(167,'Loperamide',2),(168,'Loperamide',4),(169,'Loperamide',6),(170,'Loperamide',8),(171,'Loperamide',10),(172,'Loperamide',12),(152,'Omeprazole',10),(155,'Lansoprazole',15),(153,'Omeprazole',20),(157,'Pantoprazole',20),(159,'Eso-ompreazole (Nexium)',20),(156,'Lansoprazole',30),(154,'Omeprazole',40),(158,'Pantoprazole',40),(160,'Eso-ompreazole (Nexium)',40),(150,'Ranitidine',150),(151,'Ranitine',300),(140,'/5ml Ferrous fumarate (Galfer) syrup',140),(135,'Ferrous sulphate MR (Feospan) capsules',150),(134,'Ferrous sulphate tablets',200),(138,'Ferrous fumarate (Fersamal) tablets',210),(141,'Ferrous gluconate tablets',300),(139,'Ferrous fumarate (Galfer) capsules',305),(137,'Ferrous fumarate (Fersaday) tablets',322),(136,'Ferrous sulphate MR (Ferrograd) tablets',325),(146,'Co-codamol (8mg codeine/500 mg paracetamol) tablets',0),(147,'Co-codamol (30 mg codeine/ 500 mg paracetamol) tablets',0),(144,'Codeine tablets',15),(145,'Codeine tablets',30),(148,'Dihydrocodeine',30),(149,'Dihydrocodeine',60),(142,'Paracetamol tablets',500),(143,'Paracetamol capsules',500),(161,'Calcium (One tablet)',0),(162,'Caclium (Two tablets)',0),(163,'Calcium with vitamin D (one tablet)',0),(164,'Calcium with Vitamin D (two tablets)',0),(165,'B12 injections (Hydroxocobalamin)',1),(166,'Folic acid',5);
/*!40000 ALTER TABLE `ibd_medication_dose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_medication_type`
--

DROP TABLE IF EXISTS `ibd_medication_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_medication_type`
--

LOCK TABLES `ibd_medication_type` WRITE;
/*!40000 ALTER TABLE `ibd_medication_type` DISABLE KEYS */;
INSERT INTO `ibd_medication_type` VALUES (1,'Aminosalicylates (Mesalazine / 5 ASAs)'),(2,'Rectal / Topical Treatments (Suppositories / Enemas)'),(3,'Oral Steroids (Prednisolone / Budesonide)'),(4,'Immunomodulators (Azathioprine / Mercaptopurine / Methotrexate)'),(5,'Biologics (Adalimumab-Humira /  Infliximab-Remicade)'),(6,'Other');
/*!40000 ALTER TABLE `ibd_medication_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_medication_type_medications`
--

DROP TABLE IF EXISTS `ibd_medication_type_medications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_type_medications` (
  `medication_type_id` bigint(20) NOT NULL,
  `medication_id` bigint(20) NOT NULL,
  KEY `FK4D48F99B99BAAE42` (`medication_id`),
  KEY `FK4D48F99B57719061` (`medication_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_medication_type_medications`
--

LOCK TABLES `ibd_medication_type_medications` WRITE;
/*!40000 ALTER TABLE `ibd_medication_type_medications` DISABLE KEYS */;
INSERT INTO `ibd_medication_type_medications` VALUES (1,1),(1,2),(1,14),(1,11),(1,12),(1,10),(1,13),(1,15),(1,16),(1,3),(1,4),(1,8),(1,5),(1,9),(1,7),(1,6),(1,18),(1,17),(2,20),(2,21),(2,19),(2,23),(2,24),(2,22),(3,26),(3,28),(3,27),(3,25),(4,29),(4,30),(4,31),(5,33),(5,32),(6,38),(6,36),(6,34),(6,35),(6,37);
/*!40000 ALTER TABLE `ibd_medication_type_medications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_my_medication`
--

DROP TABLE IF EXISTS `ibd_my_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_my_medication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateStarted` datetime NOT NULL,
  `dateStopped` datetime DEFAULT NULL,
  `medication_frequency_id` bigint(20) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherMedication` text,
  `reasonForStopping` text,
  `medication_id` bigint(20) DEFAULT NULL,
  `medication_dose_id` bigint(20) DEFAULT NULL,
  `medication_type_id` bigint(20) NOT NULL,
  `otherMedicationDose` text,
  PRIMARY KEY (`id`),
  KEY `FK434B606EF8204181` (`medication_dose_id`),
  KEY `FK434B606E99BAAE42` (`medication_id`),
  KEY `FK434B606E57719061` (`medication_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_my_medication`
--

LOCK TABLES `ibd_my_medication` WRITE;
/*!40000 ALTER TABLE `ibd_my_medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_my_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_myibd`
--

DROP TABLE IF EXISTS `ibd_myibd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_myibd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bodyPartAffected` text,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) NOT NULL,
  `namedConsultant` text,
  `nhsno` varchar(10) NOT NULL,
  `nurses` text,
  `yearForSurveillanceColonoscopy` datetime DEFAULT NULL,
  `yearOfDiagnosis` datetime NOT NULL,
  `familyHistory` text,
  `smoking` text,
  `surgery` text,
  `vaccinationRecord` text,
  `eiManifestations` varchar(255) DEFAULT NULL,
  `unitcode` varchar(20) DEFAULT NULL,
  `complications` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_myibd`
--

LOCK TABLES `ibd_myibd` WRITE;
/*!40000 ALTER TABLE `ibd_myibd` DISABLE KEYS */;
INSERT INTO `ibd_myibd` VALUES (3,NULL,3,6,'Dr Lal','9876543210','Cath Stansfield, Justine Newberry','2017-08-29 00:00:00','2005-01-01 00:00:00','Family history of bowel cancer','Never smoked','Other procedure - please specify, subtotal colcectomy, ileo-rectal anastomosis','Previous history of chicken pox, Previous TB (BCG) vaccination','None','rm301','Peri-anal disease (fissures, fistulas,abscesses): Large bowel stricturing:\n                Osteopaenia'),(2,'Body parts affected',3,6,'Dr Named Consultant','1234567890','Nurse IBD','2012-05-31 00:00:00','2005-01-25 00:00:00','Family history of bowel cancer','Never smoked','Other procedure - please specify, subtotal colcectomy, ileo-rectal anastomosis','Previous history of chicken pox, Previous TB (BCG) vaccination','IBD EI Manifestations','A','Peri-anal disease (fissures, fistulas,abscesses): Large bowel stricturing:\n                Osteopaenia');
/*!40000 ALTER TABLE `ibd_myibd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_myibd_severity_level`
--

DROP TABLE IF EXISTS `ibd_myibd_severity_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_myibd_severity_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL,
  `nhsno` varchar(255) NOT NULL,
  `severity_id` bigint(20) NOT NULL,
  `treatment` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_myibd_severity_level`
--

LOCK TABLES `ibd_myibd_severity_level` WRITE;
/*!40000 ALTER TABLE `ibd_myibd_severity_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_myibd_severity_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ibd_nutrition`
--

DROP TABLE IF EXISTS `ibd_nutrition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_nutrition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `foodsThatDisagree` varchar(255) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `nutritionDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_nutrition`
--

LOCK TABLES `ibd_nutrition` WRITE;
/*!40000 ALTER TABLE `ibd_nutrition` DISABLE KEYS */;
/*!40000 ALTER TABLE `ibd_nutrition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter`
--

DROP TABLE IF EXISTS `letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `type` varchar(100) DEFAULT '',
  `content` text,
  PRIMARY KEY (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=40254205 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter`
--

LOCK TABLES `letter` WRITE;
/*!40000 ALTER TABLE `letter` DISABLE KEYS */;
/*!40000 ALTER TABLE `letter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `actor` varchar(100) NOT NULL DEFAULT '',
  `action` varchar(100) NOT NULL DEFAULT '',
  `nhsno` varchar(100) DEFAULT '',
  `user` varchar(100) DEFAULT '',
  `unitcode` varchar(100) DEFAULT NULL,
  `extrainfo` text,
  `specialty_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `log_index_nhsno` (`nhsno`),
  KEY `log_index_action` (`action`),
  KEY `log_index_date` (`date`)
) ENGINE=MyISAM AUTO_INCREMENT=18191421 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (18191405,'2013-11-21 12:00:38','superadmin','logon','','superadmin','','',1),(18191406,'2013-11-21 12:10:31','superadmin','admin add','','renala-unitadmin1','RENALA','',1),(18191407,'2013-11-21 12:11:30','superadmin','admin add','','renalb-unitadmin1','RENALB','',1),(18191408,'2013-11-21 12:12:04','superadmin','admin add','','renalc-unitadmin1','RENALC','',1),(18191409,'2013-11-21 12:16:43','superadmin','admin add','','renala-renalb-unitadmin','RENALA','',1),(18191410,'2013-11-21 12:18:18','superadmin','admin add','','renala-renalb-unitadmin','RENALB','',1),(18191411,'2013-11-21 12:26:51','superadmin','admin add','','srns-groupadmin1','SRNS','',1),(18191412,'2013-11-21 12:27:31','superadmin','admin add','','mpgn-groupadmin1','MPGN','',1),(18191413,'2013-11-21 12:27:55','superadmin','admin add','','alport-groupadmin1','ALPORT','',1),(18191414,'2013-11-21 12:28:21','superadmin','admin add','','hnf1b-groupadmin1','HNF1B','',1),(18191415,'2013-11-21 12:28:55','superadmin','admin add','','gen1-groupadmin1','GENDISGRP1','',1),(18191416,'2013-11-21 12:29:51','superadmin','admin add','','gen2-groupadmin1','GENDISGRP2','',1),(18191417,'2013-11-21 12:32:32','superadmin','admin add','','srns-gen1-groupadmin','SRNS','',1),(18191418,'2013-11-21 12:33:15','superadmin','admin add','','srns-gen1-groupadmin','GENDISGRP1','',1),(18191419,'2013-11-21 12:37:03','superadmin','admin add','','renala-alport-unitadmin','RENALA','',1),(18191420,'2013-11-21 12:37:28','superadmin','admin add','','renala-alport-unitadmin','ALPORT','',1);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `startdate` datetime DEFAULT '0000-00-00 00:00:00',
  `name` varchar(200) DEFAULT '',
  `dose` varchar(200) DEFAULT '',
  `enddate` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=98560732 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `hasRead` tinyint(1) NOT NULL,
  `conversation_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `groupEnum` varchar(255) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C2397E72776A072` (`conversation_id`),
  KEY `fk_message_unit` (`unit_id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `patient` tinyint(1) NOT NULL DEFAULT '0',
  `everyone` tinyint(10) NOT NULL DEFAULT '0',
  `headline` varchar(255) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  `specialty_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=184 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `surname` varchar(100) DEFAULT '',
  `forename` varchar(100) DEFAULT '',
  `dateofbirth` varchar(100) DEFAULT '',
  `sex` varchar(100) DEFAULT '',
  `address1` varchar(100) DEFAULT '',
  `address2` varchar(100) DEFAULT '',
  `address3` varchar(100) DEFAULT '',
  `postcode` varchar(100) DEFAULT '',
  `telephone1` varchar(100) DEFAULT '',
  `telephone2` varchar(100) DEFAULT '',
  `mobile` varchar(100) DEFAULT '',
  `diagnosis` varchar(100) DEFAULT '',
  `treatment` varchar(100) DEFAULT '',
  `transplantstatus` varchar(100) DEFAULT '',
  `hospitalnumber` varchar(100) DEFAULT '',
  `gpname` varchar(100) DEFAULT '',
  `gpaddress1` varchar(100) DEFAULT '',
  `gpaddress2` varchar(100) DEFAULT '',
  `gpaddress3` varchar(100) DEFAULT '',
  `gppostcode` varchar(100) DEFAULT '',
  `gptelephone` varchar(100) DEFAULT '',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `otherConditions` text,
  `address4` varchar(255) DEFAULT NULL,
  `bloodgroup` varchar(255) DEFAULT NULL,
  `bmdexam` datetime DEFAULT NULL,
  `gpemail` varchar(255) DEFAULT NULL,
  `diagnosisDate` datetime DEFAULT NULL,
  `radarNo` int(11) DEFAULT NULL,
  `rrNo` varchar(10) DEFAULT NULL,
  `dateReg` datetime DEFAULT NULL,
  `nhsNoType` int(11) DEFAULT NULL,
  `uktNo` bigint(20) DEFAULT NULL,
  `surnameAlias` varchar(50) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `ethnicGp` varchar(6) DEFAULT NULL,
  `postcodeOld` varchar(50) DEFAULT NULL,
  `consent` bit(1) DEFAULT NULL,
  `dateBapnReg` datetime DEFAULT NULL,
  `consNeph` varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `rrtModality` int(10) DEFAULT NULL,
  `otherClinicianAndContactInfo` varchar(500) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `republicOfIrelandId` varchar(20) DEFAULT NULL,
  `isleOfManId` varchar(20) DEFAULT NULL,
  `channelIslandsId` varchar(20) DEFAULT NULL,
  `indiaId` varchar(20) DEFAULT NULL,
  `generic` tinyint(1) DEFAULT NULL,
  `genericDiagnosis` varchar(20) DEFAULT NULL,
  `dateOfGenericDiagnosis` datetime DEFAULT NULL,
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `radarConsentConfirmedByUserId` int(11) DEFAULT NULL,
  `mostRecentTestResultDateRangeStopDate` datetime DEFAULT NULL,
  `sourceType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nhsno` (`nhsno`,`unitcode`),
  KEY `fk_unitcode` (`unitcode`),
  KEY `fk_genericDiagnosis` (`genericDiagnosis`)
) ENGINE=MyISAM AUTO_INCREMENT=605522 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_admin_notification`
--

DROP TABLE IF EXISTS `pv_admin_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_admin_notification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `notification_id` bigint(20) NOT NULL,
  `email` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `notification_id` (`notification_id`,`email`),
  UNIQUE KEY `notification_id_2` (`notification_id`,`email`),
  UNIQUE KEY `notification_id_3` (`notification_id`,`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_admin_notification`
--

LOCK TABLES `pv_admin_notification` WRITE;
/*!40000 ALTER TABLE `pv_admin_notification` DISABLE KEYS */;
INSERT INTO `pv_admin_notification` VALUES (1,0,'patientview-testing@solidstategroup.com'),(2,1,'patientview-testing@solidstategroup.com'),(4,2,'patientview-testing@solidstategroup.com');
/*!40000 ALTER TABLE `pv_admin_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_allergy`
--

DROP TABLE IF EXISTS `pv_allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_allergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `confidenceLevel` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `infoSource` varchar(255) DEFAULT NULL,
  `reaction` varchar(255) DEFAULT NULL,
  `recordedDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `substance` varchar(255) DEFAULT NULL,
  `typeCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_allergy`
--

LOCK TABLES `pv_allergy` WRITE;
/*!40000 ALTER TABLE `pv_allergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_emailqueue`
--

DROP TABLE IF EXISTS `pv_emailqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_emailqueue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `message_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `finished` datetime DEFAULT NULL,
  `report` text,
  PRIMARY KEY (`id`),
  KEY `FKC4A69AF58F35B002` (`job_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_emailqueue`
--

LOCK TABLES `pv_emailqueue` WRITE;
/*!40000 ALTER TABLE `pv_emailqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_emailqueue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_feature_access`
--

DROP TABLE IF EXISTS `pv_feature_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_feature_access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `unit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_feature_access`
--

LOCK TABLES `pv_feature_access` WRITE;
/*!40000 ALTER TABLE `pv_feature_access` DISABLE KEYS */;
INSERT INTO `pv_feature_access` VALUES (6,'messaging',1116);
/*!40000 ALTER TABLE `pv_feature_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_groupmessage`
--

DROP TABLE IF EXISTS `pv_groupmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_groupmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniquegroupmessage` (`conversation_id`,`recipient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_groupmessage`
--

LOCK TABLES `pv_groupmessage` WRITE;
/*!40000 ALTER TABLE `pv_groupmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_groupmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_job`
--

DROP TABLE IF EXISTS `pv_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `errorCount` bigint(20) NOT NULL,
  `finished` datetime DEFAULT NULL,
  `groupEnum` varchar(255) NOT NULL,
  `started` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `creator_id` bigint(20) NOT NULL,
  `message_id` bigint(20) NOT NULL,
  `specialty_id` bigint(20) NOT NULL,
  `report` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_job`
--

LOCK TABLES `pv_job` WRITE;
/*!40000 ALTER TABLE `pv_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_patientjoin_request`
--

DROP TABLE IF EXISTS `pv_patientjoin_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_patientjoin_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL DEFAULT '',
  `lastname` varchar(100) NOT NULL DEFAULT '',
  `dateofbirth` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT NULL,
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `nhsNo` varchar(100) DEFAULT NULL,
  `dateOfRequest` datetime DEFAULT NULL,
  `isComplete` tinyint(1) DEFAULT '0',
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_patientjoin_request`
--

LOCK TABLES `pv_patientjoin_request` WRITE;
/*!40000 ALTER TABLE `pv_patientjoin_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_patientjoin_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_procedure`
--

DROP TABLE IF EXISTS `pv_procedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_procedure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `proceduretext` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_procedure`
--

LOCK TABLES `pv_procedure` WRITE;
/*!40000 ALTER TABLE `pv_procedure` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_procedure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pv_user_log`
--

DROP TABLE IF EXISTS `pv_user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pv_user_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(255) DEFAULT NULL,
  `lastdatadate` datetime DEFAULT NULL,
  `unitcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_user_log`
--

LOCK TABLES `pv_user_log` WRITE;
/*!40000 ALTER TABLE `pv_user_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_user_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdc_genetic_test`
--

DROP TABLE IF EXISTS `rdc_genetic_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdc_genetic_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `radar_no` bigint(20) NOT NULL,
  `testsDone` int(11) NOT NULL,
  `labWhereTestWasDone` text,
  `testDoneOn` text,
  `referenceNumber` varchar(255) DEFAULT NULL,
  `whatResultsShowed` text,
  `keyEvidence` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdc_genetic_test`
--

LOCK TABLES `rdc_genetic_test` WRITE;
/*!40000 ALTER TABLE `rdc_genetic_test` DISABLE KEYS */;
/*!40000 ALTER TABLE `rdc_genetic_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_alport_deafness`
--

DROP TABLE IF EXISTS `rdr_alport_deafness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_alport_deafness` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `radar_no` bigint(20) NOT NULL,
  `evidenceOfDeafness` int(11) NOT NULL,
  `ageProblemFirstNoticed` int(11) DEFAULT NULL,
  `ageStartedUsingHearingAid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_alport_deafness`
--

LOCK TABLES `rdr_alport_deafness` WRITE;
/*!40000 ALTER TABLE `rdr_alport_deafness` DISABLE KEYS */;
/*!40000 ALTER TABLE `rdr_alport_deafness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_diagnosis_mapping`
--

DROP TABLE IF EXISTS `rdr_diagnosis_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_diagnosis_mapping` (
  `workingGroup` varchar(100) NOT NULL,
  `PRDCode` varchar(20) NOT NULL,
  `ordering` int(10) DEFAULT NULL,
  PRIMARY KEY (`workingGroup`,`PRDCode`),
  KEY `rdr_diagnosis_mapping_ibfk_1` (`PRDCode`),
  CONSTRAINT `rdr_diagnosis_mapping_ibfk_1` FOREIGN KEY (`PRDCode`) REFERENCES `rdr_prd_code` (`ERA_EDTA_PRD_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_diagnosis_mapping`
--

LOCK TABLES `rdr_diagnosis_mapping` WRITE;
/*!40000 ALTER TABLE `rdr_diagnosis_mapping` DISABLE KEYS */;
INSERT INTO `rdr_diagnosis_mapping` VALUES ('AHUS','xAHUS',1),('ALPORT','2756',1),('ALPORT','2760',2),('ARPKD','xARPKD',1),('Arthrogryposis,1','RDG004',1),('Autoimmune2','RDG005',1),('Bardet3','3351',1),('Bartter4','3085',1),('Branchio-oto-renal5','3322',1),('CYSTIN','2964',1),('Denys-Drash7','1074',1),('EAST8','RDG006',1),('Frasier10','3314',1),('FUAN','xFUAN',1),('GENDISGRP1','1300',1),('GENDISGRP2','1301',1),('Gitelman11','3092',1),('Glucocorticoid-remediable13','3125',1),('Gordon12','3156',1),('Haemolytic14','2610',1),('Haemolytic14','2623',2),('Haemolytic14','2647',3),('Haemolytic14','2652',4),('Haemolytic14','2668',5),('HNF1B','1639',3),('HNF1B','1656',2),('HNF1B','3139',4),('HNF1B','3627',1),('HYPALK','xHYPALK',1),('Hyperoxaluria16','1850',1),('Hyperoxaluria16','3194',2),('Hyperoxaluria16','3207',3),('Hyperoxaluria16','3211',4),('Hyperoxaluria16','3731',5),('Hyperparathyroidism,20','RDG010',1),('Hyperparathyroidism19','RDG009',1),('Hyperparathyroidism21','RDG011',1),('HYPERRDG','xHYPERRDG',1),('Hyperuricemic22','2827',1),('Hypocalciuria15','3160',1),('Hypoparathyroidism17','RDG007',1),('Hypoparathyroidism18','RDG008',1),('Liddle26','3102',1),('Lipoprotein27','RDG013',1),('Lowe28','2938',1),('Maturity-Onset29','3139',1),('Medullary30','2815',1),('Membranoproliferative31','1233',1),('MEMRDG','xMEMRDG',1),('Nail32','3253',1),('Nephrolithiasis33','2929',1),('Nephronophthisis34','2836',1),('Nephronophthisis34','2843',2),('Nephronophthisis34','2858',3),('Nephronophthisis34','2862',4),('Nephronophthisis34','2870',5),('Nephronophthisis34','2889',6),('Nephronophthisis34','2891',7),('Nephrotic35','1003',1),('Nephrotic35','1019',2),('Nephrotic35','1026',5),('Nephrotic35','1035',6),('Nephrotic35','1042',7),('Nephrotic35','1057',8),('Nephrotic35','1061',9),('Nephrotic35','1088',10),('Nephrotic35','1090',11),('Nephrotic35','1100',12),('Nephrotic35','3604',3),('Nephrotic35','3615',4),('PAULRADAR2','3139',1),('Polycystic36','2741',1),('Polycystic37','2725',1),('Polycystic38','2739',1),('Renal39','RDG012',1),('Renal40','3000',1),('Renal40','3016',2),('Renal40','3028',3),('Renal40','3037',4),('SRNS:41','3604',1),('STECHUS','xSTECHUS',1),('Tuberous42','3276',1),('VASRDG','1383',1),('VASRDG','1396',2),('Von43','3282',1);
/*!40000 ALTER TABLE `rdr_diagnosis_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_hnf1b_misc`
--

DROP TABLE IF EXISTS `rdr_hnf1b_misc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_hnf1b_misc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `radar_no` bigint(20) NOT NULL,
  `renalCysts` int(11) DEFAULT '0',
  `singleKidney` int(11) DEFAULT '0',
  `otherRenalMalformations` int(11) DEFAULT '0',
  `otherRenalMalformationsDetails` varchar(1000) DEFAULT NULL,
  `diabetes` int(11) DEFAULT '0',
  `ageAtDiabetesDiagnosis` int(11) DEFAULT '0',
  `gout` int(11) DEFAULT '0',
  `ageAtGoutDiagnosis` int(11) DEFAULT '0',
  `genitalMalformation` int(11) DEFAULT '0',
  `genitalMalformationDetails` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_hnf1b_misc`
--

LOCK TABLES `rdr_hnf1b_misc` WRITE;
/*!40000 ALTER TABLE `rdr_hnf1b_misc` DISABLE KEYS */;
/*!40000 ALTER TABLE `rdr_hnf1b_misc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_patient_linkage`
--

DROP TABLE IF EXISTS `rdr_patient_linkage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_patient_linkage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_nhsno` varchar(20) DEFAULT NULL,
  `source_unitcode` varchar(20) DEFAULT NULL,
  `dest_nhsno` varchar(20) DEFAULT NULL,
  `dest_unitcode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_patient_linkage`
--

LOCK TABLES `rdr_patient_linkage` WRITE;
/*!40000 ALTER TABLE `rdr_patient_linkage` DISABLE KEYS */;
/*!40000 ALTER TABLE `rdr_patient_linkage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_prd_code`
--

DROP TABLE IF EXISTS `rdr_prd_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_prd_code` (
  `ERA_EDTA_PRD_code` varchar(20) NOT NULL,
  `ERA_EDTA_primaryRenalDiagnosisTerm` varchar(200) DEFAULT NULL,
  `histology` tinyint(1) DEFAULT NULL,
  `clinicalHistory` tinyint(1) DEFAULT NULL,
  `familyHistory` tinyint(1) DEFAULT NULL,
  `clinicalExam` tinyint(1) DEFAULT NULL,
  `biochemistry` tinyint(1) DEFAULT NULL,
  `immunology` tinyint(1) DEFAULT NULL,
  `urineAnalysis` tinyint(1) DEFAULT NULL,
  `imaging` tinyint(1) DEFAULT NULL,
  `geneTest` tinyint(1) DEFAULT NULL,
  `otherCriteriaAndNotes` varchar(1000) DEFAULT NULL,
  `SNOMED_CT_conceptIdentifierForFocusConcept` varchar(50) DEFAULT NULL,
  `SNOMED_CT_fullySpecifiedName` varchar(200) DEFAULT NULL,
  `SNOMED_CT_expressionConstraint` varchar(200) DEFAULT NULL,
  `majorHeading` varchar(200) DEFAULT NULL,
  `mappingToOldPRDCode` int(10) DEFAULT NULL,
  `mappingToOldPRDTerm` varchar(200) DEFAULT NULL,
  `ERA_EDTA_defaultSortOrder` int(10) DEFAULT NULL,
  `geneticsHomeReferenceLink` varchar(200) DEFAULT NULL,
  `nationalCenterForBiotechnologyLink` varchar(200) DEFAULT NULL,
  `ICD_10_code` varchar(200) DEFAULT NULL,
  `ICD10_rubricTerm` varchar(200) DEFAULT NULL,
  `alternativesearchTerms` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ERA_EDTA_PRD_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_prd_code`
--

LOCK TABLES `rdr_prd_code` WRITE;
/*!40000 ALTER TABLE `rdr_prd_code` DISABLE KEYS */;
INSERT INTO `rdr_prd_code` VALUES ('1003','Adult Nephrotic syndrome - no histology',0,1,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','52254009','Nephrotic syndrome (disorder)','52254009 | Nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',2,'','','N049','Nephrotic syndrome, unspecified',''),('1019','Nephrotic syndrome of childhood - steroid sensitive - no histology',0,1,0,0,1,0,0,0,0,'Defined by response to steroid therapy.','445119005','Steroid sensitive nephrotic syndrome of childhood (disorder)','445119005 | Steroid sensitive nephrotic syndrome of childhood |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',3,'','','N049','Nephrotic syndrome, unspecified',''),('1026','Congenital nephrotic syndrome (CNS) - no histology',0,1,0,0,1,0,0,0,0,'Defined by response to steroid therapy.\nClinical history: Onset in 1st 3 months after birth.','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome (disorder) |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',6,'','for background information see:\nhttp://omim.org/entry/256300 about nephrotic syndrome, type 1; NPHS1\nhttp://omim.org/entry/602716 about nephrin; NPHS1\nhttp://omim.org/entry/600995 about nephrotic synd','N049','Nephrotic syndrome, unspecified','CNS'),('1035','Congenital nephrotic syndrome (CNS) - Finnish type - no histology',0,1,0,0,1,0,0,0,1,'Mutation in the gene encoding nephrin (NPHS1) on chromosome 19q13.1.\nClinical history: Onset in 1st 3 months after birth.','197601003','Finnish congenital nephrotic syndrome (disorder)','197601003 | Finnish congenital nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',7,'','http://omim.org/entry/256300 about nephrotic syndrome, type 1; NPHS1\nhttp://omim.org/entry/602716 about nephrin; NPHS1','N049','Nephrotic syndrome, unspecified','CNS'),('1042','Congenital nephrotic syndrome (CNS) - Finnish type - histologically proven',1,1,0,0,1,0,0,0,1,'Mutation in the gene encoding nephrin (NPHS1) on chromosome 19q13.1.\nClinical history: Onset in 1st 3 months after birth.','197601003','Finnish congenital nephrotic syndrome (disorder)','97601003 | Finnish congenital nephrotic syndrome |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',19,'Glomerulonephritis; histologically examined',8,'','http://omim.org/entry/256300\nhttp://omim.org/entry/602716 about nephrin; NPHS1','N049','Nephrotic syndrome, unspecified','CNS'),('1057','Congenital nephrotic syndrome (CNS) - diffuse mesangial sclerosis',1,1,0,0,1,0,0,0,1,'Often associated with mutation in the Wilms tumor suppressor gene (WT1) on chromosome 11p13.\nClinical history: Onset in 1st 3 months after birth.','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome |:\n47429007 | Associated with | = 111406002 | Diffuse mesangial sclerosis |','Glomerular disease',19,'Glomerulonephritis; histologically examined',9,'','for background information see:\nhttp://omim.org/entry/256370 about nephrotic syndrome, type 4; NPHS4\nhttp://omim.org/entry/607102 about WT1 gene; WT1','N049','Nephrotic syndrome, unspecified','CNS'),('1061','Congenital nephrotic syndrome (CNS) - focal segmental glomerulosclerosis (FSGS)',1,1,0,0,1,0,0,0,1,'Clinical history: Onset in 1st 3 months after birth.\nOften associated with NPHS2 mutations.','236384008','Congenital nephrotic syndrome with focal glomerulosclerosis (disorder)','','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',10,'','http://omim.org/entry/600995','N071','Focal and segmental glomerular lesions',''),('1074','Denys-Drash syndrome',0,1,0,1,0,0,0,0,1,'Mutation in the WT1 gene.\nClinical history: Onset in 1st 3 months after birth.','236385009','Drash syndrome (disorder)','','Glomerular disease',99,'Other identified renal disorders',11,'','http://omim.org/entry/194080\nhttp://omim.org/entry/607102 about WT1 gene; WT1','N048','Nephrotic syndrome, other',''),('1088','Congenital nephrotic syndrome (CNS) - congenital infection',0,1,0,0,1,0,0,0,0,'Clinical history:\nOnset in 1st 3 months after birth. Infection as a cause of congenital nephrotic syndrome is rare, especially in Europe.\nThis PRD should not be used for hepatitis C related nephropathy or autoimmune disease,\nboth of which are coded elsewhere.\n','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome |:\n47429007 | Associated with | = ((40733004 | Infectious disease |)\nAND (! 278929008 | Congenital hepatitis C infection |)\nAND (! < 52079000 | Congenital huma','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',12,'','','N049','Nephrotic syndrome, unspecified',''),('1090','Minimal change nephropathy - no histology',0,0,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','44785005','Minimal change disease (disorder)','44785005 | Minimal change disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',13,'','','N050','Unspecified nephritic syndrome, minor glomerular abnormality','MCN'),('1100','Minimal change nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','44785005','Minimal change disease (disorder)','44785005 | Minimal change disease |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',19,'Glomerulonephritis; histologically examined',14,'','','N050','Unspecified nephritic syndrome, minor glomerular abnormality','MCN'),('1116','IgA nephropathy - no histology',0,1,0,0,0,0,0,0,0,'In the absence of a renal histopathology, consider alternative PRDs\neg postinfection glomerulonephritis before choosing this code.\nNote that Henoch-Schönlein nephritis has a separate PRD.','236407003','IgA nephropathy (disorder)','236407003 | IgA nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',15,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy\n'),('1128','IgA nephropathy - histologically proven',1,0,0,0,0,0,0,0,0,'IgA must be demonstrated by renal histopathology.\nIgA in skin histopathology or raised serum IgA concentration are not sufficient for this PRD.\nNote that Henoch-Schönlein nephritis is coded separately.','236407003','IgA nephropathy (disorder)','236407003 | IgA nephropathy |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',16,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1137','Familial IgA nephropathy - no histology',0,1,1,0,0,0,0,0,0,'With histological evidence in a first degree relative and a compatible clinical setting in the patient,\nthis PRD allows nephrologists to record their preferred diagnosis of IgA nephropathy even in the absence of a renal histopathology.','445404003','Familial immunoglobulin A nephropathy (disorder)','445404003 | Familial immunoglobulin A nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',17,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1144','Familial IgA nephropathy - histologically proven',1,0,1,0,0,0,0,0,0,'IgA must be demonstrated by renal histopathology.\nIgA in skin histopathology or raised serum IgA concentration are not sufficient for this PRD.\n','445404003','Familial immunoglobulin A nephropathy (disorder)','445404003 | Familial immunoglobulin A nephropathy |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',18,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1159','IgA nephropathy secondary to liver cirrhosis - no histology',0,1,0,0,0,0,0,0,0,'','282364005','IgA nephropathy associated with liver disease (disorder)','282364005 | IgA nephropathy associated with liver disease |: 42752001 | Due to | = 19943007 | Cirrhosis of liver |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',19,'','','N028 AND K746','Recurrent and persistent haematuria, other AND Other and unspecified cirrhosis of liver','IgA IgAN immunoglobulin A nephropathy'),('1163','IgA nephropathy secondary to liver cirrhosis - histologically proven',1,1,0,0,0,0,0,0,0,'','282364005','IgA nephropathy associated with liver disease (disorder)','282364005 | IgA nephropathy associated with liver disease |: 42752001 | Due to | = 19943007 | Cirrhosis of liver |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',20,'','','N028 AND K746','Recurrent and persistent haematuria, other AND Other and unspecified cirrhosis of liver','IgA IgAN immunoglobulin A nephropathy'),('1171','IgM - associated nephropathy',1,0,0,0,0,0,0,0,0,'The presence of IgM should be demonstrated by IF and the presence of deposits compatible with\nimmunoglobulins in the same region of the glomerulus should be seen on EM.','236411009','IgM nephropathy (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',21,'','','N053','Diffuse mesangial proliferative glomerulonephritis',''),('1185','Membranous nephropathy - idiopathic',1,0,0,0,0,1,0,0,0,'May be associated with antibodies to the phospholipase A2 receptor.\nThis PRD should not be used for SLE related nephropathies which are coded elsewhere.','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis | ','Glomerular disease',14,'Membranous nephropathy',22,'','for background information see:\nhttp://omim.org/entry/604939 about phospholipase A2 receptor 1; PLA2R1','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN'),('1192','Membranous nephropathy - malignancy associated',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n47429007 | Associated with | = << 363346000 | Malignant neoplastic disease |','Glomerular disease',14,'Membranous nephropathy',23,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN cancer'),('1205','Membranous nephropathy - drug induced',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n246075003 | causative agent | = < 410942007 | Drug or medicament |','Glomerular disease',14,'Membranous nephropathy',24,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN medicine'),('1214','Membranous nephropathy - infection associated',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n47429007 | Associated with | = < 40733004 | Infectious disease | ','Glomerular disease',14,'Membranous nephropathy',25,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN infection'),('1222','Mesangiocapillary glomerulonephritis type 1',1,0,0,0,0,0,0,0,0,'Often associated with cryoglobulinaemia.','75888001','Mesangiocapillary glomerulonephritis, type I (disorder)','','Glomerular disease',15,'Membrano-proliferative GN; type I (proven by immunofluorescence / electron microscopy, not code 84 or 89)',26,'','for background information see:\nhttp://omim.org/entry/305800','N055','Diffuse mesangiocapillary glomerulonephritis','MCGN MPGN membranoproliferative'),('1233','Mesangiocapillary glomerulonephritis type 2 (dense deposit disease)',1,0,0,0,0,0,0,0,0,'','59479006','Mesangiocapillary glomerulonephritis, type II (disorder)','','Glomerular disease',13,'Dense deposit disease; membrano-proliferative GN; type II (proven by immunofluorescence / electron microscopy)',27,'','for background information see:\nhttp://omim.org/entry/305800','N056','Unspecified nephritic syndrome, dense deposit disease','MCGN MPGN membranoproliferative DD'),('1246','Mesangiocapillary glomerulonephritis type 3',1,0,0,0,0,0,0,0,0,'','236409000','Mesangiocapillary glomerulonephritis type III (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',28,'','for background information see:\nhttp://omim.org/entry/305800','N055','Diffuse mesangiocapillary glomerulonephritis','MCGN MPGN membranoproliferative'),('1251','Idiopathic rapidly progressive (crescentic) glomerulonephritis',1,0,0,0,0,0,0,0,0,'This PRD should not be used for other specific PRDs e.g. IgA, vasculitis or Goodpastures syndrome.','236398000','Crescentic glomerulonephritis (disorder)','<< 236398000 |Crescentic glomerulonephritis |\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',16,'Crescentic (extracapillary) glomerulonephritis (type I, II, III)',29,'','','N057','Diffuse concentric glomerulonephritis','RPGN'),('1267','Primary focal segmental glomerulosclerosis (FSGS)',1,0,0,0,0,0,0,0,0,'','236403004','Focal segmental glomerulosclerosis (disorder)','<< 236403004 | Focal segmental glomerulosclerosis |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',30,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions',''),('1279','Familial focal segmental glomerulosclerosis (FSGS) - autosomal recessive - no histology',0,0,1,0,0,0,0,0,1,'This PRD can be used if there is a compatible family history with renal histological evidence in a first degree relative but not in the patient.','445388002','Autosomal recessive focal segmental glomerulosclerosis (disorder)','445388002 | Autosomal recessive focal segmental glomerulosclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',50,'Hereditary/Familial nephropathy - type unspecified',31,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AR'),('1280','Familial focal segmental glomerulosclerosis (FSGS) - autosomal recessive - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should be used if there is a compatible family history and renal histological evidence in the patient.','445388002','Autosomal recessive focal segmental glomerulosclerosis (disorder)','445388002 | Autosomal recessive focal segmental glomerulosclerosis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',32,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AR'),('1298','Familial focal segmental glomerulosclerosis (FSGS) - autosomal dominant - no histology',0,0,1,0,0,0,0,0,1,'This PRD can be used if there is a compatible family history with renal histological evidence in a first degree relative but not in the patient.','444977005','Autosomal dominant focal segmental glomerulosclerosis (disorder)','444977005 | Autosomal dominant focal segmental glomerulosclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',50,'Hereditary/Familial nephropathy - type unspecified',33,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AD'),('1300','Generic Group 1 Diag Term 1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1301','Generic Group 2 Diag Term 2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1308','Familial focal segmental glomerulosclerosis (FSGS) - autosomal dominant - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should be used if there is a compatible family history and renal histological evidence in the patient.','444977005','Autosomal dominant focal segmental glomerulosclerosis (disorder)','444977005 | Autosomal dominant focal segmental glomerulosclerosis |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',34,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AD'),('1312','Focal segmental glomerulosclerosis (FSGS) secondary to obesity - no histology',0,0,0,1,1,0,0,0,0,'Obesity on clinical examination.\nThis PRD should be used with great caution.\nIt allows a nephrologist to give an opinion in the absence of the histological proof which is required to substantiate a diagnosis of FSGS.\nAn alternative PRD eg Adult Nephrotic syndrome - no histology should be considered.\n','236403004','Focal segmental glomerulosclerosis (disorder)','236403004 | Focal segmental glomerulosclerosis (disorder) |:\n47429007 | Associated with | = 414916001 | Obesity |,\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',35,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS fat overweight'),('1320','Focal segmental glomerulosclerosis (FSGS) secondary to obesity - histologically proven',1,0,0,1,1,0,0,0,0,'Obesity on clinical examination.','236403004','Focal segmental glomerulosclerosis (disorder)','236403004 | Focal segmental glomerulosclerosis (disorder) |:\n47429007 | Associated with | = 414916001 | Obesity |,\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',36,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS fat overweight'),('1331','Diffuse endocapillary glomerulonephritis',1,0,0,0,0,0,0,0,0,'Typical of post-infectious glomerulonephritis.','3704008','Diffuse endocapillary proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',37,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('1349','Mesangial proliferative glomerulonephritis ',1,0,0,0,0,0,0,0,0,'This PRD should not be used for SLE related nephropathies which are coded elsewhere.','35546006','Mesangial proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',38,'','','N033','Diffuse mesangial proliferative glomerulonephritis','MPGN'),('1354','Focal and segmental proliferative glomerulonephritis',1,0,0,0,0,0,0,0,0,'','83866005','Focal AND segmental proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',39,'','','N071','Focal and segmental glomerular lesions','FSGN FSPGN FSGS'),('1365','Glomerulonephritis - secondary to other systemic disease',1,0,0,0,0,0,0,0,0,'Examples of the systemic conditions include malignancy and liver disease but this PRD should not be used if a more accurate one is available.','36171008','Glomerulonephritis (disorder)','36171008 | Glomerulonephritis |:\n42752001 | Due to | = 56019007 | Systemic disease |\n','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',40,'','','N059','Unspecified nephritic syndrome, unspecified','GN 2y'),('1377','Glomerulonephritis - histologically indeterminate',1,0,0,0,0,0,0,0,0,'Use this PRD if no other seems appropriate even after considering all the evidence and the renal histopathology report.','36171008','Glomerulonephritis (disorder)','(36171008 | Glomerulonephritis |)\nAND\n(372269006 | Histologic type cannot be determined |)','Glomerular disease',19,'Glomerulonephritis; histologically examined',41,'','','N059','Unspecified nephritic syndrome, unspecified','GN'),('1383','Systemic vasculitis - ANCA negative - histologically proven',1,0,0,0,0,1,0,0,0,'','46956008','Systemic vasculitis (disorder)','(46956008 | Systemic vasculitis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |)\nAND\n(310255003 | Anti-neutrophil cytoplasmic antibody negative |)','Glomerular disease',70,'Renal vascular disease - type unspecified',42,'','','I776','Arteritis, unspecified','mPAN PAN vasculitis'),('1396','Systemic vasculitis - ANCA positive - no histology',0,1,0,1,1,1,0,0,0,'Must be clinical evidence to suggest a vasculitis.','46956008','Systemic vasculitis (disorder)','(46956008 | Systemic vasculitis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |\nAND\n310256002 | Antineutrophil cytoplasmic antibody positive |','Glomerular disease',74,'Wegeners granulomatosis',43,'','','I776','Arteritis, unspecified','mPAN PAN  PR3 MPO vasculitis'),('1401','Wegeners granulomatosis - no histology',0,1,0,1,0,1,0,0,0,'Involvement of upper or lower respiratory tract or the oral cavity in addition to renal involvement.','195353004','Wegeners granulomatosis (disorder)','195353004 | Wegeners granulomatosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',44,'','http://omim.org/entry/177020','M313','Wegeners granulomatosis','mPAN PAN PR3 vasculitis'),('1417','Wegeners granulomatosis - histologically proven',1,0,0,0,0,1,0,0,0,'In a compatible clinical setting, histological diagnosis from nasal or respiratory tract can support this PRD if renal histology is not available.','195353004','Wegeners granulomatosis (disorder)','195353004 | Wegeners granulomatosis |:\n418775008 | Finding method | = << 7246002| Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',45,'','http://omim.org/entry/177020','M313','Wegeners granulomatosis','mPAN PAN PR3 vasculitis'),('1429','Microscopic polyangiitis - histologically proven',1,0,0,0,0,1,0,0,0,'','239928004','Microscopic polyarteritis nodosa (disorder)','239928004 | Microscopic polyarteritis nodosa |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',46,'','','M300','Polyarteritis nodosa','mPAN PAN vasculitis'),('1438','Churg-Strauss syndrome - no histology',0,1,0,0,0,1,0,0,0,'Peripheral blood eosinophilia is often found.','82275008','Allergic granulomatosis angiitis (disorder)','82275008 | Allergic granulomatosis angiitis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',47,'','','M301','Polyarteritis with lung involvement [Churg-Strauss]','vasculitis'),('1440','Churg-Strauss syndrome - histologically proven',1,0,0,0,0,1,0,0,0,'','82275008','Allergic granulomatosis angiitis (disorder)','82275008 | Allergic granulomatosis angiitis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',48,'','','M301','Polyarteritis with lung involvement [Churg-Strauss]','vasculitis'),('1455','Polyarteritis nodosa',0,0,0,0,0,0,0,1,0,'ANCA may be positive but specific tests for anti MPO and anti PR3 are negative.\nPAN can be secondary to other disorders.\nUse this PRD for classical PAN proven with imaging.\nDo not use this PRD for any type of microscopic polyangiitis.\n','155441006','Polyarteritis nodosa (disorder)','<< 155441006 | Polyarteritis nodosa |\nAND\n! < 239928004 | Microscopic polyarteritis nodosa |\n','Glomerular disease',73,'Renal vascular disease due to polyarteritis',49,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','PAN vasculitis'),('1464','Anti-Glomerular basement membrane (GBM) disease / Goodpastures syndrome - no histology',0,0,0,0,0,1,0,0,0,'','236506009','Goodpastures disease (disorder)','236506009 | Goodpastures disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',86,'Goodpasture’s Syndrome ',50,'','http://omim.org/entry/233450','M310','Hypersensitivity angiitis','GBM aGBM linear IgG'),('1472','Anti-Glomerular basement membrane (GBM) disease / Goodpastures syndrome - histologically proven',1,0,0,0,0,0,0,0,0,'','50581000','Goodpastures syndrome','<< 50581000 | Goodpastures syndrome |:\n418775008 | Finding method | = << 7246002| Kidney biopsy |','Glomerular disease',86,'Goodpasture’s Syndrome ',51,'','http://omim.org/entry/233450','M310','Hypersensitivity angiitis','GBM aGBM linear IgG'),('1486','Systemic lupus erythematosus / nephritis - no histology',0,1,0,0,0,1,0,0,0,'Evidence of renal disease manifested by at least either proteinuria or haematuria.\n','68815009','Systemic lupus erythematosus glomerulonephritis syndrome (disorder)','68815009 | Systemic lupus erythematosus glomerulonephritis syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',84,'Lupus erythematosus',52,'','for background information see:\nhttp://omim.org/entry/152700','M321D','Systemic lupus erythematosus with organ or sys involv','SLE LE lupus'),('1493','Systemic lupus erythematosus / nephritis - histologically proven',1,0,0,0,0,0,0,0,0,'','68815009','Systemic lupus erythematosus glomerulonephritis syndrome (disorder)','68815009 | Systemic lupus erythematosus glomerulonephritis syndrome |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',84,'Lupus erythematosus',53,'','for background information see:\nhttp://omim.org/entry/152700','M321D','Systemic lupus erythematosus with organ or sys involv','SLE LE lupus'),('1504','Henoch-Schönlein purpura / nephritis - no histology',0,1,0,1,0,0,0,0,0,'Evidence of renal disease manifested by at least either proteinuria or haematuria.\nMust be clinical evidence or history compatible with HSP.','191306005','Henoch-Schönlein purpura (disorder)','191306005 | Henoch-Schönlein purpura |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',85,'Henoch-Schoenlein purpura',54,'','','D690','Allergic purpura','HSP vasculitis leukocytoclastic'),('1515','Henoch-Schönlein purpura / nephritis - histologically proven',1,1,0,1,0,0,0,0,0,'Must be clinical evidence or history compatible with HSP.','191306005','Henoch-Schönlein purpura (disorder)','191306005 | Henoch-Schönlein purpura |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',85,'Henoch-Schoenlein purpura',55,'','','D690','Allergic purpura','HSP vasculitis leukocytoclastic'),('1527','Renal scleroderma / systemic sclerosis - no histology',0,1,0,0,0,0,0,0,0,'Cutaneous and systemic symptoms with autoantibodies.','89155008','Systemic sclerosis','(89155008 | Systemic sclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Glomerular disease',87,'Systemic sclerosis (scleroderma)',56,'','','M349','Systemic sclerosis, unspecified','SS PSS'),('1536','Renal scleroderma / systemic sclerosis - histologically proven',1,1,0,0,0,0,0,0,0,'Cutaneous and systemic symptoms with autoantibodies.','236502006','Renal involvement in scleroderma (disorder)','(236502006 | Renal involvement in scleroderma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |)','Glomerular disease',87,'Systemic sclerosis (scleroderma)',57,'','','M348','Other forms of systemic sclerosis','SS PSS'),('1543','Essential mixed cryoglobulinaemia - no histology',0,0,0,0,1,0,0,0,0,'','239947001','Essential mixed cryoglobulinemia (disorder)','239947001 | Essential mixed cryoglobulinemia |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',58,'','for background information see:\nhttp://omim.org/entry/123550\n','D891','Cryoglobulinaemia','cold agglutination'),('1558','Essential mixed cryoglobulinaemia - histologically proven',1,0,0,0,1,0,0,0,0,'','239947001','Essential mixed cryoglobulinemia (disorder)','239947001 | Essential mixed cryoglobulinemia |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',59,'','','D891','Cryoglobulinaemia','cold agglutination'),('1562','Cryoglobulinaemia secondary to hepatitis C - no histology',0,0,0,0,0,1,0,0,0,'','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = << 50711007 | Viral hepatitis C |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',60,'','for background information see:\nhttp://omim.org/entry/609532','D891 AND B171\nOR\nD891 AND B182','Cryoglobulinaemia AND Acute hepatitis C\nOR\nCryoglobulinaemia AND Chronic hepatitis C','cold agglutination liver infection'),('1570','Cryoglobulinaemia secondary to hepatitis C - histologically proven',1,0,0,0,0,1,0,0,0,'','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = << 50711007 | Viral hepatitis C |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',61,'','for background information see:\nhttp://omim.org/entry/609532','D891 AND B171\nOR\nD891 AND B182','Cryoglobulinaemia AND Acute hepatitis C\nOR\nCryoglobulinaemia AND Chronic hepatitis C','cold agglutination liver infection'),('1589','Cryoglobulinaemia secondary to systemic disease - no histology',0,0,0,0,0,1,0,0,0,'This PRD should not be used for hepatitis C related nephropathy or autoimmune disease. Alternative PRDs are available.','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = ((56019007 | Systemic disease |)\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',62,'','','D891','Cryoglobulinaemia','cold agglutination'),('1591','Cryoglobulinaemia secondary to systemic disease - histologically proven',1,0,0,0,0,1,0,0,0,'This PRD should not be used for hepatitis C related nephropathy or autoimmune disease. Alternative PRDs are available.','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',63,'','','D891','Cryoglobulinaemia','cold agglutination'),('1602','Primary reflux nephropathy - sporadic',0,0,0,0,0,0,0,1,0,'A positive family history excludes this diagnosis.\nThis PRD should not normally be used if there is a positive family history.\nIn that case use PRD Familial reflux nephropathy.\n','197764002','Non-obstructive reflux-associated chronic pyelonephritis (disorder)','','Tubulo-interstitial disease',24,'Pyelonephritis due to vesico-ureteric reflux without obstruction',64,'','','N110','Nonobstructive reflux-associated chronic pyelonephritis','CPN PN TIN'),('1618','Familial reflux nephropathy',0,0,1,0,0,0,0,1,0,'A positive family history is required for this PRD.','522551000000101','Familial non-obstructive reflux-associated chronic pyelonephritis (disorder)','','Tubulo-interstitial disease',24,'Pyelonephritis due to vesico-ureteric reflux without obstruction',65,'http://ghr.nlm.nih.gov/gene/ROBO2\nhttp://ghr.nlm.nih.gov/gene/SOX17','http://omim.org/entry/193000 about VUR1\nhttp://omim.org/entry/610878 about VUR2\nhttp://omim.org/entry/613674 about VUR3','N110','Nonobstructive reflux-associated chronic pyelonephritis','CPN PN TIN'),('1625','Congenital dysplasia / hypoplasia',0,0,0,0,0,0,0,1,0,'','204949001','Renal dysplasia (disorder)','','Tubulo-interstitial disease',60,'Renal hypoplasia (congenital) - type unspecified',66,'','','Q614','Renal dysplasia','cystic cysts cyst'),('1639','Multicystic dysplastic kidneys',0,0,0,0,0,0,0,1,1,'Associated with the HNF1B gene.','82525005','Congenital cystic kidney disease (disorder)','','Tubulo-interstitial disease',40,'Cystic kidney disease - type unspecified',67,'http://ghr.nlm.nih.gov/gene/CDC5L','http://omim.org/entry/143400\nhttp://omim.org/entry/602868 about cell division cycle 5, s. pombe, homolog of; CDC5L','Q619','Cystic kidney disease, unspecified','cystic cysts cyst'),('1641','Dysplasia due to fetal ACE-inhibitor exposure',0,1,0,0,0,0,0,1,0,'','519331000000100','Renal dysplasia due to fetus affected by maternal use of angiotensin converting enzyme inhibitor (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',68,'','for background information see:\nhttp://omim.org/entry/106180','Q614','Renal dysplasia','ACE ACEi ACE-i'),('1656','Glomerulocystic disease',1,0,0,0,0,0,0,0,1,'Associated with the HNF1B gene.','253864004','Familial hypoplastic, glomerulocystic kidney (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',70,'','http://omim.org/entry/609886','Q605','Renal hypoplasia, unspecified','cystic cysts cyst'),('1660','Congenital pelvi-ureteric junction obstruction',0,0,0,0,0,0,0,1,0,'','373584008','Congenital pelviureteric junction obstruction (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',71,'','http://omim.org/entry/143400','Q621','Atresia and stenosis of ureter','hydronephrosis'),('1673','Congenital vesico-ureteric junction obstruction',0,0,0,0,0,0,0,1,0,'','373585009','Congenital ureterovesical obstruction (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',72,'','http://omim.org/entry/193000 about VUR1\nhttp://omim.org/entry/610878 about VUR2\nhttp://omim.org/entry/613674 about VUR3','Q621','Atresia and stenosis of ureter','hydronephrosis'),('1687','Posterior urethral valves',0,0,0,0,0,0,0,1,0,'','253900005','Congenital posterior urethral valves (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',73,'','','Q643','Other atresia and stenosis of urethra and bladder neck',''),('1694','Syndrome of agenesis of abdominal muscles - prune belly syndrome',0,0,0,1,0,0,0,1,0,'','5187006','Prune belly syndrome ','','Tubulo-interstitial disease',66,'Syndrome of agenesis of abdominal muscles (Prune Belly)',74,'','http://omim.org/entry/100100\nhttp://omim.org/entry/264140','Q794','Prune belly syndrome',''),('1706','Congenital neurogenic bladder',0,0,0,0,0,0,0,1,0,'Investigation will usually include urodynamic studies.','445387007','Congenital neurogenic urinary bladder (finding)','','Tubulo-interstitial disease',99,'Other identified renal disorders',75,'','','N319','Neuromuscular dysfunction of bladder, unspecified','hydronephrosis obstruction CPN PN TIN'),('1710','Bladder exstrophy',0,0,0,1,0,0,0,0,0,'','61758007','Exstrophy of bladder sequence (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',76,'','for background information see:\nhttp://omim.org/entry/600057','Q641','Exstrophy of urinary bladder',''),('1723','Megacystis-megaureter',0,0,0,0,0,0,0,1,0,'Bilateral hydroureteronephrosis; large, smooth, thin-walled bladder without urethral obstruction, gross reflux.','253904001','Megacystis-megaureter syndrome (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',77,'','','Q622','Congenital megaloureter',''),('1734','Oligomeganephronia',1,0,0,0,0,0,0,0,0,'Reduced number of enlarged nephrons.','18417009','Oligomeganephronic hypoplasia of kidney (disorder)','','Tubulo-interstitial disease',61,'Oligomeganephronic hypoplasia',78,'','for background information see:\nhttp://omim.org/entry/246560\nhttp://omim.org/entry/137920','Q605','Renal hypoplasia, unspecified',''),('1747','Renal papillary necrosis - cause unknown',0,0,0,0,0,0,0,1,0,'','90241004','Papillary necrosis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorder',79,'','','N172','Acute renal failure with medullary necrosis',''),('1752','Acquired obstructive uropathy / nephropathy',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',80,'','','N138\nOR\nN139','Other obstructive and reflux uropathy\nOR\nObstructive and reflux uropathy, unspecified','hydronephrosis obstruction CPN PN TIN'),('1768','Acquired obstructive nephropathy due to neurogenic bladder',0,0,0,1,0,0,0,1,0,'The diagnosis is made by imaging plus either clinical examination or urodynamic studies.','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','acquired obstructive nephropathy |:\n42752001 |due to | = 398064005 | Neurogenic bladder |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',81,'','','N319','Neuromuscular dysfunction of bladder, unspecified','hydronephrosis obstruction CPN PN TIN'),('1775','Obstructive nephropathy due to prostatic hypertrophy',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 266569009 | Benign prostatic hyperplasia |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',82,'','','N138 AND N40X','Other obstructive and reflux uropathy AND Hyperplasia of prostate','hydronephrosis obstruction prostate CPN PN TIN'),('1781','Obstructive nephropathy due to prostate cancer',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 399068003 | Malignant tumor of prostate |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',83,'','for background information see:\nhttp://omim.org/entry/176807','N138 AND C61X','Other obstructive and reflux uropathy AND Malignant neoplasm of prostate','hydronephrosis obstruction prostatic malignancy CPN PN TIN'),('1799','Obstructive nephropathy due to bladder cancer',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 399326009 | Malignant tumor of urinary bladder |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',84,'','for background information see:\nhttp://omim.org/entry/109800','N138 AND C679','Other obstructive and reflux uropathy AND Malignant neoplasm of bladder, unspecified','hydronephrosis obstruction prostatic malignancy CPN PN TIN'),('1809','Obstructive nephropathy due to other malignancies',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = << 363346000 | Malignant neoplastic disease |\nAND 42752001 | due to | = ! 266569009 | Benign prostatic hyperplasia |\nAND 42752001 | due to |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',85,'','','N138','Other obstructive and reflux uropathy','hydronephrosis obstruction malignancy CPN PN TIN'),('1813','Idiopathic retroperitoneal fibrosis',0,0,0,0,0,0,0,1,0,'','197808006','Idiopathic retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',86,'','for background information see:\nhttp://omim.org/entry/228800','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction  CPN PN TIN'),('1821','Retroperitoneal fibrosis secondary to malignancies',0,1,0,0,0,0,0,1,0,'','236017004','Malignant retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',87,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction malignancy CPN PN TIN'),('1832','Calculus nephropathy / urolithiasis',0,0,0,0,0,0,0,1,0,'','95566004','Urolithiasis (disorder)','','Tubulo-interstitial disease',25,'Pyelonephritis due to urolithiasis ',90,'','','N209','Urinary calculus, unspecified','stone calculus '),('1845','Calcium oxalate urolithiasis',0,0,0,0,1,0,0,1,0,'','444717006','Calcium oxalate urolithiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',91,'','for background information see:\nhttp://omim.org/entry/167030','N209','Urinary calculus, unspecified','stone calculus '),('1850','Enteric hyperoxaluria ',0,1,0,0,1,0,0,0,0,'','37497004','Enteric hyperoxaluria (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',92,'','','E748','Other specified disorders of carbohydrate metabolism','stone calculus oxalate gut'),('1866','Magnesium ammonium phosphate (struvite) urolithiasis',0,0,0,0,1,0,0,1,0,'Associated with chronic infections with urease producing organisms.','444690001','Magnesium ammonium phosphate urolithiasis (disorder)','','Tubulo-interstitial disease',25,'Pyelonephritis due to urolithiasis ',93,'','','N209','Urinary calculus, unspecified','stone calculus'),('1878','Uric acid urolithiasis',0,0,0,0,1,0,0,1,0,'','267441009','Uric acid urolithiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',94,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','stone calculus urate  gout'),('1884','Tubulo interstitial nephritis - no histology',0,0,0,0,0,0,0,0,0,'This PRD can be used in an appropriate clinical setting, if there is convincing evidence of tubular dysfunction\ne.g. Fanconi syndrome, tubular proteinuria.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',95,'','','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1897','Tubulo interstitial nephritis - histologically proven',1,0,0,0,1,0,0,0,0,' ','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',96,'','','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1907','Familial interstitial nephropathy - no histology',0,0,1,0,0,0,0,0,1,'This PRD may be used if the diagnosis has been confirmed with a renal histology in at least one affected family member.\nThis PRD should not be used for familial nephropathies which have a more accurate PRD.\nA genetic test may help to make the diagnosis but is not mandatory for this PRD.\n','28689008','Interstitial nephritis (disorder)','28689008 | Interstitial nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',50,'Hereditary/Familial nephropathy - type unspecified',97,'','http://omim.org/entry/161900 about progression of renal failure','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1911','Familial interstitial nephropathy - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should not be used for familial nephropathies which have a more accurate PRD.','28689008','Interstitial nephritis (disorder)','28689008 | Interstitial nephritis |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',50,'Hereditary/Familial nephropathy - type unspecified',98,'','http://omim.org/entry/161900 about progression of renal failure','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1924','Tubulo interstitial nephritis associated with autoimmune disease - no histology',0,1,0,0,1,0,0,0,0,'A genetic test may help to make the diagnosis but is not mandatory for this PRD.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = << 85828009 | Autoimmune disease |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',99,'','','N12X AND M359','Tubulo-interstitial nephritis not spec as acute or chronic AND Systemic involvement of connective tissue, unspecified','TIN IN AI immune immunity'),('1930','Tubulo interstitial nephritis associated with autoimmune disease - histologically proven',1,1,0,0,1,0,0,0,0,'','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = << 85828009 | Autoimmune disease |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',100,'','','N12X AND M359','Tubulo-interstitial nephritis not spec as acute or chronic AND Systemic involvement of connective tissue, unspecified','TIN IN AI immune immunity'),('1948','Tubulo interstitial nephritis with uveitis (TINU) - no histology',0,0,0,1,0,0,0,0,0,'To use this PRD, there must be evidence of past or current uveitis.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = 128473001 | Uveitis |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',101,'','http://omim.org/entry/607665','N12X AND H209','Tubulo-interstitial nephritis not spec as acute or chronic AND Iridocyclitis, unspecified','TIN IN TINU eye ophthalmic '),('1953','Tubulo interstitial nephritis with uveitis (TINU) - histologically proven',1,0,0,1,0,0,0,0,0,'To use this PRD, there must be evidence of past or current uveitis.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = 128473001 | Uveitis |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',102,'','http://omim.org/entry/607665','N12X AND H209','Tubulo-interstitial nephritis not spec as acute or chronic AND Iridocyclitis, unspecified','TIN IN TINU eye ophthalmic '),('1969','Renal sarcoidosis - no histology',0,1,0,0,0,0,0,0,0,'Any standard method of diagnosing sarcoid is acceptable.','37061001','Granulomatous sarcoid nephropathy (disorder)','37061001 | Granulomatous sarcoid nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',103,'','for background information see:\nhttp://omim.org/entry/181000','D868D','Sarcoidosis of other and combined sites','TIN IN granuloma granulomatous giant'),('1976','Renal sarcoidosis - histologically proven',1,1,0,0,0,0,0,0,0,'Histology from non renal tissue is acceptable.','37061001','Granulomatous sarcoid nephropathy (disorder)','37061001 | Granulomatous sarcoid nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',104,'','for background information see:\nhttp://omim.org/entry/181000','D868D','Sarcoidosis of other and combined sites','TIN IN granuloma granulomatous giant'),('1982','Aristolochic acid nephropathy (Balkan / Chinese herb / endemic nephropathy) - no histology',0,1,0,0,0,0,0,0,0,'','236514003','Toxic nephropathy (disorder)','236514003 | Toxic nephropathy |:\n246075003 | causative agent | = 8423004 | Aristolochia |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',94,'Balkan nephropathy',105,'','for background information see:\nhttp://omim.org/entry/124100','N144','Toxic nephropathy, not elsewhere classified','TIN IN'),('1995','Aristolochic acid nephropathy (Balkan / Chinese herb / endemic nephropathy) - histologically proven',1,1,0,0,0,0,0,0,0,'Compatible renal histopathology evidence is required to use this PRD but alone is insufficient because the appearances are not diagnostic.\nA clinical history consistent with exposure to Aristolochia or residence in an area in which this condition is endemic is also necessary.','236514003','Toxic nephropathy (disorder)','236514003 | Toxic nephropathy |:\n246075003 | causative agent | = 8423004 | Aristolochia (organism) |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',94,'Balkan nephropathy',106,'','for background information see:\nhttp://omim.org/entry/124100','N144','Toxic nephropathy, not elsewhere classified','TIN IN'),('2005','Drug-induced tubulointerstitial nephritis  - no histology',0,1,0,0,0,0,0,0,0,'','439990003','Drug-induced tubulointerstitial nephritis (disorder)','439990003 | Drug-induced tubulointerstitial nephritis |:\n418775008 | Finding method | = ! << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',107,'','','N142','Nephropathy induced by unspec drug medicament or biol subs','TIN IN drug medicine'),('2014','Drug-induced tubulointerstitial nephritis - histologically proven',1,1,0,0,0,0,0,0,0,'','439990003','Drug-induced tubulointerstitial nephritis (disorder)','439990003 | Drug-induced tubulointerstitial nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',108,'','','N142','Nephropathy induced by unspec drug medicament or biol subs','TIN IN drug medicine'),('2022','Nephropathy due to analgesic drugs - no histology',0,1,0,0,0,0,0,1,0,'','59400006','Analgesic nephropathy (disorder)','59400006 | Analgesic nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',31,'Nephropathy (interstitial) due to analgesic drugs',109,'','','N140','Analgesic nephropathy','TIN IN drug medicine'),('2033','Nephropathy due to analgesic drugs - histologically proven',1,1,0,0,0,0,0,1,0,'','59400006','Analgesic nephropathy (disorder)','59400006 | Analgesic nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',31,'Nephropathy (interstitial) due to analgesic drugs',110,'','','N140','Analgesic nephropathy','TIN IN drug medicine'),('2046','Nephropathy due to ciclosporin - no histology',0,1,0,0,0,0,0,0,0,'','519481000000106','Nephropathy induced by ciclosporin (disorder)','519481000000106 | Nephropathy due to ciclosporin |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',33,'Nephropathy (interstitial) due to cyclosporin A',111,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2051','Nephropathy due to ciclosporin - histologically proven',1,1,0,0,0,0,0,0,0,'','519481000000106','Nephropathy induced by ciclosporin (disorder)','519481000000106 | Nephropathy due to ciclosporin |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',33,'Nephropathy (interstitial) due to cyclosporin A',112,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2067','Nephropathy due to tacrolimus - no histology',0,1,0,0,0,0,0,0,0,'','519491000000108','Nephropathy induced by tacrolimus (disorder)','519491000000108 | Nephropathy due to tacrolimus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',113,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2079','Nephropathy due to tacrolimus - histologically proven',1,1,0,0,0,0,0,0,0,'','519491000000108','Nephropathy induced by tacrolimus (disorder)','519491000000108 | Nephropathy due to tacrolimus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',114,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2080','Nephropathy due to aminoglycosides - no histology',0,1,0,0,0,0,0,0,0,'','519501000000102','Nephropathy induced by aminoglycosides (disorder)','519501000000102 | Nephropathy due to aminoglycosides |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',115,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine gentamicin netilmicin amikacin'),('2098','Nephropathy due to aminoglycosides - histologically proven',1,1,0,0,0,0,0,0,0,'','519501000000102','Nephropathy induced by aminoglycosides (disorder)','519501000000102 | Nephropathy due to aminoglycosides |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',116,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine gentamicin netilmicin amikacin'),('2108','Nephropathy due to amphotericin - no histology',0,1,0,0,0,0,0,0,0,'','519511000000100','Nephropathy induced by amphotericin (disorder)','519511000000100 | Nephropathy due to amphotericin |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',117,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine antifungal fungal fungus candida'),('2112','Nephropathy due to amphotericin - histologically proven',1,1,0,0,0,0,0,0,0,'','519511000000100','Nephropathy induced by amphotericin (disorder)','519511000000100 | Nephropathy due to amphotericin |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',118,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine antifungal fungal fungus candida'),('2120','Nephropathy due to cisplatin - no histology',0,1,0,0,0,0,0,0,0,'','53556002','Cis-platinum nephropathy (disorder)','53556002 | Cis-platinum nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',32,'Nephropathy (interstitial) due to cis-platinum',119,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine cancer malignancy'),('2131','Nephropathy due to cisplatin - histologically proven',1,1,0,0,0,0,0,0,0,'','53556002','Cis-platinum nephropathy (disorder)','53556002 | Cis-platinum nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',32,'Nephropathy (interstitial) due to cis-platinum',120,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine cancer malignancy'),('2149','Nephropathy due to lithium - no histology',0,1,0,0,0,0,0,0,0,'','4390004','Lithium nephropathy (disorder)','4390004 | Lithium nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',121,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine lithium bipolar'),('2154','Nephropathy due to lithium - histologically proven',1,1,0,0,0,0,0,0,0,'','4390004','Lithium nephropathy (disorder)','4390004 | Lithium nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',122,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine lithium bipolar'),('2165','Lead induced nephropathy - no histology',0,0,0,0,1,0,0,0,0,'To use this PRD there should be biochemical evidence of chronic lead intoxication\n(eg EDTA chelation) plus hyperuricaemia and hypertension.\nA single measurement of blood lead is insufficient.','519521000000106','Nephropathy induced by lead (disorder)','519521000000106 | Nephropathy induced by lead |:\n418775008 | Finding method | =\n! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',34,'Lead induced nephropathy (interstitial)',123,'','','N143','Nephropathy induced by heavy metals','TIN IN drug Pb metal heavy'),('2177','Lead induced nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'To use this PRD there should be biochemical evidence of chronic lead intoxication\n(eg EDTA chelation) plus hyperuricaemia and hypertension.\nA single measurement of blood lead is insufficient.','519521000000106','Nephropathy induced by lead (disorder)','519521000000106 | Nephropathy induced by lead |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',34,'Lead induced nephropathy (interstitial)',124,'','','N143','Nephropathy induced by heavy metals','TIN IN drug Pb metal heavy'),('2183','Acute urate nephropathy - no histology',0,1,0,0,1,0,0,0,0,'','236496000','Acute urate nephropathy (disorder)','236496000 | Acute urate nephropathy |:\n418775008 | Finding method | =\n!< 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',125,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','E790','Hyperuricaem without sign inflamm arthritis+tophaceous dis','TIN IN uric '),('2196','Acute urate nephropathy - histologically proven',1,1,0,0,1,0,0,0,0,'','236496000','Acute urate nephropathy (disorder)','236496000 | Acute urate nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',126,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','E790','Hyperuricaem without sign inflamm arthritis+tophaceous dis','TIN IN uric '),('2203','Chronic urate nephropathy - histologically proven',1,1,0,0,0,0,0,0,0,'Requires the presence of parenchymal kidney damage resulting from urate deposits.\nShould be distinguished from Uromodulin-associated nephropathy (PRD 2827).','190829000','Chronic urate nephropathy (disorder)','418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',128,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','TIN IN uric '),('2219','Radiation nephritis',0,1,0,0,0,0,0,0,0,'To use this PRD, there should be a history of radiation with the radiation field including the kidneys.','7725007','Radiation nephritis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',130,'','','T66X','Unspecified effects of radiation','TIN IN X ray X-Ray Xray ionising ionisation isotope implant radiology radiograph'),('2226','Renal / perinephric abscess',0,0,0,0,0,0,0,1,0,'','3321001\nOR\n80640009','Renal abscess (disorder)\nOR\nPerirenal abscess (disorder)','3321001 | Renal abscess |\nOR\n80640009 | Perirenal abscess |','Tubulo-interstitial disease',99,'Other identified renal disorders',131,'','','N151','Renal and perinephric abscess','TIN IN infection bacteria'),('2235','Renal tuberculosis',0,1,0,0,0,0,0,1,0,'A diagnosis of past or present tuberculosis of the kidney must have been made.','44323002','Tuberculosis of kidney (disorder)','','Tubulo-interstitial disease',91,'Tuberculosis',132,'','for background information see:\nhttp://omim.org/entry/607948','A181D','Tuberculosis of genitourinary system','TB Koch '),('2242','Leptospirosis',0,0,0,0,0,0,0,0,0,'A diagnosis of leptospirosis must have been made.\nThe diagnosis is normally supported by serology, culture or PCR.','77377001','Leptospirosis (disorder)','<< 77377001 | Leptospirosis |:\n418775008 | Finding method | = (<< 68793005 | Serologic test\n |)\nOR (<< 104178000 | Bacterial culture |)\nOR (<< 9718006 | Polymerase chain reaction analysis |) ','Tubulo-interstitial disease',99,'Other identified renal disorders',133,'','','A279','Leptospirosis, unspecified',''),('2257','Hantavirus nephropathy',1,1,0,0,0,0,0,0,0,'Diagnosis of hantavirus infection should have been confirmed by PCR.','102455002','Hemorrhagic nephroso-nephritis (disorder)','102455002 | Hemorrhagic nephroso-nephritis |:\nfinding method = 9718006 | Polymerase chain reaction analysis |','Tubulo-interstitial disease',99,'Other identified renal disorders',134,'','','A985D','Haemorrhagic fever with renal syndrome','virus viral'),('2261','Xanthogranulomatous pyelonephritis',0,0,0,0,0,0,0,1,0,'','38898003','Xanthogranulomatous pyelonephritis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',135,'','','N119','Chronic tubulo-interstitial nephritis, unspecified','XPN CPN PN TIN'),('2274','Nephropathy related to HIV - no histology',0,0,0,0,0,0,0,0,0,'This PRD requires evidence of HIV infection.','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n47429007 | associated with | = << 86406008 | Human immunodeficiency virus infection |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',136,'','for background information see:\nhttp://omim.org/entry/609423\nhttp://omim.org/entry/612551\nhttp://omim.org/entry/607832','N289 AND B24X','Disorder of kidney and ureter, unspecified AND Unspecified human immunodefiency virus [HIV] disease','virus viral'),('2288','Nephropathy related to HIV - histologically proven',1,0,0,0,0,0,0,0,0,'','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n47429007 | associated with | = << 86406008 | Human immunodeficiency virus infection |\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',137,'','for background information see:\nhttp://omim.org/entry/609423\nhttp://omim.org/entry/612551\nhttp://omim.org/entry/607832','N289 AND B24X','Disorder of kidney and ureter, unspecified AND Unspecified human immunodefiency virus [HIV] disease','virus viral'),('2290','Schistosomiasis',0,0,0,0,0,0,0,1,0,'This PRD requires microbiological evidence of urinary tract infection with Schistosomiasis.\nThis PRD should not be used for immune complex nephropathy secondary to Schistosomiasis.','236706006','Urinary schistosomiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',138,'','for background information see:\nhttp://omim.org/entry/181460','B650D','Schistosom due Schis haematobium [urin schistosom]',''),('2300','Other specific infection',0,0,0,0,0,0,0,0,0,'This PRD requires evidence of infection with the putative organism.\nThis PRD should only be used if there is evidence of infection directly causing renal impairment not listed elsewhere.','40733004','Infectious disease (disorder)','<< 40733004 | Infectious disease |','Tubulo-interstitial disease',99,'Other identified renal disorders',139,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('2316','Diabetic nephropathy in type I diabetes - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of type I diabetes mellitus must have been made.\nFor a diagnosis of diabetic nephropathy without evidence from renal histopathology,\nproteinuria must have been documented at some point in the patients history.\nA PRD of diabetic nephropathy is not mandatory in the presence of DM with proteinuria and alternative diagnoses can be considered.\nIn the absence of a renal histopathology the differential diagnosis will include\nChronic kidney disease (CKD) / chronic renal failure (CRF)  aetiology uncertain / unknown - (with or without histology),\n ischaemic nephropathy, renovascular disease and atheroembolic renal disease.\n','421893009','Renal disorder associated with type I diabetes mellitus (disorder)','<< 421893009 | Renal disorder associated with type I diabetes mellitus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',80,'Diabetes glomerulosclerosis or diabetic nephropathy - Type I',140,'','for background information see:\nhttp://omim.org/entry/222100','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2328','Diabetic nephropathy in type I diabetes - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of type I diabetes mellitus must have been made.\nHistopathological features most compatible with diabetic nephropathy must be present.','421893009','Renal disorder associated with type I diabetes mellitus (disorder)','<< 421893009 | Renal disorder associated with type I diabetes mellitus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',80,'Diabetes glomerulosclerosis or diabetic nephropathy - Type I',141,'','for background information see:\nhttp://omim.org/entry/222100','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2337','Diabetic nephropathy in type II diabetes - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of type II diabetes mellitus must have been made.\nFor a diagnosis of diabetic nephropathy, proteinuria must have been documented at some point in the patients history .\nA PRD of diabetic nephropathy is not mandatory in the presence of DM with proteinuria and alternative diagnoses can be considered.\nIn the absence of a renal histology the differential diagnosis will include\nChronic kidney disease (CKD) / chronic renal failure (CRF) aetiology uncertain / unknown -(with or without a histology),\n ischaemic nephropathy, renovascular disease and atheroembolic renal disease.\nDistinguish from: Inherited / genetic diabetes mellitus type II.\n','420279001','Renal disorder associated with type II diabetes mellitus (disorder)','<< 420279001 | Renal disorder associated with type II diabetes mellitus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy | ','Systemic diseases affecting the kidney',81,'Diabetes glomerulosclerosis or diabetic nephropathy - Type II',142,'','for background information see:\nhttp://omim.org/entry/125853','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2344','Diabetic nephropathy in type II diabetes - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of type II diabetes mellitus must have been made.\nHistopathological features most compatible with diabetic nephropathy must be present.','420279001','Renal disorder associated with type II diabetes mellitus (disorder)','<< 420279001 | Renal disorder associated with type II diabetes mellitus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',81,'Diabetes glomerulosclerosis or diabetic nephropathy - Type II',143,'','for background information see:\nhttp://omim.org/entry/125853','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN Glucose hyperglycaemia'),('2359','Chronic Hypertensive nephropathy - no histology',0,1,0,1,0,0,0,0,0,'The patient must have a history of hypertension.\nOther PRDs will usually have been considered before accepting this PRD.','38481006','Hypertensive renal disease (disorder)','(<< 38481006 | Hypertensive renal disease |:\n263502005 | Clinical course | = 90734009 | Chronic |\n, 418775008 | Finding method | = ! << 7246002 | Kidney biopsy |)\nAND\n(! <38481006 | Hypertensive renal','Systemic diseases affecting the kidney',72,'Renal vascular disease due to hypertension',144,'','for background information see:\nhttp://omim.org/entry/145500 about essential hypertension\nhttp://omim.org/entry/161900 about progression of renal failure','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2363','Chronic Hypertensive nephropathy - histologically proven',1,1,0,1,0,0,0,0,0,'','38481006','Hypertensive renal disease (disorder)','(<< 38481006 | Hypertensive renal disease |:\n263502005 | Clinical course | = 90734009 | Chronic |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |)\nAND\n(! <38481006 | Hypertensive renal d','Systemic diseases affecting the kidney',72,'Renal vascular disease due to hypertension',145,'','for background information see:\nhttp://omim.org/entry/145500 about essential hypertension\nhttp://omim.org/entry/161900 about progression of renal failure','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2371','Malignant hypertensive nephropathy / accelerated hypertension nephropathy - no histology',0,0,0,1,1,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','65443008','Malignant hypertensive renal disease (disorder)','<< 65443008 | Malignant hypertensive renal disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',71,'Renal vascular disease due to malignant hypertension',146,'','','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2385','Malignant hypertensive nephropathy / accelerated hypertension nephropathy - histologically proven',1,0,0,1,0,0,0,0,0,'Lacks evidence of systemic sclerosis.','65443008','Malignant hypertensive renal disease (disorder)','<< 65443008 | Malignant hypertensive renal disease |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',71,'Renal vascular disease due to malignant hypertension',147,'','','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2392','Ageing kidney - no histology',0,1,0,1,1,0,0,1,0,'If the patient develops stage 4 CKD, consider a PRD of ischaemic nephropathy.','445108007','Age related reduction of renal function (finding)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',148,'','for background information see:\nhttp://omim.org/entry/502000','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','senile age related senescence  old geriatric'),('2407','Ischaemic nephropathy - no histology',0,1,0,0,1,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','519581000000107','Ischaemic nephropathy (disorder)','42752001 | due to | = 52674009 | Ischemia (disorder) |\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',149,'','','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli'),('2411','Ischaemic nephropathy / microvascular disease - histologically proven',1,1,0,0,0,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','519581000000107','Ischaemic nephropathy (disorder)','519581000000107 | Ischaemic nephropathy |:\nOR\n(< 16934004 | Renal vascular disorder |)\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',150,'','','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli'),('2424','Renal artery stenosis',0,0,0,0,0,0,0,1,0,'','302233006','Renal artery stenosis (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',151,'','for background information see:\nhttp://omim.org/entry/135580\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli stenosed stenotic '),('2430','Atheroembolic renal disease - no histology',0,1,0,1,0,0,0,1,0,'Eosinophilia or complement consumption are suggestive.','51677000','Atheroembolism of renal arteries (disorder)','51677000 | Atheroembolism of renal arteries |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',152,'','for background information see:\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli'),('2448','Atheroembolic renal disease - histologically proven',1,0,0,0,0,0,0,0,0,'','51677000','Atheroembolism of renal arteries (disorder)','51677000 | Atheroembolism of renal arteries |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',153,'','for background information see:\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli cleft ghost'),('2453','Fibromuscular dysplasia of renal artery',0,0,0,0,0,0,0,1,0,'','2900003','Hyperplasia of renal artery (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',154,'','http://omim.org/entry/135580','I773','Arterial fibromuscular dysplasia','vascular ischaemia PVD stenosed stenotic FMD'),('2469','Renal arterial thrombosis / occlusion',0,0,0,0,0,0,0,1,0,'A source of emboli or a hypercoagulable state are usually identified.','236488005','Renal artery occlusion (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',155,'','for background information see:\nhttp://omim.org/entry/188050','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli cleft ghost occluded artery '),('2476','Renal vein thrombosis',0,0,0,0,0,0,0,1,0,'If there is an underlying nephrotic syndrome causing the thrombosis, the underlying renal diagnosis should take precedence.','15842009','Thrombosis of renal vein (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',156,'','for background information see:\nhttp://omim.org/entry/188050','I823','Embolism and thrombosis of renal vein','venous thrombus thrombosed RVT clot'),('2482','Cardiorenal syndrome',0,1,0,0,1,0,0,0,0,'Intractable heart failure with severe renal impairment without evidence of parenchymal kidney disease or renal vascular disease.\nCorresponds to CardioRenal Syndrome type 2 as described by the Acute Dialysis Quality Initiative group\n(Ronco C et al J Am Coll Cardiol 2008; 52; 1527-1539).\n','445236007','Cardiorenal syndrome (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',157,'','for background information see:\nhttp://omim.org/entry/232200\n','I139','Hypertensive heart and renal disease, unspecified','heart cardiac '),('2495','Hepatorenal syndrome',0,1,0,0,1,0,0,0,0,'A renal biopsy is not normally done in this clinical situation.\nRenal histology is essentially normal.\nPatients with liver disease are predisposed to IgA nephropathy and\nwhere this is found, the alternative PRD IgA nephropathy secondary to liver cirrhosis\nshould be considered.','51292008','Hepatorenal syndrome (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',158,'','','K767','Hepatorenal syndrome','liver hepatic '),('2509','Renal amyloidosis',1,0,0,0,0,0,0,0,0,'Histological proof of amyloid from another tissue is an adequate substitute for renal histology.','48713002','Amyloid nephropathy (disorder)','','Systemic diseases affecting the kidney',83,'Amyloid',159,'','','E854','Organ-limited amyloidosis','amyloid'),('2513','AA amyloid secondary to chronic inflammation',1,0,0,0,0,0,0,0,0,'Histological proof of amyloid from another tissue is an adequate substitute for renal histology.','274945004','AA amyloidosis (disorder)','274945004 | AA amyloidosis |:\nassociated morphology = 84499006 | Chronic inflammation |','Systemic diseases affecting the kidney',83,'Amyloid',160,'','','E853','Secondary systemic amyloidosis','amyloidosis'),('2521','AL amyloid secondary to plasma cell dyscrasia',1,0,0,0,1,0,0,0,0,'Evidence of monoclonal light chain (biochemical or immunological).\nHistological proof of amyloid from another tissue is a substitute for renal histology.\nThere should be evidence of a monoclonal light chain (eg biochemical or immunological).','23132008','AL amyloidosis (disorder)','23132008 | AL amyloidosis |:\nassociated morphology = 127580003 | Plasma cell neoplasm |','Systemic diseases affecting the kidney',83,'Amyloid',161,'','http://omim.org/entry/254500','E859','Amyloidosis, unspecified','amyloidosis'),('2532','Familial amyloid secondary to protein mutations - no histology',0,0,1,0,0,0,0,0,0,'Amyloid should have been demonstrated histologically in at least one affected family member.','66451004','Familial visceral amyloidosis, Ostertag type (disorder)','<< 66451004 | Familial renal amyloidosis |:\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',162,'','http://omim.org/entry/105200','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2545','Familial amyloid secondary to protein mutations - histologically proven',1,0,1,0,0,0,0,0,0,'','66451004','Familial visceral amyloidosis, Ostertag type (disorder)','<< 66451004 | Familial renal amyloidosis |:\n, 418775008 | Finding method | =  << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',163,'','http://omim.org/entry/105200','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2550','Familial AA amyloid secondary to familial Mediterranean fever / TRAPS (Hibernian fever) - no histology',0,1,1,0,0,0,0,0,0,'','367528006','Amyloid of familial Mediterranean fever (disorder)','367528006 | Amyloid of familial Mediterranean fever |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',164,'','http://omim.org/entry/142680\nfor background information see:\nhttp://omim.org/entry/608107','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2566','Familial AA amyloid secondary to familial Mediterranean fever / TRAPS (Hibernian fever) - histologically proven',1,1,1,0,0,0,0,0,1,'','367528006','Amyloid of familial Mediterranean fever (disorder)','367528006 | Amyloid of familial Mediterranean fever |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',165,'','http://omim.org/entry/142680\nfor background information see:\nhttp://omim.org/entry/608107','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2578','Myeloma kidney - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of myeloma must have been made with\nbiochemical, haematological or immunological confirmation.','32278006','Myeloma kidney (disorder)','32278006 | Myeloma kidney |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',166,'','for background information see:\nhttp://omim.org/entry/254500','C900D','Multiple myeloma','multiple MM bence jones'),('2584','Myeloma cast nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of myeloma must have been made with\nbiochemical, haematological or immunological confirmation.','32278006','Myeloma kidney (disorder)','32278006 | Myeloma kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',167,'','for background information see:\nhttp://omim.org/entry/254500','C900D','Multiple myeloma','multiple MM bence jones fracture fractured'),('2597','Light chain deposition disease',1,0,0,0,1,0,0,0,0,'A diagnosis of light chain disease must have been made with\nbiochemical, haematological or immunological confirmation.','373604002','Light chain deposition disease (disorder)','','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',168,'','for background information see:\nhttp://omim.org/entry/254500','D808','Other immunodeficiencies with predominantly antibody defects','LCD LCDD immunoglobulin dyscrasia'),('2606','Immunotactoid / fibrillary nephropathy',1,0,0,0,0,0,0,0,0,'','73305009','Fibrillary glomerulonephritis (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',169,'','for background information see:\nhttp://omim.org/entry/137950','N059','Unspecified nephritic syndrome, unspecified',''),('2610','Haemolytic uraemic syndrome (HUS) - diarrhoea associated',0,1,0,0,0,0,0,0,0,'This PRD is usually associated with E. coli 0157 infection.','373421000','Diarrhea-associated hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',170,'','','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2623','Atypical haemolytic uraemic syndrome (HUS) - diarrhoea negative',1,1,0,0,0,0,0,0,0,'If there is evidence of a genetic mutation, or of a family history of haemolytic uraemic syndrome, then the code\nCongenital haemolytic uraemic syndrome or Familial haemolytic uraemic syndrome should be used.','373422007','Diarrhea-negative hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',171,'','http://omim.org/entry/235400 about AHUS1\nhttp://omim.org/entry/612922 about AHUS2\nhttp://omim.org/entry/612923 about AHUS3\nhttp://omim.org/entry/612924 about AHUS4\nhttp://omim.org/entry/612925 about A','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2634','Thrombotic thrombocytopenic purpura (TTP)',1,1,0,0,1,0,0,0,1,'ADAMTS13 deficiency or autoantibodies to ADAMTS13 may be demonstrated (not mandatory).','78129009','Thrombotic thrombocytopenic purpura (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',172,'','http://omim.org/entry/274150','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('2647','Haemolytic uraemic syndrome (HUS) secondary to systemic disease',0,0,0,0,1,0,0,0,0,'This PRD includes HUS secondary to drugs or malignancies.','111407006','Hemolytic uremic syndrome (disorder)','111407006 | Hemolytic uremic syndrome |:\n42752001 | due to | = 56019007 | Systemic disease |','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',173,'','','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2652','Congenital haemolytic uraemic syndrome (HUS) ',0,0,0,0,1,0,0,0,0,'','444976001','Congenital hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',174,'','http://omim.org/entry/274150','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2668','Familial haemolytic uraemic syndrome (HUS)',0,0,1,0,1,0,0,0,1,'This PRD includes complement factor H or I abnormalities.','373420004','Upshaw-Schulman syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',175,'','http://omim.org/entry/274150','M311','Thrombotic microangiopathy','haemolysis fragment'),('2675','Familial thrombotic thrombocytopenic purpura (TTP)',0,0,1,0,0,0,0,0,1,'ADAMTS13 deficiency (not mandatory).','373420004','Upshaw-Schulman syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',176,'','http://omim.org/entry/274150','M311','Thrombotic microangiopathy',''),('2681','Nephropathy due to pre-eclampsia / eclampsia',0,1,0,0,1,0,0,0,0,'','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n42752001 | due to | = (<< 398254007 | Pre-eclampsia |\nOR << 15938005 | Eclampsia |)','Systemic diseases affecting the kidney',99,'Other identified renal disorders',177,'','for background information see:\nhttp://omim.org/entry/189800 about PEE1\nhttp://omim.org/entry/609402 about PEE2\nhttp://omim.org/entry/609403 about PEE3\nhttp://omim.org/entry/609404 about PEE4\n','N289 AND O159\nOR\nN289 AND O149\n','Disorder of kidney and ureter, unspecified AND Eclampsia, unspecified as to time period\nOR\nDisorder of kidney and ureter, unspecified AND Pre-eclampsia, unspecified','pregnant pregnancy'),('2699','Sickle cell nephropathy - no histology',0,1,0,0,0,0,0,0,0,'','13886001','Sickle cell nephropathy (disorder)','13886001 | Sickle cell nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',99,'Other identified renal disorders',178,'','for background information see:\nhttp://omim.org/entry/603903','D571D','Sickle-cell anaemia without crisis',''),('2702','Sickle cell nephropathy - histologically proven',1,1,0,0,0,0,0,0,0,'','13886001','Sickle cell nephropathy (disorder)','13886001 | Sickle cell nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',99,'Other identified renal disorders',179,'','for background information see:\nhttp://omim.org/entry/603903','D571D','Sickle-cell anaemia without crisis',''),('2718','Autosomal dominant (AD) polycystic kidney disease',0,0,1,0,0,0,0,1,1,'','28728008','Polycystic kidney disease, adult type (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',180,'','for background information see:\nhttp://omim.org/entry/173900 about PKD1\nhttp://omim.org/entry/613095 about PKD2\nhttp://omim.org/entry/600666 about PKD3','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2725','Autosomal dominant (AD) polycystic kidney disease type I',0,0,1,0,0,0,0,1,1,'','253878003','Adult type polycystic kidney disease type 1 (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',181,'','http://omim.org/entry/173900 about PKD1\nhttp://omim.org/entry/601313 about polycystin 1\nhttp://omim.org/entry/606702 about PKDH1','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2739','Autosomal dominant (AD) polycystic kidney disease type II',0,0,1,0,0,0,0,1,1,'','253879006','Adult type polycystic kidney disease type 2 (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',182,'','http://omim.org/entry/613095 about PKD2\nhttp://omim.org/entry/173910 about polycystin 2','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2741','Autosomal recessive (AR) polycystic kidney disease',0,0,1,0,0,0,0,1,1,'','28770003','Polycystic kidney disease, infantile type (disorder)','','Familial / hereditary nephropathies',42,'Polycystic kidneys; infantile (recessive)',183,'','http://omim.org/entry/263200','Q611','Polycystic kidney, infantile type','CK PCK ARPKD ARPCKD PCKD PKD APKD cysts'),('2756','Alport syndrome - no histology',0,1,1,1,0,0,0,0,1,'','399340005','Hereditary nephritis (disorder)','399340005 | Hereditary nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',51,'Hereditary nephritis with nerve deafness (Alports Syndrome)',184,'','http://omim.org/entry/301050 about alport syndrome, X-linked; ATS\nhttp://omim.org/entry/303630 about collagen, type IV, alpha-5; COL4A5\nhttp://omim.org/entry/203780 about alport syndrome, autosomal re','N079','Unspecified morphological changes','thin deaf split '),('2760','Alport syndrome - histologically proven',1,1,1,1,0,0,0,0,1,'','399340005','Hereditary nephritis (disorder)','399340005 | Hereditary nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',51,'Hereditary nephritis with nerve deafness (Alports Syndrome)',185,'','http://omim.org/entry/301050 about alport syndrome, X-linked; ATS\nhttp://omim.org/entry/303630 about collagen, type IV, alpha-5; COL4A5\nhttp://omim.org/entry/203780 about alport syndrome, autosomal re','N079','Unspecified morphological changes','thin deaf split '),('2773','Benign familial haematuria',0,1,1,0,0,0,1,0,0,'The clinical history can be useful because it does NOT suggest any other nephropathy.\nHaematuria can be demonstrated by any means eg dip stick or urine microscopy.','236421001','Benign familial hematuria (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',186,'','http://omim.org/entry/141200','N029','Recurrent and persistent haematuria, unspecified',''),('2787','Thin basement membrane disease',1,0,0,0,0,0,0,0,0,'','236418003','Thin basement membrane disease (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',187,'','http://omim.org/entry/141200','N050','Unspecified nephritic syndrome, minor glomerular abnormality',''),('2794','Cystic kidney disease',0,0,0,0,0,0,0,1,0,'','82525005','Congenital cystic kidney disease (disorder)','','Familial / hereditary nephropathies',40,'Cystic kidney disease - type unspecified',188,'','','Q619','Cystic kidney disease, unspecified','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2804','Medullary cystic kidney disease type I',0,1,1,1,1,0,1,1,1,'Usually autosomal dominant family history, bland urine and without heavy proteinuria.','444699000','Medullary cystic kidney disease type 1 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',189,'','http://omim.org/entry/174000','Q615','Medullary cystic kidney','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2815','Medullary cystic kidney disease type II',1,0,1,1,0,0,0,1,1,'Genetically identical to uromodulin-associated nephropathy, but characterised by presence of cysts and gout.','445503007','Medullary cystic kidney disease type 2 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',190,'','http://omim.org/entry/603860\n','Q615','Medullary cystic kidney','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2827','Uromodulin-associated nephropathy (familial juvenile hyperuricaemic nephropathy)',1,0,1,1,0,0,0,1,1,'Autosomal dominant inheritance, high incidence of gout, but no cysts.','46785007','Familial juvenile gout (disorder)','','Familial / hereditary nephropathies',49,'Cystic kidney disease - other specified type',191,'','http://omim.org/entry/162000 about HNFJ1\nhttp://omim.org/entry/613092 about HNFJ2\nhttp://omim.org/entry/614227 about HNFJ3\nfor background information see:\nhttp://omim.org/entry/191845 about uromodulin','E798','Other disorders of purine and pyrimidine metabolism',' '),('2836','Nephronophthisis',0,1,1,0,0,0,0,0,0,'','204958008','Nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',192,'','for background information see:\nhttp://omim.org/entry/609254 about senior-loken syndrome 5; SLSN5 and NPHP\nhttp://omim.org/entry/612013 about coiled-coil and C2 domains-containing protein 2A; CC2D2A\nh','Q618','Other cystic kidney diseases',''),('2843','Nephronophthisis - type 1 (juvenile type)',0,1,1,0,0,0,0,0,1,'','444830001','Juvenile nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',193,'','http://omim.org/entry/256100 about NPHP1\nhttp://omim.org/entry/607100 about nephrocystin 1; NPHP','Q618','Other cystic kidney diseases',''),('2858','Nephronophthisis - type 2 (infantile type)',0,0,1,0,0,0,0,0,1,'','444558002','Infantile nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',194,'','http://omim.org/entry/602088 about NPHP2','Q618','Other cystic kidney diseases',''),('2862','Nephronophthisis - type 3 (adolescent type)',0,1,1,0,0,0,0,0,1,'','444749006','Adolescent nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',195,'','http://omim.org/entry/604387 about NPHP3\nhttp://omim.org/entry/608002 about nephrocystin 3; NPHP3','Q618','Other cystic kidney diseases',''),('2870','Nephronophthisis - type 4 (juvenile type)',0,1,1,0,0,0,0,0,1,'','446989009','Nephronophthisis type 4 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',196,'','http://omim.org/entry/606966 about NPHP4\nhttp://omim.org/entry/607215 about nephrocystin 4; NPHP4','Q618','Other cystic kidney diseases',''),('2889','Nephronophthisis - type 5',0,1,1,0,0,0,0,0,1,'','446991001','Nephronophthisis type 5 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',197,'','','Q618','Other cystic kidney diseases',''),('2891','Nephronophthisis - type 6',0,1,1,0,0,0,0,0,1,'','447335007','Nephronophthisis type 6 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',198,'','','Q618','Other cystic kidney diseases',''),('2901','Primary Fanconi syndrome',0,0,0,0,1,0,0,0,0,'Clinical syndrome associated with genetic mutations.','236466005','Congenital Fanconi syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',199,'','http://omim.org/entry/134600 about FRTS1\nhttp://omim.org/entry/613388 about FRTS2','E720','Disorders of amino-acid transport',''),('2917','Tubular disorder as part of inherited metabolic diseases',0,0,1,0,1,0,0,0,0,'','197744007','Renal tubulo-interstitial disorders in metabolic diseases (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',200,'','http://omim.org/entry/551200 about chronic tubulointerstitial nephropathy','N163A','Renal tubulo-interstitial disorders in metabolic diseases','TIN IN'),('2929','Dent disease',0,1,1,0,1,0,0,1,1,'X linked recessive nephrolithiasis + mutation in gene CLCN5.\nClinical: low MW proteinuria, raised urinary calcium:creatinine ratio.\nMay have nephrocalcinosis, nephrolithiasis, hypophosphatemic rickets.','444645005','Dents disease (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',201,'','http://omim.org/entry/300009','N398','Other specified disorders of urinary system',''),('2938','Lowe syndrome (oculocerebrorenal syndrome)',0,1,1,1,1,0,0,1,1,'Genetic mutation in OCR1.\nSimilar renal phenotype to that seen in Dent disease.','79385002','Lowe syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',202,'','http://omim.org/entry/309000','E720','Disorders of amino-acid transport',''),('2940','Inherited aminoaciduria',0,0,1,0,1,0,0,0,0,'\n','522601000000103','Inherited aminoaciduria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',203,'','for background information see:\nhttp://omim.org/ search for <amino AND aciduria>','E729','Disorder of amino-acid metabolism, unspecified',''),('2955','Cystinuria',0,1,1,0,1,0,0,1,1,'Urinary cystine concentration / nephrolithiasis / genetic mutations.','85020001','Cystinuria (disorder)','<< 85020001 | Cystinuria |','Familial / hereditary nephropathies',99,'Other identified renal disorders',204,'','http://omim.org/entry/220100\nhttp://omim.org/entry/606407 about hypotonia-cystinuria syndrome','E720','Disorders of amino-acid transport',''),('2964','Cystinosis',0,1,0,1,1,0,0,0,1,'Systemic deposit cystine / mutation in CTNS gene.','190681003','Cystinosis (disorder)','<< 190681003 | Cystinosis |','Familial / hereditary nephropathies',52,'Cystinosis',205,'','http://omim.org/entry/219800\nhttp://omim.org/entry/219900 about cystinosis late-onset juvenile or adolescent nephropathic type','E720','Disorders of amino-acid transport',''),('2972','Inherited renal glycosuria',0,0,1,0,1,0,1,0,0,'','226309007','Familial renal glucosuria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',206,'',' http://omim.org/entry/233100','E748','Other specified disorders of carbohydrate metabolism',''),('2986','Hypophosphataemic rickets X-linked (XL)',0,1,1,1,1,0,0,1,1,'','82236004','Familial x-linked hypophosphatemic vitamin D refractory rickets (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',207,'','http://omim.org/entry/307800 about hypophosphatemic rickets, X-linked dominant; XLHR\nhttp://omim.org/entry/300554 about hypophosphatemic rickets, X-linked recessive\n','E833','Disorders of phosphorus metabolism',''),('2993','Hypophosphataemic rickets autosomal recessive (AR)',0,0,1,0,0,0,0,1,1,'Imaging shows rickets.','90505000','Autosomal recessive hypophosphatemic vitamin D refractory rickets (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',208,'','http://omim.org/entry/241520','E833','Disorders of phosphorus metabolism',''),('3000','Primary renal tubular acidosis (RTA)',0,0,0,0,1,0,0,0,0,'','1776003','Renal tubular acidosis (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',209,'','for background information see:\nhttp://omim.org/entry/267200 about renal tubular acidosis III','N258','Other disorders resulting from impaired renal tubular funct',''),('3016','Proximal renal tubular acidosis (RTA) - type II',0,0,0,0,1,0,0,0,0,'','24790002','Proximal renal tubular acidosis (disorder)','<< 24790002 | Proximal renal tubular acidosis |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',210,'','http://omim.org/entry/179830\nhttp://omim.org/entry/604278 about renal tubular acidosis, proximal, with ocular abnormalities and mental retardation','N258','Other disorders resulting from impaired renal tubular funct',''),('3028','Distal renal tubular acidosis (RTA) - type I',0,0,0,0,1,0,0,1,1,'May have nephrocalcinosis.','236461000','Distal renal tubular acidosis (disorder)','<< 236461000 | Distal renal tubular acidosis |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',211,'','http://omim.org/entry/179800 about renal tubular acidosis, distal, autosomal dominant\nhttp://omim.org/entry/602722 about renal tubular acidosis, distal, autosomal recessive; RTADR\nhttp://omim.org/entr','N258','Other disorders resulting from impaired renal tubular funct',''),('3037','Distal renal tubular acidosis with sensorineural deafness - gene mutations',0,0,1,1,0,0,0,1,1,'May have sensorineural deafness and nephrocalcinosis.','236461000','Distal renal tubular acidosis (disorder)','236461000 | Distal renal tubular acidosis |:\nassociated with = << 60700002 | Sensorineural hearing loss |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',212,'','http://omim.org/entry/267300 about renal tubular acidosis, distal, with progressive nerve deafness','N258 AND H905','Other disorders resulting from impaired renal tubular funct AND Sensorineural hearing loss, unspecified','RTA'),('3044','Nephrogenic diabetes insipidus',0,1,1,0,1,0,0,0,1,'','111395007','Nephrogenic diabetes insipidus (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',213,'','http://omim.org/entry/304800 about diabetes insipidus, nephrogenic, X-linked\nhttp://omim.org/entry/125800 about diabetes insipidus, nephrogenic, autosomal\nhttp://omim.org/entry/221995 about diabetes i','N251','Nephrogenic diabetes insipidus','DI NDI'),('3059','Lesch Nyhan syndrome - hypoxanthine guanine phosphoribosyl transferase deficiency ',0,1,1,1,1,0,0,1,1,'','10406007','Lesch-Nyhan syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',214,'','http://omim.org/entry/300322\nhttp://omim.org/entry/300323\nfor background information see:\nhttp://omim.org/entry/308000\nhttp://omim.org/entry/308950\n','E791','Lesch-Nyhan syndrome','HGPTD'),('3063','Phosphoribosyl pyrophosphate synthetase (PRPPS) superactivity',0,1,0,1,1,0,0,1,1,'','35759001','Ribose-phosphate pyrophosphokinase overactivity (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',215,'','http://omim.org/entry/300661','E798','Other disorders of purine and pyrimidine metabolism',''),('3071','Alagille syndrome',0,1,0,1,0,0,0,1,1,'Renal dysplasia, renal artery stenosis, and cystic kidney disease have been described in this condition.','31742004','Arteriohepatic dysplasia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',216,'','http://omim.org/entry/118450 about alagille syndrome 1; ALGS1\nhttp://omim.org/entry/610205 about alagille syndrome 2; ALGS2','Q447','Other congenital malformations of liver',''),('3085','Bartter syndrome',0,0,0,0,1,0,0,0,1,'','71275003','Pseudoprimary aldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',217,'','http://omim.org/entry/601678 about type 1\nhttp://omim.org/entry/241200 about type 2\nhttp://omim.org/entry/607364 about type 3\nhttp://omim.org/entry/602522 about type 4a\nhttp://omim.org/entry/613090 ab','E268','Other hyperaldosteronism',''),('3092','Gitelman syndrome',0,0,1,0,1,0,0,0,1,'','3188003','Familial hypokalemia-hypomagnesemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',218,'','http://omim.org/entry/263800','E876','Hypokalaemia',''),('3102','Liddle syndrome',0,0,1,0,1,0,0,0,1,'','71275003','Pseudoprimary aldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',219,'','http://omim.org/entry/177200','E268','Other hyperaldosteronism',''),('3118','Apparent mineralocorticoid excess',0,0,0,0,1,0,0,0,0,'','237770005','Syndrome of apparent mineralocorticoid excess (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',220,'','http://omim.org/entry/218030','E278','Other specified disorders of adrenal gland',''),('3125','Glucocorticoid suppressible hyperaldosteronism',0,0,1,0,1,0,0,0,1,'','237743003','Glucocorticoid-suppressible hyperaldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',221,'','http://omim.org/entry/103900','E268','Other hyperaldosteronism',''),('3139','Inherited / genetic diabetes mellitus type II',1,1,1,0,1,0,0,0,1,'Multiple / variable mutations.\nPreviously called Maturity onset diabetes in young people (MODY).\nMODY types 1-6 (HNF-1alpha mutation accounts for 65%).\n','237604008','Diabetes mellitus autosomal dominant type II (disorder)\n','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',222,'','http://omim.org/entry/125853 about DM II and its gene relationships','E139','Other specified diabetes mellitus without complications','DM DN glucose hyperglycaemia insulin'),('3141','Pseudohypoaldosteronism type 1',0,0,1,0,1,0,0,0,0,'','43941006','Pseudohypoaldosteronism, type 1 (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',223,'','http://omim.org/entry/177735 about Pseudohypoaldosteronism type I, autosomal dominant PHA1A\nhttp://omim.org/entry/264350 about Pseudohypoaldosteronism type I, autosomal recessive PHA1B','E274','Other and unspecified adrenocortical insufficiency','aldosterone'),('3156','Pseudohypoaldosteronism type 2 (Gordon syndrome)',0,0,1,0,1,0,0,0,1,'','15689008','Pseudohypoaldosteronism, type 2 (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',224,'','http://omim.org/entry/145260','E878','Other disorders of electrolyte and fluid balance NEC','aldosterone'),('3160','Familial hypocalciuric hypercalcaemia',0,0,1,0,1,0,0,0,1,'','237885008','Familial hypocalciuric hypercalcemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',225,'','http://omim.org/entry/145980 about HHC1\nhttp://omim.org/entry/145981 about HHC2\nhttp://omim.org/entry/600740 about HHC3','E835','Disorders of calcium metabolism','Ca calcium'),('3173','Familial hypercalciuric hypocalcaemia',0,0,1,0,1,0,0,0,1,'','237885008','Familial hypocalciuric hypercalcemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',226,'','http://omim.org/entry/145980\nhttp://omim.org/entry/146200','E835','Disorders of calcium metabolism','Ca calcium'),('3187','Familial hypomagnesaemia',0,0,1,0,1,0,0,0,1,'A number of syndromes with different mutations have been identified.','80710001','Primary hypomagnesemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',227,'','http://omim.org/entry/602014 about HOMG1\nhttp://omim.org/entry/154020 about HOMG2\nhttp://omim.org/entry/248250 about HOMG3\nhttp://omim.org/entry/611718 about HOMG4\nhttp://omim.org/entry/248190 about H','E834','Disorders of magnesium metabolism','Mg magnesium'),('3194','Primary hyperoxaluria',1,0,0,0,1,0,0,1,0,'','17901006','Primary hyperoxaluria (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',228,'','for background information see:\nhttp://omim.org/entry/259900 about hyperoxaluria, primary, type I; HP1\nhttp://omim.org/entry/260000 about hyperoxaluria, primary, type II; HP2\nhttp://omim.org/entry/613','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3207','Primary hyperoxaluria type I',1,0,0,0,1,0,0,1,1,'','65520001','Primary hyperoxaluria type I (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',229,'','http://omim.org/entry/259900','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3211','Primary hyperoxaluria type II',1,0,0,0,1,0,0,1,1,'','40951006','Primary hyperoxaluria, type II (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',230,'','http://omim.org/entry/260000','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3224','Fabry disease - no histology',0,1,1,1,1,1,0,0,1,'','16652001','Fabrys disease (disorder)','16652001 | Fabrys disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',54,'Fabrys disease',232,'','http://omim.org/entry/301500','E752','Other sphingolipidosis',''),('3230','Fabry disease - histologically proven',1,1,1,1,1,1,0,0,1,'','16652001','Fabrys disease (disorder)','16652001 | Fabrys disease |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',54,'Fabrys disease',233,'','http://omim.org/entry/301500','E752','Other sphingolipidosis',''),('3248','Xanthinuria',0,0,0,0,1,0,0,0,1,'','190919008','Xanthinuria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',234,'','http://omim.org/entry/278300 about xanthinuria, type I\nhttp://omim.org/entry/603592 about xanthinuria, type II ','E798','Other disorders of purine and pyrimidine metabolism',''),('3253','Nail-patella syndrome',0,1,1,1,0,0,0,1,1,'','236527004','Nail patella-like renal disease (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',235,'','http://omim.org/entry/161200','N078','Hereditary nephropathy, not elsewhere classified, other','NPS'),('3269','Rubinstein-Taybi syndrome',0,1,1,1,0,0,0,0,1,'','45582004','Rubinstein-Taybi syndrome (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',236,'','http://omim.org/entry/180849 about Rubinstein-Taybi syndrome 1; RSTS1\nhttp://omim.org/entry/613684 about Rubinstein-Taybi syndrome 2; RSTS2','Q872','Cong malformation syndromes predominantly involving limbs',''),('3276','Tuberous sclerosis',0,0,1,1,0,0,0,1,1,'','7199000','Tuberous sclerosis syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',237,'','http://omim.org/entry/191100 about tuberous sclerosis 1; TSC1\nhttp://omim.org/entry/605284 about TSC1 gene; TSC1\nhttp://omim.org/entry/613254 about tuberous sclerosis 2; TSC2\nhttp://omim.org/entry/191','Q851','Tuberous sclerosis',''),('3282','Von Hippel-Lindau disease',0,0,0,1,0,0,0,1,0,'','46659004','Von Hippel-Lindau syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',238,'','http://omim.org/entry/193300 about Von Hippel-Lindau syndrome; VHL\nhttp://omim.org/entry/608537 about VHL gene','Q858','Other phakomatoses, not elsewhere classified','VHL gene'),('3295','Medullary sponge kidneys',0,0,0,0,0,0,0,1,0,'','236443009','Medullary sponge kidney (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',239,'','','Q615','Medullary cystic kidney','MSK'),('3305','Horse-shoe kidney',0,0,0,0,0,0,0,1,0,'','41729002','Horseshoe kidney (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',240,'','','Q631','Lobulated, fused and horseshoe kidney',''),('3314','Frasier syndrome',0,1,0,1,0,0,0,1,1,'','445431000','Frasier syndrome (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',241,'','http://omim.org/entry/136680','Q998','Other specified chromosome abnormalities',''),('3322','Branchio-oto-renal syndrome',0,1,1,1,0,0,0,0,1,'','290006','Melnick-Fraser syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',243,'','http://omim.org/entry/113650 about branchiootorenal syndrome 1; BOR1\nhttp://omim.org/entry/610896 about branchiootorenal syndrome 2; BOR2','Q870','Cong malform syndromes predom affect facial appearance',''),('3333','Williams syndrome',0,0,0,1,0,0,0,0,1,'','63247009','Williams syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',244,'','','Q878','Other specified congenital malformation syndromes NEC',''),('3346','Townes-Brocks syndrome',0,1,1,1,0,0,0,0,1,'SALL1 is the only gene known to be associated with TBS (2011). ','523411000000105','Townes-Brocks syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',245,'http://ghr.nlm.nih.gov/condition/townes-brocks-syndrome','http://www.ncbi.nlm.nih.gov/omim/107480','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','TBS'),('3351','Lawrence-Moon-Biedl / Bardet-Biedl syndrome',0,1,1,1,0,0,0,0,1,'','232059000','Laurence-Moon syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',246,'','http://omim.org/entry/245800 about Laurence-Moon syndrome\nhttp://omim.org/entry/209900 about Bardet-Biedl syndrome; BBS','Q878','Other specified congenital malformation syndromes NEC','LMB LMBS BB BBS'),('3367','Mitochondrial cytopathy',0,0,0,0,0,0,0,0,1,'Usually diagnosed by muscle histopathology and mitochondrial enzyme complex functional assay.','240096000','Mitochondrial cytopathy (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',247,'','for background information see:\nhttp://omim.org/entry/251900 about mitochondrial myopathy','G713','Mitochondrial myopathy, not elsewhere classified',''),('3379','Familial nephropathy',0,0,1,0,0,0,0,0,0,'A clear and compatible family history must be present that cannot be assigned to any more specific familial disorder. ','236419006','Progressive hereditary glomerulonephritis without deafness (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',248,'','','N079','Unspecified morphological changes',''),('3380','Acute kidney injury',0,1,0,0,1,0,0,0,0,'For a brief definition of acute and chronic renal failure when RRT is required, see ‘Notes for users’.\nThis super concept PRD should only be used if it is not possible to choose a more accurate one.\n','14669001','Acute renal failure syndrome (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',249,'','','N179','Acute renal failure, unspecified','AKI ARF'),('3398','Acute kidney injury due to hypovolaemia ',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 28560003 Hypovolemia |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',250,'','','N179 AND E86X','Acute renal failure, unspecified AND Volume depletion','AKI ARF renal failure'),('3403','Acute kidney injury due to circulatory failure',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 27942005 Shock |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',251,'','','N179 AND R579','Acute renal failure, unspecified AND Shock, unspecified','AKI ARF renal failure'),('3419','Acute kidney injury due to sepsis',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 91302008 | Systemic infection |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',252,'','','N179','Acute renal failure, unspecified','AKI ARF infection septicaemia bacteraemia renal failure'),('3426','Acute kidney injury due to rhabdomyolysis',0,1,0,0,1,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 240131006 Rhabdomyolysis |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',253,'','','N179 AND M628','Acute renal failure, unspecified AND Other specified disorders of muscle','AKI ARF myoglobin CK renal failure'),('3435','Acute kidney injury due to nephrotoxicity',0,1,0,0,0,0,0,0,0,'','236428007','Nephrotoxic acute renal failure (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',254,'','','N144','Toxic nephropathy, not elsewhere classified','AKI ARF poison toxin toxic toxicity drug medicine renal failure'),('3442','Acute cortical necrosis',0,1,0,0,0,0,0,1,0,'Either imaging or histological evidence is acceptable.','197650009','Acute cortical necrosis (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',255,'','','N171','Acute renal failure with acute cortical necrosis','AKI ARF CAN'),('3457','Acute pyelonephritis',0,1,0,0,0,0,0,0,0,'Either evidence of urinary tract infection with white cell casts or histology.','36689008','Acute pyelonephritis (disorder)','','Miscellaneous renal disorders',0,'Not available in previous coding system',256,'','','N10X','Acute tubulo-interstitial nephritis','PN'),('3461','Kidney tumour',0,0,0,0,0,0,0,1,0,'','126880001','Neoplasm of kidney (disorder)','','Miscellaneous renal disorders',95,'Kidney tumour',257,'','http://omim.org/entry/144700 about renal cell carcinoma, nonpapillary; RCC\nhttp://omim.org/entry/150800 about hereditary leiomyomatosis and renal cell cancer; HLRCC\nhttp://omim.org/entry/609322 about ','D410','Neoplasm uncert / unkn behav kidney','mass cancer malignancy neoplasm neoplasia'),('3474','Renal cell carcinoma - histologically proven',1,0,0,0,0,0,0,0,0,'','254915003','Clear cell carcinoma of kidney (disorder)','254915003 | Clear cell carcinoma of kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',258,'','http://omim.org/entry/605074 about renal cell carcinoma, papillary, 1; RCCP1\nhttp://omim.org/entry/613253 about hereditary renal cancer, associated 1; HRCA1\nhttp://omim.org/entry/606423 about disrupte','C64X','Malignant neoplasm of kidney, except renal pelvis','mass cancer malignancy neoplasm neoplasia tumour'),('3488','Transitional cell carcinoma - histologically proven',1,0,0,0,0,0,0,0,0,'','408642003','Transitional cell carcinoma of kidney (disorder)','408642003 | Transitional cell carcinoma of kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',259,'','http://omim.org/entry/109800 about bladder cancer. Refers to TCC of the renal pelvis','C64X','Malignant neoplasm of kidney, except renal pelvis','cancer malignancy neoplasm neoplasia mass tumour'),('3490','Wilms tumour - histologically proven',1,0,0,0,0,0,0,0,0,'','302849000','Nephroblastoma (disorder)','302849000 | Nephroblastoma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',260,'','http://omim.org/entry/194070 about Wilms tumor 1; WT1\nhttp://omim.org/entry/605442 about Wilms tumor 1-associating protein; WTAP\nhttp://omim.org/entry/194071 about Wilms tumor 2; WT2\nhttp://omim.org/e','C64X','Malignant neoplasm of kidney, except renal pelvis','cancer malignancy neoplasm neoplasia mass'),('3501','Mesoblastic nephroma - histologically proven',1,0,0,0,0,0,0,0,0,'','307604008','Mesoblastic nephroma (disorder)','307604008 | Mesoblastic nephroma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',261,'','','D410','Neoplasm uncert / unkn behav kidney',''),('3517','Single kidney identified in adulthood',0,0,0,0,0,0,0,1,0,'Diagnosis codes expressing the presence of a kidney (left or right) are not suitable because they do not imply absence of the contralateral kidney.\n','824131000000108','Solitary kidney (finding)',' ','Miscellaneous renal disorders',99,'Other identified renal disorders',262,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','absent one unilateral'),('3529','Chronic kidney disease (CKD) / chronic renal failure (CRF) caused by tumour nephrectomy',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n42752001 | due to | = 108022006 | Kidney excision |','Miscellaneous renal disorders',96,'Traumatic or surgical loss of kidney',263,'','','N189','Chronic renal failure, unspecified','surgery'),('3538','Chronic kidney disease (CKD) / chronic renal failure (CRF) due to traumatic loss of kidney',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','','Miscellaneous renal disorders',96,'Traumatic or surgical loss of kidney',264,'','','N189','Chronic renal failure, unspecified',''),('3540','Chronic kidney disease (CKD) / chronic renal failure (CRF) due to donor nephrectomy',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\ndue to =175911000 | Live donor nephrectomy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',265,'','','N189','Chronic renal failure, unspecified',''),('3555','Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - no histology',0,1,0,0,0,0,0,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',266,'','','N189','Chronic renal failure, unspecified',''),('3564','Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - histologically proven',1,1,0,0,0,0,0,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',267,'','','N189','Chronic renal failure, unspecified',''),('3572','Haematuria and proteinuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.','53298000 AND 29738008 ','Hematuria syndrome (disorder) AND Proteinuria (disorder)','(53298000 | Hematuria syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)\nAND\n(29738008 | Proteinuria |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',268,'','','R31X AND N391','Unspecified haematuria AND Persistent proteinuria, unspecified','blood red cells protein albumin albuminuria'),('3604','Nephrotic syndrome of childhood - steroid resistant - no histology',0,1,0,0,1,0,0,0,0,'No remission despite a therapeutic trial of corticosteroids.','800991000000107','Steroid resistant nephrotic syndrome of childhood (disorder)','236381000 | Steroid-resistant nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',4,'','for background information see:\nhttp://omim.org/entry/600995 about nephrotic syndrome, type 2; NPHS2 = nephrotic syndrome, steroid-resistant, autosomal recessive; SRN1\nhttp://omim.org/entry/610725 abo','N049','Nephrotic syndrome, unspecified',''),('3615','Nephrotic syndrome of childhood - no trial of steroids - no histology',0,1,0,0,1,0,0,0,0,'Nephrotic syndrome in childhood. No trial of steroids, no histology, no information to allow a more detailed PRD to be selected.','445119005','Steroid sensitive nephrotic syndrome of childhood (disorder)','445119005 | Steroid sensitive nephrotic syndrome of childhood |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',5,'','','N049','Nephrotic syndrome, unspecified',''),('3627','Renal cysts and diabetes syndrome',0,0,0,0,0,1,0,1,1,'Associated with the HNF1B gene.','446641003','Renal cysts and diabetes syndrome (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',69,'','http://omim.org/entry/137920','E142','Unspecified diabetes mellitus with renal complications','cystic cysts cyst'),('3636','Chronic urate nephropathy - no histology',0,1,0,0,1,0,0,0,0,'','190829000','Chronic urate nephropathy (disorder)','418775008 | Finding method | = !< 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',127,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','TIN IN uric '),('3643','Chronic renal failure due to systemic infection',0,1,0,0,1,0,0,1,0,'','90688005','Chronic renal failure syndrome (disorder)','90688005 | Chronic renal failure syndrome |:\n42752001 | due to | = 91302008 | Systemic infection |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',271,'','','N189','Chronic renal failure, unspecified','CKD CRF'),('3658','Renal coloboma syndrome',0,1,1,1,0,0,0,1,1,'Associated with the Pax2 gene.','446449009','Renal coloboma syndrome (disorder)',' ','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',242,'','http://omim.org/entry/120330','Q605','Renal hypoplasia, unspecified',''),('3662','Hypercalcaemic nephropathy ',0,0,0,0,0,0,0,0,0,'Use a more accurate PRD if appropriate.','33763006','Hypercalcemic nephropathy (disorder)','','Tubulo-interstitial disease',93,'Nephrocalcinosis and hypercalcaemic nephropathy',129,'','for background information see:\nhttp://omim.org/entry/143880\nhttp://omim.org/entry/145000','E835','Disorders of calcium metabolism','TIN IN calcium Ca Ca++ high'),('3670','Retroperitoneal fibrosis secondary to peri-aortitis',0,0,0,0,0,0,0,1,0,'','49120005','Retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',89,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction  CPN PN TIN AAA aneurysm inflammation aorta'),('3689','Retroperitoneal fibrosis secondary to drugs',0,1,0,0,0,0,0,1,0,'','236015007','Drug-induced retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',88,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction medicine drug CPN PN TIN'),('3691','Renal failure',0,1,0,0,1,0,0,0,0,'This super concept PRD should only be used if it is not possible to specify even whether the patient has acute or chronic renal failure.','42399005','Renal failure syndrome (disorder)','','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',272,'','','N19X','Unspecified renal failure','CKD CRF ARF AKI'),('3708','Chronic renal failure',0,0,0,0,0,0,0,0,0,'This super concept PRD should be used:\n1) when the investigations that have been undertaken do not allow any more granular PRD to be selected\nor\n2) very infrequently when a full diagnosis has been made which can not be assigned to another PRD.\nIn this case, please send an email to the ERA-EDTA Registry office, highlighting the PRD that is missing.\nWhere a patient has been investigated appropriately but it is not possible to choose a more granular PRD, consider using\nPRD 3555  = Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - no histology,\nor\nPRD 3564 Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - histologically proven.\nSee Notes for users.','90688005','Chronic renal failure syndrome (disorder)','','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',273,'','','N189','Chronic renal failure, unspecified','CKD CRF'),('3712','Isolated haematuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.\nAlternative PRDs are available for Isolated proteinuria - no histology and for Haematuria and proteinuria - no histology.\n','53298000','Hematuria syndrome (disorder)\n','(53298000 | Hematuria syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)\n','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',269,'','','R31X','Unspecified haematuria','blood red cells '),('3720','Isolated proteinuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.\nAlternative PRDs are available for Isolated haematuria - no histology and for Haematuria and proteinuria - no histology.','29738008','Proteinuria (disorder)','(29738008 | Proteinuria |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',270,'','','N391','Persistent proteinuria, unspecified','protein albumin albuminuria'),('3731','Primary hyperoxaluria type III',1,0,0,0,1,0,0,1,1,'Associated with mutations in an uncharacterized gene (DHDPSL) on chromosome 10.','828971000000101','Primary hyperoxaluria, type III (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',231,'','http://omim.org/entry/613616','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3749','Glomerulonephritis - no histology',0,1,0,1,1,1,1,1,0,'','36171008','Glomerulonephritis (disorder)','36171008 | Glomerulonephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',1,'','','N059','Unspecified nephritic syndrome, Unspecified','GN'),('RDG001','New demo diagnosis 1 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',274,'','','','',''),('RDG002','New demo diagnosis 2 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',275,'','','','',''),('RDG003','New demo diagnosis 3 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',276,'','','','',''),('RDG004','Arthrogryposis, renal dysfunction and cholestasis',0,0,0,0,0,0,0,0,0,'','','','','',0,'',277,'','','','',''),('RDG005','Autoimmune polyendocrinopathy syndrome Type 1',0,0,0,0,0,0,0,0,0,'','','','','',0,'',278,'','','','',''),('RDG006','EAST syndrome',0,0,0,0,0,0,0,0,0,'','','','','',0,'',279,'','','','',''),('RDG007','Hypoparathyroidism (primary) isolated familial',0,0,0,0,0,0,0,0,0,'','','','','',0,'',280,'','','','',''),('RDG008','Hypoparathyroidism (primary) with Sensorineural Deafness and renal Dysplasia',0,0,0,0,0,0,0,0,0,'','','','','',0,'',281,'','','','',''),('RDG009','Hyperparathyroidism (primary) monogenetic forms',0,0,0,0,0,0,0,0,0,'','','','','',0,'',282,'','','','',''),('RDG010','Hyperparathyroidism, Neonatal severe primary',0,0,0,0,0,0,0,0,0,'','','','','',0,'',283,'','','','',''),('RDG011','Hyperparathyroidism type 2',0,0,0,0,0,0,0,0,0,'','','','','',0,'',284,'','','','',''),('RDG012','Renal Cell Carcinoma Papillary 2',0,0,0,0,0,0,0,0,0,'','','','','',0,'',285,'','','','',''),('RDG013','Lipoprotein Glomerulopathy',0,0,0,0,0,0,0,0,0,'','','','','',0,'',286,'','','','',''),('xAHUS','Atypical Haemolytic Uraemic Syndrome',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xARPKD','ARPKD – autosomal recessive polycystic kidney disease',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xFUAN','Familial Urate Associated Nephropathy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xHYPALK','Hypokalaemic alkalosis',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xHYPERRDG','Hyperoxaluria',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xMEMRDG','Membranous nephropathy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xSTECHUS','STEC-associated HUS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rdr_prd_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdr_user_mapping`
--

DROP TABLE IF EXISTS `rdr_user_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_user_mapping` (
  `userId` bigint(20) NOT NULL,
  `radarUserId` bigint(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_user_mapping`
--

LOCK TABLES `rdr_user_mapping` WRITE;
/*!40000 ALTER TABLE `rdr_user_mapping` DISABLE KEYS */;
INSERT INTO `rdr_user_mapping` VALUES (58127,1101,'ROLE_PROFESSIONAL'),(58128,1102,'ROLE_PROFESSIONAL'),(58129,1103,'ROLE_PROFESSIONAL'),(58130,1104,'ROLE_PROFESSIONAL'),(58131,1105,'ROLE_PROFESSIONAL'),(58132,1106,'ROLE_PROFESSIONAL'),(58133,1107,'ROLE_PROFESSIONAL'),(58134,1108,'ROLE_PROFESSIONAL'),(58135,1109,'ROLE_PROFESSIONAL'),(58136,1110,'ROLE_PROFESSIONAL'),(58137,1111,'ROLE_PROFESSIONAL'),(58138,1112,'ROLE_PROFESSIONAL');
/*!40000 ALTER TABLE `rdr_user_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_heading`
--

DROP TABLE IF EXISTS `result_heading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_heading` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `headingcode` varchar(20) NOT NULL DEFAULT '',
  `heading` varchar(30) NOT NULL DEFAULT '',
  `rollover` varchar(50) NOT NULL DEFAULT 'Click for info',
  `link` varchar(100) NOT NULL DEFAULT '',
  `panel` int(11) NOT NULL DEFAULT '0',
  `panelorder` int(11) NOT NULL DEFAULT '0',
  `specialty_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `headingcode` (`headingcode`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_heading`
--

LOCK TABLES `result_heading` WRITE;
/*!40000 ALTER TABLE `result_heading` DISABLE KEYS */;
INSERT INTO `result_heading` VALUES (1,'urea','Urea','Urea (2.5-7.0) Click for info','http://www.rixg.org/results/urea.html',1,1,1),(2,'creatinine','Creatinine','Creatinine (60-120) Click for info','http://www.rixg.org/results/creatinine.html',1,2,1),(3,'potassium','K','Potassium (3.5-5.0) Click for info','http://www.rixg.org/results/potassium.html',1,3,1),(4,'calcium','Ca','Calcium (2.1-2.6) Click for info','http://www.rixg.org/results/calcium.html',1,4,1),(5,'phosphate','PO4','Phosphate (0.8-1.4) Click for info','http://www.rixg.org/results/phosphate.html',1,5,1),(6,'hb','Hb','Haemoglobin (115-180) Click for info','http://www.rixg.org/results/haemoglobin.html',1,6,1),(7,'wbc','wbc','White blood cell count (4-11) Click for info','http://www.rixg.org/results/wbc.html',1,7,1),(8,'platelets','plats','Platelets (150-400) Click for info','http://www.rixg.org/results/platelets.html',1,8,1),(9,'ciclosporin','Ciclo','Ciclosporin (cyclosporin) Click for info','http://www.rixg.org/results/ciclosporin.html',3,6,1),(10,'adjustedcalcium','AdjCa','Adjusted Calcium (2.1-2.6) Click for info','http://www.rixg.org/results/adjcalcium.html',2,10,1),(11,'albumin','Alb','Albumin (35-50) Click for info','http://www.rixg.org/results/albumin.html',2,1,1),(12,'bpdia','BPdia','Diastolic blood pressure - Click for info','http://www.rixg.org/results/bp.html',3,3,1),(13,'bpsys','BPsys','Systolic blood pressure - Click for info','http://www.rixg.org/results/bp.html',3,2,1),(14,'cholesterol','Cholest','Cholesterol - Click for info','http://www.rixg.org/results/lipids.html',2,7,1),(15,'crp','CRP','C-reactive protein (0-10) Click for info','http://www.rixg.org/results/crp.html',2,2,1),(16,'glucose','Gluc','Glucose (3.5-7, or 11) Click for info','http://www.rixg.org/results/glucose.html',2,9,1),(17,'hba1c','HbA1c','HbA1c (less than 7% or 42) Click for info','http://www.rixg.org/results/glucose.html',3,10,1),(18,'height','Height','Click for info','http://www.rixg.org/results/weightheight.html',3,4,1),(19,'ktv','Kt/V','Kt/V (dialysis adequacy measure) Click for info','http://www.rixg.org/results/ktv.html',4,6,1),(20,'pth','PTH','Parathyroid hormone - Click for info','http://www.rixg.org/results/pth.html',3,9,1),(21,'tacrolimus','Tacro','Tacrolimus - Click for info','http://www.rixg.org/results/tacrolimus.html',3,7,1),(22,'tg','TG','Triglycerides - Click for info','http://www.rixg.org/results/lipids.html',2,8,1),(23,'urr','URR','Urea reduction ratio - Click for info','http://www.rixg.org/results/urr.html',4,5,1),(24,'weight','Weight','in kg.  Click for info','http://www.rixg.org/results/weightheight.html',3,1,1),(25,'egfr','eGFR','Estimated GFR (over 60) Click for info','http://www.rixg.org/results/egfr.html',1,9,1),(26,'inr','INR','Warfarin dose control (Usually 2-4) - Click for in','http://www.rixg.org/results/inr.html',3,5,1),(27,'sodium','Na','Sodium (135-147) Click for info','http://www.rixg.org/results/sodium.html',2,3,1),(28,'hco3','HCO3','Bicarbonate (20-30) Click for info','http://www.rixg.org/results/hco3.html',2,4,1),(29,'pcr','PCR','Protein Creatinine ratio (less than 15) Click for','http://www.rixg.org/results/pcr.html',2,5,1),(30,'acr','ACR','Albumin:creatinine ratio (less than 3.5) Click for','http://www.rixg.org/results/acr.html',2,6,1),(31,'sirolimus','Siro','Sirolimus - Click for info','http://www.rixg.org/results/sirolimus.html',3,8,1),(32,'bili','Bili','Bilirubin (liver test) (2-17) Click for info','http://www.rixg.org/results/lft.html',5,1,1),(33,'ast','AST','AST (liver test) (varies, eg 10-45) Click for info','http://www.rixg.org/results/lft.html',5,2,1),(34,'alt','ALT','AST (liver test) (10-50) Click for info','http://www.rixg.org/results/lft.html',5,3,1),(35,'alp','AlkP','AlkP (liver/bone) (varies: ?40-125+ click for info','http://www.rixg.org/results/lft.html',5,4,1),(36,'ggt','GGT','GGT (liver test) (varies, eg 5-55) Click for info','http://www.rixg.org/results/lft.html',5,5,1),(37,'ferritin','Ferr','Ferritin (iron test) (17-300) Click for info','http://www.rixg.org/results/iron.html',5,6,1),(38,'iron','Iron','Iron (10-32) - Click for info','http://www.rixg.org/results/iron.html',5,7,1),(39,'transferrin','Tferrin','Transferrin (iron test) (2-4) Click for info','http://www.rixg.org/results/iron.html',5,8,1),(40,'ironsat','Fe Sat','Iron Saturation (14-56) Click for info','http://www.rixg.org/results/iron.html',5,9,1),(41,'urate','Urate','Uric Acid (0.12-0.4) Click for info','http://www.rixg.org/results/urate.html',5,10,1),(42,'resultcomment','Comment','Click for info','',1,10,1),(43,'phepku','Phe','Phenylalanine (for PKU)  Click for info','http://www.rixg.org/results/phe.html',6,3,1),(44,'lithium','Li','Lithium level (drug) 0.4-1.0   Click for info','http://www.rixg.org/results/lithium.html',6,2,1);
/*!40000 ALTER TABLE `result_heading` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `context` (`context`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (1,'renal','Renal Patient View','Renal Patient View'),(2,'ibd','Inflammatory Bowel Disease','Inflammatory Bowel Disease');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialtyuserrole`
--

DROP TABLE IF EXISTS `specialtyuserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialtyuserrole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `specialty_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tenancy_id` (`specialty_id`,`user_id`,`role`),
  KEY `FK7A1749E1AEFDD122` (`specialty_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58003 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialtyuserrole`
--

LOCK TABLES `specialtyuserrole` WRITE;
/*!40000 ALTER TABLE `specialtyuserrole` DISABLE KEYS */;
INSERT INTO `specialtyuserrole` VALUES (57849,'superadmin',1,57987),(57987,'patient',1,NULL),(57985,'patient',1,NULL),(58002,'patient',1,58139),(58001,'unitadmin',1,58138),(58000,'unitadmin',1,58137),(57999,'unitadmin',1,58136),(57998,'unitadmin',1,58135),(57997,'unitadmin',1,58134),(57996,'unitadmin',1,58133),(57995,'unitadmin',1,58132),(57994,'unitadmin',1,58131),(57993,'unitadmin',1,58130),(57992,'unitadmin',1,58129),(57991,'unitadmin',1,58128),(57990,'unitadmin',1,58127);
/*!40000 ALTER TABLE `specialtyuserrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `splashpage`
--

DROP TABLE IF EXISTS `splashpage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `splashpage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `live` tinyint(1) NOT NULL,
  `headline` varchar(100) NOT NULL,
  `bodytext` text NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `specialty_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `splashpage`
--

LOCK TABLES `splashpage` WRITE;
/*!40000 ALTER TABLE `splashpage` DISABLE KEYS */;
/*!40000 ALTER TABLE `splashpage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `splashpageuserseen`
--

DROP TABLE IF EXISTS `splashpageuserseen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `splashpageuserseen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `splashpageid` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersplashpage` (`username`,`splashpageid`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `splashpageuserseen`
--

LOCK TABLES `splashpageuserseen` WRITE;
/*!40000 ALTER TABLE `splashpageuserseen` DISABLE KEYS */;
/*!40000 ALTER TABLE `splashpageuserseen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysdiagrams`
--

DROP TABLE IF EXISTS `sysdiagrams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysdiagrams` (
  `name` varchar(128) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `diagram_id` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `definition` blob
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysdiagrams`
--

LOCK TABLES `sysdiagrams` WRITE;
/*!40000 ALTER TABLE `sysdiagrams` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysdiagrams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_6month`
--

DROP TABLE IF EXISTS `tbl_6month`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_6month` (
  `fuID` int(11) NOT NULL,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_FUP` datetime DEFAULT NULL,
  `RELAP_SINCE_LAST` bit(1) DEFAULT NULL,
  `RELAP_LEN` int(11) DEFAULT NULL,
  `VIRAL_TRIG` int(11) DEFAULT NULL,
  `IMMUN_TRIG` int(11) DEFAULT NULL,
  `OTHER_TRIG` int(11) DEFAULT NULL,
  `IMMUNOSUP_INC` bit(1) DEFAULT NULL,
  `IMMUNOSUP_DOSE` int(11) DEFAULT NULL,
  `IMMUNOSUP_DUR` int(11) DEFAULT NULL,
  `PLASMA_EXCH` int(11) DEFAULT NULL,
  `PLASMA_EXCH_NO` int(11) DEFAULT NULL,
  `RESPONSE_TO` int(11) DEFAULT NULL,
  `MAX_PR_CREAT_RATIO` int(11) DEFAULT NULL,
  `MIN_SER_ALB` int(11) DEFAULT NULL,
  `COMP1` int(11) DEFAULT NULL,
  `COMP2` int(11) DEFAULT NULL,
  `COMP3` int(11) DEFAULT NULL,
  `COMP4` int(11) DEFAULT NULL,
  `OTHER_COMP` varchar(50) DEFAULT NULL,
  `DATE_START_DIAL` datetime DEFAULT NULL,
  `DIAL_TYPE` int(11) DEFAULT NULL,
  `DATE_TRANSPLANT` datetime DEFAULT NULL,
  `TRANS_TYPE` int(11) DEFAULT NULL,
  `TRANS_RECURR` bit(1) DEFAULT NULL,
  `DATE_TX_REJECT` datetime DEFAULT NULL,
  `DATE_BX` datetime DEFAULT NULL,
  `DATE_NEPHRECT` datetime DEFAULT NULL,
  `DRUG1` varchar(50) DEFAULT NULL,
  `DRUG2` varchar(50) DEFAULT NULL,
  `DRUG3` varchar(50) DEFAULT NULL,
  `DRUG4` varchar(50) DEFAULT NULL,
  `DRUG5` varchar(50) DEFAULT NULL,
  `DRUG6` varchar(50) DEFAULT NULL,
  `SIG_CHANGE_STATUS` char(10) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_6month`
--

LOCK TABLES `tbl_6month` WRITE;
/*!40000 ALTER TABLE `tbl_6month` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_6month` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_adminusers`
--

DROP TABLE IF EXISTS `tbl_adminusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_adminusers` (
  `uID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`uID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_adminusers`
--

LOCK TABLES `tbl_adminusers` WRITE;
/*!40000 ALTER TABLE `tbl_adminusers` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_adminusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clin_pres`
--

DROP TABLE IF EXISTS `tbl_clin_pres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clin_pres` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `CLIN_PRES` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clin_pres`
--

LOCK TABLES `tbl_clin_pres` WRITE;
/*!40000 ALTER TABLE `tbl_clin_pres` DISABLE KEYS */;
INSERT INTO `tbl_clin_pres` VALUES (1,'Nephrotic'),(2,'Nephritic'),(3,'Haematuria'),(4,'Proteinuria'),(99,'other');
/*!40000 ALTER TABLE `tbl_clin_pres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_clinicaldata`
--

DROP TABLE IF EXISTS `tbl_clinicaldata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_clinicaldata` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) NOT NULL,
  `DATE_CLIN_PIC` datetime DEFAULT NULL,
  `HEIGHT` decimal(8,1) DEFAULT NULL,
  `WEIGHT` decimal(8,2) DEFAULT NULL,
  `COURSE_DIS` int(11) DEFAULT NULL,
  `SYS_BP` int(11) DEFAULT NULL,
  `DIA_BP` int(11) DEFAULT NULL,
  `MAP_BP` int(11) DEFAULT NULL,
  `DIALYSIS_REQ` int(11) DEFAULT NULL,
  `DATE_BX` datetime DEFAULT NULL,
  `OEDEMA` bit(1) DEFAULT NULL,
  `ANAEMIA` bit(1) DEFAULT NULL,
  `HYPOVAL` bit(1) DEFAULT NULL,
  `FEVER` bit(1) DEFAULT NULL,
  `INFECTION` bit(1) DEFAULT NULL,
  `INFECTION_DETAIL` varchar(50) DEFAULT NULL,
  `INFECTION_TYPE` varchar(50) DEFAULT NULL,
  `THROMBOSIS` bit(1) DEFAULT NULL,
  `THROMBOSIS_DETAIL` varchar(250) DEFAULT NULL,
  `COMP_THROMBOSIS` bit(1) DEFAULT NULL,
  `COMP_THROMBOSIS_DETAIL` text,
  `PERITONITIS` bit(1) DEFAULT NULL,
  `PUL_OED` bit(1) DEFAULT NULL,
  `HTH_REQ_TMT` bit(1) DEFAULT NULL,
  `PREC_INF` bit(1) DEFAULT NULL,
  `PREC_INF_DETAIL` varchar(150) DEFAULT NULL,
  `CLIN_EV_CHR_INF` bit(1) DEFAULT NULL,
  `CLIN_EV_CHR_INF_DETAIL` varchar(150) DEFAULT NULL,
  `DIABETES` smallint(6) DEFAULT NULL,
  `URTICARIA` smallint(6) DEFAULT NULL,
  `RASH` bit(1) DEFAULT NULL,
  `RASH_DETAIL` varchar(50) DEFAULT NULL,
  `PART_LIPODYS` bit(1) DEFAULT NULL,
  `OPTHALM` bit(1) DEFAULT NULL,
  `OPTHALM_DETAIL` varchar(50) DEFAULT NULL,
  `IMMUNIS_TRIGGER` bit(1) DEFAULT NULL,
  `COMMENTS` text,
  `PHENOTYPE1` int(11) DEFAULT NULL,
  `PHENOTYPE2` int(11) DEFAULT NULL,
  `PHENOTYPE3` int(11) DEFAULT NULL,
  `PHENOTYPE4` int(11) DEFAULT NULL,
  `SIG_DIAG1` varchar(30) DEFAULT NULL,
  `SIG_DIAG2` varchar(30) DEFAULT NULL,
  `TX_LISTED` bit(1) DEFAULT NULL,
  `CKD_STAGE` int(11) DEFAULT NULL,
  `SEQ_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM AUTO_INCREMENT=2078 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clinicaldata`
--

LOCK TABLES `tbl_clinicaldata` WRITE;
/*!40000 ALTER TABLE `tbl_clinicaldata` DISABLE KEYS */;
INSERT INTO `tbl_clinicaldata` VALUES (2077,740,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1);
/*!40000 ALTER TABLE `tbl_clinicaldata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_complication`
--

DROP TABLE IF EXISTS `tbl_complication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_complication` (
  `cmpID` int(11) NOT NULL AUTO_INCREMENT,
  `cmpDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cmpID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_complication`
--

LOCK TABLES `tbl_complication` WRITE;
/*!40000 ALTER TABLE `tbl_complication` DISABLE KEYS */;
INSERT INTO `tbl_complication` VALUES (1,'Viral Infection'),(2,'Bacterial Infection'),(3,'Fungal Infection'),(4,'Thrombosis');
/*!40000 ALTER TABLE `tbl_complication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_consultants`
--

DROP TABLE IF EXISTS `tbl_consultants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_consultants` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `cSNAME` varchar(50) DEFAULT NULL,
  `cFNAME` varchar(50) DEFAULT NULL,
  `cCentre` int(11) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM AUTO_INCREMENT=145 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_consultants`
--

LOCK TABLES `tbl_consultants` WRITE;
/*!40000 ALTER TABLE `tbl_consultants` DISABLE KEYS */;
INSERT INTO `tbl_consultants` VALUES (1,'Smith','John',1098);
/*!40000 ALTER TABLE `tbl_consultants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_country`
--

DROP TABLE IF EXISTS `tbl_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_country` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_country`
--

LOCK TABLES `tbl_country` WRITE;
/*!40000 ALTER TABLE `tbl_country` DISABLE KEYS */;
INSERT INTO `tbl_country` VALUES (1,'GB and Ireland'),(2,'Outside GB and Ireland');
/*!40000 ALTER TABLE `tbl_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_diagcode`
--

DROP TABLE IF EXISTS `tbl_diagcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_diagcode` (
  `dcID` int(11) NOT NULL AUTO_INCREMENT,
  `dcDesc` varchar(70) DEFAULT NULL,
  `dcAbbr` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`dcID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_diagcode`
--

LOCK TABLES `tbl_diagcode` WRITE;
/*!40000 ALTER TABLE `tbl_diagcode` DISABLE KEYS */;
INSERT INTO `tbl_diagcode` VALUES (1,'Steroid Resistant Nephrotic Syndrome ','SRNS'),(2,'Mesangiocappillary Glomerulonephritis','MPGN/DDD');
/*!40000 ALTER TABLE `tbl_diagcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_diagnosis`
--

DROP TABLE IF EXISTS `tbl_diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_diagnosis` (
  `dID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_DIAG` datetime DEFAULT NULL,
  `DIAG` int(11) DEFAULT NULL,
  `DIAG_TXT` varchar(100) DEFAULT NULL,
  `AGE_AT_DIAG` decimal(4,1) DEFAULT NULL,
  `HEIGHT_FIRST_VISIT` decimal(4,1) DEFAULT NULL,
  `BX_PROVEN_DIAG` varchar(1) DEFAULT NULL,
  `PREPUB_DIAG` bit(1) DEFAULT NULL,
  `CLIN_PRES` int(11) DEFAULT NULL,
  `CLIN_PRES_B` int(11) DEFAULT NULL,
  `GENE_MUT` varchar(50) DEFAULT NULL,
  `GENE_MUT_TEXT` varchar(100) DEFAULT NULL,
  `KARYOTYPE` varchar(50) DEFAULT NULL,
  `KARYOTYPE_OTHER` varchar(100) DEFAULT NULL,
  `DATE_ONSET_RENALDIS` datetime DEFAULT NULL,
  `CONSANGUINITY` int(11) DEFAULT NULL,
  `FAM_HIST` int(11) DEFAULT NULL,
  `REL1` varchar(20) DEFAULT NULL,
  `REL1_RADAR` int(11) DEFAULT NULL,
  `REL2` varchar(20) DEFAULT NULL,
  `REL2_RADAR` int(11) DEFAULT NULL,
  `REL3` varchar(20) DEFAULT NULL,
  `REL3_RADAR` int(11) DEFAULT NULL,
  `REL4` varchar(20) DEFAULT NULL,
  `REL4_RADAR` int(11) DEFAULT NULL,
  `REL5` varchar(20) DEFAULT NULL,
  `REL5_RADAR` int(11) DEFAULT NULL,
  `REL6` varchar(20) DEFAULT NULL,
  `REL6_RADAR` int(11) DEFAULT NULL,
  `SIG_DIAG1` varchar(50) DEFAULT NULL,
  `SIG_DIAG2` varchar(50) DEFAULT NULL,
  `STEROID_RESIST` int(11) DEFAULT NULL,
  `DATE_ESRF` datetime DEFAULT NULL,
  `MUTATION_1` bit(1) DEFAULT NULL,
  `MUTATION_1S` bit(1) DEFAULT NULL,
  `MUTATION_2` bit(1) DEFAULT NULL,
  `MUTATION_2S` bit(1) DEFAULT NULL,
  `MUTATION_3` bit(1) DEFAULT NULL,
  `MUTATION_3S` bit(1) DEFAULT NULL,
  `MUTATION_4` bit(1) DEFAULT NULL,
  `MUTATION_4S` bit(1) DEFAULT NULL,
  `MUTATION_5` bit(1) DEFAULT NULL,
  `MUTATION_5S` bit(1) DEFAULT NULL,
  `MUTATION_6` bit(1) DEFAULT NULL,
  `MUTATION_6S` bit(1) DEFAULT NULL,
  `MUTATION_7` bit(1) DEFAULT NULL,
  `MUTATION_7S` bit(1) DEFAULT NULL,
  `MUTATION_8` bit(1) DEFAULT NULL,
  `MUTATION_8S` bit(1) DEFAULT NULL,
  `MUTATION_9` bit(1) DEFAULT NULL,
  `MUTATION_9S` bit(1) DEFAULT NULL,
  PRIMARY KEY (`dID`)
) ENGINE=MyISAM AUTO_INCREMENT=550 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_diagnosis`
--

LOCK TABLES `tbl_diagnosis` WRITE;
/*!40000 ALTER TABLE `tbl_diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_diseasedata`
--

DROP TABLE IF EXISTS `tbl_diseasedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_diseasedata` (
  `dID` int(11) NOT NULL AUTO_INCREMENT,
  `dText` text,
  PRIMARY KEY (`dID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_diseasedata`
--

LOCK TABLES `tbl_diseasedata` WRITE;
/*!40000 ALTER TABLE `tbl_diseasedata` DISABLE KEYS */;
INSERT INTO `tbl_diseasedata` VALUES (1,'<h1>Steroid Resistant Nephrotic Syndrome</h1>\n\n<p>Normally when blood flows through the kidneys, they filter out the waste products and hold onto the rest including protein and most of the water. However, in nephrotic syndrome, the kidneys become leaky and cannot hold onto protein and it too leaks out with the waste. When the protein leaks out this is drops the level of protein in the blood and this is enough to make a child unwell. When the blood level of protein drops, water leaks out into the tissues and can be seen as children get puffy. This is called Nephrotic Syndrome.</p>\n\n<p>The cause of Nephrotic Syndrome in most cases is unknown, although we are learning more about the disease and the changes it can cause in the kidney cells. Also we are learning more about genes which are involved – more about this later!</p>\n\n<p>Doctors will try to treat the disease with steroids in the first instance, however when these do not work the Nephrotic Syndrome is defined as being ‘Steroid Resistant Nephrotic Syndrome’ (SRNS) – this can include one type known as focal segmental glomerulosclerosis (FSGS). </p>\n\n<p>Steroid resistant nephrotic syndrome (SRNS) can be a difficult disease to control even with other strong medicine and it can sometimes cause significant kidney damage leading to kidney failure. </p>\n\n<p>If a child with SRNS or FSGS has kidney failure and needs a kidney transplant, there is a chance that the illness can come back after the transplant. This is more likely with subsequent transplants than with the first. It can often be treated and can get completely better, but this is also difficult to achieve.</p>\n\n<p><b>The glomerular filtration barrier</b></p>\n\n<p>The functional unit of the kidney is known as the nephron. At one end there is the glomerulus which is connected to the tubule. It is the glomerulus that is important in the development of nephrotic syndrome. Blood, is pumped from the heart around the circulation to the kidneys. Inside the kidney, the blood travels in narrow blood vessels. The glomerulus sit very close to these blood vessels. There is transfer of waste products, salts and some water from the blood across into the glomerulus. This is controlled by the barrier (sieve) between the two. We call this the glomerular filtration barrier and it is made up of three layers (the blood vessel wall – known as the endothelium, the glomerular basement membrane and a specialised cell known as the podocyte). </p>\n\n<p><b>The podocyte cell</b></p>\n\n<p>The podocyte cell is a very specialised cell. It has a central body and many ‘feet’ which sit over the basement membrane and endothelium in the glomerular filtration barrier. These ‘feet’ lie very close to ‘feet’ from other podocyte cells and work together to form a sieve. In nephrotic syndrome the ‘feet’ don’t develop as normal and the holes of the sieve become bigger causing protein to leak out of the blood vessels with the waste products. This results in protein leaking into the urine causing the clinical picture of nephrotic syndrome.</p>\n\n<p><b>The Study Group</b></p>\n\n<p>Professor Moin Saleem is the head of the Academic Renal Unit based in North Bristol as well as Consultant in Paediatric Kidney Medicine at the Children’s Hospital in Bristol. He has developed a special interest in the biology of the cells involved in nephrotic syndrome and why these malfunction which results in the kidneys becoming leaky to protein. The laboratory is well known throughout the world for this work. </p>\n\n<p>The analysis of the samples will be undertaken by members of the research team including scientists, a laboratory technician and a doctor who is training to be a consultant in paediatric kidney medicine. They are all based in the laboratory in North Bristol which is secure and requires ID access to get in.</p>\n\n<p><b>The Research Project</b></p>\n\n<p>There are three main parts to this study. Firstly each patient has consented to their clinical information being uploaded onto the RaDaR database. This information will be pooled for all patients and, using statistics, will be analysed. We are looking for patterns – for instance whether it is more common in boys than girls. Although these patterns have been looked for before, it has not been done in such a big group or in the UK. </p>\n\n<p>Secondly we would like to look at genes associated with SRNS/FSGS. Over the last ten years our knowledge about what goes wrong in the cells of the kidney has increased significantly. Associated with this, we have found that, in some cases, these problems are caused by abnormal coding of our genes.</p>\n\n<p>Genes are inherited from our mother and fathers and sit in each of our cells. They are like a computer program that tells the cell how to work and what to produce to do the job they are designed for. Sometimes, during our development the genes are altered so that they do not work as well and sometimes we can inherit abnormal genes from our mother or father. We refer to these alterations as gene mutations.</p>\n\n<p>In this study we would like to test for the gene mutations that can cause SRNS/FSGS. It is not known how many children will have a gene mutation but other studies suggest that in children without another family member affected, there is a 15-20% chance of having a mutation causing the SRNS/FSGS. </p>\n\n<p>When the genes are tested, the results will be fed back to the patient’s kidney doctor (if this is what the patient has consented to) and they will discuss the findings. There is also a link to information about the genes being tested below.</p>\n\n<p>Having tested the children for these genes we will compare how the disease differs in those with gene mutations compared with those without, and hopefully learn more about what causes the disease, and how to manage it better.</p>\n\n<p>Lastly, the laboratory in Bristol has developed markers of how active the disease is in blood or plasma samples, in the context of the illness coming back after transplantation. We would like to explore this further on a larger number of samples to validate these findings and try and develop a test to predict the chance of recurrence of the illness after transplantation. These are called ‘biomarkers’ and look at particular biological-pathways inside the kidney cells and their response to disease. This will be important in being able to advise patients in advance about the risks and benefits of transplantation for each individual.</p>\n\n<p><b>The genes being tested</b></p>\n\n<p><b>Nephrin (NPHS1)</b></p>\n\n<p>The NPHS1 gene codes to make a protein called Nephrin that is only found in the podocyte cells in the kidney. </p>\n\n<p>Nephrin is important in maintaining the shape of the podocyte cell in the kidneys and controls some of the signalling that occurs inside the podocyte cell. Without it the cells do not maintain normal structure and this affects the glomerular filtration barrier function making the kidney leaky.</p>\n\n<p>It was found in 1998 by a group looking at a condition called ‘the Finnish type of congenital nephrotic syndrome’ which occurs in babies in the first few months of life. </p>\n\n<p>However, very recently, a handful of children who have developed SRNS after the age of 1year, have been found to have mutations in this gene. They had nephrotic syndrome which was resistant to steroids and other medications but generally had a slow progression of disease.</p>\n\n<p><b>Podocin (NPHS2)</b></p>\n\n<p>The NPHS2 gene codes for a protein called Podocin which again is only found in the podocyte cell in the kidney. Podocin works closely with Nephrin in maintaining the structural shape of the slit diaphragm and helps the Nephrin signal to other parts of the cell. Like Nephrin, the absence of Podocin causes dysfunction of the glomerular filtration barrier resulting in protein leak from the kidneys.</p>\n\n<p>It was found in 2000 and has since been shown to be relatively common (in about 25%) of children affected by FSGS where other members of the family are also affected. It also occurs in children where there is no family history but is not as common. It is thought that children with Podocin mutations have a lower risk of the disease coming back after transplantation than children without.</p>\n\n<p><b>PLCe1 (NPHS3)</b></p>\n\n<p>This gene was found in 2006 and codes for a protein (phospholipase C epsilon) which helps with sending signals around the podocyte cell. It also helps with development of the podocyte and so in its absence the cells probably do not fully develop and it can again cause disruption to the glomerular filtration barrier which results in protein leak from kidneys.</p>\n\n<p><b>WT1</b></p>\n\n<p>This gene codes for Wilms Tumour Suppressor Gene 1 which when mutated can cause several illnesses in children. It can cause a severe form of nephrotic syndrome in infants under one known as diffuse mesangial sclerosis but it can also cause FSGS. Often the nephrotic syndrome is associated with other abnormalities such as eye problems or genitourinary problems.</p>\n\n<p><b>TRPC6</b></p>\n\n<p>This is a gene which codes for a protein which sits on the surface of the podocyte cell and controls the entry of calcium into the cell which in turn is important for signalling within the cell. </p>\n\n<p>In 1999 a group studying a large family where the disease affected several members in adulthood, found that this gene was mutated and responsible for them developing FSGS. This has been repeated with other families where several members are affected. More recently it was described in a child for the first time.</p>\n\n<p><b>CD2AP</b></p>\n\n<p>This is a gene that codes for a protein located in podocyte cells and other cells. In the podocyte it works with Nephrin and Podocin and helps maintain the structure of the cell. It has been found in children and adults that when it is mutated it can cause loss of the special function of the podocyte cells and the glomerular filtration barrier and therefore the kidneys leak protein. </p>\n\n<p><b>Alpha-actinin 4</b></p>\n\n<p>This gene codes for a protein which again is involved with maintaining the structure and therefore function of the podocyte cell interacting with Nephrin and Podocin in the process. It was first identified in 1998 and initial work found that in a large family where several members had FSGS and no other cause was identified, then this gene was found to be mutated. So far almost all work with this has looked at adults with FSGS; this will be the biggest group of children to be tested for it.</p>\n\n<p><b>Support groups</b></p>\n\n<p><b>The UK National Kidney Federation</b> <a href=\"http://www.kidney.org.uk/\">www.kidney.org.uk</a></p>\n\n<p>The NKF is the national charity in the United Kingdom Run by Kidney Patients for Kidney Patients.</p>\n\n<p>The Federation’s aim throughout the UK is to promote both the best renal medical practice and treatment, and the health of persons suffering from Chronic Kidney Disease (CKD) or Established Renal Failure (ERF).</p>\n\n<p>The NKF also supports the related needs of those relatives and friends who care for kidney patients.</p>\n\n<p><b>Nephrotic Support Trust</b> <a href=\"http://www.nstrust.co.uk/\">www.nstrust.co.uk</a></p>\n\n<p>The <em>Nephrotic Syndrome Trust</em>, or NeST, was set up in 2005 by trustee David Yearsley whose son James sufferers from the disease, and is now run by Wendy Cook, another parent caring for a child with NS.</p>\n\n<p>Nephrotic Syndrome affects around 10,000 people in the UK. Doctors do not fully understand the cause and there is currently no known cure. But a dedicated team at the specialist Kidney Unit at Bristol Children\'s hospital is researching the disorder in hope of finding a cure.</p>\n\n<p>This research work is funded solely by charitable donations and grants and has to proceed on a project-by-project basis. It was for this reason that a dedicated charity has been set up to raise money to fund this vital research.</p>\n\n<p>The Trust aims to:</p>\n\n<ul>\n<li>Raise awareness of the disease</li>\n<li>Raise funds for research to find a cure</li>\n<li>Offer a website resource and forum for sufferers and their carers who often feel isolated</li>\n</ul>\n\n<p>NeST is supported by the world-renowned rugby player Jona Lomu himself a sufferer of NS who has agreed to be an Ambassador for the charity.</p>\n\n<p>Our <a href=\"http://www.nstrust.co.uk/nstrust-community/index.php\" target=\"_blank\" title=\"Go to our NSTrust Community\">NSTrust Community</a> Bulletin Board is now up and running and you can read and post messages relating to Nephrotic Syndrome and NeST.</p>\n\n<p><b>Nephcure International</b> <a href=\"http://www.nephcure-international.org/\">www.nephcure-international.org</a></p>\n\n<p>Nephrotic Syndrome, a rare disease, affects thousands of individuals throughout the world, hundreds more are diagnosed every day. NephCure International (NI), a newly organized consortium of eight (8) countries, is the only global patient organization solely dedicated to finding a cause and a cure for Idiopathic <a href=\"http://www.nephcure.org/Info_aboutneph.html\">Nephrotic Syndrome</a>. Comprised and supported by patients, families and researchers from all over the world, NI is empowered by hope and perseverance in pursuit of its mission. </p>\n\n<p>Together these countries, will share their ideas, combine their strengths and support each other in order to grow.</p>\n\n<p>Together we stand for all patients and are dedicated to: Provide information, education, support and advocacy for patients and their families, while raising overall public awareness</p>\n'),(2,'<div>\n	<h1>Membranoproliferative Glomerulonephritis and Dense deposit \n	Disease </h1>\n	\n	<p><strong><em>What does the name &quot;membranoproliferative glomerulonephritis&quot; \n	mean? </p>\n	</em></strong>\n	<p><em>Membranoproliferative glomerulonephritis</em> (MPGN) is the name \n	given to a group of related kidney disorders. Some doctors use the term <em>\n	mesangiocapillary glomerulonephritis</em> instead, but the two names refer \n	to the same group of conditions. </p>\n	<p>Glomerulonephritis is the technical word to describe inflammation of the \n	special blood vessels in the kidney that filter the bloodstream. The filters \n	are known as glomeruli, hence the name. The diagnosis of MPGN is made by a \n	kidney biopsy, and describes the pattern of inflammation seen in the \n	filters. </p>\n	<p>MPGN is traditionally divided into three groups; type 1, type 2 and type \n	3. This division is made according to some of the details seen in the \n	biopsy. In some people there is an overlap of features so that pathologists \n	cannot always clearly allocate a definite sub-type to every MPGN patient. \n	However, MPGN type 2 is sufficiently different from the other types to have \n	its own diagnostic term, <em>Dense Deposit Disease </em>(DDD). </p>\n	<p><strong><em>How is the diagnosis made? </p>\n	</em></strong>\n	<p>Inflammation in the kidney filters, glomerulonephritis, allows blood and \n	protein from the plasma to escape into the urine. Often it is finding blood \n	and protein in the urine that leads to investigation for kidney condition. \n	Urine is mostly water that has been filtered out of the blood plasma by the \n	kidneys. The filters do not normally allow red blood cells or protein \n	(albumin) into the urine. (Albumin in the human blood stream is very like \n	chicken albumin which is the clear gelatinous &quot;white&quot; of an egg.) </p>\n	<p>Patients may have so great a loss of albumin into the urine that their \n	blood level of albumin is low. This can cause the body to retain fluids \n	leading to swelling (oedema).&nbsp; This sequence of events is called \n	nephrotic syndrome. MPGN is just one possible cause of nephrotic syndrome.\n	</p>\n	<p>Some patients with MPGN will have reduced kidney function. That is to say \n	waste products that are normally excreted (removed) in the urine can build \n	up in the body. Usually there are few symptoms from this, and a simple blood \n	test is used to see how well the kidneys are functioning. People with MPGN \n	often have signs of anaemia (they look pale and lack energy), and quite \n	often get high blood pressure. </p>\n	<p>Blood tests help to exclude alternative diagnoses. However the diagnosis \n	can only be made for certain with a kidney biopsy. </p>\n	<p><strong><em>Kidney biopsy. </p>\n	</em></strong>\n	<p>The small core of kidney tissue obtained by a biopsy is cut into thin \n	slices, stained, and looked at under a microscope. Special stains are used \n	to identify key components of the immune system (such as complement \n	components discussed below). Usually the biopsy is also examined with an \n	electron microscope. This essential to diagnose DDD, the characteristic \n	dense deposits that give the condition it name are seen in the filter \n	membranes of the kidney. </p>\n	<strong><em></em></strong>\n	<p><strong><em>What causes MPGN?</em></strong>&nbsp;&nbsp; </p>\n	<p>For most people with MPGN the cause of the problem is not fully known. \n	However, certain infections can cause it, for example hepatitis C infection.&nbsp; \n	In some patients there are defects in the way their immune system is \n	regulated. Understanding how the immune system causes disease has improved \n	recently, although more need to be done. It raises hope for better treatment \n	in future. Some problems of regulating the immune system are genetic. Others \n	are acquired. Some of the infective or immune problems seem to relate to the \n	specific types of MPGN or DDD. </p>\n	<p>A part of the immune system that is important in MPGN is known as the \n	Complement System.&nbsp; The Complement System is used to kill germs very \n	quickly and defend us from infection. It is made up of several proteins that \n	are naturally present in body fluids. When they are activated, they send \n	signals to the body&#39;s white blood cells, and they join together to create \n	small holes (pores) in membranes. When this happens on the surface of a \n	bacterium, for example, it quickly kills it.&nbsp; Complement would be \n	dangerous if it was activated against our own healthy tissues by mistake. \n	There is a system of regulators to make sure that it does not. </p>\n	<p>In MPGN, complement is seen in the kidney filters. It should not be \n	there. It is almost certainly capable of causing the kind of damage that one \n	gets in MPGN.&nbsp; </p>\n	<p>There are different ways of activating complement. One uses \n	immunoglobulins (antibodies), another group of proteins that recognise germs \n	and direct the complement system to work right where it is needed on an \n	invading bacterium or virus. In MPGN type 1 and type 3 we typically find \n	immunoglobulin and complement together, meaning that this pathway is being \n	used. For example, antibodies known as cryoglobulins, probably caused \n	indirectly by infection, can activate complement and get deposited in kidney \n	filters. Cryoglobulins are associated with MPGN and can be detected in a \n	blood test. </p>\n	<p>Another pathway does not use immunoglobulin. Complement gets activated by \n	itself. This suggests that some of the normal regulators of complement are \n	not working properly. This pattern is more usual in MPGN type 2 (DDD). For \n	example, some patients have a deficiency of Factor H, an important regulator \n	of complement. This defect allows complement to be activated in the wrong \n	place at the wrong time, such as in the kidney. Animals bred to have a \n	genetic fault in Factor H get MPGN, confirming the importance of this idea.\n	</p>\n	<p>To make matters more complex, some people with MPGN of various types \n	develop antibodies against complement itself. These are known as nephritic \n	factors. They cause complement to be activated and escape the normal \n	regulatory processes. One can test for the presence of nephritic factor in \n	blood. It is not known why some people make it. </p>\n	<p><strong><em>What happens to people with MPGN? </p>\n	</em></strong>\n	<p>For most patients, MPGN is slowly progressive. Without treatment kidneys \n	tend to wear out over a decade because of the inflammation and damage to the \n	filters (glomeruli). All our glomeruli are made before we are born. After \n	that if some are damaged and lost they can not be replaced. Kidneys have \n	evolved to have considerable spare capacity, so kidney function can hold up \n	in spite of quite a lot of damage. The amount of damage can be assessed from \n	a kidney biopsy. The level of kidney function is assessed on a blood test \n	that measures a waste product such as creatinine. If the creatinine level \n	goes up it is because the kidney function has gone down.&nbsp; If kidney \n	function becomes very low, below about a tenth of normal, patients will need \n	dialysis or a kidney transplant. </p>\n	<p>Sometimes the disease goes into remission, either because of treatment or \n	spontaneously. Remission is indicated when the blood and protein in the \n	urine go away and the urine tests return to normal. If this happens it \n	implies that the inflammation in the kidney has ceased. Sometime the blood \n	tests show that the immune system remains abnormal even though the kidney \n	problem has improved. </p>\n	<p>In a few patients the disease is especially aggressive and kidney \n	function declines rapidly. For an individual patient it is not possible to \n	predict exactly how quickly or slowly the disease will go. However it there \n	is a lot of damage already present at the beginning of the illness this is \n	unfavourable. The kidney biopsy is useful in telling how much damage has \n	already occurred. </p>\n	<p><strong><em>Treatment options </p>\n	</em></strong>\n	<p>As the immune system is involved in the disease process, immune \n	suppressing treatments have been used. Some, such as corticosteroids \n	(Prednisolone), have shown benefit in trials, but they are not always well \n	tolerated and side effects are common. There is a lack of good quality trial \n	information on the newer immunosuppressant medicines. This is because \n	different types of MPGN and DDD might respond differently to different \n	classes of drug. Many of the older studies have not distinguished between \n	the various sub-types of MPGN, so they are difficult to interpret. Better, \n	modern studies are needed. </p>\n	<p>There is a lot that can be done to improve wellbeing even if it does not \n	address the root cause of the problem. These days many patients will be \n	treated with angiotensin antagonists/inhibitors to reduce the wastage of \n	protein in the urine and improve blood pressure. Diuretic medicines \n	(medicines that help get rid of water from the body) can help control oedema \n	(swelling). Nutritional support helps to keep up the strength of bones and \n	muscles. Attention to detail is important, and decisions have to be tailored \n	to each person&#39;s needs. Patients with MPGN or DDD should be closely \n	supervised by a renal physician or a paediatric nephrologist. </p>\n	<p>If the kidney damage becomes severe enough, and kidney function is too \n	low, kidney replacement treatment becomes necessary. This might involve \n	dialysis or kidney transplantation. Some patients get MPGN in the \n	transplanted kidney. This is not necessarily as severe as the original \n	illness, and some people show evidence of the process in a transplant kidney \n	biopsy with very little in the way of disease activity. However, some will \n	lose the transplant because of disease recurrence. There needs to be a \n	careful evaluation of a patient&#39;s transplant risks early on, although \n	predicting the risk is not well refined. </p>\n	<p><strong><em>MPGN studies </p>\n	</em></strong>\n	<p>The MPGN Working Group is a consortium of doctors, scientists and others \n	who study different aspects of MPGN and provide better information for \n	patients and professionals. The Group is collecting clinical and laboratory \n	information on as many children and adults with MPGN and DDD as possible. It \n	is also looking to perform new treatment trials. For information about the \n	group click the MPGN/DDD Working Group link here. </p>\n	<p>Stephen Marks and Mark Taylor, v 3-2-10 </p>\n	<p></p>\n	<p></p>\n	<p></p>\n	<p></p>\n	<p></p>\n</div>\n');
/*!40000 ALTER TABLE `tbl_diseasedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ethnicity`
--

DROP TABLE IF EXISTS `tbl_ethnicity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_ethnicity` (
  `eID` int(11) NOT NULL AUTO_INCREMENT,
  `eName` varchar(50) DEFAULT NULL,
  `eCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`eID`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ethnicity`
--

LOCK TABLES `tbl_ethnicity` WRITE;
/*!40000 ALTER TABLE `tbl_ethnicity` DISABLE KEYS */;
INSERT INTO `tbl_ethnicity` VALUES (1,'White','9S1..'),(2,'Black Caribbean','9S2..'),(3,'Black African','9S3..'),(4,'Indian','9S6..'),(5,'Pakistani','9S7..'),(6,'Indian sub-continent (NMO)','9SA7.'),(7,'Other Asian (NMO)','9SA8.'),(8,'Other Ethnic NEC','9SAD.'),(9,'Chinese','9S9..'),(10,'Black / other / non-mixed origin','9S4..'),(11,'Other ethnic / mixed origin','9SB..'),(12,'Bangladeshi','9S8.'),(13,'Black - other / mixed','9S5..'),(14,'Black - other African country','9S44.'),(15,'Black - other Asian','9S47.'),(17,'Black Black - other','9S48.'),(18,'Black British','9S41.'),(19,'Black Caribbean','9S42.'),(20,'Black East African Asian','9S45.'),(21,'Black Indian sub-continent','9S46.'),(22,'Black North African','9S43.'),(23,'Brit. ethnic minor. spec. (NMO)','9SA1.'),(24,'Brit. ethnic minor. unsp. (NMO)','9SA2.'),(25,'Caribbean Island (NMO)','9SA3.'),(26,'East African Asian (NMO)','9SA6.'),(27,'Greek Cypriot (NMO)','9SAA.'),(28,'Irish (NMO)','9SA9.'),(29,'North African Arab (NMO)','9SA4.'),(30,'Other African countries (NMO)','9SA5.'),(31,'Other Black - Black/Asian orig','9S52.'),(32,'Other Black - Black/White orig','9S51.'),(33,'Other Ethnic / Asian/White orig','9SB2.'),(34,'Other Ethnic / Black/White orig','9SB1.'),(35,'Other Ethnic / mixed white orig','9Sb3.'),(36,'Other Ethnic / other mixed orig','9SB4.'),(37,'Other Ethnic non-mixed (NMO)','9SA..'),(38,'Other European (NMO)','9SAC.'),(39,'Turkish Cypriot (NMO)','9SAB.');
/*!40000 ALTER TABLE `tbl_ethnicity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_genemutation`
--

DROP TABLE IF EXISTS `tbl_genemutation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_genemutation` (
  `gmID` int(11) NOT NULL AUTO_INCREMENT,
  `GENE_MUTATION` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gmID`)
) ENGINE=MyISAM AUTO_INCREMENT=212 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_genemutation`
--

LOCK TABLES `tbl_genemutation` WRITE;
/*!40000 ALTER TABLE `tbl_genemutation` DISABLE KEYS */;
INSERT INTO `tbl_genemutation` VALUES (1,'ACTN4 - ALPHA-ACTININ 4'),(2,'CD2AP'),(3,'LAMB2 - LAMININ-BETA-2'),(4,'NPHS1 - NEPHRIN'),(5,'NPHS2 - PODOCIN'),(6,'NPHS3 - PLCE1'),(7,'TRPC6'),(8,'WT1 - Wilms\' tumour'),(9,'Other'),(88,'Sample sent'),(99,'Not done'),(200,'CFH'),(201,'CFI'),(202,'CD46'),(203,'CFB'),(204,'C3'),(205,'CFHR1'),(206,'CFHR2'),(207,'CFHR3'),(208,'CFHR4'),(209,'CFHR5'),(210,'CR1'),(211,'Factor H autoantibodies');
/*!40000 ALTER TABLE `tbl_genemutation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hd_modality`
--

DROP TABLE IF EXISTS `tbl_hd_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hd_modality` (
  `hdID` int(11) NOT NULL AUTO_INCREMENT,
  `hdType` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`hdID`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hd_modality`
--

LOCK TABLES `tbl_hd_modality` WRITE;
/*!40000 ALTER TABLE `tbl_hd_modality` DISABLE KEYS */;
INSERT INTO `tbl_hd_modality` VALUES (1,'Haemodialysis'),(2,'Haemofiltration'),(3,'Haemodiafiltration'),(4,'Haemodialysis > 4 days per week / daily'),(5,'Ultrafiltration'),(9,'Haemodialysis – type unknown');
/*!40000 ALTER TABLE `tbl_hd_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hdial`
--

DROP TABLE IF EXISTS `tbl_hdial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hdial` (
  `hdID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_START_HDIAL` datetime DEFAULT NULL,
  PRIMARY KEY (`hdID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hdial`
--

LOCK TABLES `tbl_hdial` WRITE;
/*!40000 ALTER TABLE `tbl_hdial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_hdial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hospitalisation`
--

DROP TABLE IF EXISTS `tbl_hospitalisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hospitalisation` (
  `hID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_ADMIT` datetime DEFAULT NULL,
  `DATE_DISCHARGE` datetime DEFAULT NULL,
  `REASON_ADMIT` varchar(250) DEFAULT NULL,
  `COMMENT` text,
  PRIMARY KEY (`hID`)
) ENGINE=MyISAM AUTO_INCREMENT=497 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hospitalisation`
--

LOCK TABLES `tbl_hospitalisation` WRITE;
/*!40000 ALTER TABLE `tbl_hospitalisation` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_hospitalisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_immunosupp`
--

DROP TABLE IF EXISTS `tbl_immunosupp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_immunosupp` (
  `imID` int(11) NOT NULL AUTO_INCREMENT,
  `imDesc` varchar(50) DEFAULT NULL,
  `Group` int(11) DEFAULT NULL,
  PRIMARY KEY (`imID`)
) ENGINE=MyISAM AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_immunosupp`
--

LOCK TABLES `tbl_immunosupp` WRITE;
/*!40000 ALTER TABLE `tbl_immunosupp` DISABLE KEYS */;
INSERT INTO `tbl_immunosupp` VALUES (1,'Corticosteroids',NULL),(2,'MMF',NULL),(3,'Azathioprine',NULL),(4,'Ciclosporin',NULL),(6,'Tacrolimus',NULL),(7,'Sirolimus',NULL),(8,'Cyclophosphamide',NULL),(10,'Rituximab',NULL),(11,'Alemtuzumab',NULL),(12,'Daclizumab',NULL),(13,'Basiliximab',NULL),(14,'Eculizumab',NULL),(15,'Levamisole',NULL),(88,'Other',NULL);
/*!40000 ALTER TABLE `tbl_immunosupp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_immunsup_treatment`
--

DROP TABLE IF EXISTS `tbl_immunsup_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_immunsup_treatment` (
  `tID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `IMMUNSUP_DRUG_STARTDATE` datetime DEFAULT NULL,
  `IMMUNSUP_DRUG_ENDDATE` datetime DEFAULT NULL,
  `IMMUNSUP_DRUG` int(11) DEFAULT NULL,
  `CYCLOPHOS_TOT_DOSE` decimal(5,3) DEFAULT NULL,
  `FIRST_FLAG` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tID`)
) ENGINE=MyISAM AUTO_INCREMENT=612 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_immunsup_treatment`
--

LOCK TABLES `tbl_immunsup_treatment` WRITE;
/*!40000 ALTER TABLE `tbl_immunsup_treatment` DISABLE KEYS */;
INSERT INTO `tbl_immunsup_treatment` VALUES (611,740,'2013-08-01 00:00:00','2013-08-03 00:00:00',7,NULL,NULL);
/*!40000 ALTER TABLE `tbl_immunsup_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_issuetracker`
--

DROP TABLE IF EXISTS `tbl_issuetracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_issuetracker` (
  `bID` int(11) NOT NULL AUTO_INCREMENT,
  `bType` varchar(50) DEFAULT NULL,
  `bPage` varchar(50) DEFAULT NULL,
  `bDateLogged` datetime DEFAULT NULL,
  `bDateResolved` datetime DEFAULT NULL,
  `bDesc` text,
  `bComment` text,
  `bPriority` varchar(20) DEFAULT NULL,
  `bStatus` varchar(50) DEFAULT NULL,
  `bUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`bID`)
) ENGINE=MyISAM AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_issuetracker`
--

LOCK TABLES `tbl_issuetracker` WRITE;
/*!40000 ALTER TABLE `tbl_issuetracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_issuetracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_karyotype`
--

DROP TABLE IF EXISTS `tbl_karyotype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_karyotype` (
  `kID` int(11) NOT NULL AUTO_INCREMENT,
  `KARYOTYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kID`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_karyotype`
--

LOCK TABLES `tbl_karyotype` WRITE;
/*!40000 ALTER TABLE `tbl_karyotype` DISABLE KEYS */;
INSERT INTO `tbl_karyotype` VALUES (1,'XX'),(2,'XY'),(9,'Not done'),(8,'Other');
/*!40000 ALTER TABLE `tbl_karyotype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_labdata`
--

DROP TABLE IF EXISTS `tbl_labdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_labdata` (
  `labID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_LAB_RES` datetime DEFAULT NULL,
  `CREAT_SER` int(11) DEFAULT NULL,
  `PROTEIN` int(11) DEFAULT NULL,
  `ALBUMIN` int(11) DEFAULT NULL,
  `UREA_SER` decimal(3,1) DEFAULT NULL,
  `SODIUM` int(11) DEFAULT NULL,
  `POTASSIUM` decimal(2,1) DEFAULT NULL,
  `PHOS` decimal(3,2) DEFAULT NULL,
  `PROT_CREAT_RAT` decimal(6,1) DEFAULT NULL,
  `ALB_CREAT_RAT` decimal(5,1) DEFAULT NULL,
  `WBC` decimal(3,1) DEFAULT NULL,
  `HB` decimal(3,1) DEFAULT NULL,
  `NEUTRO` decimal(3,1) DEFAULT NULL,
  `PLATELETS` int(11) DEFAULT NULL,
  `FERRITIN` int(11) DEFAULT NULL,
  `CHOL_TOTAL` decimal(3,1) DEFAULT NULL,
  `CHOL_HDL` decimal(3,1) DEFAULT NULL,
  `CHOL_LDL` decimal(3,1) DEFAULT NULL,
  `TRIG` decimal(3,1) DEFAULT NULL,
  `CREAT_CLEAR_24_URINE` int(11) DEFAULT NULL,
  `CREAT_CLEAR_RADIO` int(11) DEFAULT NULL,
  `CREAT_CLEAR_SCHZ` int(11) DEFAULT NULL,
  `THYROX` decimal(4,1) DEFAULT NULL,
  `TSH` decimal(4,2) DEFAULT NULL,
  `ANCA` int(11) DEFAULT NULL,
  `ELISA_ASS` int(11) DEFAULT NULL,
  `ENA` int(11) DEFAULT NULL,
  `ANA` int(11) DEFAULT NULL,
  `DNA_ANTIB` varchar(50) DEFAULT NULL,
  `DNA_ANTI_DS` int(11) DEFAULT NULL,
  `CRYOGLOB` varchar(50) DEFAULT NULL,
  `ANTI_GBM` varchar(50) DEFAULT NULL,
  `IGG` decimal(4,1) DEFAULT NULL,
  `IGA` decimal(4,1) DEFAULT NULL,
  `IGM` decimal(4,1) DEFAULT NULL,
  `COMP_C3` decimal(4,2) DEFAULT NULL,
  `COMP_C4` decimal(4,2) DEFAULT NULL,
  `COMP_OTHER` text,
  `C3_NEPH_FAC` int(11) DEFAULT NULL,
  `ANTI_SLT` int(11) DEFAULT NULL,
  `INR` decimal(2,1) DEFAULT NULL,
  `CRP` int(11) DEFAULT NULL,
  `ANTI_STREP_O` int(11) DEFAULT NULL,
  `HEP_B` int(11) DEFAULT NULL,
  `HEP_C` int(11) DEFAULT NULL,
  `HIV` int(11) DEFAULT NULL,
  `DNA_FACTOR_H` bit(1) DEFAULT NULL,
  `EBV` int(11) DEFAULT NULL,
  `CMV` int(11) DEFAULT NULL,
  `CMV_SYM` bit(1) DEFAULT NULL,
  `BKV` bit(1) DEFAULT NULL,
  `BKV_SYM` bit(1) DEFAULT NULL,
  `HANTAVIRUS` bit(1) DEFAULT NULL,
  `PARVO_ANTIB` int(11) DEFAULT NULL,
  `OTHER_INFECT` bit(1) DEFAULT NULL,
  `OTHER_INFECT_SP` varchar(50) DEFAULT NULL,
  `UR_VOL_24H` int(11) DEFAULT NULL,
  `UR_VOL_24H_COND` int(11) DEFAULT NULL,
  `HAEMATURIA` int(11) DEFAULT NULL,
  `ALBUMINURIA` int(11) DEFAULT NULL,
  `DYS_ERYTH_URINE` int(11) DEFAULT NULL,
  `RED_CCASTS_URINE` int(11) DEFAULT NULL,
  `WBC_CASTS_URINE` int(11) DEFAULT NULL,
  `LEUC_URINE` bit(1) DEFAULT NULL,
  `NITRITE` bit(1) DEFAULT NULL,
  `BACT_URINE` bit(1) DEFAULT NULL,
  `GLUC_URINE` bit(1) DEFAULT NULL,
  `OSMOLARITY` varchar(50) DEFAULT NULL,
  `PROTEINURIA_DIP` int(11) DEFAULT NULL,
  `SEQ_NO` int(11) DEFAULT NULL,
  `ANTI_CLQ` decimal(4,1) DEFAULT NULL,
  PRIMARY KEY (`labID`)
) ENGINE=MyISAM AUTO_INCREMENT=1973 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_labdata`
--

LOCK TABLES `tbl_labdata` WRITE;
/*!40000 ALTER TABLE `tbl_labdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_labdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_monoclonal`
--

DROP TABLE IF EXISTS `tbl_monoclonal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_monoclonal` (
  `mID` int(11) NOT NULL AUTO_INCREMENT,
  `mDesc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_monoclonal`
--

LOCK TABLES `tbl_monoclonal` WRITE;
/*!40000 ALTER TABLE `tbl_monoclonal` DISABLE KEYS */;
INSERT INTO `tbl_monoclonal` VALUES (1,'Rituximab'),(2,'Eculizumab'),(8,'Other');
/*!40000 ALTER TABLE `tbl_monoclonal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pathology`
--

DROP TABLE IF EXISTS `tbl_pathology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pathology` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `BX_DATE` datetime DEFAULT NULL,
  `NAT_TRANSP_KID` int(11) DEFAULT NULL,
  `LATERALITY_BX` int(11) DEFAULT NULL,
  `SAMPLE_LAB_NO` varchar(20) DEFAULT NULL,
  `PATHDIAG` int(11) DEFAULT NULL,
  `GLOM_TOTAL_NO` int(11) DEFAULT NULL,
  `GLOM_GLOB_SCL` int(11) DEFAULT NULL,
  `GLOM_SEQ_SCL` int(11) DEFAULT NULL,
  `GLOM_CELL_CRES` int(11) DEFAULT NULL,
  `GLOM_FIB_CRES` int(11) DEFAULT NULL,
  `GLOM_END_HYPER` int(11) DEFAULT NULL,
  `GLOM_FIN_NEC` int(11) DEFAULT NULL,
  `GLOM_ANY_OTH_FEAT` varchar(50) DEFAULT NULL,
  `TUB_ATROP_IF_EST` int(11) DEFAULT NULL,
  `TUB_ATROP_IF_MEAS` decimal(3,1) DEFAULT NULL,
  `TUB_OTHER_FEAT` varchar(150) DEFAULT NULL,
  `INTER_INFLAM_INFIL` varchar(150) DEFAULT NULL,
  `ART_ABNORMAL` varchar(150) DEFAULT NULL,
  `IMM_HIST_FIND` varchar(150) DEFAULT NULL,
  `ELECT_MSCOPE_FIND` varchar(150) DEFAULT NULL,
  `IMAGE_URL1` varchar(150) DEFAULT NULL,
  `IMAGE_URL2` varchar(150) DEFAULT NULL,
  `IMAGE_URL3` varchar(150) DEFAULT NULL,
  `IMAGE_URL4` varchar(150) DEFAULT NULL,
  `IMAGE_URL5` varchar(150) DEFAULT NULL,
  `PATH_TXT` text,
  `SEQ_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=MyISAM AUTO_INCREMENT=295 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pathology`
--

LOCK TABLES `tbl_pathology` WRITE;
/*!40000 ALTER TABLE `tbl_pathology` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_pathology` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_patient_users`
--

DROP TABLE IF EXISTS `tbl_patient_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_patient_users` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `pDOB` datetime DEFAULT NULL,
  `pDateReg` datetime DEFAULT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=MyISAM AUTO_INCREMENT=121 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_patient_users`
--

LOCK TABLES `tbl_patient_users` WRITE;
/*!40000 ALTER TABLE `tbl_patient_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_patient_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pd_modality`
--

DROP TABLE IF EXISTS `tbl_pd_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pd_modality` (
  `pdID` int(11) NOT NULL AUTO_INCREMENT,
  `pdType` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`pdID`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pd_modality`
--

LOCK TABLES `tbl_pd_modality` WRITE;
/*!40000 ALTER TABLE `tbl_pd_modality` DISABLE KEYS */;
INSERT INTO `tbl_pd_modality` VALUES (10,'CAPD connect'),(11,'CAPD disconnect'),(12,'Cycling PD >= 6  nights/wk dry '),(13,'Cycling PD < 6 nights /wk dry'),(14,'Cycling PD >= 6  nights/wk wet (day dwell)'),(15,'Cycling PD < 6 nights /wk wet (day dwell)'),(16,'Assisted Cycling PD >=6  nights/wk dry'),(17,'Assisted Cycling PD >=6  nights/wk wet (day dwell)'),(19,'Peritoneal dialysis – type unknown');
/*!40000 ALTER TABLE `tbl_pd_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pdial`
--

DROP TABLE IF EXISTS `tbl_pdial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pdial` (
  `pdID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_START_PDIAL` datetime DEFAULT NULL,
  PRIMARY KEY (`pdID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pdial`
--

LOCK TABLES `tbl_pdial` WRITE;
/*!40000 ALTER TABLE `tbl_pdial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_pdial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_phenotypes`
--

DROP TABLE IF EXISTS `tbl_phenotypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_phenotypes` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `pDesc` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_phenotypes`
--

LOCK TABLES `tbl_phenotypes` WRITE;
/*!40000 ALTER TABLE `tbl_phenotypes` DISABLE KEYS */;
INSERT INTO `tbl_phenotypes` VALUES (1,'Auto-immune disease'),(2,'Mental Retardation'),(3,'Polydactyly'),(4,'Blindness'),(5,'Deafness'),(6,'Cardiomyopathy'),(7,'Congenital CMV'),(8,'Other TORCH infections'),(9,'Diabetes'),(10,'Microcephaly'),(11,'Nail-Patella Syndrome'),(12,'Cardiac anomalies'),(13,'Hepatitis B'),(14,'Hepatitis C'),(15,'CNS abnormalitites'),(16,'Spondylo-epiphyseal dysplasia'),(17,'Microcoria'),(18,'Male pseudohermaphroditism');
/*!40000 ALTER TABLE `tbl_phenotypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_plasmaph`
--

DROP TABLE IF EXISTS `tbl_plasmaph`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_plasmaph` (
  `plID` int(11) NOT NULL AUTO_INCREMENT,
  `RENAL_NO` int(11) DEFAULT NULL,
  `DATE_STARTED_PLASMAPH` datetime DEFAULT NULL,
  `DUR_PLASMAPH` int(11) DEFAULT NULL,
  `NO_EXCH_PLASMAPH` int(11) DEFAULT NULL,
  PRIMARY KEY (`plID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_plasmaph`
--

LOCK TABLES `tbl_plasmaph` WRITE;
/*!40000 ALTER TABLE `tbl_plasmaph` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_plasmaph` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_relapse`
--

DROP TABLE IF EXISTS `tbl_relapse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_relapse` (
  `relID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_ONSET_RELAP` datetime DEFAULT NULL,
  `RELAP_TX_NAT` bit(1) DEFAULT NULL,
  `TRIG_VIRAL` varchar(50) DEFAULT NULL,
  `TRIG_IMMUN` varchar(50) DEFAULT NULL,
  `TRIG_OTHER` varchar(50) DEFAULT NULL,
  `RELAP_DRUG_1` varchar(50) DEFAULT NULL,
  `RELAP_DRUG_2` varchar(50) DEFAULT NULL,
  `RELAP_DRUG_3` varchar(50) DEFAULT NULL,
  `REMISS_ACHIEVE` int(11) DEFAULT NULL,
  `DATE_REMISSION` datetime DEFAULT NULL,
  `SEQ_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`relID`)
) ENGINE=MyISAM AUTO_INCREMENT=183 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_relapse`
--

LOCK TABLES `tbl_relapse` WRITE;
/*!40000 ALTER TABLE `tbl_relapse` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_relapse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_relative`
--

DROP TABLE IF EXISTS `tbl_relative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_relative` (
  `rID` int(11) NOT NULL AUTO_INCREMENT,
  `RELATIVE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rID`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_relative`
--

LOCK TABLES `tbl_relative` WRITE;
/*!40000 ALTER TABLE `tbl_relative` DISABLE KEYS */;
INSERT INTO `tbl_relative` VALUES (1,'Mother'),(2,'Father'),(3,'Sister'),(4,'Brother'),(5,'Grandmother-Maternal'),(6,'Grandmother-Paternal'),(7,'Aunt-Maternal'),(8,'Aunt-Paternal'),(9,'Uncle-Maternal'),(10,'Uncle-Paternal'),(11,'Cousin-Maternal'),(12,'Cousin-Paternal'),(13,'Half Sister'),(14,'Half Brother'),(15,'Grandfather-Maternal'),(16,'Grandfather-Paternal');
/*!40000 ALTER TABLE `tbl_relative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_hd`
--

DROP TABLE IF EXISTS `tbl_rrt_hd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_hd` (
  `hID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `HD_TMT_MODALITY` int(11) DEFAULT NULL,
  `DATE_START_HDIAL` datetime DEFAULT NULL,
  `DATE_STOP_HDIAL` datetime DEFAULT NULL,
  PRIMARY KEY (`hID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_hd`
--

LOCK TABLES `tbl_rrt_hd` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_hd` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rrt_hd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_modality`
--

DROP TABLE IF EXISTS `tbl_rrt_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_modality` (
  `mID` int(11) NOT NULL AUTO_INCREMENT,
  `mType` varchar(50) DEFAULT NULL,
  `Group` int(11) DEFAULT NULL,
  PRIMARY KEY (`mID`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_modality`
--

LOCK TABLES `tbl_rrt_modality` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_modality` DISABLE KEYS */;
INSERT INTO `tbl_rrt_modality` VALUES (1,'Haemodialysis',NULL),(2,'Haemofiltration',NULL),(3,'Haemodiafiltration',NULL),(4,'Haemodialysis > 4 days per week / daily',NULL),(5,'Ultrafiltration',NULL),(9,'Haemodialysis – type unknown',NULL),(10,'CAPD connect',NULL),(11,'CAPD disconnect',NULL),(12,'Cycling PD >= 6  nights/wk dry ',NULL),(13,'Cycling PD < 6 nights /wk dry',NULL),(14,'Cycling PD >= 6  nights/wk wet (day dwell)',NULL),(15,'Cycling PD < 6 nights /wk wet (day dwell)',NULL),(16,'Assisted Cycling PD >=6  nights/wk dry',NULL),(17,'Assisted Cycling PD >=6  nights/wk wet (day dwell)',NULL),(19,'Peritoneal dialysis – type unknown',NULL),(20,'Transplant ; Cadaver donor',NULL),(21,'Transplant ; Live related – sibling',NULL),(22,'Transplant ; Live related – parent or child ',NULL),(23,'Transplant ; Live related – other',NULL),(24,'Transplant ; Live genetically unrelated',NULL),(25,'Transplant ; Cadaver donor + transp other organ',NULL),(26,'Transplant ; Live donor + transplant other organ',NULL),(27,'Transplant ; Live donor non-UK transplant',NULL),(28,'Transplant ; non-heart-beating donor',NULL),(29,'Transplant ; type unknown',NULL);
/*!40000 ALTER TABLE `tbl_rrt_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_pd`
--

DROP TABLE IF EXISTS `tbl_rrt_pd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_pd` (
  `pID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `PD_TMT_MODALITY` int(11) DEFAULT NULL,
  `DATE_START_PD` datetime DEFAULT NULL,
  `DATE_STOP_PD` datetime DEFAULT NULL,
  PRIMARY KEY (`pID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_pd`
--

LOCK TABLES `tbl_rrt_pd` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_pd` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rrt_pd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_plasma`
--

DROP TABLE IF EXISTS `tbl_rrt_plasma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_plasma` (
  `plID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `PLASMAPH` varchar(20) DEFAULT NULL,
  `DATE_START_PLASMAPH` datetime DEFAULT NULL,
  `DATE_STOP_PLASMAPH` datetime DEFAULT NULL,
  `NO_EXCH_PLASMAPH` varchar(10) DEFAULT NULL,
  `DUR_PLASMAPH` int(11) DEFAULT NULL,
  `RESPONSE_TO_PLASMA` int(11) DEFAULT NULL,
  PRIMARY KEY (`plID`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_plasma`
--

LOCK TABLES `tbl_rrt_plasma` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_plasma` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rrt_plasma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_plasma_lu`
--

DROP TABLE IF EXISTS `tbl_rrt_plasma_lu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_plasma_lu` (
  `exID` int(11) NOT NULL AUTO_INCREMENT,
  `exDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`exID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_plasma_lu`
--

LOCK TABLES `tbl_rrt_plasma_lu` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_plasma_lu` DISABLE KEYS */;
INSERT INTO `tbl_rrt_plasma_lu` VALUES (1,'Daily'),(2,'x5/wk'),(3,'x4/wk'),(4,'x3/wk'),(5,'x2/wk'),(6,'x1/wk'),(7,'x1/2wks'),(8,'x1/4wks');
/*!40000 ALTER TABLE `tbl_rrt_plasma_lu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rrt_treatment`
--

DROP TABLE IF EXISTS `tbl_rrt_treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rrt_treatment` (
  `tID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `MODALITY` int(11) DEFAULT NULL,
  `DATE_START` datetime DEFAULT NULL,
  `DATE_STOP` datetime DEFAULT NULL,
  `UNIT_CODE` int(11) DEFAULT NULL,
  `FIRST_FLAG` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tID`)
) ENGINE=MyISAM AUTO_INCREMENT=224 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rrt_treatment`
--

LOCK TABLES `tbl_rrt_treatment` WRITE;
/*!40000 ALTER TABLE `tbl_rrt_treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rrt_treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rt_modality`
--

DROP TABLE IF EXISTS `tbl_rt_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rt_modality` (
  `mID` int(11) NOT NULL,
  `mDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rt_modality`
--

LOCK TABLES `tbl_rt_modality` WRITE;
/*!40000 ALTER TABLE `tbl_rt_modality` DISABLE KEYS */;
INSERT INTO `tbl_rt_modality` VALUES (0,'Medical only - not started RRT'),(1,'Haemodialysis'),(2,'Peritoneal Dialysis'),(3,'Transplant'),(4,'Conservative management');
/*!40000 ALTER TABLE `tbl_rt_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sex`
--

DROP TABLE IF EXISTS `tbl_sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_sex` (
  `sID` int(11) NOT NULL AUTO_INCREMENT,
  `sType` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`sID`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sex`
--

LOCK TABLES `tbl_sex` WRITE;
/*!40000 ALTER TABLE `tbl_sex` DISABLE KEYS */;
INSERT INTO `tbl_sex` VALUES (1,'Male'),(2,'Female'),(9,'Not specified');
/*!40000 ALTER TABLE `tbl_sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_status`
--

DROP TABLE IF EXISTS `tbl_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_status` (
  `sID` int(11) NOT NULL AUTO_INCREMENT,
  `sDesc` varchar(50) DEFAULT NULL,
  `sAbbrev` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_status`
--

LOCK TABLES `tbl_status` WRITE;
/*!40000 ALTER TABLE `tbl_status` DISABLE KEYS */;
INSERT INTO `tbl_status` VALUES (1,'Current','Curr'),(2,'Transferred to adult unit','Adult'),(3,'Transferred to other Paediatric unit','Paed other'),(4,'Discharged','Disch'),(5,'Moved abroad','Abroad'),(6,'Died','Died');
/*!40000 ALTER TABLE `tbl_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_test`
--

DROP TABLE IF EXISTS `tbl_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_test` (
  `tID` smallint(6) NOT NULL AUTO_INCREMENT,
  `tOne` bit(1) DEFAULT NULL,
  `tTwo` bit(1) DEFAULT NULL,
  `tThree` char(10) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`tID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_test`
--

LOCK TABLES `tbl_test` WRITE;
/*!40000 ALTER TABLE `tbl_test` DISABLE KEYS */;
INSERT INTO `tbl_test` VALUES (1,'','','Test'),(2,'','\0','Test 2'),(3,'','\0','Third');
/*!40000 ALTER TABLE `tbl_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_therapy`
--

DROP TABLE IF EXISTS `tbl_therapy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_therapy` (
  `tID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `SIG_CHANGE_STATUS` varchar(20) DEFAULT NULL,
  `P_NSAID` bit(1) DEFAULT NULL,
  `NSAID` bit(1) DEFAULT NULL,
  `P_DIURETIC` bit(1) DEFAULT NULL,
  `DIURETIC` bit(1) DEFAULT NULL,
  `P_ANTI_HTN` bit(1) DEFAULT NULL,
  `ANTI_HTN` bit(1) DEFAULT NULL,
  `P_ACE_INHIB` bit(1) DEFAULT NULL,
  `ACE_INHIB` bit(1) DEFAULT NULL,
  `P_ARB_ANTAG` bit(1) DEFAULT NULL,
  `ARB_ANTAG` bit(1) DEFAULT NULL,
  `P_CA_CH_BLOCK` bit(1) DEFAULT NULL,
  `CA_CH_BLOCK` bit(1) DEFAULT NULL,
  `P_B_BLOCK` bit(1) DEFAULT NULL,
  `B_BLOCK` bit(1) DEFAULT NULL,
  `P_OTHER_ANTI_HTN` bit(1) DEFAULT NULL,
  `OTHER_ANTI_HTN` bit(1) DEFAULT NULL,
  `P_INSULIN` bit(1) DEFAULT NULL,
  `INSULIN` bit(1) DEFAULT NULL,
  `P_LIP_LOWER_AG` bit(1) DEFAULT NULL,
  `LIP_LOWER_AG` bit(1) DEFAULT NULL,
  `P_EPO` bit(1) DEFAULT NULL,
  `EPO` bit(1) DEFAULT NULL,
  `P_OTHER_DRUG1` varchar(50) DEFAULT NULL,
  `OTHER_DRUG1` varchar(50) DEFAULT NULL,
  `P_OTHER_DRUG2` varchar(50) DEFAULT NULL,
  `OTHER_DRUG2` varchar(50) DEFAULT NULL,
  `P_OTHER_DRUG3` varchar(50) DEFAULT NULL,
  `OTHER_DRUG3` varchar(50) DEFAULT NULL,
  `P_OTHER_DRUG4` varchar(50) DEFAULT NULL,
  `OTHER_DRUG4` varchar(50) DEFAULT NULL,
  `P_IMMUN_SUP` bit(1) DEFAULT NULL,
  `IMMUN_SUP` bit(1) DEFAULT NULL,
  `P_IMMUN_SUP_DRUG` varchar(50) DEFAULT NULL,
  `IMMUN_SUP_DRUG` varchar(50) DEFAULT NULL,
  `MONOCLONAL_YN` bit(1) DEFAULT NULL,
  `MONOCLONAL_NAME` varchar(50) DEFAULT NULL,
  `DATE_TREAT` datetime DEFAULT NULL,
  `TMT_MODALITY` varchar(50) DEFAULT NULL,
  `SEQ_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`tID`)
) ENGINE=MyISAM AUTO_INCREMENT=1475 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_therapy`
--

LOCK TABLES `tbl_therapy` WRITE;
/*!40000 ALTER TABLE `tbl_therapy` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_therapy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_transplant`
--

DROP TABLE IF EXISTS `tbl_transplant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_transplant` (
  `trID` int(11) NOT NULL AUTO_INCREMENT,
  `RADAR_NO` int(11) DEFAULT NULL,
  `DATE_TRANSPLANT` datetime DEFAULT NULL,
  `TRANS_TYPE` varchar(50) DEFAULT NULL,
  `TRANSPLANT_COUNTER` int(11) DEFAULT NULL,
  `DATE_NEPHRECT` datetime DEFAULT NULL,
  `TRANS_RECURR` bit(1) DEFAULT NULL,
  `DATE_RECURR_TXK` datetime DEFAULT NULL,
  `DATE_TX_REJECT` datetime DEFAULT NULL,
  `DATE_BX_TXK` datetime DEFAULT NULL,
  PRIMARY KEY (`trID`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_transplant`
--

LOCK TABLES `tbl_transplant` WRITE;
/*!40000 ALTER TABLE `tbl_transplant` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transplant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_transplant_modality`
--

DROP TABLE IF EXISTS `tbl_transplant_modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_transplant_modality` (
  `trID` int(11) NOT NULL AUTO_INCREMENT,
  `trDesc` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`trID`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_transplant_modality`
--

LOCK TABLES `tbl_transplant_modality` WRITE;
/*!40000 ALTER TABLE `tbl_transplant_modality` DISABLE KEYS */;
INSERT INTO `tbl_transplant_modality` VALUES (20,'Tx : Cadaver donor'),(21,'Tx : Live related – sibling'),(22,'Tx : Live related – parent or child '),(23,'Tx : Live related – other'),(24,'Tx : Live genetically unrelated'),(25,'Tx : Cadaver donor + transp other organ'),(26,'Tx : Live donor + transplant other organ'),(27,'Tx : Live donor non-UK transplant'),(28,'Tx : Non-heart-beating donor'),(29,'Tx : type unknown');
/*!40000 ALTER TABLE `tbl_transplant_modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_transplant_reject`
--

DROP TABLE IF EXISTS `tbl_transplant_reject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_transplant_reject` (
  `recID` int(11) NOT NULL AUTO_INCREMENT,
  `trID` int(11) NOT NULL,
  `trRejectDate` datetime DEFAULT NULL,
  `trBiopsyDate` datetime DEFAULT NULL,
  `trFailureDate` datetime DEFAULT NULL,
  PRIMARY KEY (`recID`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_transplant_reject`
--

LOCK TABLES `tbl_transplant_reject` WRITE;
/*!40000 ALTER TABLE `tbl_transplant_reject` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transplant_reject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_users` (
  `uID` int(11) NOT NULL AUTO_INCREMENT,
  `uSurname` varchar(50) DEFAULT NULL,
  `uForename` varchar(50) DEFAULT NULL,
  `uTitle` varchar(50) DEFAULT NULL,
  `uGMC` varchar(50) DEFAULT NULL,
  `uRole` varchar(50) DEFAULT NULL,
  `uPhone` varchar(50) DEFAULT NULL,
  `uCentre` int(11) DEFAULT NULL,
  `uDateJoin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uID`)
) ENGINE=MyISAM AUTO_INCREMENT=1113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users`
--

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
INSERT INTO `tbl_users` VALUES (1101,'','renala-unitadmin1',NULL,NULL,NULL,NULL,1098,'2013-11-21 12:10:31'),(1102,'','renalb-unitadmin1',NULL,NULL,NULL,NULL,1116,'2013-11-21 12:11:30'),(1103,'','renalc-unitadmin1',NULL,NULL,NULL,NULL,1122,'2013-11-21 12:12:04'),(1104,'','renala-renalb-unitadmin',NULL,NULL,NULL,NULL,1098,'2013-11-21 12:16:43'),(1105,'','srns-groupadmin1',NULL,NULL,NULL,NULL,1099,'2013-11-21 12:26:51'),(1106,'','mpgn-groupadmin1',NULL,NULL,NULL,NULL,1100,'2013-11-21 12:27:31'),(1107,'','alport-groupadmin1',NULL,NULL,NULL,NULL,1101,'2013-11-21 12:27:55'),(1108,'','hnf1b-groupadmin1',NULL,NULL,NULL,NULL,1102,'2013-11-21 12:28:21'),(1109,'','gen1-groupadmin1',NULL,NULL,NULL,NULL,1123,'2013-11-21 12:28:55'),(1110,'','gen2-groupadmin1',NULL,NULL,NULL,NULL,1124,'2013-11-21 12:29:51'),(1111,'','srns-gen1-groupadmin',NULL,NULL,NULL,NULL,1099,'2013-11-21 12:32:32'),(1112,'','renala-alport-unitadmin',NULL,NULL,NULL,NULL,1098,'2013-11-21 12:37:03');
/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testresult`
--

DROP TABLE IF EXISTS `testresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testresult` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `testcode` varchar(100) NOT NULL DEFAULT '',
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `prepost` varchar(100) DEFAULT '',
  `value` varchar(100) NOT NULL DEFAULT '',
  `RADAR_NO` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nhsno_testcode` (`nhsno`,`testcode`,`unitcode`),
  KEY `radarno_unitcode` (`RADAR_NO`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=543912178 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testresult`
--

LOCK TABLES `testresult` WRITE;
/*!40000 ALTER TABLE `testresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `testresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsNo` varchar(100) NOT NULL DEFAULT '',
  `treatmentCode` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nhsNo` (`nhsNo`,`treatmentCode`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uktcode`
--

DROP TABLE IF EXISTS `uktcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uktcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uktcode` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uktcode_unique` (`uktcode`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uktcode`
--

LOCK TABLES `uktcode` WRITE;
/*!40000 ALTER TABLE `uktcode` DISABLE KEYS */;
INSERT INTO `uktcode` VALUES (1,'A','Active'),(2,'S','Suspended'),(3,'T','Transplanted'),(4,'R','Not on list'),(5,'N','Not on list'),(6,'O','Not on list');
/*!40000 ALTER TABLE `uktcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uktstatus`
--

DROP TABLE IF EXISTS `uktstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uktstatus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `kidney` varchar(10) DEFAULT '',
  `pancreas` varchar(10) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=82311 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uktstatus`
--

LOCK TABLES `uktstatus` WRITE;
/*!40000 ALTER TABLE `uktstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `uktstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `shortname` varchar(15) NOT NULL DEFAULT '',
  `unituser` varchar(20) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `address3` varchar(100) DEFAULT NULL,
  `postcode` varchar(100) DEFAULT NULL,
  `uniturl` varchar(100) DEFAULT NULL,
  `trusturl` varchar(100) DEFAULT NULL,
  `renaladminname` varchar(100) DEFAULT NULL,
  `renaladminphone` varchar(100) DEFAULT NULL,
  `renaladminemail` varchar(100) DEFAULT NULL,
  `unitenquiriesphone` varchar(100) DEFAULT NULL,
  `unitenquiriesemail` varchar(100) DEFAULT NULL,
  `appointmentphone` varchar(100) DEFAULT NULL,
  `appointmentemail` varchar(100) DEFAULT NULL,
  `outofhours` varchar(100) DEFAULT NULL,
  `peritonealdialysisphone` varchar(100) DEFAULT NULL,
  `peritonealdialysisemail` varchar(100) DEFAULT NULL,
  `haemodialysisunitname1` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone1` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation1` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl1` varchar(100) DEFAULT NULL,
  `haemodialysisunitname2` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone2` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation2` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl2` varchar(100) DEFAULT NULL,
  `haemodialysisunitname3` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone3` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation3` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl3` varchar(100) DEFAULT NULL,
  `haemodialysisunitname4` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone4` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation4` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl4` varchar(100) DEFAULT NULL,
  `haemodialysisunitname5` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone5` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation5` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl5` varchar(100) DEFAULT NULL,
  `haemodialysisunitname6` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone6` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation6` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl6` varchar(100) DEFAULT NULL,
  `haemodialysisunitname7` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone7` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation7` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl7` varchar(100) DEFAULT NULL,
  `haemodialysisunitname8` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone8` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation8` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl8` varchar(100) DEFAULT NULL,
  `haemodialysisunitname9` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone9` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation9` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl9` varchar(100) DEFAULT NULL,
  `haemodialysisunitname10` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone10` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation10` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl10` varchar(100) DEFAULT NULL,
  `haemodialysisunitname11` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone11` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation11` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl11` varchar(100) DEFAULT NULL,
  `haemodialysisunitname12` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone12` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation12` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl12` varchar(100) DEFAULT NULL,
  `sourceType` varchar(50) DEFAULT NULL,
  `specialty_id` bigint(20) NOT NULL,
  `country` varchar(100) DEFAULT NULL,
  `lastImportDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unitcode` (`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=1125 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1098,'RENALA','Renal Unit A','Renal A','','','','','','','','','','patientview-testing@solidstategroup.com','','patientview-testing@solidstategroup.com','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','renalunit',1,'1','2013-11-19 13:43:12'),(1099,'SRNS','SRNS: Steroid Resistant Nephrotic Syndrome (including inherited, monogenic, primary FSGS and syndrom','SRNS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL,NULL),(1100,'MPGN','Membranoproliferative glomerulonephritis / Dense Deposit Disease ','MPGN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL,NULL),(1101,'ALPORT','Alport syndrome, and disorders of basement membrane structure','Alport',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL,NULL),(1102,'HNF1B','HNF1b Mutations','HNF1B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL,NULL),(1116,'RENALB','Renal Unit B','Renal B','','','','','','','','','','patientview-testing@solidstategroup.com','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','renalunit',1,'1','2013-11-20 15:48:05'),(1124,'GENDISGRP2','Generic Disease Group 2','GEN2','','','','','','','','Ben Kupper','34435435345','patientview-testing@solidstategroup.com','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','radargroup',1,'1',NULL),(1123,'GENDISGRP1','Generic Disease Group 1','GEN1','','','','','','','','Paul Chenery','12321123123','patientview-testing@solidstategroup.com','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','radargroup',1,'1',NULL),(1122,'RENALC','Renal Unit C','RENC','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','renalunit',1,'1',NULL);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unitstat`
--

DROP TABLE IF EXISTS `unitstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unitstat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unitcode` varchar(20) NOT NULL,
  `yearmonth` varchar(7) NOT NULL,
  `action` varchar(30) NOT NULL,
  `count` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=39744 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unitstat`
--

LOCK TABLES `unitstat` WRITE;
/*!40000 ALTER TABLE `unitstat` DISABLE KEYS */;
/*!40000 ALTER TABLE `unitstat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `role` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `emailverified` tinyint(1) DEFAULT '0',
  `firstlogon` tinyint(1) DEFAULT '0',
  `dummypatient` tinyint(1) NOT NULL DEFAULT '0',
  `lastlogon` datetime DEFAULT NULL,
  `failedlogons` int(3) DEFAULT '0',
  `accountlocked` tinyint(1) DEFAULT '0',
  `isrecipient` tinyint(1) NOT NULL DEFAULT '0',
  `isclinician` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=58140 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (57987,'superadmin','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Super Admin','patientview-testing@solidstategroup.com',0,0,0,'2013-11-21 12:01:07',0,0,0,0),(58137,'srns-gen1-groupadmin','d1e3e9368a19c4c1e1afa29970de006a08d0ab9028cfeee782d388d4c55ffcb4','','srns-gen1-groupadmin','srns-gen1-groupadmin@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58138,'renala-alport-unitadmin','d080008e60418faf03aa582c82030bcd7300bd3c06bcfd3876aee0fbd57aa4ab','','renala-alport-unitadmin','renala-alport-unitadmin@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58136,'gen2-groupadmin1','9444f8b435e807e7c7898c93526d7bce1fd87ee50e25389277177705e5ed108f','','gen2-groupadmin1','gen2-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58135,'gen1-groupadmin1','85d9eb0ba96b7cb765301e91470eaf7b33e8899026ee564155a84e03648383f2','','gen1-groupadmin1','gen1-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58134,'hnf1b-groupadmin1','4da77718fdd45a0a01d6f60502ba37d1128f6c8615ab30fbe41120f260231f0e','','hnf1b-groupadmin1','hnf1b-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58132,'mpgn-groupadmin1','e3d755ec725fa76defbf121b4fb257bafad2ff1fd7aac451e78b5a21943b081e','','mpgn-groupadmin1','mpgn-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58133,'alport-groupadmin1','da0def2b91a3240409380f58517b61fbad703ab1966aed3e8642afbf5ce2e75b','','alport-groupadmin1','alport-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58131,'srns-groupadmin1','1d67c3d423bb3de898a3f17ce76d968f41e860a23cbcc035c74c346e2c1ef161','','srns-groupadmin1','srns-groupadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58129,'renalc-unitadmin1','2cde9026e99976dd0b0e88e595f1ada71144199fc5d25fb88d0615ecdc2298b9','','renalc-unitadmin1','renalc-unitadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58130,'renala-renalb-unitadmin','317b94b16a776fddcaa721df95df0c31aa55d901b7a7089affcc26f104431885','','renala-renalb-unitadmin','renala-renalb-unitadmin@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58128,'renalb-unitadmin1','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','renalb-unitadmin1','renalb-unitadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0),(58127,'renala-unitadmin1','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','renala-unitadmin1','renala-unitadmin1@solidstategroup.com',0,1,0,NULL,0,0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL,
  `unitcode` varchar(100) DEFAULT NULL,
  `role` varchar(100) NOT NULL DEFAULT '',
  `count` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5298 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlog`
--

LOCK TABLES `userlog` WRITE;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usermapping`
--

DROP TABLE IF EXISTS `usermapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  `specialty_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usermapping_index_nhsno` (`nhsno`),
  KEY `usermapping_index_username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=88278 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermapping`
--

LOCK TABLES `usermapping` WRITE;
/*!40000 ALTER TABLE `usermapping` DISABLE KEYS */;
INSERT INTO `usermapping` VALUES (88260,'renala-unitadmin1','RENALA','',1),(88261,'renalb-unitadmin1','RENALB','',1),(88262,'renalc-unitadmin1','RENALC','',1),(88263,'renala-renalb-unitadmin','RENALA','',1),(88264,'renala-renalb-unitadmin','RENALB','',1),(88265,'srns-groupadmin1','SRNS','',1),(88266,'mpgn-groupadmin1','MPGN','',1),(88267,'alport-groupadmin1','ALPORT','',1),(88268,'hnf1b-groupadmin1','HNF1B','',1),(88269,'gen1-groupadmin1','GENDISGRP1','',1),(88270,'gen2-groupadmin1','GENDISGRP2','',1),(88271,'srns-gen1-groupadmin','SRNS','',1),(88272,'srns-gen1-groupadmin','GENDISGRP1','',1),(88273,'renala-alport-unitadmin','RENALA','',1),(88274,'renala-alport-unitadmin','ALPORT','',1);
/*!40000 ALTER TABLE `usermapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-02 11:18:03
