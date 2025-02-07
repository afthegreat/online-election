package com.onlineelection.system.UserModelService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.onlineelection.system.UserModelService.DTO.RegisterAccountDTO;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Password encoder to hash passwords

    @Override
    public void run(String... args) throws Exception {
        createAdminAccount(); // Create admin account when the application starts
    }

    private void createAdminAccount() throws Exception {
        String email = "etsayhaben@gmail.com"; // Admin email
        String studentId = "main58787012"; // Admin student ID
        String password = passwordEncoder.encode("12345678"); // Hash the password

        RegisterAccountDTO adminAccount = new RegisterAccountDTO();
        adminAccount.setEmail(email);
        adminAccount.setStudentId(studentId);
        adminAccount.setPassword(password); // Store hashed password
        adminAccount.setRole("ADMIN"); // Set role appropriately

        // Check if the admin account already exists
        if (!accountService.checkExistingStudentId(studentId)) {
            accountService.saveAccount(adminAccount); // Save account with hashed password
            System.out.println("Admin account created: " + email);
        } else {
            System.out.println("Admin account already exists: " + email);
        }
    }
}
