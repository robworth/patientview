package org.patientview.test.repository.radar;

import org.junit.runner.RunWith;
import org.patientview.test.repository.patientview.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context-dao.xml"})
public abstract class BaseDaoTest extends TestPvDbSchema {

}
