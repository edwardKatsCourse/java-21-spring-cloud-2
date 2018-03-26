package com.example.accountserviceproduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceProductionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceProductionApplication.class, args);
    }
}

@Component
class CLRRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("peter", "daniella", "simon",
                "david", "jimmy")
                .map(x -> {
                    User user = new User();
                    user.setUsername(x);
                    return user;
                })
                .forEach(x -> userRepository.save(x));

        System.out.println(userRepository.findAll());
    }
}
