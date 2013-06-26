package org.patientview.radar.web.components;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.util.List;

/**
 * Utility class for components
 */
public class ComponentHelper {

    /*
      Use this method to avoid ajax update error when a component cannot update because its parent is hidden
    */
    public static void updateComponentsIfParentIsVisible(AjaxRequestTarget target, List<? extends Component>
            componentsToUpdate) {
        for (Component component : componentsToUpdate) {
            if (component.getParent().isVisibleInHierarchy()) {
                target.add(component);
            }
        }
    }
}
