package com.worthsoln.actionutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
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

    /**
     * Anti-spam question for the quick contact form
     * Generate a very basic random math operation for users and sets the answer in the session
     * Sets the answer in session
     *
     * @return the math question
     */
    public static String getAntiSpamQuestion(HttpServletRequest request) {
        Random randomGenerator = new Random();

        int firstNumber = randomGenerator.nextInt(10);
        int secondNumber = randomGenerator.nextInt(10);

        String question = firstNumber + " + " + secondNumber;

        request.getSession().setAttribute("ANTI_SPAM_ANSWER", Integer.toString(firstNumber + secondNumber));

        return question;
    }

}
