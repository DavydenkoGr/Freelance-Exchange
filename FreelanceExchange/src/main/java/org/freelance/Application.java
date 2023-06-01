package org.freelance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class which runs site
 */
@SpringBootApplication
public class Application {
    /**
     * Main method
     * Run SpringBootApplication
     * @param args command line args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }
}
