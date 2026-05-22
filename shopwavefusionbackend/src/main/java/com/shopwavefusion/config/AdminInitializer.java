package com.shopwavefusion.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shopwavefusion.modal.User;
import com.shopwavefusion.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@example.com") == null) {
            User adminUser = new User();
            adminUser.setEmail("admin@example.com");
            adminUser.setFirstName("Admin");
            adminUser.setLastName("Admin");
            adminUser.setMobile("1234567890");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRole("ROLE_ADMIN");
            adminUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(adminUser);
        }
    }
}