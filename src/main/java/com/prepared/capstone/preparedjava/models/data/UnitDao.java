package com.prepared.capstone.preparedjava.models.data;


import com.prepared.capstone.preparedjava.models.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UnitDao extends CrudRepository<Unit, Integer> {
}
