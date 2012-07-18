package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.News;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.NewsDao;

import java.util.List;

/**
 *
 */
public class NewsDaoImpl extends AbstractHibernateDAO<News> implements NewsDao {

    /**
     * All calls need hsql = hsql + " order by news.datestamp desc ";
     *
     *
     *
     */

    @Override
    public List<News> getNewsForEveryone() {

//        hsql = "from " + News.class.getName() + " as news where news.everyone = ?";
//        params = new Object[]{1};
//        types = new Type[]{Hibernate.INTEGER};

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<News> getAdminNewsForUnitCodes(List<String> unitCodes) {

//        String unitCodeClause = "";
//        params = new Object[unitcodes.size() + 4];
//        types = new Type[unitcodes.size() + 4];
//        unitCodeClause = createUnitCodeStringParamsAndTypes(params, types, unitcodes, unitCodeClause);
//
//        hsql = "from " + News.class.getName() +
//                " as news where (( " + unitCodeClause + " ) and (news.admin = ? or news.patient = ?)) " +
//                " or news.everyone = ?";
//        params[unitcodes.size()] = "all";
//        params[unitcodes.size() + 1] = 1;
//        params[unitcodes.size() + 2] = 1;
//        params[unitcodes.size() + 3] = 1;
//
//        types[unitcodes.size()] = Hibernate.STRING;
//        types[unitcodes.size() + 1] = Hibernate.INTEGER;
//        types[unitcodes.size() + 2] = Hibernate.INTEGER;
//        types[unitcodes.size() + 3] = Hibernate.INTEGER;

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes) {

//        String unitCodeClause = "";
//        params = new Object[unitcodes.size() + 2];
//        types = new Type[unitcodes.size() + 2];
//
//        for (int i = 0; i < unitcodes.size(); i++) {
//            unitCodeClause += " news.unitcode = ? or ";
//            params[i] = unitcodes.get(i);
//            types[i] = Hibernate.STRING;
//        }
//        unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);
//
//        hsql = "from " + News.class.getName() +
//                " as news where (" + unitCodeClause + " and (news.admin = ? or news.patient = ?)) ";
//        params[unitcodes.size()] = 1;
//        params[unitcodes.size() + 1] = 1;
//
//        types[unitcodes.size()] = Hibernate.INTEGER;
//        types[unitcodes.size() + 1] = Hibernate.INTEGER;

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<News> getPatientNewsForUnitCodes(List<String> unitCodes) {

//        String unitCodeClause = "";
//        params = new Object[unitcodes.size() + 3];
//        types = new Type[unitcodes.size() + 3];
//        unitCodeClause = createUnitCodeStringParamsAndTypes(params, types, unitcodes, unitCodeClause);
//
//        hsql = "from " + News.class.getName() +
//                " as news where (( " + unitCodeClause + " ) and news.patient = ?) or news.everyone = ?";
//        params[unitcodes.size()] = "all";
//        params[unitcodes.size() + 1] = 1;
//        params[unitcodes.size() + 2] = 1;
//
//        types[unitcodes.size()] = Hibernate.STRING;
//        types[unitcodes.size() + 1] = Hibernate.INTEGER;
//        types[unitcodes.size() + 2] = Hibernate.INTEGER;

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

//    private static String createUnitCodeStringParamsAndTypes(Object[] params, Type[] types, List<String> unitcodes, String unitCodeClause) {
//        for (int i = 0; i < unitcodes.size(); i++) {
//            unitCodeClause += " news.unitcode = ? or ";
//            params[i] = unitcodes.get(i);
//            types[i] = Hibernate.STRING;
//        }
//        unitCodeClause += " news.unitcode = ? ";
//        return unitCodeClause;
//    }
}
