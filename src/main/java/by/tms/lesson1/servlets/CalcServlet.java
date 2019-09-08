package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(name = "calcServlet", urlPatterns = "/index/calc")
public class CalcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String action = req.getParameter("action");

        printResultPage(req, resp, num1, num2, action);
    }

    private void printResultPage(HttpServletRequest req, HttpServletResponse resp, String num1, String num2, String action) throws IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();
        if (currentSession.isNew() || currentSession.getAttribute("user") == null) {
            resp.getWriter().println(REGISTER_REQUEST);
        } else {
            topBlockOfPage(req, resp);

            MainServlet.printHistory(resp, req.getSession());

            List<String> history = (List<String>) req.getSession().getAttribute("history");
            currentResultHandler(resp, num1, num2, action, history);
        }

        resp.getWriter().println(HTML_FOOTER);
    }

    private void topBlockOfPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser != null) {
            resp.getWriter().println(LOGOUT_BUTTON);

            resp.getWriter().println("<H1>Hello, " + currentUser.getName() + "</H1>");
        } else {
            resp.getWriter().println(REGISTER_REQUEST);
        }
        resp.getWriter().println(CALC_REQUEST);
    }

    private void currentResultHandler(HttpServletResponse resp, String num1, String num2, String action, List<String> history) throws IOException {
        if (validateParametors(num1, num2, action)) {
            Integer result = getResult(num1, num2, action);
            StringBuilder currentResult = new StringBuilder().append("num1 = ").append(num1)
                    .append(", num2 = ").append(num2)
                    .append(", action: ").append(action)
                    .append(", result = ").append(result);
            history.add(currentResult.toString());
            resp.getWriter().println("<H3>Current result:</H3>");
            resp.getWriter().println((currentResult));
        } else {
            resp.getWriter().println(CURRENT_REQUEST_IS_INCORRECT);
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

    private boolean validateParametors(String num1, String num2, String action) {
        boolean validNumbers = (isIntByJonas(num1) & isIntByJonas(num2));
        boolean validAction = false;
        if (action != null) {
            switch (action) {
                case "sum":
                case "diff":
                case "mult":
                case "div":
                    validAction = true;
            }
        }
        return (validNumbers & validAction);
    }

    private boolean isIntByJonas(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
}
