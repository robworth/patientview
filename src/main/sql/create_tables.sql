CREATE TABLE sysdiagrams (
	name varchar(128) NOT NULL,
	principal_id int NOT NULL,
	diagram_id int NOT NULL,
	version int,
	definition blob
);

CREATE TABLE tbl_6Month (
	fuID int NOT NULL,
	RADAR_NO int,
	DATE_FUP datetime,
	RELAP_SINCE_LAST bit,
	RELAP_LEN int,
	VIRAL_TRIG int,
	IMMUN_TRIG int,
	OTHER_TRIG int,
	IMMUNOSUP_INC bit,
	IMMUNOSUP_DOSE int,
	IMMUNOSUP_DUR int,
	PLASMA_EXCH int,
	PLASMA_EXCH_NO int,
	RESPONSE_TO int,
	MAX_PR_CREAT_RATIO int,
	MIN_SER_ALB int,
	COMP1 int,
	COMP2 int,
	COMP3 int,
	COMP4 int,
	OTHER_COMP varchar(50),
	DATE_START_DIAL datetime,
	DIAL_TYPE int,
	DATE_TRANSPLANT datetime,
	TRANS_TYPE int,
	TRANS_RECURR bit,
	DATE_TX_REJECT datetime,
	DATE_BX datetime,
	DATE_NEPHRECT datetime,
	DRUG1 varchar(50),
	DRUG2 varchar(50),
	DRUG3 varchar(50),
	DRUG4 varchar(50),
	DRUG5 varchar(50),
	DRUG6 varchar(50),
	SIG_CHANGE_STATUS nchar(10)
);

CREATE TABLE tbl_AdminUsers (
	uID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (uID),
	uName varchar(30),
	uEmail varchar(50),
	uPass varbinary(50),
	uUserName varbinary(50)
);

CREATE TABLE tbl_Centres (
	cID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (cID),
	cName varchar(80),
	cAbbrev varchar(15),
	cCountry int
);

CREATE TABLE tbl_Clin_Pres (
	cID int AUTO_INCREMENT, PRIMARY KEY (cID),
	CLIN_PRES varchar(20)
);

CREATE TABLE tbl_ClinicalData (
	cID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (cID),
	RADAR_NO int NOT NULL,
	DATE_CLIN_PIC datetime,
	HEIGHT decimal(8,1),
	WEIGHT decimal(8,2),
	COURSE_DIS int,
	SYS_BP int,
	DIA_BP int,
	MAP_BP int,
	DIALYSIS_REQ int,
	DATE_BX datetime,
	OEDEMA bit,
	ANAEMIA bit,
	HYPOVAL bit,
	FEVER bit,
	INFECTION bit,
	INFECTION_DETAIL varchar(50),
	INFECTION_TYPE varchar(50),
	THROMBOSIS bit,
	THROMBOSIS_DETAIL varchar(250),
	COMP_THROMBOSIS bit,
	COMP_THROMBOSIS_DETAIL text,
	PERITONITIS bit,
	PUL_OED bit,
	HTH_REQ_TMT bit,
	PREC_INF bit,
	PREC_INF_DETAIL varchar(150),
	CLIN_EV_CHR_INF bit,
	CLIN_EV_CHR_INF_DETAIL varchar(150),
	DIABETES smallint,
	URTICARIA smallint,
	RASH bit,
	RASH_DETAIL varchar(50),
	PART_LIPODYS bit,
	OPTHALM bit,
	OPTHALM_DETAIL varchar(50),
	IMMUNIS_TRIGGER bit,
	COMMENTS text,
	PHENOTYPE1 int,
	PHENOTYPE2 int,
	PHENOTYPE3 int,
	PHENOTYPE4 int,
	SIG_DIAG1 varchar(30),
	SIG_DIAG2 varchar(30),
	TX_LISTED bit,
	CKD_STAGE int,
	SEQ_NO int
);

CREATE TABLE tbl_Complication (
	cmpID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (cmpID),
	cmpDesc varchar(50)
);

CREATE TABLE tbl_Consultants (
	cID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (cID),
	cSNAME varchar(50),
	cFNAME varchar(50),
	cCentre int
);

CREATE TABLE tbl_Country (
	cID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (cID),
	cName varchar(50)
);

CREATE TABLE tbl_Demographics (
	RADAR_NO int NOT NULL AUTO_INCREMENT, PRIMARY KEY (RADAR_NO),
	RR_NO varchar(10),
	DATE_REG datetime,
	NHS_NO varbinary(50),
	HOSP_NO varbinary(50),
	UKT_NO bigint,
	CHI_NO bigint,
	SNAME varbinary(50),
	SNAME_ALIAS varbinary(50),
	FNAME varbinary(50),
	DOB varbinary(50),
	AGE int,
	SEX int,
	ETHNIC_GP varchar(6),
	ADD1 varbinary(50),
	ADD2 varbinary(50),
	ADD3 varbinary(50),
	ADD4 varbinary(50),
	POSTCODE varbinary(50),
	POSTCODE_OLD varbinary(50),
	CONSENT bit,
	DATE_BAPN_REG datetime,
	CONS_NEPH varchar(6),
	RENAL_UNIT int,
	RENAL_UNIT_2 int,
	STATUS int
);

CREATE TABLE tbl_DiagCode (
	dcID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (dcID),
	dcDesc varchar(70),
	dcAbbr varchar(15)
);

CREATE TABLE tbl_Diagnosis (
	dID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (dID),
	RADAR_NO int,
	DATE_DIAG datetime,
	DIAG int,
	DIAG_TXT varchar(500),
	AGE_AT_DIAG decimal(4,1),
	HEIGHT_FIRST_VISIT numeric(4,1),
	BX_PROVEN_DIAG varchar(1),
	PREPUB_DIAG bit,
	CLIN_PRES int,
	CLIN_PRES_B int,
	GENE_MUT varchar(50),
	GENE_MUT_TEXT varchar(500),
	KARYOTYPE varchar(50),
	KARYOTYPE_OTHER varchar(500),
	DATE_ONSET_RENALDIS datetime,
	CONSANGUINITY int,
	FAM_HIST int,
	REL1 varchar(20),
	REL1_RADAR int,
	REL2 varchar(20),
	REL2_RADAR int,
	REL3 varchar(20),
	REL3_RADAR int,
	REL4 varchar(20),
	REL4_RADAR int,
	REL5 varchar(20),
	REL5_RADAR int,
	REL6 varchar(20),
	REL6_RADAR int,
	SIG_DIAG1 varchar(50),
	SIG_DIAG2 varchar(50),
	STEROID_RESIST int,
	DATE_ESRF datetime,
	MUTATION_1 bit,
	MUTATION_1S bit,
	MUTATION_2 bit,
	MUTATION_2S bit,
	MUTATION_3 bit,
	MUTATION_3S bit,
	MUTATION_4 bit,
	MUTATION_4S bit,
	MUTATION_5 bit,
	MUTATION_5S bit,
	MUTATION_6 bit,
	MUTATION_6S bit,
	MUTATION_7 bit,
	MUTATION_7S bit,
	MUTATION_8 bit,
	MUTATION_8S bit,
	MUTATION_9 bit,
	MUTATION_9S bit
);

CREATE TABLE tbl_DiseaseData (
	dID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (dID),
	dText text
);

CREATE TABLE tbl_Ethnicity (
	eID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (eID),
	eName varchar(50),
	eCode varchar(50)
);

CREATE TABLE tbl_GeneMutation (
	gmID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (gmID),
	GENE_MUTATION varchar(30)
);

CREATE TABLE tbl_HD_MODALITY (
	hdID int AUTO_INCREMENT, PRIMARY KEY (hdID),
	hdType varchar(75)
);

CREATE TABLE tbl_HDial (
	hdID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (hdID),
	RADAR_NO int,
	DATE_START_HDIAL datetime
);

CREATE TABLE tbl_Hospitalisation (
	hID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (hID),
	RADAR_NO int,
	DATE_ADMIT datetime,
	DATE_DISCHARGE datetime,
	REASON_ADMIT varchar(250),
	COMMENT text
);

CREATE TABLE tbl_ImmunoSupp (
	imID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (imID),
	imDesc varchar(50),
	Group int
);

CREATE TABLE tbl_IMMUNSUP_TREATMENT (
	tID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (tID),
	RADAR_NO int,
	IMMUNSUP_DRUG_STARTDATE datetime,
	IMMUNSUP_DRUG_ENDDATE datetime,
	IMMUNSUP_DRUG int,
	CYCLOPHOS_TOT_DOSE decimal(5,3),
	FIRST_FLAG bit
);

CREATE TABLE tbl_IssueTracker (
  bID int(11) NOT NULL auto_increment, PRIMARY KEY  (bID),
  bType varchar(50) default NULL,
  bPage varchar(50) default NULL,
  bDateLogged datetime default NULL,
  bDateResolved datetime default NULL,
  bDesc text,
  bComment text,
  bPriority varchar(20) default NULL,
  bStatus varchar(50) default NULL,
  bUpdated datetime default NULL
);

CREATE TABLE tbl_Karyotype (
	kID int AUTO_INCREMENT, PRIMARY KEY (kID),
	KARYOTYPE varchar(50)
);

CREATE TABLE tbl_LabData (
	labID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (labID),
	RADAR_NO int,
	DATE_LAB_RES datetime,
	CREAT_SER int,
	PROTEIN int,
	ALBUMIN int,
	UREA_SER numeric(3,1),
	SODIUM int,
	POTASSIUM numeric(2,1),
	PHOS numeric(3,2),
	PROT_CREAT_RAT numeric(6,1),
	ALB_CREAT_RAT numeric(5,1),
	WBC numeric(3,1),
	HB numeric(3,1),
	NEUTRO numeric(3,1),
	PLATELETS int,
	FERRITIN int,
	CHOL_TOTAL numeric(3,1),
	CHOL_HDL numeric(3,1),
	CHOL_LDL numeric(3,1),
	TRIG numeric(3,1),
	CREAT_CLEAR_24_URINE int,
	CREAT_CLEAR_RADIO int,
	CREAT_CLEAR_SCHZ int,
	THYROX numeric(4,1),
	TSH numeric(4,2),
	ANCA int,
	ELISA_ASS int,
	ENA int,
	ANA int,
	DNA_ANTIB varchar(50),
	DNA_ANTI_DS int,
	CRYOGLOB varchar(50),
	ANTI_GBM varchar(50),
	IGG numeric(4,1),
	IGA numeric(4,1),
	IGM numeric(4,1),
	COMP_C3 numeric(4,2),
	COMP_C4 numeric(4,2),
	COMP_OTHER text,
	C3_NEPH_FAC int,
	ANTI_SLT int,
	INR numeric(2,1),
	CRP int,
	ANTI_STREP_O int,
	HEP_B int,
	HEP_C int,
	HIV int,
	DNA_FACTOR_H bit,
	EBV int,
	CMV int,
	CMV_SYM bit,
	BKV bit,
	BKV_SYM bit,
	HANTAVIRUS bit,
	PARVO_ANTIB int,
	OTHER_INFECT bit,
	OTHER_INFECT_SP varchar(50),
	UR_VOL_24H int,
	UR_VOL_24H_COND int,
	HAEMATURIA int,
	ALBUMINURIA int,
	DYS_ERYTH_URINE int,
	RED_CCASTS_URINE int,
	WBC_CASTS_URINE int,
	LEUC_URINE bit,
	NITRITE bit,
	BACT_URINE bit,
	GLUC_URINE bit,
	OSMOLARITY varchar(50),
	PROTEINURIA_DIP int,
	SEQ_NO int,
	ANTI_CLQ numeric(4,1)
);

CREATE TABLE tbl_MONOCLONAL (
	mID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (mID),
	mDesc varchar(20)
);

CREATE TABLE tbl_Pathology (
	pID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (pID),
	RADAR_NO int,
	BX_DATE datetime,
	NAT_TRANSP_KID int,
	LATERALITY_BX int,
	SAMPLE_LAB_NO varchar(20),
	PATHDIAG int,
	GLOM_TOTAL_NO int,
	GLOM_GLOB_SCL int,
	GLOM_SEQ_SCL int,
	GLOM_CELL_CRES int,
	GLOM_FIB_CRES int,
	GLOM_END_HYPER int,
	GLOM_FIN_NEC int,
	GLOM_ANY_OTH_FEAT varchar(50),
	TUB_ATROP_IF_EST int,
	TUB_ATROP_IF_MEAS numeric(3,1),
	TUB_OTHER_FEAT varchar(150),
	INTER_INFLAM_INFIL varchar(150),
	ART_ABNORMAL varchar(150),
	IMM_HIST_FIND varchar(150),
	ELECT_MSCOPE_FIND varchar(150),
	IMAGE_URL1 varchar(150),
	IMAGE_URL2 varchar(150),
	IMAGE_URL3 varchar(150),
	IMAGE_URL4 varchar(150),
	IMAGE_URL5 varchar(150),
	PATH_TXT text,
	SEQ_NO int
);

CREATE TABLE tbl_Patient_Users (
	pID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (pID),
	RADAR_NO int,
	pUserName varchar(50),
	pPassWord varbinary(50),
	pDOB datetime,
	pDateReg datetime
);

CREATE TABLE tbl_PD_MODALITY (
	pdID int AUTO_INCREMENT, PRIMARY KEY (pdID),
	pdType varchar(75)
);

CREATE TABLE tbl_PDial (
	pdID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (pdID),
	RADAR_NO int,
	DATE_START_PDIAL datetime
);
CREATE TABLE tbl_PHENOTYPES (
	pID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (pID),
	pDesc varchar(75)
);

CREATE TABLE tbl_Plasmaph (
	plID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (plID),
	RENAL_NO int,
	DATE_STARTED_PLASMAPH datetime,
	DUR_PLASMAPH int,
	NO_EXCH_PLASMAPH int
);

CREATE TABLE tbl_Relapse (
	relID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (relID),
	RADAR_NO int,
	DATE_ONSET_RELAP datetime,
	RELAP_TX_NAT bit,
	TRIG_VIRAL varchar(50),
	TRIG_IMMUN varchar(50),
	TRIG_OTHER varchar(50),
	RELAP_DRUG_1 varchar(50),
	RELAP_DRUG_2 varchar(50),
	RELAP_DRUG_3 varchar(50),
	REMISS_ACHIEVE int,
	DATE_REMISSION datetime,
	SEQ_NO int
);

CREATE TABLE tbl_Relative (
	rID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (rID),
	RELATIVE varchar(20)
);

CREATE TABLE tbl_RRT_HD (
	hID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (hID),
	RADAR_NO int,
	HD_TMT_MODALITY int,
	DATE_START_HDIAL datetime,
	DATE_STOP_HDIAL datetime
);

CREATE TABLE tbl_RRT_MODALITY (
	mID int AUTO_INCREMENT, PRIMARY KEY (mID),
	mType varchar(50),
	Group int
);

CREATE TABLE tbl_RRT_PD (
	pID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (pID),
	RADAR_NO int,
	PD_TMT_MODALITY int,
	DATE_START_PD datetime,
	DATE_STOP_PD datetime
);

CREATE TABLE tbl_RRT_PLASMA (
	plID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (plID),
	RADAR_NO int,
	PLASMAPH varchar(20),
	DATE_START_PLASMAPH datetime,
	DATE_STOP_PLASMAPH datetime,
	NO_EXCH_PLASMAPH varchar(10),
	DUR_PLASMAPH int,
	RESPONSE_TO_PLASMA int
);

CREATE TABLE tbl_RRT_PLASMA_LU (
	exID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (exID),
	exDesc varchar(50)
);

CREATE TABLE tbl_RRT_TREATMENT (
	tID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (tID),
	RADAR_NO int,
	MODALITY int,
	DATE_START datetime,
	DATE_STOP datetime,
	UNIT_CODE int,
	FIRST_FLAG bit
);

CREATE TABLE tbl_RT_Modality (
	mID int NOT NULL, PRIMARY KEY (mID),
	mDesc varchar(50)
);

CREATE TABLE tbl_Sex (
	sID int AUTO_INCREMENT, PRIMARY KEY (sID),
	sType varchar(14)
);

CREATE TABLE tbl_Status (
	sID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (sID),
	sDesc varchar(50),
	sAbbrev varchar(20)
);

CREATE TABLE tbl_Test (
	tID smallint NOT NULL AUTO_INCREMENT, PRIMARY KEY (tID),
	tOne bit,
	tTwo bit,
	tThree nchar(10)
);

CREATE TABLE tbl_Therapy (
	tID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (tID),
	RADAR_NO int,
	SIG_CHANGE_STATUS varchar(20),
	P_NSAID bit,
	NSAID bit,
	P_DIURETIC bit,
	DIURETIC bit,
	P_ANTI_HTN bit,
	ANTI_HTN bit,
	P_ACE_INHIB bit,
	ACE_INHIB bit,
	P_ARB_ANTAG bit,
	ARB_ANTAG bit,
	P_CA_CH_BLOCK bit,
	CA_CH_BLOCK bit,
	P_B_BLOCK bit,
	B_BLOCK bit,
	P_OTHER_ANTI_HTN bit,
	OTHER_ANTI_HTN bit,
	P_INSULIN bit,
	INSULIN bit,
	P_LIP_LOWER_AG bit,
	LIP_LOWER_AG bit,
	P_EPO bit,
	EPO bit,
	P_OTHER_DRUG1 varchar(50),
	OTHER_DRUG1 varchar(50),
	P_OTHER_DRUG2 varchar(50),
	OTHER_DRUG2 varchar(50),
	P_OTHER_DRUG3 varchar(50),
	OTHER_DRUG3 varchar(50),
	P_OTHER_DRUG4 varchar(50),
	OTHER_DRUG4 varchar(50),
	P_IMMUN_SUP bit,
	IMMUN_SUP bit,
	P_IMMUN_SUP_DRUG varchar(50),
	IMMUN_SUP_DRUG varchar(50),
	MONOCLONAL_YN bit,
	MONOCLONAL_NAME varchar(50),
	DATE_TREAT datetime,
	TMT_MODALITY varchar(50),
	SEQ_NO int
);

CREATE TABLE tbl_Transplant (
	trID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (trID),
	RADAR_NO int,
	DATE_TRANSPLANT datetime,
	TRANS_TYPE varchar(50),
	TRANSPLANT_COUNTER int,
	DATE_NEPHRECT datetime,
	TRANS_RECURR bit,
	DATE_RECURR_TXK datetime,
	DATE_TX_REJECT datetime,
	DATE_BX_TXK datetime
);

CREATE TABLE tbl_TRANSPLANT_MODALITY (
	trID int AUTO_INCREMENT, PRIMARY KEY (trID),
	trDesc varchar(75)
);

CREATE TABLE tbl_Transplant_Reject (
	recID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (recID),
	trID int NOT NULL,
	trRejectDate datetime,
	trBiopsyDate datetime,
	trFailureDate datetime
);

CREATE TABLE tbl_Users (
	uID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (uID),
	uSurname varchar(50),
	uForename varchar(50),
	uTitle varchar(50),
	uGMC varchar(50),
	uRole varchar(50),
	uEmail varchar(50),
	uPhone varchar(50),
	uCentre int,
	uDateJoin TIMESTAMP,
	uPass varbinary(50),
	uUserName varbinary(50)
);

CREATE TABLE rdr_hnf1b_misc (
  id int(11) unsigned NOT NULL auto_increment,
  radar_no bigint(20) NOT NULL,
  renalCysts int(11) default '0',
  singleKidney int(11) default '0',
  otherRenalMalformations int(11) default '0',
  otherRenalMalformationsDetails varchar(1000) default NULL,
  diabetes int(11) default '0',
  ageAtDiabetesDiagnosis int(11) default '0',
  gout int(11) default '0',
  ageAtGoutDiagnosis int(11) default '0',
  genitalMalformation int(11) default '0',
  genitalMalformationDetails varchar(1000) default NULL,
  PRIMARY KEY  (id)
);

