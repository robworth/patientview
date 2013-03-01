package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.TenancyDao;
import com.worthsoln.repository.TenancyUserRoleDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class TenancyDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private TenancyDao tenancyDao;

    @Inject
    private TenancyUserRoleDao tenancyUserRoleDao;

    @Test
    public void testAddGetTenancy() {

        Tenancy tenancy = repositoryHelpers.createTenancy("Tenant 1", "ten", "Test description");

        Tenancy checkTenancy = tenancyDao.get(tenancy.getId());

        assertTrue("Invalid id for new tenancy", checkTenancy.getId() > 0);
        assertEquals("Name not persisted", tenancy.getName(), checkTenancy.getName());
    }

    @Test
    public void testAddGetTenancyUserRoles() {

        Tenancy tenancy = repositoryHelpers.createTenancy("Tenant 1", "ten", "Test description");

        Tenancy tenancy2 = repositoryHelpers.createTenancy("Tenant 2", "ten2", "Test description 2");

        User user = repositoryHelpers.createUser("test", "test@worthsolns.com", "password", "Firstname Lastname",
                "screenname");

        TenancyUserRole tenancyUserRole1 = repositoryHelpers.createTenancyUserRole(tenancy, user, "patient");

        assertTrue("Invalid id", tenancyUserRole1.getId() > 0);

        TenancyUserRole tenancyUserRole2 = repositoryHelpers.createTenancyUserRole(tenancy2, user, "patient");
        List<TenancyUserRole> tenancyUserRoles = tenancyUserRoleDao.get(user);

        assertEquals("tenancyUserRoles not persisted", 2, tenancyUserRoles.size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testUniqueTenancyUserRoleConstraint() {
        Tenancy tenancy = repositoryHelpers.createTenancy("Tenant 1", "ten", "Test description");

        User user = repositoryHelpers.createUser("test", "test@worthsolns.com", "password", "Firstname Lastname",
                "screenname");

        repositoryHelpers.createTenancyUserRole(tenancy, user, "patient");
        repositoryHelpers.createTenancyUserRole(tenancy, user, "patient");
    }
}
