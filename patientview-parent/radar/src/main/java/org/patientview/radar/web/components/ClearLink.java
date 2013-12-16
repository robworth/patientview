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

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.data.DataView;

public class ClearLink extends AjaxLink {
    private SortableDataProvider sortableProvider;
    private DataView dataView;
    private WebMarkupContainer container;

    public ClearLink(String s, SortableDataProvider sortableProvider, DataView dataView, WebMarkupContainer container) {
        super(s);
        this.sortableProvider = sortableProvider;
        this.dataView = dataView;
        this.container = container;

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected IAjaxCallDecorator getAjaxCallDecorator() {
        return new IAjaxCallDecorator() {
            public CharSequence decorateScript(Component component, CharSequence charSequence) {
                return "$('input.dxeEditArea').val(''); " + charSequence;
            }

            public CharSequence decorateOnSuccessScript(Component component, CharSequence charSequence) {
                return charSequence;
            }

            public CharSequence decorateOnFailureScript(Component component, CharSequence charSequence) {
                return charSequence;
            }
        };
    }

    @Override
    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
        if (sortableProvider.hasSearchCriteria()) {
            sortableProvider.clearSearchCriteria();
            dataView.setCurrentPage(0);
            ajaxRequestTarget.add(container);
            ajaxRequestTarget.add(this);
        }
    }

    @Override
    public boolean isVisible() {
        return sortableProvider.hasSearchCriteria();
    }
}
