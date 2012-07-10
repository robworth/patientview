package com.worthsoln.patientview;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.parser.XmlParserUtils;

public class ParseXml extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String xmlFileName = BeanUtils.getProperty(form, "src");
        DatabaseDAO dao = getDao(request);

        ServletContext context = request.getSession().getServletContext();
        XmlParserUtils.updateXmlData(context, new File(xmlFileName), dao);
        FindXmlFiles.putXmlFilesInRequest(request);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return null;
    }
}
