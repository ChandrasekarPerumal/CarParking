package com.car.parking.app;

public class CarTicket {

	private String regNo;
	private String color;

//	public CarTicket() {
//		
//	}

	public CarTicket(String regNo, String color) {
		super();
		this.regNo = regNo;
		this.color = color;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return regNo + " " + color;
	}

}
