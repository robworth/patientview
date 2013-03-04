package com.worthsoln.patientview;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.parser.XmlParserUtils;

public class ParseXml extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String xmlFileName = BeanUtils.getProperty(form, "src");

        ServletContext context = request.getSession().getServletContext();
        XmlParserUtils.updateXmlData(context, new File(xmlFileName));
        FindXmlFiles.putXmlFilesInRequest(request);

        return LogonUtils.logonChecks(mapping, request);
    }

}
