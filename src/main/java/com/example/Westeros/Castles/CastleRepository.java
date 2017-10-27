package com.example.Westeros.Castles;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CastleRepository extends PagingAndSortingRepository<Castle,String> {
	Castle findByName(@Param("name") String name);
}
