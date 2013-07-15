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

package org.patientview.patientview.unitstat;

import org.patientview.actionutils.ActionUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.UnitStat;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

public class UnitStatAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        List<UnitStat> unitStats = LegacySpringUtils.getUnitManager().getUnitStatsForUnit(unitcode);
        List<UnitStat> patientCountStats = LegacySpringUtils.getUnitManager().getPatientCountsForUnit(unitcode);
        Collection<UnitMonthStats> statsInRecords = turnUnitStatsListIntoRecords(unitStats, patientCountStats);
        addDownloadableFilenames(request, statsInRecords);
        List statsHeadings = statsHeadings();
        request.setAttribute("unitstats", statsInRecords);
        request.setAttribute("statsHeadings", statsHeadings);

        if (unitcode != null) {
            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            if (unit != null) {
                request.getSession().setAttribute("unit", unit);
            }
        }

        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        return LogonUtils.logonChecks(mapping, request);
    }

    private void addDownloadableFilenames(HttpServletRequest request, Collection<UnitMonthStats> statsInRecords) {
        ServletContext context = request.getSession().getServletContext();
        String unitStatDirPath = context.getInitParameter("unitstatfiles.directory");
        for (UnitMonthStats unitMonthStat : statsInRecords) {
            String unitStatFilename = unitMonthStat.getUnitcode() + "-" + unitMonthStat.getYearmonth() + ".csv";
            File statFile = new File(unitStatDirPath, unitStatFilename);
            if (statFile.exists()) {
                unitMonthStat.setDownloadFilename(statFile.getName());
            }
        }
    }

    private List statsHeadings() {
        List<StatsHeading> statsHeadings = new ArrayList<StatsHeading>();
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_COUNT));
        statsHeadings.add(new StatsHeading(AddLog.LOGGED_ON));
        statsHeadings.add(new StatsHeading("unique logon"));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_VIEW));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_ADD));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_DATA_FOLLOWUP));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_DATA_FAIL));
        statsHeadings.add(new StatsHeading("unique data load"));
        statsHeadings.add(new StatsHeading(AddLog.PASSWORD_CHANGE));
        statsHeadings.add(new StatsHeading(AddLog.PASSWORD_RESET));
        statsHeadings.add(new StatsHeading(AddLog.PASSWORD_LOCKED));
        statsHeadings.add(new StatsHeading(AddLog.PASSWORD_UNLOCKED));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_DATA_REMOVE));
        statsHeadings.add(new StatsHeading(AddLog.PATIENT_DELETE));
        return statsHeadings;
    }

    private Collection<UnitMonthStats> turnUnitStatsListIntoRecords(List<UnitStat> unitStatsList,
                                                                    List<UnitStat> patientCountStatsList) {
        TreeMap<UnitStatId, UnitMonthStats> unitStatsRecords = new TreeMap<UnitStatId, UnitMonthStats>();
        addStatsToTree(unitStatsList, unitStatsRecords);
        addStatsToTree(patientCountStatsList, unitStatsRecords);
        return unitStatsRecords.values();
    }

    private void addStatsToTree(List<UnitStat> unitStatsList, TreeMap<UnitStatId, UnitMonthStats> unitStatsRecords) {
        for (UnitStat unitStat : unitStatsList) {
            UnitStatId unitStatId = new UnitStatId(unitStat);
            UnitMonthStats unitMonthStats = unitStatsRecords.get(unitStatId);
            if (unitMonthStats == null) {
                unitMonthStats = new UnitMonthStats(unitStat);
                unitStatsRecords.put(unitStatId, unitMonthStats);
            }
            unitMonthStats.addStat(unitStat.getAction(), unitStat.getCount() + "");
        }
    }
}

class UnitStatId implements Comparable {

    private String unitcode;
    private String yearmonth;

    public UnitStatId(UnitStat unitStat) {
        this.unitcode = unitStat.getUnitcode();
        this.yearmonth = unitStat.getYearmonth();
    }

    public int compareTo(Object o) {
        UnitStatId resultToCompareThisTo = (UnitStatId) o;
        if (unitcode.equals(resultToCompareThisTo.getUnitcode())) {
            return -yearmonth.compareToIgnoreCase(resultToCompareThisTo.getYearmonth());
        } else {
            return unitcode.compareToIgnoreCase(resultToCompareThisTo.getUnitcode());
        }
    }

    public String getUnitcode() {
        return unitcode;
    }

    public String getYearmonth() {
        return yearmonth;
    }
}
