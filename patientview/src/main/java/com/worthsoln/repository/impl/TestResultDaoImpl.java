package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.patientview.model.TestResultWithUnitShortname;
import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.repository.AbstractHibernateDAO;
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
public class TestResultDaoImpl extends AbstractHibernateDAO<TestResult> implements TestResultDao {

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

    @Override
    public List<TestResult> get(String nhsno, String unitcode) {

        String sql = "SELECT testresult.* FROM testresult WHERE testresult.nhsno = ? AND testresult.unitcode = ? " +
                " ORDER BY testcode, datestamp";

        List<Object> params = new ArrayList<Object>();
        params.add(nhsno);
        params.add(unitcode);

        return jdbcTemplate.query(sql, params.toArray(), new TestResultMapper());
    }

    @Override
    public String getLatestWeightFromResults(String nhsno, List<String> unitcodes) {
        List<Object> params = new ArrayList<Object>();
        params.add(nhsno);
        params.add("weight");

        String sql = "SELECT testresult.* FROM testresult WHERE testresult.nhsno = ? " +
                " AND testresult.testcode = ? AND testresult.unitcode IN (";

        for (int x = 0; x < unitcodes.size(); x++) {
            sql += " ? ";

            params.add(unitcodes.get(x));

            if (x != unitcodes.size() - 1) {
                sql += ",";
            }
        }

        sql += ")  ORDER BY datestamp DESC";


        List<TestResult> testResult = jdbcTemplate.query(sql, params.toArray(), new TestResultMapper());

        if (testResult != null && !testResult.isEmpty()) {
            return testResult.get(0).getValue();
        }

        return null;
    }

    private class TestResultMapper implements RowMapper<TestResult> {

        @Override
        public TestResult mapRow(ResultSet resultSet, int i) throws SQLException {

            TestResult testResult = new TestResult();
            testResult.setId(resultSet.getLong("id"));
            testResult.setDatestamp(resultSet.getTimestamp("datestamp"));
            testResult.setNhsno(resultSet.getString("nhsno"));
            testResult.setPrepost(resultSet.getString("prepost"));
            testResult.setTestcode(resultSet.getString("testcode"));
            testResult.setUnitcode(resultSet.getString("unitcode"));
            testResult.setValue(resultSet.getString("value"));

            return testResult;
        }
    }

    private class TestResultWithUnitShortnameMapper implements RowMapper<TestResultWithUnitShortname> {

        @Override
        public TestResultWithUnitShortname mapRow(ResultSet resultSet, int i) throws SQLException {

            TestResultWithUnitShortname testResultWithUnitShortname = new TestResultWithUnitShortname();
            testResultWithUnitShortname.setId(resultSet.getLong("id"));
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
