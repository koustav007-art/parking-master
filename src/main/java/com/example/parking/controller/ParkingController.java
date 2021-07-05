package com.example.parking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.parking.components.data.ParkingData;
import com.example.parking.components.data.VehicleType;
import com.example.parking.config.OverSize;
import com.example.parking.config.SetupDataLoader;
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingHistory;
import com.example.parking.service.ParkingService;
import com.example.parking.utils.Constants;
import com.example.parking.utils.OverSizeException;



@Controller
@RequestMapping("/parkinglot")
public class ParkingController {
	
	private final ParkingService service;

	
	public ParkingController(ParkingService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/tokenform")
	public String tokenform() {

	    return "tokenform";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/vacateform")
	public String vacateform() {

	    return "vacateform";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/historyform")
	public String historyform() {

	    return "historyform";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/vacancyform")
	public String vacancyform() {

	    return "vacancyform";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/registerform")
	public String registerform() {

	    return "registerform";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/generateToken")
	public ModelAndView generateToken(@ModelAttribute("SpringWeb")ParkingData parkingData) {
		try {
			Parking parking = service.createParking(parkingData);
			return new ModelAndView("token", "parking", parking);
		} catch (Exception e) {
			OverSize overSize = new OverSize(true);
			return new ModelAndView("errortoken", "overSize", overSize);
		}
	}	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/vacateParking")
	@SuppressWarnings("deprecation")
	public ModelAndView vacateParking(@ModelAttribute("SpringWeb")ParkingData parkingData) {
		Optional<Parking> parkingOpt = service.unparkCar(parkingData.getCarNumber());
		if(parkingOpt.isPresent()) {
			return new ModelAndView("vacate", "parking", parkingOpt.get());
		}
		return new ModelAndView("vacate", "parking", new Parking());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/getHistory")
	public ModelAndView getHistory(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		List<ParkingHistory> parkingHistory = service.getParkingByCarNumber(parkingData.getCarNumber());
		return new ModelAndView("history", "parkingHistory", parkingHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/getStatus")
	public ModelAndView getStatus() {
		List<Parking> parking = service.getStatus();
		return new ModelAndView("status", "parking", parking);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/getVacancy")
	public ModelAndView getVacancy(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		VehicleType vehicleType = VehicleType.returnInstance("com.example.parking.components.data."+parkingData.getType());
		return new ModelAndView("vacancy", "vehicleType", vehicleType.getSize());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/register")
	public String register(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		service.createUserIfNotFound(parkingData.getType()+":"+parkingData.getCarNumber(), parkingData.getPassword());		
		return "registersuccess";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
	

}
