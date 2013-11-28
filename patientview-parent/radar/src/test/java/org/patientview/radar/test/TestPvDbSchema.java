package org.patientview.radar.test;

import org.junit.Ignore;
import org.patientview.common.test.BaseTestPvDbSchema;

@Ignore
public class TestPvDbSchema extends BaseTestPvDbSchema {

    protected String getTestNhsNo() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0; i<=9; i++) {
            stringBuffer.append((int)(Math.random() * 10));
        }
        return stringBuffer.toString();
    }
}
