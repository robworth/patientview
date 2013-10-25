package org.patientview.test.radar;

import org.junit.runner.RunWith;
import org.patientview.test.patientview.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public abstract class BaseDaoTest extends TestPvDbSchema {

}
