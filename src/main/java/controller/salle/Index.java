package controller.salle;

import java.io.*;
import java.util.List;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Salle;
import org.hibernate.Session;

@WebServlet("/salle")
public class Index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            List<Salle> salles = session.createQuery("from Salle ").getResultList();
            session.close();
            request.setAttribute("salles", salles);
            request.getRequestDispatcher("/WEB-INF/salle.jsp").forward(request, response);
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
            String designation = request.getParameter("designation");
            Salle salle = new Salle(designation);
            session.persist(salle);
            session.getTransaction().commit();
            session.close();
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}