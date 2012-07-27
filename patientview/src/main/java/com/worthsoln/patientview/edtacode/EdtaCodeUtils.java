package com.worthsoln.patientview.edtacode;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.utils.LegacySpringUtils;
import com.worthsoln.database.DatabaseDAO;
import org.apache.commons.beanutils.BeanUtils;

public class EdtaCodeUtils {

    public static EdtaCode retrieveEdtaCode(DatabaseDAO dao, String code) {
        return (EdtaCode) dao.retrieveItem(new EdtaCodeDao(new EdtaCode(code)));
    }

    public static void addEdtaCodeToRequest(String code, String attributeName, DatabaseDAO dao,
                                            HttpServletRequest request) {
        EdtaCode treatmentCode = EdtaCodeUtils.retrieveEdtaCode(dao, code);

        request.setAttribute(attributeName, treatmentCode);

        // todo test this - it looks wrong
        EdtaCode edtaCode = LegacySpringUtils.getEdtaCodeManager().getEdtaCode(code);
        request.setAttribute(attributeName, edtaCode);
    }

    public static List getCodeLinksForLinkType(String linkTypeMappingParameter) throws Exception {

        return LegacySpringUtils.getEdtaCodeManager().get(linkTypeMappingParameter);
    }

    public static EdtaCode build(Object form) throws Exception {

        EdtaCode edtaCode = new EdtaCode();

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

        return edtaCode;
    }
}
