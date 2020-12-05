package com.cola.operaciones.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ColaOperacionesEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColaOperacionesEurekaApplication.class, args);
    }

}
