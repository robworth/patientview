package com.worthsoln.patientview.sharingthoughts;

import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.patientview.model.SharedThought;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SharingThoughtsSaveAction extends BaseAction {

    private List<String> errors = new ArrayList<String>();

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        errors = new ArrayList<String>();

        DynaActionForm dynaForm = (DynaActionForm) form;

        String forwardMapping = "";

        User user = UserUtils.retrieveUser(request);

        int positiveNegative = (Integer) dynaForm.get(SharingThoughts.POSITIVE_NEGATIVE);

        boolean isSubmitted = (null != request.getParameter(SharingThoughts.SUBMIT));

        if (isSubmitted) {
            if (positiveNegative == 1) {
                if (validatePositiveThought(dynaForm)) {
                    forwardMapping = "submit";
                } else {
                    request.setAttribute(SharingThoughts.ERRORS_PARAM, errors);
                    return mapping.findForward("input_positive");
                }
            } else {
                if (validateNegativeThought(dynaForm)) {
                    forwardMapping = "submit";
                } else {
                    request.setAttribute(SharingThoughts.ERRORS_PARAM, errors);
                    return mapping.findForward("input_negative");
                }
            }
        } else {
            forwardMapping = "savedraft";
        }

        Long thoughtId = null;
        try {
            thoughtId = (Long) dynaForm.get(SharingThoughts.ID);
        } catch (Exception ignored) {
        }

        Boolean isPatient = null;
        try {
            isPatient = (Boolean) dynaForm.get(SharingThoughts.IS_PATIENT);
        } catch (Exception ignored) {
        }

        Boolean isPrincipalCarer = null;
        try {
            isPrincipalCarer = (Boolean) dynaForm.get(SharingThoughts.IS_PRINCIPAL_CARER);
        } catch (Exception ignored) {
        }

        Boolean isRelative = null;
        try {
            isRelative = (Boolean) dynaForm.get(SharingThoughts.IS_RELATIVE);
        } catch (Exception ignored) {
        }

        Boolean isFriend = null;
        try {
            isFriend = (Boolean) dynaForm.get(SharingThoughts.IS_FRIEND);
        } catch (Exception ignored) {
        }

        Boolean isAboutMe = null;
        try {
            isAboutMe = (Boolean) dynaForm.get(SharingThoughts.IS_ABOUT_ME);
        } catch (Exception ignored) {
        }

        Boolean isAboutOther = null;
        try {
            isAboutOther = (Boolean) dynaForm.get(SharingThoughts.IS_ABOUT_OTHER);
        } catch (Exception ignored) {
        }

        Boolean isAnonymous = null;
        try {
            isAnonymous = (Boolean) dynaForm.get(SharingThoughts.IS_ANONYMOUS);
        } catch (Exception ignored) {
        }

        Date dateOfExperience = null;
        try {
            dateOfExperience = convertFormDateString(SharingThoughts.DATE_OF_EXPERIENCE, dynaForm);
        } catch (Exception ignored) {
        }

        String location = null;
        try {
            location = (String) dynaForm.get(SharingThoughts.LOCATION);
        } catch (Exception ignored) {
        }

        String description = null;
        try {
            description = (String) dynaForm.get(SharingThoughts.DESCRIPTION);
        } catch (Exception ignored) {
        }

        String suggestedAction = null;
        try {
            suggestedAction = (String) dynaForm.get(SharingThoughts.SUGGESTED_ACTION);
        } catch (Exception ignored) {
        }

        String concernReason = null;
        try {
            concernReason = (String) dynaForm.get(SharingThoughts.CONCERN_REASON);
        } catch (Exception ignored) {
        }

        int likelihoodOfRecurrence = 0;
        try {
            likelihoodOfRecurrence = (Integer) dynaForm.get(SharingThoughts.LIKELIHOOD_0F_RECURRENCE);
        } catch (Exception ignored) {
        }

        int howSerious = 0;
        try {
            howSerious = (Integer) dynaForm.get(SharingThoughts.HOW_SERIOUS);
        } catch (Exception ignored) {
        }

        SharedThought thought;
        if (thoughtId == null) {
            thought = new SharedThought(user, positiveNegative, isPatient, isPrincipalCarer, isRelative,
                    isFriend, isAboutMe, isAboutOther, isAnonymous, dateOfExperience, location, description,
                    suggestedAction,
                    concernReason, likelihoodOfRecurrence, howSerious, isSubmitted);
        } else {
            thought = new SharedThought(thoughtId, user, positiveNegative, isPatient, isPrincipalCarer, isRelative,
                    isFriend, isAboutMe, isAboutOther, isAnonymous, dateOfExperience, location, description,
                    suggestedAction,
                    concernReason, likelihoodOfRecurrence, howSerious, isSubmitted);
        }
        getSharedThoughtManager().save(thought);

        if (!isSubmitted) {
            SharingThoughts.putThoughtListInRequest(request, user, true);
            SharingThoughts.putThoughtListInRequest(request, user, false);
        }

        return mapping.findForward(forwardMapping);
    }

    private boolean validatePositiveThought(DynaActionForm form) {
        boolean isValid = true;

        if (null == form.get(SharingThoughts.IS_PATIENT)) {
            errors.add("Please tell us if you are the patient");
            isValid = false;
        }

        if (null != form.get(SharingThoughts.IS_PATIENT) && !(Boolean) form.get(SharingThoughts.IS_PATIENT) &&
                null == form.get(SharingThoughts.IS_PRINCIPAL_CARER) && null == form.get(SharingThoughts.IS_RELATIVE)
                && null == form.get(SharingThoughts.IS_FRIEND)) {
            errors.add("If you are not the patient, please indicate who you are");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.IS_ABOUT_ME) && null == form.get(SharingThoughts.IS_ABOUT_OTHER)) {
            errors.add("Please indicate who this is about");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.IS_ANONYMOUS)) {
            errors.add("Please show whether you wish to remain anonymous");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.DATE_OF_EXPERIENCE) ||
                "".equals(form.get(SharingThoughts.DATE_OF_EXPERIENCE)) ||
                null == convertFormDateString(SharingThoughts.DATE_OF_EXPERIENCE, form)) {
            errors.add("Please enter a valid date");
            isValid = false;
        }

        if ((null == form.get(SharingThoughts.LOCATION)) || "".equals(form.get(SharingThoughts.LOCATION))) {
            errors.add("Please enter a location");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.DESCRIPTION) || "".equals(form.get(SharingThoughts.DESCRIPTION))) {
            errors.add("Please tell us what was good");
            isValid = false;
        }

        if ((null == form.get(SharingThoughts.SUGGESTED_ACTION)) || "".equals(form.get(SharingThoughts
                .SUGGESTED_ACTION))) {
            errors.add("Please tell us what can be done");
            isValid = false;
        }

        return isValid;
    }

    private boolean validateNegativeThought(DynaActionForm form) {
        boolean isValid = true;

        if (null == form.get(SharingThoughts.IS_PATIENT)) {
            errors.add("Please tell us if you are the patient");
            isValid = false;
        }

        if (null != form.get(SharingThoughts.IS_PATIENT) && !(Boolean) form.get(SharingThoughts.IS_PATIENT) &&
                null == form.get(SharingThoughts.IS_PRINCIPAL_CARER) && null == form.get(SharingThoughts.IS_RELATIVE)
                && null == form.get(SharingThoughts.IS_FRIEND)) {
            errors.add("If you are not the patient, please indicate who you are");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.IS_ABOUT_ME) && null == form.get(SharingThoughts.IS_ABOUT_OTHER)) {
            errors.add("Please indicate who this is about");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.IS_ANONYMOUS)) {
            errors.add("Please show whether you wish to remain anonymous");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.DATE_OF_EXPERIENCE) ||
                "".equals(form.get(SharingThoughts.DATE_OF_EXPERIENCE)) ||
                null == convertFormDateString(SharingThoughts.DATE_OF_EXPERIENCE, form)) {
            errors.add("Please enter a valid date");
            isValid = false;
        }

        if ((null == form.get(SharingThoughts.LOCATION)) || "".equals(form.get(SharingThoughts.LOCATION))) {
            errors.add("Please enter a location");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.DESCRIPTION) ||
                "".equals(form.get(SharingThoughts.DESCRIPTION))) {
            errors.add("Please describe your experience or concern");
            isValid = false;
        }

        if ((null == form.get(SharingThoughts.CONCERN_REASON)) || "".equals(form.get(SharingThoughts.CONCERN_REASON)
        )) {
            errors.add("Please explain the reason");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.LIKELIHOOD_0F_RECURRENCE)) {
            errors.add("Please show how likely this is to happen again");
            isValid = false;
        }

        if ((null == form.get(SharingThoughts.SUGGESTED_ACTION)) || "".equals(form.get(SharingThoughts
                .SUGGESTED_ACTION))) {
            errors.add("Please tell us what can be done");
            isValid = false;
        }

        if (null == form.get(SharingThoughts.HOW_SERIOUS)) {
            errors.add("Please tell us how serious this is");
            isValid = false;
        }

        return isValid;
    }
}
