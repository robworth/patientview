package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UnitStatDao;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class UnitDaoTest extends BaseDaoTest {

    @Inject
    private UnitDao unitDao;

    @Inject
    private UnitStatDao unitStatDao;

    @Test
    public void testAddGetUnitStatByUnitCode() {

        // NOTE: the unit codes need to be uppercase!

        UnitStat unitStat = new UnitStat();
        unitStat.setAction("action1");
        unitStat.setCount(1);
        unitStat.setUnitcode("UNITCODE1");
        unitStat.setYearmonth("1207");
        unitStatDao.save(unitStat);

        unitStat = new UnitStat();
        unitStat.setAction("action1");
        unitStat.setCount(1);
        unitStat.setUnitcode("UNITCODE1");
        unitStat.setYearmonth("1207");
        unitStatDao.save(unitStat);

        // different unit
        unitStat = new UnitStat();
        unitStat.setAction("action2");
        unitStat.setCount(1);
        unitStat.setUnitcode("UNITCODE2");
        unitStat.setYearmonth("1207");
        unitStatDao.save(unitStat);

        List<UnitStat> unitStats = unitStatDao.get("UNITCODE1");

        assertEquals("Wrong number of unitstats", 2, unitStats.size());

        assertEquals("Wrong sample yearmonth", "1207", unitStats.get(0).getYearmonth());
    }


    @Before
    public void createUnits() {

        Unit unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE1");
        unit.setName("name1");
        unit.setShortname("nam1");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE2");
        unit.setName("name2");
        unit.setShortname("nam2");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE3");
        unit.setName("name3");
        unit.setShortname("nam3");
        // not required
        unit.setUnituser("user2");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE4");
        unit.setName("name4");
        unit.setShortname("nam4");
        // not required
        unit.setUnituser("user2");

        unitDao.save(unit);
    }


    @Test
    public void testAddGetUnit() {

        Unit unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE5");
        unit.setName("nameA");
        unit.setShortname("namA");
        // not required
        unit.setUnituser("userA");

        unitDao.save(unit);

        Unit checkUnit = unitDao.get(unit.getId());

        assertTrue("Unit not found with id", checkUnit != null && checkUnit.getId() > 0);

        assertEquals("Unitname incorrect", "nameA", checkUnit.getName());

        checkUnit = unitDao.get(unit.getUnitcode());

        assertTrue("Unit not found with unitcode", checkUnit != null && checkUnit.getId() > 0);
    }
}
