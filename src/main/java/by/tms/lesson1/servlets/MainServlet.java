package by.tms.lesson1.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(name = "mainServlet", urlPatterns = "/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }

    public static void printHistory(HttpServletResponse resp, HttpSession session) throws IOException {
        List<String> historyList = (List<String>) session.getAttribute("history");

        resp.getWriter().println(HISTORY);

        if ((historyList != null) & (historyList.size() > 0)) {
            historyList.forEach(history -> {
                try {
                    resp.getWriter().println(history + "<br>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            resp.getWriter().println(HISTORY_IS_EMPTY);
        }
    }
}
