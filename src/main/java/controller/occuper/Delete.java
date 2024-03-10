package controller.occuper;

import database.Db;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Occuper;
import models.Prof;
import org.hibernate.Session;

import java.io.IOException;

@WebServlet("/api/occuper/delete")
public class Delete extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = Db.initialize();
        try {
            assert session != null;
            session.beginTransaction();
            Long id = Long.parseLong(request.getParameter("id"));
            Occuper c = session.get(Occuper.class, id);
            session.remove(c);
            session.getTransaction().commit();
            session.close();
            response.sendRedirect("/occuper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}