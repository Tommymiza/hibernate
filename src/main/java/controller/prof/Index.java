package controller.prof;

import java.io.*;
import java.util.List;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Prof;
import org.hibernate.Session;
import type.Grade;

@WebServlet("")
public class Index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            String search = request.getParameter("search");
            if (search != null) {
                List<Prof> profs = session.createQuery("from Prof where concat(codeprof, ' ', nom, ' ', prenom, ' ', prenom, ' ', nom) like :search")
                        .setParameter("search", "%" + search + "%").getResultList();
                request.setAttribute("profs", profs);
                request.setAttribute("search", search);
                request.getRequestDispatcher("/WEB-INF/prof.jsp").forward(request, response);
                return;
            }
            List<Prof> profs = session.createQuery("from Prof").getResultList();
            request.setAttribute("profs", profs);
            session.close();
            request.getRequestDispatcher("/WEB-INF/prof.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Grade grade = Grade.valueOf(request.getParameter("grade"));
            Prof prof = new Prof(nom, prenom, grade);
            session.persist(prof);
            session.getTransaction().commit();
            session.close();
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}