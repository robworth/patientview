package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.LabDataDao;
import com.solidstategroup.radar.model.sequenced.LabData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LabDataDaoImpl extends BaseDaoImpl implements LabDataDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabDataDaoImpl.class);

    public LabData getLabData(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_LabData WHERE labID = ?",
                new Object[]{id}, new LabDataRowMapper());
    }

    public List<LabData> getLabDataByRadarNumber(long id) {
        return jdbcTemplate.query("SELECT * FROM tbl_LabData WHERE RADAR_NO = ?",
                new Object[]{id}, new LabDataRowMapper());
    }

    private class LabDataRowMapper implements RowMapper<LabData> {
        public LabData mapRow(ResultSet resultSet, int i) throws SQLException {
            // Construct a lab data object and set all the fields
            LabData labData = new LabData();

            labData.setId(resultSet.getLong("labID"));
            labData.setRadarNumber(resultSet.getLong("RADAR_NO"));
            labData.setDate(resultSet.getDate("DATE_LAB_RES"));

            // Blood bits
            labData.setSerumCreatinine(resultSet.getDouble("CREAT_SER"));
            labData.setProtein(resultSet.getDouble("PROTEIN"));
            labData.setAlbumin(resultSet.getDouble("ALBUMIN"));
            labData.setBun(resultSet.getDouble("UREA_SER"));
            labData.setSodium(resultSet.getDouble("SODIUM"));
            labData.setPotassium(resultSet.getDouble("POTASSIUM"));
            labData.setPhosphate(resultSet.getDouble("PHOS"));
            labData.setProteinCreatinineRatio(resultSet.getDouble("PROT_CREAT_RAT"));
            labData.setAlbuminCreatinineRatio(resultSet.getDouble("ALB_CREAT_RAT"));
            labData.setWbc(resultSet.getDouble("WBC"));
            labData.setHb(resultSet.getDouble("HB"));
            labData.setNeutrophils(resultSet.getDouble("NEUTRO"));
            labData.setPlatelets(resultSet.getDouble("PLATELETS"));
            labData.setFerritin(resultSet.getDouble("FERRITIN"));

            // Cholesterol
            labData.setTotalCholesterol(resultSet.getDouble("CHOL_TOTAL"));
            labData.setHdlCholesterol(resultSet.getDouble("CHOL_HDL"));
            labData.setLdlCholesterol(resultSet.getDouble("CHOL_HDL"));

            labData.setTriglycerides(resultSet.getDouble("TRIG"));

            // There is a row CREAT_CLEAR_24_URINE but all references are commented out in legacy ASP code
            // Same goes for CREAT_CLEAR_RADIO

            labData.setCreatinineClearance(resultSet.getDouble("CREAT_CLEAR_SCHZ"));

            labData.setThyroxine(resultSet.getDouble("THYROX"));
            labData.setTsh(resultSet.getDouble("TSH"));

            labData.setAnca(BaseDaoImpl.<LabData.Anca>getEnumValue(LabData.Anca.class, resultSet.getInt("ANCA")));

            // There is a row ELISA_ASS but again it is all commented out in current code

            labData.setEna(BaseDaoImpl
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ENA")));
            labData.setAna(BaseDaoImpl
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ANA")));

            labData.setDnaAntibodies(resultSet.getString("DNA_ANTIB"));
            labData.setAntiDsDna(BaseDaoImpl
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("DNA_ANTI_DS")));

            labData.setCryoglobulins(BaseDaoImpl
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("CRYOGLOB")));
            labData.setAntiGbm(BaseDaoImpl
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ANTI_GBM")));

            labData.setIgG(resultSet.getDouble("IGG"));
            labData.setIgA(resultSet.getDouble("IGA"));
            labData.setIgM(resultSet.getDouble("IGM"));

            labData.setComplementC3(resultSet.getDouble("COMP_C3"));
            labData.setComplementC4(resultSet.getDouble("COMP_C4"));
            labData.setComplementOtherDetail(resultSet.getString("COMP_OTHER"));

            labData.setC3NephriticFactor(BaseDaoImpl
                    .<LabData.PositiveNegativeUnknown>getEnumValue(LabData.PositiveNegativeUnknown.class,
                            resultSet.getInt("C3_NEPH_FAC")));

            // There is an ANTI_SLT column that doesn't seem to be used within legacy code

            labData.setInr(resultSet.getDouble("INR"));
            labData.setCrp(resultSet.getDouble("CRP"));

            labData.setAntistreptolysin(resultSet.getDouble("ANTI_STREP_O"));

            labData.setHepatitisB(BaseDaoImpl.<LabData.PositiveNegativeUnknown>getEnumValue(
                    LabData.PositiveNegativeUnknown.class, resultSet.getInt("HEP_B")));
            labData.setHepatitisC(BaseDaoImpl.<LabData.PositiveNegativeUnknown>getEnumValue(
                    LabData.PositiveNegativeUnknown.class, resultSet.getInt("HEP_C")));

            labData.setHivAntibody(BaseDaoImpl.<LabData.PositiveNegativeUnknown>getEnumValue(
                    LabData.PositiveNegativeUnknown.class, resultSet.getInt("HIV")));

            labData.setDnaTakenFactorH(resultSet.getBoolean("DNA_FACTOR_H"));

            labData.setEbv(BaseDaoImpl
                    .<LabData.Immunoglobulins>getEnumValue(LabData.Immunoglobulins.class, resultSet.getInt("EBV")));
            labData.setCmvSerology(BaseDaoImpl
                    .<LabData.Immunoglobulins>getEnumValue(LabData.Immunoglobulins.class, resultSet.getInt("CMV")));
            labData.setCmvSymptomatic(resultSet.getBoolean("CMV_SYM"));

            // Three more commented within code: BKV and BKV_SYM and HANTAVIRUS

            labData.setParvovirusAntibody(BaseDaoImpl.<LabData.PositiveNegativeNotDone>getEnumValue(
                    LabData.PositiveNegativeNotDone.class, resultSet.getInt("PARVO_ANTIB")));
            labData.setOtherInfection(resultSet.getBoolean("OTHER_INFECT"));
            labData.setOtherInfectionDetail(resultSet.getString("OTHER_INFECT_SP"));

            labData.setUrineVolume(resultSet.getDouble("UR_VOL_24H"));
            labData.setUrineVolumeCondition(BaseDaoImpl.<LabData.UrineVolumeCondition>getEnumValue(
                    LabData.UrineVolumeCondition.class, resultSet.getInt("UR_VOL_24H_COND")));

            labData.setHaematuria(BaseDaoImpl
                    .<LabData.Haematuria>getEnumValue(LabData.Haematuria.class, resultSet.getInt("HAEMATURIA")));
            labData.setAlbuminuria(BaseDaoImpl
                    .<LabData.Albuminuria>getEnumValue(LabData.Albuminuria.class, resultSet.getInt("HAEMATURIA")));

            labData.setDysmorphicErythrocytes(BaseDaoImpl
                    .<LabData.Present>getEnumValue(LabData.Present.class, resultSet.getInt("DYS_ERYTH_URINE")));
            labData.setRedCellCast(BaseDaoImpl
                    .<LabData.Present>getEnumValue(LabData.Present.class, resultSet.getInt("RED_CCASTS_URINE")));
            labData.setWhiteCellCasts(BaseDaoImpl
                    .<LabData.Present>getEnumValue(LabData.Present.class, resultSet.getInt("WBC_CASTS_URINE")));

            labData.setLeucocytesUrine(resultSet.getBoolean("LEUC_URINE"));
            labData.setNitrite(resultSet.getBoolean("NITRITE"));
            labData.setBacteria(resultSet.getBoolean("BACT_URINE"));
            labData.setGlucose(resultSet.getBoolean("GLUC_URINE"));

            // Osmolarity is within the database as varchar but validated on legacy frontend as integer
            // So within our model keep it as a double and do the conversion here
            labData.setOsmolality(resultSet.getDouble("OSMOLARITY"));

            labData.setProteinuria(BaseDaoImpl.<LabData.Proteinuria>getEnumValue(LabData.Proteinuria.class,
                    resultSet.getInt("PROTEINURIA_DIP")));

            labData.setSequenceNumber(resultSet.getInt("SEQ_NO"));
            labData.setAntiClqAntibodies(resultSet.getDouble("ANTI_CLQ"));

            return labData;
        }
    }

}
