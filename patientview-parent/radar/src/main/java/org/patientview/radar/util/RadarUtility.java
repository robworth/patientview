package org.patientview.radar.util;

import com.Ostermiller.util.RandPass;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

/**
 * Radar Utility - miscellaneous utility methods go here
 */
public class RadarUtility {

    /**
     * @param event1Start cannot be null
     * @param event2Start cannot be null
     * @return true if there is an overlap
     */
    public static boolean isEventsOverlapping(Date event1Start, Date event1End, Date event2Start, Date event2End) {
        if (event1End == null) {
            if (event2End == null) {
                if (event1Start.compareTo(event2Start) == 0) {
                    return true;
                }
            } else {
                if (event1Start.compareTo(event2Start) >= 0 &&
                        event1Start.compareTo(event2End) < 1) {
                    return true;
                }
            }
        } else {
            if (event2Start.compareTo(event1Start) >= 0 &&
                    event2Start.compareTo(event1End) < 1) {
                return true;
            }
            if (event2End != null) {
                if (event1Start.compareTo(event2Start) >= 0 && event1Start.compareTo(event2End) < 1 ||
                        event1End.compareTo(event2Start) >= 0 && event1End.compareTo(event2End) < 1 ||
                        event2Start.compareTo(event1Start) >= 0 && event2Start.compareTo(event1End) < 1 ||
                        event2End.compareTo(event1Start) >= 0 && event2End.compareTo(event1End) < 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns a user friendly label for an enum for example PART1_PART2 will return Part1 Part2
     */
    public static String getLabelFromEnum(String enumString) {
        String label = enumString;
        label = label.replace("_", " ");
        String[] parts = label.split(" ");
        label = "";
        for (String part : parts) {
            String formatted = part.toLowerCase();
            formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
            label += formatted;
            label += " ";
        }
        return label;
    }

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static String generateNewPassword() {
        return new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(8);
    }
}
