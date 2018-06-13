package org.mocr.poc.reactive.positionbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PositionBuilderApplication {
	
 
	public static void main(String[] args) {   
	        SpringApplication springApplication = new SpringApplication(PositionBuilderApplication.class);
	        springApplication.addListeners(new PropertiesLogger()); 
	        springApplication.run(args); 
	       
	} 
}
