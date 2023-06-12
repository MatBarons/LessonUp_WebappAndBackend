package com.project.backend.API;

import com.project.backend.Dao.DaoUser;
import com.project.backend.Exceptions.UserException.UserAlreadyExist;
import com.project.backend.Exceptions.UserException.UserDoesNotExist;
import com.project.backend.Model.User;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
@WebServlet(name = "ApiUser", value = "/api/user")
public class ApiUser extends HttpServlet {
    public DaoUser daoU = new DaoUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        if(req.getParameter("path")!= null){
            switch (req.getParameter("path")){
                case "getAllUsers":{
                    String token = req.getHeader("Authorization");
                    if(token != null && TokenManager.verifyToken(token)){
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
                    }else{
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
                break;
                case "getAllUsersByRole":{
                    String token = req.getHeader("Authorization");
                    if(token != null && TokenManager.verifyToken(token)){
                        if(daoU == null){
                            out.println("dao is null -- API User doGet");
                        }else{
                            String role = req.getParameter("role");
                            int i=0;
                            ArrayList<User> list = daoU.getUsersByRole(role);
                            Gson g = new GsonBuilder().setPrettyPrinting().create();
                            out.println("[");
                            for(User u : list){
                                out.println("{");
                                out.println("\"name\"" + ":" + "\"" + u.getName() + "\"" + ",");
                                out.println("\"surname\"" + ":" + "\"" + u.getSurname() + "\"" + ",");
                                out.println("\"email\"" + ":" + "\"" + u.getEmail() + "\"" + ",");
                                out.println("\"password\"" + ":" + "\"" + u.getPassword() + "\"" + ",");
                                out.println("\"role\"" + ":" + "\"" + u.getRole() + "\"" + ",");
                                out.println("\"isActive\"" + ":" + "\"" + u.isActive() + "\"");
                                out.println("}");
                                if(i<list.size()-1){
                                    i++;
                                    out.println(",");
                                }
                            }
                            out.println("]");
                            out.flush();
                        }
                    }else{
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
                break;
                case "getUserData":{
                    String token = req.getHeader("Authorization");
                    if(token != null && TokenManager.verifyToken(token)){
                        if(daoU == null){
                            out.println("dao is null -- API User doGet");
                        }else{
                            String email = req.getParameter("email");
                            User user = daoU.getUserData(email);
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + user.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + user.getSurname() + "\"" + ",");
                            out.println("\"role\"" + ":" + "\"" + user.getRole() + "\"" + ",");
                            out.println("\"email\"" + ":" + "\"" + user.getEmail() + "\"");
                            out.println("}");
                            out.flush();
                        }
                    }else{
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
                break;
                case "getProfessorsBySubject":{
                    String token = req.getHeader("Authorization");
                    if(token != null && TokenManager.verifyToken(token)){
                        if(daoU == null){
                            out.println("dao is null -- API User doGet");
                        }else{
                            String subject = req.getParameter("subject");
                            int i=0;
                            ArrayList<User> users = daoU.getProfessorsBySubject(subject,true);
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
                    }else{
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }
                break;
                case "logMeIn":{
                    if(daoU == null){
                        out.println("dao is null -- API User doGet");
                    }else{
                        String email = req.getParameter("email");
                        String password = req.getParameter("password");
                        User user = daoU.getUserData(email);
                        if(daoU.checkMD5(user.getPassword(),password) && !user.getRole().equals("professor")){
                            String token = TokenManager.generateToken(email);
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + user.getName() + "\"" + ",");
                            out.println("\"surname\"" + ":" + "\"" + user.getSurname() + "\"" + ",");
                            out.println("\"role\"" + ":" + "\"" + user.getRole() + "\"" + ",");
                            out.println("\"token\"" + ":" + "\"" + token + "\"");
                            out.println("}");
                            resp.setStatus(HttpServletResponse.SC_OK);
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
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if (req.getParameter("path") != null) {
            if(TokenManager.verifyToken(req.getHeader("Authorization"))){
                User user = null;
                if (daoU == null) {
                    out.println("dao is null -- API User doPost -- insertUser");
                } else {
                    try {
                        BufferedReader reader = req.getReader();
                        String body = "";
                        String line;
                        while((line=reader.readLine()) != null){
                            body += line;
                        }
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(body,JsonObject.class);

                        String name = jsonObject.get("name").getAsString();
                        String surname = jsonObject.get("surname").getAsString();
                        String email = jsonObject.get("email").getAsString();
                        String password = jsonObject.get("password").getAsString();
                        String role = jsonObject.get("role").getAsString();
                        boolean isActive = jsonObject.get("email").getAsBoolean();

                        user = new User(name,surname,role,email,password,isActive);
                        daoU.insertUser(user);
                        jsonResponse.addProperty("message", "User registered successfully");
                        resp.setStatus(HttpServletResponse.SC_OK);
                    } catch (UserAlreadyExist e) {
                        jsonResponse.addProperty("error", "Failed to register user, email already used");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
            }else{
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if (req.getParameter("path") != null) {
            if (TokenManager.verifyToken(req.getHeader("Authorization"))) {
                switch (req.getParameter("path")) {
                    case "changePassword": {
                        if (daoU == null) {
                            out.println("dao is null -- API User doPost -- changePassword");
                        } else {
                            try {

                                BufferedReader reader = req.getReader();
                                String body = "";
                                String line;
                                while((line=reader.readLine()) != null){
                                    body += line;
                                }
                                Gson gson = new Gson();
                                JsonObject jsonObject = gson.fromJson(body,JsonObject.class);

                                String email = jsonObject.get("email").getAsString();
                                String oldPassword = jsonObject.get("oldPassword").getAsString();
                                String newPassword = jsonObject.get("newPassword").getAsString();
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
                    case "toggleUser":{
                        if(daoU == null){
                            out.println("dao is null -- API User doPut -- toggleUser");
                        }else{
                            try{

                                BufferedReader reader = req.getReader();
                                String body = "";
                                String line;
                                while((line=reader.readLine()) != null){
                                    body += line;
                                }
                                Gson gson = new Gson();
                                JsonObject jsonObject = gson.fromJson(body,JsonObject.class);


                                String email = jsonObject.get("email").getAsString();
                                boolean activity = jsonObject.get("isActive").getAsBoolean();
                                daoU.toggleUserActivity(email,activity);
                                jsonResponse.addProperty("message", "User deleted successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            }catch (UserDoesNotExist e){
                                jsonResponse.addProperty("error", "Failed to delete user, user doesn't exist");
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                    break;
                    case "updateAllPasswords":{
                        if(daoU == null){
                            out.println("dao is null -- API User doPut -- updateAllPasswords");
                        }else{
                            daoU.updateAllPassword();
                        }
                    }
                    break;
                }
            }else{
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }
}


