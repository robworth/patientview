package org.patientview.radar.web.behaviours;

import org.apache.wicket.AttributeModifier;

/**
 * Adds code to the beginning of an attribute.
 * @see org.apache.wicket.behavior.AttributeAppender
 */
public final class AttributePrepender extends AttributeModifier {

    /**
     * constructor is private. Use static getNewInstance method to get a new instance
     */
    private AttributePrepender(String attribute, org.apache.wicket.model.IModel<?> replaceModel) {
       super(attribute, replaceModel);
    }

    @Override
    protected String newValue(String currentValue, String appendValue) {
      return appendValue + currentValue;
    }

    /**
     * gets new instance
     */

    public static AttributePrepender getNewInstance(String attribute, org.apache.wicket.model.IModel<?> replaceModel) {
        return new AttributePrepender(attribute, replaceModel);
    }
}
