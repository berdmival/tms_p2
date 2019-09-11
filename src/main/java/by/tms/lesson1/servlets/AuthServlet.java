package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(name = "authServlet", urlPatterns = "/index/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserName = req.getParameter("name");
        String currentUserPassword = req.getParameter("password");

        HttpSession currentSession = req.getSession();

        if ((currentUserName != null) & (currentUserPassword != null)) {
            User currentUser = new User();
            currentUser.setName(currentUserName);
            currentUser.setPassword(currentUserPassword);
            ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("users");
            boolean authenticationSuccessful = false;
            for (User user : users) {
                if (user.equals(currentUser)) {
                    authenticationSuccessful = true;
                    currentSession.setAttribute("user", user);
                    ((Map<String, HttpSession>) getServletContext().getAttribute("sessions")).put(currentSession.getId(), currentSession);
                    resp.sendRedirect("/index");
                }
            }
            if (!authenticationSuccessful) {
                req.setAttribute("message", AUTH_FAIL);
                getServletContext().getRequestDispatcher("/WEB-INF/pages/auth.jsp").forward(req, resp);
            }
        } else {
            resp.getWriter().println(REGISTER_REQUEST);
        }
    }
}
