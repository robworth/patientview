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

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.patientview.patientview.model.EdtaCode;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;

public final class EdtaCodeUtils {

    private EdtaCodeUtils() {
    }

    public static void addEdtaCodeToRequest(String code, String attributeName, HttpServletRequest request) {
        EdtaCode edtaCode = LegacySpringUtils.getEdtaCodeManager().getEdtaCode(code);
        request.setAttribute(attributeName, edtaCode);
    }

    public static List getCodeLinksForLinkType(String linkTypeMappingParameter) throws Exception {
        return LegacySpringUtils.getEdtaCodeManager().get(linkTypeMappingParameter);
    }

    public static void build(Object form, EdtaCode edtaCode) throws Exception {

        edtaCode.setDescription(BeanUtils.getProperty(form, "description"));
        edtaCode.setEdtaCode(BeanUtils.getProperty(form, "edtaCode"));
        edtaCode.setLinkType(BeanUtils.getProperty(form, "linkType"));
        edtaCode.setMedicalLink01(BeanUtils.getProperty(form, "medicalLink01"));
        edtaCode.setMedicalLink02(BeanUtils.getProperty(form, "medicalLink02"));
        edtaCode.setMedicalLink03(BeanUtils.getProperty(form, "medicalLink03"));
        edtaCode.setMedicalLink04(BeanUtils.getProperty(form, "medicalLink04"));
        edtaCode.setMedicalLink05(BeanUtils.getProperty(form, "medicalLink05"));
        edtaCode.setMedicalLink06(BeanUtils.getProperty(form, "medicalLink06"));
        edtaCode.setMedicalLinkText01(BeanUtils.getProperty(form, "medicalLinkText01"));
        edtaCode.setMedicalLinkText02(BeanUtils.getProperty(form, "medicalLinkText02"));
        edtaCode.setMedicalLinkText03(BeanUtils.getProperty(form, "medicalLinkText03"));
        edtaCode.setMedicalLinkText04(BeanUtils.getProperty(form, "medicalLinkText04"));
        edtaCode.setMedicalLinkText05(BeanUtils.getProperty(form, "medicalLinkText05"));
        edtaCode.setMedicalLinkText06(BeanUtils.getProperty(form, "medicalLinkText06"));
        edtaCode.setPatientLink01(BeanUtils.getProperty(form, "patientLink01"));
        edtaCode.setPatientLink02(BeanUtils.getProperty(form, "patientLink02"));
        edtaCode.setPatientLink03(BeanUtils.getProperty(form, "patientLink03"));
        edtaCode.setPatientLink04(BeanUtils.getProperty(form, "patientLink04"));
        edtaCode.setPatientLink05(BeanUtils.getProperty(form, "patientLink05"));
        edtaCode.setPatientLink06(BeanUtils.getProperty(form, "patientLink06"));
        edtaCode.setPatientLinkText01(BeanUtils.getProperty(form, "patientLinkText01"));
        edtaCode.setPatientLinkText02(BeanUtils.getProperty(form, "patientLinkText02"));
        edtaCode.setPatientLinkText03(BeanUtils.getProperty(form, "patientLinkText03"));
        edtaCode.setPatientLinkText04(BeanUtils.getProperty(form, "patientLinkText04"));
        edtaCode.setPatientLinkText05(BeanUtils.getProperty(form, "patientLinkText05"));
        edtaCode.setPatientLinkText06(BeanUtils.getProperty(form, "patientLinkText06"));
    }
}
