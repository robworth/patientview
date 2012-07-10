package com.worthsoln;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
        }
    }

    public static final ThreadLocal SESSION = new ThreadLocal();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) SESSION.get();
        if (s == null) {
            s = SESSION_FACTORY.openSession();
            SESSION.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) SESSION.get();
        SESSION.set(null);
        if (s != null) {
            s.close();
        }
    }

    public static String[] getPropertyNames(Class className) throws HibernateException {
        return SESSION_FACTORY.getClassMetadata(className).getPropertyNames();
    }

    public static String getIdentifierPropertyName(Class className) throws HibernateException {
        return SESSION_FACTORY.getClassMetadata(className).getIdentifierPropertyName();
    }

    public static void retrievePersistentObjectAndAddToRequest(HttpServletRequest request, Class classs,
                                                               String parameterName) {
        String parameterValue = request.getParameter(parameterName);
        retrievePersistentObjectAndAddToRequestWithIdParameter(request, classs, parameterValue, parameterName);
    }

    public static void retrievePersistentObjectAndAddToRequest(HttpServletRequest request, Class classs,
                                                               String parameterName, String attributeName) {
        String parameterValue = request.getParameter(parameterName);
        retrievePersistentObjectAndAddToRequestWithIdParameter(request, classs, parameterValue, attributeName);
    }

    public static void retrievePersistentObjectAndAddToRequestWithIdParameter(HttpServletRequest request, Class classs,
                                                                              String parameterValue,
                                                                              String attributeName) {
        if (parameterValue != null) {
            Object persistentItem = getPersistentObject(classs, parameterValue);
            request.setAttribute(attributeName, persistentItem);
        }
    }

    public static void retrievePersistentObjectAndAddToSession(HttpServletRequest request, Class classs,
                                                               String parameterName) {
        String parameterValue = request.getParameter(parameterName);
        retrievePersistentObjectAndAddToSessionWithIdParameter(request, classs, parameterValue, parameterName);
    }

    public static void retrievePersistentObjectAndAddToSession(HttpServletRequest request, Class classs,
                                                               String parameterName, String attributeName) {
        String parameterValue = request.getParameter(parameterName);
        retrievePersistentObjectAndAddToSessionWithIdParameter(request, classs, parameterValue, attributeName);
    }

    public static void retrievePersistentObjectAndAddToSessionWithIdParameter(HttpServletRequest request, Class classs,
                                                                              String parameterValue,
                                                                              String attributeName) {
        if (parameterValue != null) {
            Object persistentItem = getPersistentObject(classs, parameterValue);
            request.getSession().setAttribute(attributeName, persistentItem);
        }
    }

    public static Object getPersistentObject(Class classs, String parameterValue) {
        Object persistentItem = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            persistentItem = session.get(classs, parameterValue);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persistentItem;
    }

    public static Object getPersistentObject(Class classs, Integer parameterValue) {
        Object persistentItem = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            persistentItem = session.get(classs, parameterValue);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return persistentItem;
    }

    public static void extractDataFromFormMakeObjectAndAdd(Object objectToBuild, ActionForm form,
                                                           HttpServletRequest request, String attributeName) {
        try {
            buildObject(objectToBuild, form);
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.save(objectToBuild);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        request.setAttribute(attributeName, objectToBuild);
    }

    public static void extractDataFromFormMakeObjectAndUpdate(Object objectToBuild, ActionForm form,
                                                              HttpServletRequest request, String attributeName) {
        try {
            buildObject(objectToBuild, form);
            saveOrUpdateWithTransaction(objectToBuild);
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        request.setAttribute(attributeName, objectToBuild);
    }

    public static void saveOrUpdateWithTransaction(Object objectToBuild) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(objectToBuild);
        tx.commit();
        HibernateUtil.closeSession();
    }

    public static void saveWithTransaction(Object objectToBuild) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        session.save(objectToBuild);
        tx.commit();
        HibernateUtil.closeSession();
    }

    private static void buildObject(Object objectToBuild, ActionForm form)
            throws HibernateException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String identifierPropertyName = HibernateUtil.getIdentifierPropertyName(objectToBuild.getClass());
        BeanUtils.setProperty(objectToBuild, identifierPropertyName,
                BeanUtils.getProperty(form, identifierPropertyName));
        String[] colNames = HibernateUtil.getPropertyNames(objectToBuild.getClass());
        for (int i = 0; i < colNames.length; i++) {
            BeanUtils.setProperty(objectToBuild, colNames[i], BeanUtils.getProperty(form, colNames[i]));
        }
    }

    public static void putListInRequest(Class classs, String attributeName, HttpServletRequest request)
            throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List items = session.find("from " + classs.getName());
        tx.commit();
        HibernateUtil.closeSession();
        request.setAttribute(attributeName, items);
    }

    public static void deleteFromDatabase(Class classs, int id) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Object persistentItem = session.get(classs, id);
        session.delete(persistentItem);
        tx.commit();
        HibernateUtil.closeSession();
    }


}
