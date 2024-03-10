package controller.salle;

import java.io.*;

import database.Db;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Salle;
import org.hibernate.Session;

@WebServlet("/api/salle/delete")
public class Delete extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            Long codesalle = Long.parseLong(request.getParameter("codesalle"));
            Salle salle = session.get(Salle.class, codesalle);
            session.remove(salle);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/salle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}