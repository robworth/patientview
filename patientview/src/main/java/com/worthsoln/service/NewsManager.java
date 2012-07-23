package com.worthsoln.service;

import com.worthsoln.patientview.model.News;

import java.util.List;

/**
 *
 */
public interface NewsManager {

    News get(Long id);

    void save(News news);

    void delete(News news);

    List<News> getNewsForViewing();

    List<News> getNewsForEditing();
}
