package com.isa.platform.u202023992.si729ebu202023992;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Si729ebu202023992Application {

	public static void main(String[] args) {

		SpringApplication.run(Si729ebu202023992Application.class, args);

		String url = "http://localhost:8080/swagger-ui.html";
		System.out.println("\n• Swagger UI is available at » " + url);
	}

}
