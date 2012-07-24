package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.Unit_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UnitDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UnitDaoImpl extends AbstractHibernateDAO<Unit> implements UnitDao {

    @Override
    public Unit get(String unitCode) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> from = criteria.from(Unit.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(Unit_.unitcode), unitCode));

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {

        if (sortByName) {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            items = session.find("from " + Unit.class.getName() + " order by name");
//            tx.commit();
//            HibernateUtil.closeSession();

            return null;
        } else {
            return getAll();
        }
    }

    @Override
    public List<Unit> getUnitsWithUser() {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        units = session. find("from " + Unit.class.getName() + " where unituser is not null and unituser <> ''");
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes) {

//        if (unitcodes.size() != 0) {
//            String unitCodeClause = "";
//            Object[] params = new Object[unitcodes.size()];
//            Type[] types = new Type[unitcodes.size()];
//
//            for (int i = 0; i < unitcodes.size(); i++) {
//                unitCodeClause += " unit.unitcode = ? or ";
//                params[i] = unitcodes.get(i);
//                types[i] = Hibernate.STRING;
//            }
//            unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);
//
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            items = session.find("from " + Unit.class.getName() + " as unit where " + unitCodeClause, params, types);
//            tx.commit();
//            HibernateUtil.closeSession();
//        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Unit> get(List<String> usersUnitCodes, String[] notTheseUnitCodes, String[] plusTheseUnitCodes) {

//        List<Unit> items;
//        String addAND = " AND ";
//        if (unitcodes.size() == 0) {
//            String queryString = "";
//            if (notTheseUnitCodes.length == 0) {
//                queryString = "from " + Unit.class.getName() + " order by name ";
//
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                items = session.find(queryString);
//                tx.commit();
//                HibernateUtil.closeSession();
//            } else {
//                queryString = "from " + Unit.class.getName() + " where ";
//                Object[] notTheseUnitcodeArray = new Object[notTheseUnitCodes.length];
//                Type[] typeArray = new Type[notTheseUnitCodes.length];
//
//                for (int i = 0; i < notTheseUnitCodes.length; i++) {
//                    queryString += " unitcode != ? " + addAND;
//                    notTheseUnitcodeArray[i] = notTheseUnitCodes[i];
//                    typeArray[i] = Hibernate.STRING;
//                }
//                queryString = queryString.substring(0, queryString.length() - addAND.length());
//
//                queryString += " order by name";
//
//                Session session = HibernateUtil.currentSession();
//                Transaction tx = session.beginTransaction();
//                items = session.find(queryString, notTheseUnitcodeArray, typeArray);
//                tx.commit();
//                HibernateUtil.closeSession();
//            }
//        } else {
//            String queryString = "from " + Unit.class.getName() + " as unit where ( ";
//            Object[] unitcodeArray = new Object[unitcodes.size() + notTheseUnitCodes.length];
//            Type[] typeArray = new Type[unitcodes.size() + notTheseUnitCodes.length];
//
//            for (int i = 0; i < unitcodes.size(); i++) {
//                queryString += " unit.unitcode = ? OR ";
//                unitcodeArray[i] = unitcodes.get(i);
//                typeArray[i] = Hibernate.STRING;
//            }
//            queryString = queryString.substring(0, queryString.length() - 3);
//
//            queryString += " ) ";
//
//            if (notTheseUnitCodes.length != 0) {
//                for (int i = 0; i < notTheseUnitCodes.length; i++) {
//                    queryString += addAND + " unitcode != ? ";
//                    unitcodeArray[unitcodes.size() + i] = notTheseUnitCodes[i];
//                    typeArray[unitcodes.size() + i] = Hibernate.STRING;
//                }
//            }
//
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            items = session.find(queryString, unitcodeArray, typeArray);
//            tx.commit();
//            HibernateUtil.closeSession();
//        }
//
//        for (String unitcode : plusTheseUnitCodes) {
//            Unit unit = new Unit(unitcode);
//            unit.setName(unitcode);
//            items.add(unit);
//        }


        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
