package com.example.traveliotripmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TravelioTripManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelioTripManagerApplication.class, args);
    }

}
