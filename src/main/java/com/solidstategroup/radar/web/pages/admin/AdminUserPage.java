package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.dao.UserDao;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

public class AdminUserPage extends AdminsBasePage {

    @SpringBean
    private UserDao userDao;

    private static final String PARAM_ID = "ID";
    private ProfessionalUser user;

    public AdminUserPage() {
        this(new PageParameters());
    }

    public AdminUserPage(PageParameters parameters) {
        super();

        StringValue idValue = parameters.get(PARAM_ID);
        if (idValue.isEmpty() || idValue.toLong() == -1) {
            user = new ProfessionalUser();
        } else {
            userDao.getProfessionalUser(idValue.toLongObject());    
        }
    }
    
    public static PageParameters getPageParameters(ProfessionalUser user) {
        return new PageParameters().set(PARAM_ID, user.getId());
    }
}
