package com.example.studentasu.DB;

public class Course {
    private String name;
    private String code;
    private String drname;
    private String semester;
    private int credithour;
    private int level;


    public Course(String name, String code, String drname, String semester, int credithour, int level) {
        this.name = name;
        this.code = code;
        this.drname = drname;
        this.semester = semester;
        this.credithour = credithour;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getCredithour() {
        return credithour;
    }

    public void setCredithour(int credithour) {
        this.credithour = credithour;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
