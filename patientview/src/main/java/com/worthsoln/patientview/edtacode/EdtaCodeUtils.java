package com.worthsoln.patientview.edtacode;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.utils.LegacySpringUtils;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.HibernateUtil;

public class EdtaCodeUtils {

    public static EdtaCode retrieveEdtaCode(DatabaseDAO dao, String code) {
        return (EdtaCode) dao.retrieveItem(new EdtaCodeDao(new EdtaCode(code)));
    }

    public static void addEdtaCodeToRequest(String code, String attributeName, DatabaseDAO dao,
                                            HttpServletRequest request) {
        EdtaCode treatmentCode = EdtaCodeUtils.retrieveEdtaCode(dao, code);

        request.setAttribute(attributeName, treatmentCode);
        HibernateUtil.retrievePersistentObjectAndAddToRequestWithIdParameter(request, EdtaCode.class, code,
            attributeName);
    }

    public static List getCodeLinksForLinkType(String linkTypeMappingParameter) throws Exception {

        return LegacySpringUtils.getEdtaCodeManager().get(linkTypeMappingParameter);
    }
}
