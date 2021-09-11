package com.example.mpesa_springboot;

import com.example.mpesa_springboot.dtos.AcknowledgeResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MpesaSpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(MpesaSpringbootApplication.class, args);
	}

	@Bean
	public AcknowledgeResponse getAcknowledgeResponse() {
		AcknowledgeResponse acknowledgeResponse = new AcknowledgeResponse();
		acknowledgeResponse.setMessage("Success");
		return acknowledgeResponse;
	}

}
