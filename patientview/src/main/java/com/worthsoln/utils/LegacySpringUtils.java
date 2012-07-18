package com.worthsoln.utils;

import com.worthsoln.service.CommentManager;
import com.worthsoln.service.ResultHeadingManager;
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.SplashPageManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;


/**
 *  Used to connect up the methods in the old model classes that do service layer operations to the newer service
 * managers. We can move stuff out of this when the service/DAO code gets moved out from the model classes.
 * When/if...
 */
public class LegacySpringUtils {

    private static CommentManager commentManager;

    private static ResultHeadingManager resultHeadingManager;

    private static SecurityUserManager securityUserManager;

    private static SplashPageManager splashPageManager;

    private static UnitManager unitManager;

    private static UserManager userManager;

    public static CommentManager getCommentManager() {
        return commentManager;
    }

    public static void setCommentManager(CommentManager commentManager) {
        LegacySpringUtils.commentManager = commentManager;
    }

    public static ResultHeadingManager getResultHeadingManager() {
        return resultHeadingManager;
    }

    public static void setResultHeadingManager(ResultHeadingManager resultHeadingManager) {
        LegacySpringUtils.resultHeadingManager = resultHeadingManager;
    }

    public static void setSecurityUserManager(SecurityUserManager securityUserManager) {
        LegacySpringUtils.securityUserManager = securityUserManager;
    }

    public static SecurityUserManager getSecurityUserManager() {
        return securityUserManager;
    }

    public static SplashPageManager getSplashPageManager() {
        return splashPageManager;
    }

    public static void setSplashPageManager(SplashPageManager splashPageManager) {
        LegacySpringUtils.splashPageManager = splashPageManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static void setUserManager(UserManager userManager) {
        LegacySpringUtils.userManager = userManager;
    }

    public static UnitManager getUnitManager() {
        return unitManager;
    }

    public static void setUnitManager(UnitManager unitManager) {
        LegacySpringUtils.unitManager = unitManager;
    }
}
