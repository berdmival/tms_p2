package by.tms.lesson1.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebFilter(filterName = "calcArgFilter", servletNames = "calcServlet")
public class CalcArgFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if ((req.getParameter("num1") != null) & (req.getParameter("num2") != null) & (req.getParameter("action") != null)) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().println(HTML_HEADER);
            res.getWriter().println(CALC_REQUEST);
            res.getWriter().println(HTML_FOOTER);
        }
    }
}
