package com.example.parking.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Parking {
	
	@Id
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

	public Parking() {
		super();
	}

	public Parking(String carNumber, String type) {
		super();
		this.carNumber = carNumber;
		this.type = type;
		this.intime = new Date() ;
		this.outtime = null;
		this.cost = 0L;
		
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
