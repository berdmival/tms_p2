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

@WebServlet(name = "logoutServlet", urlPatterns = "/index/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ((Map<String, HttpSession>) getServletContext().getAttribute("sessions")).remove(request.getSession().getId());
        request.getSession().invalidate();
        response.sendRedirect("/index");
    }
}
