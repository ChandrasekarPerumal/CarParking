package com.car.parking.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CarParkingLot {

	// Holds parking lot capacity
	private int totalCapacity;

	/** Holds SlotNumber and Registration of a car */
	private Map<Integer, CarTicket> parkingSlots;

	/** Holds Registration number of a car and Parked slot Number */
	private Map<String, Integer> slotNumbersByRegistration;

	/** Holds Color and List of vehicle associated to that color */
	private Map<String, Set<Integer>> slotNumbersByColor;

	public CarParkingLot(int totalCapacity) {
		this.totalCapacity = totalCapacity;
		parkingSlots = new HashMap<>();
		slotNumbersByRegistration = new HashMap<>();
		slotNumbersByColor = new HashMap<>();
		slotInitialization();
	}

	private void slotInitialization() {
		// To serve the nearest park slot
		for (int i = 1; i <= totalCapacity; i++) {
			parkingSlots.put(i, null);
		}
	}

	/**
	 * Input required 1. String carRegistrationNumber 2. String carColor
	 */
	public void parkVehicle(String regNo, String carColor) {
		boolean flag = false;

		for (int slot_index = 1; slot_index <= totalCapacity; slot_index++) {
			if (parkingSlots.get(slot_index) == null) {
				parkingSlots.put(slot_index, new CarTicket(regNo, carColor));
				slotNumbersByRegistration.put(regNo, slot_index);

				// Add the slotNumber to the list by color
				if (slotNumbersByColor.containsKey(carColor)) {
					Set<Integer> slotNumsSet = new HashSet<>();
					slotNumsSet = slotNumbersByColor.get(carColor);
					slotNumsSet.add(slot_index);
					slotNumbersByColor.put(carColor, slotNumsSet);
				} else {
					Set<Integer> slotNumsSet = new HashSet<>();
					slotNumsSet.add(slot_index);
					slotNumbersByColor.put(carColor, slotNumsSet);
				}
				System.out.println("Allocated slot number: " + slot_index);
				return;
			}
		}
		System.out.println("Sorry, parking lot is full");
	}

	public void clearSlot(int slotNumber) {
		if (slotNumber <= totalCapacity && slotNumber > 0) {
			if (parkingSlots.get(slotNumber) != null) {
				deleteDataInMaps(slotNumber);
				parkingSlots.put(slotNumber, null);
				System.out.println("Slot number " + slotNumber + " is free ");
			} else {
				System.out.println("Already Slot number " + slotNumber + " is free");
			}
		} else {
			System.out.println("No such slot number with :" + slotNumber);
		}
	}

	private void deleteDataInMaps(int slotNumber) {
		CarTicket carTicket = parkingSlots.get(slotNumber);
		String color = carTicket.getColor();
		Set<Integer> slotNumsSet = new HashSet<>();
		slotNumsSet = slotNumbersByColor.get(color);

		if (slotNumsSet.contains(slotNumber)) {
			slotNumsSet.remove(slotNumber);
			slotNumbersByColor.remove(color);

			if (slotNumsSet.isEmpty()) {
				slotNumbersByColor.remove(color);
			} else {
				slotNumbersByColor.put(color, slotNumsSet);
			}
		}
		slotNumbersByRegistration.remove(carTicket.getRegNo());
	}

	public boolean isAlreadyPark(String regNo) {
		return slotNumbersByRegistration.containsKey(regNo);
	}

	public void statusOfParking() {
		for (Map.Entry<Integer, CarTicket> ps : parkingSlots.entrySet()) {
			int slotNumber = ps.getKey();
			CarTicket ticket = ps.getValue();
			if (ticket != null) {
				System.out.println(slotNumber + "  " + ticket.toString());
			}
		}
	}

	public void getSlotNumberByRegNo(String regno) {

		System.out.println(" Registration   Slot Number");		
		if(slotNumbersByRegistration.containsKey(regno)) {
			int slotNumber = slotNumbersByRegistration.get(regno);
			System.out.println(regno +" is parked in "+slotNumber);
		}
	}

	public void getSlotNumbersByColor(String color) {

		System.out.println(" Color  Slot Numbers ");
		if(slotNumbersByColor.containsKey(color)) {
//			Set<Integer> slotNumbers = slotNumbersByColor.get(color).stream().;
			System.out.println(slotNumbersByColor.get(color));			
		}
		
	}

	public void carRegNoByColour(String carColor) {

		System.out.println("\n***  Registration Number by colour *** ");

		if (slotNumbersByColor.containsKey(carColor)) {
			// If the car colour exist in our List
			Set<Integer> slotNumberSet = slotNumbersByColor.get(carColor);
			for (int slotNumber : slotNumberSet) {
				if (parkingSlots.get(slotNumber) != null) {
					System.out.println(parkingSlots.get(slotNumber).getRegNo());
				}
			}
		} else {
			System.out.println("No such colour car is in Parking");
		}

	}

}
