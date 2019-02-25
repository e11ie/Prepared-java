package com.prepared.capstone.preparedjava.models.data;

import com.prepared.capstone.preparedjava.models.MealPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface MealPlanDao extends CrudRepository<MealPlan, Integer> {
}