package com.example.parking.service;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.parking.components.data.Bike;
import com.example.parking.components.data.Car;
import com.example.parking.components.data.Handicapped;
import com.example.parking.components.data.ParkingData;
import com.example.parking.utils.OverSizeException;
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingHistory;
import com.example.parking.model.ParkingHistoryRepository;
import com.example.parking.model.ParkingRepository;
import com.example.parking.model.Recharge;
import com.example.parking.model.RechargeRepository;
import com.example.parking.model.Role;
import com.example.parking.model.RoleRepository;
import com.example.parking.model.UserEntity;
import com.example.parking.model.UserRepository;
import com.example.parking.utils.Constants;

@Component
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired
	private ParkingRepository parkingRepository;
	
	@Autowired
	private ParkingHistoryRepository parkingHistoryRepository;
	
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private RechargeRepository rechargeRepository;
    
	public Parking createParking(ParkingData parkingData) throws OverSizeException {
		Parking parking;
		if ((!getParkingById(parkingData.getCarNumber()).isPresent()) || (getParkingById(parkingData.getCarNumber()).get().getOuttime()!=null)) {
			parking = new Parking(parkingData.getCarNumber(), parkingData.getType());
			if (parking.getType().equalsIgnoreCase(Constants.BIKE)) {
				if (Bike.size == 0) {
					throw new OverSizeException();
				} else {
					Bike.size--;
				}
			} else if (parking.getType().equalsIgnoreCase(Constants.CAR)) {
				if (Car.size == 0) {
					throw new OverSizeException();
				} else {
					Car.size--;
				}
			} else if (parking.getType().equalsIgnoreCase(Constants.HANDICAPPED)) {
				if (Handicapped.size == 0) {
					throw new OverSizeException();
				} else {
					Handicapped.size--;
				}
			}
			parkingRepository.save(parking);
			return parking;
		}

		return getParkingById(parkingData.getCarNumber()).get();
	}

	public Optional<Parking> unparkCar(String id) {

		Optional<Parking> parkingOpt = getParkingById(id);
		
		if(parkingOpt.isPresent()) {
			Parking parking = parkingOpt.get();
			parking.setOuttime(new Date());
			
			if (parking.getType().equalsIgnoreCase(Constants.BIKE)) {

					Bike.size++;

			} else if (parking.getType().equalsIgnoreCase(Constants.CAR)) {
				
					Car.size++;

			} else if (parking.getType().equalsIgnoreCase(Constants.HANDICAPPED)) {

					Handicapped.size++;

			}
			long inday = parking.getIntime().getDay();			
			long outday = parking.getOuttime().getDay();
			
			long inhour = parking.getIntime().getHours();			
			long outhour = parking.getOuttime().getHours();
			
			long cost = Constants.COST_PER_HOUR;;
			if (outday - inday > 0) {
				 cost = (outday - inday) * Constants.COST_PER_DAY;
			} else if (outhour - inhour > 0) {
				 cost = (outhour - inhour) * Constants.COST_PER_HOUR;
			}			
			parking.setCost(cost);
			
			ParkingHistory ph = new ParkingHistory(parking);
			parkingHistoryRepository.save(ph);
			if (rechargeRepository.findById(id).isPresent()) {
				Recharge r = rechargeRepository.findById(id).get();
				if (r.getRechargeAmt()>cost) {
					r.setRechargeAmt(r.getRechargeAmt()-cost);	
					parking.setCost(0L);
				} else {
					parking.setCost(cost-r.getRechargeAmt());
					r.setRechargeAmt(0L);
				}
				
				rechargeRepository.save(r);
				
			}
			parkingRepository.deleteById(id);
			
		}

		
		return parkingOpt;
		
	}
	
	public Optional<Parking> getParkingById(String id) {
		return parkingRepository.findById(id);
	}
	
	public List<ParkingHistory> getParkingByCarNumber(String id) {
		return (List<ParkingHistory>)parkingHistoryRepository.findByCarNumber(id);
	}
	
    
    public final UserEntity createUserIfNotFound(final String name, final String password) {
    	Role role = createRoleIfNotFound(Role.ROLE_USER);
        UserEntity user = userRepository.findByUsername(name);
        if (user == null) {
            user = new UserEntity(name, "{noop}"+password);
            Set<Role> set = new HashSet<Role>();
            set.add(role);
            user.setRoles(set);
            user = userRepository.save(user);
        }
        return user;
    }
    
    
    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }

	public void recharge(ParkingData parkingData) {
		
		if (rechargeRepository.findById(parkingData.getCarNumber()).isPresent()) {
			Recharge recharge = rechargeRepository.findById(parkingData.getCarNumber()).get();
			recharge.setRechargeAmt(parkingData.getRechargeAmt() + recharge.getRechargeAmt());
			rechargeRepository.save(recharge);
		} else {
			Recharge recharge = new Recharge();
			recharge.setCarNumber(parkingData.getCarNumber());
			recharge.setRechargeAmt(parkingData.getRechargeAmt());
			rechargeRepository.save(recharge);
		}
	}
	
    public Recharge rechargeCheck(ParkingData parkingData) {
		
		if (rechargeRepository.findById(parkingData.getCarNumber()).isPresent()) {
			Recharge recharge = rechargeRepository.findById(parkingData.getCarNumber()).get();
			return recharge;
		} else {
			return new Recharge();
		}
	}

	public List<Parking> getStatus() {
		return parkingRepository.findAll();
	}

}