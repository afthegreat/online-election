package com.onlineelection.system.RegisterationService.Entity; // Add the correct package declaration

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;

    // Update the fields to store file paths instead of binary data
    private String candidatePhotoPath;
    private String candidateExperiencePath;

    @OneToOne
    @JoinColumn(name = "member_of_parliament_id")
    private MemberOfParliament memberOfParliament;

    // Constructors
    public Campaign() {
    }

    public Campaign(String candidatePhotoPath, String candidateExperiencePath, MemberOfParliament memberOfParliament) {
        this.candidatePhotoPath = candidatePhotoPath;
        this.candidateExperiencePath = candidateExperiencePath;
        this.memberOfParliament = memberOfParliament;
    }

    // Getters and Setters
    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCandidatePhotoPath() {
        return candidatePhotoPath;
    }

    public void setCandidatePhotoPath(String candidatePhotoPath) {
        this.candidatePhotoPath = candidatePhotoPath;
    }

    public String getCandidateExperiencePath() {
        return candidateExperiencePath;
    }

    public void setCandidateExperiencePath(String candidateExperiencePath) {
        this.candidateExperiencePath = candidateExperiencePath;
    }

    public MemberOfParliament getMemberOfParliament() {
        return memberOfParliament;
    }

    public void setMemberOfParliament(MemberOfParliament memberOfParliament) {
        this.memberOfParliament = memberOfParliament;
    }
}
