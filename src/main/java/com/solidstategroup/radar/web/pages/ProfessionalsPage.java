package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.user.User;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class ProfessionalsPage extends BasePage {
}
