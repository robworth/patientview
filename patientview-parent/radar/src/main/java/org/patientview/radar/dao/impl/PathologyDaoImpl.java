package org.patientview.radar.dao.impl;

import org.patientview.radar.dao.PathologyDao;
import org.patientview.radar.model.enums.KidneyTransplantedNative;
import org.patientview.radar.model.sequenced.Pathology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathologyDaoImpl extends BaseDaoImpl implements PathologyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathologyDaoImpl.class);
    private SimpleJdbcInsert pathologyInsert;

    @Override
    public void setDataSource(DataSource dataSource) {
        // Call super
        super.setDataSource(dataSource);

        // Initialise a simple JDBC insert to be able to get the allocated ID
        pathologyInsert = new SimpleJdbcInsert(dataSource).withTableName("tbl_pathology")
                .usingGeneratedKeyColumns("pID")
                .usingColumns(
                        "RADAR_NO", "BX_DATE", "NAT_TRANSP_KID", "LATERALITY_BX", "SAMPLE_LAB_NO", "PATHDIAG",
                        "GLOM_TOTAL_NO", "GLOM_GLOB_SCL", "GLOM_SEQ_SCL", "GLOM_CELL_CRES", "GLOM_FIB_CRES",
                        "GLOM_END_HYPER", "GLOM_FIN_NEC", "GLOM_ANY_OTH_FEAT", "TUB_ATROP_IF_EST", "TUB_ATROP_IF_MEAS",
                        "TUB_OTHER_FEAT", "INTER_INFLAM_INFIL", "ART_ABNORMAL", "IMM_HIST_FIND", "ELECT_MSCOPE_FIND",
                        "IMAGE_URL1", "IMAGE_URL2", "IMAGE_URL3", "IMAGE_URL4", "IMAGE_URL5", "PATH_TXT", "SEQ_NO"
                );
    }


    public void savePathology(final Pathology pathology) {
        Map<String, Object> pathologyMap = new HashMap<String, Object>() {
            {
                put("RADAR_NO", pathology.getRadarNumber());
                put("BX_DATE", pathology.getBiopsyDate());
                put("NAT_TRANSP_KID", pathology.getKidneyTransplantedNative() != null ?
                        pathology.getKidneyTransplantedNative().getId() : null);
                put("LATERALITY_BX", pathology.getSide() != null ? pathology.getSide().getId() : null);
                put("SAMPLE_LAB_NO", pathology.getSampleLabNumber());
                put("PATHDIAG", null);
                put("GLOM_TOTAL_NO", pathology.getTotalNumber());
                put("GLOM_GLOB_SCL", pathology.getNumberSclerosed());
                put("GLOM_SEQ_SCL", pathology.getNumberSegmentallySclerosed());
                put("GLOM_CELL_CRES", pathology.getNumberCellularCrescents());
                put("GLOM_FIB_CRES", pathology.getNumberFibrousCrescents());
                put("GLOM_END_HYPER", pathology.getNumberEndocapillaryHypercelluarity());
                put("GLOM_FIN_NEC", pathology.getNumberFibrinoidNecrosis());
                put("GLOM_ANY_OTH_FEAT", pathology.getOtherFeature());
                put("TUB_ATROP_IF_EST", pathology.getEstimatedTubules());
                put("TUB_ATROP_IF_MEAS", pathology.getMeasuredTubules());
                put("TUB_OTHER_FEAT", pathology.getTubulesOtherFeature());
                put("INTER_INFLAM_INFIL", pathology.getInterstitalInflmatoryInfilitrate());
                put("ART_ABNORMAL", pathology.getArterialAbnormalities());
                put("IMM_HIST_FIND", pathology.getImmunohistologicalFindings());
                put("ELECT_MSCOPE_FIND", pathology.getElectronMicroscopicFindings());
                put("IMAGE_URL1", pathology.getImageUrl1());
                put("IMAGE_URL2", pathology.getImageUrl2());
                put("IMAGE_URL3", pathology.getImageUrl3());
                put("IMAGE_URL4", pathology.getImageUrl4());
                put("IMAGE_URL5", pathology.getImageUrl5());
                put("PATH_TXT", pathology.getHistologicalSummary());
                put("SEQ_NO", pathology.getSequenceNumber());
            }
        };


        if (pathology.hasValidId()) {
            pathologyMap.put("pID", pathology.getId());

            namedParameterJdbcTemplate.update("UPDATE tbl_pathology " +
                    "SET RADAR_NO = :RADAR_NO, " +
                    "BX_DATE = :BX_DATE, " +
                    "NAT_TRANSP_KID = :NAT_TRANSP_KID, " +
                    "LATERALITY_BX = :LATERALITY_BX, " +
                    "SAMPLE_LAB_NO = :SAMPLE_LAB_NO, " +
                    "PATHDIAG = :PATHDIAG, " +
                    "GLOM_TOTAL_NO = :GLOM_TOTAL_NO, " +
                    "GLOM_GLOB_SCL = :GLOM_GLOB_SCL, " +
                    "GLOM_SEQ_SCL = :GLOM_SEQ_SCL, " +
                    "GLOM_CELL_CRES = :GLOM_CELL_CRES, " +
                    "GLOM_FIB_CRES = :GLOM_FIB_CRES, " +
                    "GLOM_END_HYPER = :GLOM_END_HYPER, " +
                    "GLOM_FIN_NEC = :GLOM_FIN_NEC, " +
                    "GLOM_ANY_OTH_FEAT = :GLOM_ANY_OTH_FEAT, " +
                    "TUB_ATROP_IF_EST = :TUB_ATROP_IF_EST, " +
                    "TUB_ATROP_IF_MEAS = :TUB_ATROP_IF_MEAS, " +
                    "TUB_OTHER_FEAT = :TUB_OTHER_FEAT, " +
                    "INTER_INFLAM_INFIL = :INTER_INFLAM_INFIL, " +
                    "ART_ABNORMAL = :ART_ABNORMAL, " +
                    "IMM_HIST_FIND = :IMM_HIST_FIND, " +
                    "ELECT_MSCOPE_FIND = :ELECT_MSCOPE_FIND, " +
                    "IMAGE_URL1 = :IMAGE_URL1, " +
                    "IMAGE_URL2 = :IMAGE_URL2, " +
                    "IMAGE_URL3 = :IMAGE_URL3, " +
                    "IMAGE_URL4 = :IMAGE_URL4, " +
                    "IMAGE_URL5 = :IMAGE_URL5, " +
                    "PATH_TXT = :PATH_TXT, " +
                    "SEQ_NO = :SEQ_NO " +
                    "WHERE pID = :pID;", pathologyMap);

        } else {
            Number id = pathologyInsert.executeAndReturnKey(pathologyMap);
            pathology.setId(id.longValue());
        }
    }

    public Pathology getPathology(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tbl_Pathology WHERE pID = ?", new Object[]{id},
                    new PathologyRowMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("No therapy object found with ID {}", id);
            return null;
        }
    }

    public List<Pathology> getPathologyByRadarNumber(long radarNumber) {
        return jdbcTemplate.query("SELECT * FROM tbl_Pathology WHERE RADAR_NO = ?", new Object[]{radarNumber},
                new PathologyRowMapper());
    }

    private class PathologyRowMapper implements RowMapper<Pathology> {
        public Pathology mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a pathology object and set the fields
            Pathology pathology = new Pathology();
            pathology.setId(resultSet.getLong("pID"));
            pathology.setRadarNumber(resultSet.getLong("RADAR_NO"));

            pathology.setBiopsyDate(resultSet.getDate("BX_DATE"));

            pathology.setSampleLabNumber(resultSet.getString("SAMPLE_LAB_NO"));

            pathology.setTotalNumber(getIntegerWithNullCheck("GLOM_TOTAL_NO", resultSet));
            pathology.setNumberSclerosed(getIntegerWithNullCheck("GLOM_GLOB_SCL", resultSet));
            pathology.setNumberSegmentallySclerosed(getIntegerWithNullCheck("GLOM_SEQ_SCL", resultSet));
            pathology.setNumberCellularCrescents(getIntegerWithNullCheck("GLOM_CELL_CRES", resultSet));
            pathology.setNumberFibrousCrescents(getIntegerWithNullCheck("GLOM_FIB_CRES", resultSet));
            pathology.setNumberEndocapillaryHypercelluarity(getIntegerWithNullCheck("GLOM_END_HYPER", resultSet));
            pathology.setNumberFibrinoidNecrosis(getIntegerWithNullCheck("GLOM_FIN_NEC", resultSet));
            pathology.setOtherFeature(resultSet.getString("GLOM_ANY_OTH_FEAT"));

            pathology.setSequenceNumber(getIntegerWithNullCheck("SEQ_NO", resultSet));

            pathology.setImageUrl1(resultSet.getString("IMAGE_URL1"));
            pathology.setImageUrl2(resultSet.getString("IMAGE_URL2"));
            pathology.setImageUrl3(resultSet.getString("IMAGE_URL3"));
            pathology.setImageUrl4(resultSet.getString("IMAGE_URL4"));
            pathology.setImageUrl5(resultSet.getString("IMAGE_URL5"));

            pathology.setHistologicalSummary(resultSet.getString("PATH_TXT"));

            // PATHDIAG not used in select but always seems to be set to 2 in the insert?

            pathology.setEstimatedTubules(getDoubleWithNullCheck("TUB_ATROP_IF_EST", resultSet));
            pathology.setMeasuredTubules(getDoubleWithNullCheck("TUB_ATROP_IF_MEAS", resultSet));
            pathology.setTubulesOtherFeature(resultSet.getString("TUB_OTHER_FEAT"));

            pathology.setElectronMicroscopicFindings(resultSet.getString("ELECT_MSCOPE_FIND"));

            // NAT_TRANSP_KID
            pathology.setKidneyTransplantedNative(BaseDaoImpl.<KidneyTransplantedNative>getEnumValue(
                    KidneyTransplantedNative.class, getIntegerWithNullCheck("NAT_TRANSP_KID", resultSet)));

            // Side - LATERALITY_BX
            pathology.setSide(
                    BaseDaoImpl.<Pathology.Side>getEnumValue(Pathology.Side.class,
                            getIntegerWithNullCheck("LATERALITY_BX", resultSet)));

            pathology.setInterstitalInflmatoryInfilitrate(resultSet.getString("INTER_INFLAM_INFIL"));
            pathology.setArterialAbnormalities(resultSet.getString("ART_ABNORMAL"));
            pathology.setImmunohistologicalFindings(resultSet.getString("IMM_HIST_FIND"));

            return pathology;
        }
    }
}
