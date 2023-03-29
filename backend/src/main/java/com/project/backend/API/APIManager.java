
/* package com.project.backend.API;

import com.project.backend.Dao.DaoCourse;
import com.project.backend.Dao.DaoLecture;
import com.project.backend.Dao.DaoUser;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DaoInit", value = "/DaoInit", loadOnStartup = 1, asyncSupported = false)
public class APIManager extends HttpServlet{

    public void init(ServletConfig c){
        DaoUser daoU = new DaoUser();
        c.getServletContext().setAttribute("daoU",daoU);
        DaoLecture daoL = new DaoLecture();
        c.getServletContext().setAttribute("daoL",daoL);
        DaoCourse daoC = new DaoCourse();
        c.getServletContext().setAttribute("daoC",daoC);
    }
}


 */