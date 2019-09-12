package by.tms.lesson1.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "calcServlet", urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {

    public static final String CALC_JSP = "/pages/calc.jsp";
    public static final String HISTORY_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String action = req.getParameter("action");

        if (validateParameters(num1, num2, action)) {
            Integer result = getResult(num1, num2, action);

            StringBuilder currentResult = new StringBuilder()
                    .append("<b>").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(HISTORY_DATE_PATTERN))).append("</b>: ")
                    .append("num1 = ").append(num1)
                    .append(", num2 = ").append(num2)
                    .append(", action: ").append(action)
                    .append(", result = ").append(result);

            ((List<String>) req.getSession().getAttribute("history")).add(currentResult.toString());

            currentResult.insert(0, "Current result: ");
            req.setAttribute("message", currentResult.toString());

            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
        } else {
            req.setAttribute("message", "Check your input, please");
            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
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

    private boolean validateParameters(String num1, String num2, String action) {
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
