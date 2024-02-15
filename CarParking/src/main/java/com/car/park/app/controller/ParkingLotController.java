package com.car.park.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.park.app.entity.CarTicket;
import com.car.park.app.service.ParkingLotService;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;

	static int createdSpaceSize = 0;

	@PostMapping("/create")
	public ResponseEntity<String> createParkingLot(@RequestBody Map<String, Integer> reuestData) {
		try {
			int totalCapacity = reuestData.get("totalCapacity");
			if (createdSpaceSize > 0) {
				return new ResponseEntity<>(" Already lot is created with size  :" + createdSpaceSize,
						HttpStatus.BAD_REQUEST);
			}
			if (totalCapacity > 0 && createdSpaceSize == 0) {
				createdSpaceSize = totalCapacity;
				parkingLotService.create(totalCapacity);
				return new ResponseEntity<>("Created a Parking Lot with Size :" + totalCapacity, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid Input Data :" + totalCapacity, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/park-new")
	public ResponseEntity<String> createParkingLot(@RequestBody CarTicket carTicket) {
		try {
			String response = parkingLotService.parkVehicle(carTicket.getRegNo(), carTicket.getColor());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/leave/{slotNumber}")
	public ResponseEntity<String> createParkingLot(@PathVariable("slotNumber") int slotNumber) {
		try {
			String response = parkingLotService.clearSlot(slotNumber);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/status")
	public ResponseEntity<?> getParkingStatus() {
		try {
			List<CarTicket> parkingStatusList = parkingLotService.statusOfParking();
			if (!parkingStatusList.isEmpty()) {
				return new ResponseEntity<>(parkingStatusList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("All slots are free", HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/slotno-regno/{registrationNo}")
	public ResponseEntity<?> getParkingStatus(@PathVariable("registrationNo") String registrationNo) {
		try {
			int slotNumber = parkingLotService.getSlotNumberByRegNo(registrationNo);
			if (slotNumber != 0) {
				return new ResponseEntity<>("Car " + registrationNo + " Parked Slot-no: " + slotNumber, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not Found Car With :" + registrationNo, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/slotno-color/{color}")
	public ResponseEntity<?> getSlotNumbersByColor(@PathVariable("color") String color) {
		try {
			Set<Integer> slotNumbers = parkingLotService.getSlotNumbersByColor(color);
			if (!slotNumbers.isEmpty()) {
				return new ResponseEntity<>(color + " are parked in slots: " + slotNumbers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not Found Car-Color with :" + color, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/regno-color/{color}")
	public ResponseEntity<?> getRegistrationNumByColor(@PathVariable("color") String color) {
		try {
			String response = parkingLotService.carRegNoByColour(color);
			if (!response.isEmpty()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not Found Car-Color with :" + color, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Server Error: Kindly check your request ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
