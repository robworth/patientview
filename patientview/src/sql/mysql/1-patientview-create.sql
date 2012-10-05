-- MySQL dump 10.11
--
-- Host: localhost    Database: patientview
-- ------------------------------------------------------
-- Server version	5.0.95-log

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
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) default NULL,
  `aboutme` text,
  `talkabout` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `centre`
--

DROP TABLE IF EXISTS `centre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centre` (
  `id` bigint(20) NOT NULL auto_increment,
  `centreCode` varchar(10) NOT NULL default '',
  `centreName` varchar(100) default '',
  `centreAddress1` varchar(100) default '',
  `centreAddress2` varchar(100) default '',
  `centreAddress3` varchar(100) default '',
  `centreAddress4` varchar(100) default '',
  `centrePostCode` varchar(100) default '',
  `centreTelephone` varchar(100) default '',
  `centreEmail` varchar(100) default '',
  PRIMARY KEY  (`id`),
  KEY `centreCode` (`centreCode`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL auto_increment,
  `datestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `nhsno` varchar(10) NOT NULL default '',
  `body` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosis` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(20) NOT NULL default '',
  `unitcode` varchar(20) NOT NULL default '',
  `diagnosis` varchar(200) default '',
  `displayorder` int(3) default '0',
  PRIMARY KEY  (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diagnostic`
--

DROP TABLE IF EXISTS `diagnostic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnostic` (
  `id` bigint(20) NOT NULL auto_increment,
  `datestamp` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `diagnostic_type_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `edtacode`
--

DROP TABLE IF EXISTS `edtacode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edtacode` (
  `id` bigint(20) NOT NULL auto_increment,
  `edtaCode` varchar(100) NOT NULL default '',
  `linkType` varchar(20) default NULL,
  `description` varchar(100) default '',
  `medicalLink01` varchar(100) default '',
  `medicalLink02` varchar(100) default '',
  `medicalLink03` varchar(100) default '',
  `medicalLink04` varchar(100) default '',
  `medicalLink05` varchar(100) default '',
  `medicalLink06` varchar(100) default '',
  `medicalLinkText01` varchar(100) default '',
  `medicalLinkText02` varchar(100) default '',
  `medicalLinkText03` varchar(100) default '',
  `medicalLinkText04` varchar(100) default '',
  `medicalLinkText05` varchar(100) default '',
  `medicalLinkText06` varchar(100) default '',
  `patientLink01` varchar(100) default '',
  `patientLink02` varchar(100) default '',
  `patientLink03` varchar(100) default '',
  `patientLink04` varchar(100) default '',
  `patientLink05` varchar(100) default '',
  `patientLink06` varchar(100) default '',
  `patientLinkText01` varchar(100) default '',
  `patientLinkText02` varchar(100) default '',
  `patientLinkText03` varchar(100) default '',
  `patientLinkText04` varchar(100) default '',
  `patientLinkText05` varchar(100) default '',
  `patientLinkText06` varchar(100) default '',
  `tenancy_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `edtaCode` (`edtaCode`),
  KEY `FK75096F79AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `emailverification`
--

DROP TABLE IF EXISTS `emailverification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailverification` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `verificationcode` varchar(50) NOT NULL,
  `expirydatestamp` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `datestamp` datetime NOT NULL,
  `comment` text NOT NULL,
  `commentedited` text,
  `anonymous` tinyint(1) NOT NULL,
  `makepublic` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_careplan`
--

DROP TABLE IF EXISTS `ibd_careplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_careplan` (
  `id` bigint(20) NOT NULL auto_increment,
  `barriers` text,
  `confidenceScale` int(11) default NULL,
  `furtherTopics` text,
  `goalScale` int(11) default NULL,
  `goalToAchieve` text,
  `goals` text,
  `howToAchieveGoal` text,
  `nhsno` varchar(255) NOT NULL,
  `reviewDate` datetime default NULL,
  `whatCanBeDone` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_careplan_areas_to_discuss`
--

DROP TABLE IF EXISTS `ibd_careplan_areas_to_discuss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_careplan_areas_to_discuss` (
  `careplan_id` bigint(20) NOT NULL,
  `area_to_discuss_id` bigint(20) default NULL,
  KEY `FK149F8986A3FEFAD1` (`careplan_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_colitis_symptoms`
--

DROP TABLE IF EXISTS `ibd_colitis_symptoms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_colitis_symptoms` (
  `id` bigint(20) NOT NULL auto_increment,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `complication_id` int(11) NOT NULL,
  `number_of_stools_daytime_id` int(11) NOT NULL,
  `number_of_stools_nighttime_id` int(11) NOT NULL,
  `present_blood_id` int(11) NOT NULL,
  `toilet_timing_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_crohns_symptoms`
--

DROP TABLE IF EXISTS `ibd_crohns_symptoms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_crohns_symptoms` (
  `id` bigint(20) NOT NULL auto_increment,
  `feeling_id` int(11) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `symptomDate` datetime NOT NULL,
  `abdominal_pain_id` int(11) NOT NULL,
  `complication_id` int(11) NOT NULL,
  `mass_in_tummy_id` int(11) NOT NULL,
  `openBowels` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_medication`
--

DROP TABLE IF EXISTS `ibd_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_medication_dose`
--

DROP TABLE IF EXISTS `ibd_medication_dose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_dose` (
  `id` bigint(20) NOT NULL auto_increment,
  `extraInformation` varchar(255) default NULL,
  `mg` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_medication_type`
--

DROP TABLE IF EXISTS `ibd_medication_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_medication_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_my_medication`
--

DROP TABLE IF EXISTS `ibd_my_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_my_medication` (
  `id` bigint(20) NOT NULL auto_increment,
  `dateStarted` datetime NOT NULL,
  `dateStopped` datetime default NULL,
  `medication_frequency_id` bigint(20) NOT NULL,
  `medication_no_of_id` bigint(20) NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherMedication` text,
  `reasonForStopping` text,
  `medication_id` bigint(20) default NULL,
  `medication_dose_id` bigint(20) default NULL,
  `medication_type_id` bigint(20) NOT NULL,
  `otherMedicationDose` text,
  PRIMARY KEY  (`id`),
  KEY `FK434B606EF8204181` (`medication_dose_id`),
  KEY `FK434B606E99BAAE42` (`medication_id`),
  KEY `FK434B606E57719061` (`medication_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_myibd`
--

DROP TABLE IF EXISTS `ibd_myibd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_myibd` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `body_part_affected_id` bigint(20) NOT NULL,
  `diagnosis_id` bigint(20) NOT NULL,
  `disease_extent_id` bigint(20) NOT NULL,
  `eiManifestations` varchar(255) default NULL,
  `family_history_id` bigint(20) NOT NULL,
  `namedConsultant` text,
  `nurses` text,
  `smoking_id` bigint(20) NOT NULL,
  `surgery_id` bigint(20) NOT NULL,
  `vaccination_record_id` bigint(20) NOT NULL,
  `weight` double default NULL,
  `yearForSurveillanceColonoscopy` datetime default NULL,
  `yearOfDiagnosis` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_myibd_complications`
--

DROP TABLE IF EXISTS `ibd_myibd_complications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_myibd_complications` (
  `myibd_id` bigint(20) NOT NULL,
  `complication_id` bigint(20) default NULL,
  KEY `FKC4EEAD21FDB07963` (`myibd_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_myibd_severity_level`
--

DROP TABLE IF EXISTS `ibd_myibd_severity_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_myibd_severity_level` (
  `id` bigint(20) NOT NULL auto_increment,
  `level` int(11) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `severity_id` bigint(20) NOT NULL,
  `treatment` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ibd_nutrition`
--

DROP TABLE IF EXISTS `ibd_nutrition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ibd_nutrition` (
  `id` bigint(20) NOT NULL auto_increment,
  `comment` varchar(255) default NULL,
  `foodsThatDisagree` varchar(255) NOT NULL,
  `weight` double NOT NULL,
  `nhsno` varchar(255) NOT NULL,
  `nutritionDate` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_api`
--

DROP TABLE IF EXISTS `jforum_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_api` (
  `api_id` int(11) NOT NULL auto_increment,
  `api_key` varchar(32) NOT NULL,
  `api_validity` datetime NOT NULL,
  PRIMARY KEY  (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_attach`
--

DROP TABLE IF EXISTS `jforum_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach` (
  `attach_id` int(11) NOT NULL auto_increment,
  `post_id` int(11) default NULL,
  `privmsgs_id` int(11) default NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`attach_id`),
  KEY `idx_att_post` (`post_id`),
  KEY `idx_att_priv` (`privmsgs_id`),
  KEY `idx_att_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_attach_desc`
--

DROP TABLE IF EXISTS `jforum_attach_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach_desc` (
  `attach_desc_id` int(11) NOT NULL auto_increment,
  `attach_id` int(11) NOT NULL,
  `physical_filename` varchar(255) NOT NULL,
  `real_filename` varchar(255) NOT NULL,
  `download_count` int(11) default NULL,
  `description` varchar(255) default NULL,
  `mimetype` varchar(50) default NULL,
  `filesize` int(11) default NULL,
  `upload_time` datetime default NULL,
  `thumb` tinyint(1) default '0',
  `extension_id` int(11) default NULL,
  PRIMARY KEY  (`attach_desc_id`),
  KEY `idx_att_d_att` (`attach_id`),
  KEY `idx_att_d_ext` (`extension_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_attach_quota`
--

DROP TABLE IF EXISTS `jforum_attach_quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_attach_quota` (
  `attach_quota_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) NOT NULL,
  `quota_limit_id` int(11) NOT NULL,
  PRIMARY KEY  (`attach_quota_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_banlist`
--

DROP TABLE IF EXISTS `jforum_banlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_banlist` (
  `banlist_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `banlist_ip` varchar(15) default NULL,
  `banlist_email` varchar(255) default NULL,
  PRIMARY KEY  (`banlist_id`),
  KEY `idx_user` (`user_id`),
  KEY `banlist_ip` (`banlist_ip`),
  KEY `banlist_email` (`banlist_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_banner`
--

DROP TABLE IF EXISTS `jforum_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_banner` (
  `banner_id` int(11) NOT NULL auto_increment,
  `banner_name` varchar(90) default NULL,
  `banner_placement` int(11) NOT NULL default '0',
  `banner_description` varchar(250) default NULL,
  `banner_clicks` int(11) NOT NULL default '0',
  `banner_views` int(11) NOT NULL default '0',
  `banner_url` varchar(250) default NULL,
  `banner_weight` tinyint(1) NOT NULL default '50',
  `banner_active` tinyint(1) NOT NULL default '0',
  `banner_comment` varchar(250) default NULL,
  `banner_type` int(11) NOT NULL default '0',
  `banner_width` int(11) NOT NULL default '0',
  `banner_height` int(11) NOT NULL default '0',
  PRIMARY KEY  (`banner_id`),
  KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_bookmarks`
--

DROP TABLE IF EXISTS `jforum_bookmarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_bookmarks` (
  `bookmark_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `relation_id` int(11) NOT NULL,
  `relation_type` int(11) NOT NULL,
  `public_visible` int(11) default '1',
  `title` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`bookmark_id`),
  KEY `book_idx_relation` (`relation_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_categories`
--

DROP TABLE IF EXISTS `jforum_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_categories` (
  `categories_id` int(11) NOT NULL auto_increment,
  `title` varchar(100) NOT NULL default '',
  `display_order` int(11) NOT NULL default '0',
  `moderated` tinyint(1) default '0',
  PRIMARY KEY  (`categories_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_config`
--

DROP TABLE IF EXISTS `jforum_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_config` (
  `config_name` varchar(255) NOT NULL default '',
  `config_value` varchar(255) NOT NULL default '',
  `config_id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_extension_groups`
--

DROP TABLE IF EXISTS `jforum_extension_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_extension_groups` (
  `extension_group_id` int(11) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL,
  `allow` tinyint(1) default '1',
  `upload_icon` varchar(100) default NULL,
  `download_mode` tinyint(1) default '1',
  PRIMARY KEY  (`extension_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_extensions`
--

DROP TABLE IF EXISTS `jforum_extensions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_extensions` (
  `extension_id` int(11) NOT NULL auto_increment,
  `extension_group_id` int(11) NOT NULL,
  `description` varchar(100) default NULL,
  `upload_icon` varchar(100) default NULL,
  `extension` varchar(10) default NULL,
  `allow` tinyint(1) default '1',
  PRIMARY KEY  (`extension_id`),
  KEY `extension_group_id` (`extension_group_id`),
  KEY `extension` (`extension`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_forums`
--

DROP TABLE IF EXISTS `jforum_forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_forums` (
  `forum_id` int(11) NOT NULL auto_increment,
  `categories_id` int(11) NOT NULL default '1',
  `forum_name` varchar(150) NOT NULL default '',
  `forum_desc` varchar(255) default NULL,
  `forum_order` int(11) default '1',
  `forum_topics` int(11) NOT NULL default '0',
  `forum_last_post_id` int(11) NOT NULL default '0',
  `moderated` tinyint(1) default '0',
  PRIMARY KEY  (`forum_id`),
  KEY `categories_id` (`categories_id`),
  KEY `idx_forums_cats` (`categories_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `jforum_groups`
--

DROP TABLE IF EXISTS `jforum_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_groups` (
  `group_id` int(11) NOT NULL auto_increment,
  `group_name` varchar(40) NOT NULL default '',
  `group_description` varchar(255) default NULL,
  `parent_id` int(11) default '0',
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_karma`
--

DROP TABLE IF EXISTS `jforum_karma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_karma` (
  `karma_id` int(11) NOT NULL auto_increment,
  `post_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `post_user_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `rate_date` datetime default NULL,
  PRIMARY KEY  (`karma_id`),
  KEY `post_id` (`post_id`),
  KEY `topic_id` (`topic_id`),
  KEY `post_user_id` (`post_user_id`),
  KEY `from_user_id` (`from_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `pop_port` int(11) default '110',
  `pop_ssl` tinyint(4) default '0',
  KEY `forum_id` (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_moderation_log`
--

DROP TABLE IF EXISTS `jforum_moderation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_moderation_log` (
  `log_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `log_description` text NOT NULL,
  `log_original_message` text,
  `log_date` datetime NOT NULL,
  `log_type` tinyint(4) default '0',
  `post_id` int(11) default '0',
  `topic_id` int(11) default '0',
  `post_user_id` int(11) default '0',
  PRIMARY KEY  (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `post_user_id` (`post_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_posts`
--

DROP TABLE IF EXISTS `jforum_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_posts` (
  `post_id` int(11) NOT NULL auto_increment,
  `topic_id` int(11) NOT NULL default '0',
  `forum_id` int(11) NOT NULL default '0',
  `user_id` int(11) NOT NULL default '0',
  `post_time` datetime default NULL,
  `poster_ip` varchar(15) default NULL,
  `enable_bbcode` tinyint(1) NOT NULL default '1',
  `enable_html` tinyint(1) NOT NULL default '1',
  `enable_smilies` tinyint(1) NOT NULL default '1',
  `enable_sig` tinyint(1) NOT NULL default '1',
  `post_edit_time` datetime default NULL,
  `post_edit_count` int(11) NOT NULL default '0',
  `status` tinyint(1) default '1',
  `attach` tinyint(1) default '0',
  `need_moderate` tinyint(1) default '0',
  PRIMARY KEY  (`post_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `post_time` (`post_time`),
  KEY `need_moderate` (`need_moderate`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_posts_text`
--

DROP TABLE IF EXISTS `jforum_posts_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_posts_text` (
  `post_id` int(11) NOT NULL,
  `post_text` text,
  `post_subject` varchar(100) default NULL,
  PRIMARY KEY  (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_privmsgs`
--

DROP TABLE IF EXISTS `jforum_privmsgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_privmsgs` (
  `privmsgs_id` int(11) NOT NULL auto_increment,
  `privmsgs_type` tinyint(4) NOT NULL default '0',
  `privmsgs_subject` varchar(255) NOT NULL default '',
  `privmsgs_from_userid` int(11) NOT NULL default '0',
  `privmsgs_to_userid` int(11) NOT NULL default '0',
  `privmsgs_date` datetime default NULL,
  `privmsgs_ip` varchar(15) NOT NULL default '',
  `privmsgs_enable_bbcode` tinyint(1) NOT NULL default '1',
  `privmsgs_enable_html` tinyint(1) NOT NULL default '0',
  `privmsgs_enable_smilies` tinyint(1) NOT NULL default '1',
  `privmsgs_attach_sig` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_privmsgs_text`
--

DROP TABLE IF EXISTS `jforum_privmsgs_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_privmsgs_text` (
  `privmsgs_id` int(11) NOT NULL,
  `privmsgs_text` text,
  PRIMARY KEY  (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_quota_limit`
--

DROP TABLE IF EXISTS `jforum_quota_limit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_quota_limit` (
  `quota_limit_id` int(11) NOT NULL auto_increment,
  `quota_desc` varchar(50) NOT NULL,
  `quota_limit` int(11) NOT NULL,
  `quota_type` tinyint(1) default '1',
  PRIMARY KEY  (`quota_limit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_ranks`
--

DROP TABLE IF EXISTS `jforum_ranks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_ranks` (
  `rank_id` int(11) NOT NULL auto_increment,
  `rank_title` varchar(50) NOT NULL default '',
  `rank_min` int(11) NOT NULL default '0',
  `rank_special` tinyint(1) default NULL,
  `rank_image` varchar(255) default NULL,
  PRIMARY KEY  (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_role_values`
--

DROP TABLE IF EXISTS `jforum_role_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_role_values` (
  `role_id` int(11) NOT NULL,
  `role_value` varchar(255) default NULL,
  KEY `idx_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_roles`
--

DROP TABLE IF EXISTS `jforum_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_roles` (
  `role_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) default '0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`role_id`),
  KEY `idx_group` (`group_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_sessions`
--

DROP TABLE IF EXISTS `jforum_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_sessions` (
  `session_id` varchar(150) NOT NULL default '',
  `session_user_id` int(11) NOT NULL default '0',
  `session_start` datetime default NULL,
  `session_time` bigint(20) default '0',
  `session_ip` varchar(15) NOT NULL default '',
  `session_page` int(11) NOT NULL default '0',
  `session_logged_int` tinyint(1) default NULL,
  KEY `idx_sessions_users` (`session_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_smilies`
--

DROP TABLE IF EXISTS `jforum_smilies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_smilies` (
  `smilie_id` int(11) NOT NULL auto_increment,
  `code` varchar(50) NOT NULL default '',
  `url` varchar(100) default NULL,
  `disk_name` varchar(255) default NULL,
  PRIMARY KEY  (`smilie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_themes`
--

DROP TABLE IF EXISTS `jforum_themes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_themes` (
  `themes_id` int(11) NOT NULL auto_increment,
  `template_name` varchar(30) NOT NULL default '',
  `style_name` varchar(30) NOT NULL default '',
  PRIMARY KEY  (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_topics`
--

DROP TABLE IF EXISTS `jforum_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_topics` (
  `topic_id` int(11) NOT NULL auto_increment,
  `forum_id` int(11) NOT NULL default '0',
  `topic_title` varchar(100) NOT NULL default '',
  `user_id` int(11) NOT NULL default '0',
  `topic_time` datetime default NULL,
  `topic_views` int(11) default '1',
  `topic_replies` int(11) default '0',
  `topic_status` tinyint(3) default '0',
  `topic_vote_id` int(11) NOT NULL default '0',
  `topic_type` tinyint(3) default '0',
  `topic_first_post_id` int(11) default '0',
  `topic_last_post_id` int(11) NOT NULL default '0',
  `topic_moved_id` int(11) default '0',
  `moderated` tinyint(1) default '0',
  PRIMARY KEY  (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_first_post_id` (`topic_first_post_id`),
  KEY `topic_last_post_id` (`topic_last_post_id`),
  KEY `topic_moved_id` (`topic_moved_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_topics_watch`
--

DROP TABLE IF EXISTS `jforum_topics_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_topics_watch` (
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(1) default '1',
  KEY `idx_topic` (`topic_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `jforum_users`
--

DROP TABLE IF EXISTS `jforum_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_users` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_active` tinyint(1) default NULL,
  `username` varchar(50) NOT NULL default '',
  `user_password` varchar(32) NOT NULL default '',
  `user_session_time` bigint(20) default '0',
  `user_session_page` int(11) NOT NULL default '0',
  `user_lastvisit` datetime default NULL,
  `user_regdate` datetime default NULL,
  `user_level` tinyint(4) default NULL,
  `user_posts` int(11) NOT NULL default '0',
  `user_timezone` varchar(5) NOT NULL default '',
  `user_style` tinyint(4) default NULL,
  `user_lang` varchar(255) NOT NULL default '',
  `user_dateformat` varchar(20) NOT NULL default '%d/%M/%Y %H:%i',
  `user_new_privmsg` int(11) NOT NULL default '0',
  `user_unread_privmsg` int(11) NOT NULL default '0',
  `user_last_privmsg` datetime default NULL,
  `user_emailtime` datetime default NULL,
  `user_viewemail` tinyint(1) default '0',
  `user_attachsig` tinyint(1) default '1',
  `user_allowhtml` tinyint(1) default '0',
  `user_allowbbcode` tinyint(1) default '1',
  `user_allowsmilies` tinyint(1) default '1',
  `user_allowavatar` tinyint(1) default '1',
  `user_allow_pm` tinyint(1) default '1',
  `user_allow_viewonline` tinyint(1) default '1',
  `user_notify` tinyint(1) default '1',
  `user_notify_always` tinyint(1) default '0',
  `user_notify_text` tinyint(1) default '0',
  `user_notify_pm` tinyint(1) default '1',
  `user_popup_pm` tinyint(1) default '1',
  `rank_id` int(11) default '0',
  `user_avatar` varchar(100) default NULL,
  `user_avatar_type` tinyint(4) NOT NULL default '0',
  `user_email` varchar(255) NOT NULL default '',
  `user_icq` varchar(15) default NULL,
  `user_website` varchar(255) default NULL,
  `user_from` varchar(100) default NULL,
  `user_sig` text,
  `user_sig_bbcode_uid` varchar(10) default NULL,
  `user_aim` varchar(255) default NULL,
  `user_yim` varchar(255) default NULL,
  `user_msnm` varchar(255) default NULL,
  `user_occ` varchar(100) default NULL,
  `user_interests` varchar(255) default NULL,
  `user_biography` text,
  `user_actkey` varchar(32) default NULL,
  `gender` char(1) default NULL,
  `themes_id` int(11) default NULL,
  `deleted` tinyint(1) default NULL,
  `user_viewonline` tinyint(1) default '1',
  `security_hash` varchar(32) default NULL,
  `user_karma` double default NULL,
  `user_authhash` varchar(32) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_vote_desc`
--

DROP TABLE IF EXISTS `jforum_vote_desc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_desc` (
  `vote_id` int(11) NOT NULL auto_increment,
  `topic_id` int(11) NOT NULL default '0',
  `vote_text` varchar(255) NOT NULL default '',
  `vote_start` datetime NOT NULL,
  `vote_length` int(11) NOT NULL default '0',
  PRIMARY KEY  (`vote_id`),
  KEY `topic_id` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_vote_results`
--

DROP TABLE IF EXISTS `jforum_vote_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_results` (
  `vote_id` int(11) NOT NULL default '0',
  `vote_option_id` tinyint(4) NOT NULL default '0',
  `vote_option_text` varchar(255) NOT NULL default '',
  `vote_result` int(11) NOT NULL default '0',
  KEY `vote_id` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_vote_voters`
--

DROP TABLE IF EXISTS `jforum_vote_voters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_vote_voters` (
  `vote_id` int(11) NOT NULL default '0',
  `vote_user_id` int(11) NOT NULL default '0',
  `vote_user_ip` varchar(15) NOT NULL default '',
  KEY `vote_id` (`vote_id`),
  KEY `vote_user_id` (`vote_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jforum_words`
--

DROP TABLE IF EXISTS `jforum_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jforum_words` (
  `word_id` int(11) NOT NULL auto_increment,
  `word` varchar(100) NOT NULL default '',
  `replacement` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `letter`
--

DROP TABLE IF EXISTS `letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(100) NOT NULL default '',
  `unitcode` varchar(20) NOT NULL default '',
  `date` datetime default '0000-00-00 00:00:00',
  `type` varchar(100) default '',
  `content` text,
  PRIMARY KEY  (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL auto_increment,
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  `actor` varchar(100) NOT NULL default '',
  `action` varchar(100) NOT NULL default '',
  `nhsno` varchar(100) default '',
  `user` varchar(100) default '',
  `unitcode` varchar(100) default '',
  `extrainfo` text,
  `tenancy_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1A344AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=793 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(20) NOT NULL default '',
  `chino` varchar(20) default NULL,
  `unitcode` varchar(20) NOT NULL default '',
  `startdate` datetime default '0000-00-00 00:00:00',
  `name` varchar(100) default '',
  `dose` varchar(100) default '',
  `enddate` datetime default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL auto_increment,
  `datestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `unitcode` varchar(100) NOT NULL default '',
  `admin` tinyint(1) NOT NULL default '0',
  `patient` tinyint(1) NOT NULL default '0',
  `everyone` tinyint(10) NOT NULL default '0',
  `headline` varchar(255) NOT NULL default '',
  `body` text NOT NULL,
  `tenancy_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK24FEF3AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL auto_increment,
  `address1` varchar(255) default NULL,
  `address2` varchar(255) default NULL,
  `address3` varchar(255) default NULL,
  `address4` varchar(255) default NULL,
  `bloodgroup` varchar(255) default NULL,
  `bmdexam` datetime default NULL,
  `centreCode` varchar(20) NOT NULL,
  `colonoscopysurveillance` datetime default NULL,
  `dateofbirth` varchar(255) default NULL,
  `diagnosis` varchar(255) default NULL,
  `diagnosisDate` datetime default NULL,
  `forename` varchar(255) default NULL,
  `gpaddress1` varchar(255) default NULL,
  `gpaddress2` varchar(255) default NULL,
  `gpaddress3` varchar(255) default NULL,
  `gpemail` varchar(255) default NULL,
  `gpname` varchar(255) default NULL,
  `gppostcode` varchar(255) default NULL,
  `gptelephone` varchar(255) default NULL,
  `hospitalnumber` varchar(255) default NULL,
  `ibdnurse` varchar(255) default NULL,
  `mobile` varchar(255) default NULL,
  `namedconsultant` varchar(255) default NULL,
  `nhsno` varchar(255) NOT NULL,
  `otherConditions` text,
  `postcode` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `surname` varchar(255) default NULL,
  `telephone1` varchar(255) default NULL,
  `telephone2` varchar(255) default NULL,
  `transplantstatus` varchar(255) default NULL,
  `treatment` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pv_allergy`
--

DROP TABLE IF EXISTS `pv_allergy`;

CREATE TABLE `pv_allergy` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `confidenceLevel` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `infoSource` varchar(255) default NULL,
  `reaction` varchar(255) default NULL,
  `recordedDate` datetime default NULL,
  `status` varchar(255) default NULL,
  `substance` varchar(255) default NULL,
  `typeCode` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `pv_diagnostic`
--

DROP TABLE IF EXISTS `pv_diagnostic`;

CREATE TABLE `pv_diagnostic` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `date` datetime NOT NULL,
  `diagnostic` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `pv_procedure`
--

DROP TABLE IF EXISTS `pv_procedure`;

CREATE TABLE `pv_procedure` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(255) NOT NULL,
  `unitcode` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `proceduretext` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `rdc_genetic_test`
--

DROP TABLE IF EXISTS `rdc_genetic_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdc_genetic_test` (
  `id` bigint(20) NOT NULL auto_increment,
  `radar_no` bigint(20) NOT NULL,
  `testsDone` int(11) NOT NULL,
  `labWhereTestWasDone` text,
  `testDoneOn` text,
  `referenceNumber` varchar(255) default NULL,
  `whatResultsShowed` text,
  `keyEvidence` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rdr_alport_deafness`
--

DROP TABLE IF EXISTS `rdr_alport_deafness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_alport_deafness` (
  `id` bigint(20) NOT NULL auto_increment,
  `radar_no` bigint(20) NOT NULL,
  `evidenceOfDeafness` int(11) NOT NULL,
  `ageProblemFirstNoticed` int(11) default NULL,
  `ageStartedUsingHearingAid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rdr_prd_code`
--

DROP TABLE IF EXISTS `rdr_prd_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdr_prd_code` (
  `ERA_EDTA_PRD_code` varchar(20) NOT NULL,
  `ERA_EDTA_primaryRenalDiagnosisTerm` varchar(200) default NULL,
  `histology` tinyint(1) default NULL,
  `clinicalHistory` tinyint(1) default NULL,
  `familyHistory` tinyint(1) default NULL,
  `clinicalExam` tinyint(1) default NULL,
  `biochemistry` tinyint(1) default NULL,
  `immunology` tinyint(1) default NULL,
  `urineAnalysis` tinyint(1) default NULL,
  `imaging` tinyint(1) default NULL,
  `geneTest` tinyint(1) default NULL,
  `otherCriteriaAndNotes` varchar(1000) default NULL,
  `SNOMED_CT_conceptIdentifierForFocusConcept` varchar(50) default NULL,
  `SNOMED_CT_fullySpecifiedName` varchar(200) default NULL,
  `SNOMED_CT_expressionConstraint` varchar(200) default NULL,
  `majorHeading` varchar(200) default NULL,
  `mappingToOldPRDCode` int(10) default NULL,
  `mappingToOldPRDTerm` varchar(200) default NULL,
  `ERA_EDTA_defaultSortOrder` int(10) default NULL,
  `geneticsHomeReferenceLink` varchar(200) default NULL,
  `nationalCenterForBiotechnologyLink` varchar(200) default NULL,
  `ICD_10_code` varchar(200) default NULL,
  `ICD10_rubricTerm` varchar(200) default NULL,
  `alternativesearchTerms` varchar(200) default NULL,
  PRIMARY KEY  (`ERA_EDTA_PRD_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `result_heading`
--

DROP TABLE IF EXISTS `result_heading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result_heading` (
  `id` bigint(20) NOT NULL auto_increment,
  `headingcode` varchar(20) NOT NULL default '',
  `heading` varchar(30) NOT NULL default '',
  `rollover` varchar(50) NOT NULL default 'Click for info',
  `link` varchar(100) NOT NULL default '',
  `panel` int(11) NOT NULL default '0',
  `panelorder` int(11) NOT NULL default '0',
  `tenancy_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `headingcode` (`headingcode`),
  KEY `FK29BD7FE0AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `splashpage`
--

DROP TABLE IF EXISTS `splashpage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `splashpage` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `live` tinyint(1) NOT NULL,
  `headline` varchar(100) NOT NULL,
  `bodytext` text NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  `tenancy_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBA1851B6AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `splashpageuserseen`
--

DROP TABLE IF EXISTS `splashpageuserseen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `splashpageuserseen` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `splashpageid` int(10) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `usersplashpage` USING BTREE (`username`,`splashpageid`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tenancy`
--

DROP TABLE IF EXISTS `tenancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenancy` (
  `id` bigint(20) NOT NULL auto_increment,
  `context` varchar(255) NOT NULL,
  `description` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `context` (`context`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tenancyuserrole`
--

DROP TABLE IF EXISTS `tenancyuserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenancyuserrole` (
  `id` bigint(20) NOT NULL auto_increment,
  `role` varchar(255) NOT NULL,
  `tenancy_id` bigint(20) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `tenancy_id` (`tenancy_id`,`user_id`,`role`),
  KEY `FK7A1749E1AEFDD122` (`tenancy_id`),
  KEY `FK7A1749E1A4701372` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testresult`
--

DROP TABLE IF EXISTS `testresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testresult` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(100) NOT NULL default '',
  `unitcode` varchar(20) NOT NULL default '',
  `testcode` varchar(100) NOT NULL default '',
  `datestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `prepost` varchar(100) default '',
  `value` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `nhsno_testcode` (`nhsno`,`testcode`,`unitcode`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsNo` varchar(100) NOT NULL default '',
  `treatmentCode` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `nhsNo` (`nhsNo`,`treatmentCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `uktcode`
--

DROP TABLE IF EXISTS `uktcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uktcode` (
  `id` bigint(20) NOT NULL auto_increment,
  `uktcode` varchar(10) NOT NULL default '',
  `description` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uktcode_unique` (`uktcode`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `uktstatus`
--

DROP TABLE IF EXISTS `uktstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uktstatus` (
  `id` bigint(20) NOT NULL auto_increment,
  `nhsno` varchar(20) NOT NULL default '',
  `kidney` varchar(10) default '',
  `pancreas` varchar(10) default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `id` bigint(20) NOT NULL auto_increment,
  `unitcode` varchar(100) NOT NULL default '',
  `name` varchar(100) NOT NULL default '',
  `shortname` varchar(15) NOT NULL default '',
  `unituser` varchar(20) default NULL,
  `address1` varchar(100) default NULL,
  `address2` varchar(100) default NULL,
  `address3` varchar(100) default NULL,
  `postcode` varchar(100) default NULL,
  `uniturl` varchar(100) default NULL,
  `trusturl` varchar(100) default NULL,
  `rpvadminname` varchar(100) default NULL,
  `rpvadminphone` varchar(100) default NULL,
  `rpvadminemail` varchar(100) default NULL,
  `unitenquiriesphone` varchar(100) default NULL,
  `unitenquiriesemail` varchar(100) default NULL,
  `appointmentphone` varchar(100) default NULL,
  `appointmentemail` varchar(100) default NULL,
  `outofhours` varchar(100) default NULL,
  `peritonealdialysisphone` varchar(100) default NULL,
  `peritonealdialysisemail` varchar(100) default NULL,
  `haemodialysisunitname1` varchar(100) default NULL,
  `haemodialysisunitphone1` varchar(100) default NULL,
  `haemodialysisunitlocation1` varchar(100) default NULL,
  `haemodialysisuniturl1` varchar(100) default NULL,
  `haemodialysisunitname2` varchar(100) default NULL,
  `haemodialysisunitphone2` varchar(100) default NULL,
  `haemodialysisunitlocation2` varchar(100) default NULL,
  `haemodialysisuniturl2` varchar(100) default NULL,
  `haemodialysisunitname3` varchar(100) default NULL,
  `haemodialysisunitphone3` varchar(100) default NULL,
  `haemodialysisunitlocation3` varchar(100) default NULL,
  `haemodialysisuniturl3` varchar(100) default NULL,
  `haemodialysisunitname4` varchar(100) default NULL,
  `haemodialysisunitphone4` varchar(100) default NULL,
  `haemodialysisunitlocation4` varchar(100) default NULL,
  `haemodialysisuniturl4` varchar(100) default NULL,
  `haemodialysisunitname5` varchar(100) default NULL,
  `haemodialysisunitphone5` varchar(100) default NULL,
  `haemodialysisunitlocation5` varchar(100) default NULL,
  `haemodialysisuniturl5` varchar(100) default NULL,
  `haemodialysisunitname6` varchar(100) default NULL,
  `haemodialysisunitphone6` varchar(100) default NULL,
  `haemodialysisunitlocation6` varchar(100) default NULL,
  `haemodialysisuniturl6` varchar(100) default NULL,
  `haemodialysisunitname7` varchar(100) default NULL,
  `haemodialysisunitphone7` varchar(100) default NULL,
  `haemodialysisunitlocation7` varchar(100) default NULL,
  `haemodialysisuniturl7` varchar(100) default NULL,
  `haemodialysisunitname8` varchar(100) default NULL,
  `haemodialysisunitphone8` varchar(100) default NULL,
  `haemodialysisunitlocation8` varchar(100) default NULL,
  `haemodialysisuniturl8` varchar(100) default NULL,
  `haemodialysisunitname9` varchar(100) default NULL,
  `haemodialysisunitphone9` varchar(100) default NULL,
  `haemodialysisunitlocation9` varchar(100) default NULL,
  `haemodialysisuniturl9` varchar(100) default NULL,
  `haemodialysisunitname10` varchar(100) default NULL,
  `haemodialysisunitphone10` varchar(100) default NULL,
  `haemodialysisunitlocation10` varchar(100) default NULL,
  `haemodialysisuniturl10` varchar(100) default NULL,
  `haemodialysisunitname11` varchar(100) default NULL,
  `haemodialysisunitphone11` varchar(100) default NULL,
  `haemodialysisunitlocation11` varchar(100) default NULL,
  `haemodialysisuniturl11` varchar(100) default NULL,
  `haemodialysisunitname12` varchar(100) default NULL,
  `haemodialysisunitphone12` varchar(100) default NULL,
  `haemodialysisunitlocation12` varchar(100) default NULL,
  `haemodialysisuniturl12` varchar(100) default NULL,
  `tenancy_id` bigint(20) NOT NULL,
  `sourceType` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `unitcode` (`unitcode`),
  KEY `FK284DA4AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `unitstat`
--

DROP TABLE IF EXISTS `unitstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unitstat` (
  `id` bigint(20) NOT NULL auto_increment,
  `unitcode` varchar(20) NOT NULL,
  `yearmonth` varchar(7) NOT NULL,
  `action` varchar(30) NOT NULL,
  `count` int(10) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL default '',
  `password` varchar(100) NOT NULL default '',
  `name` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `emailverified` tinyint(1) default '0',
  `firstlogon` tinyint(1) default '0',
  `dummypatient` tinyint(1) NOT NULL default '0',
  `lastlogon` datetime default NULL,
  `failedlogons` int(10) default '0',
  `accountlocked` tinyint(1) default '0',
  `screenname` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userlog`
--

DROP TABLE IF EXISTS `userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlog` (
  `id` bigint(20) NOT NULL auto_increment,
  `datestamp` datetime NOT NULL default '0000-00-00 00:00:00',
  `unitcode` varchar(100) default NULL,
  `role` varchar(100) NOT NULL default '',
  `count` int(21) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usermapping`
--

DROP TABLE IF EXISTS `usermapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermapping` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `nhsno` varchar(10) default NULL,
  `tenancy_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK8357A263AEFDD122` (`tenancy_id`)
) ENGINE=MyISAM AUTO_INCREMENT=154 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-03 10:16:58
