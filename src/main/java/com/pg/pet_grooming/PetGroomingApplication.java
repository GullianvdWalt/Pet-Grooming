/*
*   © Pet Grooming 
*   Pearson Pretoria ITSP300 - Project 2020
*   
    This is the main class for the main pet grooming application
 */
package com.pg.pet_grooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PetGroomingApplication {

    // Return username with bean, eliminates constructor
    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    public static void main(String[] args) {
        System.setProperty("spring.main.lazy.initialization", "true");
        SpringApplication.run(PetGroomingApplication.class, args);
    }

}

