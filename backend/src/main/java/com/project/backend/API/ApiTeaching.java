package com.project.backend.API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.backend.Dao.DaoTeaching;
import com.project.backend.Exceptions.TeachingExceptions.ProfessorCourseCoupleAlreadyExist;
import com.project.backend.Exceptions.TeachingExceptions.ProfessorCourseCoupleDoesNotExist;
import com.project.backend.Exceptions.TeachingExceptions.UserIsNotAProfessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ApiTeaching",value = "/api/teaching")
public class ApiTeaching extends HttpServlet {
    public DaoTeaching daoT = new DaoTeaching();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        if(req.getParameter("path")!=null){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                switch (req.getParameter("path")){
                    case "getAllProfessorForASubject":{
                        if(daoT == null){
                            out.println("dao is null -- Api Teaching doGet");
                        }else{
                            int i=0;
                            ArrayList<String> professors = daoT.getAllProfessorForASubject(req.getParameter("subject"));
                            out.println("[");
                            for(String s : professors){
                                out.println("\"" + s + "\"");
                                if(i<professors.size()-1){
                                    i++;
                                    out.println(",");
                                }
                            }
                            out.println("]");
                            out.flush();
                        }
                    }
                    break;
                    case "getAllSubjectForAProfessor":{
                        if(daoT == null){
                            out.println("dao is null -- Api Teaching doGet");
                        }else{
                            int i=0;
                            ArrayList<String> subject = daoT.getAllSubjectForAProfessor(req.getParameter("email"));
                            out.println("[");
                            for(String s : subject){
                                out.println(s);
                                if(i<subject.size()-1){
                                    i++;
                                    out.println(",");
                                }
                            }
                            out.println("]");
                            out.flush();
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if(req.getParameter("path") != null){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                if(daoT == null){
                    out.println("dao is null -- Api Teaching doPost -- insertTeaching");
                }else{
                    try {
                        BufferedReader reader = req.getReader();
                        String body = "";
                        String line;
                        while((line=reader.readLine()) != null){
                            body += line;
                        }
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(body,JsonObject.class);

                        daoT.insertSubjectForProfessor(jsonObject.get("email").getAsString(),jsonObject.get("course").getAsString());
                    } catch (UserIsNotAProfessor e) {
                        jsonResponse.addProperty("error", "Failed to insert, user is not a professor");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    } catch (ProfessorCourseCoupleAlreadyExist e) {
                        jsonResponse.addProperty("error","Failed to insert,association already exist");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        String token = req.getHeader("Authorization");
        if(token != null && TokenManager.verifyToken(token)){
            if(daoT == null){
                out.println("dao is null -- Api Teaching doPost -- deleteTeaching");
            }else{
                try {
                    daoT.deleteTeaching(req.getParameter("email"),req.getParameter("subject"));
                }catch (ProfessorCourseCoupleDoesNotExist e) {
                    jsonResponse.addProperty("error","Failed to insert,association already exist");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }
}
