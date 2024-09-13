package com.example.demo1.model;

public class Student {
    private String studentId;
    private String fullName;
    private String studentClass;

    public Student(String studentId, String fullName, String studentClass) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.studentClass = studentClass;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
