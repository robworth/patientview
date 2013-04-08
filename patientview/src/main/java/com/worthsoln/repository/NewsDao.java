package com.worthsoln.repository;

import com.worthsoln.patientview.model.News;
import com.worthsoln.patientview.model.Specialty;
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

    List<News> getAll(Specialty specialty);

    List<News> getNewsForEveryone(Specialty specialty);

    List<News> getAdminNewsForUnitCodes(List<String> unitCodes, Specialty specialty);

    List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes, Specialty specialty);

    List<News> getPatientNewsForUnitCodes(List<String> unitCodes, Specialty specialty);
}
