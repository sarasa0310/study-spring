package com.jimmy.studyspring.accessingdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JpaDemo {

    @Bean
    CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            // save 4 demo customers
            repository.save(new Customer("지일", "은"));
            repository.save(new Customer("경혜", "은"));
            repository.save(new Customer("종욱", "은"));
            repository.save(new Customer("영주", "최"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('은'):");
            log.info("--------------------------------------------");
            repository.findByLastName("은").forEach(eun -> log.info(eun.toString()));
            log.info("");
        };
    }

}
