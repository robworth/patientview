package com.solidstategroup.radar.dao.impl;

import com.solidstategroup.radar.dao.LabDataDao;
import com.solidstategroup.radar.model.sequenced.LabData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Method;
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

            labData.setAnca(
                    LabDataDaoImpl.this.<LabData.Anca>getEnumValue(LabData.Anca.class, resultSet.getInt("ANCA")));

            // There is a row ELISA_ASS but again it is all commented out in current code

            labData.setEna(LabDataDaoImpl.this
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ENA")));
            labData.setAna(LabDataDaoImpl.this
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ANA")));

            labData.setDnaAntibodies(resultSet.getString("DNA_ANTIB"));
            labData.setAntiDsDna(LabDataDaoImpl.this
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("DNA_ANTI_DS")));

            labData.setCryoglobulins(LabDataDaoImpl.this
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("CRYOGLOB")));
            labData.setAntiGbm(LabDataDaoImpl.this
                    .<LabData.PositiveNegativeNotDone>getEnumValue(LabData.PositiveNegativeNotDone.class,
                            resultSet.getInt("ANTI_GBM")));

            labData.setIgG(resultSet.getDouble("IGG"));
            labData.setIgA(resultSet.getDouble("IGA"));
            labData.setIgM(resultSet.getDouble("IGM"));

            labData.setComplementC3(resultSet.getDouble("COMP_C3"));
            labData.setComplementC4(resultSet.getDouble("COMP_C4"));

            /*

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

            */


            return labData;
        }
    }

    private <T> T getEnumValue(Class<T> enumClass, int id) {
        try {
            // Assume we've been supplied an enum with an ID field, so get the accessor method
            Method getId = enumClass.getMethod("getId");
            // All enums have a static values method to get an array of the possible values
            Method method = enumClass.getMethod("values");

            // Use the static method to get an array of all the possible values
            T[] values = (T[]) method.invoke(null);
            for (T t : values) {
                // Get the ID field value
                Integer thisId = (Integer) getId.invoke(t);
                if (id == thisId) {
                    return t;
                }
            }

            // If we looped all the ID's and didn't find a value then return null
            LOGGER.debug("Could not find matching value for enum {} with ID {}", enumClass, id);
            return null;

        } catch (Exception e) {
            // This is pretty bad so lets throw a runtime exception
            LOGGER.error("Could not get values for enum {}", enumClass);
            throw new RuntimeException(e);
        }
    }

}
