package com.liam.photoappuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

// DiscoveryClient is more generalized but should work for everything
//@EnableDiscoveryClient
@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(basePackages = {"com.liam.photoappuserservice.services"})
public class PhotoAppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppUserServiceApplication.class, args);
	}

}
