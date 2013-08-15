package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Clinician;
import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.Status;
import org.patientview.radar.model.enums.NhsNumberType;
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

    public void saveDemographics(final Demographics demographics) {
        // If we have an ID then update, otherwise insert new and set the ID
        if (demographics.hasValidId()) {
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
                    demographics.getRenalRegistryNumber(),
                    demographics.getDateRegistered(),
                    demographics.getNhsNumber(),
                    demographics.getNhsNumberType().getId(),
                    demographics.getHospitalNumber(),
                    demographics.getUkTransplantNumber(),
                    demographics.getSurname(),
                    demographics.getSurnameAlias(),
                    demographics.getForename(),
                    new SimpleDateFormat(DATE_FORMAT).format(demographics.getDateOfBirth()),
                    demographics.getAge(),
                    demographics.getSex() != null ? demographics.getSex().getId() : null,
                    demographics.getEthnicity() != null ? demographics.getEthnicity().getCode() : null,
                    demographics.getAddress1(),
                    demographics.getAddress2(),
                    demographics.getAddress3(),
                    demographics.getAddress4(),
                    demographics.getPostcode(),
                    demographics.getPreviousPostcode(),
                    demographics.isConsent(),
                    demographics.getDateRegistered(),
                    demographics.getClinician() != null ? demographics.getClinician().getId() : null,
                    demographics.getRenalUnit() != null ? demographics.getRenalUnit().getId() : null,
                    demographics.getRenalUnitAuthorised() != null ?
                            demographics.getRenalUnitAuthorised().getId() : null,
                    demographics.getStatus() != null ? demographics.getStatus().getId() : null,
                    demographics.getDiseaseGroup() != null ? demographics.getDiseaseGroup().getId() : null,
                    demographics.getEmailAddress(),
                    demographics.getPhone1(),
                    demographics.getPhone2(),
                    demographics.getMobile(),
                    demographics.getRrtModality() != null ? demographics.getRrtModality().getId() : null,
                    demographics.getGenericDiagnosis() != null ? demographics.getGenericDiagnosis().getId() : null,
                    demographics.getDateOfGenericDiagnosis(),
                    demographics.getOtherClinicianAndContactInfo(),
                    demographics.getComments(),
                    demographics.getRepublicOfIrelandId(),
                    demographics.getIsleOfManId(),
                    demographics.getChannelIslandsId(),
                    demographics.getIndiaId(),
                    demographics.isGeneric(),
                    demographics.getId());
        } else {
            Number id = demographicsInsert.executeAndReturnKey(new HashMap<String, Object>() {
                {
                    put("RR_NO", demographics.getRenalRegistryNumber());
                    put("DATE_REG", demographics.getDateRegistered());
                    put("NHS_NO", demographics.getNhsNumber());
                    put("NHS_NO_TYPE", demographics.getNhsNumberType().getId());
                    put("HOSP_NO", demographics.getHospitalNumber());
                    put("UKT_NO", demographics.getUkTransplantNumber());
                    put("SNAME", demographics.getSurname());
                    put("SNAME_ALIAS", demographics.getSurnameAlias());
                    put("FNAME", demographics.getForename());
                    put("DOB", demographics.getDateOfBirth() != null ?
                            new SimpleDateFormat(DATE_FORMAT).format(
                                    demographics.getDateOfBirth()) : null);
                    put("AGE", demographics.getAge());
                    put("SEX", demographics.getSex() != null ? demographics.getSex().getId() : null);
                    put("ETHNIC_GP",
                            demographics.getEthnicity() != null ? demographics.getEthnicity().getCode() : null);
                    put("ADD1", demographics.getAddress1());
                    put("ADD2", demographics.getAddress2());
                    put("ADD3", demographics.getAddress3());
                    put("ADD4", demographics.getAddress4());
                    put("POSTCODE", demographics.getPostcode());
                    put("POSTCODE_OLD", demographics.getPreviousPostcode());
                    put("CONSENT", demographics.isConsent());
                    put("DATE_BAPN_REG", null); // Todo: Fix
                    put("CONS_NEPH", demographics.getClinician() != null ? demographics.getClinician().getId()
                            : null);
                    put("RENAL_UNIT", demographics.getRenalUnit() != null ? demographics.getRenalUnit().getId() : null);
                    put("RENAL_UNIT_2", demographics.getRenalUnitAuthorised() != null ?
                            demographics.getRenalUnitAuthorised().getId() : null);
                    put("STATUS", demographics.getStatus() != null ? demographics.getStatus().getId() : null);
                    put("RDG", demographics.getDiseaseGroup() != null ? demographics.getDiseaseGroup().getId() : null);
                    put("emailAddress", demographics.getEmailAddress());
                    put("phone1", demographics.getPhone1());
                    put("phone2", demographics.getPhone2());
                    put("mobile", demographics.getMobile());
                    put("RRT_modality", demographics.getRrtModality() != null ? demographics.getRrtModality().getId()
                            : null);
                    put("genericDiagnosis", demographics.getGenericDiagnosis() != null ?
                            demographics.getGenericDiagnosis().getId() : null);
                    put("dateOfGenericDiagnosis", demographics.getDateOfGenericDiagnosis());
                    put("otherClinicianAndContactInfo", demographics.getOtherClinicianAndContactInfo());
                    put("comments", demographics.getComments());
                    put("republicOfIrelandId", demographics.getRepublicOfIrelandId());
                    put("isleOfManId", demographics.getIsleOfManId());
                    put("channelIslandsId", demographics.getChannelIslandsId());
                    put("indiaId", demographics.getIndiaId());
                    put("generic", demographics.isGeneric());
                }
            });
            demographics.setId(id.longValue());
        }
    }

    public Demographics getDemographicsByRadarNumber(long radarNumber) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Demographics WHERE RADAR_NO = ?",
                    new Object[]{radarNumber}, new DemographicsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No demographic record found for radar number {}", radarNumber);
            return null;
        }
    }

    public List<Demographics> getDemographicsByRenalUnit(Centre centre) {
        return jdbcTemplate.query("SELECT * FROM tbl_Demographics WHERE RENAL_UNIT = ? OR RENAL_UNIT_2 = ? ",
                new Object[]{centre.getId(), centre.getId()}, new DemographicsRowMapper());
    }

    public List<Demographics> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
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


    public class DemographicsRowMapper implements RowMapper<Demographics> {
        public Demographics mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct object and set radar number
            Demographics demographics = new Demographics();
            Long radarId = resultSet.getLong("RADAR_NO");
            demographics.setId(radarId);
            demographics.setDateRegistered(resultSet.getDate("DATE_REG"));

            // Renal registry number
            demographics.setRenalRegistryNumber(resultSet.getString("RR_NO"));

            // UK transplant number
            demographics.setUkTransplantNumber(resultSet.getString("UKT_NO"));

            demographics.setNhsNumber(resultSet.getString("NHS_NO"));
            demographics.setNhsNumberType(NhsNumberType.getNhsNumberType(resultSet.getLong("NHS_NO_TYPE")));
            demographics.setHospitalNumber(resultSet.getString("HOSP_NO"));
            demographics.setSurname(resultSet.getString("SNAME"));
            demographics.setSurnameAlias(resultSet.getString("SNAME_ALIAS"));
            demographics.setForename(resultSet.getString("FNAME"));

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
                    demographics.setDateOfBirth(dateOfBirth);
                } else {
                    LOGGER.error("Could not parse date of birth from any format for dob {}",
                            dateOfBirthString);
                    String a = "";
                }
            }

            // Addresses
            demographics.setAddress1(resultSet.getString("ADD1"));
            demographics.setAddress2(resultSet.getString("ADD2"));
            demographics.setAddress3(resultSet.getString("ADD3"));
            demographics.setAddress4(resultSet.getString("ADD4"));
            demographics.setPostcode(resultSet.getString("POSTCODE"));
            demographics.setPreviousPostcode(resultSet.getString("POSTCODE_OLD"));

            // Set sex
            demographics.setSex(getSex(resultSet.getLong("SEX")));

            // Try and get ethnicity
            String ethnicityCode = resultSet.getString("ETHNIC_GP");
            if (StringUtils.isNotBlank(ethnicityCode)) {
                demographics.setEthnicity(utilityDao.getEthnicityByCode(ethnicityCode));
            }

            demographics.setConsent(resultSet.getBoolean("CONSENT"));

            // Set the centre if we have an ID
            long renalUnitId = resultSet.getLong("RENAL_UNIT");
            if (renalUnitId > 0) {
                demographics.setRenalUnit(utilityDao.getCentre(renalUnitId));
            }

            // Set status
            long statusId = resultSet.getLong("STATUS");
            if (statusId > 0) {
                demographics.setStatus(getStatus(statusId));
            }

            Long consultantId = resultSet.getLong("CONS_NEPH");
            if (!resultSet.wasNull()) {

                try {
                    Clinician clinician = utilityDao.getClinician(consultantId);
                    if (clinician != null) {
                        demographics.setClinician(clinician);
                    }
                } catch (Exception e) {
                    LOGGER.error("Unable to access consultant using consultantId {}", consultantId);
                    e.printStackTrace();
                }


            }

            Long renalUnitAuthorisedId = resultSet.getLong("RENAL_UNIT_2");
            if (!resultSet.wasNull()) {
                Centre centre = utilityDao.getCentre(renalUnitAuthorisedId);
                demographics.setRenalUnitAuthorised(centre);
            }

            // set generic fields
            String diseaseGroupId = resultSet.getString("RDG"); //RDG,
            if (diseaseGroupId != null) {
                demographics.setDiseaseGroup(diseaseGroupDao.getById(diseaseGroupId));
            }
            demographics.setEmailAddress(resultSet.getString("emailAddress")); //emailAddress,
            demographics.setPhone1(resultSet.getString("phone1")); //phone1,
            demographics.setPhone2(resultSet.getString("phone2")); //phone2,
            demographics.setMobile(resultSet.getString("mobile")); //mobile,
            Integer rrtModalityId = getIntegerWithNullCheck("RRT_modality", resultSet); //RRT_modality,
            if (rrtModalityId != null) {
                demographics.setRrtModality(getEnumValue(Demographics.RRTModality.class, rrtModalityId));
            }

            String genericDiagnosisId = resultSet.getString("genericDiagnosis");
            if (StringUtils.isNotBlank(genericDiagnosisId) && StringUtils.isNotBlank(diseaseGroupId)) {
                demographics.setGenericDiagnosis(genericDiagnosisDao.get(genericDiagnosisId, diseaseGroupId));
            }

            demographics.setDateOfGenericDiagnosis(resultSet.getDate("dateOfGenericDiagnosis"));
            demographics.setOtherClinicianAndContactInfo(resultSet.getString("otherClinicianAndContactInfo"));
            demographics.setComments(resultSet.getString("comments")); //comments,
            demographics.setRepublicOfIrelandId(resultSet.getString("republicOfIrelandId"));
            demographics.setIsleOfManId(resultSet.getString("isleOfManId"));
            demographics.setChannelIslandsId(resultSet.getString("channelIslandsId"));
            demographics.setIndiaId(resultSet.getString("indiaId"));
            demographics.setGeneric(resultSet.getBoolean("generic"));

            return demographics;
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