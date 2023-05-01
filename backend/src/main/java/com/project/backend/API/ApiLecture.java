
package com.project.backend.API;
import com.google.gson.JsonObject;
import com.project.backend.Dao.DaoLecture;
import com.project.backend.Exceptions.LectureExceptions.LectureAlreadyExist;
import com.project.backend.Exceptions.LectureExceptions.LectureDoesNotExist;
import com.project.backend.Model.Lecture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ApiLecture", value = "/api/lecture")
public class ApiLecture extends HttpServlet {
    public DaoLecture dao = new DaoLecture();
    /*
    @Override
    public void init(ServletConfig c) throws ServletException {
        dao = (DaoLecture) c.getServletContext().getAttribute("Dao");
    }
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        if(req.getParameter("path")!= null){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                switch (req.getParameter("path")){
                    case "getAllLecturesBySubjectAndStatusAndDate":{
                        String subject = req.getParameter("subject");
                        String status = req.getParameter("status");
                        String d = req.getParameter("date");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        System.out.println(d);
                        try {
                            date = format.parse(d);
                        } catch (ParseException e) {
                            System.out.println("wrong date format, please use yyyy-MM-dd");
                            throw new RuntimeException(e);
                        }
                        if(dao == null){
                            out.println("dao is null -- API Lecture doGet");
                        }else{
                            int i=0;
                            ArrayList<Lecture> list = dao.getLecturesBySubjectAndStatusAndDate(subject,status,date);
                            Gson g = new GsonBuilder().setPrettyPrinting().create();
                            Date comodo;
                            try {
                                comodo = format.parse(d);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            format.applyPattern("dd/MM/yyyy");
                            d = format.format(comodo);
                            out.println("[");
                            for(Lecture l : list){
                                out.println("{");
                                out.println("\"date\"" + ":" + "\"" + d + "\"" + ",");
                                out.println("\"time\"" + ":" + "\"" + l.getTime().toString().substring(0,5)+ "\"" + ",");
                                out.println("\"name\"" + ":" + "\"" + l.getProfName()+ "\"" + ",");
                                out.println("\"surname\"" + ":" + "\"" + l.getProfSurname()+ "\"" + ",");
                                out.println("\"email\"" + ":" + "\"" + l.getProfessor()+ "\"" + ",");
                                out.println("\"subject\"" + ":" + "\"" + l.getSubject() + "\"");
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
                    case "getAllLecturesByStudentAndStatus":{
                        String student = req.getParameter("student");
                        String status = req.getParameter("status");
                        Gson g = new GsonBuilder().setPrettyPrinting().create();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        if(dao == null){
                            out.println("dao is null -- API Lecture doGet");
                        }else{
                            try{
                                int i=0;
                                ArrayList<Lecture> list = dao.getLecturesByStudentAndStatus(student,status);
                                out.println("[");
                                for(Lecture l : list){
                                    out.println("{");
                                    out.println("\"date\"" + ":" + "\"" + sdf.format( l.getDate()) + "\"" + ",");
                                    out.println("\"time\"" + ":" + "\"" + l.getTime().toString().substring(0,5)+ "\"" + ",");
                                    out.println("\"name\"" + ":" + "\"" + l.getProfName()+ "\"" + ",");
                                    out.println("\"surname\"" + ":" + "\"" + l.getProfSurname()+ "\"" + ",");
                                    out.println("\"email\"" + ":" + "\"" + l.getProfessor() + "\""+ ",");
                                    out.println("\"subject\"" + ":" + "\"" + l.getSubject() + "\"");
                                    out.println("}");
                                    if(i<list.size()-1){
                                        i++;
                                        out.println(",");
                                    }
                                }
                                out.println("]");
                                out.flush();
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    }
                    break;
                    case "getAllLecturesByStudentAndStatusAndDateAndSubject":{
                        String student = req.getParameter("student");
                        String status = req.getParameter("status");
                        String subject = req.getParameter("subject");
                        String d = req.getParameter("date");
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = format.parse(d);
                        } catch (ParseException e) {
                            System.out.println("wrong date format, please use yyyy-MM-dd");
                            throw new RuntimeException(e);
                        }
                        if(dao == null){
                            out.println("dao is null -- API Lecture doGet");
                        }else{
                            int i=0;
                            Date comodo;
                            try {
                                comodo = format.parse(d);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            format.applyPattern("dd/MM/yyyy");
                            d = format.format(comodo);
                            ArrayList<Lecture> list = dao.getLecturesByStudentAndStatusAndDateAndSubject(student,status,date,subject);
                            out.println("[");
                            for(Lecture l : list){
                                out.println("{");
                                out.println("\"date\"" + ":" + "\"" + d + "\"" + ",");
                                out.println("\"time\"" + ":" + "\"" + l.getTime()+ "\"" + ",");
                                out.println("\"name\"" + ":" + "\"" + l.getProfName()+ "\"" + ",");
                                out.println("\"surname\"" + ":" + "\"" + l.getProfSurname()+ "\"" + ",");
                                out.println("\"email\"" + ":" + "\"" + l.getProfessor() + "\""+ ",");
                                out.println("\"subject\"" + ":" + "\"" + subject + "\"");
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
                    case "getAllLecturesByStatus":{
                        String status = req.getParameter("status");
                        if(dao == null){
                            out.println("dao is null -- API Lecture doGet");
                        }else{
                            int i=0;
                            ArrayList<Lecture> list = dao.getLecturesByStatus(status);
                            System.out.println(list);
                            out.println("[");
                            for(Lecture l : list){
                                out.println("{");
                                out.println("\"date\"" + ":" + "\"" + l.getDate() + "\"" + ",");
                                out.println("\"time\"" + ":" + "\"" + l.getTime()+ "\"" + ",");
                                out.println("\"email\"" + ":" + "\"" + l.getProfessor() + "\""+ ",");
                                out.println("\"subject\"" + ":" + "\"" + l.getSubject() + "\"" + ",");
                                out.println("\"student\"" + ":" + "\"" + l.getStudent() + "\"" );
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
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if(req.getParameter("path")!= null){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                switch (req.getParameter("path")){
                    case "insertLecture": {
                        String student = req.getParameter("student");
                        String status = req.getParameter("status");
                        String professor = req.getParameter("professor");
                        String subject = req.getParameter("subject");
                        String d = req.getParameter("date");
                        String t = req.getParameter("time");
                        Time time = new Time(Integer.parseInt(t.substring(0,2)),Integer.parseInt(t.substring(3,5)),Integer.parseInt(t.substring(6,8)));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        System.out.println(d);
                        try {
                            date = format.parse(d);
                        } catch (ParseException e) {
                            System.out.println("wrong date format, please use yyyy-MM-dd");
                            throw new RuntimeException(e);
                        }
                        if(dao == null){
                            out.println("dao is null -- API Lecture doPost -- insertLecture");
                        }else{
                            try {
                                dao.insertLecture(new Lecture(date,time.toLocalTime(),professor,subject),student,status);
                                jsonResponse.addProperty("message", "Lecture registered successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            } catch (LectureAlreadyExist e) {
                                jsonResponse.addProperty("error", "Failed to register lecture, it already exist");
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                    break;
                    case "deleteLecture":{
                        if(dao == null){
                            out.println("dao is null -- API Lecture doDelete -- deleteLecture");
                        }else{
                            try{
                                String professor = req.getParameter("professor");
                                String subject = req.getParameter("subject");
                                String d = req.getParameter("date");
                                String t = req.getParameter("time");
                                Time time = new Time(Integer.parseInt(t.substring(0,2)),Integer.parseInt(t.substring(3,5)),Integer.parseInt(t.substring(6,8)));
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = null;
                                System.out.println(d);
                                try {
                                    date = format.parse(d);
                                } catch (ParseException e) {
                                    System.out.println("wrong date format, please use yyyy-MM-dd");
                                    throw new RuntimeException(e);
                                }
                                dao.deleteLecture(new Lecture(date,time.toLocalTime(),professor,subject));
                                jsonResponse.addProperty("message", "Lecture deleted successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            }catch (LectureDoesNotExist e){
                                jsonResponse.addProperty("error", "Failed to delete lecture, lecture doesn't exist");
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        JsonObject jsonResponse = new JsonObject();
        if(req.getParameter("path")!= null){
            String token = req.getHeader("Authorization");
            if(token != null && TokenManager.verifyToken(token)){
                switch (req.getParameter("path")){
                    case "changeStatus" : {
                        BufferedReader reader = req.getReader();
                        String body = "";
                        String line;
                        while((line=reader.readLine()) != null){
                            body += line;
                        }
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(body,JsonObject.class);

                        String d = jsonObject.get("date").getAsString();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = format.parse(d);
                        } catch (ParseException e) {
                            System.out.println("wrong date format, please use yyyy-MM-dd");
                            throw new RuntimeException(e);
                        }

                        String t = jsonObject.get("time").getAsString();
                        Time time = new Time(Integer.parseInt(t.substring(0,2)),Integer.parseInt(t.substring(3,5)),Integer.parseInt(t.substring(6,8)));


                        Lecture lecture = new Lecture(date,time.toLocalTime(),jsonObject.get("professor").getAsString(),jsonObject.get("subject").getAsString());
                        if(dao == null){
                            out.println("dao is null -- API Lecture goPut");
                        }else{
                            try {
                                dao.changeStatus(lecture,jsonObject.get("status").getAsString());
                                jsonResponse.addProperty("message", "Lecture status changed successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            } catch (LectureDoesNotExist e) {
                                jsonResponse.addProperty("error", "Failed to update status value in lecture, it doesn't exist");
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                    break;
                    case "changeStatusAndStudent" : {
                        BufferedReader reader = req.getReader();
                        String body = "";
                        String line;
                        while((line=reader.readLine()) != null){
                            body += line;
                        }
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(body,JsonObject.class);

                        String d = jsonObject.get("date").getAsString();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = format.parse(d);
                        } catch (ParseException e) {
                            System.out.println("wrong date format, please use yyyy-MM-dd");
                            throw new RuntimeException(e);
                        }

                        String t = jsonObject.get("time").getAsString();
                        Time time = new Time(Integer.parseInt(t.substring(0,2)),Integer.parseInt(t.substring(3,5)),Integer.parseInt(t.substring(6,8)));
                        String professor = jsonObject.get("professor").getAsString();
                        String subject = jsonObject.get("subject").getAsString();
                        Lecture lecture = new Lecture(date,time.toLocalTime(),professor,subject);
                        if(dao==null){
                            out.println("dao is null -- API Lecture goPut");
                        }else{
                            try {
                                String status = jsonObject.get("status").getAsString();
                                String student = String.valueOf(jsonObject.get("student").getAsJsonNull());
                                dao.changeStatusAndStudent(lecture,status,student);
                                jsonResponse.addProperty("message", "Lecture status and student changed successfully");
                                resp.setStatus(HttpServletResponse.SC_OK);
                            } catch (LectureDoesNotExist e) {
                                jsonResponse.addProperty("error", "Failed to update status and student values in lecture, it doesn't exist");
                                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
