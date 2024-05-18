package com.onlineelection.system.UserModelService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineelection.system.RegisterationService.DTO.CampaignDto;
import com.onlineelection.system.RegisterationService.Service.CandidateService;
import com.onlineelection.system.UserModelService.DTO.RegisterAccountDTO;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Service.AccountService;

@RestController
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    private final CandidateService candidateService;

    public AccountController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody RegisterAccountDTO registeraccountDTO) throws Exception {
        String email = registeraccountDTO.getEmail();
        String studentId = registeraccountDTO.getStudentId();

        boolean isExistingEmail = accountService.checkExistingEmail(email);
        boolean isExistingId = accountService.checkExistingStudentId(studentId);
        if (isExistingEmail || isExistingId)
            return ResponseEntity.badRequest().body("Email or Id already exists");
        else {

            // Save the account or perform any other operations
            accountService.saveAccount(registeraccountDTO);

            return ResponseEntity.ok("Created successfully");
        }

    }
    // @PostMapping("/confirm")
    // public String sendPasswordTOEmail(@RequestBody RegisterAccountDTO
    // registerAccountDTO)

    // {

    // }
    @GetMapping("/{studentId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable String studentId) {
        Account account = accountService.getAccountByStudentId(studentId);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}