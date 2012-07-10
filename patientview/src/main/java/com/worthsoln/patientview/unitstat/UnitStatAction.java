package com.worthsoln.patientview.unitstat;

import com.worthsoln.HibernateUtil;
import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.unit.Unit;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;
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
        List<UnitStat> unitStats = getUnitStatsForUnit(unitcode);
        List<UnitStat> patientCountStats = getPatientCountsForUnit(unitcode);
        Collection<UnitMonthStats> statsInRecords = turnUnitStatsListIntoRecords(unitStats, patientCountStats);
        addDownloadableFilenames(request, statsInRecords);
        List statsHeadings = statsHeadings();
        request.setAttribute("unitstats", statsInRecords);
        request.setAttribute("statsHeadings", statsHeadings);
        HibernateUtil.retrievePersistentObjectAndAddToSessionWithIdParameter(request, Unit.class, unitcode, "unit");
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
        return statsHeadings;
    }

    private List<UnitStat> getPatientCountsForUnit(String unitcode) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Object[] parameters = new String[]{unitcode, "patient"};
        Type[] types = new Type[]{Hibernate.STRING, Hibernate.STRING};

        List<PatientCount> patientCounts =
                session.find("from " + PatientCount.class.getName() + " patientcount " +
                        "where patientcount.unitcode = ? and patientcount.role = ?", parameters, types);
        tx.commit();
        HibernateUtil.closeSession();

        List<UnitStat> patientCountStats = new ArrayList<UnitStat>();
        for (PatientCount patientCount : patientCounts) {
            String yearmonth = patientCount.getYearmonth();
            int count = patientCount.getCount();
            patientCountStats.add(new UnitStat(unitcode, yearmonth, AddLog.PATIENT_COUNT, count));
        }

        return patientCountStats;
    }

    private List<UnitStat> getUnitStatsForUnit(String unitcode) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<UnitStat> unitStats =
                session.find("from " + UnitStat.class.getName() + " unitstat where unitstat.unitcode = ?", unitcode,
                        Hibernate.STRING);
        tx.commit();
        HibernateUtil.closeSession();
        return unitStats;
    }

    private Collection<UnitMonthStats> turnUnitStatsListIntoRecords(List<UnitStat> unitStatsList, List<UnitStat> patientCountStatsList) {
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
