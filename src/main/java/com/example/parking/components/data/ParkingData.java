package com.example.parking.components.data;


public class ParkingData {

	private String carNumber;
	
	private String type;
	
	private String password;
	
	
	private Long rechargeAmt;
	
	
	public Long getRechargeAmt() {
		return rechargeAmt;
	}

	public void setRechargeAmt(Long rechargeAmt) {
		this.rechargeAmt = rechargeAmt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ParkingData [carNumber=" + carNumber + ", type=" + type + "]";
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

	public ParkingData() {
		super();
	}
	
	public ParkingData(String carNumber, String type) {
		this.type = type;
		this.carNumber = carNumber;
	}
	
}
