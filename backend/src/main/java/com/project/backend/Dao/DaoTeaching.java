package com.project.backend.Dao;

import com.project.backend.Exceptions.DaoExceptions;
import com.project.backend.Exceptions.TeachingExceptions.ProfessorCourseCoupleAlreadyExist;
import com.project.backend.Exceptions.TeachingExceptions.UserIsNotAProfessor;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoTeaching extends Dao{

    public static final String insertSubjectForProfessor = "INSERT INTO Teaching (emailProf,nomeCorso) Values(?,?)";
    public static final String getAllProfessorForASubject = "SELECT emailProf FROM Teaching WHERE nomeCorso=?";
    public static final String getAllSubjectForAProfessor = "SELECT nomeCorso FROM Teaching WHERE emailProf=?";
    public static final String doesExist = "SELECT * FROM Teaching WHERE email=? AND nomeCorso=?";
    public static final String isAProfessor = "SELECT * FROM User WHERE email=? AND role=\"professor\"";

    public DaoTeaching(){super();}

    public void insertSubjectForProfessor(String emailProf,String subject) throws UserIsNotAProfessor,ProfessorCourseCoupleAlreadyExist{
        try{
            if(isAProfessor(emailProf)){
                if(doesExist(emailProf,subject)){
                    launchUpdate(insertSubjectForProfessor,emailProf,subject);
                }else{
                    throw new ProfessorCourseCoupleAlreadyExist("This professor/subject couple already exist -- insertTeaching");
                }
            }else {
                throw new UserIsNotAProfessor("This user is not a professor -- insertTeaching");
            }
        } catch (DaoExceptions e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isAProfessor(String emailProf){
        boolean check=false;
        try{
            CachedRowSet rs = launchQuery(isAProfessor,emailProf);
            check = rs.next();
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
            check = false;
        }
        return check;

    }
    private boolean doesExist(String emailProf,String subject){
        boolean check=false;
        try{
            CachedRowSet rs = launchQuery(doesExist,emailProf,subject);
            check = rs.next();
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
            check = false;
        }
        return check;
    }

    public ArrayList<String> getAllProfessorForASubject(String subject){
        ArrayList<String> professors = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getAllProfessorForASubject,subject);
            while(rs.next()){
                String prof = rs.getString("emailProf");
                professors.add(prof);
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return professors;
    }

    public ArrayList<String> getAllSubjectForAProfessor(String professor){
        ArrayList<String> subject = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getAllSubjectForAProfessor,professor);
            while(rs.next()){
                String prof = rs.getString("nomeCorso");
                subject.add(prof);
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return subject;
    }
}
