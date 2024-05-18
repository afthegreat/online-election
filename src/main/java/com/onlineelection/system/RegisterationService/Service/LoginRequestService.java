package com.onlineelection.system.RegisterationService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Repository.AccountRepository;

@Service
public class LoginRequestService {

    @Autowired
    private AccountRepository accountRepository;

    public Account checkUserAvailability(String studentId, String password) {
        // Retrieve account details based on student ID and password
        Account account = accountRepository.findByStudentIdAndPassword(studentId, password);
        return account;
    }

}
