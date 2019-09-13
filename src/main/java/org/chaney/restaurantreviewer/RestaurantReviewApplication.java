package org.chaney.restaurantreviewer;

import org.chaney.restaurantreviewer.model.User;
import org.chaney.restaurantreviewer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackages={"org.chaney.restaurantreviewer.controller", "org.chaney.restaurantreviewer.config", "org.chaney.restaurantreviewer.service", "org.chaney.restaurantreviewer.repository"
        , "org.chaney.restaurantreviewer.utils"})
public class RestaurantReviewApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestaurantReviewApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@domain.com",name);
                userRepository.save(user);
            });
            userRepository.findAll().forEach(System.out::println);
        };
    }
}
