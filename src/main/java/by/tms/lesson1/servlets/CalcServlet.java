package by.tms.lesson1.servlets;

import by.tms.lesson1.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/index/calc")
public class CalcServlet extends HttpServlet {

    public static final String CALC_REQUEST = "<H2>Enter request: http://yourserver:port/calc?num1=[value 1]&num2=[value 2]&action=[sum or diff or mult or div]</H2>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String action = req.getParameter("action");

        printResultPage(req, resp, num1, num2, action);
    }

    private void printResultPage(HttpServletRequest req, HttpServletResponse resp, String num1, String num2, String action) throws IOException {
        resp.getWriter().println(MainServlet.HTML_HEADER);

        HttpSession currentSession = req.getSession();
        if (currentSession.isNew() || currentSession.getAttribute("user") == null) {
            resp.getWriter().println(MainServlet.REGISTER_REQUEST);
        } else {
            topBlockOfPage(req, resp);

            MainServlet.printHistory(resp, req.getSession());

            List<String> history = (List<String>) req.getSession().getAttribute("history");
            currentResultHandler(resp, num1, num2, action, history);
        }

        resp.getWriter().println(MainServlet.HTML_FOOTER);
    }

    private void topBlockOfPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser != null) {
            resp.getWriter().println("<H1>Hello, " + currentUser.getName() + "</H1>");
        } else {
            resp.getWriter().println(MainServlet.REGISTER_REQUEST);
        }
        resp.getWriter().println(CALC_REQUEST);
    }

    private void currentResultHandler(HttpServletResponse resp, String num1, String num2, String action, List<String> history) throws IOException {
        if ((num1 != null) & (num2 != null) & (action != null)) {
            Integer result = getResult(num1, num2, action);
            if (result != null) {
                String currentResult = "num1 = " + num1 + ", num2 = " + num2 + ", action: " + action + ", result = " + result;
                history.add(currentResult);
                resp.getWriter().println("<H3>Current result:</H3>");
                resp.getWriter().println((currentResult));
            } else {
                resp.getWriter().println("<H3>Current request is incorrect!</H3>");
            }
        } else {
            resp.getWriter().println("<H3>Current request is incorrect!</H3>");
        }
    }

    private Integer getResult(String num1, String num2, String action) {
        int result;
        switch (action) {
            case "sum":
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                break;
            case "diff":
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                break;
            case "mult":
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                break;
            case "div":
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                break;
            default:
                return null;
        }

        return result;
    }
}
