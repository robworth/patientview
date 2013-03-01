package com.worthsoln.repository.impl;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Patient_;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.PatientDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "patientDao")
public class PatientDaoImpl extends AbstractHibernateDAO<Patient> implements PatientDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Patient get(String nhsno, String unitcode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
        Root<Patient> from = criteria.from(Patient.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Patient_.nhsno), nhsno));
        wherePredicates.add(builder.equal(from.get(Patient_.centreCode), unitcode));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String nhsno, String unitcode) {

        if (nhsno == null || nhsno.length() == 0 || unitcode == null || unitcode.length() == 0) {
            throw new IllegalArgumentException("Required parameters nhsno and unitcode to delete patient");
        }

        Patient patient = get(nhsno, unitcode);

        if (patient != null) {
            delete(patient);
        }
    }

    @Override
    public List<Patient> get(String centreCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
        Root<Patient> from = criteria.from(Patient.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Patient_.centreCode), centreCode));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List getUnitPatientsWithTreatmentDao(String unitcode, String nhsno, String name, boolean showgps,
                                                Tenancy tenancy) {

        DatabaseDAO dao = new DatabaseDAO("patientview");

        UnitPatientsWithTreatmentDao patientDao = new UnitPatientsWithTreatmentDao(unitcode, nhsno, name, showgps,
                tenancy);
        return dao.retrieveList(patientDao);

        /*

        todo could replace this with the following to run with JPA native

        String sql = "SELECT "
                + "user.username,  user.password, user.name, user.email, usermapping.nhsno, usermapping.unitcode, "
                + "user.firstlogon, patient.treatment "
                + "FROM user, tenancyuserrole, usermapping "
                + "LEFT JOIN patient ON usermapping.nhsno = patient.nhsno AND usermapping.unitcode = patient.centreCode "
                + "WHERE tenancyuserrole.role = (?1) "
                + "AND user.username = usermapping.username "
                + "AND user.id = tenancyuserrole.user_id "
                + "AND usermapping.unitcode <> '" + UnitUtils.PATIENT_ENTERS_UNITCODE + "' ";

        if (!"".equals(unitcode)) {
            sql += "AND usermapping.unitcode = (?2) ";
        }
        sql += "AND usermapping.nhsno LIKE (?3) AND user.name LIKE (?4) ";
        if (!showgps) {
            sql += "AND user.name NOT LIKE (?5) ";
        }
        sql += "AND tenancyuserrole.tenancy_id = (?6) ";

        sql += "ORDER BY user.name ASC ";

        Query query = getEntityManager().createNativeQuery(sql);

        query.setParameter(1, "patient");
        if (!"".equals(unitcode)) {
            query.setParameter(2, unitcode);
        }
        query.setParameter(3, "%" + nhsno + "%");
        query.setParameter(4, "%" + name + "%");
        if (!showgps) {
            query.setParameter(5, "%-GP");
        }
        query.setParameter(6, tenancy.getId());

        List results = query.getResultList();

        return results;

        */
    }

    @Override
    public List getUnitPatientsAllWithTreatmentDao(String unitcode, Tenancy tenancy) {

        DatabaseDAO dao = new DatabaseDAO("patientview");

        UnitPatientsAllWithTreatmentDao patientDao = new UnitPatientsAllWithTreatmentDao(unitcode, tenancy);
        return dao.retrieveList(patientDao);
    }

    @Override
    public List<Patient> getUktPatients() {

        String sql = "SELECT DISTINCT patient.id, patient.nhsno, patient.surname, patient.forename, " +
                " patient.dateofbirth, patient.postcode FROM patient, user, usermapping " +
                " WHERE patient.nhsno REGEXP '^[0-9]{10}$' AND patient.nhsno = usermapping.nhsno " +
                "AND user.username = usermapping.username " +
                " AND usermapping.username NOT LIKE '%-GP' AND user.dummypatient = 0";

        return jdbcTemplate.query(sql, new PatientMapper());
    }

    private class PatientMapper implements RowMapper<Patient> {

        @Override
        public Patient mapRow(ResultSet resultSet, int i) throws SQLException {

            Patient patient = new Patient();
            patient.setId(resultSet.getLong("id"));
            patient.setNhsno(resultSet.getString("nhsno"));
            patient.setSurname(resultSet.getString("surname"));
            patient.setForename(resultSet.getString("forename"));
            patient.setDateofbirth(resultSet.getString("dateofbirth"));
            patient.setPostcode(resultSet.getString("postcode"));

            return patient;
        }
    }


}
