package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.FeatureDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository(value = "featureDao")
public class FeatureDaoImpl extends AbstractHibernateDAO<Letter> implements FeatureDao {

    @Override
    public List<Unit> getUnitsForFeature(String feature) {

        if (null != feature && !"".equals(feature)) {
            String sql = "SELECT " +
                    "  u.*  " +
                    "FROM " +
                    "   unit u, " +
                    "   pv_feature_access f " +
                    "WHERE " +
                    "   f.unit_id = u.id " +
                    "AND " +
                    "   f.name = :featureName ";

            Query query = getEntityManager().createNativeQuery(sql, Unit.class);

            query.setParameter("featureName", feature);

            List<Unit> units = query.getResultList();

            return units;
        }

        return Collections.emptyList();
    }
}
