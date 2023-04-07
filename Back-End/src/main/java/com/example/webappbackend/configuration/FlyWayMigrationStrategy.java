package com.example.webappbackend.configuration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Profile("reset")
@Configuration
/**
 * Class allows to clean and migrate database from Flyway SQL Files after every reboot the server.
 */
public class FlyWayMigrationStrategy {
    @Bean
    FlywayMigrationStrategy flywayConfigurationCustomizer() {
        return configuration -> {
            configuration.clean();
            configuration.migrate();
        };
    }
}
