SET FOREIGN_KEY_CHECKS=0;
SET AUTOCOMMIT=0;
-- MySQL dump 10.13  Distrib 5.1.67, for redhat-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: patientview_staging
-- ------------------------------------------------------
-- Server version	5.1.67

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
) ENGINE=MyISAM AUTO_INCREMENT=535 DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM AUTO_INCREMENT=576769 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centre`
--

LOCK TABLES `centre` WRITE;
/*!40000 ALTER TABLE `centre` DISABLE KEYS */;
INSERT INTO `centre` VALUES (576657,'2020','Belfast City Hospital','Lisburn Road','Belfast','Co. Antrim',NULL,'BT9 7AB','028 90329241','Hospital@bch.n-i.nhs.uk'),(576658,'RAE05','St. James\'s University Hospital','Renal Unit','Beckett Street','Leeds','North Yorks','LS9 7TF','0113 206 4600','renal@leedsth.nhs.uk'),(576659,'45020','Altnagelvin Area Hospital','Glenshane Road','Londonderry','Co. Londonderry',NULL,'BT47 6SB','028 71345171','Altnagelvin@westerntrust.hscni.net'),(576660,'RAZ','St Helier Hospital','Renal Unit','Wrythe Lane','Carshalton',NULL,'SM5 1AA','020 8296 2000','enquiries@epsom-sthelier.nhs.uk'),(576664,'RCJAT','James Cook University Hospital','Renal Unit','Marton Road','Middlesbrough','Cleveland','TS4 3BW','01642 854617','beverley.smith@stees.nhs.uk'),(576662,'RAQ01','Lister Hospital','Department of Renal Medicine','Coreys Mill Lane','Stevenage','Hertfordshire','SG1 4AB','01438 781230',NULL),(576665,'REF12','Royal Cornwall Hospital','Truro','Cornwall',NULL,NULL,'TR1 3LJ','01872 250000','donna.steward@cornwall.nhs.uk'),(576671,'RGU01','Brighton',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576701,'RHU02','Wessex Renal and Transplant Service Portsmouth','Wessex Renal and Transplant Service','Queen Alexandra Hospital','Portsmouth','Hants','PO6 3LY','02392 286346','renalpatientview@porthosp.nhs.uk'),(576702,'RJ100','Renal Patient View','Renal Offices','6th Floor, Borough Wing','Guy\'s Hospital','London','SE1 9RT','020 7188 7635','rpvadministrator@gstt.nhs.uk'),(576703,'RJZ','King\'s College Hospital','Renal Admin','King\'s College Hospital','London',NULL,'SE5 9RS','020 3299 6233','kch-tr.renal@nhs.net'),(576707,'RK7CC','Sheffield Kidney Institute','Northern General Hospital','Sorby OPD','Herries Road','Sheffield','S5 7AU','0114 2269201',NULL),(576708,'RKB01','UHCW renal unit','University Hospital','Clifford Bridge Road','Walsgrave','Coventry','CV2 2DX','02476 964000',NULL),(576710,'RLNGH','City Hospitals Sunderland',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576713,'RM102','Norfolk and Norwich University Hospital NHS Trust','Colney Lane','Norwich',NULL,NULL,'NR4 7UY','01603 286286','hospital@nnuh.nhs.uk'),(576723,'RM301',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(576726,'RM574','Prestwich','BURY NEW ROAD',NULL,'PRESTWICH',NULL,NULL,NULL,NULL),(576731,'RMF01','Royal Preston Hospital','Renal Unit','Sharoe Green Road','Fulwood','Preston','PR2 9HT','01772-716565',NULL),(576732,'RQR00','St. James\'s University Hospital','Renal Unit','Beckett Street','Leeds','North Yorks','LS9 7TF','0113 206 4600','renal@leedsth.nhs.uk'),(576741,'RRK02','UHB NHS Foundation Trust','Dept of Nephrology and Transplantation','Queen Elizabeth Hospital','Edgbaston','Birmingham','B15  2TH','0121 472 1311, ext 3170','bindu.gohil@uhb.nhs.uk'),(576743,'RSC02','Royal Infirmary Edinburgh','Renal Unit','51 Little France Crescent','Old Dalkeith Road','Edinburgh','EH16 4SA','0131 536 1000',NULL),(576745,'RTD01','FRH','Freeman Road',NULL,'Newcastle upon Tyne',NULL,'NE7 7DN','0191 2336161',NULL),(576754,'RX1CC','Nottingham University Hospitals NHS Trust','Renal Unit','Hucknall Road','Nottingham','Notts','NG5 1PB','0115 969 1169',NULL),(576758,'SAC02','Cross House Hospital','Renal Unit','14 Lister Street','Kilmarnock','Ayrshire','KA2 0BE','01563 521133',NULL),(576759,'SFC01','Queen Margaret Hospital','Renal Unit','Whitefield Road','Dunfermline',NULL,'KY12 0SU','01383 623623',NULL),(576761,'SGC04','Glasgow Western Infirmary','Renal Unit','Dumbarton Road','Glasgow','Scotland','G11 6NT','0141 211 2000','renal@northglasgow.scot.nhs.uk'),(576764,'SGC05','Royal Infirmary Glasgow','Renal Unit','3rd Floor Walton Building','Glasgow','Scotland','G4 0SF','0141 211 4000','renal@northglasgow.scot.nhs.uk'),(576768,'SYC01','Dumfries and Galloway NHS Trust','Renal Unit','Bankend Road','Dumfries',NULL,'DG1 4AP','01387 241657',NULL),(576767,'SNC01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `comment` VALUES (812,'2013-04-26 10:59:08','1111111112','hello'),(813,'2013-04-26 10:59:53','1111111112','sdfsdf');
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (10,0,'2013-05-07 15:37:20',58033,58031,'Hello');
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
) ENGINE=MyISAM AUTO_INCREMENT=22365827 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
INSERT INTO `diagnosis` VALUES (22365710,'3204969541','2020','Cystic disease of kidney',0),(22365711,'4087878406','RAE05','Diabetic Nephropathy',0),(22365712,'6067495406','45020','Haemolytic uraemic syndrome',0),(22365713,'4445706906','RAZ','Diabetes glomerulosclerosis or nephropathy NIDDM',0),(22365714,'4482941506','RCJAT','POLYCYSTIC KIDNEY',0),(22365715,'4647463206','RAQ01','Polycystic kidneys; adult type (dominant)',0),(22365716,'6343053506','RCJAT','TYPE 1 DIABETIC NEPHROPATHY',0),(22365718,'4742567706','REF12','Renal vascular disease - type unspecified',1),(22365717,'4742567706','REF12','Diabetes glomerulosclerosis or diabetic nephropathy',0),(22365750,'4586203706','RHU02','Chronic GN',0),(22365755,'4904965906','RHU02','Diabetic Nephropathy',0),(22365749,'4445790206','RHU02','not specified',1),(22365757,'4971252606','RHU02','Renal Transplant 1994',1),(22365756,'4971252606','RHU02','Chronic glomerulonephritis',0),(22365751,'4586203706','RHU02','Hypentension',1),(22365752,'4681455806','RHU02','Focal segmental glomerulosclerosis with',0),(22365753,'4681455806','RHU02','nephrotic syndrome in adults',1),(22365754,'4725435406','RHU02','Diabetic Nephropathy',0),(22365758,'6070932706','RHU02','Polycystic kidneys',0),(22365748,'4445790206','RHU02','Pyelonephritis/interstitial nephritis - cause',0),(22365759,'6302448506','RHU02','hypertensive glomerulosclerosis',0),(22365760,'6320259906','RJZ','Focal segmental glomeruloscerosis with nephrotic syndrome in',0),(22365761,'4283486906','RM102','Congenital abnormality of the kidney',0),(22365762,'4487674506','RM102','Autosomal dominant polycystic kidney disease',0),(22365763,'6041028606','RM102','Membranous glomerulonephritis',0),(22365768,'4528844206','RMF01','Chronic Renal Failure (CRF), aetiology uncertain',0),(22365770,'6189530206','RMF01','Kidney transplant failure and rejection',1),(22365769,'6189530206','RMF01','Pyelonephritis/Interstit. nephritis-vesico-ureteric reflux',0),(22365771,'4540124706','RMF01','Polycystic kidneys, adult type (dominant)',0),(22365772,'4542524906','RRK02','Unknown/Unavailable',0),(22365773,'4545932606','RRK02','IgA nephropathy (proven by immunofluorescence, not code 76 and not 85)',0),(22365774,'4731555906','RRK02','Unknown/Unavailable',0),(22365775,'6081253406','RRK02','Unknown/Unavailable',0),(22365776,'6091400606','RRK02','Focal segmental glomeraloscerosis with nephrotic syndrome in adults',0),(22365777,'6263926406','RRK02','Amyloid',0),(22365778,'6362908406','RRK02','Glomerulonephritis not histologically examined',0),(22365779,'6287399406','RRK02','Unknown/Unavailable',0),(22365780,'6491705706','RRK02','IgA nephropathy (proven by immunofluorescence, not code 76 and not 85)',0),(22365790,'0202581306','RSC02','Nausea, likely 2ndry liver & kidney size',9),(22365789,'0202581306','RSC02','Bronchiectasis',8),(22365788,'0202581306','RSC02','asthma',7),(22365787,'0202581306','RSC02','Hypertension',6),(22365786,'0202581306','RSC02','Recurrent UTIs',5),(22365785,'0202581306','RSC02','Active on transplant list',4),(22365784,'0202581306','RSC02','Left nephrectomy (infections/size cf Tx) Aug 2010',3),(22365783,'0202581306','RSC02','started HD (L fistula mature)-December 2009',2),(22365805,'1306741106','RSC02','Ex-smoker ( approx 2005 )',13),(22365806,'1306741106','RSC02','@@@@@@ Allergic to amoxycillin@@@@@@',14),(22365804,'1306741106','RSC02','laser therapy - April 2008',12),(22365802,'1306741106','RSC02','nephritis - probably due to amoxycillin',10),(22365803,'1306741106','RSC02','CIN stage 1/2 on smear - Feb \'08',11),(22365801,'1306741106','RSC02','acute graft failure secondary to acute interstitial',9),(22365800,'1306741106','RSC02','Second cadaveric renal transplant - July 1997',8),(22365799,'1306741106','RSC02','First cadaveric renal transplant 1995 ( early failure )',7),(22365797,'1306741106','RSC02','late diagnosis from investigation of affected son',5),(22365798,'1306741106','RSC02','late-onset deafness',6),(22365796,'1306741106','RSC02','history or systemic features; non-specific biopsy',4),(22365708,'2007661713','RSC02','Pulmonary and cutaneous sarcoid',12),(22365706,'2007661713','RSC02','Hypertension',10),(22365707,'2007661713','RSC02','hypercalcaemia secondary to sarcoidosis',11),(22365704,'2007661713','RSC02','treated for pul TB 2002, 2004, not cultured',8),(22365705,'2007661713','RSC02','HRCT thorax; consistent with sarcoid',9),(22365703,'2007661713','RSC02','HIV positive since 2001',7),(22365702,'2007661713','RSC02','Biopsy - crystal nephropathy 2dry HAART',6),(22365701,'2007661713','RSC02','Acute-on-chronic renal failure - emergency HD',5),(22365700,'2007661713','RSC02','Low grade proteinuria',4),(22365782,'0202581306','RSC02','Stage 5 CKD',1),(22365781,'0202581306','RSC02','Adult Polycystic Kidney Disease',0),(22365795,'1306741106','RSC02','Presented @ end-stage 1994 with no family',3),(22365794,'1306741106','RSC02','ESRF secondary to Alport\'s syndrome',2),(22365793,'1306741106','RSC02','full house match / Sister as donor',1),(22365792,'1306741106','RSC02','Live donor renal transplant 14/03/2012',0),(22365699,'2007661713','RSC02','Biopsy performed 2004 Royal Free London',3),(22365698,'2007661713','RSC02','Hypertensive nephrosclerosis on biopsy',2),(22365697,'2007661713','RSC02','ERSD - on HD since 08/2010',1),(22365696,'2007661713','RSC02','gout',0),(22365709,'2007661713','RSC02','HAART',13),(22365791,'0202581306','RSC02','Ba swallow & UGIE NAD',10),(22365807,'4244855106','RX1CC','Infantile sarcoidosis.  Benign intracranial hypertension.',0),(22365808,'4287449606','RX1CC','Adult polycystic disease',0),(22365809,'4464071406','RX1CC','Diabetic nephropathy',0),(22365810,'4924592706','RX1CC','Glomerulonephritis',0),(22365811,'4561171606','RX1CC','Glomerulonephritis',0),(22365812,'4944744706','RX1CC','Diabetic nephropathy',0),(22365813,'4971252606','RX1CC','Glomerulonephritis',0),(22365814,'4984873906','RX1CC','Chronic renal failure, aetiology uncertain',0),(22365815,'1803453206','SAC02','(EDTA PRD) 24 Pyelo/Interstitial nephritis - vesico-ureteric reflux no obstruction',0),(22365816,'1202760406','SFC01','Type I diabetes',0),(22365817,'1202760406','SFC01','CKD stage V',1),(22365818,'1202760406','SFC01','Renal stone disease',2),(22365819,'1202760406','SFC01','Diabetic retinopathy',3),(22365820,'1202760406','SFC01','Pregnancy 2010',4),(22365821,'1202760406','SFC01','For APD',5),(22365822,'1202760406','SFC01','Referred for SPK',6),(22365823,'1202760406','SFC01','? diabetic gastroparesis',7),(22365824,'2907790706','SGC04','(EDTA PRD) 10 Glomerulonephritis, histologically NOT examined',0),(22365825,'2411496206','SGC05','(EDTA PRD) 41 Polycystic kidneys - adult type (Dominant)',0),(22365826,'2808583206','SYC01','(EDTA PRD) 41 Polycystic kidneys - adult type (Dominant)',0);
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5525 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailverification`
--

LOCK TABLES `emailverification` WRITE;
/*!40000 ALTER TABLE `emailverification` DISABLE KEYS */;
INSERT INTO `emailverification` VALUES (5510,'renalaadmin','patientview-testing@solidstategroup.com','1ic9cPYHxPLTKa6dnUeN4DZhY4hZjq3W1qWI6q4KLbfjtu1iVG','2013-04-24 11:07:00'),(5514,'andel1','andel1@test.com','q03HtpBqQ3Al5uoMvGgWopq6F2gIcdDvf6rdXqvdt8tNuKc5Wv','2013-04-30 14:18:40'),(5515,'undel2','undel2@test.com','pogO1qF7T8J79Da8LebTfYKTzE5EfRxJjD60kX1TZ1nLz2qN2q','2013-04-30 14:22:09'),(5516,'ewfwfw','paul4@solidstategroup.com','sSbuEgHMQxNZYv6pXZIDS6C7Werk4tTHb4McKglHietP8rV2Ir','2013-04-30 14:23:19'),(5517,'wegfretyjtyjyuk','pau657l@solidstategroup.com','S3iEInRvuxYUPQFSENUAJ22WqfuXMBfXxcfFG6tafs0g2ibHZd','2013-04-30 14:28:27'),(5518,'ergeg','pau2342l@solidstategroup.com','vbVfFyAUDnl94svX8LoEmt26CRLF8desWSEX4wSNnS6hSikDDL','2013-04-30 14:34:59'),(5519,'Seth_Lakeman','seth.lakeman@nhs.net','9LZcBIfcxBb5JSkdZxKluKpz2CgDcRFxVQM54Q1Y1ic5JSdLVK','2013-05-01 12:05:26'),(5520,'paulchenery','paul@solidstategroup.com','l0A1dEwU7btLkW3f6WlsUwlG1V7zGjcxCQCSBj8NMOSbtfGaMK','2013-05-02 17:08:04'),(5521,'andrew1','andrew1@solidstategroup.com','moItWHx4uMpIfvO6wNbENnQFMgzWkDNJTd36QuAOixlnz4v0pm','2013-05-03 12:00:00'),(5522,'radarprof','patientview-testing@solidstategroup.com','a7BnxL6yFgppXgw1nFszzsSaURYxuc1exsAwrYL8SGdfqFWQJN','2013-05-03 12:18:08'),(5523,'ben1','ben1@solidstategroup.com','13cFSQLXWhXGhLytybr1cG0sG0QjT0GoVOHdHkTCm5h9zoMiN3','2013-05-03 12:53:52'),(5524,'Endgame','f.braddon@gmail.com','yWYHaU2VF4dXHdYEx53zgk0auWhn0VrP8TzMKjyc9rfSUhW6Im','2013-05-08 15:22:57');
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ibd_myibd`
--

LOCK TABLES `ibd_myibd` WRITE;
/*!40000 ALTER TABLE `ibd_myibd` DISABLE KEYS */;
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
-- Table structure for table `jforum_api`
--

DROP TABLE IF EXISTS `jforum_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(32) NOT NULL,
  `api_validity` datetime NOT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_api`
--

LOCK TABLES `jforum_api` WRITE;
/*!40000 ALTER TABLE `jforum_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_attach`
--

DROP TABLE IF EXISTS `jforum_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach` (
  `attach_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `privmsgs_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_id`),
  KEY `idx_att_post` (`post_id`),
  KEY `idx_att_priv` (`privmsgs_id`),
  KEY `idx_att_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_attach`
--

LOCK TABLES `jforum_attach` WRITE;
/*!40000 ALTER TABLE `jforum_attach` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_attach_desc`
--

DROP TABLE IF EXISTS `jforum_attach_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach_desc` (
  `attach_desc_id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_id` int(11) NOT NULL,
  `physical_filename` varchar(255) NOT NULL,
  `real_filename` varchar(255) NOT NULL,
  `download_count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mimetype` varchar(50) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `thumb` tinyint(1) DEFAULT '0',
  `extension_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`attach_desc_id`),
  KEY `idx_att_d_att` (`attach_id`),
  KEY `idx_att_d_ext` (`extension_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_attach_desc`
--

LOCK TABLES `jforum_attach_desc` WRITE;
/*!40000 ALTER TABLE `jforum_attach_desc` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_attach_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_attach_quota`
--

DROP TABLE IF EXISTS `jforum_attach_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach_quota` (
  `attach_quota_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `quota_limit_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_quota_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_attach_quota`
--

LOCK TABLES `jforum_attach_quota` WRITE;
/*!40000 ALTER TABLE `jforum_attach_quota` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_attach_quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_banlist`
--

DROP TABLE IF EXISTS `jforum_banlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_banlist` (
  `banlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `banlist_ip` varchar(15) DEFAULT NULL,
  `banlist_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`banlist_id`),
  KEY `idx_user` (`user_id`),
  KEY `banlist_ip` (`banlist_ip`),
  KEY `banlist_email` (`banlist_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_banlist`
--

LOCK TABLES `jforum_banlist` WRITE;
/*!40000 ALTER TABLE `jforum_banlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_banlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_banner`
--

DROP TABLE IF EXISTS `jforum_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(90) DEFAULT NULL,
  `banner_placement` int(11) NOT NULL DEFAULT '0',
  `banner_description` varchar(250) DEFAULT NULL,
  `banner_clicks` int(11) NOT NULL DEFAULT '0',
  `banner_views` int(11) NOT NULL DEFAULT '0',
  `banner_url` varchar(250) DEFAULT NULL,
  `banner_weight` tinyint(1) NOT NULL DEFAULT '50',
  `banner_active` tinyint(1) NOT NULL DEFAULT '0',
  `banner_comment` varchar(250) DEFAULT NULL,
  `banner_type` int(11) NOT NULL DEFAULT '0',
  `banner_width` int(11) NOT NULL DEFAULT '0',
  `banner_height` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`banner_id`),
  KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_banner`
--

LOCK TABLES `jforum_banner` WRITE;
/*!40000 ALTER TABLE `jforum_banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_bookmarks`
--

DROP TABLE IF EXISTS `jforum_bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_bookmarks` (
  `bookmark_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `relation_id` int(11) NOT NULL,
  `relation_type` int(11) NOT NULL,
  `public_visible` int(11) DEFAULT '1',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `book_idx_relation` (`relation_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_bookmarks`
--

LOCK TABLES `jforum_bookmarks` WRITE;
/*!40000 ALTER TABLE `jforum_bookmarks` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_bookmarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_categories`
--

DROP TABLE IF EXISTS `jforum_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_categories` (
  `categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `display_order` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`categories_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_categories`
--

LOCK TABLES `jforum_categories` WRITE;
/*!40000 ALTER TABLE `jforum_categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_config`
--

DROP TABLE IF EXISTS `jforum_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_config` (
  `config_name` varchar(255) NOT NULL DEFAULT '',
  `config_value` varchar(255) NOT NULL DEFAULT '',
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_config`
--

LOCK TABLES `jforum_config` WRITE;
/*!40000 ALTER TABLE `jforum_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_extension_groups`
--

DROP TABLE IF EXISTS `jforum_extension_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_extension_groups` (
  `extension_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `allow` tinyint(1) DEFAULT '1',
  `upload_icon` varchar(100) DEFAULT NULL,
  `download_mode` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_extension_groups`
--

LOCK TABLES `jforum_extension_groups` WRITE;
/*!40000 ALTER TABLE `jforum_extension_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_extension_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_extensions`
--

DROP TABLE IF EXISTS `jforum_extensions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_extensions` (
  `extension_id` int(11) NOT NULL AUTO_INCREMENT,
  `extension_group_id` int(11) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `upload_icon` varchar(100) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `allow` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_id`),
  KEY `extension_group_id` (`extension_group_id`),
  KEY `extension` (`extension`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_extensions`
--

LOCK TABLES `jforum_extensions` WRITE;
/*!40000 ALTER TABLE `jforum_extensions` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_extensions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_forums`
--

DROP TABLE IF EXISTS `jforum_forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_forums` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `categories_id` int(11) NOT NULL DEFAULT '1',
  `forum_name` varchar(150) NOT NULL DEFAULT '',
  `forum_desc` varchar(255) DEFAULT NULL,
  `forum_order` int(11) DEFAULT '1',
  `forum_topics` int(11) NOT NULL DEFAULT '0',
  `forum_last_post_id` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`forum_id`),
  KEY `categories_id` (`categories_id`),
  KEY `idx_forums_cats` (`categories_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_forums`
--

LOCK TABLES `jforum_forums` WRITE;
/*!40000 ALTER TABLE `jforum_forums` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_forums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_forums_watch`
--

DROP TABLE IF EXISTS `jforum_forums_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_forums_watch` (
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_fw_forum` (`forum_id`),
  KEY `idx_fw_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_forums_watch`
--

LOCK TABLES `jforum_forums_watch` WRITE;
/*!40000 ALTER TABLE `jforum_forums_watch` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_forums_watch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_groups`
--

DROP TABLE IF EXISTS `jforum_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(40) NOT NULL DEFAULT '',
  `group_description` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_groups`
--

LOCK TABLES `jforum_groups` WRITE;
/*!40000 ALTER TABLE `jforum_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_karma`
--

DROP TABLE IF EXISTS `jforum_karma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_karma` (
  `karma_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `post_user_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `rate_date` datetime DEFAULT NULL,
  PRIMARY KEY (`karma_id`),
  KEY `post_id` (`post_id`),
  KEY `topic_id` (`topic_id`),
  KEY `post_user_id` (`post_user_id`),
  KEY `from_user_id` (`from_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_karma`
--

LOCK TABLES `jforum_karma` WRITE;
/*!40000 ALTER TABLE `jforum_karma` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_karma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_mail_integration`
--

DROP TABLE IF EXISTS `jforum_mail_integration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_mail_integration` (
  `forum_id` int(11) NOT NULL,
  `forum_email` varchar(100) NOT NULL,
  `pop_username` varchar(100) NOT NULL,
  `pop_password` varchar(100) NOT NULL,
  `pop_host` varchar(100) NOT NULL,
  `pop_port` int(11) DEFAULT '110',
  `pop_ssl` tinyint(4) DEFAULT '0',
  KEY `forum_id` (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_mail_integration`
--

LOCK TABLES `jforum_mail_integration` WRITE;
/*!40000 ALTER TABLE `jforum_mail_integration` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_mail_integration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_moderation_log`
--

DROP TABLE IF EXISTS `jforum_moderation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_moderation_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `log_description` text NOT NULL,
  `log_original_message` text,
  `log_date` datetime NOT NULL,
  `log_type` tinyint(4) DEFAULT '0',
  `post_id` int(11) DEFAULT '0',
  `topic_id` int(11) DEFAULT '0',
  `post_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `post_user_id` (`post_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_moderation_log`
--

LOCK TABLES `jforum_moderation_log` WRITE;
/*!40000 ALTER TABLE `jforum_moderation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_moderation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_posts`
--

DROP TABLE IF EXISTS `jforum_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `post_time` datetime DEFAULT NULL,
  `poster_ip` varchar(15) DEFAULT NULL,
  `enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `enable_html` tinyint(1) NOT NULL DEFAULT '1',
  `enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `enable_sig` tinyint(1) NOT NULL DEFAULT '1',
  `post_edit_time` datetime DEFAULT NULL,
  `post_edit_count` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  `attach` tinyint(1) DEFAULT '0',
  `need_moderate` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `post_time` (`post_time`),
  KEY `need_moderate` (`need_moderate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_posts`
--

LOCK TABLES `jforum_posts` WRITE;
/*!40000 ALTER TABLE `jforum_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_posts_text`
--

DROP TABLE IF EXISTS `jforum_posts_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_posts_text` (
  `post_id` int(11) NOT NULL,
  `post_text` text,
  `post_subject` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_posts_text`
--

LOCK TABLES `jforum_posts_text` WRITE;
/*!40000 ALTER TABLE `jforum_posts_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_posts_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_privmsgs`
--

DROP TABLE IF EXISTS `jforum_privmsgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_privmsgs` (
  `privmsgs_id` int(11) NOT NULL AUTO_INCREMENT,
  `privmsgs_type` tinyint(4) NOT NULL DEFAULT '0',
  `privmsgs_subject` varchar(255) NOT NULL DEFAULT '',
  `privmsgs_from_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_to_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_date` datetime DEFAULT NULL,
  `privmsgs_ip` varchar(15) NOT NULL DEFAULT '',
  `privmsgs_enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_enable_html` tinyint(1) NOT NULL DEFAULT '0',
  `privmsgs_enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_attach_sig` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_privmsgs`
--

LOCK TABLES `jforum_privmsgs` WRITE;
/*!40000 ALTER TABLE `jforum_privmsgs` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_privmsgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_privmsgs_text`
--

DROP TABLE IF EXISTS `jforum_privmsgs_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_privmsgs_text` (
  `privmsgs_id` int(11) NOT NULL,
  `privmsgs_text` text,
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_privmsgs_text`
--

LOCK TABLES `jforum_privmsgs_text` WRITE;
/*!40000 ALTER TABLE `jforum_privmsgs_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_privmsgs_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_quota_limit`
--

DROP TABLE IF EXISTS `jforum_quota_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_quota_limit` (
  `quota_limit_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_desc` varchar(50) NOT NULL,
  `quota_limit` int(11) NOT NULL,
  `quota_type` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`quota_limit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_quota_limit`
--

LOCK TABLES `jforum_quota_limit` WRITE;
/*!40000 ALTER TABLE `jforum_quota_limit` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_quota_limit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_ranks`
--

DROP TABLE IF EXISTS `jforum_ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_ranks` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_title` varchar(50) NOT NULL DEFAULT '',
  `rank_min` int(11) NOT NULL DEFAULT '0',
  `rank_special` tinyint(1) DEFAULT NULL,
  `rank_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_ranks`
--

LOCK TABLES `jforum_ranks` WRITE;
/*!40000 ALTER TABLE `jforum_ranks` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_ranks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_role_values`
--

DROP TABLE IF EXISTS `jforum_role_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_role_values` (
  `role_id` int(11) NOT NULL,
  `role_value` varchar(255) DEFAULT NULL,
  KEY `idx_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_role_values`
--

LOCK TABLES `jforum_role_values` WRITE;
/*!40000 ALTER TABLE `jforum_role_values` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_role_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_roles`
--

DROP TABLE IF EXISTS `jforum_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_group` (`group_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_roles`
--

LOCK TABLES `jforum_roles` WRITE;
/*!40000 ALTER TABLE `jforum_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_sessions`
--

DROP TABLE IF EXISTS `jforum_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_sessions` (
  `session_id` varchar(150) NOT NULL DEFAULT '',
  `session_user_id` int(11) NOT NULL DEFAULT '0',
  `session_start` datetime DEFAULT NULL,
  `session_time` bigint(20) DEFAULT '0',
  `session_ip` varchar(15) NOT NULL DEFAULT '',
  `session_page` int(11) NOT NULL DEFAULT '0',
  `session_logged_int` tinyint(1) DEFAULT NULL,
  KEY `idx_sessions_users` (`session_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_sessions`
--

LOCK TABLES `jforum_sessions` WRITE;
/*!40000 ALTER TABLE `jforum_sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_smilies`
--

DROP TABLE IF EXISTS `jforum_smilies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_smilies` (
  `smilie_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(100) DEFAULT NULL,
  `disk_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`smilie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_smilies`
--

LOCK TABLES `jforum_smilies` WRITE;
/*!40000 ALTER TABLE `jforum_smilies` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_smilies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_themes`
--

DROP TABLE IF EXISTS `jforum_themes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_themes` (
  `themes_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(30) NOT NULL DEFAULT '',
  `style_name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_themes`
--

LOCK TABLES `jforum_themes` WRITE;
/*!40000 ALTER TABLE `jforum_themes` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_themes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_topics`
--

DROP TABLE IF EXISTS `jforum_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_topics` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `topic_title` varchar(100) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `topic_time` datetime DEFAULT NULL,
  `topic_views` int(11) DEFAULT '1',
  `topic_replies` int(11) DEFAULT '0',
  `topic_status` tinyint(3) DEFAULT '0',
  `topic_vote_id` int(11) NOT NULL DEFAULT '0',
  `topic_type` tinyint(3) DEFAULT '0',
  `topic_first_post_id` int(11) DEFAULT '0',
  `topic_last_post_id` int(11) NOT NULL DEFAULT '0',
  `topic_moved_id` int(11) DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_first_post_id` (`topic_first_post_id`),
  KEY `topic_last_post_id` (`topic_last_post_id`),
  KEY `topic_moved_id` (`topic_moved_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_topics`
--

LOCK TABLES `jforum_topics` WRITE;
/*!40000 ALTER TABLE `jforum_topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_topics_watch`
--

DROP TABLE IF EXISTS `jforum_topics_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_topics_watch` (
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(1) DEFAULT '1',
  KEY `idx_topic` (`topic_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_topics_watch`
--

LOCK TABLES `jforum_topics_watch` WRITE;
/*!40000 ALTER TABLE `jforum_topics_watch` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_topics_watch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_user_groups`
--

DROP TABLE IF EXISTS `jforum_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_user_groups` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_group` (`group_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_user_groups`
--

LOCK TABLES `jforum_user_groups` WRITE;
/*!40000 ALTER TABLE `jforum_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_users`
--

DROP TABLE IF EXISTS `jforum_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_active` tinyint(1) DEFAULT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `user_password` varchar(32) NOT NULL DEFAULT '',
  `user_session_time` bigint(20) DEFAULT '0',
  `user_session_page` int(11) NOT NULL DEFAULT '0',
  `user_lastvisit` datetime DEFAULT NULL,
  `user_regdate` datetime DEFAULT NULL,
  `user_level` tinyint(4) DEFAULT NULL,
  `user_posts` int(11) NOT NULL DEFAULT '0',
  `user_timezone` varchar(5) NOT NULL DEFAULT '',
  `user_style` tinyint(4) DEFAULT NULL,
  `user_lang` varchar(255) NOT NULL DEFAULT '',
  `user_dateformat` varchar(20) NOT NULL DEFAULT '%d/%M/%Y %H:%i',
  `user_new_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_unread_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_last_privmsg` datetime DEFAULT NULL,
  `user_emailtime` datetime DEFAULT NULL,
  `user_viewemail` tinyint(1) DEFAULT '0',
  `user_attachsig` tinyint(1) DEFAULT '1',
  `user_allowhtml` tinyint(1) DEFAULT '0',
  `user_allowbbcode` tinyint(1) DEFAULT '1',
  `user_allowsmilies` tinyint(1) DEFAULT '1',
  `user_allowavatar` tinyint(1) DEFAULT '1',
  `user_allow_pm` tinyint(1) DEFAULT '1',
  `user_allow_viewonline` tinyint(1) DEFAULT '1',
  `user_notify` tinyint(1) DEFAULT '1',
  `user_notify_always` tinyint(1) DEFAULT '0',
  `user_notify_text` tinyint(1) DEFAULT '0',
  `user_notify_pm` tinyint(1) DEFAULT '1',
  `user_popup_pm` tinyint(1) DEFAULT '1',
  `rank_id` int(11) DEFAULT '0',
  `user_avatar` varchar(100) DEFAULT NULL,
  `user_avatar_type` tinyint(4) NOT NULL DEFAULT '0',
  `user_email` varchar(255) NOT NULL DEFAULT '',
  `user_icq` varchar(15) DEFAULT NULL,
  `user_website` varchar(255) DEFAULT NULL,
  `user_from` varchar(100) DEFAULT NULL,
  `user_sig` text,
  `user_sig_bbcode_uid` varchar(10) DEFAULT NULL,
  `user_aim` varchar(255) DEFAULT NULL,
  `user_yim` varchar(255) DEFAULT NULL,
  `user_msnm` varchar(255) DEFAULT NULL,
  `user_occ` varchar(100) DEFAULT NULL,
  `user_interests` varchar(255) DEFAULT NULL,
  `user_biography` text,
  `user_actkey` varchar(32) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `themes_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `user_viewonline` tinyint(1) DEFAULT '1',
  `security_hash` varchar(32) DEFAULT NULL,
  `user_karma` double DEFAULT NULL,
  `user_authhash` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_users`
--

LOCK TABLES `jforum_users` WRITE;
/*!40000 ALTER TABLE `jforum_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_vote_desc`
--

DROP TABLE IF EXISTS `jforum_vote_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_desc` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `vote_text` varchar(255) NOT NULL DEFAULT '',
  `vote_start` datetime NOT NULL,
  `vote_length` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `topic_id` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_vote_desc`
--

LOCK TABLES `jforum_vote_desc` WRITE;
/*!40000 ALTER TABLE `jforum_vote_desc` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_vote_desc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_vote_results`
--

DROP TABLE IF EXISTS `jforum_vote_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_results` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_option_id` tinyint(4) NOT NULL DEFAULT '0',
  `vote_option_text` varchar(255) NOT NULL DEFAULT '',
  `vote_result` int(11) NOT NULL DEFAULT '0',
  KEY `vote_id` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_vote_results`
--

LOCK TABLES `jforum_vote_results` WRITE;
/*!40000 ALTER TABLE `jforum_vote_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_vote_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_vote_voters`
--

DROP TABLE IF EXISTS `jforum_vote_voters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_voters` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_ip` varchar(15) NOT NULL DEFAULT '',
  KEY `vote_id` (`vote_id`),
  KEY `vote_user_id` (`vote_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_vote_voters`
--

LOCK TABLES `jforum_vote_voters` WRITE;
/*!40000 ALTER TABLE `jforum_vote_voters` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_vote_voters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jforum_words`
--

DROP TABLE IF EXISTS `jforum_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_words` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL DEFAULT '',
  `replacement` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jforum_words`
--

LOCK TABLES `jforum_words` WRITE;
/*!40000 ALTER TABLE `jforum_words` DISABLE KEYS */;
/*!40000 ALTER TABLE `jforum_words` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=40254192 DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM AUTO_INCREMENT=18190874 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (18190380,'2013-04-10 11:04:51','superadmin','logon','','superadmin','','',1),(18190381,'2013-04-10 11:07:00','superadmin','admin add','','renalaadmin','RENALA','',1),(18190382,'2013-04-10 11:07:15','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190383,'2013-04-10 11:07:24','renalaadmin','password change','','renalaadmin','RENALA','',1),(18190384,'2013-04-10 11:07:36','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190385,'2013-04-10 11:08:30','renalaadmin','patient add','1111111111','patienta','RENALA','',1),(18190386,'2013-04-10 11:08:40','patienta','logon','1111111111','patienta','RENALA','',1),(18190387,'2013-04-10 11:08:57','patienta','password change','','patienta','RENALA','',1),(18190388,'2013-04-10 11:09:17','patienta','email verified','1111111111','patienta','1111111111','patientview-testing@solidstategroup.com',1),(18190389,'2013-04-10 11:21:22','superadmin','logon','','superadmin','','',1),(18190390,'2013-04-10 11:25:59','superadmin','password reset','','patientview-testing@solidstategroup.com','RENALA','',1),(18190391,'2013-04-10 11:26:19','patientview-testing@solidstategroup.com','logon','','patientview-testing@solidstategroup.com','RENALA','',1),(18190392,'2013-04-10 11:26:28','patientview-testing@solidstategroup.com','password change','','patientview-testing@solidstategroup.com','RENALA','',1),(18190393,'2013-04-10 11:26:40','patientview-testing@solidstategroup.com','logon','','patientview-testing@solidstategroup.com','RENALA','',1),(18190394,'2013-04-10 11:51:05','patientview-testing@solidstategroup.com','logon','','patientview-testing@solidstategroup.com','RENALA','',1),(18190395,'2013-04-10 11:51:33','patientview-testing@solidstategroup.com','patient add','2222222222','radarpatienta','RENALA','',1),(18190396,'2013-04-10 11:51:52','radarpatienta','logon','2222222222','radarpatienta','RENALA','',1),(18190397,'2013-04-10 11:52:04','radarpatienta','password change','','radarpatienta','RENALA','',1),(18190398,'2013-04-10 11:59:05','superadmin','logon','','superadmin','','',1),(18190399,'2013-04-10 11:59:26','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190400,'2013-04-10 11:59:56','renalaadmin','patient add','3333333333','patientb','RENALA','',1),(18190401,'2013-04-10 12:00:13','patientb','logon','3333333333','patientb','RENALA','',1),(18190402,'2013-04-10 12:00:26','patientb','password change','','patientb','RENALA','',1),(18190403,'2013-04-10 12:00:35','patientb','logon','3333333333','patientb','RENALA','',1),(18190404,'2013-04-10 13:19:14','superadmin','logon','','superadmin','','',1),(18190405,'2013-04-10 13:19:57','superadmin','patient add','4444444444','asdasd','RENALA','',1),(18190406,'2013-04-10 14:12:27','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190407,'2013-04-10 14:13:12','renalaadmin','patient add','6424970037','sdfsadklf','RENALA','',1),(18190408,'2013-04-10 15:58:34','system','patient data load','3204969541','','2020','2020_0000379_3204969541.gpg.xml',NULL),(18190409,'2013-04-10 16:09:11','patienta','logon','1111111111','patienta','RENALA','',1),(18190410,'2013-04-10 16:17:47','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190411,'2013-04-10 16:21:30','patientb','logon','3333333333','patientb','RENALA','',1),(18190412,'2013-04-10 16:23:29','superadmin','logon','','superadmin','','',1),(18190413,'2013-04-10 16:29:55','patienta','logon','1111111111','patienta','RENALA','',1),(18190414,'2013-04-10 16:31:58','patienta','logon','1111111111','patienta','RENALA','',1),(18190415,'2013-04-10 16:34:43','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190416,'2013-04-10 16:38:52','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190417,'2013-04-10 16:42:26','renalaadmin','admin add','','neilconsultant','RENALA','',1),(18190418,'2013-04-10 16:48:25','neilconsultant','logon','','neilconsultant','RENALA','',1),(18190419,'2013-04-10 16:48:38','neilconsultant','password change','','neilconsultant','RENALA','',1),(18190420,'2013-04-10 16:50:43','neilconsultant','email verified','','neilconsultant','','neilturn@gmail.com',1),(18190421,'2013-04-10 16:52:04','neilconsultant','logon','','neilconsultant','RENALA','',1),(18190422,'2013-04-10 16:53:57','renalaadmin','patient add','2804562612','patientc','RENALA','',1),(18190423,'2013-04-10 16:54:44','patientc','logon','2804562612','patientc','RENALA','',1),(18190424,'2013-04-10 16:55:13','patientc','password change','','patientc','RENALA','',1),(18190425,'2013-04-10 16:55:54','patientc','email verified','','patientc','','nturner2@nhs.net',NULL),(18190426,'2013-04-10 16:56:03','patientc','logon','2804562612','patientc','RENALA','',1),(18190427,'2013-04-10 17:05:45','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190428,'2013-04-10 17:27:35','patientc','logon','2804562612','patientc','RENALA','',1),(18190429,'2013-04-10 17:29:26','neilconsultant','logon','','neilconsultant','RENALA','',1),(18190430,'2013-04-10 17:32:23','patientc','logon','2804562612','patientc','RENALA','',1),(18190431,'2013-04-11 10:00:41','system','patient data load','4087878406','','RAE05','rae05_000107_4087878406.gpg.xml',NULL),(18190432,'2013-04-11 10:00:42','system','patient data load','6067495406','','45020','45020_0000379_6067495406.gpg.xml',NULL),(18190433,'2013-04-11 10:00:43','system','patient data load','4445706906','','RAZ','raz_003498_4445706906.gpg.xml',NULL),(18190434,'2013-04-11 10:00:44','system','patient data load','4482941506','','RCJAT','rcjat_002742_4482941506.gpg.xml',NULL),(18190435,'2013-04-11 10:00:46','system','patient data load','4647463206','','RAQ01','RAQ01_001178_4647463206.gpg.xml',NULL),(18190436,'2013-04-11 10:00:46','system','patient data load','4908112606','','RCJAT','rcjat_002742_4908112606.gpg.xml',NULL),(18190437,'2013-04-11 10:00:46','system','patient data load','6343053506','','RCJAT','rcjat_002742_6343053506.gpg.xml',NULL),(18190438,'2013-04-11 10:00:47','system','patient data load','4742567706','','REF12','REF12_000460_4742567706.gpg.xml',NULL),(18190439,'2013-04-11 10:00:47','system','patient data load','6048066406','','RGU01','RGU01_000122_6048066406.gpg.xml',NULL),(18190440,'2013-04-11 10:00:47','system','patient data load','4468928506','','RGU01','RGU01_000215_4468928506.gpg.xml',NULL),(18190441,'2013-04-11 10:00:47','system','patient data load','6260711506','','RGU01','RGU01_000396_6260711506.gpg.xml',NULL),(18190442,'2013-04-11 10:00:47','system','patient data load','4564232606','','RGU01','RGU01_000445_4564232606.gpg.xml',NULL),(18190443,'2013-04-11 10:00:47','system','patient data load','4546792506','','RGU01','RGU01_000488_4546792506.gpg.xml',NULL),(18190444,'2013-04-11 10:00:47','system','patient data load','4122819806','','RGU01','RGU01_000574_4122819806.gpg.xml',NULL),(18190445,'2013-04-11 10:00:48','system','patient data load','4586203706','','RHU02','rhu02_007516_4586203706.gpg.xml',NULL),(18190446,'2013-04-11 10:00:48','system','patient data load','4904965906','','RHU02','rhu02_007516_4904965906.gpg.xml',NULL),(18190447,'2013-04-11 10:00:49','system','patient data load','4445790206','','RHU02','rhu02_007517_4445790206.gpg.xml',NULL),(18190448,'2013-04-11 10:00:49','system','patient data load','4971252606','','RHU02','rhu02_007516_4971252606.gpg.xml',NULL),(18190449,'2013-04-11 10:00:50','system','patient data load','4586203706','','RHU02','rhu02_007517_4586203706.gpg.xml',NULL),(18190450,'2013-04-11 10:00:50','system','patient data load','4681455806','','RHU02','rhu02_007517_4681455806.gpg.xml',NULL),(18190451,'2013-04-11 10:00:51','system','patient data load','4847463706','','RHU02','rhu02_007517_4847463706.gpg.xml',NULL),(18190452,'2013-04-11 10:00:51','system','patient data load','4725435406','','RHU02','rhu02_007517_4725435406.gpg.xml',NULL),(18190453,'2013-04-11 10:00:52','system','patient data load','4904965906','','RHU02','rhu02_007517_4904965906.gpg.xml',NULL),(18190454,'2013-04-11 10:00:53','system','patient data load','6070932706','','RHU02','rhu02_007517_6070932706.gpg.xml',NULL),(18190455,'2013-04-11 10:00:53','system','patient data load','4971252606','','RHU02','rhu02_007517_4971252606.gpg.xml',NULL),(18190456,'2013-04-11 10:00:54','system','patient data load','4445790206','','RHU02','rhu02_007518_4445790206.gpg.xml',NULL),(18190457,'2013-04-11 10:00:54','system','patient data load','6302448506','','RHU02','rhu02_007517_6302448506.gpg.xml',NULL),(18190458,'2013-04-11 10:00:55','system','patient data load','4586203706','','RHU02','rhu02_007518_4586203706.gpg.xml',NULL),(18190459,'2013-04-11 10:00:56','system','patient data load','4725435406','','RHU02','rhu02_007518_4725435406.gpg.xml',NULL),(18190460,'2013-04-11 10:00:56','system','patient data load','4681455806','','RHU02','rhu02_007518_4681455806.gpg.xml',NULL),(18190461,'2013-04-11 10:00:57','system','patient data load','4847463706','','RHU02','rhu02_007518_4847463706.gpg.xml',NULL),(18190462,'2013-04-11 10:00:57','system','patient data load','4971252606','','RHU02','rhu02_007518_4971252606.gpg.xml',NULL),(18190463,'2013-04-11 10:00:58','system','patient data load','4904965906','','RHU02','rhu02_007518_4904965906.gpg.xml',NULL),(18190464,'2013-04-11 10:00:58','system','patient data load','6070932706','','RHU02','rhu02_007518_6070932706.gpg.xml',NULL),(18190465,'2013-04-11 10:00:59','system','patient data load','6302448506','','RHU02','rhu02_007518_6302448506.gpg.xml',NULL),(18190466,'2013-04-11 10:01:00','system','patient data load','4445790206','','RHU02','rhu02_007519_4445790206.gpg.xml',NULL),(18190467,'2013-04-11 10:01:00','system','patient data load','4586203706','','RHU02','rhu02_007519_4586203706.gpg.xml',NULL),(18190468,'2013-04-11 10:01:01','system','patient data load','4681455806','','RHU02','rhu02_007519_4681455806.gpg.xml',NULL),(18190469,'2013-04-11 10:01:01','system','patient data load','4847463706','','RHU02','rhu02_007519_4847463706.gpg.xml',NULL),(18190470,'2013-04-11 10:01:02','system','patient data load','4725435406','','RHU02','rhu02_007519_4725435406.gpg.xml',NULL),(18190471,'2013-04-11 10:01:02','system','patient data load','4904965906','','RHU02','rhu02_007519_4904965906.gpg.xml',NULL),(18190472,'2013-04-11 10:01:03','system','patient data load','4971252606','','RHU02','rhu02_007519_4971252606.gpg.xml',NULL),(18190473,'2013-04-11 10:01:04','system','patient data load','6070932706','','RHU02','rhu02_007519_6070932706.gpg.xml',NULL),(18190474,'2013-04-11 10:01:04','system','patient data load','6302448506','','RHU02','rhu02_007519_6302448506.gpg.xml',NULL),(18190475,'2013-04-11 10:01:04','system','patient data load','4429440506','','RJ100','rj100_000116_4429440506.gpg.xml',NULL),(18190476,'2013-04-11 10:01:04','system','patient data load','6320259906','','RJZ','RJZ_000008_6320259906.gpg.xml',NULL),(18190477,'2013-04-11 10:01:05','system','patient data load','4582942806','','RK7CC','rk7cc_002089_4582942806.gpg.xml',NULL),(18190478,'2013-04-11 10:01:06','system','patient data load','4860328906','','RK7CC','rk7cc_002089_4860328906.gpg.xml',NULL),(18190479,'2013-04-11 10:01:06','system','patient data load','4945911606','','RK7CC','rk7cc_002089_4945911606.gpg.xml',NULL),(18190480,'2013-04-11 10:01:07','system','patient data load','6209468306','','RK7CC','rk7cc_002089_6209468306.gpg.xml',NULL),(18190481,'2013-04-11 10:01:07','system','patient data load','4908266506','','RKB01','rkb01_000530_4908266506.gpg.xml',NULL),(18190482,'2013-04-11 10:01:07','system','patient data load','4482075906','','RLNGH','RLNGH_001353_4482075906.gpg.xml',NULL),(18190483,'2013-04-11 10:01:07','system','patient data load','4482075906','','RLNGH','RLNGH_001354_4482075906.gpg.xml',NULL),(18190484,'2013-04-11 10:01:07','system','patient data load','4283486906','','RM102','RM102_0001843_4283486906.gpg.xml',NULL),(18190485,'2013-04-11 10:01:07','system','patient data load','4487674506','','RM102','RM102_0001843_4487674506.gpg.xml',NULL),(18190486,'2013-04-11 10:01:07','system','patient data load','6041028606','','RM102','RM102_0001843_6041028606.gpg.xml',NULL),(18190487,'2013-04-11 10:01:08','system','patient data load','4527925806','','RM301','rm301_002177_4527925806.gpg.xml',NULL),(18190488,'2013-04-11 10:01:08','system','patient data load','4540652206','','RM301','rm301_002177_4540652206.gpg.xml',NULL),(18190489,'2013-04-11 10:01:08','system','patient data load','4700462906','','RM301','rm301_002177_4700462906.gpg.xml',NULL),(18190490,'2013-04-11 10:01:08','system','patient data load','4700504706','','RM301','rm301_002177_4700504706.gpg.xml',NULL),(18190491,'2013-04-11 10:01:09','system','patient data load','4708181906','','RM301','rm301_002177_4708181906.gpg.xml',NULL),(18190492,'2013-04-11 10:01:09','system','patient data load','6148115506','','RM301','rm301_002177_6148115506.gpg.xml',NULL),(18190493,'2013-04-11 10:01:09','system','patient data load','6185790106','','RM301','rm301_002177_6185790106.gpg.xml',NULL),(18190494,'2013-04-11 10:01:09','system','patient data load','6305828806','','RM301','rm301_002177_6305828806.gpg.xml',NULL),(18190495,'2013-04-11 10:01:09','system','patient data load','4021785906','','RM574','RM574_000110_4021785906.gpg.xml',NULL),(18190496,'2013-04-11 10:01:09','system','patient data load','6281792106','','RM301','rm301_002177_6281792106.gpg.xml',NULL),(18190497,'2013-04-11 10:01:09','system','patient data load','4761842806','','RM574','RM574_000343_4761842806.gpg.xml',NULL),(18190498,'2013-04-11 10:01:10','system','patient data load','4528844206','','RMF01','rmf01_007267_4528844206.gpg.xml',NULL),(18190499,'2013-04-11 10:01:10','system','patient data load','6180612706','','RM574','RM574_000332_6180612706.gpg.xml',NULL),(18190500,'2013-04-11 10:01:10','system','patient data load','6189530206','','RMF01','rmf01_007267_6189530206.gpg.xml',NULL),(18190501,'2013-04-11 10:01:11','system','patient data load','4540124706','','RMF01','rmf01_007267_4540124706.gpg.xml',NULL),(18190502,'2013-04-11 10:01:11','system','patient data load','4528844206','','RMF01','rmf01_007268_4528844206.gpg.xml',NULL),(18190503,'2013-04-11 10:01:12','system','patient data load','6189530206','','RMF01','rmf01_007268_6189530206.gpg.xml',NULL),(18190504,'2013-04-11 10:01:12','system','patient data load','4540124706','','RMF01','rmf01_007268_4540124706.gpg.xml',NULL),(18190505,'2013-04-11 10:01:13','system','patient data load','4105496506','','RQR00','rqr00_000107_4105496506.gpg.xml',NULL),(18190506,'2013-04-11 10:01:13','system','patient data load','4542524906','','RRK02','RRK02_001286_4542524906.gpg.xml',NULL),(18190507,'2013-04-11 10:01:14','system','patient data load','4545932606','','RRK02','RRK02_001286_4545932606.gpg.xml',NULL),(18190508,'2013-04-11 10:01:14','system','patient data load','4731555906','','RRK02','RRK02_001286_4731555906.gpg.xml',NULL),(18190509,'2013-04-11 10:01:15','system','patient data load','6081253406','','RRK02','RRK02_001286_6081253406.gpg.xml',NULL),(18190510,'2013-04-11 10:01:15','system','patient data load','6091400606','','RRK02','RRK02_001286_6091400606.gpg.xml',NULL),(18190511,'2013-04-11 10:01:15','system','patient data load','6263926406','','RRK02','RRK02_001286_6263926406.gpg.xml',NULL),(18190512,'2013-04-11 10:01:16','system','patient data load','6362908406','','RRK02','RRK02_001286_6362908406.gpg.xml',NULL),(18190513,'2013-04-11 10:01:16','system','patient data load','6287399406','','RRK02','RRK02_001286_6287399406.gpg.xml',NULL),(18190514,'2013-04-11 10:01:17','system','patient data load','6491705706','','RRK02','RRK02_001286_6491705706.gpg.xml',NULL),(18190515,'2013-04-11 10:01:17','system','patient data load','0202581306','','RSC02','rsc02_006767_0202581306.gpg.xml',NULL),(18190516,'2013-04-11 10:01:18','system','patient data load','1306741106','','RSC02','rsc02_006767_1306741106.gpg.xml',NULL),(18190517,'2013-04-11 10:01:18','system','patient data load','4485079406','','RTD01','RTD01_000237_4485079406.gpg.xml',NULL),(18190518,'2013-04-11 10:09:09','system','patient data load','2007661713','','RSC02','rsc02_006639_2007661713.gpg.xml',NULL),(18190519,'2013-04-11 10:09:09','system','patient data load','3204969541','','2020','2020_0000379_3204969541.gpg.xml',NULL),(18190520,'2013-04-11 10:09:10','system','patient data load','4087878406','','RAE05','rae05_000107_4087878406.gpg.xml',NULL),(18190521,'2013-04-11 10:09:11','system','patient data load','6067495406','','45020','45020_0000379_6067495406.gpg.xml',NULL),(18190522,'2013-04-11 10:09:12','system','patient data load','4445706906','','RAZ','raz_003498_4445706906.gpg.xml',NULL),(18190523,'2013-04-11 10:09:13','system','patient data load','4482941506','','RCJAT','rcjat_002742_4482941506.gpg.xml',NULL),(18190524,'2013-04-11 10:09:14','system','patient data load','4647463206','','RAQ01','RAQ01_001178_4647463206.gpg.xml',NULL),(18190525,'2013-04-11 10:09:14','system','patient data load','4908112606','','RCJAT','rcjat_002742_4908112606.gpg.xml',NULL),(18190526,'2013-04-11 10:09:15','system','patient data load','6343053506','','RCJAT','rcjat_002742_6343053506.gpg.xml',NULL),(18190527,'2013-04-11 10:09:15','system','patient data load','4742567706','','REF12','REF12_000460_4742567706.gpg.xml',NULL),(18190528,'2013-04-11 10:09:15','system','patient data load','6048066406','','RGU01','RGU01_000122_6048066406.gpg.xml',NULL),(18190529,'2013-04-11 10:09:15','system','patient data load','4468928506','','RGU01','RGU01_000215_4468928506.gpg.xml',NULL),(18190530,'2013-04-11 10:09:16','system','patient data load','6260711506','','RGU01','RGU01_000396_6260711506.gpg.xml',NULL),(18190531,'2013-04-11 10:09:16','system','patient data load','4564232606','','RGU01','RGU01_000445_4564232606.gpg.xml',NULL),(18190532,'2013-04-11 10:09:16','system','patient data load','4546792506','','RGU01','RGU01_000488_4546792506.gpg.xml',NULL),(18190533,'2013-04-11 10:09:16','system','patient data load','4122819806','','RGU01','RGU01_000574_4122819806.gpg.xml',NULL),(18190534,'2013-04-11 10:09:16','system','patient data load','4586203706','','RHU02','rhu02_007516_4586203706.gpg.xml',NULL),(18190535,'2013-04-11 10:09:17','system','patient data load','4904965906','','RHU02','rhu02_007516_4904965906.gpg.xml',NULL),(18190536,'2013-04-11 10:09:18','system','patient data load','4445790206','','RHU02','rhu02_007517_4445790206.gpg.xml',NULL),(18190537,'2013-04-11 10:09:18','system','patient data load','4971252606','','RHU02','rhu02_007516_4971252606.gpg.xml',NULL),(18190538,'2013-04-11 10:09:19','system','patient data load','4586203706','','RHU02','rhu02_007517_4586203706.gpg.xml',NULL),(18190539,'2013-04-11 10:09:20','system','patient data load','4681455806','','RHU02','rhu02_007517_4681455806.gpg.xml',NULL),(18190540,'2013-04-11 10:09:20','system','patient data load','4847463706','','RHU02','rhu02_007517_4847463706.gpg.xml',NULL),(18190541,'2013-04-11 10:09:21','system','patient data load','4725435406','','RHU02','rhu02_007517_4725435406.gpg.xml',NULL),(18190542,'2013-04-11 10:09:22','system','patient data load','4904965906','','RHU02','rhu02_007517_4904965906.gpg.xml',NULL),(18190543,'2013-04-11 10:09:23','system','patient data load','6070932706','','RHU02','rhu02_007517_6070932706.gpg.xml',NULL),(18190544,'2013-04-11 10:09:23','system','patient data load','4971252606','','RHU02','rhu02_007517_4971252606.gpg.xml',NULL),(18190545,'2013-04-11 10:09:24','system','patient data load','4445790206','','RHU02','rhu02_007518_4445790206.gpg.xml',NULL),(18190546,'2013-04-11 10:09:25','system','patient data load','6302448506','','RHU02','rhu02_007517_6302448506.gpg.xml',NULL),(18190547,'2013-04-11 10:09:25','system','patient data load','4586203706','','RHU02','rhu02_007518_4586203706.gpg.xml',NULL),(18190548,'2013-04-11 10:09:26','system','patient data load','4725435406','','RHU02','rhu02_007518_4725435406.gpg.xml',NULL),(18190549,'2013-04-11 10:09:27','system','patient data load','4681455806','','RHU02','rhu02_007518_4681455806.gpg.xml',NULL),(18190550,'2013-04-11 10:09:27','system','patient data load','4847463706','','RHU02','rhu02_007518_4847463706.gpg.xml',NULL),(18190551,'2013-04-11 10:09:28','system','patient data load','4971252606','','RHU02','rhu02_007518_4971252606.gpg.xml',NULL),(18190552,'2013-04-11 10:09:29','system','patient data load','4904965906','','RHU02','rhu02_007518_4904965906.gpg.xml',NULL),(18190553,'2013-04-11 10:09:30','system','patient data load','6070932706','','RHU02','rhu02_007518_6070932706.gpg.xml',NULL),(18190554,'2013-04-11 10:09:30','system','patient data load','6302448506','','RHU02','rhu02_007518_6302448506.gpg.xml',NULL),(18190555,'2013-04-11 10:09:31','system','patient data load','4445790206','','RHU02','rhu02_007519_4445790206.gpg.xml',NULL),(18190556,'2013-04-11 10:09:32','system','patient data load','4586203706','','RHU02','rhu02_007519_4586203706.gpg.xml',NULL),(18190557,'2013-04-11 10:09:32','system','patient data load','4681455806','','RHU02','rhu02_007519_4681455806.gpg.xml',NULL),(18190558,'2013-04-11 10:09:33','system','patient data load','4847463706','','RHU02','rhu02_007519_4847463706.gpg.xml',NULL),(18190559,'2013-04-11 10:09:34','system','patient data load','4725435406','','RHU02','rhu02_007519_4725435406.gpg.xml',NULL),(18190560,'2013-04-11 10:09:34','system','patient data load','4904965906','','RHU02','rhu02_007519_4904965906.gpg.xml',NULL),(18190561,'2013-04-11 10:09:35','system','patient data load','4971252606','','RHU02','rhu02_007519_4971252606.gpg.xml',NULL),(18190562,'2013-04-11 10:09:36','system','patient data load','6070932706','','RHU02','rhu02_007519_6070932706.gpg.xml',NULL),(18190563,'2013-04-11 10:09:37','system','patient data load','6302448506','','RHU02','rhu02_007519_6302448506.gpg.xml',NULL),(18190564,'2013-04-11 10:09:37','system','patient data load','4429440506','','RJ100','rj100_000116_4429440506.gpg.xml',NULL),(18190565,'2013-04-11 10:09:37','system','patient data load','6320259906','','RJZ','RJZ_000008_6320259906.gpg.xml',NULL),(18190566,'2013-04-11 10:09:38','system','patient data load','4582942806','','RK7CC','rk7cc_002089_4582942806.gpg.xml',NULL),(18190567,'2013-04-11 10:09:38','system','patient data load','4860328906','','RK7CC','rk7cc_002089_4860328906.gpg.xml',NULL),(18190568,'2013-04-11 10:09:39','system','patient data load','4945911606','','RK7CC','rk7cc_002089_4945911606.gpg.xml',NULL),(18190569,'2013-04-11 10:09:40','system','patient data load','6209468306','','RK7CC','rk7cc_002089_6209468306.gpg.xml',NULL),(18190570,'2013-04-11 10:09:40','system','patient data load','4908266506','','RKB01','rkb01_000530_4908266506.gpg.xml',NULL),(18190571,'2013-04-11 10:09:40','system','patient data load','4482075906','','RLNGH','RLNGH_001353_4482075906.gpg.xml',NULL),(18190572,'2013-04-11 10:09:40','system','patient data load','4482075906','','RLNGH','RLNGH_001354_4482075906.gpg.xml',NULL),(18190573,'2013-04-11 10:09:40','system','patient data load','4283486906','','RM102','RM102_0001843_4283486906.gpg.xml',NULL),(18190574,'2013-04-11 10:09:40','system','patient data load','4487674506','','RM102','RM102_0001843_4487674506.gpg.xml',NULL),(18190575,'2013-04-11 10:09:40','system','patient data load','6041028606','','RM102','RM102_0001843_6041028606.gpg.xml',NULL),(18190576,'2013-04-11 10:09:41','system','patient data load','4527925806','','RM301','rm301_002177_4527925806.gpg.xml',NULL),(18190577,'2013-04-11 10:09:41','system','patient data load','4540652206','','RM301','rm301_002177_4540652206.gpg.xml',NULL),(18190578,'2013-04-11 10:09:41','system','patient data load','4700462906','','RM301','rm301_002177_4700462906.gpg.xml',NULL),(18190579,'2013-04-11 10:09:42','system','patient data load','4700504706','','RM301','rm301_002177_4700504706.gpg.xml',NULL),(18190580,'2013-04-11 10:09:42','system','patient data load','4708181906','','RM301','rm301_002177_4708181906.gpg.xml',NULL),(18190581,'2013-04-11 10:09:42','system','patient data load','6148115506','','RM301','rm301_002177_6148115506.gpg.xml',NULL),(18190582,'2013-04-11 10:09:42','system','patient data load','6185790106','','RM301','rm301_002177_6185790106.gpg.xml',NULL),(18190583,'2013-04-11 10:09:43','system','patient data load','6305828806','','RM301','rm301_002177_6305828806.gpg.xml',NULL),(18190584,'2013-04-11 10:09:43','system','patient data load','4021785906','','RM574','RM574_000110_4021785906.gpg.xml',NULL),(18190585,'2013-04-11 10:09:43','system','patient data load','6281792106','','RM301','rm301_002177_6281792106.gpg.xml',NULL),(18190586,'2013-04-11 10:09:43','system','patient data load','4761842806','','RM574','RM574_000343_4761842806.gpg.xml',NULL),(18190587,'2013-04-11 10:09:44','system','patient data load','4528844206','','RMF01','rmf01_007267_4528844206.gpg.xml',NULL),(18190588,'2013-04-11 10:09:44','system','patient data load','6180612706','','RM574','RM574_000332_6180612706.gpg.xml',NULL),(18190589,'2013-04-11 10:09:44','system','patient data load','6189530206','','RMF01','rmf01_007267_6189530206.gpg.xml',NULL),(18190590,'2013-04-11 10:09:45','system','patient data load','4540124706','','RMF01','rmf01_007267_4540124706.gpg.xml',NULL),(18190591,'2013-04-11 10:09:46','system','patient data load','4528844206','','RMF01','rmf01_007268_4528844206.gpg.xml',NULL),(18190592,'2013-04-11 10:09:46','system','patient data load','6189530206','','RMF01','rmf01_007268_6189530206.gpg.xml',NULL),(18190593,'2013-04-11 10:09:47','system','patient data load','4540124706','','RMF01','rmf01_007268_4540124706.gpg.xml',NULL),(18190594,'2013-04-11 10:09:48','system','patient data load','4105496506','','RQR00','rqr00_000107_4105496506.gpg.xml',NULL),(18190595,'2013-04-11 10:09:48','system','patient data load','4542524906','','RRK02','RRK02_001286_4542524906.gpg.xml',NULL),(18190596,'2013-04-11 10:09:49','system','patient data load','4545932606','','RRK02','RRK02_001286_4545932606.gpg.xml',NULL),(18190597,'2013-04-11 10:09:49','system','patient data load','4731555906','','RRK02','RRK02_001286_4731555906.gpg.xml',NULL),(18190598,'2013-04-11 10:09:49','system','patient data load','6081253406','','RRK02','RRK02_001286_6081253406.gpg.xml',NULL),(18190599,'2013-04-11 10:09:50','system','patient data load','6091400606','','RRK02','RRK02_001286_6091400606.gpg.xml',NULL),(18190600,'2013-04-11 10:09:50','system','patient data load','6263926406','','RRK02','RRK02_001286_6263926406.gpg.xml',NULL),(18190601,'2013-04-11 10:09:51','system','patient data load','6362908406','','RRK02','RRK02_001286_6362908406.gpg.xml',NULL),(18190602,'2013-04-11 10:09:52','system','patient data load','6287399406','','RRK02','RRK02_001286_6287399406.gpg.xml',NULL),(18190603,'2013-04-11 10:09:52','system','patient data load','6491705706','','RRK02','RRK02_001286_6491705706.gpg.xml',NULL),(18190604,'2013-04-11 10:09:53','system','patient data load','0202581306','','RSC02','rsc02_006767_0202581306.gpg.xml',NULL),(18190605,'2013-04-11 10:09:54','system','patient data load','1306741106','','RSC02','rsc02_006767_1306741106.gpg.xml',NULL),(18190606,'2013-04-11 10:09:54','system','patient data load','4485079406','','RTD01','RTD01_000237_4485079406.gpg.xml',NULL),(18190607,'2013-04-11 10:14:33','system','patient data load','2007661713','','RSC02','rsc02_006639_2007661713.gpg.xml',NULL),(18190608,'2013-04-11 10:14:34','system','patient data load','3204969541','','2020','2020_0000379_3204969541.gpg.xml',NULL),(18190609,'2013-04-11 10:14:35','system','patient data load','4087878406','','RAE05','rae05_000107_4087878406.gpg.xml',NULL),(18190610,'2013-04-11 10:14:35','system','patient data load','6067495406','','45020','45020_0000379_6067495406.gpg.xml',NULL),(18190611,'2013-04-11 10:14:36','system','patient data load','4445706906','','RAZ','raz_003498_4445706906.gpg.xml',NULL),(18190612,'2013-04-11 10:14:37','system','patient data load','4482941506','','RCJAT','rcjat_002742_4482941506.gpg.xml',NULL),(18190613,'2013-04-11 10:14:39','system','patient data load','4647463206','','RAQ01','RAQ01_001178_4647463206.gpg.xml',NULL),(18190614,'2013-04-11 10:14:39','system','patient data load','4908112606','','RCJAT','rcjat_002742_4908112606.gpg.xml',NULL),(18190615,'2013-04-11 10:14:40','system','patient data load','6343053506','','RCJAT','rcjat_002742_6343053506.gpg.xml',NULL),(18190616,'2013-04-11 10:14:40','system','patient data load','4742567706','','REF12','REF12_000460_4742567706.gpg.xml',NULL),(18190617,'2013-04-11 10:14:40','system','patient data load','6048066406','','RGU01','RGU01_000122_6048066406.gpg.xml',NULL),(18190618,'2013-04-11 10:14:40','system','patient data load','4468928506','','RGU01','RGU01_000215_4468928506.gpg.xml',NULL),(18190619,'2013-04-11 10:14:40','system','patient data load','6260711506','','RGU01','RGU01_000396_6260711506.gpg.xml',NULL),(18190620,'2013-04-11 10:14:40','system','patient data load','4564232606','','RGU01','RGU01_000445_4564232606.gpg.xml',NULL),(18190621,'2013-04-11 10:14:40','system','patient data load','4546792506','','RGU01','RGU01_000488_4546792506.gpg.xml',NULL),(18190622,'2013-04-11 10:14:40','system','patient data load','4122819806','','RGU01','RGU01_000574_4122819806.gpg.xml',NULL),(18190623,'2013-04-11 10:14:41','system','patient data load','4586203706','','RHU02','rhu02_007516_4586203706.gpg.xml',NULL),(18190624,'2013-04-11 10:14:42','system','patient data load','4904965906','','RHU02','rhu02_007516_4904965906.gpg.xml',NULL),(18190625,'2013-04-11 10:14:42','system','patient data load','4445790206','','RHU02','rhu02_007517_4445790206.gpg.xml',NULL),(18190626,'2013-04-11 10:14:43','system','patient data load','4971252606','','RHU02','rhu02_007516_4971252606.gpg.xml',NULL),(18190627,'2013-04-11 10:14:44','system','patient data load','4586203706','','RHU02','rhu02_007517_4586203706.gpg.xml',NULL),(18190628,'2013-04-11 10:14:45','system','patient data load','4681455806','','RHU02','rhu02_007517_4681455806.gpg.xml',NULL),(18190629,'2013-04-11 10:14:45','system','patient data load','4847463706','','RHU02','rhu02_007517_4847463706.gpg.xml',NULL),(18190630,'2013-04-11 10:14:46','system','patient data load','4725435406','','RHU02','rhu02_007517_4725435406.gpg.xml',NULL),(18190631,'2013-04-11 10:14:47','system','patient data load','4904965906','','RHU02','rhu02_007517_4904965906.gpg.xml',NULL),(18190632,'2013-04-11 10:14:47','system','patient data load','6070932706','','RHU02','rhu02_007517_6070932706.gpg.xml',NULL),(18190633,'2013-04-11 10:14:48','system','patient data load','4971252606','','RHU02','rhu02_007517_4971252606.gpg.xml',NULL),(18190634,'2013-04-11 10:14:49','system','patient data load','4445790206','','RHU02','rhu02_007518_4445790206.gpg.xml',NULL),(18190635,'2013-04-11 10:14:50','system','patient data load','6302448506','','RHU02','rhu02_007517_6302448506.gpg.xml',NULL),(18190636,'2013-04-11 10:14:51','system','patient data load','4586203706','','RHU02','rhu02_007518_4586203706.gpg.xml',NULL),(18190637,'2013-04-11 10:14:52','system','patient data load','4725435406','','RHU02','rhu02_007518_4725435406.gpg.xml',NULL),(18190638,'2013-04-11 10:14:53','system','patient data load','4681455806','','RHU02','rhu02_007518_4681455806.gpg.xml',NULL),(18190639,'2013-04-11 10:14:53','system','patient data load','4847463706','','RHU02','rhu02_007518_4847463706.gpg.xml',NULL),(18190640,'2013-04-11 10:16:24','system','patient data load','2007661713','','RSC02','rsc02_006639_2007661713.gpg.xml',NULL),(18190641,'2013-04-11 10:16:24','system','patient data load','3204969541','','2020','2020_0000379_3204969541.gpg.xml',NULL),(18190642,'2013-04-11 10:16:25','system','patient data load','4087878406','','RAE05','rae05_000107_4087878406.gpg.xml',NULL),(18190643,'2013-04-11 10:16:26','system','patient data load','6067495406','','45020','45020_0000379_6067495406.gpg.xml',NULL),(18190644,'2013-04-11 10:16:27','system','patient data load','4445706906','','RAZ','raz_003498_4445706906.gpg.xml',NULL),(18190645,'2013-04-11 10:16:28','system','patient data load','4482941506','','RCJAT','rcjat_002742_4482941506.gpg.xml',NULL),(18190646,'2013-04-11 10:16:29','system','patient data load','4647463206','','RAQ01','RAQ01_001178_4647463206.gpg.xml',NULL),(18190647,'2013-04-11 10:16:29','system','patient data load','4908112606','','RCJAT','rcjat_002742_4908112606.gpg.xml',NULL),(18190648,'2013-04-11 10:16:29','system','patient data load','6343053506','','RCJAT','rcjat_002742_6343053506.gpg.xml',NULL),(18190649,'2013-04-11 10:16:30','system','patient data load','4742567706','','REF12','REF12_000460_4742567706.gpg.xml',NULL),(18190650,'2013-04-11 10:16:30','system','patient data load','6048066406','','RGU01','RGU01_000122_6048066406.gpg.xml',NULL),(18190651,'2013-04-11 10:16:30','system','patient data load','4468928506','','RGU01','RGU01_000215_4468928506.gpg.xml',NULL),(18190652,'2013-04-11 10:16:30','system','patient data load','6260711506','','RGU01','RGU01_000396_6260711506.gpg.xml',NULL),(18190653,'2013-04-11 10:16:30','system','patient data load','4564232606','','RGU01','RGU01_000445_4564232606.gpg.xml',NULL),(18190654,'2013-04-11 10:16:30','system','patient data load','4546792506','','RGU01','RGU01_000488_4546792506.gpg.xml',NULL),(18190655,'2013-04-11 10:16:30','system','patient data load','4122819806','','RGU01','RGU01_000574_4122819806.gpg.xml',NULL),(18190656,'2013-04-11 10:16:31','system','patient data load','4586203706','','RHU02','rhu02_007516_4586203706.gpg.xml',NULL),(18190657,'2013-04-11 10:16:31','system','patient data load','4904965906','','RHU02','rhu02_007516_4904965906.gpg.xml',NULL),(18190658,'2013-04-11 10:16:32','system','patient data load','4445790206','','RHU02','rhu02_007517_4445790206.gpg.xml',NULL),(18190659,'2013-04-11 10:16:33','system','patient data load','4971252606','','RHU02','rhu02_007516_4971252606.gpg.xml',NULL),(18190660,'2013-04-11 10:16:34','system','patient data load','4586203706','','RHU02','rhu02_007517_4586203706.gpg.xml',NULL),(18190661,'2013-04-11 10:16:34','system','patient data load','4681455806','','RHU02','rhu02_007517_4681455806.gpg.xml',NULL),(18190662,'2013-04-11 10:16:35','system','patient data load','4847463706','','RHU02','rhu02_007517_4847463706.gpg.xml',NULL),(18190663,'2013-04-11 10:16:36','system','patient data load','4725435406','','RHU02','rhu02_007517_4725435406.gpg.xml',NULL),(18190664,'2013-04-11 10:16:36','system','patient data load','4904965906','','RHU02','rhu02_007517_4904965906.gpg.xml',NULL),(18190665,'2013-04-11 10:16:37','system','patient data load','6070932706','','RHU02','rhu02_007517_6070932706.gpg.xml',NULL),(18190666,'2013-04-11 10:16:38','system','patient data load','4971252606','','RHU02','rhu02_007517_4971252606.gpg.xml',NULL),(18190667,'2013-04-11 10:16:39','system','patient data load','4445790206','','RHU02','rhu02_007518_4445790206.gpg.xml',NULL),(18190668,'2013-04-11 10:16:39','system','patient data load','6302448506','','RHU02','rhu02_007517_6302448506.gpg.xml',NULL),(18190669,'2013-04-11 10:16:40','system','patient data load','4586203706','','RHU02','rhu02_007518_4586203706.gpg.xml',NULL),(18190670,'2013-04-11 10:16:41','system','patient data load','4725435406','','RHU02','rhu02_007518_4725435406.gpg.xml',NULL),(18190671,'2013-04-11 10:16:42','system','patient data load','4681455806','','RHU02','rhu02_007518_4681455806.gpg.xml',NULL),(18190672,'2013-04-11 10:16:42','system','patient data load','4847463706','','RHU02','rhu02_007518_4847463706.gpg.xml',NULL),(18190673,'2013-04-11 10:16:43','system','patient data load','4971252606','','RHU02','rhu02_007518_4971252606.gpg.xml',NULL),(18190674,'2013-04-11 10:16:44','system','patient data load','4904965906','','RHU02','rhu02_007518_4904965906.gpg.xml',NULL),(18190675,'2013-04-11 10:16:44','system','patient data load','6070932706','','RHU02','rhu02_007518_6070932706.gpg.xml',NULL),(18190676,'2013-04-11 10:16:45','system','patient data load','6302448506','','RHU02','rhu02_007518_6302448506.gpg.xml',NULL),(18190677,'2013-04-11 10:16:46','system','patient data load','4445790206','','RHU02','rhu02_007519_4445790206.gpg.xml',NULL),(18190678,'2013-04-11 10:16:46','system','patient data load','4586203706','','RHU02','rhu02_007519_4586203706.gpg.xml',NULL),(18190679,'2013-04-11 10:16:47','system','patient data load','4681455806','','RHU02','rhu02_007519_4681455806.gpg.xml',NULL),(18190680,'2013-04-11 10:16:48','system','patient data load','4847463706','','RHU02','rhu02_007519_4847463706.gpg.xml',NULL),(18190681,'2013-04-11 10:16:49','system','patient data load','4725435406','','RHU02','rhu02_007519_4725435406.gpg.xml',NULL),(18190682,'2013-04-11 10:16:49','system','patient data load','4904965906','','RHU02','rhu02_007519_4904965906.gpg.xml',NULL),(18190683,'2013-04-11 10:16:50','system','patient data load','4971252606','','RHU02','rhu02_007519_4971252606.gpg.xml',NULL),(18190684,'2013-04-11 10:16:51','system','patient data load','6070932706','','RHU02','rhu02_007519_6070932706.gpg.xml',NULL),(18190685,'2013-04-11 10:16:51','system','patient data load','6302448506','','RHU02','rhu02_007519_6302448506.gpg.xml',NULL),(18190686,'2013-04-11 10:16:51','system','patient data load','4429440506','','RJ100','rj100_000116_4429440506.gpg.xml',NULL),(18190687,'2013-04-11 10:16:51','system','patient data load','6320259906','','RJZ','RJZ_000008_6320259906.gpg.xml',NULL),(18190688,'2013-04-11 10:16:52','system','patient data load','4582942806','','RK7CC','rk7cc_002089_4582942806.gpg.xml',NULL),(18190689,'2013-04-11 10:16:53','system','patient data load','4860328906','','RK7CC','rk7cc_002089_4860328906.gpg.xml',NULL),(18190690,'2013-04-11 10:16:54','system','patient data load','4945911606','','RK7CC','rk7cc_002089_4945911606.gpg.xml',NULL),(18190691,'2013-04-11 10:16:54','system','patient data load','6209468306','','RK7CC','rk7cc_002089_6209468306.gpg.xml',NULL),(18190692,'2013-04-11 10:16:55','system','patient data load','4908266506','','RKB01','rkb01_000530_4908266506.gpg.xml',NULL),(18190693,'2013-04-11 10:16:55','system','patient data load','4482075906','','RLNGH','RLNGH_001353_4482075906.gpg.xml',NULL),(18190694,'2013-04-11 10:16:55','system','patient data load','4482075906','','RLNGH','RLNGH_001354_4482075906.gpg.xml',NULL),(18190695,'2013-04-11 10:16:55','system','patient data load','4283486906','','RM102','RM102_0001843_4283486906.gpg.xml',NULL),(18190696,'2013-04-11 10:16:55','system','patient data load','4487674506','','RM102','RM102_0001843_4487674506.gpg.xml',NULL),(18190697,'2013-04-11 10:16:55','system','patient data load','6041028606','','RM102','RM102_0001843_6041028606.gpg.xml',NULL),(18190698,'2013-04-11 10:16:55','system','patient data load','4527925806','','RM301','rm301_002177_4527925806.gpg.xml',NULL),(18190699,'2013-04-11 10:16:56','system','patient data load','4540652206','','RM301','rm301_002177_4540652206.gpg.xml',NULL),(18190700,'2013-04-11 10:16:56','system','patient data load','4700462906','','RM301','rm301_002177_4700462906.gpg.xml',NULL),(18190701,'2013-04-11 10:16:56','system','patient data load','4700504706','','RM301','rm301_002177_4700504706.gpg.xml',NULL),(18190702,'2013-04-11 10:16:57','system','patient data load','4708181906','','RM301','rm301_002177_4708181906.gpg.xml',NULL),(18190703,'2013-04-11 10:16:57','system','patient data load','6148115506','','RM301','rm301_002177_6148115506.gpg.xml',NULL),(18190704,'2013-04-11 10:16:57','system','patient data load','6185790106','','RM301','rm301_002177_6185790106.gpg.xml',NULL),(18190705,'2013-04-11 10:16:57','system','patient data load','6305828806','','RM301','rm301_002177_6305828806.gpg.xml',NULL),(18190706,'2013-04-11 10:16:57','system','patient data load','4021785906','','RM574','RM574_000110_4021785906.gpg.xml',NULL),(18190707,'2013-04-11 10:16:57','system','patient data load','6281792106','','RM301','rm301_002177_6281792106.gpg.xml',NULL),(18190708,'2013-04-11 10:16:57','system','patient data load','4761842806','','RM574','RM574_000343_4761842806.gpg.xml',NULL),(18190709,'2013-04-11 10:16:58','system','patient data load','4528844206','','RMF01','rmf01_007267_4528844206.gpg.xml',NULL),(18190710,'2013-04-11 10:16:58','system','patient data load','6180612706','','RM574','RM574_000332_6180612706.gpg.xml',NULL),(18190711,'2013-04-11 10:16:59','system','patient data load','6189530206','','RMF01','rmf01_007267_6189530206.gpg.xml',NULL),(18190712,'2013-04-11 10:17:00','system','patient data load','4540124706','','RMF01','rmf01_007267_4540124706.gpg.xml',NULL),(18190713,'2013-04-11 10:17:00','system','patient data load','4528844206','','RMF01','rmf01_007268_4528844206.gpg.xml',NULL),(18190714,'2013-04-11 10:17:01','system','patient data load','6189530206','','RMF01','rmf01_007268_6189530206.gpg.xml',NULL),(18190715,'2013-04-11 10:17:02','system','patient data load','4540124706','','RMF01','rmf01_007268_4540124706.gpg.xml',NULL),(18190716,'2013-04-11 10:17:02','system','patient data load','4105496506','','RQR00','rqr00_000107_4105496506.gpg.xml',NULL),(18190717,'2013-04-11 10:17:03','system','patient data load','4542524906','','RRK02','RRK02_001286_4542524906.gpg.xml',NULL),(18190718,'2013-04-11 10:17:03','system','patient data load','4545932606','','RRK02','RRK02_001286_4545932606.gpg.xml',NULL),(18190719,'2013-04-11 10:17:04','system','patient data load','4731555906','','RRK02','RRK02_001286_4731555906.gpg.xml',NULL),(18190720,'2013-04-11 10:17:04','system','patient data load','6081253406','','RRK02','RRK02_001286_6081253406.gpg.xml',NULL),(18190721,'2013-04-11 10:17:04','system','patient data load','6091400606','','RRK02','RRK02_001286_6091400606.gpg.xml',NULL),(18190722,'2013-04-11 10:17:05','system','patient data load','6263926406','','RRK02','RRK02_001286_6263926406.gpg.xml',NULL),(18190723,'2013-04-11 10:17:06','system','patient data load','6362908406','','RRK02','RRK02_001286_6362908406.gpg.xml',NULL),(18190724,'2013-04-11 10:17:06','system','patient data load','6287399406','','RRK02','RRK02_001286_6287399406.gpg.xml',NULL),(18190725,'2013-04-11 10:17:07','system','patient data load','6491705706','','RRK02','RRK02_001286_6491705706.gpg.xml',NULL),(18190726,'2013-04-11 10:17:08','system','patient data load','0202581306','','RSC02','rsc02_006767_0202581306.gpg.xml',NULL),(18190727,'2013-04-11 10:17:08','system','patient data load','1306741106','','RSC02','rsc02_006767_1306741106.gpg.xml',NULL),(18190728,'2013-04-11 10:17:08','system','patient data load','4485079406','','RTD01','RTD01_000237_4485079406.gpg.xml',NULL),(18190729,'2013-04-11 10:30:00','system','patient data fail','4486032306','','RTD01','RTD01_000000_4486032306.gpg.xml : there was an error test',NULL),(18190730,'2013-04-11 10:30:01','system','patient data load','4924881406','','RTD01','RTD01_000271_4924881406.gpg.xml',NULL),(18190731,'2013-04-11 10:30:02','system','patient data load','4244855106','','RX1CC','rx1cc_003121_4244855106.gpg.xml',NULL),(18190732,'2013-04-11 10:30:03','system','patient data load','4287449606','','RX1CC','rx1cc_003121_4287449606.gpg.xml',NULL),(18190733,'2013-04-11 10:30:04','system','patient data load','4464071406','','RX1CC','rx1cc_003121_4464071406.gpg.xml',NULL),(18190734,'2013-04-11 10:30:05','system','patient data load','4924592706','','RX1CC','rx1cc_003121_4924592706.gpg.xml',NULL),(18190735,'2013-04-11 10:30:06','system','patient data load','4561171606','','RX1CC','rx1cc_003121_4561171606.gpg.xml',NULL),(18190736,'2013-04-11 10:30:07','system','patient data load','4944744706','','RX1CC','rx1cc_003121_4944744706.gpg.xml',NULL),(18190737,'2013-04-11 10:30:08','system','patient data load','4971252606','','RX1CC','rx1cc_003121_4971252606.gpg.xml',NULL),(18190738,'2013-04-11 10:30:08','system','patient data load','0105393606','','SAC02','SAC02_01206_0105393606.gpg.xml',NULL),(18190739,'2013-04-11 10:30:09','system','patient data load','4984873906','','RX1CC','rx1cc_003121_4984873906.gpg.xml',NULL),(18190740,'2013-04-11 10:30:10','system','patient data load','1606853406','','SAC02','SAC02_01206_1606853406.gpg.xml',NULL),(18190741,'2013-04-11 10:30:11','system','patient data load','1803453206','','SAC02','SAC02_01206_1803453206.gpg.xml',NULL),(18190742,'2013-04-11 10:30:12','system','patient data load','2207523306','','SAC02','SAC02_01206_2207523306.gpg.xml',NULL),(18190743,'2013-04-11 10:30:12','system','patient data load','2011423406','','SAC02','SAC02_01206_2011423406.gpg.xml',NULL),(18190744,'2013-04-11 10:30:13','system','patient data load','1202760406','','SFC01','sfc01_006767_1202760406.gpg.xml',NULL),(18190745,'2013-04-11 10:30:14','system','patient data load','2708575406','','SGC04','SGC04_01206_2708575406.gpg.xml',NULL),(18190746,'2013-04-11 10:30:15','system','patient data load','2907790706','','SGC04','SGC04_01206_2907790706.gpg.xml',NULL),(18190747,'2013-04-11 10:30:17','system','patient data load','0705698106','','SGC05','SGC05_01206_0705698106.gpg.xml',NULL),(18190748,'2013-04-11 10:30:17','system','patient data load','2209605806','','SGC05','SGC05_01206_2209605806.gpg.xml',NULL),(18190749,'2013-04-11 10:30:18','system','patient data load','2411496206','','SGC05','SGC05_01206_2411496206.gpg.xml',NULL),(18190750,'2013-04-11 10:30:18','system','patient data load','1409965406','','SNC01','SNC01_000103_1409965406.gpg.xml',NULL),(18190751,'2013-04-11 10:30:27','system','patient data load','0911892206','','SNC01','SNC01_000000_0911892206.gpg.xml',NULL),(18190752,'2013-04-11 10:30:27','system','patient data load','1710872306','','SNC01','SNC01_000289_1710872306.gpg.xml',NULL),(18190753,'2013-04-11 10:30:27','system','patient data load','2808583206','','SYC01','SYC01_01206_2808583206.gpg.xml',NULL),(18190754,'2013-04-11 11:17:08','system','patient data load error','','',NULL,'RTD01_000000_4486032306.gpg.xml : \nThese are the error(s) that have been found in this xml:\nThis result has a date thats NOT WITHIN the DATE RANGE specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange START=\"2010-03-04\" STOP=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<VALUE>7.6</VALUE>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<VALUE>7</VALUE>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<VALUE>3.9</VALUE>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<VALUE>3.8</VALUE>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<VALUE>7.9</VALUE>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<VALUE>7.7</VALUE>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<VALUE>8.4</VALUE>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<VALUE>6.9</VALUE>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<VALUE>5.4</VALUE>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<VALUE>6.8</VALUE>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<VALUE>4.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<VALUE>7.5</VALUE>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<VALUE>10.2</VALUE>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<VALUE>4</VALUE>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<VALUE>3.3</VALUE>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<VALUE>3.4</VALUE>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n</test>\nThis result has a DATE thats not within the date range specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<value>7.6</value>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<value>7</value>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<value>3.9</value>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<value>3.8</value>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<value>7.9</value>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<value>7.7</value>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<value>8.4</value>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<value>6.9</value>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<value>5.4</value>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<value>6.8</value>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<value>4.2</value>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<value>7.5</value>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<value>10.2</value>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<value>4</value>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<value>3.3</value>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<value>3.4</value>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<value>4.7</value>\n</result>\n</test>\nThis result has a date thats NOT WITHIN the DATE RANGE specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange START=\"2010-03-04\" STOP=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<VALUE>7.6</VALUE>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<VALUE>7</VALUE>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<VALUE>3.9</VALUE>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<VALUE>3.8</VALUE>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<VALUE>7.9</VALUE>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<VALUE>7.7</VALUE>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<VALUE>8.4</VALUE>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<VALUE>6.9</VALUE>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<VALUE>5.4</VALUE>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<VALUE>6.8</VALUE>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<VALUE>4.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<VALUE>7.5</VALUE>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<VALUE>10.2</VALUE>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<VALUE>4</VALUE>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<VALUE>3.3</VALUE>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<VALUE>3.4</VALUE>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n</test>\nThis result has a DATE thats not within the date range specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<value>7.6</value>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<value>7</value>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<value>3.9</value>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<value>3.8</value>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<value>7.9</value>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<value>7.7</value>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<value>8.4</value>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<value>6.9</value>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<value>5.4</value>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<value>6.8</value>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<value>4.2</value>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<value>7.5</value>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<value>10.2</value>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<value>4</value>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<value>3.3</value>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<value>3.4</value>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<value>4.7</value>\n</result>\n</test>\nThis result has a date thats NOT WITHIN the DATE RANGE specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange START=\"2010-03-04\" STOP=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<VALUE>7.6</VALUE>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<VALUE>7</VALUE>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<VALUE>3.9</VALUE>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<VALUE>3.8</VALUE>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<VALUE>7.9</VALUE>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<VALUE>7.7</VALUE>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<VALUE>8.4</VALUE>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<VALUE>6.9</VALUE>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<VALUE>5.4</VALUE>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<VALUE>6.8</VALUE>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<VALUE>4.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<VALUE>7.5</VALUE>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<VALUE>10.2</VALUE>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<VALUE>4</VALUE>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<VALUE>3.3</VALUE>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<VALUE>3.4</VALUE>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n</test>\nThis result has a DATE thats not within the date range specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<value>7.6</value>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<value>7</value>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<value>3.9</value>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<value>3.8</value>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<value>7.9</value>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<value>7.7</value>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<value>8.4</value>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<value>6.9</value>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<value>5.4</value>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<value>6.8</value>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<value>4.2</value>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<value>7.5</value>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<value>10.2</value>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<value>4</value>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<value>3.3</value>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<value>3.4</value>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<value>4.7</value>\n</result>\n</test>\nThis result has a date thats NOT WITHIN the DATE RANGE specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange START=\"2010-03-04\" STOP=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<VALUE>7.6</VALUE>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<VALUE>7</VALUE>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<VALUE>3.9</VALUE>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<VALUE>3.8</VALUE>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<VALUE>7.9</VALUE>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<VALUE>7.7</VALUE>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<VALUE>6.4</VALUE>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<VALUE>5.1</VALUE>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<VALUE>4.8</VALUE>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<VALUE>6.3</VALUE>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<VALUE>8.4</VALUE>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<VALUE>6.9</VALUE>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<VALUE>5.4</VALUE>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<VALUE>6</VALUE>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<VALUE>6.8</VALUE>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<VALUE>5.6</VALUE>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<VALUE>4.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<VALUE>6.5</VALUE>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<VALUE>7.5</VALUE>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<VALUE>10.2</VALUE>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<VALUE>5.5</VALUE>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<VALUE>5.8</VALUE>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<VALUE>4.5</VALUE>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<VALUE>5.2</VALUE>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<VALUE>4.9</VALUE>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<VALUE>4</VALUE>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<VALUE>3.3</VALUE>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<VALUE>6.2</VALUE>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<VALUE>4.1</VALUE>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<VALUE>5.7</VALUE>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<VALUE>5</VALUE>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:00</datestamp>\n<VALUE>4.3</VALUE>\n</result>\n<result>\n<datestamp>2012-05-15T20:45:00</datestamp>\n<VALUE>7.2</VALUE>\n</result>\n<result>\n<datestamp>2012-06-08T02:18:00</datestamp>\n<VALUE>6.1</VALUE>\n</result>\n<result>\n<datestamp>2012-07-05T20:06:00</datestamp>\n<VALUE>6.7</VALUE>\n</result>\n<result>\n<datestamp>2012-08-14T19:00:00</datestamp>\n<VALUE>5.3</VALUE>\n</result>\n<result>\n<datestamp>2012-09-06T19:30:00</datestamp>\n<VALUE>4.4</VALUE>\n</result>\n<result>\n<datestamp>2012-10-04T21:00:00</datestamp>\n<VALUE>3.4</VALUE>\n</result>\n<result>\n<datestamp>2012-11-08T21:47:00</datestamp>\n<VALUE>5.9</VALUE>\n</result>\n<result>\n<datestamp>2012-12-10T19:10:00</datestamp>\n<VALUE>4.6</VALUE>\n</result>\n<result>\n<datestamp>2013-01-10T23:26:00</datestamp>\n<VALUE>4.7</VALUE>\n</result>\n</test>\nThis result has a DATE thats not within the date range specified:\n<test>\n<testname>Glucose, serum</testname>\n<testcode>glucose</testcode>\n<units/>\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\n<result>\n<datestamp>2010-03-04T00:00:00</datestamp>\n<value>7.6</value>\n</result>\n<result>\n<datestamp>2010-02-04T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2010-01-07T00:00:00</datestamp>\n<value>7</value>\n</result>\n<result>\n<datestamp>2009-12-03T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-11-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2009-10-08T00:00:00</datestamp>\n<value>3.9</value>\n</result>\n<result>\n<datestamp>2009-09-10T00:00:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2009-07-07T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2009-08-06T00:00:00</datestamp>\n<value>3.8</value>\n</result>\n<result>\n<datestamp>2009-06-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2009-05-06T00:00:00</datestamp>\n<value>7.9</value>\n</result>\n<result>\n<datestamp>2009-03-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2009-02-04T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2009-01-06T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2008-10-08T00:00:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2008-11-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2008-12-03T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2008-09-09T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2008-08-06T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-07-11T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2008-06-04T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2008-04-09T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-05-07T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-02-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2008-03-05T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2008-01-09T00:00:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2007-11-06T00:00:00</datestamp>\n<value>7.7</value>\n</result>\n<result>\n<datestamp>2007-10-09T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2007-12-03T00:00:00</datestamp>\n<value>6.4</value>\n</result>\n<result>\n<datestamp>2007-12-04T00:00:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2007-09-04T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2007-08-08T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-07-04T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-06-06T00:00:00</datestamp>\n<value>5.1</value>\n</result>\n<result>\n<datestamp>2007-04-13T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2007-05-09T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2007-02-15T00:00:00</datestamp>\n<value>4.8</value>\n</result>\n<result>\n<datestamp>2007-03-07T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-12-05T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2006-11-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2007-01-10T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2006-10-04T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2006-09-05T00:00:00</datestamp>\n<value>6.7</value>\n</result>\n<result>\n<datestamp>2006-08-16T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-07-05T00:00:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2006-06-06T00:00:00</datestamp>\n<value>6.3</value>\n</result>\n<result>\n<datestamp>2006-04-05T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2006-03-07T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2006-02-07T00:00:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2006-01-10T00:00:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2005-12-06T00:00:00</datestamp>\n<value>8.4</value>\n</result>\n<result>\n<datestamp>2005-11-08T00:00:00</datestamp>\n<value>6.9</value>\n</result>\n<result>\n<datestamp>2005-10-14T00:00:00</datestamp>\n<value>5.4</value>\n</result>\n<result>\n<datestamp>2005-09-16T00:00:00</datestamp>\n<value>6</value>\n</result>\n<result>\n<datestamp>2005-08-02T00:00:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2005-07-06T00:00:00</datestamp>\n<value>6.8</value>\n</result>\n<result>\n<datestamp>2005-05-10T00:00:00</datestamp>\n<value>4.3</value>\n</result>\n<result>\n<datestamp>2005-06-07T00:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2005-03-08T00:00:00</datestamp>\n<value>5.9</value>\n</result>\n<result>\n<datestamp>2005-04-11T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2005-04-13T00:00:00</datestamp>\n<value>5.6</value>\n</result>\n<result>\n<datestamp>2005-02-08T00:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2005-01-11T00:00:00</datestamp>\n<value>4.2</value>\n</result>\n<result>\n<datestamp>2004-12-10T00:00:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2004-12-07T00:00:00</datestamp>\n<value>6.5</value>\n</result>\n<result>\n<datestamp>2004-08-29T00:00:00</datestamp>\n<value>7.5</value>\n</result>\n<result>\n<datestamp>2004-07-22T00:00:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2004-09-14T00:00:00</datestamp>\n<value>10.2</value>\n</result>\n<result>\n<datestamp>2010-04-08T00:00:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-05-21T00:00:00</datestamp>\n<value>4.6</value>\n</result>\n<result>\n<datestamp>2010-06-10T21:00:00</datestamp>\n<value>5.5</value>\n</result>\n<result>\n<datestamp>2010-08-05T21:00:00</datestamp>\n<value>5.8</value>\n</result>\n<result>\n<datestamp>2010-09-03T03:40:00</datestamp>\n<value>4.5</value>\n</result>\n<result>\n<datestamp>2010-10-07T20:40:00</datestamp>\n<value>5.2</value>\n</result>\n<result>\n<datestamp>2010-12-14T20:54:00</datestamp>\n<value>4.9</value>\n</result>\n<result>\n<datestamp>2011-01-13T22:55:00</datestamp>\n<value>4</value>\n</result>\n<result>\n<datestamp>2011-02-10T19:30:00</datestamp>\n<value>5.3</value>\n</result>\n<result>\n<datestamp>2011-03-09T07:40:00</datestamp>\n<value>3.3</value>\n</result>\n<result>\n<datestamp>2011-04-06T14:20:00</datestamp>\n<value>6.2</value>\n</result>\n<result>\n<datestamp>2011-05-04T07:28:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-06-13T07:30:00</datestamp>\n<value>4.4</value>\n</result>\n<result>\n<datestamp>2011-07-06T07:20:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2011-08-03T07:45:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2011-09-08T20:40:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-10-12T13:15:00</datestamp>\n<value>4.1</value>\n</result>\n<result>\n<datestamp>2011-11-10T23:25:00</datestamp>\n<value>5.7</value>\n</result>\n<result>\n<datestamp>2011-12-08T21:01:00</datestamp>\n<value>7.2</value>\n</result>\n<result>\n<datestamp>2012-01-06T00:07:00</datestamp>\n<value>4.7</value>\n</result>\n<result>\n<datestamp>2012-02-08T20:40:00</datestamp>\n<value>5</value>\n</result>\n<result>\n<datestamp>2012-03-08T22:18:00</datestamp>\n<value>6.1</value>\n</result>\n<result>\n<datestamp>2012-04-17T20:45:',NULL),(18190755,'2013-04-11 11:18:28','system','patient data fail','4486032306','','RTD01','RTD01_000000_4486032306.gpg.xml : \r\nThese are the error(s) that have been found in this xml:\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-04-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-03-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-02-07T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-01-10T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-12-06T00:00:00</datestamp>\r\n<value>8.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-11-08T00:00:00</datestamp>\r\n<value>6.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-10-14T00:00:00</datestamp>\r\n<value>5.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-09-16T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-08-02T00:00:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-07-06T00:00:00</datestamp>\r\n<value>6.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-05-10T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-06-07T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-03-08T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-11T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-04-13T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-02-08T00:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2005-01-11T00:00:00</datestamp>\r\n<value>4.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-10T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-12-07T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-08-29T00:00:00</datestamp>\r\n<value>7.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-07-22T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2004-09-14T00:00:00</datestamp>\r\n<value>10.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-04-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-05-21T00:00:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-06-10T21:00:00</datestamp>\r\n<value>5.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-08-05T21:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-09-03T03:40:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-10-07T20:40:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-12-14T20:54:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-01-13T22:55:00</datestamp>\r\n<value>4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-02-10T19:30:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-03-09T07:40:00</datestamp>\r\n<value>3.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-04-06T14:20:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-05-04T07:28:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-06-13T07:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-07-06T07:20:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-08-03T07:45:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-09-08T20:40:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-10-12T13:15:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-11-10T23:25:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2011-12-08T21:01:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-01-06T00:07:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-02-08T20:40:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-03-08T22:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-04-17T20:45:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-05-15T20:45:00</datestamp>\r\n<value>7.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-06-08T02:18:00</datestamp>\r\n<value>6.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-07-05T20:06:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-08-14T19:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-09-06T19:30:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-10-04T21:00:00</datestamp>\r\n<value>3.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-11-08T21:47:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2012-12-10T19:10:00</datestamp>\r\n<value>4.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2013-01-10T23:26:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n</test>\r\n\r\n\r\n\r\nThis result has a date that\'s not within the date range specified:\r\n<test>\r\n<testname>Glucose, serum</testname>\r\n<testcode>glucose</testcode>\r\n<units/>\r\n<daterange start=\"2010-03-04\" stop=\"2013-04-09\"/>\r\n<result>\r\n<datestamp>2010-03-04T00:00:00</datestamp>\r\n<value>7.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-02-04T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2010-01-07T00:00:00</datestamp>\r\n<value>7</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-12-03T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-11-05T00:00:00</datestamp>\r\n<value>5.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-10-08T00:00:00</datestamp>\r\n<value>3.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-09-10T00:00:00</datestamp>\r\n<value>4.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-07-07T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-08-06T00:00:00</datestamp>\r\n<value>3.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-06-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-05-06T00:00:00</datestamp>\r\n<value>7.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-03-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-02-04T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2009-01-06T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-10-08T00:00:00</datestamp>\r\n<value>5.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-11-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-12-03T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-09-09T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-08-06T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-07-11T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-06-04T00:00:00</datestamp>\r\n<value>5.6</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-04-09T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-05-07T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-02-08T00:00:00</datestamp>\r\n<value>4.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-03-05T00:00:00</datestamp>\r\n<value>6.2</value>\r\n</result>\r\n<result>\r\n<datestamp>2008-01-09T00:00:00</datestamp>\r\n<value>4.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-11-06T00:00:00</datestamp>\r\n<value>7.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-10-09T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-03T00:00:00</datestamp>\r\n<value>6.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-12-04T00:00:00</datestamp>\r\n<value>5.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-09-04T00:00:00</datestamp>\r\n<value>6.5</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-08-08T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-07-04T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-06-06T00:00:00</datestamp>\r\n<value>5.1</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-04-13T00:00:00</datestamp>\r\n<value>5.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-05-09T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-02-15T00:00:00</datestamp>\r\n<value>4.8</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-03-07T00:00:00</datestamp>\r\n<value>6.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-12-05T00:00:00</datestamp>\r\n<value>4.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-11-07T00:00:00</datestamp>\r\n<value>6</value>\r\n</result>\r\n<result>\r\n<datestamp>2007-01-10T00:00:00</datestamp>\r\n<value>5</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-10-04T00:00:00</datestamp>\r\n<value>5.3</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-09-05T00:00:00</datestamp>\r\n<value>6.7</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-08-16T00:00:00</datestamp>\r\n<value>4.9</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-07-05T00:00:00</datestamp>\r\n<value>4.4</value>\r\n</result>\r\n<result>\r\n<datestamp>2006-06-06T00:00:00</d',NULL),(18190756,'2013-04-11 12:54:55','superadmin','logon','','superadmin','','',1),(18190757,'2013-04-11 12:55:59','superadmin','patient add','9999999999','Testing','RENALA','',1),(18190758,'2013-04-11 13:43:48','superadmin','logon','','superadmin','','',1),(18190759,'2013-04-11 13:44:52','superadmin','admin add','','deleteme','RENALA','',1),(18190760,'2013-04-11 14:06:49','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190761,'2013-04-11 14:10:59','renalaadmin','patient add','9889999999','test ME','RENALA','',1),(18190762,'2013-04-11 14:11:54','superadmin','logon','','superadmin','','',1),(18190763,'2013-04-11 14:12:40','superadmin','logon','','superadmin','','',1),(18190764,'2013-04-14 15:38:41','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190765,'2013-04-14 15:39:33','renalaadmin','patient add','2804562611','nturner22','RENALA','',1),(18190766,'2013-04-15 10:26:28','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190767,'2013-04-15 10:30:13','patienta','logon','1111111111','patienta','RENALA','',1),(18190768,'2013-04-15 10:35:01','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190769,'2013-04-15 11:41:22','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190770,'2013-04-15 11:43:27','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190771,'2013-04-15 12:04:14','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190772,'2013-04-15 12:04:57','renalaadmin','patient add','7551045694','test10','RENALA','',1),(18190773,'2013-04-15 12:06:55','test10','logon','7551045694','test10','RENALA','',1),(18190774,'2013-04-15 12:14:04','test10','logon','7551045694','test10','RENALA','',1),(18190775,'2013-04-15 14:33:17','superadmin','logon','','superadmin','','',1),(18190776,'2013-04-15 17:32:17','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190777,'2013-04-15 17:35:18','superadmin','logon','','superadmin','','',1),(18190778,'2013-04-15 17:36:03','neilconsultant','logon','','neilconsultant','RENALA','',1),(18190779,'2013-04-15 17:36:48','patienta','logon','1111111111','patienta','RENALA','',1),(18190780,'2013-04-15 17:38:06','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190781,'2013-04-15 17:38:45','patienta','logon','1111111111','patienta','RENALA','',1),(18190782,'2013-04-15 17:43:35','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190783,'2013-04-16 13:07:39','test10','logon','7551045694','test10','RENALA','',1),(18190784,'2013-04-16 13:18:29','test10','logon','7551045694','test10','RENALA','',1),(18190785,'2013-04-16 13:25:16','test10','logon','7551045694','test10','RENALA','',1),(18190786,'2013-04-16 13:36:32','test10','logon','7551045694','test10','RENALA','',1),(18190787,'2013-04-16 13:43:59','test10','logon','7551045694','test10','RENALA','',1),(18190788,'2013-04-16 13:48:54','test10','logon','7551045694','test10','RENALA','',1),(18190789,'2013-04-16 14:05:45','test10','logon','7551045694','test10','RENALA','',1),(18190790,'2013-04-16 14:07:09','superadmin','logon','','superadmin','','',1),(18190791,'2013-04-16 14:09:12','superadmin','logon','','superadmin','','',1),(18190792,'2013-04-16 14:12:35','superadmin','patient delete','','deleteme','','',1),(18190793,'2013-04-16 14:18:40','superadmin','admin add','','andel1','RENALA','',1),(18190794,'2013-04-16 14:21:38','superadmin','patient delete','','andel1','','',1),(18190795,'2013-04-16 14:22:09','superadmin','admin add','','undel2','RENALA','',1),(18190796,'2013-04-16 14:22:27','superadmin','patient delete','','undel2','RENALA','',1),(18190797,'2013-04-16 14:23:19','superadmin','admin add','','ewfwfw','RENALA','',1),(18190798,'2013-04-16 14:23:27','superadmin','password reset','','ewfwfw','RENALA','',1),(18190799,'2013-04-16 14:26:56','superadmin','patient delete','','ewfwfw','RENALA','',1),(18190800,'2013-04-16 14:28:27','superadmin','admin add','','wegfretyjtyjyuk','RENALA','',1),(18190801,'2013-04-16 14:28:43','superadmin','patient delete','','wegfretyjtyjyuk','RENALA','',1),(18190802,'2013-04-16 14:34:59','superadmin','admin add','','ergeg','RENALA','',1),(18190803,'2013-04-16 14:46:01','superadmin','patient delete','4444444444','asdasd','RENALA','',1),(18190804,'2013-04-16 14:46:22','superadmin','password reset','','sdfsadklf','RENALA','',1),(18190805,'2013-04-16 14:51:57','superadmin','logon','','superadmin','','',1),(18190806,'2013-04-16 15:01:41','superadmin','logon','','superadmin','','',1),(18190807,'2013-04-16 15:02:54','superadmin','logon','','superadmin','','',1),(18190808,'2013-04-16 15:03:23','superadmin','logon','','superadmin','','',1),(18190809,'2013-04-16 15:03:52','superadmin','logon','','superadmin','','',1),(18190810,'2013-04-16 15:05:48','superadmin','patient delete','6424970037','sdfsadklf','RENALA','',1),(18190811,'2013-04-17 11:53:14','superadmin','logon','','superadmin','','',1),(18190812,'2013-04-17 11:54:35','superadmin','password reset','','patientc','RENALA','',1),(18190813,'2013-04-17 12:04:24','test10','logon','7551045694','test10','RENALA','',1),(18190814,'2013-04-17 12:05:26','superadmin','admin add','','Seth_Lakeman','RXGIG67','',1),(18190815,'2013-04-17 12:06:43','Seth_Lakeman','logon','','Seth_Lakeman','RXGIG67','',1),(18190816,'2013-04-17 12:08:01','Seth_Lakeman','password change','','Seth_Lakeman','RXGIG67','',1),(18190817,'2013-04-17 12:10:38','renalaadmin','logon','','renalaadmin','RENALA','',1),(18190818,'2013-04-17 12:10:57','renalaadmin','patient delete','','ergeg','RENALA','',1),(18190819,'2013-04-17 12:12:03','test10','logon','7551045694','test10','RENALA','',1),(18190820,'2013-04-17 12:32:15','Seth_Lakeman','patient add','9999999484','LakeS','RXGIG67','',1),(18190821,'2013-04-18 17:06:40','superadmin','logon','','superadmin','','',1),(18190822,'2013-04-18 17:08:04','superadmin','admin add','','paulchenery','RENALA','',1),(18190823,'2013-04-18 17:21:02','superadmin','patient delete','','paulchenery','RENALA','',1),(18190824,'2013-04-19 11:58:56','superadmin','logon','','superadmin','','',1),(18190825,'2013-04-19 12:00:00','superadmin','admin add','','andrew1','RENALA','',1),(18190826,'2013-04-19 12:03:25','superadmin','patient delete','','andrew1','RENALA','',1),(18190827,'2013-04-19 12:18:08','superadmin','admin add','','radarprof','RENALA','',1),(18190828,'2013-04-19 12:51:33','superadmin','logon','','superadmin','','',1),(18190829,'2013-04-19 12:53:52','superadmin','admin add','','ben1','RENALA','',1),(18190830,'2013-04-19 12:55:50','superadmin','patient delete','','ben1','RENALA','',1),(18190831,'2013-04-22 13:35:58','patienta','logon','1111111111','patienta','RENALA','',1),(18190832,'2013-04-22 14:08:03','test10','logon','7551045694','test10','RENALA','',1),(18190833,'2013-04-24 10:39:56','superadmin','logon','','superadmin','','',1),(18190834,'2013-04-24 10:41:39','patienta','logon','1111111111','patienta','RENALA','',1),(18190835,'2013-04-24 10:44:37','patienta','logon','1111111111','patienta','RENALA','',1),(18190836,'2013-04-24 15:11:28','superadmin','logon','','superadmin','','',1),(18190837,'2013-04-24 15:16:47','superadmin','patient add','9999999476','Endgame','RXGIG67','',1),(18190838,'2013-04-24 15:22:02','Endgame','logon','9999999476','Endgame','RXGIG67','',1),(18190839,'2013-04-24 15:22:57','Endgame','password change','','Endgame','RXGIG67','',1),(18190840,'2013-04-25 15:49:00','patienta','logon','1111111111','patienta','RENALA','',1),(18190841,'2013-04-25 15:51:08','superadmin','logon','','superadmin','','',1),(18190842,'2013-04-25 15:52:16','superadmin','logon','','superadmin','','',1),(18190843,'2013-04-26 08:01:52','superadmin','logon','','superadmin','','',1),(18190844,'2013-04-26 08:11:14','superadmin','logon','','superadmin','','',1),(18190845,'2013-04-26 10:38:03','superadmin','logon','','superadmin','','',1),(18190846,'2013-04-26 10:41:04','superadmin','admin add','','radarprof','RENALA','',1),(18190847,'2013-04-26 10:44:31','radarprof','logon','','radarprof','RENALA','',1),(18190848,'2013-04-26 10:45:03','radarprof','patient add','6810341560','patient1','RENALA','',1),(18190849,'2013-04-26 10:47:10','radarprof','patient add','7531599198','patient2','RENALA','',1),(18190850,'2013-04-26 10:49:09','superadmin','logon','','superadmin','','',1),(18190851,'2013-04-26 10:49:22','superadmin','patient delete','','radarprof','RENALA','',1),(18190852,'2013-04-26 10:54:13','superadmin','patient add','1111111111','patientb','RENALB','',1),(18190853,'2013-04-26 10:55:07','superadmin','patient add','1111111111','patientb','RENALA','',1),(18190854,'2013-04-26 10:57:42','patientb','logon','1111111111','patientb','RENALB','',1),(18190855,'2013-04-26 10:59:07','patientb','logon','1111111111','patientb','RENALB','',1),(18190856,'2013-05-01 09:41:28','patient1','logon','6810341560','patient1','RENALA','',1),(18190857,'2013-05-01 10:09:01','superadmin','logon','','superadmin','','',1),(18190858,'2013-05-01 10:09:25','superadmin','admin add','','unitaadmin','RENALA','',1),(18190859,'2013-05-01 13:54:34','patientb','logon','1111111111','patientb','RENALB','',1),(18190860,'2013-05-03 10:48:48','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190861,'2013-05-03 10:51:24','patientb','logon','1111111111','patientb','RENALB','',1),(18190862,'2013-05-03 11:06:58','patient1','logon','6810341560','patient1','RENALA','',1),(18190863,'2013-05-03 11:08:16','patientb','logon','1111111111','patientb','RENALB','',1),(18190864,'2013-05-07 09:38:22','superadmin','logon','','superadmin','','',1),(18190865,'2013-05-07 10:41:36','superadmin','logon','','superadmin','','',1),(18190866,'2013-05-07 10:46:07','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190867,'2013-05-07 15:36:17','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190868,'2013-05-07 15:37:45','patientb','logon','1111111111','patientb','RENALB','',1),(18190869,'2013-05-07 15:38:12','patientb','logon','1111111111','patientb','RENALB','',1),(18190870,'2013-05-07 15:39:08','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190871,'2013-05-07 15:45:21','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190872,'2013-05-07 15:50:11','unitaadmin','logon','','unitaadmin','RENALA','',1),(18190873,'2013-05-07 17:49:01','unitaadmin','logon','','unitaadmin','RENALA','',1);
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
) ENGINE=MyISAM AUTO_INCREMENT=98560697 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (98560259,'3204969541','2020','2012-07-25 00:00:10','Warfarin sodium 1mg tablet','1 mg  variable as advised  Oral   ','0000-00-00 00:00:00'),(98560258,'3204969541','2020','2012-02-20 00:00:10','Vitamins capsule BPC','1 caps  daily  Oral   ','0000-00-00 00:00:00'),(98560257,'3204969541','2020','2010-09-14 00:00:10','Tiotropium 18micrograms capsule+inhaler device','18 micrograms  in morning  Oral   ','0000-00-00 00:00:00'),(98560254,'3204969541','2020','2012-01-30 00:00:10','Spiriva 18micrograms inhalation capsule','18 micrograms  daily  Inhal   ','0000-00-00 00:00:00'),(98560255,'3204969541','2020','2012-08-07 00:00:10','Symbicort 200/6 Turbohaler','200 puff  two times daily  Ears   ','0000-00-00 00:00:00'),(98560256,'3204969541','2020','2012-01-31 00:00:10','Tinzaparin sodium 2500iu/0.25mL prefilled syringe','2500 units  variable as advised  IV   On HD','0000-00-00 00:00:00'),(98560253,'3204969541','2020','2010-09-15 00:00:10','Salbutamol 100micrograms CFC-free inhaler','2 puff  variable as advised  Inhal   ','0000-00-00 00:00:00'),(98560252,'3204969541','2020','2012-07-07 00:00:10','Paracetamol 500mg capsule','1 g  variable as advised  Oral   PRN','0000-00-00 00:00:00'),(98560251,'3204969541','2020','2012-01-30 00:00:10','Omacor 1g capsule','1 g  daily  Oral   ','0000-00-00 00:00:00'),(98560249,'3204969541','2020','2012-07-25 00:00:10','Nebivolol 2.5mg tablet','2.5 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560250,'3204969541','2020','2012-01-30 00:00:10','Nystatin 100,000units/mL oral suspension','100000 units  four times daily  Oral   ','0000-00-00 00:00:00'),(98560248,'3204969541','2020','2010-09-14 00:00:10','Montelukast 10mg tablet','10 mg  at night  Oral   ','0000-00-00 00:00:00'),(98560246,'3204969541','2020','2012-01-13 00:00:10','Heparin sodium 1000iu/1mL injection','1000 units  three times weekly  IV   On HD','0000-00-00 00:00:00'),(98560247,'3204969541','2020','2012-07-25 00:00:10','Ipratropium bromide 500micrograms/2mL nebuliser solution','500 micrograms  four times daily  Inhal   ','0000-00-00 00:00:00'),(98560245,'3204969541','2020','2012-07-25 00:00:10','Furosemide 40mg tablet','40 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560244,'3204969541','2020','2012-11-13 00:00:10','Darbepoetin alfa 40micrograms/0.4mL prefilled syringe','40 micrograms  weekly  IV   On HD','0000-00-00 00:00:00'),(98560243,'3204969541','2020','2013-01-15 00:00:10','Cyclizine hydrochloride 50mg tablet','50 mg  three times daily  Oral   PRN','0000-00-00 00:00:00'),(98560278,'4087878406','RAE05','2011-09-04 00:00:10','Atorvastatin tabs 10mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560364,'6260711506','RGU01','2012-11-21 00:00:10','Darbepoetin alfa','40 mcg Sub cut Every 2 weeks','0000-00-00 00:00:00'),(98560276,'4087878406','RAE05','2011-09-04 00:00:10','Aspirin soluble tabs 75mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560277,'4087878406','RAE05','2011-09-04 00:00:10','Folic Acid tabs 5mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560274,'4087878406','RAE05','2011-09-04 00:00:10','Vitamin B tabs Compound Strong','2 1x/day Oral','0000-00-00 00:00:00'),(98560275,'4087878406','RAE05','2011-09-04 00:00:10','Calcichew tabs 1250mg','1 3x/day Oral','0000-00-00 00:00:00'),(98560273,'4087878406','RAE05','2011-09-04 00:00:10','Insulin',NULL,'0000-00-00 00:00:00'),(98560271,'4087878406','RAE05','2011-11-07 00:00:10','Humalog KWIK Pens 5 x 3ml','asd 3x/day S.C.','0000-00-00 00:00:00'),(98560272,'4087878406','RAE05','2011-09-07 00:00:10','Lantus Solostar','34 u 1x/day S.C.','0000-00-00 00:00:00'),(98560269,'4087878406','RAE05','2012-03-22 00:00:10','Pregabalin caps 100mg','2 2x/day Oral','0000-00-00 00:00:00'),(98560270,'4087878406','RAE05','2011-12-23 00:00:10','Quinine Sulphate tabs 200mg','1 Nocte Oral','0000-00-00 00:00:00'),(98560268,'4087878406','RAE05','2012-05-31 00:00:10','Emla Cream','topic 3xweek Tpical','0000-00-00 00:00:00'),(98560267,'4087878406','RAE05','2012-11-09 00:00:10','Iron Saccharate Injection 100mg','1 3xweek I.V.','0000-00-00 00:00:00'),(98560266,'4087878406','RAE05','2013-01-09 00:00:10','Loperamide caps 2mg','2 PRN Oral','0000-00-00 00:00:00'),(98560265,'4087878406','RAE05','2013-01-09 00:00:10','Loperamide caps 2mg','1 PRN Oral','0000-00-00 00:00:00'),(98560264,'4087878406','RAE05','2013-02-08 00:00:10','Aranesp (mcg)','60 Weekly I.V.','0000-00-00 00:00:00'),(98560292,'6067495406','45020','2009-04-22 00:00:10','Paracetamol 500mg / dihydrocodeine tartrate 7.46mg effervescent tablet','1000 mg  variable as advised  Oral   PRN','0000-00-00 00:00:00'),(98560293,'6067495406','45020','2011-06-14 00:00:10','Pregabalin 150mg capsule','150 mg  two times daily  Oral   ','0000-00-00 00:00:00'),(98560294,'6067495406','45020','2012-09-20 00:00:10','Ramipril 10mg capsule','10 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560295,'6067495406','45020','2013-02-14 00:00:10','Venofer 100mg/5mL intravenous solution','100 mg  weekly  IV   On HD','0000-00-00 00:00:00'),(98560291,'6067495406','45020','2009-01-15 00:00:10','Omeprazole 20mg capsule','20 mg  two times daily  Oral   ','0000-00-00 00:00:00'),(98560289,'6067495406','45020','2012-08-03 00:00:10','Lanthanum (as carbonate) 1g chewable tablet','1 g  three times daily  Oral   ','0000-00-00 00:00:00'),(98560290,'6067495406','45020','2013-01-02 00:00:10','NeoRecormon 2000iu prefilled syringe','4000 units  three times weekly  IV   ','0000-00-00 00:00:00'),(98560288,'6067495406','45020','2012-07-02 00:00:10','Heparin sodium 1000iu/1mL injection','1000 units  4 times weekly  IV   On HD','0000-00-00 00:00:00'),(98560287,'6067495406','45020','2011-10-28 00:00:10','Cyclizine hydrochloride 50mg tablet','50 mg  three times daily  Oral   PRN','0000-00-00 00:00:00'),(98560286,'6067495406','45020','2010-11-30 00:00:10','Cetirizine dihydrochloride 10mg tablet','10 mg  daily  Oral   PRN','0000-00-00 00:00:00'),(98560284,'6067495406','45020','2012-02-01 00:00:10','BuTrans 5micrograms/hour patch','20 micrograms  variable as advised  Top\'l   ','0000-00-00 00:00:00'),(98560285,'6067495406','45020','2013-04-09 00:00:10','Calcium gluconate 10% injection','10 %  once  IV   On HDPRN','0000-00-00 00:00:00'),(98560283,'6067495406','45020','2007-04-17 00:00:10','Atorvastatin 80mg tablet','80 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560281,'6067495406','45020','2012-03-23 00:00:10','Amlodipine 10mg tablet','10 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560307,'4482941506','RCJAT','2003-06-06 00:00:10','Azathioprine','100 mgs. daily','0000-00-00 00:00:00'),(98560305,'4482941506','RCJAT','2007-04-02 00:00:10','Atorvastatin','20 mgs nocte','0000-00-00 00:00:00'),(98560306,'4482941506','RCJAT','2003-06-06 00:00:10','Lansoprazole','30 mgs. daily','0000-00-00 00:00:00'),(98560304,'4482941506','RCJAT','2007-04-02 00:00:10','Perindopril','4 mgs. daily','0000-00-00 00:00:00'),(98560302,'4482941506','RCJAT','2010-03-29 00:00:10','One Alpha Calcidol','1 mcgm. daily','0000-00-00 00:00:00'),(98560303,'4482941506','RCJAT','2010-01-11 00:00:10','Ciclosporin Neoral','100 mgs. b.d.','0000-00-00 00:00:00'),(98560301,'4482941506','RCJAT','2010-07-12 00:00:10','Loperamide','2 mgs. prn','0000-00-00 00:00:00'),(98560300,'4482941506','RCJAT','2011-05-16 00:00:10','Cosmofer','1gm iv','0000-00-00 00:00:00'),(98560299,'4482941506','RCJAT','2011-10-12 00:00:10','Aranesp','10mcg weekly','0000-00-00 00:00:00'),(98560312,'4647463206','RAQ01','2012-04-04 00:00:10','dalteparin','2500 IU 3X wkly','0000-00-00 00:00:00'),(98560313,'4647463206','RAQ01','2012-04-04 00:00:10','Eprex','7000 IU 2X wkly','0000-00-00 00:00:00'),(98560314,'4647463206','RAQ01','2012-04-04 00:00:10','Hydroxocobalamin','1 mg 6 mthly','0000-00-00 00:00:00'),(98560324,'4647463206','RAQ01','2013-03-30 00:00:10','Senna','2 Tabs Nocte','0000-00-00 00:00:00'),(98560323,'4647463206','RAQ01','2013-03-30 00:00:10','Lactulose','10 ml 12Hrly','0000-00-00 00:00:00'),(98560322,'4647463206','RAQ01','2013-03-20 00:00:10','Ranitidine','150 mg 12Hrly','0000-00-00 00:00:00'),(98560321,'4647463206','RAQ01','2013-03-13 00:00:10','Temazepam','10 mg Nocte','0000-00-00 00:00:00'),(98560320,'4647463206','RAQ01','2013-03-04 00:00:10','Ondansetron','4 mg 6 Hrly','0000-00-00 00:00:00'),(98560318,'4647463206','RAQ01','2013-03-03 00:00:10','Clexane','20 mg Alt d1','0000-00-00 00:00:00'),(98560319,'4647463206','RAQ01','2013-03-04 00:00:10','Cyclizine','50 mg 8 Hrly','0000-00-00 00:00:00'),(98560316,'4647463206','RAQ01','2012-06-28 00:00:10','EMLA CREAM','1 * 3/wkly','0000-00-00 00:00:00'),(98560317,'4647463206','RAQ01','2012-08-21 00:00:10','iron saccharate (Ven','100 mg weekly','0000-00-00 00:00:00'),(98560337,'6343053506','RCJAT','2010-08-02 00:00:10','Candesartan','4 mgs 1daily','0000-00-00 00:00:00'),(98560335,'6343053506','RCJAT','2010-08-02 00:00:10','Maxepa','5 b.d.','0000-00-00 00:00:00'),(98560336,'6343053506','RCJAT','2010-08-02 00:00:10','Creon','1 - 2 t.d.s.','0000-00-00 00:00:00'),(98560333,'6343053506','RCJAT','2010-08-02 00:00:10','Senna','7.5mg 2at night','0000-00-00 00:00:00'),(98560334,'6343053506','RCJAT','2010-08-02 00:00:10','Insulin',NULL,'0000-00-00 00:00:00'),(98560332,'6343053506','RCJAT','2010-08-02 00:00:10','Aspirin','150 mgs. daily','0000-00-00 00:00:00'),(98560330,'6343053506','RCJAT','2012-09-14 00:00:10','Aranesp','20mcg weekly','0000-00-00 00:00:00'),(98560331,'6343053506','RCJAT','2011-09-15 00:00:10','Folic Acid','5 mgs. daily','0000-00-00 00:00:00'),(98560327,'6343053506','RCJAT','2012-12-08 00:00:10','Phosex','2g tds','0000-00-00 00:00:00'),(98560328,'6343053506','RCJAT','2012-12-08 00:00:10','Mimpara','60mg','0000-00-00 00:00:00'),(98560338,'4742567706','REF12','2011-11-21 00:00:10','DIALYVIT','2 Tab Postdial','0000-00-00 00:00:00'),(98560339,'4742567706','REF12','2011-11-22 00:00:10','FRAGMIN','2500 IU Predial','0000-00-00 00:00:00'),(98560340,'4742567706','REF12','2011-12-01 00:00:10','Sodium Chloride 0.9%','300 ml on dialysis','0000-00-00 00:00:00'),(98560344,'6048066406','RGU01','2011-06-13 00:00:10','Azathioprine','50 mg PO OD','0000-00-00 00:00:00'),(98560343,'6048066406','RGU01','2010-05-05 00:00:10','Irbesartan','75 mg PO OD','0000-00-00 00:00:00'),(98560342,'6048066406','RGU01','2010-01-19 00:00:10','Metformin','500 mg PO BD','0000-00-00 00:00:00'),(98560341,'6048066406','RGU01','2009-07-08 00:00:10','Prednisolone','5 mg PO OD','0000-00-00 00:00:00'),(98560352,'4468928506','RGU01','2012-10-26 00:00:10','Calcium carbonate','1 tablet PO BD','0000-00-00 00:00:00'),(98560350,'4468928506','RGU01','2012-09-20 00:00:10','Ketovite tablet','2 tablet PO OD','0000-00-00 00:00:00'),(98560349,'4468928506','RGU01','2012-09-20 00:00:10','Folic acid 5mg tablet','5 mg PO OD','0000-00-00 00:00:00'),(98560347,'4468928506','RGU01','2012-05-25 00:00:10','Paracetamol','1 gram PO QDS (PRN)','0000-00-00 00:00:00'),(98560345,'4468928506','RGU01','2012-04-10 00:00:10','Darbepoetin alfa','50 mcg Sub cut Every 3 wks','0000-00-00 00:00:00'),(98560348,'4468928506','RGU01','2012-05-25 00:00:10','Alfacalcidol','250 nanogram PO OD','0000-00-00 00:00:00'),(98560356,'6260711506','RGU01','2010-09-14 00:00:10','NovoRapid 100units/mL injection vial','16 units Sub cut With Meals','0000-00-00 00:00:00'),(98560363,'6260711506','RGU01','2011-12-12 00:00:10','Senna','2 tablet PO BD','0000-00-00 00:00:00'),(98560362,'6260711506','RGU01','2011-12-12 00:00:10','Atorvastatin','20 mg PO Nocte','0000-00-00 00:00:00'),(98560361,'6260711506','RGU01','2011-12-08 00:00:10','Alfacalcidol','500 nanogram PO OD','0000-00-00 00:00:00'),(98560360,'6260711506','RGU01','2011-12-08 00:00:10','Calcium carbonate','2 tablet PO With Meals','0000-00-00 00:00:00'),(98560359,'6260711506','RGU01','2011-05-27 00:00:10','Bisoprolol','2.5 mg PO BD','0000-00-00 00:00:00'),(98560358,'6260711506','RGU01','2011-05-27 00:00:10','Frusemide','250 mg PO OD','0000-00-00 00:00:00'),(98560355,'6260711506','RGU01','2010-09-14 00:00:10','Insulin Glargine (Lantus)','22 units Sub cut Nocte','0000-00-00 00:00:00'),(98560400,'6320259906','RJZ','2012-04-11 00:00:10','Co-trimoxazole  tablets','480mg','0000-00-00 00:00:00'),(98560373,'4564232606','RGU01','2013-02-04 00:00:10','Sodium bicarbonate','2.4 gram PO BD','0000-00-00 00:00:00'),(98560371,'4564232606','RGU01','2011-02-04 00:00:10','Ferrous sulphate','200 mg PO BD','0000-00-00 00:00:00'),(98560372,'4564232606','RGU01','2013-02-04 00:00:10','Ezetimibe','10 mg PO OD','0000-00-00 00:00:00'),(98560370,'4564232606','RGU01','2011-01-10 00:00:10','Colchicine','500 mcg PO TDS (PRN)','0000-00-00 00:00:00'),(98560369,'4564232606','RGU01','2011-01-10 00:00:10','Allopurinol','100 mg PO OD','0000-00-00 00:00:00'),(98560367,'4564232606','RGU01','2010-08-11 00:00:10','Cetirizine','10 mg PO BD','0000-00-00 00:00:00'),(98560380,'4546792506','RGU01','2012-03-15 00:00:10','Prednisolone','30 mg PO OD','0000-00-00 00:00:00'),(98560377,'4546792506','RGU01','2006-04-04 00:00:10','Ezetimibe','10 mg PO OD','0000-00-00 00:00:00'),(98560379,'4546792506','RGU01','2012-01-10 00:00:10','Azathioprine','50 mg PO OD','0000-00-00 00:00:00'),(98560378,'4546792506','RGU01','2009-07-15 00:00:10','Atorvastatin','10 mg PO Nocte','0000-00-00 00:00:00'),(98560376,'4546792506','RGU01','2005-12-06 00:00:10','NovoRapid 100units/mL injection vial','999 units Various With Meals','0000-00-00 00:00:00'),(98560389,'4122819806','RGU01','2013-03-07 00:00:10','Clopidogrel','75 mg PO OD','0000-00-00 00:00:00'),(98560390,'4122819806','RGU01','2013-03-07 00:00:10','Simvastatin','20 mg PO Nocte','0000-00-00 00:00:00'),(98560391,'4122819806','RGU01','2013-03-08 00:00:10','Alfacalcidol','500 nanogram PO OD','0000-00-00 00:00:00'),(98560388,'4122819806','RGU01','2011-11-09 00:00:10','Calcium carbonate','3 tablet PO With Meals','0000-00-00 00:00:00'),(98560387,'4122819806','RGU01','2010-07-23 00:00:10','Darbepoetin alfa','30 mcg Sub cut Every 2 weeks','0000-00-00 00:00:00'),(98560385,'4122819806','RGU01','2010-02-24 00:00:10','Mupirocin 2% nasal ointment','1 drop Intranasal TDS','0000-00-00 00:00:00'),(98560386,'4122819806','RGU01','2010-07-22 00:00:10','Ferrous sulphate','200 mg PO BD','0000-00-00 00:00:00'),(98560384,'4122819806','RGU01','2010-02-23 00:00:10','Senna','2 tablet PO Nocte','0000-00-00 00:00:00'),(98560383,'4122819806','RGU01','2009-08-21 00:00:10','Dialyvit','1 caps PO OD','0000-00-00 00:00:00'),(98560398,'6320259906','RJZ','2009-02-10 00:00:10','Aspirin Dispersible Tablets','[Enteric-coated] 75mg','0000-00-00 00:00:00'),(98560397,'6320259906','RJZ','2013-01-22 00:00:10','Prednisolone Tablets','5 mg ','0000-00-00 00:00:00'),(98560396,'6320259906','RJZ','2013-01-22 00:00:10','Atorvastatin Tablets','10 mg','0000-00-00 00:00:00'),(98560395,'6320259906','RJZ','2013-01-22 00:00:10','Ramipril Tablets','2.5 mg ','0000-00-00 00:00:00'),(98560393,'6320259906','RJZ','2013-02-13 00:00:10','Lansoprazole','30 mg ','0000-00-00 00:00:00'),(98560394,'6320259906','RJZ','2013-01-22 00:00:10','Doxazosin Tablets','2 mg','0000-00-00 00:00:00'),(98560406,'4582942806','RK7CC','2005-08-09 00:00:10','PREDNISOLONE 5mg tablets','5 mg OD','0000-00-00 00:00:00'),(98560405,'4582942806','RK7CC','2007-03-20 00:00:10','DOXAZOSIN 4mg tablets','8 mg OD','0000-00-00 00:00:00'),(98560404,'4582942806','RK7CC','2008-02-27 00:00:10','PARACETAMOL+CODEINE PHOSPHATE 500mg/3','2 tabs QDS','0000-00-00 00:00:00'),(98560403,'4582942806','RK7CC','2009-05-06 00:00:10','AMLODIPINE 5mg tablets','5 mg OD','0000-00-00 00:00:00'),(98560402,'4582942806','RK7CC','2009-11-18 00:00:10','LANSOPRAZOLE 15mg capsules','15 mg OD','0000-00-00 00:00:00'),(98560424,'4860328906','RK7CC','2002-08-09 00:00:10','PRAVASTATIN SODIUM 20mg tablets','40 mg NOC','0000-00-00 00:00:00'),(98560423,'4860328906','RK7CC','2008-09-26 00:00:10','RAMIPRIL 2.5mg capsules','2.5 mg BD','0000-00-00 00:00:00'),(98560422,'4860328906','RK7CC','2008-09-26 00:00:10','ASPIRIN 75mg tablets','75 mg OD','0000-00-00 00:00:00'),(98560421,'4860328906','RK7CC','2008-10-03 00:00:10','LANTHANUM (as carbonate) 1g chewable','1 tabs TDS','0000-00-00 00:00:00'),(98560420,'4860328906','RK7CC','2009-10-24 00:00:10','CINACALCET 90mg tablets','90 mg OD','0000-00-00 00:00:00'),(98560419,'4860328906','RK7CC','2010-03-09 00:00:10','TESTOGEL 50mg gel 5g sachet','1 units OD','0000-00-00 00:00:00'),(98560418,'4860328906','RK7CC','2010-06-29 00:00:10','NOVORAPID 100units/mL injection vial','20 units TDS','0000-00-00 00:00:00'),(98560416,'4860328906','RK7CC','2010-06-29 00:00:10','KETOVITE tablets','1 tabs BD','0000-00-00 00:00:00'),(98560417,'4860328906','RK7CC','2010-06-29 00:00:10','INSULIN DETEMIR 100iu/mL prefilled pe','20 units OD','0000-00-00 00:00:00'),(98560415,'4860328906','RK7CC','2010-07-20 00:00:10','FOLIC ACID 5mg tablets','5 mg OD','0000-00-00 00:00:00'),(98560414,'4860328906','RK7CC','2011-10-18 00:00:10','ALFACALCIDOL 250nanograms capsules','0.75 ug OD','0000-00-00 00:00:00'),(98560412,'4860328906','RK7CC','2012-04-17 00:00:10','ORLISTAT 120mg capsules','120 mg BD','0000-00-00 00:00:00'),(98560413,'4860328906','RK7CC','2012-02-21 00:00:10','NEORECORMON 2000iu prefilled syringe','2000 units 3xW','0000-00-00 00:00:00'),(98560440,'4945911606','RK7CC','2005-04-01 00:00:10','CARVEDILOL 3.125mg tablets','1 tabs OD','0000-00-00 00:00:00'),(98560438,'4945911606','RK7CC','2005-04-01 00:00:10','SPIRONOLACTONE 25mg tablets','25 mg OD','0000-00-00 00:00:00'),(98560439,'4945911606','RK7CC','2005-04-01 00:00:10','WARFARIN SODIUM 1mg tablets','AS','0000-00-00 00:00:00'),(98560437,'4945911606','RK7CC','2005-04-01 00:00:10','GLYTRIN 400micrograms CFC-free spray','2 puffs PRN','0000-00-00 00:00:00'),(98560436,'4945911606','RK7CC','2005-04-01 00:00:10','TEMAZEPAM 10mg tablets','10 mg OD','0000-00-00 00:00:00'),(98560434,'4945911606','RK7CC','2007-05-18 00:00:10','NICORANDIL 10mg tablets','30 mg BD','0000-00-00 00:00:00'),(98560435,'4945911606','RK7CC','2007-05-18 00:00:10','HYDRALAZINE HYDROCHLORIDE 50mg tablet','50 mg BD','0000-00-00 00:00:00'),(98560433,'4945911606','RK7CC','2007-05-18 00:00:10','ISOSORBIDE MONONITRATE 10mg tablets','30 mg BD','0000-00-00 00:00:00'),(98560431,'4945911606','RK7CC','2007-05-18 00:00:10','QUININE SULPHATE 300mg tablets','300 mg NOC','0000-00-00 00:00:00'),(98560432,'4945911606','RK7CC','2007-05-18 00:00:10','SIMVASTATIN 40mg tablets','40 mg NOC','0000-00-00 00:00:00'),(98560430,'4945911606','RK7CC','2007-05-18 00:00:10','CYCLIZINE 50mg tablets','50 mg TDS','0000-00-00 00:00:00'),(98560429,'4945911606','RK7CC','2007-09-21 00:00:10','LEVEMIR PENFILL 100iu/mL injection ca','units OD','0000-00-00 00:00:00'),(98560428,'4945911606','RK7CC','2007-09-21 00:00:10','NOVORAPID FLEXPEN 100units/mL prefill','units BD','0000-00-00 00:00:00'),(98560450,'6209468306','RK7CC','2001-12-20 00:00:10','PREDNISOLONE 5mg tablets','10 mg ALT','0000-00-00 00:00:00'),(98560448,'6209468306','RK7CC','2003-08-05 00:00:10','EMLA cream 5g',NULL,'0000-00-00 00:00:00'),(98560449,'6209468306','RK7CC','2001-12-20 00:00:10','EPILIM 200mg e/c tablets','600 mg BD','0000-00-00 00:00:00'),(98560447,'6209468306','RK7CC','2005-05-17 00:00:10','AZATHIOPRINE 25mg tablets','25 mg OD','0000-00-00 00:00:00'),(98560446,'6209468306','RK7CC','2005-09-06 00:00:10','FERROUS SULPHATE 200mg tablets','200 mg OD','0000-00-00 00:00:00'),(98560445,'6209468306','RK7CC','2009-04-15 00:00:10','ATORVASTATIN 10mg tablets','10 mg OD','0000-00-00 00:00:00'),(98560444,'6209468306','RK7CC','2011-03-23 00:00:10','BENDROFLUMETHIAZIDE 2.5mg tablets','1 tabs OD','0000-00-00 00:00:00'),(98560443,'6209468306','RK7CC','2011-03-23 00:00:10','ATENOLOL 50mg tablets','50 mg OD','0000-00-00 00:00:00'),(98560475,'4482075906','RLNGH','1997-05-16 00:00:10','GTN spray','0 units NA PRN','0000-00-00 00:00:00'),(98560474,'4482075906','RLNGH','1997-05-13 00:00:10','Metoprolol','50 mg PO BID','0000-00-00 00:00:00'),(98560471,'4482075906','RLNGH','2008-11-18 00:00:10','Cyclosporin = Neoral','50 mg PO BID','0000-00-00 00:00:00'),(98560473,'4482075906','RLNGH','1992-09-09 00:00:10','Ranitidine','150 mg PO BID','0000-00-00 00:00:00'),(98560472,'4482075906','RLNGH','2010-07-13 00:00:10','Alendronic Acid','70 mg PO Daily','0000-00-00 00:00:00'),(98560467,'4482075906','RLNGH','2005-12-21 00:00:10','Doxazosin','1 mg PO Daily','0000-00-00 00:00:00'),(98560468,'4482075906','RLNGH','2006-08-29 00:00:10','Allopurinol','100 mg PO Daily','0000-00-00 00:00:00'),(98560469,'4482075906','RLNGH','2006-10-10 00:00:10','Furosemide','20 mg PO PRN','0000-00-00 00:00:00'),(98560470,'4482075906','RLNGH','2007-02-06 00:00:10','Prednisolone','7.5 mg PO Daily','0000-00-00 00:00:00'),(98560464,'4482075906','RLNGH','1999-04-16 00:00:10','Atorvastatin','10 mg PO Daily','0000-00-00 00:00:00'),(98560465,'4482075906','RLNGH','2004-11-23 00:00:10','Dipyridamole','75 mg PO TID','0000-00-00 00:00:00'),(98560479,'4283486906','RM102','2012-07-31 00:00:10','Ranitidine 150mg effervescent tablet','150 mg  2 times daily  Oral   ','0000-00-00 00:00:00'),(98560480,'4283486906','RM102','2013-04-09 00:00:10','Sirolimus 1mg tablet','5 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560478,'4283486906','RM102','2013-04-09 00:00:10','Mycophenolate mofetil 500mg tablet','250 mg  2 times daily  Oral   ','0000-00-00 00:00:00'),(98560477,'4283486906','RM102','2011-04-11 00:00:10','Citalopram 10mg tablet','10 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560488,'4487674506','RM102','2012-05-18 00:00:10','Venofer 100mg/5mL intravenous solution','100 mg  every 2 weeks  IV   On HD','0000-00-00 00:00:00'),(98560486,'4487674506','RM102','2013-01-16 00:00:10','Renvela 800mg tablet','800 mg  4 times daily  Oral   ','0000-00-00 00:00:00'),(98560487,'4487674506','RM102','2012-01-30 00:00:10','Simvastatin 20mg tablet','20 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560485,'4487674506','RM102','2012-01-11 00:00:10','Quinine sulphate 200mg tablet','200 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560483,'4487674506','RM102','2011-12-23 00:00:10','Ketovite tablet','2 tabs  daily  Oral   ','0000-00-00 00:00:00'),(98560484,'4487674506','RM102','2011-12-23 00:00:10','Paracetamol 500mg capsule','1000 mg  4 times daily  Oral   PRN','0000-00-00 00:00:00'),(98560482,'4487674506','RM102','2011-12-23 00:00:10','Dalteparin sodium 5000iu/0.2mL prefilled syringe','5000 units  3 times weekly  IV   On HD','0000-00-00 00:00:00'),(98560505,'6041028606','RM102','2008-09-02 00:00:10','Senna 7.5mg tablet','7.5 mg  2 times daily  Oral   ','0000-00-00 00:00:00'),(98560506,'6041028606','RM102','2013-03-06 00:00:10','Venofer 100mg/5mL intravenous solution','100 mg  weekly  IV   On HD','0000-00-00 00:00:00'),(98560504,'6041028606','RM102','2008-07-25 00:00:10','Salbutamol 100micrograms CFC-free inhaler','200 micrograms  variable as advised  Inhal   PRN','0000-00-00 00:00:00'),(98560503,'6041028606','RM102','2009-03-04 00:00:10','Lercanidipine hydrochloride 10mg tablet','20 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560501,'6041028606','RM102','2004-12-09 00:00:10','Ketovite tablet','2 tabs  daily  Oral   ','0000-00-00 00:00:00'),(98560502,'6041028606','RM102','2012-10-09 00:00:10','Lanthanum (as carbonate) 750mg/sachet oral powder','750 mg  3 times daily  Oral   ','0000-00-00 00:00:00'),(98560500,'6041028606','RM102','2011-09-08 00:00:10','Irbesartan 300mg tablet','300 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560499,'6041028606','RM102','2010-04-27 00:00:10','Folic acid 5mg tablet','5 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560497,'6041028606','RM102','2009-10-22 00:00:10','Doxazosin 8mg m/r tablet','16 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560498,'6041028606','RM102','2011-06-22 00:00:10','Eprex 8000iu/0.8mL prefilled syringe','8000 units  3 times weekly  IV   On HD','0000-00-00 00:00:00'),(98560496,'6041028606','RM102','2009-03-04 00:00:10','Domperidone 10mg tablet','10 mg  3 times daily  Oral   PRN','0000-00-00 00:00:00'),(98560495,'6041028606','RM102','2009-01-04 00:00:10','Digoxin 62.5micrograms tablet','125 micrograms  daily  Oral   ','0000-00-00 00:00:00'),(98560494,'6041028606','RM102','2013-04-03 00:00:10','Dalteparin sodium 2500iu/0.2mL prefilled syringe','5000 units  3 times weekly  IV   On HD','0000-00-00 00:00:00'),(98560493,'6041028606','RM102','2010-04-28 00:00:10','Co-codamol 8mg/500mg capsule','2 tabs  4 times daily  Oral   PRN','0000-00-00 00:00:00'),(98560492,'6041028606','RM102','2004-12-09 00:00:10','Clonazepam 500micrograms tablet','500 micrograms  at night  Oral   PRN','0000-00-00 00:00:00'),(98560514,'4021785906','RM574','2013-03-15 00:00:10','DARBEPOETIN ALFA','30 mcg','0000-00-00 00:00:00'),(98560515,'4021785906','RM574','2013-03-19 00:00:10','ALFACALCIDOL','0.25 mcg','0000-00-00 00:00:00'),(98560513,'4021785906','RM574','2013-02-26 00:00:10','LANSOPRAZOLE','15 mg','0000-00-00 00:00:00'),(98560512,'4021785906','RM574','2013-02-14 00:00:10','DOXAZOSIN','2 mg','0000-00-00 00:00:00'),(98560511,'4021785906','RM574','2013-02-07 00:00:10','ASPIRIN','75 mg','0000-00-00 00:00:00'),(98560510,'4021785906','RM574','2012-10-30 00:00:10','LANTHANUM','500 mg','0000-00-00 00:00:00'),(98560509,'4021785906','RM574','2012-10-30 00:00:10','PARACETAMOL','1 gram','0000-00-00 00:00:00'),(98560508,'4021785906','RM574','2012-10-30 00:00:10','CALOGEN','30 mL','0000-00-00 00:00:00'),(98560516,'6180612706','RM574','2013-03-04 00:00:10','DARBEPOETIN ALFA','15 mcg','0000-00-00 00:00:00'),(98560537,'6189530206','RMF01','2010-10-06 00:00:10','CANDESARTAN CILEXETI','    8.000 mg OD Oral','0000-00-00 00:00:00'),(98560528,'6189530206','RMF01','2013-01-23 00:00:10','ARANESP 40micrograms','X1FORT IV','0000-00-00 00:00:00'),(98560529,'6189530206','RMF01','2012-03-02 00:00:10','VENOFER 100mg/5mL in','X1 FORTNIGHT IV','0000-00-00 00:00:00'),(98560530,'6189530206','RMF01','2011-01-12 00:00:10','ALFACALCIDOL 2microg','    2.000 mcg DIAL DAYS Oral','0000-00-00 00:00:00'),(98560531,'6189530206','RMF01','2010-12-08 00:00:10','FOLIC ACID 5mg table','    5.000 mg ALTERNATE DA Oral','0000-00-00 00:00:00'),(98560532,'6189530206','RMF01','2010-11-11 00:00:10','LANTHANUM (as carbon','  750.000 mg TDS Oral','0000-00-00 00:00:00'),(98560533,'6189530206','RMF01','2010-10-20 00:00:10','CALCIUM ACETATE 1g t','TDS Oral','0000-00-00 00:00:00'),(98560534,'6189530206','RMF01','2010-10-06 00:00:10','RAMIPRIL 10mg capsul','0D Oral','0000-00-00 00:00:00'),(98560538,'4540124706','RMF01','2013-04-04 00:00:10','ARANESP 50micrograms','X1FORT SA SC','0000-00-00 00:00:00'),(98560536,'6189530206','RMF01','2010-10-06 00:00:10','CREON 10000 e/c gran','30000.000 unit QDS Oral','0000-00-00 00:00:00'),(98560557,'0202581306','RSC02','2007-10-03 00:00:10','QUININE SULPHATE 200mg tablets','1 nocte oral','0000-00-00 00:00:00'),(98560555,'0202581306','RSC02','2010-03-26 00:00:10','SERETIDE 125 Evohaler','2puffbd inhale','0000-00-00 00:00:00'),(98560556,'0202581306','RSC02','2009-09-30 00:00:10','SIMVASTATIN 20mg tablets','1 nocte oral','0000-00-00 00:00:00'),(98560553,'0202581306','RSC02','2011-08-05 00:00:10','FOLIC ACID 5mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560554,'0202581306','RSC02','2010-11-25 00:00:10','VENTOLIN 100micrograms Evohaler','2puf prn inhale','0000-00-00 00:00:00'),(98560552,'0202581306','RSC02','2011-08-05 00:00:10','RENAGEL 800mg tablets','2/2/2 oral','0000-00-00 00:00:00'),(98560550,'0202581306','RSC02','2012-08-10 00:00:10','EPREX 10,000iu/1mL prefilled syring','3 /wk I.V.','0000-00-00 00:00:00'),(98560551,'0202581306','RSC02','2011-09-19 00:00:10','TINZAPARIN SODIUM 2500iu(anti Xa)/0','1 pre HD I.V.','0000-00-00 00:00:00'),(98560549,'0202581306','RSC02','2012-08-10 00:00:10','VENOFER 100mg/5mL intravenous solut','1 /wk I.V.','0000-00-00 00:00:00'),(98560548,'0202581306','RSC02','2012-12-01 00:00:10','LANSOPRAZOLE 30mg capsules','1 mane oral','0000-00-00 00:00:00'),(98560546,'0202581306','RSC02','2013-01-23 00:00:10','ALFACALCIDOL 250nanograms capsules','alt.day oral','0000-00-00 00:00:00'),(98560547,'0202581306','RSC02','2013-01-03 00:00:10','DOMPERIDONE 10mg tablets','2 tds oral','0000-00-00 00:00:00'),(98560545,'0202581306','RSC02','2013-01-25 00:00:10','SUMATRIPTAN 20mg nasal spray','prn topical','0000-00-00 00:00:00'),(98560544,'0202581306','RSC02','2013-01-25 00:00:10','ONDANSETRON 8mg wafer','prn oral','0000-00-00 00:00:00'),(98560543,'0202581306','RSC02','2013-01-26 00:00:10','ASPIRIN 75mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560564,'1306741106','RSC02','2012-03-14 00:00:10','RANITIDINE 150mg tablets','1 bd oral','0000-00-00 00:00:00'),(98560563,'1306741106','RSC02','2012-03-14 00:00:10','CALCICHEW 1.25g chewable tablets','2 nocte oral','0000-00-00 00:00:00'),(98560562,'1306741106','RSC02','2012-03-14 00:00:10','ALFACALCIDOL 250nanograms capsules','1 mane oral','0000-00-00 00:00:00'),(98560561,'1306741106','RSC02','2012-06-01 00:00:10','PREDNISOLONE 5mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560560,'1306741106','RSC02','2012-06-22 00:00:10','PROGRAF 1mg capsules','2 bd oral','0000-00-00 00:00:00'),(98560559,'1306741106','RSC02','2013-01-14 00:00:10','AZATHIOPRINE 25mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560565,'4485079406','RTD01','2013-01-31 00:00:10','Aranesp SureClick 40mcg/0.4ml','40 mcg SC Monthly','0000-00-00 00:00:00'),(98560237,'2007661713','RSC02','2010-07-02 00:00:10','FOLIC ACID 5mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560236,'2007661713','RSC02','2010-07-02 00:00:10','PRAVASTATIN SODIUM 10mg tablets','1 nocte oral','0000-00-00 00:00:00'),(98560235,'2007661713','RSC02','2010-07-27 00:00:10','RANITIC 150mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560234,'2007661713','RSC02','2010-07-27 00:00:10','ONDANSETRON 4mg tablets','prn oral','0000-00-00 00:00:00'),(98560233,'2007661713','RSC02','2011-09-26 00:00:10','RITONAVIR 100mg capsules','1 bd oral','0000-00-00 00:00:00'),(98560231,'2007661713','RSC02','2011-09-26 00:00:10','PARACETAMOL 500mg tablets','2 qid oral','0000-00-00 00:00:00'),(98560232,'2007661713','RSC02','2011-09-26 00:00:10','Darunavir 300mg tablets','2 bd oral','0000-00-00 00:00:00'),(98560230,'2007661713','RSC02','2011-12-12 00:00:10','Raltegravir 400mg tablets','1 bd oral','0000-00-00 00:00:00'),(98560229,'2007661713','RSC02','2011-12-12 00:00:10','LAMIVUDINE 100mg tablets','1/4 bd oral','0000-00-00 00:00:00'),(98560228,'2007661713','RSC02','2011-12-12 00:00:10','CHLORPHENAMINE MALEATE 4mg tablets','prn oral','0000-00-00 00:00:00'),(98560227,'2007661713','RSC02','2012-05-23 00:00:10','VENOFER 100mg/5mL intravenous solut','1 /wk I.V.','0000-00-00 00:00:00'),(98560226,'2007661713','RSC02','2012-10-01 00:00:10','RENAGEL 800mg tablets','3/3/3 oral','0000-00-00 00:00:00'),(98560224,'2007661713','RSC02','2012-10-11 00:00:10','ALFACALCIDOL 500nanograms capsules','1 nocte oral','0000-00-00 00:00:00'),(98560225,'2007661713','RSC02','2012-10-01 00:00:10','CETIRIZINE 10mg tablets','prn oral','0000-00-00 00:00:00'),(98560222,'2007661713','RSC02','2012-11-15 00:00:10','HIBISCRUB 4% solution 250mL','1 mane skin','0000-00-00 00:00:00'),(98560223,'2007661713','RSC02','2012-10-11 00:00:10','ALFACALCIDOL 250nanograms capsules','1 nocte oral','0000-00-00 00:00:00'),(98560242,'3204969541','2020','2012-01-27 00:00:10','Carbocisteine 375mg capsule','750 mg  three times daily  Oral   ','0000-00-00 00:00:00'),(98560240,'3204969541','2020','2010-09-14 00:00:10','Aspirin 75mg dispersible tablet','75 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560241,'3204969541','2020','2012-07-25 00:00:10','Azithromycin 500mg tablet','500 mg  variable as advised  Oral   ','0000-00-00 00:00:00'),(98560239,'3204969541','2020','2012-01-27 00:00:10','Adcal 600mg chewable tablet','600 mg  three times daily  Oral   ','0000-00-00 00:00:00'),(98560262,'4087878406','RAE05','2013-03-05 00:00:10','Glyceryl Trinitrate spray','2 puf PRN S.L.','0000-00-00 00:00:00'),(98560261,'4087878406','RAE05','2013-03-13 00:00:10','Bumetanide tabs 5mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560260,'4087878406','RAE05','2013-03-13 00:00:10','Duloxetine caps 20mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560282,'6067495406','45020','2010-04-14 00:00:10','Aspirin 75mg e/c tablet','75 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560280,'6067495406','45020','2013-04-09 00:00:10','Alfacalcidol 1microgram capsule','4 micrograms  two times daily  Oral   ','0000-00-00 00:00:00'),(98560298,'4482941506','RCJAT','2012-04-12 00:00:10','Adalat','30 mgs. daily','0000-00-00 00:00:00'),(98560297,'4482941506','RCJAT','2012-04-12 00:00:10','Ferrous Sulphate',NULL,'0000-00-00 00:00:00'),(98560296,'4482941506','RCJAT','2012-04-12 00:00:10','Cefalexin','500 mgs. nocte','0000-00-00 00:00:00'),(98560315,'4647463206','RAQ01','2012-05-09 00:00:10','Paracetamol (Soluble','1 g 6 Hrly','0000-00-00 00:00:00'),(98560311,'4647463206','RAQ01','2012-04-04 00:00:10','Alfacalcidol','0.25 ug 3X wkly','0000-00-00 00:00:00'),(98560310,'4647463206','RAQ01','2009-06-10 00:00:10','Vitamin B Co','1 Tabs 12Hrly','0000-00-00 00:00:00'),(98560309,'4647463206','RAQ01','2008-06-30 00:00:10','Verapamil','240 mg daily','0000-00-00 00:00:00'),(98560329,'6343053506','RCJAT','2012-09-14 00:00:10','Dialyvit','100mg 3xweekly','0000-00-00 00:00:00'),(98560326,'6343053506','RCJAT','2013-01-05 00:00:10','Venofer','200mg monthly.','0000-00-00 00:00:00'),(98560346,'4468928506','RGU01','2012-05-25 00:00:10','Docusate sodium','200 mg PO BD','0000-00-00 00:00:00'),(98560357,'6260711506','RGU01','2010-12-13 00:00:10','Irbesartan','150 mg PO OD','0000-00-00 00:00:00'),(98560354,'6260711506','RGU01','2010-01-22 00:00:10','Ferrous sulphate','200 mg PO TDS','0000-00-00 00:00:00'),(98560368,'4564232606','RGU01','2010-08-11 00:00:10','Candesartan cilexetil','32 mg PO OD','0000-00-00 00:00:00'),(98560366,'4564232606','RGU01','2010-08-11 00:00:10','Rabeprazole sodium','20 mg PO OD','0000-00-00 00:00:00'),(98560375,'4546792506','RGU01','2005-12-06 00:00:10','Atenolol','25 mg PO OD','0000-00-00 00:00:00'),(98560374,'4546792506','RGU01','2005-12-06 00:00:10','Aspirin','75 mg PO OD','0000-00-00 00:00:00'),(98560382,'4122819806','RGU01','2009-07-29 00:00:10','Docusate sodium','200 mg PO BD','0000-00-00 00:00:00'),(98560381,'4122819806','RGU01','2009-07-29 00:00:10','Folic acid 5mg tablet','5 mg PO OD','0000-00-00 00:00:00'),(98560399,'6320259906','RJZ','2012-04-11 00:00:10','Atenolol Tablets','25mg','0000-00-00 00:00:00'),(98560392,'6320259906','RJZ','2013-02-22 00:00:10','Adoport (Tacrolimus Immediate ','2.5mg','0000-00-00 00:00:00'),(98560407,'4582942806','RK7CC','2004-10-13 00:00:10','ATENOLOL 50mg tablets','50 mg OD','0000-00-00 00:00:00'),(98560401,'4582942806','RK7CC','2011-08-31 00:00:10','ADOPORT 1mg capsules','3 mg BD','0000-00-00 00:00:00'),(98560411,'4860328906','RK7CC','2012-04-17 00:00:10','CLONAZEPAM 500microgram tablets','500 ug NOC','0000-00-00 00:00:00'),(98560410,'4860328906','RK7CC','2012-05-29 00:00:10','OSVAREN 435mg/235mg tablets','3 tabs TDS','0000-00-00 00:00:00'),(98560409,'4860328906','RK7CC','2013-02-21 00:00:10','VENOFER 100mg/5mL intravenous solutio','100 mg 2xM','0000-00-00 00:00:00'),(98560427,'4945911606','RK7CC','2007-09-21 00:00:10','ALLOPURINOL 100mg tablets','100 mg OD','0000-00-00 00:00:00'),(98560426,'4945911606','RK7CC','2007-09-21 00:00:10','ONE-ALPHA 250nanograms capsules','250 ng ALT','0000-00-00 00:00:00'),(98560425,'4945911606','RK7CC','2008-01-25 00:00:10','FUROSEMIDE 40mg tablets','40 mg BD','0000-00-00 00:00:00'),(98560442,'6209468306','RK7CC','2012-09-26 00:00:10','LAMOTRIGINE 100mg tablets','300 mg OD','0000-00-00 00:00:00'),(98560441,'6209468306','RK7CC','2013-01-02 00:00:10','COLECALCIFEROL 800iu capsules','1 tabs OD','0000-00-00 00:00:00'),(98560466,'4482075906','RLNGH','2005-08-25 00:00:10','Risedronate','35 mg PO Weekly','0000-00-00 00:00:00'),(98560476,'4283486906','RM102','2013-03-04 00:00:10','Aranesp SureClick 60micrograms/0.3mL prefilled pen','60 micrograms  every 2 weeks  SC   ','0000-00-00 00:00:00'),(98560481,'4487674506','RM102','2012-06-27 00:00:10','Aspirin 75mg tablet','75 mg  daily  Oral   ','0000-00-00 00:00:00'),(98560491,'6041028606','RM102','2011-05-28 00:00:10','Chlorphenamine maleate 4mg tablet','4 mg  4 times daily  Oral   PRN','0000-00-00 00:00:00'),(98560490,'6041028606','RM102','2008-10-02 00:00:10','Beclometasone 200 cyclocaps','2 puff  2 times daily  Inhal   ','0000-00-00 00:00:00'),(98560489,'6041028606','RM102','2013-01-23 00:00:10','Alfacalcidol 1microgram/0.5mL injection','2 micrograms  2 times weekly  IV   On HD','0000-00-00 00:00:00'),(98560507,'4021785906','RM574','2012-10-30 00:00:10','AMLODIPINE','10 mg','0000-00-00 00:00:00'),(98560535,'6189530206','RMF01','2010-10-06 00:00:10','OMEPRAZOLE 40mg tabl','OD Oral','0000-00-00 00:00:00'),(98560542,'0202581306','RSC02','2013-01-26 00:00:10','DILTIAZEM HYDROCHLORIDE 60mg m/r ca','1 bd oral','0000-00-00 00:00:00'),(98560541,'0202581306','RSC02','2013-03-06 00:00:10','PARACETAMOL 500mg tablets','2 qid oral','0000-00-00 00:00:00'),(98560540,'0202581306','RSC02','2013-03-06 00:00:10','NEFOPAM HYDROCHLORIDE 30mg tablets','prn oral','0000-00-00 00:00:00'),(98560539,'0202581306','RSC02','2013-03-14 00:00:10','LACTULOSE 3.35g/5mL solution','10ml bd oral','0000-00-00 00:00:00'),(98560558,'1306741106','RSC02','2013-01-14 00:00:10','AZATHIOPRINE 50mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560221,'2007661713','RSC02','2012-11-15 00:00:10','BACTROBAN NASAL 2% nose ointment 3g','1 tid topical','0000-00-00 00:00:00'),(98560220,'2007661713','RSC02','2012-11-15 00:00:10','CHLORHEXIDINE 0.2% mouthwash','1 bd oral','0000-00-00 00:00:00'),(98560219,'2007661713','RSC02','2012-11-15 00:00:10','EPREX 8000iu/0.8mL prefilled syring','3 /wk I.V.','0000-00-00 00:00:00'),(98560263,'4087878406','RAE05','2013-03-05 00:00:10','Bisoprolol tabs 5mg','1 1x/day Oral','0000-00-00 00:00:00'),(98560279,'6067495406','45020','2013-04-09 00:00:10','Adcal 600mg chewable tablet','1200 mg  four times daily  Oral   ','0000-00-00 00:00:00'),(98560308,'4647463206','RAQ01','2008-03-29 00:00:10','Folic acid','5 mg daily','0000-00-00 00:00:00'),(98560325,'6343053506','RCJAT','2013-02-06 00:00:10','One Alpha Calcidol','0.25 daily','0000-00-00 00:00:00'),(98560351,'4468928506','RGU01','2012-10-25 00:00:10','Venofer 100mg/5mL intravenous solution','200 mg IV PRN','0000-00-00 00:00:00'),(98560353,'6260711506','RGU01','2008-11-27 00:00:10','Aspirin','75 mg PO OD','0000-00-00 00:00:00'),(98560365,'4564232606','RGU01','2010-08-11 00:00:10','Alfacalcidol','250 nanogram PO OD','0000-00-00 00:00:00'),(98560238,'2007661713','RSC02','2006-11-09 00:00:10','ALLOPURINOL 100mg tablets','1 mane oral','0000-00-00 00:00:00'),(98560408,'4582942806','RK7CC','2004-10-07 00:00:10','ASPIRIN 75mg tablets','75 mg OD','0000-00-00 00:00:00'),(98560451,'6209468306','RK7CC','2001-12-20 00:00:10','NEORAL 25mg capsules','75 mg BD','0000-00-00 00:00:00'),(98560566,'4924881406','RTD01','2013-04-04 00:00:10','Ferric carboxymaltose','800 mg IV Stat','0000-00-00 00:00:00'),(98560567,'4561171606','RX1CC','2013-01-31 00:00:10','CNI','1.25','0000-00-00 00:00:00'),(98560568,'4971252606','RX1CC','2010-08-05 00:00:10','Pred','  2.0','0000-00-00 00:00:00'),(98560569,'0105393606','SAC02','2013-03-06 15:34:30','Ferinject','1000 mg. Infusion Iv Once only 2nd dose ','0000-00-00 00:00:00'),(98560570,'0105393606','SAC02','2013-02-27 00:00:10','Darbepoetin','50 micro g PreFillSyringe sub cut Every 2 weeks 1st prescription ','0000-00-00 00:00:00'),(98560571,'0105393606','SAC02','2013-01-07 00:00:10','Imatinib','400 mg. Tablet/s Oral Daily Philadelphia +ve CML\n\nStarted by Dr Eynaud ','0000-00-00 00:00:00'),(98560572,'1606853406','SAC02','2013-02-26 00:00:10','Osvaren','2 _ Capsules Oral 3 x per day. Calcium & Mg supplement ','0000-00-00 00:00:00'),(98560573,'1606853406','SAC02','2013-02-21 00:00:10','Darbepoetin alfa','80 micro g PreFillSyringe sub cut weekly. ','0000-00-00 00:00:00'),(98560574,'1606853406','SAC02','2013-02-20 00:00:10','Omeprazole','20 mg. Capsules Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560575,'1606853406','SAC02','2013-02-20 00:00:10','Laxido orange','1 Sachet Oral 2 x per day- ','0000-00-00 00:00:00'),(98560576,'1606853406','SAC02','2013-02-20 00:00:10','Slow-K','600 mg. Tablet/s Oral 3 x per day. ','0000-00-00 00:00:00'),(98560577,'1606853406','SAC02','2013-02-19 11:29:35','Alfacalcidol','1.5 micro g Capsules Oral Daily ','0000-00-00 00:00:00'),(98560578,'1606853406','SAC02','2013-02-11 00:00:10','Ferrous sulphate','200 mg. Tablet/s Oral 3 x per day. ','0000-00-00 00:00:00'),(98560579,'1606853406','SAC02','2013-01-21 00:00:10','Cyclizine','50 mg. Tablet/s Oral 3 x per day if necessary ','0000-00-00 00:00:00'),(98560580,'1606853406','SAC02','2013-01-21 00:00:10','Ranitidine','150 mg. Tablet/s Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560581,'1606853406','SAC02','2012-09-27 00:00:10','Ferinject','900 mg. Infusion Iv Once only 1st dose ','0000-00-00 00:00:00'),(98560582,'1606853406','SAC02','2012-08-28 09:12:59','Tacrolimus (Prograf)','0.5 mg. Capsules Oral 2 x per day.. Changed by Newcastle ','0000-00-00 00:00:00'),(98560583,'1606853406','SAC02','2012-07-19 10:57:27','Sodium bicarbonate','500 mg. Capsules Oral 2 x per day- ','0000-00-00 00:00:00'),(98560584,'1606853406','SAC02','2012-07-02 00:00:10','Prednisolone','10 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560585,'1606853406','SAC02','2011-11-22 00:00:10','Amlodipine','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560586,'1606853406','SAC02','1901-01-01 00:00:10','Simvastatin','10 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560587,'1606853406','SAC02','1901-01-01 00:00:10','Allopurinol','100 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560588,'1606853406','SAC02','1901-01-01 00:00:10','Mycophenolate Mofet.(Cellcept)','500 mg. Tablet/s Oral 2 x per day- ','0000-00-00 00:00:00'),(98560589,'1803453206','SAC02','2013-02-14 00:00:10','Mirtazapine','15 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560590,'1803453206','SAC02','2013-01-30 15:56:32','Iron sucrose','100 mg. Injection Iv weekly. ','0000-00-00 00:00:00'),(98560591,'1803453206','SAC02','2012-04-02 00:00:10','Heparin','2000 units Injection Iv before dialysis ','0000-00-00 00:00:00'),(98560592,'1803453206','SAC02','2012-04-02 00:00:10','Heparin','800 units Infusion Iv during dialysis ','0000-00-00 00:00:00'),(98560593,'1803453206','SAC02','2011-06-15 00:00:10','Paracetamol','1 g. Tablet/s Oral 4 x per day if necessary ','0000-00-00 00:00:00'),(98560594,'1803453206','SAC02','2010-06-15 00:00:10','Dialyvit(adult)','1 _ Tablet/s Oral after dialysis ','0000-00-00 00:00:00'),(98560595,'1803453206','SAC02','2008-01-01 00:00:10','Raloxifene hydrochloride','60 mg. Tablet/s Oral Daily WIG Presc Started by:Othr ','0000-00-00 00:00:00'),(98560596,'1803453206','SAC02','2006-10-31 00:00:10','Pravastatin','20 mg. Tablet/s Oral Daily WIG Presc Started by:BRM ','0000-00-00 00:00:00'),(98560597,'1803453206','SAC02','2005-06-29 00:00:10','Aspirin','75 mg. Tab(Soluble) Oral In the morning WIG Presc Started by:TGW ','0000-00-00 00:00:00'),(98560598,'2207523306','SAC02','2012-09-18 00:00:10','Prednisolone','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560599,'2207523306','SAC02','2012-07-25 00:00:10','Ferrous fumarate','210 mg. Tablet/s Oral 3 x per day. GP to start ','0000-00-00 00:00:00'),(98560600,'2207523306','SAC02','2012-07-13 00:00:10','Ranitidine','150 mg. Tablet/s Oral 2 x per day- ','0000-00-00 00:00:00'),(98560601,'2207523306','SAC02','2012-07-04 00:00:10','Paracetamol','1 g. Tablet/s Oral 4 x per day if necessary ','0000-00-00 00:00:00'),(98560602,'2207523306','SAC02','2012-07-04 00:00:10','Senna','15 mg. Tablet/s Oral At Night ','0000-00-00 00:00:00'),(98560603,'2011423406','SAC02','2013-03-25 00:00:10','Citalopram','10 mg. Solution NJ Daily ','0000-00-00 00:00:00'),(98560604,'2011423406','SAC02','2013-03-22 00:00:10','Oxycodone HCl 5mg/5mL s/f liq','1 mg. Linctus NJ 4 x per day. ','0000-00-00 00:00:00'),(98560605,'2011423406','SAC02','2013-03-21 00:00:10','Hyoscine butylbromide','20 mg. Tablet/s NJ 3 x per day. ','0000-00-00 00:00:00'),(98560606,'2011423406','SAC02','2013-03-20 00:00:10','Oxycodone HCl 5mg/5mL s/f liq','0.5-1.0 mg. Solution NJ when necessary as per pain team ','0000-00-00 00:00:00'),(98560607,'2011423406','SAC02','2013-03-13 09:39:32','Co-amoxiclav','250/62 mg. Suspension NJ 3 x per day. On micro advice\nFor 6 weeks\nStarted 5/3 ','0000-00-00 00:00:00'),(98560608,'2011423406','SAC02','2013-03-13 00:00:10','Ranitidine','150 mg. Syrup Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560609,'2011423406','SAC02','2013-03-13 00:00:10','Zopiclone','3.75 mg. Tablet/s Oral when necessary  ','0000-00-00 00:00:00'),(98560610,'2011423406','SAC02','2013-03-03 00:00:10','Cyclizine','50 mg. Tablet/s Oral 3 x per day. ','0000-00-00 00:00:00'),(98560611,'2011423406','SAC02','2013-02-21 00:00:10','Amitriptyline','10 mg. Tablet/s Oral At Night For pain ','0000-00-00 00:00:00'),(98560612,'2011423406','SAC02','2013-02-20 09:23:52','Prednisolone','2.5 mg. Tablet/s Oral Daily To be reviewed at clinic ','0000-00-00 00:00:00'),(98560613,'2011423406','SAC02','2013-01-21 16:43:44','Darbepoetin alfa','80 micro g PreFillSyringe *SEE COMMENT weekly. Can be given IV or SC\nTuesdays ','0000-00-00 00:00:00'),(98560614,'2011423406','SAC02','2012-11-28 00:00:10','Hydroxocobalamin','1 mg. Injection Im 3 monthly ','0000-00-00 00:00:00'),(98560615,'2011423406','SAC02','2012-10-26 00:00:10','Renavit','1 Tablet/s Oral after dialysis ','0000-00-00 00:00:00'),(98560616,'2011423406','SAC02','2012-10-25 12:44:14','Ramipril','1.25 mg. Capsules Oral At Night ','0000-00-00 00:00:00'),(98560617,'2011423406','SAC02','2012-09-07 12:48:21','Bisoprolol','2.5 mg. Tablet/s Oral Daily  ','0000-00-00 00:00:00'),(98560618,'2011423406','SAC02','2012-07-11 00:00:10','Iron sucrose','100 mg. Injection Iv monthly. ','0000-00-00 00:00:00'),(98560619,'2011423406','SAC02','2012-05-24 00:00:10','Heparin','1000 units Injection Iv before dialysis ','0000-00-00 00:00:00'),(98560620,'2011423406','SAC02','2012-04-05 16:20:52','Heparin','500 hr units Injection Iv during dialysis 500 units/hour ','0000-00-00 00:00:00'),(98560621,'2011423406','SAC02','2012-03-22 00:00:10','Glyceryl trinitrate','1 puffs Solution Sub Ling when necessary for chest pain ','0000-00-00 00:00:00'),(98560622,'2011423406','SAC02','2010-07-21 00:00:10','Paracetamol','1 g. Tablet/s Oral 4 x per day if necessary ','0000-00-00 00:00:00'),(98560623,'2011423406','SAC02','2010-07-21 00:00:10','Simvastatin','40 mg. Tablet/s Oral as prescribed ','0000-00-00 00:00:00'),(98560624,'2011423406','SAC02','2010-07-21 00:00:10','Aspirin','75 mg. Tab(Soluble) Oral In the morning ','0000-00-00 00:00:00'),(98560625,'1202760406','SFC01','2013-04-10 00:00:10','ARANESP 30micrograms/0.3mL prefille','1 /wk S.C.','0000-00-00 00:00:00'),(98560626,'1202760406','SFC01','2013-01-17 00:00:10','LANTHANUM CARBONATE 500mg tablets','1/1/1 oral','0000-00-00 00:00:00'),(98560627,'1202760406','SFC01','2013-01-17 00:00:10','ARANESP 20micrograms/0.5mL prefille','alt wk S.C.','0000-00-00 00:00:00'),(98560628,'1202760406','SFC01','2013-01-11 00:00:10','CLONAZEPAM 500microgram tablets','2 nocte oral','0000-00-00 00:00:00'),(98560629,'1202760406','SFC01','2013-01-11 00:00:10','FEXOFENADINE HYDROCHLORIDE 120mg ta','1 nocte oral','0000-00-00 00:00:00'),(98560630,'1202760406','SFC01','2012-05-30 00:00:10','ALFACALCIDOL 500nanograms capsules','1 mane oral','0000-00-00 00:00:00'),(98560631,'1202760406','SFC01','2011-10-26 00:00:10','CYCLIZINE 50mg tablets','prn oral','0000-00-00 00:00:00'),(98560632,'1202760406','SFC01','2011-10-26 00:00:10','QUININE SULPHATE 300mg tablets','1 nocte oral','0000-00-00 00:00:00'),(98560633,'1202760406','SFC01','2007-08-22 00:00:10','NOVORAPID FLEXPEN 100units/mL prefi','1 tid S.C.','0000-00-00 00:00:00'),(98560634,'2708575406','SGC04','2013-01-29 17:21:14','Mycophenolate Mofet.(Cellcept)','500 mg. Tablet/s Oral In the morning ','0000-00-00 00:00:00'),(98560635,'2708575406','SGC04','2013-01-29 00:00:10','Mycophenolate mofetil','250 mg. Oral In the evening- ','0000-00-00 00:00:00'),(98560636,'2708575406','SGC04','2007-05-03 00:00:10','Omeprazole','20 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560637,'2708575406','SGC04','2007-05-03 00:00:10','Folic acid','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560638,'2907790706','SGC04','2013-04-08 00:00:10','Ramipril','1.25 mg. Capsules Oral Daily ','0000-00-00 00:00:00'),(98560639,'2907790706','SGC04','2013-03-18 00:00:10','Leflunomide','20` mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560640,'2907790706','SGC04','2013-03-11 00:00:10','Amlodipine','10 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560641,'2907790706','SGC04','2012-12-17 00:00:10','Tacrolimus (Prograf)','3.5 mg. Capsules Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560642,'2907790706','SGC04','2011-11-07 00:00:10','Omeprazole','20 mg. Capsules Oral Daily ','0000-00-00 00:00:00'),(98560643,'2907790706','SGC04','2011-06-02 09:16:50','Prednisolone','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560644,'0705698106','SGC05','2012-11-27 00:00:10','Sodium bicarbonate','500 mg. Capsules Oral 3 x per day. ','0000-00-00 00:00:00'),(98560645,'0705698106','SGC05','2012-11-26 00:00:10','Lactulose solution','15 ml. Solution Oral 2 x per day- ','0000-00-00 00:00:00'),(98560646,'0705698106','SGC05','2012-11-18 00:00:10','Cyclizine','50 mg. Injection Iv 3 x per day if necessary ','0000-00-00 00:00:00'),(98560647,'0705698106','SGC05','2012-09-17 00:00:10','Cholestyramine','4 g. Sachet Oral Daily ','0000-00-00 00:00:00'),(98560648,'0705698106','SGC05','2012-06-03 00:00:10','Dalteparin','5000 units Injection sub cut Daily ','0000-00-00 00:00:00'),(98560649,'0705698106','SGC05','2012-06-01 00:00:10','Diprobase','1 appl Cream. Topical when necessary ','0000-00-00 00:00:00'),(98560650,'0705698106','SGC05','2011-09-02 09:10:02','Tacrolimus (Prograf)','2.5 mg. Capsules Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560651,'0705698106','SGC05','2011-07-22 00:00:10','Paracetamol','1 g. Tablet/s Oral 4 x per day if necessary ','0000-00-00 00:00:00'),(98560652,'0705698106','SGC05','2011-03-03 00:00:10','Mycophenolate mofetil','250 mg. Tablet/s Oral 3 x per day. 250mg mane & 500mg at night ','0000-00-00 00:00:00'),(98560653,'0705698106','SGC05','2011-02-24 00:00:10','Cinacalcet','90 mg. Tablet/s Oral 2 x per day- Started in nov 2009, but recorded as 180 od ','0000-00-00 00:00:00'),(98560654,'0705698106','SGC05','2011-01-13 00:00:10','Prednisolone','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560655,'0705698106','SGC05','2010-11-15 00:00:10','Cephalexin','250 mg. Capsules Oral Daily Alternating with Ciprofloxacin. Prophylactic therapy. ','0000-00-00 00:00:00'),(98560656,'0705698106','SGC05','2010-11-15 00:00:10','Ciprofloxacin','250 mg. Tablet/s Oral Daily Alternation with Cephalexin. Prophylactic therapy ','0000-00-00 00:00:00'),(98560657,'0705698106','SGC05','2010-11-04 00:00:10','Progynova','1 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560658,'0705698106','SGC05','2010-07-08 00:00:10','Omeprazole','20 mg. Capsules Oral Daily ','0000-00-00 00:00:00'),(98560659,'2209605806','SGC05','2013-03-13 10:56:55','Bisoprolol','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560660,'2209605806','SGC05','2012-11-05 11:06:45','Doxazosin','2 mg. Tablet/s Oral 2 x per day- ','0000-00-00 00:00:00'),(98560661,'2209605806','SGC05','2012-10-17 00:00:10','Folic acid','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560662,'2209605806','SGC05','2012-03-24 00:00:10','Ranitidine','150 mg. Tablet/s Oral 2 x per day- ','0000-00-00 00:00:00'),(98560663,'2209605806','SGC05','2012-03-15 00:00:10','Amlodipine','10 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560664,'2209605806','SGC05','2012-03-15 00:00:10','Citalopram','20 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560665,'2209605806','SGC05','2012-03-15 00:00:10','Ferrous fumarate','210 mg. Tablet/s Oral 3 x per day. ','0000-00-00 00:00:00'),(98560666,'2411496206','SGC05','2013-02-18 00:00:10','Tacrolimus (Prograf)','3 mg. Capsules Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560667,'2411496206','SGC05','2012-12-03 00:00:10','Ferrous fumarate','210 mg. Tablet/s Oral 3 x per day. ','0000-00-00 00:00:00'),(98560668,'2411496206','SGC05','2012-09-04 15:23:31','Myfortic','360 mg. Capsules Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560669,'2411496206','SGC05','2012-08-30 17:27:37','Furosemide.','40 mg. Tablet/s Oral In the morning if required ','0000-00-00 00:00:00'),(98560670,'2411496206','SGC05','2012-01-05 10:36:23','Omeprazole','20 mg. Capsules Oral Daily ','0000-00-00 00:00:00'),(98560671,'2411496206','SGC05','2011-12-01 00:00:10','Aspirin','75 mg. Tab(Soluble) Oral In the morning ','0000-00-00 00:00:00'),(98560672,'1409965406','SNC01','2012-10-05 00:00:10','Tacrolimus','5 mg','0000-00-00 00:00:00'),(98560673,'1409965406','SNC01','2012-10-05 00:00:10','Prednisolone','5 mg','0000-00-00 00:00:00'),(98560674,'1409965406','SNC01','2012-10-05 00:00:10','Lisinopril','7.5 mg','0000-00-00 00:00:00'),(98560675,'0911892206','SNC01','2007-11-08 00:00:10','Lansoprazole','15 mg','0000-00-00 00:00:00'),(98560676,'0911892206','SNC01','2010-10-26 00:00:10','Mycophenolate mofetil','500 mg','0000-00-00 00:00:00'),(98560677,'0911892206','SNC01','2010-07-07 00:00:10','Cefradine','250 mg','0000-00-00 00:00:00'),(98560678,'0911892206','SNC01','2012-11-13 00:00:10','Prednisolone','5 mg','0000-00-00 00:00:00'),(98560679,'0911892206','SNC01','2013-03-12 00:00:10','Sertraline','50 mg','0000-00-00 00:00:00'),(98560680,'0911892206','SNC01','2013-03-14 00:00:10','Ferric carboxymaltose','500 mg','0000-00-00 00:00:00'),(98560681,'0911892206','SNC01','2013-04-04 00:00:10','Tacrolimus','7.5 mg','0000-00-00 00:00:00'),(98560682,'0911892206','SNC01','2013-04-09 00:00:10','Darbepoetin Alfa','10 mcg','0000-00-00 00:00:00'),(98560683,'1710872306','SNC01','2012-03-28 00:00:10','Hydroxychloroquine','400 mg','0000-00-00 00:00:00'),(98560684,'1710872306','SNC01','2012-03-28 00:00:10','Ramipril','2.5 mg','0000-00-00 00:00:00'),(98560685,'1710872306','SNC01','2012-04-11 00:00:10','Prednisolone','30 mg','0000-00-00 00:00:00'),(98560686,'1710872306','SNC01','2012-04-11 00:00:10','Furosemide','40 mg','0000-00-00 00:00:00'),(98560687,'1710872306','SNC01','2012-04-11 00:00:10','Myfenax','500 mg','0000-00-00 00:00:00'),(98560688,'2808583206','SYC01','2013-03-04 00:00:10','Tacrolimus (Prograf)','3 mg. Capsules Oral 2 x per day- ','0000-00-00 00:00:00'),(98560689,'2808583206','SYC01','2013-03-04 00:00:10','Ranitidine','300 mg. Tablet/s Oral ','0000-00-00 00:00:00'),(98560690,'2808583206','SYC01','2013-02-01 00:00:10','Ferrous fumarate','305 mg. Tablet/s Oral 2 x per day.. ','0000-00-00 00:00:00'),(98560691,'2808583206','SYC01','2013-01-31 00:00:10','Atenolol','50 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560692,'2808583206','SYC01','2013-01-24 00:00:10','Prednisolone','5 mg. Tablet/s Oral Daily ','0000-00-00 00:00:00'),(98560693,'2808583206','SYC01','2012-12-18 00:00:10','MedRec completed',NULL,'0000-00-00 00:00:00'),(98560694,'2808583206','SYC01','2012-03-28 00:00:10','Simvastatin','40 mg. Tablet/s Oral At Night ','0000-00-00 00:00:00'),(98560695,'2808583206','SYC01','2012-01-27 00:00:10','Paracetamol','1 g. Tablet/s Oral 4 x per day if necessary ','0000-00-00 00:00:00'),(98560696,'2804562611','ALPORT','2012-04-02 00:00:00','Methotrexate','7.5mg once weekly',NULL);
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
  `recipient_id` bigint(20) NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C2397E72776A072` (`conversation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (23,'The patient b','2013-05-07 15:37:20',0,1,10,58031,58033),(24,'Hi unit admin a','2013-05-07 15:38:28',0,0,10,58033,58031);
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
) ENGINE=MyISAM AUTO_INCREMENT=182 DEFAULT CHARSET=latin1;
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
  `centreCode` varchar(20) NOT NULL DEFAULT '',
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `nhsno` (`nhsno`,`centreCode`)
) ENGINE=MyISAM AUTO_INCREMENT=605462 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_allergy`
--

LOCK TABLES `pv_allergy` WRITE;
/*!40000 ALTER TABLE `pv_allergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_allergy` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_feature_access`
--

LOCK TABLES `pv_feature_access` WRITE;
/*!40000 ALTER TABLE `pv_feature_access` DISABLE KEYS */;
INSERT INTO `pv_feature_access` VALUES (4,'messaging',1112);
/*!40000 ALTER TABLE `pv_feature_access` ENABLE KEYS */;
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
  `email` varchar(255) NOT NULL DEFAULT '',
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `nhsNo` varchar(100) DEFAULT NULL,
  `dateOfRequest` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_patientjoin_request`
--

LOCK TABLES `pv_patientjoin_request` WRITE;
/*!40000 ALTER TABLE `pv_patientjoin_request` DISABLE KEYS */;
INSERT INTO `pv_patientjoin_request` VALUES (3,'Neil','Turner','1956-04-28 00:00:00','neilturn@gmail.com','RENALA','2804562611','2012-10-14 00:00:00'),(4,'Neil','Turner','1956-04-28 00:00:00','neilturn@gmail.com','TESTTEST','2804562611','2012-10-14 00:00:00');
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pv_procedure`
--

LOCK TABLES `pv_procedure` WRITE;
/*!40000 ALTER TABLE `pv_procedure` DISABLE KEYS */;
/*!40000 ALTER TABLE `pv_procedure` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdc_genetic_test`
--

LOCK TABLES `rdc_genetic_test` WRITE;
/*!40000 ALTER TABLE `rdc_genetic_test` DISABLE KEYS */;
INSERT INTO `rdc_genetic_test` VALUES (5,731,3,'Guy\'s','Maternal Uncle, don\'t know which',NULL,'COL4A5 deletion','Maternal uncles had deafness and ESRD in teens/twenties. Renal biopsy aged 7; EM showed characteristic changes');
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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_alport_deafness`
--

LOCK TABLES `rdr_alport_deafness` WRITE;
/*!40000 ALTER TABLE `rdr_alport_deafness` DISABLE KEYS */;
INSERT INTO `rdr_alport_deafness` VALUES (5,731,1,0,0);
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
INSERT INTO `rdr_diagnosis_mapping` VALUES ('AHUS','xAHUS',1),('ALPORT','2756',1),('ALPORT','2760',2),('ARPKD','xARPKD',1),('Arthrogryposis,1','RDG004',1),('Autoimmune2','RDG005',1),('Bardet3','3351',1),('Bartter4','3085',1),('Branchio-oto-renal5','3322',1),('CYSTIN','2964',1),('Denys-Drash7','1074',1),('EAST8','RDG006',1),('Frasier10','3314',1),('FUAN','xFUAN',1),('Gitelman11','3092',1),('Glucocorticoid-remediable13','3125',1),('Gordon12','3156',1),('Haemolytic14','2610',1),('Haemolytic14','2623',2),('Haemolytic14','2647',3),('Haemolytic14','2652',4),('Haemolytic14','2668',5),('HNF1B','1639',3),('HNF1B','1656',2),('HNF1B','3139',4),('HNF1B','3627',1),('HYPALK','xHYPALK',1),('Hyperoxaluria16','1850',1),('Hyperoxaluria16','3194',2),('Hyperoxaluria16','3207',3),('Hyperoxaluria16','3211',4),('Hyperoxaluria16','3731',5),('Hyperparathyroidism,20','RDG010',1),('Hyperparathyroidism19','RDG009',1),('Hyperparathyroidism21','RDG011',1),('HYPERRDG','xHYPERRDG',1),('Hyperuricemic22','2827',1),('Hypocalciuria15','3160',1),('Hypoparathyroidism17','RDG007',1),('Hypoparathyroidism18','RDG008',1),('Liddle26','3102',1),('Lipoprotein27','RDG013',1),('Lowe28','2938',1),('Maturity-Onset29','3139',1),('Medullary30','2815',1),('Membranoproliferative31','1233',1),('MEMRDG','xMEMRDG',1),('Nail32','3253',1),('Nephrolithiasis33','2929',1),('Nephronophthisis34','2836',1),('Nephronophthisis34','2843',2),('Nephronophthisis34','2858',3),('Nephronophthisis34','2862',4),('Nephronophthisis34','2870',5),('Nephronophthisis34','2889',6),('Nephronophthisis34','2891',7),('Nephrotic35','1003',1),('Nephrotic35','1019',2),('Nephrotic35','1026',5),('Nephrotic35','1035',6),('Nephrotic35','1042',7),('Nephrotic35','1057',8),('Nephrotic35','1061',9),('Nephrotic35','1088',10),('Nephrotic35','1090',11),('Nephrotic35','1100',12),('Nephrotic35','3604',3),('Nephrotic35','3615',4),('Polycystic36','2741',1),('Polycystic37','2725',1),('Polycystic38','2739',1),('Renal39','RDG012',1),('Renal40','3000',1),('Renal40','3016',2),('Renal40','3028',3),('Renal40','3037',4),('SRNS:41','3604',1),('STECHUS','xSTECHUS',1),('Tuberous42','3276',1),('VASRDG','1383',1),('VASRDG','1396',2),('Von43','3282',1);
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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdr_hnf1b_misc`
--

LOCK TABLES `rdr_hnf1b_misc` WRITE;
/*!40000 ALTER TABLE `rdr_hnf1b_misc` DISABLE KEYS */;
INSERT INTO `rdr_hnf1b_misc` VALUES (3,733,1,1,1,'small kidney',1,1,1,4,0,NULL);
/*!40000 ALTER TABLE `rdr_hnf1b_misc` ENABLE KEYS */;
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
INSERT INTO `rdr_prd_code` VALUES ('1003','Adult Nephrotic syndrome - no histology',0,1,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','52254009','Nephrotic syndrome (disorder)','52254009 | Nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',2,'','','N049','Nephrotic syndrome, unspecified',''),('1019','Nephrotic syndrome of childhood - steroid sensitive - no histology',0,1,0,0,1,0,0,0,0,'Defined by response to steroid therapy.','445119005','Steroid sensitive nephrotic syndrome of childhood (disorder)','445119005 | Steroid sensitive nephrotic syndrome of childhood |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',3,'','','N049','Nephrotic syndrome, unspecified',''),('1026','Congenital nephrotic syndrome (CNS) - no histology',0,1,0,0,1,0,0,0,0,'Defined by response to steroid therapy.\nClinical history: Onset in 1st 3 months after birth.','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome (disorder) |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',6,'','for background information see:\nhttp://omim.org/entry/256300 about nephrotic syndrome, type 1; NPHS1\nhttp://omim.org/entry/602716 about nephrin; NPHS1\nhttp://omim.org/entry/600995 about nephrotic synd','N049','Nephrotic syndrome, unspecified','CNS'),('1035','Congenital nephrotic syndrome (CNS) - Finnish type - no histology',0,1,0,0,1,0,0,0,1,'Mutation in the gene encoding nephrin (NPHS1) on chromosome 19q13.1.\nClinical history: Onset in 1st 3 months after birth.','197601003','Finnish congenital nephrotic syndrome (disorder)','197601003 | Finnish congenital nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',7,'','http://omim.org/entry/256300 about nephrotic syndrome, type 1; NPHS1\nhttp://omim.org/entry/602716 about nephrin; NPHS1','N049','Nephrotic syndrome, unspecified','CNS'),('1042','Congenital nephrotic syndrome (CNS) - Finnish type - histologically proven',1,1,0,0,1,0,0,0,1,'Mutation in the gene encoding nephrin (NPHS1) on chromosome 19q13.1.\nClinical history: Onset in 1st 3 months after birth.','197601003','Finnish congenital nephrotic syndrome (disorder)','97601003 | Finnish congenital nephrotic syndrome |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',19,'Glomerulonephritis; histologically examined',8,'','http://omim.org/entry/256300\nhttp://omim.org/entry/602716 about nephrin; NPHS1','N049','Nephrotic syndrome, unspecified','CNS'),('1057','Congenital nephrotic syndrome (CNS) - diffuse mesangial sclerosis',1,1,0,0,1,0,0,0,1,'Often associated with mutation in the Wilms tumor suppressor gene (WT1) on chromosome 11p13.\nClinical history: Onset in 1st 3 months after birth.','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome |:\n47429007 | Associated with | = 111406002 | Diffuse mesangial sclerosis |','Glomerular disease',19,'Glomerulonephritis; histologically examined',9,'','for background information see:\nhttp://omim.org/entry/256370 about nephrotic syndrome, type 4; NPHS4\nhttp://omim.org/entry/607102 about WT1 gene; WT1','N049','Nephrotic syndrome, unspecified','CNS'),('1061','Congenital nephrotic syndrome (CNS) - focal segmental glomerulosclerosis (FSGS)',1,1,0,0,1,0,0,0,1,'Clinical history: Onset in 1st 3 months after birth.\nOften associated with NPHS2 mutations.','236384008','Congenital nephrotic syndrome with focal glomerulosclerosis (disorder)','','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',10,'','http://omim.org/entry/600995','N071','Focal and segmental glomerular lesions',''),('1074','Denys-Drash syndrome',0,1,0,1,0,0,0,0,1,'Mutation in the WT1 gene.\nClinical history: Onset in 1st 3 months after birth.','236385009','Drash syndrome (disorder)','','Glomerular disease',99,'Other identified renal disorders',11,'','http://omim.org/entry/194080\nhttp://omim.org/entry/607102 about WT1 gene; WT1','N048','Nephrotic syndrome, other',''),('1088','Congenital nephrotic syndrome (CNS) - congenital infection',0,1,0,0,1,0,0,0,0,'Clinical history:\nOnset in 1st 3 months after birth. Infection as a cause of congenital nephrotic syndrome is rare, especially in Europe.\nThis PRD should not be used for hepatitis C related nephropathy or autoimmune disease,\nboth of which are coded elsewhere.\n','48796009','Congenital nephrotic syndrome (disorder)','48796009 | Congenital nephrotic syndrome |:\n47429007 | Associated with | = ((40733004 | Infectious disease |)\nAND (! 278929008 | Congenital hepatitis C infection |)\nAND (! < 52079000 | Congenital huma','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',12,'','','N049','Nephrotic syndrome, unspecified',''),('1090','Minimal change nephropathy - no histology',0,0,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','44785005','Minimal change disease (disorder)','44785005 | Minimal change disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',13,'','','N050','Unspecified nephritic syndrome, minor glomerular abnormality','MCN'),('1100','Minimal change nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'A history of heavy proteinuria at some point is required.','44785005','Minimal change disease (disorder)','44785005 | Minimal change disease |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',19,'Glomerulonephritis; histologically examined',14,'','','N050','Unspecified nephritic syndrome, minor glomerular abnormality','MCN'),('1116','IgA nephropathy - no histology',0,1,0,0,0,0,0,0,0,'In the absence of a renal histopathology, consider alternative PRDs\neg postinfection glomerulonephritis before choosing this code.\nNote that Henoch-Schönlein nephritis has a separate PRD.','236407003','IgA nephropathy (disorder)','236407003 | IgA nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',15,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy\n'),('1128','IgA nephropathy - histologically proven',1,0,0,0,0,0,0,0,0,'IgA must be demonstrated by renal histopathology.\nIgA in skin histopathology or raised serum IgA concentration are not sufficient for this PRD.\nNote that Henoch-Schönlein nephritis is coded separately.','236407003','IgA nephropathy (disorder)','236407003 | IgA nephropathy |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',16,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1137','Familial IgA nephropathy - no histology',0,1,1,0,0,0,0,0,0,'With histological evidence in a first degree relative and a compatible clinical setting in the patient,\nthis PRD allows nephrologists to record their preferred diagnosis of IgA nephropathy even in the absence of a renal histopathology.','445404003','Familial immunoglobulin A nephropathy (disorder)','445404003 | Familial immunoglobulin A nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',17,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1144','Familial IgA nephropathy - histologically proven',1,0,1,0,0,0,0,0,0,'IgA must be demonstrated by renal histopathology.\nIgA in skin histopathology or raised serum IgA concentration are not sufficient for this PRD.\n','445404003','Familial immunoglobulin A nephropathy (disorder)','445404003 | Familial immunoglobulin A nephropathy |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',18,'','for background information see:\nhttp://omim.org/entry/161950\nhttp://omim.org/entry/613944','N028','Recurrent and persistent haematuria, other','IgA IgAN immunoglobulin A nephropathy'),('1159','IgA nephropathy secondary to liver cirrhosis - no histology',0,1,0,0,0,0,0,0,0,'','282364005','IgA nephropathy associated with liver disease (disorder)','282364005 | IgA nephropathy associated with liver disease |: 42752001 | Due to | = 19943007 | Cirrhosis of liver |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',19,'','','N028 AND K746','Recurrent and persistent haematuria, other AND Other and unspecified cirrhosis of liver','IgA IgAN immunoglobulin A nephropathy'),('1163','IgA nephropathy secondary to liver cirrhosis - histologically proven',1,1,0,0,0,0,0,0,0,'','282364005','IgA nephropathy associated with liver disease (disorder)','282364005 | IgA nephropathy associated with liver disease |: 42752001 | Due to | = 19943007 | Cirrhosis of liver |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',12,'IgA nephropathy (proven by immunofluorescence, not code 76 or 85)',20,'','','N028 AND K746','Recurrent and persistent haematuria, other AND Other and unspecified cirrhosis of liver','IgA IgAN immunoglobulin A nephropathy'),('1171','IgM - associated nephropathy',1,0,0,0,0,0,0,0,0,'The presence of IgM should be demonstrated by IF and the presence of deposits compatible with\nimmunoglobulins in the same region of the glomerulus should be seen on EM.','236411009','IgM nephropathy (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',21,'','','N053','Diffuse mesangial proliferative glomerulonephritis',''),('1185','Membranous nephropathy - idiopathic',1,0,0,0,0,1,0,0,0,'May be associated with antibodies to the phospholipase A2 receptor.\nThis PRD should not be used for SLE related nephropathies which are coded elsewhere.','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis | ','Glomerular disease',14,'Membranous nephropathy',22,'','for background information see:\nhttp://omim.org/entry/604939 about phospholipase A2 receptor 1; PLA2R1','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN'),('1192','Membranous nephropathy - malignancy associated',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n47429007 | Associated with | = << 363346000 | Malignant neoplastic disease |','Glomerular disease',14,'Membranous nephropathy',23,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN cancer'),('1205','Membranous nephropathy - drug induced',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n246075003 | causative agent | = < 410942007 | Drug or medicament |','Glomerular disease',14,'Membranous nephropathy',24,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN medicine'),('1214','Membranous nephropathy - infection associated',1,1,0,0,0,0,0,0,0,'','197590001','Nephrotic syndrome with membranous glomerulonephritis (disorder)','197590001 | Nephrotic syndrome with membranous glomerulonephritis |:\n47429007 | Associated with | = < 40733004 | Infectious disease | ','Glomerular disease',14,'Membranous nephropathy',25,'','','N042','Nephrotic syndrome, diffuse membranous glomerulonephritis','MN infection'),('1222','Mesangiocapillary glomerulonephritis type 1',1,0,0,0,0,0,0,0,0,'Often associated with cryoglobulinaemia.','75888001','Mesangiocapillary glomerulonephritis, type I (disorder)','','Glomerular disease',15,'Membrano-proliferative GN; type I (proven by immunofluorescence / electron microscopy, not code 84 or 89)',26,'','for background information see:\nhttp://omim.org/entry/305800','N055','Diffuse mesangiocapillary glomerulonephritis','MCGN MPGN membranoproliferative'),('1233','Mesangiocapillary glomerulonephritis type 2 (dense deposit disease)',1,0,0,0,0,0,0,0,0,'','59479006','Mesangiocapillary glomerulonephritis, type II (disorder)','','Glomerular disease',13,'Dense deposit disease; membrano-proliferative GN; type II (proven by immunofluorescence / electron microscopy)',27,'','for background information see:\nhttp://omim.org/entry/305800','N056','Unspecified nephritic syndrome, dense deposit disease','MCGN MPGN membranoproliferative DD'),('1246','Mesangiocapillary glomerulonephritis type 3',1,0,0,0,0,0,0,0,0,'','236409000','Mesangiocapillary glomerulonephritis type III (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',28,'','for background information see:\nhttp://omim.org/entry/305800','N055','Diffuse mesangiocapillary glomerulonephritis','MCGN MPGN membranoproliferative'),('1251','Idiopathic rapidly progressive (crescentic) glomerulonephritis',1,0,0,0,0,0,0,0,0,'This PRD should not be used for other specific PRDs e.g. IgA, vasculitis or Goodpastures syndrome.','236398000','Crescentic glomerulonephritis (disorder)','<< 236398000 |Crescentic glomerulonephritis |\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',16,'Crescentic (extracapillary) glomerulonephritis (type I, II, III)',29,'','','N057','Diffuse concentric glomerulonephritis','RPGN'),('1267','Primary focal segmental glomerulosclerosis (FSGS)',1,0,0,0,0,0,0,0,0,'','236403004','Focal segmental glomerulosclerosis (disorder)','<< 236403004 | Focal segmental glomerulosclerosis |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',30,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions',''),('1279','Familial focal segmental glomerulosclerosis (FSGS) - autosomal recessive - no histology',0,0,1,0,0,0,0,0,1,'This PRD can be used if there is a compatible family history with renal histological evidence in a first degree relative but not in the patient.','445388002','Autosomal recessive focal segmental glomerulosclerosis (disorder)','445388002 | Autosomal recessive focal segmental glomerulosclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',50,'Hereditary/Familial nephropathy - type unspecified',31,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AR'),('1280','Familial focal segmental glomerulosclerosis (FSGS) - autosomal recessive - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should be used if there is a compatible family history and renal histological evidence in the patient.','445388002','Autosomal recessive focal segmental glomerulosclerosis (disorder)','445388002 | Autosomal recessive focal segmental glomerulosclerosis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',32,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AR'),('1298','Familial focal segmental glomerulosclerosis (FSGS) - autosomal dominant - no histology',0,0,1,0,0,0,0,0,1,'This PRD can be used if there is a compatible family history with renal histological evidence in a first degree relative but not in the patient.','444977005','Autosomal dominant focal segmental glomerulosclerosis (disorder)','444977005 | Autosomal dominant focal segmental glomerulosclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',50,'Hereditary/Familial nephropathy - type unspecified',33,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AD'),('1308','Familial focal segmental glomerulosclerosis (FSGS) - autosomal dominant - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should be used if there is a compatible family history and renal histological evidence in the patient.','444977005','Autosomal dominant focal segmental glomerulosclerosis (disorder)','444977005 | Autosomal dominant focal segmental glomerulosclerosis |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',11,'Focal segmental glomerulosclerosis with nephrotic syndrome in children',34,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS AD'),('1312','Focal segmental glomerulosclerosis (FSGS) secondary to obesity - no histology',0,0,0,1,1,0,0,0,0,'Obesity on clinical examination.\nThis PRD should be used with great caution.\nIt allows a nephrologist to give an opinion in the absence of the histological proof which is required to substantiate a diagnosis of FSGS.\nAn alternative PRD eg Adult Nephrotic syndrome - no histology should be considered.\n','236403004','Focal segmental glomerulosclerosis (disorder)','236403004 | Focal segmental glomerulosclerosis (disorder) |:\n47429007 | Associated with | = 414916001 | Obesity |,\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',35,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS fat overweight'),('1320','Focal segmental glomerulosclerosis (FSGS) secondary to obesity - histologically proven',1,0,0,1,1,0,0,0,0,'Obesity on clinical examination.','236403004','Focal segmental glomerulosclerosis (disorder)','236403004 | Focal segmental glomerulosclerosis (disorder) |:\n47429007 | Associated with | = 414916001 | Obesity |,\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',17,'Focal segmental glomerulosclerosis with nephrotic syndrome in adults',36,'','for background information see:\nhttp://omim.org/entry/603278 about FSGS1\nhttp://omim.org/entry/603965 about FSGS2\nhttp://omim.org/entry/607832 about FSGS3\nhttp://omim.org/entry/612551 about FSGS4\nhttp','N051','Focal and segmental glomerular lesions','FSGS fat overweight'),('1331','Diffuse endocapillary glomerulonephritis',1,0,0,0,0,0,0,0,0,'Typical of post-infectious glomerulonephritis.','3704008','Diffuse endocapillary proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',37,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('1349','Mesangial proliferative glomerulonephritis ',1,0,0,0,0,0,0,0,0,'This PRD should not be used for SLE related nephropathies which are coded elsewhere.','35546006','Mesangial proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',38,'','','N033','Diffuse mesangial proliferative glomerulonephritis','MPGN'),('1354','Focal and segmental proliferative glomerulonephritis',1,0,0,0,0,0,0,0,0,'','83866005','Focal AND segmental proliferative glomerulonephritis (disorder)','','Glomerular disease',19,'Glomerulonephritis; histologically examined',39,'','','N071','Focal and segmental glomerular lesions','FSGN FSPGN FSGS'),('1365','Glomerulonephritis - secondary to other systemic disease',1,0,0,0,0,0,0,0,0,'Examples of the systemic conditions include malignancy and liver disease but this PRD should not be used if a more accurate one is available.','36171008','Glomerulonephritis (disorder)','36171008 | Glomerulonephritis |:\n42752001 | Due to | = 56019007 | Systemic disease |\n','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',40,'','','N059','Unspecified nephritic syndrome, unspecified','GN 2y'),('1377','Glomerulonephritis - histologically indeterminate',1,0,0,0,0,0,0,0,0,'Use this PRD if no other seems appropriate even after considering all the evidence and the renal histopathology report.','36171008','Glomerulonephritis (disorder)','(36171008 | Glomerulonephritis |)\nAND\n(372269006 | Histologic type cannot be determined |)','Glomerular disease',19,'Glomerulonephritis; histologically examined',41,'','','N059','Unspecified nephritic syndrome, unspecified','GN'),('1383','Systemic vasculitis - ANCA negative - histologically proven',1,0,0,0,0,1,0,0,0,'','46956008','Systemic vasculitis (disorder)','(46956008 | Systemic vasculitis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |)\nAND\n(310255003 | Anti-neutrophil cytoplasmic antibody negative |)','Glomerular disease',70,'Renal vascular disease - type unspecified',42,'','','I776','Arteritis, unspecified','mPAN PAN vasculitis'),('1396','Systemic vasculitis - ANCA positive - no histology',0,1,0,1,1,1,0,0,0,'Must be clinical evidence to suggest a vasculitis.','46956008','Systemic vasculitis (disorder)','(46956008 | Systemic vasculitis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |\nAND\n310256002 | Antineutrophil cytoplasmic antibody positive |','Glomerular disease',74,'Wegeners granulomatosis',43,'','','I776','Arteritis, unspecified','mPAN PAN  PR3 MPO vasculitis'),('1401','Wegeners granulomatosis - no histology',0,1,0,1,0,1,0,0,0,'Involvement of upper or lower respiratory tract or the oral cavity in addition to renal involvement.','195353004','Wegeners granulomatosis (disorder)','195353004 | Wegeners granulomatosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',44,'','http://omim.org/entry/177020','M313','Wegeners granulomatosis','mPAN PAN PR3 vasculitis'),('1417','Wegeners granulomatosis - histologically proven',1,0,0,0,0,1,0,0,0,'In a compatible clinical setting, histological diagnosis from nasal or respiratory tract can support this PRD if renal histology is not available.','195353004','Wegeners granulomatosis (disorder)','195353004 | Wegeners granulomatosis |:\n418775008 | Finding method | = << 7246002| Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',45,'','http://omim.org/entry/177020','M313','Wegeners granulomatosis','mPAN PAN PR3 vasculitis'),('1429','Microscopic polyangiitis - histologically proven',1,0,0,0,0,1,0,0,0,'','239928004','Microscopic polyarteritis nodosa (disorder)','239928004 | Microscopic polyarteritis nodosa |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',46,'','','M300','Polyarteritis nodosa','mPAN PAN vasculitis'),('1438','Churg-Strauss syndrome - no histology',0,1,0,0,0,1,0,0,0,'Peripheral blood eosinophilia is often found.','82275008','Allergic granulomatosis angiitis (disorder)','82275008 | Allergic granulomatosis angiitis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',47,'','','M301','Polyarteritis with lung involvement [Churg-Strauss]','vasculitis'),('1440','Churg-Strauss syndrome - histologically proven',1,0,0,0,0,1,0,0,0,'','82275008','Allergic granulomatosis angiitis (disorder)','82275008 | Allergic granulomatosis angiitis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',74,'Wegeners granulomatosis',48,'','','M301','Polyarteritis with lung involvement [Churg-Strauss]','vasculitis'),('1455','Polyarteritis nodosa',0,0,0,0,0,0,0,1,0,'ANCA may be positive but specific tests for anti MPO and anti PR3 are negative.\nPAN can be secondary to other disorders.\nUse this PRD for classical PAN proven with imaging.\nDo not use this PRD for any type of microscopic polyangiitis.\n','155441006','Polyarteritis nodosa (disorder)','<< 155441006 | Polyarteritis nodosa |\nAND\n! < 239928004 | Microscopic polyarteritis nodosa |\n','Glomerular disease',73,'Renal vascular disease due to polyarteritis',49,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','PAN vasculitis'),('1464','Anti-Glomerular basement membrane (GBM) disease / Goodpastures syndrome - no histology',0,0,0,0,0,1,0,0,0,'','236506009','Goodpastures disease (disorder)','236506009 | Goodpastures disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',86,'Goodpasture’s Syndrome ',50,'','http://omim.org/entry/233450','M310','Hypersensitivity angiitis','GBM aGBM linear IgG'),('1472','Anti-Glomerular basement membrane (GBM) disease / Goodpastures syndrome - histologically proven',1,0,0,0,0,0,0,0,0,'','50581000','Goodpastures syndrome','<< 50581000 | Goodpastures syndrome |:\n418775008 | Finding method | = << 7246002| Kidney biopsy |','Glomerular disease',86,'Goodpasture’s Syndrome ',51,'','http://omim.org/entry/233450','M310','Hypersensitivity angiitis','GBM aGBM linear IgG'),('1486','Systemic lupus erythematosus / nephritis - no histology',0,1,0,0,0,1,0,0,0,'Evidence of renal disease manifested by at least either proteinuria or haematuria.\n','68815009','Systemic lupus erythematosus glomerulonephritis syndrome (disorder)','68815009 | Systemic lupus erythematosus glomerulonephritis syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',84,'Lupus erythematosus',52,'','for background information see:\nhttp://omim.org/entry/152700','M321D','Systemic lupus erythematosus with organ or sys involv','SLE LE lupus'),('1493','Systemic lupus erythematosus / nephritis - histologically proven',1,0,0,0,0,0,0,0,0,'','68815009','Systemic lupus erythematosus glomerulonephritis syndrome (disorder)','68815009 | Systemic lupus erythematosus glomerulonephritis syndrome |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',84,'Lupus erythematosus',53,'','for background information see:\nhttp://omim.org/entry/152700','M321D','Systemic lupus erythematosus with organ or sys involv','SLE LE lupus'),('1504','Henoch-Schönlein purpura / nephritis - no histology',0,1,0,1,0,0,0,0,0,'Evidence of renal disease manifested by at least either proteinuria or haematuria.\nMust be clinical evidence or history compatible with HSP.','191306005','Henoch-Schönlein purpura (disorder)','191306005 | Henoch-Schönlein purpura |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',85,'Henoch-Schoenlein purpura',54,'','','D690','Allergic purpura','HSP vasculitis leukocytoclastic'),('1515','Henoch-Schönlein purpura / nephritis - histologically proven',1,1,0,1,0,0,0,0,0,'Must be clinical evidence or history compatible with HSP.','191306005','Henoch-Schönlein purpura (disorder)','191306005 | Henoch-Schönlein purpura |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',85,'Henoch-Schoenlein purpura',55,'','','D690','Allergic purpura','HSP vasculitis leukocytoclastic'),('1527','Renal scleroderma / systemic sclerosis - no histology',0,1,0,0,0,0,0,0,0,'Cutaneous and systemic symptoms with autoantibodies.','89155008','Systemic sclerosis','(89155008 | Systemic sclerosis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Glomerular disease',87,'Systemic sclerosis (scleroderma)',56,'','','M349','Systemic sclerosis, unspecified','SS PSS'),('1536','Renal scleroderma / systemic sclerosis - histologically proven',1,1,0,0,0,0,0,0,0,'Cutaneous and systemic symptoms with autoantibodies.','236502006','Renal involvement in scleroderma (disorder)','(236502006 | Renal involvement in scleroderma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |)','Glomerular disease',87,'Systemic sclerosis (scleroderma)',57,'','','M348','Other forms of systemic sclerosis','SS PSS'),('1543','Essential mixed cryoglobulinaemia - no histology',0,0,0,0,1,0,0,0,0,'','239947001','Essential mixed cryoglobulinemia (disorder)','239947001 | Essential mixed cryoglobulinemia |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',58,'','for background information see:\nhttp://omim.org/entry/123550\n','D891','Cryoglobulinaemia','cold agglutination'),('1558','Essential mixed cryoglobulinaemia - histologically proven',1,0,0,0,1,0,0,0,0,'','239947001','Essential mixed cryoglobulinemia (disorder)','239947001 | Essential mixed cryoglobulinemia |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',59,'','','D891','Cryoglobulinaemia','cold agglutination'),('1562','Cryoglobulinaemia secondary to hepatitis C - no histology',0,0,0,0,0,1,0,0,0,'','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = << 50711007 | Viral hepatitis C |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',60,'','for background information see:\nhttp://omim.org/entry/609532','D891 AND B171\nOR\nD891 AND B182','Cryoglobulinaemia AND Acute hepatitis C\nOR\nCryoglobulinaemia AND Chronic hepatitis C','cold agglutination liver infection'),('1570','Cryoglobulinaemia secondary to hepatitis C - histologically proven',1,0,0,0,0,1,0,0,0,'','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = << 50711007 | Viral hepatitis C |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',61,'','for background information see:\nhttp://omim.org/entry/609532','D891 AND B171\nOR\nD891 AND B182','Cryoglobulinaemia AND Acute hepatitis C\nOR\nCryoglobulinaemia AND Chronic hepatitis C','cold agglutination liver infection'),('1589','Cryoglobulinaemia secondary to systemic disease - no histology',0,0,0,0,0,1,0,0,0,'This PRD should not be used for hepatitis C related nephropathy or autoimmune disease. Alternative PRDs are available.','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n42752001 | due to | = ((56019007 | Systemic disease |)\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',62,'','','D891','Cryoglobulinaemia','cold agglutination'),('1591','Cryoglobulinaemia secondary to systemic disease - histologically proven',1,0,0,0,0,1,0,0,0,'This PRD should not be used for hepatitis C related nephropathy or autoimmune disease. Alternative PRDs are available.','30911005','Cryoglobulinemia (disorder)','30911005 | Cryoglobulinemia |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Glomerular disease',78,'Cryoglobulinaemic glomerulonephritis',63,'','','D891','Cryoglobulinaemia','cold agglutination'),('1602','Primary reflux nephropathy - sporadic',0,0,0,0,0,0,0,1,0,'A positive family history excludes this diagnosis.\nThis PRD should not normally be used if there is a positive family history.\nIn that case use PRD Familial reflux nephropathy.\n','197764002','Non-obstructive reflux-associated chronic pyelonephritis (disorder)','','Tubulo-interstitial disease',24,'Pyelonephritis due to vesico-ureteric reflux without obstruction',64,'','','N110','Nonobstructive reflux-associated chronic pyelonephritis','CPN PN TIN'),('1618','Familial reflux nephropathy',0,0,1,0,0,0,0,1,0,'A positive family history is required for this PRD.','522551000000101','Familial non-obstructive reflux-associated chronic pyelonephritis (disorder)','','Tubulo-interstitial disease',24,'Pyelonephritis due to vesico-ureteric reflux without obstruction',65,'http://ghr.nlm.nih.gov/gene/ROBO2\nhttp://ghr.nlm.nih.gov/gene/SOX17','http://omim.org/entry/193000 about VUR1\nhttp://omim.org/entry/610878 about VUR2\nhttp://omim.org/entry/613674 about VUR3','N110','Nonobstructive reflux-associated chronic pyelonephritis','CPN PN TIN'),('1625','Congenital dysplasia / hypoplasia',0,0,0,0,0,0,0,1,0,'','204949001','Renal dysplasia (disorder)','','Tubulo-interstitial disease',60,'Renal hypoplasia (congenital) - type unspecified',66,'','','Q614','Renal dysplasia','cystic cysts cyst'),('1639','Multicystic dysplastic kidneys',0,0,0,0,0,0,0,1,1,'Associated with the HNF1B gene.','82525005','Congenital cystic kidney disease (disorder)','','Tubulo-interstitial disease',40,'Cystic kidney disease - type unspecified',67,'http://ghr.nlm.nih.gov/gene/CDC5L','http://omim.org/entry/143400\nhttp://omim.org/entry/602868 about cell division cycle 5, s. pombe, homolog of; CDC5L','Q619','Cystic kidney disease, unspecified','cystic cysts cyst'),('1641','Dysplasia due to fetal ACE-inhibitor exposure',0,1,0,0,0,0,0,1,0,'','519331000000100','Renal dysplasia due to fetus affected by maternal use of angiotensin converting enzyme inhibitor (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',68,'','for background information see:\nhttp://omim.org/entry/106180','Q614','Renal dysplasia','ACE ACEi ACE-i'),('1656','Glomerulocystic disease',1,0,0,0,0,0,0,0,1,'Associated with the HNF1B gene.','253864004','Familial hypoplastic, glomerulocystic kidney (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',70,'','http://omim.org/entry/609886','Q605','Renal hypoplasia, unspecified','cystic cysts cyst'),('1660','Congenital pelvi-ureteric junction obstruction',0,0,0,0,0,0,0,1,0,'','373584008','Congenital pelviureteric junction obstruction (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',71,'','http://omim.org/entry/143400','Q621','Atresia and stenosis of ureter','hydronephrosis'),('1673','Congenital vesico-ureteric junction obstruction',0,0,0,0,0,0,0,1,0,'','373585009','Congenital ureterovesical obstruction (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',72,'','http://omim.org/entry/193000 about VUR1\nhttp://omim.org/entry/610878 about VUR2\nhttp://omim.org/entry/613674 about VUR3','Q621','Atresia and stenosis of ureter','hydronephrosis'),('1687','Posterior urethral valves',0,0,0,0,0,0,0,1,0,'','253900005','Congenital posterior urethral valves (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',73,'','','Q643','Other atresia and stenosis of urethra and bladder neck',''),('1694','Syndrome of agenesis of abdominal muscles - prune belly syndrome',0,0,0,1,0,0,0,1,0,'','5187006','Prune belly syndrome ','','Tubulo-interstitial disease',66,'Syndrome of agenesis of abdominal muscles (Prune Belly)',74,'','http://omim.org/entry/100100\nhttp://omim.org/entry/264140','Q794','Prune belly syndrome',''),('1706','Congenital neurogenic bladder',0,0,0,0,0,0,0,1,0,'Investigation will usually include urodynamic studies.','445387007','Congenital neurogenic urinary bladder (finding)','','Tubulo-interstitial disease',99,'Other identified renal disorders',75,'','','N319','Neuromuscular dysfunction of bladder, unspecified','hydronephrosis obstruction CPN PN TIN'),('1710','Bladder exstrophy',0,0,0,1,0,0,0,0,0,'','61758007','Exstrophy of bladder sequence (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',76,'','for background information see:\nhttp://omim.org/entry/600057','Q641','Exstrophy of urinary bladder',''),('1723','Megacystis-megaureter',0,0,0,0,0,0,0,1,0,'Bilateral hydroureteronephrosis; large, smooth, thin-walled bladder without urethral obstruction, gross reflux.','253904001','Megacystis-megaureter syndrome (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',77,'','','Q622','Congenital megaloureter',''),('1734','Oligomeganephronia',1,0,0,0,0,0,0,0,0,'Reduced number of enlarged nephrons.','18417009','Oligomeganephronic hypoplasia of kidney (disorder)','','Tubulo-interstitial disease',61,'Oligomeganephronic hypoplasia',78,'','for background information see:\nhttp://omim.org/entry/246560\nhttp://omim.org/entry/137920','Q605','Renal hypoplasia, unspecified',''),('1747','Renal papillary necrosis - cause unknown',0,0,0,0,0,0,0,1,0,'','90241004','Papillary necrosis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorder',79,'','','N172','Acute renal failure with medullary necrosis',''),('1752','Acquired obstructive uropathy / nephropathy',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',80,'','','N138\nOR\nN139','Other obstructive and reflux uropathy\nOR\nObstructive and reflux uropathy, unspecified','hydronephrosis obstruction CPN PN TIN'),('1768','Acquired obstructive nephropathy due to neurogenic bladder',0,0,0,1,0,0,0,1,0,'The diagnosis is made by imaging plus either clinical examination or urodynamic studies.','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','acquired obstructive nephropathy |:\n42752001 |due to | = 398064005 | Neurogenic bladder |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',81,'','','N319','Neuromuscular dysfunction of bladder, unspecified','hydronephrosis obstruction CPN PN TIN'),('1775','Obstructive nephropathy due to prostatic hypertrophy',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 266569009 | Benign prostatic hyperplasia |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',82,'','','N138 AND N40X','Other obstructive and reflux uropathy AND Hyperplasia of prostate','hydronephrosis obstruction prostate CPN PN TIN'),('1781','Obstructive nephropathy due to prostate cancer',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 399068003 | Malignant tumor of prostate |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',83,'','for background information see:\nhttp://omim.org/entry/176807','N138 AND C61X','Other obstructive and reflux uropathy AND Malignant neoplasm of prostate','hydronephrosis obstruction prostatic malignancy CPN PN TIN'),('1799','Obstructive nephropathy due to bladder cancer',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = 399326009 | Malignant tumor of urinary bladder |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',84,'','for background information see:\nhttp://omim.org/entry/109800','N138 AND C679','Other obstructive and reflux uropathy AND Malignant neoplasm of bladder, unspecified','hydronephrosis obstruction prostatic malignancy CPN PN TIN'),('1809','Obstructive nephropathy due to other malignancies',0,0,0,0,0,0,0,1,0,'','522591000000109','Nephropathy due to acquired urinary tract obstruction (disorder)','86249007 | Obstructive nephropathy |:\n42752001 | due to | = << 363346000 | Malignant neoplastic disease |\nAND 42752001 | due to | = ! 266569009 | Benign prostatic hyperplasia |\nAND 42752001 | due to |','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',85,'','','N138','Other obstructive and reflux uropathy','hydronephrosis obstruction malignancy CPN PN TIN'),('1813','Idiopathic retroperitoneal fibrosis',0,0,0,0,0,0,0,1,0,'','197808006','Idiopathic retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',86,'','for background information see:\nhttp://omim.org/entry/228800','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction  CPN PN TIN'),('1821','Retroperitoneal fibrosis secondary to malignancies',0,1,0,0,0,0,0,1,0,'','236017004','Malignant retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',87,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction malignancy CPN PN TIN'),('1832','Calculus nephropathy / urolithiasis',0,0,0,0,0,0,0,1,0,'','95566004','Urolithiasis (disorder)','','Tubulo-interstitial disease',25,'Pyelonephritis due to urolithiasis ',90,'','','N209','Urinary calculus, unspecified','stone calculus '),('1845','Calcium oxalate urolithiasis',0,0,0,0,1,0,0,1,0,'','444717006','Calcium oxalate urolithiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',91,'','for background information see:\nhttp://omim.org/entry/167030','N209','Urinary calculus, unspecified','stone calculus '),('1850','Enteric hyperoxaluria ',0,1,0,0,1,0,0,0,0,'','37497004','Enteric hyperoxaluria (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',92,'','','E748','Other specified disorders of carbohydrate metabolism','stone calculus oxalate gut'),('1866','Magnesium ammonium phosphate (struvite) urolithiasis',0,0,0,0,1,0,0,1,0,'Associated with chronic infections with urease producing organisms.','444690001','Magnesium ammonium phosphate urolithiasis (disorder)','','Tubulo-interstitial disease',25,'Pyelonephritis due to urolithiasis ',93,'','','N209','Urinary calculus, unspecified','stone calculus'),('1878','Uric acid urolithiasis',0,0,0,0,1,0,0,1,0,'','267441009','Uric acid urolithiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',94,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','stone calculus urate  gout'),('1884','Tubulo interstitial nephritis - no histology',0,0,0,0,0,0,0,0,0,'This PRD can be used in an appropriate clinical setting, if there is convincing evidence of tubular dysfunction\ne.g. Fanconi syndrome, tubular proteinuria.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',95,'','','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1897','Tubulo interstitial nephritis - histologically proven',1,0,0,0,1,0,0,0,0,' ','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',96,'','','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1907','Familial interstitial nephropathy - no histology',0,0,1,0,0,0,0,0,1,'This PRD may be used if the diagnosis has been confirmed with a renal histology in at least one affected family member.\nThis PRD should not be used for familial nephropathies which have a more accurate PRD.\nA genetic test may help to make the diagnosis but is not mandatory for this PRD.\n','28689008','Interstitial nephritis (disorder)','28689008 | Interstitial nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',50,'Hereditary/Familial nephropathy - type unspecified',97,'','http://omim.org/entry/161900 about progression of renal failure','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1911','Familial interstitial nephropathy - histologically proven',1,0,1,0,0,0,0,0,1,'This PRD should not be used for familial nephropathies which have a more accurate PRD.','28689008','Interstitial nephritis (disorder)','28689008 | Interstitial nephritis |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',50,'Hereditary/Familial nephropathy - type unspecified',98,'','http://omim.org/entry/161900 about progression of renal failure','N12X','Tubulo-interstitial nephritis not spec as acute or chronic','TIN IN'),('1924','Tubulo interstitial nephritis associated with autoimmune disease - no histology',0,1,0,0,1,0,0,0,0,'A genetic test may help to make the diagnosis but is not mandatory for this PRD.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = << 85828009 | Autoimmune disease |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',99,'','','N12X AND M359','Tubulo-interstitial nephritis not spec as acute or chronic AND Systemic involvement of connective tissue, unspecified','TIN IN AI immune immunity'),('1930','Tubulo interstitial nephritis associated with autoimmune disease - histologically proven',1,1,0,0,1,0,0,0,0,'','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = << 85828009 | Autoimmune disease |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',100,'','','N12X AND M359','Tubulo-interstitial nephritis not spec as acute or chronic AND Systemic involvement of connective tissue, unspecified','TIN IN AI immune immunity'),('1948','Tubulo interstitial nephritis with uveitis (TINU) - no histology',0,0,0,1,0,0,0,0,0,'To use this PRD, there must be evidence of past or current uveitis.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = 128473001 | Uveitis |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',101,'','http://omim.org/entry/607665','N12X AND H209','Tubulo-interstitial nephritis not spec as acute or chronic AND Iridocyclitis, unspecified','TIN IN TINU eye ophthalmic '),('1953','Tubulo interstitial nephritis with uveitis (TINU) - histologically proven',1,0,0,1,0,0,0,0,0,'To use this PRD, there must be evidence of past or current uveitis.','428255004','Tubulointerstitial nephritis (disorder)','428255004 | Tubulointerstitial nephritis |:\n47429007 | Associated with | = 128473001 | Uveitis |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',30,'Interstitial nephritis (not pyelonephritis) due to other cause, or unspecified',102,'','http://omim.org/entry/607665','N12X AND H209','Tubulo-interstitial nephritis not spec as acute or chronic AND Iridocyclitis, unspecified','TIN IN TINU eye ophthalmic '),('1969','Renal sarcoidosis - no histology',0,1,0,0,0,0,0,0,0,'Any standard method of diagnosing sarcoid is acceptable.','37061001','Granulomatous sarcoid nephropathy (disorder)','37061001 | Granulomatous sarcoid nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',103,'','for background information see:\nhttp://omim.org/entry/181000','D868D','Sarcoidosis of other and combined sites','TIN IN granuloma granulomatous giant'),('1976','Renal sarcoidosis - histologically proven',1,1,0,0,0,0,0,0,0,'Histology from non renal tissue is acceptable.','37061001','Granulomatous sarcoid nephropathy (disorder)','37061001 | Granulomatous sarcoid nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',104,'','for background information see:\nhttp://omim.org/entry/181000','D868D','Sarcoidosis of other and combined sites','TIN IN granuloma granulomatous giant'),('1982','Aristolochic acid nephropathy (Balkan / Chinese herb / endemic nephropathy) - no histology',0,1,0,0,0,0,0,0,0,'','236514003','Toxic nephropathy (disorder)','236514003 | Toxic nephropathy |:\n246075003 | causative agent | = 8423004 | Aristolochia |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',94,'Balkan nephropathy',105,'','for background information see:\nhttp://omim.org/entry/124100','N144','Toxic nephropathy, not elsewhere classified','TIN IN'),('1995','Aristolochic acid nephropathy (Balkan / Chinese herb / endemic nephropathy) - histologically proven',1,1,0,0,0,0,0,0,0,'Compatible renal histopathology evidence is required to use this PRD but alone is insufficient because the appearances are not diagnostic.\nA clinical history consistent with exposure to Aristolochia or residence in an area in which this condition is endemic is also necessary.','236514003','Toxic nephropathy (disorder)','236514003 | Toxic nephropathy |:\n246075003 | causative agent | = 8423004 | Aristolochia (organism) |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',94,'Balkan nephropathy',106,'','for background information see:\nhttp://omim.org/entry/124100','N144','Toxic nephropathy, not elsewhere classified','TIN IN'),('2005','Drug-induced tubulointerstitial nephritis  - no histology',0,1,0,0,0,0,0,0,0,'','439990003','Drug-induced tubulointerstitial nephritis (disorder)','439990003 | Drug-induced tubulointerstitial nephritis |:\n418775008 | Finding method | = ! << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',107,'','','N142','Nephropathy induced by unspec drug medicament or biol subs','TIN IN drug medicine'),('2014','Drug-induced tubulointerstitial nephritis - histologically proven',1,1,0,0,0,0,0,0,0,'','439990003','Drug-induced tubulointerstitial nephritis (disorder)','439990003 | Drug-induced tubulointerstitial nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',108,'','','N142','Nephropathy induced by unspec drug medicament or biol subs','TIN IN drug medicine'),('2022','Nephropathy due to analgesic drugs - no histology',0,1,0,0,0,0,0,1,0,'','59400006','Analgesic nephropathy (disorder)','59400006 | Analgesic nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',31,'Nephropathy (interstitial) due to analgesic drugs',109,'','','N140','Analgesic nephropathy','TIN IN drug medicine'),('2033','Nephropathy due to analgesic drugs - histologically proven',1,1,0,0,0,0,0,1,0,'','59400006','Analgesic nephropathy (disorder)','59400006 | Analgesic nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',31,'Nephropathy (interstitial) due to analgesic drugs',110,'','','N140','Analgesic nephropathy','TIN IN drug medicine'),('2046','Nephropathy due to ciclosporin - no histology',0,1,0,0,0,0,0,0,0,'','519481000000106','Nephropathy induced by ciclosporin (disorder)','519481000000106 | Nephropathy due to ciclosporin |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',33,'Nephropathy (interstitial) due to cyclosporin A',111,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2051','Nephropathy due to ciclosporin - histologically proven',1,1,0,0,0,0,0,0,0,'','519481000000106','Nephropathy induced by ciclosporin (disorder)','519481000000106 | Nephropathy due to ciclosporin |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',33,'Nephropathy (interstitial) due to cyclosporin A',112,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2067','Nephropathy due to tacrolimus - no histology',0,1,0,0,0,0,0,0,0,'','519491000000108','Nephropathy induced by tacrolimus (disorder)','519491000000108 | Nephropathy due to tacrolimus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',113,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2079','Nephropathy due to tacrolimus - histologically proven',1,1,0,0,0,0,0,0,0,'','519491000000108','Nephropathy induced by tacrolimus (disorder)','519491000000108 | Nephropathy due to tacrolimus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',114,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine CNI'),('2080','Nephropathy due to aminoglycosides - no histology',0,1,0,0,0,0,0,0,0,'','519501000000102','Nephropathy induced by aminoglycosides (disorder)','519501000000102 | Nephropathy due to aminoglycosides |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',115,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine gentamicin netilmicin amikacin'),('2098','Nephropathy due to aminoglycosides - histologically proven',1,1,0,0,0,0,0,0,0,'','519501000000102','Nephropathy induced by aminoglycosides (disorder)','519501000000102 | Nephropathy due to aminoglycosides |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',116,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine gentamicin netilmicin amikacin'),('2108','Nephropathy due to amphotericin - no histology',0,1,0,0,0,0,0,0,0,'','519511000000100','Nephropathy induced by amphotericin (disorder)','519511000000100 | Nephropathy due to amphotericin |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',117,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine antifungal fungal fungus candida'),('2112','Nephropathy due to amphotericin - histologically proven',1,1,0,0,0,0,0,0,0,'','519511000000100','Nephropathy induced by amphotericin (disorder)','519511000000100 | Nephropathy due to amphotericin |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',118,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine antifungal fungal fungus candida'),('2120','Nephropathy due to cisplatin - no histology',0,1,0,0,0,0,0,0,0,'','53556002','Cis-platinum nephropathy (disorder)','53556002 | Cis-platinum nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',32,'Nephropathy (interstitial) due to cis-platinum',119,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine cancer malignancy'),('2131','Nephropathy due to cisplatin - histologically proven',1,1,0,0,0,0,0,0,0,'','53556002','Cis-platinum nephropathy (disorder)','53556002 | Cis-platinum nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',32,'Nephropathy (interstitial) due to cis-platinum',120,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine cancer malignancy'),('2149','Nephropathy due to lithium - no histology',0,1,0,0,0,0,0,0,0,'','4390004','Lithium nephropathy (disorder)','4390004 | Lithium nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',121,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine lithium bipolar'),('2154','Nephropathy due to lithium - histologically proven',1,1,0,0,0,0,0,0,0,'','4390004','Lithium nephropathy (disorder)','4390004 | Lithium nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',39,'Drug induced nephropathy (interstitial)',122,'','','N141','Nephropathy induc by other drugs meds and biolog subs','TIN IN drug medicine lithium bipolar'),('2165','Lead induced nephropathy - no histology',0,0,0,0,1,0,0,0,0,'To use this PRD there should be biochemical evidence of chronic lead intoxication\n(eg EDTA chelation) plus hyperuricaemia and hypertension.\nA single measurement of blood lead is insufficient.','519521000000106','Nephropathy induced by lead (disorder)','519521000000106 | Nephropathy induced by lead |:\n418775008 | Finding method | =\n! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',34,'Lead induced nephropathy (interstitial)',123,'','','N143','Nephropathy induced by heavy metals','TIN IN drug Pb metal heavy'),('2177','Lead induced nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'To use this PRD there should be biochemical evidence of chronic lead intoxication\n(eg EDTA chelation) plus hyperuricaemia and hypertension.\nA single measurement of blood lead is insufficient.','519521000000106','Nephropathy induced by lead (disorder)','519521000000106 | Nephropathy induced by lead |:\n 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',34,'Lead induced nephropathy (interstitial)',124,'','','N143','Nephropathy induced by heavy metals','TIN IN drug Pb metal heavy'),('2183','Acute urate nephropathy - no histology',0,1,0,0,1,0,0,0,0,'','236496000','Acute urate nephropathy (disorder)','236496000 | Acute urate nephropathy |:\n418775008 | Finding method | =\n!< 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',125,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','E790','Hyperuricaem without sign inflamm arthritis+tophaceous dis','TIN IN uric '),('2196','Acute urate nephropathy - histologically proven',1,1,0,0,1,0,0,0,0,'','236496000','Acute urate nephropathy (disorder)','236496000 | Acute urate nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',126,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','E790','Hyperuricaem without sign inflamm arthritis+tophaceous dis','TIN IN uric '),('2203','Chronic urate nephropathy - histologically proven',1,1,0,0,0,0,0,0,0,'Requires the presence of parenchymal kidney damage resulting from urate deposits.\nShould be distinguished from Uromodulin-associated nephropathy (PRD 2827).','190829000','Chronic urate nephropathy (disorder)','418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',128,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','TIN IN uric '),('2219','Radiation nephritis',0,1,0,0,0,0,0,0,0,'To use this PRD, there should be a history of radiation with the radiation field including the kidneys.','7725007','Radiation nephritis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',130,'','','T66X','Unspecified effects of radiation','TIN IN X ray X-Ray Xray ionising ionisation isotope implant radiology radiograph'),('2226','Renal / perinephric abscess',0,0,0,0,0,0,0,1,0,'','3321001\nOR\n80640009','Renal abscess (disorder)\nOR\nPerirenal abscess (disorder)','3321001 | Renal abscess |\nOR\n80640009 | Perirenal abscess |','Tubulo-interstitial disease',99,'Other identified renal disorders',131,'','','N151','Renal and perinephric abscess','TIN IN infection bacteria'),('2235','Renal tuberculosis',0,1,0,0,0,0,0,1,0,'A diagnosis of past or present tuberculosis of the kidney must have been made.','44323002','Tuberculosis of kidney (disorder)','','Tubulo-interstitial disease',91,'Tuberculosis',132,'','for background information see:\nhttp://omim.org/entry/607948','A181D','Tuberculosis of genitourinary system','TB Koch '),('2242','Leptospirosis',0,0,0,0,0,0,0,0,0,'A diagnosis of leptospirosis must have been made.\nThe diagnosis is normally supported by serology, culture or PCR.','77377001','Leptospirosis (disorder)','<< 77377001 | Leptospirosis |:\n418775008 | Finding method | = (<< 68793005 | Serologic test\n |)\nOR (<< 104178000 | Bacterial culture |)\nOR (<< 9718006 | Polymerase chain reaction analysis |) ','Tubulo-interstitial disease',99,'Other identified renal disorders',133,'','','A279','Leptospirosis, unspecified',''),('2257','Hantavirus nephropathy',1,1,0,0,0,0,0,0,0,'Diagnosis of hantavirus infection should have been confirmed by PCR.','102455002','Hemorrhagic nephroso-nephritis (disorder)','102455002 | Hemorrhagic nephroso-nephritis |:\nfinding method = 9718006 | Polymerase chain reaction analysis |','Tubulo-interstitial disease',99,'Other identified renal disorders',134,'','','A985D','Haemorrhagic fever with renal syndrome','virus viral'),('2261','Xanthogranulomatous pyelonephritis',0,0,0,0,0,0,0,1,0,'','38898003','Xanthogranulomatous pyelonephritis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',135,'','','N119','Chronic tubulo-interstitial nephritis, unspecified','XPN CPN PN TIN'),('2274','Nephropathy related to HIV - no histology',0,0,0,0,0,0,0,0,0,'This PRD requires evidence of HIV infection.','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n47429007 | associated with | = << 86406008 | Human immunodeficiency virus infection |\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',136,'','for background information see:\nhttp://omim.org/entry/609423\nhttp://omim.org/entry/612551\nhttp://omim.org/entry/607832','N289 AND B24X','Disorder of kidney and ureter, unspecified AND Unspecified human immunodefiency virus [HIV] disease','virus viral'),('2288','Nephropathy related to HIV - histologically proven',1,0,0,0,0,0,0,0,0,'','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n47429007 | associated with | = << 86406008 | Human immunodeficiency virus infection |\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Tubulo-interstitial disease',99,'Other identified renal disorders',137,'','for background information see:\nhttp://omim.org/entry/609423\nhttp://omim.org/entry/612551\nhttp://omim.org/entry/607832','N289 AND B24X','Disorder of kidney and ureter, unspecified AND Unspecified human immunodefiency virus [HIV] disease','virus viral'),('2290','Schistosomiasis',0,0,0,0,0,0,0,1,0,'This PRD requires microbiological evidence of urinary tract infection with Schistosomiasis.\nThis PRD should not be used for immune complex nephropathy secondary to Schistosomiasis.','236706006','Urinary schistosomiasis (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',138,'','for background information see:\nhttp://omim.org/entry/181460','B650D','Schistosom due Schis haematobium [urin schistosom]',''),('2300','Other specific infection',0,0,0,0,0,0,0,0,0,'This PRD requires evidence of infection with the putative organism.\nThis PRD should only be used if there is evidence of infection directly causing renal impairment not listed elsewhere.','40733004','Infectious disease (disorder)','<< 40733004 | Infectious disease |','Tubulo-interstitial disease',99,'Other identified renal disorders',139,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('2316','Diabetic nephropathy in type I diabetes - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of type I diabetes mellitus must have been made.\nFor a diagnosis of diabetic nephropathy without evidence from renal histopathology,\nproteinuria must have been documented at some point in the patients history.\nA PRD of diabetic nephropathy is not mandatory in the presence of DM with proteinuria and alternative diagnoses can be considered.\nIn the absence of a renal histopathology the differential diagnosis will include\nChronic kidney disease (CKD) / chronic renal failure (CRF)  aetiology uncertain / unknown - (with or without histology),\n ischaemic nephropathy, renovascular disease and atheroembolic renal disease.\n','421893009','Renal disorder associated with type I diabetes mellitus (disorder)','<< 421893009 | Renal disorder associated with type I diabetes mellitus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',80,'Diabetes glomerulosclerosis or diabetic nephropathy - Type I',140,'','for background information see:\nhttp://omim.org/entry/222100','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2328','Diabetic nephropathy in type I diabetes - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of type I diabetes mellitus must have been made.\nHistopathological features most compatible with diabetic nephropathy must be present.','421893009','Renal disorder associated with type I diabetes mellitus (disorder)','<< 421893009 | Renal disorder associated with type I diabetes mellitus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',80,'Diabetes glomerulosclerosis or diabetic nephropathy - Type I',141,'','for background information see:\nhttp://omim.org/entry/222100','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2337','Diabetic nephropathy in type II diabetes - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of type II diabetes mellitus must have been made.\nFor a diagnosis of diabetic nephropathy, proteinuria must have been documented at some point in the patients history .\nA PRD of diabetic nephropathy is not mandatory in the presence of DM with proteinuria and alternative diagnoses can be considered.\nIn the absence of a renal histology the differential diagnosis will include\nChronic kidney disease (CKD) / chronic renal failure (CRF) aetiology uncertain / unknown -(with or without a histology),\n ischaemic nephropathy, renovascular disease and atheroembolic renal disease.\nDistinguish from: Inherited / genetic diabetes mellitus type II.\n','420279001','Renal disorder associated with type II diabetes mellitus (disorder)','<< 420279001 | Renal disorder associated with type II diabetes mellitus |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy | ','Systemic diseases affecting the kidney',81,'Diabetes glomerulosclerosis or diabetic nephropathy - Type II',142,'','for background information see:\nhttp://omim.org/entry/125853','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN glucose hyperglycaemia insulin'),('2344','Diabetic nephropathy in type II diabetes - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of type II diabetes mellitus must have been made.\nHistopathological features most compatible with diabetic nephropathy must be present.','420279001','Renal disorder associated with type II diabetes mellitus (disorder)','<< 420279001 | Renal disorder associated with type II diabetes mellitus |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',81,'Diabetes glomerulosclerosis or diabetic nephropathy - Type II',143,'','for background information see:\nhttp://omim.org/entry/125853','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','DM DN Glucose hyperglycaemia'),('2359','Chronic Hypertensive nephropathy - no histology',0,1,0,1,0,0,0,0,0,'The patient must have a history of hypertension.\nOther PRDs will usually have been considered before accepting this PRD.','38481006','Hypertensive renal disease (disorder)','(<< 38481006 | Hypertensive renal disease |:\n263502005 | Clinical course | = 90734009 | Chronic |\n, 418775008 | Finding method | = ! << 7246002 | Kidney biopsy |)\nAND\n(! <38481006 | Hypertensive renal','Systemic diseases affecting the kidney',72,'Renal vascular disease due to hypertension',144,'','for background information see:\nhttp://omim.org/entry/145500 about essential hypertension\nhttp://omim.org/entry/161900 about progression of renal failure','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2363','Chronic Hypertensive nephropathy - histologically proven',1,1,0,1,0,0,0,0,0,'','38481006','Hypertensive renal disease (disorder)','(<< 38481006 | Hypertensive renal disease |:\n263502005 | Clinical course | = 90734009 | Chronic |\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |)\nAND\n(! <38481006 | Hypertensive renal d','Systemic diseases affecting the kidney',72,'Renal vascular disease due to hypertension',145,'','for background information see:\nhttp://omim.org/entry/145500 about essential hypertension\nhttp://omim.org/entry/161900 about progression of renal failure','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2371','Malignant hypertensive nephropathy / accelerated hypertension nephropathy - no histology',0,0,0,1,1,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','65443008','Malignant hypertensive renal disease (disorder)','<< 65443008 | Malignant hypertensive renal disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',71,'Renal vascular disease due to malignant hypertension',146,'','','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2385','Malignant hypertensive nephropathy / accelerated hypertension nephropathy - histologically proven',1,0,0,1,0,0,0,0,0,'Lacks evidence of systemic sclerosis.','65443008','Malignant hypertensive renal disease (disorder)','<< 65443008 | Malignant hypertensive renal disease |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',71,'Renal vascular disease due to malignant hypertension',147,'','','I120','Hypertensive renal disease with renal failure','TIN IN BP HBP high blood pressure hypertension'),('2392','Ageing kidney - no histology',0,1,0,1,1,0,0,1,0,'If the patient develops stage 4 CKD, consider a PRD of ischaemic nephropathy.','445108007','Age related reduction of renal function (finding)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',148,'','for background information see:\nhttp://omim.org/entry/502000','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','senile age related senescence  old geriatric'),('2407','Ischaemic nephropathy - no histology',0,1,0,0,1,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','519581000000107','Ischaemic nephropathy (disorder)','42752001 | due to | = 52674009 | Ischemia (disorder) |\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',149,'','','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli'),('2411','Ischaemic nephropathy / microvascular disease - histologically proven',1,1,0,0,0,0,0,0,0,'Lacks evidence of any other PRD responsible for the hypertension and renal failure.','519581000000107','Ischaemic nephropathy (disorder)','519581000000107 | Ischaemic nephropathy |:\nOR\n(< 16934004 | Renal vascular disorder |)\n, 418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',150,'','','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli'),('2424','Renal artery stenosis',0,0,0,0,0,0,0,1,0,'','302233006','Renal artery stenosis (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',151,'','for background information see:\nhttp://omim.org/entry/135580\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli stenosed stenotic '),('2430','Atheroembolic renal disease - no histology',0,1,0,1,0,0,0,1,0,'Eosinophilia or complement consumption are suggestive.','51677000','Atheroembolism of renal arteries (disorder)','51677000 | Atheroembolism of renal arteries |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',152,'','for background information see:\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli'),('2448','Atheroembolic renal disease - histologically proven',1,0,0,0,0,0,0,0,0,'','51677000','Atheroembolism of renal arteries (disorder)','51677000 | Atheroembolism of renal arteries |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',75,'Ischaemic renal disease/cholesterol embolism',153,'','for background information see:\nhttp://omim.org/entry/108725','I701','Atherosclerosis of renal artery','vascular ischaemia PVD cholesterol embolus emboli cleft ghost'),('2453','Fibromuscular dysplasia of renal artery',0,0,0,0,0,0,0,1,0,'','2900003','Hyperplasia of renal artery (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',154,'','http://omim.org/entry/135580','I773','Arterial fibromuscular dysplasia','vascular ischaemia PVD stenosed stenotic FMD'),('2469','Renal arterial thrombosis / occlusion',0,0,0,0,0,0,0,1,0,'A source of emboli or a hypercoagulable state are usually identified.','236488005','Renal artery occlusion (disorder)','','Systemic diseases affecting the kidney',70,'Renal vascular disease - type unspecified',155,'','for background information see:\nhttp://omim.org/entry/188050','N280','Ischaemia and infarction of kidney','vascular ischaemia PVD cholesterol embolus emboli cleft ghost occluded artery '),('2476','Renal vein thrombosis',0,0,0,0,0,0,0,1,0,'If there is an underlying nephrotic syndrome causing the thrombosis, the underlying renal diagnosis should take precedence.','15842009','Thrombosis of renal vein (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',156,'','for background information see:\nhttp://omim.org/entry/188050','I823','Embolism and thrombosis of renal vein','venous thrombus thrombosed RVT clot'),('2482','Cardiorenal syndrome',0,1,0,0,1,0,0,0,0,'Intractable heart failure with severe renal impairment without evidence of parenchymal kidney disease or renal vascular disease.\nCorresponds to CardioRenal Syndrome type 2 as described by the Acute Dialysis Quality Initiative group\n(Ronco C et al J Am Coll Cardiol 2008; 52; 1527-1539).\n','445236007','Cardiorenal syndrome (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',157,'','for background information see:\nhttp://omim.org/entry/232200\n','I139','Hypertensive heart and renal disease, unspecified','heart cardiac '),('2495','Hepatorenal syndrome',0,1,0,0,1,0,0,0,0,'A renal biopsy is not normally done in this clinical situation.\nRenal histology is essentially normal.\nPatients with liver disease are predisposed to IgA nephropathy and\nwhere this is found, the alternative PRD IgA nephropathy secondary to liver cirrhosis\nshould be considered.','51292008','Hepatorenal syndrome (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',158,'','','K767','Hepatorenal syndrome','liver hepatic '),('2509','Renal amyloidosis',1,0,0,0,0,0,0,0,0,'Histological proof of amyloid from another tissue is an adequate substitute for renal histology.','48713002','Amyloid nephropathy (disorder)','','Systemic diseases affecting the kidney',83,'Amyloid',159,'','','E854','Organ-limited amyloidosis','amyloid'),('2513','AA amyloid secondary to chronic inflammation',1,0,0,0,0,0,0,0,0,'Histological proof of amyloid from another tissue is an adequate substitute for renal histology.','274945004','AA amyloidosis (disorder)','274945004 | AA amyloidosis |:\nassociated morphology = 84499006 | Chronic inflammation |','Systemic diseases affecting the kidney',83,'Amyloid',160,'','','E853','Secondary systemic amyloidosis','amyloidosis'),('2521','AL amyloid secondary to plasma cell dyscrasia',1,0,0,0,1,0,0,0,0,'Evidence of monoclonal light chain (biochemical or immunological).\nHistological proof of amyloid from another tissue is a substitute for renal histology.\nThere should be evidence of a monoclonal light chain (eg biochemical or immunological).','23132008','AL amyloidosis (disorder)','23132008 | AL amyloidosis |:\nassociated morphology = 127580003 | Plasma cell neoplasm |','Systemic diseases affecting the kidney',83,'Amyloid',161,'','http://omim.org/entry/254500','E859','Amyloidosis, unspecified','amyloidosis'),('2532','Familial amyloid secondary to protein mutations - no histology',0,0,1,0,0,0,0,0,0,'Amyloid should have been demonstrated histologically in at least one affected family member.','66451004','Familial visceral amyloidosis, Ostertag type (disorder)','<< 66451004 | Familial renal amyloidosis |:\n, 418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',162,'','http://omim.org/entry/105200','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2545','Familial amyloid secondary to protein mutations - histologically proven',1,0,1,0,0,0,0,0,0,'','66451004','Familial visceral amyloidosis, Ostertag type (disorder)','<< 66451004 | Familial renal amyloidosis |:\n, 418775008 | Finding method | =  << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',163,'','http://omim.org/entry/105200','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2550','Familial AA amyloid secondary to familial Mediterranean fever / TRAPS (Hibernian fever) - no histology',0,1,1,0,0,0,0,0,0,'','367528006','Amyloid of familial Mediterranean fever (disorder)','367528006 | Amyloid of familial Mediterranean fever |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',164,'','http://omim.org/entry/142680\nfor background information see:\nhttp://omim.org/entry/608107','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2566','Familial AA amyloid secondary to familial Mediterranean fever / TRAPS (Hibernian fever) - histologically proven',1,1,1,0,0,0,0,0,1,'','367528006','Amyloid of familial Mediterranean fever (disorder)','367528006 | Amyloid of familial Mediterranean fever |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',83,'Amyloid',165,'','http://omim.org/entry/142680\nfor background information see:\nhttp://omim.org/entry/608107','E850','Non-neuropathic heredofamilial amyloidosis','amyloidosis'),('2578','Myeloma kidney - no histology',0,0,0,0,1,0,0,0,0,'A diagnosis of myeloma must have been made with\nbiochemical, haematological or immunological confirmation.','32278006','Myeloma kidney (disorder)','32278006 | Myeloma kidney |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',166,'','for background information see:\nhttp://omim.org/entry/254500','C900D','Multiple myeloma','multiple MM bence jones'),('2584','Myeloma cast nephropathy - histologically proven',1,0,0,0,1,0,0,0,0,'A diagnosis of myeloma must have been made with\nbiochemical, haematological or immunological confirmation.','32278006','Myeloma kidney (disorder)','32278006 | Myeloma kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',167,'','for background information see:\nhttp://omim.org/entry/254500','C900D','Multiple myeloma','multiple MM bence jones fracture fractured'),('2597','Light chain deposition disease',1,0,0,0,1,0,0,0,0,'A diagnosis of light chain disease must have been made with\nbiochemical, haematological or immunological confirmation.','373604002','Light chain deposition disease (disorder)','','Systemic diseases affecting the kidney',82,'Myelomatosis / light chain deposit disease',168,'','for background information see:\nhttp://omim.org/entry/254500','D808','Other immunodeficiencies with predominantly antibody defects','LCD LCDD immunoglobulin dyscrasia'),('2606','Immunotactoid / fibrillary nephropathy',1,0,0,0,0,0,0,0,0,'','73305009','Fibrillary glomerulonephritis (disorder)','','Systemic diseases affecting the kidney',99,'Other identified renal disorders',169,'','for background information see:\nhttp://omim.org/entry/137950','N059','Unspecified nephritic syndrome, unspecified',''),('2610','Haemolytic uraemic syndrome (HUS) - diarrhoea associated',0,1,0,0,0,0,0,0,0,'This PRD is usually associated with E. coli 0157 infection.','373421000','Diarrhea-associated hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',170,'','','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2623','Atypical haemolytic uraemic syndrome (HUS) - diarrhoea negative',1,1,0,0,0,0,0,0,0,'If there is evidence of a genetic mutation, or of a family history of haemolytic uraemic syndrome, then the code\nCongenital haemolytic uraemic syndrome or Familial haemolytic uraemic syndrome should be used.','373422007','Diarrhea-negative hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',171,'','http://omim.org/entry/235400 about AHUS1\nhttp://omim.org/entry/612922 about AHUS2\nhttp://omim.org/entry/612923 about AHUS3\nhttp://omim.org/entry/612924 about AHUS4\nhttp://omim.org/entry/612925 about A','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2634','Thrombotic thrombocytopenic purpura (TTP)',1,1,0,0,1,0,0,0,1,'ADAMTS13 deficiency or autoantibodies to ADAMTS13 may be demonstrated (not mandatory).','78129009','Thrombotic thrombocytopenic purpura (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',172,'','http://omim.org/entry/274150','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','',''),('2647','Haemolytic uraemic syndrome (HUS) secondary to systemic disease',0,0,0,0,1,0,0,0,0,'This PRD includes HUS secondary to drugs or malignancies.','111407006','Hemolytic uremic syndrome (disorder)','111407006 | Hemolytic uremic syndrome |:\n42752001 | due to | = 56019007 | Systemic disease |','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',173,'','','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2652','Congenital haemolytic uraemic syndrome (HUS) ',0,0,0,0,1,0,0,0,0,'','444976001','Congenital hemolytic uremic syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',174,'','http://omim.org/entry/274150','D593','Haemolytic-uraemic syndrome','haemolysis fragment'),('2668','Familial haemolytic uraemic syndrome (HUS)',0,0,1,0,1,0,0,0,1,'This PRD includes complement factor H or I abnormalities.','373420004','Upshaw-Schulman syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',175,'','http://omim.org/entry/274150','M311','Thrombotic microangiopathy','haemolysis fragment'),('2675','Familial thrombotic thrombocytopenic purpura (TTP)',0,0,1,0,0,0,0,0,1,'ADAMTS13 deficiency (not mandatory).','373420004','Upshaw-Schulman syndrome (disorder)','','Systemic diseases affecting the kidney',88,'Haemolytic Uraemic Syndrome (including Moschcowitz Syndrome)',176,'','http://omim.org/entry/274150','M311','Thrombotic microangiopathy',''),('2681','Nephropathy due to pre-eclampsia / eclampsia',0,1,0,0,1,0,0,0,0,'','90708001','Kidney disease (disorder)','90708001 | Kidney disease |:\n42752001 | due to | = (<< 398254007 | Pre-eclampsia |\nOR << 15938005 | Eclampsia |)','Systemic diseases affecting the kidney',99,'Other identified renal disorders',177,'','for background information see:\nhttp://omim.org/entry/189800 about PEE1\nhttp://omim.org/entry/609402 about PEE2\nhttp://omim.org/entry/609403 about PEE3\nhttp://omim.org/entry/609404 about PEE4\n','N289 AND O159\nOR\nN289 AND O149\n','Disorder of kidney and ureter, unspecified AND Eclampsia, unspecified as to time period\nOR\nDisorder of kidney and ureter, unspecified AND Pre-eclampsia, unspecified','pregnant pregnancy'),('2699','Sickle cell nephropathy - no histology',0,1,0,0,0,0,0,0,0,'','13886001','Sickle cell nephropathy (disorder)','13886001 | Sickle cell nephropathy |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',99,'Other identified renal disorders',178,'','for background information see:\nhttp://omim.org/entry/603903','D571D','Sickle-cell anaemia without crisis',''),('2702','Sickle cell nephropathy - histologically proven',1,1,0,0,0,0,0,0,0,'','13886001','Sickle cell nephropathy (disorder)','13886001 | Sickle cell nephropathy |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Systemic diseases affecting the kidney',99,'Other identified renal disorders',179,'','for background information see:\nhttp://omim.org/entry/603903','D571D','Sickle-cell anaemia without crisis',''),('2718','Autosomal dominant (AD) polycystic kidney disease',0,0,1,0,0,0,0,1,1,'','28728008','Polycystic kidney disease, adult type (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',180,'','for background information see:\nhttp://omim.org/entry/173900 about PKD1\nhttp://omim.org/entry/613095 about PKD2\nhttp://omim.org/entry/600666 about PKD3','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2725','Autosomal dominant (AD) polycystic kidney disease type I',0,0,1,0,0,0,0,1,1,'','253878003','Adult type polycystic kidney disease type 1 (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',181,'','http://omim.org/entry/173900 about PKD1\nhttp://omim.org/entry/601313 about polycystin 1\nhttp://omim.org/entry/606702 about PKDH1','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2739','Autosomal dominant (AD) polycystic kidney disease type II',0,0,1,0,0,0,0,1,1,'','253879006','Adult type polycystic kidney disease type 2 (disorder)','','Familial / hereditary nephropathies',41,'Polycystic kidneys; adult type (dominant)',182,'','http://omim.org/entry/613095 about PKD2\nhttp://omim.org/entry/173910 about polycystin 2','Q612','Polycystic kidney, adult type','CK PCK ADPKD ADPCKD PCKD PKD APKD cysts'),('2741','Autosomal recessive (AR) polycystic kidney disease',0,0,1,0,0,0,0,1,1,'','28770003','Polycystic kidney disease, infantile type (disorder)','','Familial / hereditary nephropathies',42,'Polycystic kidneys; infantile (recessive)',183,'','http://omim.org/entry/263200','Q611','Polycystic kidney, infantile type','CK PCK ARPKD ARPCKD PCKD PKD APKD cysts'),('2756','Alport syndrome - no histology',0,1,1,1,0,0,0,0,1,'','399340005','Hereditary nephritis (disorder)','399340005 | Hereditary nephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',51,'Hereditary nephritis with nerve deafness (Alports Syndrome)',184,'','http://omim.org/entry/301050 about alport syndrome, X-linked; ATS\nhttp://omim.org/entry/303630 about collagen, type IV, alpha-5; COL4A5\nhttp://omim.org/entry/203780 about alport syndrome, autosomal re','N079','Unspecified morphological changes','thin deaf split '),('2760','Alport syndrome - histologically proven',1,1,1,1,0,0,0,0,1,'','399340005','Hereditary nephritis (disorder)','399340005 | Hereditary nephritis |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',51,'Hereditary nephritis with nerve deafness (Alports Syndrome)',185,'','http://omim.org/entry/301050 about alport syndrome, X-linked; ATS\nhttp://omim.org/entry/303630 about collagen, type IV, alpha-5; COL4A5\nhttp://omim.org/entry/203780 about alport syndrome, autosomal re','N079','Unspecified morphological changes','thin deaf split '),('2773','Benign familial haematuria',0,1,1,0,0,0,1,0,0,'The clinical history can be useful because it does NOT suggest any other nephropathy.\nHaematuria can be demonstrated by any means eg dip stick or urine microscopy.','236421001','Benign familial hematuria (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',186,'','http://omim.org/entry/141200','N029','Recurrent and persistent haematuria, unspecified',''),('2787','Thin basement membrane disease',1,0,0,0,0,0,0,0,0,'','236418003','Thin basement membrane disease (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',187,'','http://omim.org/entry/141200','N050','Unspecified nephritic syndrome, minor glomerular abnormality',''),('2794','Cystic kidney disease',0,0,0,0,0,0,0,1,0,'','82525005','Congenital cystic kidney disease (disorder)','','Familial / hereditary nephropathies',40,'Cystic kidney disease - type unspecified',188,'','','Q619','Cystic kidney disease, unspecified','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2804','Medullary cystic kidney disease type I',0,1,1,1,1,0,1,1,1,'Usually autosomal dominant family history, bland urine and without heavy proteinuria.','444699000','Medullary cystic kidney disease type 1 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',189,'','http://omim.org/entry/174000','Q615','Medullary cystic kidney','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2815','Medullary cystic kidney disease type II',1,0,1,1,0,0,0,1,1,'Genetically identical to uromodulin-associated nephropathy, but characterised by presence of cysts and gout.','445503007','Medullary cystic kidney disease type 2 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',190,'','http://omim.org/entry/603860\n','Q615','Medullary cystic kidney','CK PCK ADPCKD PCKD PKD APKD cysts ARPCKD'),('2827','Uromodulin-associated nephropathy (familial juvenile hyperuricaemic nephropathy)',1,0,1,1,0,0,0,1,1,'Autosomal dominant inheritance, high incidence of gout, but no cysts.','46785007','Familial juvenile gout (disorder)','','Familial / hereditary nephropathies',49,'Cystic kidney disease - other specified type',191,'','http://omim.org/entry/162000 about HNFJ1\nhttp://omim.org/entry/613092 about HNFJ2\nhttp://omim.org/entry/614227 about HNFJ3\nfor background information see:\nhttp://omim.org/entry/191845 about uromodulin','E798','Other disorders of purine and pyrimidine metabolism',' '),('2836','Nephronophthisis',0,1,1,0,0,0,0,0,0,'','204958008','Nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',192,'','for background information see:\nhttp://omim.org/entry/609254 about senior-loken syndrome 5; SLSN5 and NPHP\nhttp://omim.org/entry/612013 about coiled-coil and C2 domains-containing protein 2A; CC2D2A\nh','Q618','Other cystic kidney diseases',''),('2843','Nephronophthisis - type 1 (juvenile type)',0,1,1,0,0,0,0,0,1,'','444830001','Juvenile nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',193,'','http://omim.org/entry/256100 about NPHP1\nhttp://omim.org/entry/607100 about nephrocystin 1; NPHP','Q618','Other cystic kidney diseases',''),('2858','Nephronophthisis - type 2 (infantile type)',0,0,1,0,0,0,0,0,1,'','444558002','Infantile nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',194,'','http://omim.org/entry/602088 about NPHP2','Q618','Other cystic kidney diseases',''),('2862','Nephronophthisis - type 3 (adolescent type)',0,1,1,0,0,0,0,0,1,'','444749006','Adolescent nephronophthisis (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',195,'','http://omim.org/entry/604387 about NPHP3\nhttp://omim.org/entry/608002 about nephrocystin 3; NPHP3','Q618','Other cystic kidney diseases',''),('2870','Nephronophthisis - type 4 (juvenile type)',0,1,1,0,0,0,0,0,1,'','446989009','Nephronophthisis type 4 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',196,'','http://omim.org/entry/606966 about NPHP4\nhttp://omim.org/entry/607215 about nephrocystin 4; NPHP4','Q618','Other cystic kidney diseases',''),('2889','Nephronophthisis - type 5',0,1,1,0,0,0,0,0,1,'','446991001','Nephronophthisis type 5 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',197,'','','Q618','Other cystic kidney diseases',''),('2891','Nephronophthisis - type 6',0,1,1,0,0,0,0,0,1,'','447335007','Nephronophthisis type 6 (disorder)','','Familial / hereditary nephropathies',43,'Medullary cystic disease; including nephronophthisis',198,'','','Q618','Other cystic kidney diseases',''),('2901','Primary Fanconi syndrome',0,0,0,0,1,0,0,0,0,'Clinical syndrome associated with genetic mutations.','236466005','Congenital Fanconi syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',199,'','http://omim.org/entry/134600 about FRTS1\nhttp://omim.org/entry/613388 about FRTS2','E720','Disorders of amino-acid transport',''),('2917','Tubular disorder as part of inherited metabolic diseases',0,0,1,0,1,0,0,0,0,'','197744007','Renal tubulo-interstitial disorders in metabolic diseases (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',200,'','http://omim.org/entry/551200 about chronic tubulointerstitial nephropathy','N163A','Renal tubulo-interstitial disorders in metabolic diseases','TIN IN'),('2929','Dent disease',0,1,1,0,1,0,0,1,1,'X linked recessive nephrolithiasis + mutation in gene CLCN5.\nClinical: low MW proteinuria, raised urinary calcium:creatinine ratio.\nMay have nephrocalcinosis, nephrolithiasis, hypophosphatemic rickets.','444645005','Dents disease (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',201,'','http://omim.org/entry/300009','N398','Other specified disorders of urinary system',''),('2938','Lowe syndrome (oculocerebrorenal syndrome)',0,1,1,1,1,0,0,1,1,'Genetic mutation in OCR1.\nSimilar renal phenotype to that seen in Dent disease.','79385002','Lowe syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',202,'','http://omim.org/entry/309000','E720','Disorders of amino-acid transport',''),('2940','Inherited aminoaciduria',0,0,1,0,1,0,0,0,0,'\n','522601000000103','Inherited aminoaciduria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',203,'','for background information see:\nhttp://omim.org/ search for <amino AND aciduria>','E729','Disorder of amino-acid metabolism, unspecified',''),('2955','Cystinuria',0,1,1,0,1,0,0,1,1,'Urinary cystine concentration / nephrolithiasis / genetic mutations.','85020001','Cystinuria (disorder)','<< 85020001 | Cystinuria |','Familial / hereditary nephropathies',99,'Other identified renal disorders',204,'','http://omim.org/entry/220100\nhttp://omim.org/entry/606407 about hypotonia-cystinuria syndrome','E720','Disorders of amino-acid transport',''),('2964','Cystinosis',0,1,0,1,1,0,0,0,1,'Systemic deposit cystine / mutation in CTNS gene.','190681003','Cystinosis (disorder)','<< 190681003 | Cystinosis |','Familial / hereditary nephropathies',52,'Cystinosis',205,'','http://omim.org/entry/219800\nhttp://omim.org/entry/219900 about cystinosis late-onset juvenile or adolescent nephropathic type','E720','Disorders of amino-acid transport',''),('2972','Inherited renal glycosuria',0,0,1,0,1,0,1,0,0,'','226309007','Familial renal glucosuria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',206,'',' http://omim.org/entry/233100','E748','Other specified disorders of carbohydrate metabolism',''),('2986','Hypophosphataemic rickets X-linked (XL)',0,1,1,1,1,0,0,1,1,'','82236004','Familial x-linked hypophosphatemic vitamin D refractory rickets (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',207,'','http://omim.org/entry/307800 about hypophosphatemic rickets, X-linked dominant; XLHR\nhttp://omim.org/entry/300554 about hypophosphatemic rickets, X-linked recessive\n','E833','Disorders of phosphorus metabolism',''),('2993','Hypophosphataemic rickets autosomal recessive (AR)',0,0,1,0,0,0,0,1,1,'Imaging shows rickets.','90505000','Autosomal recessive hypophosphatemic vitamin D refractory rickets (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',208,'','http://omim.org/entry/241520','E833','Disorders of phosphorus metabolism',''),('3000','Primary renal tubular acidosis (RTA)',0,0,0,0,1,0,0,0,0,'','1776003','Renal tubular acidosis (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',209,'','for background information see:\nhttp://omim.org/entry/267200 about renal tubular acidosis III','N258','Other disorders resulting from impaired renal tubular funct',''),('3016','Proximal renal tubular acidosis (RTA) - type II',0,0,0,0,1,0,0,0,0,'','24790002','Proximal renal tubular acidosis (disorder)','<< 24790002 | Proximal renal tubular acidosis |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',210,'','http://omim.org/entry/179830\nhttp://omim.org/entry/604278 about renal tubular acidosis, proximal, with ocular abnormalities and mental retardation','N258','Other disorders resulting from impaired renal tubular funct',''),('3028','Distal renal tubular acidosis (RTA) - type I',0,0,0,0,1,0,0,1,1,'May have nephrocalcinosis.','236461000','Distal renal tubular acidosis (disorder)','<< 236461000 | Distal renal tubular acidosis |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',211,'','http://omim.org/entry/179800 about renal tubular acidosis, distal, autosomal dominant\nhttp://omim.org/entry/602722 about renal tubular acidosis, distal, autosomal recessive; RTADR\nhttp://omim.org/entr','N258','Other disorders resulting from impaired renal tubular funct',''),('3037','Distal renal tubular acidosis with sensorineural deafness - gene mutations',0,0,1,1,0,0,0,1,1,'May have sensorineural deafness and nephrocalcinosis.','236461000','Distal renal tubular acidosis (disorder)','236461000 | Distal renal tubular acidosis |:\nassociated with = << 60700002 | Sensorineural hearing loss |','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',212,'','http://omim.org/entry/267300 about renal tubular acidosis, distal, with progressive nerve deafness','N258 AND H905','Other disorders resulting from impaired renal tubular funct AND Sensorineural hearing loss, unspecified','RTA'),('3044','Nephrogenic diabetes insipidus',0,1,1,0,1,0,0,0,1,'','111395007','Nephrogenic diabetes insipidus (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',213,'','http://omim.org/entry/304800 about diabetes insipidus, nephrogenic, X-linked\nhttp://omim.org/entry/125800 about diabetes insipidus, nephrogenic, autosomal\nhttp://omim.org/entry/221995 about diabetes i','N251','Nephrogenic diabetes insipidus','DI NDI'),('3059','Lesch Nyhan syndrome - hypoxanthine guanine phosphoribosyl transferase deficiency ',0,1,1,1,1,0,0,1,1,'','10406007','Lesch-Nyhan syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',214,'','http://omim.org/entry/300322\nhttp://omim.org/entry/300323\nfor background information see:\nhttp://omim.org/entry/308000\nhttp://omim.org/entry/308950\n','E791','Lesch-Nyhan syndrome','HGPTD'),('3063','Phosphoribosyl pyrophosphate synthetase (PRPPS) superactivity',0,1,0,1,1,0,0,1,1,'','35759001','Ribose-phosphate pyrophosphokinase overactivity (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',215,'','http://omim.org/entry/300661','E798','Other disorders of purine and pyrimidine metabolism',''),('3071','Alagille syndrome',0,1,0,1,0,0,0,1,1,'Renal dysplasia, renal artery stenosis, and cystic kidney disease have been described in this condition.','31742004','Arteriohepatic dysplasia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',216,'','http://omim.org/entry/118450 about alagille syndrome 1; ALGS1\nhttp://omim.org/entry/610205 about alagille syndrome 2; ALGS2','Q447','Other congenital malformations of liver',''),('3085','Bartter syndrome',0,0,0,0,1,0,0,0,1,'','71275003','Pseudoprimary aldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',217,'','http://omim.org/entry/601678 about type 1\nhttp://omim.org/entry/241200 about type 2\nhttp://omim.org/entry/607364 about type 3\nhttp://omim.org/entry/602522 about type 4a\nhttp://omim.org/entry/613090 ab','E268','Other hyperaldosteronism',''),('3092','Gitelman syndrome',0,0,1,0,1,0,0,0,1,'','3188003','Familial hypokalemia-hypomagnesemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',218,'','http://omim.org/entry/263800','E876','Hypokalaemia',''),('3102','Liddle syndrome',0,0,1,0,1,0,0,0,1,'','71275003','Pseudoprimary aldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',219,'','http://omim.org/entry/177200','E268','Other hyperaldosteronism',''),('3118','Apparent mineralocorticoid excess',0,0,0,0,1,0,0,0,0,'','237770005','Syndrome of apparent mineralocorticoid excess (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',220,'','http://omim.org/entry/218030','E278','Other specified disorders of adrenal gland',''),('3125','Glucocorticoid suppressible hyperaldosteronism',0,0,1,0,1,0,0,0,1,'','237743003','Glucocorticoid-suppressible hyperaldosteronism (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',221,'','http://omim.org/entry/103900','E268','Other hyperaldosteronism',''),('3139','Inherited / genetic diabetes mellitus type II',1,1,1,0,1,0,0,0,1,'Multiple / variable mutations.\nPreviously called Maturity onset diabetes in young people (MODY).\nMODY types 1-6 (HNF-1alpha mutation accounts for 65%).\n','237604008','Diabetes mellitus autosomal dominant type II (disorder)\n','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',222,'','http://omim.org/entry/125853 about DM II and its gene relationships','E139','Other specified diabetes mellitus without complications','DM DN glucose hyperglycaemia insulin'),('3141','Pseudohypoaldosteronism type 1',0,0,1,0,1,0,0,0,0,'','43941006','Pseudohypoaldosteronism, type 1 (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',223,'','http://omim.org/entry/177735 about Pseudohypoaldosteronism type I, autosomal dominant PHA1A\nhttp://omim.org/entry/264350 about Pseudohypoaldosteronism type I, autosomal recessive PHA1B','E274','Other and unspecified adrenocortical insufficiency','aldosterone'),('3156','Pseudohypoaldosteronism type 2 (Gordon syndrome)',0,0,1,0,1,0,0,0,1,'','15689008','Pseudohypoaldosteronism, type 2 (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',224,'','http://omim.org/entry/145260','E878','Other disorders of electrolyte and fluid balance NEC','aldosterone'),('3160','Familial hypocalciuric hypercalcaemia',0,0,1,0,1,0,0,0,1,'','237885008','Familial hypocalciuric hypercalcemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',225,'','http://omim.org/entry/145980 about HHC1\nhttp://omim.org/entry/145981 about HHC2\nhttp://omim.org/entry/600740 about HHC3','E835','Disorders of calcium metabolism','Ca calcium'),('3173','Familial hypercalciuric hypocalcaemia',0,0,1,0,1,0,0,0,1,'','237885008','Familial hypocalciuric hypercalcemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',226,'','http://omim.org/entry/145980\nhttp://omim.org/entry/146200','E835','Disorders of calcium metabolism','Ca calcium'),('3187','Familial hypomagnesaemia',0,0,1,0,1,0,0,0,1,'A number of syndromes with different mutations have been identified.','80710001','Primary hypomagnesemia (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',227,'','http://omim.org/entry/602014 about HOMG1\nhttp://omim.org/entry/154020 about HOMG2\nhttp://omim.org/entry/248250 about HOMG3\nhttp://omim.org/entry/611718 about HOMG4\nhttp://omim.org/entry/248190 about H','E834','Disorders of magnesium metabolism','Mg magnesium'),('3194','Primary hyperoxaluria',1,0,0,0,1,0,0,1,0,'','17901006','Primary hyperoxaluria (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',228,'','for background information see:\nhttp://omim.org/entry/259900 about hyperoxaluria, primary, type I; HP1\nhttp://omim.org/entry/260000 about hyperoxaluria, primary, type II; HP2\nhttp://omim.org/entry/613','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3207','Primary hyperoxaluria type I',1,0,0,0,1,0,0,1,1,'','65520001','Primary hyperoxaluria type I (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',229,'','http://omim.org/entry/259900','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3211','Primary hyperoxaluria type II',1,0,0,0,1,0,0,1,1,'','40951006','Primary hyperoxaluria, type II (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',230,'','http://omim.org/entry/260000','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3224','Fabry disease - no histology',0,1,1,1,1,1,0,0,1,'','16652001','Fabrys disease (disorder)','16652001 | Fabrys disease |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',54,'Fabrys disease',232,'','http://omim.org/entry/301500','E752','Other sphingolipidosis',''),('3230','Fabry disease - histologically proven',1,1,1,1,1,1,0,0,1,'','16652001','Fabrys disease (disorder)','16652001 | Fabrys disease |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Familial / hereditary nephropathies',54,'Fabrys disease',233,'','http://omim.org/entry/301500','E752','Other sphingolipidosis',''),('3248','Xanthinuria',0,0,0,0,1,0,0,0,1,'','190919008','Xanthinuria (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',234,'','http://omim.org/entry/278300 about xanthinuria, type I\nhttp://omim.org/entry/603592 about xanthinuria, type II ','E798','Other disorders of purine and pyrimidine metabolism',''),('3253','Nail-patella syndrome',0,1,1,1,0,0,0,1,1,'','236527004','Nail patella-like renal disease (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',235,'','http://omim.org/entry/161200','N078','Hereditary nephropathy, not elsewhere classified, other','NPS'),('3269','Rubinstein-Taybi syndrome',0,1,1,1,0,0,0,0,1,'','45582004','Rubinstein-Taybi syndrome (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',236,'','http://omim.org/entry/180849 about Rubinstein-Taybi syndrome 1; RSTS1\nhttp://omim.org/entry/613684 about Rubinstein-Taybi syndrome 2; RSTS2','Q872','Cong malformation syndromes predominantly involving limbs',''),('3276','Tuberous sclerosis',0,0,1,1,0,0,0,1,1,'','7199000','Tuberous sclerosis syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',237,'','http://omim.org/entry/191100 about tuberous sclerosis 1; TSC1\nhttp://omim.org/entry/605284 about TSC1 gene; TSC1\nhttp://omim.org/entry/613254 about tuberous sclerosis 2; TSC2\nhttp://omim.org/entry/191','Q851','Tuberous sclerosis',''),('3282','Von Hippel-Lindau disease',0,0,0,1,0,0,0,1,0,'','46659004','Von Hippel-Lindau syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',238,'','http://omim.org/entry/193300 about Von Hippel-Lindau syndrome; VHL\nhttp://omim.org/entry/608537 about VHL gene','Q858','Other phakomatoses, not elsewhere classified','VHL gene'),('3295','Medullary sponge kidneys',0,0,0,0,0,0,0,1,0,'','236443009','Medullary sponge kidney (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',239,'','','Q615','Medullary cystic kidney','MSK'),('3305','Horse-shoe kidney',0,0,0,0,0,0,0,1,0,'','41729002','Horseshoe kidney (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',240,'','','Q631','Lobulated, fused and horseshoe kidney',''),('3314','Frasier syndrome',0,1,0,1,0,0,0,1,1,'','445431000','Frasier syndrome (disorder)','','Familial / hereditary nephropathies',99,'Other identified renal disorders',241,'','http://omim.org/entry/136680','Q998','Other specified chromosome abnormalities',''),('3322','Branchio-oto-renal syndrome',0,1,1,1,0,0,0,0,1,'','290006','Melnick-Fraser syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',243,'','http://omim.org/entry/113650 about branchiootorenal syndrome 1; BOR1\nhttp://omim.org/entry/610896 about branchiootorenal syndrome 2; BOR2','Q870','Cong malform syndromes predom affect facial appearance',''),('3333','Williams syndrome',0,0,0,1,0,0,0,0,1,'','63247009','Williams syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',244,'','','Q878','Other specified congenital malformation syndromes NEC',''),('3346','Townes-Brocks syndrome',0,1,1,1,0,0,0,0,1,'SALL1 is the only gene known to be associated with TBS (2011). ','523411000000105','Townes-Brocks syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',245,'http://ghr.nlm.nih.gov/condition/townes-brocks-syndrome','http://www.ncbi.nlm.nih.gov/omim/107480','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','TBS'),('3351','Lawrence-Moon-Biedl / Bardet-Biedl syndrome',0,1,1,1,0,0,0,0,1,'','232059000','Laurence-Moon syndrome (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',246,'','http://omim.org/entry/245800 about Laurence-Moon syndrome\nhttp://omim.org/entry/209900 about Bardet-Biedl syndrome; BBS','Q878','Other specified congenital malformation syndromes NEC','LMB LMBS BB BBS'),('3367','Mitochondrial cytopathy',0,0,0,0,0,0,0,0,1,'Usually diagnosed by muscle histopathology and mitochondrial enzyme complex functional assay.','240096000','Mitochondrial cytopathy (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',247,'','for background information see:\nhttp://omim.org/entry/251900 about mitochondrial myopathy','G713','Mitochondrial myopathy, not elsewhere classified',''),('3379','Familial nephropathy',0,0,1,0,0,0,0,0,0,'A clear and compatible family history must be present that cannot be assigned to any more specific familial disorder. ','236419006','Progressive hereditary glomerulonephritis without deafness (disorder)','','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',248,'','','N079','Unspecified morphological changes',''),('3380','Acute kidney injury',0,1,0,0,1,0,0,0,0,'For a brief definition of acute and chronic renal failure when RRT is required, see ‘Notes for users’.\nThis super concept PRD should only be used if it is not possible to choose a more accurate one.\n','14669001','Acute renal failure syndrome (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',249,'','','N179','Acute renal failure, unspecified','AKI ARF'),('3398','Acute kidney injury due to hypovolaemia ',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 28560003 Hypovolemia |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',250,'','','N179 AND E86X','Acute renal failure, unspecified AND Volume depletion','AKI ARF renal failure'),('3403','Acute kidney injury due to circulatory failure',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 27942005 Shock |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',251,'','','N179 AND R579','Acute renal failure, unspecified AND Shock, unspecified','AKI ARF renal failure'),('3419','Acute kidney injury due to sepsis',0,1,0,0,0,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 91302008 | Systemic infection |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',252,'','','N179','Acute renal failure, unspecified','AKI ARF infection septicaemia bacteraemia renal failure'),('3426','Acute kidney injury due to rhabdomyolysis',0,1,0,0,1,0,0,0,0,'','14669001','Acute renal failure syndrome (disorder)','14669001 | Acute renal failure syndrome |:\n42752001 | due to | = 240131006 Rhabdomyolysis |','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',253,'','','N179 AND M628','Acute renal failure, unspecified AND Other specified disorders of muscle','AKI ARF myoglobin CK renal failure'),('3435','Acute kidney injury due to nephrotoxicity',0,1,0,0,0,0,0,0,0,'','236428007','Nephrotoxic acute renal failure (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',254,'','','N144','Toxic nephropathy, not elsewhere classified','AKI ARF poison toxin toxic toxicity drug medicine renal failure'),('3442','Acute cortical necrosis',0,1,0,0,0,0,0,1,0,'Either imaging or histological evidence is acceptable.','197650009','Acute cortical necrosis (disorder)','','Miscellaneous renal disorders',90,'Tubular necrosis (irreversible) or cortical necrosis (different from 88)',255,'','','N171','Acute renal failure with acute cortical necrosis','AKI ARF CAN'),('3457','Acute pyelonephritis',0,1,0,0,0,0,0,0,0,'Either evidence of urinary tract infection with white cell casts or histology.','36689008','Acute pyelonephritis (disorder)','','Miscellaneous renal disorders',0,'Not available in previous coding system',256,'','','N10X','Acute tubulo-interstitial nephritis','PN'),('3461','Kidney tumour',0,0,0,0,0,0,0,1,0,'','126880001','Neoplasm of kidney (disorder)','','Miscellaneous renal disorders',95,'Kidney tumour',257,'','http://omim.org/entry/144700 about renal cell carcinoma, nonpapillary; RCC\nhttp://omim.org/entry/150800 about hereditary leiomyomatosis and renal cell cancer; HLRCC\nhttp://omim.org/entry/609322 about ','D410','Neoplasm uncert / unkn behav kidney','mass cancer malignancy neoplasm neoplasia'),('3474','Renal cell carcinoma - histologically proven',1,0,0,0,0,0,0,0,0,'','254915003','Clear cell carcinoma of kidney (disorder)','254915003 | Clear cell carcinoma of kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',258,'','http://omim.org/entry/605074 about renal cell carcinoma, papillary, 1; RCCP1\nhttp://omim.org/entry/613253 about hereditary renal cancer, associated 1; HRCA1\nhttp://omim.org/entry/606423 about disrupte','C64X','Malignant neoplasm of kidney, except renal pelvis','mass cancer malignancy neoplasm neoplasia tumour'),('3488','Transitional cell carcinoma - histologically proven',1,0,0,0,0,0,0,0,0,'','408642003','Transitional cell carcinoma of kidney (disorder)','408642003 | Transitional cell carcinoma of kidney |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',259,'','http://omim.org/entry/109800 about bladder cancer. Refers to TCC of the renal pelvis','C64X','Malignant neoplasm of kidney, except renal pelvis','cancer malignancy neoplasm neoplasia mass tumour'),('3490','Wilms tumour - histologically proven',1,0,0,0,0,0,0,0,0,'','302849000','Nephroblastoma (disorder)','302849000 | Nephroblastoma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',260,'','http://omim.org/entry/194070 about Wilms tumor 1; WT1\nhttp://omim.org/entry/605442 about Wilms tumor 1-associating protein; WTAP\nhttp://omim.org/entry/194071 about Wilms tumor 2; WT2\nhttp://omim.org/e','C64X','Malignant neoplasm of kidney, except renal pelvis','cancer malignancy neoplasm neoplasia mass'),('3501','Mesoblastic nephroma - histologically proven',1,0,0,0,0,0,0,0,0,'','307604008','Mesoblastic nephroma (disorder)','307604008 | Mesoblastic nephroma |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',95,'Kidney tumour',261,'','','D410','Neoplasm uncert / unkn behav kidney',''),('3517','Single kidney identified in adulthood',0,0,0,0,0,0,0,1,0,'Diagnosis codes expressing the presence of a kidney (left or right) are not suitable because they do not imply absence of the contralateral kidney.\n','824131000000108','Solitary kidney (finding)',' ','Miscellaneous renal disorders',99,'Other identified renal disorders',262,'','','ICD-10 code is not available.\nWhen required, users should discuss the clinical features with local ICD-10 coding staff and select the most appropriate code.','','absent one unilateral'),('3529','Chronic kidney disease (CKD) / chronic renal failure (CRF) caused by tumour nephrectomy',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n42752001 | due to | = 108022006 | Kidney excision |','Miscellaneous renal disorders',96,'Traumatic or surgical loss of kidney',263,'','','N189','Chronic renal failure, unspecified','surgery'),('3538','Chronic kidney disease (CKD) / chronic renal failure (CRF) due to traumatic loss of kidney',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','','Miscellaneous renal disorders',96,'Traumatic or surgical loss of kidney',264,'','','N189','Chronic renal failure, unspecified',''),('3540','Chronic kidney disease (CKD) / chronic renal failure (CRF) due to donor nephrectomy',0,1,0,0,0,0,0,0,0,'Causing kidney disease due to loss of renal mass in the absence of another identified primary renal disease.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\ndue to =175911000 | Live donor nephrectomy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',265,'','','N189','Chronic renal failure, unspecified',''),('3555','Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - no histology',0,1,0,0,0,0,0,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',266,'','','N189','Chronic renal failure, unspecified',''),('3564','Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - histologically proven',1,1,0,0,0,0,0,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.','236425005','Chronic renal impairment (disorder)','236425005 | Chronic renal impairment |:\n418775008 | Finding method | = << 7246002 | Kidney biopsy |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',267,'','','N189','Chronic renal failure, unspecified',''),('3572','Haematuria and proteinuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.','53298000 AND 29738008 ','Hematuria syndrome (disorder) AND Proteinuria (disorder)','(53298000 | Hematuria syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)\nAND\n(29738008 | Proteinuria |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',268,'','','R31X AND N391','Unspecified haematuria AND Persistent proteinuria, unspecified','blood red cells protein albumin albuminuria'),('3604','Nephrotic syndrome of childhood - steroid resistant - no histology',0,1,0,0,1,0,0,0,0,'No remission despite a therapeutic trial of corticosteroids.','800991000000107','Steroid resistant nephrotic syndrome of childhood (disorder)','236381000 | Steroid-resistant nephrotic syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',4,'','for background information see:\nhttp://omim.org/entry/600995 about nephrotic syndrome, type 2; NPHS2 = nephrotic syndrome, steroid-resistant, autosomal recessive; SRN1\nhttp://omim.org/entry/610725 abo','N049','Nephrotic syndrome, unspecified',''),('3615','Nephrotic syndrome of childhood - no trial of steroids - no histology',0,1,0,0,1,0,0,0,0,'Nephrotic syndrome in childhood. No trial of steroids, no histology, no information to allow a more detailed PRD to be selected.','445119005','Steroid sensitive nephrotic syndrome of childhood (disorder)','445119005 | Steroid sensitive nephrotic syndrome of childhood |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',5,'','','N049','Nephrotic syndrome, unspecified',''),('3627','Renal cysts and diabetes syndrome',0,0,0,0,0,1,0,1,1,'Associated with the HNF1B gene.','446641003','Renal cysts and diabetes syndrome (disorder)','','Tubulo-interstitial disease',99,'Other identified renal disorders',69,'','http://omim.org/entry/137920','E142','Unspecified diabetes mellitus with renal complications','cystic cysts cyst'),('3636','Chronic urate nephropathy - no histology',0,1,0,0,1,0,0,0,0,'','190829000','Chronic urate nephropathy (disorder)','418775008 | Finding method | = !< 7246002 | Kidney biopsy |','Tubulo-interstitial disease',92,'Gout',127,'','for background information see:\nhttp://omim.org/entry/191700\nhttp://omim.org/entry/191540','M100D','Idiopathic gout','TIN IN uric '),('3643','Chronic renal failure due to systemic infection',0,1,0,0,1,0,0,1,0,'','90688005','Chronic renal failure syndrome (disorder)','90688005 | Chronic renal failure syndrome |:\n42752001 | due to | = 91302008 | Systemic infection |','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',271,'','','N189','Chronic renal failure, unspecified','CKD CRF'),('3658','Renal coloboma syndrome',0,1,1,1,0,0,0,1,1,'Associated with the Pax2 gene.','446449009','Renal coloboma syndrome (disorder)',' ','Familial / hereditary nephropathies',59,'Hereditary nephropathy - other specified type',242,'','http://omim.org/entry/120330','Q605','Renal hypoplasia, unspecified',''),('3662','Hypercalcaemic nephropathy ',0,0,0,0,0,0,0,0,0,'Use a more accurate PRD if appropriate.','33763006','Hypercalcemic nephropathy (disorder)','','Tubulo-interstitial disease',93,'Nephrocalcinosis and hypercalcaemic nephropathy',129,'','for background information see:\nhttp://omim.org/entry/143880\nhttp://omim.org/entry/145000','E835','Disorders of calcium metabolism','TIN IN calcium Ca Ca++ high'),('3670','Retroperitoneal fibrosis secondary to peri-aortitis',0,0,0,0,0,0,0,1,0,'','49120005','Retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',89,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction  CPN PN TIN AAA aneurysm inflammation aorta'),('3689','Retroperitoneal fibrosis secondary to drugs',0,1,0,0,0,0,0,1,0,'','236015007','Drug-induced retroperitoneal fibrosis (disorder)','','Tubulo-interstitial disease',23,'Pyelonephritis due to acquired obstructive uropathy',88,'','','N135','Kinking and stricture of ureter without hydronephrosis','hydronephrosis obstruction medicine drug CPN PN TIN'),('3691','Renal failure',0,1,0,0,1,0,0,0,0,'This super concept PRD should only be used if it is not possible to specify even whether the patient has acute or chronic renal failure.','42399005','Renal failure syndrome (disorder)','','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',272,'','','N19X','Unspecified renal failure','CKD CRF ARF AKI'),('3708','Chronic renal failure',0,0,0,0,0,0,0,0,0,'This super concept PRD should be used:\n1) when the investigations that have been undertaken do not allow any more granular PRD to be selected\nor\n2) very infrequently when a full diagnosis has been made which can not be assigned to another PRD.\nIn this case, please send an email to the ERA-EDTA Registry office, highlighting the PRD that is missing.\nWhere a patient has been investigated appropriately but it is not possible to choose a more granular PRD, consider using\nPRD 3555  = Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - no histology,\nor\nPRD 3564 Chronic kidney disease (CKD) / chronic renal failure (CRF) - aetiology uncertain / unknown - histologically proven.\nSee Notes for users.','90688005','Chronic renal failure syndrome (disorder)','','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',273,'','','N189','Chronic renal failure, unspecified','CKD CRF'),('3712','Isolated haematuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.\nAlternative PRDs are available for Isolated proteinuria - no histology and for Haematuria and proteinuria - no histology.\n','53298000','Hematuria syndrome (disorder)\n','(53298000 | Hematuria syndrome |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)\n','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',269,'','','R31X','Unspecified haematuria','blood red cells '),('3720','Isolated proteinuria - no histology',0,0,0,0,1,0,1,0,0,'This PRD should only be used after careful history, clinical examination and appropriate investigation.\nIt does not mean that no information is available.  In that case, consider using PRD 3643 Chronic renal failure.\nAlternative PRDs are available for Isolated haematuria - no histology and for Haematuria and proteinuria - no histology.','29738008','Proteinuria (disorder)','(29738008 | Proteinuria |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |)','Miscellaneous renal disorders',0,'Chronic renal failure; aetiology uncertain',270,'','','N391','Persistent proteinuria, unspecified','protein albumin albuminuria'),('3731','Primary hyperoxaluria type III',1,0,0,0,1,0,0,1,1,'Associated with mutations in an uncharacterized gene (DHDPSL) on chromosome 10.','828971000000101','Primary hyperoxaluria, type III (disorder)','','Familial / hereditary nephropathies',53,'Primary oxalosis',231,'','http://omim.org/entry/613616','E748','Other specified disorders of carbohydrate metabolism','oxalate oxalosis'),('3749','Glomerulonephritis - no histology',0,1,0,1,1,1,1,1,0,'','36171008','Glomerulonephritis (disorder)','36171008 | Glomerulonephritis |:\n418775008 | Finding method | = ! < 7246002 | Kidney biopsy |','Glomerular disease',10,'Glomerulonephritis; histologically NOT examined',1,'','','N059','Unspecified nephritic syndrome, Unspecified','GN'),('RDG001','New demo diagnosis 1 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',274,'','','','',''),('RDG002','New demo diagnosis 2 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',275,'','','','',''),('RDG003','New demo diagnosis 3 for SRNS',0,0,0,0,0,0,0,0,0,'','','','','',0,'',276,'','','','',''),('RDG004','Arthrogryposis, renal dysfunction and cholestasis',0,0,0,0,0,0,0,0,0,'','','','','',0,'',277,'','','','',''),('RDG005','Autoimmune polyendocrinopathy syndrome Type 1',0,0,0,0,0,0,0,0,0,'','','','','',0,'',278,'','','','',''),('RDG006','EAST syndrome',0,0,0,0,0,0,0,0,0,'','','','','',0,'',279,'','','','',''),('RDG007','Hypoparathyroidism (primary) isolated familial',0,0,0,0,0,0,0,0,0,'','','','','',0,'',280,'','','','',''),('RDG008','Hypoparathyroidism (primary) with Sensorineural Deafness and renal Dysplasia',0,0,0,0,0,0,0,0,0,'','','','','',0,'',281,'','','','',''),('RDG009','Hyperparathyroidism (primary) monogenetic forms',0,0,0,0,0,0,0,0,0,'','','','','',0,'',282,'','','','',''),('RDG010','Hyperparathyroidism, Neonatal severe primary',0,0,0,0,0,0,0,0,0,'','','','','',0,'',283,'','','','',''),('RDG011','Hyperparathyroidism type 2',0,0,0,0,0,0,0,0,0,'','','','','',0,'',284,'','','','',''),('RDG012','Renal Cell Carcinoma Papillary 2',0,0,0,0,0,0,0,0,0,'','','','','',0,'',285,'','','','',''),('RDG013','Lipoprotein Glomerulopathy',0,0,0,0,0,0,0,0,0,'','','','','',0,'',286,'','','','',''),('xAHUS','Atypical Haemolytic Uraemic Syndrome',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xARPKD','ARPKD – autosomal recessive polycystic kidney disease',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xFUAN','Familial Urate Associated Nephropathy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xHYPALK','Hypokalaemic alkalosis',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xHYPERRDG','Hyperoxaluria',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xMEMRDG','Membranous nephropathy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('xSTECHUS','STEC-associated HUS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `rdr_user_mapping` VALUES (57987,4,'ROLE_ADMIN'),(57992,1063,'ROLE_PROFESSIONAL'),(57993,75,'ROLE_PATIENT'),(57995,76,'ROLE_PATIENT'),(58009,77,'ROLE_PATIENT'),(58011,78,'ROLE_PATIENT'),(58019,80,'ROLE_PATIENT'),(58033,1068,'ROLE_PROFESSIONAL'),(58027,81,'ROLE_PATIENT'),(58029,82,'ROLE_PATIENT');
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
) ENGINE=MyISAM AUTO_INCREMENT=57896 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialtyuserrole`
--

LOCK TABLES `specialtyuserrole` WRITE;
/*!40000 ALTER TABLE `specialtyuserrole` DISABLE KEYS */;
INSERT INTO `specialtyuserrole` VALUES (57849,'superadmin',1,57987),(57893,'patient',1,58031),(57889,'patient',1,58027),(57890,'patient',1,58028),(57891,'patient',1,58029),(57892,'patient',1,58030),(57894,'patient',1,58032),(57895,'unitadmin',1,58033);
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
INSERT INTO `tbl_adminusers` VALUES (4);
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
) ENGINE=MyISAM AUTO_INCREMENT=2077 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_clinicaldata`
--

LOCK TABLES `tbl_clinicaldata` WRITE;
/*!40000 ALTER TABLE `tbl_clinicaldata` DISABLE KEYS */;
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
-- Table structure for table `tbl_demographics`
--

DROP TABLE IF EXISTS `tbl_demographics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_demographics` (
  `RADAR_NO` int(11) NOT NULL AUTO_INCREMENT,
  `RR_NO` varchar(10) DEFAULT NULL,
  `DATE_REG` datetime DEFAULT NULL,
  `NHS_NO` varchar(50) DEFAULT NULL,
  `NHS_NO_TYPE` int(11) NOT NULL,
  `HOSP_NO` varchar(50) DEFAULT NULL,
  `UKT_NO` bigint(20) DEFAULT NULL,
  `SNAME` varchar(50) DEFAULT NULL,
  `SNAME_ALIAS` varchar(50) DEFAULT NULL,
  `FNAME` varchar(50) DEFAULT NULL,
  `DOB` varchar(50) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `SEX` int(11) DEFAULT NULL,
  `ETHNIC_GP` varchar(6) DEFAULT NULL,
  `ADD1` varchar(50) DEFAULT NULL,
  `ADD2` varchar(50) DEFAULT NULL,
  `ADD3` varchar(50) DEFAULT NULL,
  `ADD4` varchar(50) DEFAULT NULL,
  `POSTCODE` varchar(50) DEFAULT NULL,
  `POSTCODE_OLD` varchar(50) DEFAULT NULL,
  `CONSENT` bit(1) DEFAULT NULL,
  `DATE_BAPN_REG` datetime DEFAULT NULL,
  `CONS_NEPH` varchar(6) DEFAULT NULL,
  `RENAL_UNIT` int(11) DEFAULT NULL,
  `RENAL_UNIT_2` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `RDG` varchar(100) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `phone1` varchar(20) DEFAULT NULL,
  `phone2` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `RRT_modality` int(10) DEFAULT NULL,
  `genericDiagnosis` varchar(20) DEFAULT NULL,
  `dateOfGenericDiagnosis` datetime DEFAULT NULL,
  `otherClinicianAndContactInfo` varchar(500) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `republicOfIrelandId` varchar(20) DEFAULT NULL,
  `isleOfManId` varchar(20) DEFAULT NULL,
  `channelIslandsId` varchar(20) DEFAULT NULL,
  `indiaId` varchar(20) DEFAULT NULL,
  `generic` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`RADAR_NO`),
  KEY `fk_RDG` (`RDG`),
  KEY `fk_genericDiagnosis` (`genericDiagnosis`)
) ENGINE=MyISAM AUTO_INCREMENT=736 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_demographics`
--

LOCK TABLES `tbl_demographics` WRITE;
/*!40000 ALTER TABLE `tbl_demographics` DISABLE KEYS */;
INSERT INTO `tbl_demographics` VALUES (734,NULL,'2013-04-26 00:00:00','6810341560',1,NULL,NULL,'One',NULL,'Patient','02.04.57',56,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,1098,NULL,NULL,'HNF1B',NULL,NULL,NULL,NULL,NULL,'3627','2013-04-01 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,1),(735,NULL,'2013-04-26 00:00:00','7531599198',1,NULL,NULL,'Two',NULL,'Patient','12.12.45',67,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0',NULL,NULL,1098,NULL,NULL,'FUAN',NULL,NULL,NULL,NULL,NULL,'xFUAN','2013-04-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tbl_demographics` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=549 DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM AUTO_INCREMENT=611 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_immunsup_treatment`
--

LOCK TABLES `tbl_immunsup_treatment` WRITE;
/*!40000 ALTER TABLE `tbl_immunsup_treatment` DISABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=1972 DEFAULT CHARSET=latin1;
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
) ENGINE=MyISAM AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_patient_users`
--

LOCK TABLES `tbl_patient_users` WRITE;
/*!40000 ALTER TABLE `tbl_patient_users` DISABLE KEYS */;
INSERT INTO `tbl_patient_users` VALUES (81,734,'1957-04-02 00:00:00','2013-04-26 10:45:54'),(82,735,'1945-12-12 00:00:00','2013-04-26 10:47:48');
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_status`
--

LOCK TABLES `tbl_status` WRITE;
/*!40000 ALTER TABLE `tbl_status` DISABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=1069 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users`
--

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
INSERT INTO `tbl_users` VALUES (1068,'Admin','UnitA',NULL,NULL,NULL,NULL,1098,'2013-05-01 09:09:25');
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
) ENGINE=MyISAM AUTO_INCREMENT=543910892 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testresult`
--

LOCK TABLES `testresult` WRITE;
/*!40000 ALTER TABLE `testresult` DISABLE KEYS */;
INSERT INTO `testresult` VALUES (543910891,'1111111112','SDFSDF','urea','2012-10-14 00:00:00','','10',NULL),(543910890,'1111111112','PATIENT','glucose','2013-04-26 10:59:48','','34',NULL),(543910883,'1111111111','RENALA','asdad','2013-03-14 00:00:00','','111',NULL),(543910889,'1111111111','B','urea','2012-04-04 00:00:00',NULL,'',NULL),(543910888,'1111111112','B','urea','2013-03-03 00:00:00','','112',NULL),(543910886,'7531599198','FUAN','urea','2013-12-12 00:00:00',NULL,'23.0',735),(543910887,'7531599198','FUAN','creatinine','2012-10-10 00:00:00',NULL,'45.0',735);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `unitcode` (`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=1117 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1098,'RENALA','Renal Unit A','Renal A','','','','','','','','','','patientview-testing@solidstategroup.com','','patientview-testing@solidstategroup.com','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','renalunit',1,'1'),(1099,'SRNS','SRNS: Steroid Resistant Nephrotic Syndrome (including inherited, monogenic, primary FSGS and syndrom','SRNS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1100,'MPGN','Membranoproliferative glomerulonephritis / Dense Deposit Disease ','MPGN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1101,'ALPORT','Alport syndrome, and disorders of basement membrane structure','Alport',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1102,'HNF1B','HNF1b Mutations','HNF1B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1103,'AHUS','Atypical Haemolytic Uraemic Syndrome','AHUS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1104,'ARPKD','ARPKD - autosomal recessive polycystic kidney disease','ARPKD',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1105,'VASRDG','Vasculitis','VASRDG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1106,'HYPALK','Hypokalaemic alkalosis','HYPALK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1107,'CYSTIN','Cystinosis','CYSTIN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1108,'STECHUS','STEC-associated HUS','STECHUS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1109,'HYPERRDG','Hyperoxaluria','HYPERRDG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1110,'FUAN','Familial Urate Associated Nephropathy','FUAN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1111,'MEMRDG','Membranous nephropathy','MEMRDG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'radargroup',1,NULL),(1112,'PATIENT','Patient Entered','Patient',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'renalunit',1,NULL),(1116,'RENALB','Renal Unit B','Renal B','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','renalunit',1,'1');
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
  `screenname` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=58034 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (57987,'superadmin','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Super Admin','patientview-testing@solidstategroup.com',0,0,0,'2013-05-07 10:41:36',0,0,''),(58031,'patientb','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient B','patientview-testing@solidstategroup.com',0,0,0,'2013-05-07 15:38:12',0,0,''),(58027,'patient1','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient One','',0,0,0,'2013-05-03 11:06:58',2,0,'patient1'),(58028,'patient1-GP','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient One-GP',NULL,0,1,0,NULL,0,0,''),(58029,'patient2','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient Two','',0,1,0,NULL,0,0,'patient2'),(58030,'patient2-GP','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient Two-GP',NULL,0,1,0,NULL,0,0,''),(58032,'patientb-GP','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','Patient B-GP',NULL,0,1,0,NULL,0,0,''),(58033,'unitaadmin','891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb','','UnitA Admin','patientview-testing@solidstategroup.com',0,0,0,'2013-05-07 17:49:01',0,0,'');
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
) ENGINE=MyISAM AUTO_INCREMENT=88113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermapping`
--

LOCK TABLES `usermapping` WRITE;
/*!40000 ALTER TABLE `usermapping` DISABLE KEYS */;
INSERT INTO `usermapping` VALUES (88106,'patientb','RENALB','1111111111',1),(88100,'patient1','RENALA','6810341560',1),(88101,'patient1','PATIENT','6810341560',1),(88102,'patient1-GP','RENALA','6810341560',1),(88103,'patient2','RENALA','7531599198',1),(88104,'patient2','PATIENT','7531599198',1),(88105,'patient2-GP','RENALA','7531599198',1),(88107,'patientb','PATIENT','1111111112',1),(88108,'patientb-GP','RENALB','1111111112',1),(88109,'patientb','RENALA','1111111111',1),(88110,'patientb-GP','RENALA','1111111111',1),(88111,'unitaadmin','RENALA','',1),(88112,'unitaadmin','RENALB',NULL,1);
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

-- Dump completed on 2013-05-09 10:21:04
SET AUTOCOMMIT=1;
SET FOREIGN_KEY_CHECKS=1;
