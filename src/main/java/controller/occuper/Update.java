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
import type.Grade;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/occuper/update")
public class Update extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            Prof p = session.get(Prof.class, request.getParameter("codeprof"));
            Salle s = session.get(Salle.class, request.getParameter("codesalle"));
            Occuper c = session.get(Occuper.class, request.getParameter("id"));
            LocalDateTime t = LocalDateTime.parse(request.getParameter("date"));
            c.setProfesseur(p);
            c.setSalle(s);
            c.setDate(t);
            session.persist(c);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/occuper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}