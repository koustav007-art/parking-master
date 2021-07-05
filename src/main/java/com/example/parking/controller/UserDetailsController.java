package com.example.parking.controller;

import java.security.Principal;
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
import com.example.parking.model.Parking;
import com.example.parking.model.ParkingHistory;
import com.example.parking.model.Recharge;
import com.example.parking.service.ParkingService;
import com.example.parking.utils.Constants;
import com.example.parking.utils.OverSizeException;



@Controller
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/userdetails")
public class UserDetailsController {
	
	private final ParkingService service;

	public UserDetailsController(ParkingService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/tokenform")
	public String tokenform() {

	    return "tokenform";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/vacancyform")
	public String vacancyform() {

	    return "vacancyform";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/rechargeform")
	public String rechargeform() {

	    return "rechargeform";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/rechargecheckform")
	public String rechargeCheckform() {

	    return "rechargecheckform";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/vacateform")
	public String vacateform() {

	    return "vacateform";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/historyform")
	public String historyform() {

	    return "historyform";
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/generateToken")
	public ModelAndView generateToken(Principal principal) {
		try {			
			String name = principal.getName();
			String [] data = name.split(":");
			ParkingData parkingdata =new ParkingData (data[1],data[0]);
			Parking parking = service.createParking(parkingdata);
			return new ModelAndView("token", "parking", parking);
		} catch (OverSizeException e) {
			OverSize overSize = new OverSize(true);
			return new ModelAndView("errortoken", "overSize", overSize);
		}
	}	

    @PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/vacateParking")
	@SuppressWarnings("deprecation")
	public ModelAndView vacateParking(Principal principal) {
		String name = principal.getName();
		String [] data = name.split(":");
		Optional<Parking> parkingOpt = service.unparkCar(data[1]);
		if(parkingOpt.isPresent()) {
			return new ModelAndView("vacate", "parking", parkingOpt.get());
		}
		return new ModelAndView("vacate", "parking", new Parking());
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/getHistory")
	public ModelAndView getHistory(Principal principal) {
		String name = principal.getName();
		String [] data = name.split(":");
		List<ParkingHistory> parkingHistory = service.getParkingByCarNumber(data[1]);
		return new ModelAndView("history", "parkingHistory", parkingHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(value = "/getVacancy")
	public ModelAndView getVacancy(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		VehicleType vehicleType = VehicleType.returnInstance("com.example.parking.components.data."+parkingData.getType());
		return new ModelAndView("vacancy", "vehicleType", vehicleType.getSize());
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(value = "/recharge")
	public String register(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		service.recharge(parkingData);		
		return "rechargesuccess";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(value = "/rechargecheck")
	public ModelAndView rechargeCheck(@ModelAttribute("SpringWeb") ParkingData parkingData) {
		Recharge recharge =service.rechargeCheck(parkingData);	
		return new ModelAndView("rechargecheck", "recharge", recharge);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
	

}
