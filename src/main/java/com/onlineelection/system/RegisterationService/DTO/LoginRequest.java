package com.onlineelection.system.RegisterationService.DTO;

public class LoginRequest {

    private String studentId;
    private String password;
    private String email;

    // Getters and setters
    // public LoginRequest(String studentId, String password, String email) {
    // this.studentId = studentId;
    // this.password = password;
    // this.email = email;
    // }

    public LoginRequest(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setPassword(String password) {
        this.password = password;
    }
}