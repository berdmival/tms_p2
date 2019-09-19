package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logoutServlet", urlPatterns = "/index/logout")
public class LogoutServlet extends HttpServlet {

    public static final String INDEX_PATH = "/index";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ((User) request.getSession().getAttribute("user")).logout();
        request.getSession().invalidate();
        response.sendRedirect(INDEX_PATH);
    }
}
