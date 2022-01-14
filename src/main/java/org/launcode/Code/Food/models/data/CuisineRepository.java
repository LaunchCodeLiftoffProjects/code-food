package org.launcode.Code.Food.models.data;

import org.launcode.Code.Food.models.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends CrudRepository<Cuisine,Integer> {
}

