package com.project.backend.Model;

import java.sql.Time;
import java.time.*;
import java.util.Date;

public class Lecture{
    private Date date;
    private Time time;
    private String student;
    private String professor;
    private String profName;
    private String profSurname;
    private String subject;
    private String status;

    public Lecture(Date date,Time time,String profName,String profSurname, String professor,String subject){
        this.date = date;
        this.time = time;
        this.profName = profName;
        this.profSurname = profSurname;
        this.professor = professor;
        this.subject = subject;
    }



    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfSurname() {
        return profSurname;
    }

    public void setProfSurname(String profSurname) {
        this.profSurname = profSurname;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
