package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.expression.CalcExpression;
import by.tms.lesson1.entities.expression.CalcExpressionDouble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "calcServlet", urlPatterns = "/index/calc")
public class CalcServlet extends HttpServlet {

    private static final String CALC_JSP = "/WEB-INF/pages/calc.jsp";
    private static final String CHECK_YOUR_INPUT_PLEASE_MESSAGE = "Check your input, please";

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
            CalcExpression calcExpression = new CalcExpressionDouble(Double.parseDouble(num1), Double.parseDouble(num2), action);

            ((List<CalcExpression>) req.getSession().getAttribute("history")).add(calcExpression);

            req.setAttribute("message", ((CalcExpressionDouble) calcExpression).getResult());

            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
        } else {
            req.setAttribute("message", CHECK_YOUR_INPUT_PLEASE_MESSAGE);
            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
        }
    }

    private boolean validateParameters(String num1, String num2, String action) {
        boolean validNumbers = (isNumeric(num1) & isNumeric(num2));
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

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        str = str.replaceAll(",", ".");
        int dotCount = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                if (i == length - 1) {
                    return false;
                } else if (c == '.') {
                    if (++dotCount > 1) {
                        return false;
                    }
                } else if (i != 0 || c != '+' && c != '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
