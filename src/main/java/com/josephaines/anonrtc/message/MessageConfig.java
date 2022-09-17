package com.josephaines.anonrtc.message;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    CommandLineRunner commandLineRunner(MessageRepository repository) {
        return args -> {
            Message foo = new Message(
                    1L,
                    1L,
                    "foo"
            );

            repository.save(foo);
        };
    }
}
