package controller.occuper;

import database.Db;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Occuper;
import models.Prof;
import models.Salle;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/occuper")
public class Index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            session.beginTransaction();
            List<Occuper> occupers = (List<Occuper>) session.createQuery("from Occuper").getResultList();
            List<Prof> profs = (List<Prof>) session.createQuery("from Prof").getResultList();
            List<Salle> salles = (List<Salle>) session.createQuery("from Salle").getResultList();
            request.setAttribute("occupers", occupers);
            request.setAttribute("profs", profs);
            request.setAttribute("salles", salles);
            request.getRequestDispatcher("/WEB-INF/occuper.jsp").forward(request, response);
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
            session.beginTransaction();
            Prof p = session.get(Prof.class, request.getParameter("codeprof"));
            Salle s = session.get(Salle.class, request.getParameter("codesalle"));
            LocalDateTime t = LocalDateTime.parse(request.getParameter("date"));
            Occuper o = new Occuper(p, s, t);
            session.persist(o);
            session.getTransaction().commit();
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}