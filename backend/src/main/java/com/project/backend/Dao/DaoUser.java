package com.project.backend.Dao;
import com.project.backend.Exceptions.DaoExceptions;
import com.project.backend.Exceptions.UserException.UserAlreadyExist;
import com.project.backend.Exceptions.UserException.UserDoesNotExist;
import com.project.backend.Model.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUser extends Dao{

    public static final String insertUser = "INSERT INTO User (name,surname,role,email,password,isActive) Values(?,?,?,?,?,?);";
    public static final String toggleUserActivity = "UPDATE User SET isActive=? WHERE email=?";
    public static final String updatePassword = "UPDATE User SET password=? WHERE email=?;";
    private static final String doesExist =  "SELECT * FROM User WHERE email=?;";
    public static final String getAllUsers = "SELECT * FROM User;";
    public static final String getUserData = "SELECT * FROM User WHERE email=?;";
    public static final String getUsersByRole = "SELECT * FROM User WHERE role=?;";
    public static final String getProfessorsBySubject = "SELECT u.email,u.name,u.surname FROM Teaching t JOIN User u ON(t.emailProf = u.email) WHERE t.nomeCorso=? AND u.isActive=?;";
    public static final String checkAuth = "SELECT * FROM User WHERE email=? AND role=?;";
    public DaoUser() {
        super();
    }



    public void insertUser(User user) throws UserAlreadyExist{
        try{
            if(doesExist(user.getEmail())){
                throw new UserAlreadyExist("This user already exist -- insertUser");
            }
            launchUpdate(insertUser,user.getName(),user.getSurname(),user.getRole(),user.getEmail(),user.getPassword(),user.isActive());
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }
    public void toggleUserActivity(String email,boolean activity) throws UserDoesNotExist {
        try{
            if(!doesExist(email)){
                throw new UserDoesNotExist("This user doesn't exist -- deleteUser");
            }
            launchUpdate(toggleUserActivity,activity ? 1 : 0,email);
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }

    public void updatePassword(String email,String password) throws UserDoesNotExist{
        try {
            if (!doesExist(email)) {
                throw new UserDoesNotExist("This course doesn't exist -- updatePassword");
            }
            launchUpdate(updatePassword, password, email);
        }catch (DaoExceptions d){
            System.out.println(d.getMessage());
            d.printStackTrace();
        }
    }

    private boolean doesExist(String email){
        boolean check = false;
        try{
            CachedRowSet rs = launchQuery(doesExist,email);
            check = rs.next();
        } catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
            check = false;
        }catch (DaoExceptions e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            check = false;
        }
        return check;
    }
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getAllUsers);
            while(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String isActive = rs.getString("isActive");
                users.add(new User(name,surname,role,email,password,Boolean.parseBoolean(isActive)));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<User> getUsersByRole(String role){
        ArrayList<User> users = new ArrayList<>();
        try{
            CachedRowSet rs = launchQuery(getUsersByRole,role);
            while(rs.next()){
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String isActive = rs.getString("isActive");
                users.add(new User(name,surname,role,email,password,Boolean.parseBoolean(isActive)));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public User getUserData(String email){
        User user = null;
        try{
            System.out.println(email);
            CachedRowSet rs = launchQuery(getUserData,email);
            //rs.beforeFirst();
            rs.next();
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String role = rs.getString("role");
            String password = rs.getString("password");
            String isActive = rs.getString("isActive");
            user = new User(name,surname,role,email,password,Boolean.parseBoolean(isActive));
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet: "+ e.getCause());
            e.printStackTrace();
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getProfessorsBySubject(String subject,boolean isActive){
        ArrayList<User> professors = new ArrayList<>();
        try {

            CachedRowSet rs = launchQuery(getProfessorsBySubject,subject,isActive);
            while (rs.next()) {
                String email = rs.getString("email");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                professors.add(new User(name,surname,email));
            }
        }catch (SQLException e){
            System.out.println("Exception caused by CachedRowSet");
        }catch (DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return professors;
    }

    public boolean checkAuth(String email,String role){
        boolean check=false;
        try{
            CachedRowSet rs = launchQuery(checkAuth,email,role);
            check = rs.next();
        }catch(SQLException e){
            System.out.println("Exception caused by CachedRowSet");
            check = false;
        }catch(DaoExceptions e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            check = false;
        }
        return check;
    }
}
