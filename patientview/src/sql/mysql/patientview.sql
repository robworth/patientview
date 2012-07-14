/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50136
 Source Host           : localhost
 Source Database       : patientview

 Target Server Version : 50136
 File Encoding         : iso-8859-1

 Date: 07/10/2012 17:35:20 PM
*/

SET NAMES latin1;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `aboutme`
-- ----------------------------
DROP TABLE IF EXISTS `aboutme`;
CREATE TABLE `aboutme` (
  `id` int(11) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  `aboutme` text,
  `talkabout` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `aboutme`
-- ----------------------------
BEGIN;
INSERT INTO `aboutme` VALUES ('1', '1234567890', 'sdfsdf', 'fddf');
COMMIT;

-- ----------------------------
--  Table structure for `centre`
-- ----------------------------
DROP TABLE IF EXISTS `centre`;
CREATE TABLE `centre` (
  `centreCode` varchar(10) NOT NULL DEFAULT '',
  `centreName` varchar(100) DEFAULT '',
  `centreAddress1` varchar(100) DEFAULT '',
  `centreAddress2` varchar(100) DEFAULT '',
  `centreAddress3` varchar(100) DEFAULT '',
  `centreAddress4` varchar(100) DEFAULT '',
  `centrePostCode` varchar(100) DEFAULT '',
  `centreTelephone` varchar(100) DEFAULT '',
  `centreEmail` varchar(100) DEFAULT '',
  KEY `centreCode` (`centreCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `centre`
-- ----------------------------
BEGIN;
INSERT INTO `centre` VALUES ('A', null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nhsno` varchar(10) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `diagnosis`
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `diagnosis` varchar(200) DEFAULT '',
  `displayorder` int(3) DEFAULT '0',
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `edtaCode`
-- ----------------------------
DROP TABLE IF EXISTS `edtaCode`;
CREATE TABLE `edtaCode` (
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
  PRIMARY KEY (`edtaCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `edtaCode`
-- ----------------------------
BEGIN;
INSERT INTO `edtaCode` VALUES ('41', 'edtaCode', 'Adult polycystic kidney disease (PKD)', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/PCKDLong.html', 'http://www.pkdcharity.org.uk/adpkd.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/pckd.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', 'Adult PKD (long version) (EdREN)', 'Adult PKD (PKD Charity)', 'Adult PKD (NKF)', 'Chronic renal failure (EdREN)', '', ''), ('80', 'edtaCode', 'Diabetic Nephropathy', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', 'Chronic renal failure (Merck)', 'Blood pressure and renal disease (EdREN Handbook)', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/diabetes.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diabetic_nephLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', 'Diabetes and the kidney (NKF)', 'Diabetic kidney disease (EdREN)', 'Chronic renal failure (EdREN)', '', '', ''), ('00', 'edtaCode', 'Chronic renal failure, cause uncertain', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', 'Chronic renal failure (Merck)', 'Blood pressure and renal disease (EdREN Handbook)', '', '', '', '', 'http://www.renal.org/rixg/nodiagnosis.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'No diagnosis stated (RIXG)', 'Chronic Renal Failure (EdREN)', '', '', '', ''), ('82', 'edtaCode', 'Myeloma kidney (or light chain disease)', '', '', '', '', '', '', '', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/myel.html', '', '', '', '', '', 'Kidney disease in myeloma', '', '', '', '', ''), ('12', 'edtaCode', 'IgA nephropathy', 'http://www.igan-world.org/infoprof.htm', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', '', '', '', 'IgA nephropathy for professionals (IgAN Network)', 'Blood pressure and renal disease (EdREN Handbook)', 'Chronic renal failure (Merck)', '', '', '', 'http://www.igan-world.org/infopatients.htm', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/Iga.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/IgALong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', 'IgA nephropathy for patients (IgAN Network)', 'IgA nephropathy (NKF)', 'IgA nephropathy (EdREN)', 'Blood pressure and kidney disease (EdREN)', 'Chronic renal failure (EdREN)', ''), ('HD', 'treatment', 'Haemodialysis', 'http://renux.dmed.ed.ac.uk/EdREN/medinfo/medHD.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKhaemodial.html', '', '', '', '', 'Haemodialysis for the non-specialist', 'Haemodialysis for the renal unit SHO', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/haemodialysis.html', 'http://www.kidneypatientguide.org.uk/site/HD.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/HDShort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diet_CRF.html', '', '', 'Info on haemodialysis from the NKF', 'From the Kidney Patient Guide, with animations', 'Info on haemodialysis from EdREN', 'Diet for people with kidney disease', '', ''), ('PD', 'treatment', 'Peritoneal dialysis', 'http://renux.dmed.ed.ac.uk/EdREN/medinfo/medPD.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKPeritonealDialysis.html', '', '', '', '', 'PD for the non-specialist', 'Peritoneal dialysis for the renal unit SHO', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/pd.html', 'http://www.kidneypatientguide.org.uk/site/pd.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/PDShort.htm', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diet_CRF.html', '', '', 'Info on peritoneal dialysis from the NKF', 'From the Kidney Patient Guide, with animations', 'From EdREN', 'Diet for people with kidney disease', '', ''), ('TP', 'treatment', 'Kidney transplant', 'http://renux.dmed.ed.ac.uk/EdREN/medinfo/medTP.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenHDBKhome.html#anchor340890', '', '', '', '', 'Renal transplantation for the non-specialist', 'Edinburgh Transplant Unit Handbook', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/transplant.html', 'http://www.kidneypatientguide.org.uk/site/transplants.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/TransplantShort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diet_CRF.html', '', '', 'Info on kidney transplants', 'From the Kidney Patient Guide, with animations', 'From EdREN', 'Diet for people with kidney disease', '', ''), ('GEN', 'treatment', 'General nephrology (not dialysis, not transplant)', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', '', 'Blood pressure in renal disease', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diet_CRF.html', '', '', '', 'High blood pressure and kidney disease', 'Chronic kidney disease: preventing it from getting worse', 'Diet for people with kidney disease', '', '', ''), ('static', 'static', 'Further information', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenHDBKhome.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenHDBKhome.html#anchor340890', '', '', '', '', 'Edinburgh Renal Unit handbook', 'Edinburgh Transplant Unit handbook', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/index.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/longlist.html', 'http://kidney.niddk.nih.gov/kudiseases/a-z.asp', 'https://www.renalpatientview.org/infoLinks.do', '', '', 'Kidney info from the NKF', 'Kidney words explained (EdREN)', 'Kidney and urologic diseases from the NIDDK', 'See all our info links (on all diseases and treatments)', '', ''), ('14', 'edtaCode', 'Membranous nephropathy', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKnephrotic.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKProteinuria.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02', '', '', 'Nephrotic syndrome (EdREN Handbook)\r\n', 'Proteinuria (EdREN Handbook)\r\n', 'Blood pressure and renal disease (EdREN Handbook)\r\n', 'Chronic renal failure (Merck)', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Membranous.Long.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Membranous nephropathy (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('10', 'edtaCode', 'Glomerulonephritis', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', 'Blood pressure and renal disease (EdREN Handbook)\r\n', 'Chronic renal failure (Merck)', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/glom.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://kidney.niddk.nih.gov/kudiseases/pubs/glomerular/index.htm', '', '', 'Glomerulonephritis (NKF)', 'Blood pressure and kidney disease (EdREN)', 'Chronic renal failure (EdREN)', 'Glomerular diseases (NIDDK)', '', ''), ('11', 'edtaCode', 'FSGS with nephrotic syndrome', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKnephrotic.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKProteinuria.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', 'Nephrotic syndrome (EdREN Handbook)', 'Proteinuria (EdREN Handbook)', 'Blood pressure and renal disease (EdREN Handbook)', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/FSGSLong2.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/fsgs.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/NephroticLong.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_adult.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_child.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'FSGS (EdREN)', 'FSGS (NKF)', 'Nephrotic syndrome (EdREN)', 'Nephrotic syndrome in adults (NKF)', 'Nephrotic syndrome in children (NKF)', 'Chronic renal failure (EdREN)'), ('13', 'edtaCode', 'Dense deposit disease', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', '', '', '', '    (required)', 'Chronic renal failure (EdREN)', 'Blood pressure and kidney disease (EdREN)', '', '', ''), ('15', 'edtaCode', 'MPGN type 1', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', '', '', '', 'required', 'Chronic renal failure (EdREN)', 'Blood pressure and kidney disease (EdREN)', '', '', ''), ('16', 'edtaCode', 'Crescentic nephritis', '', '', '', '', '', '', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CrescenticShort.html', '', '', '', '', '', 'Crescentic nephritis (EdREN)', '', '', '', '', ''), ('17', 'edtaCode', 'FSGS with nephrotic syndrome', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKnephrotic.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKnephrotic.html', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKnephrotic.html', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', 'ephrotic syndrome (EdREN Handbook)\r\n                        NEPHROTIC SYNDROME (EdREN Handbook)', 'Proteinuria (EdREN Handbook)', 'Blood pressure and renal disease (EdREN Handbook)', 'Chronic renal failure (Merck)', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/FSGSLong2.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/fsgs.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/NephroticLong.html', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/nephsyn_adult.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', 'FSGS (EdREN)                                                         [In adults - see 11 for childre', 'FSGS (NKF)', 'Nephrotic syndrome (EdREN)', 'hrotic syndrome in adults, and in children (NKF)                                 Nephrotic syndrome', 'Chronic renal failure (EdREN)', ''), ('19', 'edtaCode', 'Glomerulonephritis', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', 'Hyptertension and renal disease', 'Chronic renal failure (Merck)', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/glom.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://kidney.niddk.nih.gov/kudiseases/pubs/glomerular/index.htm', '', '', 'Glomerulonephritis (NKF)', 'Blood pressure and the kidney (EdREN)', 'Chronic renal failure (EdREN)', 'Glomerular diseases (NIDDK)', '', ''), ('20', 'edtaCode', 'Reflux nephropathy', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('21', 'edtaCode', 'Obstruction - neurogenic bladder', '', '', '', '', '', '', '', '', '', '', 'sdsd', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Obstruction.html', '', '', '', '', '', 'Obstructive nephropathy (EdREN)', '', '', '', '', ''), ('22', 'edtaCode', 'Obstructive nephropathy - congenital', '', '', '', '', '', '', '0', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Obstruction.html', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', '', '', '', '', 'Obstructive nephropathy (EdREN)\r\n                       Obstructive nephropathy (EdREN)', 'Congenital kidney diseases (from NephKids)', '', '', '', ''), ('23', 'edtaCode', 'Obstructive nephropathy - acquired', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Obstruction.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Obstructive nephropathy (EdREN)                       Obstructive nephropathy (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('24', 'edtaCode', 'Reflux nephropathy', 'http://renux.dmed.ed.ac.uk/EdREN/Teachingbits/UTI.html#anchor585924', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', 'Reflux nephropathy (EdREN - Education)', 'Chronic renal failure (Merck)', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/reflux.html', 'http://www.kidney.org.uk/Medical-Info/reflux/index.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', 'Reflux nephropathy (EdREN)', 'Reflux nephropathy (NKF)', 'Chronic renal failure (EdREN)', '', '', ''), ('29', 'edtaCode', 'Reflux nephropathy (pyelonephritis)', 'http://renux.dmed.ed.ac.uk/EdREN/Teachingbits/UTI.html#anchor585924', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', 'Reflux nephropathy teaching information (EdREN)', 'Chronic renal failure (Merck)', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/reflux.html', 'http://www.kidney.org.uk/Medical-Info/reflux/index.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', 'Reflux nephropathy (EdREN)', 'Reflux nephropathy (NKF)', 'Chronic renal failure (EdREN)', '', '', ''), ('30', 'edtaCode', 'Interstitial nephritis', '', '', '', '', '', '', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', '', '', '', '', '', 'nterstitial nephritis (EdREN)', '', '', '', '', ''), ('31', 'edtaCode', 'Analgesic nephropathy', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Interstitial nephritis (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('32', 'edtaCode', 'Cis-platin nephropathy', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', '', '', '', '', 'Interstitial nephritis (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('33', 'edtaCode', 'Cyclosporin nephropathy', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Interstitial nephritis (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('34', 'edtaCode', 'Lead nephropathy', '', '', '', '', '', '', '', '', '', '', '', '', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Interstitial nephritis (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('39', 'edtaCode', 'Drug-induced interstitial nephropathy', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Interstitial nephritis (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('40', 'edtaCode', 'Cystic kidney disease (unspecified type)', 'http://renux.dmed.ed.ac.uk/EdREN/Teachingbits/nonPCKD.html', '', '', '', '', '', 'Diseases that may be confused with PKD (from EdREN)', '', '', '', '', '', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Kidney cysts (NKF)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('42', 'edtaCode', 'Polycystic kidney disease (infantile type)', '', '', '', '', '', '', '', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/arpkd.html', 'http://www.pkdcharity.org.uk/arpkd.html', '', '', '', '', 'Infantile PKD (NKF)', 'ARPKD (PKD Charity)', '', '', '', ''), ('43', 'edtaCode', 'Medullary cystic disease/nephronophthisis', '', '', '', '', '', '', 'info required', '', '', '', '', '', '', '', '', '', '', '', 'info required', '', '', '', '', ''), ('49', 'edtaCode', 'Cystic kidney disease (other specified type)', '', '', '', '', '', '', 'info required', '', '', '', '', '', '', '', '', '', '', '', 'info required', '', '', '', '', ''), ('50', 'edtaCode', 'Inherited nephropathy (unspecified type)', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', '', '', '', '', 'Congenital kidney diseases (from NephKids)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('51', 'edtaCode', 'Alport syndrome', '', '', '', '', '', '', 'See patient links', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/AlportLong.html', 'http://www.kidney.org.uk/Medical-Info/alports/index.html', '', '', '', '', 'Alport syndrome (EdREN)', 'Alport\'s syndrome (NKF)', '', '', '', ''), ('52', 'edtaCode', 'Cystinosis', 'http://www.ncbi.nlm.nih.gov/entrez/dispomim.cgi?id=219800', '', '', '', '', '', 'Cystinosis (OMIM) -  very long, not very up to date', '', '', '', '', '', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', '', '', '', '', '', 'Congenital kidney diseases (from NephKids)', '', '', '', '', ''), ('53', 'edtaCode', 'Primary oxalosis', '', '', '', '', '', '', '', '', '', '', '', '', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', 'http://ghr.nlm.nih.gov/condition=primaryhyperoxaluria', 'http://www.ohf.org/about_disease.html', '', '', '', 'Congenital kidney diseases (from NephKids)', 'Primary Hyperoxaluria (NIH)', 'Primary Hyperoxaluria (Oxalosis Foundation)', '', '', ''), ('54', 'edtaCode', 'Fabry disease', '', '', '', '', '', '', 'See patients links', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/fabry-disease/index.html', '', '', '', '', '', 'Fabry disease (NKF)', '', '', '', '', ''), ('59', 'edtaCode', 'Hereditary nephropathy (other specified type)', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('61', 'edtaCode', 'Oligomeganephronic dysplasia', '', '', '', '', '', '', '', '', '', '', '', '', 'http://cnserver0.nkf.med.ualberta.ca/nephkids/congdiseases.htm', '', '', '', '', '', 'Congenital kidney diseases (from NephKids)', '', '', '', '', ''), ('63', 'edtaCode', 'Congenital renal dysplasia', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/reflux.html', 'http://www.kidney.org.uk/Medical-Info/reflux/index.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', 'Single or small kidney (NKF)', 'Reflux nephropathy (EdREN)', 'Reflux nephropathy (NKF)', 'Chronic renal failure (EdREN)', '', ''), ('66', 'edtaCode', 'Prune belly syndrome', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Obstruction.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'Obstructive nephropathy (EdREN)                       Obstructive nephropathy (EdREN)', 'Chronic renal failure (EdREN)', '', '', '', ''), ('70', 'edtaCode', 'Renal artery stenosis (or renovascular disease - type unspecified\')', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/Ras.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/angioshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', 'Renal artery stenosis (NKF; requires review)', 'Renal arteriography and angioplasty (EdREN)', 'Chronic renal failure (EdREN)', '', '', ''), ('71', 'edtaCode', 'Nephropathy caused by malignant hypertension', '', '', '', '', '', '', '0', '', '', '', '', '', '', '', '', '', '', '', '(needed)', '', '', '', '', ''), ('72', 'edtaCode', 'Hypertensive nephropathy', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', 'http://www.merckmedicus.com/pp/us/hcp/frame_micromedex.jsp?pg=http%3A%2F%2Fmerck.micromedex.com%2Fin', '', '', '', '', 'Blood pressure and renal disease (EdREN Handbook)', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', '', '', '', '0', 'Blood pressure and kidney disease (EdREN)                         Blood pressure and kidney disease', 'Chronic renal failure (EdREN)', '', '', ''), ('73', 'edtaCode', 'Polyarteritis (vasculitis)', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKsystvasc.html', '', '', '', '', '', 'Vasculitis (EdREN Handbook)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/VasculitisLong.html', '', '', '', '', 'Vasculitis (NKF)', 'Vasculitis (EdREN)', '', '', '', ''), ('74', 'edtaCode', 'Wegener\'s granulomatosis', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKsystvasc.html', '', '', '', '', '', 'Vasculitis (EdREN Handbook)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/VasculitisLong.html', '', '', '', '', 'Vasculitis (NKF)', 'Vasculitis (EdREN)', '', '', '', ''), ('75', 'edtaCode', 'Cholesterol emboli/ischaemic nephropathy', '', '', '', '', '', '', '[links as for 89]', '', '', '', '', '', '', '', '', '', '', '', '[links as for 89]', '', '', '', '', ''), ('76', 'edtaCode', 'Glomerulonephritis related to liver cirrhosis', '', '', '', '', '', '', '[links as for 89]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('78', 'edtaCode', 'Cryoglobulinaemia', '', '', '', '', '', '', '[links as for 89]', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('79', 'edtaCode', 'Renal vascular disease (other cause)', '', '', '', '', '', '', '[links as for 89]', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/Ras.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/angioshort.html', '', '', '', '', 'Renal artery stenosis (NKF)', 'Renal arteriography and angioplasty (EdREN)', '', '', '', ''), ('PRE', 'treatment', 'General nephrology (not dialysis, not transplant)', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', '', 'Blood pressure in renal disease', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/BPshort.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diet_CRF.html', '', '', '', 'High blood pressure and kidney disease', 'Chronic kidney disease: preventing it from getting worse', 'Diet for people with kidney disease', '', '', ''), ('81', 'edtaCode', 'Diabetic nephropathy (II)', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02', '', '', '', '', '', 'Chronic renal failure (Merck)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/diabetes.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/Diabetic_nephLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', 'Diabetes and the kidney (NKF)', 'Diabetic kidney disease (EdREN)', 'Chronic renal failure (EdREN)', '', '', ''), ('83', 'edtaCode', 'Amyloidosis', '', '', '', '', '', '', 'See patient links', '', '', '', '', '', 'http://www.intelihealth.com/IH/ihtIH/WSIHW000/9339/9444.html', 'http://kidney.niddk.nih.gov/kudiseases/pubs/amyloidosis/', 'http://amyloidosis.org/whatisit.asp', 'http://www.information-on-amyloidosis.com/', '', '', 'Amyloidosis from InteliHealth (US; complicated)', 'NIDDK; Simpler but mentions Primary and Dialysis-related amyloid only', 'Amyloidosis support network (very comprehensive, but complicated)', 'From NCERx - again, very detailed, complicated', '', ''), ('84', 'edtaCode', 'SLE (Lupus nephritis)', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKsle.html', '', '', '', '', '', 'Treatment of SLE (EdREN Handbook)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/lupus.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/LupusLong.html', '', '', '', '', 'Lupus and lupus kidney disease (NKF)', 'Lupus nephritis (EdREN)', '', '', '', ''), ('85', 'edtaCode', 'Henoch-Schonlein purpura', '', '', '', '', '', '', 'Information source required', '', '', '', '', '', '', '', '', '', '', '', 'Info link required', '', '', '', '', ''), ('86', 'edtaCode', 'Goodpasture\'s disease', '', '', '', '', '', '', 'See patient info', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/GoodpastureLong.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/GoodpastureShort.html', '', '', '', '', 'Goodpasture\'s disease (long version) from EdREN', 'Goodpasture\'s disease (short version) from EdREN', '', '', '', ''), ('87', 'edtaCode', 'Scleroderma kidney', '', '', '', '', '', '', '(Links required)', '', '', '', '', '', '', '', '', '', '', '', '(Links required)', '', '', '', '', ''), ('88', 'edtaCode', 'HUS and TTP', '', '', '', '', '', '', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/HUS.Long.html', 'http://kidney.niddk.nih.gov/kudiseases/pubs/hemolyticuremic/index.htm', '', '', '', '', 'HUS and TTP (EdREN', 'HUS in childhood (NIDDK)', '', '', '', ''), ('89', 'edtaCode', 'Small vessel vasculitis (or other multisystem disease)', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKsystvasc.html', '', '', '', '', '', 'Systemic vasculitis (EdREN Handbook)', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/vasc.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/VasculitisLong.html', '', '', '', '', 'Vasculitis (NKF)', 'Vasculitis (EdREN)', '', '', '', ''), ('90', 'edtaCode', 'Cortical necrosis or irreversible acute tubular necrosis', '', '', '', '', '', '', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html#anchor544505', '', '', '', '', '', 'Acute tubular necrosis (EdREN)', '', '', '', '', ''), ('91', 'edtaCode', 'Tuberculosis', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '(Information required)', '', '', '', '', ''), ('92', 'edtaCode', 'Urate nephropathy', '', '', '', '', '', '', '', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html#anchor544505', '', '', '', '', '', 'Interstitial nephritis (EdREN)', '', '', '', '', ''), ('93', 'edtaCode', 'Nephrocalcinosis', '', '', '', '', '', '', '(Links required)', '', '', '', '', '', '', '', '', '', '', '', '(Links required)', '', '', '', '', ''), ('94', 'edtaCode', 'Balkan nephropathy', '', '', '', '', '', '', '(Links required)', '', '', '', '', '', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/InterstitialLong.html', '', '', '', '', '', 'Interstitial nephritis (EdREN)', '', '', '', '', ''), ('95', 'edtaCode', 'Kidney tumour', '', '', '', '', '', '', '', '', '', '', '', '', 'http://www.patient.co.uk/showdoc/27000676/', 'http://www.merckmedicus.com/pp/us/hcp/hcp_patient_resource_allhandouts_content_search.jsp?pg=/ppdocs', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html', '', '', '', 'Kidney cancer (Patient UK)', 'Kidney cancer (Merck)', 'Single or small kidney (NKF)\r\n', '', '', ''), ('96', 'edtaCode', 'Loss of kidney through operation or trauma', '', '', '', '', '', '', '', '', '', '', '', '', 'http://www.kidney.org.uk/Medical-Info/kidney-disease/small-singlekid.html', 'http://kidney.niddk.nih.gov/kudiseases/pubs/solitarykidney/index.htm', '', '', '', '', 'Single or small kidney (NKF)', 'Solitary kidney (NIDDK)', '', '', '', ''), ('99', 'edtaCode', 'Other kidney disease', 'http://www.renal.org/rixg/nodiagnosis.html', '', '', '', '', '', 'No diagnosis stated (RIXG)', '', '', '', '', '', 'http://www.renal.org/rixg/nodiagnosis.html', '', '', '', '', '', 'No diagnosis stated (RIXG)', '', '', '', '', ''), ('DEF', 'edtaCode', 'No diagnosis stated', 'http://www.renal.org/rixg/nodiagnosis.html', '', '', '', '', '', 'Why is this message showing?', '', '', '', '', '', 'http://www.renal.org/rixg/nodiagnosis.html', '', '', '', '', '', 'Why is this message showing?', '', '', '', '', ''), ('100', 'edtaCode', 'Chronic renal failure, cause uncertain', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', 'Chronic renal failure (Merck)', 'Blood pressure and renal disease (EdREN Handbook)', '', '', '', '', 'http://www.renal.org/rixg/nodiagnosis.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'No diagnosis stated (RIXG)', 'Chronic Renal Failure (EdREN)', '', '', '', ''), ('0', 'edtaCode', 'Chronic renal failure, cause uncertain', 'http://merck.micromedex.com/index.asp?page=bpm_brief&article_id=BPM01NP02&hilight=chronic|renal|fail', 'http://renux.dmed.ed.ac.uk/EdREN/Handbookbits/HDBKBP.html', '', '', '', '', 'Chronic renal failure (Merck)', 'Blood pressure and renal disease (EdREN Handbook)', '', '', '', '', 'http://www.renal.org/rixg/nodiagnosis.html', 'http://renux.dmed.ed.ac.uk/EdREN/EdRenINFObits/CRFLong.html', '', '', '', '', 'No diagnosis stated (RIXG)', 'Chronic Renal Failure (EdREN)', '', '', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `emailverification`
-- ----------------------------
DROP TABLE IF EXISTS `emailverification`;
CREATE TABLE `emailverification` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `verificationcode` varchar(50) NOT NULL,
  `expirydatestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `emailverification`
-- ----------------------------
BEGIN;
INSERT INTO `emailverification` VALUES ('1', 'p', 'p@p.com', 'InTigEdjeUBE9LmcKel8Xmd7CQqwYNeHS4o8XpjefSEu1hlx8g', '2012-04-18 15:59:14'), ('6', 'e', 'e@e.com', 'N4gLccAKwyrbLySXyCfaop2UweQoRhBPpzTIAeizZiiK1BuObM', '2012-05-23 16:08:32');
COMMIT;

-- ----------------------------
--  Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `datestamp` datetime NOT NULL,
  `comment` text NOT NULL,
  `commentedited` text,
  `anonymous` tinyint(1) NOT NULL,
  `makepublic` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `feedback`
-- ----------------------------
BEGIN;
INSERT INTO `feedback` VALUES ('1', 'p', 'P P ', '1234567890', 'A', '2012-04-05 21:52:25', 'fsff\r\n\r\nf\r\nf\r\n\r\nf', 'fsff\r\n\r\nf\r\nf\r\n\r\nf', '1', '0'), ('2', 'p', 'P P ', '1234567890', 'A', '2012-04-10 14:15:15', 'sdssfdsfd', 'sdssfdsfd', '1', '1'), ('3', 'p', 'P P ', '1234567890', 'A', '2012-04-10 14:23:19', 'lkjavnon', 'lkjavnon', '0', '1');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_api`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_api`;
CREATE TABLE `jforum_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(32) NOT NULL,
  `api_validity` datetime NOT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_attach`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach`;
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

-- ----------------------------
--  Table structure for `jforum_attach_desc`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach_desc`;
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

-- ----------------------------
--  Table structure for `jforum_attach_quota`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach_quota`;
CREATE TABLE `jforum_attach_quota` (
  `attach_quota_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `quota_limit_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_quota_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_banlist`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_banlist`;
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

-- ----------------------------
--  Table structure for `jforum_banner`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_banner`;
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

-- ----------------------------
--  Table structure for `jforum_bookmarks`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_bookmarks`;
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

-- ----------------------------
--  Table structure for `jforum_categories`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_categories`;
CREATE TABLE `jforum_categories` (
  `categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `display_order` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`categories_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_categories`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_categories` VALUES ('1', 'General', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_config`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_config`;
CREATE TABLE `jforum_config` (
  `config_name` varchar(255) NOT NULL DEFAULT '',
  `config_value` varchar(255) NOT NULL DEFAULT '',
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_config`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_config` VALUES ('most.users.ever.online', '1', '1'), ('most.users.ever.online.date', '1269881504628', '2');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_extension_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_extension_groups`;
CREATE TABLE `jforum_extension_groups` (
  `extension_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `allow` tinyint(1) DEFAULT '1',
  `upload_icon` varchar(100) DEFAULT NULL,
  `download_mode` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_extensions`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_extensions`;
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

-- ----------------------------
--  Table structure for `jforum_forums`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_forums`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_forums`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_forums` VALUES ('2', '1', 'General', 'A general forum.', '1', '2', '3', '0');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_forums_watch`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_forums_watch`;
CREATE TABLE `jforum_forums_watch` (
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_fw_forum` (`forum_id`),
  KEY `idx_fw_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_groups`;
CREATE TABLE `jforum_groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(40) NOT NULL DEFAULT '',
  `group_description` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_groups`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_groups` VALUES ('1', 'General', 'General Users', '0'), ('2', 'Administration', 'Admin Users', '0');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_karma`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_karma`;
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

-- ----------------------------
--  Table structure for `jforum_mail_integration`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_mail_integration`;
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

-- ----------------------------
--  Table structure for `jforum_moderation_log`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_moderation_log`;
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

-- ----------------------------
--  Table structure for `jforum_posts`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_posts`;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_posts`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_posts` VALUES ('2', '2', '2', '4', '2010-03-29 21:52:02', '0:0:0:0:0:0:0:1', '1', '0', '1', '1', '2010-03-29 21:52:02', '0', '1', '0', '0'), ('3', '3', '2', '5', '2010-04-26 13:42:46', '0:0:0:0:0:0:0:1', '1', '0', '1', '1', '2010-04-26 13:42:46', '0', '1', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_posts_text`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_posts_text`;
CREATE TABLE `jforum_posts_text` (
  `post_id` int(11) NOT NULL,
  `post_text` text,
  `post_subject` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_posts_text`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_posts_text` VALUES ('2', 'Test\r\n\r\nst\r\nsfsf', 'Test'), ('3', 'ndhhfn', 'fhfnndhfn');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_privmsgs`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_privmsgs`;
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

-- ----------------------------
--  Table structure for `jforum_privmsgs_text`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_privmsgs_text`;
CREATE TABLE `jforum_privmsgs_text` (
  `privmsgs_id` int(11) NOT NULL,
  `privmsgs_text` text,
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_quota_limit`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_quota_limit`;
CREATE TABLE `jforum_quota_limit` (
  `quota_limit_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_desc` varchar(50) NOT NULL,
  `quota_limit` int(11) NOT NULL,
  `quota_type` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`quota_limit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_ranks`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_ranks`;
CREATE TABLE `jforum_ranks` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_title` varchar(50) NOT NULL DEFAULT '',
  `rank_min` int(11) NOT NULL DEFAULT '0',
  `rank_special` tinyint(1) DEFAULT NULL,
  `rank_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_role_values`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_role_values`;
CREATE TABLE `jforum_role_values` (
  `role_id` int(11) NOT NULL,
  `role_value` varchar(255) DEFAULT NULL,
  KEY `idx_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_role_values`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_role_values` VALUES ('28', '1'), ('29', '1'), ('25', '2'), ('24', '2'), ('27', '2'), ('3', '2'), ('31', '2'), ('30', '2'), ('37', '2'), ('36', '2'), ('33', '2'), ('32', '2');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_roles`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_roles`;
CREATE TABLE `jforum_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_group` (`group_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_roles`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_roles` VALUES ('1', '1', 'perm_vote'), ('2', '1', 'perm_karma_enabled'), ('3', '1', 'perm_anonymous_post'), ('4', '1', 'perm_create_poll'), ('5', '1', 'perm_bookmarks_enabled'), ('6', '1', 'perm_attachments_download'), ('7', '1', 'perm_create_sticky_announcement_topics'), ('8', '1', 'perm_moderation_log'), ('9', '2', 'perm_administration'), ('10', '2', 'perm_moderation'), ('11', '2', 'perm_moderation_post_remove'), ('12', '2', 'perm_moderation_post_edit'), ('13', '2', 'perm_moderation_topic_move'), ('14', '2', 'perm_moderation_topic_lockUnlock'), ('15', '2', 'perm_moderation_approve_messages'), ('16', '2', 'perm_create_sticky_announcement_topics'), ('17', '2', 'perm_vote'), ('18', '2', 'perm_create_poll'), ('19', '2', 'perm_karma_enabled'), ('20', '2', 'perm_bookmarks_enabled'), ('21', '2', 'perm_attachments_download'), ('22', '2', 'perm_moderation_log'), ('23', '2', 'perm_full_moderation_log'), ('24', '1', 'perm_forum'), ('25', '2', 'perm_forum'), ('26', '1', 'perm_anonymous_post'), ('27', '2', 'perm_anonymous_post'), ('28', '1', 'perm_category'), ('29', '2', 'perm_category'), ('30', '1', 'perm_read_only_forums'), ('31', '2', 'perm_read_only_forums'), ('32', '1', 'perm_html_disabled'), ('33', '2', 'perm_html_disabled'), ('34', '1', 'perm_attachments_enabled'), ('35', '2', 'perm_attachments_enabled'), ('36', '1', 'perm_reply_only'), ('37', '2', 'perm_reply_only'), ('38', '1', 'perm_reply_without_moderation'), ('39', '2', 'perm_reply_without_moderation'), ('40', '2', 'perm_moderation_forums');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_sessions`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_sessions`;
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

-- ----------------------------
--  Records of `jforum_sessions`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_sessions` VALUES ('16AD07DE44DEBF7D0B160AABFFFDD71B', '4', '2010-03-29 21:40:03', '0', '', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `jforum_smilies`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_smilies`;
CREATE TABLE `jforum_smilies` (
  `smilie_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(100) DEFAULT NULL,
  `disk_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`smilie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_smilies`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_smilies` VALUES ('1', ':)', '<img src=\"#CONTEXT#/images/smilies/3b63d1616c5dfcf29f8a7a031aaa7cad.gif\" />', '3b63d1616c5dfcf29f8a7a031aaa7cad.gif'), ('2', ':-)', '<img src=\"#CONTEXT#/images/smilies/3b63d1616c5dfcf29f8a7a031aaa7cad.gif\"/>', '3b63d1616c5dfcf29f8a7a031aaa7cad.gif'), ('3', ':D', '<img src=\"#CONTEXT#/images/smilies/283a16da79f3aa23fe1025c96295f04f.gif\" />', '283a16da79f3aa23fe1025c96295f04f.gif'), ('4', ':-D', '<img src=\"#CONTEXT#/images/smilies/283a16da79f3aa23fe1025c96295f04f.gif\" />', '283a16da79f3aa23fe1025c96295f04f.gif'), ('5', ':(', '<img src=\"#CONTEXT#/images/smilies/9d71f0541cff0a302a0309c5079e8dee.gif\" />', '9d71f0541cff0a302a0309c5079e8dee.gif'), ('6', ':mrgreen:', '<img src=\"#CONTEXT#/images/smilies/ed515dbff23a0ee3241dcc0a601c9ed6.gif\" />', 'ed515dbff23a0ee3241dcc0a601c9ed6.gif'), ('7', ':-o', '<img src=\"#CONTEXT#/images/smilies/47941865eb7bbc2a777305b46cc059a2.gif\"  />', '47941865eb7bbc2a777305b46cc059a2.gif'), ('8', ':shock:', '<img src=\"#CONTEXT#/images/smilies/385970365b8ed7503b4294502a458efa.gif\" />', '385970365b8ed7503b4294502a458efa.gif'), ('9', ':?:', '<img src=\"#CONTEXT#/images/smilies/0a4d7238daa496a758252d0a2b1a1384.gif\" />', '0a4d7238daa496a758252d0a2b1a1384.gif'), ('10', '8)', '<img src=\"#CONTEXT#/images/smilies/b2eb59423fbf5fa39342041237025880.gif\"  />', 'b2eb59423fbf5fa39342041237025880.gif'), ('11', ':lol:', '<img src=\"#CONTEXT#/images/smilies/97ada74b88049a6d50a6ed40898a03d7.gif\" />', '97ada74b88049a6d50a6ed40898a03d7.gif'), ('12', ':x', '<img src=\"#CONTEXT#/images/smilies/1069449046bcd664c21db15b1dfedaee.gif\"  />', '1069449046bcd664c21db15b1dfedaee.gif'), ('13', ':P', '<img src=\"#CONTEXT#/images/smilies/69934afc394145350659cd7add244ca9.gif\" />', '69934afc394145350659cd7add244ca9.gif'), ('14', ':-P', '<img src=\"#CONTEXT#/images/smilies/69934afc394145350659cd7add244ca9.gif\" />', '69934afc394145350659cd7add244ca9.gif'), ('15', ':oops:', '<img src=\"#CONTEXT#/images/smilies/499fd50bc713bfcdf2ab5a23c00c2d62.gif\" />', '499fd50bc713bfcdf2ab5a23c00c2d62.gif'), ('16', ':cry:', '<img src=\"#CONTEXT#/images/smilies/c30b4198e0907b23b8246bdd52aa1c3c.gif\" />', 'c30b4198e0907b23b8246bdd52aa1c3c.gif'), ('17', ':evil:', '<img src=\"#CONTEXT#/images/smilies/2e207fad049d4d292f60607f80f05768.gif\" />', '2e207fad049d4d292f60607f80f05768.gif'), ('18', ':twisted:', '<img src=\"#CONTEXT#/images/smilies/908627bbe5e9f6a080977db8c365caff.gif\" />', '908627bbe5e9f6a080977db8c365caff.gif'), ('19', ':roll:', '<img src=\"#CONTEXT#/images/smilies/2786c5c8e1a8be796fb2f726cca5a0fe.gif\" />', '2786c5c8e1a8be796fb2f726cca5a0fe.gif'), ('20', ':wink:', '<img src=\"#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif\" />', '8a80c6485cd926be453217d59a84a888.gif'), ('21', ';)', '<img src=\"#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif\" />', '8a80c6485cd926be453217d59a84a888.gif'), ('22', ';-)', '<img src=\"#CONTEXT#/images/smilies/8a80c6485cd926be453217d59a84a888.gif\" />', '8a80c6485cd926be453217d59a84a888.gif'), ('23', ':!:', '<img src=\"#CONTEXT#/images/smilies/9293feeb0183c67ea1ea8c52f0dbaf8c.gif\" />', '9293feeb0183c67ea1ea8c52f0dbaf8c.gif'), ('24', ':?', '<img src=\"#CONTEXT#/images/smilies/136dd33cba83140c7ce38db096d05aed.gif\" />', '136dd33cba83140c7ce38db096d05aed.gif'), ('25', ':idea:', '<img src=\"#CONTEXT#/images/smilies/8f7fb9dd46fb8ef86f81154a4feaada9.gif\" />', '8f7fb9dd46fb8ef86f81154a4feaada9.gif'), ('26', ':arrow:', '<img src=\"#CONTEXT#/images/smilies/d6741711aa045b812616853b5507fd2a.gif\" />', 'd6741711aa045b812616853b5507fd2a.gif'), ('27', ':hunf:', '<img src=\"#CONTEXT#/images/smilies/0320a00cb4bb5629ab9fc2bc1fcc4e9e.gif\" />', '0320a00cb4bb5629ab9fc2bc1fcc4e9e.gif'), ('28', ':-(', '<img src=\"#CONTEXT#/images/smilies/9d71f0541cff0a302a0309c5079e8dee.gif\"  />', '9d71f0541cff0a302a0309c5079e8dee.gif'), ('29', ':XD:', '<img src=\"#CONTEXT#/images/smilies/49869fe8223507d7223db3451e5321aa.gif\" />', '49869fe8223507d7223db3451e5321aa.gif'), ('30', ':thumbup:', '<img src=\"#CONTEXT#/images/smilies/e8a506dc4ad763aca51bec4ca7dc8560.gif\" />', 'e8a506dc4ad763aca51bec4ca7dc8560.gif'), ('31', ':thumbdown:', '<img src=\"#CONTEXT#/images/smilies/e78feac27fa924c4d0ad6cf5819f3554.gif\" />', 'e78feac27fa924c4d0ad6cf5819f3554.gif'), ('32', ':|', '<img src=\"#CONTEXT#/images/smilies/1cfd6e2a9a2c0cf8e74b49b35e2e46c7.gif\" />', '1cfd6e2a9a2c0cf8e74b49b35e2e46c7.gif');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_themes`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_themes`;
CREATE TABLE `jforum_themes` (
  `themes_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(30) NOT NULL DEFAULT '',
  `style_name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_topics`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_topics`;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_topics`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_topics` VALUES ('2', '2', 'Test', '4', '2010-03-29 21:52:02', '3', '0', '0', '0', '0', '2', '2', '0', '0'), ('3', '2', 'fhfnndhfn', '5', '2010-04-26 13:42:46', '3', '0', '0', '0', '0', '3', '3', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_topics_watch`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_topics_watch`;
CREATE TABLE `jforum_topics_watch` (
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(1) DEFAULT '1',
  KEY `idx_topic` (`topic_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_topics_watch`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_topics_watch` VALUES ('2', '4', '1'), ('3', '5', '1');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_user_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_user_groups`;
CREATE TABLE `jforum_user_groups` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_group` (`group_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_user_groups`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_user_groups` VALUES ('1', '1'), ('2', '2'), ('1', '3'), ('2', '4'), ('1', '5'), ('1', '6');
COMMIT;

-- ----------------------------
--  Table structure for `jforum_users`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_users`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `jforum_users`
-- ----------------------------
BEGIN;
INSERT INTO `jforum_users` VALUES ('1', null, 'Anonymous', 'nopass', '0', '0', null, '2010-03-29 17:48:19', null, '0', '', null, '', '%d/%M/%Y %H:%i', '0', '0', null, null, '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '0', null, '0', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null), ('2', null, 'Admin', '21232f297a57a5a743894a0e4a801fc3', '0', '0', null, '2010-03-29 17:48:19', null, '1', '', null, '', '%d/%M/%Y %H:%i', '0', '0', null, null, '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '0', null, '0', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null), ('4', null, 's', 'sso', '0', '0', '2010-03-29 17:52:48', '2010-03-29 17:52:48', null, '1', '', null, '', '%d/%M/%Y %H:%i', '0', '0', null, null, '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '0', null, '0', '', null, null, 'ss sssss', null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null), ('5', null, 'p', 'sso', '0', '0', '2010-04-26 13:42:24', '2010-04-26 13:42:24', null, '1', '', null, '', '%d/%M/%Y %H:%i', '0', '0', null, null, '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '0', null, '0', '', null, null, 'P PPPP', null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null), ('6', null, 'q', 'sso', '0', '0', '2012-05-31 16:52:45', '2012-05-31 16:52:45', null, '0', '', null, '', '%d/%M/%Y %H:%i', '0', '0', null, null, '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '0', null, '0', 'q@robworth.com', null, null, 'qq', null, null, null, null, null, null, null, null, null, null, '0', null, '1', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `jforum_vote_desc`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_desc`;
CREATE TABLE `jforum_vote_desc` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `vote_text` varchar(255) NOT NULL DEFAULT '',
  `vote_start` datetime NOT NULL,
  `vote_length` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `topic_id` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_vote_results`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_results`;
CREATE TABLE `jforum_vote_results` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_option_id` tinyint(4) NOT NULL DEFAULT '0',
  `vote_option_text` varchar(255) NOT NULL DEFAULT '',
  `vote_result` int(11) NOT NULL DEFAULT '0',
  KEY `vote_id` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_vote_voters`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_voters`;
CREATE TABLE `jforum_vote_voters` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_ip` varchar(15) NOT NULL DEFAULT '',
  KEY `vote_id` (`vote_id`),
  KEY `vote_user_id` (`vote_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_words`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_words`;
CREATE TABLE `jforum_words` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL DEFAULT '',
  `replacement` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `letter`
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `type` varchar(100) DEFAULT '',
  `content` text,
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `letter`
-- ----------------------------
BEGIN;
INSERT INTO `letter` VALUES ('1', '1234567890', 'A', '2012-01-24 11:24:06', 'Sample Letter', '\nSample letter\n\nsdfdf\n\ndfdf\n\ndfdf\n\n'), ('2', '1234567890', 'A', '2012-01-18 10:38:30', 'Clinic Letter', '\nAnother letter\n\n\n\n'), ('3', '1234567890', 'B', '2012-01-22 11:42:43', 'Nice Letter', 'Nice letter content'), ('4', '9876543210', 'B', '2012-03-30 12:32:25', 'Not this letter', 'Not this really not this');
COMMIT;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `actor` varchar(100) NOT NULL DEFAULT '',
  `action` varchar(100) NOT NULL DEFAULT '',
  `nhsno` varchar(100) DEFAULT '',
  `user` varchar(100) DEFAULT '',
  `unitcode` varchar(100) DEFAULT '',
  `extrainfo` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `log`
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES ('1', '2012-04-04 17:28:24', 's', 'logon', '', 's', '', ''), ('2', '2012-04-04 17:29:36', 's', 'logon', '', 's', '', ''), ('432', '2012-07-09 15:50:10', 'p', 'patient view', '1234567890', '', 'A', ''), ('4', '2012-04-04 17:32:45', 'p', 'logon', '1234567890', 'p', 'A', ''), ('5', '2012-04-04 17:33:07', 's', 'logon', '', 's', '', ''), ('6', '2012-04-04 17:33:29', 's', 'patient add', '1234567890', 'p', 'B', ''), ('7', '2012-04-04 17:35:28', 'p', 'logon', '1234567890', 'p', 'A', ''), ('8', '2012-04-04 17:36:12', 's', 'logon', '', 's', '', ''), ('9', '2012-04-04 18:36:25', 's', 'logon', '', 's', '', ''), ('10', '2012-04-04 18:44:05', 's', 'logon', '', 's', '', ''), ('11', '2012-04-04 18:44:26', 's', 'admin add', '', 'a', 'A', ''), ('12', '2012-04-04 18:45:40', 'a', 'logon', '', 'a', 'A', ''), ('13', '2012-04-04 18:45:46', 'a', 'password change', '', 'a', 'A', ''), ('14', '2012-04-04 18:45:55', 'a', 'logon', '', 'a', 'A', ''), ('15', '2012-04-04 19:07:10', 's', 'logon', '', 's', '', ''), ('16', '2012-04-04 19:10:02', 's', 'logon', '', 's', '', ''), ('17', '2012-04-04 19:10:18', 's', 'admin add', '', 'b', 'B', ''), ('18', '2012-04-04 19:12:11', 's', 'logon', '', 's', '', ''), ('19', '2012-04-04 19:16:28', 's', 'logon', '', 's', '', ''), ('20', '2012-04-04 19:57:56', 's', 'logon', '', 's', '', ''), ('21', '2012-04-04 19:58:47', 's', 'logon', '', 's', '', ''), ('22', '2012-04-04 19:59:15', 's', 'admin add', '', 'b', 'A', ''), ('23', '2012-04-04 20:13:03', 's', 'logon', '', 's', '', ''), ('24', '2012-04-04 20:13:42', 's', 'admin add', '', 'b', 'A', ''), ('25', '2012-04-04 20:16:32', 's', 'logon', '', 's', '', ''), ('26', '2012-04-04 20:17:06', 's', 'admin add', '', 'b', 'A', ''), ('27', '2012-04-04 20:17:45', 's', 'admin add', '', 'c', 'A', ''), ('28', '2012-04-04 20:19:12', 's', 'admin add', '', 'c', 'B', ''), ('29', '2012-04-04 20:27:05', 'system', 'patient data fail', '1234567890', '', 'A', 'A_00794_1234567891.gpg copy.xml : java.lang.NumberFormatException: For input string: \"201-\"\n	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)\n	at java.lang.Integer.parseInt(Integer.java:458)\n	at java.lang.Integer.parseInt(Integer.java:499)\n	at com.worthsoln.patientview.utils.TimestampUtils.createIsoDatestamp(TimestampUtils.java:43)\n	at com.worthsoln.patientview.utils.TimestampUtils.createTimestamp(TimestampUtils.java:11)\n	at com.worthsoln.patientview.ResultsUpdater.deleteDateRanges(ResultsUpdater.java:91)\n	at com.worthsoln.patientview.ResultsUpdater.updatePatientData(ResultsUpdater.java:64)\n	at com.worthsoln.patientview.ResultsUpdater.update(ResultsUpdater.java:42)\n	at com.worthsoln.patientview.parser.XmlParserUtils.updateXmlData(XmlParserUtils.java:12)\n	at com.worthsoln.patientview.parser.XmlParserThread.updateXmlFiles(XmlParserThread.java:50)\n	at com.worthsoln.patientview.parser.XmlParserThread.run(XmlParserThread.java:37)\n	at java.lang.Thread.run(Thread.java:680)\n'), ('30', '2012-04-04 20:29:05', 'system', 'patient data load', '1234567890', '', 'A', 'A_00794_1234567890.gpg copy.xml'), ('31', '2012-04-04 20:30:16', 'p', 'logon', '1234567890', 'p', 'A', ''), ('32', '2012-04-04 20:30:17', 'p', 'patient view', '1234567890', '', 'A', ''), ('33', '2012-04-04 20:30:21', 'p', 'patient view', '1234567890', '', 'A', ''), ('34', '2012-04-04 20:31:18', 'p', 'patient view', '1234567890', '', 'A', ''), ('35', '2012-04-05 15:01:44', 's', 'logon', '', 's', '', ''), ('36', '2012-04-05 15:05:04', 's', 'logon', '', 's', '', ''), ('37', '2012-04-05 15:08:42', 'a', 'logon', '', 'a', 'A', ''), ('38', '2012-04-05 15:37:49', 's', 'logon', '', 's', '', ''), ('39', '2012-04-05 15:38:02', 'a', 'logon', '', 'a', 'A', ''), ('40', '2012-04-05 15:38:39', 'a', 'logon', '', 'a', 'A', ''), ('41', '2012-04-05 15:40:00', 'a', 'logon', '', 'a', 'A', ''), ('42', '2012-04-05 15:40:53', 'a', 'logon', '', 'a', 'A', ''), ('43', '2012-04-05 15:41:57', 'a', 'logon', '', 'a', 'A', ''), ('44', '2012-04-05 15:42:06', 'a', 'password reset', '', 'c', 'A', ''), ('45', '2012-04-05 15:42:17', 'c', 'logon', '', 'c', 'A', ''), ('46', '2012-04-05 15:42:24', 'c', 'password change', '', 'c', 'A', ''), ('47', '2012-04-05 15:42:33', 'c', 'logon', '', 'c', 'A', ''), ('48', '2012-04-05 15:42:58', 's', 'logon', '', 's', '', ''), ('49', '2012-04-05 16:34:11', 's', 'logon', '', 's', '', ''), ('50', '2012-04-05 16:34:23', 'a', 'logon', '', 'a', 'A', ''), ('51', '2012-04-05 16:38:10', 'a', 'logon', '', 'a', 'A', ''), ('52', '2012-04-05 16:44:04', 'a', 'logon', '', 'a', 'A', ''), ('53', '2012-04-05 18:15:39', 'a', 'logon', '', 'a', 'A', ''), ('54', '2012-04-05 18:20:10', 'a', 'logon', '', 'a', 'A', ''), ('55', '2012-04-05 18:25:28', 'a', 'logon', '', 'a', 'A', ''), ('56', '2012-04-05 18:27:42', 'a', 'logon', '', 'a', 'A', ''), ('57', '2012-04-05 19:17:08', 'a', 'logon', '', 'a', 'A', ''), ('58', '2012-04-05 19:20:01', 'a', 'logon', '', 'a', 'A', ''), ('59', '2012-04-05 19:20:06', 'a', 'patient view', '1234567890', '', 'A', ''), ('60', '2012-04-05 19:20:15', 'a', 'patient view', '1234567890', '', 'A', ''), ('61', '2012-04-05 19:20:15', 'a', 'patient view', '1234567890', '', 'A', ''), ('62', '2012-04-05 19:20:41', 's', 'logon', '', 's', '', ''), ('63', '2012-04-05 19:23:07', 'a', 'logon', '', 'a', 'A', ''), ('64', '2012-04-05 19:23:29', 'c', 'logon', '', 'c', 'A', ''), ('65', '2012-04-05 19:24:23', 'c', 'logon', '', 'c', 'A', ''), ('66', '2012-04-05 19:24:34', 'c', 'password reset', '', 'b', 'B', ''), ('67', '2012-04-05 19:24:44', 'b', 'logon', '', 'b', 'B', ''), ('68', '2012-04-05 19:24:50', 'b', 'password change', '', 'b', 'B', ''), ('69', '2012-04-05 19:25:05', 'c', 'logon', '', 'c', 'A', ''), ('70', '2012-04-05 19:25:45', 'p', 'logon', '1234567890', 'p', 'A', ''), ('71', '2012-04-05 19:27:25', 'p', 'logon', '1234567890', 'p', 'A', ''), ('72', '2012-04-05 19:30:46', 'p', 'logon', '1234567890', 'p', 'A', ''), ('73', '2012-04-05 20:25:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('74', '2012-04-05 20:27:55', 'p', 'logon', '1234567890', 'p', 'A', ''), ('75', '2012-04-05 20:29:16', 'p', 'logon', '1234567890', 'p', 'A', ''), ('76', '2012-04-05 20:34:49', 'p', 'logon', '1234567890', 'p', 'A', ''), ('77', '2012-04-05 20:37:29', 'p', 'logon', '1234567890', 'p', 'A', ''), ('78', '2012-04-05 20:43:31', 'p', 'logon', '1234567890', 'p', 'A', ''), ('79', '2012-04-05 20:46:11', 'p', 'logon', '1234567890', 'p', 'A', ''), ('80', '2012-04-05 21:08:50', 'p', 'logon', '1234567890', 'p', 'A', ''), ('81', '2012-04-05 21:20:00', 'p', 'logon', '1234567890', 'p', 'A', ''), ('82', '2012-04-05 21:21:37', 'p', 'logon', '1234567890', 'p', 'A', ''), ('83', '2012-04-05 21:52:18', 'p', 'logon', '1234567890', 'p', 'A', ''), ('84', '2012-04-05 21:52:40', 'a', 'logon', '', 'a', 'A', ''), ('85', '2012-04-10 14:14:46', 'p', 'logon', '1234567890', 'p', 'A', ''), ('86', '2012-04-10 14:15:51', 'a', 'logon', '', 'a', 'A', ''), ('87', '2012-04-10 14:16:26', 'p', 'logon', '1234567890', 'p', 'A', ''), ('88', '2012-04-10 14:22:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('89', '2012-04-10 14:23:40', 'a', 'logon', '', 'a', 'A', ''), ('90', '2012-04-10 14:24:04', 'p', 'logon', '1234567890', 'p', 'A', ''), ('91', '2012-04-11 10:41:41', 's', 'logon', '', 's', '', ''), ('92', '2012-04-11 10:44:00', 's', 'logon', '', 's', '', ''), ('93', '2012-04-11 10:45:46', 's', 'password change', '', 's', '', ''), ('94', '2012-04-11 10:45:56', 's', 'logon', '', 's', '', ''), ('95', '2012-04-11 10:46:07', 's', 'password change', '', 's', '', ''), ('96', '2012-04-11 10:46:17', 's', 'logon', '', 's', '', ''), ('97', '2012-04-11 10:46:39', 's', 'logon', '', 's', '', ''), ('98', '2012-04-11 10:50:39', 's', 'logon', '', 's', '', ''), ('99', '2012-04-11 10:57:18', 'a', 'logon', '', 'a', 'A', ''), ('100', '2012-04-11 11:39:50', 'a', 'logon', '', 'a', 'A', ''), ('101', '2012-04-11 11:40:08', 's', 'logon', '', 's', '', ''), ('102', '2012-04-11 11:40:23', 'a', 'logon', '', 'a', 'A', ''), ('103', '2012-04-11 11:54:59', 'a', 'logon', '', 'a', 'A', ''), ('104', '2012-04-11 11:58:13', 'a', 'logon', '', 'a', 'A', ''), ('105', '2012-04-11 11:58:24', 'c', 'logon', '', 'c', 'A', ''), ('106', '2012-04-11 11:59:43', 'a', 'logon', '', 'a', 'A', ''), ('107', '2012-04-11 14:26:19', 'a', 'logon', '', 'a', 'A', ''), ('108', '2012-04-11 16:15:19', 's', 'logon', '', 's', '', ''), ('109', '2012-04-11 16:17:04', 'system', 'patient data fail', '1234567890', '', 'A', 'A_00794_1234567890.gpg copy.xml : java.lang.NullPointerException\n	at com.worthsoln.patientview.parser.ResultParser.collectTopLevelData(ResultParser.java:196)\n	at com.worthsoln.patientview.parser.ResultParser.parseResults(ResultParser.java:41)\n	at com.worthsoln.patientview.ResultsUpdater.update(ResultsUpdater.java:34)\n	at com.worthsoln.patientview.parser.XmlParserUtils.updateXmlData(XmlParserUtils.java:12)\n	at com.worthsoln.patientview.parser.XmlParserThread.updateXmlFiles(XmlParserThread.java:50)\n	at com.worthsoln.patientview.parser.XmlParserThread.run(XmlParserThread.java:37)\n	at java.lang.Thread.run(Thread.java:680)\n'), ('110', '2012-04-11 16:19:11', 'system', 'patient data fail', '1234567890', '', 'A', 'A_00794_1234567890.gpg copy.xml : java.lang.NullPointerException\n	at com.worthsoln.patientview.parser.ResultParser.collectTopLevelData(ResultParser.java:196)\n	at com.worthsoln.patientview.parser.ResultParser.parseResults(ResultParser.java:41)\n	at com.worthsoln.patientview.ResultsUpdater.update(ResultsUpdater.java:34)\n	at com.worthsoln.patientview.parser.XmlParserUtils.updateXmlData(XmlParserUtils.java:12)\n	at com.worthsoln.patientview.parser.XmlParserThread.updateXmlFiles(XmlParserThread.java:50)\n	at com.worthsoln.patientview.parser.XmlParserThread.run(XmlParserThread.java:37)\n	at java.lang.Thread.run(Thread.java:680)\n'), ('111', '2012-04-11 16:21:09', 'a', 'logon', '', 'a', 'A', ''), ('112', '2012-04-11 16:23:12', 'system', 'patient data fail', '1234567890', '', 'A', 'A_00794_1234567890.gpg copy.xml : java.lang.NullPointerException\n	at com.worthsoln.patientview.parser.ResultParser.collectTopLevelData(ResultParser.java:196)\n	at com.worthsoln.patientview.parser.ResultParser.parseResults(ResultParser.java:41)\n	at com.worthsoln.patientview.ResultsUpdater.update(ResultsUpdater.java:34)\n	at com.worthsoln.patientview.parser.XmlParserUtils.updateXmlData(XmlParserUtils.java:12)\n	at com.worthsoln.patientview.parser.XmlParserThread.updateXmlFiles(XmlParserThread.java:50)\n	at com.worthsoln.patientview.parser.XmlParserThread.run(XmlParserThread.java:37)\n	at java.lang.Thread.run(Thread.java:680)\n'), ('113', '2012-04-11 16:25:12', 'system', 'patient data fail', '1234567890', '', 'A', 'A_00794_1234567890.gpg copy.xml : java.lang.NullPointerException\n	at com.worthsoln.patientview.parser.ResultParser.collectTopLevelData(ResultParser.java:196)\n	at com.worthsoln.patientview.parser.ResultParser.parseResults(ResultParser.java:41)\n	at com.worthsoln.patientview.ResultsUpdater.update(ResultsUpdater.java:34)\n	at com.worthsoln.patientview.parser.XmlParserUtils.updateXmlData(XmlParserUtils.java:12)\n	at com.worthsoln.patientview.parser.XmlParserThread.updateXmlFiles(XmlParserThread.java:50)\n	at com.worthsoln.patientview.parser.XmlParserThread.run(XmlParserThread.java:37)\n	at java.lang.Thread.run(Thread.java:680)\n'), ('114', '2012-04-11 17:53:40', 'a', 'logon', '', 'a', 'A', ''), ('115', '2012-04-11 17:55:22', 'a', 'logon', '', 'a', 'A', ''), ('116', '2012-04-11 17:56:03', 'a', 'logon', '', 'a', 'A', ''), ('117', '2012-04-11 17:57:01', 'a', 'logon', '', 'a', 'A', ''), ('118', '2012-04-11 18:00:23', 'a', 'logon', '', 'a', 'A', ''), ('119', '2012-04-11 18:19:51', 'a', 'logon', '', 'a', 'A', ''), ('120', '2012-04-11 19:20:34', 'a', 'logon', '', 'a', 'A', ''), ('121', '2012-04-17 15:23:27', 'a', 'logon', '', 'a', 'A', ''), ('122', '2012-04-17 15:28:28', 'a', 'logon', '', 'a', 'A', ''), ('123', '2012-04-17 15:34:33', 'a', 'logon', '', 'a', 'A', ''), ('124', '2012-04-17 15:38:25', 'a', 'logon', '', 'a', 'A', ''), ('125', '2012-04-17 15:54:01', 'system', 'patient data load', '1234567890', '', 'A', 'A_00794_1234567891.gpg copy.xml'), ('126', '2012-04-17 15:54:07', 'a', 'logon', '', 'a', 'A', ''), ('127', '2012-04-17 16:01:43', 'system', 'patient data load', '1234567890', '', 'A', 'A_00794_1234567891.gpg copy.xml'), ('128', '2012-04-17 16:01:55', 'a', 'logon', '', 'a', 'A', ''), ('129', '2012-04-23 20:02:09', 's', 'logon', '', 's', '', ''), ('130', '2012-05-08 17:15:14', 'a', 'logon', '', 'a', 'A', ''), ('131', '2012-05-08 17:15:32', 'a', 'admin add', '', 'm', '', ''), ('132', '2012-05-08 17:19:14', 'a', 'logon', '', 'a', 'A', ''), ('133', '2012-05-08 17:19:44', 'a', 'admin add', '', 'm', '', ''), ('134', '2012-05-08 17:23:39', 'a', 'logon', '', 'a', 'A', ''), ('135', '2012-05-08 17:23:52', 'a', 'patient view', '1234567890', '', 'A', ''), ('136', '2012-05-08 17:32:49', 'a', 'logon', '', 'a', 'A', ''), ('137', '2012-05-08 17:35:52', 'a', 'patient add', '0987654312', 'm', 'A', ''), ('138', '2012-05-08 17:36:26', 'm', 'logon', '0987654312', 'm', 'A', ''), ('139', '2012-05-08 17:36:32', 'm', 'password change', '', 'm', 'A', ''), ('140', '2012-05-08 17:36:38', 'a', 'logon', '', 'a', 'A', ''), ('141', '2012-05-08 18:09:44', 'a', 'logon', '', 'a', 'A', ''), ('142', '2012-05-08 18:11:12', 'a', 'logon', '', 'a', 'A', ''), ('143', '2012-05-08 18:14:25', 'a', 'logon', '', 'a', 'A', ''), ('144', '2012-05-08 18:21:54', 'a', 'logon', '', 'a', 'A', ''), ('145', '2012-05-08 18:24:32', 'a', 'logon', '', 'a', 'A', ''), ('146', '2012-05-08 18:26:36', 'a', 'logon', '', 'a', 'A', ''), ('147', '2012-05-08 18:29:40', 'a', 'logon', '', 'a', 'A', ''), ('148', '2012-05-08 18:38:59', 'a', 'logon', '', 'a', 'A', ''), ('149', '2012-05-08 18:39:15', 'a', 'patient add', '1234567809', 'n', 'A', ''), ('150', '2012-05-08 18:40:42', 's', 'logon', '', 's', '', ''), ('151', '2012-05-08 18:41:05', 's', 'patient add', '1234567809', 'n', 'B', ''), ('152', '2012-05-08 18:53:07', 'a', 'logon', '', 'a', 'A', ''), ('446', '2012-07-09 16:41:38', 'p', 'logon', '1234567890', 'p', 'A', ''), ('154', '2012-05-08 19:29:19', 'a', 'logon', '', 'a', 'A', ''), ('155', '2012-05-08 19:30:12', 'a', 'logon', '', 'a', 'A', ''), ('156', '2012-05-08 19:30:45', 'a', 'password reset', '', 'm', 'A', ''), ('157', '2012-05-08 19:35:16', 'a', 'logon', '', 'a', 'A', ''), ('158', '2012-05-08 19:43:05', 'a', 'logon', '', 'a', 'A', ''), ('159', '2012-05-08 20:02:08', 'a', 'logon', '', 'a', 'A', ''), ('160', '2012-05-08 20:03:56', 'a', 'logon', '', 'a', 'A', ''), ('161', '2012-05-08 20:06:47', 'a', 'logon', '', 'a', 'A', ''), ('162', '2012-05-09 10:56:22', 'a', 'logon', '', 'a', 'A', ''), ('163', '2012-05-09 11:07:01', 'a', 'logon', '', 'a', 'A', ''), ('164', '2012-05-09 11:07:33', 'a', 'patient view', '1234567890', '', 'A', ''), ('165', '2012-05-09 11:12:26', 'a', 'logon', '', 'a', 'A', ''), ('166', '2012-05-09 11:22:03', 'a', 'logon', '', 'a', 'A', ''), ('167', '2012-05-09 11:24:28', 'a', 'logon', '', 'a', 'A', ''), ('168', '2012-05-09 11:25:55', 'a', 'logon', '', 'a', 'A', ''), ('169', '2012-05-09 14:17:51', 'a', 'logon', '', 'a', 'A', ''), ('170', '2012-05-09 14:18:08', 'a', 'patient view', '1234567890', '', 'A', ''), ('171', '2012-05-09 14:19:49', 'a', 'patient view', '1234567890', '', 'A', ''), ('172', '2012-05-09 16:00:15', 'b', 'logon', '', 'b', 'B', ''), ('445', '2012-07-09 16:41:29', 'p', 'logon', '1234567890', 'p', 'A', ''), ('174', '2012-05-09 16:01:04', 'a', 'logon', '', 'a', 'A', ''), ('175', '2012-05-09 16:01:15', 'b', 'logon', '', 'b', 'B', ''), ('176', '2012-05-09 16:01:28', 'c', 'logon', '', 'c', 'A', ''), ('177', '2012-05-09 16:07:57', 'a', 'logon', '', 'a', 'A', ''), ('178', '2012-05-09 16:08:32', 'a', 'admin add', '', 'e', 'A', ''), ('179', '2012-05-09 16:19:00', 'a', 'logon', '', 'a', 'A', ''), ('180', '2012-05-09 16:20:34', 'a', 'patient view', '1234567890', '', 'A', ''), ('181', '2012-05-09 16:26:22', 'p', 'logon', '1234567890', 'p', 'A', ''), ('182', '2012-05-09 16:26:26', 'p', 'patient view', '1234567890', '', 'A', ''), ('183', '2012-05-09 16:26:29', 'p', 'patient view', '1234567890', '', 'A', ''), ('184', '2012-05-09 16:28:52', 'a', 'logon', '', 'a', 'A', ''), ('185', '2012-05-09 16:29:10', 's', 'logon', '', 's', '', ''), ('186', '2012-05-09 16:29:57', 'p', 'logon', '1234567890', 'p', 'A', ''), ('187', '2012-05-09 16:30:13', 's', 'logon', '', 's', '', ''), ('392', '2012-07-06 11:06:10', 'p', 'logon', '1234567890', 'p', 'A', ''), ('189', '2012-05-09 16:30:46', 'p', 'logon', '1234567890', 'p', 'A', ''), ('190', '2012-05-09 16:52:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('191', '2012-05-09 17:03:27', 'p', 'logon', '1234567890', 'p', 'A', ''), ('192', '2012-05-09 17:07:18', 'system', 'patient data load', '1234567890', '', 'A', 'A_00795_1234567890.gpg.xml'), ('193', '2012-05-09 17:21:02', 'a', 'logon', '', 'a', 'A', ''), ('194', '2012-05-09 19:49:56', 'a', 'logon', '', 'a', 'A', ''), ('195', '2012-05-09 19:50:51', 'a', 'patient add', '2222222222', 'z', 'A', ''), ('196', '2012-05-09 19:53:12', 'a', 'patient add', '2222222227', 'z', 'A', ''), ('197', '2012-05-09 19:56:16', 'a', 'logon', '', 'a', 'A', ''), ('420', '2012-07-09 11:57:50', 'p', 'logon', '1234567890', 'p', 'A', ''), ('421', '2012-07-09 15:46:52', 'p', 'logon', '1234567890', 'p', 'A', ''), ('422', '2012-07-09 15:47:03', 'p', 'patient view', '1234567890', '', 'A', ''), ('202', '2012-05-09 20:25:45', 'a', 'logon', '', 'a', 'A', ''), ('203', '2012-05-09 20:26:27', 'a', 'patient add', '3333333333', 'xx', 'A', ''), ('204', '2012-05-09 20:27:43', 'a', 'logon', '', 'a', 'A', ''), ('433', '2012-07-09 15:50:47', 'p', 'patient view', '1234567890', '', 'A', ''), ('206', '2012-05-09 20:30:17', 'a', 'logon', '', 'a', 'A', ''), ('207', '2012-05-09 20:32:39', 'a', 'logon', '', 'a', 'A', ''), ('443', '2012-07-09 16:40:35', 'p', 'logon', '1234567890', 'p', 'A', ''), ('442', '2012-07-09 16:40:27', 'p', 'patient view', '1234567890', '', 'A', ''), ('437', '2012-07-09 15:59:36', 'p', 'logon', '1234567890', 'p', 'A', ''), ('211', '2012-05-09 20:36:04', 'a', 'logon', '', 'a', 'A', ''), ('444', '2012-07-09 16:41:11', 'p', 'patient view', '1234567890', '', 'A', ''), ('404', '2012-07-09 11:30:35', 'p', 'logon', '1234567890', 'p', 'A', ''), ('214', '2012-05-10 11:48:46', 'b', 'logon', '', 'b', 'B', ''), ('215', '2012-05-10 11:54:26', 'a', 'logon', '', 'a', 'A', ''), ('216', '2012-05-10 13:00:57', 'a', 'logon', '', 'a', 'A', ''), ('217', '2012-05-10 13:59:05', 'a', 'logon', '', 'a', 'A', ''), ('218', '2012-05-10 14:02:46', 'a', 'logon', '', 'a', 'A', ''), ('428', '2012-07-09 15:48:46', 'p', 'patient view', '1234567890', '', 'A', ''), ('429', '2012-07-09 15:48:47', 'p', 'patient view', '1234567890', '', 'A', ''), ('221', '2012-05-10 18:34:10', 'a', 'logon', '', 'a', 'A', ''), ('222', '2012-05-10 18:40:41', 'a', 'logon', '', 'a', 'A', ''), ('223', '2012-05-10 19:12:17', 'a', 'logon', '', 'a', 'A', ''), ('224', '2012-05-10 19:44:17', 'p', 'logon', '1234567890', 'p', 'A', ''), ('225', '2012-05-10 19:45:43', 'a', 'logon', '', 'a', 'A', ''), ('226', '2012-05-10 19:46:24', 'a', 'password change', '', 'a', 'A', ''), ('227', '2012-05-10 19:46:37', 'a', 'logon', '', 'a', 'A', ''), ('228', '2012-05-10 19:47:04', 'a', 'password change', '', 'a', 'A', ''), ('229', '2012-05-10 19:47:17', 'a', 'logon', '', 'a', 'A', ''), ('230', '2012-05-10 19:48:09', 'a', 'logon', '', 'a', 'A', ''), ('231', '2012-05-10 19:48:20', 'a', 'password change', '', 'a', 'A', ''), ('232', '2012-05-10 19:48:43', 'a', 'logon', '', 'a', 'A', ''), ('233', '2012-05-10 19:53:10', 'a', 'logon', '', 'a', 'A', ''), ('234', '2012-05-10 21:18:34', 'a', 'logon', '', 'a', 'A', ''), ('426', '2012-07-09 15:48:31', 'p', 'logon', '1234567890', 'p', 'A', ''), ('236', '2012-05-10 22:00:56', 'a', 'logon', '', 'a', 'A', ''), ('237', '2012-05-10 22:02:29', 'a', 'logon', '', 'a', 'A', ''), ('427', '2012-07-09 15:48:38', 'p', 'patient view', '1234567890', '', 'A', ''), ('397', '2012-07-06 11:19:40', 'p', 'logon', '1234567890', 'p', 'A', ''), ('398', '2012-07-06 11:19:43', 'p', 'patient view', '1234567890', '', 'A', ''), ('241', '2012-05-10 22:05:21', 'a', 'logon', '', 'a', 'A', ''), ('438', '2012-07-09 16:37:48', 'p', 'logon', '1234567890', 'p', 'A', ''), ('243', '2012-05-10 22:10:55', 'a', 'logon', '', 'a', 'A', ''), ('439', '2012-07-09 16:38:02', 'p', 'logon', '1234567890', 'p', 'A', ''), ('440', '2012-07-09 16:40:21', 'p', 'logon', '1234567890', 'p', 'A', ''), ('441', '2012-07-09 16:40:24', 'p', 'patient view', '1234567890', '', 'A', ''), ('247', '2012-05-10 22:13:32', 'a', 'logon', '', 'a', 'A', ''), ('430', '2012-07-09 15:48:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('431', '2012-07-09 15:49:37', 'p', 'logon', '1234567890', 'p', 'A', ''), ('251', '2012-05-10 22:14:45', 'b', 'logon', '', 'b', 'B', ''), ('434', '2012-07-09 15:51:05', 'p', 'logon', '1234567890', 'p', 'A', ''), ('253', '2012-05-10 22:15:27', 'a', 'logon', '', 'a', 'A', ''), ('435', '2012-07-09 15:51:09', 'p', 'patient view', '1234567890', '', 'A', ''), ('255', '2012-05-10 22:27:11', 'a', 'logon', '', 'a', 'A', ''), ('256', '2012-05-10 22:27:30', 'b', 'logon', '', 'b', 'B', ''), ('436', '2012-07-09 15:58:45', 'p', 'logon', '1234567890', 'p', 'A', ''), ('258', '2012-05-10 22:29:42', 'b', 'logon', '', 'b', 'B', ''), ('425', '2012-07-09 15:47:57', 's', 'logon', '', 's', '', ''), ('260', '2012-05-10 22:32:57', 'b', 'logon', '', 'b', 'B', ''), ('423', '2012-07-09 15:47:22', 'p', 'patient view', '1234567890', '', 'A', ''), ('424', '2012-07-09 15:47:32', 'p', 'logon', '1234567890', 'p', 'A', ''), ('263', '2012-05-11 09:00:55', 'a', 'logon', '', 'a', 'A', ''), ('264', '2012-05-11 09:01:09', 'a', 'patient view', '1234567890', '', 'A', ''), ('265', '2012-05-11 09:01:23', 'a', 'patient view', '1234567890', '', 'A', ''), ('266', '2012-05-11 09:02:25', 'a', 'patient view', '1234567890', '', 'A', ''), ('267', '2012-05-11 09:02:57', 'a', 'patient view', '1234567890', '', 'A', ''), ('268', '2012-05-11 09:03:23', 'a', 'patient view', '1234567890', '', 'A', ''), ('269', '2012-05-11 09:03:28', 'a', 'patient view', '1234567890', '', 'A', ''), ('270', '2012-05-11 09:04:12', 'a', 'patient view', '1234567890', '', 'A', ''), ('271', '2012-05-11 09:04:34', 'a', 'patient view', '1234567890', '', 'A', ''), ('272', '2012-05-11 09:04:45', 'a', 'patient view', '1234567890', '', 'A', ''), ('273', '2012-05-11 09:05:22', 'a', 'patient view', '1234567890', '', 'A', ''), ('274', '2012-05-11 16:27:09', 'p', 'logon', '1234567890', 'p', 'A', ''), ('275', '2012-05-11 16:28:54', 'p', 'logon', '1234567890', 'p', 'A', ''), ('276', '2012-05-11 16:40:02', 'p', 'logon', '1234567890', 'p', 'A', ''), ('277', '2012-05-11 16:56:13', 'p', 'logon', '1234567890', 'p', 'A', ''), ('278', '2012-05-11 17:13:02', 'p', 'logon', '1234567890', 'p', 'A', ''), ('279', '2012-05-11 17:14:03', 'p', 'logon', '1234567890', 'p', 'A', ''), ('280', '2012-05-11 17:16:10', 'p', 'logon', '1234567890', 'p', 'A', ''), ('281', '2012-05-11 17:28:51', 'p', 'logon', '1234567890', 'p', 'A', ''), ('282', '2012-05-11 17:32:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('283', '2012-05-11 17:39:14', 'p', 'logon', '1234567890', 'p', 'A', ''), ('284', '2012-05-11 17:50:15', 'p', 'logon', '1234567890', 'p', 'A', ''), ('285', '2012-05-14 14:38:29', 'a', 'logon', '', 'a', 'A', ''), ('286', '2012-05-14 14:59:13', 'a', 'logon', '', 'a', 'A', ''), ('287', '2012-05-14 17:26:48', 'p', 'logon', '1234567890', 'p', 'A', ''), ('288', '2012-05-14 18:37:12', 'a', 'logon', '', 'a', 'A', ''), ('289', '2012-05-14 18:38:03', 's', 'logon', '', 's', '', ''), ('290', '2012-05-14 18:43:31', 'p', 'logon', '1234567890', 'p', 'A', ''), ('291', '2012-05-14 18:44:46', 'p', 'logon', '1234567890', 'p', 'A', ''), ('292', '2012-05-14 18:50:37', 'p', 'logon', '1234567890', 'p', 'A', ''), ('293', '2012-05-14 18:50:42', 'p', 'patient view', '1234567890', '', 'A', ''), ('294', '2012-05-14 18:50:45', 'p', 'patient view', '1234567890', '', 'A', ''), ('295', '2012-05-14 18:50:52', 'p', 'logon', '1234567890', 'p', 'A', ''), ('296', '2012-05-14 18:58:11', 'p', 'logon', '1234567890', 'p', 'A', ''), ('297', '2012-05-14 18:59:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('298', '2012-05-14 19:01:09', 's', 'logon', '', 's', '', ''), ('299', '2012-05-14 19:02:52', 'p', 'logon', '1234567890', 'p', 'A', ''), ('300', '2012-05-14 19:03:08', 'p', 'logon', '1234567890', 'p', 'A', ''), ('301', '2012-05-14 19:03:38', 'p', 'logon', '1234567890', 'p', 'A', ''), ('302', '2012-05-15 00:28:39', 's', 'logon', '', 's', '', ''), ('303', '2012-05-15 00:50:51', 's', 'logon', '', 's', '', ''), ('304', '2012-05-15 00:51:59', 's', 'logon', '', 's', '', ''), ('305', '2012-05-15 00:57:27', 's', 'logon', '', 's', '', ''), ('306', '2012-05-15 01:06:18', 's', 'logon', '', 's', '', ''), ('307', '2012-05-15 01:11:32', 's', 'logon', '', 's', '', ''), ('308', '2012-05-15 01:20:10', 's', 'logon', '', 's', '', ''), ('309', '2012-05-15 01:21:43', 's', 'logon', '', 's', '', ''), ('310', '2012-05-15 01:33:27', 's', 'logon', '', 's', '', ''), ('311', '2012-05-15 01:37:32', 's', 'logon', '', 's', '', ''), ('312', '2012-05-15 01:43:41', 's', 'logon', '', 's', '', ''), ('313', '2012-05-15 01:45:59', 's', 'logon', '', 's', '', ''), ('314', '2012-05-15 23:44:48', 'p', 'logon', '1234567890', 'p', 'A', ''), ('315', '2012-05-17 09:34:06', 'p', 'logon', '1234567890', 'p', 'A', ''), ('316', '2012-05-17 09:34:24', 'P', 'logon', '1234567890', 'P', 'A', ''), ('317', '2012-05-18 10:30:11', 'a', 'logon', '', 'a', 'A', ''), ('318', '2012-05-18 10:30:29', 'a', 'patient add', '0394857485', 'wfwef', 'A', ''), ('319', '2012-05-18 10:31:24', 'a', 'logon', '', 'a', 'A', ''), ('393', '2012-07-06 11:06:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('394', '2012-07-06 11:10:11', 'p', 'logon', '1234567890', 'p', 'A', ''), ('395', '2012-07-06 11:11:23', 'p', 'logon', '1234567890', 'p', 'A', ''), ('396', '2012-07-06 11:17:23', 's', 'logon', '', 's', '', ''), ('324', '2012-05-18 10:35:17', 'a', 'logon', '', 'a', 'A', ''), ('326', '2012-05-24 12:07:29', 's', 'logon', '', 's', '', ''), ('327', '2012-05-24 12:12:58', 'p', 'logon', '1234567890', 'p', 'A', ''), ('328', '2012-05-24 12:14:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('329', '2012-05-24 12:15:07', 'P', 'logon', '1234567890', 'P', 'A', ''), ('330', '2012-05-24 15:54:15', 'p', 'logon', '1234567890', 'p', 'A', ''), ('331', '2012-05-31 16:46:57', 'q', 'logon', '0987654321', 'q', 'A', ''), ('332', '2012-06-06 15:13:29', 'p', 'logon', '1234567890', 'p', 'A', ''), ('333', '2012-06-06 15:20:33', 'p', 'logon', '1234567890', 'p', 'A', ''), ('334', '2012-06-06 15:22:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('335', '2012-06-06 15:28:25', 'p', 'logon', '1234567890', 'p', 'A', ''), ('336', '2012-06-06 15:37:15', 'a', 'logon', '', 'a', 'A', ''), ('337', '2012-06-06 15:37:20', 'a', 'patient view', '1234567890', '', 'A', ''), ('338', '2012-06-06 16:27:42', 'a', 'logon', '', 'a', 'A', ''), ('339', '2012-06-06 16:27:54', 'a', 'patient add', '1365896254', 'sdfvb', 'A', ''), ('340', '2012-06-06 16:29:04', 'a', 'patient add', '1564965896', 'kjhsoidb', 'A', ''), ('341', '2012-06-06 16:33:29', 'a', 'logon', '', 'a', 'A', ''), ('342', '2012-06-06 16:33:54', 'a', 'patient add', '1648539268', 'ssjbo', 'A', ''), ('343', '2012-06-07 15:53:30', 'p', 'logon', '1234567890', 'p', 'A', ''), ('344', '2012-06-07 15:53:33', 'p', 'patient view', '1234567890', '', 'A', ''), ('345', '2012-06-20 14:25:15', 'a', 'logon', '', 'a', 'A', ''), ('346', '2012-06-20 14:25:38', 's', 'logon', '', 's', '', ''), ('347', '2012-06-20 14:28:24', 's', 'logon', '', 's', '', ''), ('348', '2012-06-20 14:32:56', 's', 'logon', '', 's', '', ''), ('349', '2012-06-20 14:36:19', 's', 'logon', '', 's', '', ''), ('350', '2012-06-20 14:40:31', 's', 'logon', '', 's', '', ''), ('351', '2012-06-20 14:45:26', 's', 'logon', '', 's', '', ''), ('352', '2012-06-20 14:49:12', 's', 'logon', '', 's', '', ''), ('353', '2012-06-20 14:52:07', 's', 'logon', '', 's', '', ''), ('354', '2012-06-20 14:53:18', 's', 'logon', '', 's', '', ''), ('355', '2012-06-20 14:53:37', 'a', 'logon', '', 'a', 'A', ''), ('356', '2012-06-20 15:49:06', 's', 'logon', '', 's', '', ''), ('357', '2012-06-20 15:49:56', 'a', 'logon', '', 'a', 'A', ''), ('358', '2012-06-20 15:53:48', 'a', 'logon', '', 'a', 'A', ''), ('359', '2012-06-20 15:59:37', 'c', 'logon', '', 'c', 'A', ''), ('360', '2012-06-20 16:42:01', 's', 'logon', '', 's', '', ''), ('361', '2012-06-20 16:44:05', 's', 'logon', '', 's', '', ''), ('362', '2012-06-20 16:44:27', 'a', 'logon', '', 'a', 'A', ''), ('363', '2012-06-20 16:48:58', 's', 'logon', '', 's', '', ''), ('364', '2012-06-20 16:49:26', 'a', 'logon', '', 'a', 'A', ''), ('365', '2012-06-20 16:49:41', 'c', 'logon', '', 'c', 'A', ''), ('366', '2012-06-20 16:50:08', 's', 'logon', '', 's', '', ''), ('367', '2012-06-21 14:51:22', 's', 'logon', '', 's', '', ''), ('368', '2012-06-21 14:52:06', 'a', 'logon', '', 'a', 'A', ''), ('369', '2012-06-21 14:57:12', 's', 'logon', '', 's', '', ''), ('370', '2012-06-22 16:58:47', 's', 'logon', '', 's', '', ''), ('371', '2012-06-22 17:08:31', 's', 'logon', '', 's', '', ''), ('407', '2012-07-09 11:53:41', 'p', 'logon', '1234567890', 'p', 'A', ''), ('408', '2012-07-09 11:56:48', 'p', 'patient view', '1234567890', '', 'A', ''), ('409', '2012-07-09 11:56:48', 'p', 'patient view', '1234567890', '', 'A', ''), ('410', '2012-07-09 11:56:59', 'p', 'logon', '1234567890', 'p', 'A', ''), ('411', '2012-07-09 11:57:01', 'p', 'patient view', '1234567890', '', 'A', ''), ('412', '2012-07-09 11:57:02', 'p', 'patient view', '1234567890', '', 'A', ''), ('413', '2012-07-09 11:57:11', 'p', 'logon', '1234567890', 'p', 'A', ''), ('414', '2012-07-09 11:57:13', 'p', 'patient view', '1234567890', '', 'A', ''), ('415', '2012-07-09 11:57:14', 'p', 'patient view', '1234567890', '', 'A', ''), ('416', '2012-07-09 11:57:32', 'p', 'logon', '1234567890', 'p', 'A', ''), ('417', '2012-07-09 11:57:35', 'p', 'patient view', '1234567890', '', 'A', ''), ('418', '2012-07-09 11:57:38', 'p', 'patient view', '1234567890', '', 'A', ''), ('419', '2012-07-09 11:57:40', 'p', 'patient view', '1234567890', '', 'A', ''), ('399', '2012-07-06 11:19:48', 'p', 'logon', '1234567890', 'p', 'A', ''), ('400', '2012-07-06 11:19:55', 'c', 'logon', '', 'c', 'A', ''), ('401', '2012-07-06 11:20:47', 'c', 'patient add', '1234567890', 'p', 'B', ''), ('402', '2012-07-06 11:21:08', 'p', 'logon', '1234567890', 'p', 'A', ''), ('403', '2012-07-09 11:27:17', 'p', 'logon', '1234567890', 'p', 'A', ''), ('377', '2012-07-03 10:40:49', 'a', 'logon', '', 'a', 'A', ''), ('378', '2012-07-04 18:00:44', 'p', 'logon', '1234567890', 'p', 'A', ''), ('379', '2012-07-04 18:00:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('380', '2012-07-04 18:01:01', 's', 'logon', '', 's', '', ''), ('381', '2012-07-04 22:17:39', 's', 'logon', '', 's', '', ''), ('382', '2012-07-04 22:21:52', 's', 'logon', '', 's', '', ''), ('383', '2012-07-04 22:22:27', 's', 'logon', '', 's', '', ''), ('384', '2012-07-04 22:25:47', 's', 'logon', '', 's', '', ''), ('385', '2012-07-04 22:32:14', 'p', 'logon', '1234567890', 'p', 'A', ''), ('386', '2012-07-04 22:32:21', 'p', 'patient view', '1234567890', '', 'A', ''), ('387', '2012-07-04 22:32:26', 'p', 'logon', '1234567890', 'p', 'A', ''), ('388', '2012-07-04 22:37:23', 's', 'logon', '', 's', '', ''), ('389', '2012-07-04 22:39:11', 'p', 'logon', '1234567890', 'p', 'A', ''), ('405', '2012-07-09 11:40:19', 'p', 'logon', '1234567890', 'p', 'A', ''), ('391', '2012-07-06 08:35:13', 's', 'logon', '', 's', '', ''), ('406', '2012-07-09 11:50:28', 'p', 'logon', '1234567890', 'p', 'A', ''), ('447', '2012-07-09 16:41:52', 'p', 'logon', '1234567890', 'p', 'A', ''), ('448', '2012-07-09 16:41:56', 'p', 'patient view', '1234567890', '', 'A', ''), ('449', '2012-07-09 16:45:43', 'p', 'logon', '1234567890', 'p', 'A', ''), ('450', '2012-07-09 16:45:53', 'p', 'patient view', '1234567890', '', 'A', ''), ('451', '2012-07-09 16:45:54', 'p', 'patient view', '1234567890', '', 'A', ''), ('452', '2012-07-09 16:46:04', 'p', 'logon', '1234567890', 'p', 'A', ''), ('453', '2012-07-09 16:46:29', 'p', 'patient view', '1234567890', '', 'A', ''), ('454', '2012-07-09 16:52:02', 'p', 'logon', '1234567890', 'p', 'A', ''), ('455', '2012-07-09 16:52:05', 'p', 'patient view', '1234567890', '', 'A', ''), ('456', '2012-07-09 16:52:06', 'p', 'patient view', '1234567890', '', 'A', ''), ('457', '2012-07-09 16:54:38', 'p', 'logon', '1234567890', 'p', 'A', ''), ('458', '2012-07-09 16:54:50', 'p', 'logon', '1234567890', 'p', 'A', ''), ('459', '2012-07-09 16:55:04', 'p', 'logon', '1234567890', 'p', 'A', ''), ('460', '2012-07-09 17:11:35', 'p', 'patient view', '1234567890', '', 'A', ''), ('461', '2012-07-09 17:11:35', 'p', 'logon', '1234567890', 'p', 'A', ''), ('462', '2012-07-09 17:11:53', 'p', 'patient view', '1234567890', '', 'A', ''), ('463', '2012-07-09 17:11:53', 'p', 'logon', '1234567890', 'p', 'A', ''), ('464', '2012-07-09 17:12:00', 'p', 'logon', '1234567890', 'p', 'A', ''), ('465', '2012-07-09 17:12:02', 'p', 'patient view', '1234567890', '', 'A', ''), ('466', '2012-07-09 17:12:13', 'p', 'patient view', '1234567890', '', 'A', ''), ('467', '2012-07-09 17:12:13', 'p', 'patient view', '1234567890', '', 'A', ''), ('468', '2012-07-09 17:15:48', 'p', 'logon', '1234567890', 'p', 'A', ''), ('469', '2012-07-09 17:15:53', 'p', 'patient view', '1234567890', '', 'A', ''), ('470', '2012-07-09 17:15:54', 'p', 'patient view', '1234567890', '', 'A', ''), ('471', '2012-07-09 17:16:03', 'p', 'logon', '1234567890', 'p', 'A', ''), ('472', '2012-07-09 17:16:18', 'p', 'logon', '1234567890', 'p', 'A', ''), ('473', '2012-07-09 17:16:28', 'p', 'logon', '1234567890', 'p', 'A', ''), ('474', '2012-07-09 17:18:13', 'a', 'logon', '', 'a', 'A', ''), ('475', '2012-07-09 17:18:24', 's', 'logon', '', 's', '', ''), ('476', '2012-07-10 10:11:17', 'q', 'logon', '0987654321', 'q', 'A', ''), ('477', '2012-07-10 10:11:32', 'q', 'logon', '0987654321', 'q', 'A', ''), ('478', '2012-07-10 10:11:52', 'p', 'logon', '1234567890', 'p', 'A', ''), ('479', '2012-07-10 10:11:54', 'p', 'patient view', '1234567890', '', 'A', ''), ('480', '2012-07-10 10:12:01', 'p', 'logon', '1234567890', 'p', 'A', ''), ('481', '2012-07-10 10:12:15', 'p', 'logon', '1234567890', 'p', 'A', ''), ('482', '2012-07-10 10:14:08', 'a', 'logon', '', 'a', 'A', ''), ('483', '2012-07-10 10:14:22', 'b', 'logon', '', 'b', 'B', ''), ('484', '2012-07-10 10:14:54', 'a', 'logon', '', 'a', 'A', ''), ('485', '2012-07-10 10:15:21', 'b', 'logon', '', 'b', 'B', ''), ('486', '2012-07-10 10:15:38', 'c', 'logon', '', 'c', 'A', ''), ('487', '2012-07-10 10:15:56', 's', 'logon', '', 's', '', ''), ('488', '2012-07-10 10:54:48', 's', 'logon', '', 's', '', ''), ('489', '2012-07-10 11:05:08', 's', 'logon', '', 's', '', ''), ('490', '2012-07-10 11:16:44', 's', 'logon', '', 's', '', ''), ('491', '2012-07-10 11:18:32', 's', 'logon', '', 's', '', ''), ('492', '2012-07-10 11:24:13', 's', 'logon', '', 's', '', ''), ('493', '2012-07-10 11:25:55', 'a', 'logon', '', 'a', 'A', ''), ('494', '2012-07-10 11:26:18', 'c', 'logon', '', 'c', 'A', ''), ('495', '2012-07-10 11:26:41', 'b', 'logon', '', 'b', 'B', ''), ('496', '2012-07-10 11:26:50', 's', 'logon', '', 's', '', ''), ('497', '2012-07-10 11:28:31', 'p', 'logon', '1234567890', 'p', 'A', ''), ('498', '2012-07-10 11:28:39', 'p', 'logon', '1234567890', 'p', 'A', ''), ('499', '2012-07-10 11:28:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('500', '2012-07-10 11:31:36', 'p', 'logon', '1234567890', 'p', 'A', ''), ('501', '2012-07-10 11:31:38', 'p', 'patient view', '1234567890', '', 'A', ''), ('502', '2012-07-10 11:31:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('503', '2012-07-10 12:33:29', 's', 'logon', '', 's', '', ''), ('504', '2012-07-10 12:36:38', 's', 'logon', '', 's', '', ''), ('505', '2012-07-10 12:41:26', 's', 'logon', '', 's', '', ''), ('506', '2012-07-10 12:41:45', 'p', 'logon', '1234567890', 'p', 'A', ''), ('507', '2012-07-10 12:41:47', 'p', 'patient view', '1234567890', '', 'A', ''), ('508', '2012-07-10 12:42:09', 's', 'logon', '', 's', '', ''), ('509', '2012-07-10 12:43:12', 's', 'logon', '', 's', '', ''), ('510', '2012-07-10 12:43:47', 'p', 'logon', '1234567890', 'p', 'A', ''), ('511', '2012-07-10 12:43:50', 'p', 'patient view', '1234567890', '', 'A', ''), ('512', '2012-07-10 12:43:57', 'p', 'logon', '1234567890', 'p', 'A', ''), ('513', '2012-07-10 12:44:16', 's', 'logon', '', 's', '', ''), ('514', '2012-07-10 16:37:05', 'p', 'logon', '1234567890', 'p', 'A', ''), ('515', '2012-07-10 16:55:04', 's', 'logon', '', 's', '', ''), ('516', '2012-07-10 16:57:50', 's', 'logon', '', 's', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `medicine`
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `startdate` datetime DEFAULT '0000-00-00 00:00:00',
  `name` varchar(100) DEFAULT '',
  `dose` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `medicine`
-- ----------------------------
BEGIN;
INSERT INTO `medicine` VALUES ('1', '1234567890', 'A', '2012-03-15 00:00:10', 'Darbepoetin alfa', '20 micro g PreFillSyringe Iv Weekly on dialysis. '), ('2', '1234567890', 'A', '2012-02-23 14:46:47', 'Ramipril', '5 mg. Capsules Oral Daily '), ('3', '1234567890', 'A', '2012-01-19 00:00:10', 'Aspirin', '75 mg. Tabs (Soluble) Oral In the morning '), ('4', '1234567890', 'A', '2011-12-13 14:01:12', 'Prednisolone', '5 mg. Tablet/s Oral In the morning '), ('5', '1234567890', 'A', '2011-08-08 00:00:10', 'Sevelamer HCl (Renagel)', '2 Tablet/s Oral 3 x day @ meals '), ('6', '1234567890', 'A', '2011-05-09 00:00:10', 'Levothyroxine', '175 micro g Tablet/s Oral In the morning '), ('7', '1234567890', 'A', '2011-05-09 00:00:10', 'Omeprazole', '20 mg. Capsules Oral In the morning '), ('8', '1234567890', 'A', '2010-11-09 00:00:10', 'Venofer', '100 mg. Iv weekly. ');
COMMIT;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL DEFAULT '0',
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `patient` tinyint(1) NOT NULL DEFAULT '0',
  `everyone` tinyint(10) NOT NULL DEFAULT '0',
  `headline` varchar(255) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `news`
-- ----------------------------
BEGIN;
INSERT INTO `news` VALUES ('2', '2012-04-05 19:18:49', 'A', '1', '0', '0', 'asdasdd', 'dsddssdsdsdddssd'), ('1', '2012-04-05 19:21:02', 'A', '0', '0', '1', 'ishoifh', 'oihasdoif');
COMMIT;

-- ----------------------------
--  Table structure for `patient`
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
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
  `centreCode` varchar(100) NOT NULL DEFAULT '',
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
  PRIMARY KEY (`nhsno`,`centreCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `patient`
-- ----------------------------
BEGIN;
INSERT INTO `patient` VALUES ('1234567890', 'Andrew', 'Andy', '1961-09-08', 'Male', '1 Silly St', 'Yorkshire', null, 'F56 6HJL', '020 9999 8888', '07886888888', '0789999999', 'A', 'DEF', 'HD', null, '1111111', 'Dr GP', '12 GP Street', 'slidjfln', 'sdf', 'GP1 7GP', '02 9384 5595');
COMMIT;

-- ----------------------------
--  Table structure for `result_heading`
-- ----------------------------
DROP TABLE IF EXISTS `result_heading`;
CREATE TABLE `result_heading` (
  `headingcode` varchar(20) NOT NULL DEFAULT '',
  `heading` varchar(30) NOT NULL DEFAULT '',
  `rollover` varchar(50) NOT NULL DEFAULT 'Click for info',
  `link` varchar(100) NOT NULL DEFAULT '',
  `panel` int(11) NOT NULL DEFAULT '0',
  `panelorder` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`headingcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `result_heading`
-- ----------------------------
BEGIN;
INSERT INTO `result_heading` VALUES ('urea', 'Urea', 'Urea (2.5-7.0)', 'http://www.renal.org/rixg/results/urea.html', '1', '1'), ('creatinine', 'Creatinine', 'Creatinine (60-120)', 'http://www.renal.org/rixg/results/creatinine.html', '1', '2'), ('potassium', 'K', 'Potassium (3.5-5.0)', 'http://www.renal.org/rixg/results/potassium.html', '1', '3'), ('calcium', 'Ca', 'Calcium (2.1-2.6)', 'http://www.renal.org/rixg/results/calcium.html', '1', '4'), ('phosphate', 'PO4', 'Phosphate (0.8-1.4)', 'http://www.renal.org/rixg/results/phosphate.html', '1', '6'), ('hb', 'Hb', 'Haemoglobin (115-180)', 'http://www.renal.org/rixg/results/haemoglobin.html', '3', '7'), ('wbc', 'wbc', 'White blood cell count (4-11)', 'http://www.renal.org/rixg/results/wbc.html', '1', '8'), ('platelets', 'plats', 'Platelets (150-400)', 'http://www.renal.org/rixg/results/platelets.html', '1', '9'), ('ciclosporin', 'Ciclo', 'Ciclosporin (cyclosporin) Click for info', 'http://www.renal.org/rixg/results/ciclosporin.html', '3', '6'), ('adjustedcalcium', 'AdjCa', 'Adjusted Calcium (2.1-2.6) Click for info', 'http://www.renal.org/rixg/results/adjcalcium.html', '1', '5'), ('albumin', 'Alb', 'Albumin (35-50) Click for info', 'http://www.renal.org/rixg/results/albumin.html', '2', '1'), ('bpdia', 'BPdia', 'Diastolic blood pressure - Click for info', 'http://www.renal.org/rixg/results/bp.html', '3', '3'), ('bpsys', 'BPsys', 'Systolic blood pressure - Click for info', 'http://www.renal.org/rixg/results/bp.html', '3', '2'), ('cholesterol', 'Cholest', 'Cholesterol - Click for info', 'http://www.renal.org/rixg/results/lipids.html', '2', '7'), ('crp', 'CRP', 'C-reactive protein (0-10) Click for info', 'http://www.renal.org/rixg/results/crp.html', '2', '2'), ('glucose', 'Gluc', 'Glucose (3.5-7, or 11) Click for info', 'http://www.renal.org/rixg/results/glucose.html', '2', '9'), ('hba1c', 'HbA1c', 'HbA1c (less than 7%) Click for info', 'http://www.renal.org/rixg/results/glucose.html', '3', '10'), ('height', 'Height', 'Click for info', 'http://www.renal.org/rixg/results/weightheight.html', '3', '4'), ('ktv', 'Kt/V', 'Kt/V (dialysis adequacy measure) Click for info', 'http://www.renal.org/rixg/results/ktv.html', '4', '6'), ('pth', 'PTH', 'Parathyroid hormone - Click for info', 'http://www.renal.org/rixg/results/pth.html', '3', '9'), ('tacrolimus', 'Tacro', 'Tacrolimus - Click for info', 'http://www.renal.org/rixg/results/tacrolimus.html', '3', '7'), ('tg', 'TG', 'Triglycerides - Click for info', 'http://www.renal.org/rixg/results/lipids.html', '2', '8'), ('urr', 'URR', 'Urea reduction ratio - Click for info', 'http://www.renal.org/rixg/results/urr.html', '4', '5'), ('weight', 'Weight', 'in kg.  Click for info', 'http://www.renal.org/rixg/results/weightheight.html', '3', '1'), ('egfr', 'eGFR', 'Estimated GFR (over 60) Click for info', 'http://www.renal.org/rixg/results/egfr.html', '1', '10'), ('inr', 'INR', 'Warfarin dose control (Usually 2-4) - Click for in', 'http://www.renal.org/rixg/results/inr.html', '3', '5'), ('sodium', 'Na', 'Sodium (135-147) Click for info', 'http://www.renal.org/rixg/results/sodium.html', '2', '3'), ('hco3', 'HCO3', 'Bicarbonate (20-30) Click for info', 'http://www.renal.org/rixg/results/hco3.html', '2', '4'), ('pcr', 'PCR', 'Protein Creatinine ratio (less than 15) Click for', 'http://www.renal.org/rixg/results/pcr.html', '2', '5'), ('acr', 'ACR', 'Albumin:creatinine ratio (less than 3.5) Click for', 'http://www.renal.org/rixg/results/acr.html', '2', '6'), ('sirolimus', 'Siro', 'Sirolimus - Click for info', 'http://www.renal.org/rixg/results/sirolimus.html', '3', '8'), ('bili', 'Bili', 'Bilirubin (liver test) (2-17) Click for info', 'http://www.renal.org/rixg/results/lft.html', '5', '1'), ('ast', 'AST', 'AST (liver test) (10-45) Click for info', 'http://www.renal.org/rixg/results/lft.html', '5', '2'), ('alt', 'ALT', 'AST (liver test) (10-50) Click for info', 'http://www.renal.org/rixg/results/lft.html', '5', '3'), ('alp', 'AlkP', 'AlkP (liver test) (40-125) Click for info', 'http://www.renal.org/rixg/results/lft.html', '5', '4'), ('ggt', 'GGT', 'GGT (liver test) (5-55) Click for info', 'http://www.renal.org/rixg/results/lft.html', '5', '5'), ('ferritin', 'Ferr', 'Ferritin (iron test) (17-300) Click for info', 'http://www.renal.org/rixg/results/iron.html', '5', '6'), ('iron', 'Iron', 'Iron (10-32) Click for info', 'http://www.renal.org/rixg/results/iron.html', '5', '7'), ('transferrin', 'Tferrin', 'Transferrin (iron test) (2-4) Click for info', 'http://www.renal.org/rixg/results/iron.html', '5', '8'), ('ironsat', 'Fe Sat', 'Iron Saturation (14-56) Click for info', 'http://www.renal.org/rixg/results/iron.html', '5', '9'), ('urate', 'Urate', 'Uric Acid (0.12-0.4) Click for info', 'http://www.renal.org/rixg/results/urate.html', '5', '10'), ('resultcomment', 'Comment', 'Click for info', '', '2', '20');
COMMIT;

-- ----------------------------
--  Table structure for `splashpage`
-- ----------------------------
DROP TABLE IF EXISTS `splashpage`;
CREATE TABLE `splashpage` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `live` tinyint(1) NOT NULL,
  `headline` varchar(100) NOT NULL,
  `bodytext` text NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `splashpageuserseen`
-- ----------------------------
DROP TABLE IF EXISTS `splashpageuserseen`;
CREATE TABLE `splashpageuserseen` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `splashpageid` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersplashpage` (`username`,`splashpageid`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `splashpageuserseen`
-- ----------------------------
BEGIN;
INSERT INTO `splashpageuserseen` VALUES ('33', 'p', '6'), ('36', 'p', '1'), ('35', 'p', '2');
COMMIT;

-- ----------------------------
--  Table structure for `testresult`
-- ----------------------------
DROP TABLE IF EXISTS `testresult`;
CREATE TABLE `testresult` (
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `testcode` varchar(100) NOT NULL DEFAULT '',
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `prepost` varchar(100) DEFAULT '',
  `value` varchar(100) NOT NULL DEFAULT '',
  KEY `nhsno_testcode` (`nhsno`,`testcode`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `testresult`
-- ----------------------------
BEGIN;
INSERT INTO `testresult` VALUES ('1234567890', 'A', 'hb', '2012-03-13 00:00:10', '', '8.90'), ('1234567890', 'A', 'wbc', '2012-03-20 14:00:00', '', '6.00'), ('1234567890', 'A', 'platelets', '2012-01-26 00:00:10', '', '104'), ('1234567890', 'A', 'urea', '2012-03-13 00:00:10', '', '4.10'), ('1234567890', 'A', 'creatinine', '2012-03-13 00:00:10', '', '285'), ('1234567890', 'A', 'potassium', '2012-03-13 00:00:10', '', '2.70'), ('1234567890', 'A', 'calcium', '2010-02-16 07:00:00', '', '1.99'), ('1234567890', 'A', 'calcium', '2010-02-07 07:00:00', '', '1.82'), ('1234567890', 'A', 'adjustedcalcium', '2012-03-13 00:00:10', '', '2.07'), ('1234567890', 'A', 'adjustedcalcium', '2012-01-26 00:00:10', '', '1.98'), ('1234567890', 'A', 'phosphate', '2012-03-13 00:00:10', '', '0.86'), ('1234567890', 'A', 'albumin', '2012-03-13 00:00:10', '', '40'), ('1234567890', 'A', 'crp', '2012-03-08 14:00:00', '', '6'), ('1234567890', 'A', 'pth', '2012-02-02 00:00:10', '', '0.9'), ('1234567890', 'A', 'cholesterol', '2012-02-02 00:00:10', '', '4'), ('1234567890', 'A', 'bpsys', '2012-03-20 13:55:00', '', '122'), ('1234567890', 'PATIENT', 'bpsys', '2012-06-06 15:36:31', '', '120'), ('1234567890', 'PATIENT', 'bpdia', '2012-06-06 15:36:31', '', '80'), ('1234567890', 'A', 'bpdia', '2012-03-20 13:55:00', '', '85'), ('1234567890', 'A', 'height', '2009-11-24 00:00:10', '', '175'), ('1234567890', 'A', 'weight', '2012-03-20 13:55:00', '', '94.2'), ('1234567890', 'A', 'hb', '2012-03-20 14:00:00', '', '8.60');
COMMIT;

-- ----------------------------
--  Table structure for `treatment`
-- ----------------------------
DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment` (
  `nhsNo` varchar(100) NOT NULL DEFAULT '',
  `treatmentCode` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`nhsNo`,`treatmentCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `uktcode`
-- ----------------------------
DROP TABLE IF EXISTS `uktcode`;
CREATE TABLE `uktcode` (
  `id` int(11) NOT NULL DEFAULT '0',
  `uktcode` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uktcode_unique` (`uktcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `uktcode`
-- ----------------------------
BEGIN;
INSERT INTO `uktcode` VALUES ('1', 'A', 'Active'), ('2', 'S', 'Suspended'), ('3', 'T', 'Transplanted'), ('4', 'R', 'Not on list'), ('5', 'N', 'Not on list'), ('6', 'O', 'Not on list');
COMMIT;

-- ----------------------------
--  Table structure for `uktstatus`
-- ----------------------------
DROP TABLE IF EXISTS `uktstatus`;
CREATE TABLE `uktstatus` (
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `kidney` varchar(10) DEFAULT '',
  `pancreas` varchar(10) DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `unit`
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
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
  `rpvadminname` varchar(100) DEFAULT NULL,
  `rpvadminphone` varchar(100) DEFAULT NULL,
  `rpvadminemail` varchar(100) DEFAULT NULL,
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
  PRIMARY KEY (`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `unit`
-- ----------------------------
BEGIN;
INSERT INTO `unit` VALUES ('A', 'A A', 'wwwwwww wwwwwww', 'A_sdfsdf', '1 A stree', 'A town', '', 'AA1 7AA', '', '', 'A Aton', '020 1111 1111', '', '', 'rob@robworth.com', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('B', 'B B', 'Bb', 'B_sdfsdf', '2 B Street', 'B Town', '', 'BB2 2BB', '', '', 'B Bville', '020 22222222', '', '', 'mail@robworth.com', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''), ('PATIENT', 'Patient Entered', 'Patient', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `unitstat`
-- ----------------------------
DROP TABLE IF EXISTS `unitstat`;
CREATE TABLE `unitstat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitcode` varchar(20) NOT NULL,
  `yearmonth` varchar(7) NOT NULL,
  `action` varchar(30) NOT NULL,
  `count` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `role` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `emailverified` tinyint(1) DEFAULT '0',
  `firstlogon` tinyint(1) DEFAULT '0',
  `dummypatient` tinyint(1) NOT NULL DEFAULT '0',
  `lastlogon` datetime DEFAULT NULL,
  `failedlogons` int(10) DEFAULT '0',
  `accountlocked` tinyint(1) DEFAULT '0',
  `screenname` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('s', '0a1b086f072513ebb1d3d715166583135b706781ce4948cb1eb90b9837eb5707', 'superadmin', 's', null, '0', '0', '0', '2012-07-10 16:57:50', '0', '0', 'ss sssss'), ('p', '891e12e156d8c6609c6d5f3e04b2fc8da6d9ff3d7e9f906314c0909da69637eb', 'patient', 'P P ', 'p@robworth.com', '0', '0', '0', '2012-07-10 16:37:05', '0', '0', ''), ('p-GP', '4b74d2594e5ed2e0795553a211b563cf424bf8ff993a3be8ca3f72609b335980', 'patient', 'P P -GP', null, '0', '1', '0', null, '0', '0', ''), ('q', 'b6197fe0d62a4e463edd2925382d4d268c4fce0859378682608efa4fda326f26', 'patient', 'Q q', 'q@robworth.co', '0', '0', '0', '2012-07-10 10:11:10', '0', '0', 'qq'), ('q-GP', 'a5f4f867eed6109f671bd08a30ccc8b8aafbfd98e3c033943f4fd52a0bb087f0', 'patient', 'Q Q-GP', null, '0', '1', '0', null, '0', '0', ''), ('x', 'b7fb217694ae2d305e766608d250f797daa984e4ac4b5fa638a729be352f2fcd', 'patient', 'X X', 'x@robworth.com', '0', '0', '0', '2012-04-04 16:09:50', '0', '0', ''), ('x-GP', 'd0a91ebf8bee1af09d0b4bd1285845e9da557ee54fa212efefba51544c95a676', 'patient', 'X X-GP', null, '0', '1', '0', null, '0', '0', ''), ('y', '96ee59df0b588d3d0c2402e6bf6f51403e94332a6da5924c3a087f92659aa44e', 'patient', 'Y Y', 'y@robworth.com', '0', '0', '0', '2012-04-04 16:10:47', '0', '0', ''), ('y-GP', 'c18b5546807238100a9864840efb024f7e543a920b4132a3f47762d21a87625d', 'patient', 'Y Y-GP', null, '0', '1', '0', null, '0', '0', ''), ('k', '4ae9171dfdaad10c368ddcaafb205bd77346dbe393a8a0317d03409f88c048d2', 'patient', 'K K', '', '0', '1', '0', null, '1', '0', ''), ('k-GP', 'bb0686b0c4bafa468208471357ce6e0e39010538a74cfb66ae11a8795a1a1ef3', 'patient', 'K K-GP', null, '0', '1', '0', null, '0', '0', ''), ('d', '1a969cb7f2c224d31553cab73daf550bebeb983a462cdfc6c0448efe56690e62', 'patient', 'd d', '', '0', '1', '0', null, '0', '0', ''), ('d-GP', 'aa9a8fc42c12dd104ac9064d24cacdb3c40618068ea0dd5f64d5e05032697d97', 'patient', 'd d-GP', null, '0', '1', '0', null, '0', '0', ''), ('a', 'ed02457b5c41d964dbd2f2a609d63fe1bb7528dbe55e1abf5b52c249cd735797', 'unitadmin', 'A A', '', '0', '0', '0', '2012-07-10 11:25:55', '0', '0', ''), ('b', '4625fd63b0e96fc0d656ae7381605e48d4a0f63a319fc743adf22688613883c7', 'unitadmin', 'B B', '', '0', '0', '0', '2012-07-10 11:26:41', '0', '0', ''), ('c', '8c0e24f73bf5fe793dc45e5f90b5a9682c0cb345f524ddef77ab2859b9352d52', 'unitadmin', 'C C', 'cc@robworth.com', '0', '0', '0', null, '0', '0', ''), ('e', '563a71e7753d26d6aa574117baab58433da1702b7c12ca3023bf4cdb2633c71a', 'unitstaff', 'e d', 'd@e.com', '0', '1', '0', null, '0', '0', '');
COMMIT;

-- ----------------------------
--  Table structure for `userlog`
-- ----------------------------
DROP TABLE IF EXISTS `userlog`;
CREATE TABLE `userlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `unitcode` varchar(100) DEFAULT NULL,
  `role` varchar(100) NOT NULL DEFAULT '',
  `count` int(21) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `usermapping`
-- ----------------------------
DROP TABLE IF EXISTS `usermapping`;
CREATE TABLE `usermapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `usermapping`
-- ----------------------------
BEGIN;
INSERT INTO `usermapping` VALUES ('53', 's', '', ''), ('54', 'p', 'A', '1234567890'), ('55', 'p-GP', 'A', '1234567890'), ('56', 'q', 'A', '0987654321'), ('57', 'q-GP', 'A', '0987654321'), ('58', 'x', 'B', '5647382910'), ('59', 'x-GP', 'B', '5647382910'), ('60', 'y', 'B', '1029384756'), ('61', 'y-GP', 'B', '1029384756'), ('62', 'k', 'A', '1357924680'), ('63', 'k-GP', 'A', '1357924680'), ('64', 'd', 'B', '2468013579'), ('65', 'd-GP', 'A', '2468013579'), ('66', 'a', 'A', ''), ('67', 'b', 'B', ''), ('68', 'c', 'A', ''), ('69', 'p', 'PATIENT', '1234567890'), ('70', 'q', 'PATIENT', '0987654321'), ('71', 'x', 'PATIENT', '5647382910'), ('72', 'y', 'PATIENT', '1029384756'), ('73', 'k', 'PATIENT', '1357924680'), ('74', 'd', 'PATIENT', '2468013579'), ('83', 'c', 'B', ''), ('145', 'p', 'B', '1234567890'), ('146', 'p-GP', 'B', '1234567890');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
