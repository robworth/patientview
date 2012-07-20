package com.worthsoln;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

public class HibernateUtil {




    public static void retrievePersistentObjectAndAddToRequest(HttpServletRequest request, Class classs,
                                                               String parameterName) {
        String parameterValue = request.getParameter(parameterName);
        retrievePersistentObjectAndAddToRequestWithIdParameter(request, classs, parameterValue, parameterName);
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
//        try {
//            buildObject(objectToBuild, form);
//            saveOrUpdateWithTransaction(objectToBuild);
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        request.setAttribute(attributeName, objectToBuild);
    }

    public static void saveOrUpdateWithTransaction(Object objectToBuild) throws Exception {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        session.saveOrUpdate(objectToBuild);
//        tx.commit();
//        HibernateUtil.closeSession();
    }

    public static void saveWithTransaction(Object objectToBuild) throws Exception {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        session.save(objectToBuild);
//        tx.commit();
//        HibernateUtil.closeSession();
    }

    private static void buildObject(Object objectToBuild, ActionForm form)
            throws Exception, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        String identifierPropertyName = HibernateUtil.getIdentifierPropertyName(objectToBuild.getClass());
//        BeanUtils.setProperty(objectToBuild, identifierPropertyName,
//                BeanUtils.getProperty(form, identifierPropertyName));
//        String[] colNames = HibernateUtil.getPropertyNames(objectToBuild.getClass());
//        for (int i = 0; i < colNames.length; i++) {
//            BeanUtils.setProperty(objectToBuild, colNames[i], BeanUtils.getProperty(form, colNames[i]));
//        }
    }
}
