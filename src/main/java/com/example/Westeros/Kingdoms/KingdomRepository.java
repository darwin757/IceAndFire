package com.example.Westeros.Kingdoms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface KingdomRepository extends JpaRepository<Kingdom, String> {

	Kingdom findByName(@Param("name") String name);

}
