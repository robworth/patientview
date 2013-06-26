package org.patientview.radar.web.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * A text area component which displays help text when the model value is an empty string
 */

public abstract class TextAreaWithHelpText extends TextArea {
    private String helpText;

    public TextAreaWithHelpText(String id, final String helpText) {
        super(id);
        this.helpText = helpText;
        setModel(new Model<String>() {

            @Override
            public String getObject() {
                String modelData = getModelData();
                if (modelData.isEmpty()) {
                    return helpText;
                }
                return modelData;
            }

            @Override
            public void setObject(String object) {
                object = object == null ? "" : object;
                setModelData(!object.equals(helpText) ? object : "");
            }
        });
    }

    /**
     * call this after creating the component - for some reason adding behaviour in the constructor doesnt work!
     */
    public void initBehaviour() {
        add(new AttributeModifier("onblur", "radarUtility.checkDetailsInput(this, '" +
                helpText + "');"));

        add(new AttributeModifier("onclick", "radarUtility.removeDefaultValue(this, '" +
                helpText + "');"));

        add(new AttributeModifier("class", new Model() {
            @Override
            public Serializable getObject() {
                return getModelData().isEmpty() ? "grey" : "";
            }
        }));

    }

    public abstract String getModelData();

    public abstract void setModelData(String data);
}
