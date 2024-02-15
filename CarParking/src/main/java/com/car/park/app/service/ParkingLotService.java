package com.car.park.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.car.park.app.entity.CarTicket;

@Service
public class ParkingLotService {

	// Holds parking lot capacity
	private int totalCapacity;

	/** Holds SlotNumber and Registration of a car */
	private Map<Integer, CarTicket> parkingSlots;

	/** Holds Registration number of a car and Parked slot Number */
	private Map<String, Integer> slotNumbersByRegistration;

	/** Holds Color and List of vehicle associated to that color */
	private Map<String, Set<Integer>> slotNumbersByColor;

	private void slotInitialization() {
		// To serve the nearest park slot
		for (int i = 1; i <= totalCapacity; i++) {
			parkingSlots.put(i, null);
			System.out.println(parkingSlots);
		}
	}

	public List<CarTicket> statusOfParking() {
		List<CarTicket> responseList = new ArrayList<>();
		for (Map.Entry<Integer, CarTicket> ps : parkingSlots.entrySet()) {
			int slotNumber = ps.getKey();
			Optional<CarTicket> ticket = Optional.ofNullable(ps.getValue());

			if (ticket.isPresent()) {
				responseList.add(ps.getValue());
			}
		}

		return responseList;
	}

	public void create(int totalCapacity) {
		this.totalCapacity = totalCapacity;
		parkingSlots = new HashMap<>();
		slotNumbersByRegistration = new HashMap<>();
		slotNumbersByColor = new HashMap<>();
		slotInitialization();
//		statusOfParking();
	}

	public boolean isAlreadyPark(String regNo) {
		return slotNumbersByRegistration.containsKey(regNo);
	}

	public String parkVehicle(String regNo, String carColor) {

		if (isAlreadyPark(regNo)) {
			return "Car is already parked with registration no. :" + regNo;
		} else {

			for (int slot_index = 1; slot_index <= totalCapacity; slot_index++) {
				if (parkingSlots.get(slot_index) == null) {
					parkingSlots.put(slot_index, new CarTicket(slot_index, regNo, carColor));
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
					return "Allocated slot number: " + slot_index;
				}
			}
			return "Sorry, parking lot is full";

		}
	}

	/*
	 * Delete
	 * 
	 * public void clearSlot(int slotNumber) { if (slotNumber <= totalCapacity &&
	 * slotNumber > 0) { if (parkingSlots.get(slotNumber) != null) {
	 * deleteDataInMaps(slotNumber); parkingSlots.put(slotNumber, null);
	 * System.out.println("Slot number " + slotNumber + " is free "); } else {
	 * System.out.println("Already Slot number " + slotNumber + " is free"); } }
	 * else { System.out.println("No such slot number with :" + slotNumber); } }
	 */
}
