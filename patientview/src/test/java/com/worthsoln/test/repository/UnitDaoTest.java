package com.worthsoln.test.repository;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UnitStatDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
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

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

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

        specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");

        Unit unit = new Unit();
        unit.setSpecialty(specialty);
        // required fields
        unit.setUnitcode("UNITCODE1");
        unit.setName("z");
        unit.setShortname("nam1");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty);
        // required fields
        unit.setUnitcode("UNITCODE2");
        unit.setName("y");
        unit.setShortname("nam2");
        // not required
        unit.setUnituser("user1");

        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty);
        // required fields
        unit.setUnitcode("UNITCODE3");
        unit.setName("x");
        unit.setShortname("nam3");
        // not required
        unit.setUnituser("user2");

        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty);
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
        unit.setSpecialty(specialty);
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

        checkUnit = unitDao.get(unit.getUnitcode(), specialty);

        assertTrue("Unit not found with unitcode", checkUnit != null && checkUnit.getId() > 0);
    }

    @Test
    public void testGetAllSort() {

        List<Unit> units = unitDao.getAll(false, specialty);

        assertEquals("Incorrect number of units found", 4, units.size());
        assertEquals("Incorrect first unit", "UNITCODE1", units.get(0).getUnitcode());

        units = unitDao.getAll(true, specialty);

        assertEquals("Incorrect number of units found", 4, units.size());
        assertEquals("Incorrect first unit", "UNITCODE4", units.get(0).getUnitcode());
    }

    @Test
    public void testGetUnitsWIthUser() {

        assertEquals("Incorrect number of units with user", 3, unitDao.getUnitsWithUser(specialty).size());
    }

    @Test
    public void testWithUnitCodes() {
        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");
        unitCodes.add("UNITCODE4");

        List<Unit> units = unitDao.get(unitCodes, specialty);

        assertEquals("Incorrect number of units", 2, units.size());
    }

    @Test
    public void testNotTheseUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        String[] notTheseUnitCodes = new String[] {"UNITCODE2", "UNITCODE4"};

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, new String[]{}, specialty);

        assertEquals("Incorrect number of units", 2, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE1", units.get(1).getUnitcode());
    }

    @Test
    public void testNotTheseUnitCodesPlusUnitCode() {

        Unit unit = new Unit();
        unit.setSpecialty(specialty);
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

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, plusUnitCodes, specialty);

        assertEquals("Incorrect number of units", 3, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE5", units.get(2).getUnitcode());
    }

    @Test
    public void testGetUnitCodesNotTheseUnitCodesPlusUnitCode() {

        Unit unit = new Unit();
        unit.setSpecialty(specialty);
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

        List<Unit> units = unitDao.get(unitCodes, notTheseUnitCodes, plusUnitCodes, specialty);

        assertEquals("Incorrect number of units", 2, units.size());
        assertEquals("Incorrect first unit", "UNITCODE3", units.get(0).getUnitcode());
        assertEquals("Incorrect last unit", "UNITCODE5", units.get(1).getUnitcode());
    }

    @Test
    public void testGetUnitUsers() {
        // create 2 unit, one with 2 users, one with 1 user
        // with usermappings and check the users can be pulled back
        Unit unit = new Unit();
        unit.setSpecialty(specialty);
        // required fields
        unit.setUnitcode("UNITCODEA");
        unit.setName("unit 1");
        unit.setShortname("unit 1");

        unitDao.save(unit);

        User user = repositoryHelpers.createUserWithMapping("paulc", "paul@test.com", "p", "Paul", "", "UNITCODEA",
                "nhs1", specialty);
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");
        user = repositoryHelpers.createUserWithMapping("deniz", "deniz@test.com", "d", "Deniz", "", "UNITCODEA", "nhs2",
                specialty);
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");

        unit = new Unit();
        unit.setSpecialty(specialty);
        // required fields
        unit.setUnitcode("UNITCODEB");
        unit.setName("unit 2");
        unit.setShortname("unit 2");

        unitDao.save(unit);

        user = repositoryHelpers.createUserWithMapping("dave", "dave@test.com", "d", "Dave", "", "UNITCODEB", "nhs3",
                specialty);
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "unitadmin");

        List<UnitAdmin> users = unitDao.getUnitUsers("UNITCODEA", specialty);
        assertEquals("Wrong number of users in unit A", 2, users.size());

        users = unitDao.getUnitUsers("UNITCODEB", specialty);
        assertEquals("Wrong number of users in unit B", 1, users.size());
    }
}
