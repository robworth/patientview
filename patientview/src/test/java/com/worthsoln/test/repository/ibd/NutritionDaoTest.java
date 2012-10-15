package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.Nutrition;
import com.worthsoln.repository.ibd.NutritionDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NutritionDaoTest extends BaseDaoTest {

    @Inject
    private NutritionDao nutritionDao;

    @Test
    public void testAddGetNutrition() throws Exception {
        Nutrition nutrition = getTestObject();

        nutritionDao.save(nutrition);

        assertTrue("Invalid id for new nutrition", nutrition.getId() > 0);

        Nutrition checkNutrition = nutritionDao.get(nutrition.getId());

        assertNotNull(checkNutrition);
        assertEquals("NHS no not persisted", checkNutrition.getNhsno(), nutrition.getNhsno());
        assertEquals("Nutrition date not persisted", checkNutrition.getNutritionDate(), nutrition.getNutritionDate());
        assertEquals("Foods that disagree not persisted", checkNutrition.getFoodsThatDisagree(),
                nutrition.getFoodsThatDisagree());
        assertEquals("Comment not persisted", checkNutrition.getComment(), nutrition.getComment());
    }

    private Nutrition getTestObject() throws Exception {
        Nutrition nutrition = new Nutrition();

        nutrition.setNhsno("1234567890");
        nutrition.setNutritionDate(new Date());
        nutrition.setFoodsThatDisagree("Cauliflower disagrees with me");
        nutrition.setComment("I feel sick after eating cauliflowers");

        return nutrition;
    }

}
