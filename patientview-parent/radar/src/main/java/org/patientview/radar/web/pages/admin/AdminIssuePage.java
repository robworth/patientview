package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.Issue;
import org.patientview.radar.model.enums.IssuePriority;
import org.patientview.radar.model.enums.IssueStatus;
import org.patientview.radar.model.enums.IssueType;
import org.patientview.radar.service.IssueManager;
import org.patientview.radar.web.RadarApplication;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.Arrays;

public class AdminIssuePage extends AdminsBasePage {

    @SpringBean
    private IssueManager issueManager;

    private static final String PARAM_ID = "ID";

    public AdminIssuePage(PageParameters parameters) {
        super();

        final Issue issue;

        // if id is empty or -1 then its a new consultant else try pull back record
        StringValue idValue = parameters.get(PARAM_ID);
        if (idValue.isEmpty() || idValue.toLong() == -1) {
            issue = new Issue();
        } else {
            issue = issueManager.getIssue(idValue.toLongObject());
        }

        CompoundPropertyModel<Issue> issueModel =
                new CompoundPropertyModel<Issue>(issue);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<Issue> issueForm = new Form<Issue>("issueForm", issueModel) {
            protected void onSubmit() {
                try {
                    issueManager.saveIssue(getModelObject());
                    setResponsePage(AdminIssuesPage.class);
                } catch (Exception e) {
                    error("Could not save issue: " + e.toString());
                }
            }
        };
        add(issueForm);

        DropDownChoice<IssueType> type = new DropDownChoice<IssueType>("type", Arrays.asList(IssueType.values()));
        type.setRequired(true);
        issueForm.add(type);

        issueForm.add(new RequiredTextField("page"));

        DateTextField dateLogged = new DateTextField("dateLogged", RadarApplication.DATE_PATTERN);
        dateLogged.setRequired(true);
        dateLogged.add(new DatePicker());
        issueForm.add(dateLogged);

        DateTextField dateResolved = new DateTextField("dateResolved", RadarApplication.DATE_PATTERN);
        dateResolved.add(new DatePicker());
        issueForm.add(dateResolved);

        TextArea description = new TextArea("description");
        description.setRequired(true);
        issueForm.add(description);

        TextArea comments = new TextArea("comments");
        issueForm.add(comments);

        DropDownChoice<IssuePriority> priority = new DropDownChoice<IssuePriority>("priority",
                Arrays.asList(IssuePriority.values()));
        priority.setRequired(true);
        issueForm.add(priority);

        DropDownChoice<IssueStatus> status = new DropDownChoice<IssueStatus>("status",
                Arrays.asList(IssueStatus.values()));
        status.setRequired(true);
        issueForm.add(status);

        DateTextField updated = new DateTextField("updated", RadarApplication.DATE_PATTERN);
        updated.add(new DatePicker());
        issueForm.add(updated);

        issueForm.add(new AjaxSubmitLink("saveTop") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminIssuesPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        issueForm.add(new AjaxLink("cancelTop") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminIssuesPage.class);
            }
        });

        issueForm.add(new AjaxSubmitLink("saveBottom") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminIssuesPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        issueForm.add(new AjaxLink("cancelBottom") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminIssuesPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(Issue issue) {
        return new PageParameters().set(PARAM_ID, issue.getId());
    }
}
