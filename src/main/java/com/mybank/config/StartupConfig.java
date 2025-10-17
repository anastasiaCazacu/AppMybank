package com.mybank.config;

import com.mybank.entity.Role;
import com.mybank.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ADMIN") == null) {
                roleRepository.save(new Role("ADMIN"));
            }
            if (roleRepository.findByName("CLIENT") == null) {
                roleRepository.save(new Role("CLIENT"));
            }
            if (roleRepository.findByName("BANK") == null) {
                roleRepository.save(new Role("BANK"));
            }
        };
    }
}