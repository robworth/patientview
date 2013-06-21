package org.patientview.radar.test;

import org.patientview.radar.util.UserUpgradeManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 *
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
@Ignore // ignore for now - this only needs running on go live
public class TestUserUpgrade {

    @Inject
    private UserUpgradeManager userUpgradeManager;

    @Test
    public void testUserUpgrade() {
        userUpgradeManager.run();
    }
}
