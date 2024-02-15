package com.car.parking.app;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		CarParkingLot carParkingLot = null;
		boolean isLotCreated = false;

		System.out.println("\n *** Welcome to Car Parking Application ***");

		System.out.println(
				"\n Choose the menu \n create_parking_lot \n park \n leave \n status \n registration_numbers_for_cars_with_colour \n registration_number_for_slot_number \n slot_numbers_by_registrat_num ");

		// Run until exit command is received as input
		while (true) {
			try {
				// Receives the input
				System.out.println("\n$ ");
				String getUserInput = scanner.nextLine().trim();
				String splitUserInstructions[] = getUserInput.split(" ");

				// Holds the operational value
				String userSelected = splitUserInstructions[0].toLowerCase();

				switch (userSelected) {

				// create_parking_lot
				case "create_parking_lot":
					if (isLotCreated) {
						System.out.println("Already parking lot created...");
					} else {
						int totalCapacity = Integer.parseInt(splitUserInstructions[1]);
						carParkingLot = new CarParkingLot(totalCapacity);
						System.out.println("Created a parking lot with " + totalCapacity + " slots");
						// At present only once you allocate the lot size
						isLotCreated = true;
					}
					break;

				case "park":
					if (carParkingLot != null) {
						String registrationNo = splitUserInstructions[1];
						String carColor = splitUserInstructions[2];
						if (carParkingLot.isAlreadyPark(registrationNo)) {
							System.out.println("This car is already parked :" + registrationNo);
							break;
						}
						carParkingLot.parkVehicle(registrationNo, carColor);

					} else {
						System.out.println("Please create the parking lot with total capacity");
					}
					break;

				case "leave":
					if (carParkingLot != null) {
						int slotNumber = Integer.parseInt(splitUserInstructions[1]);
						carParkingLot.clearSlot(slotNumber);
					} else {
						System.out.println("Please create the parking lot with total capacity");
					}
					break;

				case "status":
					if (carParkingLot != null) {
						carParkingLot.statusOfParking();
					} else {
						System.out.println("Please create the parking lot with total capacity");
					}

					break;

				case "registration_numbers_for_cars_with_colour":
					if (carParkingLot != null) {
						carParkingLot.carRegNoByColour(splitUserInstructions[1]);
					} else {
						System.out.println("Please create the parking lot with total capacity");
					}

					break;

				case "registration_number_for_slot_number":
					if (carParkingLot != null) {
						carParkingLot.getSlotNumberByRegNo(splitUserInstructions[1]);
					} else {
						System.out.println("Please create the parking lot with total capacity");
					}

					break;

				case "slot_numbers_by_registrat_num":
					if (carParkingLot != null) {
						carParkingLot.getSlotNumbersByColor(splitUserInstructions[1]);
					} else {
						System.out.println("Please create the parking lot with total capacity");
					}
					break;

				case "exit":
					System.out.println("\nClosing the application ...");
					System.exit(0);
				default:
					break;
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(" Kindly enter valid numbers and name");
			} catch (Exception e) {
				System.out.println(" Provide valid details ");
			}
		}

	}

}
