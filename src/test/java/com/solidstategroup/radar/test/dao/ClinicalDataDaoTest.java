package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.ClinicalDataDao;
import com.solidstategroup.radar.model.Phenotype;
import com.solidstategroup.radar.model.sequenced.ClinicalData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ClinicalDataDaoTest extends BaseDaoTest {

    @Autowired
    private ClinicalDataDao clinicalDataDao;

    @Test
    public void testGetClinicalData() {
        ClinicalData clinicalData = clinicalDataDao.getClinicalData(135L);
        assertNotNull("Clinical data is null for ID", clinicalData);
    }

    @Test
    public void testGetClinicalDataUnknown() {
        ClinicalData clinicalData = clinicalDataDao.getClinicalData(132325L);
        assertNull("Clinical data is not null for ID", clinicalData);
    }

    @Test
    public void testGetClinicalDataByRadarNumber() {
        List<ClinicalData> clinicalDatas = clinicalDataDao.getClinicalDataByRadarNumber(244L);
        assertNotNull("List of clinical datas was null", clinicalDatas);
    }

    @Test
    public void testGetPhenotype() {
        Phenotype phenotype = clinicalDataDao.getPhenotype(4L);
        assertNotNull("Phenotype was null", phenotype);
        assertEquals("Phenotype had wrong description", "Blindness", phenotype.getDescription());
        assertEquals("Phenotype had ID wrong", new Long(4L), phenotype.getId());
    }

    @Test
    public void testGetPhenotypes() {
        List<Phenotype> phenotypes = clinicalDataDao.getPhenotypes();
        assertNotNull("Phenotypes list was null", phenotypes);
        assertTrue("Phenotypes list was empty", phenotypes.size() > 0);
    }

}
