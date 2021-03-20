package com.company.springbootprojects;

import com.company.springbootprojects.consumingrest.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        String uri ="https://quoters.apps.pcfone.io/api/random";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Quote.class);
    }
}
