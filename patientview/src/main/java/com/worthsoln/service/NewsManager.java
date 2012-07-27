package com.worthsoln.service;

import com.worthsoln.patientview.model.News;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface NewsManager {

    News get(Long id);

    void save(News news);

    void delete(News news);

    List<News> getNewsForViewing();

    List<News> getNewsForEditing();
}
