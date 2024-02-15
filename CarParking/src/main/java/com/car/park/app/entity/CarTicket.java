package com.car.park.app.entity;

public class CarTicket {

	private String regNo;
	private String color;
	private int slotNumber;

//	public CarTicket() {
//		
//	}

	public CarTicket(int slotNumber,String regNo, String color) {
		super();
		this.slotNumber = slotNumber;
		this.regNo = regNo;
		this.color = color;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
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
		return slotNumber + " " + regNo + " " + color;
	}

}
