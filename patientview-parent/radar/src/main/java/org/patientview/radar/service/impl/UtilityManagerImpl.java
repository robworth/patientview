package org.patientview.radar.service.impl;

import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Clinician;
import org.patientview.radar.model.Ethnicity;
import org.patientview.radar.model.Country;
import org.patientview.radar.model.Relative;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.filter.ConsultantFilter;
import org.patientview.radar.service.UtilityManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class UtilityManagerImpl implements UtilityManager {

    private UtilityDao utilityDao;

    private String siteUrl;

    private String patientViewSiteUrl;

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getPatientViewSiteUrl() {
        return patientViewSiteUrl;
    }

    public void setPatientViewSiteUrl(String patientViewSiteUrl) {
        this.patientViewSiteUrl = patientViewSiteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Centre getCentre(long id) {
        return utilityDao.getCentre(id);
    }

    public List<Centre> getCentres() {
        return utilityDao.getCentres();
    }

    public Consultant getConsultant(long id) {
        return utilityDao.getConsultant(id);
    }

    public List<Consultant> getConsultants() {
        return getConsultants(new ConsultantFilter(), -1, -1);
    }

    public List<Consultant> getConsultants(ConsultantFilter filter) {
        return getConsultants(filter, -1, -1);
    }

    public List<Consultant> getConsultants(ConsultantFilter filter, int page, int numberPerPage) {
        return utilityDao.getConsultants(filter, page, numberPerPage);
    }

    public List<Consultant> getConsultantsByCentre(Centre centre) {
        return utilityDao.getConsultantsByCentre(centre);
    }

    public void saveConsultant(Consultant consultant) throws Exception {
        utilityDao.saveConsultant(consultant);
    }

    public void deleteConsultant(Consultant consultant) throws Exception {
        utilityDao.deleteConsultant(consultant);
    }

    public Country getCountry(long id) {
        return utilityDao.getCountry(id);
    }

    public List<Country> getCountries() {
        return utilityDao.getCountries();
    }

    public Ethnicity getEthnicityByCode(String ethnicityCode) {
        return utilityDao.getEthnicityByCode(ethnicityCode);
    }

    public List<Ethnicity> getEthnicities() {
        return utilityDao.getEthnicities();
    }

    public Relative getRelative(long id) {
        return utilityDao.getRelative(id);
    }

    public List<Relative> getRelatives() {
        return utilityDao.getRelatives();
    }

    public Map<Long, Integer> getPatientCountPerUnitByDiagnosisCode(DiagnosisCode diagnosisCode) {
        return utilityDao.getPatientCountPerUnitByDiagnosisCode(diagnosisCode);
    }

    public int getPatientCountByUnit(Centre centre) {
        return utilityDao.getPatientCountByUnit(centre);
    }

    public JFreeChart getPatientCountPerUnitChart() {
        // create dataset
        String srnsSeries = "SRNS";
        String mpgnSeries = "MPGN";

        DiagnosisCode srnsCode = new DiagnosisCode();
        srnsCode.setId(DiagnosisCode.SRNS_ID);

        DiagnosisCode mpgnCode = new DiagnosisCode();
        mpgnCode.setId(DiagnosisCode.MPGN_ID);

        Map<Long, Integer> srnsPatientCountMap = getPatientCountPerUnitByDiagnosisCode(srnsCode);
        Map<Long, Integer> mpgnPatientCountMap = getPatientCountPerUnitByDiagnosisCode(mpgnCode);

        java.util.List<Centre> centreList = getCentres();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Centre centre : centreList) {

            String centreCategory = centre.getAbbreviation() != null ? centre.getAbbreviation() : centre.getName();

            Integer srnsCount = srnsPatientCountMap.containsKey(centre.getId()) ?
                    srnsPatientCountMap.get(centre.getId()) : null;
            dataset.addValue(srnsCount, srnsSeries, centreCategory);

            Integer mpgnCount = mpgnPatientCountMap.containsKey(centre.getId()) ?
                    mpgnPatientCountMap.get(centre.getId()) : null;
            dataset.addValue(mpgnCount, mpgnSeries, centreCategory);
        }

        // create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Total number of registered patients by unit",         // chart title
                "",               // domain axis label
                "",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        DecimalFormat decimalformat1 = new DecimalFormat("##,###");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        renderer.setBaseItemLabelsVisible(true);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64)
        );
        GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0)
        );
        GradientPaint gp2 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0)
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Times New Roman", Font.PLAIN, 12));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

    public List<Clinician> getCliniciansByCentre(Centre centre) {
        return utilityDao.getClinicians(centre);
    }

    public UtilityDao getUtilityDao() {
        return utilityDao;
    }

    public void setUtilityDao(UtilityDao utilityDao) {
        this.utilityDao = utilityDao;
    }
}
