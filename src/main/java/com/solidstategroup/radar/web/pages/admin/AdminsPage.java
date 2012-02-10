package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.web.pages.HomePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

// nothing actually on this page its just what the user lands on after being logged in
public class AdminsPage extends AdminsBasePage {
    public AdminsPage() {
        add(new BookmarkablePageLink<HomePage>("usersPageLink", AdminUsersPage.class));
    }
}
