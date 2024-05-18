package com.onlineelection.system.RegisterationService.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.onlineelection.system.RegisterationService.DTO.CampaignDto;
import com.onlineelection.system.RegisterationService.Entity.Campaign;
import com.onlineelection.system.RegisterationService.Service.CandidateService;
import com.onlineelection.system.UserModelService.Entity.Account;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final CandidateService candidateService;

    @Autowired
    public CampaignController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyCampaign(
            @RequestParam("studentId") String studentId,
            @RequestParam("fullName") String fullName,
            @RequestParam("yearOfStudy") int yearOfStudy,
            @RequestParam("candidatePhoto") MultipartFile candidatePhoto,
            @RequestParam("experience") MultipartFile experience) {
        try {

            String uploadResult = candidateService.applyCampaign(
                    studentId, fullName, yearOfStudy, candidatePhoto, experience);
            return ResponseEntity.status(HttpStatus.OK).body(uploadResult);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading files: " + e.getMessage());
        }
    }

    @GetMapping("getCandidates")
    public List<Campaign> getCandidates() {
        return candidateService.getCandidates();
    }
}
