/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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
