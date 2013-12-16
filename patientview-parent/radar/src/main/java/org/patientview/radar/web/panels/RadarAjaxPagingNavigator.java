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

package org.patientview.radar.web.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.PropertyModel;

public class RadarAjaxPagingNavigator extends AjaxPagingNavigator {

    private WebMarkupContainer summaryContainer;

    public RadarAjaxPagingNavigator(String id, IPageable pageable, int totalResults) {
        super(id, pageable);

        summaryContainer = new WebMarkupContainer("summary");
        summaryContainer.setOutputMarkupId(true);
        summaryContainer.setOutputMarkupPlaceholderTag(true);

        summaryContainer.add(new Label("currentPage", new PropertyModel<Integer>(this, "currentPage")));
        summaryContainer.add(new Label("totalPages", new PropertyModel<Integer>(getPageable(), "pageCount")));
        summaryContainer.add(new Label("totalResults", Integer.toString(totalResults)));

        add(summaryContainer);
    }

    public int getCurrentPage() {
        return getPageable().getCurrentPage() + 1;
    }

    @Override
    protected void onAjaxEvent(AjaxRequestTarget target) {
        super.onAjaxEvent(target);
        target.add(summaryContainer);
    }

}
