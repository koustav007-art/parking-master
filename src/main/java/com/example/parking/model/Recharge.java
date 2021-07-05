package com.example.parking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recharge {

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Long getRechargeAmt() {
		return rechargeAmt;
	}

	public void setRechargeAmt(Long rechargeAmt) {
		this.rechargeAmt = rechargeAmt;
	}

	public Recharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recharge(String carNumber, Long rechargeAmt) {
		super();
		this.carNumber = carNumber;
		this.rechargeAmt = rechargeAmt;
	}

	@Id
	@Column
	private String carNumber;
	
	@Column
	private Long rechargeAmt;
}
