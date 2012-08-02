package com.worthsoln.test.repository;

import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.ibd.model.enums.AreaToDiscuss;
import com.worthsoln.repository.ibd.CarePlanDao;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CarePlanDaoTest extends BaseDaoTest {

    @Inject
    private CarePlanDao carePlanDao;

    @Test
    public void testAddGetCarePlan() throws Exception {
        CarePlan carePlan = getTestObject();

        carePlanDao.save(carePlan);

        assertTrue("Invalid id for new care plan", carePlan.getId() > 0);

        CarePlan checkCarePlan = carePlanDao.get(carePlan.getId());

        assertNotNull(checkCarePlan);
        assertEquals("NHS no not persisted", checkCarePlan.getNhsno(), carePlan.getNhsno());
        assertEquals("Areas to discuss not persisted", checkCarePlan.getAreasToDiscuss().size(),
                carePlan.getAreasToDiscuss().size());
        assertEquals("Further topics not persisted", checkCarePlan.getFurtherTopics(), carePlan.getFurtherTopics());
        assertEquals("Goals not persisted", checkCarePlan.getGoals(), carePlan.getGoals());
        assertEquals("Goal to achieve not persisted", checkCarePlan.getGoalToAchieve(), carePlan.getGoalToAchieve());
        assertEquals("Goal scale not persisted", checkCarePlan.getGoalScale(), carePlan.getGoalScale());
        assertEquals("How to achieve not persisted", checkCarePlan.getHowToAchieveGoal(),
                carePlan.getHowToAchieveGoal());
        assertEquals("Barriers not persisted", checkCarePlan.getBarriers(), carePlan.getBarriers());
        assertEquals("What can be done not persisted", checkCarePlan.getWhatCanBeDone(), carePlan.getWhatCanBeDone());
        assertEquals("Confidence scale not persisted", checkCarePlan.getConfidenceScale(),
                carePlan.getConfidenceScale());
        assertEquals("Review date not persisted", checkCarePlan.getReviewDate(), carePlan.getReviewDate());
    }

    @Test
    public void testGetByNhsNo() throws Exception {
        CarePlan carePlan = getTestObject();

        carePlanDao.save(carePlan);

        assertTrue("Invalid id for new care plan", carePlan.getId() > 0);

        CarePlan checkCarePlan = carePlanDao.get(carePlan.getNhsno());

        assertNotNull(checkCarePlan);
    }

    private CarePlan getTestObject() throws Exception {
        CarePlan carePlan = new CarePlan();

        carePlan.setNhsno("1234567890");
        carePlan.setFurtherTopics("Further topics");
        carePlan.setGoals("Goals");
        carePlan.setGoalToAchieve("Goal");
        carePlan.setGoalScale(1);
        carePlan.setHowToAchieveGoal("How to achieve goal");
        carePlan.setBarriers("Barriers");
        carePlan.setWhatCanBeDone("What can be done");
        carePlan.setConfidenceScale(1);
        carePlan.setReviewDate(new Date());

        List<AreaToDiscuss> areasToDiscuss = new ArrayList<AreaToDiscuss>();
        areasToDiscuss.add(AreaToDiscuss.ALTERNATIVE_AND_COMPLEMENTARY_TREATMENTS);
        areasToDiscuss.add(AreaToDiscuss.EXCERCISE_AND_SPORT);

        carePlan.setAreasToDiscuss(areasToDiscuss);

        return carePlan;
    }
}
