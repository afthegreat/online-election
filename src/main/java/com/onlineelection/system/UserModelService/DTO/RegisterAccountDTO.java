package com.onlineelection.system.UserModelService.DTO;

public class RegisterAccountDTO {
    private String student_id;
    private String full_name;
    private String email;
    private String role;

    private RegisterAccountDTO(String student_id, String full_name, String email, String role) {
        this.student_id = student_id;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;

    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;

    }

    public String getStudentId() {
        return this.student_id;
    }

    public String getFullName() {
        return this.full_name.toUpperCase();
    }

    public String getEmail() {
        return this.email;

    }

    public String getRole() {
        return this.role;
    }
}
