package com.vijay.chennam.vehicleregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VehicleRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRegisterApplication.class, args);
    }

}
