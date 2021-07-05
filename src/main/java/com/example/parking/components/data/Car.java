package com.example.parking.components.data;

import com.example.parking.utils.Constants;

public class Car extends VehicleType{

	public static int size = Constants.CAR_SIZE;

	public int getSize() {
		return size;
	}

	public static void setSize(int size) {
		Car.size = size;
	}
	
	
}
