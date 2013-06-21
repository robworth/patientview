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

package org.patientview.patientview.splashpage;

import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.SplashPage;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SplashPageUpdateAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SplashPage splashPage;
        String idStr = BeanUtils.getProperty(form, "id");
        if (idStr != null && !idStr.equals("")) {
            splashPage = LegacySpringUtils.getSplashPageManager().get(Long.decode(idStr));
        } else {
            splashPage = new SplashPage();
        }

        BeanUtils.setProperty(splashPage, "name", BeanUtils.getProperty(form, "name"));
        BeanUtils.setProperty(splashPage, "headline", BeanUtils.getProperty(form, "headline"));
        BeanUtils.setProperty(splashPage, "bodytext", BeanUtils.getProperty(form, "bodytext"));
        BeanUtils.setProperty(splashPage, "unitcode", BeanUtils.getProperty(form, "unitcode"));
        String stringLive = BeanUtils.getProperty(form, "live");
        boolean isLive = "true".equals(stringLive);
        splashPage.setLive(isLive);

        LegacySpringUtils.getSplashPageManager().save(splashPage);

        request.setAttribute("splashPage", splashPage);

        List<SplashPage> splashpages = LegacySpringUtils.getSplashPageManager().getAll();
        request.setAttribute("splashpages", splashpages);

        return LogonUtils.logonChecks(mapping, request);
    }

}
