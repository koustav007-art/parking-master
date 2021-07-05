package com.example.parking.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingHistory {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@Column
	private String carNumber;
	
	@Column
	private String type;
	
	@Column
	private Date intime;
	
	@Column
	private Date outtime;
	
	
	@Column
	private Long cost;

	@Override
	public String toString() {
		return "Parking [carNumber=" + carNumber + ", type=" + type + ", intime=" + intime + ", outtime=" + outtime
				+ ", cost=" + cost + "]";
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public ParkingHistory() {
		super();
	}

	public ParkingHistory(Parking parking) {
		super();
		this.carNumber = parking.getCarNumber();
		this.type = parking.getType();
		this.intime = parking.getIntime() ;
		this.outtime = parking.getOuttime();
		this.cost = parking.getCost();
		
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

}
