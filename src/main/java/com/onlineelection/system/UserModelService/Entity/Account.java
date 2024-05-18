package com.onlineelection.system.UserModelService.Entity;

import com.onlineelection.system.RegisterationService.Entity.ElectionComNominees;
import com.onlineelection.system.RegisterationService.Entity.MemberOfParliament;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Account {
    @Id
    private String studentId;// measho

    private String password;// 123
    private String email;// measho@gmila.com
    private String role;// memberOfParlament

    // Constructors
    public Account() {
    }

    public Account(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public Account(String studentId, String password, String email, String role) {
        this.studentId = studentId;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @OneToOne(mappedBy = "account")
    private MemberOfParliament memberOfParlament;
    @OneToOne(mappedBy = "account")
    private ElectionComNominees electionComNominees;

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
