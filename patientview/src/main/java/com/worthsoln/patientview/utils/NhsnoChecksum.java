package com.worthsoln.patientview.utils;

import com.worthsoln.patientview.user.UserUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;

public class NhsnoChecksum {

    public static boolean nhsnoChecksum(
            Object bean,
            ValidatorAction va,
            Field field,
            ActionMessages errors,
            HttpServletRequest request) {

        String value = ValidatorUtils.getValueAsString(
                bean,
                field.getProperty());

        boolean checksumValid = UserUtils.nhsNumberChecksumValid(value);

        if (!checksumValid) {
            errors.add(field.getKey(), Resources.getActionMessage(request, va, field));
        }

        return checksumValid;
    }
}
