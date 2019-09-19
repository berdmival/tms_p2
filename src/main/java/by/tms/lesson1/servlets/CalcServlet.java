package by.tms.lesson1.servlets;

import by.tms.lesson1.action.ActionTypeEnum;
import by.tms.lesson1.entities.expression.CalcExpression;
import by.tms.lesson1.entities.expression.CalcExpressionDouble;
import by.tms.lesson1.entities.user.User;
import by.tms.lesson1.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String num1 = req.getParameter("num1").replaceAll(",", ".");
        String num2 = req.getParameter("num2").replaceAll(",", ".");
        String action = req.getParameter("action").toUpperCase();

        if (Validator.isNumeric(num1) && Validator.isNumeric(num2) && Validator.isValidAction(action)) {
            if (Validator.isValidExpression(Double.parseDouble(num1), Double.parseDouble(num2), ActionTypeEnum.valueOf(action))) {
                CalcExpression calcExpression = new CalcExpressionDouble(Double.parseDouble(num1), Double.parseDouble(num2), ActionTypeEnum.valueOf(action));

                ((User) req.getSession().getAttribute("user")).getHistory().add(calcExpression);

                req.setAttribute("message", ((CalcExpressionDouble) calcExpression).getResult());
            } else {
                req.setAttribute("message", CHECK_YOUR_INPUT_PLEASE_MESSAGE);
            }
        } else {
            req.setAttribute("message", CHECK_YOUR_INPUT_PLEASE_MESSAGE);
        }
        getServletContext().getRequestDispatcher(CALC_JSP).forward(req, resp);
    }

}
