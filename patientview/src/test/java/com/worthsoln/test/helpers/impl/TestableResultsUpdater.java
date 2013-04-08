package com.worthsoln.test.helpers.impl;

import com.worthsoln.patientview.ResultsUpdater;

import javax.servlet.ServletContext;
import java.io.File;

/**
 *
 */
public class TestableResultsUpdater extends ResultsUpdater {

    @Override
    protected void renameDirectory(ServletContext context, File xmlFile) {
        // do nothing - it's a test
    }
}
