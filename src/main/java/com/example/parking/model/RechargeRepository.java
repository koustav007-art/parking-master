package com.example.parking.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, String>{

	@Query("Select rr from Recharge rr where rr.carNumber like %?1")
	Recharge findByCarNumber(String carNumber);
}
