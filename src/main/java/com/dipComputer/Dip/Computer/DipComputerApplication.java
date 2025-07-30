package com.dipComputer.Dip.Computer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DipComputerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DipComputerApplication.class, args);
	}

}
