package com.worthsoln.repository;

import com.worthsoln.patientview.model.News;

import java.util.List;

/**
 *
 */
public interface NewsDao {

    List<News> getNewsForEveryone();
}
