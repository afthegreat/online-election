
package com.onlineelection.system.RegisterationService.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

import com.onlineelection.system.UserModelService.Entity.Account;

@Entity
@Table(name = "ElectionComNominees")
public class ElectionComNominees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String firstName;
    private String lastName;
    private String yearOfStudy;
    private String phoneNumber;
    private String committeeDescription;
    @OneToOne
    @JoinColumn(name = "account_id") // Foreign key column in ElectionCommitteeMember table
    private Account account;

    // Getter methods

    public Account getAccount() {
        return account;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getfirstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCommitteeDescription() {
        return committeeDescription;
    }

    // Setter methods

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCommitteeDescription(String committeeDescription) {
        this.committeeDescription = committeeDescription;
    }

}
