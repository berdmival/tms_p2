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
import java.util.Map;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(urlPatterns = "/index")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();

        if (currentSession.isNew()) {
            resp.getWriter().println(REGISTER_REQUEST);
        } else {
            User currentUser = (User) currentSession.getAttribute("user");
            if (currentUser != null) {
                resp.getWriter().println("<H1>Hello, " + currentUser.getName() + "</H1>");
                printCurrentSessionFromMapOfSessions(resp, currentSession);
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        }

        resp.getWriter().println(HTML_FOOTER);

    }

    private void printCurrentSessionFromMapOfSessions(HttpServletResponse resp, HttpSession currentSession) throws IOException {
        Map<String, HttpSession> listOfSessions = (Map<String, HttpSession>) getServletContext().getAttribute("sessions");

        if (currentSession.getAttribute("user") == null) {
            listOfSessions.forEach((id, session) -> {
                try {
                    resp.getWriter().println("<H3>Session with id " + id + ": "
                            + ((User)(session.getAttribute("user"))).toString() + "</H3>");

                    printHistory(resp, session);

                    resp.getWriter().println(LINE);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            resp.getWriter().println(SESSIONS_MAP_IS_EMPTY);
        }
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
