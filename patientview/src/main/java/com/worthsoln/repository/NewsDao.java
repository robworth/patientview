package com.worthsoln.repository;

import com.worthsoln.patientview.model.News;

import java.util.List;

/**
 *
 */
public interface NewsDao {

    News get(Long id);

    void delete(News news);

    List<News> getAll();

    List<News> getNewsForEveryone();

    List<News> getAdminNewsForUnitCodes(List<String> unitCodes);

    List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes);

    List<News> getPatientNewsForUnitCodes(List<String> unitCodes);
}
