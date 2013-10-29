package org.patientview.repository.radar.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.repository.radar.util.UserUpgradeManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 *
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context-service.xml"})
//@Ignore // ignore for now - this only needs running on go live
public class TestUserUpgrade {

    @Inject
    ApplicationContext applicationContext;


    @Inject
    private UserUpgradeManager userUpgradeManager;

    @Test
    public void testUserUpgrade() {
       System.out.println("Test");
      // userUpgradeManager.run();
    }
}
