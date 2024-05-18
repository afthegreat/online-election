
package com.onlineelection.system.RegisterationService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections; // Import for Collections class
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineelection.system.RegisterationService.DTO.LoginRequest;
import com.onlineelection.system.RegisterationService.Service.LoginRequestService;
import com.onlineelection.system.UserModelService.Entity.Account;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/login")
public class LoginRequestController {

    @Autowired
    private LoginRequestService loginRequestService;

    @PostMapping("/check")
    public ResponseEntity<?> checkUserAvailability(@RequestBody LoginRequest loginRequest,
            HttpServletResponse response) {
        Account account = loginRequestService.checkUserAvailability(loginRequest.getStudentId(),
                loginRequest.getPassword());
        if (account != null) {
            // User found
            switch (account.getRole().toLowerCase()) {
                case "admin":
                    response.setHeader("Location", "/admin");
                    return ResponseEntity.ok().body(Collections.singletonMap("role", "admin"));
                case "voter":
                    response.setHeader("Location", "/voter");
                    return ResponseEntity.ok().body(Collections.singletonMap("role", "voter"));
                case "candidate":
                    response.setHeader("Location", "/candidateDashboard");
                    return ResponseEntity.ok().body(Collections.singletonMap("role", "candidate"));
                case "memberofparliament":
                    response.setHeader("Location", "/memberOfParliament");
                    return ResponseEntity.ok().body(Collections.singletonMap("role", "memberOfParliament"));
                case "electioncommittee":
                    response.setHeader("Location", "/electioncommittee");
                    return ResponseEntity.ok().body(Collections.singletonMap("role", "electionCommittee"));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown role");
            }
        } else {
            // User not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect username or password");
        }
    }

}
