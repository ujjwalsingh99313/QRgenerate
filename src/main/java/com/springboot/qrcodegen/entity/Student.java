package com.springboot.qrcodegen.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rollNo;
    private String name;
    private String email;
    private double paper1Marks;
    private double paper2Marks;

    public Student() {
    }

    public Student(Long id, String rollNo, String name, String email, double paper1Marks, double paper2Marks) {
        this.id = id;
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.paper1Marks = paper1Marks;
        this.paper2Marks = paper2Marks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPaper1Marks() {
        return paper1Marks;
    }

    public void setPaper1Marks(double paper1Marks) {
        this.paper1Marks = paper1Marks;
    }

    public double getPaper2Marks() {
        return paper2Marks;
    }

    public void setPaper2Marks(double paper2Marks) {
        this.paper2Marks = paper2Marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", paper1Marks='" + paper1Marks + '\'' +
                ", paper2Marks='" + paper2Marks + '\'' +
                '}';
    }
}


