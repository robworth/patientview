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

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.util.time.Duration;
import org.jfree.chart.JFreeChart;


public class JFreeChartImage extends Image {

    private int width;
    private int height;

    public JFreeChartImage(String id, JFreeChart chart, int width, int height) {
        super(id, new Model(chart));
        this.width = width;
        this.height = height;
    }

    @Override
    protected IResource getImageResource() {
        DynamicImageResource resource = new DynamicImageResource() {

            @Override
            protected byte[] getImageData(final Attributes attributes) {
                JFreeChart chart = (JFreeChart) getDefaultModelObject();
                return toImageData(chart.createBufferedImage(width, height));
            }

            @Override
            protected void configureResponse(final ResourceResponse response, final Attributes attributes) {
                super.configureResponse(response, attributes);

                response.setCacheDuration(Duration.NONE);
                response.setCacheScope(WebResponse.CacheScope.PRIVATE);

            }

        };

        return resource;
    }

}
