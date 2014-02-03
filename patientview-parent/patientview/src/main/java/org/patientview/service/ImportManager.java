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

package org.patientview.service;

import org.patientview.model.Unit;
import org.patientview.quartz.exception.ProcessException;

import java.io.File;

/**
 *  Importer for results, patient data etc
 */
public interface ImportManager {

    /**
     * Import data into the system via XML file.
     * Can be called from the web application to import a single file, or to be called as part of the
     * scheduled importer job.  Each has the same behaviour.
     * @param xmlFile the file to import
     */
    void process(File xmlFile) throws ProcessException;

    /**
     * This method is here because importer task should running without login user's authority,
     * so copy it from UnitManger to here.
     */
    Unit retrieveUnit(String unitcode);


}
