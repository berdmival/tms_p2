package by.tms.lesson1.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainServlet", urlPatterns = "/index")
public class MainServlet extends HttpServlet {

    public static final String INDEX_JSP = "/WEB-INF/pages/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(INDEX_JSP).forward(req, resp);
    }
}
