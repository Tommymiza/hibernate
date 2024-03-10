package controller.prof;

import java.io.*;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Prof;
import org.hibernate.Session;

@WebServlet("/api/prof/delete")
public class Delete extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            Long codeprof = Long.parseLong(request.getParameter("codeprof"));
            Prof prof = session.get(Prof.class, codeprof);
            session.remove(prof);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}