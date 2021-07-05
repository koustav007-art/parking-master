package com.example.parking.config;

import com.example.parking.utils.Constants;

public class OverSize {
	boolean noSpaceAvaiable;
	
	String spaceAvaiable;
	
	

	public String getSpaceAvaiable() {
		return spaceAvaiable;
	}

	public void setSpaceAvaiable(String spaceAvaiable) {
		this.spaceAvaiable = spaceAvaiable;
	}

	public boolean isNoSpaceAvaiable() {
		return noSpaceAvaiable;
	}

	public void setNoSpaceAvaiable(boolean noSpaceAvaiable) {
		this.noSpaceAvaiable = noSpaceAvaiable;
	}


	public OverSize(boolean noSpaceAvaiable) {
		super();
		this.noSpaceAvaiable = noSpaceAvaiable;
		this.spaceAvaiable = Constants.NO_SPACE_AVAILABLE;
	}
	
	
}
