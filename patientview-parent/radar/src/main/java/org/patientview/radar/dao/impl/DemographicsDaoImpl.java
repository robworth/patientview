package org.patientview.radar.dao.impl;

import com.Ostermiller.util.RandPass;
import org.apache.commons.codec.digest.DigestUtils;
import org.patientview.model.Centre;
import org.patientview.model.Clinician;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.model.Patient;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.apache.commons.lang.StringUtils;
import org.patientview.radar.model.user.DemographicsUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DemographicsDaoImpl extends BaseDaoImpl implements DemographicsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemographicsDaoImpl.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_1 = "dd.MM.y";
    private static final String DATE_FORMAT_2 = "dd-MM-y";
    private static final String DATE_FORMAT_3 = "dd/MM/y";
    private static final String PATIENT_ENTERS_UNITCODE = "PATIENT";
    private static final String ADD_PATIENT_TASK = "Add patient to XML feed to PatientView";
    private static final String CONTACT_PATIENT_TASK = "Contact patient with PatientView password";
    private SimpleJdbcInsert demographicsInsert;
    private SimpleJdbcInsert joinRequestInsert;
    private UtilityDao utilityDao;
    private DiseaseGroupDao diseaseGroupDao;
    private GenericDiagnosisDao genericDiagnosisDao;
    private UserDao userDao;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        demographicsInsert = new SimpleJdbcInsert(dataSource).withTableName("patient")
                .usingGeneratedKeyColumns("id")
                .usingColumns(
                        "rrNo", "dateReg", "nhsno", "hospitalnumber", "uktNo", "surname",
                        "surnameAlias", "forename", "dateofbirth", "AGE", "SEX", "ethnicGp", "address1",
                        "address2", "address3", "address4", "POSTCODE", "radarConsentConfirmedByUserId",
                        "postcodeOld", "CONSENT", "dateBapnReg", "consNeph", "unitcode",
                        "STATUS", "emailAddress", "telephone1", "telephone2", "mobile", "rrtModality",
                        "genericDiagnosis", "dateOfGenericDiagnosis", "otherClinicianAndContactInfo", "comments",
                        "republicOfIrelandId", "isleOfManId", "channelIslandsId", "indiaId", "generic");

        joinRequestInsert = new SimpleJdbcInsert(dataSource).withTableName("pv_patientjoin_request")
                .usingGeneratedKeyColumns("id")
                .usingColumns(
                        "firstname", "lastname", "dateofbirth", "email", "unitcode", "nhsno",
                        "dateOfRequest", "isComplete", "taskTitle");
    }

    public void saveDemographics(final Patient patient) {
        // If we have an ID then update, otherwise insert new and set the ID
        if (patient.hasValidId()) {
            jdbcTemplate.update(
                    "UPDATE patient SET " +
                            "rrNo = ?, " +
                            "dateReg = ?, " +
                            "nhsno = ?, " +
                            "hospitalnumber = ?, " +
                            "uktNo = ?, " +
                            "surname = ?, " +
                            "surnameAlias = ?, " +
                            "forename = ?, " +
                            "dateofbirth = ?, " +
                            "AGE = ?, " +
                            "SEX = ?, " +
                            "ethnicGp = ?, " +
                            "address1 = ?, " +
                            "address2 = ?, " +
                            "address3 = ?, " +
                            "address4 = ?, " +
                            "POSTCODE = ?, " +
                            "postcodeOld = ?," +
                            "CONSENT = ?, " +
                            "dateBapnReg = ?, " +
                            "consNeph = ?, " +
                            "unitcode = ?, " +
//                            "RENAL_UNIT_2 = ?, " +
                            "STATUS = ?, " +
//                            "RDG = ?, " +
                            "emailAddress = ?, " +
                            "telephone1 = ?, " +
                            "telephone2 = ?, " +
                            "mobile = ?, " +
                            "rrtModality = ?, " +
                            "genericDiagnosis = ?, " +
                            "dateOfGenericDiagnosis = ?, " +
                            "otherClinicianAndContactInfo = ?, " +
                            "comments = ?, " +
                            "republicOfIrelandId = ?, " +
                            "isleOfManId = ?, " +
                            "channelIslandsId = ?, " +
                            "indiaId = ?, " +
                            "radarConsentConfirmedByUserId = ?, " +
                            "generic = ? " +
                            " WHERE radarNo = ?",
                    patient.getRrNo(),
                    patient.getDateReg(),
                    patient.getNhsno(),
                    patient.getHospitalnumber(),
                    patient.getUktNo(),
                    patient.getSurname(),
                    patient.getSurnameAlias(),
                    patient.getForename(),
                    new SimpleDateFormat(DATE_FORMAT).format(patient.getDob()),
                    patient.getAge(),
                    patient.getSexModel() != null ? patient.getSexModel().getType() : null,
                    patient.getEthnicity() != null ? patient.getEthnicity().getCode() : null,
                    patient.getAddress1(),
                    patient.getAddress2(),
                    patient.getAddress3(),
                    patient.getAddress4(),
                    patient.getPostcode(),
                    patient.getPostcodeOld(),
                    patient.isConsent(),
                    patient.getDateReg(),
                    patient.getClinician() != null ? patient.getClinician().getId() : null,
                    patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null,
//                    patient.getRenalUnitAuthorised() != null ?
//                            patient.getRenalUnitAuthorised().getId() : null,
                    patient.getStatusModel() != null ? patient.getStatusModel().getId() : null,
//                    patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null,
                    patient.getEmailAddress(),
                    patient.getTelephone1(),
                    patient.getTelephone2(),
                    patient.getMobile(),
                    patient.getRrtModalityEunm() != null ? patient.getRrtModalityEunm().getId() : null,
                    patient.getGenericDiagnosisModel() != null ? patient.getGenericDiagnosisModel().getId() : null,
                    patient.getDateOfGenericDiagnosis(),
                    patient.getOtherClinicianAndContactInfo(),
                    patient.getComments(),
                    patient.getRepublicOfIrelandId(),
                    patient.getIsleOfManId(),
                    patient.getChannelIslandsId(),
                    patient.getIndiaId(),
                    patient.getRadarConsentConfirmedByUserId(),
                    patient.isGeneric(),
                    patient.getId());
        } else {
            Number id = demographicsInsert.executeAndReturnKey(new HashMap<String, Object>() {
                {
                    put("rrNo", patient.getRrNo());
                    put("dateReg", patient.getDateReg());
                    put("nhsno", patient.getNhsno());
                    put("hospitalnumber", patient.getHospitalnumber());
                    put("uktNo", patient.getUktNo());
                    put("surname", patient.getSurname());
                    put("surnameAlias", patient.getSurnameAlias());
                    put("forename", patient.getForename());
                    put("dateofbirth", patient.getDob() != null ?
                            new SimpleDateFormat(DATE_FORMAT).format(
                                    patient.getDob()) : null);
                    put("AGE", patient.getAge());
                    put("SEX", patient.getSexModel() != null ? patient.getSexModel().getType() : null);
                    put("ethnicGp",
                            patient.getEthnicity() != null ? patient.getEthnicity().getCode() : null);
                    put("address1", patient.getAddress1());
                    put("address2", patient.getAddress2());
                    put("address3", patient.getAddress3());
                    put("address4", patient.getAddress4());
                    put("POSTCODE", patient.getPostcode());
                    put("postcodeOld", patient.getPostcodeOld());
                    put("CONSENT", patient.isConsent());
                    put("dateBapnReg", null); // Todo: Fix
                    put("consNeph", patient.getClinician() != null ? patient.getClinician().getId()
                            : null);
                    put("unitcode", patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null);
//                    put("RENAL_UNIT_2", patient.getRenalUnitAuthorised() != null ?
//                            patient.getRenalUnitAuthorised().getId() : null);
                    put("STATUS", patient.getStatusModel() != null ? patient.getStatusModel().getId() : null);
//                    put("RDG", patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null);
                    put("emailAddress", patient.getEmailAddress());
                    put("telephone1", patient.getTelephone1());
                    put("telephone2", patient.getTelephone2());
                    put("mobile", patient.getMobile());
                    put("rrtModality", patient.getRrtModalityEunm() != null ? patient.getRrtModalityEunm().getId()
                            : null);
                    put("genericDiagnosis", patient.getGenericDiagnosisModel() != null ?
                            patient.getGenericDiagnosisModel().getId() : null);
                    put("dateOfGenericDiagnosis", patient.getDateOfGenericDiagnosis());
                    put("otherClinicianAndContactInfo", patient.getOtherClinicianAndContactInfo());
                    put("comments", patient.getComments());
                    put("republicOfIrelandId", patient.getRepublicOfIrelandId());
                    put("isleOfManId", patient.getIsleOfManId());
                    put("channelIslandsId", patient.getChannelIslandsId());
                    put("indiaId", patient.getIndiaId());
                    put("generic", patient.isGeneric());
                    put("radarConsentConfirmedByUserId", patient.getRadarConsentConfirmedByUserId());
                }
            });
            patient.setId(id.longValue());

            jdbcTemplate.update("UPDATE patient set radarNo = ? WHERE id = ? ", id.longValue(), id.longValue());

            try {
                // insert data into user table
                createPVUser(patient);
                // insert two data into pv_patientjoin_request table
                createTask(patient, ADD_PATIENT_TASK);
                createTask(patient, CONTACT_PATIENT_TASK);
            } catch (Exception e) {
                LOGGER.error("Unable to create usermapping using {}", patient.getNhsno());
            }
        }
    }

    private void createTask(Patient patient, String taskTile) {
        Map<String, Object> joinRequestMap = new HashMap<String, Object>();
        joinRequestMap.put("firstname", patient.getForename());
        joinRequestMap.put("lastname", patient.getSurname());
        joinRequestMap.put("dateofbirth", new SimpleDateFormat(DATE_FORMAT).format(
                patient.getDob()));
        joinRequestMap.put("email", patient.getEmailAddress() != null ? patient.getEmailAddress() : "");
        joinRequestMap.put("unitcode", patient.getRenalUnit().getUnitCode());
        joinRequestMap.put("nhsno", patient.getNhsno());
        joinRequestMap.put("dateOfRequest", new Date());
        joinRequestMap.put("isComplete", false);
        joinRequestMap.put("taskTitle", taskTile);

        joinRequestInsert.executeAndReturnKey(joinRequestMap);

    }

    private void createPVUser(Patient patient) {
        String password = new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(8);
        password = DigestUtils.sha256Hex(password);
        String gppassword = new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(8);
        gppassword = DigestUtils.sha256Hex(gppassword);

        try {
            userDao.createPVUser(patient.getForename() + patient.getSurname(),
                    password,
                    patient.getForename() + patient.getSurname(),
                    patient.getEmailAddress());
            // patient enters usermapping
            userDao.createUserMappingInPatientView(patient.getForename() + patient.getSurname(),
                    patient.getNhsno(), PATIENT_ENTERS_UNITCODE);
            // renal_unit usermapping
            userDao.createUserMappingInPatientView(patient.getForename() + patient.getSurname(),
                    patient.getNhsno(), patient.getRenalUnit().getUnitCode());
            // unitcode usermapping
            userDao.createUserMappingInPatientView(patient.getForename() + patient.getSurname(),
                    patient.getNhsno(), patient.getDiseaseGroup().getId());

            // GP user
            userDao.createPVUser(patient.getForename() + patient.getSurname() + "-GP",
                    gppassword,
                    patient.getForename() + patient.getSurname() + "-GP",
                    patient.getEmailAddress());
            // GP usermapping
            userDao.createUserMappingInPatientView(patient.getForename() + patient.getSurname() + "-GP",
                    patient.getNhsno(), patient.getRenalUnit().getUnitCode()); // TODO unitcode

        } catch (Exception e) {
            LOGGER.error("Unable to create user and usermapping using {}", patient.getNhsno());
        }

    }

    public Patient getDemographicsByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM patient WHERE radarNo = ?",
                    new Object[]{radarNumber}, new DemographicsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No demographic record found for radar number {}", radarNumber);
            return null;
        }
    }

    public Patient getDemographicsByNhsNoAndUnitCode(String nhsNo, String unitCode) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM patient WHERE nhsno = ? AND unitcode = ? ",
                    new Object[]{nhsNo, unitCode}, new DemographicsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No demographic record found for radar number {}", nhsNo);
            return null;
        }
    }

    public List<Patient> getDemographicsByRenalUnit(Centre centre) {
        String unitCode = centre.getUnitCode();
        return jdbcTemplate.query("SELECT pa.* " +
                "FROM " +
                "     (" +
                "       SELECT patient.*, usermapping.unitcode as ucode " +
                "         FROM patient LEFT OUTER JOIN usermapping ON patient.nhsno = usermapping.nhsno   " +
                "        WHERE usermapping.unitcode = ? " +
                "          AND usermapping.username NOT LIKE '%-GP%' " +
                "     ) AS pa " +
                "WHERE pa.unitcode = ? OR pa.ucode = ?  ",
                new Object[]{unitCode, unitCode, unitCode}, new DemographicsRowMapper());
    }

    public List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new DemographicsFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add("SELECT " +
                "   patient.*, " +
                "   tbl_Consultants.cFNAME, " +
                "   tbl_Consultants.cSNAME, " +
                "   unit.shortname, " +
                "   tbl_DiagCode.dcAbbr " +
                "FROM " +
                "   tbl_DiagCode " +
                "INNER JOIN " +
                "   tbl_Diagnosis " +
                "ON " +
                "   tbl_DiagCode.dcID = tbl_Diagnosis.DIAG " +
                "RIGHT OUTER JOIN " +
                "   patient " +
                "ON " +
                "   tbl_Diagnosis.RADAR_NO = patient.radarNo " +
                "LEFT OUTER JOIN " +
                "   unit " +
                "INNER JOIN " +
                "   tbl_Consultants " +
                "ON " +
                "   unit.id = tbl_Consultants.cCentre " +
                "ON " +
                "   patient.consNeph = tbl_Consultants.cID");

        // if there are search queries then build the where
        if (filter.hasSearchCriteria()) {
            sqlQueries.add(buildWhereQuery(filter.getSearchFields(), true, params));
        }

        // if the filter has a sort then order by it
        if (filter.hasSortFilter()) {
            sqlQueries.add(buildOrderQuery(filter.getSortField(), filter.isReverse()));
        }

        // if a range has been set limit the results
        sqlQueries.add(buildLimitQuery(page, numberPerPage, params));

        // combine the statement and return result
        return jdbcTemplate.query(StringUtils.join(sqlQueries.toArray(), " "), params.toArray(),
                new DemographicsRowMapper());
    }

    public Sex getSex(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Sex WHERE sID = ?", new Object[]{id},
                    new SexRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No sex found for ID {}", id);
            return null;
        }
    }

    public Sex getSex(String sex) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Sex WHERE sType = ?", new Object[]{sex},
                    new SexRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No sex found for ID {}", sex);
            return null;
        }
    }

    public List<Sex> getSexes() {
        return jdbcTemplate.query("SELECT * FROM tbl_Sex", new SexRowMapper());
    }

    public Status getStatus(long id) {
        try {
            return jdbcTemplate
                    .queryForObject("SELECT * FROM tbl_Status WHERE sID = ?", new Object[]{id}, new StatusRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No status found for ID {}", id);
            return null;
        }
    }

    public List<Status> getStatuses() {
        return jdbcTemplate.query("SELECT * FROM tbl_Status", new StatusRowMapper());
    }


    public class DemographicsRowMapper implements RowMapper<Patient> {
        public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct object and set radar number
            Patient patient = new Patient();
            Long radarId = resultSet.getLong("radarNo");
            patient.setId(radarId);
            patient.setDateReg(resultSet.getDate("dateReg"));

            // Renal registry number
            patient.setRrNo(resultSet.getString("rrNo"));

            // UK transplant number
            patient.setUktNo(resultSet.getString("uktNo"));

            patient.setNhsno(resultSet.getString("nhsno"));
            patient.setHospitalnumber(resultSet.getString("hospitalnumber"));
            patient.setSurname(resultSet.getString("surname"));
            patient.setSurnameAlias(resultSet.getString("surnameAlias"));
            patient.setForename(resultSet.getString("forename"));

            // Date needs to be decrypted to string, then parsed
            String dateOfBirthString = resultSet.getString("dateofbirth");
            if (StringUtils.isNotBlank(dateOfBirthString)) {
                Date dateOfBirth = null;

                // It seems that the encrypted strings in the DB have different date formats, nice.
                for (String dateFormat : new String[]{DATE_FORMAT, DATE_FORMAT_1, DATE_FORMAT_2, DATE_FORMAT_3}) {
                    try {
                        dateOfBirth = new SimpleDateFormat(dateFormat).parse(dateOfBirthString);
                    } catch (ParseException e) {
                        LOGGER.debug("Could not parse date of birth {}", dateOfBirthString);
                    }
                }

                // If after trying those formats we don't have anything then log as error
                if (dateOfBirth != null) {
                    patient.setDob(dateOfBirth);
                } else {
                    LOGGER.error("Could not parse date of birth from any format for dob {}",
                            dateOfBirthString);
                    String a = "";
                }
            }

            // Addresses
            patient.setAddress1(resultSet.getString("address1"));
            patient.setAddress2(resultSet.getString("address2"));
            patient.setAddress3(resultSet.getString("address3"));
            patient.setAddress4(resultSet.getString("address4"));
            patient.setPostcode(resultSet.getString("POSTCODE"));
            patient.setPostcodeOld(resultSet.getString("postcodeOld"));

            // Set sex
            patient.setSexModel(getSex(resultSet.getString("SEX")));

            // Try and get ethnicity
            String ethnicityCode = resultSet.getString("ethnicGp");
            if (StringUtils.isNotBlank(ethnicityCode)) {
                patient.setEthnicity(utilityDao.getEthnicityByCode(ethnicityCode));
            }

            patient.setConsent(resultSet.getBoolean("CONSENT"));

            // Set the centre if we have an ID
            String nhsno = resultSet.getString("nhsno");
            if (nhsno != null) {
                patient.setRenalUnit(utilityDao.getRenalUnitCentre(nhsno));
            }

            // Set status
            long statusId = resultSet.getLong("STATUS");
            if (statusId > 0) {
                patient.setStatusModel(getStatus(statusId));
            }

            Long consultantId = resultSet.getLong("consNeph");
            if (!resultSet.wasNull()) {

                try {
                    Clinician clinician = utilityDao.getClinician(consultantId);
                    if (clinician != null) {
                        patient.setClinician(clinician);
                    }
                } catch (Exception e) {
                    LOGGER.error("Unable to access consultant using consultantId {}", consultantId);
                    LOGGER.debug(e.getMessage(), e);
                }


            }

//            Long renalUnitAuthorisedId = resultSet.getLong("RENAL_UNIT_2");
//            if (!resultSet.wasNull()) {
//                Centre centre = utilityDao.getCentre(renalUnitAuthorisedId);
//                patient.setRenalUnitAuthorised(centre);
//            }
//
            // set generic fields
            String diseaseGroupId = resultSet.getString("unitcode"); //RDG,
            if (diseaseGroupId != null) {
                patient.setDiseaseGroup(diseaseGroupDao.getById(diseaseGroupId));
            }
            // todo fix the renal_unit_2 and RDG
            patient.setEmailAddress(resultSet.getString("emailAddress")); //emailAddress,
            patient.setTelephone1(resultSet.getString("telephone1")); //phone1,
            patient.setTelephone2(resultSet.getString("telephone2")); //phone2,
            patient.setMobile(resultSet.getString("mobile")); //mobile,
            Integer rrtModalityId = getIntegerWithNullCheck("rrtModality", resultSet); //RRT_modality,
            if (rrtModalityId != null) {
                patient.setRrtModalityEunm(getEnumValue(Patient.RRTModality.class, rrtModalityId));
            }

            String genericDiagnosisId = resultSet.getString("genericDiagnosis");
            if (StringUtils.isNotBlank(genericDiagnosisId) && StringUtils.isNotBlank(diseaseGroupId)) {
                patient.setGenericDiagnosisModel(genericDiagnosisDao.get(genericDiagnosisId, diseaseGroupId));
            }

            patient.setDateOfGenericDiagnosis(resultSet.getDate("dateOfGenericDiagnosis"));
            if (patient.getDateOfGenericDiagnosis() == null) {
                patient.setDiagnosisDateSelect(true);
            } else {
                patient.setDiagnosisDateSelect(false);
            }
            patient.setOtherClinicianAndContactInfo(resultSet.getString("otherClinicianAndContactInfo"));
            patient.setComments(resultSet.getString("comments")); //comments,
            patient.setRepublicOfIrelandId(resultSet.getString("republicOfIrelandId"));
            patient.setIsleOfManId(resultSet.getString("isleOfManId"));
            patient.setChannelIslandsId(resultSet.getString("channelIslandsId"));
            patient.setIndiaId(resultSet.getString("indiaId"));
            patient.setGeneric(resultSet.getBoolean("generic"));
            patient.setRadarConsentConfirmedByUserId(resultSet.getLong("radarConsentConfirmedByUserId"));

            return patient;
        }
    }

    private class SexRowMapper implements RowMapper<Sex> {
        public Sex mapRow(ResultSet resultSet, int i) throws SQLException {
            Sex sex = new Sex();
            sex.setId(resultSet.getLong("sID"));
            sex.setType(resultSet.getString("sType"));
            return sex;
        }
    }

    private class StatusRowMapper implements RowMapper<Status> {
        public Status mapRow(ResultSet resultSet, int i) throws SQLException {
            // Contruct new status object
            Status status = new Status();
            status.setId(resultSet.getLong("sID"));
            status.setDescription(resultSet.getString("sDesc"));
            status.setAbbreviation(resultSet.getString("sAbbrev"));
            return status;
        }
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }

    public SimpleJdbcInsert getDemographicsInsert() {
        return demographicsInsert;
    }

    public void setDemographicsInsert(SimpleJdbcInsert demographicsInsert) {
        this.demographicsInsert = demographicsInsert;
    }

    public void setDiseaseGroupDao(DiseaseGroupDao diseaseGroupDao) {
        this.diseaseGroupDao = diseaseGroupDao;
    }

    public void setGenericDiagnosisDao(GenericDiagnosisDaoImpl genericDiagnosisDao) {
        this.genericDiagnosisDao = genericDiagnosisDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode) {
        String sql = "SELECT "
                + "user.email, user.emailverified, user.accountlocked, "
                + "emailverification.lastverificationdate, user.lastlogon, pv_user_log.lastdatadate "
                + "FROM "
                + "( SELECT DISTINCT username, nhsno FROM usermapping "
                + "  WHERE nhsno = ? AND username NOT LIKE '%_GP' ) AS un "
                + "LEFT JOIN pv_user_log ON un.nhsno = pv_user_log.nhsno, "
                + "user LEFT JOIN emailverification ON user.username = emailverification.username "
                + "WHERE "
                + "user.username = un.username ";
        List<Object> params = new ArrayList<Object>();
        params.add(nhsno);
        try {
            return jdbcTemplate.queryForObject(sql, params.toArray(), new DemographicsUserDetailMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No DemographicsUserDetail found for nhsno:"+nhsno);
            return new DemographicsUserDetail();
        }
    }

    public List<String> getTasks(String nhsNo) {
        return jdbcTemplate.queryForList("SELECT j.taskTitle FROM patient p, pv_patientjoin_request j " +
                "WHERE p.nhsno = j.nhsno " +
                "AND p.nhsno = ? " +
                "ORDER BY j.taskTitle " , new Object[]{nhsNo}, String.class);
    }

    private class DemographicsUserDetailMapper implements RowMapper<DemographicsUserDetail> {

        public DemographicsUserDetail mapRow(ResultSet resultSet, int i) throws SQLException {
            DemographicsUserDetail patient = new DemographicsUserDetail();
            patient.setLastverificationdate(resultSet.getDate("lastverificationdate"));
            patient.setEmail(resultSet.getString("email"));
            patient.setLastlogon(resultSet.getDate("lastlogon"));
            patient.setAccountlocked(resultSet.getBoolean("accountlocked"));
            patient.setLastdatadate(resultSet.getDate("lastdatadate"));
            return patient;
        }
    }
}