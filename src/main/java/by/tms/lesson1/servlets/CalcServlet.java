package by.tms.lesson1.servlets;

import by.tms.lesson1.entities.CalcExpressionInt;

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
            CalcExpressionInt calcExpressionInt = new CalcExpressionInt(Integer.parseInt(num1), Integer.parseInt(num2), action);
            String currentResult = calcExpressionInt.resultToString();

            ((List<String>) req.getSession().getAttribute("history")).add(currentResult);

            req.setAttribute("message", currentResult);

            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
        } else {
            req.setAttribute("message", CHECK_YOUR_INPUT_PLEASE_MESSAGE);
            getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
        }
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
