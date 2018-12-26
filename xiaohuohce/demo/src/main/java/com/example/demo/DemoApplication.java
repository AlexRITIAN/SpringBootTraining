package com.example.demo;

import java.io.IOException;

import com.example.demo.core.Train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		Train train = new Train();
		// while (true) {
		// 	train.move();
		// }
		for (int i = 0; i < 1; i++) {
			try {
				train.move();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
