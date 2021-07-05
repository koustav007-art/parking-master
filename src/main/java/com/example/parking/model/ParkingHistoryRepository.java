package com.example.parking.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory, Integer>{

	@Query("Select ph from ParkingHistory ph where ph.carNumber like %?1")
	List<ParkingHistory> findByCarNumber(String carNumber);
}
