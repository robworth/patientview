package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.PathologyDao;
import com.solidstategroup.radar.model.enums.KidneyTransplantedNative;
import com.solidstategroup.radar.model.sequenced.Pathology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PathologyDaoImpl extends BaseDaoImpl implements PathologyDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathologyDaoImpl.class);

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

            pathology.setTotalNumber(resultSet.getInt("GLOM_TOTAL_NO"));
            pathology.setNumberSclerosed(resultSet.getInt("GLOM_GLOB_SCL"));
            pathology.setNumberSegmentallySclerosed(resultSet.getInt("GLOM_SEQ_SCL"));
            pathology.setNumberCellularCrescents(resultSet.getInt("GLOM_CELL_CRES"));
            pathology.setNumberFibrousCrescents(resultSet.getInt("GLOM_FIB_CRES"));
            pathology.setNumberEndocapillaryHypercelluarity(resultSet.getInt("GLOM_END_HYPER"));
            pathology.setNumberFibrinoidNecrosis(resultSet.getInt("GLOM_FIN_NEC"));
            pathology.setOtherFeature(resultSet.getString("GLOM_ANY_OTH_FEAT"));

            pathology.setSequenceNumber(resultSet.getInt("SEQ_NO"));

            pathology.setImageUrl1(resultSet.getString("IMAGE_URL1"));
            pathology.setImageUrl2(resultSet.getString("IMAGE_URL2"));
            pathology.setImageUrl3(resultSet.getString("IMAGE_URL3"));
            pathology.setImageUrl4(resultSet.getString("IMAGE_URL4"));
            pathology.setImageUrl5(resultSet.getString("IMAGE_URL5"));

            pathology.setHistologicalSummary(resultSet.getString("PATH_TXT"));

            // PATHDIAG not used in select but always seems to be set to 2 in the insert?

            pathology.setEstimatedTubules(resultSet.getDouble("TUB_ATROP_IF_EST"));
            pathology.setMeasuredTubules(resultSet.getDouble("TUB_ATROP_IF_MEAS"));
            pathology.setTubulesOtherFeature(resultSet.getString("TUB_OTHER_FEAT"));

            pathology.setElectronMicroscopicFindings(resultSet.getString("ELECT_MSCOPE_FIND"));

            // NAT_TRANSP_KID
            pathology.setKidneyTransplantedNative(BaseDaoImpl.<KidneyTransplantedNative>getEnumValue(
                    KidneyTransplantedNative.class, resultSet.getInt("NAT_TRANSP_KID")));

            // Side - LATERALITY_BX
            pathology.setSide(
                    BaseDaoImpl.<Pathology.Side>getEnumValue(Pathology.Side.class, resultSet.getInt("LATERALITY_BX")));

            pathology.setInterstitalInflmatoryInfilitrate(resultSet.getString("INTER_INFLAM_INFIL"));
            pathology.setArterialAbnormalities(resultSet.getString("ART_ABNORMAL"));
            pathology.setImmunohistologicalFindings(resultSet.getString("IMM_HIST_FIND"));

            return pathology;
        }
    }
}
