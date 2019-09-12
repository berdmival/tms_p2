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

@WebServlet(name = "authServlet", urlPatterns = "/auth")
public class AuthServlet extends HttpServlet {

    public static final String AUTH_JSP = "/pages/auth.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
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
                    resp.sendRedirect("/");
                }
            }
            if (!authenticationSuccessful) {
                req.setAttribute("message", "Name or password is incorrect!");
                getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Name or password is incorrect!");
            getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
        }
    }
}
