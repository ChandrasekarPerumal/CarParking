package com.car.park.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarParkingApp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CarParkingApp.class, args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
