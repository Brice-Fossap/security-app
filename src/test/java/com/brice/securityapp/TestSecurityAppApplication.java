package com.brice.securityapp;

import org.springframework.boot.SpringApplication;

public class TestSecurityAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}