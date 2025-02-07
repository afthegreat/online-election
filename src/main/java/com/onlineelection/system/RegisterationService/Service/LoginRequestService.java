package com.onlineelection.system.RegisterationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import for BCrypt
import org.springframework.stereotype.Service;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Repository.AccountRepository;
import java.util.Optional;
import com.onlineelection.system.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class LoginRequestService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Add BCryptPasswordEncoder

    // Method to check if the user exists and validate password
    public Account checkUserAvailability(String studentId, String password) {
        // Fetch account by studentId
        Optional<Account> accountOptional = accountRepository.findByStudentId(studentId);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            // Logging the entered and stored password for debugging
            System.out.println("Entered password: " + password);
            System.out.println("Stored hashed password: " + account.getPassword());

            // Use passwordEncoder.matches() to compare plain password with hashed password
            if (account.getPassword() != null && passwordEncoder.matches(password, account.getPassword())) {
                System.out.println("Password match: true"); // Successful match
                return account; // Return the account if password matches
            } else {
                System.out.println("Password match: false"); // Password mismatch
            }
        }
        // Throw exception if user not found or password mismatch
        throw new UserNotFoundException("Incorrect username or password in checking user availability");
    }
}
