package org.patientview.radar.util;

/**
 *
 */
public interface UserUpgradeManager {

    void run();

    void addUserForAllRadarPatients() throws Exception;
}
