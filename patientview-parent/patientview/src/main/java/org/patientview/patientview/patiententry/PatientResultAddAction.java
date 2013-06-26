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

package org.patientview.patientview.patiententry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class PatientResultAddAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String year = BeanUtils.getProperty(form, "year");
        String month = BeanUtils.getProperty(form, "month");
        String day = BeanUtils.getProperty(form, "day");
        String hour = BeanUtils.getProperty(form, "hour");
        String minute = BeanUtils.getProperty(form, "minute");
        String resultName = BeanUtils.getProperty(form, "patientResultName");
        String resultCode1 = BeanUtils.getProperty(form, "patientResultCode1");
        String resultValue1 = BeanUtils.getProperty(form, "patientResultValue1");
        String resultCode2 = (BeanUtils.getProperty(form, "patientResultCode2") != null)
                ? BeanUtils.getProperty(form, "patientResultCode2") : "";
        String resultValue2 = (BeanUtils.getProperty(form, "patientResultValue2") != null)
                ? BeanUtils.getProperty(form, "patientResultValue2") : "";

        HttpSession session = request.getSession();
        Map<Long, PatientEnteredResult> results = (Map<Long, PatientEnteredResult>) session.getAttribute(resultName);

        if (null == results) {
            results = new TreeMap<Long, PatientEnteredResult>();
        }

        int intMonth = Integer.decode(month).intValue() - 1;

        if ("".equals(resultCode2)) {
            PatientEnteredResult result = new PatientEnteredResult(year, intMonth + "", day, hour, minute,
                    resultCode1, resultValue1);
            results.put((new Date()).getTime(), result);
        } else {
            PatientEnteredResult result = new PatientEnteredResult(year, intMonth + "", day, hour, minute,
                    resultCode1, resultValue1, resultCode2, resultValue2);
            results.put((new Date()).getTime(), result);
        }

        session.setAttribute(resultName, results);

        BeanUtils.setProperty(form, "patientResultCode1", null);
        BeanUtils.setProperty(form, "patientResultCode2", null);
        BeanUtils.setProperty(form, "patientResultValue1", null);
        BeanUtils.setProperty(form, "patientResultValue2", null);

        return mapping.findForward("success");
    }
}
