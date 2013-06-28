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

package org.patientview.patientview.model.enums;

import java.util.Arrays;
import java.util.List;

public enum XmlImportNotification  {

    DEFAULT(0, "Default"),
    FAILED_IMPORT(1, "Failed Import"),
    IMPORTER_STOPPED(2, "Importer Stopped");

    private long id;
    private String name;

    private XmlImportNotification(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static XmlImportNotification getXmlImportNotification(Long id) {
        for (XmlImportNotification xmlImportNotification : XmlImportNotification.values()) {
            if (xmlImportNotification.getId() == id) {
                return xmlImportNotification;
            }
        }

        return null;
    }

    public static XmlImportNotification getXmlImportNotificationByName(String name) {
        for (XmlImportNotification xmlImportNotification : XmlImportNotification.values()) {
            if (xmlImportNotification.getName().equalsIgnoreCase(name)) {
                return xmlImportNotification;
            }
        }

        return null;
    }

    public static List<XmlImportNotification> getAsList() {
        return Arrays.asList(XmlImportNotification.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
