package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.sequenced.ClinicalData;
import com.solidstategroup.radar.web.components.PhenotypeChooser;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarTextFieldWithValidation;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.panels.FollowUpPanel;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentLabel;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClinicalPicturePanel extends Panel {

    @SpringBean
    private ClinicalDataDao clinicalDataDao;
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;

    public ClinicalPicturePanel(String id, final IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
        Panel clinicalPicturePanel = new com.solidstategroup.radar.web.panels.firstvisit.
                ClinicalPicturePanel("clinicalPicturePanel", radarNumberModel, false);
        add(clinicalPicturePanel);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.CLINICAL_PICTURE);
    }

}
