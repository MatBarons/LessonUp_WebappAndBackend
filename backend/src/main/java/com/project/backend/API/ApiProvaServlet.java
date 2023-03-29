package com.project.backend.API;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "ApiProvaServlet", value = "/api/PROVA")

public class ApiProvaServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("{\"dio\":\"Marco\"}");
        out.flush();

    }

}
