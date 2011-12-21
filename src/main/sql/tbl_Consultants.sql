CREATE TABLE rpv.dbo.tbl_Consultants (
	cID int NOT NULL,
	cSNAME nvarchar(50),
	cFNAME nvarchar(50),
	cCentre int
);

CREATE TABLE rpv.dbo.tbl_Country (
	cID int NOT NULL,
	cName nvarchar(50)
);

CREATE TABLE rpv.dbo.tbl_Demographics (
	RADAR_NO int NOT NULL,
	RR_NO nvarchar(10),
	DATE_REG smalldatetime,
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
	ETHNIC_GP nvarchar(6),
	ADD1 varbinary(50),
	ADD2 varbinary(50),
	ADD3 varbinary(50),
	ADD4 varbinary(50),
	POSTCODE varbinary(50),
	POSTCODE_OLD varbinary(50),
	CONSENT bit,
	DATE_BAPN_REG smalldatetime,
	CONS_NEPH nvarchar(6),
	RENAL_UNIT int,
	RENAL_UNIT_2 int,
	STATUS int
);

CREATE TABLE rpv.dbo.tbl_DiagCode (
	dcID int NOT NULL,
	dcDesc nvarchar(70),
	dcAbbr nvarchar(15)
);

CREATE TABLE rpv.dbo.tbl_Diagnosis (
	dID int NOT NULL,
	RADAR_NO int,
	DATE_DIAG smalldatetime,
	DIAG int,
	DIAG_TXT nvarchar(100),
	AGE_AT_DIAG decimal(4,1),
	HEIGHT_FIRST_VISIT numeric(4,1),
	BX_PROVEN_DIAG nvarchar(1),
	PREPUB_DIAG bit,
	CLIN_PRES int,
	CLIN_PRES_B int,
	GENE_MUT nvarchar(50),
	GENE_MUT_TEXT nvarchar(100),
	KARYOTYPE nvarchar(50),
	KARYOTYPE_OTHER nvarchar(100),
	DATE_ONSET_RENALDIS smalldatetime,
	CONSANGUINITY int,
	FAM_HIST int,
	REL1 nvarchar(20),
	REL1_RADAR int,
	REL2 nvarchar(20),
	REL2_RADAR int,
	REL3 nvarchar(20),
	REL3_RADAR int,
	REL4 nvarchar(20),
	REL4_RADAR int,
	REL5 nvarchar(20),
	REL5_RADAR int,
	REL6 nvarchar(20),
	REL6_RADAR int,
	SIG_DIAG1 nvarchar(50),
	SIG_DIAG2 nvarchar(50),
	STEROID_RESIST int,
	DATE_ESRF smalldatetime,
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

CREATE TABLE rpv.dbo.tbl_DiseaseData (
	dID int NOT NULL,
	dText text
);

CREATE TABLE rpv.dbo.tbl_Ethnicity (
	eID int NOT NULL,
	eName nvarchar(50),
	eCode nvarchar(50)
);

CREATE TABLE rpv.dbo.tbl_GeneMutation (
	gmID int NOT NULL,
	GENE_MUTATION nvarchar(30)
);

CREATE TABLE rpv.dbo.tbl_HD_MODALITY (
	hdID int,
	hdType nvarchar(75)
);

CREATE TABLE rpv.dbo.tbl_HDial (
	hdID int NOT NULL,
	RADAR_NO int,
	DATE_START_HDIAL datetime
);

CREATE TABLE rpv.dbo.tbl_Hospitalisation (
	hID int NOT NULL,
	RADAR_NO int,
	DATE_ADMIT smalldatetime,
	DATE_DISCHARGE smalldatetime,
	REASON_ADMIT nvarchar(250),
	COMMENT text
);

CREATE TABLE rpv.dbo.tbl_ImmunoSupp (
	imID int NOT NULL,
	imDesc nvarchar(50),
	"Group" int
);

CREATE TABLE rpv.dbo.tbl_IMMUNSUP_TREATMENT (
	tID int NOT NULL,
	RADAR_NO int,
	IMMUNSUP_DRUG_STARTDATE datetime,
	IMMUNSUP_DRUG_ENDDATE datetime,
	IMMUNSUP_DRUG int,
	CYCLOPHOS_TOT_DOSE decimal(5,3),
	FIRST_FLAG bit
);

CREATE TABLE rpv.dbo.tbl_Karyotype (
	kID int,
	KARYOTYPE nvarchar(50)
);

CREATE TABLE rpv.dbo.tbl_LabData (
	labID int NOT NULL,
	RADAR_NO int,
	DATE_LAB_RES datetime,
	CREAT_SER int,
	PROTEIN int,
	ALBUMIN int,
	UREA_SER numeric(3,1),
	SODIUM int,
	POTASSIUM numeric(2,1),
	PHOS numeric(3,2),
	PROT_CREAT_RAT numeric(5,1),
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
	DNA_ANTIB nvarchar(50),
	DNA_ANTI_DS int,
	CRYOGLOB nvarchar(50),
	ANTI_GBM nvarchar(50),
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
	OTHER_INFECT_SP nvarchar(50),
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
	OSMOLARITY nvarchar(50),
	PROTEINURIA_DIP int,
	SEQ_NO int,
	ANTI_CLQ numeric(4,1)
);

CREATE TABLE rpv.dbo.tbl_MONOCLONAL (
	mID int NOT NULL,
	mDesc nvarchar(20)
);

CREATE TABLE rpv.dbo.tbl_Pathology (
	pID int NOT NULL,
	RADAR_NO int,
	BX_DATE smalldatetime,
	NAT_TRANSP_KID int,
	LATERALITY_BX int,
	SAMPLE_LAB_NO nvarchar(20),
	PATHDIAG int,
	GLOM_TOTAL_NO int,
	GLOM_GLOB_SCL int,
	GLOM_SEQ_SCL int,
	GLOM_CELL_CRES int,
	GLOM_FIB_CRES int,
	GLOM_END_HYPER int,
	GLOM_FIN_NEC int,
	GLOM_ANY_OTH_FEAT nvarchar(50),
	TUB_ATROP_IF_EST int,
	TUB_ATROP_IF_MEAS numeric(3,1),
	TUB_OTHER_FEAT nvarchar(150),
	INTER_INFLAM_INFIL nvarchar(150),
	ART_ABNORMAL nvarchar(150),
	IMM_HIST_FIND nvarchar(150),
	ELECT_MSCOPE_FIND nvarchar(150),
	IMAGE_URL1 nvarchar(150),
	IMAGE_URL2 nvarchar(150),
	IMAGE_URL3 nvarchar(150),
	IMAGE_URL4 nvarchar(150),
	IMAGE_URL5 nvarchar(150),
	PATH_TXT text,
	SEQ_NO int
);

CREATE TABLE rpv.dbo.tbl_Patient_Users (
	pID int NOT NULL,
	RADAR_NO int,
	pUserName nvarchar(50),
	pPassWord varbinary(50),
	pDOB datetime,
	pDateReg datetime
);

CREATE TABLE rpv.dbo.tbl_PD_MODALITY (
	pdID int,
	pdType nvarchar(75)
);

CREATE TABLE rpv.dbo.tbl_PDial (
	pdID int NOT NULL,
	RADAR_NO int,
	DATE_START_PDIAL datetime
);
CREATE TABLE rpv.dbo.tbl_PHENOTYPES (
	pID int NOT NULL,
	pDesc nvarchar(75)
);

CREATE TABLE rpv.dbo.tbl_Plasmaph (
	plID int NOT NULL,
	RENAL_NO int,
	DATE_STARTED_PLASMAPH datetime,
	DUR_PLASMAPH int,
	NO_EXCH_PLASMAPH int
);

CREATE TABLE rpv.dbo.tbl_Relapse (
	relID int NOT NULL,
	RADAR_NO int,
	DATE_ONSET_RELAP datetime,
	RELAP_TX_NAT bit,
	TRIG_VIRAL nvarchar(50),
	TRIG_IMMUN nvarchar(50),
	TRIG_OTHER nvarchar(50),
	RELAP_DRUG_1 nvarchar(50),
	RELAP_DRUG_2 nvarchar(50),
	RELAP_DRUG_3 nvarchar(50),
	REMISS_ACHIEVE int,
	DATE_REMISSION datetime,
	SEQ_NO int
);

CREATE TABLE rpv.dbo.tbl_Relative (
	rID int NOT NULL,
	RELATIVE nvarchar(20)
);

CREATE TABLE rpv.dbo.tbl_RRT_HD (
	hID int NOT NULL,
	RADAR_NO int,
	HD_TMT_MODALITY int,
	DATE_START_HDIAL smalldatetime,
	DATE_STOP_HDIAL smalldatetime
);

CREATE TABLE rpv.dbo.tbl_RRT_MODALITY (
	mID int,
	mType nvarchar(50),
	"Group" int
);

CREATE TABLE rpv.dbo.tbl_RRT_PD (
	pID int NOT NULL,
	RADAR_NO int,
	PD_TMT_MODALITY int,
	DATE_START_PD smalldatetime,
	DATE_STOP_PD smalldatetime
);

CREATE TABLE rpv.dbo.tbl_RRT_PLASMA (
	plID int NOT NULL,
	RADAR_NO int,
	PLASMAPH nvarchar(20),
	DATE_START_PLASMAPH smalldatetime,
	DATE_STOP_PLASMAPH smalldatetime,
	NO_EXCH_PLASMAPH nvarchar(10),
	DUR_PLASMAPH int,
	RESPONSE_TO_PLASMA int
);

CREATE TABLE rpv.dbo.tbl_RRT_PLASMA_LU (
	exID int NOT NULL,
	exDesc nvarchar(50)
);

CREATE TABLE rpv.dbo.tbl_RRT_TREATMENT (
	tID int NOT NULL,
	RADAR_NO int,
	MODALITY int,
	DATE_START smalldatetime,
	DATE_STOP smalldatetime,
	UNIT_CODE int,
	FIRST_FLAG bit
);

CREATE TABLE rpv.dbo.tbl_RT_Modality (
	mID int NOT NULL,
	mDesc nvarchar(50)
);

CREATE TABLE rpv.dbo.tbl_Sex (
	sID int,
	sType nvarchar(14)
);

CREATE TABLE rpv.dbo.tbl_Status (
	sID int NOT NULL,
	sDesc nvarchar(50),
	sAbbrev nvarchar(20)
);

CREATE TABLE rpv.dbo.tbl_Test (
	tID smallint NOT NULL,
	tOne bit,
	tTwo bit,
	tThree nchar(10)
);

CREATE TABLE rpv.dbo.tbl_Therapy (
	tID int NOT NULL,
	RADAR_NO int,
	SIG_CHANGE_STATUS nvarchar(20),
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
	P_OTHER_DRUG1 nvarchar(50),
	OTHER_DRUG1 nvarchar(50),
	P_OTHER_DRUG2 nvarchar(50),
	OTHER_DRUG2 nvarchar(50),
	P_OTHER_DRUG3 nvarchar(50),
	OTHER_DRUG3 nvarchar(50),
	P_OTHER_DRUG4 nvarchar(50),
	OTHER_DRUG4 nvarchar(50),
	P_IMMUN_SUP bit,
	IMMUN_SUP bit,
	P_IMMUN_SUP_DRUG nvarchar(50),
	IMMUN_SUP_DRUG nvarchar(50),
	MONOCLONAL_YN bit,
	MONOCLONAL_NAME nvarchar(50),
	DATE_TREAT datetime,
	TMT_MODALITY nvarchar(50),
	SEQ_NO int
);

CREATE TABLE rpv.dbo.tbl_Transplant (
	trID int NOT NULL,
	RADAR_NO int,
	DATE_TRANSPLANT datetime,
	TRANS_TYPE nvarchar(50),
	TRANSPLANT_COUNTER int,
	DATE_NEPHRECT datetime,
	TRANS_RECURR bit,
	DATE_RECURR_TXK datetime,
	DATE_TX_REJECT datetime,
	DATE_BX_TXK datetime
);

CREATE TABLE rpv.dbo.tbl_TRANSPLANT_MODALITY (
	trID int,
	trDesc nvarchar(75)
);

CREATE TABLE rpv.dbo.tbl_Transplant_Reject (
	recID int NOT NULL,
	trID int NOT NULL,
	trRejectDate smalldatetime,
	trBiopsyDate smalldatetime,
	trFailureDate smalldatetime
);

CREATE TABLE rpv.dbo.tbl_Users (
	uID int NOT NULL,
	uSurname nvarchar(50),
	uForename nvarchar(50),
	uTitle nvarchar(50),
	uGMC nvarchar(50),
	uRole nvarchar(50),
	uEmail nvarchar(50),
	uPhone nvarchar(50),
	uCentre int,
	uDateJoin smalldatetime,
	uPass varbinary(50),
	uUserName varbinary(50)
);

