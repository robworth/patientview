package com.worthsoln.actionutils;

import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

public class ActionUtils {

    public static void setUpNavLink(String parameter, HttpServletRequest request) {
        if ((parameter != null) && !("".equals(parameter))) {
            request.setAttribute("currentNav", parameter);
        }
    }

    public static String retrieveStringPropertyValue(String propertyName, ActionForm form, HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String propertyValue = BeanUtils.getProperty(form, propertyName);
        if (propertyValue == null) {
            propertyValue = (String) request.getAttribute(propertyName);
        }
        return propertyValue;
    }
    
}
