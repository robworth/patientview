package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.Nutrition;

import java.util.List;

public interface NutritionDao {

    Nutrition get(Long id);

    void save(Nutrition nutrition);

    List<Nutrition> getAllNutritions(String nhsno);

}
