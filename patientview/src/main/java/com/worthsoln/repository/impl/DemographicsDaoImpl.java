package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Demographics;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.DemographicsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "demographicsDao")
public class DemographicsDaoImpl extends AbstractHibernateDAO<Demographics> implements DemographicsDao {

    @Override
    public Demographics getDemographicsByNhsNo(String nhsno) {
        Query query = getEntityManager().createQuery(
                "SELECT radarNo " +
                "FROM tbl_demographics " +
                "WHERE NHS_NO = :nhsno");
        query.setParameter("nhsno", nhsno);

        List<Long> rawDemograpicsList = query.getResultList();

        Demographics demographics = new Demographics();

        if (rawDemograpicsList.size() != 0) {
            demographics.setRadarNo(rawDemograpicsList.get(0));
        }

        return demographics;
    }
}
