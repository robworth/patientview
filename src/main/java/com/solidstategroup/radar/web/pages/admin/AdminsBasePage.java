package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({User.ROLE_ADMIN})
public class AdminsBasePage extends BasePage {
}
