package es.jmmluna.tim.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TimApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TimApplication.class, args);				
	}

}
