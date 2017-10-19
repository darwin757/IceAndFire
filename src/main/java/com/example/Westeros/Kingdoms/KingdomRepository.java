package com.example.Westeros.Kingdoms;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface KingdomRepository extends PagingAndSortingRepository<Kingdom, String> {

	Kingdom findByName(@Param("name") String name);

}
