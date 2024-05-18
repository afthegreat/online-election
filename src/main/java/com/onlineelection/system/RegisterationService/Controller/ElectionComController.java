package com.onlineelection.system.RegisterationService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineelection.system.RegisterationService.Entity.ElectionComNominees;
import com.onlineelection.system.RegisterationService.Service.ElectionComNomineesService;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Service.AccountService;

@RestController
@RequestMapping("/electioncom")
public class ElectionComController {

    private final ElectionComNomineesService electionComNomineesService;
    private final AccountService accountService;

    @Autowired
    public ElectionComController(ElectionComNomineesService electionComService, AccountService accountService) {
        this.electionComNomineesService = electionComService;
        this.accountService = accountService;
    }

    // Endpoint to store data
    @PostMapping("/nominees")
    public void saveNominees(@RequestBody ElectionComNominees nominees) {
        electionComNomineesService.saveNominees(nominees);
    }

    // Endpoint to update the role of an account
    @PutMapping("/updateRole/{campaignId}")
    public void updateAccountRole(@PathVariable Long campaignId) {
        accountService.updateRole(campaignId);
    }

    // Endpoint to retrieve data (if needed)
    // Add this method if you want to retrieve data later
    /*
     * @GetMapping("/nominees")
     * public List<ElectionComNominees> getNominees() {
     * return electionComService.getNominees();
     * }
     */
}
