package com.example.parking.components.data;

import com.example.parking.utils.Constants;

public class Handicapped extends VehicleType{

	public static int size = Constants.HANDICAPPED_SIZE;

	public int getSize() {
		return size;
	}

	public static void setSize(int size) {
		Handicapped.size = size;
	}
	
}
