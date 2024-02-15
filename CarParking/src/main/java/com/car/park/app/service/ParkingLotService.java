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

	public String clearSlot(int slotNumber) {
		if (slotNumber <= totalCapacity && slotNumber > 0) {
			if (parkingSlots.get(slotNumber) != null) {
				deleteDataInMaps(slotNumber);
				parkingSlots.put(slotNumber, null);
				return "Slot number " + slotNumber + " is free ";
			} else {
				return "Already Slot number " + slotNumber + " is free";
			}
		} else {
			return "No such slot number with :" + slotNumber;
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

	public String carRegNoByColour(String carColor) {

		StringBuilder response = new StringBuilder();
		if (slotNumbersByColor.containsKey(carColor)) {
			// If the car colour exist in our List
			Set<Integer> slotNumberSet = slotNumbersByColor.get(carColor);
			for (int slotNumber : slotNumberSet) {
				if (parkingSlots.get(slotNumber) != null) {
					response.append(parkingSlots.get(slotNumber).getRegNo() + "  ");
				}
			}
			return response.toString();
		} else {
			return "No such colour car is in Parking";
		}
	}

	public int getSlotNumberByRegNo(String regno) {

		if (slotNumbersByRegistration.containsKey(regno)) {
			int slotNumber = slotNumbersByRegistration.get(regno);
			return slotNumber;
		}
		return 0;
	}

	public Set<Integer> getSlotNumbersByColor(String color) {

		Set<Integer> response = new HashSet<>();
		if (slotNumbersByColor.containsKey(color)) {
			return response = slotNumbersByColor.get(color);
		}

		return response;

	}

}
