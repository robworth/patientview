package com.worthsoln.patientview.sharingthoughts;


import com.worthsoln.patientview.model.SharedThought;
import com.worthsoln.patientview.model.User;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SharingThoughts {

    public static final String ID = "id";
    public static final String IS_PATIENT = "patient";
    public static final String POSITIVE_NEGATIVE = "positiveNegative";
    public static final String IS_PRINCIPAL_CARER = "principalCarer";
    public static final String IS_RELATIVE = "relative";
    public static final String IS_FRIEND = "friend";
    public static final String IS_ABOUT_ME = "aboutMe";
    public static final String IS_ABOUT_OTHER = "aboutOther";
    public static final String IS_ANONYMOUS = "anonymous";
    public static final String DATE_OF_EXPERIENCE = "dateOfExperience";
    public static final String DATE_OF_EXPERIENCE_FORMATTED_DATE = "dateOfExperienceFormattedDate";
    public static final String DATE_OF_EXPERIENCE_FORMATTED_DATE_TIME = "dateOfExperienceFormattedDateTime";
    public static final String LOCATION = "location";
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_BEGINNING = "descriptionBeginning";
    public static final String SUGGESTED_ACTION = "suggestedAction";
    public static final String CONCERN_REASON = "concernReason";
    public static final String LIKELIHOOD_0F_RECURRENCE = "likelihoodOfRecurrence";
    public static final String HOW_SERIOUS = "howSerious";
    public static final String DATE_LAST_SAVED = "dateLastSaved";
    public static final String DATE_LAST_SAVED_FORMATTED_DATE = "dateLastSavedFormattedDate";
    public static final String DATE_LAST_SAVED_FORMATTED_DATE_TIME = "dateLastSavedFormattedDateTime";
    public static final String SUBMIT = "Submit";
    public static final String ERRORS_PARAM = "errors";
    public static final String USERS_THOUGHTS_DRAFT_PARAM = "usersDraftSharedThoughts";
    public static final String USERS_THOUGHTS_SUBMITTED_PARAM = "usersSubmittedSharedThoughts";
    public static final String THOUGHT_PARAM = "thought";

    static void putThoughtListInRequest(HttpServletRequest request, User user, boolean submitted) {
        String attributeName = (submitted) ? SharingThoughts.USERS_THOUGHTS_SUBMITTED_PARAM : SharingThoughts.USERS_THOUGHTS_DRAFT_PARAM;

        List<SharedThought> usersSharedThoughtsDrafts = LegacySpringUtils.getSharedThoughtManager().getUsersThoughts(user.getId(), submitted);
        request.setAttribute(attributeName, usersSharedThoughtsDrafts);
    }

}
