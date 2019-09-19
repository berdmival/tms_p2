package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "authServlet", urlPatterns = "/index/auth")
public class AuthServlet extends HttpServlet {

    private static final String AUTH_JSP = "/WEB-INF/pages/auth.jsp";
    private static final String INDEX_PATH = "/index";
    private static final String NAME_OR_PASSWORD_IS_INCORRECT_MESSAGE = "Name or password is incorrect!";

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
                    if (!user.isLogin()) {
                        authenticationSuccessful = true;
                        user.login();
                        currentSession.setAttribute("user", user);
                        resp.sendRedirect(INDEX_PATH);
                    } else {
                        req.setAttribute("message", "User " + user.getName() + " is already login!");
                        getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
                    }
                }
            }
            if (!authenticationSuccessful) {
                req.setAttribute("message", NAME_OR_PASSWORD_IS_INCORRECT_MESSAGE);
                getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
            }
        } else {
            req.setAttribute("message", NAME_OR_PASSWORD_IS_INCORRECT_MESSAGE);
            getServletContext().getRequestDispatcher(AUTH_JSP).forward(req, resp);
        }
    }
}
