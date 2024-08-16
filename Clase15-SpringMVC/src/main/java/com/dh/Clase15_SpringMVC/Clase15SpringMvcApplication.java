package com.dh.Clase15_SpringMVC;

import com.dh.Clase15_SpringMVC.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Clase15SpringMvcApplication {

	public static void main(String[] args) {
		BD.createTable();
		SpringApplication.run(Clase15SpringMvcApplication.class, args);
	}

}
