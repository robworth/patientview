package com.solidstategroup.radar.web.pages.admin;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

// nothing actually on this page its just what the user lands on after being logged in
public class AdminsPage extends AdminsBasePage {
    public AdminsPage() {
        add(new BookmarkablePageLink<AdminUsersPage>("usersPageLink", AdminUsersPage.class));
        add(new BookmarkablePageLink<AdminConsultantsPage>("consultantsPageLink", AdminConsultantsPage.class));
    }
}
