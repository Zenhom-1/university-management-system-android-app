package com.example.studentasu.DB;

public class Student {
    private long id;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String department;
    private int level;

    public Student(long id, String username, String mobile, String email, String department, int level, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.department = department;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
