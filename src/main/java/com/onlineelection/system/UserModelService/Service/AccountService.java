package com.onlineelection.system.UserModelService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineelection.system.RegisterationService.Entity.Campaign;
import com.onlineelection.system.RegisterationService.Entity.MemberOfParliament;
import com.onlineelection.system.RegisterationService.Repository.CampaignRepository;
import com.onlineelection.system.RegisterationService.Repository.MemberOfParlamentRepository;
import com.onlineelection.system.UserModelService.DTO.RegisterAccountDTO;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private CampaignRepository campaignRepository;
    private MemberOfParlamentRepository memberOfParlamentRepository;

    @Autowired
    public AccountService(MemberOfParlamentRepository memberOfParlamentRepository, AccountRepository accountRepository,
            CampaignRepository campaignRepository) {
        this.accountRepository = accountRepository;
        this.memberOfParlamentRepository = memberOfParlamentRepository;
        this.campaignRepository = campaignRepository;
    }

    public Account saveAccount(RegisterAccountDTO registeraccountDTO) throws Exception {

        Account account = new Account();
        account.setStudentId(registeraccountDTO.getStudentId());

        switch (registeraccountDTO.getRole()) {
            case "candidate":
            case "ElectionCommittee":
            case "MemberOfParliament":
            case "voter":
                account.setPassword(registeraccountDTO.getFullName().toUpperCase());

        }

        // Create MemberOfParliament entity if the role is "MemberOfParliament"
        if (registeraccountDTO.getRole().equals("MemberOfParliament")) {
            MemberOfParliament memberOfParliament = new MemberOfParliament();
            memberOfParliament.setStudentId(registeraccountDTO.getStudentId());
            memberOfParliament.setPosition(registeraccountDTO.getRole());
            memberOfParlamentRepository.save(memberOfParliament);
        }

        account.setEmail(registeraccountDTO.getEmail());
        account.setRole(registeraccountDTO.getRole());

        return accountRepository.save(account);
    }

    public Account getAccountByStudentId(String studentId) {
        return accountRepository.findByStudentId(studentId);
    }

    public boolean checkExistingStudentId(String studentId) {
        Account account = accountRepository.findByStudentId(studentId);
        return account != null;
    }

    public boolean checkExistingEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        return account != null;
    }

    public void updateRole(Long campaignId) {
        // check student with that campaign;
        Campaign campaign = campaignRepository.findByCampaignId(campaignId);
        if (campaign != null) {
            // fiind the student with that
            MemberOfParliament mop = campaign.getMemberOfParliament();// to know the owner of the candidate
            Account account = accountRepository.findByStudentId(mop.getStudentId());// the owner of the campaign
            if (account != null) {
                account.setRole("Candidate"); // Set the role to "candidate"
                accountRepository.save(account);
                mop.setPosition("Candidate");
            }

        }

    }

}