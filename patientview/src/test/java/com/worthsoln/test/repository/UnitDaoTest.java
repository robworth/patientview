package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UnitStatDao;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
        unit.setName("z");
        unit.setShortname("nam1");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE2");
        unit.setName("y");
        unit.setShortname("nam2");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE3");
        unit.setName("x");
        unit.setShortname("nam3");
        // not required
        unit.setUnituser("user2");

        unitDao.save(unit);

        unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE4");
        unit.setName("w");
        unit.setShortname("nam4");
        // not required
        unit.setUnituser("");  // no user

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

    @Test
    public void testGetAllSort() {

        List<Unit> units = unitDao.getAll(false);

        assertEquals("Incorrect number of units found", 4, units.size());
        assertEquals("Incorrect first unit", "UNITCODE1", units.get(0).getUnitcode());

        units = unitDao.getAll(true);

        assertEquals("Incorrect number of units found", 4, units.size());
        assertEquals("Incorrect first unit", "UNITCODE4", units.get(0).getUnitcode());
    }

    @Test
    public void testGetUnitsWIthUser() {

        assertEquals("Incorrect number of units with user", 3, unitDao.getUnitsWithUser().size());
    }

    @Test
    public void testWithUnitCodes() {
        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");
        unitCodes.add("UNITCODE4");

        List<Unit> units = unitDao.get(unitCodes);

        assertEquals("Incorrect number of units", 2, units.size());
    }

    @Test
    public void testNotTheseUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        String[] notTheseUnitCodes = new String[] {"UNITCODE2", "UNITCODE4"};

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, new String[]{});

        assertEquals("Incorrect number of units", 2, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE1", units.get(1).getUnitcode());
    }

    @Test
    public void testNotTheseUnitCodesPlusUnitCode() {

        Unit unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE5");
        unit.setName("za");
        unit.setShortname("nam5");
        // not required
        unit.setUnituser("user5");

        unitDao.save(unit);

        List<String> unitCodes = new ArrayList<String>();
        String[] notTheseUnitCodes = new String[] {"UNITCODE2", "UNITCODE4"};
        String[] plusUnitCodes = new String[] {"UNITCODE5"};

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, plusUnitCodes);

        assertEquals("Incorrect number of units", 3, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE5", units.get(2).getUnitcode());
    }

    @Test
    public void testGetUnitCodesNotTheseUnitCodesPlusUnitCode() {

        Unit unit = new Unit();
        // required fields
        unit.setUnitcode("UNITCODE5");
        unit.setName("za");
        unit.setShortname("nam5");
        // not required
        unit.setUnituser("user5");

        unitDao.save(unit);

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE3");
        String[] notTheseUnitCodes = new String[] {"UNITCODE2", "UNITCODE4"};
        String[] plusUnitCodes = new String[] {"UNITCODE5"};

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, plusUnitCodes);

        assertEquals("Incorrect number of units", 2, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE5", units.get(1).getUnitcode());
    }
}
