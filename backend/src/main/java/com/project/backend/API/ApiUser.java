package com.project.backend.API;
import com.google.gson.JsonObject;
import com.project.backend.Dao.DaoUser;
import com.project.backend.Exceptions.UserException.UserAlreadyExist;
import com.project.backend.Exceptions.UserException.UserDoesNotExist;
import com.project.backend.Model.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
@WebServlet(name = "ApiUser", value = "/api/user")
public class ApiUser extends HttpServlet {
    public DaoUser daoU = new DaoUser();

    /*
    public void  init(ServletConfig c){
        daoU = (DaoUser) c.getServletContext().getAttribute("daoU");
    }
     */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        //req.getHeader("email");
        if(req.getParameter("path")!= null){
            switch (req.getParameter("path")){
                case "getAllUsers":{

                    /*
                    if(!(APIManager.checkAuth(req.getHeader("email"),"admin") || APIManager.checkAuth(req.getHeader("email"),"student"))){
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                     */

                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        int i=0;
                        ArrayList<User> list = daoU.getAllUsers();
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        out.println("[");
                        for(User u : list){
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + u.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + u.getSurname() + "\"" + ",");
                            out.println("\"role\"" + ":" + "\"" + u.getRole() + "\"" + ",");
                            out.println("\"email\"" + ":" + "\"" + u.getEmail() + "\"");
                            out.println("}");
                            if(i<list.size()-1){
                                i++;
                                out.println(",");
                            }
                        }
                        out.println("]");
                        out.flush();
                    }
                }
                break;
                case "getUsersByRole":{
                    /*
                    if(!(APIManager.checkAuth(req.getHeader("email"),"admin") || APIManager.checkAuth(req.getHeader("email"),"student"))){
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    */
                    String role = req.getParameter("role");
                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        int i=0;
                        ArrayList<User> list = daoU.getUsersByRole(role);
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        out.println("[");
                        for(User u : list){
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + u.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + u.getSurname() + "\"" + ",");
                            out.println("\"email\"" + ":" + "\"" + u.getEmail() + "\"");
                            out.println("}");
                            if(i<list.size()-1){
                                i++;
                                out.println(",");
                            }
                        }
                        out.println("]");
                        out.flush();
                    }
                }
                break;
                case "getUserData":{
                    /*
                    if(!(APIManager.checkAuth(req.getHeader("email"),"admin") || APIManager.checkAuth(req.getHeader("email"),"student"))){
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    */
                    String email = req.getParameter("email");

                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        User user = daoU.getUserData(email);
                        out.println("{");
                        out.println("\"name\"" + ":" + "\"" + user.getName() + "\"" + ",");
                        out.println("\"surname\"" + ":" + "\"" + user.getSurname() + "\"" + ",");
                        out.println("\"role\"" + ":" + "\"" + user.getRole() + "\"" + ",");
                        out.println("\"email\"" + ":" + "\"" + user.getEmail() + "\"");
                        out.println("}");
                        out.flush();
                    }
                }
                break;
                case "getProfessorsBySubject":{
                    /*
                    if(!(APIManager.checkAuth(req.getHeader("email"),"admin") || APIManager.checkAuth(req.getHeader("email"),"student"))){
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    */
                    String subject = req.getParameter("subject");
                    int i=0;
                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        ArrayList<User> users = daoU.getProfessorsBySubject(subject);
                        out.println("[");
                        for(User u : users){
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + u.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + u.getSurname() + "\"" + ",");
                            out.println("\"email\"" + ":" + "\"" + u.getEmail() + "\"");
                            out.println("}");
                            if(i<users.size()-1){
                                i++;
                                out.println(",");
                            }
                        }
                        out.println("]");
                        out.flush();
                    }
                }
                break;
                case "logMeIn":{
                    /*
                    if(!(APIManager.checkAuth(req.getHeader("email"),"admin") || APIManager.checkAuth(req.getHeader("email"),"student"))){
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    */
                    Gson g = new Gson();
                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        String email = req.getParameter("email");
                        String password = req.getParameter("password");
                        User user = daoU.getUserData(email);
                        if(user.getPassword().equals(password)){
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + user.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + user.getSurname() + "\"" + ",");
                            out.println("\"role\"" + ":" + "\"" + user.getRole() + "\"");
                            out.println("}");
                            resp.setStatus(HttpServletResponse.SC_OK);
                            //resp.setHeader();
                        }else{
                            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
        if(!APIManager.checkAuth(req.getHeader("email"),"admin")){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        */
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if (req.getParameter("path") != null) {
            switch (req.getParameter("path")) {
                case "insertUser": {
                    User user = new User(req.getParameter("name"), req.getParameter("surname"), req.getParameter("role"), req.getParameter("email"), req.getParameter("password"));
                    if (daoU == null) {
                        out.println("dao is null -- API User doPost -- insertUser");
                    } else {
                        try {
                            daoU.insertUser(user);
                            jsonResponse.addProperty("message", "User registered successfully");
                            resp.setStatus(HttpServletResponse.SC_OK);
                        } catch (UserAlreadyExist e) {
                            jsonResponse.addProperty("error", "Failed to register user, email already used");
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    }
                }
                break;
                case "changePassword": {
                    if (daoU == null) {
                        out.println("dao is null -- API User doPost -- changePassword");
                    } else {
                        try {
                            String email = req.getParameter("email");
                            String oldPassword = req.getParameter("oldPassword");
                            String newPassword = req.getParameter("newPassword");
                            User user = daoU.getUserData(email);
                            if (user.getPassword().equals(oldPassword)) {
                                daoU.updatePassword(user.getEmail(), newPassword);
                                jsonResponse.addProperty("message", "Password changed successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            } else {
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        } catch (UserDoesNotExist e) {
                            jsonResponse.addProperty("error", "Failed to update password, user doesn't exist");
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    }
                }
                break;
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
        if(!APIManager.checkAuth(req.getHeader("email"),"admin")){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
           */
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if(req.getParameter("path")!= null){
            User user = gson.fromJson(req.getReader(),User.class);
            if(daoU == null){
                out.println("dao is null -- API User doDelete -- deleteUser");
            }else{
                try{
                    daoU.deleteUser(user.getEmail());
                    jsonResponse.addProperty("message", "User deleted successfully");
                    resp.setStatus(HttpServletResponse.SC_OK);
                }catch (UserDoesNotExist e){
                    jsonResponse.addProperty("error", "Failed to delete user, user doesn't exist");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }
}


