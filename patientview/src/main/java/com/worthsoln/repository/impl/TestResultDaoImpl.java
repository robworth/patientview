package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.repository.TestResultDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "testResultDao")
public class TestResultDaoImpl implements TestResultDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    // units is a required parameter
    public List<TestResultWithUnitShortname> getTestResultForPatient(String username, Panel panel, List<Unit> units) {

        String sql = "SELECT testresult.*, unit.shortname FROM testresult, user, usermapping, result_heading, unit " +
                "WHERE user.username = ? " +
                "AND user.username = usermapping.username " +
                "AND usermapping.nhsno = testresult.nhsno " +
                "AND testresult.testcode = result_heading.headingcode " +
                "AND testresult.unitcode = unit.unitcode " +
                "AND result_heading.panel = ? " +
                "AND unit.unitcode IN (";

        List<Object> params = new ArrayList<Object>();
        params.add(username);
        params.add(panel.getPanel());

        int i = 0;
        for (Unit unit : units) {
            i++;
            sql += " ? ";
            params.add(unit.getUnitcode());
            if (i != units.size()) {
                sql += ",";
            }
        }

        sql += ")";

        return jdbcTemplate.query(sql, params.toArray(), new TestResultWithUnitShortnameMapper());
    }

    private class TestResultWithUnitShortnameMapper implements RowMapper<TestResultWithUnitShortname> {

        @Override
        public TestResultWithUnitShortname mapRow(ResultSet resultSet, int i) throws SQLException {
            TestResultWithUnitShortname testResultWithUnitShortname = new TestResultWithUnitShortname();
            testResultWithUnitShortname.setShortname(resultSet.getString("shortname"));
            testResultWithUnitShortname.setDatestamp(resultSet.getTimestamp("datestamp"));
            testResultWithUnitShortname.setNhsno(resultSet.getString("nhsno"));
            testResultWithUnitShortname.setPrepost(resultSet.getString("prepost"));
            testResultWithUnitShortname.setTestcode(resultSet.getString("testcode"));
            testResultWithUnitShortname.setUnitcode(resultSet.getString("unitcode"));
            testResultWithUnitShortname.setValue(resultSet.getString("value"));

            return testResultWithUnitShortname;
        }
    }
}
