package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.User_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository(value = "userDao")
public class UserDaoImpl extends AbstractHibernateDAO<User> implements UserDao {

    private static final String RADAR_PROFESSIONAL_USER_TABLE_NAME = "tbl_users"; // rdr specific user
    private static final String RADAR_PROFESSIONAL_USER_ID_FIELD_NAME = "uID";
    private static final String RADAR_PROFESSIONAL_USER_SURNAME_FIELD_NAME = "uSurname";
    private static final String RADAR_PROFESSIONAL_USER_FORENAME_FIELD_NAME = "uForename";
    private static final String RADAR_PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME = "uCentre";

    private static final String RADAR_USER_MAPPING_TABLE_NAME = "rdr_user_mapping";
    private static final String RADAR_USER_MAPPING_USER_ID_FIELD_NAME = "userId";
    private static final String RADAR_USER_MAPPING_ROLE_FIELD_NAME = "role";
    private static final String RADAR_USER_MAPPING_RADAR_USER_ID_FIELD_NAME = "radarUserId";

    private JdbcTemplate jdbcTemplate;

    @Inject
    private DataSource dataSource;

    private SimpleJdbcInsert radarProfessionalUsersInsert;

    private SimpleJdbcInsert radarUserMappingInsert;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);

        radarProfessionalUsersInsert = new SimpleJdbcInsert(dataSource).withTableName(
                RADAR_PROFESSIONAL_USER_TABLE_NAME)
                .usingGeneratedKeyColumns(RADAR_PROFESSIONAL_USER_ID_FIELD_NAME)
                .usingColumns(RADAR_PROFESSIONAL_USER_SURNAME_FIELD_NAME, RADAR_PROFESSIONAL_USER_FORENAME_FIELD_NAME,
                        RADAR_PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME);

        radarUserMappingInsert = new SimpleJdbcInsert(dataSource).withTableName(RADAR_USER_MAPPING_TABLE_NAME)
                .usingColumns(RADAR_USER_MAPPING_USER_ID_FIELD_NAME, RADAR_USER_MAPPING_ROLE_FIELD_NAME,
                        RADAR_USER_MAPPING_RADAR_USER_ID_FIELD_NAME);
    }

    @Override
    public User get(String username) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get(User_.username), username));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean userExistsInRadar(Long userId) {
        Integer count = jdbcTemplate.queryForInt("SELECT count(*) FROM rdr_user_mapping WHERE userId = " +
                userId);

        return count != null && count > 0;
    }

    @Override
    public void createProfessionalUserInRadar(User user, Unit unit) {
        String[] name =user.getName().split(" ");
        String forename = name[0];
        String surname = "";

        if (name.length > 1) {
            surname = name[1];

            if (name.length > 2) {
                for (int x = 2; x < name.length; x++) {
                    surname += " " + name[x];
                }
            }
        }

        // create the professional user record first
        Map<String, Object> professionalUserMap = new HashMap<String, Object>();
        professionalUserMap.put(RADAR_PROFESSIONAL_USER_SURNAME_FIELD_NAME, surname);
        professionalUserMap.put(RADAR_PROFESSIONAL_USER_FORENAME_FIELD_NAME, forename);
        professionalUserMap.put(RADAR_PROFESSIONAL_USER_CENTRE_ID_FIELD_NAME, unit.getId());

        Number professionalUserId = radarProfessionalUsersInsert.executeAndReturnKey(professionalUserMap);

        // then create the mapping from the professional user to the normal user
        Map<String, Object> userMappingMap = new HashMap<String, Object>();
        userMappingMap.put(RADAR_USER_MAPPING_USER_ID_FIELD_NAME, user.getId());
        userMappingMap.put(RADAR_USER_MAPPING_ROLE_FIELD_NAME, "ROLE_PROFESSIONAL");
        userMappingMap.put(RADAR_USER_MAPPING_RADAR_USER_ID_FIELD_NAME, professionalUserId);

        radarUserMappingInsert.execute(userMappingMap);
    }
}
