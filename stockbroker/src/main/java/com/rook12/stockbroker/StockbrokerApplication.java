package com.rook12.stockbroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.Bootstrap;

import java.io.IOException;

@SpringBootApplication
public class StockbrokerApplication  {

	public static void main(String[] args) {
		SpringApplication.run(StockbrokerApplication.class, args);
		//Bootstrap.main(args);
	}

}
