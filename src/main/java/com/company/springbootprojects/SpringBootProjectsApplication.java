package com.company.springbootprojects;

import com.company.springbootprojects.model.Customer;
import com.company.springbootprojects.model.Quote;
import com.company.springbootprojects.service.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringBootProjectsApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootProjectsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectsApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/test")
    public Quote test() {
        String uri = "https://quoters.apps.pcfone.io/api/random";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Quote.class);
        }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository)
    //demo method that puts the CustomerRepository through a few tests.
    // First, it fetches the CustomerRepository from the Spring application context.
    // Then it saves a handful of Customer objects, demonstrating the save() method
    // and setting up some data to work with. Next, it calls findAll() to fetch all Customer objects from the database.
    // Then it calls findById() to fetch a single Customer by its ID.
    // Finally, it calls findByLastName() to find all customers whose last name is "Bauer".
    // The demo() method returns a CommandLineRunner bean that automatically runs the code when the application launches.


    {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

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
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }
}

