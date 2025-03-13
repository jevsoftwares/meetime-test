package com.jaysonmm.meetime_test;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static com.jaysonmm.meetime_test.utlis.Constants.API_KEY_HEADER;
import static com.jaysonmm.meetime_test.utlis.Constants.AUTHENTICATION_HEADER;

@EnableFeignClients
@SpringBootApplication

@SecurityScheme(name = API_KEY_HEADER, type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
@SecurityScheme(name = AUTHENTICATION_HEADER, type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class MeetimeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetimeTestApplication.class, args);
	}

}
