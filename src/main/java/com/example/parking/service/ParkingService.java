package com.example.parking.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.parking.components.data.ParkingData;
import com.example.parking.utils.OverSizeException;
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingHistory;
import com.example.parking.model.Recharge;
import com.example.parking.model.Role;
import com.example.parking.model.UserEntity;


@Component
public interface ParkingService {
	
	
	public Parking createParking(ParkingData parkingData) throws OverSizeException;
		
	public Optional<Parking> getParkingById(String id);
	
	public List<ParkingHistory> getParkingByCarNumber(String id);
		
	public Optional<Parking> unparkCar(String id);
	
    public UserEntity createUserIfNotFound(final String name, final String password);

	public void recharge(ParkingData parkingData);
	
	public Recharge rechargeCheck(ParkingData parkingData);

	public List<Parking> getStatus();

	

}