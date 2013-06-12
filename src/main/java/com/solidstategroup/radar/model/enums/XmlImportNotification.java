package com.solidstategroup.radar.model.enums;

import java.util.Arrays;
import java.util.List;

public enum XmlImportNotification  {

    DEFAULT(0, "Default"),
    FAILED_IMPORT(1, "Failed Import"),
    IMPORTER_STOPPED(2, "Importer Stopped"),
    RADAR_PROF_REQUEST(3, "Radar Professional Request");

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
