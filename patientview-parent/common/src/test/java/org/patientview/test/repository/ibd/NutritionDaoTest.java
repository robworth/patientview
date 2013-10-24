/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.repository.ibd;

import org.patientview.ibd.model.Nutrition;
import org.patientview.repository.ibd.NutritionDao;
import org.patientview.test.repository.BaseDaoTest;
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
