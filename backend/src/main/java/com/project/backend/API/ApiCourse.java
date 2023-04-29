
package com.project.backend.API;
import com.project.backend.Dao.DaoCourse;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyActive;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyExist;
import com.project.backend.Exceptions.CourseExceptions.CourseAlreadyNotActive;
import com.project.backend.Exceptions.CourseExceptions.CourseDoesNotExist;
import com.project.backend.Model.Course;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
@WebServlet(name = "ApiCourse", value = "/api/course")
public class ApiCourse extends HttpServlet {
    public DaoCourse dao = new DaoCourse();

    /*
    @Override
    public void init(ServletConfig c) throws ServletException {
        dao = (DaoCourse) c.getServletContext().getAttribute("DaoC");
    }
     */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        if(req.getParameter("path")!= null){
            switch (req.getParameter("path")){
                case "getAllCourses":{
                    if(dao == null){
                        out.println("dao is null -- API Courses doGet");
                    }else{
                        int i=0;
                        ArrayList<Course> list = dao.getAllCourses();
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        out.println("[");
                        for(Course c : list){
                            out.println("{");
                            out.println("\"name\"" + ":" + "\"" + c.getName() + "\"" + ",");
                            out.println("\"isActive\"" + ":" + "\"" + c.isActive() + "\"");
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
                case "getAllCoursesByActivity":{
                    boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
                    if (dao == null) {
                        out.println("dao is null -- API Courses doGet");
                    }else{
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        String json = g.toJson(dao.getAllCoursesByActivity(isActive));
                        JsonElement je = JsonParser.parseString(json);
                        out.println(g.toJson(je));
                        out.flush();
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
        if(req.getParameter("path")!= null){
            String name = req.getParameter("name");
            switch (req.getParameter("path")){
                case "insertCourse":{
                    if(dao == null){
                        out.println("dao is null -- API Courses doPut -- deactivateCourse");
                    }else{
                        try {
                            dao.insertCourse(name);
                            jsonResponse.addProperty("message", "Course registered successfully");
                            resp.setStatus(HttpServletResponse.SC_OK);
                        } catch (CourseAlreadyExist e) {
                            jsonResponse.addProperty("error", "Failed to register course, it already exist");
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    }
                }
                break;
                case "toggleCourse":{
                    if(dao == null){
                        out.println("dao is null -- API Courses doPut -- deactivateCourse");
                    }else{
                        try {
                            boolean activity = Boolean.parseBoolean(req.getParameter("activity"));
                            dao.toggleCourse(name,activity);
                            out.print("{" +
                                    "\"active_state\"" + ":" + "\"false\"" +" ,"+
                                    "\"state_description\"" + ":" + "\"course successfully deactivated\"" +
                                    "}");
                            out.flush();
                            resp.setStatus(HttpServletResponse.SC_OK);
                        } catch (CourseAlreadyNotActive e) {
                            out.print("{" +
                                    "\"active_state\"" + ":" + "\"???\"" +" ,"+
                                    "\"state_description\"" + ":" + "\"course is already deactivated\"" +
                                    "}");
                            out.flush();
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        } catch (CourseDoesNotExist e){
                            out.print("{" +
                                    "\"active_state\"" + ":" + "\"???\"" +" ,"+
                                    "\"state_description\"" + ":" + "\"course doesn't exist \"" +
                                    "}");
                            out.flush();
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    }
                }
                break;
            }
        }
    }


}

