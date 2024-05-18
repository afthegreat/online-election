package com.onlineelection.system.RegisterationService.Entity;

import com.onlineelection.system.UserModelService.Entity.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class MemberOfParliament {
    @Id
    private String studentId; // Use studentId as primary identifier

    private String position;//

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public MemberOfParliament(String studentId, String position) {
        this.studentId = studentId;
        this.position = position;
    }

    public MemberOfParliament() {

    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
