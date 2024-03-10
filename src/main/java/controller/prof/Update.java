package controller.prof;

import java.io.*;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Prof;
import org.hibernate.Session;
import type.Grade;

@WebServlet("/api/prof/update")
public class Update extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Grade grade = Grade.valueOf(request.getParameter("grade"));
            Prof prof = session.get(Prof.class, request.getParameter("codeprof"));
            prof.setNom(nom);
            prof.setPrenom(prenom);
            prof.setGrade(grade);
            session.persist(prof);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}