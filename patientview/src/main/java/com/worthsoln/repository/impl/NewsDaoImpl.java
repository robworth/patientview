package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.News;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.NewsDao;

import java.util.List;

/**
 *
 */
public class NewsDaoImpl extends AbstractHibernateDAO<News> implements NewsDao {

    @Override
    public List<News> getNewsForEveryone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
