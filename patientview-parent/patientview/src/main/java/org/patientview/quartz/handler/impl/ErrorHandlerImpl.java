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

package org.patientview.quartz.handler.impl;

import org.patientview.patientview.EmailUtils;
import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.LogEntry;
import org.patientview.patientview.parser.ResultParser;
import org.patientview.quartz.exception.ResultParserException;
import org.patientview.quartz.handler.ErrorHandler;
import org.patientview.service.LogEntryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.File;
import java.util.Calendar;

/**
 * Class to deal with the errors produced by the XML import task.
 *
 * User: james@solidstategroup.com
 * Date: 17/12/13
 * Time: 11:49
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Component
public class ErrorHandlerImpl implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerImpl.class);

    @Inject
    private XmlImportUtils xmlImportUtils;

    @Inject
    private LogEntryManager logEntryManager;


    @Override
    public void processingException(File xmlFile, Exception e) {
        createLogEntry(xmlFile, AddLog.PATIENT_DATA_FAIL, e.getMessage());
        try {
            xmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile);
        } catch (Exception me) {
            LOGGER.error("Unable to send email {}", me.getMessage());
        }
    }

    @Override
    public void parserException(File xmlFile, ResultParserException e) {
        createLogEntry(xmlFile, AddLog.PATIENT_DATA_FAIL, EmailUtils.extractErrorsFromException(e));
        try {
            xmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile);
        } catch (Exception me) {
            LOGGER.error("Unable to send email {}", me.getMessage());
        }
    }

    @Override
    public void emptyFile(File xmlFile) {
        createLogEntry(xmlFile, AddLog.PATIENT_DATA_FAIL, "Empty file");
        try {
            xmlImportUtils.sendEmptyFileEmailToUnitAdmin(xmlFile.getName());
        } catch (Exception me) {
            LOGGER.error("Unable to send email {}", me.getMessage());
        }
    }

    @Override
    public void corruptNodes(File xmlFile, ResultParser resultParser) {
        createLogEntry(xmlFile, AddLog.PATIENT_DATA_FAIL,
                EmailUtils.createCorruptNodeEmailTest(resultParser.getCorruptNodes()));
        try {
            xmlImportUtils.sendCorruptDataEmail(resultParser);
        } catch (Exception me) {
            LOGGER.error("Unable to send email {}", me.getMessage());
        }
    }

    @Override
    public void createLogEntry(File xmlFile, String action, String extraInfoExplanation) {
        LogEntry logEntry = new LogEntry();
        logEntry.setActor(AddLog.ACTOR_SYSTEM);
        logEntry.setDate(Calendar.getInstance());
        logEntry.setNhsno(xmlImportUtils.getNhsNumber(xmlFile.getName()));
        logEntry.setUnitcode(xmlImportUtils.getUnitCode(xmlFile.getName()));
        logEntry.setUser("");
        logEntry.setAction(action);
        if (null != extraInfoExplanation && !"".equals(extraInfoExplanation)) {
            logEntry.setExtrainfo(xmlFile.getName() + " : " + extraInfoExplanation);
        } else {
            logEntry.setExtrainfo(xmlFile.getName());
        }
        logEntryManager.save(logEntry);
    }




}
