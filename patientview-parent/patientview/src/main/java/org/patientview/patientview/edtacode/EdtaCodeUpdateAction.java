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

package org.patientview.patientview.edtacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patientview.patientview.model.EdtaCode;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.patientview.patientview.logon.LogonUtils;

public class EdtaCodeUpdateAction extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EdtaCode edtaCode = LegacySpringUtils.getEdtaCodeManager().getEdtaCode(BeanUtils.getProperty(form, "edtaCode"));

        EdtaCodeUtils.build(form, edtaCode);

        LegacySpringUtils.getEdtaCodeManager().save(edtaCode);

        request.setAttribute("edtaCode", edtaCode);

        return LogonUtils.logonChecks(mapping, request);
    }

}
