package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.user.User;

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

    private static final String REG_JSP = "/WEB-INF/pages/reg.jsp";
    private static final String INDEX_AUTH_PATH = "/index/auth";
    private static final String CHECK_YOUR_INPUT_PLEASE_MESSAGE = "Check your input, please!";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(REG_JSP).forward(req, resp);
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
                    req.setAttribute("message", "User " + user.getName() + " is already exists!");
                    getServletContext().getRequestDispatcher(REG_JSP).forward(req, resp);
                }
            }
            if (!userExists) {
                users.add(currentUser);
                resp.sendRedirect(INDEX_AUTH_PATH);
            }
        } else {
            req.setAttribute("message", CHECK_YOUR_INPUT_PLEASE_MESSAGE);
            getServletContext().getRequestDispatcher(REG_JSP).forward(req, resp);
        }
    }
}
