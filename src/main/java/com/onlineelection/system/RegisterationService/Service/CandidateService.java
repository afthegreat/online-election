package com.onlineelection.system.RegisterationService.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.onlineelection.system.RegisterationService.DTO.CampaignDto;
import com.onlineelection.system.RegisterationService.Entity.Campaign;
import com.onlineelection.system.RegisterationService.Entity.MemberOfParliament;
import com.onlineelection.system.RegisterationService.Repository.CampaignRepository;
import com.onlineelection.system.RegisterationService.Repository.MemberOfParlamentRepository;
import com.onlineelection.system.RegisterationService.Utils.ImageUtils;
import com.onlineelection.system.UserModelService.Entity.Account;
import com.onlineelection.system.UserModelService.Repository.AccountRepository;
import com.onlineelection.system.UserModelService.Service.AccountService;

@Service
public class CandidateService {
    @Autowired

    private final CampaignRepository campaignRepository;
    private final AccountService accountService;
    private final MemberOfParlamentRepository memberOfParlamentRepository;

    @Autowired
    public CandidateService(MemberOfParlamentRepository memberOfParlamentRepository,
            CampaignRepository campaignRepository, AccountService accountService) {
        this.campaignRepository = campaignRepository;
        this.accountService = accountService;
        this.memberOfParlamentRepository = memberOfParlamentRepository;
    }

    public List<Campaign> getCandidates() {
        return campaignRepository.findAll();
    }

    public String getMemberOfParliament() {
        return "";
    }

    @Transactional
    public String applyCampaign(String studentId, String fullName, int yearOfStudy, MultipartFile candidatePhoto,
            MultipartFile experience) throws IOException {
        if (studentId.isEmpty() || fullName.isEmpty() || yearOfStudy <= 0 || candidatePhoto.isEmpty()
                || experience.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        // Retrieve account by student ID
        Account account = accountService.getAccountByStudentId(studentId);
        if (account == null) {
            // If account is not found, throw an exception
            throw new IllegalArgumentException("Account not found for student ID: " + studentId);
        }

        // Check if the account has the role of "memberOfParliament"
        if (!account.getRole().equals("MemberOfParliament")) {
            // If not, throw an exception indicating that only registered members of
            // parliament can apply for a campaign
            throw new IllegalArgumentException("Only registered members of parliament can apply for a campaign.");
        }

        // Retrieve or create MemberOfParliament
        MemberOfParliament memberOfParliament = memberOfParlamentRepository.findByStudentId(studentId);
        if (memberOfParliament == null) {
            // If MemberOfParliament is not found, create a new one
            memberOfParliament = saveMemberOfParliament(studentId, account.getRole());
            memberOfParlamentRepository.save(memberOfParliament);
        }

        // Save candidate files
        String photoPath = saveFile(candidatePhoto, "images");
        String experiencePath = saveFile(experience, "files");

        // Save Campaign
        Campaign campaign = new Campaign(photoPath, experiencePath, memberOfParliament);
        campaignRepository.save(campaign);

        return "Campaign application submitted successfully.";
    }

    private String saveFile(MultipartFile file, String directory) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        // Ensure directory exists
        File dir = new File("public/" + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Generate a unique filename
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        // Path for storing the file
        Path path = Paths.get("public/" + directory + "/" + fileName);
        // Save file
        Files.write(path, file.getBytes());
        return directory + "/" + fileName;
    }

    public MemberOfParliament saveMemberOfParliament(String studentid, String position) {
        MemberOfParliament mop = new MemberOfParliament();
        mop.setPosition(position);
        mop.setStudentId(studentid);
        return mop;
    }
}
