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

package org.patientview.patientview;

import org.patientview.patientview.parser.XmlParserThread;
import org.patientview.patientview.uktransplant.UktExportThread;
import org.patientview.patientview.uktransplant.UktParserThread;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ParserMonitorServlet extends HttpServlet {

    public void init() throws ServletException {
        super.init();
        String runThreads = getServletContext().getInitParameter("run.import.export.threads");
        if ((runThreads == null) || !"false".equals(runThreads)) {
            startParseThread("xml", new XmlParserThread(new String[]{".xml", }));
            //  startParseThread("dataout", new DataOutThread());
        }
        String runUktThreads = getServletContext().getInitParameter("run.ukt.threads");
        if ((runUktThreads == null) || !"false".equals(runUktThreads)) {
            startParseThread("ukt", new UktParserThread());
            startParseThread("uktexport", new UktExportThread());
        }
    }

    private void startParseThread(String prebit, ParserThread parserThread) {
        String directory = getServletContext().getInitParameter(prebit + ".directory");
        String archiveDirectory = getServletContext().getInitParameter(prebit + ".archive.directory");
        String minutesBetweenWaitString = getServletContext().getInitParameter(prebit + ".minutes.to.wait");
        int minutesBetweenWait = Integer.parseInt(minutesBetweenWaitString);
        parserThread.setPrebit(prebit);
        parserThread.setArchiveDirectory(archiveDirectory);
        parserThread.setDirectory(directory);
        parserThread.setMinutesBetweenWait(minutesBetweenWait);
        parserThread.setServletContext(getServletContext());
        Runnable runnable = (Runnable) parserThread;
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
