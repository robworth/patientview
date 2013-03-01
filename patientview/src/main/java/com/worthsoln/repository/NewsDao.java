package com.worthsoln.repository;

import com.worthsoln.patientview.model.News;
import com.worthsoln.patientview.model.Tenancy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface NewsDao {

    News get(Long id);

    void save(News news);

    void delete(News news);

    List<News> getAll(Tenancy tenancy);

    List<News> getNewsForEveryone(Tenancy tenancy);

    List<News> getAdminNewsForUnitCodes(List<String> unitCodes, Tenancy tenancy);

    List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes, Tenancy tenancy);

    List<News> getPatientNewsForUnitCodes(List<String> unitCodes, Tenancy tenancy);
}
