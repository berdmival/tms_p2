package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(name = "regServlet", urlPatterns = "/index/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();
        ServletContext servletContext = getServletContext();
        if (currentSession.isNew() || currentSession.getAttribute("user") == null) {
            String currentUserName = req.getParameter("name");
            String currentUserAge = req.getParameter("age");
            String currentUserPassword = req.getParameter("password");
            if ((currentUserName != null) & (currentUserAge != null) & (currentUserPassword != null)) {
                User currentUser = new User(currentUserName, Integer.parseInt(currentUserAge), currentUserPassword);
                ArrayList<User> users = (ArrayList<User>) servletContext.getAttribute("users");
                boolean userExists = false;
                for (User user : users) {
                    if (user.getName().equals(currentUser.getName())) {
                        userExists = true;
                        resp.getWriter().println("<H1>User " + user.getName() + " is already exists</H1>");
                    }
                }
                if (!userExists) {
                    users.add(currentUser);
                    resp.getWriter().println("<H1>Welcome, " + currentUser.getName()
                            + "! Please, authenticate manually, if your browser don't redirect automatically</H1>");
                    resp.sendRedirect("/index/auth?name="+currentUserName+"&password="+currentUserPassword);
                }
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        } else {
            resp.getWriter().println(LOGOUT_BUTTON);
            resp.getWriter().println("<H1>Hello, " + ((User) (currentSession.getAttribute("user"))).getName() + "</H1>");
        }

        resp.getWriter().println(HTML_FOOTER);
    }
}
