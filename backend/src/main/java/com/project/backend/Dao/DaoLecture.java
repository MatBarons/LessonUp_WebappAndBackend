package com.project.backend.Dao;
import com.project.backend.Exceptions.DaoExceptions;
import com.project.backend.Exceptions.LectureExceptions.LectureAlreadyExist;
import com.project.backend.Exceptions.LectureExceptions.LectureDoesNotExist;
import com.project.backend.Model.Lecture;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.project.backend.Dao.DaoUser;

public class DaoLecture extends Dao{//DECIDI LE CHIAVI NEL DB
    public static final String insertLecture = "INSERT INTO Lecture (student,professor,subject,date,time,status) Values(null,?,?,?,?,?);";
    public static final String deleteLecture = "DELETE FROM Lecture WHERE professor=? AND date=? AND time=? AND subject=?;";
    public static final String doesExist = "SELECT * FROM Lecture WHERE professor=? AND date=? AND time=? AND subject=?;";
    public static final String changeStatusAndStudent = "UPDATE Lecture SET student=?,status=? WHERE date=? AND time=? AND professor=? AND subject=?;";
    public static final String changeStatus = "UPDATE Lecture SET status=? WHERE date=? AND time=? AND professor=? AND subject=?;";
    public static final String getLecturesBySubjectAndStatusAndDate = "SELECT l.time,l.subject,l.professor,u.name,u.surname FROM Lecture l JOIN User u ON(l.professor = u.email) WHERE l.subject=? AND l.status=? AND l.date=?;";
    public static final String getLecturesByStudentAndStatus = "SELECT l.date,l.time,l.subject,l.professor,u.name,u.surname FROM Lecture l JOIN User u ON(l.professor = u.email) WHERE l.student=? AND l.status=?;";

    public DaoLecture() {
        super();
    }

    public void insertLecture(Lecture lecture) throws LectureAlreadyExist {
        try{
            if(doesExist(lecture.getProfessor(), lecture.getSubject(), lecture.getDate(), lecture.getTime())){
                throw new LectureAlreadyExist("This lecture already exist -- insertLecture");
            }
            launchUpdate(insertLecture,lecture.getProfessor(), lecture.getSubject(), lecture.getDate(), lecture.getTime());
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }
    public void deleteLecture(Lecture lecture) throws LectureDoesNotExist {
        try {
            if (!doesExist(lecture.getProfessor(), lecture.getSubject(), lecture.getDate(), lecture.getTime())) {
                throw new LectureDoesNotExist("This lecture doesn't exist -- insertLecture");
            }
            launchUpdate(deleteLecture, lecture.getProfessor(), lecture.getSubject(), lecture.getDate(), lecture.getTime());
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }

    private boolean doesExist(String professor,String subject,Date date,Time time){
        boolean check = false;
        try{
            CachedRowSet rs = launchQuery(doesExist,professor,date,time,subject);
            check = rs.next();
        }catch (SQLException e){
            System.out.println("PreparedStatement didn't close correctly in launchUpdate");
            e.printStackTrace();
            check = false;
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
            check = false;
        }
        return check;
    }

    public void changeStatusAndStudent(Lecture lecture, String status, String student) throws LectureDoesNotExist{
        try{
            if(!doesExist(lecture.getProfessor(),lecture.getSubject(),lecture.getDate(),lecture.getTime())){ //professor,subject,date,time
                throw new LectureDoesNotExist("This lecture doesn't exist -- insertLecture");
            }
            launchUpdate(changeStatusAndStudent,student,status,lecture.getDate(),lecture.getTime(),lecture.getProfessor(),lecture.getSubject());
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }

    public void changeStatus(Lecture lecture, String status) throws LectureDoesNotExist{
        try{
            if(!doesExist(lecture.getProfessor(),lecture.getSubject(),lecture.getDate(),lecture.getTime())){ //professor,subject,date,time
                throw new LectureDoesNotExist("This lecture doesn't exist -- insertLecture");
            }
            launchUpdate(changeStatus,status,lecture.getDate(),lecture.getTime(),lecture.getProfessor(),lecture.getSubject());
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }
    public ArrayList<Lecture> getLecturesBySubjectAndStatusAndDate(String subject,String status,Date date){
        ArrayList<Lecture> lectures = new ArrayList<>();
        try {
            CachedRowSet rs = launchQuery(getLecturesBySubjectAndStatusAndDate, subject, status, date);
            while (rs.next()) {
                String profName = rs.getString("name");
                String profSurname = rs.getString("surname");
                String email = rs.getString("professor");
                Time time = rs.getTime("time");
                lectures.add(new Lecture(date, time, profName, profSurname, email,subject));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lectures;
    }

    public ArrayList<Lecture> getLecturesByStudentAndStatus(String student,String status){
        ArrayList<Lecture> lectures = new ArrayList<>();
        try {
            CachedRowSet rs = launchQuery(getLecturesByStudentAndStatus, student, status);
            while (rs.next()) {
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                String profName = rs.getString("name");
                String profSurname = rs.getString("surname");
                String email = rs.getString("professor");
                String subject = rs.getString("subject");
                lectures.add(new Lecture(date, time, profName,profSurname,email,subject));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return lectures;
    }
}
