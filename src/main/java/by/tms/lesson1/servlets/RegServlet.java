package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "regServlet", urlPatterns = "/index/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserName = req.getParameter("name");
        String currentUserAge = req.getParameter("age");
        String currentUserPassword = req.getParameter("password");

        ServletContext servletContext = getServletContext();

        if ((currentUserName != null) & (currentUserAge != null) & (currentUserPassword != null)) {
            User currentUser = new User(currentUserName, Integer.parseInt(currentUserAge), currentUserPassword);
            ArrayList<User> users = (ArrayList<User>) servletContext.getAttribute("users");
            boolean userExists = false;
            for (User user : users) {
                if (user.getName().equals(currentUser.getName())) {
                    userExists = true;
                    req.setAttribute("message", "User " + user.getName() + " is already exists");
                    getServletContext().getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(req, resp);
                }
            }
            if (!userExists) {
                users.add(currentUser);
                resp.sendRedirect("/index/auth");
            }
        }
    }
}
