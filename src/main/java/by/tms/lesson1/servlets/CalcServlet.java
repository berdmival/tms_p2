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

@WebServlet(name = "calcServlet", urlPatterns = "/index/calc")
public class CalcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/pages/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String action = req.getParameter("action");

        Integer result = getResult(num1, num2, action);

        StringBuilder currentResult = new StringBuilder()
                .append("<b>").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("</b>: ")
                .append("num1 = ").append(num1)
                .append(", num2 = ").append(num2)
                .append(", action: ").append(action)
                .append(", result = ").append(result);

        ((List<String>) req.getSession().getAttribute("history")).add(currentResult.toString());

        currentResult.insert(0, "Current result: ");
        req.setAttribute("message", currentResult.toString());

        getServletContext().getRequestDispatcher("/WEB-INF/pages/calc.jsp").forward(req, resp);
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
