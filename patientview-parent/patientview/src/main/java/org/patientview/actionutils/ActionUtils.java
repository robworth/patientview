/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.actionutils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public final class ActionUtils {

    private static final int RANDOM_RANGE = 10;

    private ActionUtils() {

    }

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

        int firstNumber = randomGenerator.nextInt(RANDOM_RANGE);
        int secondNumber = randomGenerator.nextInt(RANDOM_RANGE);

        String question = firstNumber + " + " + secondNumber;

        request.getSession().setAttribute("ANTI_SPAM_ANSWER", Integer.toString(firstNumber + secondNumber));

        return question;
    }
}
