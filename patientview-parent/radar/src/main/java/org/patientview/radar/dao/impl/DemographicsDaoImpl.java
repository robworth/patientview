package org.patientview.radar.dao.impl;

import org.patientview.model.Centre;
import org.patientview.model.Clinician;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.model.enums.NhsNumberType;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.model.Patient;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DemographicsDaoImpl extends BaseDaoImpl implements DemographicsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemographicsDaoImpl.class);

    private static final String DATE_FORMAT = "dd.MM.y";
    private static final String DATE_FORMAT_2 = "dd-MM-y";
    private static final String DATE_FORMAT_3 = "dd/MM/y";
    private SimpleJdbcInsert demographicsInsert;
    private UtilityDao utilityDao;
    private DiseaseGroupDao diseaseGroupDao;
    private GenericDiagnosisDao genericDiagnosisDao;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        demographicsInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_Demographics")
                .usingGeneratedKeyColumns("RADAR_NO")
                .usingColumns(
                        "RR_NO", "DATE_REG", "NHS_NO", "NHS_NO_TYPE", "HOSP_NO", "UKT_NO", "SNAME", "SNAME_ALIAS",
                        "FNAME", "DOB", "AGE", "SEX", "ETHNIC_GP", "ADD1", "ADD2", "ADD3", "ADD4", "POSTCODE",
                        "POSTCODE_OLD", "CONSENT", "DATE_BAPN_REG", "CONS_NEPH", "RENAL_UNIT", "RENAL_UNIT_2",
                        "STATUS", "RDG", "emailAddress", "phone1", "phone2", "mobile", "RRT_modality",
                        "genericDiagnosis", "dateOfGenericDiagnosis", "otherClinicianAndContactInfo", "comments",
                        "republicOfIrelandId", "isleOfManId", "channelIslandsId", "indiaId", "generic");
    }

    public void saveDemographics(final Patient patient) {
        // If we have an ID then update, otherwise insert new and set the ID
        if (patient.hasValidId()) {
            jdbcTemplate.update(
                    "UPDATE tbl_Demographics SET " +
                            "RR_NO = ?, " +
                            "DATE_REG = ?, " +
                            "NHS_NO = ?, " +
                            "NHS_NO_TYPE = ?, " +
                            "HOSP_NO = ?, " +
                            "UKT_NO = ?, " +
                            "SNAME = ?, " +
                            "SNAME_ALIAS = ?, " +
                            "FNAME = ?, " +
                            "DOB = ?, " +
                            "AGE = ?, " +
                            "SEX = ?, " +
                            "ETHNIC_GP = ?, " +
                            "ADD1 = ?, " +
                            "ADD2 = ?, " +
                            "ADD3 = ?, " +
                            "ADD4 = ?, " +
                            "POSTCODE = ?, " +
                            "POSTCODE_OLD = ?," +
                            "CONSENT = ?, " +
                            "DATE_BAPN_REG = ?, " +
                            "CONS_NEPH = ?, " +
                            "RENAL_UNIT = ?, " +
                            "RENAL_UNIT_2 = ?, " +
                            "STATUS = ?, " +
                            "RDG = ?, " +
                            "emailAddress = ?, " +
                            "phone1 = ?, " +
                            "phone2 = ?, " +
                            "mobile = ?, " +
                            "RRT_modality = ?, " +
                            "genericDiagnosis = ?, " +
                            "dateOfGenericDiagnosis = ?, " +
                            "otherClinicianAndContactInfo = ?, " +
                            "comments = ?, " +
                            "republicOfIrelandId = ?, " +
                            "isleOfManId = ?, " +
                            "channelIslandsId = ?, " +
                            "indiaId = ?, " +
                            "generic = ? " +
                            " WHERE RADAR_NO = ?",
                    patient.getRrNo(),
                    patient.getDateReg(),
                    patient.getNhsno(),
                    patient.getNhsNumberType().getId(),
                    patient.getHospitalnumber(),
                    patient.getUktNo(),
                    patient.getSurname(),
                    patient.getSnameAlias(),
                    patient.getForename(),
                    new SimpleDateFormat(DATE_FORMAT).format(patient.getDob()),
                    patient.getAge(),
                    patient.getSex() != null ? patient.getSexModel().getId() : null,
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
                    patient.getRenalUnit() != null ? patient.getRenalUnit().getId() : null,
                    patient.getRenalUnitAuthorised() != null ?
                            patient.getRenalUnitAuthorised().getId() : null,
                    patient.getStatus() != null ? patient.getStatusModel().getId() : null,
                    patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null,
                    patient.getEmailAddress(),
                    patient.getTelephone1(),
                    patient.getTelephone2(),
                    patient.getMobile(),
                    patient.getRrtModality() != null ? patient.getRrtModalityEunm().getId() : null,
                    patient.getGenericDiagnosis() != null ? patient.getGenericDiagnosisModel().getId() : null,
                    patient.getDateOfGenericDiagnosis(),
                    patient.getOtherClinicianAndContactInfo(),
                    patient.getComments(),
                    patient.getRepublicOfIrelandId(),
                    patient.getIsleOfManId(),
                    patient.getChannelIslandsId(),
                    patient.getIndiaId(),
                    patient.isGeneric(),
                    patient.getId());
        } else {
            Number id = demographicsInsert.executeAndReturnKey(new HashMap<String, Object>() {
                {
                    put("RR_NO", patient.getRrNo());
                    put("DATE_REG", patient.getDateReg());
                    put("NHS_NO", patient.getNhsno());
                    put("NHS_NO_TYPE", patient.getNhsNumberType().getId());
                    put("HOSP_NO", patient.getHospitalnumber());
                    put("UKT_NO", patient.getUktNo());
                    put("SNAME", patient.getSurname());
                    put("SNAME_ALIAS", patient.getSnameAlias());
                    put("FNAME", patient.getForename());
                    put("DOB", patient.getDob() != null ?
                            new SimpleDateFormat(DATE_FORMAT).format(
                                    patient.getDob()) : null);
                    put("AGE", patient.getAge());
                    put("SEX", patient.getSex() != null ? patient.getSexModel().getId() : null);
                    put("ETHNIC_GP",
                            patient.getEthnicity() != null ? patient.getEthnicity().getCode() : null);
                    put("ADD1", patient.getAddress1());
                    put("ADD2", patient.getAddress2());
                    put("ADD3", patient.getAddress3());
                    put("ADD4", patient.getAddress4());
                    put("POSTCODE", patient.getPostcode());
                    put("POSTCODE_OLD", patient.getPostcodeOld());
                    put("CONSENT", patient.isConsent());
                    put("DATE_BAPN_REG", null); // Todo: Fix
                    put("CONS_NEPH", patient.getClinician() != null ? patient.getClinician().getId()
                            : null);
                    put("RENAL_UNIT", patient.getRenalUnit() != null ? patient.getRenalUnit().getId() : null);
                    put("RENAL_UNIT_2", patient.getRenalUnitAuthorised() != null ?
                            patient.getRenalUnitAuthorised().getId() : null);
                    put("STATUS", patient.getStatus() != null ? patient.getStatusModel().getId() : null);
                    put("RDG", patient.getDiseaseGroup() != null ? patient.getDiseaseGroup().getId() : null);
                    put("emailAddress", patient.getEmailAddress());
                    put("phone1", patient.getTelephone1());
                    put("phone2", patient.getTelephone2());
                    put("mobile", patient.getMobile());
                    put("RRT_modality", patient.getRrtModality() != null ? patient.getRrtModalityEunm().getId()
                            : null);
                    put("genericDiagnosis", patient.getGenericDiagnosis() != null ?
                            patient.getGenericDiagnosisModel().getId() : null);
                    put("dateOfGenericDiagnosis", patient.getDateOfGenericDiagnosis());
                    put("otherClinicianAndContactInfo", patient.getOtherClinicianAndContactInfo());
                    put("comments", patient.getComments());
                    put("republicOfIrelandId", patient.getRepublicOfIrelandId());
                    put("isleOfManId", patient.getIsleOfManId());
                    put("channelIslandsId", patient.getChannelIslandsId());
                    put("indiaId", patient.getIndiaId());
                    put("generic", patient.isGeneric());
                }
            });
            patient.setId(id.longValue());
        }
    }

    public Patient getDemographicsByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Demographics WHERE RADAR_NO = ?",
                    new Object[]{radarNumber}, new DemographicsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No demographic record found for radar number {}", radarNumber);
            return null;
        }
    }

    public List<Patient> getDemographicsByRenalUnit(Centre centre) {
        return jdbcTemplate.query("SELECT * FROM tbl_Demographics WHERE RENAL_UNIT = ? OR RENAL_UNIT_2 = ? ",
                new Object[]{centre.getId(), centre.getId()}, new DemographicsRowMapper());
    }

    public List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
        if (filter == null) {
            filter = new DemographicsFilter();
        }

        List<String> sqlQueries = new ArrayList<String>();
        List<Object> params = new ArrayList<Object>();

        // normal sql query without any filter options
        sqlQueries.add("SELECT " +
                "   tbl_Demographics.*, " +
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
                "   tbl_Demographics " +
                "ON " +
                "   tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO " +
                "LEFT OUTER JOIN " +
                "   unit " +
                "INNER JOIN " +
                "   tbl_Consultants " +
                "ON " +
                "   unit.id = tbl_Consultants.cCentre " +
                "ON " +
                "   tbl_Demographics.CONS_NEPH = tbl_Consultants.cID");

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
            Long radarId = resultSet.getLong("RADAR_NO");
            patient.setId(radarId);
            patient.setDateReg(resultSet.getDate("DATE_REG"));

            // Renal registry number
            patient.setRrNo(resultSet.getString("RR_NO"));

            // UK transplant number
            patient.setUktNo(resultSet.getString("UKT_NO"));

            patient.setNhsno(resultSet.getString("NHS_NO"));
            patient.setNhsNumberType(NhsNumberType.getNhsNumberType(resultSet.getLong("NHS_NO_TYPE")));
            patient.setHospitalnumber(resultSet.getString("HOSP_NO"));
            patient.setSurname(resultSet.getString("SNAME"));
            patient.setSnameAlias(resultSet.getString("SNAME_ALIAS"));
            patient.setForename(resultSet.getString("FNAME"));

            // Date needs to be decrypted to string, then parsed
            String dateOfBirthString = resultSet.getString("DOB");
            if (StringUtils.isNotBlank(dateOfBirthString)) {
                Date dateOfBirth = null;

                // It seems that the encrypted strings in the DB have different date formats, nice.
                for (String dateFormat : new String[]{DATE_FORMAT, DATE_FORMAT_2, DATE_FORMAT_3}) {
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
            patient.setAddress1(resultSet.getString("ADD1"));
            patient.setAddress2(resultSet.getString("ADD2"));
            patient.setAddress3(resultSet.getString("ADD3"));
            patient.setAddress4(resultSet.getString("ADD4"));
            patient.setPostcode(resultSet.getString("POSTCODE"));
            patient.setPostcodeOld(resultSet.getString("POSTCODE_OLD"));

            // Set sex
            patient.setSexModel(getSex(resultSet.getLong("SEX")));

            // Try and get ethnicity
            String ethnicityCode = resultSet.getString("ETHNIC_GP");
            if (StringUtils.isNotBlank(ethnicityCode)) {
                patient.setEthnicity(utilityDao.getEthnicityByCode(ethnicityCode));
            }

            patient.setConsent(resultSet.getBoolean("CONSENT"));

            // Set the centre if we have an ID
            long renalUnitId = resultSet.getLong("RENAL_UNIT");
            if (renalUnitId > 0) {
                patient.setRenalUnit(utilityDao.getCentre(renalUnitId));
            }

            // Set status
            long statusId = resultSet.getLong("STATUS");
            if (statusId > 0) {
                patient.setStatusModel(getStatus(statusId));
            }

            Long consultantId = resultSet.getLong("CONS_NEPH");
            if (!resultSet.wasNull()) {

                try {
                    Clinician clinician = utilityDao.getClinician(consultantId);
                    if (clinician != null) {
                        patient.setClinician(clinician);
                    }
                } catch (Exception e) {
                    LOGGER.error("Unable to access consultant using consultantId {}", consultantId);
                    e.printStackTrace();
                }


            }

            Long renalUnitAuthorisedId = resultSet.getLong("RENAL_UNIT_2");
            if (!resultSet.wasNull()) {
                Centre centre = utilityDao.getCentre(renalUnitAuthorisedId);
                patient.setRenalUnitAuthorised(centre);
            }

            // set generic fields
            String diseaseGroupId = resultSet.getString("RDG"); //RDG,
            if (diseaseGroupId != null) {
                patient.setDiseaseGroup(diseaseGroupDao.getById(diseaseGroupId));
            }
            patient.setEmailAddress(resultSet.getString("emailAddress")); //emailAddress,
            patient.setTelephone1(resultSet.getString("phone1")); //phone1,
            patient.setTelephone2(resultSet.getString("phone2")); //phone2,
            patient.setMobile(resultSet.getString("mobile")); //mobile,
            Integer rrtModalityId = getIntegerWithNullCheck("RRT_modality", resultSet); //RRT_modality,
            if (rrtModalityId != null) {
                patient.setRrtModalityEunm(getEnumValue(Patient.RRTModality.class, rrtModalityId));
            }

            String genericDiagnosisId = resultSet.getString("genericDiagnosis");
            if (StringUtils.isNotBlank(genericDiagnosisId) && StringUtils.isNotBlank(diseaseGroupId)) {
                patient.setGenericDiagnosisModel(genericDiagnosisDao.get(genericDiagnosisId, diseaseGroupId));
            }

            patient.setDateOfGenericDiagnosis(resultSet.getDate("dateOfGenericDiagnosis"));
            patient.setOtherClinicianAndContactInfo(resultSet.getString("otherClinicianAndContactInfo"));
            patient.setComments(resultSet.getString("comments")); //comments,
            patient.setRepublicOfIrelandId(resultSet.getString("republicOfIrelandId"));
            patient.setIsleOfManId(resultSet.getString("isleOfManId"));
            patient.setChannelIslandsId(resultSet.getString("channelIslandsId"));
            patient.setIndiaId(resultSet.getString("indiaId"));
            patient.setGeneric(resultSet.getBoolean("generic"));

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
}