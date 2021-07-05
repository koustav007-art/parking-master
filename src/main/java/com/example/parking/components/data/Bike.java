package com.example.parking.components.data;

import com.example.parking.utils.Constants;

public class Bike extends VehicleType{

	public static int size = Constants.BIKE_SIZE;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		Bike.size = size;
	}
	
}
