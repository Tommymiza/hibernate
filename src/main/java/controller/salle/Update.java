package controller.salle;

import java.io.*;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Salle;
import org.hibernate.Session;

@WebServlet("/api/salle/update")
public class Update extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            String designation = request.getParameter("designation");
            Salle salle = session.get(Salle.class, request.getParameter("codesalle"));
            salle.setDesignation(designation);
            session.persist(salle);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/salle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}