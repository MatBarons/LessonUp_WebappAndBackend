
package com.project.backend.API;
import com.project.backend.Dao.DaoCourse;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyExist;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyNotActive;
import com.project.backend.Exceptions.CourseExceptions.CourseDoesNotExist;
import com.project.backend.Model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
@WebServlet(name = "ApiCourse", value = "/api/course")
public class ApiCourse extends HttpServlet {
    public DaoCourse dao = new DaoCourse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        if (req.getParameter("path") != null) {
            switch (req.getParameter("path")) {
                case "getAllCourses": {
                    String token = req.getHeader("Authorization");
                    if (token != null && TokenManager.verifyToken(token)) {
                        if (dao == null) {
                            out.println("dao is null -- API Courses doGet");
                        } else {
                            int i = 0;
                            ArrayList<Course> list = dao.getAllCourses();
                            out.println("[");
                            for (Course c : list) {
                                out.println("{");
                                out.println("\"name\"" + ":" + "\"" + c.getName() + "\"" + ",");
                                out.println("\"isActive\"" + ":" + "\"" + c.isActive() + "\"");
                                out.println("}");
                                if (i < list.size() - 1) {
                                    i++;
                                    out.println(",");
                                }
                            }
                            out.println("]");
                            out.flush();
                        }
                    }

                }
                break;
                case "getAllCoursesByActivity": {
                    boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
                    if (dao == null) {
                        out.println("dao is null -- API Courses doGet");
                    } else {
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        String json = g.toJson(dao.getAllCoursesByActivity(isActive));
                        JsonElement je = JsonParser.parseString(json);
                        out.println(g.toJson(je));
                        out.flush();
                    }
                    break;
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if(req.getParameter("path").equals("insertCourse")){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                if(dao == null){
                    out.println("dao is null -- API Courses doPut -- deactivateCourse");
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

                        dao.insertCourse(jsonObject.get("name").getAsString(),jsonObject.get("isActive").getAsBoolean());
                        jsonResponse.addProperty("message", "Course registered successfully");
                        resp.setStatus(HttpServletResponse.SC_OK);
                    } catch (CourseAlreadyExist e) {
                        jsonResponse.addProperty("error", "Failed to register course, it already exist");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String token = req.getHeader("Authorization");
        if(token != null && TokenManager.verifyToken(token)){
            if(dao == null){
                out.println("dao is null -- API Courses doPut -- deactivateCourse");
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

                    String name = jsonObject.get("name").getAsString();
                    boolean activity =jsonObject.get("isActive").getAsBoolean();
                    System.out.println("nome: " + name);
                    System.out.println("attività: " + activity);
                    dao.toggleCourse(name,activity);
                    out.print("{" +
                            "\"active_state\"" + ":" + "\"" + activity + "\"" +" ,"+
                            "\"state_description\"" + ":" + "\"course successfully toggled\"" +
                            "}");
                    out.flush();
                    resp.setStatus(HttpServletResponse.SC_OK);
                }catch (CourseDoesNotExist e){
                    out.print("{" +
                            "\"active_state\"" + ":" + "\"???\"" +" ,"+
                            "\"state_description\"" + ":" + "\"course doesn't exist \"" +
                            "}");
                    out.flush();
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }
}

