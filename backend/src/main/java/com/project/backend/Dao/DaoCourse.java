package com.project.backend.Dao;

import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyActive;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyExist;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyNotActive;
import com.project.backend.Exceptions.CourseExceptions.CourseDoesNotExist;
import com.project.backend.Exceptions.DaoExceptions;
import com.project.backend.Model.Course;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCourse extends Dao{
    public static final String insertCourse = "INSERT INTO Course (name,isActive) Values(?,?);";
    public static final String toggleCourse = "UPDATE Course SET isActive=? WHERE name = ?;";
    public static final String doesExist = "SELECT * FROM Course WHERE name=?;";
    public static final String getAllCourses = "SELECT * FROM Course;";
    public static final String getAllCoursesByActivity = "SELECT * FROM Course WHERE isActive=?;";
    public DaoCourse() {
        super();
    }

    public void insertCourse(String name,boolean activity) throws CourseAlreadyExist {
        try{
            if(doesExist(name)){
                throw new CourseAlreadyExist("This course already exist -- insertCourse");
            }
            launchUpdate(insertCourse,name,activity ? 1 : 0);
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.getStackTrace();
        }
    }
    public void toggleCourse(String name,boolean activity) throws CourseDoesNotExist{
        try{
            if(!doesExist(name)){
                throw new CourseDoesNotExist("This course doesn't exist -- deactivateCourse");
            }
            launchUpdate(toggleCourse,activity,name);
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }

    private boolean doesExist(String name){
        boolean check = false;
        try{
            CachedRowSet rs = launchQuery(doesExist,name);
            check = rs.next();
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
            check = false;
        }
        return check;
    }


    public ArrayList<Course> getAllCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getAllCourses);
            while(rs.next()){
                //String id = rs.getString("ID");
                String name = rs.getString("name");
                boolean isActive = rs.getBoolean("isActive");
                courses.add(new Course(name,isActive));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
        return courses;
    }

    public ArrayList<String> getAllCoursesByActivity(boolean activity){
        ArrayList<String> courses = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getAllCoursesByActivity,activity);
            while(rs.next()){
                String name = rs.getString("name");
                courses.add(name);
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
        return courses;
    }
}
