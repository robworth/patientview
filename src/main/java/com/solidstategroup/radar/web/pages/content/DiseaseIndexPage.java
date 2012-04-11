package com.solidstategroup.radar.web.pages.content;


import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class DiseaseIndexPage extends BasePage {
    public DiseaseIndexPage() {
        add(new BookmarkablePageLink("srnsLink", SrnsPage.class));
    }
}
