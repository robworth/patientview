package com.worthsoln.patientview.patiententry;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;


public class SysGreaterThanDiaValidator {

    public static boolean sysGreaterThanDia(
            Object bean,
            ValidatorAction va,
            Field field,
            ActionMessages errors,
            HttpServletRequest request) {

        String value = ValidatorUtils.getValueAsString(
                bean,
                field.getProperty());
        String sProperty2 = field.getVarValue("secondProperty");
        String value2 = ValidatorUtils.getValueAsString(
                bean,
                sProperty2);
        if (!GenericValidator.isBlankOrNull(value)) {
            int intValue = Integer.decode(value);
            int intValue2 = Integer.decode(value2);

            try {
                if (intValue2 <= intValue) {
                    errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
                    return false;
                }
            } catch (Exception e) {
                errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
                return false;
            }
        }

        return true;
    }
}
