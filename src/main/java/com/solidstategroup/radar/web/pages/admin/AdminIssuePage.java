package com.solidstategroup.radar.web.pages.admin;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.*;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import com.solidstategroup.radar.service.IssueManager;
import com.solidstategroup.radar.model.Issue;

public class AdminIssuePage extends AdminsBasePage {

    @SpringBean
    private IssueManager issueManager;

    private static final String PARAM_ID = "ID";
    private boolean newIssue = false;

    public AdminIssuePage(PageParameters parameters) {
        super();

        final Issue issue;

        // if id is empty or -1 then its a new consultant else try pull back record
        StringValue idValue = parameters.get(PARAM_ID);
        if (idValue.isEmpty() || idValue.toLong() == -1) {
            issue = new Issue();
            newIssue = true;
        } else {
            issue = issueManager.getIssue(idValue.toLongObject());
        }

        CompoundPropertyModel<Issue> issueModel =
                new CompoundPropertyModel<Issue>(issue);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<Issue> userForm = new Form<Issue>("issueForm", issueModel) {
            protected void onSubmit() {
                try {
                    issueManager.saveIssue(getModelObject());

                    if (newIssue) {
                        setResponsePage(AdminIssuesPage.class);
                    }
                } catch (Exception e) {
                    error("Could not save issue: " + e.toString());
                }
            }
        };
        add(userForm);

        // TODO: add form elements

        AjaxLink delete = new AjaxLink("delete") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                try {
                    issueManager.deleteIssue(issue);
                    setResponsePage(AdminIssuesPage.class);
                } catch (Exception e) {
                    error("Could not delete issue " + e);
                    ajaxRequestTarget.add(feedback);
                }

            }
        };
        delete.setVisible(!newIssue);
        userForm.add(delete);

        userForm.add(new AjaxSubmitLink("save") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminIssuesPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancel") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminIssuesPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(Issue issue) {
        return new PageParameters().set(PARAM_ID, issue.getId());
    }
}
